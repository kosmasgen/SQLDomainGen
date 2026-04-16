package gr.knowledge.pepTest.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.envers.Audited;

@Entity
@Audited
@Table(name = "chamber_departmenti18n", uniqueConstraints = @UniqueConstraint(columnNames = {"department_id", "chamber_i18n_id"}))
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChamberDepartmenti18n {

    @EmbeddedId
    private ChamberDepartmenti18nKey id;

    @MapsId("departmentId")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "department_id", nullable = false)
    private ChamberDepartment department;

    @MapsId("languageId")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "language_id", nullable = false)
    private Languages language;

    @Column(name = "description", length = 50, nullable = false)
    private String description;

    @CreationTimestamp
    @Column(name = "date_created", updatable = false)
    private LocalDateTime dateCreated;

    @Column(name = "last_updated")
    private LocalDateTime lastUpdated;

    @Column(name = "recdeleted", nullable = false)
    private Boolean recdeleted;

    @Column(name = "chamber_i18n_id")
    private Integer chamberI18nId;

}
