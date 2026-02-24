package gr.knowledge.pepTest.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import gr.knowledge.pepTest.entity.CompanyBgCooperation;
import gr.knowledge.pepTest.dto.CompanyBgCooperationDto;

/**
 * Mapper for {@link CompanyBgCooperation} and {@link CompanyBgCooperationDto}.
 */
@Component
public class CompanyBgCooperationMapper extends BaseMapper<CompanyBgCooperation, CompanyBgCooperationDto> {

    public CompanyBgCooperationMapper(ModelMapper modelMapper) {
        super(modelMapper, CompanyBgCooperation.class, CompanyBgCooperationDto.class);
    }
}
