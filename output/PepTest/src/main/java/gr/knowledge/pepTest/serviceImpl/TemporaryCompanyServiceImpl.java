package gr.knowledge.pepTest.serviceImpl;

import gr.knowledge.pepTest.dto.TemporaryCompanyDto;
import gr.knowledge.pepTest.mapper.TemporaryCompanyMapper;
import gr.knowledge.pepTest.entity.TemporaryCompany;
import gr.knowledge.pepTest.repository.TemporaryCompanyRepository;
import gr.knowledge.pepTest.service.TemporaryCompanyService;
import gr.knowledge.pepTest.exception.ErrorCodes;
import gr.knowledge.pepTest.exception.GeneratedRuntimeException;
import java.math.BigInteger;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * Service implementation for {@code Temporary Company} domain operations.
 */
@Service
@RequiredArgsConstructor
@Transactional
@Log4j2
public class TemporaryCompanyServiceImpl implements TemporaryCompanyService {

    private final TemporaryCompanyRepository temporaryCompanyRepository;
    private final TemporaryCompanyMapper temporaryCompanyMapper;

    /**
     * Retrieves all temporary companies records.
     * @return list of TemporaryCompanyDto
     */
    @Override
    public List<TemporaryCompanyDto> getAllTemporaryCompanies() {
        log.info("Fetching all temporary companies records.");
        return temporaryCompanyMapper.toDTOList(temporaryCompanyRepository.findAll());
    }

    /**
     * Retrieves a temporary company record by id.
     * @param id the temporary company id
     * @return TemporaryCompanyDto
     */
    @Override
    public TemporaryCompanyDto getTemporaryCompanyById(BigInteger id) {
        log.info("Fetching temporary company with id: {}", id);

        TemporaryCompany existingEntity = findTemporaryCompanyByIdOrThrow(id);
        return temporaryCompanyMapper.toDTO(existingEntity);
    }

    /**
     * Creates a new temporary company record.
     * @param dto input payload
     * @return created {@link TemporaryCompanyDto}
     */
    @Override
    public TemporaryCompanyDto createTemporaryCompany(TemporaryCompanyDto dto) {
        log.info("Creating temporary company.");

        TemporaryCompany entity = temporaryCompanyMapper.toEntity(dto);
        TemporaryCompany savedEntity = temporaryCompanyRepository.save(entity);

        return temporaryCompanyMapper.toDTO(savedEntity);
    }

    /**
     * Updates an existing temporary company record.
     * <p>
     * Only non null fields from the DTO are applied to the existing entity.
     * @param id the temporary company id
     * @param dto input payload with partial fields
     * @return updated {@link TemporaryCompanyDto}
     */
    @Override
    public TemporaryCompanyDto updateTemporaryCompany(BigInteger id, TemporaryCompanyDto dto) {
        log.info("Updating temporary company with id: {}", id);

        TemporaryCompany existingEntity = findTemporaryCompanyByIdOrThrow(id);
        temporaryCompanyMapper.partialUpdate(existingEntity, dto);
        TemporaryCompany savedEntity = temporaryCompanyRepository.save(existingEntity);

        return temporaryCompanyMapper.toDTO(savedEntity);
    }

    /**
     * Delete a temporary company record by id.
     * @param id the temporary company id
     */
    @Override
    public void deleteTemporaryCompany(BigInteger id) {
        log.info("Deleting temporary company with id: {}", id);

        findTemporaryCompanyByIdOrThrow(id);
        temporaryCompanyRepository.deleteById(id);
    }

    /**
     * Finds an existing temporary company record by id or throws an exception.
     * @param id the temporary company id
     * @return existing TemporaryCompany entity
     */
    private TemporaryCompany findTemporaryCompanyByIdOrThrow(BigInteger id) {
        return temporaryCompanyRepository.findById(id)
                .orElseThrow(() -> createTemporaryCompanyNotFoundException(id));
    }

    /**
     Creates a NOT FOUND exception for the temporary company entity.
     @param id the temporary company id
     @return runtime exception
     */
    private RuntimeException createTemporaryCompanyNotFoundException(BigInteger id) {
        log.warn("TemporaryCompany not found with id: {}", id);

        return GeneratedRuntimeException.builder()
                .code(ErrorCodes.NOT_FOUND)
                .entity("TemporaryCompany")
                .message("TemporaryCompany not found with id: " + id)
                .build();
    }

}
