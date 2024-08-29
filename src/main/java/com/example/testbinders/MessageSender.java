package com.example.testbinders;

import jakarta.annotation.Resource;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Sinks;

@Component
public class MessageSender {

    @Resource(name = "produceBinding")
    private Sinks.Many<Message<String>> messageSender;

    public void publishReceivableAccountingQueue(String message) {
        messageSender.emitNext(new Message<>() {
            @Override
            public String getPayload() {
                return message;
            }

            @Override
            public MessageHeaders getHeaders() {
                return new MessageHeaders(null);
            }
        }, Sinks.EmitFailureHandler.FAIL_FAST);
    }
}
