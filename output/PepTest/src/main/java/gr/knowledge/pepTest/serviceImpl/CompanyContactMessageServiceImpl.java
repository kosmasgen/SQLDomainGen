package gr.knowledge.pepTest.serviceImpl;

import gr.knowledge.pepTest.dto.CompanyContactMessageDto;
import gr.knowledge.pepTest.mapper.CompanyContactMessageMapper;
import gr.knowledge.pepTest.entity.CompanyContactMessage;
import gr.knowledge.pepTest.repository.CompanyContactMessageRepository;
import gr.knowledge.pepTest.service.CompanyContactMessageService;
import java.util.UUID;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

/**
 * Service implementation for {@code CompanyContactMessage} domain operations.
 */
@Service
@RequiredArgsConstructor
@Transactional
@Log4j2
public class CompanyContactMessageServiceImpl implements CompanyContactMessageService {

    private final CompanyContactMessageRepository companyContactMessageRepository;
    private final CompanyContactMessageMapper companyContactMessageMapper;

    /**
     * Retrieves all records.
     *
     * @return non-null list of {@link CompanyContactMessageDto}
     */
    @Override
    public List<CompanyContactMessageDto> getAllCompanyContactMessage() {
        log.info("Fetching all company-contact-message.");
        return companyContactMessageMapper.toDTO(companyContactMessageRepository.findAll());
    }

    /**
     * Retrieves a record by id.
     *
     * @param id the record id
     * @return the matching {@link CompanyContactMessageDto}
     */
    @Override
    public CompanyContactMessageDto getCompanyContactMessageById(UUID id) {
        log.info("Fetching company-contact-message with id: {}", id);
        CompanyContactMessage entity = companyContactMessageRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "CompanyContactMessage not found with id: " + id));
        return companyContactMessageMapper.toDTO(entity);
    }

    /**
     * Creates a new record.
     *
     * @param dto input payload
     * @return created {@link CompanyContactMessageDto}
     */
    @Override
    public CompanyContactMessageDto createCompanyContactMessage(CompanyContactMessageDto dto) {
        log.info("Creating company-contact-message.");
        CompanyContactMessage entity = companyContactMessageMapper.toEntity(dto);
        CompanyContactMessage saved = companyContactMessageRepository.save(entity);
        return companyContactMessageMapper.toDTO(saved);
    }

    /**
     * Updates an existing record.
     *
     * Note: current implementation performs a full update (PUT-style).
     * PATCH behavior (merge non-null fields) can be added via ModelMapper config.
     *
     * @param id  the record id
     * @param dto input payload
     * @return updated {@link CompanyContactMessageDto}
     */
    @Override
    public CompanyContactMessageDto updateCompanyContactMessage(UUID id, CompanyContactMessageDto dto) {
        log.info("Updating company-contact-message with id: {}", id);
        companyContactMessageRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "CompanyContactMessage not found with id: " + id));
        CompanyContactMessage entity = companyContactMessageMapper.toEntity(dto);
        entity.setId(id);
        CompanyContactMessage saved = companyContactMessageRepository.save(entity);
        return companyContactMessageMapper.toDTO(saved);
    }

    /**
     * Deletes a record by id.
     *
     * @param id the record id
     */
    @Override
    public void deleteCompanyContactMessage(UUID id) {
        log.info("Deleting company-contact-message with id: {}", id);
        companyContactMessageRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "CompanyContactMessage not found with id: " + id));
        companyContactMessageRepository.deleteById(id);
    }
}
