package com.semenov.account.controller;

import com.semenov.account.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;

@Controller
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/{login}/editPassword")
    public ResponseEntity<?> createUserWithAccount(
            @PathVariable String login,
            @RequestParam String newPass
    ) {
        userService.updatePasswordByLogin(login, newPass);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{login}/editUserAccounts")
    public ResponseEntity<?> createUserWithAccount(
            @PathVariable String login,
            @RequestParam String name,
            @RequestParam LocalDate birthdate
    ) {
        userService.updateNameAndBirthdayByLogin(login, name, birthdate);
        return ResponseEntity.ok().build();
    }

}
