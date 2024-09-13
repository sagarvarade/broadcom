package com.apigateway.clients;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class CheckToken {
    private final Logger log = LoggerFactory.getLogger(CheckToken.class);

    @Autowired
    RestTemplate restTemplate;
    @Value("${broadcom_communication_token}")
    String broadcom_communication_token;
    @Value("${authentication_service_url}")
    String AUTH_URL;

    public ResponseEntity<String> checkToken(String token) {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.set("broadcom_communication_token", broadcom_communication_token);
            headers.set("Authorization", "Bearer " + token);
            HttpEntity<String> entity = new HttpEntity<>(headers);
            log.info("Check Token , Calling : AUTH_URL  {} ",AUTH_URL);
            ResponseEntity<String> response = restTemplate.exchange(AUTH_URL,
                    HttpMethod.GET,
                    entity, String.class);
            log.info("Response: {} ", response.getBody());
            log.info("Response status : {} ", response.getStatusCode());
            return response;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>("Error", HttpStatus.BAD_REQUEST);
    }
}
