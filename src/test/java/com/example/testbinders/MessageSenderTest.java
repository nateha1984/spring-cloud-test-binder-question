package com.example.testbinders;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.stream.binder.test.OutputDestination;
import org.springframework.cloud.stream.binder.test.TestChannelBinderConfiguration;
import org.springframework.context.annotation.Import;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ImportAutoConfiguration({TestChannelBinderConfiguration.class})
class MessageSenderTest {

    @Autowired
    private MessageSender messageSender;

    @Autowired
    private OutputDestination outputDestination;

    @Test
    void publishReceivableAccountingQueue() {
        messageSender.publishReceivableAccountingQueue("test message");

        byte[] msg = outputDestination.receive().getPayload();

        assertThat(msg, notNullValue());
    }
}