package net.rfbezerra.kafkaproducer.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.rfbezerra.kafkaproducer.model.Message;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

@Service
@RequiredArgsConstructor
@Slf4j
public class MessageService {
    private final KafkaTemplate<String, String> kafkaTemplate;

    public void postMessage(Message message) {
        ListenableFuture<SendResult<String, String>> listenableFuture = kafkaTemplate.send("net.rfbezerra.messages", message.getText());
        listenableFuture.addCallback(new ListenableFutureCallback<SendResult<String, String>>() {
            @Override
            public void onFailure(Throwable ex) {
                log.error("Failed to send message to topic", ex);
            }

            @Override
            public void onSuccess(SendResult<String, String> result) {
                log.info("Message sent to topi {}", result.getProducerRecord().topic());
            }
        });
    }

}
