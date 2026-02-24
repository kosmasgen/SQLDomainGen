package gr.knowledge.pepTest.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import gr.knowledge.pepTest.entity.CountryI18n;
import gr.knowledge.pepTest.dto.CountryI18nDto;

/**
 * Mapper for {@link CountryI18n} and {@link CountryI18nDto}.
 */
@Component
public class CountryI18nMapper extends BaseMapper<CountryI18n, CountryI18nDto> {

    public CountryI18nMapper(ModelMapper modelMapper) {
        super(modelMapper, CountryI18n.class, CountryI18nDto.class);
    }
}
