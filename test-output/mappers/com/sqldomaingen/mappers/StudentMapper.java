package com.sqldomaingen.mappers;

import com.sqldomaingen.dto.StudentDTO;
import com.sqldomaingen.entities.Student;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import java.util.List;

@Mapper
public interface StudentMapper {

    StudentMapper INSTANCE = Mappers.getMapper(StudentMapper.class);

    StudentDTO toDTO(Student entity);

    Student toEntity(StudentDTO dto);

    List<StudentDTO> toDTOList(List<Student> entities);

    List<Student> toEntityList(List<StudentDTO> dtos);
}
