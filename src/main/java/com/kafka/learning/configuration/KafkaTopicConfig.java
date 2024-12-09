package com.kafka.learning.configuration;

import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.common.config.TopicConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.KafkaAdmin;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaTopicConfig {

    @Bean
    public NewTopic moduleTopic() {
        return TopicBuilder.name("module")
                .partitions(15)
                .replicas(2)
                .config(TopicConfig.RETENTION_MS_CONFIG,"3360000")
                .build();
    }

    @Bean
    public NewTopic applicationTopic() {
        return TopicBuilder.name("application")
                .partitions(10)
                .replicas(1)
                .build();
    }

    @Bean
    public NewTopic clientTopic() {
        return TopicBuilder.name("client")
                .partitions(15)
                .replicas(2)
                .config(TopicConfig.RETENTION_MS_CONFIG,"1680000")
                .build();
    }
}
