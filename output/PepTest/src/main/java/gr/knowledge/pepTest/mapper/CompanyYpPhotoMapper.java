package gr.knowledge.pepTest.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import gr.knowledge.pepTest.entity.CompanyYpPhoto;
import gr.knowledge.pepTest.dto.CompanyYpPhotoDto;

/**
 * Mapper for {@link CompanyYpPhoto} and {@link CompanyYpPhotoDto}.
 */
@Component
public class CompanyYpPhotoMapper extends BaseMapper<CompanyYpPhoto, CompanyYpPhotoDto> {

    public CompanyYpPhotoMapper(ModelMapper modelMapper) {
        super(modelMapper, CompanyYpPhoto.class, CompanyYpPhotoDto.class);
    }
}
