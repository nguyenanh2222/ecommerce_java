package com.example.restservice.payload;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class LoginRequest {

    @NotBlank
    private String userName;

    @NotBlank
    private String password;

    public String userName() {
        return userName;
    }

    public LoginRequest setUserName(String userName) {
        this.userName = userName;
        return this;
    }
}
