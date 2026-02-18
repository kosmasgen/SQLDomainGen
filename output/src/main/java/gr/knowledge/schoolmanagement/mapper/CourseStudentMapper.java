package gr.knowledge.schoolmanagement.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import gr.knowledge.schoolmanagement.entity.CourseStudent;
import gr.knowledge.schoolmanagement.dto.CourseStudentDto;

/**
 * Mapper for {@link CourseStudent} and {@link CourseStudentDto}.
 */
@Component
public class CourseStudentMapper extends BaseMapper<CourseStudent, CourseStudentDto> {

    public CourseStudentMapper(ModelMapper modelMapper) {
        super(modelMapper, CourseStudent.class, CourseStudentDto.class);
    }
}
