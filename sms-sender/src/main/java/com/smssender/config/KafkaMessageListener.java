package com.smssender.config;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.smssender.beans.TemplatesGenerated;
import com.smssender.service.TemplateService;
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

    @KafkaListener(topicPartitions = @TopicPartition(topic = "broad-sms-topic", partitions = {"0"}),
            containerFactory = "kafkaListenerContainerFactory")
    public void consumeTemplateOne(String message) {
        try {
            TemplatesGenerated template = objectMapper.readValue(message, TemplatesGenerated.class);
            templateService.save(template);
            log.info("Consumer One : Email consume the template : {} ", template.toString());
        } catch (JsonProcessingException e) {
            log.error("One : SMS-Sender consumer template error : {} ", e.getMessage());
        }
    }

    @KafkaListener(topicPartitions = @TopicPartition(topic = "broad-sms-topic", partitions = {"1"}),
            containerFactory = "kafkaListenerContainerFactory")
    public void consumeTemplateTwo(String message) {
        try {
            TemplatesGenerated template = objectMapper.readValue(message, TemplatesGenerated.class);
            templateService.save(template);
            log.info("Consumer Two : Email consume the template : {} ", template.toString());
        } catch (JsonProcessingException e) {
            log.error("Two : SMS-Sender consumer template error : {} ", e.getMessage());
        }
    }

    @KafkaListener(topicPartitions = @TopicPartition(topic = "broad-sms-topic", partitions = {"2"}),
            containerFactory = "kafkaListenerContainerFactory")
    public void consumeTemplateThree(String message) {
        try {
            TemplatesGenerated template = objectMapper.readValue(message, TemplatesGenerated.class);
            templateService.save(template);
            log.info("Consumer Three : Email consume the template : {} ", template.toString());
        } catch (JsonProcessingException e) {
            log.error("Three : SMS-Sender consumer template error : {} ", e.getMessage());
        }
    }

    @KafkaListener(topicPartitions = @TopicPartition(topic = "broad-sms-topic", partitions = {"3"}),
            containerFactory = "kafkaListenerContainerFactory")
    public void consumeTemplateFour(String message) {
        try {
            TemplatesGenerated template = objectMapper.readValue(message, TemplatesGenerated.class);
            templateService.save(template);
            log.info("Consumer Four : Email consume the template : {} ", template.toString());
        } catch (JsonProcessingException e) {
            log.error("Four : SMS-Sender consumer template error : {} ", e.getMessage());
        }
    }

    @KafkaListener(topicPartitions = @TopicPartition(topic = "broad-sms-topic", partitions = {"4"}),
            containerFactory = "kafkaListenerContainerFactory")
    public void consumeTemplateFive(String message) {
        try {
            TemplatesGenerated template = objectMapper.readValue(message, TemplatesGenerated.class);
            templateService.save(template);
            log.info("Consumer Five : Email consume the template : {} ", template.toString());
        } catch (JsonProcessingException e) {
            log.error("Five : SMS-Sender consumer template error : {} ", e.getMessage());
        }
    }

    @KafkaListener(topicPartitions = @TopicPartition(topic = "broad-sms-topic", partitions = {"5"}),
            containerFactory = "kafkaListenerContainerFactory")
    public void consumeTemplateSix(String message) {
        try {
            TemplatesGenerated template = objectMapper.readValue(message, TemplatesGenerated.class);
            templateService.save(template);
            log.info("Consumer Six : Email consume the template : {} ", template.toString());
        } catch (JsonProcessingException e) {
            log.error("Six : SMS-Sender consumer template error : {} ", e.getMessage());
        }
    }

}
