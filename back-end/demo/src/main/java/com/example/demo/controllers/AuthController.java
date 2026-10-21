package com.example.demo.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.RestController;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.core.context.SecurityContext;
import com.example.demo.dto.LoginRequest;
import com.example.demo.dto.RegisterRequest;
import com.example.demo.models.UserModel;
import com.example.demo.repositories.UserRepository;
import com.example.demo.services.JwtService;

import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
private JwtService jwtService;

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody RegisterRequest request) {
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
public ResponseEntity<?> login(
        @RequestBody LoginRequest request) {

    Authentication authentication =
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getUsername(),
                            request.getPassword()));

    UserDetails user =
            (UserDetails) authentication.getPrincipal();

    String jwt =
            jwtService.generateToken(user);

    return ResponseEntity.ok(jwt);
}
    // @GetMapping("/login")
    // public String getLogin() {
    // System.out.println("hello");
    // return "hello";
    // }
}
