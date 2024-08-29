package com.example.testbinders;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.messaging.Message;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

import java.util.function.Supplier;

@SpringBootApplication
public class TestBindersApplication {

    public static void main(String[] args) {
        SpringApplication.run(TestBindersApplication.class, args);
    }

    @Bean
    public Sinks.Many<Message<String>> produceBinding() {
        return Sinks.many().unicast().onBackpressureBuffer();
    }

    @Bean
    public Supplier<Flux<Message<String>>> produceMessage() {
        return () -> produceBinding().asFlux();
    }

}
