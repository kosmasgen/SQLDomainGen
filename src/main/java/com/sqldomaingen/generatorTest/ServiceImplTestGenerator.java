package com.sqldomaingen.generatorTest;

import com.sqldomaingen.model.Column;

import com.sqldomaingen.model.Entity;
import com.sqldomaingen.model.Field;
import com.sqldomaingen.model.Table;
import com.sqldomaingen.util.GeneratorSupport;
import com.sqldomaingen.util.NamingConverter;
import com.sqldomaingen.util.PackageResolver;
import lombok.extern.log4j.Log4j2;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

/**
 * Generates service implementation test classes for parsed tables.
 */
@Log4j2
public class ServiceImplTestGenerator {

    /**
     * Generates service implementation test classes for all parsed tables.
     *
     * @param tables parsed SQL tables
     * @param entities generated entity metadata
     * @param outputDir project root output directory
     * @param basePackage base Java package
     * @param overwrite overwrite existing files when true
     */
    public void generateServiceImplTests(
            List<Table> tables,
            List<Entity> entities,
            String outputDir,
            String basePackage,
            boolean overwrite
    ) {
        Objects.requireNonNull(tables, "tables must not be null");
        Objects.requireNonNull(entities, "entities must not be null");
        Objects.requireNonNull(outputDir, "outputDir must not be null");
        Objects.requireNonNull(basePackage, "basePackage must not be null");

        Path serviceTestDir = GeneratorSupport.ensureDirectory(
                PackageResolver.resolvePath(outputDir, basePackage, "serviceImpl", true)
        );
        String testPackage = PackageResolver.resolvePackageName(basePackage, "serviceImpl");

        String entityPackage = PackageResolver.resolvePackageName(basePackage, "entity");
        String dtoPackage = PackageResolver.resolvePackageName(basePackage, "dto");
        String repositoryPackage = PackageResolver.resolvePackageName(basePackage, "repository");
        String mapperPackage = PackageResolver.resolvePackageName(basePackage, "mapper");
        String serviceImplPackage = PackageResolver.resolvePackageName(basePackage, "serviceImpl");

        for (Table table : tables) {
            String entityName = NamingConverter.toPascalCase(
                    GeneratorSupport.normalizeTableName(table.getName())
            );

            Entity entityMetadata = findEntityMetadata(entities, entityName);

            if (entityMetadata == null) {
                throw new IllegalStateException(
                        "No generated Entity metadata found for service test generation: " + entityName
                );
            }

            String dtoName = entityName + "Dto";
            List<Field> dtoFields = loadGeneratedDtoFields(outputDir, basePackage, dtoName);

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
                    ? entityName + "Key"
                    : detectSinglePrimaryKeyType(table);

            StringBuilder content = new StringBuilder();

            content.append("package ").append(testPackage).append(";\n\n");
            appendDomainImports(
                    content,
                    testPackage,
                    entityPackage,
                    dtoPackage,
                    dtoFields,
                    repositoryPackage,
                    mapperPackage,
                    serviceImplPackage,
                    entityName,
                    dtoName,
                    repositoryName,
                    mapperName,
                    serviceImplName
            );
            appendTypeImports(
                    content,
                    table,
                    dtoFields,
                    entityPackage,
                    entityName,
                    primaryKeyType,
                    compositePrimaryKey
            );
            appendFrameworkImports(content, basePackage);
            appendClassHeader(
                    content,
                    testName,
                    repositoryName,
                    repositoryVar,
                    mapperName,
                    mapperVar,
                    serviceImplName,
                    serviceVar
            );
            appendStatusAssertionHelpers(content, entityName);
            appendGetAllTest(content, entityName, dtoName, repositoryVar, mapperVar, serviceVar);
            appendGetByIdSuccessTest(
                    content,
                    table,
                    entityName,
                    dtoName,
                    entityVar,
                    dtoVar,
                    repositoryVar,
                    mapperVar,
                    serviceVar,
                    primaryKeyType
            );
            appendGetByIdNotFoundTest(
                    content,
                    table,
                    entityName,
                    repositoryVar,
                    mapperVar,
                    serviceVar,
                    primaryKeyType
            );
            appendCreateTest(content, entityName, dtoName, repositoryVar, mapperVar, serviceVar);
            appendCreateUniqueConstraintTests(
                    content,
                    table,
                    entityName,
                    dtoName,
                    repositoryVar,
                    serviceVar
            );
            appendPatchSuccessTest(
                    content,
                    table,
                    entityName,
                    dtoName,
                    repositoryVar,
                    mapperVar,
                    serviceVar,
                    primaryKeyType
            );
            appendPatchValidationTests(
                    content,
                    table,
                    dtoFields,
                    entityName,
                    dtoName,
                    repositoryVar,
                    mapperVar,
                    serviceVar,
                    primaryKeyType
            );
            appendPatchNotFoundTest(
                    content,
                    table,
                    entityName,
                    dtoName,
                    repositoryVar,
                    mapperVar,
                    serviceVar,
                    primaryKeyType
            );
            appendDeleteSuccessTest(content, table, entityName, repositoryVar, serviceVar, primaryKeyType);
            appendDeleteNotFoundTest(content, table, entityName, repositoryVar, serviceVar, primaryKeyType);
            appendFixtureMethods(content, table, entityMetadata, dtoFields, entityName, dtoName);
            content.append("}\n");

            GeneratorSupport.writeFile(serviceTestDir.resolve(testName + ".java"), content.toString(), overwrite);
        }

        log.info("Service implementation test classes generated under: {}", serviceTestDir.toAbsolutePath());
    }

    /**
     * Appends domain-specific imports for the generated test class.
     *
     * @param content generated test content
     * @param testPackage generated test package name
     * @param entityPackage entity package name
     * @param dtoPackage dto package name
     * @param dtoFields actual generated DTO fields
     * @param repositoryPackage repository package name
     * @param mapperPackage mapper package name
     * @param serviceImplPackage service implementation package name
     * @param entityName entity simple name
     * @param dtoName dto simple name
     * @param repositoryName repository simple name
     * @param mapperName mapper simple name
     * @param serviceImplName service implementation simple name
     */
    private void appendDomainImports(
            StringBuilder content,
            String testPackage,
            String entityPackage,
            String dtoPackage,
            List<Field> dtoFields,
            String repositoryPackage,
            String mapperPackage,
            String serviceImplPackage,
            String entityName,
            String dtoName,
            String repositoryName,
            String mapperName,
            String serviceImplName
    ) {
        content.append("import ").append(entityPackage).append(".").append(entityName).append(";\n");
        content.append("import ").append(dtoPackage).append(".").append(dtoName).append(";\n");

        for (String nestedDtoType : resolveNestedDtoImports(dtoFields, dtoName)) {
            content.append("import ").append(dtoPackage).append(".").append(nestedDtoType).append(";\n");
        }

        content.append("import ").append(repositoryPackage).append(".").append(repositoryName).append(";\n");
        content.append("import ").append(mapperPackage).append(".").append(mapperName).append(";\n");

        if (!Objects.equals(testPackage, serviceImplPackage)) {
            content.append("import ").append(serviceImplPackage).append(".").append(serviceImplName).append(";\n");
        }
    }

    /**
     * Resolves nested DTO imports required by generated service implementation tests.
     *
     * @param dtoFields actual generated DTO fields
     * @param rootDtoName current root DTO simple name
     * @return nested DTO simple names that must be imported
     */
    private Set<String> resolveNestedDtoImports(List<Field> dtoFields, String rootDtoName) {
        Set<String> imports = new LinkedHashSet<>();

        if (dtoFields == null || dtoFields.isEmpty()) {
            return imports;
        }

        for (Field field : dtoFields) {
            if (field == null || field.getType() == null || field.getType().isBlank()) {
                continue;
            }

            String simpleType = normalizeJavaType(field.getType());

            if (simpleType.isBlank()) {
                continue;
            }

            if (!simpleType.endsWith("Dto")) {
                continue;
            }

            if (simpleType.equals(rootDtoName)) {
                continue;
            }

            imports.add(simpleType);
        }

        return imports;
    }

    /**
     * Appends Java type imports required by the generated fixtures and key declarations.
     *
     * @param content generated test content
     * @param table current table
     * @param dtoFields actual generated DTO fields
     * @param entityPackage entity package name
     * @param entityName entity simple name
     * @param primaryKeyType primary key type
     * @param compositePrimaryKey whether the table uses composite primary key
     */
    private void appendTypeImports(
            StringBuilder content,
            Table table,
            List<Field> dtoFields,
            String entityPackage,
            String entityName,
            String primaryKeyType,
            boolean compositePrimaryKey
    ) {
        Set<String> importLines = new LinkedHashSet<>();

        if (compositePrimaryKey) {
            importLines.add("import " + entityPackage + "." + entityName + "Key;");
        }

        String primaryKeyImportLine = compositePrimaryKey
                ? null
                : buildPrimaryKeyImportLine(primaryKeyType);

        if (primaryKeyImportLine != null && !primaryKeyImportLine.isBlank()) {
            importLines.add(primaryKeyImportLine);
        }

        importLines.addAll(buildCompositePrimaryKeyExtraImports(table));
        importLines.addAll(buildFixtureExtraImports(table));
        importLines.addAll(buildImportsForFields(dtoFields));

        for (String importLine : importLines) {
            content.append(importLine).append("\n");
        }

        content.append("\n");
    }

    /**
     * Appends framework imports used by the generated tests.
     *
     * @param content generated test content
     * @param basePackage base Java package
     */
    private void appendFrameworkImports(StringBuilder content, String basePackage) {
        String exceptionPackage = PackageResolver.resolvePackageName(basePackage, "exception");

        content.append("import ").append(exceptionPackage).append(".ErrorCodes;\n");
        content.append("import ").append(exceptionPackage).append(".GeneratedRuntimeException;\n");
        content.append("import org.junit.jupiter.api.Test;\n");
        content.append("import org.junit.jupiter.api.extension.ExtendWith;\n");
        content.append("import org.mockito.InjectMocks;\n");
        content.append("import org.mockito.Mock;\n");
        content.append("import org.mockito.junit.jupiter.MockitoExtension;\n\n");

        content.append("import java.util.List;\n");
        content.append("import java.util.Optional;\n\n");

        content.append("import static org.junit.jupiter.api.Assertions.assertEquals;\n");
        content.append("import static org.junit.jupiter.api.Assertions.assertSame;\n");
        content.append("import static org.junit.jupiter.api.Assertions.assertThrows;\n");
        content.append("import static org.mockito.ArgumentMatchers.any;\n");
        content.append("import static org.mockito.BDDMockito.given;\n");
        content.append("import static org.mockito.Mockito.never;\n");
        content.append("import static org.mockito.Mockito.verify;\n\n");
    }

    /**
     * Appends the generated test class header.
     *
     * @param content generated test content
     * @param testName test class name
     * @param repositoryName repository simple name
     * @param repositoryVar repository variable name
     * @param mapperName mapper simple name
     * @param mapperVar mapper variable name
     * @param serviceImplName service implementation simple name
     * @param serviceVar service variable name
     */
    private void appendClassHeader(
            StringBuilder content,
            String testName,
            String repositoryName,
            String repositoryVar,
            String mapperName,
            String mapperVar,
            String serviceImplName,
            String serviceVar
    ) {
        content.append("@ExtendWith(MockitoExtension.class)\n");
        content.append("class ").append(testName).append(" {\n\n");

        content.append("    @Mock\n");
        content.append("    private ").append(repositoryName).append(" ").append(repositoryVar).append(";\n\n");

        content.append("    @Mock\n");
        content.append("    private ").append(mapperName).append(" ").append(mapperVar).append(";\n\n");

        content.append("    @InjectMocks\n");
        content.append("    private ").append(serviceImplName).append(" ").append(serviceVar).append(";\n\n");
    }

    /**
     * Appends the reusable NOT FOUND assertion helper method to the generated test class.
     *
     * @param content generated test content
     * @param entityName entity simple name
     */
    private void appendStatusAssertionHelpers(
            StringBuilder content,
            String entityName
    ) {
        content.append("    /**\n");
        content.append("     * Asserts that a NOT FOUND GeneratedRuntimeException is thrown for ")
                .append(entityName)
                .append(".\n");
        content.append("     *\n");
        content.append("     * @param executable executable under test\n");
        content.append("     */\n");
        content.append("    private void assertNotFound(Runnable executable) {\n");
        content.append("        GeneratedRuntimeException exception =\n");
        content.append("                assertThrows(GeneratedRuntimeException.class, executable::run);\n");
        content.append("        assertEquals(ErrorCodes.NOT_FOUND, exception.getCode());\n");
        content.append("        assertEquals(\"").append(entityName).append("\", exception.getEntity());\n");
        content.append("    }\n\n");
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
        String pluralMethodSuffix = NamingConverter.toPascalCase(
                NamingConverter.toCamelCasePlural(entityName)
        );

        content.append("    @Test\n");
        content.append("    void shouldReturnMappedDtoListWhenGetAll").append(pluralMethodSuffix).append("IsCalled() {\n");
        content.append("        List<").append(entityName).append("> entityList = List.of(")
                .append("createSample").append(entityName).append("Entity(), ")
                .append("createAnother").append(entityName).append("Entity());\n");
        content.append("        List<").append(dtoName).append("> dtoList = List.of(")
                .append("createSample").append(entityName).append("Dto(), ")
                .append("createAnother").append(entityName).append("Dto());\n\n");
        content.append("        given(").append(repositoryVar).append(".findAll()).willReturn(entityList);\n");
        content.append("        given(").append(mapperVar).append(".toDTOList(entityList)).willReturn(dtoList);\n\n");
        content.append("        List<").append(dtoName).append("> result = ").append(serviceVar)
                .append(".getAll").append(pluralMethodSuffix).append("();\n\n");
        content.append("        assertSame(dtoList, result);\n");
        content.append("        verify(").append(repositoryVar).append(").findAll();\n");
        content.append("        verify(").append(mapperVar).append(").toDTOList(entityList);\n");
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
        String serviceArguments = buildServicePrimaryKeyArguments(table);

        content.append("    @Test\n");
        content.append("    void shouldReturnMappedDtoWhenGet").append(entityName).append("ByIdIsCalled() {\n");
        content.append(buildPrimaryKeyDeclarationCode(table, primaryKeyType));
        content.append("        ").append(entityName).append(" ").append(entityVar).append(" = createSample")
                .append(entityName).append("Entity();\n");
        content.append("        ").append(dtoName).append(" ").append(dtoVar).append(" = createSample")
                .append(entityName).append("Dto();\n\n");
        content.append("        given(").append(repositoryVar).append(".findById(id)).willReturn(Optional.of(")
                .append(entityVar).append("));\n");
        content.append("        given(").append(mapperVar).append(".toDTO(").append(entityVar).append(")).willReturn(")
                .append(dtoVar).append(");\n\n");
        content.append("        ").append(dtoName).append(" result = ").append(serviceVar)
                .append(".get").append(entityName).append("ById(").append(serviceArguments).append(");\n\n");
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
        String serviceArguments = buildServicePrimaryKeyArguments(table);

        content.append("    @Test\n");
        content.append("    void shouldThrowWhenGet").append(entityName).append("ByIdCannotFindEntity() {\n");
        content.append(buildPrimaryKeyDeclarationCode(table, primaryKeyType));
        content.append("        given(").append(repositoryVar).append(".findById(id)).willReturn(Optional.empty());\n\n");
        content.append("        assertNotFound(() -> ").append(serviceVar).append(".get")
                .append(entityName).append("ById(").append(serviceArguments).append("));\n\n");
        content.append("        verify(").append(repositoryVar).append(").findById(id);\n");
        content.append("        verify(").append(mapperVar).append(", never()).toDTO(any(")
                .append(entityName).append(".class));\n");
        content.append("    }\n\n");
    }

    /**
     * Appends the createMethod service test.
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
        content.append("    void shouldCreate").append(entityName).append("WhenCreate").append(entityName)
                .append("IsCalled() {\n");
        content.append("        ").append(dtoName).append(" requestDto = createSample").append(entityName)
                .append("Dto();\n");
        content.append("        ").append(entityName).append(" mappedEntity = createSample").append(entityName)
                .append("Entity();\n");
        content.append("        ").append(entityName).append(" savedEntity = createAnother").append(entityName)
                .append("Entity();\n");
        content.append("        ").append(dtoName).append(" responseDto = createAnother").append(entityName)
                .append("Dto();\n\n");
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
     * Appends the successful update service test.
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
        String serviceArguments = buildServicePrimaryKeyArguments(table);

        content.append("    @Test\n");
        content.append("    void shouldUpdate").append(entityName).append("WhenEntityExists() {\n");
        content.append(buildPrimaryKeyDeclarationCode(table, primaryKeyType));
        content.append("        ").append(dtoName).append(" requestDto = createPatch").append(entityName)
                .append("Dto();\n");
        content.append("        ").append(entityName).append(" existingEntity = createSample").append(entityName)
                .append("Entity();\n");
        content.append("        ").append(entityName).append(" savedEntity = createAnother").append(entityName)
                .append("Entity();\n");
        content.append("        ").append(dtoName).append(" responseDto = createAnother").append(entityName)
                .append("Dto();\n\n");
        content.append("        given(").append(repositoryVar).append(".findById(id)).willReturn(Optional.of(existingEntity));\n");
        content.append("        given(").append(repositoryVar).append(".save(existingEntity)).willReturn(savedEntity);\n");
        content.append("        given(").append(mapperVar).append(".toDTO(savedEntity)).willReturn(responseDto);\n\n");
        content.append("        ").append(dtoName).append(" result = ").append(serviceVar)
                .append(".update").append(entityName).append("(").append(serviceArguments)
                .append(", requestDto);\n\n");
        content.append("        assertSame(responseDto, result);\n");
        content.append("        verify(").append(repositoryVar).append(").findById(id);\n");
        content.append("        verify(").append(mapperVar).append(").partialUpdate(existingEntity, requestDto);\n");
        content.append("        verify(").append(repositoryVar).append(").save(existingEntity);\n");
        content.append("        verify(").append(mapperVar).append(").toDTO(savedEntity);\n");
        content.append("        verify(").append(mapperVar).append(", never()).toEntity(any(")
                .append(dtoName).append(".class));\n");
        content.append("    }\n\n");
    }

    /**
     * Appends the not-found update service test.
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
        String serviceArguments = buildServicePrimaryKeyArguments(table);

        content.append("    @Test\n");
        content.append("    void shouldThrowWhenUpdate").append(entityName).append("CannotFindEntity() {\n");
        content.append(buildPrimaryKeyDeclarationCode(table, primaryKeyType));
        content.append("        ").append(dtoName).append(" requestDto = createPatch").append(entityName)
                .append("Dto();\n\n");
        content.append("        given(").append(repositoryVar).append(".findById(id)).willReturn(Optional.empty());\n\n");
        content.append("        assertNotFound(() -> ").append(serviceVar).append(".update")
                .append(entityName).append("(").append(serviceArguments).append(", requestDto));\n\n");
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
        String serviceArguments = buildServicePrimaryKeyArguments(table);

        content.append("    @Test\n");
        content.append("    void shouldDelete").append(entityName).append("WhenEntityExists() {\n");
        content.append(buildPrimaryKeyDeclarationCode(table, primaryKeyType));
        content.append("        ").append(entityName).append(" existingEntity = createSample").append(entityName)
                .append("Entity();\n\n");
        content.append("        given(").append(repositoryVar).append(".findById(id)).willReturn(Optional.of(existingEntity));\n\n");
        content.append("        ").append(serviceVar).append(".delete").append(entityName)
                .append("(").append(serviceArguments).append(");\n\n");
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
        String serviceArguments = buildServicePrimaryKeyArguments(table);

        content.append("    @Test\n");
        content.append("    void shouldThrowWhenDelete").append(entityName).append("CannotFindEntity() {\n");
        content.append(buildPrimaryKeyDeclarationCode(table, primaryKeyType));
        content.append("        given(").append(repositoryVar).append(".findById(id)).willReturn(Optional.empty());\n\n");
        content.append("        assertNotFound(() -> ").append(serviceVar).append(".delete")
                .append(entityName).append("(").append(serviceArguments).append("));\n\n");
        content.append("        verify(").append(repositoryVar).append(").findById(id);\n");
        content.append("        verify(").append(repositoryVar).append(", never()).deleteById(id);\n");
        content.append("    }\n\n");
    }

    /**
     * Appends create unique constraint tests.
     */
    private void appendCreateUniqueConstraintTests(
            StringBuilder content,
            Table table,
            String entityName,
            String dtoName,
            String repositoryVar,
            String serviceVar
    ) {
        table.getColumns().stream()
                .filter(Column::isUnique)
                .forEach(column -> {

                    String fieldName = NamingConverter.toPascalCase(
                            GeneratorSupport.unquoteIdentifier(column.getName())
                    );

                    String methodSuffix = "shouldThrowBadRequestWhenCreate" + entityName + "WithExisting" + fieldName;

                    content.append("    @Test\n");
                    content.append("    void ").append(methodSuffix).append("() {\n");
                    content.append("        ").append(dtoName).append(" requestDto = createSample")
                            .append(entityName).append("Dto();\n\n");

                    content.append("        given(").append(repositoryVar)
                            .append(".existsBy").append(fieldName)
                            .append("(requestDto.get").append(fieldName).append("()))")
                            .append(".willReturn(true);\n\n");

                    content.append("        assertThrows(GeneratedRuntimeException.class, () -> ")
                            .append(serviceVar).append(".create").append(entityName)
                            .append("(requestDto));\n\n");

                    content.append("        verify(").append(repositoryVar).append(", never()).save(any());\n");
                    content.append("    }\n\n");
                });
    }

    /**
     * Appends update validation (not-null) tests.
     */
    private void appendPatchValidationTests(
            StringBuilder content,
            Table table,
            List<Field> dtoFields,
            String entityName,
            String dtoName,
            String repositoryVar,
            String mapperVar,
            String serviceVar,
            String primaryKeyType
    ) {
        String serviceArguments = buildServicePrimaryKeyArguments(table);

        loadRequiredDtoFields(table, dtoFields).forEach(field -> {

            String fieldName = NamingConverter.toPascalCase(field.getName());

            content.append("    @Test\n");
            content.append("    void shouldThrowWhenUpdate").append(entityName)
                    .append(fieldName).append("IsNull() {\n");

            content.append(buildPrimaryKeyDeclarationCode(table, primaryKeyType));

            content.append("        ").append(dtoName).append(" requestDto = createPatch")
                    .append(entityName).append("Dto();\n");
            content.append("        requestDto.set").append(fieldName).append("(null);\n\n");

            content.append("        given(").append(repositoryVar)
                    .append(".findById(id)).willReturn(Optional.of(createSample")
                    .append(entityName).append("Entity()));\n\n");

            content.append("        assertThrows(IllegalArgumentException.class, () -> ")
                    .append(serviceVar).append(".update").append(entityName)
                    .append("(").append(serviceArguments).append(", requestDto));\n\n");

            content.append("        verify(").append(mapperVar).append(", never()).partialUpdate(any(), any());\n");
            content.append("        verify(").append(repositoryVar).append(", never()).save(any());\n");
            content.append("    }\n\n");
        });
    }

    /**
     * Loads required DTO fields that actually exist in the generated DTO source.
     *
     * @param table current table
     * @param dtoFields actual generated DTO fields
     * @return dto fields that are required and safe for null-validation tests
     */
    private List<Field> loadRequiredDtoFields(Table table, List<Field> dtoFields) {
        if (dtoFields == null || dtoFields.isEmpty()) {
            return List.of();
        }

        Set<String> requiredColumnFieldNames = table.getColumns().stream()
                .filter(Objects::nonNull)
                .filter(column -> !column.isNullable())
                .filter(column -> !column.isPrimaryKey())
                .map(Column::getName)
                .filter(Objects::nonNull)
                .map(GeneratorSupport::unquoteIdentifier)
                .map(NamingConverter::toCamelCase)
                .collect(java.util.stream.Collectors.toCollection(LinkedHashSet::new));

        return dtoFields.stream()
                .filter(Objects::nonNull)
                .filter(field -> field.getName() != null && !field.getName().isBlank())
                .filter(field -> requiredColumnFieldNames.contains(field.getName()))
                .filter(field -> !"id".equals(field.getName()))
                .filter(field -> !"dateCreated".equals(field.getName()))
                .filter(field -> !"lastUpdated".equals(field.getName()))
                .filter(this::canAssignNullToField)
                .toList();
    }

    /**
     * Determines whether the generated DTO field can safely receive null in validation tests.
     *
     * @param field dto field metadata
     * @return true when null can be assigned safely
     */
    private boolean canAssignNullToField(Field field) {
        if (field == null) {
            return false;
        }

        String fieldType = normalizeJavaType(field.getType());

        return !"int".equals(fieldType)
                && !"long".equals(fieldType)
                && !"short".equals(fieldType)
                && !"byte".equals(fieldType)
                && !"boolean".equals(fieldType)
                && !"double".equals(fieldType)
                && !"float".equals(fieldType)
                && !"char".equals(fieldType);
    }

    /**
     * Appends fixture factory methods for the generated service test.
     *
     * @param content generated test content
     * @param table current table
     * @param entityMetadata generated entity metadata
     * @param dtoFields actual generated DTO fields
     * @param entityName entity simple name
     * @param dtoName dto simple name
     */
    private void appendFixtureMethods(
            StringBuilder content,
            Table table,
            Entity entityMetadata,
            List<Field> dtoFields,
            String entityName,
            String dtoName
    ) {
        appendEntityFixtureMethod(content, entityMetadata, entityName, "createSample" + entityName + "Entity", 1);
        appendEntityFixtureMethod(content, entityMetadata, entityName, "createAnother" + entityName + "Entity", 2);
        appendDtoFixtureMethod(content, table, dtoFields, dtoName, "createSample" + entityName + "Dto", 1, false);
        appendDtoFixtureMethod(content, table, dtoFields, dtoName, "createAnother" + entityName + "Dto", 2, false);
        appendDtoFixtureMethod(content, table, dtoFields, dtoName, "createPatch" + entityName + "Dto", 3, true);
    }

    /**
     * Appends an entity fixture factory method.
     *
     * @param content generated test content
     * @param entityMetadata generated entity metadata
     * @param entityName entity simple name
     * @param methodName generated method name
     * @param variant sample variant index
     */
    private void appendEntityFixtureMethod(
            StringBuilder content,
            Entity entityMetadata,
            String entityName,
            String methodName,
            int variant
    ) {
        content.append("    /**\n");
        content.append("     * Creates a populated ").append(entityName).append(" fixture for service tests.\n");
        content.append("     *\n");
        content.append("     * @return populated entity fixture\n");
        content.append("     */\n");
        content.append("    private ").append(entityName).append(" ").append(methodName).append("() {\n");
        content.append("        ").append(entityName).append(" entity = new ").append(entityName).append("();\n");
        appendEntityFixtureSetterLines(content, getSafeEntityFields(entityMetadata), variant);
        content.append("        return entity;\n");
        content.append("    }\n\n");
    }

    /**
     * Appends a DTO fixture factory method.
     *
     * @param content generated test content
     * @param table current table
     * @param dtoFields actual generated DTO fields
     * @param dtoName dto simple name
     * @param methodName generated method name
     * @param variant sample variant index
     * @param skipPrimaryKeys whether primary key fields should be skipped
     */
    private void appendDtoFixtureMethod(
            StringBuilder content,
            Table table,
            List<Field> dtoFields,
            String dtoName,
            String methodName,
            int variant,
            boolean skipPrimaryKeys
    ) {
        content.append("    /**\n");
        content.append("     * Creates a populated ").append(dtoName).append(" fixture for service tests.\n");
        content.append("     *\n");
        content.append("     * @return populated dto fixture\n");
        content.append("     */\n");
        content.append("    private ").append(dtoName).append(" ").append(methodName).append("() {\n");
        content.append("        ").append(dtoName).append(" dto = new ").append(dtoName).append("();\n");
        appendDtoFixtureSetterLines(content, table, dtoFields, variant, skipPrimaryKeys);
        content.append("        return dto;\n");
        content.append("    }\n\n");
    }

    /**
     * Appends fixture setter lines for the entity using the real generated fields.
     *
     * @param content generated test content
     * @param fields generated entity fields
     * @param variant sample variant index
     */
    private void appendEntityFixtureSetterLines(
            StringBuilder content,
            List<Field> fields,
            int variant
    ) {
        String targetVariable = "entity";

        for (Field field : fields) {
            String setterName = resolveSetterName(field);
            String javaLiteral = sampleLiteralForField(field, variant);

            if ("null".equals(javaLiteral)) {
                continue;
            }

            content.append("        ")
                    .append(targetVariable)
                    .append(".")
                    .append(setterName)
                    .append("(")
                    .append(javaLiteral)
                    .append(");\n");
        }

        content.append("\n");
    }

    /**
     * Appends fixture setter lines for the DTO using the actual generated DTO fields.
     *
     * @param content generated test content
     * @param table current table
     * @param dtoFields actual generated DTO fields
     * @param variant sample variant index
     * @param skipPrimaryKeys whether primary key fields should be skipped
     */
    private void appendDtoFixtureSetterLines(
            StringBuilder content,
            Table table,
            List<Field> dtoFields,
            int variant,
            boolean skipPrimaryKeys
    ) {
        String targetVariable = "dto";

        for (Field field : dtoFields) {
            if (shouldSkipField(table, field, skipPrimaryKeys)) {
                continue;
            }

            String setterName = resolveSetterName(field);
            String javaLiteral = sampleLiteralForDtoField(field, variant);

            if ("null".equals(javaLiteral)) {
                continue;
            }

            content.append("        ")
                    .append(targetVariable)
                    .append(".")
                    .append(setterName)
                    .append("(")
                    .append(javaLiteral)
                    .append(");\n");
        }

        content.append("\n");
    }

    /**
     * Resolves the setter name for a generated field.
     *
     * @param field generated field metadata
     * @return setter method name
     */
    private String resolveSetterName(Field field) {
        String fieldName = field.getName();
        String fieldType = GeneratorSupport.trimToEmpty(field.getType());

        if ("boolean".equals(fieldType)
                && fieldName.startsWith("is")
                && fieldName.length() > 2
                && Character.isUpperCase(fieldName.charAt(2))) {
            return "set" + fieldName.substring(2);
        }

        return "set" + NamingConverter.toPascalCase(fieldName);
    }

    /**
     * Builds a sample Java literal for a generated entity field.
     *
     * @param field generated field metadata
     * @param variant sample variant index
     * @return Java literal source code
     */
    private String sampleLiteralForField(Field field, int variant) {
        String javaType = normalizeJavaType(field.getType());
        String fieldName = field.getName();

        return switch (javaType) {
            case "UUID" -> buildUuidSampleLiteral(variant);
            case "BigDecimal" -> "new BigDecimal(\"" + variant + ".00\")";
            case "BigInteger" -> "new BigInteger(\"" + variant + "\")";
            case "Integer", "int" -> String.valueOf(variant);
            case "Short", "short" -> "(short) " + variant;
            case "Byte", "byte" -> "(byte) " + variant;
            case "Boolean", "boolean" -> variant % 2 == 0 ? "false" : "true";
            case "LocalDate" -> "LocalDate.of(2025, 1, " + Math.min(variant, 28) + ")";
            case "LocalTime" -> "LocalTime.of(" + (9 + variant) + ", 0, 0)";
            case "LocalDateTime" -> "LocalDateTime.of(2025, 1, " + Math.min(variant, 28) + ", 10, 0, 0)";
            case "Instant" -> "Instant.parse(\"2025-01-0" + variant + "T10:00:00Z\")";
            case "OffsetDateTime" -> "OffsetDateTime.parse(\"2025-01-0" + variant + "T10:00:00Z\")";
            case "ZonedDateTime" -> "ZonedDateTime.parse(\"2025-01-0" + variant + "T10:00:00Z\")";
            case "Character", "char" -> variant % 2 == 0 ? "'B'" : "'A'";
            case "Double", "double" -> variant + ".0d";
            case "Float", "float" -> variant + ".0f";
            case "String" -> "\"" + fieldName + "-value-" + variant + "\"";
            case "byte[]" -> variant % 2 == 0 ? "new byte[]{2}" : "new byte[]{1}";
            case "Byte[]" -> variant % 2 == 0 ? "new Byte[]{2}" : "new Byte[]{1}";
            case "Long", "long" -> variant + "L";
            default -> "null";
        };
    }

    /**
     * Builds a sample Java literal for an actual generated DTO field.
     *
     * @param field actual generated DTO field metadata
     * @param variant sample variant index
     * @return Java literal source code
     */
    private String sampleLiteralForDtoField(Field field, int variant) {
        if (field == null) {
            return "null";
        }

        String normalizedJavaType = normalizeJavaType(field.getType());
        String fieldName = field.getName();

        if (normalizedJavaType.endsWith("Dto")) {
            return "new " + normalizedJavaType + "()";
        }

        return switch (normalizedJavaType) {
            case "UUID" -> buildUuidSampleLiteral(variant);
            case "BigDecimal" -> "new BigDecimal(\"" + variant + ".00\")";
            case "BigInteger" -> "new BigInteger(\"" + variant + "\")";
            case "Integer", "int" -> String.valueOf(variant);
            case "Short", "short" -> "(short) " + variant;
            case "Byte", "byte" -> "(byte) " + variant;
            case "Boolean", "boolean" -> variant % 2 == 0 ? "false" : "true";
            case "LocalDate" -> "LocalDate.of(2025, 1, " + Math.min(variant, 28) + ")";
            case "LocalTime" -> "LocalTime.of(" + (9 + variant) + ", 0, 0)";
            case "LocalDateTime" -> "LocalDateTime.of(2025, 1, " + Math.min(variant, 28) + ", 10, 0, 0)";
            case "Instant" -> "Instant.parse(\"2025-01-0" + variant + "T10:00:00Z\")";
            case "OffsetDateTime" -> "OffsetDateTime.parse(\"2025-01-0" + variant + "T10:00:00Z\")";
            case "ZonedDateTime" -> "ZonedDateTime.parse(\"2025-01-0" + variant + "T10:00:00Z\")";
            case "Character", "char" -> variant % 2 == 0 ? "'B'" : "'A'";
            case "Double", "double" -> variant + ".0d";
            case "Float", "float" -> variant + ".0f";
            case "String" -> "\"" + fieldName + "-value-" + variant + "\"";
            case "byte[]" -> variant % 2 == 0 ? "new byte[]{2}" : "new byte[]{1}";
            case "Byte[]" -> variant % 2 == 0 ? "new Byte[]{2}" : "new Byte[]{1}";
            case "Long", "long" -> variant + "L";
            default -> "null";
        };
    }

    /**
     * Normalizes a Java type by removing package prefixes.
     *
     * @param rawType raw Java type
     * @return normalized simple Java type
     */
    private String normalizeJavaType(String rawType) {
        String javaType = GeneratorSupport.trimToEmpty(rawType);

        if (javaType.isBlank()) {
            return "Long";
        }

        if (javaType.contains(".")) {
            return javaType.substring(javaType.lastIndexOf('.') + 1);
        }

        return javaType;
    }

    /**
     * Builds Java code that declares and initializes the primary key variables used by service tests.
     *
     * <p>
     * For single primary keys, this method declares only {@code id}.
     * For composite primary keys, this method declares each flattened primary key component
     * used to call the service method and also builds the composite {@code id} object used
     * by repository stubbing and verification.
     * </p>
     *
     * @param table current table
     * @param primaryKeyType primary key type
     * @return generated Java code
     */
    private String buildPrimaryKeyDeclarationCode(Table table, String primaryKeyType) {
        String indent = "        ";

        if (!hasCompositePrimaryKey(table)) {
            return indent + primaryKeyType + " id = " + sampleValueForType(primaryKeyType) + ";\n\n";
        }

        StringBuilder builder = new StringBuilder();

        for (Column column : getPrimaryKeyColumns(table)) {
            String javaType = detectJavaTypeForColumn(column);
            String variableName = resolvePrimaryKeyComponentVariableName(column);
            String javaLiteral = sampleLiteralForColumn(column, 1);

            builder.append(indent)
                    .append(javaType)
                    .append(" ")
                    .append(variableName)
                    .append(" = ")
                    .append(javaLiteral)
                    .append(";\n");
        }

        builder.append("\n");
        builder.append(indent)
                .append(primaryKeyType)
                .append(" id = new ")
                .append(primaryKeyType)
                .append("();\n");

        for (Column column : getPrimaryKeyColumns(table)) {
            String normalizedColumnName = GeneratorSupport.unquoteIdentifier(column.getName());
            String setterName = "set" + NamingConverter.toPascalCase(normalizedColumnName);
            String variableName = resolvePrimaryKeyComponentVariableName(column);

            builder.append(indent)
                    .append("id.")
                    .append(setterName)
                    .append("(")
                    .append(variableName)
                    .append(");\n");
        }

        builder.append("\n");
        return builder.toString();
    }

    /**
     * Builds a sample Java literal for a column.
     *
     * @param column source column
     * @param variant sample variant index
     * @return Java literal source code
     */
    private String sampleLiteralForColumn(Column column, int variant) {
        String javaType = detectJavaTypeForColumn(column);
        String normalizedColumnName = GeneratorSupport.unquoteIdentifier(column.getName());

        return switch (GeneratorSupport.trimToEmpty(javaType)) {
            case "UUID" -> buildUuidSampleLiteral(variant);
            case "BigDecimal" -> "new BigDecimal(\"" + variant + ".00\")";
            case "BigInteger" -> "new BigInteger(\"" + variant + "\")";
            case "Integer", "int" -> String.valueOf(variant);
            case "Short", "short" -> "(short) " + variant;
            case "Byte", "byte" -> "(byte) " + variant;
            case "Boolean", "boolean" -> variant % 2 == 0 ? "false" : "true";
            case "LocalDate" -> "LocalDate.of(2025, 1, " + Math.min(variant, 28) + ")";
            case "LocalTime" -> "LocalTime.of(" + (9 + variant) + ", 0, 0)";
            case "LocalDateTime" -> "LocalDateTime.of(2025, 1, " + Math.min(variant, 28) + ", 10, 0, 0)";
            case "Instant" -> "Instant.parse(\"2025-01-0" + variant + "T10:00:00Z\")";
            case "OffsetDateTime" -> "OffsetDateTime.parse(\"2025-01-0" + variant + "T10:00:00Z\")";
            case "ZonedDateTime" -> "ZonedDateTime.parse(\"2025-01-0" + variant + "T10:00:00Z\")";
            case "Character", "char" -> variant % 2 == 0 ? "'B'" : "'A'";
            case "Double", "double" -> variant + ".0d";
            case "Float", "float" -> variant + ".0f";
            case "String" -> "\"" + normalizedColumnName + "-value-" + variant + "\"";
            case "byte[]" -> variant % 2 == 0 ? "new byte[]{2}" : "new byte[]{1}";
            case "Byte[]" -> variant % 2 == 0 ? "new Byte[]{2}" : "new Byte[]{1}";
            case "Long", "long" -> variant + "L";
            default -> "null";
        };
    }

    /**
     * Builds a deterministic UUID sample literal for a fixture variant.
     *
     * @param variant sample variant index
     * @return UUID literal source code
     */
    private String buildUuidSampleLiteral(int variant) {
        return switch (variant) {
            case 1 -> "UUID.fromString(\"123e4567-e89b-12d3-a456-426614174000\")";
            case 2 -> "UUID.fromString(\"223e4567-e89b-12d3-a456-426614174000\")";
            default -> "UUID.fromString(\"323e4567-e89b-12d3-a456-426614174000\")";
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

        List<Column> primaryKeyColumns = getPrimaryKeyColumns(table);
        return buildImportsForColumns(primaryKeyColumns);
    }

    /**
     * Builds extra import lines required by generated sample fixtures.
     *
     * @param table current table
     * @return import lines
     */
    private List<String> buildFixtureExtraImports(Table table) {
        List<Column> usedColumns = table.getColumns().stream()
                .filter(Objects::nonNull)
                .filter(this::isColumnUsedInAnyFixture)
                .toList();

        return buildImportsForColumns(usedColumns);
    }

    /**
     * Determines whether the provided column is actually used
     * in any generated fixture method.
     *
     * @param column source column
     * @return true when the column contributes a non-null fixture value
     */
    private boolean isColumnUsedInAnyFixture(Column column) {
        if (column == null) {
            return false;
        }

        if (!"null".equals(sampleLiteralForColumn(column, 1))) {
            return true;
        }

        if (!"null".equals(sampleLiteralForColumn(column, 2))) {
            return true;
        }

        return !column.isPrimaryKey() && !"null".equals(sampleLiteralForColumn(column, 3));
    }

    /**
     * Builds Java import lines for the provided columns.
     *
     * @param columns source columns
     * @return import lines
     */
    private List<String> buildImportsForColumns(List<Column> columns) {
        boolean needsUuid = false;
        boolean needsBigDecimal = false;
        boolean needsBigInteger = false;
        boolean needsLocalDate = false;
        boolean needsLocalTime = false;
        boolean needsLocalDateTime = false;
        boolean needsInstant = false;
        boolean needsOffsetDateTime = false;
        boolean needsZonedDateTime = false;

        for (Column column : columns) {
            String javaType = detectJavaTypeForColumn(column);

            switch (GeneratorSupport.trimToEmpty(javaType)) {
                case "UUID" -> needsUuid = true;
                case "BigDecimal" -> needsBigDecimal = true;
                case "BigInteger" -> needsBigInteger = true;
                case "LocalDate" -> needsLocalDate = true;
                case "LocalTime" -> needsLocalTime = true;
                case "LocalDateTime" -> needsLocalDateTime = true;
                case "Instant" -> needsInstant = true;
                case "OffsetDateTime" -> needsOffsetDateTime = true;
                case "ZonedDateTime" -> needsZonedDateTime = true;
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
        if (needsLocalTime) {
            imports.add("import java.time.LocalTime;");
        }
        if (needsLocalDateTime) {
            imports.add("import java.time.LocalDateTime;");
        }
        if (needsInstant) {
            imports.add("import java.time.Instant;");
        }
        if (needsOffsetDateTime) {
            imports.add("import java.time.OffsetDateTime;");
        }
        if (needsZonedDateTime) {
            imports.add("import java.time.ZonedDateTime;");
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
            case "UUID" -> buildUuidSampleLiteral(1);
            case "BigDecimal" -> "new BigDecimal(\"1\")";
            case "BigInteger" -> "new BigInteger(\"1\")";
            case "Integer", "int" -> "1";
            case "Short", "short" -> "(short) 1";
            case "Byte", "byte" -> "(byte) 1";
            case "Boolean", "boolean" -> "true";
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
     * Resolves the Java type for a column.
     *
     * @param column source column
     * @return resolved Java type
     */
    private String detectJavaTypeForColumn(Column column) {
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

        return detectJavaTypeForColumn(pkColumns.get(0));
    }

    /**
     * Finds the generated entity metadata for the provided entity name.
     *
     * @param entities generated entities
     * @param entityName target entity name
     * @return matching entity metadata, or null when not found
     */
    private Entity findEntityMetadata(List<Entity> entities, String entityName) {
        for (Entity entity : entities) {
            if (entity == null) {
                continue;
            }

            if (entityName.equals(entity.getName())) {
                return entity;
            }
        }

        return null;
    }

    /**
     * Returns the generated entity fields or an empty list when none exist.
     *
     * @param entityMetadata generated entity metadata
     * @return safe entity field list
     */
    private List<Field> getSafeEntityFields(Entity entityMetadata) {
        if (entityMetadata == null || entityMetadata.getFields() == null) {
            return List.of();
        }

        return entityMetadata.getFields();
    }

    /**
     * Determines whether a generated field should be skipped when creating fixtures.
     *
     * @param table current table
     * @param field generated field metadata
     * @param skipPrimaryKeys whether primary key fields should be skipped
     * @return true when the field should be skipped
     */
    private boolean shouldSkipField(Table table, Field field, boolean skipPrimaryKeys) {
        if (!skipPrimaryKeys || field == null) {
            return false;
        }

        String fieldName = GeneratorSupport.trimToEmpty(field.getName());

        if (fieldName.isBlank()) {
            return false;
        }

        if ("id".equals(fieldName) && hasCompositePrimaryKey(table)) {
            return true;
        }

        for (Column primaryKeyColumn : getPrimaryKeyColumns(table)) {
            String primaryKeyFieldName = NamingConverter.decapitalizeFirstLetter(
                    NamingConverter.toPascalCase(
                            GeneratorSupport.unquoteIdentifier(primaryKeyColumn.getName())
                    )
            );

            if (fieldName.equals(primaryKeyFieldName)) {
                return true;
            }
        }

        return false;
    }

    /**
     * Loads actual generated DTO fields from the written DTO source file.
     *
     * @param outputDir project root output directory
     * @param basePackage base Java package
     * @param dtoName dto simple name
     * @return actual dto fields in declaration order
     */
    private List<Field> loadGeneratedDtoFields(
            String outputDir,
            String basePackage,
            String dtoName
    ) {
        Path dtoPath = PackageResolver.resolvePath(outputDir, basePackage, "dto")
                .resolve(dtoName + ".java");

        if (!java.nio.file.Files.exists(dtoPath)) {
            return List.of();
        }

        List<Field> dtoFields = new ArrayList<>();
        java.util.regex.Pattern fieldPattern =
                java.util.regex.Pattern.compile("^\\s*private\\s+(.+?)\\s+(\\w+)\\s*;\\s*$");

        try {
            for (String line : java.nio.file.Files.readAllLines(dtoPath)) {
                java.util.regex.Matcher matcher = fieldPattern.matcher(line);
                if (!matcher.matches()) {
                    continue;
                }

                String fieldType = matcher.group(1).trim();
                String fieldName = matcher.group(2).trim();

                dtoFields.add(Field.builder()
                        .name(fieldName)
                        .type(fieldType)
                        .build());
            }
        } catch (java.io.IOException exception) {
            log.warn("Could not read DTO file for service test generation: {}", dtoPath, exception);
        }

        return dtoFields;
    }

    /**
     * Builds extra import lines required by generated DTO field fixtures.
     *
     * @param fields actual generated DTO fields
     * @return import lines
     */
    private List<String> buildImportsForFields(List<Field> fields) {
        boolean needsUuid = false;
        boolean needsBigDecimal = false;
        boolean needsBigInteger = false;
        boolean needsLocalDate = false;
        boolean needsLocalTime = false;
        boolean needsLocalDateTime = false;
        boolean needsInstant = false;
        boolean needsOffsetDateTime = false;
        boolean needsZonedDateTime = false;

        for (Field field : fields) {
            if (field == null) {
                continue;
            }

            String javaType = normalizeJavaType(field.getType());

            switch (GeneratorSupport.trimToEmpty(javaType)) {
                case "UUID" -> needsUuid = true;
                case "BigDecimal" -> needsBigDecimal = true;
                case "BigInteger" -> needsBigInteger = true;
                case "LocalDate" -> needsLocalDate = true;
                case "LocalTime" -> needsLocalTime = true;
                case "LocalDateTime" -> needsLocalDateTime = true;
                case "Instant" -> needsInstant = true;
                case "OffsetDateTime" -> needsOffsetDateTime = true;
                case "ZonedDateTime" -> needsZonedDateTime = true;
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
        if (needsLocalTime) {
            imports.add("import java.time.LocalTime;");
        }
        if (needsLocalDateTime) {
            imports.add("import java.time.LocalDateTime;");
        }
        if (needsInstant) {
            imports.add("import java.time.Instant;");
        }
        if (needsOffsetDateTime) {
            imports.add("import java.time.OffsetDateTime;");
        }
        if (needsZonedDateTime) {
            imports.add("import java.time.ZonedDateTime;");
        }

        return imports;
    }

    /**
     * Builds the ordered service method argument list for the current table primary key.
     *
     * <p>
     * For single primary keys, this method returns {@code id}.
     * For composite primary keys, it returns the flattened primary key component variable names
     * in declaration order, for example {@code businessLocation, language}.
     * </p>
     *
     * @param table current table
     * @return comma-separated service method argument list
     */
    private String buildServicePrimaryKeyArguments(Table table) {
        if (!hasCompositePrimaryKey(table)) {
            return "id";
        }

        StringBuilder builder = new StringBuilder();

        List<Column> primaryKeyColumns = getPrimaryKeyColumns(table);
        for (int index = 0; index < primaryKeyColumns.size(); index++) {
            if (index > 0) {
                builder.append(", ");
            }

            builder.append(resolvePrimaryKeyComponentVariableName(primaryKeyColumns.get(index)));
        }

        return builder.toString();
    }

    /**
     * Resolves the flattened variable name used for a composite primary key component.
     *
     * @param column primary key column
     * @return generated variable name
     */
    private String resolvePrimaryKeyComponentVariableName(Column column) {
        if (column == null || column.getName() == null || column.getName().isBlank()) {
            return "idPart";
        }

        String columnName = GeneratorSupport.unquoteIdentifier(column.getName());

        if (columnName == null || columnName.isBlank()) {
            return "idPart";
        }

        return NamingConverter.toCamelCase(columnName);
    }
}