package com.semenov.front.controller;

import com.semenov.front.client.AccountClient;
import com.semenov.front.client.CashClient;
import com.semenov.front.dto.CashDto;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;

@Controller
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final InMemoryUserDetailsManager userDetailsService;
    private final PasswordEncoder passwordEncoder;
    private final AccountClient accountClient;
    private final CashClient cashClient;

    @PostMapping("/{login}/editPassword")
    public String editPassword(@PathVariable(name = "login") String login,
                               @RequestParam(name = "password") String password,
                               @RequestParam(name = "confirmPassword") String confirmPassword,
                               HttpServletRequest request,
                               RedirectAttributes redirectAttributes) {

        accountClient.updatePasswordByLogin(login, password);
        return "redirect:/main";
    }

    @PostMapping("/{login}/editUserAccounts")
    public String editUserAccounts(@PathVariable(name = "login") String login,
                                   @RequestParam(name = "name") String name,
                                   @RequestParam(name = "birthdate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate birthdate,
                                   RedirectAttributes redirectAttributes) {

        accountClient.editUserAccounts(login, name, birthdate);
        return "redirect:/main";
    }

    @PostMapping("/{login}/сash")
    public String сash(@PathVariable(name = "login") String login,
                       @RequestParam(name = "currency") String currency,
                       @RequestParam(name = "value") Double value,
                       @RequestParam(name = "action") String action,
                       RedirectAttributes redirectAttributes) {


        CashDto cashDto = CashDto.builder()
                .cashAction(action)
                .value(value)
                .login(login)
                .currency(currency)
                .build();

        cashClient.cash(cashDto);

        return "redirect:/main";
    }
}
