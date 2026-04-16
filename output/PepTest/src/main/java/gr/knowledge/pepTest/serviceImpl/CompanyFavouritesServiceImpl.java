package gr.knowledge.pepTest.serviceImpl;

import gr.knowledge.pepTest.dto.CompanyFavouritesDto;
import gr.knowledge.pepTest.mapper.CompanyFavouritesMapper;
import gr.knowledge.pepTest.entity.CompanyFavourites;
import gr.knowledge.pepTest.repository.CompanyFavouritesRepository;
import gr.knowledge.pepTest.service.CompanyFavouritesService;
import gr.knowledge.pepTest.exception.ErrorCodes;
import gr.knowledge.pepTest.exception.GeneratedRuntimeException;
import java.util.UUID;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * Service implementation for {@code Company Favourites} domain operations.
 */
@Service
@RequiredArgsConstructor
@Transactional
@Log4j2
public class CompanyFavouritesServiceImpl implements CompanyFavouritesService {

    private final CompanyFavouritesRepository companyFavouritesRepository;
    private final CompanyFavouritesMapper companyFavouritesMapper;

    /**
     * Retrieves all company favouriteses records.
     * @return list of CompanyFavouritesDto
     */
    @Override
    public List<CompanyFavouritesDto> getAllCompanyFavouriteses() {
        log.info("Fetching all company favouriteses records.");
        return companyFavouritesMapper.toDTOList(companyFavouritesRepository.findAll());
    }

    /**
     * Retrieves a company favourites record by id.
     * @param id the company favourites id
     * @return CompanyFavouritesDto
     */
    @Override
    public CompanyFavouritesDto getCompanyFavouritesById(UUID id) {
        log.info("Fetching company favourites with id: {}", id);

        CompanyFavourites existingEntity = findCompanyFavouritesByIdOrThrow(id);
        return companyFavouritesMapper.toDTO(existingEntity);
    }

    /**
     * Creates a new company favourites record.
     * @param dto input payload
     * @return created {@link CompanyFavouritesDto}
     */
    @Override
    public CompanyFavouritesDto createCompanyFavourites(CompanyFavouritesDto dto) {
        log.info("Creating company favourites.");

        CompanyFavourites entity = companyFavouritesMapper.toEntity(dto);
        CompanyFavourites savedEntity = companyFavouritesRepository.save(entity);

        return companyFavouritesMapper.toDTO(savedEntity);
    }

    /**
     * Updates an existing company favourites record.
     * <p>
     * Only non null fields from the DTO are applied to the existing entity.
     * @param id the company favourites id
     * @param dto input payload with partial fields
     * @return updated {@link CompanyFavouritesDto}
     */
    @Override
    public CompanyFavouritesDto updateCompanyFavourites(UUID id, CompanyFavouritesDto dto) {
        log.info("Updating company favourites with id: {}", id);

        CompanyFavourites existingEntity = findCompanyFavouritesByIdOrThrow(id);
        companyFavouritesMapper.partialUpdate(existingEntity, dto);
        CompanyFavourites savedEntity = companyFavouritesRepository.save(existingEntity);

        return companyFavouritesMapper.toDTO(savedEntity);
    }

    /**
     * Delete a company favourites record by id.
     * @param id the company favourites id
     */
    @Override
    public void deleteCompanyFavourites(UUID id) {
        log.info("Deleting company favourites with id: {}", id);

        findCompanyFavouritesByIdOrThrow(id);
        companyFavouritesRepository.deleteById(id);
    }

    /**
     * Finds an existing company favourites record by id or throws an exception.
     * @param id the company favourites id
     * @return existing CompanyFavourites entity
     */
    private CompanyFavourites findCompanyFavouritesByIdOrThrow(UUID id) {
        return companyFavouritesRepository.findById(id)
                .orElseThrow(() -> createCompanyFavouritesNotFoundException(id));
    }

    /**
     Creates a NOT FOUND exception for the company favourites entity.
     @param id the company favourites id
     @return runtime exception
     */
    private RuntimeException createCompanyFavouritesNotFoundException(UUID id) {
        log.warn("CompanyFavourites not found with id: {}", id);

        return GeneratedRuntimeException.builder()
                .code(ErrorCodes.NOT_FOUND)
                .entity("CompanyFavourites")
                .message("CompanyFavourites not found with id: " + id)
                .build();
    }

}
