package gr.knowledge.pepTest.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import gr.knowledge.pepTest.entity.UserGeodata;
import gr.knowledge.pepTest.dto.UserGeodataDto;

/**
 * Mapper for {@link UserGeodata} and {@link UserGeodataDto}.
 */
@Component
public class UserGeodataMapper extends BaseMapper<UserGeodata, UserGeodataDto> {

    public UserGeodataMapper(ModelMapper modelMapper) {
        super(modelMapper, UserGeodata.class, UserGeodataDto.class);
    }
}
