package com.semenov.account.controller;

import com.semenov.account.dto.CashDto;
import com.semenov.account.service.CashService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/cash")
public class CashController {

    private final CashService cashService;

    @PostMapping("/move")
    public ResponseEntity<?> moveCash(@RequestBody CashDto cashDto) {
        cashService.moveCash(cashDto);
        return ResponseEntity.ok().build();
    }
}
