package gr.knowledge.pepTest.mapper;

import org.modelmapper.ModelMapper;

import java.util.List;

/**
 * Base mapper for converting between Entity and DTO using {@link ModelMapper}.
 *
 * @param <E> entity type
 * @param <D> dto type
 */
public abstract class BaseMapper<E, D> {

    protected final ModelMapper modelMapper;
    private final Class<E> entityClass;
    private final Class<D> dtoClass;

    protected BaseMapper(ModelMapper modelMapper, Class<E> entityClass, Class<D> dtoClass) {
        this.modelMapper = modelMapper;
        this.entityClass = entityClass;
        this.dtoClass = dtoClass;
    }

    /**
     * Converts an entity to a DTO.
     *
     * @param entity source entity
     * @return mapped dto
     */
    public D toDTO(E entity) {
        return modelMapper.map(entity, dtoClass);
    }

    /**
     * Converts a list of entities to DTOs.
     *
     * @param entities source entities
     * @return mapped dto list
     */
    public List<D> toDTO(List<E> entities) {
        return entities.stream().map(this::toDTO).toList();
    }

    /**
     * Converts a DTO to an entity.
     *
     * @param dto source dto
     * @return mapped entity
     */
    public E toEntity(D dto) {
        return modelMapper.map(dto, entityClass);
    }

    /**
     * Converts a list of DTOs to entities.
     *
     * @param dtos source dtos
     * @return mapped entity list
     */
    public List<E> toEntity(List<D> dtos) {
        return dtos.stream().map(this::toEntity).toList();
    }
}
