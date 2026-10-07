package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.repositories.UserRepository;

@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    public UserRepository userRepository;

    @GetMapping("/auth")
    public String getAllUsers() {
        return userRepository.findAll().toString();
    }
}
