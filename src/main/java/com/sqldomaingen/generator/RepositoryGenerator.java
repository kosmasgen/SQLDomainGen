package com.sqldomaingen.generator;

import com.sqldomaingen.model.Column;
import com.sqldomaingen.model.Table;
import com.sqldomaingen.util.NamingConverter;
import com.sqldomaingen.util.PackageResolver;
import lombok.extern.log4j.Log4j2;

import com.sqldomaingen.util.GeneratorSupport;
import java.nio.file.Path;
import java.util.List;
import java.util.Objects;

@Log4j2
public class RepositoryGenerator {



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

        Path repositoriesDir = GeneratorSupport.ensureDirectory(
                PackageResolver.resolvePath(outputDir, basePackage, "repository")
        );

        for (Table table : tables) {
            String entityName = NamingConverter.toPascalCase(
                    GeneratorSupport.normalizeTableName(table.getName())
            );
            String repositoryCode = generateRepositoryForTable(table, basePackage);
            Path filePath = repositoriesDir.resolve(entityName + "Repository.java");
            GeneratorSupport.writeFile(filePath, repositoryCode, overwrite);
        }

        log.info(" Repository generation completed. Output directory: {}", repositoriesDir.toAbsolutePath());
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
     * public interface {Entity}Repository extends Jpa Repository<{Entity}, {PK}> {}
     *
     * @param table table metadata
     * @param basePackage base Java package
     * @return generated repository source code
     */
    public String generateRepositoryForTable(Table table, String basePackage) {
        Objects.requireNonNull(table, "table must not be null");
        Objects.requireNonNull(basePackage, "basePackage must not be null");

        String entityName = NamingConverter.toPascalCase(
                GeneratorSupport.normalizeTableName(table.getName())
        );
        String repositoryName = entityName + "Repository";

        String repositoryPackage = PackageResolver.resolvePackageName(basePackage, "repository");
        String entityPackage = PackageResolver.resolvePackageName(basePackage, "entity");

        TypeRef pkTypeRef = detectPrimaryKeyTypeRef(table, entityName, entityPackage);

        StringBuilder builder = new StringBuilder();

        builder.append("package ").append(repositoryPackage).append(";\n\n");
        builder.append("import ").append(entityPackage).append(".").append(entityName).append(";\n");
        builder.append("import org.springframework.data.jpa.repository.JpaRepository;\n");
        builder.append("import org.springframework.stereotype.Repository;\n");

        if (pkTypeRef.importLine() != null) {
            builder.append(pkTypeRef.importLine()).append("\n");
        }

        builder.append("\n");

        builder.append("@Repository\n");
        builder.append("public interface ").append(repositoryName)
                .append(" extends JpaRepository<")
                .append(entityName).append(", ").append(pkTypeRef.simpleName()).append("> {\n");
        builder.append("}\n");

        return builder.toString();
    }

    /**
     * Returns all primary key columns defined for the given table.
     *
     * @param table table metadata
     * @return list of primary key columns
     */
    private static List<Column> getPrimaryKeyColumns(Table table) {
        Objects.requireNonNull(table, "table must not be null");

        return table.getColumns().stream()
                .filter(Objects::nonNull)
                .filter(Column::isPrimaryKey)
                .toList();
    }

    /**
     * Determines whether the table uses a composite primary key.
     *
     * @param table table metadata
     * @return true when the table has more than one primary key column
     */
    private static boolean hasCompositePrimaryKey(Table table) {
        return getPrimaryKeyColumns(table).size() > 1;
    }


    /**
     * Resolves the primary key type reference for repository generation.
     *
     * @param table table metadata
     * @param entityName entity simple name
     * @param entityPackage entity package name
     * @return primary key type reference containing simple type name and optional import
     */
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

        Column primaryKeyColumn = pkColumns.get(0);

        String rawType = primaryKeyColumn.getJavaType();
        if (rawType == null || rawType.isBlank()) {
            return new TypeRef("Long", null);
        }

        String normalizedType = rawType.trim();

        if ("UUID".equalsIgnoreCase(normalizedType)
                || "java.util.UUID".equals(normalizedType)
                || normalizedType.endsWith(".UUID")) {
            return new TypeRef("UUID", "import java.util.UUID;");
        }

        if ("BigDecimal".equals(normalizedType) || "java.math.BigDecimal".equals(normalizedType)) {
            return new TypeRef("BigDecimal", "import java.math.BigDecimal;");
        }

        if ("long".equalsIgnoreCase(normalizedType) || "java.lang.Long".equals(normalizedType)) {
            return new TypeRef("Long", null);
        }
        if ("int".equalsIgnoreCase(normalizedType) || "java.lang.Integer".equals(normalizedType)) {
            return new TypeRef("Integer", null);
        }
        if ("short".equalsIgnoreCase(normalizedType) || "java.lang.Short".equals(normalizedType)) {
            return new TypeRef("Short", null);
        }
        if ("byte".equalsIgnoreCase(normalizedType) || "java.lang.Byte".equals(normalizedType)) {
            return new TypeRef("Byte", null);
        }

        if (normalizedType.contains(".")) {
            String simpleTypeName = normalizedType.substring(normalizedType.lastIndexOf('.') + 1);
            return new TypeRef(simpleTypeName, "import " + normalizedType + ";");
        }

        return new TypeRef(normalizedType, null);
    }


    /**
     * Holds a resolved type name and its optional import line.
     *
     * @param simpleName simple type name
     * @param importLine optional import line
     */
    private record TypeRef(String simpleName, String importLine) {
    }
}
