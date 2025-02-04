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

    @ManyToMany
    @JoinTable(name = "", joinColumns = @JoinColumn(name = "order_id"), inverseJoinColumns = @JoinColumn(name = ""))
    @Column(name = "order_id")
    private Long orderId;

    @ManyToMany
    @JoinTable(name = "", joinColumns = @JoinColumn(name = "product_id"), inverseJoinColumns = @JoinColumn(name = ""))
    @Column(name = "product_id")
    private Long productId;

    @Column(name = "quantity", nullable = false)
    private Long quantity;

}
