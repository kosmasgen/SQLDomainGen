package gr.knowledge.pepTest.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import gr.knowledge.pepTest.entity.Syncruns;
import gr.knowledge.pepTest.dto.SyncrunsDto;

/**
 * Mapper for {@link Syncruns} and {@link SyncrunsDto}.
 */
@Component
public class SyncrunsMapper extends BaseMapper<Syncruns, SyncrunsDto> {

    public SyncrunsMapper(ModelMapper modelMapper) {
        super(modelMapper, Syncruns.class, SyncrunsDto.class);
    }
}
