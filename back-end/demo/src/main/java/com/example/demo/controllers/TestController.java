package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.repositories.UserRepository;
import org.springframework.security.core.Authentication;

@RestController
public class TestController {

    @GetMapping("/test/auth")
    public String auth(Authentication authentication) {
        return authentication.getName();
    }

}