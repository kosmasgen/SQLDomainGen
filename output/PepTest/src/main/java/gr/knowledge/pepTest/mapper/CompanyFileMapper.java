package gr.knowledge.pepTest.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import gr.knowledge.pepTest.entity.CompanyFile;
import gr.knowledge.pepTest.dto.CompanyFileDto;

/**
 * Mapper for {@link CompanyFile} and {@link CompanyFileDto}.
 */
@Component
public class CompanyFileMapper extends BaseMapper<CompanyFile, CompanyFileDto> {

    public CompanyFileMapper(ModelMapper modelMapper) {
        super(modelMapper, CompanyFile.class, CompanyFileDto.class);
    }
}
