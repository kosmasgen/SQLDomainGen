package gr.knowledge.pepTest.serviceImpl;

import gr.knowledge.pepTest.dto.CompanyProfessionDto;
import gr.knowledge.pepTest.mapper.CompanyProfessionMapper;
import gr.knowledge.pepTest.entity.CompanyProfession;
import gr.knowledge.pepTest.repository.CompanyProfessionRepository;
import gr.knowledge.pepTest.service.CompanyProfessionService;
import gr.knowledge.pepTest.exception.ErrorCodes;
import gr.knowledge.pepTest.exception.GeneratedRuntimeException;
import java.util.UUID;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * Service implementation for {@code Company Profession} domain operations.
 */
@Service
@RequiredArgsConstructor
@Transactional
@Log4j2
public class CompanyProfessionServiceImpl implements CompanyProfessionService {

    private final CompanyProfessionRepository companyProfessionRepository;
    private final CompanyProfessionMapper companyProfessionMapper;

    /**
     * Retrieves all company professions records.
     * @return list of CompanyProfessionDto
     */
    @Override
    public List<CompanyProfessionDto> getAllCompanyProfessions() {
        log.info("Fetching all company professions records.");
        return companyProfessionMapper.toDTOList(companyProfessionRepository.findAll());
    }

    /**
     * Retrieves a company profession record by id.
     * @param id the company profession id
     * @return CompanyProfessionDto
     */
    @Override
    public CompanyProfessionDto getCompanyProfessionById(UUID id) {
        log.info("Fetching company profession with id: {}", id);

        CompanyProfession existingEntity = findCompanyProfessionByIdOrThrow(id);
        return companyProfessionMapper.toDTO(existingEntity);
    }

    /**
     * Creates a new company profession record.
     * @param dto input payload
     * @return created {@link CompanyProfessionDto}
     */
    @Override
    public CompanyProfessionDto createCompanyProfession(CompanyProfessionDto dto) {
        log.info("Creating company profession.");

        validateCompanyProfessionCreateUniqueConstraints(dto);

        CompanyProfession entity = companyProfessionMapper.toEntity(dto);
        CompanyProfession savedEntity = companyProfessionRepository.save(entity);

        return companyProfessionMapper.toDTO(savedEntity);
    }

    /**
     * Updates an existing company profession record.
     *
     * @param id the company profession id
     * @param dto input payload
     * @return updated {@link CompanyProfessionDto}
     */
    @Override
    public CompanyProfessionDto updateCompanyProfession(UUID id, CompanyProfessionDto dto) {
        log.info("Updating company profession with id: {}", id);

        CompanyProfession existingEntity = findCompanyProfessionByIdOrThrow(id);
        companyProfessionMapper.partialUpdate(existingEntity, dto);
        CompanyProfession savedEntity = companyProfessionRepository.save(existingEntity);

        return companyProfessionMapper.toDTO(savedEntity);
    }

    /**
     * Delete a company profession record by id.
     * @param id the company profession id
     */
    @Override
    public void deleteCompanyProfession(UUID id) {
        log.info("Deleting company profession with id: {}", id);

        findCompanyProfessionByIdOrThrow(id);
        companyProfessionRepository.deleteById(id);
    }

    /**
     * Validates unique constraints for create operations.
     * @param dto input payload
     */
    private void validateCompanyProfessionCreateUniqueConstraints(CompanyProfessionDto dto) {

        if (dto == null) {
            return;
        }

        if (dto.getChamberId() != null && dto.getChamberCompanyProfessionId() != null && companyProfessionRepository.existsByChamberIdAndChamberCompanyProfessionId(dto.getChamberId(), dto.getChamberCompanyProfessionId())) {
            throw GeneratedRuntimeException.builder()
                    .code(ErrorCodes.BAD_REQUEST)
                    .entity("CompanyProfession")
                    .message("CompanyProfession already exists with " + "chamberId=" + dto.getChamberId() + ", " + "chamberCompanyProfessionId=" + dto.getChamberCompanyProfessionId())
                    .build();
        }
    }

    /**
     * Finds an existing company profession record by id or throws an exception.
     * @param id the company profession id
     * @return existing CompanyProfession entity
     */
    private CompanyProfession findCompanyProfessionByIdOrThrow(UUID id) {
        return companyProfessionRepository.findById(id)
                .orElseThrow(() -> createCompanyProfessionNotFoundException(id));
    }

    /**
     Creates a NOT FOUND exception for the company profession entity.
     @param id the company profession id
     @return runtime exception
     */
    private RuntimeException createCompanyProfessionNotFoundException(UUID id) {
        log.warn("CompanyProfession not found with id: {}", id);

        return GeneratedRuntimeException.builder()
                .code(ErrorCodes.NOT_FOUND)
                .entity("CompanyProfession")
                .message("CompanyProfession not found with id: " + id)
                .build();
    }

}
