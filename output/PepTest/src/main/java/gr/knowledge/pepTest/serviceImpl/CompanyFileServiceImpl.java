package gr.knowledge.pepTest.serviceImpl;

import gr.knowledge.pepTest.dto.CompanyFileDto;
import gr.knowledge.pepTest.mapper.CompanyFileMapper;
import gr.knowledge.pepTest.entity.CompanyFile;
import gr.knowledge.pepTest.repository.CompanyFileRepository;
import gr.knowledge.pepTest.service.CompanyFileService;
import java.util.UUID;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

/**
 * Service implementation for {@code CompanyFile} domain operations.
 */
@Service
@RequiredArgsConstructor
@Transactional
@Log4j2
public class CompanyFileServiceImpl implements CompanyFileService {

    private final CompanyFileRepository companyFileRepository;
    private final CompanyFileMapper companyFileMapper;

    /**
     * Retrieves all records.
     *
     * @return non-null list of {@link CompanyFileDto}
     */
    @Override
    public List<CompanyFileDto> getAllCompanyFile() {
        log.info("Fetching all company-file.");
        return companyFileMapper.toDTO(companyFileRepository.findAll());
    }

    /**
     * Retrieves a record by id.
     *
     * @param id the record id
     * @return the matching {@link CompanyFileDto}
     */
    @Override
    public CompanyFileDto getCompanyFileById(UUID id) {
        log.info("Fetching company-file with id: {}", id);
        CompanyFile entity = companyFileRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "CompanyFile not found with id: " + id));
        return companyFileMapper.toDTO(entity);
    }

    /**
     * Creates a new record.
     *
     * @param dto input payload
     * @return created {@link CompanyFileDto}
     */
    @Override
    public CompanyFileDto createCompanyFile(CompanyFileDto dto) {
        log.info("Creating company-file.");
        CompanyFile entity = companyFileMapper.toEntity(dto);
        CompanyFile saved = companyFileRepository.save(entity);
        return companyFileMapper.toDTO(saved);
    }

    /**
     * Updates an existing record.
     *
     * Note: current implementation performs a full update (PUT-style).
     * PATCH behavior (merge non-null fields) can be added via ModelMapper config.
     *
     * @param id  the record id
     * @param dto input payload
     * @return updated {@link CompanyFileDto}
     */
    @Override
    public CompanyFileDto updateCompanyFile(UUID id, CompanyFileDto dto) {
        log.info("Updating company-file with id: {}", id);
        companyFileRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "CompanyFile not found with id: " + id));
        CompanyFile entity = companyFileMapper.toEntity(dto);
        entity.setId(id);
        CompanyFile saved = companyFileRepository.save(entity);
        return companyFileMapper.toDTO(saved);
    }

    /**
     * Deletes a record by id.
     *
     * @param id the record id
     */
    @Override
    public void deleteCompanyFile(UUID id) {
        log.info("Deleting company-file with id: {}", id);
        companyFileRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "CompanyFile not found with id: " + id));
        companyFileRepository.deleteById(id);
    }
}
