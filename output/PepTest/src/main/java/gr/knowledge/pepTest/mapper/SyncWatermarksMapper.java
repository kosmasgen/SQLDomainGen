package gr.knowledge.pepTest.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import gr.knowledge.pepTest.entity.SyncWatermarks;
import gr.knowledge.pepTest.dto.SyncWatermarksDto;

/**
 * Mapper for {@link SyncWatermarks} and {@link SyncWatermarksDto}.
 */
@Component
public class SyncWatermarksMapper extends BaseMapper<SyncWatermarks, SyncWatermarksDto> {

    public SyncWatermarksMapper(ModelMapper modelMapper) {
        super(modelMapper, SyncWatermarks.class, SyncWatermarksDto.class);
    }
}
