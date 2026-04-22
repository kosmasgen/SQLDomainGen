package gr.knowledge.pepTest.serviceImpl;

import gr.knowledge.pepTest.dto.CompanyDto;
import gr.knowledge.pepTest.mapper.CompanyMapper;
import gr.knowledge.pepTest.entity.Company;
import gr.knowledge.pepTest.repository.CompanyRepository;
import gr.knowledge.pepTest.service.CompanyService;
import gr.knowledge.pepTest.exception.ErrorCodes;
import gr.knowledge.pepTest.exception.GeneratedRuntimeException;
import java.util.UUID;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * Service implementation for {@code Company} domain operations.
 */
@Service
@RequiredArgsConstructor
@Transactional
@Log4j2
public class CompanyServiceImpl implements CompanyService {

    private final CompanyRepository companyRepository;
    private final CompanyMapper companyMapper;

    /**
     * Retrieves all companies records.
     * @return list of CompanyDto
     */
    @Override
    public List<CompanyDto> getAllCompanies() {
        log.info("Fetching all companies records.");
        return companyMapper.toDTOList(companyRepository.findAll());
    }

    /**
     * Retrieves a company record by id.
     * @param id the company id
     * @return CompanyDto
     */
    @Override
    public CompanyDto getCompanyById(UUID id) {
        log.info("Fetching company with id: {}", id);

        Company existingEntity = findCompanyByIdOrThrow(id);
        return companyMapper.toDTO(existingEntity);
    }

    /**
     * Creates a new company record.
     * @param dto input payload
     * @return created {@link CompanyDto}
     */
    @Override
    public CompanyDto createCompany(CompanyDto dto) {
        log.info("Creating company.");

        validateCompanyCreateUniqueConstraints(dto);

        Company entity = companyMapper.toEntity(dto);
        Company savedEntity = companyRepository.save(entity);

        return companyMapper.toDTO(savedEntity);
    }

    /**
     * Updates an existing company record.
     *
     * @param id the company id
     * @param dto input payload
     * @return updated {@link CompanyDto}
     */
    @Override
    public CompanyDto updateCompany(UUID id, CompanyDto dto) {
        log.info("Updating company with id: {}", id);

        Company existingEntity = findCompanyByIdOrThrow(id);
        companyMapper.partialUpdate(existingEntity, dto);
        Company savedEntity = companyRepository.save(existingEntity);

        return companyMapper.toDTO(savedEntity);
    }

    /**
     * Delete a company record by id.
     * @param id the company id
     */
    @Override
    public void deleteCompany(UUID id) {
        log.info("Deleting company with id: {}", id);

        findCompanyByIdOrThrow(id);
        companyRepository.deleteById(id);
    }

    /**
     * Validates unique constraints for create operations.
     * @param dto input payload
     */
    private void validateCompanyCreateUniqueConstraints(CompanyDto dto) {

        if (dto == null) {
            return;
        }

        if (dto.getChamberId() != null && dto.getChamberCompanyId() != null && companyRepository.existsByChamberIdAndChamberCompanyId(dto.getChamberId(), dto.getChamberCompanyId())) {
            throw GeneratedRuntimeException.builder()
                    .code(ErrorCodes.BAD_REQUEST)
                    .entity("Company")
                    .message("Company already exists with " + "chamberId=" + dto.getChamberId() + ", " + "chamberCompanyId=" + dto.getChamberCompanyId())
                    .build();
        }
    }

    /**
     * Finds an existing company record by id or throws an exception.
     * @param id the company id
     * @return existing Company entity
     */
    private Company findCompanyByIdOrThrow(UUID id) {
        return companyRepository.findById(id)
                .orElseThrow(() -> createCompanyNotFoundException(id));
    }

    /**
     Creates a NOT FOUND exception for the company entity.
     @param id the company id
     @return runtime exception
     */
    private RuntimeException createCompanyNotFoundException(UUID id) {
        log.warn("Company not found with id: {}", id);

        return GeneratedRuntimeException.builder()
                .code(ErrorCodes.NOT_FOUND)
                .entity("Company")
                .message("Company not found with id: " + id)
                .build();
    }

}
