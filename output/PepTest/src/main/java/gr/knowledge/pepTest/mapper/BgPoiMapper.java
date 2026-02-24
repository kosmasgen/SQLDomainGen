package gr.knowledge.pepTest.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import gr.knowledge.pepTest.entity.BgPoi;
import gr.knowledge.pepTest.dto.BgPoiDto;

/**
 * Mapper for {@link BgPoi} and {@link BgPoiDto}.
 */
@Component
public class BgPoiMapper extends BaseMapper<BgPoi, BgPoiDto> {

    public BgPoiMapper(ModelMapper modelMapper) {
        super(modelMapper, BgPoi.class, BgPoiDto.class);
    }
}
