package gr.knowledge.pepTest.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import gr.knowledge.pepTest.entity.ChAppUserContact;
import gr.knowledge.pepTest.dto.ChAppUserContactDto;

/**
 * Mapper for {@link ChAppUserContact} and {@link ChAppUserContactDto}.
 */
@Component
public class ChAppUserContactMapper extends BaseMapper<ChAppUserContact, ChAppUserContactDto> {

    public ChAppUserContactMapper(ModelMapper modelMapper) {
        super(modelMapper, ChAppUserContact.class, ChAppUserContactDto.class);
    }
}
