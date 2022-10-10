package net.rfbezerra.kafkaproducer.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.rfbezerra.kafkaproducer.model.Message;
import net.rfbezerra.kafkaproducer.service.MessageService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/messages")
public class MessagesController {

    private final MessageService service;

    @PostMapping
    public void postMessage(@RequestBody Message message) {
        log.info("Post message received {}", message);
        service.postMessage(message);
    }
}
