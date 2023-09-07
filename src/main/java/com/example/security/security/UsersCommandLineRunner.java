package com.example.security.security;


import com.example.security.security.controller.AuthenticationRepository;
import com.example.security.security.model.CreateUser;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UsersCommandLineRunner implements CommandLineRunner {


    private AuthenticationRepository authenticationRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public UsersCommandLineRunner(AuthenticationRepository authenticationRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.authenticationRepository = authenticationRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public void run(String... args) throws Exception {
        List<CreateUser> users = new ArrayList<>();
        users.add(new CreateUser("ahmedelbagory60", "ahmed1", "nader1", "ahmed12@gmail.com",bCryptPasswordEncoder.encode("ahmed123"), "20"));
        users.add(new CreateUser("ahmedelbagory61", "ahmed2", "nader2", "ahmed123@gmail.com", bCryptPasswordEncoder.encode("ahmed123"),"21"));
        users.add(new CreateUser("ahmedelbagory62", "ahmed3", "nader3", "ahmed1234@gmail.com", bCryptPasswordEncoder.encode("ahmed123"),"22"));
        users.add(new CreateUser("ahmedelbagory63", "ahmed4", "nader4", "ahmed12345@gmail.com",bCryptPasswordEncoder.encode("ahmed123"), "23"));


        authenticationRepository.saveAll(users);

    }
}