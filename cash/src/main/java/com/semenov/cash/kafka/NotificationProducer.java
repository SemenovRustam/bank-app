package com.semenov.cash.kafka;

import io.micrometer.tracing.Span;
import io.micrometer.tracing.Tracer;
import lombok.RequiredArgsConstructor;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class NotificationProducer {

    private final KafkaTemplate<String, String> kafkaTemplate;

    private final Tracer tracer;

    public void notifyClient(String message) {
        Span span = tracer.nextSpan().name("notify-span").start();

        try(Tracer.SpanInScope spanInScope = tracer.withSpan(span)) {
            kafkaTemplate.send("notify", message);
        } finally {
            span.end();
        }
    }
}
