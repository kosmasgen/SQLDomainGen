package gr.knowledge.pepTest.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import gr.knowledge.pepTest.entity.CompanyProfession;
import gr.knowledge.pepTest.dto.CompanyProfessionDto;

/**
 * Mapper for {@link CompanyProfession} and {@link CompanyProfessionDto}.
 */
@Component
public class CompanyProfessionMapper extends BaseMapper<CompanyProfession, CompanyProfessionDto> {

    public CompanyProfessionMapper(ModelMapper modelMapper) {
        super(modelMapper, CompanyProfession.class, CompanyProfessionDto.class);
    }
}
