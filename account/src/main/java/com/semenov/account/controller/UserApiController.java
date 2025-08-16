package com.semenov.account.controller;

import com.semenov.account.dto.Currency;
import com.semenov.account.dto.UserAccountDto;
import com.semenov.account.entity.Account;
import com.semenov.account.entity.User;
import com.semenov.account.service.AccountService;
import com.semenov.account.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserApiController {

    private final UserService userService;
    private final AccountService accountService;

    @PostMapping
    public ResponseEntity<?> createUserWithAccount(@RequestBody UserAccountDto dto) {
        User user = userService.createUser(dto.getLogin(), dto.getName(), dto.getBirthdate());
        Account account = accountService.createAccount(
                user.getId(),
                dto.getCurrency(),
                dto.getInitialValue()
        );
        return ResponseEntity.ok().build();
    }
}
