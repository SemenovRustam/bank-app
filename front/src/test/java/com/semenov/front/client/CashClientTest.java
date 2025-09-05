package com.semenov.front.client;

import com.semenov.front.dto.CashDto;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.Field;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

class CashClientTest {

    private RestTemplate restTemplate;
    private CashClient cashClient;

    @BeforeEach
    void setUp() throws Exception {
        restTemplate = mock(RestTemplate.class);
        cashClient = new CashClient(restTemplate);

        // Устанавливаем private поле cashServiceUrl через рефлексию
        Field urlField = CashClient.class.getDeclaredField("cashServiceUrl");
        urlField.setAccessible(true);
        urlField.set(cashClient, "http://localhost:8081");
    }

    @Test
    void testCash() {
        CashDto dto = new CashDto();
        cashClient.cash(dto);

        verify(restTemplate, times(1))
                .postForEntity(eq("http://localhost:8081/cash"), eq(dto), eq(Void.class));
    }
}
