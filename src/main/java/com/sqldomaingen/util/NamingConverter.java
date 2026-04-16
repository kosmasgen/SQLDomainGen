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
     * Converts a snake_case or simple singular name to a plural camelCase form.
     *
     * <p>Examples:
     * <ul>
     *     <li>{@code course_student -> courseStudents}</li>
     *     <li>{@code student -> students}</li>
     *     <li>{@code company -> companies}</li>
     *     <li>{@code company_status -> companyStatuses}</li>
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

        if (singular.isBlank()) {
            return singular;
        }

        String normalizedValue = decapitalizeFirstLetter(singular);
        return pluralizeLastCamelCaseSegment(normalizedValue);
    }

    /**
     * Pluralizes only the last segment of a camelCase value.
     *
     * <p>Examples:
     * <ul>
     *     <li>{@code company -> companies}</li>
     *     <li>{@code companyStatus -> companyStatuses}</li>
     *     <li>{@code bgPoi -> bgPois}</li>
     * </ul>
     *
     * @param value the camelCase value
     * @return the value with its last segment pluralized
     */
    private static String pluralizeLastCamelCaseSegment(String value) {
        if (value == null || value.isBlank()) {
            return value;
        }

        int lastSegmentStart = 0;
        for (int index = 1; index < value.length(); index++) {
            if (Character.isUpperCase(value.charAt(index))) {
                lastSegmentStart = index;
            }
        }

        String prefix = value.substring(0, lastSegmentStart);
        String lastSegment = value.substring(lastSegmentStart);

        return prefix + pluralizeWord(lastSegment);
    }

    /**
     * Applies simple English pluralization rules to a single word.
     *
     * <p>Supported rules:
     * <ul>
     *     <li>consonant + y -> ies</li>
     *     <li>s, x, z, ch, sh -> es</li>
     *     <li>default -> s</li>
     * </ul>
     *
     * @param word the singular word
     * @return pluralized word
     */
    private static String pluralizeWord(String word) {
        if (word == null || word.isBlank()) {
            return word;
        }

        String lowerCaseWord = word.toLowerCase();

        if (lowerCaseWord.endsWith("y") && word.length() > 1 && isConsonant(word.charAt(word.length() - 2))) {
            return word.substring(0, word.length() - 1) + "ies";
        }

        if (lowerCaseWord.endsWith("s")
                || lowerCaseWord.endsWith("x")
                || lowerCaseWord.endsWith("z")
                || lowerCaseWord.endsWith("ch")
                || lowerCaseWord.endsWith("sh")) {
            return word + "es";
        }

        return word + "s";
    }

    /**
     * Returns true when the provided character is an English consonant.
     *
     * @param value the source character
     * @return true when the character is a consonant
     */
    private static boolean isConsonant(char value) {
        char lowerCaseValue = Character.toLowerCase(value);
        return Character.isLetter(lowerCaseValue)
                && lowerCaseValue != 'a'
                && lowerCaseValue != 'e'
                && lowerCaseValue != 'i'
                && lowerCaseValue != 'o'
                && lowerCaseValue != 'u';
    }

    /**
     * Converts a camelCase or PascalCase string to plural kebab-case.
     *
     * <p>Examples:
     * <ul>
     *     <li>{@code Company -> companies}</li>
     *     <li>{@code BgPoi -> bg-pois}</li>
     *     <li>{@code CompanyStatus -> company-statuses}</li>
     * </ul>
     *
     * @param input the input value
     * @return the plural kebab-case value, or the original value when null or blank
     */
    public static String toKebabCasePlural(String input) {
        String singular = toKebabCase(input);
        if (singular == null || singular.isBlank()) {
            return singular;
        }

        return pluralizeLastSeparatedSegment(singular, "-");
    }

    /**
     * Pluralizes only the last segment of a separated value.
     *
     * <p>Examples:
     * <ul>
     *     <li>{@code company -> companies}</li>
     *     <li>{@code company-status -> company-statuses}</li>
     *     <li>{@code bg-poi -> bg-pois}</li>
     * </ul>
     *
     * @param value the separated value
     * @param separator the segment separator
     * @return the value with its last segment pluralized
     */
    private static String pluralizeLastSeparatedSegment(String value, String separator) {
        if (value == null || value.isBlank()) {
            return value;
        }

        int lastSeparatorIndex = value.lastIndexOf(separator);
        if (lastSeparatorIndex < 0) {
            return pluralizeWord(value);
        }

        String prefix = value.substring(0, lastSeparatorIndex + separator.length());
        String lastSegment = value.substring(lastSeparatorIndex + separator.length());

        return prefix + pluralizeWord(lastSegment);
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

        // critical: if starts with digit → prefix
        if (!Character.isJavaIdentifierStart(camelCase.charAt(0))) {
            camelCase = "field" + capitalizeFirstLetter(camelCase);
        }

        //  ensure all chars are valid
        StringBuilder safeName = new StringBuilder();
        for (char c : camelCase.toCharArray()) {
            if (Character.isJavaIdentifierPart(c)) {
                safeName.append(c);
            }
        }

        String result = safeName.toString();

        //  reserved keywords protection
        if (isJavaKeyword(result)) {
            result = "field" + capitalizeFirstLetter(result);
        }

        return result;
    }

    /**
     * Converts a lowercase human-readable label to title case.
     *
     * <p>Examples:
     * <ul>
     *     <li>{@code company -> Company}</li>
     *     <li>{@code company article files -> Company Article Files}</li>
     * </ul>
     *
     * @param lowerLabel lowercase human-readable label
     * @return title-case label, or an empty string when the input is null or blank
     */
    public static String buildTitleCaseLabel(String lowerLabel) {
        if (lowerLabel == null || lowerLabel.isBlank()) {
            return "";
        }

        String[] parts = lowerLabel.split("\\s+");
        StringBuilder builder = new StringBuilder();

        for (int index = 0; index < parts.length; index++) {
            String part = parts[index];
            if (part.isBlank()) {
                continue;
            }

            builder.append(Character.toUpperCase(part.charAt(0)));
            if (part.length() > 1) {
                builder.append(part.substring(1));
            }

            if (index < parts.length - 1) {
                builder.append(" ");
            }
        }

        return builder.toString();
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