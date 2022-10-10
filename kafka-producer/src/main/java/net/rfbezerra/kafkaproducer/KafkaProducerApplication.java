package net.rfbezerra.kafkaproducer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;

@SpringBootApplication
@Slf4j
public class KafkaProducerApplication {

    public static void main(String[] args) {
        SpringApplication.run(KafkaProducerApplication.class, args);
    }

}
