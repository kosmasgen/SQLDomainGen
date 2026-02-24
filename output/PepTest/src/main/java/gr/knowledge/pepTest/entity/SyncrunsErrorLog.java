package gr.knowledge.pepTest.entity;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "syncruns_error_log")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SyncrunsErrorLog {

    @Id
    @Column(name = "id", nullable = false)
    private String id;

    @Column(name = "error_message")
    private String errorMessage;

    @Column(name = "table_name", length = 255, nullable = false)
    private String tableName;

    @Column(name = "table_chamber_id", nullable = false)
    private BigDecimal tableChamberId;

    @Column(name = "email_send_date")
    private LocalDateTime emailSendDate;

}
