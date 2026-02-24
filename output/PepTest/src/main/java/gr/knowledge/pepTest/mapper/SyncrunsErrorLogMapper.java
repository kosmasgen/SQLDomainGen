package gr.knowledge.pepTest.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import gr.knowledge.pepTest.entity.SyncrunsErrorLog;
import gr.knowledge.pepTest.dto.SyncrunsErrorLogDto;

/**
 * Mapper for {@link SyncrunsErrorLog} and {@link SyncrunsErrorLogDto}.
 */
@Component
public class SyncrunsErrorLogMapper extends BaseMapper<SyncrunsErrorLog, SyncrunsErrorLogDto> {

    public SyncrunsErrorLogMapper(ModelMapper modelMapper) {
        super(modelMapper, SyncrunsErrorLog.class, SyncrunsErrorLogDto.class);
    }
}
