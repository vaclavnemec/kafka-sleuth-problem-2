package com.example.demo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class KafkaService {

    @KafkaListener(
            topics = "my-test-topic",
            containerFactory = "kafkaListenerContainerFactory",
            groupId = "test"
    )
    public void onDataLoadNotification(@Payload Message<MyTestMessage> message) {
        log.info("received a message: {}", message);
    }

}
