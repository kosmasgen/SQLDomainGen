package com.sqldomaingen.validation;

import com.sqldomaingen.model.Entity;
import com.sqldomaingen.model.Table;
import com.sqldomaingen.util.PackageResolver;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * Collects generation validation information and produces a unified report.
 */
public class GenerationValidationRunner {

    /**
     * Runs post-generation validations and returns one unified report.
     *
     * @param inputFile input SQL file path
     * @param outputDir output project directory
     * @param basePackage base Java package
     * @param parsedTables all parsed tables
     * @param javaGenerationTables filtered Java-generation tables
     * @param models generated entity models
     * @return unified validation report
     */
    public GenerationValidationReport run(
            String inputFile,
            String outputDir,
            String basePackage,
            List<Table> parsedTables,
            List<Table> javaGenerationTables,
            List<Entity> models
    ) {
        GenerationValidationReport report = new GenerationValidationReport(inputFile, outputDir, basePackage);

        appendInputSection(report, inputFile, outputDir, basePackage, parsedTables, javaGenerationTables, models);
        appendGeneratedCountsSection(report, javaGenerationTables, models, outputDir, basePackage);
        appendLiquibaseSection(report, outputDir);
        appendSchemaValidationChecklistSection(report);
        appendTodoEntitiesSection(report);
        appendSchemaValidationSection(report);

        return report;
    }

    /**
     * Appends the schema validation checklist section.
     *
     * @param report target report
     */
    private void appendSchemaValidationChecklistSection(GenerationValidationReport report) {
        List<String> details = new ArrayList<>();
        List<String> violations = new ArrayList<>();

        try {
            EntitySchemaValidator validator = new EntitySchemaValidator();
            List<String> checklist = validator.getValidationChecklistLines();

            details.add("Total checks: " + checklist.size());
            details.addAll(checklist);
        } catch (Exception exception) {
            violations.add("Could not build schema validation checklist: " + exception.getMessage());
        }

        report.addSection("Schema Validation Checklist", details, violations);
    }

    /**
     * Appends schema validation results into the report.
     *
     * @param report target report
     */
    private void appendSchemaValidationSection(GenerationValidationReport report) {
        List<String> details = new ArrayList<>();
        List<String> violations = new ArrayList<>();

        try {
            EntitySchemaValidationService service = new EntitySchemaValidationService();
            List<String> results = service.validate();

            details.add("Schema validation executed.");

            if (results.isEmpty()) {
                details.add("No schema violations detected.");
            } else {
                details.add("Schema violations detected: " + results.size());
                violations.addAll(results);
            }
        } catch (Exception exception) {
            violations.add("Schema validation failed: " + exception.getMessage());
        }

        report.addSection("Schema Validation", details, violations);
    }

    /**
     * Appends the input summary section.
     *
     * @param report target report
     * @param inputFile input SQL file path
     * @param outputDir output directory
     * @param basePackage base package
     * @param parsedTables parsed tables
     * @param javaGenerationTables Java-generation tables
     * @param models generated models
     */
    private void appendInputSection(
            GenerationValidationReport report,
            String inputFile,
            String outputDir,
            String basePackage,
            List<Table> parsedTables,
            List<Table> javaGenerationTables,
            List<Entity> models
    ) {
        List<String> details = new ArrayList<>();
        List<String> violations = new ArrayList<>();

        details.add("Input file: " + inputFile);
        details.add("Output directory: " + outputDir);
        details.add("Base package: " + basePackage);
        details.add("Parsed tables: " + safeSize(parsedTables));
        details.add("Java generation tables: " + safeSize(javaGenerationTables));
        details.add("Generated entity models: " + safeSize(models));

        if (isBlank(inputFile)) {
            violations.add("Input file path is blank.");
        }
        if (isBlank(outputDir)) {
            violations.add("Output directory is blank.");
        }
        if (isBlank(basePackage)) {
            violations.add("Base package is blank.");
        }
        if (parsedTables == null || parsedTables.isEmpty()) {
            violations.add("Parsed tables list is empty.");
        }
        if (models == null || models.isEmpty()) {
            violations.add("Generated entity model list is empty.");
        }

        report.addSection("Generation Inputs", details, violations);
    }


    /**
     * Appends generated file count information.
     *
     * @param report target report
     * @param javaGenerationTables Java-generation tables
     * @param models generated models
     * @param outputDir output directory
     * @param basePackage base package
     */
    private void appendGeneratedCountsSection(
            GenerationValidationReport report,
            List<Table> javaGenerationTables,
            List<Entity> models,
            String outputDir,
            String basePackage
    ) {
        List<String> details = new ArrayList<>();
        List<String> violations = new ArrayList<>();

        Path entityDir = PackageResolver.resolvePath(outputDir, basePackage, "entity");
        Path dtoDir = PackageResolver.resolvePath(outputDir, basePackage, "dto");
        Path repositoryDir = PackageResolver.resolvePath(outputDir, basePackage, "repository");
        Path mapperDir = PackageResolver.resolvePath(outputDir, basePackage, "mapper");
        Path serviceDir = PackageResolver.resolvePath(outputDir, basePackage, "service");
        Path serviceImplDir = PackageResolver.resolvePath(outputDir, basePackage, "serviceImpl");
        Path controllerDir = PackageResolver.resolvePath(outputDir, basePackage, "controller");

        int expectedJavaArtifacts = safeSize(javaGenerationTables);
        int expectedEntityFiles = safeSize(models);

        int entityFileCount = countJavaFiles(entityDir);
        int dtoFileCount = countJavaFiles(dtoDir);
        int repositoryFileCount = countJavaFiles(repositoryDir);
        int mapperFileCount = countJavaFiles(mapperDir);
        int serviceFileCount = countJavaFiles(serviceDir);
        int serviceImplFileCount = countJavaFiles(serviceImplDir);
        int controllerFileCount = countJavaFiles(controllerDir);

        details.add("Expected generated table count: " + expectedJavaArtifacts);
        details.add("Expected generated entity model count: " + expectedEntityFiles);
        details.add("Entity java files: " + entityFileCount);
        details.add("DTO java files: " + dtoFileCount);
        details.add("Repository java files: " + repositoryFileCount);
        details.add("Mapper java files: " + mapperFileCount);
        details.add("Service java files: " + serviceFileCount);
        details.add("ServiceImpl java files: " + serviceImplFileCount);
        details.add("Controller java files: " + controllerFileCount);

        if (expectedJavaArtifacts > 0 && repositoryFileCount == 0) {
            violations.add("No repository files were generated.");
        }
        if (expectedJavaArtifacts > 0 && mapperFileCount == 0) {
            violations.add("No mapper files were generated.");
        }
        if (expectedJavaArtifacts > 0 && serviceFileCount == 0) {
            violations.add("No service files were generated.");
        }
        if (expectedJavaArtifacts > 0 && serviceImplFileCount == 0) {
            violations.add("No service implementation files were generated.");
        }
        if (expectedJavaArtifacts > 0 && controllerFileCount == 0) {
            violations.add("No controller files were generated.");
        }
        if (expectedEntityFiles > 0 && entityFileCount == 0) {
            violations.add("No entity files were generated.");
        }
        if (expectedEntityFiles > 0 && dtoFileCount == 0) {
            violations.add("No DTO files were generated.");
        }

        report.addSection("Generated File Counts", details, violations);
    }

    /**
     * Appends the Liquibase section.
     *
     * @param report target report
     * @param outputDir output directory
     */
    private void appendLiquibaseSection(
            GenerationValidationReport report,
            String outputDir
    ) {
        List<String> details = new ArrayList<>();
        List<String> violations = new ArrayList<>();

        Path changelogRoot = Paths.get(
                outputDir,
                "src",
                "main",
                "resources",
                "db",
                "migration",
                "changelogs",
                "v0.1.0"
        );

        details.add("Liquibase changelog root: " + changelogRoot.toAbsolutePath());
        details.add("Liquibase xml file count: " + countXmlFiles(changelogRoot));

        validateDirectoryExists(changelogRoot, "Liquibase changelog directory", violations);

        report.addSection("Liquibase Output", details, violations);
    }

    /**
     * Validates that a directory exists.
     *
     * @param directory directory path
     * @param label human-readable label
     * @param violations target violations
     */
    private void validateDirectoryExists(Path directory, String label, List<String> violations) {
        if (!Files.exists(directory)) {
            violations.add(label + " does not exist: " + directory.toAbsolutePath());
            return;
        }

        if (!Files.isDirectory(directory)) {
            violations.add(label + " is not a directory: " + directory.toAbsolutePath());
        }
    }


    /**
     * Counts Java source files in a directory.
     *
     * @param directory source directory
     * @return Java file count
     */
    private int countJavaFiles(Path directory) {
        if (!Files.exists(directory) || !Files.isDirectory(directory)) {
            return 0;
        }

        try (var paths = Files.walk(directory)) {
            return (int) paths
                    .filter(Files::isRegularFile)
                    .filter(path -> path.toString().endsWith(".java"))
                    .count();
        } catch (Exception exception) {
            return 0;
        }
    }

    /**
     * Counts XML files in a directory.
     *
     * @param directory target directory
     * @return XML file count
     */
    private int countXmlFiles(Path directory) {
        if (!Files.exists(directory) || !Files.isDirectory(directory)) {
            return 0;
        }

        try (var paths = Files.walk(directory)) {
            return (int) paths
                    .filter(Files::isRegularFile)
                    .filter(path -> path.toString().endsWith(".xml"))
                    .count();
        } catch (Exception exception) {
            return 0;
        }
    }

    /**
     * Appends the generated entities with TODO comments section.
     *
     * @param report target report
     */
    private void appendTodoEntitiesSection(GenerationValidationReport report) {
        List<String> details = new ArrayList<>();
        List<String> violations = new ArrayList<>();

        try {
            EntitySchemaValidator validator = new EntitySchemaValidator();
            List<String> todoEntities = validator.findEntityDisplayNamesWithTodoComments();

            details.add("Total classes with TODO: " + todoEntities.size());

            if (todoEntities.isEmpty()) {
                details.add("No generated entity classes with TODO comments.");
            } else {
                details.addAll(todoEntities);
            }
        } catch (Exception exception) {
            violations.add("Could not collect generated entity classes with TODO comments: " + exception.getMessage());
        }

        report.addSection("Generated Entity Classes With TODO Comments", details, violations);
    }

    /**
     * Returns a safe size for a list.
     *
     * @param values source list
     * @return list size or zero
     */
    private int safeSize(List<?> values) {
        return values == null ? 0 : values.size();
    }

    /**
     * Returns whether a string is blank.
     *
     * @param value source value
     * @return true when blank
     */
    private boolean isBlank(String value) {
        return value == null || value.isBlank();
    }
}