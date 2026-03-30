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

        for (Table table : tables) {
            String entityName = NamingConverter.toPascalCase(
                    GeneratorSupport.normalizeTableName(table.getName())
            );
            String dtoName = entityName + "Dto";
            String testName = entityName + "ControllerTest";
            String controllerName = entityName + "Controller";
            String serviceName = entityName + "Service";
            String serviceVar = NamingConverter.decapitalizeFirstLetter(entityName) + "Service";

            String dtoPackage = PackageResolver.resolvePackageName(basePackage, "dto");
            String servicePackage = PackageResolver.resolvePackageName(basePackage, "service");

            boolean compositePrimaryKey = hasCompositePrimaryKey(table);
            List<Column> primaryKeyColumns = getPrimaryKeyColumns(table);
            String apiPath = "/api/" + NamingConverter.toKebabCase(entityName) + "s";

            boolean shouldImportUuid = needsUuidImport(table);
            boolean shouldImportBigDecimal = needsBigDecimalImport(table);
            boolean shouldImportBigInteger = needsBigIntegerImport(table);

            StringBuilder content = new StringBuilder();

            appendPackageAndImports(
                    content,
                    testPackage,
                    dtoPackage,
                    dtoName,
                    servicePackage,
                    serviceName,
                    shouldImportUuid,
                    shouldImportBigDecimal,
                    shouldImportBigInteger
            );

            content.append("@WebMvcTest(").append(controllerName).append(".class)\n");
            content.append("class ").append(testName).append(" {\n\n");

            appendFields(content, serviceName, serviceVar);
            appendGetAllTest(content, entityName, dtoName, serviceVar, apiPath);

            if (compositePrimaryKey) {
                appendCompositeGetByIdTests(content, entityName, dtoName, serviceVar, apiPath, primaryKeyColumns);
                appendCompositeCreateTests(content, entityName, dtoName, serviceVar, apiPath);
                appendCompositePatchTests(content, entityName, dtoName, serviceVar, apiPath, primaryKeyColumns);
                appendCompositeDeleteTests(content, entityName, serviceVar, apiPath, primaryKeyColumns);
            } else {
                appendSinglePrimaryKeyTests(content, table, entityName, dtoName, serviceVar, apiPath);
            }

            content.append("}\n");

            GeneratorSupport.writeFile(controllerTestDir.resolve(testName + ".java"), content.toString(), overwrite);
        }

        log.info("Controller test classes generated under: {}", controllerTestDir.toAbsolutePath());
    }

    /**
     * Appends package and import statements.
     *
     * @param content generated file content
     * @param testPackage test package
     * @param dtoPackage dto package
     * @param dtoName dto simple name
     * @param servicePackage service package
     * @param serviceName service simple name
     * @param shouldImportUuid whether UUID import is needed
     * @param shouldImportBigDecimal whether BigDecimal import is needed
     * @param shouldImportBigInteger whether BigInteger import is needed
     */
    private void appendPackageAndImports(
            StringBuilder content,
            String testPackage,
            String dtoPackage,
            String dtoName,
            String servicePackage,
            String serviceName,
            boolean shouldImportUuid,
            boolean shouldImportBigDecimal,
            boolean shouldImportBigInteger
    ) {
        content.append("package ").append(testPackage).append(";\n\n");

        content.append("import ").append(dtoPackage).append(".").append(dtoName).append(";\n");
        content.append("import ").append(servicePackage).append(".").append(serviceName).append(";\n\n");

        content.append("import com.fasterxml.jackson.databind.ObjectMapper;\n");
        content.append("import org.junit.jupiter.api.Test;\n");
        content.append("import org.springframework.beans.factory.annotation.Autowired;\n");
        content.append("import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;\n");
        content.append("import org.springframework.http.HttpStatus;\n");
        content.append("import org.springframework.http.MediaType;\n");
        content.append("import org.springframework.test.context.bean.override.mockito.MockitoBean;\n");
        content.append("import org.springframework.test.web.servlet.MockMvc;\n");
        content.append("import org.springframework.web.server.ResponseStatusException;\n\n");

        content.append("import java.util.List;\n");
        if (shouldImportUuid) {
            content.append("import java.util.UUID;\n");
        }
        if (shouldImportBigDecimal) {
            content.append("import java.math.BigDecimal;\n");
        }
        if (shouldImportBigInteger) {
            content.append("import java.math.BigInteger;\n");
        }
        content.append("\n");

        content.append("import static org.mockito.ArgumentMatchers.any;\n");
        content.append("import static org.mockito.ArgumentMatchers.eq;\n");
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
    private void appendGetAllTest(
            StringBuilder content,
            String entityName,
            String dtoName,
            String serviceVar,
            String apiPath
    ) {
        content.append("    @Test\n");
        content.append("    void shouldReturnOkForGetAll() throws Exception {\n");
        content.append("        given(").append(serviceVar).append(".getAll").append(entityName)
                .append("()).willReturn(List.of(new ").append(dtoName).append("()));\n\n");
        content.append("        mockMvc.perform(get(\"").append(apiPath).append("\"))\n");
        content.append("                .andExpect(status().isOk())\n");
        content.append("                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));\n\n");
        content.append("        verify(").append(serviceVar).append(").getAll").append(entityName).append("();\n");
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
     */
    private void appendSinglePrimaryKeyTests(
            StringBuilder content,
            Table table,
            String entityName,
            String dtoName,
            String serviceVar,
            String apiPath
    ) {
        String primaryKeyType = detectSinglePrimaryKeyType(table);
        String sampleId = sampleValueForType(primaryKeyType);

        appendSingleGetByIdTests(content, entityName, dtoName, serviceVar, apiPath, primaryKeyType, sampleId);
        appendSingleCreateTests(content, entityName, dtoName, serviceVar, apiPath);
        appendSinglePatchTests(content, entityName, dtoName, serviceVar, apiPath, primaryKeyType, sampleId);
        appendSingleDeleteTests(content, entityName, serviceVar, apiPath, primaryKeyType, sampleId);
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
    private void appendSingleGetByIdTests(
            StringBuilder content,
            String entityName,
            String dtoName,
            String serviceVar,
            String apiPath,
            String primaryKeyType,
            String sampleId
    ) {
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
        content.append("                .willThrow(new ResponseStatusException(HttpStatus.NOT_FOUND, \"")
                .append(entityName).append(" not found\"));\n\n");
        content.append("        mockMvc.perform(get(\"").append(apiPath).append("/{id}\", id))\n");
        content.append("                .andExpect(status().isNotFound());\n");
        content.append("    }\n\n");
    }

    /**
     * Appends single-primary-key create tests.
     *
     * @param content generated test content
     * @param entityName entity simple name
     * @param dtoName dto simple name
     * @param serviceVar service variable name
     * @param apiPath api base path
     */
    private void appendSingleCreateTests(
            StringBuilder content,
            String entityName,
            String dtoName,
            String serviceVar,
            String apiPath
    ) {
        content.append("    @Test\n");
        content.append("    void shouldReturnCreatedForCreate() throws Exception {\n");
        content.append("        ").append(dtoName).append(" requestDto = new ").append(dtoName).append("();\n");
        content.append("        ").append(dtoName).append(" responseDto = new ").append(dtoName).append("();\n");
        content.append("        given(").append(serviceVar).append(".create").append(entityName)
                .append("(any(").append(dtoName).append(".class))).willReturn(responseDto);\n\n");
        content.append("        mockMvc.perform(post(\"").append(apiPath).append("\")\n");
        content.append("                        .contentType(MediaType.APPLICATION_JSON)\n");
        content.append("                        .content(objectMapper.writeValueAsString(requestDto)))\n");
        content.append("                .andExpect(status().isCreated())\n");
        content.append("                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));\n\n");
        content.append("        verify(").append(serviceVar).append(").create").append(entityName)
                .append("(any(").append(dtoName).append(".class));\n");
        content.append("    }\n\n");

        content.append("    @Test\n");
        content.append("    void shouldReturnBadRequestForCreate() throws Exception {\n");
        content.append("        ").append(dtoName).append(" requestDto = new ").append(dtoName).append("();\n");
        content.append("        given(").append(serviceVar).append(".create").append(entityName)
                .append("(any(").append(dtoName).append(".class)))\n");
        content.append("                .willThrow(new ResponseStatusException(HttpStatus.BAD_REQUEST, \"Invalid payload\"));\n\n");
        content.append("        mockMvc.perform(post(\"").append(apiPath).append("\")\n");
        content.append("                        .contentType(MediaType.APPLICATION_JSON)\n");
        content.append("                        .content(objectMapper.writeValueAsString(requestDto)))\n");
        content.append("                .andExpect(status().isBadRequest());\n");
        content.append("    }\n\n");
    }

    /**
     * Appends single-primary-key patch tests.
     *
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
        content.append("        given(").append(serviceVar).append(".patch").append(entityName)
                .append("(eq(id), any(").append(dtoName).append(".class))).willReturn(responseDto);\n\n");
        content.append("        mockMvc.perform(patch(\"").append(apiPath).append("/{id}\", id)\n");
        content.append("                        .contentType(MediaType.APPLICATION_JSON)\n");
        content.append("                        .content(objectMapper.writeValueAsString(requestDto)))\n");
        content.append("                .andExpect(status().isOk())\n");
        content.append("                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));\n\n");
        content.append("        verify(").append(serviceVar).append(").patch").append(entityName)
                .append("(eq(id), any(").append(dtoName).append(".class));\n");
        content.append("    }\n\n");

        content.append("    @Test\n");
        content.append("    void shouldReturnNotFoundForPatch() throws Exception {\n");
        content.append("        ").append(primaryKeyType).append(" id = ").append(sampleId).append(";\n");
        content.append("        ").append(dtoName).append(" requestDto = new ").append(dtoName).append("();\n");
        content.append("        given(").append(serviceVar).append(".patch").append(entityName)
                .append("(eq(id), any(").append(dtoName).append(".class)))\n");
        content.append("                .willThrow(new ResponseStatusException(HttpStatus.NOT_FOUND, \"")
                .append(entityName).append(" not found\"));\n\n");
        content.append("        mockMvc.perform(patch(\"").append(apiPath).append("/{id}\", id)\n");
        content.append("                        .contentType(MediaType.APPLICATION_JSON)\n");
        content.append("                        .content(objectMapper.writeValueAsString(requestDto)))\n");
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
    private void appendSingleDeleteTests(
            StringBuilder content,
            String entityName,
            String serviceVar,
            String apiPath,
            String primaryKeyType,
            String sampleId
    ) {
        content.append("    @Test\n");
        content.append("    void shouldReturnNoContentForDelete() throws Exception {\n");
        content.append("        ").append(primaryKeyType).append(" id = ").append(sampleId).append(";\n");
        content.append("        willDoNothing().given(").append(serviceVar).append(").delete").append(entityName).append("(id);\n\n");
        content.append("        mockMvc.perform(delete(\"").append(apiPath).append("/{id}\", id))\n");
        content.append("                .andExpect(status().isNoContent());\n\n");
        content.append("        verify(").append(serviceVar).append(").delete").append(entityName).append("(id);\n");
        content.append("    }\n\n");

        content.append("    @Test\n");
        content.append("    void shouldReturnNotFoundForDelete() throws Exception {\n");
        content.append("        ").append(primaryKeyType).append(" id = ").append(sampleId).append(";\n");
        content.append("        willThrow(new ResponseStatusException(HttpStatus.NOT_FOUND, \"")
                .append(entityName).append(" not found\"))\n");
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
    private void appendCompositeGetByIdTests(
            StringBuilder content,
            String entityName,
            String dtoName,
            String serviceVar,
            String apiPath,
            List<Column> primaryKeyColumns
    ) {
        content.append("    @Test\n");
        content.append("    void shouldReturnOkForGetById() throws Exception {\n");

        appendCompositePrimaryKeyDeclarations(content, primaryKeyColumns);

        content.append("        given(").append(serviceVar).append(".get").append(entityName)
                .append("ById(any())).willReturn(new ").append(dtoName).append("());\n\n");

        content.append("        mockMvc.perform(get(\"").append(apiPath).append("/by-id\")");
        appendCompositeParams(content, primaryKeyColumns);
        content.append(")\n");
        content.append("                .andExpect(status().isOk())\n");
        content.append("                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));\n");
        content.append("    }\n\n");

        content.append("    @Test\n");
        content.append("    void shouldReturnNotFoundForGetById() throws Exception {\n");

        appendCompositePrimaryKeyDeclarations(content, primaryKeyColumns);

        content.append("        given(").append(serviceVar).append(".get").append(entityName).append("ById(any()))\n");
        content.append("                .willThrow(new ResponseStatusException(HttpStatus.NOT_FOUND, \"")
                .append(entityName).append(" not found\"));\n\n");

        content.append("        mockMvc.perform(get(\"").append(apiPath).append("/by-id\")");
        appendCompositeParams(content, primaryKeyColumns);
        content.append(")\n");
        content.append("                .andExpect(status().isNotFound());\n");
        content.append("    }\n\n");
    }

    /**
     * Appends composite-primary-key create tests.
     *
     * @param content generated test content
     * @param entityName entity simple name
     * @param dtoName dto simple name
     * @param serviceVar service variable name
     * @param apiPath api base path
     */
    private void appendCompositeCreateTests(
            StringBuilder content,
            String entityName,
            String dtoName,
            String serviceVar,
            String apiPath
    ) {
        content.append("    @Test\n");
        content.append("    void shouldReturnCreatedForCreate() throws Exception {\n");
        content.append("        ").append(dtoName).append(" requestDto = new ").append(dtoName).append("();\n");
        content.append("        ").append(dtoName).append(" responseDto = new ").append(dtoName).append("();\n");
        content.append("        given(").append(serviceVar).append(".create").append(entityName)
                .append("(any(").append(dtoName).append(".class))).willReturn(responseDto);\n\n");
        content.append("        mockMvc.perform(post(\"").append(apiPath).append("\")\n");
        content.append("                        .contentType(MediaType.APPLICATION_JSON)\n");
        content.append("                        .content(objectMapper.writeValueAsString(requestDto)))\n");
        content.append("                .andExpect(status().isCreated())\n");
        content.append("                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));\n\n");
        content.append("        verify(").append(serviceVar).append(").create").append(entityName)
                .append("(any(").append(dtoName).append(".class));\n");
        content.append("    }\n\n");

        content.append("    @Test\n");
        content.append("    void shouldReturnBadRequestForCreate() throws Exception {\n");
        content.append("        ").append(dtoName).append(" requestDto = new ").append(dtoName).append("();\n");
        content.append("        given(").append(serviceVar).append(".create").append(entityName)
                .append("(any(").append(dtoName).append(".class)))\n");
        content.append("                .willThrow(new ResponseStatusException(HttpStatus.BAD_REQUEST, \"Invalid payload\"));\n\n");
        content.append("        mockMvc.perform(post(\"").append(apiPath).append("\")\n");
        content.append("                        .contentType(MediaType.APPLICATION_JSON)\n");
        content.append("                        .content(objectMapper.writeValueAsString(requestDto)))\n");
        content.append("                .andExpect(status().isBadRequest());\n");
        content.append("    }\n\n");
    }

    /**
     * Appends composite-primary-key patch tests.
     *
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
        content.append("    @Test\n");
        content.append("    void shouldReturnOkForPatch() throws Exception {\n");

        appendCompositePrimaryKeyDeclarations(content, primaryKeyColumns);

        content.append("        ").append(dtoName).append(" requestDto = new ").append(dtoName).append("();\n");
        content.append("        ").append(dtoName).append(" responseDto = new ").append(dtoName).append("();\n");
        content.append("        given(").append(serviceVar).append(".patch").append(entityName)
                .append("(any(), any(").append(dtoName).append(".class))).willReturn(responseDto);\n\n");

        content.append("        mockMvc.perform(patch(\"").append(apiPath).append("/by-id\")");
        appendCompositeParams(content, primaryKeyColumns);
        content.append("\n                        .contentType(MediaType.APPLICATION_JSON)\n");
        content.append("                        .content(objectMapper.writeValueAsString(requestDto)))\n");
        content.append("                .andExpect(status().isOk())\n");
        content.append("                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));\n");
        content.append("    }\n\n");

        content.append("    @Test\n");
        content.append("    void shouldReturnNotFoundForPatch() throws Exception {\n");

        appendCompositePrimaryKeyDeclarations(content, primaryKeyColumns);

        content.append("        ").append(dtoName).append(" requestDto = new ").append(dtoName).append("();\n");
        content.append("        given(").append(serviceVar).append(".patch").append(entityName)
                .append("(any(), any(").append(dtoName).append(".class)))\n");
        content.append("                .willThrow(new ResponseStatusException(HttpStatus.NOT_FOUND, \"")
                .append(entityName).append(" not found\"));\n\n");

        content.append("        mockMvc.perform(patch(\"").append(apiPath).append("/by-id\")");
        appendCompositeParams(content, primaryKeyColumns);
        content.append("\n                        .contentType(MediaType.APPLICATION_JSON)\n");
        content.append("                        .content(objectMapper.writeValueAsString(requestDto)))\n");
        content.append("                .andExpect(status().isNotFound());\n");
        content.append("    }\n\n");
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
    private void appendCompositeDeleteTests(
            StringBuilder content,
            String entityName,
            String serviceVar,
            String apiPath,
            List<Column> primaryKeyColumns
    ) {
        content.append("    @Test\n");
        content.append("    void shouldReturnNoContentForDelete() throws Exception {\n");

        appendCompositePrimaryKeyDeclarations(content, primaryKeyColumns);

        content.append("        willDoNothing().given(").append(serviceVar).append(").delete").append(entityName)
                .append("(any());\n\n");

        content.append("        mockMvc.perform(delete(\"").append(apiPath).append("/by-id\")");
        appendCompositeParams(content, primaryKeyColumns);
        content.append(")\n");
        content.append("                .andExpect(status().isNoContent());\n");
        content.append("    }\n\n");

        content.append("    @Test\n");
        content.append("    void shouldReturnNotFoundForDelete() throws Exception {\n");

        appendCompositePrimaryKeyDeclarations(content, primaryKeyColumns);

        content.append("        willThrow(new ResponseStatusException(HttpStatus.NOT_FOUND, \"")
                .append(entityName).append(" not found\"))\n");
        content.append("                .given(").append(serviceVar).append(").delete").append(entityName).append("(any());\n\n");

        content.append("        mockMvc.perform(delete(\"").append(apiPath).append("/by-id\")");
        appendCompositeParams(content, primaryKeyColumns);
        content.append(")\n");
        content.append("                .andExpect(status().isNotFound());\n");
        content.append("    }\n\n");
    }

    /**
     * Appends sample declarations for composite primary key fields.
     *
     * @param content generated test content
     * @param primaryKeyColumns primary key columns
     */
    private void appendCompositePrimaryKeyDeclarations(StringBuilder content, List<Column> primaryKeyColumns) {
        for (Column primaryKeyColumn : primaryKeyColumns) {
            String columnName = GeneratorSupport.unquoteIdentifier(primaryKeyColumn.getName());
            String paramName = toCamelCase(columnName);
            String paramType = detectJavaTypeForPrimaryKeyColumn(primaryKeyColumn);
            content.append("        ").append(paramType).append(" ").append(paramName)
                    .append(" = ").append(sampleValueForType(paramType)).append(";\n");
        }
    }

    /**
     * Appends request parameters for composite primary key requests.
     *
     * @param content generated test content
     * @param primaryKeyColumns primary key columns
     */
    private void appendCompositeParams(StringBuilder content, List<Column> primaryKeyColumns) {
        for (Column primaryKeyColumn : primaryKeyColumns) {
            String columnName = GeneratorSupport.unquoteIdentifier(primaryKeyColumn.getName());
            String paramName = toCamelCase(columnName);
            content.append("\n                        .param(\"").append(columnName)
                    .append("\", String.valueOf(").append(paramName).append("))");
        }
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
    private String detectJavaTypeForPrimaryKeyColumn(Column column) {
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
        List<Column> primaryKeyColumns = getPrimaryKeyColumns(table);

        if (primaryKeyColumns.isEmpty()) {
            throw new IllegalStateException("No Primary Key found for table: " + table.getName());
        }

        if (primaryKeyColumns.size() > 1) {
            throw new IllegalStateException("Composite PK not supported here");
        }

        return detectJavaTypeForPrimaryKeyColumn(primaryKeyColumns.get(0));
    }

    /**
     * Converts a snake_case identifier to camelCase.
     *
     * @param value source value
     * @return camelCase value
     */
    private String toCamelCase(String value) {
        if (value == null || value.isBlank()) {
            return value;
        }

        String[] parts = value.toLowerCase().split("_");
        StringBuilder result = new StringBuilder(parts[0]);

        for (int i = 1; i < parts.length; i++) {
            result.append(Character.toUpperCase(parts[i].charAt(0)))
                    .append(parts[i].substring(1));
        }

        return result.toString();
    }

    /**
     * Determines whether a UUID import is needed.
     *
     * @param table current table
     * @return true when UUID import is needed
     */
    private boolean needsUuidImport(Table table) {
        return table.getColumns().stream()
                .filter(Objects::nonNull)
                .filter(Column::isPrimaryKey)
                .map(Column::getJavaType)
                .filter(Objects::nonNull)
                .map(String::trim)
                .anyMatch(type -> type.equalsIgnoreCase("UUID")
                        || type.equals("java.util.UUID")
                        || type.endsWith(".UUID"));
    }

    /**
     * Determines whether a BigDecimal import is needed.
     *
     * @param table current table
     * @return true when BigDecimal import is needed
     */
    private boolean needsBigDecimalImport(Table table) {
        return table.getColumns().stream()
                .filter(Objects::nonNull)
                .filter(Column::isPrimaryKey)
                .map(Column::getJavaType)
                .filter(Objects::nonNull)
                .map(String::trim)
                .anyMatch(type -> type.equals("BigDecimal")
                        || type.equals("java.math.BigDecimal"));
    }

    /**
     * Determines whether a BigInteger import is needed.
     *
     * @param table current table
     * @return true when BigInteger import is needed
     */
    private boolean needsBigIntegerImport(Table table) {
        return table.getColumns().stream()
                .filter(Objects::nonNull)
                .filter(Column::isPrimaryKey)
                .map(Column::getJavaType)
                .filter(Objects::nonNull)
                .map(String::trim)
                .anyMatch(type -> type.equals("BigInteger")
                        || type.equals("java.math.BigInteger"));
    }
}