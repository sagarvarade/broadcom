package com.smssender;


import com.Broadcomapp.message.beans.TemplatesGenerated;
import com.Broadcomapp.message.service.TemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaMessageListener {

    @Autowired
    private TemplateService templateService;
    @KafkaListener(topics = "broad-sms-topic", containerFactory = "kafkaListenerContainerFactory")
    public void consumeTemplate(TemplatesGenerated templatesGenerated){
        templateService.save(templatesGenerated);
        System.out.println("Consumer: SMS : consume the template : "+templatesGenerated.toString());
    }
}
