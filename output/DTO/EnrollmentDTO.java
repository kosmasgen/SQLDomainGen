package com.sqldomaingen.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.sqldomaingen.dto.CourseDTO;
import com.sqldomaingen.dto.StudentDTO;
import jakarta.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class EnrollmentDTO {

    @NotNull(message = "{validation.enrollment.id.notnull}")
    private Long id;

    private StudentDTO student;

    private CourseDTO course;

}

