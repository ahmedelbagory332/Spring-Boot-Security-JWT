package com.example.security.security.controller;


import com.example.security.security.model.CreateUser;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthenticationService {

   private AuthenticationRepository authenticationRepository;

    public AuthenticationService(AuthenticationRepository authenticationRepository) {
        this.authenticationRepository = authenticationRepository;
    }


    public CreateUser findByEmail(String email) {
        Optional<CreateUser> user = authenticationRepository.findByEmail(email);
        return user.orElse(null);

    }
}
