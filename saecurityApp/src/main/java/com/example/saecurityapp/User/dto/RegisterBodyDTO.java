package com.example.saecurityapp.User.dto;

import com.example.saecurityapp.Utils.enums.Sex;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class RegisterBodyDTO {
    private String firstname;
    private String lastname;
    private String email;
    private String password;
    private String address;
    private String phone;
    private Sex sex;
    private String role;
    private LocalDate birthdate;
}

