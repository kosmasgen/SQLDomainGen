package gr.knowledge.pepTest.serviceImpl;

import gr.knowledge.pepTest.dto.FolderDto;
import gr.knowledge.pepTest.mapper.FolderMapper;
import gr.knowledge.pepTest.entity.Folder;
import gr.knowledge.pepTest.repository.FolderRepository;
import gr.knowledge.pepTest.service.FolderService;
import gr.knowledge.pepTest.exception.ErrorCodes;
import gr.knowledge.pepTest.exception.GeneratedRuntimeException;
import java.util.UUID;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * Service implementation for {@code Folder} domain operations.
 */
@Service
@RequiredArgsConstructor
@Transactional
@Log4j2
public class FolderServiceImpl implements FolderService {

    private final FolderRepository folderRepository;
    private final FolderMapper folderMapper;

    /**
     * Retrieves all folders records.
     * @return list of FolderDto
     */
    @Override
    public List<FolderDto> getAllFolders() {
        log.info("Fetching all folders records.");
        return folderMapper.toDTOList(folderRepository.findAll());
    }

    /**
     * Retrieves a folder record by id.
     * @param id the folder id
     * @return FolderDto
     */
    @Override
    public FolderDto getFolderById(UUID id) {
        log.info("Fetching folder with id: {}", id);

        Folder existingEntity = findFolderByIdOrThrow(id);
        return folderMapper.toDTO(existingEntity);
    }

    /**
     * Creates a new folder record.
     * @param dto input payload
     * @return created {@link FolderDto}
     */
    @Override
    public FolderDto createFolder(FolderDto dto) {
        log.info("Creating folder.");

        Folder entity = folderMapper.toEntity(dto);
        Folder savedEntity = folderRepository.save(entity);

        return folderMapper.toDTO(savedEntity);
    }

    /**
     * Updates an existing folder record.
     *
     * @param id the folder id
     * @param dto input payload
     * @return updated {@link FolderDto}
     */
    @Override
    public FolderDto updateFolder(UUID id, FolderDto dto) {
        log.info("Updating folder with id: {}", id);

        Folder existingEntity = findFolderByIdOrThrow(id);
        folderMapper.partialUpdate(existingEntity, dto);
        Folder savedEntity = folderRepository.save(existingEntity);

        return folderMapper.toDTO(savedEntity);
    }

    /**
     * Delete a folder record by id.
     * @param id the folder id
     */
    @Override
    public void deleteFolder(UUID id) {
        log.info("Deleting folder with id: {}", id);

        findFolderByIdOrThrow(id);
        folderRepository.deleteById(id);
    }

    /**
     * Finds an existing folder record by id or throws an exception.
     * @param id the folder id
     * @return existing Folder entity
     */
    private Folder findFolderByIdOrThrow(UUID id) {
        return folderRepository.findById(id)
                .orElseThrow(() -> createFolderNotFoundException(id));
    }

    /**
     Creates a NOT FOUND exception for the folder entity.
     @param id the folder id
     @return runtime exception
     */
    private RuntimeException createFolderNotFoundException(UUID id) {
        log.warn("Folder not found with id: {}", id);

        return GeneratedRuntimeException.builder()
                .code(ErrorCodes.NOT_FOUND)
                .entity("Folder")
                .message("Folder not found with id: " + id)
                .build();
    }

}
