package com.semenov.account.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class UserAccountDto {
    private String login;
    private String name;
    private String password;
    private LocalDate birthdate;
    private String currency;
    private BigDecimal initialValue;
}
