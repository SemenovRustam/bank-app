package com.semenov.front.client;


import com.semenov.front.dto.UserAccountDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@RequiredArgsConstructor
public class AccountClient {

    private final RestTemplate restTemplate;

    @Value("${client.account.url}") // Пример: http://localhost:8081 или http://account-service
    private String accountServiceUrl;

    public void createUserAccount(UserAccountDto dto) {
        String url = accountServiceUrl + "/api/users";
        restTemplate.postForEntity(url, dto, Void.class);
    }
}
