package gr.knowledge.pepTest.serviceImpl;

import gr.knowledge.pepTest.dto.CompanyYpPhotoDto;
import gr.knowledge.pepTest.mapper.CompanyYpPhotoMapper;
import gr.knowledge.pepTest.entity.CompanyYpPhoto;
import gr.knowledge.pepTest.repository.CompanyYpPhotoRepository;
import gr.knowledge.pepTest.service.CompanyYpPhotoService;
import gr.knowledge.pepTest.exception.ErrorCodes;
import gr.knowledge.pepTest.exception.GeneratedRuntimeException;
import java.util.UUID;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * Service implementation for {@code Company Yp Photo} domain operations.
 */
@Service
@RequiredArgsConstructor
@Transactional
@Log4j2
public class CompanyYpPhotoServiceImpl implements CompanyYpPhotoService {

    private final CompanyYpPhotoRepository companyYpPhotoRepository;
    private final CompanyYpPhotoMapper companyYpPhotoMapper;

    /**
     * Retrieves all company yp photos records.
     * @return list of CompanyYpPhotoDto
     */
    @Override
    public List<CompanyYpPhotoDto> getAllCompanyYpPhotos() {
        log.info("Fetching all company yp photos records.");
        return companyYpPhotoMapper.toDTOList(companyYpPhotoRepository.findAll());
    }

    /**
     * Retrieves a company yp photo record by id.
     * @param id the company yp photo id
     * @return CompanyYpPhotoDto
     */
    @Override
    public CompanyYpPhotoDto getCompanyYpPhotoById(UUID id) {
        log.info("Fetching company yp photo with id: {}", id);

        CompanyYpPhoto existingEntity = findCompanyYpPhotoByIdOrThrow(id);
        return companyYpPhotoMapper.toDTO(existingEntity);
    }

    /**
     * Creates a new company yp photo record.
     * @param dto input payload
     * @return created {@link CompanyYpPhotoDto}
     */
    @Override
    public CompanyYpPhotoDto createCompanyYpPhoto(CompanyYpPhotoDto dto) {
        log.info("Creating company yp photo.");

        validateCompanyYpPhotoCreateUniqueConstraints(dto);

        CompanyYpPhoto entity = companyYpPhotoMapper.toEntity(dto);
        CompanyYpPhoto savedEntity = companyYpPhotoRepository.save(entity);

        return companyYpPhotoMapper.toDTO(savedEntity);
    }

    /**
     * Updates an existing company yp photo record.
     * <p>
     * Only non null fields from the DTO are applied to the existing entity.
     * @param id the company yp photo id
     * @param dto input payload with partial fields
     * @return updated {@link CompanyYpPhotoDto}
     */
    @Override
    public CompanyYpPhotoDto updateCompanyYpPhoto(UUID id, CompanyYpPhotoDto dto) {
        log.info("Updating company yp photo with id: {}", id);

        CompanyYpPhoto existingEntity = findCompanyYpPhotoByIdOrThrow(id);
        companyYpPhotoMapper.partialUpdate(existingEntity, dto);
        CompanyYpPhoto savedEntity = companyYpPhotoRepository.save(existingEntity);

        return companyYpPhotoMapper.toDTO(savedEntity);
    }

    /**
     * Delete a company yp photo record by id.
     * @param id the company yp photo id
     */
    @Override
    public void deleteCompanyYpPhoto(UUID id) {
        log.info("Deleting company yp photo with id: {}", id);

        findCompanyYpPhotoByIdOrThrow(id);
        companyYpPhotoRepository.deleteById(id);
    }

    /**
     * Validates unique constraints for create operations.
     * @param dto input payload
     */
    private void validateCompanyYpPhotoCreateUniqueConstraints(CompanyYpPhotoDto dto) {

        if (dto == null) {
            return;
        }

        if ((dto.getCompany() != null ? dto.getCompany().getId() : null) != null && dto.getFileName() != null && companyYpPhotoRepository.existsByCompanyIdAndFileName((dto.getCompany() != null ? dto.getCompany().getId() : null), dto.getFileName())) {
            throw GeneratedRuntimeException.builder()
                    .code(ErrorCodes.BAD_REQUEST)
                    .entity("CompanyYpPhoto")
                    .message("CompanyYpPhoto already exists with " + "companyId=" + (dto.getCompany() != null ? dto.getCompany().getId() : null) + ", " + "fileName=" + dto.getFileName())
                    .build();
        }
    }

    /**
     * Finds an existing company yp photo record by id or throws an exception.
     * @param id the company yp photo id
     * @return existing CompanyYpPhoto entity
     */
    private CompanyYpPhoto findCompanyYpPhotoByIdOrThrow(UUID id) {
        return companyYpPhotoRepository.findById(id)
                .orElseThrow(() -> createCompanyYpPhotoNotFoundException(id));
    }

    /**
     Creates a NOT FOUND exception for the company yp photo entity.
     @param id the company yp photo id
     @return runtime exception
     */
    private RuntimeException createCompanyYpPhotoNotFoundException(UUID id) {
        log.warn("CompanyYpPhoto not found with id: {}", id);

        return GeneratedRuntimeException.builder()
                .code(ErrorCodes.NOT_FOUND)
                .entity("CompanyYpPhoto")
                .message("CompanyYpPhoto not found with id: " + id)
                .build();
    }

}
