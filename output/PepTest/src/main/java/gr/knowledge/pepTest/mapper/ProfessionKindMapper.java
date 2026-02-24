package gr.knowledge.pepTest.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import gr.knowledge.pepTest.entity.ProfessionKind;
import gr.knowledge.pepTest.dto.ProfessionKindDto;

/**
 * Mapper for {@link ProfessionKind} and {@link ProfessionKindDto}.
 */
@Component
public class ProfessionKindMapper extends BaseMapper<ProfessionKind, ProfessionKindDto> {

    public ProfessionKindMapper(ModelMapper modelMapper) {
        super(modelMapper, ProfessionKind.class, ProfessionKindDto.class);
    }
}
