package gr.knowledge.pepTest.serviceImpl;

import gr.knowledge.pepTest.dto.MunicipalityI18nDto;
import gr.knowledge.pepTest.mapper.MunicipalityI18nMapper;
import gr.knowledge.pepTest.entity.MunicipalityI18n;
import gr.knowledge.pepTest.repository.MunicipalityI18nRepository;
import gr.knowledge.pepTest.service.MunicipalityI18nService;
import gr.knowledge.pepTest.entity.MunicipalityI18nPK;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

/**
 * Service implementation for {@code MunicipalityI18n} domain operations.
 */
@Service
@RequiredArgsConstructor
@Transactional
@Log4j2
public class MunicipalityI18nServiceImpl implements MunicipalityI18nService {

    private final MunicipalityI18nRepository municipalityI18nRepository;
    private final MunicipalityI18nMapper municipalityI18nMapper;

    /**
     * Retrieves all records.
     *
     * @return non-null list of {@link MunicipalityI18nDto}
     */
    @Override
    public List<MunicipalityI18nDto> getAllMunicipalityI18n() {
        log.info("Fetching all municipality-i18n.");
        return municipalityI18nMapper.toDTO(municipalityI18nRepository.findAll());
    }

    /**
     * Retrieves a record by id.
     *
     * @param id the record id
     * @return the matching {@link MunicipalityI18nDto}
     */
    @Override
    public MunicipalityI18nDto getMunicipalityI18nById(MunicipalityI18nPK id) {
        log.info("Fetching municipality-i18n with id: {}", id);
        MunicipalityI18n entity = municipalityI18nRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "MunicipalityI18n not found with id: " + id));
        return municipalityI18nMapper.toDTO(entity);
    }

    /**
     * Creates a new record.
     *
     * @param dto input payload
     * @return created {@link MunicipalityI18nDto}
     */
    @Override
    public MunicipalityI18nDto createMunicipalityI18n(MunicipalityI18nDto dto) {
        log.info("Creating municipality-i18n.");
        MunicipalityI18n entity = municipalityI18nMapper.toEntity(dto);
        MunicipalityI18n saved = municipalityI18nRepository.save(entity);
        return municipalityI18nMapper.toDTO(saved);
    }

    /**
     * Updates an existing record.
     *
     * Note: current implementation performs a full update (PUT-style).
     * PATCH behavior (merge non-null fields) can be added via ModelMapper config.
     *
     * @param id  the record id
     * @param dto input payload
     * @return updated {@link MunicipalityI18nDto}
     */
    @Override
    public MunicipalityI18nDto updateMunicipalityI18n(MunicipalityI18nPK id, MunicipalityI18nDto dto) {
        log.info("Updating municipality-i18n with id: {}", id);
        municipalityI18nRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "MunicipalityI18n not found with id: " + id));
        MunicipalityI18n entity = municipalityI18nMapper.toEntity(dto);
        entity.setId(id);
        MunicipalityI18n saved = municipalityI18nRepository.save(entity);
        return municipalityI18nMapper.toDTO(saved);
    }

    /**
     * Deletes a record by id.
     *
     * @param id the record id
     */
    @Override
    public void deleteMunicipalityI18n(MunicipalityI18nPK id) {
        log.info("Deleting municipality-i18n with id: {}", id);
        municipalityI18nRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "MunicipalityI18n not found with id: " + id));
        municipalityI18nRepository.deleteById(id);
    }
}
