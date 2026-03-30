package com.sqldomaingen.generator;

import com.sqldomaingen.model.Column;
import com.sqldomaingen.model.Table;
import com.sqldomaingen.util.GeneratorSupport;
import com.sqldomaingen.util.NamingConverter;
import com.sqldomaingen.util.PackageResolver;
import lombok.extern.log4j.Log4j2;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Generates service implementation test classes for parsed tables.
 */
@Log4j2
public class ServiceImplTestGenerator {

    /**
     * Generates service implementation test classes for all parsed tables.
     *
     * @param tables parsed SQL tables
     * @param outputDir project root output directory
     * @param basePackage base Java package
     * @param overwrite overwrite existing files when true
     */
    public void generateServiceImplTests(List<Table> tables, String outputDir, String basePackage, boolean overwrite) {
        Objects.requireNonNull(tables, "tables must not be null");
        Objects.requireNonNull(outputDir, "outputDir must not be null");
        Objects.requireNonNull(basePackage, "basePackage must not be null");

        Path serviceTestDir = GeneratorSupport.ensureDirectory(
                PackageResolver.resolvePath(outputDir, basePackage, "service", true)
        );
        String testPackage = PackageResolver.resolvePackageName(basePackage, "service");

        String entityPackage = PackageResolver.resolvePackageName(basePackage, "entity");
        String dtoPackage = PackageResolver.resolvePackageName(basePackage, "dto");
        String repositoryPackage = PackageResolver.resolvePackageName(basePackage, "repository");
        String mapperPackage = PackageResolver.resolvePackageName(basePackage, "mapper");
        String serviceImplPackage = PackageResolver.resolvePackageName(basePackage, "serviceImpl");

        for (Table table : tables) {
            String entityName = NamingConverter.toPascalCase(
                    GeneratorSupport.normalizeTableName(table.getName())
            );

            String dtoName = entityName + "Dto";
            String testName = entityName + "ServiceImplTest";
            String repositoryName = entityName + "Repository";
            String mapperName = entityName + "Mapper";
            String serviceImplName = entityName + "ServiceImpl";

            String entityVar = NamingConverter.decapitalizeFirstLetter(entityName);
            String dtoVar = NamingConverter.decapitalizeFirstLetter(dtoName);
            String repositoryVar = NamingConverter.decapitalizeFirstLetter(entityName) + "Repository";
            String mapperVar = NamingConverter.decapitalizeFirstLetter(entityName) + "Mapper";
            String serviceVar = NamingConverter.decapitalizeFirstLetter(entityName) + "Service";

            boolean compositePrimaryKey = hasCompositePrimaryKey(table);
            String primaryKeyType = compositePrimaryKey
                    ? entityName + "PK"
                    : detectSinglePrimaryKeyType(table);

            String primaryKeyImportLine = compositePrimaryKey
                    ? "import " + entityPackage + "." + entityName + "PK;"
                    : buildPrimaryKeyImportLine(primaryKeyType);

            StringBuilder content = new StringBuilder();

            content.append("package ").append(testPackage).append(";\n\n");

            content.append("import ").append(entityPackage).append(".").append(entityName).append(";\n");
            content.append("import ").append(dtoPackage).append(".").append(dtoName).append(";\n");
            content.append("import ").append(repositoryPackage).append(".").append(repositoryName).append(";\n");
            content.append("import ").append(mapperPackage).append(".").append(mapperName).append(";\n");
            content.append("import ").append(serviceImplPackage).append(".").append(serviceImplName).append(";\n");
            if (primaryKeyImportLine != null && !primaryKeyImportLine.isBlank()) {
                content.append(primaryKeyImportLine).append("\n");
            }

            for (String importLine : buildCompositePrimaryKeyExtraImports(table)) {
                content.append(importLine).append("\n");
            }

            content.append("\n");

            content.append("import org.junit.jupiter.api.Test;\n");
            content.append("import org.junit.jupiter.api.extension.ExtendWith;\n");
            content.append("import org.mockito.InjectMocks;\n");
            content.append("import org.mockito.Mock;\n");
            content.append("import org.mockito.junit.jupiter.MockitoExtension;\n");
            content.append("import org.springframework.web.server.ResponseStatusException;\n\n");

            content.append("import java.util.List;\n");
            content.append("import java.util.Optional;\n\n");

            content.append("import static org.junit.jupiter.api.Assertions.assertSame;\n");
            content.append("import static org.junit.jupiter.api.Assertions.assertThrows;\n");
            content.append("import static org.mockito.ArgumentMatchers.any;\n");
            content.append("import static org.mockito.BDDMockito.given;\n");
            content.append("import static org.mockito.Mockito.never;\n");
            content.append("import static org.mockito.Mockito.verify;\n\n");

            content.append("@ExtendWith(MockitoExtension.class)\n");
            content.append("class ").append(testName).append(" {\n\n");

            content.append("    @Mock\n");
            content.append("    private ").append(repositoryName).append(" ").append(repositoryVar).append(";\n\n");

            content.append("    @Mock\n");
            content.append("    private ").append(mapperName).append(" ").append(mapperVar).append(";\n\n");

            content.append("    @InjectMocks\n");
            content.append("    private ").append(serviceImplName).append(" ").append(serviceVar).append(";\n\n");

            appendGetAllTest(content, entityName, dtoName, repositoryVar, mapperVar, serviceVar);
            appendGetByIdSuccessTest(content, table, entityName, dtoName, entityVar, dtoVar, repositoryVar, mapperVar, serviceVar, primaryKeyType);
            appendGetByIdNotFoundTest(content, table, entityName, repositoryVar, mapperVar, serviceVar, primaryKeyType);
            appendCreateTest(content, entityName, dtoName, repositoryVar, mapperVar, serviceVar);
            appendPatchSuccessTest(content, table, entityName, dtoName, repositoryVar, mapperVar, serviceVar, primaryKeyType);
            appendPatchNotFoundTest(content, table, entityName, dtoName, repositoryVar, mapperVar, serviceVar, primaryKeyType);
            appendDeleteSuccessTest(content, table, entityName, repositoryVar, serviceVar, primaryKeyType);
            appendDeleteNotFoundTest(content, table, entityName, repositoryVar, serviceVar, primaryKeyType);

            content.append("}\n");

            GeneratorSupport.writeFile(serviceTestDir.resolve(testName + ".java"), content.toString(), overwrite);
        }

        log.info("Service implementation test classes generated under: {}", serviceTestDir.toAbsolutePath());
    }

    /**
     * Appends the get-all service test.
     *
     * @param content generated test content
     * @param entityName entity simple name
     * @param dtoName dto simple name
     * @param repositoryVar repository variable name
     * @param mapperVar mapper variable name
     * @param serviceVar service variable name
     */
    private void appendGetAllTest(
            StringBuilder content,
            String entityName,
            String dtoName,
            String repositoryVar,
            String mapperVar,
            String serviceVar
    ) {
        content.append("    @Test\n");
        content.append("    void shouldReturnMappedDtosWhenGetAll").append(entityName).append("IsCalled() {\n");
        content.append("        List<").append(entityName).append("> entities = List.of(new ").append(entityName).append("());\n");
        content.append("        List<").append(dtoName).append("> dtos = List.of(new ").append(dtoName).append("());\n\n");
        content.append("        given(").append(repositoryVar).append(".findAll()).willReturn(entities);\n");
        content.append("        given(").append(mapperVar).append(".toDTO(entities)).willReturn(dtos);\n\n");
        content.append("        List<").append(dtoName).append("> result = ").append(serviceVar)
                .append(".getAll").append(entityName).append("();\n\n");
        content.append("        assertSame(dtos, result);\n");
        content.append("        verify(").append(repositoryVar).append(").findAll();\n");
        content.append("        verify(").append(mapperVar).append(").toDTO(entities);\n");
        content.append("    }\n\n");
    }

    /**
     * Appends the successful get-by-id service test.
     *
     * @param content generated test content
     * @param table current table
     * @param entityName entity simple name
     * @param dtoName dto simple name
     * @param entityVar entity variable name
     * @param dtoVar dto variable name
     * @param repositoryVar repository variable name
     * @param mapperVar mapper variable name
     * @param serviceVar service variable name
     * @param primaryKeyType primary key type
     */
    private void appendGetByIdSuccessTest(
            StringBuilder content,
            Table table,
            String entityName,
            String dtoName,
            String entityVar,
            String dtoVar,
            String repositoryVar,
            String mapperVar,
            String serviceVar,
            String primaryKeyType
    ) {
        content.append("    @Test\n");
        content.append("    void shouldReturnMappedDtoWhenGet").append(entityName).append("ByIdIsCalled() {\n");
        content.append(buildPrimaryKeyDeclarationCode(table, primaryKeyType, "id", "        "));
        content.append("        ").append(entityName).append(" ").append(entityVar).append(" = new ").append(entityName).append("();\n");
        content.append("        ").append(dtoName).append(" ").append(dtoVar).append(" = new ").append(dtoName).append("();\n\n");
        content.append("        given(").append(repositoryVar).append(".findById(id)).willReturn(Optional.of(").append(entityVar).append("));\n");
        content.append("        given(").append(mapperVar).append(".toDTO(").append(entityVar).append(")).willReturn(").append(dtoVar).append(");\n\n");
        content.append("        ").append(dtoName).append(" result = ").append(serviceVar)
                .append(".get").append(entityName).append("ById(id);\n\n");
        content.append("        assertSame(").append(dtoVar).append(", result);\n");
        content.append("        verify(").append(repositoryVar).append(").findById(id);\n");
        content.append("        verify(").append(mapperVar).append(").toDTO(").append(entityVar).append(");\n");
        content.append("    }\n\n");
    }

    /**
     * Appends the not-found get-by-id service test.
     *
     * @param content generated test content
     * @param table current table
     * @param entityName entity simple name
     * @param repositoryVar repository variable name
     * @param mapperVar mapper variable name
     * @param serviceVar service variable name
     * @param primaryKeyType primary key type
     */
    private void appendGetByIdNotFoundTest(
            StringBuilder content,
            Table table,
            String entityName,
            String repositoryVar,
            String mapperVar,
            String serviceVar,
            String primaryKeyType
    ) {
        content.append("    @Test\n");
        content.append("    void shouldThrowWhenGet").append(entityName).append("ByIdCannotFindEntity() {\n");
        content.append(buildPrimaryKeyDeclarationCode(table, primaryKeyType, "id", "        "));
        content.append("        given(").append(repositoryVar).append(".findById(id)).willReturn(Optional.empty());\n\n");
        content.append("        assertThrows(ResponseStatusException.class, () -> ")
                .append(serviceVar).append(".get").append(entityName).append("ById(id));\n\n");
        content.append("        verify(").append(repositoryVar).append(").findById(id);\n");
        content.append("        verify(").append(mapperVar).append(", never()).toDTO(any(").append(entityName).append(".class));\n");
        content.append("    }\n\n");
    }

    /**
     * Appends the create service test.
     *
     * @param content generated test content
     * @param entityName entity simple name
     * @param dtoName dto simple name
     * @param repositoryVar repository variable name
     * @param mapperVar mapper variable name
     * @param serviceVar service variable name
     */
    private void appendCreateTest(
            StringBuilder content,
            String entityName,
            String dtoName,
            String repositoryVar,
            String mapperVar,
            String serviceVar
    ) {
        content.append("    @Test\n");
        content.append("    void shouldCreate").append(entityName).append("WhenCreate").append(entityName).append("IsCalled() {\n");
        content.append("        ").append(dtoName).append(" requestDto = new ").append(dtoName).append("();\n");
        content.append("        ").append(entityName).append(" mappedEntity = new ").append(entityName).append("();\n");
        content.append("        ").append(entityName).append(" savedEntity = new ").append(entityName).append("();\n");
        content.append("        ").append(dtoName).append(" responseDto = new ").append(dtoName).append("();\n\n");
        content.append("        given(").append(mapperVar).append(".toEntity(requestDto)).willReturn(mappedEntity);\n");
        content.append("        given(").append(repositoryVar).append(".save(mappedEntity)).willReturn(savedEntity);\n");
        content.append("        given(").append(mapperVar).append(".toDTO(savedEntity)).willReturn(responseDto);\n\n");
        content.append("        ").append(dtoName).append(" result = ").append(serviceVar)
                .append(".create").append(entityName).append("(requestDto);\n\n");
        content.append("        assertSame(responseDto, result);\n");
        content.append("        verify(").append(mapperVar).append(").toEntity(requestDto);\n");
        content.append("        verify(").append(repositoryVar).append(").save(mappedEntity);\n");
        content.append("        verify(").append(mapperVar).append(").toDTO(savedEntity);\n");
        content.append("    }\n\n");
    }

    /**
     * Appends the successful PATCH service test.
     *
     * @param content generated test content
     * @param table current table
     * @param entityName entity simple name
     * @param dtoName dto simple name
     * @param repositoryVar repository variable name
     * @param mapperVar mapper variable name
     * @param serviceVar service variable name
     * @param primaryKeyType primary key type
     */
    private void appendPatchSuccessTest(
            StringBuilder content,
            Table table,
            String entityName,
            String dtoName,
            String repositoryVar,
            String mapperVar,
            String serviceVar,
            String primaryKeyType
    ) {
        content.append("    @Test\n");
        content.append("    void shouldPatch").append(entityName).append("WhenEntityExists() {\n");
        content.append(buildPrimaryKeyDeclarationCode(table, primaryKeyType, "id", "        "));
        content.append("        ").append(dtoName).append(" requestDto = new ").append(dtoName).append("();\n");
        content.append("        ").append(entityName).append(" existingEntity = new ").append(entityName).append("();\n");
        content.append("        ").append(entityName).append(" savedEntity = new ").append(entityName).append("();\n");
        content.append("        ").append(dtoName).append(" responseDto = new ").append(dtoName).append("();\n\n");
        content.append("        given(").append(repositoryVar).append(".findById(id)).willReturn(Optional.of(existingEntity));\n");
        content.append("        given(").append(repositoryVar).append(".save(existingEntity)).willReturn(savedEntity);\n");
        content.append("        given(").append(mapperVar).append(".toDTO(savedEntity)).willReturn(responseDto);\n\n");
        content.append("        ").append(dtoName).append(" result = ").append(serviceVar)
                .append(".patch").append(entityName).append("(id, requestDto);\n\n");
        content.append("        assertSame(responseDto, result);\n");
        content.append("        verify(").append(repositoryVar).append(").findById(id);\n");
        content.append("        verify(").append(mapperVar).append(").partialUpdate(existingEntity, requestDto);\n");
        content.append("        verify(").append(repositoryVar).append(").save(existingEntity);\n");
        content.append("        verify(").append(mapperVar).append(").toDTO(savedEntity);\n");
        content.append("        verify(").append(mapperVar).append(", never()).toEntity(any(").append(dtoName).append(".class));\n");
        content.append("    }\n\n");
    }

    /**
     * Appends the not-found PATCH service test.
     *
     * @param content generated test content
     * @param table current table
     * @param entityName entity simple name
     * @param dtoName dto simple name
     * @param repositoryVar repository variable name
     * @param mapperVar mapper variable name
     * @param serviceVar service variable name
     * @param primaryKeyType primary key type
     */
    private void appendPatchNotFoundTest(
            StringBuilder content,
            Table table,
            String entityName,
            String dtoName,
            String repositoryVar,
            String mapperVar,
            String serviceVar,
            String primaryKeyType
    ) {
        content.append("    @Test\n");
        content.append("    void shouldThrowWhenPatch").append(entityName).append("CannotFindEntity() {\n");
        content.append(buildPrimaryKeyDeclarationCode(table, primaryKeyType, "id", "        "));
        content.append("        ").append(dtoName).append(" requestDto = new ").append(dtoName).append("();\n\n");
        content.append("        given(").append(repositoryVar).append(".findById(id)).willReturn(Optional.empty());\n\n");
        content.append("        assertThrows(ResponseStatusException.class, () -> ")
                .append(serviceVar).append(".patch").append(entityName).append("(id, requestDto));\n\n");
        content.append("        verify(").append(repositoryVar).append(").findById(id);\n");
        content.append("        verify(").append(mapperVar).append(", never()).partialUpdate(any(), any());\n");
        content.append("        verify(").append(repositoryVar).append(", never()).save(any());\n");
        content.append("    }\n\n");
    }

    /**
     * Appends the successful delete service test.
     *
     * @param content generated test content
     * @param table current table
     * @param entityName entity simple name
     * @param repositoryVar repository variable name
     * @param serviceVar service variable name
     * @param primaryKeyType primary key type
     */
    private void appendDeleteSuccessTest(
            StringBuilder content,
            Table table,
            String entityName,
            String repositoryVar,
            String serviceVar,
            String primaryKeyType
    ) {
        content.append("    @Test\n");
        content.append("    void shouldDelete").append(entityName).append("WhenEntityExists() {\n");
        content.append(buildPrimaryKeyDeclarationCode(table, primaryKeyType, "id", "        "));
        content.append("        ").append(entityName).append(" existingEntity = new ").append(entityName).append("();\n\n");
        content.append("        given(").append(repositoryVar).append(".findById(id)).willReturn(Optional.of(existingEntity));\n\n");
        content.append("        ").append(serviceVar).append(".delete").append(entityName).append("(id);\n\n");
        content.append("        verify(").append(repositoryVar).append(").findById(id);\n");
        content.append("        verify(").append(repositoryVar).append(").deleteById(id);\n");
        content.append("    }\n\n");
    }

    /**
     * Appends the not-found delete service test.
     *
     * @param content generated test content
     * @param table current table
     * @param entityName entity simple name
     * @param repositoryVar repository variable name
     * @param serviceVar service variable name
     * @param primaryKeyType primary key type
     */
    private void appendDeleteNotFoundTest(
            StringBuilder content,
            Table table,
            String entityName,
            String repositoryVar,
            String serviceVar,
            String primaryKeyType
    ) {
        content.append("    @Test\n");
        content.append("    void shouldThrowWhenDelete").append(entityName).append("CannotFindEntity() {\n");
        content.append(buildPrimaryKeyDeclarationCode(table, primaryKeyType, "id", "        "));
        content.append("        given(").append(repositoryVar).append(".findById(id)).willReturn(Optional.empty());\n\n");
        content.append("        assertThrows(ResponseStatusException.class, () -> ")
                .append(serviceVar).append(".delete").append(entityName).append("(id));\n\n");
        content.append("        verify(").append(repositoryVar).append(").findById(id);\n");
        content.append("        verify(").append(repositoryVar).append(", never()).deleteById(id);\n");
        content.append("    }\n");
    }

    /**
     * Builds Java code that declares and initializes a primary key variable.
     *
     * @param table current table
     * @param primaryKeyType primary key type
     * @param variableName variable name to generate
     * @param indent indentation to prepend to each generated line
     * @return generated Java code
     */
    private String buildPrimaryKeyDeclarationCode(
            Table table,
            String primaryKeyType,
            String variableName,
            String indent
    ) {
        if (!hasCompositePrimaryKey(table)) {
            return indent + primaryKeyType + " " + variableName + " = " + sampleValueForType(primaryKeyType) + ";\n\n";
        }

        StringBuilder builder = new StringBuilder();
        builder.append(indent)
                .append(primaryKeyType)
                .append(" ")
                .append(variableName)
                .append(" = new ")
                .append(primaryKeyType)
                .append("();\n");

        for (Column column : getPrimaryKeyColumns(table)) {
            String normalizedColumnName = GeneratorSupport.unquoteIdentifier(column.getName());
            String setterName = "set" + NamingConverter.toPascalCase(normalizedColumnName);
            String javaLiteral = sampleLiteralForColumn(column);

            builder.append(indent)
                    .append(variableName)
                    .append(".")
                    .append(setterName)
                    .append("(")
                    .append(javaLiteral)
                    .append(");\n");
        }

        builder.append("\n");
        return builder.toString();
    }

    /**
     * Builds a sample Java literal for a primary key column.
     *
     * @param column primary key column
     * @return Java literal source code
     */
    private String sampleLiteralForColumn(Column column) {
        String javaType = detectJavaTypeForPkColumn(column);

        return switch (GeneratorSupport.trimToEmpty(javaType)) {
            case "UUID" -> "UUID.fromString(\"123e4567-e89b-12d3-a456-426614174000\")";
            case "BigDecimal" -> "new BigDecimal(\"1\")";
            case "BigInteger" -> "new BigInteger(\"1\")";
            case "Integer" -> "1";
            case "Short" -> "(short) 1";
            case "Byte" -> "(byte) 1";
            case "Boolean" -> "true";
            case "LocalDate" -> "LocalDate.of(2024, 1, 1)";
            case "LocalDateTime" -> "LocalDateTime.of(2024, 1, 1, 10, 0)";
            case "String" -> "\"test-value\"";
            default -> "1L";
        };
    }

    /**
     * Builds extra import lines required for composite primary key literals.
     *
     * @param table current table
     * @return import lines
     */
    private List<String> buildCompositePrimaryKeyExtraImports(Table table) {
        if (!hasCompositePrimaryKey(table)) {
            return List.of();
        }

        boolean needsUuid = false;
        boolean needsBigDecimal = false;
        boolean needsBigInteger = false;
        boolean needsLocalDate = false;
        boolean needsLocalDateTime = false;

        for (Column column : getPrimaryKeyColumns(table)) {
            String javaType = detectJavaTypeForPkColumn(column);

            switch (GeneratorSupport.trimToEmpty(javaType)) {
                case "UUID" -> needsUuid = true;
                case "BigDecimal" -> needsBigDecimal = true;
                case "BigInteger" -> needsBigInteger = true;
                case "LocalDate" -> needsLocalDate = true;
                case "LocalDateTime" -> needsLocalDateTime = true;
                default -> {
                }
            }
        }

        List<String> imports = new ArrayList<>();

        if (needsUuid) {
            imports.add("import java.util.UUID;");
        }
        if (needsBigDecimal) {
            imports.add("import java.math.BigDecimal;");
        }
        if (needsBigInteger) {
            imports.add("import java.math.BigInteger;");
        }
        if (needsLocalDate) {
            imports.add("import java.time.LocalDate;");
        }
        if (needsLocalDateTime) {
            imports.add("import java.time.LocalDateTime;");
        }

        return imports;
    }

    /**
     * Builds the import line for a simple primary key type.
     *
     * @param pkType primary key type
     * @return import line or null
     */
    private String buildPrimaryKeyImportLine(String pkType) {
        return switch (GeneratorSupport.trimToEmpty(pkType)) {
            case "UUID" -> "import java.util.UUID;";
            case "BigDecimal" -> "import java.math.BigDecimal;";
            case "BigInteger" -> "import java.math.BigInteger;";
            default -> null;
        };
    }

    /**
     * Builds a sample value for the provided Java type.
     *
     * @param javaType Java type name
     * @return Java literal source
     */
    private String sampleValueForType(String javaType) {
        String normalizedType = GeneratorSupport.trimToEmpty(javaType);

        return switch (normalizedType) {
            case "UUID" -> "UUID.fromString(\"123e4567-e89b-12d3-a456-426614174000\")";
            case "BigDecimal" -> "new BigDecimal(\"1\")";
            case "BigInteger" -> "new BigInteger(\"1\")";
            case "Integer" -> "1";
            case "Short" -> "(short) 1";
            case "Byte" -> "(byte) 1";
            case "Boolean" -> "true";
            case "String" -> "\"test-id\"";
            default -> "1L";
        };
    }

    /**
     * Determines whether the table uses a composite primary key.
     *
     * @param table current table
     * @return true when more than one primary key column exists
     */
    private boolean hasCompositePrimaryKey(Table table) {
        return getPrimaryKeyColumns(table).size() > 1;
    }

    /**
     * Returns all primary key columns of the table.
     *
     * @param table current table
     * @return primary key columns
     */
    private List<Column> getPrimaryKeyColumns(Table table) {
        return table.getColumns().stream()
                .filter(Column::isPrimaryKey)
                .toList();
    }

    /**
     * Resolves the Java type for a primary key column.
     *
     * @param column primary key column
     * @return resolved Java type
     */
    private String detectJavaTypeForPkColumn(Column column) {
        String javaType = GeneratorSupport.trimToEmpty(column.getJavaType());

        if (javaType.isBlank()) {
            return "Long";
        }

        if (javaType.contains(".")) {
            return javaType.substring(javaType.lastIndexOf('.') + 1);
        }

        return javaType;
    }

    /**
     * Resolves the Java type for a single primary key.
     *
     * @param table current table
     * @return resolved Java type
     */
    private String detectSinglePrimaryKeyType(Table table) {
        List<Column> pkColumns = getPrimaryKeyColumns(table);

        if (pkColumns.isEmpty()) {
            throw new IllegalStateException("No Primary Key found for table: " + table.getName());
        }

        if (pkColumns.size() > 1) {
            throw new IllegalStateException("Composite PK not supported here");
        }

        return detectJavaTypeForPkColumn(pkColumns.get(0));
    }
}