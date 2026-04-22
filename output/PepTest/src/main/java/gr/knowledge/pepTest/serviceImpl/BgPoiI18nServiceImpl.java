package gr.knowledge.pepTest.serviceImpl;

import gr.knowledge.pepTest.dto.BgPoiI18nDto;
import gr.knowledge.pepTest.mapper.BgPoiI18nMapper;
import gr.knowledge.pepTest.entity.BgPoiI18n;
import gr.knowledge.pepTest.repository.BgPoiI18nRepository;
import gr.knowledge.pepTest.service.BgPoiI18nService;
import gr.knowledge.pepTest.exception.ErrorCodes;
import gr.knowledge.pepTest.exception.GeneratedRuntimeException;
import java.util.UUID;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * Service implementation for {@code Bg Poi I18n} domain operations.
 */
@Service
@RequiredArgsConstructor
@Transactional
@Log4j2
public class BgPoiI18nServiceImpl implements BgPoiI18nService {

    private final BgPoiI18nRepository bgPoiI18nRepository;
    private final BgPoiI18nMapper bgPoiI18nMapper;

    /**
     * Retrieves all bg poi i18ns records.
     * @return list of BgPoiI18nDto
     */
    @Override
    public List<BgPoiI18nDto> getAllBgPoiI18ns() {
        log.info("Fetching all bg poi i18ns records.");
        return bgPoiI18nMapper.toDTOList(bgPoiI18nRepository.findAll());
    }

    /**
     * Retrieves a bg poi i18n record by id.
     * @param id the bg poi i18n id
     * @return BgPoiI18nDto
     */
    @Override
    public BgPoiI18nDto getBgPoiI18nById(UUID id) {
        log.info("Fetching bg poi i18n with id: {}", id);

        BgPoiI18n existingEntity = findBgPoiI18nByIdOrThrow(id);
        return bgPoiI18nMapper.toDTO(existingEntity);
    }

    /**
     * Creates a new bg poi i18n record.
     * @param dto input payload
     * @return created {@link BgPoiI18nDto}
     */
    @Override
    public BgPoiI18nDto createBgPoiI18n(BgPoiI18nDto dto) {
        log.info("Creating bg poi i18n.");

        BgPoiI18n entity = bgPoiI18nMapper.toEntity(dto);
        BgPoiI18n savedEntity = bgPoiI18nRepository.save(entity);

        return bgPoiI18nMapper.toDTO(savedEntity);
    }

    /**
     * Updates an existing bg poi i18n record.
     *
     * @param id the bg poi i18n id
     * @param dto input payload
     * @return updated {@link BgPoiI18nDto}
     */
    @Override
    public BgPoiI18nDto updateBgPoiI18n(UUID id, BgPoiI18nDto dto) {
        log.info("Updating bg poi i18n with id: {}", id);

        BgPoiI18n existingEntity = findBgPoiI18nByIdOrThrow(id);
        bgPoiI18nMapper.partialUpdate(existingEntity, dto);
        BgPoiI18n savedEntity = bgPoiI18nRepository.save(existingEntity);

        return bgPoiI18nMapper.toDTO(savedEntity);
    }

    /**
     * Delete a bg poi i18n record by id.
     * @param id the bg poi i18n id
     */
    @Override
    public void deleteBgPoiI18n(UUID id) {
        log.info("Deleting bg poi i18n with id: {}", id);

        findBgPoiI18nByIdOrThrow(id);
        bgPoiI18nRepository.deleteById(id);
    }

    /**
     * Finds an existing bg poi i18n record by id or throws an exception.
     * @param id the bg poi i18n id
     * @return existing BgPoiI18n entity
     */
    private BgPoiI18n findBgPoiI18nByIdOrThrow(UUID id) {
        return bgPoiI18nRepository.findById(id)
                .orElseThrow(() -> createBgPoiI18nNotFoundException(id));
    }

    /**
     Creates a NOT FOUND exception for the bg poi i18n entity.
     @param id the bg poi i18n id
     @return runtime exception
     */
    private RuntimeException createBgPoiI18nNotFoundException(UUID id) {
        log.warn("BgPoiI18n not found with id: {}", id);

        return GeneratedRuntimeException.builder()
                .code(ErrorCodes.NOT_FOUND)
                .entity("BgPoiI18n")
                .message("BgPoiI18n not found with id: " + id)
                .build();
    }

}
