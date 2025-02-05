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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private Users Users;

    @Column(name = "order_date", columnDefinition = "DEFAULT 'CURRENT_TIMESTAMP'")
    private java.time.LocalDateTime orderDate;

    @Column(name = "total_price", precision = 10, scale = 2, nullable = false)
    private java.math.BigDecimal totalPrice;

}
