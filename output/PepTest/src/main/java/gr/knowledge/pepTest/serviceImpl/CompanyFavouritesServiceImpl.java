package gr.knowledge.pepTest.serviceImpl;

import gr.knowledge.pepTest.dto.CompanyFavouritesDto;
import gr.knowledge.pepTest.mapper.CompanyFavouritesMapper;
import gr.knowledge.pepTest.entity.CompanyFavourites;
import gr.knowledge.pepTest.repository.CompanyFavouritesRepository;
import gr.knowledge.pepTest.service.CompanyFavouritesService;
import java.util.UUID;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

/**
 * Service implementation for {@code CompanyFavourites} domain operations.
 */
@Service
@RequiredArgsConstructor
@Transactional
@Log4j2
public class CompanyFavouritesServiceImpl implements CompanyFavouritesService {

    private final CompanyFavouritesRepository companyFavouritesRepository;
    private final CompanyFavouritesMapper companyFavouritesMapper;

    /**
     * Retrieves all records.
     *
     * @return non-null list of {@link CompanyFavouritesDto}
     */
    @Override
    public List<CompanyFavouritesDto> getAllCompanyFavourites() {
        log.info("Fetching all company-favourites.");
        return companyFavouritesMapper.toDTO(companyFavouritesRepository.findAll());
    }

    /**
     * Retrieves a record by id.
     *
     * @param id the record id
     * @return the matching {@link CompanyFavouritesDto}
     */
    @Override
    public CompanyFavouritesDto getCompanyFavouritesById(UUID id) {
        log.info("Fetching company-favourites with id: {}", id);
        CompanyFavourites entity = companyFavouritesRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "CompanyFavourites not found with id: " + id));
        return companyFavouritesMapper.toDTO(entity);
    }

    /**
     * Creates a new record.
     *
     * @param dto input payload
     * @return created {@link CompanyFavouritesDto}
     */
    @Override
    public CompanyFavouritesDto createCompanyFavourites(CompanyFavouritesDto dto) {
        log.info("Creating company-favourites.");
        CompanyFavourites entity = companyFavouritesMapper.toEntity(dto);
        CompanyFavourites saved = companyFavouritesRepository.save(entity);
        return companyFavouritesMapper.toDTO(saved);
    }

    /**
     * Updates an existing record.
     *
     * Note: current implementation performs a full update (PUT-style).
     * PATCH behavior (merge non-null fields) can be added via ModelMapper config.
     *
     * @param id  the record id
     * @param dto input payload
     * @return updated {@link CompanyFavouritesDto}
     */
    @Override
    public CompanyFavouritesDto updateCompanyFavourites(UUID id, CompanyFavouritesDto dto) {
        log.info("Updating company-favourites with id: {}", id);
        companyFavouritesRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "CompanyFavourites not found with id: " + id));
        CompanyFavourites entity = companyFavouritesMapper.toEntity(dto);
        entity.setId(id);
        CompanyFavourites saved = companyFavouritesRepository.save(entity);
        return companyFavouritesMapper.toDTO(saved);
    }

    /**
     * Deletes a record by id.
     *
     * @param id the record id
     */
    @Override
    public void deleteCompanyFavourites(UUID id) {
        log.info("Deleting company-favourites with id: {}", id);
        companyFavouritesRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "CompanyFavourites not found with id: " + id));
        companyFavouritesRepository.deleteById(id);
    }
}
