package gr.knowledge.pepTest.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import gr.knowledge.pepTest.entity.Municipality;
import gr.knowledge.pepTest.dto.MunicipalityDto;

/**
 * Mapper for {@link Municipality} and {@link MunicipalityDto}.
 */
@Component
public class MunicipalityMapper extends BaseMapper<Municipality, MunicipalityDto> {

    public MunicipalityMapper(ModelMapper modelMapper) {
        super(modelMapper, Municipality.class, MunicipalityDto.class);
    }
}
