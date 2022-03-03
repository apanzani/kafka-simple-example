package com.simple.example.kafka_prod_cons.controller;

import com.simple.example.kafka_prod_cons.config.KafkaConfigData;
import com.simple.example.kafka_prod_cons.model.SimpleModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/kafka")
@Slf4j
public class KafkaSimpleController {


    private KafkaTemplate<String,SimpleModel> kafkaTemplate;
    private final KafkaConfigData kafkaConfigData;

    public KafkaSimpleController(KafkaTemplate<String, SimpleModel> kafkaTemplate, KafkaConfigData kafkaConfigData) {
        this.kafkaTemplate = kafkaTemplate;
        this.kafkaConfigData = kafkaConfigData;
    }

    @PostMapping
    public void post(@RequestBody SimpleModel simpleModel){
        log.info("Received message {}", simpleModel);
        kafkaTemplate.send(kafkaConfigData.getTopicName(),simpleModel);
    }

    @KafkaListener(topics = "${kafka-config.topic-name}")
    public void receive(SimpleModel simpleModel){
        log.info("Received message from kafka {}", simpleModel);
    }
}
