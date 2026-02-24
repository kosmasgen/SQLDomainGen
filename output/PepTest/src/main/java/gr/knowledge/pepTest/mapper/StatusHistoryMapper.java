package gr.knowledge.pepTest.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import gr.knowledge.pepTest.entity.StatusHistory;
import gr.knowledge.pepTest.dto.StatusHistoryDto;

/**
 * Mapper for {@link StatusHistory} and {@link StatusHistoryDto}.
 */
@Component
public class StatusHistoryMapper extends BaseMapper<StatusHistory, StatusHistoryDto> {

    public StatusHistoryMapper(ModelMapper modelMapper) {
        super(modelMapper, StatusHistory.class, StatusHistoryDto.class);
    }
}
