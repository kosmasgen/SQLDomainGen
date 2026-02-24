package gr.knowledge.pepTest.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import gr.knowledge.pepTest.entity.IncomeGemiPayment;
import gr.knowledge.pepTest.dto.IncomeGemiPaymentDto;

/**
 * Mapper for {@link IncomeGemiPayment} and {@link IncomeGemiPaymentDto}.
 */
@Component
public class IncomeGemiPaymentMapper extends BaseMapper<IncomeGemiPayment, IncomeGemiPaymentDto> {

    public IncomeGemiPaymentMapper(ModelMapper modelMapper) {
        super(modelMapper, IncomeGemiPayment.class, IncomeGemiPaymentDto.class);
    }
}
