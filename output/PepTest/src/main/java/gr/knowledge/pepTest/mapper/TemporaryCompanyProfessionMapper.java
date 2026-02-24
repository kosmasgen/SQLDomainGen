package gr.knowledge.pepTest.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import gr.knowledge.pepTest.entity.TemporaryCompanyProfession;
import gr.knowledge.pepTest.dto.TemporaryCompanyProfessionDto;

/**
 * Mapper for {@link TemporaryCompanyProfession} and {@link TemporaryCompanyProfessionDto}.
 */
@Component
public class TemporaryCompanyProfessionMapper extends BaseMapper<TemporaryCompanyProfession, TemporaryCompanyProfessionDto> {

    public TemporaryCompanyProfessionMapper(ModelMapper modelMapper) {
        super(modelMapper, TemporaryCompanyProfession.class, TemporaryCompanyProfessionDto.class);
    }
}
