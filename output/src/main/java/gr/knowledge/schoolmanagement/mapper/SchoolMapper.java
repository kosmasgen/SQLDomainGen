package gr.knowledge.schoolmanagement.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import gr.knowledge.schoolmanagement.entity.School;
import gr.knowledge.schoolmanagement.dto.SchoolDto;

/**
 * Mapper for {@link School} and {@link SchoolDto}.
 */
@Component
public class SchoolMapper extends BaseMapper<School, SchoolDto> {

    public SchoolMapper(ModelMapper modelMapper) {
        super(modelMapper, School.class, SchoolDto.class);
    }
}
