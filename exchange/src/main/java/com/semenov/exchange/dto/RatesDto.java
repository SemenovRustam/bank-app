package com.semenov.exchange.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RatesDto {

    private Currency currency;
    private Double value;
}
