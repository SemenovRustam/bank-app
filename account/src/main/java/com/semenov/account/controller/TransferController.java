package com.semenov.account.controller;

import com.semenov.account.dto.TransferDto;
import com.semenov.account.service.TransferService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/transfer")
@RequiredArgsConstructor
public class TransferController {

    private final TransferService transferService;

    @PostMapping
    private ResponseEntity<?> transferService(@RequestBody TransferDto dto) {
        transferService.transfer(dto);
        return ResponseEntity.ok().build();
    }
}

