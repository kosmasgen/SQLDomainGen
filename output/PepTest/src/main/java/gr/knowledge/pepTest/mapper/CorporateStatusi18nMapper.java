package gr.knowledge.pepTest.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import gr.knowledge.pepTest.entity.CorporateStatusi18n;
import gr.knowledge.pepTest.dto.CorporateStatusi18nDto;

/**
 * Mapper for {@link CorporateStatusi18n} and {@link CorporateStatusi18nDto}.
 */
@Component
public class CorporateStatusi18nMapper extends BaseMapper<CorporateStatusi18n, CorporateStatusi18nDto> {

    public CorporateStatusi18nMapper(ModelMapper modelMapper) {
        super(modelMapper, CorporateStatusi18n.class, CorporateStatusi18nDto.class);
    }
}
