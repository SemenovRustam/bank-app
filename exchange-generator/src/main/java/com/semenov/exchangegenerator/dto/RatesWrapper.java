package com.semenov.exchangegenerator.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
public class RatesWrapper {
    private List<RatesDto> ratesDto;
}
