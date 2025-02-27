package com.sqldomaingen.mappers;

import org.springframework.stereotype.Component;
import com.sqldomaingen.dto.ProfessorDTO;
import com.sqldomaingen.entities.Professor;
import com.sqldomaingen.mappers.BaseMapper;
import java.util.List;

import com.sqldomaingen.dto.CourseDTO;
import com.sqldomaingen.mappers.CourseMapper;

@Component
public class ProfessorMapper implements BaseMapper<Professor, ProfessorDTO> {

}
