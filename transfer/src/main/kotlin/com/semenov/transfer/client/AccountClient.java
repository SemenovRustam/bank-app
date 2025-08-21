package com.semenov.transfer.client;

import com.semenov.transfer.dto.TransferDto;
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

    public void editUserAccounts(TransferDto transferDto) {
        String url = accountServiceUrl + "/transfer";
        restTemplate.postForEntity(url, transferDto, Void.class);
        restTemplate.postForEntity(url, null, Void.class);
    }
}
