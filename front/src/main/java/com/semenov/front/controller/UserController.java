package com.semenov.front.controller;

import com.semenov.front.client.AccountClient;
import com.semenov.front.client.CashClient;
import com.semenov.front.client.TransferClient;
import com.semenov.front.dto.CashDto;
import com.semenov.front.dto.Currency;
import com.semenov.front.dto.TransferDto;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final InMemoryUserDetailsManager userDetailsService;
    private final PasswordEncoder passwordEncoder;
    private final AccountClient accountClient;
    private final CashClient cashClient;
    private final TransferClient transferClient;

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

    @PostMapping("/{login}/cash")
    public String сash(@PathVariable(name = "login") String login,
                       @RequestParam(name = "currency", defaultValue = "RUB") String currency,
                       @RequestParam(name = "value") Double value,
                       @RequestParam(name = "action") String action,
                       RedirectAttributes redirectAttributes) {
        log.info("/{login}/cash request, currency {}, action {}", currency, action);

        CashDto cashDto = CashDto.builder()
                .cashAction(action)
                .value(value)
                .login(login)
                .currency(currency)
                .build();

        cashClient.cash(cashDto);

        return "redirect:/main";
    }

    @PostMapping("/{login}/transfer")
    public String transfer(@PathVariable(name = "login") String login,
                           @RequestParam(name = "from_currency", defaultValue = "RUB") String fromCurrency,
                           @RequestParam(name = "to_currency", defaultValue = "RUB") String toCurrency,
                           @RequestParam(name = "value") Double value,
                           @RequestParam(name = "to_login") String toLogin,
                           RedirectAttributes redirectAttributes) {


        TransferDto transferDto = TransferDto.builder()
                .to(toLogin)
                .from(login)
                .value(value)
                .build();


        transferClient.transfer(transferDto);

        return "redirect:/main";
    }
}