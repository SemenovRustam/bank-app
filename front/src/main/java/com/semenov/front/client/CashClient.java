package com.semenov.front.client;

import com.semenov.front.dto.CashDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@RequiredArgsConstructor
public class CashClient {

    private final RestTemplate restTemplate;

    @Value("${client.cash.url}")
    private String cashServiceUrl;

    public void cash(CashDto dto) {
        String url = cashServiceUrl + "/cash";
        restTemplate.postForEntity(url, dto, Void.class);
    }
}
