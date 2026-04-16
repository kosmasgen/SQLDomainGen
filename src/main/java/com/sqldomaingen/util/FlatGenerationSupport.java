package com.sqldomaingen.util;

import com.sqldomaingen.model.Column;

/**
 * Utility class for handling flat field generation (ID-based instead of relations).
 *
 * <p>This class centralizes the rule:
 * <b>All foreign keys are generated as scalar ID fields instead of object relations.</b>
 *
 * <p>Used across:
 * <ul>
 *     <li>EntityGenerator</li>
 *     <li>DTOGenerator</li>
 *     <li>MapperGenerator</li>
 * </ul>
 */
public final class FlatGenerationSupport {

    /**
     * Prevents instantiation.
     */
    private FlatGenerationSupport() {
        throw new UnsupportedOperationException("Utility class");
    }

    /**
     * Determines whether a column should be generated as a flat ID field.
     *
     * @param column table column metadata
     * @return true when column is a foreign key and should be flattened
     */
    public static boolean shouldGenerateAsFlatId(Column column) {
        return column != null && column.isForeignKey();
    }

    /**
     * Builds the Java field name for a flat ID column.
     *
     * <p>Example:
     * <pre>
     * company_id → companyId
     * user_profile_id → userProfileId
     * </pre>
     *
     * @param columnName database column name
     * @return camelCase field name
     */
    public static String buildFlatFieldName(String columnName) {
        if (columnName == null || columnName.isBlank()) {
            return "";
        }

        return NamingConverter.toCamelCase(columnName);
    }

    /**
     * Resolves the Java type for a flat ID column.
     *
     * @param column column metadata
     * @return resolved Java type (never null)
     */
    public static String resolveFlatFieldType(Column column) {
        if (column == null) {
            return "Long";
        }

        String javaType = GeneratorSupport.trimToEmpty(column.getJavaType());

        if (javaType.isEmpty()) {
            return "Long";
        }

        return JavaTypeSupport.resolveSimpleType(javaType);
    }

    /**
     * Builds the getter name for a flat field.
     *
     * @param fieldName field name
     * @return getter name
     */
    public static String buildGetterName(String fieldName) {
        if (fieldName == null || fieldName.isBlank()) {
            return "";
        }

        return "get" + NamingConverter.capitalizeFirstLetter(fieldName);
    }

    /**
     * Builds the setter name for a flat field.
     *
     * @param fieldName field name
     * @return setter name
     */
    public static String buildSetterName(String fieldName) {
        if (fieldName == null || fieldName.isBlank()) {
            return "";
        }

        return "set" + NamingConverter.capitalizeFirstLetter(fieldName);
    }
}