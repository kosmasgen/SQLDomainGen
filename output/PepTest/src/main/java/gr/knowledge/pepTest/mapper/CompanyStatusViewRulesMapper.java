package gr.knowledge.pepTest.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import gr.knowledge.pepTest.entity.CompanyStatusViewRules;
import gr.knowledge.pepTest.dto.CompanyStatusViewRulesDto;

/**
 * Mapper for {@link CompanyStatusViewRules} and {@link CompanyStatusViewRulesDto}.
 */
@Component
public class CompanyStatusViewRulesMapper extends BaseMapper<CompanyStatusViewRules, CompanyStatusViewRulesDto> {

    public CompanyStatusViewRulesMapper(ModelMapper modelMapper) {
        super(modelMapper, CompanyStatusViewRules.class, CompanyStatusViewRulesDto.class);
    }
}
