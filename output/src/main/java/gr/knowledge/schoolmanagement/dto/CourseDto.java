package gr.knowledge.schoolmanagement.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

/**
 * Data transfer object for Course.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CourseDto {

    @NotNull(message = "{validation.course.id.notnull}")
    @Size(max = 255, message = "{validation.course.id.size}")
    private Long id;

    @Size(max = 50, message = "{validation.course.code.size}")
    private String code;

    @NotNull(message = "{validation.course.title.notnull}")
    @Size(max = 255, message = "{validation.course.title.size}")
    private String title;

    @Size(max = 255, message = "{validation.course.description.size}")
    private String description;

    @Size(max = 255, message = "{validation.course.credits.size}")
    private Long credits;

    private Long schoolId;

    private Long teacherId;

}
