package gr.knowledge.pepTest.serviceImpl;

import gr.knowledge.pepTest.dto.ExportCompanyDto;
import gr.knowledge.pepTest.mapper.ExportCompanyMapper;
import gr.knowledge.pepTest.entity.ExportCompany;
import gr.knowledge.pepTest.repository.ExportCompanyRepository;
import gr.knowledge.pepTest.service.ExportCompanyService;
import java.util.UUID;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

/**
 * Service implementation for {@code ExportCompany} domain operations.
 */
@Service
@RequiredArgsConstructor
@Transactional
@Log4j2
public class ExportCompanyServiceImpl implements ExportCompanyService {

    private final ExportCompanyRepository exportCompanyRepository;
    private final ExportCompanyMapper exportCompanyMapper;

    /**
     * Retrieves all records.
     *
     * @return non-null list of {@link ExportCompanyDto}
     */
    @Override
    public List<ExportCompanyDto> getAllExportCompany() {
        log.info("Fetching all export-company.");
        return exportCompanyMapper.toDTO(exportCompanyRepository.findAll());
    }

    /**
     * Retrieves a record by id.
     *
     * @param id the record id
     * @return the matching {@link ExportCompanyDto}
     */
    @Override
    public ExportCompanyDto getExportCompanyById(UUID id) {
        log.info("Fetching export-company with id: {}", id);
        ExportCompany entity = exportCompanyRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "ExportCompany not found with id: " + id));
        return exportCompanyMapper.toDTO(entity);
    }

    /**
     * Creates a new record.
     *
     * @param dto input payload
     * @return created {@link ExportCompanyDto}
     */
    @Override
    public ExportCompanyDto createExportCompany(ExportCompanyDto dto) {
        log.info("Creating export-company.");
        ExportCompany entity = exportCompanyMapper.toEntity(dto);
        ExportCompany saved = exportCompanyRepository.save(entity);
        return exportCompanyMapper.toDTO(saved);
    }

    /**
     * Updates an existing record.
     *
     * Note: current implementation performs a full update (PUT-style).
     * PATCH behavior (merge non-null fields) can be added via ModelMapper config.
     *
     * @param id  the record id
     * @param dto input payload
     * @return updated {@link ExportCompanyDto}
     */
    @Override
    public ExportCompanyDto updateExportCompany(UUID id, ExportCompanyDto dto) {
        log.info("Updating export-company with id: {}", id);
        exportCompanyRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "ExportCompany not found with id: " + id));
        ExportCompany entity = exportCompanyMapper.toEntity(dto);
        entity.setId(id);
        ExportCompany saved = exportCompanyRepository.save(entity);
        return exportCompanyMapper.toDTO(saved);
    }

    /**
     * Deletes a record by id.
     *
     * @param id the record id
     */
    @Override
    public void deleteExportCompany(UUID id) {
        log.info("Deleting export-company with id: {}", id);
        exportCompanyRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "ExportCompany not found with id: " + id));
        exportCompanyRepository.deleteById(id);
    }
}
