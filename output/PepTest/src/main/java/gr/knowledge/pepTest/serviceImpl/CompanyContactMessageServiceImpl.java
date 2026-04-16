package gr.knowledge.pepTest.serviceImpl;

import gr.knowledge.pepTest.dto.CompanyContactMessageDto;
import gr.knowledge.pepTest.mapper.CompanyContactMessageMapper;
import gr.knowledge.pepTest.entity.CompanyContactMessage;
import gr.knowledge.pepTest.repository.CompanyContactMessageRepository;
import gr.knowledge.pepTest.service.CompanyContactMessageService;
import gr.knowledge.pepTest.exception.ErrorCodes;
import gr.knowledge.pepTest.exception.GeneratedRuntimeException;
import java.util.UUID;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * Service implementation for {@code Company Contact Message} domain operations.
 */
@Service
@RequiredArgsConstructor
@Transactional
@Log4j2
public class CompanyContactMessageServiceImpl implements CompanyContactMessageService {

    private final CompanyContactMessageRepository companyContactMessageRepository;
    private final CompanyContactMessageMapper companyContactMessageMapper;

    /**
     * Retrieves all company contact messages records.
     * @return list of CompanyContactMessageDto
     */
    @Override
    public List<CompanyContactMessageDto> getAllCompanyContactMessages() {
        log.info("Fetching all company contact messages records.");
        return companyContactMessageMapper.toDTOList(companyContactMessageRepository.findAll());
    }

    /**
     * Retrieves a company contact message record by id.
     * @param id the company contact message id
     * @return CompanyContactMessageDto
     */
    @Override
    public CompanyContactMessageDto getCompanyContactMessageById(UUID id) {
        log.info("Fetching company contact message with id: {}", id);

        CompanyContactMessage existingEntity = findCompanyContactMessageByIdOrThrow(id);
        return companyContactMessageMapper.toDTO(existingEntity);
    }

    /**
     * Creates a new company contact message record.
     * @param dto input payload
     * @return created {@link CompanyContactMessageDto}
     */
    @Override
    public CompanyContactMessageDto createCompanyContactMessage(CompanyContactMessageDto dto) {
        log.info("Creating company contact message.");

        CompanyContactMessage entity = companyContactMessageMapper.toEntity(dto);
        CompanyContactMessage savedEntity = companyContactMessageRepository.save(entity);

        return companyContactMessageMapper.toDTO(savedEntity);
    }

    /**
     * Updates an existing company contact message record.
     * <p>
     * Only non null fields from the DTO are applied to the existing entity.
     * @param id the company contact message id
     * @param dto input payload with partial fields
     * @return updated {@link CompanyContactMessageDto}
     */
    @Override
    public CompanyContactMessageDto updateCompanyContactMessage(UUID id, CompanyContactMessageDto dto) {
        log.info("Updating company contact message with id: {}", id);

        CompanyContactMessage existingEntity = findCompanyContactMessageByIdOrThrow(id);
        companyContactMessageMapper.partialUpdate(existingEntity, dto);
        CompanyContactMessage savedEntity = companyContactMessageRepository.save(existingEntity);

        return companyContactMessageMapper.toDTO(savedEntity);
    }

    /**
     * Delete a company contact message record by id.
     * @param id the company contact message id
     */
    @Override
    public void deleteCompanyContactMessage(UUID id) {
        log.info("Deleting company contact message with id: {}", id);

        findCompanyContactMessageByIdOrThrow(id);
        companyContactMessageRepository.deleteById(id);
    }

    /**
     * Finds an existing company contact message record by id or throws an exception.
     * @param id the company contact message id
     * @return existing CompanyContactMessage entity
     */
    private CompanyContactMessage findCompanyContactMessageByIdOrThrow(UUID id) {
        return companyContactMessageRepository.findById(id)
                .orElseThrow(() -> createCompanyContactMessageNotFoundException(id));
    }

    /**
     Creates a NOT FOUND exception for the company contact message entity.
     @param id the company contact message id
     @return runtime exception
     */
    private RuntimeException createCompanyContactMessageNotFoundException(UUID id) {
        log.warn("CompanyContactMessage not found with id: {}", id);

        return GeneratedRuntimeException.builder()
                .code(ErrorCodes.NOT_FOUND)
                .entity("CompanyContactMessage")
                .message("CompanyContactMessage not found with id: " + id)
                .build();
    }

}
