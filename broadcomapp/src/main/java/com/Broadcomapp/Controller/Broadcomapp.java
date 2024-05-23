package com.Broadcomapp.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/broadcomappp")
public class Broadcomapp {

    @GetMapping("/hello")
    public String hello1() {
        return "Broadcom app hello called";
    }
}
