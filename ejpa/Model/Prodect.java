package com.example.ejpa.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
public class Prodect {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    @NotEmpty(message = " name can not be null")
    @Column(columnDefinition = "varchar(10)not null")
    private String name;

    @NotNull(message = " price can not be null")
    @Positive(message = " price can not be negative")
    @Column(columnDefinition = "double not null")
    private double price;
    @NotNull
    @Column(columnDefinition = "int not null")
    private Integer categoryId;

}
