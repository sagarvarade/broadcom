package com.emailsender.controller;

import com.emailsender.beans.EmailCredential;
import com.emailsender.service.EmailCredentialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/email-sender")
public class EmailController {

    @Autowired
    public EmailCredentialService emailCredentialService;

    @GetMapping("/hello")
    public ResponseEntity<String> getHelloWorld() {
        return new ResponseEntity<>("Hello Email Sender", HttpStatus.OK);
    }


    @PostMapping("/save-email-credential")
    public EmailCredential saveEmailCredForUser(@RequestBody EmailCredential emailCredential,@RequestHeader("user_id") String user_id){
        return  null;
    }

}
