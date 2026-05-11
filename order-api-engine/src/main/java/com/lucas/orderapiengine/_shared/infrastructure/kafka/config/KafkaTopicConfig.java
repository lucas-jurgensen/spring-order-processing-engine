package com.lucas.orderapiengine._shared.infrastructure.kafka.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

import com.lucas.orderapiengine._shared.infrastructure.kafka.topics.KafkaTopics;

@Configuration
public class KafkaTopicConfig {
    
    @Bean
    public NewTopic orderCreatedTopic() {
        return TopicBuilder.name(KafkaTopics.ORDER_CREATED)
            .partitions(3)
            .replicas(1)
            .build();
    }

    @Bean
    public NewTopic paymentApprovedTopic() {
        return TopicBuilder.name(KafkaTopics.PAYMENT_APPROVED)
            .partitions(3)
            .replicas(1)
            .build();
    }

    @Bean
    public NewTopic paymentFailedTopic() {
        return TopicBuilder.name(KafkaTopics.PAYMENT_FAILED)
            .partitions(3)
            .replicas(1)
            .build();
    }

    @Bean
    public NewTopic stockReservedTopic() {
        return TopicBuilder.name(KafkaTopics.STOCK_RESERVED)
            .partitions(3)
            .replicas(1)
            .build();
    }

    @Bean
    public NewTopic orderShipped() {
        return TopicBuilder.name(KafkaTopics.ORDER_SHIPPED)
            .partitions(3)
            .replicas(1)
            .build();
    }

    @Bean
    public NewTopic orderDelivered() {
        return TopicBuilder.name(KafkaTopics.ORDER_DELIVERED)
            .partitions(3)
            .replicas(1)
            .build();
    }
}
