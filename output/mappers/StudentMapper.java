package com.sqldomaingen.mappers;

import org.springframework.stereotype.Component;
import com.sqldomaingen.dto.StudentDTO;
import com.sqldomaingen.entities.Student;
import com.sqldomaingen.mappers.BaseMapper;
import java.util.List;

import com.sqldomaingen.dto.EnrollmentDTO;
import com.sqldomaingen.mappers.EnrollmentMapper;

@Component
public class StudentMapper implements BaseMapper<Student, StudentDTO> {

}
