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
 * Generates REST controller classes for parsed database tables.
 */
@Log4j2
public class ControllerGenerator {

    /**
     * Generates REST controllers for all tables.
     * Output directory:
     * {outputDir}/src/main/java/{basePackagePath}/controller
     * Package:
     * {basePackage}.controller
     *
     * @param tables source table metadata
     * @param outputDir target project root directory
     * @param basePackage base package for generated classes
     * @param overwrite overwrite existing files when true
     */
    public void generateControllers(List<Table> tables, String outputDir, String basePackage, boolean overwrite) {
        Objects.requireNonNull(tables, "tables must not be null");
        Objects.requireNonNull(outputDir, "outputDir must not be null");
        Objects.requireNonNull(basePackage, "basePackage must not be null");

        Path controllerDir = GeneratorSupport.ensureDirectory(
                PackageResolver.resolvePath(outputDir, basePackage, "controller")
        );
        String controllerPackage = PackageResolver.resolvePackageName(basePackage, "controller");

        for (Table table : tables) {
            String entityName = NamingConverter.toPascalCase(
                    GeneratorSupport.normalizeTableName(table.getName())
            );
            String code = generateControllerCode(table, controllerPackage, basePackage);

            Path filePath = controllerDir.resolve(entityName + "Controller.java");
            GeneratorSupport.writeFile(filePath, code, overwrite);
        }

        log.info("✅ Controllers generated under: {}", controllerDir.toAbsolutePath());
    }

    /**
     * Generates controller source code for a single table.
     *
     * @param table source table metadata
     * @param controllerPackage package of the generated controller
     * @param basePackage base package for generated classes
     * @return generated Java source code
     */
    public String generateControllerCode(Table table, String controllerPackage, String basePackage) {
        Objects.requireNonNull(table, "table must not be null");
        Objects.requireNonNull(controllerPackage, "controllerPackage must not be null");
        Objects.requireNonNull(basePackage, "basePackage must not be null");

        String entityName = NamingConverter.toPascalCase(
                GeneratorSupport.normalizeTableName(table.getName())
        );
        String dtoName = entityName + "Dto";
        String serviceName = entityName + "Service";
        String serviceVar = NamingConverter.decapitalizeFirstLetter(entityName) + "Service";

        String modelPackage = PackageResolver.resolvePackageName(basePackage, "entity");
        String dtoPackage = PackageResolver.resolvePackageName(basePackage, "dto");
        String servicePackage = PackageResolver.resolvePackageName(basePackage, "service");

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

        boolean compositePk = hasCompositePrimaryKey(table);
        List<Column> pkColumns = getPrimaryKeyColumns(table);

        String pkType;
        String pkImportLine = null;

        if (compositePk) {
            pkType = entityName + "PK";
            pkImportLine = "import " + modelPackage + "." + pkType + ";";
        } else {
            pkType = detectSinglePrimaryKeyType(table);
        }

        boolean needsUuidImport = needsUuidImport(table);
        boolean needsBigDecimalImport = needsBigDecimalImport(table);
        boolean needsBigIntegerImport = needsBigIntegerImport(table);

        String apiPath = "/api/" + NamingConverter.toKebabCase(entityName) + "s";

        StringBuilder sb = new StringBuilder();

        sb.append("package ").append(controllerPackage).append(";\n\n");

        sb.append("import ").append(dtoPackage).append(".").append(dtoName).append(";\n");
        sb.append("import ").append(servicePackage).append(".").append(serviceName).append(";\n");
        if (pkImportLine != null) {
            sb.append(pkImportLine).append("\n");
        }
        sb.append("\n");

        sb.append("import io.swagger.v3.oas.annotations.Operation;\n");
        sb.append("import io.swagger.v3.oas.annotations.Parameter;\n");
        sb.append("import io.swagger.v3.oas.annotations.responses.ApiResponse;\n");
        sb.append("import io.swagger.v3.oas.annotations.responses.ApiResponses;\n");
        sb.append("import io.swagger.v3.oas.annotations.tags.Tag;\n\n");

        sb.append("import jakarta.validation.Valid;\n\n");

        sb.append("import lombok.RequiredArgsConstructor;\n");
        sb.append("import lombok.extern.log4j.Log4j2;\n\n");

        sb.append("import org.springframework.http.HttpStatus;\n");
        sb.append("import org.springframework.http.ResponseEntity;\n");
        sb.append("import org.springframework.web.bind.annotation.*;\n\n");

        if (needsUuidImport) {
            sb.append("import java.util.UUID;\n");
        }
        if (needsBigDecimalImport) {
            sb.append("import java.math.BigDecimal;\n");
        }
        if (needsBigIntegerImport) {
            sb.append("import java.math.BigInteger;\n");
        }
        sb.append("import java.util.List;\n\n");

        sb.append("/**\n");
        sb.append(" * REST controller for managing ").append(displayLabel).append(" resources.\n");
        sb.append(" * Generated automatically by SQLDomainGen.\n");
        sb.append(" */\n");
        sb.append("@RestController\n");
        sb.append("@RequiredArgsConstructor\n");
        sb.append("@Log4j2\n");
        sb.append("@Tag(name = \"").append(displayLabel).append("\", description = \"").append(displayLabel).append(" API\")\n");
        sb.append("@RequestMapping(\"").append(apiPath).append("\")\n");
        sb.append("public class ").append(entityName).append("Controller {\n\n");

        sb.append("    private final ").append(serviceName).append(" ").append(serviceVar).append(";\n\n");

        appendGetAllMethod(sb, entityName, dtoName, displayLabel, lowerDisplayLabel, serviceVar);
        appendGetByIdMethod(sb, entityName, dtoName, displayLabel, lowerDisplayLabel, serviceVar, pkType, pkColumns, compositePk);
        appendCreateMethod(sb, entityName, dtoName, displayLabel, lowerDisplayLabel, serviceVar);
        appendPatchMethod(sb, entityName, dtoName, displayLabel, lowerDisplayLabel, serviceVar, pkType, pkColumns, compositePk);
        appendDeleteMethod(sb, entityName, dtoName, displayLabel, lowerDisplayLabel, serviceVar, pkType, pkColumns, compositePk);

        if (compositePk) {
            appendBuildCompositeIdMethod(sb, entityName, displayLabel, pkType, pkColumns);
        }

        sb.append("}\n");

        return sb.toString();
    }

    /**
     * Appends the get-all controller method.
     *
     * @param sb target builder
     * @param entityName entity simple name
     * @param dtoName dto simple name
     * @param displayLabel human-readable display label
     * @param lowerDisplayLabel lowercase display label
     * @param serviceVar injected service variable name
     */
    private void appendGetAllMethod(
            StringBuilder sb,
            String entityName,
            String dtoName,
            String displayLabel,
            String lowerDisplayLabel,
            String serviceVar
    ) {
        sb.append("    /**\n");
        sb.append("     * Retrieves all ").append(displayLabel).append(" records.\n");
        sb.append("     *\n");
        sb.append("     * @return list of ").append(dtoName).append("\n");
        sb.append("     */\n");
        sb.append("    @Operation(summary = \"Get all ").append(displayLabel).append("\")\n");
        sb.append("    @ApiResponses({\n");
        sb.append("            @ApiResponse(responseCode = \"200\", description = \"Success\")\n");
        sb.append("    })\n");
        sb.append("    @GetMapping\n");
        sb.append("    public ResponseEntity<List<").append(dtoName).append(">> getAll() {\n");
        sb.append("        log.info(\"Fetching all ").append(lowerDisplayLabel).append(" records.\");\n");
        sb.append("        return ResponseEntity.ok(").append(serviceVar).append(".getAll").append(entityName).append("());\n");
        sb.append("    }\n\n");
    }

    /**
     * Appends the get-by-id controller method.
     *
     * @param sb target builder
     * @param entityName entity simple name
     * @param dtoName dto simple name
     * @param displayLabel human-readable display label
     * @param lowerDisplayLabel lowercase display label
     * @param serviceVar injected service variable name
     * @param pkType primary key type
     * @param pkColumns primary key columns
     * @param compositePk true when the entity uses a composite primary key
     */
    private void appendGetByIdMethod(
            StringBuilder sb,
            String entityName,
            String dtoName,
            String displayLabel,
            String lowerDisplayLabel,
            String serviceVar,
            String pkType,
            List<Column> pkColumns,
            boolean compositePk
    ) {
        sb.append("    /**\n");
        sb.append("     * Retrieves a ").append(lowerDisplayLabel).append(" record by id.\n");
        sb.append("     *\n");

        if (compositePk) {
            for (Column pkCol : pkColumns) {
                String columnName = GeneratorSupport.unquoteIdentifier(pkCol.getName());
                String paramName = toCamelCase(columnName);
                sb.append("     * @param ").append(paramName).append(" ").append(columnName).append(" value\n");
            }
        } else {
            sb.append("     * @param id ").append(lowerDisplayLabel).append(" identifier\n");
        }

        sb.append("     * @return ").append(dtoName).append("\n");
        sb.append("     */\n");
        sb.append("    @Operation(summary = \"Get ").append(displayLabel).append(" by id\")\n");
        sb.append("    @ApiResponses({\n");
        sb.append("            @ApiResponse(responseCode = \"200\", description = \"Success\"),\n");
        sb.append("            @ApiResponse(responseCode = \"404\", description = \"Not found\")\n");
        sb.append("    })\n");

        if (compositePk) {
            sb.append("    @GetMapping(\"/by-id\")\n");
            sb.append("    public ResponseEntity<").append(dtoName).append("> getById(\n");

            for (int i = 0; i < pkColumns.size(); i++) {
                Column pkCol = pkColumns.get(i);
                String columnName = GeneratorSupport.unquoteIdentifier(pkCol.getName());
                String paramName = toCamelCase(columnName);
                String paramType = detectJavaTypeForPkColumn(pkCol);

                sb.append("            @Parameter(description = \"").append(columnName).append("\", required = true)\n");
                sb.append("            @RequestParam(name = \"").append(columnName).append("\") ")
                        .append(paramType).append(" ").append(paramName);

                if (i < pkColumns.size() - 1) {
                    sb.append(",\n");
                } else {
                    sb.append(") {\n");
                }
            }

            sb.append("        ").append(pkType).append(" id = build").append(entityName).append("Id(");
            appendCompositeIdArguments(sb, pkColumns);
            sb.append(");\n");
            sb.append("        log.info(\"Fetching ").append(lowerDisplayLabel).append(" with composite id: {}\", id);\n");
            sb.append("        return ResponseEntity.ok(").append(serviceVar).append(".get").append(entityName).append("ById(id));\n");
            sb.append("    }\n\n");
            return;
        }

        sb.append("    @GetMapping(\"/{id}\")\n");
        sb.append("    public ResponseEntity<").append(dtoName).append("> getById(\n");
        sb.append("            @Parameter(description = \"").append(lowerDisplayLabel).append(" id\", required = true)\n");
        sb.append("            @PathVariable ").append(pkType).append(" id) {\n");
        sb.append("        log.info(\"Fetching ").append(lowerDisplayLabel).append(" with id: {}\", id);\n");
        sb.append("        return ResponseEntity.ok(").append(serviceVar).append(".get").append(entityName).append("ById(id));\n");
        sb.append("    }\n\n");
    }

    /**
     * Appends the create controller method.
     *
     * @param sb target builder
     * @param entityName entity simple name
     * @param dtoName dto simple name
     * @param displayLabel human-readable display label
     * @param lowerDisplayLabel lowercase display label
     * @param serviceVar injected service variable name
     */
    private void appendCreateMethod(
            StringBuilder sb,
            String entityName,
            String dtoName,
            String displayLabel,
            String lowerDisplayLabel,
            String serviceVar
    ) {
        sb.append("    /**\n");
        sb.append("     * Creates a new ").append(lowerDisplayLabel).append(" record.\n");
        sb.append("     *\n");
        sb.append("     * @param dto ").append(lowerDisplayLabel).append(" payload\n");
        sb.append("     * @return created ").append(dtoName).append("\n");
        sb.append("     */\n");
        sb.append("    @Operation(summary = \"Create ").append(displayLabel).append("\")\n");
        sb.append("    @ApiResponses({\n");
        sb.append("            @ApiResponse(responseCode = \"201\", description = \"Created\")\n");
        sb.append("    })\n");
        sb.append("    @PostMapping\n");
        sb.append("    public ResponseEntity<").append(dtoName).append("> create(@Valid @RequestBody ").append(dtoName).append(" dto) {\n");
        sb.append("        log.info(\"Creating ").append(lowerDisplayLabel).append(".\");\n");
        sb.append("        ").append(dtoName).append(" created = ").append(serviceVar).append(".create").append(entityName).append("(dto);\n");
        sb.append("        return ResponseEntity.status(HttpStatus.CREATED).body(created);\n");
        sb.append("    }\n\n");
    }

    /**
     * Appends the PATCH controller method.
     *
     * @param sb target builder
     * @param entityName entity simple name
     * @param dtoName dto simple name
     * @param displayLabel human-readable display label
     * @param lowerDisplayLabel lowercase display label
     * @param serviceVar injected service variable name
     * @param pkType primary key type
     * @param pkColumns primary key columns
     * @param compositePk true when the entity uses a composite primary key
     */
    private void appendPatchMethod(
            StringBuilder sb,
            String entityName,
            String dtoName,
            String displayLabel,
            String lowerDisplayLabel,
            String serviceVar,
            String pkType,
            List<Column> pkColumns,
            boolean compositePk
    ) {
        sb.append("    /**\n");
        sb.append("     * Partially updates an existing ").append(lowerDisplayLabel).append(" record.\n");
        sb.append("     * Only non-null fields are updated.\n");
        sb.append("     *\n");

        if (compositePk) {
            for (Column pkCol : pkColumns) {
                String columnName = GeneratorSupport.unquoteIdentifier(pkCol.getName());
                String paramName = toCamelCase(columnName);
                sb.append("     * @param ").append(paramName).append(" ").append(columnName).append(" value\n");
            }
        } else {
            sb.append("     * @param id ").append(lowerDisplayLabel).append(" identifier\n");
        }

        sb.append("     * @param dto partial ").append(lowerDisplayLabel).append(" payload\n");
        sb.append("     * @return updated ").append(dtoName).append("\n");
        sb.append("     */\n");
        sb.append("    @Operation(summary = \"Patch ").append(displayLabel).append("\")\n");
        sb.append("    @ApiResponses({\n");
        sb.append("            @ApiResponse(responseCode = \"200\", description = \"Success\"),\n");
        sb.append("            @ApiResponse(responseCode = \"404\", description = \"Not found\")\n");
        sb.append("    })\n");

        if (compositePk) {
            sb.append("    @PatchMapping(\"/by-id\")\n");
            sb.append("    public ResponseEntity<").append(dtoName).append("> patch(\n");

            for (int i = 0; i < pkColumns.size(); i++) {
                Column pkCol = pkColumns.get(i);
                String columnName = GeneratorSupport.unquoteIdentifier(pkCol.getName());
                String paramName = toCamelCase(columnName);
                String paramType = detectJavaTypeForPkColumn(pkCol);

                sb.append("            @Parameter(description = \"").append(columnName).append("\", required = true)\n");
                sb.append("            @RequestParam(name = \"").append(columnName).append("\") ")
                        .append(paramType).append(" ").append(paramName).append(",\n");
            }

            sb.append("            @RequestBody ").append(dtoName).append(" dto) {\n");
            sb.append("        ").append(pkType).append(" id = build").append(entityName).append("Id(");
            appendCompositeIdArguments(sb, pkColumns);
            sb.append(");\n");
            sb.append("        log.info(\"Patching ").append(lowerDisplayLabel).append(" with composite id: {}\", id);\n");
            sb.append("        return ResponseEntity.ok(").append(serviceVar).append(".patch").append(entityName).append("(id, dto));\n");
            sb.append("    }\n\n");
            return;
        }

        sb.append("    @PatchMapping(\"/{id}\")\n");
        sb.append("    public ResponseEntity<").append(dtoName).append("> patch(\n");
        sb.append("            @Parameter(description = \"").append(lowerDisplayLabel).append(" id\", required = true)\n");
        sb.append("            @PathVariable ").append(pkType).append(" id,\n");
        sb.append("            @RequestBody ").append(dtoName).append(" dto) {\n");
        sb.append("        log.info(\"Patching ").append(lowerDisplayLabel).append(" with id: {}\", id);\n");
        sb.append("        return ResponseEntity.ok(").append(serviceVar).append(".patch").append(entityName).append("(id, dto));\n");
        sb.append("    }\n\n");
    }

    /**
     * Appends the delete controller method.
     *
     * @param sb target builder
     * @param entityName entity simple name
     * @param dtoName dto simple name
     * @param displayLabel human-readable display label
     * @param lowerDisplayLabel lowercase display label
     * @param serviceVar injected service variable name
     * @param pkType primary key type
     * @param pkColumns primary key columns
     * @param compositePk true when the entity uses a composite primary key
     */
    private void appendDeleteMethod(
            StringBuilder sb,
            String entityName,
            String dtoName,
            String displayLabel,
            String lowerDisplayLabel,
            String serviceVar,
            String pkType,
            List<Column> pkColumns,
            boolean compositePk
    ) {
        sb.append("    /**\n");
        sb.append("     * Deletes a ").append(lowerDisplayLabel).append(" record by id.\n");
        sb.append("     *\n");

        if (compositePk) {
            for (Column pkCol : pkColumns) {
                String columnName = GeneratorSupport.unquoteIdentifier(pkCol.getName());
                String paramName = toCamelCase(columnName);
                sb.append("     * @param ").append(paramName).append(" ").append(columnName).append(" value\n");
            }
        } else {
            sb.append("     * @param id ").append(lowerDisplayLabel).append(" identifier\n");
        }

        sb.append("     * @return no content\n");
        sb.append("     */\n");
        sb.append("    @Operation(summary = \"Delete ").append(displayLabel).append("\")\n");
        sb.append("    @ApiResponses({\n");
        sb.append("            @ApiResponse(responseCode = \"204\", description = \"No content\"),\n");
        sb.append("            @ApiResponse(responseCode = \"404\", description = \"Not found\")\n");
        sb.append("    })\n");

        if (compositePk) {
            sb.append("    @DeleteMapping(\"/by-id\")\n");
            sb.append("    public ResponseEntity<Void> deleteById(\n");

            for (int i = 0; i < pkColumns.size(); i++) {
                Column pkCol = pkColumns.get(i);
                String columnName = GeneratorSupport.unquoteIdentifier(pkCol.getName());
                String paramName = toCamelCase(columnName);
                String paramType = detectJavaTypeForPkColumn(pkCol);

                sb.append("            @Parameter(description = \"").append(columnName).append("\", required = true)\n");
                sb.append("            @RequestParam(name = \"").append(columnName).append("\") ")
                        .append(paramType).append(" ").append(paramName);

                if (i < pkColumns.size() - 1) {
                    sb.append(",\n");
                } else {
                    sb.append(") {\n");
                }
            }

            sb.append("        ").append(pkType).append(" id = build").append(entityName).append("Id(");
            appendCompositeIdArguments(sb, pkColumns);
            sb.append(");\n");
            sb.append("        log.info(\"Deleting ").append(lowerDisplayLabel).append(" with composite id: {}\", id);\n");
            sb.append("        ").append(serviceVar).append(".delete").append(entityName).append("(id);\n");
            sb.append("        return ResponseEntity.noContent().build();\n");
            sb.append("    }\n\n");
            return;
        }

        sb.append("    @DeleteMapping(\"/{id}\")\n");
        sb.append("    public ResponseEntity<Void> deleteById(\n");
        sb.append("            @Parameter(description = \"").append(lowerDisplayLabel).append(" id\", required = true)\n");
        sb.append("            @PathVariable ").append(pkType).append(" id) {\n");
        sb.append("        log.info(\"Deleting ").append(lowerDisplayLabel).append(" with id: {}\", id);\n");
        sb.append("        ").append(serviceVar).append(".delete").append(entityName).append("(id);\n");
        sb.append("        return ResponseEntity.noContent().build();\n");
        sb.append("    }\n\n");
    }

    /**
     * Appends the composite id builder method.
     *
     * @param sb target builder
     * @param entityName entity simple name
     * @param displayLabel human-readable display label
     * @param pkType composite primary key type
     * @param pkColumns primary key columns
     */
    private void appendBuildCompositeIdMethod(
            StringBuilder sb,
            String entityName,
            String displayLabel,
            String pkType,
            List<Column> pkColumns
    ) {
        sb.append("    /**\n");
        sb.append("     * Builds a composite identifier for ").append(displayLabel).append(".\n");
        sb.append("     *\n");

        for (Column pkCol : pkColumns) {
            String columnName = GeneratorSupport.unquoteIdentifier(pkCol.getName());
            String paramName = toCamelCase(columnName);
            sb.append("     * @param ").append(paramName).append(" ").append(columnName).append(" value\n");
        }

        sb.append("     * @return composed ").append(pkType).append("\n");
        sb.append("     */\n");
        sb.append("    private ").append(pkType).append(" build").append(entityName).append("Id(");

        for (int i = 0; i < pkColumns.size(); i++) {
            Column pkCol = pkColumns.get(i);
            String paramType = detectJavaTypeForPkColumn(pkCol);
            String paramName = toCamelCase(GeneratorSupport.unquoteIdentifier(pkCol.getName()));

            sb.append(paramType).append(" ").append(paramName);
            if (i < pkColumns.size() - 1) {
                sb.append(", ");
            }
        }

        sb.append(") {\n");
        sb.append("        ").append(pkType).append(" id = new ").append(pkType).append("();\n");

        for (Column pkCol : pkColumns) {
            String fieldName = toCamelCase(GeneratorSupport.unquoteIdentifier(pkCol.getName()));
            sb.append("        id.set").append(toPascalCase(fieldName)).append("(").append(fieldName).append(");\n");
        }

        sb.append("        return id;\n");
        sb.append("    }\n");
    }

    /**
     * Appends the ordered composite id arguments.
     *
     * @param sb target builder
     * @param pkColumns primary key columns
     */
    private void appendCompositeIdArguments(StringBuilder sb, List<Column> pkColumns) {
        for (int i = 0; i < pkColumns.size(); i++) {
            sb.append(toCamelCase(GeneratorSupport.unquoteIdentifier(pkColumns.get(i).getName())));
            if (i < pkColumns.size() - 1) {
                sb.append(", ");
            }
        }
    }

    /**
     * Checks whether any primary key column requires a UUID import.
     *
     * @param table source table metadata
     * @return true when a UUID primary key type is detected
     */
    private static boolean needsUuidImport(Table table) {
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
     * Checks whether any primary key column requires a BigInteger import.
     *
     * @param table source table metadata
     * @return true when a BigInteger primary key type is detected
     */
    private static boolean needsBigIntegerImport(Table table) {
        return table.getColumns().stream()
                .filter(Objects::nonNull)
                .filter(Column::isPrimaryKey)
                .map(Column::getJavaType)
                .filter(Objects::nonNull)
                .map(String::trim)
                .anyMatch(type -> type.equals("BigInteger")
                        || type.equals("java.math.BigInteger"));
    }

    /**
     * Checks whether any primary key column requires a BigDecimal import.
     *
     * @param table source table metadata
     * @return true when a BigDecimal primary key type is detected
     */
    private static boolean needsBigDecimalImport(Table table) {
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
     * Checks whether a table uses a composite primary key.
     *
     * @param table source table metadata
     * @return true when more than one primary key column exists
     */
    private static boolean hasCompositePrimaryKey(Table table) {
        long pkCount = table.getColumns().stream()
                .filter(Objects::nonNull)
                .filter(Column::isPrimaryKey)
                .count();
        return pkCount > 1;
    }

    /**
     * Resolves the Java type to use for a primary key request parameter.
     *
     * @param column primary key column metadata
     * @return resolved Java type name
     */
    private static String detectJavaTypeForPkColumn(Column column) {
        if (column == null || column.getJavaType() == null || column.getJavaType().isBlank()) {
            return "Long";
        }

        String type = column.getJavaType().trim();

        if ("UUID".equalsIgnoreCase(type) || "java.util.UUID".equals(type) || type.endsWith(".UUID")) {
            return "UUID";
        }

        if ("BigInteger".equals(type) || "java.math.BigInteger".equals(type)) {
            return "BigInteger";
        }

        if ("BigDecimal".equals(type) || "java.math.BigDecimal".equals(type)) {
            return "BigDecimal";
        }

        if ("long".equalsIgnoreCase(type) || "java.lang.Long".equals(type)) {
            return "Long";
        }
        if ("int".equalsIgnoreCase(type) || "java.lang.Integer".equals(type)) {
            return "Integer";
        }
        if ("short".equalsIgnoreCase(type) || "java.lang.Short".equals(type)) {
            return "Short";
        }
        if ("byte".equalsIgnoreCase(type) || "java.lang.Byte".equals(type)) {
            return "Byte";
        }
        if ("boolean".equalsIgnoreCase(type) || "java.lang.Boolean".equals(type)) {
            return "Boolean";
        }

        if (type.contains(".")) {
            return type.substring(type.lastIndexOf('.') + 1);
        }

        return type;
    }

    /**
     * Converts a snake_case name to camelCase.
     *
     * @param raw source value
     * @return camelCase value
     */
    private static String toCamelCase(String raw) {
        String value = GeneratorSupport.trimToEmpty(raw);
        if (value.isEmpty()) {
            return "id";
        }

        String lower = value.toLowerCase();
        String[] parts = lower.split("_+");
        if (parts.length == 0) {
            return "id";
        }

        StringBuilder out = new StringBuilder(parts[0]);
        for (int i = 1; i < parts.length; i++) {
            if (parts[i].isEmpty()) {
                continue;
            }
            out.append(Character.toUpperCase(parts[i].charAt(0)));
            if (parts[i].length() > 1) {
                out.append(parts[i].substring(1));
            }
        }
        return out.toString();
    }

    /**
     * Converts a camelCase value to PascalCase.
     *
     * @param camel source value
     * @return PascalCase value
     */
    private static String toPascalCase(String camel) {
        String value = GeneratorSupport.trimToEmpty(camel);
        if (value.isEmpty()) {
            return "Id";
        }
        return Character.toUpperCase(value.charAt(0)) + value.substring(1);
    }

    /**
     * Returns all primary key columns of the table.
     *
     * @param table source table metadata
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
     * Resolves the Java type for a single primary key.
     *
     * @param table source table metadata
     * @return resolved Java type name
     */
    private static String detectSinglePrimaryKeyType(Table table) {
        List<Column> pkColumns = getPrimaryKeyColumns(table);

        if (pkColumns.isEmpty()) {
            throw new IllegalStateException("No Primary Key found for table: " + table.getName());
        }

        if (pkColumns.size() > 1) {
            throw new IllegalStateException(
                    "Composite Primary Key found for table: " + table.getName()
                            + ". Single PK type cannot be detected."
            );
        }

        Column primaryKeyColumn = pkColumns.get(0);

        String rawType = primaryKeyColumn.getJavaType();
        if (rawType == null || rawType.isBlank()) {
            return "Long";
        }

        String normalizedType = rawType.trim();

        if ("UUID".equalsIgnoreCase(normalizedType)
                || "java.util.UUID".equals(normalizedType)
                || normalizedType.endsWith(".UUID")) {
            return "UUID";
        }

        if ("BigDecimal".equals(normalizedType)
                || "java.math.BigDecimal".equals(normalizedType)) {
            return "BigDecimal";
        }

        if ("BigInteger".equals(normalizedType)
                || "java.math.BigInteger".equals(normalizedType)) {
            return "BigInteger";
        }

        if ("long".equalsIgnoreCase(normalizedType) || "java.lang.Long".equals(normalizedType)) {
            return "Long";
        }
        if ("int".equalsIgnoreCase(normalizedType) || "java.lang.Integer".equals(normalizedType)) {
            return "Integer";
        }
        if ("short".equalsIgnoreCase(normalizedType) || "java.lang.Short".equals(normalizedType)) {
            return "Short";
        }
        if ("byte".equalsIgnoreCase(normalizedType) || "java.lang.Byte".equals(normalizedType)) {
            return "Byte";
        }
        if ("boolean".equalsIgnoreCase(normalizedType) || "java.lang.Boolean".equals(normalizedType)) {
            return "Boolean";
        }

        if (normalizedType.contains(".")) {
            return normalizedType.substring(normalizedType.lastIndexOf('.') + 1);
        }

        return normalizedType;
    }
}