package com.example.saecurityapp.User.model;

import com.example.saecurityapp.Role.Role;
import com.example.saecurityapp.Utils.enums.Sex;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.UUID;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false)
    private UUID id;
    private String firstname;
    private String lastname;
    private String email;
    private String password;
    private String address;
    private String phone;
    private Sex sex;
    private LocalDate birthdate;
    @ManyToMany(fetch = FetchType.EAGER)
    protected Collection<Role> roles = new ArrayList<>();
}
