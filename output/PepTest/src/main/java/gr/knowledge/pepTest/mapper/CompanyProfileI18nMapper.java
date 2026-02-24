package gr.knowledge.pepTest.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import gr.knowledge.pepTest.entity.CompanyProfileI18n;
import gr.knowledge.pepTest.dto.CompanyProfileI18nDto;

/**
 * Mapper for {@link CompanyProfileI18n} and {@link CompanyProfileI18nDto}.
 */
@Component
public class CompanyProfileI18nMapper extends BaseMapper<CompanyProfileI18n, CompanyProfileI18nDto> {

    public CompanyProfileI18nMapper(ModelMapper modelMapper) {
        super(modelMapper, CompanyProfileI18n.class, CompanyProfileI18nDto.class);
    }
}
