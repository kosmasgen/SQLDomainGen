package gr.knowledge.pepTest.serviceImpl;

import gr.knowledge.pepTest.dto.MunicipalityDto;
import gr.knowledge.pepTest.mapper.MunicipalityMapper;
import gr.knowledge.pepTest.entity.Municipality;
import gr.knowledge.pepTest.repository.MunicipalityRepository;
import gr.knowledge.pepTest.service.MunicipalityService;
import gr.knowledge.pepTest.exception.ErrorCodes;
import gr.knowledge.pepTest.exception.GeneratedRuntimeException;
import java.util.UUID;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * Service implementation for {@code Municipality} domain operations.
 */
@Service
@RequiredArgsConstructor
@Transactional
@Log4j2
public class MunicipalityServiceImpl implements MunicipalityService {

    private final MunicipalityRepository municipalityRepository;
    private final MunicipalityMapper municipalityMapper;

    /**
     * Retrieves all municipalities records.
     * @return list of MunicipalityDto
     */
    @Override
    public List<MunicipalityDto> getAllMunicipalities() {
        log.info("Fetching all municipalities records.");
        return municipalityMapper.toDTOList(municipalityRepository.findAll());
    }

    /**
     * Retrieves a municipality record by id.
     * @param id the municipality id
     * @return MunicipalityDto
     */
    @Override
    public MunicipalityDto getMunicipalityById(UUID id) {
        log.info("Fetching municipality with id: {}", id);

        Municipality existingEntity = findMunicipalityByIdOrThrow(id);
        return municipalityMapper.toDTO(existingEntity);
    }

    /**
     * Creates a new municipality record.
     * @param dto input payload
     * @return created {@link MunicipalityDto}
     */
    @Override
    public MunicipalityDto createMunicipality(MunicipalityDto dto) {
        log.info("Creating municipality.");

        validateMunicipalityCreateUniqueConstraints(dto);

        Municipality entity = municipalityMapper.toEntity(dto);
        Municipality savedEntity = municipalityRepository.save(entity);

        return municipalityMapper.toDTO(savedEntity);
    }

    /**
     * Updates an existing municipality record.
     * <p>
     * Only non null fields from the DTO are applied to the existing entity.
     * @param id the municipality id
     * @param dto input payload with partial fields
     * @return updated {@link MunicipalityDto}
     */
    @Override
    public MunicipalityDto updateMunicipality(UUID id, MunicipalityDto dto) {
        log.info("Updating municipality with id: {}", id);

        Municipality existingEntity = findMunicipalityByIdOrThrow(id);
        municipalityMapper.partialUpdate(existingEntity, dto);
        Municipality savedEntity = municipalityRepository.save(existingEntity);

        return municipalityMapper.toDTO(savedEntity);
    }

    /**
     * Delete a municipality record by id.
     * @param id the municipality id
     */
    @Override
    public void deleteMunicipality(UUID id) {
        log.info("Deleting municipality with id: {}", id);

        findMunicipalityByIdOrThrow(id);
        municipalityRepository.deleteById(id);
    }

    /**
     * Validates unique constraints for create operations.
     * @param dto input payload
     */
    private void validateMunicipalityCreateUniqueConstraints(MunicipalityDto dto) {

        if (dto == null) {
            return;
        }

        if (dto.getChamberId() != null && dto.getChamberMunicipalityId() != null && municipalityRepository.existsByChamberIdAndChamberMunicipalityId(dto.getChamberId(), dto.getChamberMunicipalityId())) {
            throw GeneratedRuntimeException.builder()
                    .code(ErrorCodes.BAD_REQUEST)
                    .entity("Municipality")
                    .message("Municipality already exists with " + "chamberId=" + dto.getChamberId() + ", " + "chamberMunicipalityId=" + dto.getChamberMunicipalityId())
                    .build();
        }
    }

    /**
     * Finds an existing municipality record by id or throws an exception.
     * @param id the municipality id
     * @return existing Municipality entity
     */
    private Municipality findMunicipalityByIdOrThrow(UUID id) {
        return municipalityRepository.findById(id)
                .orElseThrow(() -> createMunicipalityNotFoundException(id));
    }

    /**
     Creates a NOT FOUND exception for the municipality entity.
     @param id the municipality id
     @return runtime exception
     */
    private RuntimeException createMunicipalityNotFoundException(UUID id) {
        log.warn("Municipality not found with id: {}", id);

        return GeneratedRuntimeException.builder()
                .code(ErrorCodes.NOT_FOUND)
                .entity("Municipality")
                .message("Municipality not found with id: " + id)
                .build();
    }

}
