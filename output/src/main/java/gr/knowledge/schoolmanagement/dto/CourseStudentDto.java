package gr.knowledge.schoolmanagement.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

/**
 * Data transfer object for CourseStudent.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CourseStudentDto {

    @NotNull(message = "{validation.coursestudent.id.notnull}")
    @Size(max = 255, message = "{validation.coursestudent.id.size}")
    private Long id;

    private Long courseId;

    private Long studentId;

}
