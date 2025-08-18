package com.semenov.front.client;


import com.semenov.front.dto.UserAccountDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.time.LocalDate;

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


    public void updatePasswordByLogin(String login, String newPassword) {
        String url = UriComponentsBuilder
                .fromHttpUrl(accountServiceUrl + "/user/{login}/editPassword")
                .queryParam("newPass", newPassword)
                .buildAndExpand(login)
                .toUriString();

        restTemplate.postForEntity(url, null, Void.class);
    }

    public void editUserAccounts(String login, String name, LocalDate birthdate) {
        String url = UriComponentsBuilder
                .fromHttpUrl(accountServiceUrl + "/user/{login}/editUserAccounts")
                .queryParam("name", name)
                .queryParam("birthdate", birthdate)
                .buildAndExpand(login)
                .toUriString();

        restTemplate.postForEntity(url, null, Void.class);
    }
}
