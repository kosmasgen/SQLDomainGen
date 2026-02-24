package gr.knowledge.pepTest.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import gr.knowledge.pepTest.entity.CompanyBgCooperationI18n;
import gr.knowledge.pepTest.dto.CompanyBgCooperationI18nDto;

/**
 * Mapper for {@link CompanyBgCooperationI18n} and {@link CompanyBgCooperationI18nDto}.
 */
@Component
public class CompanyBgCooperationI18nMapper extends BaseMapper<CompanyBgCooperationI18n, CompanyBgCooperationI18nDto> {

    public CompanyBgCooperationI18nMapper(ModelMapper modelMapper) {
        super(modelMapper, CompanyBgCooperationI18n.class, CompanyBgCooperationI18nDto.class);
    }
}
