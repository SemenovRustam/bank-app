package com.semenov.exchangegenerator.kafka;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.semenov.exchangegenerator.dto.RatesWrapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.protocol.types.Field;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class CurrencyGeneratorProducer {

    private final KafkaTemplate<String, RatesWrapper> kafkaTemplate;

    private final ObjectMapper objectMapper;

//    public void sendRates(RatesWrapper message) {
//        kafkaTemplate.send("rates", message);
//    }
}
