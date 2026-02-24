package gr.knowledge.pepTest.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import gr.knowledge.pepTest.entity.MunicipalityI18n;
import gr.knowledge.pepTest.dto.MunicipalityI18nDto;

/**
 * Mapper for {@link MunicipalityI18n} and {@link MunicipalityI18nDto}.
 */
@Component
public class MunicipalityI18nMapper extends BaseMapper<MunicipalityI18n, MunicipalityI18nDto> {

    public MunicipalityI18nMapper(ModelMapper modelMapper) {
        super(modelMapper, MunicipalityI18n.class, MunicipalityI18nDto.class);
    }
}
