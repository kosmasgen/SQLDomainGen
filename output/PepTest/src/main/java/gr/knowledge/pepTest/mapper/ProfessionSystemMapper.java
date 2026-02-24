package gr.knowledge.pepTest.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import gr.knowledge.pepTest.entity.ProfessionSystem;
import gr.knowledge.pepTest.dto.ProfessionSystemDto;

/**
 * Mapper for {@link ProfessionSystem} and {@link ProfessionSystemDto}.
 */
@Component
public class ProfessionSystemMapper extends BaseMapper<ProfessionSystem, ProfessionSystemDto> {

    public ProfessionSystemMapper(ModelMapper modelMapper) {
        super(modelMapper, ProfessionSystem.class, ProfessionSystemDto.class);
    }
}
