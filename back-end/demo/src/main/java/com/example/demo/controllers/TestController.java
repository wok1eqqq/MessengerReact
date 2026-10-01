package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.repositories.UserRepository;

@RestController
public class TestController {

    @Autowired
    public UserRepository userRepository;

    @GetMapping("/")
    public String getAllUsers() {

        return userRepository.findAll().toString();
    }
}
