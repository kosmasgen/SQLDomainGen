package com.sqldomaingen.util;

import com.sqldomaingen.model.Column;
import lombok.extern.log4j.Log4j2;

import java.util.HashMap;
import java.util.Map;

/**
 * Utility class responsible for mapping SQL column types to Java types.
 */
@Log4j2
public class TypeMapper {

    private static final String JAVA_BIG_DECIMAL = "java.math.BigDecimal";
    private static final String JAVA_STRING = "String";
    private static final String JAVA_LOCAL_DATE_TIME = "java.time.LocalDateTime";

    private static final Map<String, String> sqlToJavaTypeMap = new HashMap<>();

    /**
     * Prevents instantiation of the utility class.
     */
    public TypeMapper() {
        throw new AssertionError("Utility class - instantiation not allowed.");
    }

    static {
        sqlToJavaTypeMap.put("BIGINT", "Long");
        sqlToJavaTypeMap.put("LONG", "Long");
        sqlToJavaTypeMap.put("INT", "Integer");
        sqlToJavaTypeMap.put("INTEGER", "Integer");
        sqlToJavaTypeMap.put("SMALLINT", "Short");
        sqlToJavaTypeMap.put("TINYINT", "Byte");

        sqlToJavaTypeMap.put("INT4", "Integer");
        sqlToJavaTypeMap.put("INT8", "Long");
        sqlToJavaTypeMap.put("INT2", "Short");

        sqlToJavaTypeMap.put("SERIAL", "Integer");
        sqlToJavaTypeMap.put("BIGSERIAL", "Long");
        sqlToJavaTypeMap.put("SMALLSERIAL", "Short");

        sqlToJavaTypeMap.put("DECIMAL", JAVA_BIG_DECIMAL);
        sqlToJavaTypeMap.put("NUMERIC", JAVA_BIG_DECIMAL);
        sqlToJavaTypeMap.put("FLOAT", JAVA_BIG_DECIMAL);
        sqlToJavaTypeMap.put("DOUBLE", JAVA_BIG_DECIMAL);
        sqlToJavaTypeMap.put("DOUBLE PRECISION", JAVA_BIG_DECIMAL);
        sqlToJavaTypeMap.put("REAL", "Double");

        sqlToJavaTypeMap.put("CHAR", JAVA_STRING);
        sqlToJavaTypeMap.put("VARCHAR", JAVA_STRING);
        sqlToJavaTypeMap.put("TEXT", JAVA_STRING);
        sqlToJavaTypeMap.put("JSON", JAVA_STRING);
        sqlToJavaTypeMap.put("JSONB", JAVA_STRING);

        sqlToJavaTypeMap.put("DATE", "java.time.LocalDate");
        sqlToJavaTypeMap.put("TIME", "java.time.LocalTime");
        sqlToJavaTypeMap.put("TIME WITHOUT TIME ZONE", "java.time.LocalTime");
        sqlToJavaTypeMap.put("TIME WITH TIME ZONE", "java.time.OffsetTime");
        sqlToJavaTypeMap.put("TIMESTAMP", JAVA_LOCAL_DATE_TIME);
        sqlToJavaTypeMap.put("TIMESTAMP WITHOUT TIME ZONE", JAVA_LOCAL_DATE_TIME);
        sqlToJavaTypeMap.put("TIMESTAMP WITH TIME ZONE", "java.time.OffsetDateTime");
        sqlToJavaTypeMap.put("DATETIME", JAVA_LOCAL_DATE_TIME);

        sqlToJavaTypeMap.put("BLOB", "byte[]");
        sqlToJavaTypeMap.put("BYTEA", "byte[]");

        sqlToJavaTypeMap.put("BOOL", "Boolean");
        sqlToJavaTypeMap.put("BOOLEAN", "Boolean");
        sqlToJavaTypeMap.put("BIT", "Boolean");

        sqlToJavaTypeMap.put("UUID", "java.util.UUID");
        sqlToJavaTypeMap.put("ARRAY", "java.util.List<?>");
        sqlToJavaTypeMap.put("MONEY", JAVA_BIG_DECIMAL);
        sqlToJavaTypeMap.put("ENUM", JAVA_STRING);
        sqlToJavaTypeMap.put("CITEXT", JAVA_STRING);
        sqlToJavaTypeMap.put("TSVECTOR", JAVA_STRING);
        sqlToJavaTypeMap.put("INET", JAVA_STRING);
        sqlToJavaTypeMap.put("CIDR", JAVA_STRING);
        sqlToJavaTypeMap.put("MACADDR", JAVA_STRING);
        sqlToJavaTypeMap.put("XML", JAVA_STRING);
        sqlToJavaTypeMap.put("PG_LSN", JAVA_STRING);
        sqlToJavaTypeMap.put("VARBIT", JAVA_STRING);
        sqlToJavaTypeMap.put("TRIGGER", JAVA_STRING);
        sqlToJavaTypeMap.put("INTERVAL", JAVA_STRING);
    }

    /**
     * Maps a raw SQL type string to a Java type.
     *
     * @param sqlType the SQL type
     * @return the Java type
     */
    public static String mapToJavaType(String sqlType) {
        if (sqlType == null || sqlType.isEmpty()) {
            log.error("SQL type cannot be null or empty");
            throw new IllegalArgumentException("SQL type cannot be null or empty");
        }

        String baseType = normalizeBaseType(sqlType);
        String javaType = sqlToJavaTypeMap.getOrDefault(baseType, JAVA_STRING);

        if (!sqlToJavaTypeMap.containsKey(baseType)) {
            log.warn("No specific mapping found for SQL type '{}'. Defaulting to 'String'.", baseType);
        } else {
            log.info("Mapping SQL type '{}' to Java type '{}'", baseType, javaType);
        }

        return javaType;
    }

    /**
     * Maps a column definition to a Java type.
     *
     * @param column the column metadata
     * @return the Java type
     */
    public static String mapToJavaType(Column column) {
        if (column == null) {
            throw new IllegalArgumentException("Column cannot be null");
        }

        String sqlType = column.getSqlType();
        if (sqlType == null || sqlType.isBlank()) {
            throw new IllegalArgumentException("SQL type cannot be null or empty");
        }

        String normalizedSqlType = sqlType.trim().toUpperCase().replaceAll("\\s+", " ");
        String baseType = normalizeBaseType(normalizedSqlType);

        if ("NUMERIC".equals(baseType) || "DECIMAL".equals(baseType)) {
            return mapNumericColumn(column, normalizedSqlType);
        }

        String javaType = sqlToJavaTypeMap.getOrDefault(baseType, JAVA_STRING);

        if (!sqlToJavaTypeMap.containsKey(baseType)) {
            log.warn("No specific mapping found for SQL type '{}'. Defaulting to 'String'.", baseType);
        } else {
            log.info("Mapping SQL type '{}' to Java type '{}'", baseType, javaType);
        }

        return javaType;
    }

    /**
     * Returns true when the provided SQL type represents a JSON column.
     *
     * @param sqlType the SQL type
     * @return true when the type is JSON or JSONB
     */
    public static boolean isJsonType(String sqlType) {
        if (sqlType == null || sqlType.isBlank()) {
            return false;
        }

        String baseType = normalizeBaseType(sqlType);
        return "JSON".equals(baseType) || "JSONB".equals(baseType);
    }

    /**
     * Returns true when the provided column represents a JSON column.
     *
     * @param column the column metadata
     * @return true when the column is JSON or JSONB
     */
    public static boolean isJsonType(Column column) {
        if (column == null || column.getSqlType() == null || column.getSqlType().isBlank()) {
            return false;
        }

        return isJsonType(column.getSqlType());
    }

    /**
     * Returns the SQL column definition to be used in JPA for JSON columns.
     *
     * @param column the column metadata
     * @return the normalized SQL column definition
     */
    public static String getJsonColumnDefinition(Column column) {
        if (!isJsonType(column)) {
            throw new IllegalArgumentException("Column is not a JSON type");
        }

        String baseType = normalizeBaseType(column.getSqlType());
        return baseType.toLowerCase();
    }

    /**
     * Normalizes an SQL type to its base form without precision or identity suffixes.
     *
     * @param sqlType the SQL type
     * @return the normalized base SQL type
     */
    private static String normalizeBaseType(String sqlType) {
        String normalizedSqlType = sqlType.trim().toUpperCase().replaceAll("\\s+", " ");
        String baseType = normalizedSqlType.split("\\(")[0].trim();

        baseType = baseType.replaceAll("\\[\\]", "");
        baseType = baseType.split("\\s+")[0];

        if (baseType.contains(" GENERATED ALWAYS AS IDENTITY")
                || baseType.contains(" GENERATED BY DEFAULT AS IDENTITY")) {
            baseType = baseType.split("\\s+GENERATED\\s+")[0].trim();
        }

        return baseType;
    }

    /**
     * Maps NUMERIC and DECIMAL columns to the most appropriate Java numeric type.
     *
     * @param column the column metadata
     * @param normalizedSqlType the normalized SQL type
     * @return the mapped Java numeric type
     */
    private static String mapNumericColumn(Column column, String normalizedSqlType) {
        int scale = column.getScale();
        int precision = column.getPrecision();

        if (precision == 0 && scale == 0 && normalizedSqlType.contains("(") && normalizedSqlType.contains(")")) {
            try {
                String numericArguments = normalizedSqlType.substring(
                        normalizedSqlType.indexOf('(') + 1,
                        normalizedSqlType.indexOf(')')
                );

                String[] numericParts = numericArguments.split(",");

                if (numericParts.length > 0 && !numericParts[0].trim().isEmpty()) {
                    precision = Integer.parseInt(numericParts[0].trim());
                }

                if (numericParts.length > 1 && !numericParts[1].trim().isEmpty()) {
                    scale = Integer.parseInt(numericParts[1].trim());
                }
            } catch (Exception exception) {
                log.warn("Failed to parse precision/scale from sqlType '{}'", column.getSqlType(), exception);
            }
        }

        if (scale > 0) {
            return "java.math.BigDecimal";
        }

        if (column.isPrimaryKey() || column.isIdentity()) {
            return "Long";
        }

        if (precision > 0 && precision <= 9) {
            return "Integer";
        }

        if (precision > 9 && precision <= 19) {
            return "Long";
        }

        return "java.math.BigInteger";
    }
}