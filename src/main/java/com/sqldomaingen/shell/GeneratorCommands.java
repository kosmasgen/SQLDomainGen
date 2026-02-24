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
 * Spring Shell commands for generating a full Spring Boot backend from a SQL schema.
 */
@NoArgsConstructor
@ShellComponent
@Log4j2
public class GeneratorCommands {

    public final EntityGenerator entityGenerator = new EntityGenerator();

    /**
     * Runs the full generation pipeline using a SQL file as input.
     * <p>
     * Pipeline:
     * <ol>
     *   <li>Parse SQL into {@link Table} models</li>
     *   <li>Generate Maven project scaffold</li>
     *   <li>Generate config + exception handling</li>
     *   <li>Generate entities, DTOs, mappers, repositories, services, controllers, tests</li>
     * </ol>
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

            // 1) SQL -> Table models
            List<Table> tables = processSQLFile(inputFile);
            if (tables.isEmpty()) {
                log.warn("No tables were produced from the SQL file.");
                return "No tables were produced from the SQL file.";
            }

            log.info("Starting generation pipeline...");

            // 2) Project scaffold (pom.xml, application class, resources)
            new ProjectScaffoldGenerator().generateScaffold(outputDir, packageName, overwrite);

            // 3) Configs (beans, etc.)
            new ConfigGenerator().generateConfigs(outputDir, packageName, overwrite);

            // 4) Exception handling (handler + response)
            new ExceptionGenerator().generateExceptionHandling(outputDir, packageName, overwrite);

            // 5) Entities
            entityGenerator.generate(tables, outputDir, packageName, overwrite, useBuilder);

            // 6) Entity models derived from tables (used by DTO generator)
            List<Entity> models = entityGenerator.toEntities(tables);

            // 7) DTOs
            new DTOGenerator().generateDTOs(models, outputDir, packageName);

            // 8) Mappers need access to table definitions by name
            Map<String, Table> tableMap = tables.stream()
                    .collect(Collectors.toMap(Table::getName, t -> t));

            new MapperGenerator(tableMap).generateMappers(outputDir, packageName);

            // 9) Repositories
            new RepositoryGenerator().generateRepositories(tables, outputDir, packageName);

            // 10) Services
            new ServiceGenerator().generateAllServices(tables, outputDir, packageName);

            // 11) Controllers
            new ControllerGenerator().generateControllers(tables, outputDir, packageName, overwrite);

            // 12) Tests (controller + service + main)
            new TestGenerator().generateAllTests(tables, outputDir, packageName);

            log.info("Backend generation completed successfully. Output dir: {}", outputDir);

            System.exit(0);
            return "Backend generation completed successfully.";

        } catch (IOException e) {
            log.error("Generation failed", e);
            return "Generation failed: " + e.getMessage();
        }
    }

    /**
     * Ensures the output directory exists and is usable.
     * Creates it if missing.
     */
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
     * Reads a SQL file and converts its CREATE TABLE statements into {@link Table} objects.
     *
     * @param inputFile SQL file path
     * @return list of parsed tables (empty list if file is missing or parsing fails)
     * @throws IOException if the file exists but cannot be read
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
        log.debug("SQL content loaded from '{}'", inputFile);

        return parseSQLToTables(sqlContent);
    }

    /**
     * Parses SQL content using the ANTLR PostgreSQL grammar and returns table models.
     *
     * @param sqlContent raw SQL string
     * @return list of tables parsed from the SQL content
     */
    public List<Table> parseSQLToTables(String sqlContent) {
        try {
            // 1) Tokenize SQL with the lexer
            SQLParser sqlParserInstance = new SQLParser(sqlContent);
            TokenStream tokenStream = sqlParserInstance.parseSQL();

            // 2) Parse tokens into an AST (ParseTree)
            PostgreSQLParser parser = new PostgreSQLParser(tokenStream);
            PostgreSQLParser.SqlScriptContext context = parser.sqlScript();

            // 3) Walk CREATE TABLE statements and build Table models
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
