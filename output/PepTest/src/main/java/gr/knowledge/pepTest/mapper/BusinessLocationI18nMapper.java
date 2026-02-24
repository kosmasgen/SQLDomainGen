package gr.knowledge.pepTest.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import gr.knowledge.pepTest.entity.BusinessLocationI18n;
import gr.knowledge.pepTest.dto.BusinessLocationI18nDto;

/**
 * Mapper for {@link BusinessLocationI18n} and {@link BusinessLocationI18nDto}.
 */
@Component
public class BusinessLocationI18nMapper extends BaseMapper<BusinessLocationI18n, BusinessLocationI18nDto> {

    public BusinessLocationI18nMapper(ModelMapper modelMapper) {
        super(modelMapper, BusinessLocationI18n.class, BusinessLocationI18nDto.class);
    }
}
