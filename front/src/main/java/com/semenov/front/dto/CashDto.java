package com.semenov.front.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Currency;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CashDto {

    private String login;
    private String currency;
    private Double value;
    private String cashAction;

}
