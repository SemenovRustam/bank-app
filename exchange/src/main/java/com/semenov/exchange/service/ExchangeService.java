package com.semenov.exchange.service;

import com.semenov.exchange.client.ExchangeGeneratorClient;
import com.semenov.exchange.dto.RatesDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ExchangeService {

    private final ExchangeGeneratorClient exchangeGeneratorClient;

    public List<RatesDto> getRates() {

        return exchangeGeneratorClient.getRates();
    }
}
