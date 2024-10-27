package com.Broadcomapp.message.KafkaProducer;


import com.Broadcomapp.message.beans.TemplatesGenerated;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
@Slf4j
public class KafkaTemplateProducer {

    @Autowired
    private KafkaTemplate<String,Object> template;

    @Autowired
    private ObjectMapper objectMapper;

    public void sendTemplateToTopic(String topic, String key, TemplatesGenerated templatesGenerated){
        try {
            String jsonString = objectMapper.writeValueAsString(templatesGenerated);
            CompletableFuture<SendResult<String, Object>> send = template.send(topic,key, jsonString);
            send.whenComplete((res,ex)->{
                if(ex==null){
                    log.info("templatesGenerated send  to topic : {} ",res.getRecordMetadata().offset());
                }else{
                    log.info("Unable to Sent templatesGenerated : {} ",ex.getMessage());
                }
            });
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
