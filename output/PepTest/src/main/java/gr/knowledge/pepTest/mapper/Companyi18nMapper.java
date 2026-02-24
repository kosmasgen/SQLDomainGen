package gr.knowledge.pepTest.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import gr.knowledge.pepTest.entity.Companyi18n;
import gr.knowledge.pepTest.dto.Companyi18nDto;

/**
 * Mapper for {@link Companyi18n} and {@link Companyi18nDto}.
 */
@Component
public class Companyi18nMapper extends BaseMapper<Companyi18n, Companyi18nDto> {

    public Companyi18nMapper(ModelMapper modelMapper) {
        super(modelMapper, Companyi18n.class, Companyi18nDto.class);
    }
}
