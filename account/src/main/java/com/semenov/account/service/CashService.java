package com.semenov.account.service;

import com.semenov.account.dto.CashDto;
import com.semenov.account.entity.Account;
import com.semenov.account.entity.User;
import com.semenov.account.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
@Slf4j
public class CashService {

    private final UserRepository userRepository;

    public void moveCash(CashDto cashDto) {
        String login = cashDto.getLogin();
        User user = userRepository.findFirstByLogin(login);
        Account accounts = user.getAccounts();

        BigDecimal currentValue = accounts.getValue();

        final BigDecimal valueFromAction = BigDecimal.valueOf(cashDto.getValue());
        switch (cashDto.getCashAction()) {
            case "GET" -> {
                accounts.setValue(
                        currentValue.subtract(valueFromAction)
                );
                log.info("Subtract cash {} from account by login {}", cashDto.getValue(), login);
            }

            case "PUT" -> {
                accounts.setValue(
                        currentValue.add(valueFromAction)
                );
                log.info("Add cash {} from account by login {}", cashDto.getValue(), login);
            }
        }

        user.setAccounts(accounts);
        userRepository.save(user);
        log.info("Update cash account by login {} successfully", login);
    }
}
