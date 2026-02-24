package gr.knowledge.pepTest.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import gr.knowledge.pepTest.entity.ChamberDepartment;
import gr.knowledge.pepTest.dto.ChamberDepartmentDto;

/**
 * Mapper for {@link ChamberDepartment} and {@link ChamberDepartmentDto}.
 */
@Component
public class ChamberDepartmentMapper extends BaseMapper<ChamberDepartment, ChamberDepartmentDto> {

    public ChamberDepartmentMapper(ModelMapper modelMapper) {
        super(modelMapper, ChamberDepartment.class, ChamberDepartmentDto.class);
    }
}
