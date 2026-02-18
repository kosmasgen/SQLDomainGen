package gr.kosmas.schoolmanagement.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name = "recurring_pattern")
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RecurringPattern {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pattern_id", nullable = false)
    private Long patternId;

    @Column(name = "pattern_type", length = 50, nullable = false)
    private String patternType;

    @Column(name = "frequency", length = 50)
    private String frequency;

    @Column(name = "days_of_week", length = 50)
    private String daysOfWeek;

    @Column(name = "day_of_month")
    private Long dayOfMonth;

    @Column(name = "month_of_year")
    private Long monthOfYear;

    @Column(name = "end_date")
    private LocalDate endDate;

    @Column(name = "end_after_occur")
    private Long endAfterOccur;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @OneToMany(mappedBy = "recurPattern", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DepartmentDayOff> departmentDayOffs = new ArrayList<>();

    @OneToMany(mappedBy = "recurPattern", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Holiday> holidays = new ArrayList<>();

    @OneToMany(mappedBy = "recurPattern", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Event> events = new ArrayList<>();

}
