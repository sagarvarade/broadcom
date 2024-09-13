package com.smssender.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sms-sender/sms-controller")
public class SmsController {

    @GetMapping("/hello")
    public ResponseEntity<String> getHelloWorld() {
        return new ResponseEntity<>("Hello Sms Sender", HttpStatus.OK);
    }
}
