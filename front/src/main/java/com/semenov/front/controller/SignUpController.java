package com.semenov.front.controller;

import com.semenov.front.client.AccountClient;
import com.semenov.front.dto.UserAccountDto;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cglib.core.Local;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
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
            HttpServletRequest request,
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

        UserAccountDto userAccountDto = new UserAccountDto(login, name, password, birthdate, "RUB", BigDecimal.ZERO);
        accountClient.createUserAccount(userAccountDto);

        model.addAttribute("login", login);
        model.addAttribute("name", name);
        model.addAttribute("birthdate", birthdate);

        authenticateUser(login, request);

        return "redirect:/main";
    }

    @GetMapping()
    public String getSign() {
        return "signup";
    }

    private void authenticateUser(String username, HttpServletRequest request) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDetails, userDetails.getPassword(), userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authToken);
        request.getSession().setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY, SecurityContextHolder.getContext());
    }

}
