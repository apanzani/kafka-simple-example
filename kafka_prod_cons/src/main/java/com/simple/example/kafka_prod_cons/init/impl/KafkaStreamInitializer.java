package com.simple.example.kafka_prod_cons.init.impl;

import com.simple.example.kafka_prod_cons.config.KafkaAdminClient;
import com.simple.example.kafka_prod_cons.config.KafkaConfigData;
import com.simple.example.kafka_prod_cons.init.StreamInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class KafkaStreamInitializer implements StreamInitializer {

    private static final Logger LOG = LoggerFactory.getLogger(KafkaStreamInitializer.class);

    private final KafkaConfigData kafkaConfigData;
    private final KafkaAdminClient kafkaAdminClient;

    public KafkaStreamInitializer(KafkaConfigData kafkaConfigData, KafkaAdminClient kafkaAdminClient) {
        this.kafkaConfigData = kafkaConfigData;
        this.kafkaAdminClient = kafkaAdminClient;
    }

    @Override
    public void init() {
        kafkaAdminClient.createTopics();
        LOG.info("Topics with name {} is ready for operations!", kafkaConfigData.getTopicNamesToCreate());

    }
}
