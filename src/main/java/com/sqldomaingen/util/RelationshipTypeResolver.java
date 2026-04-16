package com.sqldomaingen.util;

import com.sqldomaingen.generator.DtoFieldTypeResolver;
import com.sqldomaingen.model.Entity;
import com.sqldomaingen.model.Field;


import java.util.List;
import java.util.Objects;

/**
 * Resolves relationship ID types for DTO generation based on parsed entity metadata.
 */
public final class RelationshipTypeResolver {

    private RelationshipTypeResolver() {
    }

    /**
     * Resolves the DTO field type for the given field.
     *
     * <p>
     * Scalar fields keep their own type.
     * Relationship fields are mapped to nested DTO types.
     * </p>
     *
     * @param field current field
     * @return resolved DTO field type
     */
    public static String resolveDtoFieldType(Field field) {
        if (field == null) {
            return "Object";
        }

        String rawType = GeneratorSupport.trimToEmpty(field.getType());
        
        if (field.isOwningRelationship()) {
            return resolveRelationshipDtoType(field);
        }

        return DtoFieldTypeResolver.simplifyType(rawType);
    }

    /**
     * Resolves DTO type for relationship fields.
     *
     * @param field relationship field
     * @return DTO type
     */
    private static String resolveRelationshipDtoType(Field field) {
        String entityType = DtoFieldTypeResolver.simplifyType(field.getType());

        if (field.isCollection()) {
            return "List<" + entityType + "Dto>";
        }

        return entityType + "Dto";
    }

    /**
     * Resolves the identifier type for an owning relationship field.
     *
     * @param field relationship field
     * @param currentEntity current entity
     * @param entities all parsed entities
     * @return resolved identifier type
     */
    public static String resolveRelationshipIdType(
            Field field,
            Entity currentEntity,
            List<Entity> entities
    ) {
        Objects.requireNonNull(field, "field must not be null");
        Objects.requireNonNull(currentEntity, "currentEntity must not be null");
        Objects.requireNonNull(entities, "entities must not be null");

        String referencedEntityName = DtoFieldTypeResolver.simplifyType(field.getType());
        Entity referencedEntity = findEntityByName(entities, referencedEntityName);

        if (referencedEntity == null) {
            throw new IllegalStateException(
                    "Cannot resolve referenced entity '" + referencedEntityName
                            + "' for relationship field '" + field.getName()
                            + "' in entity '" + currentEntity.getName() + "'."
            );
        }

        Field primaryKeyField = findSinglePrimaryKeyField(referencedEntity);

        if (primaryKeyField == null) {
            throw new IllegalStateException(
                    "Cannot resolve a single primary key field for referenced entity '"
                            + referencedEntity.getName()
                            + "' used by relationship field '" + field.getName()
                            + "' in entity '" + currentEntity.getName() + "'."
            );
        }

        String primaryKeyType = GeneratorSupport.trimToEmpty(primaryKeyField.getType());

        if (primaryKeyField.isOwningRelationship() && !primaryKeyField.isCollection()) {
            return resolveRelationshipIdType(primaryKeyField, referencedEntity, entities);
        }

        return DtoFieldTypeResolver.simplifyType(primaryKeyType);
    }

    /**
     * Finds an entity by its simple name.
     *
     * @param entities all parsed entities
     * @param entityName target entity name
     * @return matching entity or null when not found
     */
    private static Entity findEntityByName(List<Entity> entities, String entityName) {
        if (entities == null || entities.isEmpty()) {
            return null;
        }

        String normalizedEntityName = GeneratorSupport.trimToEmpty(entityName);

        if (normalizedEntityName.isBlank()) {
            return null;
        }

        return entities.stream()
                .filter(Objects::nonNull)
                .filter(entity -> normalizedEntityName.equals(entity.getName()))
                .findFirst()
                .orElse(null);
    }

    /**
     * Finds the single primary key field of the given entity.
     *
     * @param entity source entity
     * @return primary key field, or null when none or many exist
     */
    private static Field findSinglePrimaryKeyField(Entity entity) {
        if (entity == null || entity.getFields() == null || entity.getFields().isEmpty()) {
            return null;
        }

        List<Field> primaryKeyFields = entity.getFields().stream()
                .filter(Objects::nonNull)
                .filter(Field::isPrimaryKey)
                .toList();

        if (primaryKeyFields.size() != 1) {
            return null;
        }

        return primaryKeyFields.get(0);
    }
}