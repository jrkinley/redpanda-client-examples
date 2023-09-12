package com.redpanda;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Properties;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;

public abstract class ClientExample {
    protected Properties properties;
    protected String topic;

    ClientExample(Properties props, String topic) {
        this.properties = props;
        this.topic = topic;
    }

    private static String[] INPUT_FILES = {
            "HistoricalData_COKE_5Y.csv",
            "HistoricalData_GOOGL_5Y.csv",
            "HistoricalData_NVDA_5Y.csv"
    };

    protected static String getSymbol(String filename) {
        String[] parts = filename.split("_");
        if (parts.length == 3) {
            return parts[1];
        } else {
            return null;
        }
    }

    protected static Options getOptions() {
        Options options = new Options();
        options.addOption(new Option("s", "seed", true, "Kafka API bootstrap servers"));
        options.addOption(new Option("r", "registry", true, "Schema Registry URL"));
        options.addOption(new Option("t", "topic", true, "Produce events to this topic"));
        options.addOption(new Option("u", "username", true, "SASL username"));
        options.addOption(new Option("p", "password", true, "SASL password"));
        return options;
    }

    protected static Properties getProperties(CommandLine cmd) {
        Properties props = new Properties();
        props.put("bootstrap.servers", cmd.getOptionValue("seed", "localhost:9092"));
        props.put("schema.registry.url", cmd.getOptionValue("registry", "http://localhost:8081"));

        props.put("key.serializer", StringSerializer.class.getName());
        props.put("key.deserializer", StringDeserializer.class.getName());

        props.put("group.id", ClientExample.class.getName());
        props.put("auto.offset.reset", "earliest");
        props.put("enable.auto.commit", "false");

        final String username = cmd.getOptionValue("username");
        final String password = cmd.getOptionValue("password");

        if (username != null && password != null) {
            // Enable SASL-SCRAM
            StringBuilder jaasConfig = new StringBuilder();
            jaasConfig.append("org.apache.kafka.common.security.scram.ScramLoginModule ");
            jaasConfig.append("required ");
            jaasConfig.append(String.format("username='%s' ", username));
            jaasConfig.append(String.format("password='%s';", password));

            props.put("security.protocol", "SASL_SSL");
            props.put("sasl.mechanism", "SCRAM-SHA-256");
            props.put("sasl.jaas.config", jaasConfig.toString());

            // Schema Registry basic authentication
            props.put("basic.auth.credentials.source", "USER_INFO");
            props.put("basic.auth.user.info", String.format("%s:%s", username, password));
        }
        return props;
    }

    protected void createTopic() {
        final AdminClient admin = AdminClient.create(properties);
        try {
            admin.deleteTopics(Collections.singletonList(topic));
            final NewTopic newTopic = new NewTopic(topic, Optional.empty(), Optional.empty());
            admin.createTopics(Collections.singletonList(newTopic));
        } finally {
            admin.close();
        }
    }

    protected abstract void produce(String filename);

    protected abstract void consume();

    protected List<Thread> write() {
        List<Thread> producers = new ArrayList<>();
        for (String file : INPUT_FILES) {
            Thread t = new Thread(() -> produce(file));
            producers.add(t);
            t.start();
        }
        return producers;
    }

    protected List<Thread> read() {
        List<Thread> consumers = new ArrayList<>();
        Thread t = new Thread(() -> consume());
        consumers.add(t);
        t.start();
        return consumers;
    }
}
