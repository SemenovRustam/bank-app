package com.semenov.exchange.metric;

import io.micrometer.core.instrument.MeterRegistry;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ExchangeMetric {

    private final MeterRegistry registry;

    private static final String EXCHANGE_COUNTER = "currency_consume_rates_total";

    public void incrementExchangeCounter() {
        registry.counter(EXCHANGE_COUNTER).increment();
    }
}
