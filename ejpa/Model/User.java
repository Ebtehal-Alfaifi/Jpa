package com.example.ejpa.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Check;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
@NoArgsConstructor
@Entity

public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    @NotEmpty(message = "Username must not be empty")
    @Size(min = 5, message = "Username must be more than 5 characters long")
    @Column(columnDefinition = "varchar(20) not null")
    private String username;

    @NotEmpty(message = "Password must not be empty")
    @Size(min = 6, message = "Password must be more than 6 characters long")
    @Pattern(
            regexp = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]+$",
            message = "Password must contain both letters and digits"
    )
    @Column(columnDefinition = "varchar(20) not null")
    private String password;

    @NotEmpty(message = "Email must not be empty")
    @Email(message = "Must be a valid email")
    @Column(columnDefinition = "varchar(50) not null")
    private String email;

    @NotEmpty(message = "Role must not be empty")
    @Pattern(
            regexp = "^(Admin|Customer)$",
            message = "Role must be either 'Admin' or 'Customer'"
    )
    @Column(columnDefinition = "varchar(10) not null")
    private String role;

    @NotNull(message = "Balance must not be empty")
    @Positive(message = "Balance must be positive")
    @Column(columnDefinition = "double not null")
    private Double balance;



    }




