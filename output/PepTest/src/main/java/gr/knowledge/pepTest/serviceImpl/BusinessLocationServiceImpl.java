package gr.knowledge.pepTest.serviceImpl;

import gr.knowledge.pepTest.dto.BusinessLocationDto;
import gr.knowledge.pepTest.mapper.BusinessLocationMapper;
import gr.knowledge.pepTest.entity.BusinessLocation;
import gr.knowledge.pepTest.repository.BusinessLocationRepository;
import gr.knowledge.pepTest.service.BusinessLocationService;
import gr.knowledge.pepTest.exception.ErrorCodes;
import gr.knowledge.pepTest.exception.GeneratedRuntimeException;
import java.util.UUID;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * Service implementation for {@code Business Location} domain operations.
 */
@Service
@RequiredArgsConstructor
@Transactional
@Log4j2
public class BusinessLocationServiceImpl implements BusinessLocationService {

    private final BusinessLocationRepository businessLocationRepository;
    private final BusinessLocationMapper businessLocationMapper;

    /**
     * Retrieves all business locations records.
     * @return list of BusinessLocationDto
     */
    @Override
    public List<BusinessLocationDto> getAllBusinessLocations() {
        log.info("Fetching all business locations records.");
        return businessLocationMapper.toDTOList(businessLocationRepository.findAll());
    }

    /**
     * Retrieves a business location record by id.
     * @param id the business location id
     * @return BusinessLocationDto
     */
    @Override
    public BusinessLocationDto getBusinessLocationById(UUID id) {
        log.info("Fetching business location with id: {}", id);

        BusinessLocation existingEntity = findBusinessLocationByIdOrThrow(id);
        return businessLocationMapper.toDTO(existingEntity);
    }

    /**
     * Creates a new business location record.
     * @param dto input payload
     * @return created {@link BusinessLocationDto}
     */
    @Override
    public BusinessLocationDto createBusinessLocation(BusinessLocationDto dto) {
        log.info("Creating business location.");

        BusinessLocation entity = businessLocationMapper.toEntity(dto);
        BusinessLocation savedEntity = businessLocationRepository.save(entity);

        return businessLocationMapper.toDTO(savedEntity);
    }

    /**
     * Updates an existing business location record.
     * <p>
     * Only non null fields from the DTO are applied to the existing entity.
     * @param id the business location id
     * @param dto input payload with partial fields
     * @return updated {@link BusinessLocationDto}
     */
    @Override
    public BusinessLocationDto updateBusinessLocation(UUID id, BusinessLocationDto dto) {
        log.info("Updating business location with id: {}", id);

        BusinessLocation existingEntity = findBusinessLocationByIdOrThrow(id);
        businessLocationMapper.partialUpdate(existingEntity, dto);
        BusinessLocation savedEntity = businessLocationRepository.save(existingEntity);

        return businessLocationMapper.toDTO(savedEntity);
    }

    /**
     * Delete a business location record by id.
     * @param id the business location id
     */
    @Override
    public void deleteBusinessLocation(UUID id) {
        log.info("Deleting business location with id: {}", id);

        findBusinessLocationByIdOrThrow(id);
        businessLocationRepository.deleteById(id);
    }

    /**
     * Finds an existing business location record by id or throws an exception.
     * @param id the business location id
     * @return existing BusinessLocation entity
     */
    private BusinessLocation findBusinessLocationByIdOrThrow(UUID id) {
        return businessLocationRepository.findById(id)
                .orElseThrow(() -> createBusinessLocationNotFoundException(id));
    }

    /**
     Creates a NOT FOUND exception for the business location entity.
     @param id the business location id
     @return runtime exception
     */
    private RuntimeException createBusinessLocationNotFoundException(UUID id) {
        log.warn("BusinessLocation not found with id: {}", id);

        return GeneratedRuntimeException.builder()
                .code(ErrorCodes.NOT_FOUND)
                .entity("BusinessLocation")
                .message("BusinessLocation not found with id: " + id)
                .build();
    }

}
