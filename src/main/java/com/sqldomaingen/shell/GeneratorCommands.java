package com.sqldomaingen.shell;

import com.sqldomaingen.parser.SQLParser;
import com.sqldomaingen.generator.EntityGenerator;
import com.sqldomaingen.parser.CreateTableDefinition;
import com.sqldomaingen.model.Table;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.antlr.v4.runtime.TokenStream;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import com.sqldomaingen.parser.PostgreSQLParser;

import java.util.ArrayList;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

/**
 * Κλάση για τη διαχείριση εντολών CLI.
 */
@NoArgsConstructor
@ShellComponent
@Log4j2
public class GeneratorCommands {

    public final EntityGenerator entityGenerator = new EntityGenerator();

    /**
     * Δημιουργεί Java domain entities από SQL αρχεία.
     *
     * @param inputFile   Το μονοπάτι του SQL αρχείου.
     * @param outputDir   Ο κατάλογος όπου θα αποθηκευτούν οι παραγόμενες entities.
     * @param packageName Το όνομα του package για τις παραγόμενες entities.
     * @param overwrite   Αντικατάσταση υπαρχόντων αρχείων.
     * @param useBuilder  Χρήση builder pattern.
     * @return Μήνυμα επιτυχίας ή αποτυχίας.
     */
    @ShellMethod("Generate Java domain entities from a SQL file.")
    public String generateEntity(
            @ShellOption(value = {"--input-file", "-i"}, help = "Path to the SQL file") String inputFile,
            @ShellOption(value = {"--output-dir", "-o"}, help = "Output directory for the generated entities") String outputDir,
            @ShellOption(value = {"--package-name", "-p"}, help = "Package name for the generated entities") String packageName,
            @ShellOption(value = {"--overwrite", "-w"}, defaultValue = "false", help = "Overwrite existing files") boolean overwrite,
            @ShellOption(value = {"--use-builder", "-b"}, defaultValue = "false", help = "Enable builder pattern") boolean useBuilder
    ) {
        try {
            validateOutputDirectory(outputDir);

            List<Table> tables = processSQLFile(inputFile);

            if (tables.isEmpty()) {
                log.warn("No tables were generated from the SQL file.");
                return "No tables were generated from the SQL file.";
            }

            log.info("Starting entity generation...");
            entityGenerator.generate(tables, outputDir, packageName, overwrite, useBuilder);
            log.info("Entity generation completed successfully. Output directory: {}", outputDir);

            // Τερματισμός εφαρμογής
            System.exit(0);

            return "Entity generation completed successfully.";
        } catch (IOException e) {
            log.error("Error during entity generation", e);
            return "Error during entity generation: " + e.getMessage();
        }
    }


    public void validateOutputDirectory(String outputDirectory) throws IOException {
        if (outputDirectory == null || outputDirectory.trim().isEmpty()) {
            throw new IllegalArgumentException("Output directory cannot be null or empty.");
        }

        Path path = Paths.get(outputDirectory); // Εδώ μπορεί να προκληθεί NullPointerException αν το `outputDirectory` είναι null.
        if (!Files.exists(path)) {
            try {
                Files.createDirectories(path);
            } catch (IOException e) {
                throw new RuntimeException("Failed to create output directory: " + outputDirectory, e);
            }
        }
    }


    /**
     * Επεξεργάζεται το SQL αρχείο.
     *
     * @param inputFile Το μονοπάτι του SQL αρχείου.
     * @return Λίστα με αντικείμενα Table.
     * @throws IOException Αν αποτύχει η επεξεργασία.
     */
    public List<Table> processSQLFile(String inputFile) throws IOException {
        if (inputFile == null || inputFile.isEmpty()) {
            log.error("Input SQL file is null or empty.");
            throw new IllegalArgumentException("Input SQL file cannot be null or empty.");
        }

        log.info("Processing SQL file: {}", inputFile);
        Path filePath = Paths.get(inputFile);
        if (!Files.exists(filePath)) {
            log.warn("SQL file does not exist: {}", inputFile);
            return List.of();
        }

        String sqlContent = Files.readString(filePath);
        log.debug("SQL content from file '{}': {}", inputFile, sqlContent);

        return parseSQLToTables(sqlContent);
    }


    /**
     * Αναλύει το SQL περιεχόμενο και δημιουργεί αντικείμενο Table.
     *
     * @param sqlContent Το περιεχόμενο του SQL αρχείου.
     * @return Το αντικείμενο Table ή null αν δεν μπορεί να αναλυθεί.
     */
    public List<Table> parseSQLToTables(String sqlContent) {
        try {
            SQLParser sqlParserInstance = new SQLParser(sqlContent);

            // Παράγουμε το TokenStream από τον SQLParser
            TokenStream tokenStream = sqlParserInstance.parseSQL();

            // Δημιουργούμε έναν SQLParser από το TokenStream
            PostgreSQLParser parser = new PostgreSQLParser(tokenStream);

            // Παίρνουμε το SqlScriptContext
            PostgreSQLParser.SqlScriptContext context = parser.sqlScript();

            List<Table> tables = new ArrayList<>();
            for (PostgreSQLParser.CreateTableStatementContext createContext : context.createTableStatement()) {
                CreateTableDefinition createTableDefinition = new CreateTableDefinition();
                createTableDefinition.processCreateTable(createContext);

                Table table = createTableDefinition.toTable();
                tables.add(table);

            }
            return tables;

        } catch (Exception e) {
            log.error("Error parsing SQL content", e);
            return List.of();
        }
    }

}
