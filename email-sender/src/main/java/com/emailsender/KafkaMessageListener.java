package com.emailsender;


import com.Broadcomapp.message.beans.TemplatesGenerated;
import org.springframework.stereotype.Component;
import org.springframework.kafka.annotation.KafkaListener;

@Component
public class KafkaMessageListener {

    @KafkaListener(topics = "broad-email-topic", containerFactory = "kafkaListenerContainerFactory")
    public void consumeTemplate(TemplatesGenerated templatesGenerated){
        System.out.println("Consumer: Email consume the template : "+templatesGenerated.toString());
    }
}
