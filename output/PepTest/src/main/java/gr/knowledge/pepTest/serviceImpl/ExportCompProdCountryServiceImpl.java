package gr.knowledge.pepTest.serviceImpl;

import gr.knowledge.pepTest.dto.ExportCompProdCountryDto;
import gr.knowledge.pepTest.mapper.ExportCompProdCountryMapper;
import gr.knowledge.pepTest.entity.ExportCompProdCountry;
import gr.knowledge.pepTest.repository.ExportCompProdCountryRepository;
import gr.knowledge.pepTest.service.ExportCompProdCountryService;
import java.util.UUID;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

/**
 * Service implementation for {@code ExportCompProdCountry} domain operations.
 */
@Service
@RequiredArgsConstructor
@Transactional
@Log4j2
public class ExportCompProdCountryServiceImpl implements ExportCompProdCountryService {

    private final ExportCompProdCountryRepository exportCompProdCountryRepository;
    private final ExportCompProdCountryMapper exportCompProdCountryMapper;

    /**
     * Retrieves all records.
     *
     * @return non-null list of {@link ExportCompProdCountryDto}
     */
    @Override
    public List<ExportCompProdCountryDto> getAllExportCompProdCountry() {
        log.info("Fetching all export-comp-prod-country.");
        return exportCompProdCountryMapper.toDTO(exportCompProdCountryRepository.findAll());
    }

    /**
     * Retrieves a record by id.
     *
     * @param id the record id
     * @return the matching {@link ExportCompProdCountryDto}
     */
    @Override
    public ExportCompProdCountryDto getExportCompProdCountryById(UUID id) {
        log.info("Fetching export-comp-prod-country with id: {}", id);
        ExportCompProdCountry entity = exportCompProdCountryRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "ExportCompProdCountry not found with id: " + id));
        return exportCompProdCountryMapper.toDTO(entity);
    }

    /**
     * Creates a new record.
     *
     * @param dto input payload
     * @return created {@link ExportCompProdCountryDto}
     */
    @Override
    public ExportCompProdCountryDto createExportCompProdCountry(ExportCompProdCountryDto dto) {
        log.info("Creating export-comp-prod-country.");
        ExportCompProdCountry entity = exportCompProdCountryMapper.toEntity(dto);
        ExportCompProdCountry saved = exportCompProdCountryRepository.save(entity);
        return exportCompProdCountryMapper.toDTO(saved);
    }

    /**
     * Updates an existing record.
     *
     * Note: current implementation performs a full update (PUT-style).
     * PATCH behavior (merge non-null fields) can be added via ModelMapper config.
     *
     * @param id  the record id
     * @param dto input payload
     * @return updated {@link ExportCompProdCountryDto}
     */
    @Override
    public ExportCompProdCountryDto updateExportCompProdCountry(UUID id, ExportCompProdCountryDto dto) {
        log.info("Updating export-comp-prod-country with id: {}", id);
        exportCompProdCountryRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "ExportCompProdCountry not found with id: " + id));
        ExportCompProdCountry entity = exportCompProdCountryMapper.toEntity(dto);
        entity.setId(id);
        ExportCompProdCountry saved = exportCompProdCountryRepository.save(entity);
        return exportCompProdCountryMapper.toDTO(saved);
    }

    /**
     * Deletes a record by id.
     *
     * @param id the record id
     */
    @Override
    public void deleteExportCompProdCountry(UUID id) {
        log.info("Deleting export-comp-prod-country with id: {}", id);
        exportCompProdCountryRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "ExportCompProdCountry not found with id: " + id));
        exportCompProdCountryRepository.deleteById(id);
    }
}
