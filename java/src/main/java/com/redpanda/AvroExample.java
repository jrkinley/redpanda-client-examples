package com.redpanda;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import org.apache.avro.Schema;
import org.apache.avro.Schema.Parser;
import org.apache.avro.generic.GenericData;
import org.apache.avro.generic.GenericRecord;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import io.confluent.kafka.serializers.KafkaAvroDeserializer;
import io.confluent.kafka.serializers.KafkaAvroSerializer;

public class AvroExample extends ClientExample {
    private static String SCHEMA_AVSC = "stock.avsc";
    private static String DEFAULT_TOPIC = "nasdaq_historical_avro";

    AvroExample(Properties props, String topic) {
        super(props, topic);
    }

    private String getSchema() {
        StringBuilder schema = new StringBuilder();
        InputStream is = AvroExample.class.getClassLoader().getResourceAsStream(SCHEMA_AVSC);
        InputStreamReader sr = new InputStreamReader(is, StandardCharsets.UTF_8);
        try (BufferedReader reader = new BufferedReader(sr)) {
            String line;
            while ((line = reader.readLine()) != null) {
                schema.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.printf("Schema: %s", schema.toString());
        return schema.toString();
    }

    @Override
    protected void produce(String filename) {
        String nasdaqSchema = getSchema();
        Parser parser = new Parser();
        Schema schema = parser.parse(nasdaqSchema);

        final KafkaProducer<String, GenericRecord> producer = new KafkaProducer<String, GenericRecord>(properties);
        String symbol = getSymbol(filename);
        try {
            InputStream is = AvroExample.class.getClassLoader().getResourceAsStream(filename);
            InputStreamReader sr = new InputStreamReader(is, StandardCharsets.UTF_8);
            BufferedReader reader = new BufferedReader(sr);
            reader.readLine(); // Ignore header
            String line = reader.readLine();
            while (line != null) {
                String[] parts = line.split(",");
                Float volume = 0.0F;
                try {
                    volume = Float.parseFloat(parts[2]);
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }

                GenericRecord stock = new GenericData.Record(schema);
                stock.put("date", parts[0]);
                stock.put("last", parts[1]);
                stock.put("volume", volume);
                stock.put("open", parts[3]);
                stock.put("high", parts[4]);
                stock.put("low", parts[5]);

                ProducerRecord<String, GenericRecord> record = new ProducerRecord<String, GenericRecord>(
                        topic, symbol, stock);
                producer.send(record, new Callback() {
                    @Override
                    public void onCompletion(RecordMetadata metadata, Exception exception) {
                        if (exception != null) {
                            exception.printStackTrace();
                        } else {
                            System.out.printf("Produced: [Topic: %s \tPartition: %d \tOffset: %d \tKey: %s]%n",
                                    metadata.topic(), metadata.partition(), metadata.offset(), record.key());
                        }
                    }
                });
                Thread.sleep(100);
                line = reader.readLine();
            }
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            producer.flush();
            producer.close();
        }
    }

    @Override
    protected void consume() {
        final KafkaConsumer<String, GenericRecord> consumer = new KafkaConsumer<String, GenericRecord>(properties);
        consumer.subscribe(Arrays.asList(topic));
        try {
            while (true) {
                ConsumerRecords<String, GenericRecord> records = consumer.poll(Duration.ofMillis(30000));
                if (records.isEmpty()) {
                    break;
                }
                for (ConsumerRecord<String, GenericRecord> record : records) {
                    System.out.printf("Consumed: [Topic: %s \tPartition: %d \tOffset: %d \tKey: %s]%n",
                            record.topic(), record.partition(), record.offset(), record.key());
                }
                Thread.sleep(2000);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println("Stopped consumer");
            consumer.close();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Options options = AvroExample.getOptions();

        CommandLineParser parser = new DefaultParser();
        HelpFormatter formatter = new HelpFormatter();
        CommandLine cmd = null;
        try {
            cmd = parser.parse(options, args);
        } catch (ParseException e) {
            System.out.println(e.getMessage());
            formatter.printHelp("redpanda-examples", options);
            System.exit(1);
        }
        Properties props = AvroExample.getProperties(cmd);

        // Additional properties for Avro
        props.put("value.serializer", KafkaAvroSerializer.class.getName());
        props.put("value.deserializer", KafkaAvroDeserializer.class.getName());

        final String topic = cmd.getOptionValue("topic", DEFAULT_TOPIC);
        AvroExample client = new AvroExample(props, topic);
        client.createTopic();

        List<Thread> producers = client.write();
        List<Thread> consumers = client.read();
        for (Thread c : consumers) {
            c.join();
        }
        for (Thread p : producers) {
            p.join();
        }
    }
}
