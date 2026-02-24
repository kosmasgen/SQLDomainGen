package gr.knowledge.pepTest.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import gr.knowledge.pepTest.entity.CompanyStatus;
import gr.knowledge.pepTest.dto.CompanyStatusDto;

/**
 * Mapper for {@link CompanyStatus} and {@link CompanyStatusDto}.
 */
@Component
public class CompanyStatusMapper extends BaseMapper<CompanyStatus, CompanyStatusDto> {

    public CompanyStatusMapper(ModelMapper modelMapper) {
        super(modelMapper, CompanyStatus.class, CompanyStatusDto.class);
    }
}
