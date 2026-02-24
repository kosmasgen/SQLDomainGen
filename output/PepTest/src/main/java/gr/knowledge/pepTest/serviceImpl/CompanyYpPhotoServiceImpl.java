package gr.knowledge.pepTest.serviceImpl;

import gr.knowledge.pepTest.dto.CompanyYpPhotoDto;
import gr.knowledge.pepTest.mapper.CompanyYpPhotoMapper;
import gr.knowledge.pepTest.entity.CompanyYpPhoto;
import gr.knowledge.pepTest.repository.CompanyYpPhotoRepository;
import gr.knowledge.pepTest.service.CompanyYpPhotoService;
import java.util.UUID;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

/**
 * Service implementation for {@code CompanyYpPhoto} domain operations.
 */
@Service
@RequiredArgsConstructor
@Transactional
@Log4j2
public class CompanyYpPhotoServiceImpl implements CompanyYpPhotoService {

    private final CompanyYpPhotoRepository companyYpPhotoRepository;
    private final CompanyYpPhotoMapper companyYpPhotoMapper;

    /**
     * Retrieves all records.
     *
     * @return non-null list of {@link CompanyYpPhotoDto}
     */
    @Override
    public List<CompanyYpPhotoDto> getAllCompanyYpPhoto() {
        log.info("Fetching all company-yp-photo.");
        return companyYpPhotoMapper.toDTO(companyYpPhotoRepository.findAll());
    }

    /**
     * Retrieves a record by id.
     *
     * @param id the record id
     * @return the matching {@link CompanyYpPhotoDto}
     */
    @Override
    public CompanyYpPhotoDto getCompanyYpPhotoById(UUID id) {
        log.info("Fetching company-yp-photo with id: {}", id);
        CompanyYpPhoto entity = companyYpPhotoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "CompanyYpPhoto not found with id: " + id));
        return companyYpPhotoMapper.toDTO(entity);
    }

    /**
     * Creates a new record.
     *
     * @param dto input payload
     * @return created {@link CompanyYpPhotoDto}
     */
    @Override
    public CompanyYpPhotoDto createCompanyYpPhoto(CompanyYpPhotoDto dto) {
        log.info("Creating company-yp-photo.");
        CompanyYpPhoto entity = companyYpPhotoMapper.toEntity(dto);
        CompanyYpPhoto saved = companyYpPhotoRepository.save(entity);
        return companyYpPhotoMapper.toDTO(saved);
    }

    /**
     * Updates an existing record.
     *
     * Note: current implementation performs a full update (PUT-style).
     * PATCH behavior (merge non-null fields) can be added via ModelMapper config.
     *
     * @param id  the record id
     * @param dto input payload
     * @return updated {@link CompanyYpPhotoDto}
     */
    @Override
    public CompanyYpPhotoDto updateCompanyYpPhoto(UUID id, CompanyYpPhotoDto dto) {
        log.info("Updating company-yp-photo with id: {}", id);
        companyYpPhotoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "CompanyYpPhoto not found with id: " + id));
        CompanyYpPhoto entity = companyYpPhotoMapper.toEntity(dto);
        entity.setId(id);
        CompanyYpPhoto saved = companyYpPhotoRepository.save(entity);
        return companyYpPhotoMapper.toDTO(saved);
    }

    /**
     * Deletes a record by id.
     *
     * @param id the record id
     */
    @Override
    public void deleteCompanyYpPhoto(UUID id) {
        log.info("Deleting company-yp-photo with id: {}", id);
        companyYpPhotoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "CompanyYpPhoto not found with id: " + id));
        companyYpPhotoRepository.deleteById(id);
    }
}
