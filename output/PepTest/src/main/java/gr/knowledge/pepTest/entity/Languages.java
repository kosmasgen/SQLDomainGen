package gr.knowledge.pepTest.entity;

import jakarta.persistence.*;
import lombok.*;
import java.util.UUID;
import org.hibernate.envers.Audited;

@Entity
@Audited
@Table(name = "languages")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Languages {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false)
    private UUID id;

    @Column(name = "chamber_id", nullable = false)
    private Integer chamberId;

    @Column(name = "cd", length = 3, unique = true, nullable = false)
    private String cd;

    @Column(name = "descr", length = 50, nullable = false)
    private String descr;

    @Column(name = "chamber_language_id", unique = true)
    private Integer chamberLanguageId;

}
