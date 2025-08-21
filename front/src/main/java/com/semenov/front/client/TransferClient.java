package com.semenov.front.client;

import com.semenov.front.dto.TransferDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@RequiredArgsConstructor
@Service
public class TransferClient {

    private final RestTemplate restTemplate;

    @Value("${client.transfer.url}")
    private String transferServiceUrl;

    public void transfer(TransferDto dto) {
        String url = transferServiceUrl + "/transfer";
        restTemplate.postForEntity(url, dto, Void.class);
    }
}
