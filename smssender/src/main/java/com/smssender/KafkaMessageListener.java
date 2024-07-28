package com.smssender;


import com.Broadcomapp.message.beans.TemplatesGenerated;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaMessageListener {

    @KafkaListener(topics = "broad-sms-topic", containerFactory = "kafkaListenerContainerFactory")
    public void consumeStudent(TemplatesGenerated templatesGenerated){
        System.out.println("Consumer:5 consume the student : "+templatesGenerated.toString());
    }
}
