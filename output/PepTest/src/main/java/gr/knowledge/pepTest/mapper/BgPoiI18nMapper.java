package gr.knowledge.pepTest.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import gr.knowledge.pepTest.entity.BgPoiI18n;
import gr.knowledge.pepTest.dto.BgPoiI18nDto;

/**
 * Mapper for {@link BgPoiI18n} and {@link BgPoiI18nDto}.
 */
@Component
public class BgPoiI18nMapper extends BaseMapper<BgPoiI18n, BgPoiI18nDto> {

    public BgPoiI18nMapper(ModelMapper modelMapper) {
        super(modelMapper, BgPoiI18n.class, BgPoiI18nDto.class);
    }
}
