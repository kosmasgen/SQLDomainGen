package gr.knowledge.schoolmanagement.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import gr.knowledge.schoolmanagement.entity.Course;
import gr.knowledge.schoolmanagement.dto.CourseDto;

/**
 * Mapper for {@link Course} and {@link CourseDto}.
 */
@Component
public class CourseMapper extends BaseMapper<Course, CourseDto> {

    public CourseMapper(ModelMapper modelMapper) {
        super(modelMapper, Course.class, CourseDto.class);
    }
}
