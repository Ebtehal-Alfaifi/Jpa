package com.example.ejpa.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
public class Stock {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Product ID cannot be null")
    @Column(columnDefinition = "varchar(20) not null")
    private Integer productId;

    @NotNull(message = "Merchant ID cannot be null")
    @Column(columnDefinition = "varchar(20) not null")
    private Integer merchantId;

    @NotNull(message = "Stock cannot be null")
    @Positive(message = "Stock must be greater than 10")
    @Column(columnDefinition = "int not null")
    private Integer stock;


}
