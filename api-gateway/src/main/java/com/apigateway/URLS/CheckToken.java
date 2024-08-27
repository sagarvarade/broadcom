package com.apigateway.URLS;

import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class CheckToken {
    private final Logger log = LoggerFactory.getLogger(CheckToken.class);

    @Autowired
    private Environment env;

    @Autowired
    RestTemplate restTemplate;
    private String broadcom_communication_token;
    private String AUTH_URL;

    @PostConstruct
    private void postConstruct() {
        this.AUTH_URL = env.getProperty("authentication_service_url");
        this.broadcom_communication_token = env.getProperty("broadcom_communication_token");
    }

    public ResponseEntity<String> checkToken(String token) {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.set("broadcom_communication_token", broadcom_communication_token);
            headers.set("Authorization", "Bearer " + token);
            HttpEntity<String> entity = new HttpEntity<>(headers);
            ResponseEntity<String> response = restTemplate.exchange(AUTH_URL + "/auth/test-token",
                    HttpMethod.GET,
                    entity, String.class);
            log.info("Response: {} ", response.getBody());
            log.info("Response status : {} ", response.getStatusCode());
            return response;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<String>("Error", HttpStatus.BAD_REQUEST);
    }
}
