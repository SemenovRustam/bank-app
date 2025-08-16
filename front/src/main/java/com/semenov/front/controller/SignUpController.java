package com.semenov.front.controller;

import com.semenov.front.client.AccountClient;
import com.semenov.front.dto.UserAccountDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cglib.core.Local;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;
import java.time.LocalDate;

@Controller
@RequiredArgsConstructor
@RequestMapping("/signup")
@Slf4j
public class SignUpController {

    private final AccountClient accountClient;
    private final InMemoryUserDetailsManager userDetailsService;
    private final PasswordEncoder passwordEncoder;


    @PostMapping
    public String createAccount(
            Model model,
            @RequestParam String login,
            @RequestParam String password,
            @RequestParam String confirmPassword,
            @RequestParam String name,
            @RequestParam LocalDate birthdate
    ) {
        log.info("login: {}, password: {}, confirmPassword: {}, name: {}, date: {}", login, password, confirmPassword, name, birthdate);

        UserDetails userDetails = User.withUsername(login)
                .password(passwordEncoder.encode(password))
                .build();


        userDetailsService.createUser(userDetails);
        log.info("create user {}", userDetails);

        UserAccountDto userAccountDto = new UserAccountDto(login, name, birthdate, "RUB", BigDecimal.ZERO);
        accountClient.createUserAccount(userAccountDto);

        return "redirect:/main";
    }

    @GetMapping()
    public String getSign() {
        return "signup";
    }

}
