package ./GeneratedEntities;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;
import java.util.ArrayList;

@Entity
@Table(name = "orders")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Orders {

    @ManyToMany(mappedBy = "products", fetch = FetchType.LAZY)
    private List<Products> products = new ArrayList<>();

    @ManyToMany(mappedBy = "products", fetch = FetchType.LAZY)
    private List<Products> products = new ArrayList<>();

}
