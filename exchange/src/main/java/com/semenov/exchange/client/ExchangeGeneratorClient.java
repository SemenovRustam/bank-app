package com.semenov.exchange.client;

import com.semenov.exchange.dto.RatesDto;
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
public class ExchangeGeneratorClient {

    private final RestTemplate restTemplate;

    @Value("${client.exchange-generator.url}")
    private String exchangeGeneratorUrl;

    public List<RatesDto> getRates() {
        String url = exchangeGeneratorUrl + "/exchange";
        ResponseEntity<List<RatesDto>> response = restTemplate.exchange(
        url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<>() {}
 );
        return response.getBody();
    }
}
