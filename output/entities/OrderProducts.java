package com.example.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "OrderProducts")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class OrderProducts {

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "OrderProducts", joinColumns = @JoinColumn(name = "order_id"), inverseJoinColumns = @JoinColumn(name = "product_id"))
    private List<Orders> Orders = new ArrayList<>();

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "OrderProducts", joinColumns = @JoinColumn(name = "product_id"), inverseJoinColumns = @JoinColumn(name = "order_id"))
    private List<Products> Products = new ArrayList<>();

    @Column(name = "quantity", nullable = false)
    private Long quantity;

}
