package gr.knowledge.pepTest.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import gr.knowledge.pepTest.entity.TemporaryCompanyi18n;
import gr.knowledge.pepTest.dto.TemporaryCompanyi18nDto;

/**
 * Mapper for {@link TemporaryCompanyi18n} and {@link TemporaryCompanyi18nDto}.
 */
@Component
public class TemporaryCompanyi18nMapper extends BaseMapper<TemporaryCompanyi18n, TemporaryCompanyi18nDto> {

    public TemporaryCompanyi18nMapper(ModelMapper modelMapper) {
        super(modelMapper, TemporaryCompanyi18n.class, TemporaryCompanyi18nDto.class);
    }
}
