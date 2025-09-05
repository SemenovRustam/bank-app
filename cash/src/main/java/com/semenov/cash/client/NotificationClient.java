package com.semenov.cash.client;

import com.semenov.cash.dto.CashDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class NotificationClient {

    private final RestTemplate restTemplate;

    @Value("${client.notifications.url}")
    private String notificationServiceUrl;

    public void notify(CashDto dto) {
        String url = notificationServiceUrl + "/notification"; //todo
        restTemplate.postForEntity(url, dto, Void.class);
    }
}
