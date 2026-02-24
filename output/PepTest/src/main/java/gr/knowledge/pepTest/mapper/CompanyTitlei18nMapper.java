package gr.knowledge.pepTest.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import gr.knowledge.pepTest.entity.CompanyTitlei18n;
import gr.knowledge.pepTest.dto.CompanyTitlei18nDto;

/**
 * Mapper for {@link CompanyTitlei18n} and {@link CompanyTitlei18nDto}.
 */
@Component
public class CompanyTitlei18nMapper extends BaseMapper<CompanyTitlei18n, CompanyTitlei18nDto> {

    public CompanyTitlei18nMapper(ModelMapper modelMapper) {
        super(modelMapper, CompanyTitlei18n.class, CompanyTitlei18nDto.class);
    }
}
