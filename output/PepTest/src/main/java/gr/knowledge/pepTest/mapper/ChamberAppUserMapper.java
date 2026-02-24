package gr.knowledge.pepTest.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import gr.knowledge.pepTest.entity.ChamberAppUser;
import gr.knowledge.pepTest.dto.ChamberAppUserDto;

/**
 * Mapper for {@link ChamberAppUser} and {@link ChamberAppUserDto}.
 */
@Component
public class ChamberAppUserMapper extends BaseMapper<ChamberAppUser, ChamberAppUserDto> {

    public ChamberAppUserMapper(ModelMapper modelMapper) {
        super(modelMapper, ChamberAppUser.class, ChamberAppUserDto.class);
    }
}
