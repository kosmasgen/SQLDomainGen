package com.example.entities;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "Orders")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Orders {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "customer_id")
    private Long customerId;

    @Column(name = "order_date", columnDefinition = "DEFAULT 'CURRENT_TIMESTAMP'")
    private java.time.LocalDateTime orderDate;

    @Column(name = "total_amount", precision = 10, scale = 2, columnDefinition = "CHECK ((total_amount >= 0))")
    private java.math.BigDecimal totalAmount;

    @Column(name = "status", length = 50, columnDefinition = "CHECK ((statusIN('Pending','Shipped','Delivered','Cancelled')))")
    private String status;

}
