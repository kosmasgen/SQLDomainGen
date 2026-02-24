package gr.knowledge.pepTest.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import gr.knowledge.pepTest.entity.CompanyProfile;
import gr.knowledge.pepTest.dto.CompanyProfileDto;

/**
 * Mapper for {@link CompanyProfile} and {@link CompanyProfileDto}.
 */
@Component
public class CompanyProfileMapper extends BaseMapper<CompanyProfile, CompanyProfileDto> {

    public CompanyProfileMapper(ModelMapper modelMapper) {
        super(modelMapper, CompanyProfile.class, CompanyProfileDto.class);
    }
}
