package com.semenov.exchange.service;

import com.semenov.exchange.client.ExchangeGeneratorClient;
import com.semenov.exchange.dto.RatesDto;
import com.semenov.exchange.dto.RatesWrapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ExchangeService {

    private final ExchangeGeneratorClient exchangeGeneratorClient;
    private final List<RatesDto> rates = new ArrayList<>();

    public List<RatesDto> getRates() {
        return rates;
    }

    @KafkaListener(topics = "rates", groupId = "exchange-generator")
    public void consumeRates(RatesWrapper ratesDto) {
        rates.clear();
        rates.addAll(ratesDto.getRatesDto());
        log.info("Update rates: {}", rates);
    }

    @KafkaListener(topics = "test", groupId = "test")
    public void test(String message) {
        log.info("Find test message: {}", message);
    }
}
