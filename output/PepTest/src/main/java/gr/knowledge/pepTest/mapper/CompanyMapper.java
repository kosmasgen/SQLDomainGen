package gr.knowledge.pepTest.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import gr.knowledge.pepTest.entity.Company;
import gr.knowledge.pepTest.dto.CompanyDto;

/**
 * Mapper for {@link Company} and {@link CompanyDto}.
 */
@Component
public class CompanyMapper extends BaseMapper<Company, CompanyDto> {

    public CompanyMapper(ModelMapper modelMapper) {
        super(modelMapper, Company.class, CompanyDto.class);
    }
}
