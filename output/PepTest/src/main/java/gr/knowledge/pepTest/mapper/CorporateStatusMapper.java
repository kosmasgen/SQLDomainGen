package gr.knowledge.pepTest.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import gr.knowledge.pepTest.entity.CorporateStatus;
import gr.knowledge.pepTest.dto.CorporateStatusDto;

/**
 * Mapper for {@link CorporateStatus} and {@link CorporateStatusDto}.
 */
@Component
public class CorporateStatusMapper extends BaseMapper<CorporateStatus, CorporateStatusDto> {

    public CorporateStatusMapper(ModelMapper modelMapper) {
        super(modelMapper, CorporateStatus.class, CorporateStatusDto.class);
    }
}
