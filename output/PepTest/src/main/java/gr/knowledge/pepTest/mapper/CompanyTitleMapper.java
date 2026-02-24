package gr.knowledge.pepTest.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import gr.knowledge.pepTest.entity.CompanyTitle;
import gr.knowledge.pepTest.dto.CompanyTitleDto;

/**
 * Mapper for {@link CompanyTitle} and {@link CompanyTitleDto}.
 */
@Component
public class CompanyTitleMapper extends BaseMapper<CompanyTitle, CompanyTitleDto> {

    public CompanyTitleMapper(ModelMapper modelMapper) {
        super(modelMapper, CompanyTitle.class, CompanyTitleDto.class);
    }
}
