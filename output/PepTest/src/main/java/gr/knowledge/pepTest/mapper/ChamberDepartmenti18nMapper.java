package gr.knowledge.pepTest.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import gr.knowledge.pepTest.entity.ChamberDepartmenti18n;
import gr.knowledge.pepTest.dto.ChamberDepartmenti18nDto;

/**
 * Mapper for {@link ChamberDepartmenti18n} and {@link ChamberDepartmenti18nDto}.
 */
@Component
public class ChamberDepartmenti18nMapper extends BaseMapper<ChamberDepartmenti18n, ChamberDepartmenti18nDto> {

    public ChamberDepartmenti18nMapper(ModelMapper modelMapper) {
        super(modelMapper, ChamberDepartmenti18n.class, ChamberDepartmenti18nDto.class);
    }
}
