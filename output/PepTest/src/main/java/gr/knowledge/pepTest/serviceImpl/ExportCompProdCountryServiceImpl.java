package gr.knowledge.pepTest.serviceImpl;

import gr.knowledge.pepTest.dto.ExportCompProdCountryDto;
import gr.knowledge.pepTest.mapper.ExportCompProdCountryMapper;
import gr.knowledge.pepTest.entity.ExportCompProdCountry;
import gr.knowledge.pepTest.repository.ExportCompProdCountryRepository;
import gr.knowledge.pepTest.service.ExportCompProdCountryService;
import gr.knowledge.pepTest.exception.ErrorCodes;
import gr.knowledge.pepTest.exception.GeneratedRuntimeException;
import java.util.UUID;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * Service implementation for {@code Export Comp Prod Country} domain operations.
 */
@Service
@RequiredArgsConstructor
@Transactional
@Log4j2
public class ExportCompProdCountryServiceImpl implements ExportCompProdCountryService {

    private final ExportCompProdCountryRepository exportCompProdCountryRepository;
    private final ExportCompProdCountryMapper exportCompProdCountryMapper;

    /**
     * Retrieves all export comp prod countries records.
     * @return list of ExportCompProdCountryDto
     */
    @Override
    public List<ExportCompProdCountryDto> getAllExportCompProdCountries() {
        log.info("Fetching all export comp prod countries records.");
        return exportCompProdCountryMapper.toDTOList(exportCompProdCountryRepository.findAll());
    }

    /**
     * Retrieves a export comp prod country record by id.
     * @param id the export comp prod country id
     * @return ExportCompProdCountryDto
     */
    @Override
    public ExportCompProdCountryDto getExportCompProdCountryById(UUID id) {
        log.info("Fetching export comp prod country with id: {}", id);

        ExportCompProdCountry existingEntity = findExportCompProdCountryByIdOrThrow(id);
        return exportCompProdCountryMapper.toDTO(existingEntity);
    }

    /**
     * Creates a new export comp prod country record.
     * @param dto input payload
     * @return created {@link ExportCompProdCountryDto}
     */
    @Override
    public ExportCompProdCountryDto createExportCompProdCountry(ExportCompProdCountryDto dto) {
        log.info("Creating export comp prod country.");

        ExportCompProdCountry entity = exportCompProdCountryMapper.toEntity(dto);
        ExportCompProdCountry savedEntity = exportCompProdCountryRepository.save(entity);

        return exportCompProdCountryMapper.toDTO(savedEntity);
    }

    /**
     * Updates an existing export comp prod country record.
     *
     * @param id the export comp prod country id
     * @param dto input payload
     * @return updated {@link ExportCompProdCountryDto}
     */
    @Override
    public ExportCompProdCountryDto updateExportCompProdCountry(UUID id, ExportCompProdCountryDto dto) {
        log.info("Updating export comp prod country with id: {}", id);

        ExportCompProdCountry existingEntity = findExportCompProdCountryByIdOrThrow(id);
        exportCompProdCountryMapper.partialUpdate(existingEntity, dto);
        ExportCompProdCountry savedEntity = exportCompProdCountryRepository.save(existingEntity);

        return exportCompProdCountryMapper.toDTO(savedEntity);
    }

    /**
     * Delete a export comp prod country record by id.
     * @param id the export comp prod country id
     */
    @Override
    public void deleteExportCompProdCountry(UUID id) {
        log.info("Deleting export comp prod country with id: {}", id);

        findExportCompProdCountryByIdOrThrow(id);
        exportCompProdCountryRepository.deleteById(id);
    }

    /**
     * Finds an existing export comp prod country record by id or throws an exception.
     * @param id the export comp prod country id
     * @return existing ExportCompProdCountry entity
     */
    private ExportCompProdCountry findExportCompProdCountryByIdOrThrow(UUID id) {
        return exportCompProdCountryRepository.findById(id)
                .orElseThrow(() -> createExportCompProdCountryNotFoundException(id));
    }

    /**
     Creates a NOT FOUND exception for the export comp prod country entity.
     @param id the export comp prod country id
     @return runtime exception
     */
    private RuntimeException createExportCompProdCountryNotFoundException(UUID id) {
        log.warn("ExportCompProdCountry not found with id: {}", id);

        return GeneratedRuntimeException.builder()
                .code(ErrorCodes.NOT_FOUND)
                .entity("ExportCompProdCountry")
                .message("ExportCompProdCountry not found with id: " + id)
                .build();
    }

}
