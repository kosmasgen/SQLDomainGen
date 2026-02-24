package gr.knowledge.pepTest.serviceImpl;

import gr.knowledge.pepTest.dto.TemporaryCompanyDto;
import gr.knowledge.pepTest.mapper.TemporaryCompanyMapper;
import gr.knowledge.pepTest.entity.TemporaryCompany;
import gr.knowledge.pepTest.repository.TemporaryCompanyRepository;
import gr.knowledge.pepTest.service.TemporaryCompanyService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

/**
 * Service implementation for {@code TemporaryCompany} domain operations.
 */
@Service
@RequiredArgsConstructor
@Transactional
@Log4j2
public class TemporaryCompanyServiceImpl implements TemporaryCompanyService {

    private final TemporaryCompanyRepository temporaryCompanyRepository;
    private final TemporaryCompanyMapper temporaryCompanyMapper;

    /**
     * Retrieves all records.
     *
     * @return non-null list of {@link TemporaryCompanyDto}
     */
    @Override
    public List<TemporaryCompanyDto> getAllTemporaryCompany() {
        log.info("Fetching all temporary-company.");
        return temporaryCompanyMapper.toDTO(temporaryCompanyRepository.findAll());
    }

    /**
     * Retrieves a record by id.
     *
     * @param id the record id
     * @return the matching {@link TemporaryCompanyDto}
     */
    @Override
    public TemporaryCompanyDto getTemporaryCompanyById(Long id) {
        log.info("Fetching temporary-company with id: {}", id);
        TemporaryCompany entity = temporaryCompanyRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "TemporaryCompany not found with id: " + id));
        return temporaryCompanyMapper.toDTO(entity);
    }

    /**
     * Creates a new record.
     *
     * @param dto input payload
     * @return created {@link TemporaryCompanyDto}
     */
    @Override
    public TemporaryCompanyDto createTemporaryCompany(TemporaryCompanyDto dto) {
        log.info("Creating temporary-company.");
        TemporaryCompany entity = temporaryCompanyMapper.toEntity(dto);
        TemporaryCompany saved = temporaryCompanyRepository.save(entity);
        return temporaryCompanyMapper.toDTO(saved);
    }

    /**
     * Updates an existing record.
     *
     * Note: current implementation performs a full update (PUT-style).
     * PATCH behavior (merge non-null fields) can be added via ModelMapper config.
     *
     * @param id  the record id
     * @param dto input payload
     * @return updated {@link TemporaryCompanyDto}
     */
    @Override
    public TemporaryCompanyDto updateTemporaryCompany(Long id, TemporaryCompanyDto dto) {
        log.info("Updating temporary-company with id: {}", id);
        temporaryCompanyRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "TemporaryCompany not found with id: " + id));
        TemporaryCompany entity = temporaryCompanyMapper.toEntity(dto);
        entity.setId(id);
        TemporaryCompany saved = temporaryCompanyRepository.save(entity);
        return temporaryCompanyMapper.toDTO(saved);
    }

    /**
     * Deletes a record by id.
     *
     * @param id the record id
     */
    @Override
    public void deleteTemporaryCompany(Long id) {
        log.info("Deleting temporary-company with id: {}", id);
        temporaryCompanyRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "TemporaryCompany not found with id: " + id));
        temporaryCompanyRepository.deleteById(id);
    }
}
