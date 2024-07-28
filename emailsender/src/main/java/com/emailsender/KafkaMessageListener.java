package com.emailsender;


import org.springframework.stereotype.Component;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.messaging.handler.annotation.Payload;

@Component
public class KafkaMessageListener {

    @KafkaListener(topics = "broad-email-topic", containerFactory = "kafkaListenerContainerFactory")
    public void consumeStudent(TemplatesGenerated templatesGenerated){
        System.out.println("Consumer:5 consume the student : "+templatesGenerated.toString());
    }
}
