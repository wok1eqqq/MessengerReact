package com.example.demo.controllers;

import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.context.SecurityContextRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.LoginRequest;
import com.example.demo.dto.RegisterRequest;
import com.example.demo.models.UserModel;
import com.example.demo.repositories.UserRepository;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final SecurityContextRepository securityContextRepository;

    AuthController(
        AuthenticationManager authenticationManager,
        UserRepository userRepository,
        SecurityContextRepository securityContextRepository
    ) {
        this.authenticationManager = authenticationManager;
        this.securityContextRepository = securityContextRepository;
        this.userRepository = userRepository;
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterRequest request) {
        Optional<UserModel> user = userRepository.findByUserNameOrEmail(request.getUserName(), request.getEmail());
        if (!user.isEmpty()) {
            System.out.println("user already registered");
            return ResponseEntity.badRequest().body("user already registered");
        }
        UserModel newUser = new UserModel(request);
        userRepository.save(newUser);

        return ResponseEntity.ok().body(null);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginRequest> login(
        @RequestBody LoginRequest request,
        HttpServletRequest httpRequest,
        HttpServletResponse httpServletResponseResponse
    ) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));

        SecurityContext securityContextHolder;
        securityContextHolder = SecurityContextHolder.createEmptyContext();
        securityContextHolder.setAuthentication(authentication);

        SecurityContextHolder.setContext(securityContextHolder);
        securityContextRepository.saveContext(securityContextHolder, httpRequest, httpServletResponseResponse);

        return ResponseEntity.ok(request);
    }
}
