package gr.knowledge.schoolmanagement.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;

/**
 * Data transfer object for Teacher.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TeacherDto {

    @NotNull(message = "{validation.teacher.id.notnull}")
    @Size(max = 255, message = "{validation.teacher.id.size}")
    private Long id;

    @NotNull(message = "{validation.teacher.firstName.notnull}")
    @Size(max = 100, message = "{validation.teacher.firstName.size}")
    private String firstName;

    @NotNull(message = "{validation.teacher.lastName.notnull}")
    @Size(max = 100, message = "{validation.teacher.lastName.size}")
    private String lastName;

    @NotNull(message = "{validation.teacher.email.notnull}")
    @Size(max = 255, message = "{validation.teacher.email.size}")
    @Pattern(regexp = ".*", message = "{validation.teacher.email.pattern}")
    private String email;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @Size(max = 255, message = "{validation.teacher.enrollmentDate.size}")
    private LocalDate enrollmentDate;

    private Long schoolId;

}
