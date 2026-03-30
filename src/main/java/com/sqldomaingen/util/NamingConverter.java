package com.sqldomaingen.util;

/**
 * Utility class for converting naming conventions used across generated code.
 *
 * <p>This class provides helper methods for converting between snake_case,
 * camelCase, PascalCase, kebab-case, and human-readable log labels.
 */
public final class NamingConverter {

    /**
     * Prevents instantiation of this utility class.
     */
    private NamingConverter() {
        throw new UnsupportedOperationException("Utility class");
    }

    /**
     * Converts a snake_case string to camelCase.
     *
     * <p>Example: {@code snake_case_name -> snakeCaseName}
     *
     * @param snakeCaseName the snake_case input value
     * @return the converted camelCase value, or the original value when null or blank
     */
    public static String toCamelCase(String snakeCaseName) {
        if (snakeCaseName == null) {
            return null;
        }

        String trimmedValue = snakeCaseName.trim();
        if (trimmedValue.isEmpty()) {
            return trimmedValue;
        }

        String[] parts = trimmedValue.split("_");
        if (parts.length == 0) {
            return trimmedValue;
        }

        return parts[0] + java.util.Arrays.stream(parts, 1, parts.length)
                .map(NamingConverter::capitalizeFirstLetter)
                .collect(java.util.stream.Collectors.joining());
    }

    /**
     * Converts a snake_case string to PascalCase.
     *
     * <p>Example: {@code snake_case_name -> SnakeCaseName}
     *
     * @param snakeCaseName the snake_case input value
     * @return the converted PascalCase value, or the original value when null or blank
     */
    public static String toPascalCase(String snakeCaseName) {
        return capitalizeFirstLetter(toCamelCase(snakeCaseName));
    }

    /**
     * Converts a camelCase or PascalCase string to snake_case.
     *
     * <p>Examples:
     * <ul>
     *     <li>{@code camelCaseName -> camel_case_name}</li>
     *     <li>{@code PascalCaseName -> pascal_case_name}</li>
     * </ul>
     *
     * @param camelCaseName the camelCase or PascalCase input value
     * @return the converted snake_case value, or the original value when null or blank
     */
    public static String toSnakeCase(String camelCaseName) {
        if (camelCaseName == null || camelCaseName.isBlank()) {
            return camelCaseName;
        }

        return camelCaseName
                .replaceAll("([a-z])([A-Z])", "$1_$2")
                .toLowerCase();
    }

    /**
     * Capitalizes the first character of the given string.
     *
     * <p>Example: {@code word -> Word}
     *
     * @param name the input value
     * @return the same value with the first character capitalized,
     *         or the original value when null or blank
     */
    public static String capitalizeFirstLetter(String name) {
        if (name == null || name.isBlank()) {
            return name;
        }

        return Character.toUpperCase(name.charAt(0)) + name.substring(1);
    }

    /**
     * Lowercases the first character of the given string.
     *
     * <p>Example: {@code Word -> word}
     *
     * @param name the input value
     * @return the same value with the first character lowercased,
     *         or the original value when null or blank
     */
    public static String decapitalizeFirstLetter(String name) {
        if (name == null || name.isBlank()) {
            return name;
        }

        return Character.toLowerCase(name.charAt(0)) + name.substring(1);
    }

    /**
     * Converts a snake_case or simple singular name to a naive plural camelCase form.
     *
     * <p>This method does not apply English pluralization rules. It only appends
     * {@code s} when the generated camelCase result does not already end with {@code s}.
     *
     * <p>Examples:
     * <ul>
     *     <li>{@code course_student -> courseStudents}</li>
     *     <li>{@code student -> students}</li>
     * </ul>
     *
     * @param input the source value
     * @return the plural camelCase value, or null when the input is null
     */
    public static String toCamelCasePlural(String input) {
        String singular = toCamelCase(input);
        if (singular == null) {
            return null;
        }

        if (singular.isEmpty()) {
            return singular;
        }

        String normalizedValue = Character.toLowerCase(singular.charAt(0)) + singular.substring(1);

        if (normalizedValue.endsWith("s")) {
            return normalizedValue;
        }

        return normalizedValue + "s";
    }

    /**
     * Converts a camelCase or PascalCase string to kebab-case.
     *
     * <p>Example: {@code SchoolManagement -> school-management}
     *
     * @param input the input value
     * @return the converted kebab-case value, or the original value when null or blank
     */
    public static String toKebabCase(String input) {
        if (input == null || input.isBlank()) {
            return input;
        }

        return input
                .replaceAll("([a-z])([A-Z])", "$1-$2")
                .toLowerCase();
    }

    /**
     * Converts a Java-style type or entity name into a human-readable lowercase label.
     *
     * <p>This method is intended for cleaner log and message output.
     *
     * <p>Examples:
     * <ul>
     *     <li>{@code AuditTrail -> audit trail}</li>
     *     <li>{@code BgPoi -> bg poi}</li>
     *     <li>{@code CompanyStatusI18n -> company status i18n}</li>
     * </ul>
     *
     * @param value the source value
     * @return a lowercase human-readable label, or an empty string when the input is null or blank
     */
    public static String toLogLabel(String value) {
        if (value == null || value.isBlank()) {
            return "";
        }

        String normalizedValue = value.trim()
                .replaceAll("([a-z0-9])([A-Z])", "$1 $2")
                .replaceAll("([A-Z]+)([A-Z][a-z])", "$1 $2")
                .replaceAll("[_\\-]+", " ")
                .replaceAll("\\s+", " ")
                .trim();

        return normalizedValue.toLowerCase();
    }


    /**
     * Converts a database column name to a safe Java field name.
     *
     * Handles:
     * - snake_case -> camelCase
     * - invalid characters
     * - names starting with digits
     * - Java reserved keywords
     *
     * @param columnName raw database column name
     * @return safe Java field name
     */
    public static String toJavaFieldName(String columnName) {
        if (columnName == null || columnName.isBlank()) {
            return columnName;
        }

        // remove quotes
        String cleaned = columnName.replace("\"", "").trim();

        // replace invalid chars with underscore
        cleaned = cleaned.replaceAll("[^a-zA-Z0-9_]", "_");

        // normalize multiple underscores
        cleaned = cleaned.replaceAll("_+", "_");

        // snake_case -> camelCase
        String camelCase = toCamelCase(cleaned);

        if (camelCase == null || camelCase.isBlank()) {
            return camelCase;
        }

        // ensure first letter is lowercase
        camelCase = decapitalizeFirstLetter(camelCase);

        // ❗ critical: if starts with digit → prefix
        if (!Character.isJavaIdentifierStart(camelCase.charAt(0))) {
            camelCase = "field" + capitalizeFirstLetter(camelCase);
        }

        // ❗ ensure all chars are valid
        StringBuilder safeName = new StringBuilder();
        for (char c : camelCase.toCharArray()) {
            if (Character.isJavaIdentifierPart(c)) {
                safeName.append(c);
            }
        }

        String result = safeName.toString();

        // ❗ reserved keywords protection
        if (isJavaKeyword(result)) {
            result = "field" + capitalizeFirstLetter(result);
        }

        return result;
    }


    private static boolean isJavaKeyword(String value) {
        return java.util.Set.of(
                "abstract","assert","boolean","break","byte","case","catch","char","class","const","continue",
                "default","do","double","else","enum","extends","final","finally","float","for","goto","if",
                "implements","import","instanceof","int","interface","long","native","new","package","private",
                "protected","public","return","short","static","strictfp","super","switch","synchronized",
                "this","throw","throws","transient","try","void","volatile","while"
        ).contains(value);
    }
}