package gr.knowledge.pepTest.serviceImpl;

import gr.knowledge.pepTest.dto.ChamberDepartmenti18nDto;
import gr.knowledge.pepTest.mapper.ChamberDepartmenti18nMapper;
import gr.knowledge.pepTest.entity.ChamberDepartmenti18n;
import gr.knowledge.pepTest.repository.ChamberDepartmenti18nRepository;
import gr.knowledge.pepTest.service.ChamberDepartmenti18nService;
import gr.knowledge.pepTest.exception.ErrorCodes;
import gr.knowledge.pepTest.exception.GeneratedRuntimeException;
import gr.knowledge.pepTest.entity.ChamberDepartmenti18nKey;
import java.util.UUID;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * Service implementation for {@code Chamber Departmenti18n} domain operations.
 */
@Service
@RequiredArgsConstructor
@Transactional
@Log4j2
public class ChamberDepartmenti18nServiceImpl implements ChamberDepartmenti18nService {

    private final ChamberDepartmenti18nRepository chamberDepartmenti18nRepository;
    private final ChamberDepartmenti18nMapper chamberDepartmenti18nMapper;

    /**
     * Retrieves all chamber departmenti18ns records.
     * @return list of ChamberDepartmenti18nDto
     */
    @Override
    public List<ChamberDepartmenti18nDto> getAllChamberDepartmenti18ns() {
        log.info("Fetching all chamber departmenti18ns records.");
        return chamberDepartmenti18nMapper.toDTOList(chamberDepartmenti18nRepository.findAll());
    }

    /**
     * Retrieves a chamber departmenti18n record by id.
     * @param departmentId the departmentId value
     * @param languageId the languageId value
     * @return ChamberDepartmenti18nDto
     */
    @Override
    public ChamberDepartmenti18nDto getChamberDepartmenti18nById(UUID departmentId, UUID languageId) {

        String compositeId = buildCompositeId(departmentId, languageId);
        log.info("Fetching chamber departmenti18n with composite id: {}", compositeId);

        ChamberDepartmenti18n existingEntity = findChamberDepartmenti18nByIdOrThrow(departmentId, languageId);
        return chamberDepartmenti18nMapper.toDTO(existingEntity);
    }

    /**
     * Creates a new chamber departmenti18n record.
     * @param dto input payload
     * @return created {@link ChamberDepartmenti18nDto}
     */
    @Override
    public ChamberDepartmenti18nDto createChamberDepartmenti18n(ChamberDepartmenti18nDto dto) {
        log.info("Creating chamber departmenti18n.");

        validateChamberDepartmenti18nDoesNotExist(dto);

        validateChamberDepartmenti18nCreateUniqueConstraints(dto);

        ChamberDepartmenti18n entity = chamberDepartmenti18nMapper.toEntity(dto);
        ChamberDepartmenti18n savedEntity = chamberDepartmenti18nRepository.save(entity);

        return chamberDepartmenti18nMapper.toDTO(savedEntity);
    }

    /**
     * Updates an existing chamber departmenti18n record.
     *
     * @param departmentId the departmentId value
     * @param languageId the languageId value
     * @param dto input payload
     * @return updated {@link ChamberDepartmenti18nDto}
     */
    @Override
    public ChamberDepartmenti18nDto updateChamberDepartmenti18n(UUID departmentId, UUID languageId, ChamberDepartmenti18nDto dto) {
        String compositeId = buildCompositeId(departmentId, languageId);

        log.info("Updating chamber departmenti18n with composite id: {}", compositeId);

        ChamberDepartmenti18n existingEntity = findChamberDepartmenti18nByIdOrThrow(departmentId, languageId);
        chamberDepartmenti18nMapper.partialUpdate(existingEntity, dto);
        ChamberDepartmenti18n savedEntity = chamberDepartmenti18nRepository.save(existingEntity);

        return chamberDepartmenti18nMapper.toDTO(savedEntity);
    }

    /**
     * Delete a chamber departmenti18n record by id.
     * @param departmentId the department_id value
     * @param languageId the language_id value
     */
    @Override
    public void deleteChamberDepartmenti18n(UUID departmentId, UUID languageId) {
        String compositeId = buildCompositeId(departmentId, languageId);
        log.info("Deleting chamber departmenti18n with composite id: {}", compositeId);

        findChamberDepartmenti18nByIdOrThrow(departmentId, languageId);
        chamberDepartmenti18nRepository.deleteById(buildKey(departmentId, languageId));
    }

    /**
     * Validates unique constraints for create operations.
     * @param dto input payload
     */
    private void validateChamberDepartmenti18nCreateUniqueConstraints(ChamberDepartmenti18nDto dto) {

        if (dto == null) {
            return;
        }

        if (dto.getId() != null && dto.getId().getDepartmentId() != null && dto.getChamberI18nId() != null && chamberDepartmenti18nRepository.existsByIdDepartmentIdAndChamberI18nId(dto.getId().getDepartmentId(), dto.getChamberI18nId())) {
            throw GeneratedRuntimeException.builder()
                    .code(ErrorCodes.BAD_REQUEST)
                    .entity("ChamberDepartmenti18n")
                    .message("ChamberDepartmenti18n already exists with " + "departmentId=" + dto.getId().getDepartmentId() + ", " + "chamberI18nId=" + dto.getChamberI18nId())
                    .build();
        }
    }

    /**
     * Finds an existing chamber departmenti18n record by id or throws an exception.
     * @param departmentId the departmentId value
     * @param languageId the languageId value
     * @return existing ChamberDepartmenti18n entity
     */
    private ChamberDepartmenti18n findChamberDepartmenti18nByIdOrThrow(UUID departmentId, UUID languageId) {
        return chamberDepartmenti18nRepository.findById(buildKey(departmentId, languageId))
                .orElseThrow(() -> createChamberDepartmenti18nNotFoundException(departmentId, languageId));
    }

    /**
     Creates a NOT FOUND exception for the chamber departmenti18n entity.
     @param departmentId the department_id value
     @param languageId the language_id value
     @return runtime exception
     */
    private RuntimeException createChamberDepartmenti18nNotFoundException(UUID departmentId, UUID languageId) {
        String compositeId = buildCompositeId(departmentId, languageId);
        log.warn("ChamberDepartmenti18n not found with composite id: {}", compositeId);

        return GeneratedRuntimeException.builder()
                .code(ErrorCodes.NOT_FOUND)
                .entity("ChamberDepartmenti18n")
                .message("ChamberDepartmenti18n not found with composite id: " + compositeId)
                .build();
    }

    /**
     * Validates that a chamber departmenti18n record does not already exist.
     * @param dto input payload
     * @throws GeneratedRuntimeException if the entity already exists
     */
    private void validateChamberDepartmenti18nDoesNotExist(ChamberDepartmenti18nDto dto) {
        if (dto == null || dto.getId().getDepartmentId() == null || dto.getId().getLanguageId() == null) {
            return;
        }

        ChamberDepartmenti18nKey key = buildKey(dto.getId().getDepartmentId(), dto.getId().getLanguageId());

        if (chamberDepartmenti18nRepository.existsById(key)) {
            String compositeId = buildCompositeId(dto.getId().getDepartmentId(), dto.getId().getLanguageId());
            log.warn("ChamberDepartmenti18n already exists with composite id: {}", compositeId);

            throw GeneratedRuntimeException.builder()
                    .code(ErrorCodes.BAD_REQUEST)
                    .entity("ChamberDepartmenti18n")
                    .message("ChamberDepartmenti18n already exists with composite id: " + compositeId)
                    .build();
        }
    }

    /**
     * Builds the composite key.
     *
     * @param departmentId the departmentId value
     * @param languageId the languageId value
     * @return populated {@link ChamberDepartmenti18nKey}
     */
    private ChamberDepartmenti18nKey buildKey(UUID departmentId, UUID languageId) {
        ChamberDepartmenti18nKey key = new ChamberDepartmenti18nKey();
        key.setDepartmentId(departmentId);
        key.setLanguageId(languageId);
        return key;
    }

    /**
     * Builds the composite id string.
     *
     * @param departmentId the departmentId value
     * @param languageId the languageId value
     * @return composite id string
     */
    private String buildCompositeId(UUID departmentId, UUID languageId) {
        return "departmentId=" + departmentId + ", " + "languageId=" + languageId;
    }

}
