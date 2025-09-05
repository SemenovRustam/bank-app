package com.semenov.notifications.controller;

import com.semenov.notifications.dto.CashDto;
import com.semenov.notifications.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/notification")
public class NotificationController {

    private final NotificationService notificationService;

    @PostMapping
    public ResponseEntity<?> notify(@RequestBody CashDto cashDto) {
        notificationService.notify(cashDto);
        return ResponseEntity.ok().build();
    }
}
