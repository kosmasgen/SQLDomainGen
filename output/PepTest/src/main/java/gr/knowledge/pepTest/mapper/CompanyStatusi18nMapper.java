package gr.knowledge.pepTest.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import gr.knowledge.pepTest.entity.CompanyStatusi18n;
import gr.knowledge.pepTest.dto.CompanyStatusi18nDto;

/**
 * Mapper for {@link CompanyStatusi18n} and {@link CompanyStatusi18nDto}.
 */
@Component
public class CompanyStatusi18nMapper extends BaseMapper<CompanyStatusi18n, CompanyStatusi18nDto> {

    public CompanyStatusi18nMapper(ModelMapper modelMapper) {
        super(modelMapper, CompanyStatusi18n.class, CompanyStatusi18nDto.class);
    }
}
