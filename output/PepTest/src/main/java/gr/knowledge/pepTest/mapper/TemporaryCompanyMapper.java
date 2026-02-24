package gr.knowledge.pepTest.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import gr.knowledge.pepTest.entity.TemporaryCompany;
import gr.knowledge.pepTest.dto.TemporaryCompanyDto;

/**
 * Mapper for {@link TemporaryCompany} and {@link TemporaryCompanyDto}.
 */
@Component
public class TemporaryCompanyMapper extends BaseMapper<TemporaryCompany, TemporaryCompanyDto> {

    public TemporaryCompanyMapper(ModelMapper modelMapper) {
        super(modelMapper, TemporaryCompany.class, TemporaryCompanyDto.class);
    }
}
