package gr.knowledge.pepTest.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import gr.knowledge.pepTest.entity.CompanyContactMessage;
import gr.knowledge.pepTest.dto.CompanyContactMessageDto;

/**
 * Mapper for {@link CompanyContactMessage} and {@link CompanyContactMessageDto}.
 */
@Component
public class CompanyContactMessageMapper extends BaseMapper<CompanyContactMessage, CompanyContactMessageDto> {

    public CompanyContactMessageMapper(ModelMapper modelMapper) {
        super(modelMapper, CompanyContactMessage.class, CompanyContactMessageDto.class);
    }
}
