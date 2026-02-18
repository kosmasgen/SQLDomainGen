package com.sqldomaingen.shell;

import com.sqldomaingen.generator.*;
import com.sqldomaingen.model.Entity;
import com.sqldomaingen.model.Table;
import com.sqldomaingen.parser.CreateTableDefinition;
import com.sqldomaingen.parser.PostgreSQLParser;
import com.sqldomaingen.parser.SQLParser;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.antlr.v4.runtime.TokenStream;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Κλάση για τη διαχείριση εντολών CLI.
 */
@NoArgsConstructor
@ShellComponent
@Log4j2
public class GeneratorCommands {

    public final EntityGenerator entityGenerator = new EntityGenerator();

    /**
     * Generates a full Spring Boot backend from a SQL file.
     * The pipeline creates:
     * entities, DTOs, mappers, repositories, services, and controllers,
     * under a buildable Maven project scaffold.
     *
     * @param inputFile   path to the SQL input file
     * @param outputDir   target project root directory for generated output
     * @param packageName base Java package name (e.g. gr.knowledge.schoolmanagement)
     * @param overwrite   whether to overwrite existing files
     * @param useBuilder  whether to use builder pattern for generated entities
     * @return success or error message
     */
    @ShellMethod("Generate full Spring backend from a SQL file.")
    public String generateEntity(
            @ShellOption(value = {"--input-file", "-i"}) String inputFile,
            @ShellOption(value = {"--output-dir", "-o"}) String outputDir,
            @ShellOption(value = {"--package-name", "-p"}) String packageName,
            @ShellOption(value = {"--overwrite", "-w"}, defaultValue = "false") boolean overwrite,
            @ShellOption(value = {"--use-builder", "-b"}, defaultValue = "false") boolean useBuilder
    ) {
        try {
            validateOutputDirectory(outputDir);

            // 1) Parse SQL -> Tables
            List<Table> tables = processSQLFile(inputFile);

            if (tables.isEmpty()) {
                log.warn("No tables were generated from the SQL file.");
                return "No tables were generated from the SQL file.";
            }

            log.info("Starting backend generation pipeline...");

            // 2) Project scaffold (pom.xml, Application, resources)
            new ProjectScaffoldGenerator().generateScaffold(outputDir, packageName, overwrite);

            // 2.1) Config (ModelMapper bean, etc.)
            new ConfigGenerator().generateConfigs(outputDir, packageName, overwrite);

            // 3) Exception handling (exception package + handler + error response)
            new ExceptionGenerator().generateExceptionHandling(outputDir, packageName, overwrite);

            // 4) Entities
            entityGenerator.generate(
                    tables,
                    outputDir,
                    packageName,
                    overwrite,
                    useBuilder
            );

            // 5) Entity models
            List<Entity> models = entityGenerator.toEntities(tables);

            // 6) DTOs
            new DTOGenerator().generateDTOs(models, outputDir, packageName);

            // 7) Mappers
            Map<String, Table> tableMap = tables.stream()
                    .collect(Collectors.toMap(Table::getName, t -> t));

            new MapperGenerator(tableMap)
                    .generateMappers(outputDir, packageName);

            // 8) Repositories
            new RepositoryGenerator()
                    .generateRepositories(tables, outputDir, packageName);

            // 9) Services
            new ServiceGenerator()
                    .generateAllServices(tables, outputDir, packageName);

            // 10) Controllers
            new ControllerGenerator()
                    .generateControllers(tables, outputDir, packageName, overwrite);

            // 11) Tests (controller + service + main)
            new TestGenerator()
                    .generateAllTests(tables, outputDir, packageName);

            log.info("✅ Backend generation completed successfully. Output directory: {}", outputDir);

            System.exit(0);
            return "Backend generation completed successfully.";

        } catch (IOException e) {
            log.error("Error during generation", e);
            return "Error during generation: " + e.getMessage();
        }
    }


    public void validateOutputDirectory(String outputDirectory) throws IOException {
        if (outputDirectory == null || outputDirectory.trim().isEmpty()) {
            throw new IllegalArgumentException("Output directory cannot be null or empty.");
        }

        Path path = Paths.get(outputDirectory);
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
     * Αναλύει το SQL περιεχόμενο και δημιουργεί αντικείμενα Table.
     *
     * @param sqlContent Το περιεχόμενο του SQL αρχείου.
     * @return Λίστα με αντικείμενα Table.
     */
    public List<Table> parseSQLToTables(String sqlContent) {
        try {
            SQLParser sqlParserInstance = new SQLParser(sqlContent);

            TokenStream tokenStream = sqlParserInstance.parseSQL();

            PostgreSQLParser parser = new PostgreSQLParser(tokenStream);

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
