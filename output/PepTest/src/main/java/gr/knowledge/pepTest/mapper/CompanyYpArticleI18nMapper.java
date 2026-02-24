package gr.knowledge.pepTest.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import gr.knowledge.pepTest.entity.CompanyYpArticleI18n;
import gr.knowledge.pepTest.dto.CompanyYpArticleI18nDto;

/**
 * Mapper for {@link CompanyYpArticleI18n} and {@link CompanyYpArticleI18nDto}.
 */
@Component
public class CompanyYpArticleI18nMapper extends BaseMapper<CompanyYpArticleI18n, CompanyYpArticleI18nDto> {

    public CompanyYpArticleI18nMapper(ModelMapper modelMapper) {
        super(modelMapper, CompanyYpArticleI18n.class, CompanyYpArticleI18nDto.class);
    }
}
