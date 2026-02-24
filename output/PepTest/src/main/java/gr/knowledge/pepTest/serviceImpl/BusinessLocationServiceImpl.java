package gr.knowledge.pepTest.serviceImpl;

import gr.knowledge.pepTest.dto.BusinessLocationDto;
import gr.knowledge.pepTest.mapper.BusinessLocationMapper;
import gr.knowledge.pepTest.entity.BusinessLocation;
import gr.knowledge.pepTest.repository.BusinessLocationRepository;
import gr.knowledge.pepTest.service.BusinessLocationService;
import java.util.UUID;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

/**
 * Service implementation for {@code BusinessLocation} domain operations.
 */
@Service
@RequiredArgsConstructor
@Transactional
@Log4j2
public class BusinessLocationServiceImpl implements BusinessLocationService {

    private final BusinessLocationRepository businessLocationRepository;
    private final BusinessLocationMapper businessLocationMapper;

    /**
     * Retrieves all records.
     *
     * @return non-null list of {@link BusinessLocationDto}
     */
    @Override
    public List<BusinessLocationDto> getAllBusinessLocation() {
        log.info("Fetching all business-location.");
        return businessLocationMapper.toDTO(businessLocationRepository.findAll());
    }

    /**
     * Retrieves a record by id.
     *
     * @param id the record id
     * @return the matching {@link BusinessLocationDto}
     */
    @Override
    public BusinessLocationDto getBusinessLocationById(UUID id) {
        log.info("Fetching business-location with id: {}", id);
        BusinessLocation entity = businessLocationRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "BusinessLocation not found with id: " + id));
        return businessLocationMapper.toDTO(entity);
    }

    /**
     * Creates a new record.
     *
     * @param dto input payload
     * @return created {@link BusinessLocationDto}
     */
    @Override
    public BusinessLocationDto createBusinessLocation(BusinessLocationDto dto) {
        log.info("Creating business-location.");
        BusinessLocation entity = businessLocationMapper.toEntity(dto);
        BusinessLocation saved = businessLocationRepository.save(entity);
        return businessLocationMapper.toDTO(saved);
    }

    /**
     * Updates an existing record.
     *
     * Note: current implementation performs a full update (PUT-style).
     * PATCH behavior (merge non-null fields) can be added via ModelMapper config.
     *
     * @param id  the record id
     * @param dto input payload
     * @return updated {@link BusinessLocationDto}
     */
    @Override
    public BusinessLocationDto updateBusinessLocation(UUID id, BusinessLocationDto dto) {
        log.info("Updating business-location with id: {}", id);
        businessLocationRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "BusinessLocation not found with id: " + id));
        BusinessLocation entity = businessLocationMapper.toEntity(dto);
        entity.setId(id);
        BusinessLocation saved = businessLocationRepository.save(entity);
        return businessLocationMapper.toDTO(saved);
    }

    /**
     * Deletes a record by id.
     *
     * @param id the record id
     */
    @Override
    public void deleteBusinessLocation(UUID id) {
        log.info("Deleting business-location with id: {}", id);
        businessLocationRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "BusinessLocation not found with id: " + id));
        businessLocationRepository.deleteById(id);
    }
}
