package gr.knowledge.pepTest.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.envers.Audited;

@Entity
@Audited
@Table(name = "ch_app_user_contact_i18n")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChAppUserContactI18n {

    @EmbeddedId
    private ChAppUserContactI18nKey id;

    @MapsId("chAppUserContactId")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ch_app_user_contact_id", nullable = false)
    private ChAppUserContact chAppUserContact;

    @MapsId("languageId")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "language_id", nullable = false)
    private Languages language;

    @CreationTimestamp
    @Column(name = "date_created", updatable = false)
    private LocalDateTime dateCreated;

    @Column(name = "last_updated")
    private LocalDateTime lastUpdated;

    @Column(name = "city", length = 50)
    private String city;

    @Column(name = "street")
    private String street;

    @Column(name = "recdeleted", nullable = false)
    private Boolean recdeleted;

}
