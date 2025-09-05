package com.semenov.front.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TransferDto {
    private String from;
    private String to;
    private Double value;
}
