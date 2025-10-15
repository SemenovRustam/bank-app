package com.semenov.exchange.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.semenov.exchange.client.ExchangeGeneratorClient;
import com.semenov.exchange.dto.RatesDto;
import com.semenov.exchange.dto.RatesWrapper;
import com.semenov.exchange.metric.ExchangeMetric;
import io.micrometer.tracing.Span;
import io.micrometer.tracing.Tracer;
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
    private final Tracer tracer;
    private final ObjectMapper mapper;
    private final List<RatesDto> rates = new ArrayList<>();
    private final ExchangeMetric metric;

    public List<RatesDto> getRates() {
        return rates;
    }

    @KafkaListener(topics = "rates", groupId = "exchange-generator")
    public void consumeRates(String ratesDto) {
        Span newSpan = tracer.nextSpan().name("exchange-rates").start();

        try (Tracer.SpanInScope ws = tracer.withSpan(newSpan)) {
            rates.clear();
            try {
                rates.addAll(mapper.readValue(ratesDto, RatesWrapper.class).getRatesDto());
            } catch (Exception ex) {
                log.error("Error while parse json {}", ratesDto);
            }

            log.info("Update rates: {}", rates);
            metric.incrementExchangeCounter();
        } finally {
            newSpan.end();
        }
    }
}
