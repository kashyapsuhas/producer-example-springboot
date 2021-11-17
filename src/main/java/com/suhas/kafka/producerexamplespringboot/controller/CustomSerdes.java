package com.suhas.kafka.producerexamplespringboot.controller;

import com.suhas.kafka.producerexamplespringboot.model.TransactionData;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serdes;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.kafka.support.serializer.JsonSerializer;

public final  class CustomSerdes {

    static public final class TranscationDataSerde extends Serdes.WrapperSerde<TransactionData> {
        public TranscationDataSerde() {
            super(new JsonSerializer<>(),
                    new JsonDeserializer<>(TransactionData.class));
        }
    }

    public static Serde<TransactionData> TranscationData() {
        return new CustomSerdes.TranscationDataSerde();
    }

}
