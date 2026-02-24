package com.sqldomaingen.util;

import lombok.extern.log4j.Log4j2;

import java.util.Arrays;
import java.util.stream.Collectors;

@Log4j2
public class NamingConverter {

    // Private constructor to prevent instantiation.
    private NamingConverter() {
        throw new UnsupportedOperationException("Utility class");
    }

    /**
     * Converts a snake_case string to camelCase.
     *
     * @param snakeCaseName input in snake_case (e.g. "snake_case_name")
     * @return camelCase output (e.g. "snakeCaseName")
     */
    public static String toCamelCase(String snakeCaseName) {
        if (snakeCaseName == null) {
            log.warn("Received null input for toCamelCase.");
            return null;
        }

        snakeCaseName = snakeCaseName.trim();
        if (snakeCaseName.isEmpty()) {
            log.info("Received empty input for toCamelCase.");
            return snakeCaseName;
        }

        log.debug("Converting '{}' from snake_case to camelCase.", snakeCaseName);

        String[] parts = snakeCaseName.split("_");
        String result = parts[0] +
                Arrays.stream(parts, 1, parts.length)
                        .map(NamingConverter::capitalizeFirstLetter)
                        .collect(Collectors.joining());

        log.debug("Converted '{}' to camelCase: '{}'.", snakeCaseName, result);
        return result;
    }

    /**
     * Converts a snake_case string to PascalCase.
     *
     * @param snakeCaseName input in snake_case (e.g. "snake_case_name")
     * @return PascalCase output (e.g. "SnakeCaseName")
     */
    public static String toPascalCase(String snakeCaseName) {
        log.debug("Converting '{}' from snake_case to PascalCase.", snakeCaseName);

        String result = capitalizeFirstLetter(toCamelCase(snakeCaseName));

        log.debug("Converted '{}' to PascalCase: '{}'.", snakeCaseName, result);
        return result;
    }

    /**
     * Converts a camelCase or PascalCase string to snake_case.
     *
     * @param camelCaseName input in camelCase/PascalCase (e.g. "camelCaseName")
     * @return snake_case output (e.g. "camel_case_name")
     */
    public static String toSnakeCase(String camelCaseName) {
        if (camelCaseName == null || camelCaseName.isEmpty()) {
            log.warn("Received null or empty input for toSnakeCase.");
            return camelCaseName;
        }

        log.debug("Converting '{}' from camelCase/PascalCase to snake_case.", camelCaseName);

        String result = camelCaseName
                .replaceAll("([a-z])([A-Z])", "$1_$2")
                .toLowerCase();

        log.debug("Converted '{}' to snake_case: '{}'.", camelCaseName, result);
        return result;
    }

    /**
     * Capitalizes the first character of the provided string.
     *
     * @param name input string (e.g. "word")
     * @return output string with first letter uppercase (e.g. "Word")
     */
    public static String capitalizeFirstLetter(String name) {
        if (name == null || name.isEmpty()) {
            log.warn("Received null or empty input for capitalizeFirstLetter.");
            return name;
        }

        String result = Character.toUpperCase(name.charAt(0)) + name.substring(1);

        log.debug("Capitalized first letter of '{}': '{}'.", name, result);
        return result;
    }

    /**
     * Lowercases the first character of the provided string.
     *
     * @param name input string (e.g. "Word")
     * @return output string with first letter lowercase (e.g. "word")
     */
    public static String decapitalizeFirstLetter(String name) {
        if (name == null || name.isEmpty()) {
            log.warn("Received null or empty input for decapitalizeFirstLetter.");
            return name;
        }

        String result = Character.toLowerCase(name.charAt(0)) + name.substring(1);

        log.debug("Decapitalized first letter of '{}': '{}'.", name, result);
        return result;
    }

    /**
     * Converts a snake_case (or simple) name to a naive plural camelCase form.
     * <p>
     * This method does not attempt English pluralization rules; it simply appends 's'
     * unless the result already ends with 's'.
     *
     * @param input name (e.g. "course_student" or "student")
     * @return plural camelCase (e.g. "courseStudents" or "students")
     */
    public static String toCamelCasePlural(String input) {
        String singular = toCamelCase(input);
        if (singular == null) {
            return null;
        }

        singular = Character.toLowerCase(singular.charAt(0)) + singular.substring(1);

        if (singular.endsWith("s")) {
            return singular;
        }
        return singular + "s";
    }

    /**
     * Converts a camelCase/PascalCase string to kebab-case.
     *
     * @param input input string (e.g. "SchoolManagement")
     * @return kebab-case output (e.g. "school-management")
     */
    public static String toKebabCase(String input) {
        if (input == null || input.isEmpty()) {
            return input;
        }
        return input.replaceAll("([a-z])([A-Z])", "$1-$2").toLowerCase();
    }
}
