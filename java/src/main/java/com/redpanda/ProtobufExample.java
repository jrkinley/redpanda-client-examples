package com.redpanda;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
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

import com.redpanda.Stock.NasdaqHistorical;

import io.confluent.kafka.serializers.protobuf.KafkaProtobufDeserializer;
import io.confluent.kafka.serializers.protobuf.KafkaProtobufSerializer;

/*
 * Compile .proto to generate classes:
 * protoc -I=../data/ --java_out=src/main/java/ ../data/stock.proto
 */
public class ProtobufExample extends ClientExample {
    private static String DEFAULT_TOPIC = "nasdaq_historical_proto";

    ProtobufExample(Properties props, String topic) {
        super(props, topic);
    }

    @Override
    protected void produce(String filename) {
        final KafkaProducer<String, NasdaqHistorical> producer = new KafkaProducer<String, NasdaqHistorical>(
                properties);
        String symbol = getSymbol(filename);
        try {
            InputStream is = ProtobufExample.class.getClassLoader().getResourceAsStream(filename);
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
                NasdaqHistorical stock = NasdaqHistorical.newBuilder()
                        .setDate(parts[0])
                        .setLast(parts[1])
                        .setVolume(volume)
                        .setOpen(parts[3])
                        .setHigh(parts[4])
                        .setLow(parts[5])
                        .build();
                ProducerRecord<String, NasdaqHistorical> record = new ProducerRecord<String, NasdaqHistorical>(
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
        final KafkaConsumer<String, NasdaqHistorical> consumer = new KafkaConsumer<String, NasdaqHistorical>(
                properties);
        consumer.subscribe(Arrays.asList(topic));
        try {
            while (true) {
                ConsumerRecords<String, NasdaqHistorical> records = consumer.poll(Duration.ofMillis(100));
                for (ConsumerRecord<String, NasdaqHistorical> record : records) {
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
        Options options = ProtobufExample.getOptions();

        CommandLineParser parser = new DefaultParser();
        HelpFormatter formatter = new HelpFormatter();
        CommandLine cmd = null;
        try {
            cmd = parser.parse(options, args);
        } catch (ParseException e) {
            System.out.println(e.getMessage());
            formatter.printHelp("produce-proto", options);
            System.exit(1);
        }
        Properties props = ProtobufExample.getProperties(cmd);

        // Additional properties for Protobuf
        props.put("value.serializer", KafkaProtobufSerializer.class.getName());
        props.put("value.deserializer", KafkaProtobufDeserializer.class.getName());
        props.put("specific.protobuf.value.type", NasdaqHistorical.class);

        final String topic = cmd.getOptionValue("topic", DEFAULT_TOPIC);
        ProtobufExample client = new ProtobufExample(props, topic);
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
