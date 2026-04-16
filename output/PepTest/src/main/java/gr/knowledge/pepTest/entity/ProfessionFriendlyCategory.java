package gr.knowledge.pepTest.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.envers.Audited;

@Entity
@Audited
@Table(name = "profession_friendly_category")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProfessionFriendlyCategory {

    @Id
    @Column(name = "id", length = 100, nullable = false)
    private String id;

    @Column(name = "descr", length = 1000)
    private String descr;

}
