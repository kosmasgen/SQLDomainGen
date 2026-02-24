package gr.knowledge.pepTest.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import gr.knowledge.pepTest.entity.CompanyYpArticle;
import gr.knowledge.pepTest.dto.CompanyYpArticleDto;

/**
 * Mapper for {@link CompanyYpArticle} and {@link CompanyYpArticleDto}.
 */
@Component
public class CompanyYpArticleMapper extends BaseMapper<CompanyYpArticle, CompanyYpArticleDto> {

    public CompanyYpArticleMapper(ModelMapper modelMapper) {
        super(modelMapper, CompanyYpArticle.class, CompanyYpArticleDto.class);
    }
}
