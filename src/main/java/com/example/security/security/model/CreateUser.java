package com.example.security.security.model;


import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Entity(name = "createUser")
@Getter
@Setter
public class CreateUser {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Size(min = 6, message = "userName should have at least 6 characters")
    @NotNull(message = "userName should not be null")
    private String userName;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Size(min = 6, message = "firstName should have at least 6 characters")
    @NotNull(message = "firstName should not be null")
    private String firstName;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Size(min = 6, message = "secondName should have at least 6 characters")
    @NotNull(message = "secondName should not be null")
    private String secondName;

    @NotNull(message = "email should not be null")
    @Email(message = "email not valid")
    private String email;

    @NotNull(message = "password should not be null")
    private String password;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @NotNull(message = "age should not be null")
    private String age;

    public CreateUser() {
    }

    public CreateUser(String userName, String firstName, String secondName, String email, String password, String age) {
        this.userName = userName;
        this.firstName = firstName;
        this.secondName = secondName;
        this.email = email;
        this.password = password;
        this.age = age;
    }
}
