package com.sqldomaingen.mappers;

import org.springframework.stereotype.Component;
import com.sqldomaingen.dto.CourseDTO;
import com.sqldomaingen.entities.Course;
import com.sqldomaingen.mappers.BaseMapper;
import java.util.List;

import com.sqldomaingen.dto.EnrollmentDTO;
import com.sqldomaingen.mappers.EnrollmentMapper;

@Component
public class CourseMapper implements BaseMapper<Course, CourseDTO> {

}
