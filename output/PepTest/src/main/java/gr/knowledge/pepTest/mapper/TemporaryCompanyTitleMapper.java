package gr.knowledge.pepTest.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import gr.knowledge.pepTest.entity.TemporaryCompanyTitle;
import gr.knowledge.pepTest.dto.TemporaryCompanyTitleDto;

/**
 * Mapper for {@link TemporaryCompanyTitle} and {@link TemporaryCompanyTitleDto}.
 */
@Component
public class TemporaryCompanyTitleMapper extends BaseMapper<TemporaryCompanyTitle, TemporaryCompanyTitleDto> {

    public TemporaryCompanyTitleMapper(ModelMapper modelMapper) {
        super(modelMapper, TemporaryCompanyTitle.class, TemporaryCompanyTitleDto.class);
    }
}
