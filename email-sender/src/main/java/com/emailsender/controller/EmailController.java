package com.emailsender.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/email-sender")
public class EmailController {

    @GetMapping("/hello")
    public ResponseEntity<String> getHelloWorld() {
        return new ResponseEntity<>("Hello Email Sender", HttpStatus.OK);
    }
}
