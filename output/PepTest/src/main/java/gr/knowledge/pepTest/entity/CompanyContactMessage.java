package gr.knowledge.pepTest.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.UUID;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.envers.Audited;

@Entity
@Audited
@Table(name = "company_contact_message")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CompanyContactMessage {

    @Id
    @Column(name = "id", nullable = false)
    private UUID id;

    @Column(name = "full_name", length = 100)
    private String fullName;

    @Column(name = "sender_email")
    private String senderEmail;

    @Column(name = "subject", length = 150)
    private String subject;

    @Column(name = "message", length = 5000)
    private String message;

    @CreationTimestamp
    @Column(name = "date_created", nullable = false, updatable = false)
    private LocalDateTime dateCreated;

    @Column(name = "company_id")
    private UUID companyId;

}
