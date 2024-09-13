package com.smssender.config;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.smssender.beans.TemplatesGenerated;
import com.smssender.service.TemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaMessageListener {

    @Autowired
    private TemplateService templateService;

    @Autowired
    private ObjectMapper objectMapper;

    @KafkaListener(topics = "broad-sms-topic", containerFactory = "kafkaListenerContainerFactory")
    public void consumeTemplate(String message){
        try {
            TemplatesGenerated template = objectMapper.readValue(message, TemplatesGenerated.class);
            templateService.save(template);
            System.out.println("Consumer: SMS consume the template : "+template.toString());
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}
