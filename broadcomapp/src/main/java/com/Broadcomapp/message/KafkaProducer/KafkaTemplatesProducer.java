package com.Broadcomapp.message.KafkaProducer;


import com.Broadcomapp.message.beans.TemplatesGenerated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class KafkaTemplatesProducer {
    @Autowired
    private KafkaTemplate<String,Object> template;

    public void sendStudentToTopic(String topic, String key, TemplatesGenerated templatesGenerated){
        CompletableFuture<SendResult<String, Object>> send = template.send(topic,key, templatesGenerated);
        send.whenComplete((res,ex)->{
            if(ex==null){
                System.out.println("templatesGenerated send  to topic : : "+res.getRecordMetadata().offset());
            }else{
                System.out.println("Unable to Sent templatesGenerated : "+ex.getMessage());
            }
        });
    }
}
