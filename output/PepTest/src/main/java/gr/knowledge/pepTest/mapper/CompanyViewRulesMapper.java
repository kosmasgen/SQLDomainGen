package gr.knowledge.pepTest.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import gr.knowledge.pepTest.entity.CompanyViewRules;
import gr.knowledge.pepTest.dto.CompanyViewRulesDto;

/**
 * Mapper for {@link CompanyViewRules} and {@link CompanyViewRulesDto}.
 */
@Component
public class CompanyViewRulesMapper extends BaseMapper<CompanyViewRules, CompanyViewRulesDto> {

    public CompanyViewRulesMapper(ModelMapper modelMapper) {
        super(modelMapper, CompanyViewRules.class, CompanyViewRulesDto.class);
    }
}
