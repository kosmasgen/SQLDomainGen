package gr.knowledge.pepTest.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import gr.knowledge.pepTest.entity.Professioni18n;
import gr.knowledge.pepTest.dto.Professioni18nDto;

/**
 * Mapper for {@link Professioni18n} and {@link Professioni18nDto}.
 */
@Component
public class Professioni18nMapper extends BaseMapper<Professioni18n, Professioni18nDto> {

    public Professioni18nMapper(ModelMapper modelMapper) {
        super(modelMapper, Professioni18n.class, Professioni18nDto.class);
    }
}
