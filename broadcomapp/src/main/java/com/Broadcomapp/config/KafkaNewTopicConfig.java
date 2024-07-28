package com.Broadcomapp.config;

import jakarta.annotation.PostConstruct;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaNewTopicConfig {

    @Autowired
    private Environment env;
    private String BOOTSTRAP_SERVERS_CONFIG;

    @PostConstruct
    private void postConstruct() {
        this.BOOTSTRAP_SERVERS_CONFIG = env.getProperty("spring.kafka.bootstrap-servers");
    }

    @Bean
    public NewTopic createNewTopic(){
        return  new NewTopic("broad-email-topic",5, (short) 1);
    }
    @Bean
    public Map<String,Object> producerConfig(){
        Map<String,Object> props=new HashMap<String,Object>();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,BOOTSTRAP_SERVERS_CONFIG);
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        props.put(JsonDeserializer.TRUSTED_PACKAGES,"*");
        return props;
    }
    @Bean
    public ProducerFactory<String,Object> producerFactory(){
        return  new DefaultKafkaProducerFactory<>(producerConfig());
    }

    @Bean
    public KafkaTemplate<String,Object> kafkaTemplate(){
        return  new KafkaTemplate<>(producerFactory());
    }
}
