package com.suhas.kafka.producerexamplespringboot.controller;

import com.suhas.kafka.producerexamplespringboot.model.UserData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("kafka")
public class KafkaController {

    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaController.class);
    private static final String TOPIC ="kafka-DSL";


    @Autowired
    KafkaTemplate<String,UserData> template;

    @GetMapping ("/publish/{username}")
    public String publishMessage(@PathVariable String username){
        int count =0;

        template.send(TOPIC,UserData.builder().customerName(username).amountDeposited(5000+count).goldInGrams(10+count).build());
        LOGGER.info("object of username : {}  was published successfully ",username+count);

//        while(count <= 10){
//            count+=1;
//            template.send(TOPIC,new UserData(username+count,500000+count,100 +count));
//            LOGGER.info("object of username : {}  was published successfully ",username+count);
//        }
        return "10 Message of Object type user was Published";
    }

}
