package com.semenov.account.service;

import com.semenov.account.dto.Currency;
import com.semenov.account.entity.Account;
import com.semenov.account.entity.User;
import com.semenov.account.repository.AccountRepository;
import com.semenov.account.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@Slf4j
@RequiredArgsConstructor
public class AccountService {
    private final AccountRepository accountRepository;
    private final UserRepository userRepository;

    public Account createAccount(Long userId, String currency, BigDecimal value) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Account account = new Account();
        account.setCurrency(Currency.valueOf(currency));
        account.setValue(value);
        account.setUser(user);

        log.info("Create new account {}", account);
        return accountRepository.save(account);
    }
}
