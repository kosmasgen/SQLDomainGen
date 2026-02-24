package gr.knowledge.pepTest.serviceImpl;

import gr.knowledge.pepTest.dto.CountryDto;
import gr.knowledge.pepTest.mapper.CountryMapper;
import gr.knowledge.pepTest.entity.Country;
import gr.knowledge.pepTest.repository.CountryRepository;
import gr.knowledge.pepTest.service.CountryService;
import java.util.UUID;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

/**
 * Service implementation for {@code Country} domain operations.
 */
@Service
@RequiredArgsConstructor
@Transactional
@Log4j2
public class CountryServiceImpl implements CountryService {

    private final CountryRepository countryRepository;
    private final CountryMapper countryMapper;

    /**
     * Retrieves all records.
     *
     * @return non-null list of {@link CountryDto}
     */
    @Override
    public List<CountryDto> getAllCountry() {
        log.info("Fetching all country.");
        return countryMapper.toDTO(countryRepository.findAll());
    }

    /**
     * Retrieves a record by id.
     *
     * @param id the record id
     * @return the matching {@link CountryDto}
     */
    @Override
    public CountryDto getCountryById(UUID id) {
        log.info("Fetching country with id: {}", id);
        Country entity = countryRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Country not found with id: " + id));
        return countryMapper.toDTO(entity);
    }

    /**
     * Creates a new record.
     *
     * @param dto input payload
     * @return created {@link CountryDto}
     */
    @Override
    public CountryDto createCountry(CountryDto dto) {
        log.info("Creating country.");
        Country entity = countryMapper.toEntity(dto);
        Country saved = countryRepository.save(entity);
        return countryMapper.toDTO(saved);
    }

    /**
     * Updates an existing record.
     *
     * Note: current implementation performs a full update (PUT-style).
     * PATCH behavior (merge non-null fields) can be added via ModelMapper config.
     *
     * @param id  the record id
     * @param dto input payload
     * @return updated {@link CountryDto}
     */
    @Override
    public CountryDto updateCountry(UUID id, CountryDto dto) {
        log.info("Updating country with id: {}", id);
        countryRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Country not found with id: " + id));
        Country entity = countryMapper.toEntity(dto);
        entity.setId(id);
        Country saved = countryRepository.save(entity);
        return countryMapper.toDTO(saved);
    }

    /**
     * Deletes a record by id.
     *
     * @param id the record id
     */
    @Override
    public void deleteCountry(UUID id) {
        log.info("Deleting country with id: {}", id);
        countryRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Country not found with id: " + id));
        countryRepository.deleteById(id);
    }
}
