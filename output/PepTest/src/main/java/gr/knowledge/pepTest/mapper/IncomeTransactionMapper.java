package gr.knowledge.pepTest.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import gr.knowledge.pepTest.entity.IncomeTransaction;
import gr.knowledge.pepTest.dto.IncomeTransactionDto;

/**
 * Mapper for {@link IncomeTransaction} and {@link IncomeTransactionDto}.
 */
@Component
public class IncomeTransactionMapper extends BaseMapper<IncomeTransaction, IncomeTransactionDto> {

    public IncomeTransactionMapper(ModelMapper modelMapper) {
        super(modelMapper, IncomeTransaction.class, IncomeTransactionDto.class);
    }
}
