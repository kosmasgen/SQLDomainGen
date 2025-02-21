package com.sqldomaingen.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.util.List;
import com.sqldomaingen.dto.EnrollmentDTO;
import com.sqldomaingen.dto.CourseDTO;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class StudentDTO {

    @NotNull(message = "{validation.student.id.notnull}")
    private Long id;

    @Size(max = 100, message = "{validation.student.sName.size}")
    private String sName;

    private List<EnrollmentDTO> enrollments;

    private List<CourseDTO> courses;

}

