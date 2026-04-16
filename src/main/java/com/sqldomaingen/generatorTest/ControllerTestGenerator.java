package com.sqldomaingen.generatorTest;

import com.sqldomaingen.model.Column;
import com.sqldomaingen.model.Table;
import com.sqldomaingen.util.GeneratorImportSupport;
import com.sqldomaingen.util.GeneratorSupport;
import com.sqldomaingen.util.JavaTypeSupport;
import com.sqldomaingen.util.NamingConverter;
import com.sqldomaingen.util.PackageResolver;
import lombok.extern.log4j.Log4j2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Generates controller test classes for parsed tables.
 */
@Log4j2
public class ControllerTestGenerator {

    /**
     * Generates controller test classes for all parsed tables.
     *
     * @param tables parsed SQL tables
     * @param outputDir project root output directory
     * @param basePackage base Java package
     * @param overwrite overwrite existing files when true
     */
    public void generateControllerTests(List<Table> tables, String outputDir, String basePackage, boolean overwrite) {
        Objects.requireNonNull(tables, "tables must not be null");
        Objects.requireNonNull(outputDir, "outputDir must not be null");
        Objects.requireNonNull(basePackage, "basePackage must not be null");

        Path controllerTestDir = GeneratorSupport.ensureDirectory(
                PackageResolver.resolvePath(outputDir, basePackage, "controller", true)
        );

        String testPackage = PackageResolver.resolvePackageName(basePackage, "controller");
        String controllerPackage = PackageResolver.resolvePackageName(basePackage, "controller");
        String dtoPackage = PackageResolver.resolvePackageName(basePackage, "dto");
        String servicePackage = PackageResolver.resolvePackageName(basePackage, "service");

        for (Table table : tables) {
            if (table == null || table.getName() == null || table.getName().isBlank()) {
                continue;
            }

            String entityName = NamingConverter.toPascalCase(
                    GeneratorSupport.normalizeTableName(table.getName())
            );
            String dtoName = entityName + "Dto";
            String testName = entityName + "ControllerTest";
            String controllerName = entityName + "Controller";
            String serviceName = entityName + "Service";
            String serviceVar = NamingConverter.decapitalizeFirstLetter(entityName) + "Service";

            Map<String, String> dtoFieldTypes = loadGeneratedDtoFieldTypes(outputDir, basePackage, dtoName);

            boolean compositePrimaryKey = hasCompositePrimaryKey(table);
            boolean shouldImportEq = true;
            List<Column> primaryKeyColumns = getPrimaryKeyColumns(table);
            String apiPath = "/api/" + NamingConverter.toKebabCase(entityName);

            StringBuilder content = new StringBuilder();

            appendPackageAndImports(
                    content,
                    testPackage,
                    controllerPackage,
                    controllerName,
                    dtoPackage,
                    dtoName,
                    servicePackage,
                    serviceName,
                    shouldImportEq,
                    basePackage,
                    table,
                    dtoFieldTypes
            );

            content.append("@AutoConfigureMockMvc(addFilters = false)\n");
            content.append("@WebMvcTest(").append(controllerName).append(".class)\n");
            content.append("class ").append(testName).append(" {\n\n");

            appendFields(content, serviceName, serviceVar);
            appendGetAllTest(content, entityName, dtoName, serviceVar, apiPath);

            if (compositePrimaryKey) {
                appendCompositeGetByIdTests(content, entityName, dtoName, serviceVar, apiPath, primaryKeyColumns);
                appendCompositeCreateTests(content, table, entityName, dtoName, serviceVar, apiPath, dtoFieldTypes);
                appendCompositePatchTests(content, entityName, dtoName, serviceVar, apiPath, primaryKeyColumns);
                appendCompositeDeleteTests(content, entityName, serviceVar, apiPath, primaryKeyColumns);
            } else {
                appendSinglePrimaryKeyTests(content, table, entityName, dtoName, serviceVar, apiPath, dtoFieldTypes);
            }

            appendCreateDtoFixtureMethod(content, table, dtoName, entityName, dtoFieldTypes);
            content.append("}\n");

            GeneratorSupport.writeFile(
                    controllerTestDir.resolve(testName + ".java"),
                    content.toString(),
                    overwrite
            );
        }

        log.info("Controller test classes generated under: {}", controllerTestDir.toAbsolutePath());
    }

    /**
     * Appends package and import statements.
     *
     * @param content generated file content
     * @param testPackage test package name
     * @param controllerPackage controller package name
     * @param controllerName controller simple name
     * @param dtoPackage dto package name
     * @param dtoName dto simple name
     * @param servicePackage service package name
     * @param serviceName service simple name
     * @param shouldImportEq whether eq matcher import should be added
     * @param basePackage base package name
     * @param table current table
     * @param dtoFieldTypes actual DTO field types
     */
    private void appendPackageAndImports(
            StringBuilder content,
            String testPackage,
            String controllerPackage,
            String controllerName,
            String dtoPackage,
            String dtoName,
            String servicePackage,
            String serviceName,
            boolean shouldImportEq,
            String basePackage,
            Table table,
            Map<String, String> dtoFieldTypes
    ) {
        String exceptionPackage = PackageResolver.resolvePackageName(basePackage, "exception");

        content.append("package ").append(testPackage).append(";\n\n");

        if (!testPackage.equals(controllerPackage)) {
            content.append("import ").append(controllerPackage).append(".").append(controllerName).append(";\n");
        }

        content.append("import ").append(dtoPackage).append(".").append(dtoName).append(";\n");
        content.append("import ").append(servicePackage).append(".").append(serviceName).append(";\n");
        content.append("import ").append(exceptionPackage).append(".ErrorCodes;\n");
        content.append("import ").append(exceptionPackage).append(".GeneratedRuntimeException;\n");

        for (String nestedDtoType : resolveNestedDtoImports(dtoFieldTypes, dtoName)) {
            content.append("import ").append(dtoPackage).append(".").append(nestedDtoType).append(";\n");
        }

        content.append("\n");

        content.append("import com.fasterxml.jackson.databind.ObjectMapper;\n");
        content.append("import org.junit.jupiter.api.Test;\n");
        content.append("import org.springframework.beans.factory.annotation.Autowired;\n");
        content.append("import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;\n");
        content.append("import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;\n");
        content.append("import org.springframework.http.MediaType;\n");
        content.append("import org.springframework.test.context.bean.override.mockito.MockitoBean;\n");
        content.append("import org.springframework.test.web.servlet.MockMvc;\n\n");

        content.append("import java.util.List;\n");

        Set<String> extraImports = GeneratorImportSupport.resolveImports(
                table,
                (column, type) -> usesTypeInGeneratedTest(column, dtoFieldTypes, type)
        );

        for (String importLine : extraImports) {
            if (!"import java.util.List;".equals(importLine)) {
                content.append(importLine).append("\n");
            }
        }

        content.append("\n");

        content.append("import static org.mockito.ArgumentMatchers.any;\n");

        if (shouldImportEq) {
            content.append("import static org.mockito.ArgumentMatchers.eq;\n");
        }

        content.append("import static org.mockito.BDDMockito.given;\n");
        content.append("import static org.mockito.BDDMockito.willDoNothing;\n");
        content.append("import static org.mockito.BDDMockito.willThrow;\n");
        content.append("import static org.mockito.Mockito.verify;\n");
        content.append("import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;\n");
        content.append("import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;\n");
        content.append("import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;\n");
        content.append("import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;\n");
        content.append("import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;\n");
        content.append("import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;\n\n");
    }


    /**
     * Resolves additional nested DTO imports required by generated controller tests.
     *
     * @param dtoFieldTypes actual DTO field types
     * @param rootDtoName current root DTO simple name
     * @return nested DTO simple names that must be imported
     */
    private Set<String> resolveNestedDtoImports(Map<String, String> dtoFieldTypes, String rootDtoName) {
        Set<String> imports = new java.util.TreeSet<>();

        if (dtoFieldTypes == null || dtoFieldTypes.isEmpty()) {
            return imports;
        }

        for (String rawType : dtoFieldTypes.values()) {
            String simpleType = resolveSimpleDtoType(rawType);

            if (simpleType == null || simpleType.isBlank()) {
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
     * Appends test class fields.
     *
     * @param content generated file content
     * @param serviceName service simple name
     * @param serviceVar service variable name
     */
    private void appendFields(StringBuilder content, String serviceName, String serviceVar) {
        content.append("    @Autowired\n");
        content.append("    private MockMvc mockMvc;\n\n");

        content.append("    @Autowired\n");
        content.append("    private ObjectMapper objectMapper;\n\n");

        content.append("    @MockitoBean\n");
        content.append("    private ").append(serviceName).append(" ").append(serviceVar).append(";\n\n");
    }

    /**
     * Appends the get-all controller test.
     *
     * @param content generated test content
     * @param entityName entity simple name
     * @param dtoName dto simple name
     * @param serviceVar service variable name
     * @param apiPath api base path
     */
    private void appendGetAllTest(StringBuilder content,
                                  String entityName,
                                  String dtoName,
                                  String serviceVar,
                                  String apiPath) {
        String pluralMethodSuffix = NamingConverter.toPascalCase(
                NamingConverter.toCamelCasePlural(entityName)
        );

        content.append("    @Test\n");
        content.append("    void shouldReturnOkForGetAll() throws Exception {\n");
        content.append("        given(").append(serviceVar).append(".getAll").append(pluralMethodSuffix)
                .append("()).willReturn(List.of(new ").append(dtoName).append("()));\n\n");

        content.append("        mockMvc.perform(get(\"").append(apiPath).append("\"))\n");
        content.append("                .andExpect(status().isOk())\n");
        content.append("                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));\n\n");

        content.append("        verify(").append(serviceVar).append(").getAll").append(pluralMethodSuffix).append("();\n");
        content.append("    }\n\n");
    }

    /**
     * Appends all single-primary-key controller tests.
     *
     * @param content generated test content
     * @param table current table
     * @param entityName entity simple name
     * @param dtoName dto simple name
     * @param serviceVar service variable name
     * @param apiPath api base path
     * @param dtoFieldTypes actual DTO field types
     */
    private void appendSinglePrimaryKeyTests(StringBuilder content,
                                             Table table,
                                             String entityName,
                                             String dtoName,
                                             String serviceVar,
                                             String apiPath,
                                             Map<String, String> dtoFieldTypes) {
        String primaryKeyType = detectSinglePrimaryKeyType(table);
        String sampleId = sampleValueForType(primaryKeyType, 1);

        appendSingleGetByIdTests(content, entityName, dtoName, serviceVar, apiPath, primaryKeyType, sampleId);
        appendSingleCreateTests(content, table, entityName, dtoName, serviceVar, apiPath, dtoFieldTypes);
        appendSinglePatchTests(content, entityName, dtoName, serviceVar, apiPath, primaryKeyType, sampleId);
        appendSingleDeleteTests(content, entityName, serviceVar, apiPath, primaryKeyType, sampleId);
    }

    /**
     * Appends the creation validation-failure test when the table
     * has at least one null invalid table required create field.
     *
     * @param content generated test content
     * @param table current table
     * @param entityName entity simple name
     * @param dtoName dto simple name
     * @param apiPath api base path
     * @param dtoFieldTypes actual DTO field types
     */
    private void appendCreateValidationFailureTest(StringBuilder content,
                                                   Table table,
                                                   String entityName,
                                                   String dtoName,
                                                   String apiPath,
                                                   Map<String, String> dtoFieldTypes) {
        Column invalidColumn = findFirstNullInvalidatableCreateField(table, dtoFieldTypes);
        if (invalidColumn == null) {
            return;
        }

        String dtoFieldName = resolveFlatDtoFieldName(invalidColumn, dtoFieldTypes);
        if (dtoFieldName == null) {
            return;
        }

        String setterName = "set" + NamingConverter.toPascalCase(dtoFieldName);

        content.append("    @Test\n");
        content.append("    void shouldReturnUnprocessableEntityForCreateWhenValidationFails() throws Exception {\n");
        content.append("        ").append(dtoName).append(" requestDto = createValidCreate")
                .append(entityName).append("Dto();\n");
        content.append("        requestDto.").append(setterName).append("(null);\n\n");

        content.append("        mockMvc.perform(post(\"").append(apiPath).append("\")\n");
        content.append("                .contentType(MediaType.APPLICATION_JSON)\n");
        content.append("                .content(objectMapper.writeValueAsString(requestDto)))\n");
        content.append("                .andExpect(status().isUnprocessableEntity());\n");
        content.append("    }\n\n");
    }

    /**
     * Returns the first required create field that can safely be invalidated with null
     * in generated controller tests.
     *
     * @param table current table
     * @param dtoFieldTypes actual DTO field types
     * @return first null invalid table required create field, or null when none exists
     */
    private Column findFirstNullInvalidatableCreateField(Table table, Map<String, String> dtoFieldTypes) {
        if (table == null || table.getColumns() == null || table.getColumns().isEmpty()) {
            return null;
        }

        return table.getColumns().stream()
                .filter(Objects::nonNull)
                .filter(this::shouldPopulateCreateField)
                .filter(this::canInvalidateCreateFieldWithNull)
                .filter(column -> {
                    String dtoFieldName = resolveFlatDtoFieldName(column, dtoFieldTypes);
                    if (dtoFieldName == null) {
                        return false;
                    }
                    return isSupportedScalarDtoType(dtoFieldTypes.get(dtoFieldName));
                })
                .findFirst()
                .orElse(null);
    }

    /**
     * Determines whether a creation field can safely be invalidated with null
     * in generated controller tests.
     *
     * @param column current column
     * @return true when null can be passed to the generated setter safely
     */
    private boolean canInvalidateCreateFieldWithNull(Column column) {
        if (column == null || column.getJavaType() == null || column.getJavaType().isBlank()) {
            return true;
        }

        String normalizedType = column.getJavaType().trim();

        return !"int".equals(normalizedType)
                && !"long".equals(normalizedType)
                && !"short".equals(normalizedType)
                && !"byte".equals(normalizedType)
                && !"boolean".equals(normalizedType)
                && !"double".equals(normalizedType)
                && !"float".equals(normalizedType)
                && !"char".equals(normalizedType);
    }

    /**
     * Appends single-primary-key get-by-id tests.
     *
     * @param content generated test content
     * @param entityName entity simple name
     * @param dtoName dto simple name
     * @param serviceVar service variable name
     * @param apiPath api base path
     * @param primaryKeyType primary key type
     * @param sampleId sample id value
     */
    private void appendSingleGetByIdTests(StringBuilder content,
                                          String entityName,
                                          String dtoName,
                                          String serviceVar,
                                          String apiPath,
                                          String primaryKeyType,
                                          String sampleId) {
        content.append("    @Test\n");
        content.append("    void shouldReturnOkForGetById() throws Exception {\n");
        content.append("        ").append(primaryKeyType).append(" id = ").append(sampleId).append(";\n");
        content.append("        given(").append(serviceVar).append(".get").append(entityName)
                .append("ById(id)).willReturn(new ").append(dtoName).append("());\n\n");

        content.append("        mockMvc.perform(get(\"").append(apiPath).append("/{id}\", id))\n");
        content.append("                .andExpect(status().isOk())\n");
        content.append("                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));\n\n");

        content.append("        verify(").append(serviceVar).append(").get").append(entityName).append("ById(id);\n");
        content.append("    }\n\n");

        content.append("    @Test\n");
        content.append("    void shouldReturnNotFoundForGetById() throws Exception {\n");
        content.append("        ").append(primaryKeyType).append(" id = ").append(sampleId).append(";\n");
        content.append("        given(").append(serviceVar).append(".get").append(entityName).append("ById(id))\n");
        content.append("                .willThrow(GeneratedRuntimeException.builder()\n");
        content.append("                        .code(ErrorCodes.NOT_FOUND)\n");
        content.append("                        .entity(\"").append(entityName).append("\")\n");
        content.append("                        .message(\"").append(entityName).append(" not found with id: \" + id)\n");
        content.append("                        .build());\n\n");

        content.append("        mockMvc.perform(get(\"").append(apiPath).append("/{id}\", id))\n");
        content.append("                .andExpect(status().isNotFound());\n");
        content.append("    }\n\n");
    }

    /**
     * Appends single-primary-key create tests.
     *
     * @param content generated test content
     * @param table current table
     * @param entityName entity simple name
     * @param dtoName dto simple name
     * @param serviceVar service variable name
     * @param apiPath api base path
     * @param dtoFieldTypes actual DTO field types
     */
    private void appendSingleCreateTests(StringBuilder content,
                                         Table table,
                                         String entityName,
                                         String dtoName,
                                         String serviceVar,
                                         String apiPath,
                                         Map<String, String> dtoFieldTypes) {
        content.append("    @Test\n");
        content.append("    void shouldReturnCreatedForCreate() throws Exception {\n");
        content.append("        ").append(dtoName).append(" requestDto = createValidCreate")
                .append(entityName).append("Dto();\n");
        content.append("        ").append(dtoName).append(" responseDto = new ").append(dtoName).append("();\n");
        content.append("        given(").append(serviceVar).append(".create").append(entityName)
                .append("(any(").append(dtoName).append(".class))).willReturn(responseDto);\n\n");

        content.append("        mockMvc.perform(post(\"").append(apiPath).append("\")\n");
        content.append("                .contentType(MediaType.APPLICATION_JSON)\n");
        content.append("                .content(objectMapper.writeValueAsString(requestDto)))\n");
        content.append("                .andExpect(status().isCreated())\n");
        content.append("                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));\n\n");

        content.append("        verify(").append(serviceVar).append(").create").append(entityName)
                .append("(any(").append(dtoName).append(".class));\n");
        content.append("    }\n\n");

        appendCreateValidationFailureTest(content, table, entityName, dtoName, apiPath, dtoFieldTypes);

        content.append("    @Test\n");
        content.append("    void shouldReturnBadRequestForCreateWhenServiceThrows() throws Exception {\n");
        content.append("        ").append(dtoName).append(" requestDto = createValidCreate")
                .append(entityName).append("Dto();\n");
        content.append("        given(").append(serviceVar).append(".create").append(entityName)
                .append("(any(").append(dtoName).append(".class)))\n");
        content.append("                .willThrow(GeneratedRuntimeException.builder()\n");
        content.append("                        .code(ErrorCodes.BAD_REQUEST)\n");
        content.append("                        .entity(\"").append(entityName).append("\")\n");
        content.append("                        .message(\"Invalid payload\")\n");
        content.append("                        .build());\n\n");

        content.append("        mockMvc.perform(post(\"").append(apiPath).append("\")\n");
        content.append("                .contentType(MediaType.APPLICATION_JSON)\n");
        content.append("                .content(objectMapper.writeValueAsString(requestDto)))\n");
        content.append("                .andExpect(status().isBadRequest());\n");
        content.append("    }\n\n");
    }

    /**
     * Appends single-primary-key patch tests.
     * @param content generated test content
     * @param entityName entity simple name
     * @param dtoName dto simple name
     * @param serviceVar service variable name
     * @param apiPath api base path
     * @param primaryKeyType primary key type
     * @param sampleId sample id value
     */
    private void appendSinglePatchTests(
            StringBuilder content,
            String entityName,
            String dtoName,
            String serviceVar,
            String apiPath,
            String primaryKeyType,
            String sampleId
    ) {
        content.append("    @Test\n");
        content.append("    void shouldReturnOkForPatch() throws Exception {\n");
        content.append("        ").append(primaryKeyType).append(" id = ").append(sampleId).append(";\n");
        content.append("        ").append(dtoName).append(" requestDto = new ").append(dtoName).append("();\n");
        content.append("        ").append(dtoName).append(" responseDto = new ").append(dtoName).append("();\n");
        content.append("        given(").append(serviceVar).append(".update").append(entityName)
                .append("(eq(id), any(").append(dtoName).append(".class))).willReturn(responseDto);\n\n");

        content.append("        mockMvc.perform(patch(\"").append(apiPath).append("/{id}\", id)\n");
        content.append("                .contentType(MediaType.APPLICATION_JSON)\n");
        content.append("                .content(objectMapper.writeValueAsString(requestDto)))\n");
        content.append("                .andExpect(status().isOk())\n");
        content.append("                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));\n\n");

        content.append("        verify(").append(serviceVar).append(").update").append(entityName)
                .append("(eq(id), any(").append(dtoName).append(".class));\n");
        content.append("    }\n\n");

        content.append("    @Test\n");
        content.append("    void shouldReturnNotFoundForPatch() throws Exception {\n");
        content.append("        ").append(primaryKeyType).append(" id = ").append(sampleId).append(";\n");
        content.append("        ").append(dtoName).append(" requestDto = new ").append(dtoName).append("();\n");
        content.append("        given(").append(serviceVar).append(".update").append(entityName)
                .append("(eq(id), any(").append(dtoName).append(".class)))\n");
        content.append("                .willThrow(GeneratedRuntimeException.builder()\n");
        content.append("                        .code(ErrorCodes.NOT_FOUND)\n");
        content.append("                        .entity(\"").append(entityName).append("\")\n");
        content.append("                        .message(\"").append(entityName).append(" not found with id: \" + id)\n");
        content.append("                        .build());\n\n");

        content.append("        mockMvc.perform(patch(\"").append(apiPath).append("/{id}\", id)\n");
        content.append("                .contentType(MediaType.APPLICATION_JSON)\n");
        content.append("                .content(objectMapper.writeValueAsString(requestDto)))\n");
        content.append("                .andExpect(status().isNotFound());\n");
        content.append("    }\n\n");
    }

    /**
     * Appends single-primary-key delete tests.
     *
     * @param content generated test content
     * @param entityName entity simple name
     * @param serviceVar service variable name
     * @param apiPath api base path
     * @param primaryKeyType primary key type
     * @param sampleId sample id value
     */
    private void appendSingleDeleteTests(StringBuilder content,
                                         String entityName,
                                         String serviceVar,
                                         String apiPath,
                                         String primaryKeyType,
                                         String sampleId) {
        content.append("    @Test\n");
        content.append("    void shouldReturnNoContentForDelete() throws Exception {\n");
        content.append("        ").append(primaryKeyType).append(" id = ").append(sampleId).append(";\n");
        content.append("        willDoNothing().given(").append(serviceVar).append(").delete")
                .append(entityName).append("(id);\n\n");

        content.append("        mockMvc.perform(delete(\"").append(apiPath).append("/{id}\", id))\n");
        content.append("                .andExpect(status().isNoContent());\n\n");

        content.append("        verify(").append(serviceVar).append(").delete").append(entityName).append("(id);\n");
        content.append("    }\n\n");

        content.append("    @Test\n");
        content.append("    void shouldReturnNotFoundForDelete() throws Exception {\n");
        content.append("        ").append(primaryKeyType).append(" id = ").append(sampleId).append(";\n");
        content.append("        willThrow(GeneratedRuntimeException.builder()\n");
        content.append("                .code(ErrorCodes.NOT_FOUND)\n");
        content.append("                .entity(\"").append(entityName).append("\")\n");
        content.append("                .message(\"").append(entityName).append(" not found with id: \" + id)\n");
        content.append("                .build())\n");
        content.append("                .given(").append(serviceVar).append(").delete").append(entityName).append("(id);\n\n");

        content.append("        mockMvc.perform(delete(\"").append(apiPath).append("/{id}\", id))\n");
        content.append("                .andExpect(status().isNotFound());\n");
        content.append("    }\n\n");
    }

    /**
     * Appends composite-primary-key get-by-id tests.
     *
     * @param content generated test content
     * @param entityName entity simple name
     * @param dtoName dto simple name
     * @param serviceVar service variable name
     * @param apiPath api base path
     * @param primaryKeyColumns primary key columns
     */
    private void appendCompositeGetByIdTests(StringBuilder content,
                                             String entityName,
                                             String dtoName,
                                             String serviceVar,
                                             String apiPath,
                                             List<Column> primaryKeyColumns) {
        String serviceArguments = buildCompositeServiceArguments(primaryKeyColumns);
        String compositePathTemplate = buildCompositePathTemplate(primaryKeyColumns);

        content.append("    @Test\n");
        content.append("    void shouldReturnOkForGetById() throws Exception {\n");
        appendCompositePrimaryKeyDeclarations(content, primaryKeyColumns);
        content.append("        given(").append(serviceVar).append(".get").append(entityName)
                .append("ById(").append(serviceArguments).append("))")
                .append(".willReturn(new ").append(dtoName).append("());\n\n");

        content.append("        mockMvc.perform(get(\"").append(apiPath).append(compositePathTemplate).append("\"");
        appendCompositePathArguments(content, primaryKeyColumns);
        content.append("))\n");
        content.append("                .andExpect(status().isOk())\n");
        content.append("                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));\n\n");

        content.append("        verify(").append(serviceVar).append(").get").append(entityName)
                .append("ById(").append(serviceArguments).append(");\n");
        content.append("    }\n\n");

        content.append("    @Test\n");
        content.append("    void shouldReturnNotFoundForGetById() throws Exception {\n");
        appendCompositePrimaryKeyDeclarations(content, primaryKeyColumns);
        content.append("        given(").append(serviceVar).append(".get").append(entityName)
                .append("ById(").append(serviceArguments).append("))\n");
        content.append("                .willThrow(GeneratedRuntimeException.builder()\n");
        content.append("                        .code(ErrorCodes.NOT_FOUND)\n");
        content.append("                        .entity(\"").append(entityName).append("\")\n");
        content.append("                        .message(\"").append(entityName).append(" not found\")\n");
        content.append("                        .build());\n\n");

        content.append("        mockMvc.perform(get(\"").append(apiPath).append(compositePathTemplate).append("\"");
        appendCompositePathArguments(content, primaryKeyColumns);
        content.append("))\n");
        content.append("                .andExpect(status().isNotFound());\n");
        content.append("    }\n\n");
    }

    /**
     * Appends composite-primary-key create tests.
     *
     * @param content generated test content
     * @param table current table
     * @param entityName entity simple name
     * @param dtoName dto simple name
     * @param serviceVar service variable name
     * @param apiPath api base path
     * @param dtoFieldTypes actual DTO field types
     */
    private void appendCompositeCreateTests(StringBuilder content,
                                            Table table,
                                            String entityName,
                                            String dtoName,
                                            String serviceVar,
                                            String apiPath,
                                            Map<String, String> dtoFieldTypes) {
        content.append("    @Test\n");
        content.append("    void shouldReturnCreatedForCreate() throws Exception {\n");
        content.append("        ").append(dtoName).append(" requestDto = createValidCreate")
                .append(entityName).append("Dto();\n");
        content.append("        ").append(dtoName).append(" responseDto = new ").append(dtoName).append("();\n");
        content.append("        given(").append(serviceVar).append(".create").append(entityName)
                .append("(any(").append(dtoName).append(".class))).willReturn(responseDto);\n\n");

        content.append("        mockMvc.perform(post(\"").append(apiPath).append("\")\n");
        content.append("                .contentType(MediaType.APPLICATION_JSON)\n");
        content.append("                .content(objectMapper.writeValueAsString(requestDto)))\n");
        content.append("                .andExpect(status().isCreated())\n");
        content.append("                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));\n\n");

        content.append("        verify(").append(serviceVar).append(").create").append(entityName)
                .append("(any(").append(dtoName).append(".class));\n");
        content.append("    }\n\n");

        appendCreateValidationFailureTest(content, table, entityName, dtoName, apiPath, dtoFieldTypes);

        content.append("    @Test\n");
        content.append("    void shouldReturnBadRequestForCreateWhenServiceThrows() throws Exception {\n");
        content.append("        ").append(dtoName).append(" requestDto = createValidCreate")
                .append(entityName).append("Dto();\n");
        content.append("        given(").append(serviceVar).append(".create").append(entityName)
                .append("(any(").append(dtoName).append(".class)))\n");
        content.append("                .willThrow(GeneratedRuntimeException.builder()\n");
        content.append("                        .code(ErrorCodes.BAD_REQUEST)\n");
        content.append("                        .entity(\"").append(entityName).append("\")\n");
        content.append("                        .message(\"Invalid payload\")\n");
        content.append("                        .build());\n\n");

        content.append("        mockMvc.perform(post(\"").append(apiPath).append("\")\n");
        content.append("                .contentType(MediaType.APPLICATION_JSON)\n");
        content.append("                .content(objectMapper.writeValueAsString(requestDto)))\n");
        content.append("                .andExpect(status().isBadRequest());\n");
        content.append("    }\n\n");
    }

    /**
     * Appends a valid creation DTO fixture factory method based on required table columns.
     *
     * @param content generated test content
     * @param table current table
     * @param dtoName dto simple name
     * @param entityName entity simple name
     * @param dtoFieldTypes actual DTO field types
     */
    private void appendCreateDtoFixtureMethod(StringBuilder content,
                                              Table table,
                                              String dtoName,
                                              String entityName,
                                              Map<String, String> dtoFieldTypes) {
        content.append("    /**\n");
        content.append("     * Creates a valid create request DTO for ").append(entityName).append(".\n");
        content.append("     *\n");
        content.append("     * @return populated create request dto\n");
        content.append("     */\n");
        content.append("    private ").append(dtoName).append(" createValidCreate")
                .append(entityName).append("Dto() {\n");

        StringBuilder setterLines = new StringBuilder();
        boolean hasAssignments = appendRequiredCreateDtoSetterLines(setterLines, table, dtoFieldTypes);

        if (!hasAssignments) {
            content.append("        return new ").append(dtoName).append("();\n");
            content.append("    }\n\n");
            return;
        }

        content.append("        ").append(dtoName).append(" dto = new ").append(dtoName).append("();\n");
        content.append(setterLines);
        content.append("        return dto;\n");
        content.append("    }\n\n");
    }

    /**
     * Appends setter lines for required create DTO fields derived from the table schema.
     *
     * @param content generated test content
     * @param table current table
     * @param dtoFieldTypes actual DTO field types
     * @return true when at least one setter line was appended
     */
    private boolean appendRequiredCreateDtoSetterLines(StringBuilder content,
                                                       Table table,
                                                       Map<String, String> dtoFieldTypes) {
        if (table == null || table.getColumns() == null || table.getColumns().isEmpty()) {
            return false;
        }

        boolean appended = false;

        for (Column column : table.getColumns()) {
            if (column == null) {
                continue;
            }

            String dtoFieldName = resolveCreateDtoFieldName(column, dtoFieldTypes);
            if (dtoFieldName == null) {
                continue;
            }

            String dtoFieldType = dtoFieldTypes.get(dtoFieldName);
            if (dtoFieldType == null || dtoFieldType.isBlank()) {
                continue;
            }

            String flatDtoFieldName = resolveFlatDtoFieldName(column, dtoFieldTypes);
            boolean relationField = flatDtoFieldName == null || !flatDtoFieldName.equals(dtoFieldName);

            if (!relationField && !shouldPopulateCreateField(column)) {
                continue;
            }

            if (relationField && !column.isForeignKey()) {
                continue;
            }

            if (isSupportedScalarDtoType(dtoFieldType)) {
                appendScalarCreateFixtureLine(content, column, dtoFieldName, dtoFieldType);
                appended = true;
                continue;
            }

            if (isSupportedNestedDtoType(dtoFieldType)) {
                appendNestedDtoCreateFixtureLine(content, dtoFieldName, dtoFieldType);
                appended = true;
            }
        }

        if (appended) {
            content.append("\n");
        }

        return appended;
    }

    /**
     * Resolves the DTO field name used in create fixtures.
     *
     * <p>
     * Rules:
     * <ul>
     *     <li>Direct scalar fields use the camelCase column name</li>
     *     <li>Foreign key relation DTO fields use the relation-style name without the _id suffix</li>
     * </ul>
     *
     * @param column current database column
     * @param dtoFieldTypes map of DTO field names to their types
     * @return matching DTO field name when found, otherwise null
     */
    private String resolveCreateDtoFieldName(Column column, Map<String, String> dtoFieldTypes) {
        if (column == null || dtoFieldTypes == null || dtoFieldTypes.isEmpty()) {
            return null;
        }

        String flatFieldName = resolveFlatDtoFieldName(column, dtoFieldTypes);
        if (flatFieldName != null) {
            return flatFieldName;
        }

        if (!column.isForeignKey()) {
            return null;
        }

        String columnName = GeneratorSupport.unquoteIdentifier(column.getName());
        if (columnName == null || columnName.isBlank()) {
            return null;
        }

        String relationColumnName = columnName.replaceFirst("(?i)_id$", "");
        String relationFieldName = NamingConverter.toCamelCase(relationColumnName);

        return dtoFieldTypes.containsKey(relationFieldName) ? relationFieldName : null;
    }

    /**
     * Appends a nested DTO setter line for a required relation field.
     *
     * @param content generated test content
     * @param dtoFieldName actual DTO field name
     * @param dtoFieldType actual DTO field type
     */
    private void appendNestedDtoCreateFixtureLine(StringBuilder content,
                                                  String dtoFieldName,
                                                  String dtoFieldType) {
        String simpleType = resolveSimpleDtoType(dtoFieldType);

        if (simpleType == null || simpleType.isBlank()) {
            return;
        }

        String setterName = "set" + NamingConverter.toPascalCase(dtoFieldName);

        content.append("        dto.")
                .append(setterName)
                .append("(new ")
                .append(simpleType)
                .append("());\n");
    }

    /**
     * Determines whether the DTO field type is a supported nested DTO type for fixture assignment.
     *
     * @param dtoFieldType actual DTO field type
     * @return true when the field can be assigned with an empty nested DTO instance
     */
    private boolean isSupportedNestedDtoType(String dtoFieldType) {
        String simpleType = resolveSimpleDtoType(dtoFieldType);

        if (simpleType == null || simpleType.isBlank()) {
            return false;
        }

        return simpleType.endsWith("Dto")
                && !simpleType.startsWith("List<")
                && !simpleType.startsWith("Set<")
                && !simpleType.startsWith("Map<");
    }

    /**
     * Appends a scalar setter line for a creative DTO fixture.
     *
     * @param content generated test content
     * @param column current column
     * @param dtoFieldName actual DTO field name
     * @param dtoFieldType actual DTO field type
     */
    private void appendScalarCreateFixtureLine(StringBuilder content,
                                               Column column,
                                               String dtoFieldName,
                                               String dtoFieldType) {
        if (column == null) {
            return;
        }

        String setterName = "set" + NamingConverter.toPascalCase(dtoFieldName);
        String javaLiteral = buildCreateFixtureLiteral(column, dtoFieldType);

        if ("null".equals(javaLiteral)) {
            return;
        }

        content.append("        dto.")
                .append(setterName)
                .append("(")
                .append(javaLiteral)
                .append(");\n");
    }

    /**
     * Builds a valid fixture literal for a creative DTO field.
     *
     * @param column current column
     * @param dtoFieldType actual DTO field type
     * @return Java literal source
     */
    private String buildCreateFixtureLiteral(Column column, String dtoFieldType) {
        if (column == null) {
            return "null";
        }

        String simplifiedType = resolveSimpleDtoType(dtoFieldType);
        if ("String".equals(simplifiedType)) {
            return buildStringFixtureLiteral(column);
        }

        return sampleValueForType(simplifiedType, 1);
    }

    /**
     * Builds a valid string literal for a DTO field while respecting max length constraints.
     *
     * @param column current column
     * @return Java string literal source
     */
    private String buildStringFixtureLiteral(Column column) {
        int maxLength = column.getLength();

        if (maxLength <= 0) {
            return "\"test\"";
        }

        int safeLength = Math.min(maxLength, 5);
        String safeValue = "a".repeat(safeLength);
        return "\"" + safeValue + "\"";
    }

    /**
     * Loads the actual field names and types declared in the already generated DTO source file.
     *
     * @param outputDir project root output directory
     * @param basePackage base Java package
     * @param dtoName DTO simple name
     * @return declared DTO field names and types
     */
    private Map<String, String> loadGeneratedDtoFieldTypes(String outputDir, String basePackage, String dtoName) {
        Map<String, String> fieldTypes = new LinkedHashMap<>();

        Path dtoDir = PackageResolver.resolvePath(outputDir, basePackage, "dto");
        Path dtoPath = dtoDir.resolve(dtoName + ".java");

        if (!Files.exists(dtoPath)) {
            return fieldTypes;
        }

        Pattern fieldPattern = Pattern.compile("^\\s*private\\s+(.+?)\\s+(\\w+)\\s*;\\s*$");

        try {
            for (String line : Files.readAllLines(dtoPath)) {
                Matcher matcher = fieldPattern.matcher(line);
                if (matcher.matches()) {
                    fieldTypes.put(matcher.group(2), matcher.group(1).trim());
                }
            }
        } catch (IOException exception) {
            log.warn("Could not read DTO file for field discovery: {}", dtoPath, exception);
        }

        return fieldTypes;
    }

    /**
     * Resolves the flat DTO field name that corresponds to the given database column.
     *
     * <p>
     * This method converts the column name to camelCase and attempts to match it
     * against the existing DTO field names. It assumes that DTOs are generated
     * using a flat structure (no nested relations).
     * </p>
     *
     * @param column current database column
     * @param dtoFieldTypes map of DTO field names to their types
     * @return matching DTO field name when found, otherwise null
     */
    private String resolveFlatDtoFieldName(Column column, Map<String, String> dtoFieldTypes) {
        if (column == null || dtoFieldTypes == null || dtoFieldTypes.isEmpty()) {
            return null;
        }

        String columnName = GeneratorSupport.unquoteIdentifier(column.getName());
        String dtoFieldName = NamingConverter.toCamelCase(columnName);

        if (!dtoFieldTypes.containsKey(dtoFieldName)) {
            return null;
        }

        return dtoFieldName;
    }


    /**
     * Resolves the simple DTO type name.
     *
     * @param rawType raw DTO type
     * @return simple type name
     */
    private String resolveSimpleDtoType(String rawType) {
        return JavaTypeSupport.resolveSimpleType(rawType);
    }

    /**
     * Determines whether the DTO field type is a supported scalar type for fixture assignment.
     *
     * @param dtoFieldType actual DTO field type
     * @return true when the field can be assigned with a scalar literal
     */
    private boolean isSupportedScalarDtoType(String dtoFieldType) {
        String simpleType = resolveSimpleDtoType(dtoFieldType);

        return "String".equals(simpleType)
                || JavaTypeSupport.isScalarType(simpleType)
                || isPrimitiveType(simpleType);
    }

    /**
     * Determines whether the provided type is a Java primitive type.
     *
     * @param type simple type name
     * @return true when primitive
     */
    private boolean isPrimitiveType(String type) {
        return "long".equals(type)
                || "int".equals(type)
                || "boolean".equals(type)
                || "double".equals(type)
                || "float".equals(type)
                || "short".equals(type)
                || "byte".equals(type)
                || "char".equals(type);
    }


    /**
     * Determines whether a column should be populated in a generated valid create DTO fixture.
     *
     * @param column current column
     * @return true when the column is required for create fixture generation
     */
    private boolean shouldPopulateCreateField(Column column) {
        if (column == null) {
            return false;
        }

        if (column.isPrimaryKey()) {
            return false;
        }

        if (column.isNullable()) {
            return false;
        }

        String columnName = GeneratorSupport.unquoteIdentifier(column.getName());
        return !"date_created".equalsIgnoreCase(columnName)
                && !"last_updated".equalsIgnoreCase(columnName);
    }


    /**
     * Appends composite-primary-key patch tests.
     * @param content generated test content
     * @param entityName entity simple name
     * @param dtoName dto simple name
     * @param serviceVar service variable name
     * @param apiPath api base path
     * @param primaryKeyColumns primary key columns
     */
    private void appendCompositePatchTests(
            StringBuilder content,
            String entityName,
            String dtoName,
            String serviceVar,
            String apiPath,
            List<Column> primaryKeyColumns
    ) {
        String serviceEqArguments = buildCompositeEqServiceArguments(primaryKeyColumns);
        String compositePathTemplate = buildCompositePathTemplate(primaryKeyColumns);

        content.append("    @Test\n");
        content.append("    void shouldReturnOkForPatch() throws Exception {\n");
        appendCompositePrimaryKeyDeclarations(content, primaryKeyColumns);
        content.append("        ").append(dtoName).append(" requestDto = new ").append(dtoName).append("();\n");
        content.append("        ").append(dtoName).append(" responseDto = new ").append(dtoName).append("();\n");
        content.append("        given(").append(serviceVar).append(".update").append(entityName)
                .append("(").append(serviceEqArguments).append(", any(").append(dtoName).append(".class)))")
                .append(".willReturn(responseDto);\n\n");

        content.append("        mockMvc.perform(patch(\"").append(apiPath).append(compositePathTemplate).append("\"");
        appendCompositePathArguments(content, primaryKeyColumns);
        content.append(")\n");
        content.append("                .contentType(MediaType.APPLICATION_JSON)\n");
        content.append("                .content(objectMapper.writeValueAsString(requestDto)))\n");
        content.append("                .andExpect(status().isOk())\n");
        content.append("                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));\n\n");

        content.append("        verify(").append(serviceVar).append(").update").append(entityName)
                .append("(").append(serviceEqArguments).append(", any(").append(dtoName).append(".class));\n");
        content.append("    }\n\n");

        content.append("    @Test\n");
        content.append("    void shouldReturnNotFoundForPatch() throws Exception {\n");
        appendCompositePrimaryKeyDeclarations(content, primaryKeyColumns);
        content.append("        ").append(dtoName).append(" requestDto = new ").append(dtoName).append("();\n");
        content.append("        given(").append(serviceVar).append(".update").append(entityName)
                .append("(").append(serviceEqArguments).append(", any(").append(dtoName).append(".class)))\n");
        content.append("                .willThrow(GeneratedRuntimeException.builder()\n");
        content.append("                        .code(ErrorCodes.NOT_FOUND)\n");
        content.append("                        .entity(\"").append(entityName).append("\")\n");
        content.append("                        .message(\"").append(entityName).append(" not found\")\n");
        content.append("                        .build());\n\n");

        content.append("        mockMvc.perform(patch(\"").append(apiPath).append(compositePathTemplate).append("\"");
        appendCompositePathArguments(content, primaryKeyColumns);
        content.append(")\n");
        content.append("                .contentType(MediaType.APPLICATION_JSON)\n");
        content.append("                .content(objectMapper.writeValueAsString(requestDto)))\n");
        content.append("                .andExpect(status().isNotFound());\n");
        content.append("    }\n\n");
    }

    /**
     * Builds ordered composite primary key arguments wrapped with eq(...) for Mockito calls.
     *
     * @param primaryKeyColumns primary key columns
     * @return comma-separated Mockito eq(...) argument list
     */
    private String buildCompositeEqServiceArguments(List<Column> primaryKeyColumns) {
        StringBuilder stringBuilder = new StringBuilder();

        for (int index = 0; index < primaryKeyColumns.size(); index++) {
            if (index > 0) {
                stringBuilder.append(", ");
            }

            stringBuilder.append("eq(")
                    .append(resolvePkParamName(primaryKeyColumns.get(index)))
                    .append(")");
        }

        return stringBuilder.toString();
    }

    /**
     * Appends composite-primary-key delete tests.
     *
     * @param content generated test content
     * @param entityName entity simple name
     * @param serviceVar service variable name
     * @param apiPath api base path
     * @param primaryKeyColumns primary key columns
     */
    private void appendCompositeDeleteTests(StringBuilder content,
                                            String entityName,
                                            String serviceVar,
                                            String apiPath,
                                            List<Column> primaryKeyColumns) {
        String serviceArguments = buildCompositeServiceArguments(primaryKeyColumns);
        String compositePathTemplate = buildCompositePathTemplate(primaryKeyColumns);

        content.append("    @Test\n");
        content.append("    void shouldReturnNoContentForDelete() throws Exception {\n");
        appendCompositePrimaryKeyDeclarations(content, primaryKeyColumns);
        content.append("        willDoNothing().given(").append(serviceVar).append(").delete")
                .append(entityName).append("(").append(serviceArguments).append(");\n\n");

        content.append("        mockMvc.perform(delete(\"").append(apiPath).append(compositePathTemplate).append("\"");
        appendCompositePathArguments(content, primaryKeyColumns);
        content.append("))\n");
        content.append("                .andExpect(status().isNoContent());\n\n");

        content.append("        verify(").append(serviceVar).append(").delete").append(entityName)
                .append("(").append(serviceArguments).append(");\n");
        content.append("    }\n\n");

        content.append("    @Test\n");
        content.append("    void shouldReturnNotFoundForDelete() throws Exception {\n");
        appendCompositePrimaryKeyDeclarations(content, primaryKeyColumns);
        content.append("        willThrow(GeneratedRuntimeException.builder()\n");
        content.append("                .code(ErrorCodes.NOT_FOUND)\n");
        content.append("                .entity(\"").append(entityName).append("\")\n");
        content.append("                .message(\"").append(entityName).append(" not found\")\n");
        content.append("                .build())\n");
        content.append("                .given(").append(serviceVar).append(").delete")
                .append(entityName).append("(").append(serviceArguments).append(");\n\n");

        content.append("        mockMvc.perform(delete(\"").append(apiPath).append(compositePathTemplate).append("\"");
        appendCompositePathArguments(content, primaryKeyColumns);
        content.append("))\n");
        content.append("                .andExpect(status().isNotFound());\n");
        content.append("    }\n\n");
    }

    /**
     * Builds the URI template suffix for composite primary key endpoints.
     *
     * @param primaryKeyColumns primary key columns
     * @return path template suffix like "/{idPart1}/{idPart2}"
     */
    private String buildCompositePathTemplate(List<Column> primaryKeyColumns) {
        StringBuilder stringBuilder = new StringBuilder();

        for (Column primaryKeyColumn : primaryKeyColumns) {
            String parameterName = resolvePkParamName(primaryKeyColumn);
            stringBuilder.append("/{").append(parameterName).append("}");
        }

        return stringBuilder.toString();
    }

    /**
     * Appends sample declarations for composite primary key fields.
     *
     * @param content generated test content
     * @param primaryKeyColumns primary key columns
     */
    private void appendCompositePrimaryKeyDeclarations(StringBuilder content,
                                                       List<Column> primaryKeyColumns) {
        for (int index = 0; index < primaryKeyColumns.size(); index++) {
            Column primaryKeyColumn = primaryKeyColumns.get(index);
            String javaType = detectJavaTypeForPkColumn(primaryKeyColumn);
            String parameterName = resolvePkParamName(primaryKeyColumn);
            String sampleValue = sampleValueForType(javaType, index + 1);

            content.append("        ").append(javaType).append(" ")
                    .append(parameterName).append(" = ").append(sampleValue).append(";\n");
        }

        content.append("\n");
    }

    /**
     * Resolves a safe camelCase parameter name for a primary key column.
     *
     * @param column primary key column
     * @return normalized parameter name
     */
    private String resolvePkParamName(Column column) {
        if (column == null || column.getName() == null || column.getName().isBlank()) {
            return "id";
        }

        String columnName = GeneratorSupport.unquoteIdentifier(column.getName());
        if (columnName == null || columnName.isBlank()) {
            return "id";
        }

        return NamingConverter.toCamelCase(columnName);
    }

    /**
     * Resolves the Java type for a primary key column.
     *
     * @param column primary key column
     * @return resolved Java type
     */
    private String detectJavaTypeForPkColumn(Column column) {
        return detectJavaTypeForPrimaryKeyColumn(column);
    }

    /**
     * Appends ordered composite primary key path arguments for MockMvc requests.
     *
     * @param content generated test content
     * @param primaryKeyColumns primary key columns
     */
    private void appendCompositePathArguments(StringBuilder content,
                                              List<Column> primaryKeyColumns) {
        for (Column primaryKeyColumn : primaryKeyColumns) {
            content.append(", ").append(resolvePkParamName(primaryKeyColumn));
        }
    }

    /**
     * Builds ordered composite primary key arguments for service calls.
     *
     * @param primaryKeyColumns primary key columns
     * @return comma-separated service argument list
     */
    private String buildCompositeServiceArguments(List<Column> primaryKeyColumns) {
        StringBuilder stringBuilder = new StringBuilder();

        for (int index = 0; index < primaryKeyColumns.size(); index++) {
            if (index > 0) {
                stringBuilder.append(", ");
            }

            stringBuilder.append(resolvePkParamName(primaryKeyColumns.get(index)));
        }

        return stringBuilder.toString();
    }

    /**
     * Produces a sample Java literal for the provided simple type.
     *
     * @param javaType simple Java type name
     * @param variant sample variant index
     * @return Java literal source
     */
    private String sampleValueForType(String javaType, int variant) {
        String normalizedType = GeneratorSupport.trimToEmpty(javaType);

        return switch (normalizedType) {
            case "UUID" -> switch (variant) {
                case 1 -> "UUID.fromString(\"123e4567-e89b-12d3-a456-426614174000\")";
                case 2 -> "UUID.fromString(\"223e4567-e89b-12d3-a456-426614174000\")";
                default -> "UUID.fromString(\"323e4567-e89b-12d3-a456-426614174000\")";
            };
            case "BigDecimal" -> "new BigDecimal(\"" + variant + "\")";
            case "BigInteger" -> "new BigInteger(\"" + variant + "\")";
            case "Short", "short" -> "(short) " + variant;
            case "Byte", "byte" -> "(byte) " + variant;
            case "Boolean", "boolean" -> variant % 2 == 0 ? "false" : "true";
            case "String" -> "\"test-id-" + variant + "\"";
            case "Long", "long" -> variant + "L";
            case "Integer", "int" -> String.valueOf(variant);
            case "Double", "double" -> variant + ".0d";
            case "Float", "float" -> variant + ".0f";
            case "Character", "char" -> variant % 2 == 0 ? "'B'" : "'A'";
            case "LocalDate" -> "LocalDate.of(2025, " + variant + ", " + (variant + 10) + ")";
            case "LocalDateTime" -> "LocalDateTime.of(2025, " + variant + ", " + (variant + 10) + ", 10, 0)";
            case "LocalTime" -> "LocalTime.of(" + (variant + 9) + ", " + (variant + 5) + ")";
            case "Instant" -> "Instant.parse(\"2025-01-0" + variant + "T10:15:30Z\")";
            case "OffsetDateTime" -> "OffsetDateTime.parse(\"2025-01-0" + variant + "T10:15:30+02:00\")";
            case "ZonedDateTime" -> "ZonedDateTime.parse(\"2025-01-0" + variant + "T10:15:30+02:00[Europe/Athens]\")";
            default -> "null";
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
        if (table == null || table.getColumns() == null || table.getColumns().isEmpty()) {
            return List.of();
        }

        return table.getColumns().stream()
                .filter(Objects::nonNull)
                .filter(Column::isPrimaryKey)
                .toList();
    }

    /**
     * Resolves the Java type for a primary key column.
     *
     * @param column primary key column
     * @return resolved Java type
     */
    private String detectJavaTypeForPrimaryKeyColumn(Column column) {
        if (column == null || column.getJavaType() == null || column.getJavaType().isBlank()) {
            return "Long";
        }

        return JavaTypeSupport.resolveSimpleType(column.getJavaType());
    }

    /**
     * Resolves the Java type for a single primary key.
     *
     * @param table current table
     * @return resolved Java type
     */
    private String detectSinglePrimaryKeyType(Table table) {
        List<Column> primaryKeyColumns = getPrimaryKeyColumns(table);

        if (primaryKeyColumns.isEmpty()) {
            throw new IllegalStateException("No primary key found for table: " + table.getName());
        }

        if (primaryKeyColumns.size() > 1) {
            throw new IllegalStateException("Composite primary key is not supported here");
        }

        return detectJavaTypeForPrimaryKeyColumn(primaryKeyColumns.get(0));
    }




    /**
     * Determines whether a column contributes the given Java type
     * to generated controller test source.
     *
     * @param column current column
     * @param dtoFieldTypes actual DTO field types
     * @param expectedType expected simple Java type
     * @return true when the generated test code uses the given type
     */
    private boolean usesTypeInGeneratedTest(Column column,
                                            Map<String, String> dtoFieldTypes,
                                            String expectedType) {
        if (column == null) {
            return false;
        }

        if (column.isPrimaryKey()) {
            return expectedType.equals(detectJavaTypeForPrimaryKeyColumn(column));
        }

        if (!shouldPopulateCreateField(column)) {
            return false;
        }

        String dtoFieldName = resolveFlatDtoFieldName(column, dtoFieldTypes);
        if (dtoFieldName == null) {
            return false;
        }

        String dtoFieldType = dtoFieldTypes.get(dtoFieldName);
        if (!isSupportedScalarDtoType(dtoFieldType)) {
            return false;
        }

        return expectedType.equals(resolveSimpleDtoType(dtoFieldType));
    }
}