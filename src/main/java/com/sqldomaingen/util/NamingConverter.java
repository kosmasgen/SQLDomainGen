package com.sqldomaingen.util;

import lombok.extern.log4j.Log4j2;

import java.util.Arrays;
import java.util.stream.Collectors;

@Log4j2
public class NamingConverter {

    // Ιδιωτικός constructor για να αποτρέψουμε τη δημιουργία αντικειμένων
    private NamingConverter() {
        throw new UnsupportedOperationException("Utility class");
    }

    /**
     * Μετατρέπει ένα όνομα από snake_case σε camelCase.
     *
     * @param snakeCaseName Το όνομα σε snake_case.
     * @return Το όνομα σε camelCase.
     * Input: "snake_case_name", Output: "snakeCaseName"
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
     * Μετατρέπει ένα όνομα από snake_case σε PascalCase.
     *
     * @param snakeCaseName Το όνομα σε snake_case.
     * @return Το όνομα σε PascalCase.
     * Input: "snake_case_name", Output: "SnakeCaseName"
     */
    public static String toPascalCase(String snakeCaseName) {
        log.debug("Converting '{}' from snake_case to PascalCase.", snakeCaseName);
        String result = capitalizeFirstLetter(toCamelCase(snakeCaseName));
        log.debug("Converted '{}' to PascalCase: '{}'.", snakeCaseName, result);
        return result;
    }

    /**
     * Μετατρέπει ένα όνομα από camelCase ή PascalCase σε snake_case.
     *
     * @param camelCaseName Το όνομα σε camelCase ή PascalCase.
     * @return Το όνομα σε snake_case.
     * Input: "camelCaseName", Output: "camel_case_name"
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
     * Μετατρέπει το πρώτο γράμμα μιας λέξης σε κεφαλαίο.
     *
     * @param name Το όνομα της λέξης.
     * @return Το όνομα με κεφαλαίο το πρώτο γράμμα.
     * Input: "word", Output: "Word"
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
     * Μετατρέπει το πρώτο γράμμα μιας λέξης σε μικρό.
     *
     * @param name Το όνομα της λέξης.
     * @return Το όνομα με μικρό το πρώτο γράμμα.
     * Input: "Word", Output: "word"
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

    public static String toKebabCase(String input) {
        if (input == null || input.isEmpty()) {
            return input;
        }
        return input.replaceAll("([a-z])([A-Z])", "$1-$2").toLowerCase();
    }
}
