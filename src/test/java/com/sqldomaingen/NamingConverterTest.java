package com.sqldomaingen;

import com.sqldomaingen.util.NamingConverter;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NamingConverterTest {

    @Test
    void testToCamelCase() {
        assertEquals("snakeCaseName", NamingConverter.toCamelCase("snake_case_name"));
        assertEquals("singleword", NamingConverter.toCamelCase("singleword"));
        assertEquals("snakeCase", NamingConverter.toCamelCase("snake_case"));
        assertNull(NamingConverter.toCamelCase(null));
        assertEquals("", NamingConverter.toCamelCase(""));
        assertEquals("word", NamingConverter.toCamelCase(" word ")); // Με κενά
    }

    @Test
    void testToPascalCase() {
        assertEquals("SnakeCaseName", NamingConverter.toPascalCase("snake_case_name"));
        assertEquals("Singleword", NamingConverter.toPascalCase("singleword"));
        assertNull(NamingConverter.toPascalCase(null));
        assertEquals("", NamingConverter.toPascalCase(""));
        assertEquals("Word", NamingConverter.toPascalCase(" word ")); // Με κενά
    }

    @Test
    void testToSnakeCase() {
        assertEquals("camel_case_name", NamingConverter.toSnakeCase("camelCaseName"));
        assertEquals("pascal_case_name", NamingConverter.toSnakeCase("PascalCaseName"));
        assertEquals("singleword", NamingConverter.toSnakeCase("singleword"));
        assertNull(NamingConverter.toSnakeCase(null));
        assertEquals("", NamingConverter.toSnakeCase(""));
    }

    @Test
    void testCapitalizeFirstLetter() {
        assertEquals("Word", NamingConverter.capitalizeFirstLetter("word"));
        assertEquals("Word", NamingConverter.capitalizeFirstLetter("Word"));
        assertEquals("A", NamingConverter.capitalizeFirstLetter("a"));
        assertNull(NamingConverter.capitalizeFirstLetter(null));
        assertEquals("", NamingConverter.capitalizeFirstLetter(""));
    }

    @Test
    void testDecapitalizeFirstLetter() {
        assertEquals("word", NamingConverter.decapitalizeFirstLetter("Word"));
        assertEquals("word", NamingConverter.decapitalizeFirstLetter("word"));
        assertEquals("a", NamingConverter.decapitalizeFirstLetter("A"));
        assertNull(NamingConverter.decapitalizeFirstLetter(null));
        assertEquals("", NamingConverter.decapitalizeFirstLetter(""));
    }
}
