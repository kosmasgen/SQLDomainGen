package gr.knowledge.pepTest.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import gr.knowledge.pepTest.entity.CompanyYpFile;
import gr.knowledge.pepTest.dto.CompanyYpFileDto;

/**
 * Mapper for {@link CompanyYpFile} and {@link CompanyYpFileDto}.
 */
@Component
public class CompanyYpFileMapper extends BaseMapper<CompanyYpFile, CompanyYpFileDto> {

    public CompanyYpFileMapper(ModelMapper modelMapper) {
        super(modelMapper, CompanyYpFile.class, CompanyYpFileDto.class);
    }
}
