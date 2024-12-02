package com.example.ejpa.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty(message = "Name must not be empty")
    @Size(min = 3, message = "Name must be more than 3 characters long")
    @Column(columnDefinition = "varchar(255) not null")
    private String name;
}
