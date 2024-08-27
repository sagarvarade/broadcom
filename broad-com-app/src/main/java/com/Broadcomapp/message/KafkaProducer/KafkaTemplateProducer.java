package com.Broadcomapp.message.KafkaProducer;


import com.Broadcomapp.message.beans.TemplatesGenerated;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class KafkaTemplateProducer {

    private final Logger log = LoggerFactory.getLogger(KafkaTemplateProducer.class);

    @Autowired
    private KafkaTemplate<String,Object> template;

    public void sendTemplateToTopic(String topic, String key, TemplatesGenerated templatesGenerated){
        CompletableFuture<SendResult<String, Object>> send = template.send(topic,key, templatesGenerated);
        send.whenComplete((res,ex)->{
            if(ex==null){
                log.info("templatesGenerated send  to topic : {} ",res.getRecordMetadata().offset());
            }else{
                log.info("Unable to Sent templatesGenerated : {} ",ex.getMessage());
            }
        });
    }
}
