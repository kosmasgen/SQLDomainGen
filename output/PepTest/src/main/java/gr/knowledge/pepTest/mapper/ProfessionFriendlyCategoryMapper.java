package gr.knowledge.pepTest.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import gr.knowledge.pepTest.entity.ProfessionFriendlyCategory;
import gr.knowledge.pepTest.dto.ProfessionFriendlyCategoryDto;

/**
 * Mapper for {@link ProfessionFriendlyCategory} and {@link ProfessionFriendlyCategoryDto}.
 */
@Component
public class ProfessionFriendlyCategoryMapper extends BaseMapper<ProfessionFriendlyCategory, ProfessionFriendlyCategoryDto> {

    public ProfessionFriendlyCategoryMapper(ModelMapper modelMapper) {
        super(modelMapper, ProfessionFriendlyCategory.class, ProfessionFriendlyCategoryDto.class);
    }
}
