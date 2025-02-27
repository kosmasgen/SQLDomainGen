package com.sqldomaingen.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CourseDTO {

    @NotNull(message = "{validation.course.id.notnull}")
    private Long id;

    @Size(max = 100, message = "{validation.course.title.size}")
    private String title;

}

