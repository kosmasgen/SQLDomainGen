package ./GeneratedEntities;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;
import java.util.ArrayList;

@Entity
@Table(name = "products")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Products {

    @ManyToMany(mappedBy = "Orders", fetch = FetchType.LAZY)
    private List<Orders> orders = new ArrayList<>();

}
