package gr.knowledge.pepTest.serviceImpl;

import gr.knowledge.pepTest.dto.ExportCompanyDto;
import gr.knowledge.pepTest.mapper.ExportCompanyMapper;
import gr.knowledge.pepTest.entity.ExportCompany;
import gr.knowledge.pepTest.repository.ExportCompanyRepository;
import gr.knowledge.pepTest.service.ExportCompanyService;
import gr.knowledge.pepTest.exception.ErrorCodes;
import gr.knowledge.pepTest.exception.GeneratedRuntimeException;
import java.util.UUID;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * Service implementation for {@code Export Company} domain operations.
 */
@Service
@RequiredArgsConstructor
@Transactional
@Log4j2
public class ExportCompanyServiceImpl implements ExportCompanyService {

    private final ExportCompanyRepository exportCompanyRepository;
    private final ExportCompanyMapper exportCompanyMapper;

    /**
     * Retrieves all export companies records.
     * @return list of ExportCompanyDto
     */
    @Override
    public List<ExportCompanyDto> getAllExportCompanies() {
        log.info("Fetching all export companies records.");
        return exportCompanyMapper.toDTOList(exportCompanyRepository.findAll());
    }

    /**
     * Retrieves a export company record by id.
     * @param id the export company id
     * @return ExportCompanyDto
     */
    @Override
    public ExportCompanyDto getExportCompanyById(UUID id) {
        log.info("Fetching export company with id: {}", id);

        ExportCompany existingEntity = findExportCompanyByIdOrThrow(id);
        return exportCompanyMapper.toDTO(existingEntity);
    }

    /**
     * Creates a new export company record.
     * @param dto input payload
     * @return created {@link ExportCompanyDto}
     */
    @Override
    public ExportCompanyDto createExportCompany(ExportCompanyDto dto) {
        log.info("Creating export company.");

        ExportCompany entity = exportCompanyMapper.toEntity(dto);
        ExportCompany savedEntity = exportCompanyRepository.save(entity);

        return exportCompanyMapper.toDTO(savedEntity);
    }

    /**
     * Updates an existing export company record.
     * <p>
     * Only non null fields from the DTO are applied to the existing entity.
     * @param id the export company id
     * @param dto input payload with partial fields
     * @return updated {@link ExportCompanyDto}
     */
    @Override
    public ExportCompanyDto updateExportCompany(UUID id, ExportCompanyDto dto) {
        log.info("Updating export company with id: {}", id);

        ExportCompany existingEntity = findExportCompanyByIdOrThrow(id);
        exportCompanyMapper.partialUpdate(existingEntity, dto);
        ExportCompany savedEntity = exportCompanyRepository.save(existingEntity);

        return exportCompanyMapper.toDTO(savedEntity);
    }

    /**
     * Delete a export company record by id.
     * @param id the export company id
     */
    @Override
    public void deleteExportCompany(UUID id) {
        log.info("Deleting export company with id: {}", id);

        findExportCompanyByIdOrThrow(id);
        exportCompanyRepository.deleteById(id);
    }

    /**
     * Finds an existing export company record by id or throws an exception.
     * @param id the export company id
     * @return existing ExportCompany entity
     */
    private ExportCompany findExportCompanyByIdOrThrow(UUID id) {
        return exportCompanyRepository.findById(id)
                .orElseThrow(() -> createExportCompanyNotFoundException(id));
    }

    /**
     Creates a NOT FOUND exception for the export company entity.
     @param id the export company id
     @return runtime exception
     */
    private RuntimeException createExportCompanyNotFoundException(UUID id) {
        log.warn("ExportCompany not found with id: {}", id);

        return GeneratedRuntimeException.builder()
                .code(ErrorCodes.NOT_FOUND)
                .entity("ExportCompany")
                .message("ExportCompany not found with id: " + id)
                .build();
    }

}
