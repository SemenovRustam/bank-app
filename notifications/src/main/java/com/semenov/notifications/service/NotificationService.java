package com.semenov.notifications.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.semenov.notifications.dto.CashDto;
import io.micrometer.tracing.Span;
import io.micrometer.tracing.Tracer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class NotificationService {
    private final ObjectMapper objectMapper;
    private final Tracer tracer;

    public void notify(CashDto cashDto) {
        log.info("NOTIFICATIONS: User with login: {} {} {} {}",
                cashDto.getLogin(),
                cashDto.getCashAction(),
                cashDto.getValue(),
                cashDto.getCurrency()
        );
    }

    @KafkaListener(topics = "notify", groupId = "notify-group")
    public void notifyFromKafka(String json) {
        Span span = tracer.nextSpan().name("notify-service").start();

        try (Tracer.SpanInScope spanInScope = tracer.withSpan(span)) {

            CashDto cashDto = objectMapper.readValue(json, CashDto.class);
            log.info("NOTIFICATIONS from kafka: User with login: {} {} {} {}",
                    cashDto.getLogin(),
                    cashDto.getCashAction(),
                    cashDto.getValue(),
                    cashDto.getCurrency()
            );
        } catch (Exception ex) {
            log.error("Error while parse json from cashdto");
        } finally {
            span.end();
        }
    }
}
