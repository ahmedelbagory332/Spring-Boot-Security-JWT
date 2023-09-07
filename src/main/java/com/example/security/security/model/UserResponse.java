package com.example.security.security.model;

public record UserResponse(String token,
                           String userName,
                           String firstName,
                           String secondName,
                           String email,
                           String age) {}
