package com.semenov.account.service;

import com.semenov.account.dto.TransferDto;
import com.semenov.account.entity.Account;
import com.semenov.account.entity.User;
import com.semenov.account.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
@Slf4j
public class TransferService {
    private final UserRepository userRepository;

    @Transactional
    public void transfer(TransferDto dto) {
        User userFrom = userRepository.findFirstByLogin(dto.getFrom());
        User userTo = userRepository.findFirstByLogin(dto.getTo());

        Account accountsTo = userTo.getAccounts();
        Account accountsFrom = userFrom.getAccounts();

        accountsTo.setValue(
                accountsTo.getValue().add(BigDecimal.valueOf(dto.getValue()))
        );

        userTo.setAccounts(accountsTo);

        accountsFrom.setValue(
                accountsFrom.getValue().subtract(BigDecimal.valueOf(dto.getValue()))
        );

        userFrom.setAccounts(accountsFrom);
        log.info("Move money from {} to {}", dto.getFrom(), dto.getTo());
    }
}
