package gr.knowledge.pepTest.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import gr.knowledge.pepTest.entity.ProfessionKindi18n;
import gr.knowledge.pepTest.dto.ProfessionKindi18nDto;

/**
 * Mapper for {@link ProfessionKindi18n} and {@link ProfessionKindi18nDto}.
 */
@Component
public class ProfessionKindi18nMapper extends BaseMapper<ProfessionKindi18n, ProfessionKindi18nDto> {

    public ProfessionKindi18nMapper(ModelMapper modelMapper) {
        super(modelMapper, ProfessionKindi18n.class, ProfessionKindi18nDto.class);
    }
}
