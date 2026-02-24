package gr.knowledge.pepTest.serviceImpl;

import gr.knowledge.pepTest.dto.FolderDto;
import gr.knowledge.pepTest.mapper.FolderMapper;
import gr.knowledge.pepTest.entity.Folder;
import gr.knowledge.pepTest.repository.FolderRepository;
import gr.knowledge.pepTest.service.FolderService;
import java.util.UUID;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

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
     * Retrieves all records.
     *
     * @return non-null list of {@link FolderDto}
     */
    @Override
    public List<FolderDto> getAllFolder() {
        log.info("Fetching all folder.");
        return folderMapper.toDTO(folderRepository.findAll());
    }

    /**
     * Retrieves a record by id.
     *
     * @param id the record id
     * @return the matching {@link FolderDto}
     */
    @Override
    public FolderDto getFolderById(UUID id) {
        log.info("Fetching folder with id: {}", id);
        Folder entity = folderRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Folder not found with id: " + id));
        return folderMapper.toDTO(entity);
    }

    /**
     * Creates a new record.
     *
     * @param dto input payload
     * @return created {@link FolderDto}
     */
    @Override
    public FolderDto createFolder(FolderDto dto) {
        log.info("Creating folder.");
        Folder entity = folderMapper.toEntity(dto);
        Folder saved = folderRepository.save(entity);
        return folderMapper.toDTO(saved);
    }

    /**
     * Updates an existing record.
     *
     * Note: current implementation performs a full update (PUT-style).
     * PATCH behavior (merge non-null fields) can be added via ModelMapper config.
     *
     * @param id  the record id
     * @param dto input payload
     * @return updated {@link FolderDto}
     */
    @Override
    public FolderDto updateFolder(UUID id, FolderDto dto) {
        log.info("Updating folder with id: {}", id);
        folderRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Folder not found with id: " + id));
        Folder entity = folderMapper.toEntity(dto);
        entity.setId(id);
        Folder saved = folderRepository.save(entity);
        return folderMapper.toDTO(saved);
    }

    /**
     * Deletes a record by id.
     *
     * @param id the record id
     */
    @Override
    public void deleteFolder(UUID id) {
        log.info("Deleting folder with id: {}", id);
        folderRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Folder not found with id: " + id));
        folderRepository.deleteById(id);
    }
}
