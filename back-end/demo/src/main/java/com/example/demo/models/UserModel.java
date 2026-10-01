package com.example.demo.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.ToString;

import java.util.UUID;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.example.demo.dto.RegisterRequest;

@Entity
@Table(name = "users")
@ToString
public class UserModel {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    public UUID uuid;

    @Column(name = "username", nullable = false, unique = true)
    public String userName;

    @Column(name = "email", nullable = false, unique = true)
    public String email;

    @Column(name = "password", nullable = false)
    public String userPassword;

    public UserModel(RegisterRequest request) {
        userName = request.getUserName();
        email = request.getEmail();
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(16);
        userPassword =  "{bcrypt}"+encoder.encode(request.getPassword());
    }

    public UserModel() {
    }
}