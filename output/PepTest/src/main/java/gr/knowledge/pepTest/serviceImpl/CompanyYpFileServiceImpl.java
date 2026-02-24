package gr.knowledge.pepTest.serviceImpl;

import gr.knowledge.pepTest.dto.CompanyYpFileDto;
import gr.knowledge.pepTest.mapper.CompanyYpFileMapper;
import gr.knowledge.pepTest.entity.CompanyYpFile;
import gr.knowledge.pepTest.repository.CompanyYpFileRepository;
import gr.knowledge.pepTest.service.CompanyYpFileService;
import java.util.UUID;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

/**
 * Service implementation for {@code CompanyYpFile} domain operations.
 */
@Service
@RequiredArgsConstructor
@Transactional
@Log4j2
public class CompanyYpFileServiceImpl implements CompanyYpFileService {

    private final CompanyYpFileRepository companyYpFileRepository;
    private final CompanyYpFileMapper companyYpFileMapper;

    /**
     * Retrieves all records.
     *
     * @return non-null list of {@link CompanyYpFileDto}
     */
    @Override
    public List<CompanyYpFileDto> getAllCompanyYpFile() {
        log.info("Fetching all company-yp-file.");
        return companyYpFileMapper.toDTO(companyYpFileRepository.findAll());
    }

    /**
     * Retrieves a record by id.
     *
     * @param id the record id
     * @return the matching {@link CompanyYpFileDto}
     */
    @Override
    public CompanyYpFileDto getCompanyYpFileById(UUID id) {
        log.info("Fetching company-yp-file with id: {}", id);
        CompanyYpFile entity = companyYpFileRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "CompanyYpFile not found with id: " + id));
        return companyYpFileMapper.toDTO(entity);
    }

    /**
     * Creates a new record.
     *
     * @param dto input payload
     * @return created {@link CompanyYpFileDto}
     */
    @Override
    public CompanyYpFileDto createCompanyYpFile(CompanyYpFileDto dto) {
        log.info("Creating company-yp-file.");
        CompanyYpFile entity = companyYpFileMapper.toEntity(dto);
        CompanyYpFile saved = companyYpFileRepository.save(entity);
        return companyYpFileMapper.toDTO(saved);
    }

    /**
     * Updates an existing record.
     *
     * Note: current implementation performs a full update (PUT-style).
     * PATCH behavior (merge non-null fields) can be added via ModelMapper config.
     *
     * @param id  the record id
     * @param dto input payload
     * @return updated {@link CompanyYpFileDto}
     */
    @Override
    public CompanyYpFileDto updateCompanyYpFile(UUID id, CompanyYpFileDto dto) {
        log.info("Updating company-yp-file with id: {}", id);
        companyYpFileRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "CompanyYpFile not found with id: " + id));
        CompanyYpFile entity = companyYpFileMapper.toEntity(dto);
        entity.setId(id);
        CompanyYpFile saved = companyYpFileRepository.save(entity);
        return companyYpFileMapper.toDTO(saved);
    }

    /**
     * Deletes a record by id.
     *
     * @param id the record id
     */
    @Override
    public void deleteCompanyYpFile(UUID id) {
        log.info("Deleting company-yp-file with id: {}", id);
        companyYpFileRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "CompanyYpFile not found with id: " + id));
        companyYpFileRepository.deleteById(id);
    }
}
