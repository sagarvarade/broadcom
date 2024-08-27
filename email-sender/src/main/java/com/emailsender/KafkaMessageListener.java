package com.emailsender;


import com.Broadcomapp.message.beans.TemplatesGenerated;
import com.Broadcomapp.message.service.TemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.stereotype.Component;
import org.springframework.kafka.annotation.KafkaListener;

@Component
public class KafkaMessageListener {

    @Autowired
    private TemplateService templateService;
    @KafkaListener(topics = "broad-email-topic", containerFactory = "kafkaListenerContainerFactory")
    public void consumeTemplate(TemplatesGenerated templatesGenerated){
        templateService.save(templatesGenerated);
        System.out.println("Consumer: Email consume the template : "+templatesGenerated.toString());
    }
}
