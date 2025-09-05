package com.semenov.front.controller;

import com.semenov.front.dto.RatesDto;
import com.semenov.front.security.RatesService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/rates")
@Slf4j
public class RatesController {

    private final RatesService ratesService;

    @GetMapping
    public List<RatesDto> getRates() {

        List<RatesDto> rates = ratesService.getRates();
        log.info("Get rates: {}", rates);

        return rates;
    }
}
