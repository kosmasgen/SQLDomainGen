package gr.knowledge.pepTest.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import gr.knowledge.pepTest.entity.UserContactinfo;
import gr.knowledge.pepTest.dto.UserContactinfoDto;

/**
 * Mapper for {@link UserContactinfo} and {@link UserContactinfoDto}.
 */
@Component
public class UserContactinfoMapper extends BaseMapper<UserContactinfo, UserContactinfoDto> {

    public UserContactinfoMapper(ModelMapper modelMapper) {
        super(modelMapper, UserContactinfo.class, UserContactinfoDto.class);
    }
}
