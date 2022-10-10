package net.rfbezerra.kafkaconsumer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;

@SpringBootApplication
@Slf4j
public class KafkaConsumerApplication {
    public static void main(String[] args) {
        SpringApplication.run(KafkaConsumerApplication.class, args);
    }

    @KafkaListener(topics = "net.rfbezerra.messages", groupId = "group-1")
    public void listen(@Payload String message, @Header(KafkaHeaders.RECEIVED_PARTITION_ID) int partition) {
        log.info("Message {} received on partition {}", message, partition);
    }
}
