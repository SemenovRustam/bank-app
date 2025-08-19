package com.semenov.cash.client;

import com.semenov.cash.dto.CashDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@RequiredArgsConstructor
public class AccountClient {

    private final RestTemplate restTemplate;

    @Value("${client.account.url}")
    private String accountServiceUrl;

    public void moveCash(CashDto dto) {
        String url = accountServiceUrl + "/cash/move";
        restTemplate.postForEntity(url, dto, Void.class);
    }
}
