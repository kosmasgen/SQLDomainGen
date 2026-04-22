package gr.knowledge.pepTest.serviceImpl;

import gr.knowledge.pepTest.dto.CountryDto;
import gr.knowledge.pepTest.mapper.CountryMapper;
import gr.knowledge.pepTest.entity.Country;
import gr.knowledge.pepTest.repository.CountryRepository;
import gr.knowledge.pepTest.service.CountryService;
import gr.knowledge.pepTest.exception.ErrorCodes;
import gr.knowledge.pepTest.exception.GeneratedRuntimeException;
import java.util.UUID;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
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
     * Retrieves all countries records.
     * @return list of CountryDto
     */
    @Override
    public List<CountryDto> getAllCountries() {
        log.info("Fetching all countries records.");
        return countryMapper.toDTOList(countryRepository.findAll());
    }

    /**
     * Retrieves a country record by id.
     * @param id the country id
     * @return CountryDto
     */
    @Override
    public CountryDto getCountryById(UUID id) {
        log.info("Fetching country with id: {}", id);

        Country existingEntity = findCountryByIdOrThrow(id);
        return countryMapper.toDTO(existingEntity);
    }

    /**
     * Creates a new country record.
     * @param dto input payload
     * @return created {@link CountryDto}
     */
    @Override
    public CountryDto createCountry(CountryDto dto) {
        log.info("Creating country.");

        validateCountryCreateUniqueConstraints(dto);

        Country entity = countryMapper.toEntity(dto);
        Country savedEntity = countryRepository.save(entity);

        return countryMapper.toDTO(savedEntity);
    }

    /**
     * Updates an existing country record.
     *
     * @param id the country id
     * @param dto input payload
     * @return updated {@link CountryDto}
     */
    @Override
    public CountryDto updateCountry(UUID id, CountryDto dto) {
        log.info("Updating country with id: {}", id);

        Country existingEntity = findCountryByIdOrThrow(id);
        countryMapper.partialUpdate(existingEntity, dto);
        Country savedEntity = countryRepository.save(existingEntity);

        return countryMapper.toDTO(savedEntity);
    }

    /**
     * Delete a country record by id.
     * @param id the country id
     */
    @Override
    public void deleteCountry(UUID id) {
        log.info("Deleting country with id: {}", id);

        findCountryByIdOrThrow(id);
        countryRepository.deleteById(id);
    }

    /**
     * Validates unique constraints for create operations.
     * @param dto input payload
     */
    private void validateCountryCreateUniqueConstraints(CountryDto dto) {

        if (dto == null) {
            return;
        }

        if (dto.getChamberId() != null && dto.getChamberCountryId() != null && countryRepository.existsByChamberIdAndChamberCountryId(dto.getChamberId(), dto.getChamberCountryId())) {
            throw GeneratedRuntimeException.builder()
                    .code(ErrorCodes.BAD_REQUEST)
                    .entity("Country")
                    .message("Country already exists with " + "chamberId=" + dto.getChamberId() + ", " + "chamberCountryId=" + dto.getChamberCountryId())
                    .build();
        }
    }

    /**
     * Finds an existing country record by id or throws an exception.
     * @param id the country id
     * @return existing Country entity
     */
    private Country findCountryByIdOrThrow(UUID id) {
        return countryRepository.findById(id)
                .orElseThrow(() -> createCountryNotFoundException(id));
    }

    /**
     Creates a NOT FOUND exception for the country entity.
     @param id the country id
     @return runtime exception
     */
    private RuntimeException createCountryNotFoundException(UUID id) {
        log.warn("Country not found with id: {}", id);

        return GeneratedRuntimeException.builder()
                .code(ErrorCodes.NOT_FOUND)
                .entity("Country")
                .message("Country not found with id: " + id)
                .build();
    }

}
