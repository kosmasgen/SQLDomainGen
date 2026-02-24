package gr.knowledge.pepTest.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import gr.knowledge.pepTest.entity.Producti18n;
import gr.knowledge.pepTest.dto.Producti18nDto;

/**
 * Mapper for {@link Producti18n} and {@link Producti18nDto}.
 */
@Component
public class Producti18nMapper extends BaseMapper<Producti18n, Producti18nDto> {

    public Producti18nMapper(ModelMapper modelMapper) {
        super(modelMapper, Producti18n.class, Producti18nDto.class);
    }
}
