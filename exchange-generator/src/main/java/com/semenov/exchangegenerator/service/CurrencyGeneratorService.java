package com.semenov.exchangegenerator.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.semenov.exchangegenerator.dto.Currency;
import com.semenov.exchangegenerator.dto.RatesDto;
import com.semenov.exchangegenerator.dto.RatesWrapper;
import com.semenov.exchangegenerator.kafka.CurrencyGeneratorProducer;
import io.micrometer.tracing.Span;
import io.micrometer.tracing.Tracer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Service
@Slf4j
@RequiredArgsConstructor
public class CurrencyGeneratorService {
    private final CurrencyGeneratorProducer producer;
    private final TestProducer testProducer;
    private final ObjectMapper objectMapper;

    private final List<RatesDto> rates = new ArrayList<>();
    private final Tracer tracer;

    public List<RatesDto> getRates() {
        return rates;
    }

    @Scheduled(fixedRate = 10000)
    public void generate() {
        Span newSpan = tracer.nextSpan().name("generate-rates").start();

        try (Tracer.SpanInScope ws = tracer.withSpan(newSpan)) {
            rates.clear();
            RatesDto rub = RatesDto.builder()
                    .value(1.0)
                    .currency(Currency.RUB)
                    .build();

            RatesDto usd = RatesDto.builder()
                    .value((ThreadLocalRandom.current().nextDouble(78, 85)))
                    .currency(Currency.USD)
                    .build();

            RatesDto cny = RatesDto.builder()
                    .value(ThreadLocalRandom.current().nextDouble(9, 11))
                    .currency(Currency.CNY)
                    .build();

            rates.add(rub);
            rates.add(usd);
            rates.add(cny);

            RatesWrapper ratesWrapper = new RatesWrapper(rates);
            String message = objectMapper.writeValueAsString(ratesWrapper);

            producer.sendRates(message);
            log.info("Current rates {}", rates);
        } catch (JsonProcessingException e) {
            log.info("Error while try parse josn");
            throw new RuntimeException(e);
        } finally {
            newSpan.end(); // Завершаем span
        }
    }
}
