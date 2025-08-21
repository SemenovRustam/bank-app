package com.semenov.transfer.service;

import com.semenov.transfer.client.AccountClient;
import com.semenov.transfer.dto.TransferDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j
@RequiredArgsConstructor
public class TransferService {

    private final AccountClient accountClient;

    private final RestTemplate restTemplate;

    @Value("${client.transfer.url}")
    private String transferServiceUrl;

    public void transfer(TransferDto dto) {
        String url = transferServiceUrl + "/transfer";
        restTemplate.postForEntity(url, dto, Void.class);
    }
}
