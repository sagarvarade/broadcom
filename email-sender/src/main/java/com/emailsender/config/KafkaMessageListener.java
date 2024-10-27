package com.emailsender.config;


import com.emailsender.beans.TemplatesGenerated;
import com.emailsender.service.TemplateService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.TopicPartition;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class KafkaMessageListener {

    @Autowired
    private TemplateService templateService;

    @Autowired
    private ObjectMapper objectMapper;

    @KafkaListener(topicPartitions = @TopicPartition(topic = "broad-email-topic", partitions = {"0"}), containerFactory = "kafkaListenerContainerFactory")
    public void consumeTemplateOne(String message) {
        try {
            TemplatesGenerated template = objectMapper.readValue(message, TemplatesGenerated.class);
            templateService.save(template);
            log.info("Consumer One : Email consume the template : {} ", template.toString());
        } catch (JsonProcessingException e) {
            log.error("One : Consume Email Template Error : {} ", e.getMessage());
        }
    }

    @KafkaListener(topicPartitions = @TopicPartition(topic = "broad-email-topic", partitions = {"1"}), containerFactory = "kafkaListenerContainerFactory")
    public void consumeTemplateTwo(String message) {
        try {
            TemplatesGenerated template = objectMapper.readValue(message, TemplatesGenerated.class);
            templateService.save(template);
            log.info("Consumer Two : Email consume the template : {} ", template.toString());
        } catch (JsonProcessingException e) {
            log.error("Two : Consume Email Template Error : {} ", e.getMessage());
        }
    }

    @KafkaListener(topicPartitions = @TopicPartition(topic = "broad-email-topic", partitions = {"2"}), containerFactory = "kafkaListenerContainerFactory")
    public void consumeTemplateThree(String message) {
        try {
            TemplatesGenerated template = objectMapper.readValue(message, TemplatesGenerated.class);
            templateService.save(template);
            log.info("Consumer Three : Email consume the template : {} ", template.toString());
        } catch (JsonProcessingException e) {
            log.error("Three : Consume Email Template Error : {} ", e.getMessage());
        }
    }

    @KafkaListener(topicPartitions = @TopicPartition(topic = "broad-email-topic", partitions = {"3"}), containerFactory = "kafkaListenerContainerFactory")
    public void consumeTemplateFour(String message) {
        try {
            TemplatesGenerated template = objectMapper.readValue(message, TemplatesGenerated.class);
            templateService.save(template);
            log.info("Consumer Four : Email consume the template : {} ", template.toString());
        } catch (JsonProcessingException e) {
            log.error("Four : Consume Email Template Error : {} ", e.getMessage());
        }
    }

    @KafkaListener(topicPartitions = @TopicPartition(topic = "broad-email-topic", partitions = {"4"}), containerFactory = "kafkaListenerContainerFactory")
    public void consumeTemplateFive(String message) {
        try {
            TemplatesGenerated template = objectMapper.readValue(message, TemplatesGenerated.class);
            templateService.save(template);
            log.info("Consumer Five : Email consume the template : {} ", template.toString());
        } catch (JsonProcessingException e) {
            log.error("Five : Consume Email Template Error : {} ", e.getMessage());
        }
    }

    @KafkaListener(topicPartitions = @TopicPartition(topic = "broad-email-topic", partitions = {"5"}), containerFactory = "kafkaListenerContainerFactory")
    public void consumeTemplateSix(String message) {
        try {
            TemplatesGenerated template = objectMapper.readValue(message, TemplatesGenerated.class);
            templateService.save(template);
            log.info("Consumer Six : Email consume the template : {} ", template.toString());
        } catch (JsonProcessingException e) {
            log.error("Six : Consume Email Template Error : {} ", e.getMessage());
        }
    }
}
