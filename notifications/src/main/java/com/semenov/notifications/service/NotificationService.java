package com.semenov.notifications.service;

import com.semenov.notifications.dto.CashDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class NotificationService {

    public void notify(CashDto cashDto) {
        log.info("NOTIFICATIONS: User with login: {} {} {} {}",
                cashDto.getLogin(),
                cashDto.getCashAction(),
                cashDto.getValue(),
                cashDto.getCurrency()
        );
    }
}
