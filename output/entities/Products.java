package com.example.entities;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;

@Entity
@Table(name = "Products")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Products {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "poduct_name", length = 100, nullable = false)
    private String poductName;

    @Column(name = "price", precision = 10, scale = 2, nullable = false)
    private java.math.BigDecimal price;

    @Column(name = "stock_quantity", nullable = false)
    private Long stockQuantity;

}
