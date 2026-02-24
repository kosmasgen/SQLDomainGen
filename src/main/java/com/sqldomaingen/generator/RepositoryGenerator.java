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

        String repositoryPackage = PackageResolver.resolvePackageName(basePackage, "repository");
        String entityPackage = PackageResolver.resolvePackageName(basePackage, "entity");

        TypeRef pkTypeRef = detectPrimaryKeyTypeRef(table, entityName, entityPackage);

        StringBuilder sb = new StringBuilder();

        sb.append("package ").append(repositoryPackage).append(";\n\n");
        sb.append("import ").append(entityPackage).append(".").append(entityName).append(";\n");
        sb.append("import org.springframework.data.jpa.repository.JpaRepository;\n");
        sb.append("import org.springframework.stereotype.Repository;\n");

        if (pkTypeRef.importLine != null) {
            sb.append(pkTypeRef.importLine).append("\n");
        }

        sb.append("\n");

        sb.append("@Repository\n");
        sb.append("public interface ").append(repositoryName)
                .append(" extends JpaRepository<")
                .append(entityName).append(", ").append(pkTypeRef.simpleName).append("> {\n");
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
        Objects.requireNonNull(table, "table must not be null");

        List<Column> pkColumns = getPrimaryKeyColumns(table);

        if (pkColumns.isEmpty()) {
            throw new IllegalStateException("No Primary Key found for table: " + table.getName());
        }

        // Backward-compatible behavior:
        // For composite PK, return generic "Object" (other generators should use composite-aware methods)
        if (pkColumns.size() > 1) {
            return "Object";
        }

        Column pk = pkColumns.get(0);

        String raw = pk.getJavaType();
        if (raw == null || raw.isBlank()) {
            return "Long";
        }

        String t = raw.trim();

        // UUID
        if ("UUID".equalsIgnoreCase(t) || "java.util.UUID".equals(t) || t.endsWith(".UUID")) {
            return "UUID";
        }

        // numeric(...) PKs often mapped by parser to BigDecimal -> prefer Long (your rule)
        if ("BigDecimal".equals(t) || "java.math.BigDecimal".equals(t)) {
            return "Long";
        }

        // primitives / boxed
        if ("long".equalsIgnoreCase(t) || "java.lang.Long".equals(t)) return "Long";
        if ("int".equalsIgnoreCase(t) || "java.lang.Integer".equals(t)) return "Integer";
        if ("short".equalsIgnoreCase(t) || "java.lang.Short".equals(t)) return "Short";
        if ("byte".equalsIgnoreCase(t) || "java.lang.Byte".equals(t)) return "Byte";

        // FQCN -> simple name
        if (t.contains(".")) {
            return t.substring(t.lastIndexOf('.') + 1);
        }

        return t;
    }

    private static boolean needsUuidImport(Table table) {
        List<Column> pkColumns = getPrimaryKeyColumns(table);

        // Composite PK uses PK class import, not UUID directly in repository generic type
        if (pkColumns.size() != 1) {
            return false;
        }

        String raw = pkColumns.get(0).getJavaType();
        if (raw == null) return false;

        String t = raw.trim();
        return t.equalsIgnoreCase("UUID")
                || t.equals("java.util.UUID")
                || t.endsWith(".UUID");
    }

    private static List<Column> getPrimaryKeyColumns(Table table) {
        Objects.requireNonNull(table, "table must not be null");

        return table.getColumns().stream()
                .filter(Objects::nonNull)
                .filter(Column::isPrimaryKey)
                .toList();
    }

    private static boolean hasCompositePrimaryKey(Table table) {
        return getPrimaryKeyColumns(table).size() > 1;
    }

    private static TypeRef detectPrimaryKeyTypeRef(Table table, String entityName, String entityPackage) {
        Objects.requireNonNull(table, "table must not be null");
        Objects.requireNonNull(entityName, "entityName must not be null");
        Objects.requireNonNull(entityPackage, "entityPackage must not be null");

        if (hasCompositePrimaryKey(table)) {
            String pkClassName = entityName + "PK";
            return new TypeRef(pkClassName, "import " + entityPackage + "." + pkClassName + ";");
        }

        List<Column> pkColumns = getPrimaryKeyColumns(table);
        if (pkColumns.isEmpty()) {
            throw new IllegalStateException("No Primary Key found for table: " + table.getName());
        }

        Column pk = pkColumns.get(0);

        String raw = pk.getJavaType();
        if (raw == null || raw.isBlank()) {
            return new TypeRef("Long", null);
        }

        String t = raw.trim();

        if ("UUID".equalsIgnoreCase(t) || "java.util.UUID".equals(t) || t.endsWith(".UUID")) {
            return new TypeRef("UUID", "import java.util.UUID;");
        }

        // numeric(...) PKs -> Long
        if ("BigDecimal".equals(t) || "java.math.BigDecimal".equals(t)) {
            return new TypeRef("Long", null);
        }

        if ("long".equalsIgnoreCase(t) || "java.lang.Long".equals(t)) return new TypeRef("Long", null);
        if ("int".equalsIgnoreCase(t) || "java.lang.Integer".equals(t)) return new TypeRef("Integer", null);
        if ("short".equalsIgnoreCase(t) || "java.lang.Short".equals(t)) return new TypeRef("Short", null);
        if ("byte".equalsIgnoreCase(t) || "java.lang.Byte".equals(t)) return new TypeRef("Byte", null);

        if (t.contains(".")) {
            String simple = t.substring(t.lastIndexOf('.') + 1);
            return new TypeRef(simple, "import " + t + ";");
        }

        return new TypeRef(t, null);
    }


    private static final class TypeRef {
        private final String simpleName;
        private final String importLine;

        private TypeRef(String simpleName, String importLine) {
            this.simpleName = simpleName;
            this.importLine = importLine;
        }
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
