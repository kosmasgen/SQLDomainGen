package com.sqldomaingen.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.util.List;
import com.sqldomaingen.dto.EnrollmentDTO;
import com.sqldomaingen.dto.ProfessorDTO;
import com.sqldomaingen.dto.StudentDTO;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CourseDTO {

    @NotNull(message = "{validation.course.id.notnull}")
    private Long id;

    @NotNull(message = "{validation.course.title.notnull}")
    @Size(max = 100, message = "{validation.course.title.size}")
    private String title;

    private ProfessorDTO professor;

    private List<EnrollmentDTO> enrollments;

    private List<StudentDTO> students;

}

