package com.suhas.kafka.producerexamplespringboot.controller;

import com.suhas.kafka.producerexamplespringboot.model.TransactionData;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.Topology;
import org.apache.kafka.streams.errors.LogAndContinueExceptionHandler;
import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.kstream.Produced;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;

import static org.apache.kafka.streams.StreamsConfig.APPLICATION_ID_CONFIG;
import static org.apache.kafka.streams.StreamsConfig.BOOTSTRAP_SERVERS_CONFIG;
import static org.apache.kafka.streams.StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG;
import static org.apache.kafka.streams.StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG;

public class StreamController {

    private static final String INPUT_TOPIC = "kafka-DSL";
    private static final String OUTPUT_TOPIC = "kafka-DSL1";
    private static final Logger LOGGER = LoggerFactory.getLogger(StreamController.class);


    public static void main(String[] args) {

        Properties streamsConfiguration = new Properties();
        streamsConfiguration.put(APPLICATION_ID_CONFIG,"streams");
        streamsConfiguration.put(BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092");
        streamsConfiguration.put(DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass());
        streamsConfiguration.put(DEFAULT_VALUE_SERDE_CLASS_CONFIG,CustomSerdes.TranscationData().getClass());
        streamsConfiguration.put("default.deserialization.exception.handler", LogAndContinueExceptionHandler.class);

        LOGGER.info("started");

        StreamsBuilder builder = new StreamsBuilder();

        builder.stream(
                INPUT_TOPIC,
                Consumed.with(Serdes.String(), CustomSerdes.TranscationData()))
                .filter((s, transactionData) -> transactionData.getAmountDebited() > 50000)
                .mapValues(TransactionData::getCustomerName)
                .to(OUTPUT_TOPIC, Produced.with(Serdes.String(),Serdes.String()));

        Topology topology = builder.build();
        KafkaStreams streams = new KafkaStreams(topology, streamsConfiguration);
        streams.start();

        Runtime.getRuntime().addShutdownHook(new Thread(()->{
            LOGGER.info("shutting down");
            streams.close();
        }));
    }
}


