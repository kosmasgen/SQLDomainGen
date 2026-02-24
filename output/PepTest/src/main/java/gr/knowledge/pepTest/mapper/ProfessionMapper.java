package gr.knowledge.pepTest.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import gr.knowledge.pepTest.entity.Profession;
import gr.knowledge.pepTest.dto.ProfessionDto;

/**
 * Mapper for {@link Profession} and {@link ProfessionDto}.
 */
@Component
public class ProfessionMapper extends BaseMapper<Profession, ProfessionDto> {

    public ProfessionMapper(ModelMapper modelMapper) {
        super(modelMapper, Profession.class, ProfessionDto.class);
    }
}
