package com.semenov.front.security;

import com.semenov.front.client.ExchangeClient;
import com.semenov.front.dto.RatesDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RatesService {

    private final ExchangeClient exchangeClient;

    public List<RatesDto> getRates() {
        return exchangeClient.getRates();
    }
}
