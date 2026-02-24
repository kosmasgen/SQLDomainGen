package gr.knowledge.pepTest.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "profession_friendly_category")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProfessionFriendlyCategory {

    @Id
    @Column(name = "id", length = 255, nullable = false)
    private String id;

    @Column(name = "descr", length = 255)
    private String descr;

}
