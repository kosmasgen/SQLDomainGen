package gr.knowledge.pepTest.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import gr.knowledge.pepTest.entity.Languages;
import gr.knowledge.pepTest.dto.LanguagesDto;

/**
 * Mapper for {@link Languages} and {@link LanguagesDto}.
 */
@Component
public class LanguagesMapper extends BaseMapper<Languages, LanguagesDto> {

    public LanguagesMapper(ModelMapper modelMapper) {
        super(modelMapper, Languages.class, LanguagesDto.class);
    }
}
