package com.sqldomaingen.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.stream.Collectors;

public class NamingConverter {

    private static final Logger logger = LoggerFactory.getLogger(NamingConverter.class);

    // Ιδιωτικός constructor για να αποτρέψουμε τη δημιουργία αντικειμένων
    public NamingConverter() {
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
            logger.warn("Received null input for toCamelCase.");
            return null;
        }
        snakeCaseName = snakeCaseName.trim();
        if (snakeCaseName.isEmpty()) {
            logger.info("Received empty input for toCamelCase.");
            return snakeCaseName;
        }
        logger.debug("Converting '{}' from snake_case to camelCase.", snakeCaseName);
        String[] parts = snakeCaseName.split("_");
        String result = parts[0] +
                Arrays.stream(parts, 1, parts.length)
                        .map(NamingConverter::capitalizeFirstLetter)
                        .collect(Collectors.joining());
        logger.debug("Converted '{}' to camelCase: '{}'.", snakeCaseName, result);
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
        logger.debug("Converting '{}' from snake_case to PascalCase.", snakeCaseName);
        String result = capitalizeFirstLetter(toCamelCase(snakeCaseName));
        logger.debug("Converted '{}' to PascalCase: '{}'.", snakeCaseName, result);
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
            logger.warn("Received null or empty input for toSnakeCase.");
            return camelCaseName;
        }
        logger.debug("Converting '{}' from camelCase/PascalCase to snake_case.", camelCaseName);
        String result = camelCaseName
                .replaceAll("([a-z])([A-Z])", "$1_$2")
                .toLowerCase();
        logger.debug("Converted '{}' to snake_case: '{}'.", camelCaseName, result);
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
            logger.warn("Received null or empty input for capitalizeFirstLetter.");
            return name;
        }
        String result = Character.toUpperCase(name.charAt(0)) + name.substring(1);
        logger.debug("Capitalized first letter of '{}': '{}'.", name, result);
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
            logger.warn("Received null or empty input for decapitalizeFirstLetter.");
            return name;
        }
        String result = Character.toLowerCase(name.charAt(0)) + name.substring(1);
        logger.debug("Decapitalized first letter of '{}': '{}'.", name, result);
        return result;
    }
}
