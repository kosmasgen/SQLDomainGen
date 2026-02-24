package gr.knowledge.pepTest.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import gr.knowledge.pepTest.entity.ChAppUserContactI18n;
import gr.knowledge.pepTest.dto.ChAppUserContactI18nDto;

/**
 * Mapper for {@link ChAppUserContactI18n} and {@link ChAppUserContactI18nDto}.
 */
@Component
public class ChAppUserContactI18nMapper extends BaseMapper<ChAppUserContactI18n, ChAppUserContactI18nDto> {

    public ChAppUserContactI18nMapper(ModelMapper modelMapper) {
        super(modelMapper, ChAppUserContactI18n.class, ChAppUserContactI18nDto.class);
    }
}
