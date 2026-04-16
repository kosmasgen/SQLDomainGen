package gr.knowledge.pepTest.mapper;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Id;
import org.modelmapper.ModelMapper;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Collections;
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
     * @param entityList source entities
     * @return mapped dto list
     */
    public List<D> toDTOList(List<E> entityList) {
        if (entityList == null || entityList.isEmpty()) {
            return List.of();
        }

        return entityList.stream()
                .map(this::toDTO)
                .toList();
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
     * @param dtoList source dto list
     * @return mapped entity list
     */
    public List<E> toEntityList(List<D> dtoList) {
        if (dtoList == null || dtoList.isEmpty()) {
            return List.of();
        }

        return dtoList.stream()
                .map(this::toEntity)
                .toList();
    }

    /**
     * Applies non-null values from DTO into an existing entity.
     * Primary key fields are not modified.
     *
     * @param entity target entity already loaded from persistence
     * @param dto source dto containing partial values
     */
    public void partialUpdate(E entity, D dto) {
        if (entity == null || dto == null) {
            return;
        }

        E patchSource = modelMapper.map(dto, entityClass);
        mergeNonNullFields(patchSource, entity);
    }

    /**
     * Copies non-null field values from source entity to target entity.
     *
     * @param source source entity with patch values
     * @param target target entity to update
     */
    private void mergeNonNullFields(E source, E target) {
        for (Field field : getAllFields(entityClass)) {
            if (shouldSkipField(field)) {
                continue;
            }

            try {
                field.setAccessible(true);
                Object value = field.get(source);

                if (value != null) {
                    field.set(target, value);
                }
            } catch (IllegalAccessException exception) {
                throw new IllegalStateException(
                        "Failed to apply partial update for field: " + field.getName(),
                        exception
                );
            }
        }
    }

    /**
     * Collects all declared fields from the given class hierarchy.
     *
     * @param type root entity class
     * @return collected fields
     */
    private List<Field> getAllFields(Class<?> type) {
        List<Field> fields = new ArrayList<>();
        Class<?> currentType = type;

        while (currentType != null && currentType != Object.class) {
            Collections.addAll(fields, currentType.getDeclaredFields());
            currentType = currentType.getSuperclass();
        }

        return fields;
    }

    /**
     * Checks whether the field should be skipped during partial update.
     *
     * @param field entity field
     * @return true when the field must not be updated
     */
    private boolean shouldSkipField(Field field) {
        int modifiers = field.getModifiers();

        return Modifier.isStatic(modifiers)
                || Modifier.isFinal(modifiers)
                || field.isAnnotationPresent(Id.class)
                || field.isAnnotationPresent(EmbeddedId.class);
    }
}
