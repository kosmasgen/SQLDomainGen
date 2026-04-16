package com.sqldomaingen.util;

import com.sqldomaingen.model.Table;

/**
 * Utility class that centralizes generator-specific naming conventions.
 *
 * <p>This class provides high-level naming used by code generators
 * (entities, DTOs, repositories, services, variables, method names).
 *
 * <p>It builds on top of {@link NamingConverter} and {@link GeneratorSupport}.
 */
public final class GeneratorNamingSupport {

    private static final String DTO_SUFFIX = "Dto";
    private static final String REPOSITORY_SUFFIX = "Repository";
    private static final String SERVICE_SUFFIX = "Service";
    private static final String SERVICE_IMPL_SUFFIX = "ServiceImpl";
    private static final String MAPPER_SUFFIX = "Mapper";
    private static final String CONTROLLER_SUFFIX = "Controller";
    private static final String KEY_SUFFIX = "Key";

    /**
     * Prevents instantiation.
     */
    private GeneratorNamingSupport() {
        throw new UnsupportedOperationException("Utility class");
    }

    /**
     * Builds entity name from table.
     */
    public static String buildEntityName(Table table) {
        if (table == null || isBlank(table.getName())) {
            throw new IllegalArgumentException("Table or table name is invalid");
        }

        return buildEntityName(table.getName());
    }

    /**
     * Builds entity name from raw table name.
     */
    public static String buildEntityName(String tableName) {
        if (isBlank(tableName)) {
            throw new IllegalArgumentException("tableName must not be blank");
        }

        String normalized = GeneratorSupport.normalizeTableName(tableName);
        return NamingConverter.toPascalCase(normalized);
    }

    public static String buildDtoName(String entityName) {
        return require(entityName) + DTO_SUFFIX;
    }

    public static String buildRepositoryName(String entityName) {
        return require(entityName) + REPOSITORY_SUFFIX;
    }

    public static String buildServiceName(String entityName) {
        return require(entityName) + SERVICE_SUFFIX;
    }

    public static String buildServiceImplName(String entityName) {
        return require(entityName) + SERVICE_IMPL_SUFFIX;
    }

    public static String buildMapperName(String entityName) {
        return require(entityName) + MAPPER_SUFFIX;
    }

    public static String buildControllerName(String entityName) {
        return require(entityName) + CONTROLLER_SUFFIX;
    }

    public static String buildCompositeKeyName(String entityName) {
        return require(entityName) + KEY_SUFFIX;
    }

    public static String buildVariableName(String typeName) {
        if (isBlank(typeName)) {
            throw new IllegalArgumentException("typeName must not be blank");
        }
        return NamingConverter.decapitalizeFirstLetter(typeName);
    }

    public static String buildRepositoryVariableName(String entityName) {
        return buildVariableName(buildRepositoryName(entityName));
    }

    public static String buildMapperVariableName(String entityName) {
        return buildVariableName(buildMapperName(entityName));
    }

    public static String buildServiceVariableName(String entityName) {
        return buildVariableName(buildServiceName(entityName));
    }

    public static String buildLowerDisplayLabel(String entityName) {
        String label = NamingConverter.toLogLabel(require(entityName));
        return label.isBlank() ? entityName : label;
    }

    public static String buildDisplayLabel(String entityName) {
        return NamingConverter.buildTitleCaseLabel(buildLowerDisplayLabel(entityName));
    }

    public static String buildPluralEntityName(String entityName) {
        return NamingConverter.toCamelCasePlural(require(entityName));
    }

    public static String buildPluralLowerDisplayLabel(String entityName) {
        return NamingConverter.toLogLabel(buildPluralEntityName(entityName));
    }

    public static String buildPluralMethodSuffix(String entityName) {
        return NamingConverter.toPascalCase(
                NamingConverter.toCamelCasePlural(require(entityName))
        );
    }

    public static String buildFindByIdOrThrowMethodName(String entityName) {
        return "find" + require(entityName) + "ByIdOrThrow";
    }

    public static String buildCreateNotFoundExceptionMethodName(String entityName) {
        return "create" + require(entityName) + "NotFoundException";
    }

    public static String buildGetByIdMethodName(String entityName) {
        return "get" + require(entityName) + "ById";
    }

    public static String buildCreateMethodName(String entityName) {
        return "create" + require(entityName);
    }

    public static String buildPatchMethodName(String entityName) {
        return "patch" + require(entityName);
    }

    public static String buildDeleteMethodName(String entityName) {
        return "delete" + require(entityName);
    }

    /**
     * Internal safe validator.
     */
    private static String require(String value) {
        if (isBlank(value)) {
            throw new IllegalArgumentException("Value must not be blank");
        }
        return value;
    }

    /**
     * Internal blank check.
     */
    private static boolean isBlank(String value) {
        return value == null || value.trim().isEmpty();
    }
}