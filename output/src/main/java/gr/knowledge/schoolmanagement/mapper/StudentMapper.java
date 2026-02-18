package gr.knowledge.schoolmanagement.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import gr.knowledge.schoolmanagement.entity.Student;
import gr.knowledge.schoolmanagement.dto.StudentDto;

/**
 * Mapper for {@link Student} and {@link StudentDto}.
 */
@Component
public class StudentMapper extends BaseMapper<Student, StudentDto> {

    public StudentMapper(ModelMapper modelMapper) {
        super(modelMapper, Student.class, StudentDto.class);
    }
}
