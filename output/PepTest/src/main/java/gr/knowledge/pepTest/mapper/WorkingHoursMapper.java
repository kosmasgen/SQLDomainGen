package gr.knowledge.pepTest.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import gr.knowledge.pepTest.entity.WorkingHours;
import gr.knowledge.pepTest.dto.WorkingHoursDto;

/**
 * Mapper for {@link WorkingHours} and {@link WorkingHoursDto}.
 */
@Component
public class WorkingHoursMapper extends BaseMapper<WorkingHours, WorkingHoursDto> {

    public WorkingHoursMapper(ModelMapper modelMapper) {
        super(modelMapper, WorkingHours.class, WorkingHoursDto.class);
    }
}
