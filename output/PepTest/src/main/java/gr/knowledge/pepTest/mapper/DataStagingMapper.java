package gr.knowledge.pepTest.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import gr.knowledge.pepTest.entity.DataStaging;
import gr.knowledge.pepTest.dto.DataStagingDto;

/**
 * Mapper for {@link DataStaging} and {@link DataStagingDto}.
 */
@Component
public class DataStagingMapper extends BaseMapper<DataStaging, DataStagingDto> {

    public DataStagingMapper(ModelMapper modelMapper) {
        super(modelMapper, DataStaging.class, DataStagingDto.class);
    }
}
