package gr.knowledge.pepTest.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.LocalTime;
import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * Data transfer object for WorkingHours.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class WorkingHoursDto {

    private Long id;

    @NotNull
    private CompanyDto company;

    @NotNull
    @Size(max = 20)
    private String dayOfWeek;

    @JsonFormat(pattern = "HH:mm:ss")
    private LocalTime openingTime;

    @JsonFormat(pattern = "HH:mm:ss")
    private LocalTime closingTime;

    @NotNull
    private Boolean isClosed;

    private CompanyProfileDto profile;

}
