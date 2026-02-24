package gr.knowledge.pepTest.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import gr.knowledge.pepTest.entity.IncomeType;
import gr.knowledge.pepTest.dto.IncomeTypeDto;

/**
 * Mapper for {@link IncomeType} and {@link IncomeTypeDto}.
 */
@Component
public class IncomeTypeMapper extends BaseMapper<IncomeType, IncomeTypeDto> {

    public IncomeTypeMapper(ModelMapper modelMapper) {
        super(modelMapper, IncomeType.class, IncomeTypeDto.class);
    }
}
