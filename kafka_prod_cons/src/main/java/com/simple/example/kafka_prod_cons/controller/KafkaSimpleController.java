package com.simple.example.kafka_prod_cons.controller;

import com.google.gson.Gson;
import com.simple.example.kafka_prod_cons.config.KafkaConfigData;
import com.simple.example.kafka_prod_cons.model.MoreSimpleModel;
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


    private KafkaTemplate<String,String> kafkaTemplate;
    private final KafkaConfigData kafkaConfigData;
    private final Gson jsonConverter;

    public KafkaSimpleController(KafkaTemplate<String, String> kafkaTemplate, KafkaConfigData kafkaConfigData, Gson jsonConverter) {
        this.kafkaTemplate = kafkaTemplate;
        this.kafkaConfigData = kafkaConfigData;
        this.jsonConverter = jsonConverter;
    }

    @PostMapping
    public void post(@RequestBody SimpleModel simpleModel){
        log.info("Received message {}", simpleModel);
        kafkaTemplate.send(kafkaConfigData.getTopicName(),jsonConverter.toJson(simpleModel));
    }

    @PostMapping("/v2")
    public void post(@RequestBody MoreSimpleModel moreSimpleModel){
        log.info("Received message from api /v2 {}", moreSimpleModel);
        kafkaTemplate.send(kafkaConfigData.getTopicNamesToCreate().get(1),jsonConverter.toJson(moreSimpleModel));
    }

    @KafkaListener(topics = "${kafka-config.topic-name}")
    public void receive(String simpleModel){
        log.info("Received message from kafka {}", simpleModel);
        log.info("Received message from kafka and Gsonizzato {}", jsonConverter.fromJson(simpleModel, SimpleModel.class));
    }

    @KafkaListener(topics = "${kafka-config.topic-names-to-create[1]}")
    public void receiveV2(String simpleModel){
        log.info("Received message from kafka {}", simpleModel);
        log.info("Received message from kafka and Gsonizzato {}", jsonConverter.fromJson(simpleModel, MoreSimpleModel.class));
    }
}
