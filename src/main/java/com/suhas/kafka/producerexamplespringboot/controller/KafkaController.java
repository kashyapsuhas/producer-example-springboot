package com.suhas.kafka.producerexamplespringboot.controller;

import com.suhas.kafka.producerexamplespringboot.model.TransactionData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping ("kafka")
public class KafkaController {

    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaController.class);
    private static final String TOPIC = "kafka-DSL";


    @Autowired
    KafkaTemplate<String, TransactionData> template;

    @GetMapping ("/publish/{username}/{amount}")
    public String publishMessage(@PathVariable String username, @PathVariable int amount) {

        template.send(TOPIC, TransactionData.builder().customerName(username).amountDebited(amount).build());
        LOGGER.info("object of username : {} with amount {} was published successfully ", username , amount);

        return "Message of Object type user: "+username+" with amount: " +amount +" was Published";
    }

}
