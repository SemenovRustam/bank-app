package com.semenov.exchangegenerator.metric;

import io.micrometer.core.instrument.MeterRegistry;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ExchangeGeneratorMetric {

    private final MeterRegistry meterRegistry;

    private static final String GENERATOR_COUNTER = "currency_generate_rates_total";

    public void incrementExchangeCounter() {
        meterRegistry.counter(GENERATOR_COUNTER).increment();
    }
}
