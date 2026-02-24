package gr.knowledge.pepTest.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import gr.knowledge.pepTest.entity.BusinessLocation;
import gr.knowledge.pepTest.dto.BusinessLocationDto;

/**
 * Mapper for {@link BusinessLocation} and {@link BusinessLocationDto}.
 */
@Component
public class BusinessLocationMapper extends BaseMapper<BusinessLocation, BusinessLocationDto> {

    public BusinessLocationMapper(ModelMapper modelMapper) {
        super(modelMapper, BusinessLocation.class, BusinessLocationDto.class);
    }
}
