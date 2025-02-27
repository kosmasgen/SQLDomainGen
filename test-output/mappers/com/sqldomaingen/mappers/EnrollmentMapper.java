package com.sqldomaingen.mappers;

import com.sqldomaingen.dto.EnrollmentDTO;
import com.sqldomaingen.entities.Enrollment;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import java.util.List;

@Mapper
public interface EnrollmentMapper {

    EnrollmentMapper INSTANCE = Mappers.getMapper(EnrollmentMapper.class);

    EnrollmentDTO toDTO(Enrollment entity);

    Enrollment toEntity(EnrollmentDTO dto);

    List<EnrollmentDTO> toDTOList(List<Enrollment> entities);

    List<Enrollment> toEntityList(List<EnrollmentDTO> dtos);
}
