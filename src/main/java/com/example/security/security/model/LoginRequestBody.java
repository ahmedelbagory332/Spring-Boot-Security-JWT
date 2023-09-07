package com.example.security.security.model;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginRequestBody {

    @NotNull(message = "email should not be null")
    @Email(message = "email not valid")
    private String email;

    @NotNull(message = "password should not be null or empty")
    private String password;}
