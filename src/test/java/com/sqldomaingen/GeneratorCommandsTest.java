package com.sqldomaingen;

import com.sqldomaingen.shell.GeneratorCommands;
import com.sqldomaingen.model.Table;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.nio.file.Files;
import java.nio.file.Path;


import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class GeneratorCommandsTest {

    private static final Logger logger = LoggerFactory.getLogger(GeneratorCommandsTest.class);

    @Test
    void testGenerateEntitySuccess() throws IOException {
        logger.info("Starting testGenerateEntitySuccess...");

        GeneratorCommands generatorCommands = Mockito.spy(new GeneratorCommands());

        List<Table> mockTables = List.of(new Table());
        doReturn(mockTables).when(generatorCommands).processSQLFile(anyString());
        doNothing().when(generatorCommands).validateOutputDirectory(anyString());

        String result = generatorCommands.generateEntity(
                "input.sql",
                "outputDir",
                "com.example",
                false,
                false
        );

        logger.info("Result of generateEntity: {}", result);
        assertEquals("Entity generation completed successfully.", result);
        verify(generatorCommands).validateOutputDirectory("outputDir");
        verify(generatorCommands).processSQLFile("input.sql");
    }

    @Test
    void testGenerateEntityNoTables() throws IOException {
        logger.info("Starting testGenerateEntityNoTables...");

        GeneratorCommands generatorCommands = Mockito.spy(new GeneratorCommands());

        doReturn(List.of()).when(generatorCommands).processSQLFile(anyString());
        doNothing().when(generatorCommands).validateOutputDirectory(anyString());

        String result = generatorCommands.generateEntity(
                "input.sql",
                "outputDir",
                "com.example",
                false,
                false
        );

        logger.info("Result of generateEntity with no tables: {}", result);
        assertEquals("No tables were generated from the SQL file.", result);
        verify(generatorCommands).validateOutputDirectory("outputDir");
        verify(generatorCommands).processSQLFile("input.sql");
    }

    @Test
    void testGenerateEntityIOException() throws IOException {
        logger.info("Starting testGenerateEntityIOException...");

        GeneratorCommands generatorCommands = Mockito.spy(new GeneratorCommands());

        doThrow(new IOException("Test exception")).when(generatorCommands).validateOutputDirectory(anyString());

        String result = generatorCommands.generateEntity(
                "input.sql",
                "outputDir",
                "com.example",
                false,
                false
        );

        logger.info("Result of generateEntity with IOException: {}", result);
        assertTrue(result.contains("Error during entity generation: Test exception"));
        verify(generatorCommands).validateOutputDirectory("outputDir");
    }

    @Test
    void testProcessSQLFileFileNotFound() throws IOException {
        logger.info("Starting testProcessSQLFileFileNotFound...");

        GeneratorCommands generatorCommands = new GeneratorCommands();
        List<Table> tables = generatorCommands.processSQLFile("nonexistent.sql");

        logger.info("Tables returned for nonexistent file: {}", tables);
        assertTrue(tables.isEmpty(), "Should return an empty list when file does not exist.");
    }



    @Test
    void testValidateOutputDirectoryNull() {
        GeneratorCommands commands = new GeneratorCommands();
        Exception exception = assertThrows(IllegalArgumentException.class, () -> commands.validateOutputDirectory(null));
        assertEquals("Output directory cannot be null or empty.", exception.getMessage());
    }


    @Test
    void testGenerateEntityInvalidFileName() {
        GeneratorCommands commands = new GeneratorCommands();
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                commands.generateEntity(null, "outputDir", "com.example", false, false)
        );
        assertEquals("Input SQL file cannot be null or empty.", exception.getMessage());
    }


    @Test
    void testProcessSQLFileEmpty() throws IOException {
        logger.info("Starting testProcessSQLFileEmpty...");

        GeneratorCommands commands = new GeneratorCommands();
        List<?> result = commands.processSQLFile("nonexistent.sql");

        logger.info("Result of processSQLFile with empty input: {}", result);
        assertTrue(result.isEmpty(), "Should return empty list for nonexistent SQL file.");
    }
    @Test
    void testProcessSQLFileWithMultipleTables() throws IOException {
        String sql = """
        CREATE TABLE Orders (
            order_id INT PRIMARY KEY,
            customer_id INT,
            FOREIGN KEY (customer_id) REFERENCES Customers(customer_id) ON DELETE CASCADE
        );
        CREATE TABLE Customers (
            customer_id INT PRIMARY KEY,
            customer_name VARCHAR(100) NOT NULL
        );
    """;

        GeneratorCommands commands = new GeneratorCommands();
        Path tempFile = Files.createTempFile("test-multi-tables", ".sql");
        Files.writeString(tempFile, sql);

        List<Table> tables = commands.processSQLFile(tempFile.toString());
        assertEquals(2, tables.size(), "Expected two tables from the SQL file.");
        assertEquals("Orders", tables.get(0).getName());
        assertEquals("Customers", tables.get(1).getName());

        Files.delete(tempFile);
    }



}
