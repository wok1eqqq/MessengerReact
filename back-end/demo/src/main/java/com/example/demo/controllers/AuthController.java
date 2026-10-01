package com.example.demo.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.LoginRequest;
import com.example.demo.dto.RegisterRequest;
import com.example.demo.models.UserModel;
import com.example.demo.repositories.UserRepository;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired 
    private UserRepository userRepository;

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody RegisterRequest request) {
        Optional<UserModel> user = userRepository.findByUserNameOrEmail(request.getUserName(), request.getEmail());
        if (!user.isEmpty() ){
            System.out.println("user already registered");
            return ResponseEntity.badRequest().body("user already registered");
        }
        UserModel newUser = new UserModel(request);
        userRepository.save(newUser);
       
        return ResponseEntity.ok().body(null);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginRequest> login(@RequestBody LoginRequest request) {
        Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        
        return ResponseEntity.ok(request);
    }

    // @GetMapping("/login")
    // public String getLogin() {
    // System.out.println("hello");
    // return "hello";
    // }
}
