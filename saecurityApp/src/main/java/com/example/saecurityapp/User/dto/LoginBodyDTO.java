package com.example.saecurityapp.User.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginBodyDTO {
    private String email;
    private String password;
}