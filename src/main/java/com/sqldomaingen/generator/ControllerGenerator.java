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
public class ControllerGenerator {

    /**
     * Generates REST controllers for all tables.
     *
     * Output directory:
     * {outputDir}/src/main/java/{basePackagePath}/controller
     *
     * Package:
     * {basePackage}.controller
     */
    public void generateControllers(List<Table> tables, String outputDir, String basePackage, boolean overwrite) {
        Objects.requireNonNull(tables, "tables must not be null");
        Objects.requireNonNull(outputDir, "outputDir must not be null");
        Objects.requireNonNull(basePackage, "basePackage must not be null");

        Path controllerDir = ensureDirectory(PackageResolver.resolvePath(outputDir, basePackage, "controller"));
        String controllerPackage = PackageResolver.resolvePackageName(basePackage, "controller");

        for (Table table : tables) {
            String entityName = NamingConverter.toPascalCase(normalizeTableName(table.getName()));
            String code = generateControllerCode(table, controllerPackage, basePackage);

            Path filePath = controllerDir.resolve(entityName + "Controller.java");
            writeFile(filePath, code, overwrite);
        }

        log.info("✅ Controllers generated under: {}", controllerDir.toAbsolutePath());
    }

    /**
     * Generates controller code for a single table.
     * Uses Lombok constructor injection and Swagger/OpenAPI annotations.
     */
    public String generateControllerCode(Table table, String controllerPackage, String basePackage) {
        Objects.requireNonNull(table, "table must not be null");
        Objects.requireNonNull(controllerPackage, "controllerPackage must not be null");
        Objects.requireNonNull(basePackage, "basePackage must not be null");

        String entityName = NamingConverter.toPascalCase(normalizeTableName(table.getName()));
        String dtoName = entityName + "Dto";
        String serviceName = entityName + "Service";
        String serviceVar = NamingConverter.decapitalizeFirstLetter(entityName) + "Service";

        String pkType = RepositoryGenerator.detectPrimaryKeyType(table);
        boolean needsUuidImport = needsUuidImport(table);

        String apiPath = "/api/" + NamingConverter.toKebabCase(entityName) + "s";

        String dtoPackage = PackageResolver.resolvePackageName(basePackage, "dto");
        String servicePackage = PackageResolver.resolvePackageName(basePackage, "service");

        StringBuilder sb = new StringBuilder();

        sb.append("package ").append(controllerPackage).append(";\n\n");

        sb.append("import ").append(dtoPackage).append(".").append(dtoName).append(";\n");
        sb.append("import ").append(servicePackage).append(".").append(serviceName).append(";\n\n");

        sb.append("import io.swagger.v3.oas.annotations.Operation;\n");
        sb.append("import io.swagger.v3.oas.annotations.Parameter;\n");
        sb.append("import io.swagger.v3.oas.annotations.responses.ApiResponse;\n");
        sb.append("import io.swagger.v3.oas.annotations.responses.ApiResponses;\n");
        sb.append("import io.swagger.v3.oas.annotations.tags.Tag;\n\n");

        sb.append("import lombok.RequiredArgsConstructor;\n");
        sb.append("import lombok.extern.log4j.Log4j2;\n\n");

        sb.append("import org.springframework.http.HttpStatus;\n");
        sb.append("import org.springframework.http.ResponseEntity;\n");
        sb.append("import org.springframework.web.bind.annotation.*;\n\n");

        if (needsUuidImport) {
            sb.append("import java.util.UUID;\n");
        }
        sb.append("import java.util.List;\n\n");

        sb.append("/**\n");
        sb.append(" * REST controller for managing ").append(entityName).append(" resources.\n");
        sb.append(" * Generated automatically by SQLDomainGen.\n");
        sb.append(" */\n");
        sb.append("@RestController\n");
        sb.append("@RequiredArgsConstructor\n");
        sb.append("@Log4j2\n");
        sb.append("@Tag(name = \"").append(entityName).append("\", description = \"").append(entityName).append(" API\")\n");
        sb.append("@RequestMapping(\"").append(apiPath).append("\")\n");
        sb.append("public class ").append(entityName).append("Controller {\n\n");

        sb.append("    private final ").append(serviceName).append(" ").append(serviceVar).append(";\n\n");

        sb.append("    /**\n");
        sb.append("     * Retrieves all records.\n");
        sb.append("     *\n");
        sb.append("     * @return list of ").append(dtoName).append("\n");
        sb.append("     */\n");
        sb.append("    @Operation(summary = \"Get all ").append(entityName).append("\")\n");
        sb.append("    @ApiResponses({\n");
        sb.append("            @ApiResponse(responseCode = \"200\", description = \"Success\")\n");
        sb.append("    })\n");
        sb.append("    @GetMapping\n");
        sb.append("    public ResponseEntity<List<").append(dtoName).append(">> getAll() {\n");
        sb.append("        log.info(\"Fetching all ").append(entityName.toLowerCase()).append(" records.\");\n");
        sb.append("        return ResponseEntity.ok(").append(serviceVar).append(".getAll").append(entityName).append("());\n");
        sb.append("    }\n\n");

        sb.append("    /**\n");
        sb.append("     * Retrieves a record by id.\n");
        sb.append("     *\n");
        sb.append("     * @param id record id\n");
        sb.append("     * @return ").append(dtoName).append("\n");
        sb.append("     */\n");
        sb.append("    @Operation(summary = \"Get ").append(entityName).append(" by id\")\n");
        sb.append("    @ApiResponses({\n");
        sb.append("            @ApiResponse(responseCode = \"200\", description = \"Success\"),\n");
        sb.append("            @ApiResponse(responseCode = \"404\", description = \"Not found\")\n");
        sb.append("    })\n");
        sb.append("    @GetMapping(\"/{id}\")\n");
        sb.append("    public ResponseEntity<").append(dtoName).append("> getById(\n");
        sb.append("            @Parameter(description = \"").append(entityName).append(" id\", required = true)\n");
        sb.append("            @PathVariable ").append(pkType).append(" id) {\n");
        sb.append("        log.info(\"Fetching ").append(entityName.toLowerCase()).append(" with id: {}\", id);\n");
        sb.append("        return ResponseEntity.ok(").append(serviceVar).append(".get").append(entityName).append("ById(id));\n");
        sb.append("    }\n\n");

        sb.append("    /**\n");
        sb.append("     * Creates a new record.\n");
        sb.append("     *\n");
        sb.append("     * @param dto payload\n");
        sb.append("     * @return created ").append(dtoName).append("\n");
        sb.append("     */\n");
        sb.append("    @Operation(summary = \"Create ").append(entityName).append("\")\n");
        sb.append("    @ApiResponses({\n");
        sb.append("            @ApiResponse(responseCode = \"201\", description = \"Created\")\n");
        sb.append("    })\n");
        sb.append("    @PostMapping\n");
        sb.append("    public ResponseEntity<").append(dtoName).append("> create(@RequestBody ").append(dtoName).append(" dto) {\n");
        sb.append("        log.info(\"Creating ").append(entityName.toLowerCase()).append(".\");\n");
        sb.append("        ").append(dtoName).append(" created = ").append(serviceVar).append(".create").append(entityName).append("(dto);\n");
        sb.append("        return ResponseEntity.status(HttpStatus.CREATED).body(created);\n");
        sb.append("    }\n\n");

        sb.append("    /**\n");
        sb.append("     * Updates an existing record (PUT-style).\n");
        sb.append("     *\n");
        sb.append("     * @param id record id\n");
        sb.append("     * @param dto payload\n");
        sb.append("     * @return updated ").append(dtoName).append("\n");
        sb.append("     */\n");
        sb.append("    @Operation(summary = \"Update ").append(entityName).append("\")\n");
        sb.append("    @ApiResponses({\n");
        sb.append("            @ApiResponse(responseCode = \"200\", description = \"Success\"),\n");
        sb.append("            @ApiResponse(responseCode = \"404\", description = \"Not found\")\n");
        sb.append("    })\n");
        sb.append("    @PutMapping(\"/{id}\")\n");
        sb.append("    public ResponseEntity<").append(dtoName).append("> update(\n");
        sb.append("            @Parameter(description = \"").append(entityName).append(" id\", required = true)\n");
        sb.append("            @PathVariable ").append(pkType).append(" id,\n");
        sb.append("            @RequestBody ").append(dtoName).append(" dto) {\n");
        sb.append("        log.info(\"Updating ").append(entityName.toLowerCase()).append(" with id: {}\", id);\n");
        sb.append("        return ResponseEntity.ok(").append(serviceVar).append(".update").append(entityName).append("(id, dto));\n");
        sb.append("    }\n\n");

        sb.append("    /**\n");
        sb.append("     * Deletes a record by id.\n");
        sb.append("     *\n");
        sb.append("     * @param id record id\n");
        sb.append("     */\n");
        sb.append("    @Operation(summary = \"Delete ").append(entityName).append("\")\n");
        sb.append("    @ApiResponses({\n");
        sb.append("            @ApiResponse(responseCode = \"204\", description = \"No content\"),\n");
        sb.append("            @ApiResponse(responseCode = \"404\", description = \"Not found\")\n");
        sb.append("    })\n");
        sb.append("    @DeleteMapping(\"/{id}\")\n");
        sb.append("    public ResponseEntity<Void> deleteById(\n");
        sb.append("            @Parameter(description = \"").append(entityName).append(" id\", required = true)\n");
        sb.append("            @PathVariable ").append(pkType).append(" id) {\n");
        sb.append("        log.info(\"Deleting ").append(entityName.toLowerCase()).append(" with id: {}\", id);\n");
        sb.append("        ").append(serviceVar).append(".delete").append(entityName).append("(id);\n");
        sb.append("        return ResponseEntity.noContent().build();\n");
        sb.append("    }\n");

        sb.append("}\n");

        return sb.toString();
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
        return t.equalsIgnoreCase("UUID")
                || t.equals("java.util.UUID")
                || t.endsWith(".UUID");
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

    private static void writeFile(Path filePath, String content, boolean overwrite) {
        try {
            if (!overwrite && Files.exists(filePath)) {
                log.info("ℹ️ Skipping existing file: {}", filePath.toAbsolutePath());
                return;
            }
            Files.writeString(filePath, content, StandardCharsets.UTF_8);
            log.info("✅ Controller generated: {}", filePath.toAbsolutePath());
        } catch (IOException e) {
            throw new IllegalStateException("Failed to write file: " + filePath, e);
        }
    }
}
