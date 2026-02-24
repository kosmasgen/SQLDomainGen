package gr.knowledge.pepTest.serviceImpl;

import gr.knowledge.pepTest.dto.ChamberDepartmenti18nDto;
import gr.knowledge.pepTest.mapper.ChamberDepartmenti18nMapper;
import gr.knowledge.pepTest.entity.ChamberDepartmenti18n;
import gr.knowledge.pepTest.repository.ChamberDepartmenti18nRepository;
import gr.knowledge.pepTest.service.ChamberDepartmenti18nService;
import gr.knowledge.pepTest.entity.ChamberDepartmenti18nPK;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

/**
 * Service implementation for {@code ChamberDepartmenti18n} domain operations.
 */
@Service
@RequiredArgsConstructor
@Transactional
@Log4j2
public class ChamberDepartmenti18nServiceImpl implements ChamberDepartmenti18nService {

    private final ChamberDepartmenti18nRepository chamberDepartmenti18nRepository;
    private final ChamberDepartmenti18nMapper chamberDepartmenti18nMapper;

    /**
     * Retrieves all records.
     *
     * @return non-null list of {@link ChamberDepartmenti18nDto}
     */
    @Override
    public List<ChamberDepartmenti18nDto> getAllChamberDepartmenti18n() {
        log.info("Fetching all chamber-departmenti18n.");
        return chamberDepartmenti18nMapper.toDTO(chamberDepartmenti18nRepository.findAll());
    }

    /**
     * Retrieves a record by id.
     *
     * @param id the record id
     * @return the matching {@link ChamberDepartmenti18nDto}
     */
    @Override
    public ChamberDepartmenti18nDto getChamberDepartmenti18nById(ChamberDepartmenti18nPK id) {
        log.info("Fetching chamber-departmenti18n with id: {}", id);
        ChamberDepartmenti18n entity = chamberDepartmenti18nRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "ChamberDepartmenti18n not found with id: " + id));
        return chamberDepartmenti18nMapper.toDTO(entity);
    }

    /**
     * Creates a new record.
     *
     * @param dto input payload
     * @return created {@link ChamberDepartmenti18nDto}
     */
    @Override
    public ChamberDepartmenti18nDto createChamberDepartmenti18n(ChamberDepartmenti18nDto dto) {
        log.info("Creating chamber-departmenti18n.");
        ChamberDepartmenti18n entity = chamberDepartmenti18nMapper.toEntity(dto);
        ChamberDepartmenti18n saved = chamberDepartmenti18nRepository.save(entity);
        return chamberDepartmenti18nMapper.toDTO(saved);
    }

    /**
     * Updates an existing record.
     *
     * Note: current implementation performs a full update (PUT-style).
     * PATCH behavior (merge non-null fields) can be added via ModelMapper config.
     *
     * @param id  the record id
     * @param dto input payload
     * @return updated {@link ChamberDepartmenti18nDto}
     */
    @Override
    public ChamberDepartmenti18nDto updateChamberDepartmenti18n(ChamberDepartmenti18nPK id, ChamberDepartmenti18nDto dto) {
        log.info("Updating chamber-departmenti18n with id: {}", id);
        chamberDepartmenti18nRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "ChamberDepartmenti18n not found with id: " + id));
        ChamberDepartmenti18n entity = chamberDepartmenti18nMapper.toEntity(dto);
        entity.setId(id);
        ChamberDepartmenti18n saved = chamberDepartmenti18nRepository.save(entity);
        return chamberDepartmenti18nMapper.toDTO(saved);
    }

    /**
     * Deletes a record by id.
     *
     * @param id the record id
     */
    @Override
    public void deleteChamberDepartmenti18n(ChamberDepartmenti18nPK id) {
        log.info("Deleting chamber-departmenti18n with id: {}", id);
        chamberDepartmenti18nRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "ChamberDepartmenti18n not found with id: " + id));
        chamberDepartmenti18nRepository.deleteById(id);
    }
}
