package com.semenov.cash.service;

import com.semenov.cash.client.AccountClient;
import com.semenov.cash.dto.CashDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CashService {
    private final AccountClient accountClient;

    public void moveCash(CashDto cashDto) {
        accountClient.moveCash(cashDto);
        //todo(notify)
    }
}
