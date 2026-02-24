package gr.knowledge.pepTest.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import gr.knowledge.pepTest.entity.TemporaryCompanyTitlei18n;
import gr.knowledge.pepTest.dto.TemporaryCompanyTitlei18nDto;

/**
 * Mapper for {@link TemporaryCompanyTitlei18n} and {@link TemporaryCompanyTitlei18nDto}.
 */
@Component
public class TemporaryCompanyTitlei18nMapper extends BaseMapper<TemporaryCompanyTitlei18n, TemporaryCompanyTitlei18nDto> {

    public TemporaryCompanyTitlei18nMapper(ModelMapper modelMapper) {
        super(modelMapper, TemporaryCompanyTitlei18n.class, TemporaryCompanyTitlei18nDto.class);
    }
}
