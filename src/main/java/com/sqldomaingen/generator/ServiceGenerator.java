package com.sqldomaingen.generator;

import com.sqldomaingen.model.Column;
import com.sqldomaingen.model.Table;
import com.sqldomaingen.util.GeneratorSupport;
import com.sqldomaingen.util.NamingConverter;
import com.sqldomaingen.util.PackageResolver;
import lombok.extern.log4j.Log4j2;

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
     * Generates service interfaces and implementations for all parsed tables.
     *
     * @param tables parsed SQL tables
     * @param outputDir base output directory
     * @param basePackage base package
     */
    public void generateAllServices(List<Table> tables, String outputDir, String basePackage) {
        Objects.requireNonNull(tables, "tables must not be null");
        Objects.requireNonNull(outputDir, "outputDir must not be null");
        Objects.requireNonNull(basePackage, "basePackage must not be null");

        Path serviceDir = GeneratorSupport.ensureDirectory(
                PackageResolver.resolvePath(outputDir, basePackage, "service")
        );
        Path serviceImplDir = GeneratorSupport.ensureDirectory(
                PackageResolver.resolvePath(outputDir, basePackage, "serviceImpl")
        );

        for (Table table : tables) {
            String entityName = NamingConverter.toPascalCase(
                    GeneratorSupport.normalizeTableName(table.getName())
            );

            String serviceInterfaceCode = generateServiceInterface(table, basePackage);
            GeneratorSupport.writeFile(serviceDir.resolve(entityName + "Service.java"), serviceInterfaceCode);

            String serviceImplCode = generateServiceImpl(table, basePackage);
            GeneratorSupport.writeFile(serviceImplDir.resolve(entityName + "ServiceImpl.java"), serviceImplCode);
        }

        log.info("Services generated under: {}", serviceDir.getParent().toAbsolutePath());
    }

    /**
     * Generates the Service interface for one entity.
     *
     * @param table source table metadata
     * @param basePackage base package
     * @return Java source code
     */
    public String generateServiceInterface(Table table, String basePackage) {
        Objects.requireNonNull(table, "table must not be null");
        Objects.requireNonNull(basePackage, "basePackage must not be null");

        String entityName = NamingConverter.toPascalCase(
                GeneratorSupport.normalizeTableName(table.getName())
        );
        String dtoName = entityName + DTO_SUFFIX;

        String servicePackage = PackageResolver.resolvePackageName(basePackage, "service");
        String dtoPackage = PackageResolver.resolvePackageName(basePackage, "dto");
        String modelPackage = PackageResolver.resolvePackageName(basePackage, "entity");

        TypeRef pkType = detectPrimaryKeyType(table, entityName, modelPackage);

        StringBuilder sb = new StringBuilder();

        sb.append("package ").append(servicePackage).append(";\n\n");
        sb.append("import ").append(dtoPackage).append(".").append(dtoName).append(";\n");
        if (pkType.importLine() != null) {
            sb.append(pkType.importLine()).append("\n");
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
        sb.append("    ").append(dtoName).append(" get").append(entityName).append("ById(")
                .append(pkType.simpleName()).append(" id);\n\n");

        sb.append("    /**\n");
        sb.append("     * Creates a new record.\n");
        sb.append("     *\n");
        sb.append("     * @param dto input payload\n");
        sb.append("     * @return created {@link ").append(dtoName).append("}\n");
        sb.append("     */\n");
        sb.append("    ").append(dtoName).append(" create").append(entityName).append("(")
                .append(dtoName).append(" dto);\n\n");

        sb.append("    /**\n");
        sb.append("     * Partially updates an existing record.\n");
        sb.append("     * <p>\n");
        sb.append("     * Only non-null fields from the DTO are applied to the existing entity.\n");
        sb.append("     *\n");
        sb.append("     * @param id the record id\n");
        sb.append("     * @param dto input payload with partial fields\n");
        sb.append("     * @return updated {@link ").append(dtoName).append("}\n");
        sb.append("     */\n");
        sb.append("    ").append(dtoName).append(" patch").append(entityName).append("(")
                .append(pkType.simpleName()).append(" id, ").append(dtoName).append(" dto);\n\n");

        sb.append("    /**\n");
        sb.append("     * Deletes a record by id.\n");
        sb.append("     *\n");
        sb.append("     * @param id the record id\n");
        sb.append("     */\n");
        sb.append("    void delete").append(entityName).append("(").append(pkType.simpleName()).append(" id);\n");

        sb.append("}\n");

        return sb.toString();
    }

    /**
     * Generates the ServiceImpl class for one entity.
     *
     * @param table source table metadata
     * @param basePackage base package
     * @return Java source code
     */
    public String generateServiceImpl(Table table, String basePackage) {
        Objects.requireNonNull(table, "table must not be null");
        Objects.requireNonNull(basePackage, "basePackage must not be null");

        String entityName = NamingConverter.toPascalCase(
                GeneratorSupport.normalizeTableName(table.getName())
        );
        String dtoName = entityName + DTO_SUFFIX;
        String repositoryName = entityName + "Repository";
        String mapperName = entityName + "Mapper";
        String serviceName = entityName + "Service";

        String repositoryVar = NamingConverter.decapitalizeFirstLetter(entityName) + "Repository";
        String mapperVar = NamingConverter.decapitalizeFirstLetter(entityName) + "Mapper";

        String serviceImplPackage = PackageResolver.resolvePackageName(basePackage, "serviceImpl");
        String dtoPackage = PackageResolver.resolvePackageName(basePackage, "dto");
        String mapperPackage = PackageResolver.resolvePackageName(basePackage, "mapper");
        String modelPackage = PackageResolver.resolvePackageName(basePackage, "entity");
        String repositoryPackage = PackageResolver.resolvePackageName(basePackage, "repository");
        String servicePackage = PackageResolver.resolvePackageName(basePackage, "service");

        TypeRef pkType = detectPrimaryKeyType(table, entityName, modelPackage);

        String lowerDisplayLabel = NamingConverter.toLogLabel(entityName);
        if (lowerDisplayLabel.isBlank()) {
            lowerDisplayLabel = entityName;
        }

        String[] displayParts = lowerDisplayLabel.split("\\s+");
        StringBuilder displayLabelBuilder = new StringBuilder();
        for (int i = 0; i < displayParts.length; i++) {
            String part = displayParts[i];
            if (part.isBlank()) {
                continue;
            }

            displayLabelBuilder.append(Character.toUpperCase(part.charAt(0)));
            if (part.length() > 1) {
                displayLabelBuilder.append(part.substring(1));
            }

            if (i < displayParts.length - 1) {
                displayLabelBuilder.append(" ");
            }
        }
        String displayLabel = displayLabelBuilder.isEmpty() ? entityName : displayLabelBuilder.toString();

        StringBuilder sb = new StringBuilder();

        sb.append("package ").append(serviceImplPackage).append(";\n\n");

        sb.append("import ").append(dtoPackage).append(".").append(dtoName).append(";\n");
        sb.append("import ").append(mapperPackage).append(".").append(mapperName).append(";\n");
        sb.append("import ").append(modelPackage).append(".").append(entityName).append(";\n");
        sb.append("import ").append(repositoryPackage).append(".").append(repositoryName).append(";\n");
        sb.append("import ").append(servicePackage).append(".").append(serviceName).append(";\n");

        if (pkType.importLine() != null) {
            sb.append(pkType.importLine()).append("\n");
        }

        sb.append("import jakarta.transaction.Transactional;\n");
        sb.append("import lombok.RequiredArgsConstructor;\n");
        sb.append("import lombok.extern.log4j.Log4j2;\n");
        sb.append("import org.springframework.http.HttpStatus;\n");
        sb.append("import org.springframework.stereotype.Service;\n");
        sb.append("import org.springframework.web.server.ResponseStatusException;\n\n");
        sb.append("import java.util.List;\n\n");

        sb.append("/**\n");
        sb.append(" * Service implementation for {@code ").append(displayLabel).append("} domain operations.\n");
        sb.append(" */\n");
        sb.append("@Service\n");
        sb.append("@RequiredArgsConstructor\n");
        sb.append("@Transactional\n");
        sb.append("@Log4j2\n");
        sb.append("public class ").append(entityName).append("ServiceImpl implements ").append(serviceName).append(" {\n\n");

        sb.append("    private final ").append(repositoryName).append(" ").append(repositoryVar).append(";\n");
        sb.append("    private final ").append(mapperName).append(" ").append(mapperVar).append(";\n\n");

        sb.append("    /**\n");
        sb.append("     * Retrieves all ").append(displayLabel).append(" records.\n");
        sb.append("     *\n");
        sb.append("     * @return non-null list of {@link ").append(dtoName).append("}\n");
        sb.append("     */\n");
        sb.append("    @Override\n");
        sb.append("    public List<").append(dtoName).append("> getAll").append(entityName).append("() {\n");
        sb.append("        log.info(\"Fetching all ").append(lowerDisplayLabel).append(".\");\n");
        sb.append("        return ").append(mapperVar).append(".toDTO(").append(repositoryVar).append(".findAll());\n");
        sb.append("    }\n\n");

        sb.append("    /**\n");
        sb.append("     * Retrieves a ").append(lowerDisplayLabel).append(" record by id.\n");
        sb.append("     *\n");
        sb.append("     * @param id the ").append(lowerDisplayLabel).append(" id\n");
        sb.append("     * @return the matching {@link ").append(dtoName).append("}\n");
        sb.append("     */\n");
        sb.append("    @Override\n");
        sb.append("    public ").append(dtoName).append(" get").append(entityName).append("ById(")
                .append(pkType.simpleName()).append(" id) {\n");
        sb.append("        log.info(\"Fetching ").append(lowerDisplayLabel).append(" with id: {}\", id);\n");
        sb.append("        ").append(entityName).append(" entity = ").append(repositoryVar).append(".findById(id)\n");
        sb.append("                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, \"")
                .append(displayLabel).append(" not found with id: \" + id));\n");
        sb.append("        return ").append(mapperVar).append(".toDTO(entity);\n");
        sb.append("    }\n\n");

        sb.append("    /**\n");
        sb.append("     * Creates a new ").append(lowerDisplayLabel).append(" record.\n");
        sb.append("     *\n");
        sb.append("     * @param dto input payload\n");
        sb.append("     * @return created {@link ").append(dtoName).append("}\n");
        sb.append("     */\n");
        sb.append("    @Override\n");
        sb.append("    public ").append(dtoName).append(" create").append(entityName).append("(")
                .append(dtoName).append(" dto) {\n");
        sb.append("        log.info(\"Creating ").append(lowerDisplayLabel).append(".\");\n");
        sb.append("        ").append(entityName).append(" entity = ").append(mapperVar).append(".toEntity(dto);\n");
        sb.append("        ").append(entityName).append(" saved = ").append(repositoryVar).append(".save(entity);\n");
        sb.append("        return ").append(mapperVar).append(".toDTO(saved);\n");
        sb.append("    }\n\n");

        sb.append("    /**\n");
        sb.append("     * Partially updates an existing ").append(lowerDisplayLabel).append(" record.\n");
        sb.append("     * <p>\n");
        sb.append("     * Only non-null fields from the DTO are applied to the existing entity.\n");
        sb.append("     *\n");
        sb.append("     * @param id the ").append(lowerDisplayLabel).append(" id\n");
        sb.append("     * @param dto input payload with partial fields\n");
        sb.append("     * @return updated {@link ").append(dtoName).append("}\n");
        sb.append("     */\n");
        sb.append("    @Override\n");
        sb.append("    public ").append(dtoName).append(" patch").append(entityName).append("(")
                .append(pkType.simpleName()).append(" id, ").append(dtoName).append(" dto) {\n");
        sb.append("        log.info(\"Patching ").append(lowerDisplayLabel).append(" with id: {}\", id);\n");
        sb.append("        ").append(entityName).append(" existingEntity = ").append(repositoryVar).append(".findById(id)\n");
        sb.append("                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, \"")
                .append(displayLabel).append(" not found with id: \" + id));\n");
        sb.append("        ").append(mapperVar).append(".partialUpdate(existingEntity, dto);\n");
        sb.append("        ").append(entityName).append(" saved = ").append(repositoryVar).append(".save(existingEntity);\n");
        sb.append("        return ").append(mapperVar).append(".toDTO(saved);\n");
        sb.append("    }\n\n");

        sb.append("    /**\n");
        sb.append("     * Deletes a ").append(lowerDisplayLabel).append(" record by id.\n");
        sb.append("     *\n");
        sb.append("     * @param id the ").append(lowerDisplayLabel).append(" id\n");
        sb.append("     */\n");
        sb.append("    @Override\n");
        sb.append("    public void delete").append(entityName).append("(").append(pkType.simpleName()).append(" id) {\n");
        sb.append("        log.info(\"Deleting ").append(lowerDisplayLabel).append(" with id: {}\", id);\n");
        sb.append("        ").append(repositoryVar).append(".findById(id)\n");
        sb.append("                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, \"")
                .append(displayLabel).append(" not found with id: \" + id));\n");
        sb.append("        ").append(repositoryVar).append(".deleteById(id);\n");
        sb.append("    }\n");

        sb.append("}\n");

        return sb.toString();
    }

    /**
     * Resolves the primary key type reference for generated service code.
     *
     * @param table table metadata
     * @param entityName entity simple name
     * @param modelPackage entity model package name
     * @return primary key type reference containing simple type name and optional import
     */
    private static TypeRef detectPrimaryKeyType(Table table, String entityName, String modelPackage) {
        if (hasCompositePrimaryKey(table)) {
            String pkClassName = entityName + "PK";
            return new TypeRef(pkClassName, "import " + modelPackage + "." + pkClassName + ";");
        }

        Column pk = table.getColumns().stream()
                .filter(Objects::nonNull)
                .filter(Column::isPrimaryKey)
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("No Primary Key found for table: " + table.getName()));

        String raw = pk.getJavaType();
        if (raw == null || raw.isBlank()) {
            return new TypeRef("Long", null);
        }

        String type = raw.trim();

        if ("UUID".equalsIgnoreCase(type) || "java.util.UUID".equals(type)) {
            return new TypeRef("UUID", "import java.util.UUID;");
        }

        if ("BigDecimal".equals(type) || "java.math.BigDecimal".equals(type)) {
            return new TypeRef("BigDecimal", "import java.math.BigDecimal;");
        }

        if ("BigInteger".equals(type) || "java.math.BigInteger".equals(type)) {
            return new TypeRef("BigInteger", "import java.math.BigInteger;");
        }

        if (type.contains(".")) {
            String simple = type.substring(type.lastIndexOf('.') + 1);
            return new TypeRef(simple, "import " + type + ";");
        }

        if ("long".equalsIgnoreCase(type) || "java.lang.Long".equals(type)) {
            return new TypeRef("Long", null);
        }
        if ("int".equalsIgnoreCase(type) || "java.lang.Integer".equals(type)) {
            return new TypeRef("Integer", null);
        }

        return new TypeRef(type, null);
    }

    /**
     * Holds a resolved type name and its optional import line.
     *
     * @param simpleName simple type name
     * @param importLine optional import line
     */
    private record TypeRef(String simpleName, String importLine) {
    }

    /**
     * Determines whether the table uses a composite primary key.
     *
     * @param table source table metadata
     * @return true when more than one primary key column exists
     */
    private static boolean hasCompositePrimaryKey(Table table) {
        long primaryKeyCount = table.getColumns().stream()
                .filter(Objects::nonNull)
                .filter(Column::isPrimaryKey)
                .count();

        return primaryKeyCount > 1;
    }
}