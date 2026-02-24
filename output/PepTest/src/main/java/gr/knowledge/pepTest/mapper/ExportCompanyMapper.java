package gr.knowledge.pepTest.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import gr.knowledge.pepTest.entity.ExportCompany;
import gr.knowledge.pepTest.dto.ExportCompanyDto;

/**
 * Mapper for {@link ExportCompany} and {@link ExportCompanyDto}.
 */
@Component
public class ExportCompanyMapper extends BaseMapper<ExportCompany, ExportCompanyDto> {

    public ExportCompanyMapper(ModelMapper modelMapper) {
        super(modelMapper, ExportCompany.class, ExportCompanyDto.class);
    }
}
