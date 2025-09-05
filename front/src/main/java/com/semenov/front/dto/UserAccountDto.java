package com.semenov.front.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserAccountDto {
    private String login;
    private String name;
    private String password;
    private LocalDate birthdate;
    private String currency;
    private BigDecimal initialValue;
}
