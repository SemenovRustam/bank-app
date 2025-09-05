package com.semenov.cash.controller;

import com.semenov.cash.dto.CashDto;
import com.semenov.cash.service.CashService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/cash")
public class CashController {

    private final CashService cashService;

    @PostMapping
    public ResponseEntity moveCash(@RequestBody CashDto cashDto) {
        cashService.moveCash(cashDto);
        return ResponseEntity.ok().build();
    }
}
