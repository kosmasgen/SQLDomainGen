package com.sqldomaingen.generator;

import com.sqldomaingen.model.Column;
import com.sqldomaingen.model.Table;
import com.sqldomaingen.util.NamingConverter;
import com.sqldomaingen.util.PackageResolver;
import lombok.extern.log4j.Log4j2;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Objects;

@Log4j2
public class RepositoryGenerator {

    /**
     * Backwards-compatible helper used by other generators (e.g. ControllerGenerator).
     * Returns the primary key type as a simple name (Long, Integer, UUID, etc).
     *
     * @param table table metadata
     * @return primary key Java type (simple name)
     */
    public static String detectPrimaryKeyTypeSimple(Table table) {
        return detectPrimaryKeyType(table);
    }

    /**
     * Generates Spring Data JPA repositories for all tables.
     *
     * @param tables      parsed SQL tables
     * @param outputDir   base output directory (project root) where src/main/java will be created
     * @param basePackage base package (e.g. gr.knowledge.schoolmanagement)
     * @param overwrite   overwrite existing files
     */
    public void generateRepositories(List<Table> tables, String outputDir, String basePackage, boolean overwrite) {
        Objects.requireNonNull(tables, "tables must not be null");
        Objects.requireNonNull(outputDir, "outputDir must not be null");
        Objects.requireNonNull(basePackage, "basePackage must not be null");

        Path repositoriesDir = ensureOutputDirectory(outputDir, basePackage);

        for (Table table : tables) {
            String entityName = NamingConverter.toPascalCase(normalizeTableName(table.getName()));
            String repositoryCode = generateRepositoryForTable(table, basePackage);
            writeRepositoryToFile(repositoriesDir, repositoryCode, entityName, overwrite);
        }

        log.info("✅ Repository generation completed. Output directory: {}", repositoriesDir.toAbsolutePath());
    }

    /**
     * Backwards-compatible overload (defaults to overwrite = true).
     */
    public void generateRepositories(List<Table> tables, String outputDir, String basePackage) {
        generateRepositories(tables, outputDir, basePackage, true);
    }

    /**
     * Generates repository code for a single table.
     *
     * Output:
     * package {basePackage}.repository;
     * import {basePackage}.entity.{Entity};
     * public interface {Entity}Repository extends JpaRepository<{Entity}, {PK}> {}
     */
    public String generateRepositoryForTable(Table table, String basePackage) {
        Objects.requireNonNull(table, "table must not be null");
        Objects.requireNonNull(basePackage, "basePackage must not be null");

        String entityName = NamingConverter.toPascalCase(normalizeTableName(table.getName()));
        String repositoryName = entityName + "Repository";

        String pkType = detectPrimaryKeyType(table);
        boolean needsUuidImport = needsUuidImport(table);

        String repositoryPackage = PackageResolver.resolvePackageName(basePackage, "repository");
        String entityPackage = PackageResolver.resolvePackageName(basePackage, "entity");

        StringBuilder sb = new StringBuilder();

        sb.append("package ").append(repositoryPackage).append(";\n\n");
        sb.append("import ").append(entityPackage).append(".").append(entityName).append(";\n");
        sb.append("import org.springframework.data.jpa.repository.JpaRepository;\n");
        sb.append("import org.springframework.stereotype.Repository;\n");
        if (needsUuidImport) {
            sb.append("import java.util.UUID;\n");
        }
        sb.append("\n");

        sb.append("@Repository\n");
        sb.append("public interface ").append(repositoryName)
                .append(" extends JpaRepository<")
                .append(entityName).append(", ").append(pkType).append("> {\n");
        sb.append("}\n");

        return sb.toString();
    }

    /**
     * Detects primary key type as a simple class name (Long, Integer, UUID, etc).
     *
     * @param table table metadata
     * @return simple type name for PK
     */
    public static String detectPrimaryKeyType(Table table) {
        Column pk = table.getColumns().stream()
                .filter(Objects::nonNull)
                .filter(Column::isPrimaryKey)
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("No Primary Key found for table: " + table.getName()));

        String raw = pk.getJavaType();
        if (raw == null || raw.isBlank()) {
            return "Long";
        }

        String t = raw.trim();

        if (t.equals("long") || t.equals("java.lang.Long")) return "Long";
        if (t.equals("int") || t.equals("java.lang.Integer")) return "Integer";

        if (t.contains(".")) {
            return t.substring(t.lastIndexOf('.') + 1);
        }

        return t;
    }

    private static boolean needsUuidImport(Table table) {
        Column pk = table.getColumns().stream()
                .filter(Objects::nonNull)
                .filter(Column::isPrimaryKey)
                .findFirst()
                .orElse(null);

        if (pk == null) return false;

        String raw = pk.getJavaType();
        if (raw == null) return false;

        String t = raw.trim();
        return t.equals("UUID") || t.equalsIgnoreCase("java.util.UUID") || t.endsWith(".UUID");
    }

    private static String normalizeTableName(String raw) {
        if (raw == null) return "";
        String s = raw.trim();

        int dot = s.lastIndexOf('.');
        if (dot >= 0 && dot < s.length() - 1) {
            s = s.substring(dot + 1);
        }
        return s;
    }

    private Path ensureOutputDirectory(String outputDir, String basePackage) {
        Path path = PackageResolver.resolvePath(outputDir, basePackage, "repository");
        try {
            Files.createDirectories(path);
            return path;
        } catch (IOException e) {
            throw new IllegalStateException("Failed to create output directory: " + path, e);
        }
    }

    private void writeRepositoryToFile(Path repositoriesDir, String code, String entityName, boolean overwrite) {
        String fileName = entityName + "Repository.java";
        Path filePath = repositoriesDir.resolve(fileName);

        if (!overwrite && Files.exists(filePath)) {
            log.info("ℹ️ Repository exists, skipping (overwrite=false): {}", filePath.toAbsolutePath());
            return;
        }

        try {
            Files.writeString(filePath, code, StandardCharsets.UTF_8);
            log.info("✅ Repository generated: {}", filePath.toAbsolutePath());
        } catch (IOException e) {
            log.error("❌ Failed to write repository file: {}", filePath, e);
        }
    }
}
