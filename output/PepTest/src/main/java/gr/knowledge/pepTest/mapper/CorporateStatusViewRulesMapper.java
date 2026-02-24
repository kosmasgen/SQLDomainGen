package gr.knowledge.pepTest.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import gr.knowledge.pepTest.entity.CorporateStatusViewRules;
import gr.knowledge.pepTest.dto.CorporateStatusViewRulesDto;

/**
 * Mapper for {@link CorporateStatusViewRules} and {@link CorporateStatusViewRulesDto}.
 */
@Component
public class CorporateStatusViewRulesMapper extends BaseMapper<CorporateStatusViewRules, CorporateStatusViewRulesDto> {

    public CorporateStatusViewRulesMapper(ModelMapper modelMapper) {
        super(modelMapper, CorporateStatusViewRules.class, CorporateStatusViewRulesDto.class);
    }
}
