package com.semenov.exchangegenerator.service;

import com.semenov.exchangegenerator.dto.RatesWrapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.protocol.types.Field;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class TestProducer {

    private final KafkaTemplate<String, String> kafkaTemplate;

    public void test(String message) {
        kafkaTemplate.send("test", message);
    }

}
