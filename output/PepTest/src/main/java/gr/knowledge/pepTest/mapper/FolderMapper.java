package gr.knowledge.pepTest.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import gr.knowledge.pepTest.entity.Folder;
import gr.knowledge.pepTest.dto.FolderDto;

/**
 * Mapper for {@link Folder} and {@link FolderDto}.
 */
@Component
public class FolderMapper extends BaseMapper<Folder, FolderDto> {

    public FolderMapper(ModelMapper modelMapper) {
        super(modelMapper, Folder.class, FolderDto.class);
    }
}
