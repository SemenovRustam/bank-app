package com.semenov.exchangegenerator.kafka;

import com.semenov.exchangegenerator.metric.ExchangeGeneratorMetric;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class CurrencyGeneratorProducer {

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ExchangeGeneratorMetric exchangeGeneratorMetric;

    public void sendRates(String message) {
        kafkaTemplate.send("rates", message);
        exchangeGeneratorMetric.incrementExchangeCounter();
    }
}
