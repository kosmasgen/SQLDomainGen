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

/**
 * Generates Service interfaces and ServiceImpl classes (domain style),
 * using the project's base package and placing output under src/main/java.
 * Conventions:
 * - service package:     {basePackage}.service
 * - serviceImpl package: {basePackage}.serviceImpl
 * - DTO suffix:          Dto
 * - Repository suffix:   Repository
 * - Mapper suffix:       Mapper
 */
@Log4j2
public class ServiceGenerator {

    private static final String DTO_SUFFIX = "Dto";

    /**
     * Generates service interfaces + implementations for all parsed tables.
     *
     * @param tables      parsed SQL tables (non-null)
     * @param outputDir   base output directory (project root output)
     * @param basePackage base package (e.g. gr.knowledge.arch.med_heritage)
     */
    public void generateAllServices(List<Table> tables, String outputDir, String basePackage) {
        Objects.requireNonNull(tables, "tables must not be null");
        Objects.requireNonNull(outputDir, "outputDir must not be null");
        Objects.requireNonNull(basePackage, "basePackage must not be null");

        Path serviceDir = ensureDirectory(PackageResolver.resolvePath(outputDir, basePackage, "service"));
        Path serviceImplDir = ensureDirectory(PackageResolver.resolvePath(outputDir, basePackage, "serviceImpl"));

        for (Table table : tables) {
            String entityName = NamingConverter.toPascalCase(normalizeTableName(table.getName()));

            String serviceInterfaceCode = generateServiceInterface(table, basePackage);
            writeToFile(serviceDir, entityName + "Service.java", serviceInterfaceCode);

            String serviceImplCode = generateServiceImpl(table, basePackage);
            writeToFile(serviceImplDir, entityName + "ServiceImpl.java", serviceImplCode);
        }

        log.info("✅ Services generated under: {}", serviceDir.getParent().toAbsolutePath());
    }

    /**
     * Generates the Service interface for one entity (domain style method names).
     *
     * @param table       source table metadata
     * @param basePackage base package
     * @return Java source code
     */
    public String generateServiceInterface(Table table, String basePackage) {
        Objects.requireNonNull(table, "table must not be null");
        Objects.requireNonNull(basePackage, "basePackage must not be null");

        String entityName = NamingConverter.toPascalCase(normalizeTableName(table.getName()));
        String dtoName = entityName + DTO_SUFFIX;

        TypeRef pkType = detectPrimaryKeyType(table);

        String servicePackage = PackageResolver.resolvePackageName(basePackage, "service");
        String dtoPackage = PackageResolver.resolvePackageName(basePackage, "dto");

        StringBuilder sb = new StringBuilder();

        sb.append("package ").append(servicePackage).append(";\n\n");
        sb.append("import ").append(dtoPackage).append(".").append(dtoName).append(";\n");
        if (pkType.importLine != null) {
            sb.append(pkType.importLine).append("\n");
        }
        sb.append("import java.util.List;\n\n");

        sb.append("/**\n");
        sb.append(" * Service contract for {@code ").append(entityName).append("} domain operations.\n");
        sb.append(" */\n");
        sb.append("public interface ").append(entityName).append("Service {\n\n");

        sb.append("    /**\n");
        sb.append("     * Retrieves all records.\n");
        sb.append("     *\n");
        sb.append("     * @return non-null list of {@link ").append(dtoName).append("}\n");
        sb.append("     */\n");
        sb.append("    List<").append(dtoName).append("> getAll").append(entityName).append("();\n\n");

        sb.append("    /**\n");
        sb.append("     * Retrieves a record by id.\n");
        sb.append("     *\n");
        sb.append("     * @param id the record id\n");
        sb.append("     * @return the matching {@link ").append(dtoName).append("}\n");
        sb.append("     */\n");
        sb.append("    ").append(dtoName).append(" get").append(entityName).append("ById(").append(pkType.simpleName).append(" id);\n\n");

        sb.append("    /**\n");
        sb.append("     * Creates a new record.\n");
        sb.append("     *\n");
        sb.append("     * @param dto input payload\n");
        sb.append("     * @return created {@link ").append(dtoName).append("}\n");
        sb.append("     */\n");
        sb.append("    ").append(dtoName).append(" create").append(entityName).append("(").append(dtoName).append(" dto);\n\n");

        sb.append("    /**\n");
        sb.append("     * Updates an existing record.\n");
        sb.append("     *\n");
        sb.append("     * Note: current implementation performs a full update (PUT-style).\n");
        sb.append("     * PATCH behavior (merge non-null fields) can be added via ModelMapper config.\n");
        sb.append("     *\n");
        sb.append("     * @param id  the record id\n");
        sb.append("     * @param dto input payload\n");
        sb.append("     * @return updated {@link ").append(dtoName).append("}\n");
        sb.append("     */\n");
        sb.append("    ").append(dtoName).append(" update").append(entityName).append("(").append(pkType.simpleName).append(" id, ").append(dtoName).append(" dto);\n\n");

        sb.append("    /**\n");
        sb.append("     * Deletes a record by id.\n");
        sb.append("     *\n");
        sb.append("     * @param id the record id\n");
        sb.append("     */\n");
        sb.append("    void delete").append(entityName).append("(").append(pkType.simpleName).append(" id);\n");

        sb.append("}\n");

        return sb.toString();
    }

    /**
     * Generates the ServiceImpl class for one entity (domain style + JavaDoc).
     *
     * @param table       source table metadata
     * @param basePackage base package
     * @return Java source code
     */
    public String generateServiceImpl(Table table, String basePackage) {
        Objects.requireNonNull(table, "table must not be null");
        Objects.requireNonNull(basePackage, "basePackage must not be null");

        String entityName = NamingConverter.toPascalCase(normalizeTableName(table.getName()));
        String dtoName = entityName + DTO_SUFFIX;
        String repositoryName = entityName + "Repository";
        String mapperName = entityName + "Mapper";
        String serviceName = entityName + "Service";

        String repositoryVar = NamingConverter.decapitalizeFirstLetter(entityName) + "Repository";
        String mapperVar = NamingConverter.decapitalizeFirstLetter(entityName) + "Mapper";

        TypeRef pkType = detectPrimaryKeyType(table);

        String pkFieldName = detectPrimaryKeyFieldName(table);
        String pkSetterMethod = "set" + toPascalCase(pkFieldName);

        String serviceImplPackage = PackageResolver.resolvePackageName(basePackage, "serviceImpl");
        String dtoPackage = PackageResolver.resolvePackageName(basePackage, "dto");
        String mapperPackage = PackageResolver.resolvePackageName(basePackage, "mapper");
        String modelPackage = PackageResolver.resolvePackageName(basePackage, "entity");
        String repositoryPackage = PackageResolver.resolvePackageName(basePackage, "repository");
        String servicePackage = PackageResolver.resolvePackageName(basePackage, "service");

        StringBuilder sb = new StringBuilder();

        sb.append("package ").append(serviceImplPackage).append(";\n\n");

        sb.append("import ").append(dtoPackage).append(".").append(dtoName).append(";\n");
        sb.append("import ").append(mapperPackage).append(".").append(mapperName).append(";\n");
        sb.append("import ").append(modelPackage).append(".").append(entityName).append(";\n");
        sb.append("import ").append(repositoryPackage).append(".").append(repositoryName).append(";\n");
        sb.append("import ").append(servicePackage).append(".").append(serviceName).append(";\n");

        if (pkType.importLine != null) {
            sb.append(pkType.importLine).append("\n");
        }

        sb.append("import jakarta.transaction.Transactional;\n");
        sb.append("import lombok.RequiredArgsConstructor;\n");
        sb.append("import lombok.extern.log4j.Log4j2;\n");
        sb.append("import org.springframework.http.HttpStatus;\n");
        sb.append("import org.springframework.stereotype.Service;\n");
        sb.append("import org.springframework.web.server.ResponseStatusException;\n\n");
        sb.append("import java.util.List;\n\n");

        sb.append("/**\n");
        sb.append(" * Service implementation for {@code ").append(entityName).append("} domain operations.\n");
        sb.append(" */\n");
        sb.append("@Service\n");
        sb.append("@RequiredArgsConstructor\n");
        sb.append("@Transactional\n");
        sb.append("@Log4j2\n");
        sb.append("public class ").append(entityName).append("ServiceImpl implements ").append(serviceName).append(" {\n\n");

        sb.append("    private final ").append(repositoryName).append(" ").append(repositoryVar).append(";\n");
        sb.append("    private final ").append(mapperName).append(" ").append(mapperVar).append(";\n\n");

        sb.append("    /**\n");
        sb.append("     * Retrieves all records.\n");
        sb.append("     *\n");
        sb.append("     * @return non-null list of {@link ").append(dtoName).append("}\n");
        sb.append("     */\n");
        sb.append("    @Override\n");
        sb.append("    public List<").append(dtoName).append("> getAll").append(entityName).append("() {\n");
        sb.append("        log.info(\"Fetching all ").append(NamingConverter.toKebabCase(entityName)).append(".\");\n");
        sb.append("        return ").append(mapperVar).append(".toDTO(").append(repositoryVar).append(".findAll());\n");
        sb.append("    }\n\n");

        sb.append("    /**\n");
        sb.append("     * Retrieves a record by id.\n");
        sb.append("     *\n");
        sb.append("     * @param id the record id\n");
        sb.append("     * @return the matching {@link ").append(dtoName).append("}\n");
        sb.append("     */\n");
        sb.append("    @Override\n");
        sb.append("    public ").append(dtoName).append(" get").append(entityName).append("ById(").append(pkType.simpleName).append(" id) {\n");
        sb.append("        log.info(\"Fetching ").append(NamingConverter.toKebabCase(entityName)).append(" with id: {}\", id);\n");
        sb.append("        ").append(entityName).append(" entity = ").append(repositoryVar).append(".findById(id)\n");
        sb.append("                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, \"")
                .append(entityName).append(" not found with id: \" + id));\n");
        sb.append("        return ").append(mapperVar).append(".toDTO(entity);\n");
        sb.append("    }\n\n");

        sb.append("    /**\n");
        sb.append("     * Creates a new record.\n");
        sb.append("     *\n");
        sb.append("     * @param dto input payload\n");
        sb.append("     * @return created {@link ").append(dtoName).append("}\n");
        sb.append("     */\n");
        sb.append("    @Override\n");
        sb.append("    public ").append(dtoName).append(" create").append(entityName).append("(").append(dtoName).append(" dto) {\n");
        sb.append("        log.info(\"Creating ").append(NamingConverter.toKebabCase(entityName)).append(".\");\n");
        sb.append("        ").append(entityName).append(" entity = ").append(mapperVar).append(".toEntity(dto);\n");
        sb.append("        ").append(entityName).append(" saved = ").append(repositoryVar).append(".save(entity);\n");
        sb.append("        return ").append(mapperVar).append(".toDTO(saved);\n");
        sb.append("    }\n\n");

        sb.append("    /**\n");
        sb.append("     * Updates an existing record.\n");
        sb.append("     *\n");
        sb.append("     * Note: current implementation performs a full update (PUT-style).\n");
        sb.append("     * PATCH behavior (merge non-null fields) can be added via ModelMapper config.\n");
        sb.append("     *\n");
        sb.append("     * @param id  the record id\n");
        sb.append("     * @param dto input payload\n");
        sb.append("     * @return updated {@link ").append(dtoName).append("}\n");
        sb.append("     */\n");
        sb.append("    @Override\n");
        sb.append("    public ").append(dtoName).append(" update").append(entityName).append("(").append(pkType.simpleName).append(" id, ").append(dtoName).append(" dto) {\n");
        sb.append("        log.info(\"Updating ").append(NamingConverter.toKebabCase(entityName)).append(" with id: {}\", id);\n");
        sb.append("        ").append(repositoryVar).append(".findById(id)\n");
        sb.append("                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, \"")
                .append(entityName).append(" not found with id: \" + id));\n");
        sb.append("        ").append(entityName).append(" entity = ").append(mapperVar).append(".toEntity(dto);\n");
        sb.append("        entity.").append(pkSetterMethod).append("(id);\n");
        sb.append("        ").append(entityName).append(" saved = ").append(repositoryVar).append(".save(entity);\n");
        sb.append("        return ").append(mapperVar).append(".toDTO(saved);\n");
        sb.append("    }\n\n");

        sb.append("    /**\n");
        sb.append("     * Deletes a record by id.\n");
        sb.append("     *\n");
        sb.append("     * @param id the record id\n");
        sb.append("     */\n");
        sb.append("    @Override\n");
        sb.append("    public void delete").append(entityName).append("(").append(pkType.simpleName).append(" id) {\n");
        sb.append("        log.info(\"Deleting ").append(NamingConverter.toKebabCase(entityName)).append(" with id: {}\", id);\n");
        sb.append("        ").append(repositoryVar).append(".findById(id)\n");
        sb.append("                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, \"")
                .append(entityName).append(" not found with id: \" + id));\n");
        sb.append("        ").append(repositoryVar).append(".deleteById(id);\n");
        sb.append("    }\n");

        sb.append("}\n");

        return sb.toString();
    }

    private static TypeRef detectPrimaryKeyType(Table table) {
        Column pk = table.getColumns().stream()
                .filter(Objects::nonNull)
                .filter(Column::isPrimaryKey)
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("No Primary Key found for table: " + table.getName()));

        String raw = pk.getJavaType();
        if (raw == null || raw.isBlank()) {
            return new TypeRef("Long", null);
        }

        String t = raw.trim();

        if ("UUID".equalsIgnoreCase(t) || "java.util.UUID".equals(t)) {
            return new TypeRef("UUID", "import java.util.UUID;");
        }

        if (t.contains(".")) {
            String simple = t.substring(t.lastIndexOf('.') + 1);
            return new TypeRef(simple, "import " + t + ";");
        }

        if ("long".equalsIgnoreCase(t) || "java.lang.Long".equals(t)) return new TypeRef("Long", null);
        if ("int".equalsIgnoreCase(t) || "java.lang.Integer".equals(t)) return new TypeRef("Integer", null);

        return new TypeRef(t, null);
    }

    private static String detectPrimaryKeyFieldName(Table table) {
        Column pk = table.getColumns().stream()
                .filter(Objects::nonNull)
                .filter(Column::isPrimaryKey)
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("No Primary Key found for table: " + table.getName()));

        String colName = safe(pk.getName());
        if (colName.isEmpty()) {
            return "id";
        }

        // Normalize common cases like schema-qualified names (defensive)
        int dot = colName.lastIndexOf('.');
        if (dot >= 0 && dot < colName.length() - 1) {
            colName = colName.substring(dot + 1);
        }

        return toCamelCase(colName);
    }

    private static String toCamelCase(String raw) {
        String s = safe(raw);
        if (s.isEmpty()) return "id";

        // Handles: request_id, user_id, REQUEST_ID
        String lower = s.toLowerCase();
        String[] parts = lower.split("_+");
        if (parts.length == 0) return "id";

        StringBuilder out = new StringBuilder(parts[0]);
        for (int i = 1; i < parts.length; i++) {
            if (parts[i].isEmpty()) continue;
            out.append(Character.toUpperCase(parts[i].charAt(0)));
            if (parts[i].length() > 1) out.append(parts[i].substring(1));
        }
        return out.toString();
    }

    private static String toPascalCase(String camel) {
        String s = safe(camel);
        if (s.isEmpty()) return "Id";
        return Character.toUpperCase(s.charAt(0)) + s.substring(1);
    }

    private static String safe(String s) {
        return s == null ? "" : s.trim();
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

    private static Path ensureDirectory(Path path) {
        try {
            Files.createDirectories(path);
            return path;
        } catch (IOException e) {
            throw new IllegalStateException("Failed to create directory: " + path, e);
        }
    }

    private static void writeToFile(Path dir, String fileName, String content) {
        Path filePath = dir.resolve(fileName);
        try {
            Files.writeString(filePath, content, StandardCharsets.UTF_8);
            log.info("✅ Service generated: {}", filePath.toAbsolutePath());
        } catch (IOException e) {
            log.error("❌ Failed to write service file: {}", filePath.toAbsolutePath(), e);
        }
    }

    private static final class TypeRef {
        private final String simpleName;
        private final String importLine;

        private TypeRef(String simpleName, String importLine) {
            this.simpleName = simpleName;
            this.importLine = importLine;
        }
    }
}
