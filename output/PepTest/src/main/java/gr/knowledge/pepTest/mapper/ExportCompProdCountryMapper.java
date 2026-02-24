package gr.knowledge.pepTest.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import gr.knowledge.pepTest.entity.ExportCompProdCountry;
import gr.knowledge.pepTest.dto.ExportCompProdCountryDto;

/**
 * Mapper for {@link ExportCompProdCountry} and {@link ExportCompProdCountryDto}.
 */
@Component
public class ExportCompProdCountryMapper extends BaseMapper<ExportCompProdCountry, ExportCompProdCountryDto> {

    public ExportCompProdCountryMapper(ModelMapper modelMapper) {
        super(modelMapper, ExportCompProdCountry.class, ExportCompProdCountryDto.class);
    }
}
