package ./GeneratedEntities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "order_products")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class OrderProducts {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
, nullable = false
    private Long orderId;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
, nullable = false
    private Long productId;

}
