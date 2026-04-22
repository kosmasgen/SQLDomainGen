package gr.knowledge.pepTest.serviceImpl;

import gr.knowledge.pepTest.dto.BgPoiDto;
import gr.knowledge.pepTest.mapper.BgPoiMapper;
import gr.knowledge.pepTest.entity.BgPoi;
import gr.knowledge.pepTest.repository.BgPoiRepository;
import gr.knowledge.pepTest.service.BgPoiService;
import gr.knowledge.pepTest.exception.ErrorCodes;
import gr.knowledge.pepTest.exception.GeneratedRuntimeException;
import java.util.UUID;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * Service implementation for {@code Bg Poi} domain operations.
 */
@Service
@RequiredArgsConstructor
@Transactional
@Log4j2
public class BgPoiServiceImpl implements BgPoiService {

    private final BgPoiRepository bgPoiRepository;
    private final BgPoiMapper bgPoiMapper;

    /**
     * Retrieves all bg pois records.
     * @return list of BgPoiDto
     */
    @Override
    public List<BgPoiDto> getAllBgPois() {
        log.info("Fetching all bg pois records.");
        return bgPoiMapper.toDTOList(bgPoiRepository.findAll());
    }

    /**
     * Retrieves a bg poi record by id.
     * @param id the bg poi id
     * @return BgPoiDto
     */
    @Override
    public BgPoiDto getBgPoiById(UUID id) {
        log.info("Fetching bg poi with id: {}", id);

        BgPoi existingEntity = findBgPoiByIdOrThrow(id);
        return bgPoiMapper.toDTO(existingEntity);
    }

    /**
     * Creates a new bg poi record.
     * @param dto input payload
     * @return created {@link BgPoiDto}
     */
    @Override
    public BgPoiDto createBgPoi(BgPoiDto dto) {
        log.info("Creating bg poi.");

        BgPoi entity = bgPoiMapper.toEntity(dto);
        BgPoi savedEntity = bgPoiRepository.save(entity);

        return bgPoiMapper.toDTO(savedEntity);
    }

    /**
     * Updates an existing bg poi record.
     *
     * @param id the bg poi id
     * @param dto input payload
     * @return updated {@link BgPoiDto}
     */
    @Override
    public BgPoiDto updateBgPoi(UUID id, BgPoiDto dto) {
        log.info("Updating bg poi with id: {}", id);

        BgPoi existingEntity = findBgPoiByIdOrThrow(id);
        bgPoiMapper.partialUpdate(existingEntity, dto);
        BgPoi savedEntity = bgPoiRepository.save(existingEntity);

        return bgPoiMapper.toDTO(savedEntity);
    }

    /**
     * Delete a bg poi record by id.
     * @param id the bg poi id
     */
    @Override
    public void deleteBgPoi(UUID id) {
        log.info("Deleting bg poi with id: {}", id);

        findBgPoiByIdOrThrow(id);
        bgPoiRepository.deleteById(id);
    }

    /**
     * Finds an existing bg poi record by id or throws an exception.
     * @param id the bg poi id
     * @return existing BgPoi entity
     */
    private BgPoi findBgPoiByIdOrThrow(UUID id) {
        return bgPoiRepository.findById(id)
                .orElseThrow(() -> createBgPoiNotFoundException(id));
    }

    /**
     Creates a NOT FOUND exception for the bg poi entity.
     @param id the bg poi id
     @return runtime exception
     */
    private RuntimeException createBgPoiNotFoundException(UUID id) {
        log.warn("BgPoi not found with id: {}", id);

        return GeneratedRuntimeException.builder()
                .code(ErrorCodes.NOT_FOUND)
                .entity("BgPoi")
                .message("BgPoi not found with id: " + id)
                .build();
    }

}
