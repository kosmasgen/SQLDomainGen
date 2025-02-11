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

    @Column(name = "quantity", nullable = false)
    private Long quantity;

}
