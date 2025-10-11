package com.semenov.exchangegenerator.service;

import io.micrometer.tracing.Tracer;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import com.semenov.exchangegenerator.dto.Currency;
import com.semenov.exchangegenerator.dto.RatesDto;
import com.semenov.exchangegenerator.service.CurrencyGeneratorService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

import com.semenov.exchangegenerator.dto.Currency;
import com.semenov.exchangegenerator.dto.RatesDto;
import com.semenov.exchangegenerator.service.CurrencyGeneratorService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

//class CurrencyGeneratorServiceTest {
//
//    private CurrencyGeneratorService service;
//
//    private Tracer tracer;
//
//    @BeforeEach
//    void setUp() {
//        service = new CurrencyGeneratorService();
//    }
//
//    @Test
//    void testGenerate() {
//        assertTrue(service.getRates().isEmpty());
//
//        service.generate();
//
//        List<RatesDto> rates = service.getRates();
//
//        assertNotNull(rates);
//        assertEquals(3, rates.size());
//
//        RatesDto rub = rates.stream()
//                .filter(r -> r.getCurrency() == Currency.RUB)
//                .findFirst()
//                .orElse(null);
//        assertNotNull(rub);
//        assertEquals(1.0, rub.getValue());
//
//        RatesDto usd = rates.stream()
//                .filter(r -> r.getCurrency() == Currency.USD)
//                .findFirst()
//                .orElse(null);
//        assertNotNull(usd);
//        assertTrue(usd.getValue() >= 78 && usd.getValue() <= 85);
//
//        RatesDto cny = rates.stream()
//                .filter(r -> r.getCurrency() == Currency.CNY)
//                .findFirst()
//                .orElse(null);
//        assertNotNull(cny);
//        assertTrue(cny.getValue() >= 9 && cny.getValue() <= 11);
//    }
//}
