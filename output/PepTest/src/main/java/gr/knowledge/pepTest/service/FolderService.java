package gr.knowledge.pepTest.service;

import gr.knowledge.pepTest.dto.FolderDto;
import java.util.List;
import java.util.UUID;

/**
 * Service contract for {@code Folder} domain operations.
 */
public interface FolderService {

    /**
     * Retrieves all folders.
     * @return non-null list of {@link FolderDto}
     */
    List<FolderDto> getAllFolders();

    /**
     * Retrieves a record by id.
     * @param id the record id
     * @return the matching {@link FolderDto}
     */
    FolderDto getFolderById(UUID id);

    /**
     * Creates a new record.
     * @param dto input payload
     * @return created {@link FolderDto}
     */
    FolderDto createFolder(FolderDto dto);

    /**
     * Updates an existing record.
     * <p>
     * Only non-null fields from the DTO are applied to the existing entity.
     * @param id the record id
     * @param dto input payload with partial fields
     * @return updated {@link FolderDto}
     */
    FolderDto updateFolder(UUID id, FolderDto dto);

    /**
     * Deletes a record by id.
     * @param id the record id
     */
    void deleteFolder(UUID id);
}
