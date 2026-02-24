package gr.knowledge.pepTest.serviceImpl;

import gr.knowledge.pepTest.dto.CompanyArticleFileDto;
import gr.knowledge.pepTest.mapper.CompanyArticleFileMapper;
import gr.knowledge.pepTest.entity.CompanyArticleFile;
import gr.knowledge.pepTest.repository.CompanyArticleFileRepository;
import gr.knowledge.pepTest.service.CompanyArticleFileService;
import java.util.UUID;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

/**
 * Service implementation for {@code CompanyArticleFile} domain operations.
 */
@Service
@RequiredArgsConstructor
@Transactional
@Log4j2
public class CompanyArticleFileServiceImpl implements CompanyArticleFileService {

    private final CompanyArticleFileRepository companyArticleFileRepository;
    private final CompanyArticleFileMapper companyArticleFileMapper;

    /**
     * Retrieves all records.
     *
     * @return non-null list of {@link CompanyArticleFileDto}
     */
    @Override
    public List<CompanyArticleFileDto> getAllCompanyArticleFile() {
        log.info("Fetching all company-article-file.");
        return companyArticleFileMapper.toDTO(companyArticleFileRepository.findAll());
    }

    /**
     * Retrieves a record by id.
     *
     * @param id the record id
     * @return the matching {@link CompanyArticleFileDto}
     */
    @Override
    public CompanyArticleFileDto getCompanyArticleFileById(UUID id) {
        log.info("Fetching company-article-file with id: {}", id);
        CompanyArticleFile entity = companyArticleFileRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "CompanyArticleFile not found with id: " + id));
        return companyArticleFileMapper.toDTO(entity);
    }

    /**
     * Creates a new record.
     *
     * @param dto input payload
     * @return created {@link CompanyArticleFileDto}
     */
    @Override
    public CompanyArticleFileDto createCompanyArticleFile(CompanyArticleFileDto dto) {
        log.info("Creating company-article-file.");
        CompanyArticleFile entity = companyArticleFileMapper.toEntity(dto);
        CompanyArticleFile saved = companyArticleFileRepository.save(entity);
        return companyArticleFileMapper.toDTO(saved);
    }

    /**
     * Updates an existing record.
     *
     * Note: current implementation performs a full update (PUT-style).
     * PATCH behavior (merge non-null fields) can be added via ModelMapper config.
     *
     * @param id  the record id
     * @param dto input payload
     * @return updated {@link CompanyArticleFileDto}
     */
    @Override
    public CompanyArticleFileDto updateCompanyArticleFile(UUID id, CompanyArticleFileDto dto) {
        log.info("Updating company-article-file with id: {}", id);
        companyArticleFileRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "CompanyArticleFile not found with id: " + id));
        CompanyArticleFile entity = companyArticleFileMapper.toEntity(dto);
        entity.setId(id);
        CompanyArticleFile saved = companyArticleFileRepository.save(entity);
        return companyArticleFileMapper.toDTO(saved);
    }

    /**
     * Deletes a record by id.
     *
     * @param id the record id
     */
    @Override
    public void deleteCompanyArticleFile(UUID id) {
        log.info("Deleting company-article-file with id: {}", id);
        companyArticleFileRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "CompanyArticleFile not found with id: " + id));
        companyArticleFileRepository.deleteById(id);
    }
}
