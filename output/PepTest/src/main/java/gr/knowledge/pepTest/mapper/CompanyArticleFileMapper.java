package gr.knowledge.pepTest.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import gr.knowledge.pepTest.entity.CompanyArticleFile;
import gr.knowledge.pepTest.dto.CompanyArticleFileDto;

/**
 * Mapper for {@link CompanyArticleFile} and {@link CompanyArticleFileDto}.
 */
@Component
public class CompanyArticleFileMapper extends BaseMapper<CompanyArticleFile, CompanyArticleFileDto> {

    public CompanyArticleFileMapper(ModelMapper modelMapper) {
        super(modelMapper, CompanyArticleFile.class, CompanyArticleFileDto.class);
    }
}
