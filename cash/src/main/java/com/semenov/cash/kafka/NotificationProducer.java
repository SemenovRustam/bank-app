package com.semenov.cash.kafka;

import lombok.RequiredArgsConstructor;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class NotificationProducer {

    private final KafkaTemplate<String, String> kafkaTemplate;

    public void notifyClient(String message) {
        kafkaTemplate.send("notify", message);
    }
}
