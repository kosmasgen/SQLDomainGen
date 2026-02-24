package gr.knowledge.pepTest.service;

import gr.knowledge.pepTest.dto.FolderDto;
import java.util.UUID;
import java.util.List;

/**
 * Service contract for {@code Folder} domain operations.
 */
public interface FolderService {

    /**
     * Retrieves all records.
     *
     * @return non-null list of {@link FolderDto}
     */
    List<FolderDto> getAllFolder();

    /**
     * Retrieves a record by id.
     *
     * @param id the record id
     * @return the matching {@link FolderDto}
     */
    FolderDto getFolderById(UUID id);

    /**
     * Creates a new record.
     *
     * @param dto input payload
     * @return created {@link FolderDto}
     */
    FolderDto createFolder(FolderDto dto);

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
    FolderDto updateFolder(UUID id, FolderDto dto);

    /**
     * Deletes a record by id.
     *
     * @param id the record id
     */
    void deleteFolder(UUID id);
}
