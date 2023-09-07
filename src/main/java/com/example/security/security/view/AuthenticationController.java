package com.example.security.security.view;


import com.example.security.security.controller.AuthenticationService;
import com.example.security.security.exception.CustomNotFoundException;
import com.example.security.security.model.LoginRequestBody;
import com.example.security.security.model.UserResponse;
import com.example.security.security.model.CreateUser;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;

@RestController
public class AuthenticationController {

    private JwtEncoder jwtEncoder;
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    private AuthenticationService authenticationService;

    public AuthenticationController(JwtEncoder jwtEncoder, BCryptPasswordEncoder bCryptPasswordEncoder, AuthenticationService authenticationService) {
        this.jwtEncoder = jwtEncoder;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.authenticationService = authenticationService;
    }

    @PostMapping("/user-login")
    public UserResponse authenticateUser(@Valid @RequestBody LoginRequestBody user) {
        CreateUser logInUser = authenticationService.findByEmail(user.getEmail());

        if (logInUser == null)
            throw new CustomNotFoundException("not found user with this email");


        if (!bCryptPasswordEncoder.matches(user.getPassword(), logInUser.getPassword()))
            throw new CustomNotFoundException("not found user with this password");

        return new UserResponse(
                createToken(logInUser),
                logInUser.getUserName(),
                logInUser.getFirstName(),
                logInUser.getSecondName(),
                logInUser.getEmail(),
                logInUser.getAge()
        );
    }

    private String createToken(@Valid CreateUser user) {
        var claims = JwtClaimsSet.builder()
                .issuer("self")
                .issuedAt(Instant.now())
                .expiresAt(Instant.now().plusSeconds(600))
                .claim("scope", user)
                .build();

        return jwtEncoder.encode(JwtEncoderParameters.from(claims))
                .getTokenValue();
    }


    // just for test getting csrf-token
    // csrf token is disabled in JwtSecurityConfiguration
    @GetMapping("/csrf-token")
    public CsrfToken retrieveCsrfToken(HttpServletRequest request) {
        return (CsrfToken) request.getAttribute("_csrf");
    }


}

