package gr.knowledge.pepTest.serviceImpl;

import gr.knowledge.pepTest.dto.MunicipalityDto;
import gr.knowledge.pepTest.mapper.MunicipalityMapper;
import gr.knowledge.pepTest.entity.Municipality;
import gr.knowledge.pepTest.repository.MunicipalityRepository;
import gr.knowledge.pepTest.service.MunicipalityService;
import java.util.UUID;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

/**
 * Service implementation for {@code Municipality} domain operations.
 */
@Service
@RequiredArgsConstructor
@Transactional
@Log4j2
public class MunicipalityServiceImpl implements MunicipalityService {

    private final MunicipalityRepository municipalityRepository;
    private final MunicipalityMapper municipalityMapper;

    /**
     * Retrieves all records.
     *
     * @return non-null list of {@link MunicipalityDto}
     */
    @Override
    public List<MunicipalityDto> getAllMunicipality() {
        log.info("Fetching all municipality.");
        return municipalityMapper.toDTO(municipalityRepository.findAll());
    }

    /**
     * Retrieves a record by id.
     *
     * @param id the record id
     * @return the matching {@link MunicipalityDto}
     */
    @Override
    public MunicipalityDto getMunicipalityById(UUID id) {
        log.info("Fetching municipality with id: {}", id);
        Municipality entity = municipalityRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Municipality not found with id: " + id));
        return municipalityMapper.toDTO(entity);
    }

    /**
     * Creates a new record.
     *
     * @param dto input payload
     * @return created {@link MunicipalityDto}
     */
    @Override
    public MunicipalityDto createMunicipality(MunicipalityDto dto) {
        log.info("Creating municipality.");
        Municipality entity = municipalityMapper.toEntity(dto);
        Municipality saved = municipalityRepository.save(entity);
        return municipalityMapper.toDTO(saved);
    }

    /**
     * Updates an existing record.
     *
     * Note: current implementation performs a full update (PUT-style).
     * PATCH behavior (merge non-null fields) can be added via ModelMapper config.
     *
     * @param id  the record id
     * @param dto input payload
     * @return updated {@link MunicipalityDto}
     */
    @Override
    public MunicipalityDto updateMunicipality(UUID id, MunicipalityDto dto) {
        log.info("Updating municipality with id: {}", id);
        municipalityRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Municipality not found with id: " + id));
        Municipality entity = municipalityMapper.toEntity(dto);
        entity.setId(id);
        Municipality saved = municipalityRepository.save(entity);
        return municipalityMapper.toDTO(saved);
    }

    /**
     * Deletes a record by id.
     *
     * @param id the record id
     */
    @Override
    public void deleteMunicipality(UUID id) {
        log.info("Deleting municipality with id: {}", id);
        municipalityRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Municipality not found with id: " + id));
        municipalityRepository.deleteById(id);
    }
}
