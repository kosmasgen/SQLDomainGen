package gr.knowledge.pepTest.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import gr.knowledge.pepTest.entity.CompanyFavourites;
import gr.knowledge.pepTest.dto.CompanyFavouritesDto;

/**
 * Mapper for {@link CompanyFavourites} and {@link CompanyFavouritesDto}.
 */
@Component
public class CompanyFavouritesMapper extends BaseMapper<CompanyFavourites, CompanyFavouritesDto> {

    public CompanyFavouritesMapper(ModelMapper modelMapper) {
        super(modelMapper, CompanyFavourites.class, CompanyFavouritesDto.class);
    }
}
