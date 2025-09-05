package com.semenov.exchangegenerator.controller;


import com.semenov.exchangegenerator.dto.RatesDto;
import com.semenov.exchangegenerator.service.CurrencyGeneratorService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/exchange")
@RequiredArgsConstructor
public class ExchangeController {
    private final CurrencyGeneratorService currencyGeneratorService;

    @GetMapping
    public List<RatesDto> getRates() {
            return currencyGeneratorService.getRates();
    }
}
