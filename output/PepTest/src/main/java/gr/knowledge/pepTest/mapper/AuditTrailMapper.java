package gr.knowledge.pepTest.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import gr.knowledge.pepTest.entity.AuditTrail;
import gr.knowledge.pepTest.dto.AuditTrailDto;

/**
 * Mapper for {@link AuditTrail} and {@link AuditTrailDto}.
 */
@Component
public class AuditTrailMapper extends BaseMapper<AuditTrail, AuditTrailDto> {

    public AuditTrailMapper(ModelMapper modelMapper) {
        super(modelMapper, AuditTrail.class, AuditTrailDto.class);
    }
}
