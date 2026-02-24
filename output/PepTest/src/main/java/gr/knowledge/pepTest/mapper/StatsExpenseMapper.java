package gr.knowledge.pepTest.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import gr.knowledge.pepTest.entity.StatsExpense;
import gr.knowledge.pepTest.dto.StatsExpenseDto;

/**
 * Mapper for {@link StatsExpense} and {@link StatsExpenseDto}.
 */
@Component
public class StatsExpenseMapper extends BaseMapper<StatsExpense, StatsExpenseDto> {

    public StatsExpenseMapper(ModelMapper modelMapper) {
        super(modelMapper, StatsExpense.class, StatsExpenseDto.class);
    }
}
