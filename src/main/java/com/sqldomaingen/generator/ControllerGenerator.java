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
     * Output directory:
     * {outputDir}/src/main/java/{basePackagePath}/controller
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

        String modelPackage = PackageResolver.resolvePackageName(basePackage, "entity");
        String dtoPackage = PackageResolver.resolvePackageName(basePackage, "dto");
        String servicePackage = PackageResolver.resolvePackageName(basePackage, "service");

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

        // GET ALL
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

        // GET BY ID
        sb.append("    /**\n");
        sb.append("     * Retrieves a record by id.\n");
        sb.append("     *\n");
        sb.append("     * @return ").append(dtoName).append("\n");
        sb.append("     */\n");
        sb.append("    @Operation(summary = \"Get ").append(entityName).append(" by id\")\n");
        sb.append("    @ApiResponses({\n");
        sb.append("            @ApiResponse(responseCode = \"200\", description = \"Success\"),\n");
        sb.append("            @ApiResponse(responseCode = \"404\", description = \"Not found\")\n");
        sb.append("    })\n");

        if (compositePk) {
            sb.append("    @GetMapping(\"/by-id\")\n");
            sb.append("    public ResponseEntity<").append(dtoName).append("> getById(\n");

            for (int i = 0; i < pkColumns.size(); i++) {
                Column pkCol = pkColumns.get(i);
                String colName = safe(pkCol.getName());
                String paramName = toCamelCase(colName);
                String paramType = detectJavaTypeForPkColumn(pkCol);

                sb.append("            @Parameter(description = \"").append(colName).append("\", required = true)\n");
                sb.append("            @RequestParam(name = \"").append(colName).append("\") ").append(paramType).append(" ").append(paramName);

                if (i < pkColumns.size() - 1) {
                    sb.append(",\n");
                } else {
                    sb.append(") {\n");
                }
            }

            sb.append("        ").append(pkType).append(" id = build").append(entityName).append("Id(");
            for (int i = 0; i < pkColumns.size(); i++) {
                sb.append(toCamelCase(pkColumns.get(i).getName()));
                if (i < pkColumns.size() - 1) {
                    sb.append(", ");
                }
            }
            sb.append(");\n");

            sb.append("        log.info(\"Fetching ").append(entityName.toLowerCase()).append(" with composite id: {}\", id);\n");
            sb.append("        return ResponseEntity.ok(").append(serviceVar).append(".get").append(entityName).append("ById(id));\n");
            sb.append("    }\n\n");
        } else {
            sb.append("    @GetMapping(\"/{id}\")\n");
            sb.append("    public ResponseEntity<").append(dtoName).append("> getById(\n");
            sb.append("            @Parameter(description = \"").append(entityName).append(" id\", required = true)\n");
            sb.append("            @PathVariable ").append(pkType).append(" id) {\n");
            sb.append("        log.info(\"Fetching ").append(entityName.toLowerCase()).append(" with id: {}\", id);\n");
            sb.append("        return ResponseEntity.ok(").append(serviceVar).append(".get").append(entityName).append("ById(id));\n");
            sb.append("    }\n\n");
        }

        // CREATE
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

        // UPDATE
        sb.append("    /**\n");
        sb.append("     * Updates an existing record (PUT-style).\n");
        sb.append("     *\n");
        sb.append("     * @param dto payload\n");
        sb.append("     * @return updated ").append(dtoName).append("\n");
        sb.append("     */\n");
        sb.append("    @Operation(summary = \"Update ").append(entityName).append("\")\n");
        sb.append("    @ApiResponses({\n");
        sb.append("            @ApiResponse(responseCode = \"200\", description = \"Success\"),\n");
        sb.append("            @ApiResponse(responseCode = \"404\", description = \"Not found\")\n");
        sb.append("    })\n");

        if (compositePk) {
            sb.append("    @PutMapping(\"/by-id\")\n");
            sb.append("    public ResponseEntity<").append(dtoName).append("> update(\n");

            for (int i = 0; i < pkColumns.size(); i++) {
                Column pkCol = pkColumns.get(i);
                String colName = safe(pkCol.getName());
                String paramName = toCamelCase(colName);
                String paramType = detectJavaTypeForPkColumn(pkCol);

                sb.append("            @Parameter(description = \"").append(colName).append("\", required = true)\n");
                sb.append("            @RequestParam(name = \"").append(colName).append("\") ").append(paramType).append(" ").append(paramName).append(",\n");
            }

            sb.append("            @RequestBody ").append(dtoName).append(" dto) {\n");

            sb.append("        ").append(pkType).append(" id = build").append(entityName).append("Id(");
            for (int i = 0; i < pkColumns.size(); i++) {
                sb.append(toCamelCase(pkColumns.get(i).getName()));
                if (i < pkColumns.size() - 1) {
                    sb.append(", ");
                }
            }
            sb.append(");\n");

            sb.append("        log.info(\"Updating ").append(entityName.toLowerCase()).append(" with composite id: {}\", id);\n");
            sb.append("        return ResponseEntity.ok(").append(serviceVar).append(".update").append(entityName).append("(id, dto));\n");
            sb.append("    }\n\n");
        } else {
            sb.append("    @PutMapping(\"/{id}\")\n");
            sb.append("    public ResponseEntity<").append(dtoName).append("> update(\n");
            sb.append("            @Parameter(description = \"").append(entityName).append(" id\", required = true)\n");
            sb.append("            @PathVariable ").append(pkType).append(" id,\n");
            sb.append("            @RequestBody ").append(dtoName).append(" dto) {\n");
            sb.append("        log.info(\"Updating ").append(entityName.toLowerCase()).append(" with id: {}\", id);\n");
            sb.append("        return ResponseEntity.ok(").append(serviceVar).append(".update").append(entityName).append("(id, dto));\n");
            sb.append("    }\n\n");
        }

        // DELETE
        sb.append("    /**\n");
        sb.append("     * Deletes a record by id.\n");
        sb.append("     *\n");
        sb.append("     * @return no content\n");
        sb.append("     */\n");
        sb.append("    @Operation(summary = \"Delete ").append(entityName).append("\")\n");
        sb.append("    @ApiResponses({\n");
        sb.append("            @ApiResponse(responseCode = \"204\", description = \"No content\"),\n");
        sb.append("            @ApiResponse(responseCode = \"404\", description = \"Not found\")\n");
        sb.append("    })\n");

        if (compositePk) {
            sb.append("    @DeleteMapping(\"/by-id\")\n");
            sb.append("    public ResponseEntity<Void> deleteById(\n");

            for (int i = 0; i < pkColumns.size(); i++) {
                Column pkCol = pkColumns.get(i);
                String colName = safe(pkCol.getName());
                String paramName = toCamelCase(colName);
                String paramType = detectJavaTypeForPkColumn(pkCol);

                sb.append("            @Parameter(description = \"").append(colName).append("\", required = true)\n");
                sb.append("            @RequestParam(name = \"").append(colName).append("\") ").append(paramType).append(" ").append(paramName);

                if (i < pkColumns.size() - 1) {
                    sb.append(",\n");
                } else {
                    sb.append(") {\n");
                }
            }

            sb.append("        ").append(pkType).append(" id = build").append(entityName).append("Id(");
            for (int i = 0; i < pkColumns.size(); i++) {
                sb.append(toCamelCase(pkColumns.get(i).getName()));
                if (i < pkColumns.size() - 1) {
                    sb.append(", ");
                }
            }
            sb.append(");\n");

            sb.append("        log.info(\"Deleting ").append(entityName.toLowerCase()).append(" with composite id: {}\", id);\n");
            sb.append("        ").append(serviceVar).append(".delete").append(entityName).append("(id);\n");
            sb.append("        return ResponseEntity.noContent().build();\n");
            sb.append("    }\n\n");

            // Composite PK builder helper inside generated controller
            sb.append("    /**\n");
            sb.append("     * Builds composite id object for ").append(entityName).append(".\n");
            sb.append("     */\n");
            sb.append("    private ").append(pkType).append(" build").append(entityName).append("Id(");

            for (int i = 0; i < pkColumns.size(); i++) {
                Column pkCol = pkColumns.get(i);
                String paramType = detectJavaTypeForPkColumn(pkCol);
                String paramName = toCamelCase(pkCol.getName());

                sb.append(paramType).append(" ").append(paramName);
                if (i < pkColumns.size() - 1) {
                    sb.append(", ");
                }
            }
            sb.append(") {\n");

            sb.append("        ").append(pkType).append(" id = new ").append(pkType).append("();\n");
            for (Column pkCol : pkColumns) {
                String fieldName = toCamelCase(pkCol.getName());
                sb.append("        id.set").append(toPascalCase(fieldName)).append("(").append(fieldName).append(");\n");
            }
            sb.append("        return id;\n");
            sb.append("    }\n");
        } else {
            sb.append("    @DeleteMapping(\"/{id}\")\n");
            sb.append("    public ResponseEntity<Void> deleteById(\n");
            sb.append("            @Parameter(description = \"").append(entityName).append(" id\", required = true)\n");
            sb.append("            @PathVariable ").append(pkType).append(" id) {\n");
            sb.append("        log.info(\"Deleting ").append(entityName.toLowerCase()).append(" with id: {}\", id);\n");
            sb.append("        ").append(serviceVar).append(".delete").append(entityName).append("(id);\n");
            sb.append("        return ResponseEntity.noContent().build();\n");
            sb.append("    }\n");
        }

        sb.append("}\n");

        return sb.toString();
    }

    private static boolean needsUuidImport(Table table) {
        return table.getColumns().stream()
                .filter(Objects::nonNull)
                .filter(Column::isPrimaryKey)
                .map(Column::getJavaType)
                .filter(Objects::nonNull)
                .map(String::trim)
                .anyMatch(t -> t.equalsIgnoreCase("UUID")
                        || t.equals("java.util.UUID")
                        || t.endsWith(".UUID"));
    }

    private static boolean hasCompositePrimaryKey(Table table) {
        long pkCount = table.getColumns().stream()
                .filter(Objects::nonNull)
                .filter(Column::isPrimaryKey)
                .count();
        return pkCount > 1;
    }

    private static String detectJavaTypeForPkColumn(Column column) {
        if (column == null || column.getJavaType() == null || column.getJavaType().isBlank()) {
            return "Long";
        }

        String t = column.getJavaType().trim();

        if ("UUID".equalsIgnoreCase(t) || "java.util.UUID".equals(t) || t.endsWith(".UUID")) {
            return "UUID";
        }

        // Numeric PKs -> Long (όπως ζήτησες)
        if ("BigDecimal".equals(t) || "java.math.BigDecimal".equals(t)) {
            return "Long";
        }

        if ("long".equalsIgnoreCase(t) || "java.lang.Long".equals(t)) return "Long";
        if ("int".equalsIgnoreCase(t) || "java.lang.Integer".equals(t)) return "Integer";
        if ("short".equalsIgnoreCase(t) || "java.lang.Short".equals(t)) return "Short";
        if ("byte".equalsIgnoreCase(t) || "java.lang.Byte".equals(t)) return "Byte";
        if ("boolean".equalsIgnoreCase(t) || "java.lang.Boolean".equals(t)) return "Boolean";

        if (t.contains(".")) {
            return t.substring(t.lastIndexOf('.') + 1);
        }

        return t;
    }

    private static String safe(String s) {
        return s == null ? "" : s.trim();
    }




    private static String toCamelCase(String raw) {
        String s = safe(raw);
        if (s.isEmpty()) return "id";

        String lower = s.toLowerCase();
        String[] parts = lower.split("_+");
        if (parts.length == 0) return "id";

        StringBuilder out = new StringBuilder(parts[0]);
        for (int i = 1; i < parts.length; i++) {
            if (parts[i].isEmpty()) continue;
            out.append(Character.toUpperCase(parts[i].charAt(0)));
            if (parts[i].length() > 1) {
                out.append(parts[i].substring(1));
            }
        }
        return out.toString();
    }

    private static String toPascalCase(String camel) {
        String s = safe(camel);
        if (s.isEmpty()) return "Id";
        return Character.toUpperCase(s.charAt(0)) + s.substring(1);
    }

    private static List<Column> getPrimaryKeyColumns(Table table) {
        Objects.requireNonNull(table, "table must not be null");

        return table.getColumns().stream()
                .filter(Objects::nonNull)
                .filter(Column::isPrimaryKey)
                .toList();
    }

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

        // numeric(...) PKs usually come as BigDecimal from parser -> map to Long (your rule)
        if ("BigDecimal".equals(t) || "java.math.BigDecimal".equals(t)) {
            return "Long";
        }

        // Common boxed / primitive conversions
        if ("long".equalsIgnoreCase(t) || "java.lang.Long".equals(t)) return "Long";
        if ("int".equalsIgnoreCase(t) || "java.lang.Integer".equals(t)) return "Integer";
        if ("short".equalsIgnoreCase(t) || "java.lang.Short".equals(t)) return "Short";
        if ("byte".equalsIgnoreCase(t) || "java.lang.Byte".equals(t)) return "Byte";
        if ("boolean".equalsIgnoreCase(t) || "java.lang.Boolean".equals(t)) return "Boolean";

        // Fully qualified class name -> simple name
        if (t.contains(".")) {
            return t.substring(t.lastIndexOf('.') + 1);
        }

        return t;
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
