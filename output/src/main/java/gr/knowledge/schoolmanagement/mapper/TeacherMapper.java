package gr.knowledge.schoolmanagement.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import gr.knowledge.schoolmanagement.entity.Teacher;
import gr.knowledge.schoolmanagement.dto.TeacherDto;

/**
 * Mapper for {@link Teacher} and {@link TeacherDto}.
 */
@Component
public class TeacherMapper extends BaseMapper<Teacher, TeacherDto> {

    public TeacherMapper(ModelMapper modelMapper) {
        super(modelMapper, Teacher.class, TeacherDto.class);
    }
}
