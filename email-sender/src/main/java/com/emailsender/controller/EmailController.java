package com.emailsender.controller;

import com.emailsender.beans.EmailCredential;
import com.emailsender.service.EmailCredentialService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/email-sender")
public class EmailController {

    private final Logger log = LoggerFactory.getLogger(EmailController.class);

    @Autowired
    public EmailCredentialService emailCredentialService;

    @GetMapping("/hello")
    public ResponseEntity<String> getHelloWorld() {
        return new ResponseEntity<>("Hello Email Sender", HttpStatus.OK);
    }


    @PostMapping("/save-email-credential")
    public ResponseEntity<EmailCredential> saveEmailCredForUser(@RequestBody EmailCredential emailCredential,@RequestHeader("user_id") String userID){
        log.info("/email-sender/save-email-credential called ");
        log.info(" User Id : {} ",userID);
        try{
            LocalDateTime now=LocalDateTime.now();
            emailCredential.setCreatedBy(userID);
            emailCredential.setUpdatedBy(userID);
            emailCredential.setCreatedDate(now);
            emailCredential.setUpdatedDate(now);
            return new ResponseEntity<>(emailCredentialService.saveEmailCredentials(emailCredential),HttpStatus.OK);
        }
        catch(Exception e){
            log.info("/email-sender/save-email-credential called  Error : {} ",e.getMessage());
        }
        return  ResponseEntity.noContent().build();
    }

}
