package gr.knowledge.pepTest.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import gr.knowledge.pepTest.entity.IncomePaymentMethod;
import gr.knowledge.pepTest.dto.IncomePaymentMethodDto;

/**
 * Mapper for {@link IncomePaymentMethod} and {@link IncomePaymentMethodDto}.
 */
@Component
public class IncomePaymentMethodMapper extends BaseMapper<IncomePaymentMethod, IncomePaymentMethodDto> {

    public IncomePaymentMethodMapper(ModelMapper modelMapper) {
        super(modelMapper, IncomePaymentMethod.class, IncomePaymentMethodDto.class);
    }
}
