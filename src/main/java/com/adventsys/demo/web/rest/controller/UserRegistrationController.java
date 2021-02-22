package com.adventsys.demo.web.rest.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserRegistrationController {

    @GetMapping("/getUser")
    public String method1(){
        return "hello";
    }
}
