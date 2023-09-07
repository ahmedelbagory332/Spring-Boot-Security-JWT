package com.example.security.security.exception;



import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.UNAUTHORIZED)
public class CustomUnauthorizedException extends RuntimeException {

    public CustomUnauthorizedException(String token) {
        super(token);
    }

}