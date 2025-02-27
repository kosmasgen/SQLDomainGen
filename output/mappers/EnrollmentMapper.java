package com.sqldomaingen.mappers;

import org.springframework.stereotype.Component;
import com.sqldomaingen.dto.EnrollmentDTO;
import com.sqldomaingen.entities.Enrollment;
import com.sqldomaingen.mappers.BaseMapper;
import java.util.List;

import com.sqldomaingen.dto.StudentDTO;
import com.sqldomaingen.mappers.StudentMapper;

import com.sqldomaingen.dto.CourseDTO;
import com.sqldomaingen.mappers.CourseMapper;

@Component
public class EnrollmentMapper implements BaseMapper<Enrollment, EnrollmentDTO> {

}
