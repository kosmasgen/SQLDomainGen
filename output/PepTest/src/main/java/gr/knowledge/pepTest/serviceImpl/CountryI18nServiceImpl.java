package gr.knowledge.pepTest.serviceImpl;

import gr.knowledge.pepTest.dto.CountryI18nDto;
import gr.knowledge.pepTest.mapper.CountryI18nMapper;
import gr.knowledge.pepTest.entity.CountryI18n;
import gr.knowledge.pepTest.repository.CountryI18nRepository;
import gr.knowledge.pepTest.service.CountryI18nService;
import gr.knowledge.pepTest.entity.CountryI18nPK;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

/**
 * Service implementation for {@code CountryI18n} domain operations.
 */
@Service
@RequiredArgsConstructor
@Transactional
@Log4j2
public class CountryI18nServiceImpl implements CountryI18nService {

    private final CountryI18nRepository countryI18nRepository;
    private final CountryI18nMapper countryI18nMapper;

    /**
     * Retrieves all records.
     *
     * @return non-null list of {@link CountryI18nDto}
     */
    @Override
    public List<CountryI18nDto> getAllCountryI18n() {
        log.info("Fetching all country-i18n.");
        return countryI18nMapper.toDTO(countryI18nRepository.findAll());
    }

    /**
     * Retrieves a record by id.
     *
     * @param id the record id
     * @return the matching {@link CountryI18nDto}
     */
    @Override
    public CountryI18nDto getCountryI18nById(CountryI18nPK id) {
        log.info("Fetching country-i18n with id: {}", id);
        CountryI18n entity = countryI18nRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "CountryI18n not found with id: " + id));
        return countryI18nMapper.toDTO(entity);
    }

    /**
     * Creates a new record.
     *
     * @param dto input payload
     * @return created {@link CountryI18nDto}
     */
    @Override
    public CountryI18nDto createCountryI18n(CountryI18nDto dto) {
        log.info("Creating country-i18n.");
        CountryI18n entity = countryI18nMapper.toEntity(dto);
        CountryI18n saved = countryI18nRepository.save(entity);
        return countryI18nMapper.toDTO(saved);
    }

    /**
     * Updates an existing record.
     *
     * Note: current implementation performs a full update (PUT-style).
     * PATCH behavior (merge non-null fields) can be added via ModelMapper config.
     *
     * @param id  the record id
     * @param dto input payload
     * @return updated {@link CountryI18nDto}
     */
    @Override
    public CountryI18nDto updateCountryI18n(CountryI18nPK id, CountryI18nDto dto) {
        log.info("Updating country-i18n with id: {}", id);
        countryI18nRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "CountryI18n not found with id: " + id));
        CountryI18n entity = countryI18nMapper.toEntity(dto);
        entity.setId(id);
        CountryI18n saved = countryI18nRepository.save(entity);
        return countryI18nMapper.toDTO(saved);
    }

    /**
     * Deletes a record by id.
     *
     * @param id the record id
     */
    @Override
    public void deleteCountryI18n(CountryI18nPK id) {
        log.info("Deleting country-i18n with id: {}", id);
        countryI18nRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "CountryI18n not found with id: " + id));
        countryI18nRepository.deleteById(id);
    }
}
