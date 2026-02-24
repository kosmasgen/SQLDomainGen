package gr.knowledge.pepTest.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import gr.knowledge.pepTest.entity.Country;
import gr.knowledge.pepTest.dto.CountryDto;

/**
 * Mapper for {@link Country} and {@link CountryDto}.
 */
@Component
public class CountryMapper extends BaseMapper<Country, CountryDto> {

    public CountryMapper(ModelMapper modelMapper) {
        super(modelMapper, Country.class, CountryDto.class);
    }
}
