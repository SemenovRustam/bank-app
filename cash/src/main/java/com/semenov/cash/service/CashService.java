package com.semenov.cash.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.semenov.cash.client.AccountClient;
import com.semenov.cash.client.NotificationClient;
import com.semenov.cash.dto.CashDto;
import com.semenov.cash.kafka.NotificationProducer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class CashService {
    private final AccountClient accountClient;
    private final NotificationClient notificationClient;
    private final NotificationProducer producer;
    private final ObjectMapper objectMapper;

    public void moveCash(CashDto cashDto) {
        accountClient.moveCash(cashDto);
//        notificationClient.notify(cashDto);
        try {
            producer.notifyClient(objectMapper.writeValueAsString(cashDto));
        } catch (Exception ex) {
            log.error("Error while serialize cash");
        }
    }
}
