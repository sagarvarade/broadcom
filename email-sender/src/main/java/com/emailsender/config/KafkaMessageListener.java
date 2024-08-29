package com.emailsender.config;


import com.emailsender.beans.TemplatesGenerated;
import com.emailsender.service.TemplateService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.kafka.annotation.KafkaListener;

@Component
public class KafkaMessageListener {

    @Autowired
    private TemplateService templateService;

    @Autowired
    private ObjectMapper objectMapper;
    @KafkaListener(topics = "broad-email-topic", containerFactory = "kafkaListenerContainerFactory")
    public void consumeTemplate(String message){
        try {
            TemplatesGenerated template = objectMapper.readValue(message, TemplatesGenerated.class);
            templateService.save(template);
            System.out.println("Consumer: Email consume the template : "+template.toString());
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}
