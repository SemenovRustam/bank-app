package com.semenov.transfer.controller;

import com.semenov.transfer.dto.TransferDto;
import com.semenov.transfer.service.TransferService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/transfer")
public class TransferController {

    private final TransferService transferService;

    @PostMapping
    public ResponseEntity<?> transfer(@RequestBody TransferDto transferDto) {
        transferService.transfer(transferDto);
        return ResponseEntity.ok().build();
    }
}
