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
 * Data transfer object for Student.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class StudentDto {

    @NotNull(message = "{validation.student.id.notnull}")
    @Size(max = 255, message = "{validation.student.id.size}")
    private Long id;

    @NotNull(message = "{validation.student.firstName.notnull}")
    @Size(max = 100, message = "{validation.student.firstName.size}")
    private String firstName;

    @NotNull(message = "{validation.student.lastName.notnull}")
    @Size(max = 100, message = "{validation.student.lastName.size}")
    private String lastName;

    @NotNull(message = "{validation.student.email.notnull}")
    @Size(max = 255, message = "{validation.student.email.size}")
    @Pattern(regexp = ".*", message = "{validation.student.email.pattern}")
    private String email;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @Size(max = 255, message = "{validation.student.enrollmentDate.size}")
    private LocalDate enrollmentDate;

    private Long schoolId;

    private Long teacherId;

}
