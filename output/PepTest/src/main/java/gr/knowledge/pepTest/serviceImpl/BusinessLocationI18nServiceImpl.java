package gr.knowledge.pepTest.serviceImpl;

import gr.knowledge.pepTest.dto.BusinessLocationI18nDto;
import gr.knowledge.pepTest.mapper.BusinessLocationI18nMapper;
import gr.knowledge.pepTest.entity.BusinessLocationI18n;
import gr.knowledge.pepTest.repository.BusinessLocationI18nRepository;
import gr.knowledge.pepTest.service.BusinessLocationI18nService;
import gr.knowledge.pepTest.entity.BusinessLocationI18nPK;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

/**
 * Service implementation for {@code BusinessLocationI18n} domain operations.
 */
@Service
@RequiredArgsConstructor
@Transactional
@Log4j2
public class BusinessLocationI18nServiceImpl implements BusinessLocationI18nService {

    private final BusinessLocationI18nRepository businessLocationI18nRepository;
    private final BusinessLocationI18nMapper businessLocationI18nMapper;

    /**
     * Retrieves all records.
     *
     * @return non-null list of {@link BusinessLocationI18nDto}
     */
    @Override
    public List<BusinessLocationI18nDto> getAllBusinessLocationI18n() {
        log.info("Fetching all business-location-i18n.");
        return businessLocationI18nMapper.toDTO(businessLocationI18nRepository.findAll());
    }

    /**
     * Retrieves a record by id.
     *
     * @param id the record id
     * @return the matching {@link BusinessLocationI18nDto}
     */
    @Override
    public BusinessLocationI18nDto getBusinessLocationI18nById(BusinessLocationI18nPK id) {
        log.info("Fetching business-location-i18n with id: {}", id);
        BusinessLocationI18n entity = businessLocationI18nRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "BusinessLocationI18n not found with id: " + id));
        return businessLocationI18nMapper.toDTO(entity);
    }

    /**
     * Creates a new record.
     *
     * @param dto input payload
     * @return created {@link BusinessLocationI18nDto}
     */
    @Override
    public BusinessLocationI18nDto createBusinessLocationI18n(BusinessLocationI18nDto dto) {
        log.info("Creating business-location-i18n.");
        BusinessLocationI18n entity = businessLocationI18nMapper.toEntity(dto);
        BusinessLocationI18n saved = businessLocationI18nRepository.save(entity);
        return businessLocationI18nMapper.toDTO(saved);
    }

    /**
     * Updates an existing record.
     *
     * Note: current implementation performs a full update (PUT-style).
     * PATCH behavior (merge non-null fields) can be added via ModelMapper config.
     *
     * @param id  the record id
     * @param dto input payload
     * @return updated {@link BusinessLocationI18nDto}
     */
    @Override
    public BusinessLocationI18nDto updateBusinessLocationI18n(BusinessLocationI18nPK id, BusinessLocationI18nDto dto) {
        log.info("Updating business-location-i18n with id: {}", id);
        businessLocationI18nRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "BusinessLocationI18n not found with id: " + id));
        BusinessLocationI18n entity = businessLocationI18nMapper.toEntity(dto);
        entity.setId(id);
        BusinessLocationI18n saved = businessLocationI18nRepository.save(entity);
        return businessLocationI18nMapper.toDTO(saved);
    }

    /**
     * Deletes a record by id.
     *
     * @param id the record id
     */
    @Override
    public void deleteBusinessLocationI18n(BusinessLocationI18nPK id) {
        log.info("Deleting business-location-i18n with id: {}", id);
        businessLocationI18nRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "BusinessLocationI18n not found with id: " + id));
        businessLocationI18nRepository.deleteById(id);
    }
}
