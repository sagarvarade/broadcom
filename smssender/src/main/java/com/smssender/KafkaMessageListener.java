package com.smssender;


import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class KafkaMessageListener {

    @KafkaListener(topics = "broad-sms-topic", containerFactory = "kafkaListenerContainerFactory")
    public void listen(@Payload String message, Acknowledgment acknowledgment) {
        try {
            System.out.println("Received message: " + message);
            acknowledgment.acknowledge();
        } catch (Exception e) {
            System.err.println("Error processing message: " + message + ", error: " + e.getMessage());
            throw e;
        }
    }
}
