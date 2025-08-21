package com.semenov.front.client;

import com.semenov.front.dto.RatesDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ExchangeClient {

    private final RestTemplate restTemplate;


    @Value("${client.exchange.url}")
    private String exchangeServiceUrl;

    public List<RatesDto> getRates() {
        String url = exchangeServiceUrl + "/exchange";
        ResponseEntity<List<RatesDto>> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<>() {}
        );
        return response.getBody();
    }
}
