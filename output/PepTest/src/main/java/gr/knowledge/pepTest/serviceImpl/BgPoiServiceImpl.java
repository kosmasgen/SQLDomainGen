package gr.knowledge.pepTest.serviceImpl;

import gr.knowledge.pepTest.dto.BgPoiDto;
import gr.knowledge.pepTest.mapper.BgPoiMapper;
import gr.knowledge.pepTest.entity.BgPoi;
import gr.knowledge.pepTest.repository.BgPoiRepository;
import gr.knowledge.pepTest.service.BgPoiService;
import java.util.UUID;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

/**
 * Service implementation for {@code BgPoi} domain operations.
 */
@Service
@RequiredArgsConstructor
@Transactional
@Log4j2
public class BgPoiServiceImpl implements BgPoiService {

    private final BgPoiRepository bgPoiRepository;
    private final BgPoiMapper bgPoiMapper;

    /**
     * Retrieves all records.
     *
     * @return non-null list of {@link BgPoiDto}
     */
    @Override
    public List<BgPoiDto> getAllBgPoi() {
        log.info("Fetching all bg-poi.");
        return bgPoiMapper.toDTO(bgPoiRepository.findAll());
    }

    /**
     * Retrieves a record by id.
     *
     * @param id the record id
     * @return the matching {@link BgPoiDto}
     */
    @Override
    public BgPoiDto getBgPoiById(UUID id) {
        log.info("Fetching bg-poi with id: {}", id);
        BgPoi entity = bgPoiRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "BgPoi not found with id: " + id));
        return bgPoiMapper.toDTO(entity);
    }

    /**
     * Creates a new record.
     *
     * @param dto input payload
     * @return created {@link BgPoiDto}
     */
    @Override
    public BgPoiDto createBgPoi(BgPoiDto dto) {
        log.info("Creating bg-poi.");
        BgPoi entity = bgPoiMapper.toEntity(dto);
        BgPoi saved = bgPoiRepository.save(entity);
        return bgPoiMapper.toDTO(saved);
    }

    /**
     * Updates an existing record.
     *
     * Note: current implementation performs a full update (PUT-style).
     * PATCH behavior (merge non-null fields) can be added via ModelMapper config.
     *
     * @param id  the record id
     * @param dto input payload
     * @return updated {@link BgPoiDto}
     */
    @Override
    public BgPoiDto updateBgPoi(UUID id, BgPoiDto dto) {
        log.info("Updating bg-poi with id: {}", id);
        bgPoiRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "BgPoi not found with id: " + id));
        BgPoi entity = bgPoiMapper.toEntity(dto);
        entity.setId(id);
        BgPoi saved = bgPoiRepository.save(entity);
        return bgPoiMapper.toDTO(saved);
    }

    /**
     * Deletes a record by id.
     *
     * @param id the record id
     */
    @Override
    public void deleteBgPoi(UUID id) {
        log.info("Deleting bg-poi with id: {}", id);
        bgPoiRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "BgPoi not found with id: " + id));
        bgPoiRepository.deleteById(id);
    }
}
