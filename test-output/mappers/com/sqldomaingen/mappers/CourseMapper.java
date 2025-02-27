package com.sqldomaingen.mappers;

import com.sqldomaingen.dto.CourseDTO;
import com.sqldomaingen.entities.Course;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import java.util.List;

@Mapper
public interface CourseMapper {

    CourseMapper INSTANCE = Mappers.getMapper(CourseMapper.class);

    CourseDTO toDTO(Course entity);

    Course toEntity(CourseDTO dto);

    List<CourseDTO> toDTOList(List<Course> entities);

    List<Course> toEntityList(List<CourseDTO> dtos);
}
