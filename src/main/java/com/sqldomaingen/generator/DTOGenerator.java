package com.sqldomaingen.generator;

import com.sqldomaingen.model.Entity;
import com.sqldomaingen.model.Field;
import com.sqldomaingen.util.PackageResolver;
import lombok.extern.log4j.Log4j2;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;

@Log4j2
public class DTOGenerator {

    /**
     * Generates DTO classes under:
     * {outputDir}/src/main/java/{basePackagePath}/dto
     *
     * @param entities    entity models (non-null)
     * @param outputDir   project root output directory (non-null)
     * @param basePackage base Java package (non-null)
     */
    public void generateDTOs(List<Entity> entities, String outputDir, String basePackage) {
        Objects.requireNonNull(entities, "entities must not be null");
        Objects.requireNonNull(outputDir, "outputDir must not be null");
        Objects.requireNonNull(basePackage, "basePackage must not be null");

        String dtoPackage = PackageResolver.resolvePackageName(basePackage, "dto");
        Path dtoDir = PackageResolver.resolvePath(outputDir, basePackage, "dto");

        log.info("Starting DTO generation...");

        for (Entity entity : entities) {
            log.info("Generating DTO for entity: {}", entity.getName());

            String dtoContent = createDTOContent(entity, dtoPackage);
            writeDTOToFile(dtoContent, dtoDir, entity.getName());
        }

        log.info("DTO generation complete. Output directory: {}", dtoDir.toAbsolutePath());
    }

    private String createDTOContent(Entity entity, String dtoPackage) {
        StringBuilder builder = new StringBuilder();

        addPackageAndImports(builder, dtoPackage, entity);
        addClassDefinition(builder, entity);

        return builder.toString();
    }

    private void addPackageAndImports(StringBuilder builder, String dtoPackage, Entity entity) {
        builder.append("package ").append(dtoPackage).append(";\n\n");

        builder.append("import com.fasterxml.jackson.annotation.JsonInclude;\n");
        builder.append("import lombok.AllArgsConstructor;\n");
        builder.append("import lombok.Builder;\n");
        builder.append("import lombok.Data;\n");
        builder.append("import lombok.NoArgsConstructor;\n\n");

        Set<String> imports = collectConditionalImports(entity);
        for (String imp : imports) {
            builder.append("import ").append(imp).append(";\n");
        }

        builder.append("\n");
    }


    private Set<String> collectConditionalImports(Entity entity) {
        Set<String> imports = new TreeSet<>();

        for (Field field : entity.getFields()) {
            String rawType = safeType(field.getType());

            if (needsListImport(field, rawType)) {
                imports.add("java.util.List");
            }

            if (containsType(rawType, "LocalDateTime")) {
                imports.add("java.time.LocalDateTime");
                imports.add("com.fasterxml.jackson.annotation.JsonFormat");
            }

            if (containsType(rawType, "LocalDate")) {
                imports.add("java.time.LocalDate");
                imports.add("com.fasterxml.jackson.annotation.JsonFormat");
            }

            if (containsType(rawType, "LocalTime")) {
                imports.add("java.time.LocalTime");
                imports.add("com.fasterxml.jackson.annotation.JsonFormat");
            }

            if (containsType(rawType, "BigDecimal")) {
                imports.add("java.math.BigDecimal");
            }

            if (containsType(rawType, "UUID")) {
                imports.add("java.util.UUID");
            }

            if (!field.isRelationship() && !field.isNullable()) {
                imports.add("jakarta.validation.constraints.NotNull");
            }

            if (!field.isRelationship() && field.getLength() != null && isStringType(rawType)) {
                imports.add("jakarta.validation.constraints.Size");
            }

            if (!field.isRelationship() && isPositiveCandidate(field, rawType)) {
                imports.add("jakarta.validation.constraints.Positive");
            }

            if (!field.isRelationship() && isPatternCandidate(field, rawType)) {
                imports.add("jakarta.validation.constraints.Pattern");
            }
        }

        return imports;
    }
    private static boolean needsListImport(Field field, String rawType) {
        if (!field.isRelationship()) return false;
        String t = safeType(rawType);
        return t.startsWith("List<") || t.startsWith("java.util.List<") || "List".equals(t) || "java.util.List".equals(t);
    }

    private static boolean containsType(String rawType, String typeSimpleName) {
        String t = safeType(rawType);
        if (t.equals(typeSimpleName)) return true;
        if (t.endsWith("." + typeSimpleName)) return true;
        return t.contains("<") && t.contains(typeSimpleName);
    }

    private boolean isPositiveCandidate(Field field, String rawType) {
        String fieldName = safeType(field.getName()).toLowerCase();
        return isNumericType(rawType)
                && (fieldName.contains("price") || fieldName.contains("amount") || fieldName.contains("quantity"));
    }

    private boolean isPatternCandidate(Field field, String rawType) {
        String fieldName = safeType(field.getName()).toLowerCase();
        return isStringType(rawType)
                && (fieldName.contains("email") || fieldName.contains("phone"));
    }

    private boolean isPatternCandidate(Field field) {
        String fieldName = safeType(field.getName()).toLowerCase();
        return fieldName.contains("email") || fieldName.contains("phone");
    }

    private void addFieldAnnotations(StringBuilder builder, Field field) {
        if (field.isRelationship()) {
            return;
        }

        if (!field.isNullable()) {
            builder.append("    @NotNull\n");
        }

        String rawType = safeType(field.getType());

        if (containsType(rawType, "LocalDateTime")) {
            builder.append("    @JsonFormat(pattern = \"yyyy-MM-dd HH:mm:ss\")\n");
        } else if (containsType(rawType, "LocalDate")) {
            builder.append("    @JsonFormat(pattern = \"yyyy-MM-dd\")\n");
        } else if (containsType(rawType, "LocalTime")) {
            builder.append("    @JsonFormat(pattern = \"HH:mm:ss\")\n");
        }

        if (field.getLength() != null && isStringType(rawType)) {
            builder.append("    @Size(max = ").append(field.getLength()).append(")\n");
        }

        if (isPositiveCandidate(field, rawType)) {
            builder.append("    @Positive\n");
        }

        if (isPatternCandidate(field, rawType)) {
            builder.append("    @Pattern(regexp = \".*\")\n");
        }
    }

    private static boolean isStringType(String rawType) {
        String t = safeType(rawType);
        return "String".equals(t) || "java.lang.String".equals(t);
    }

    private void addClassDefinition(StringBuilder builder, Entity entity) {
        builder.append("/**\n");
        builder.append(" * Data transfer object for ").append(entity.getName()).append(".\n");
        builder.append(" */\n");
        builder.append("@Data\n");
        builder.append("@Builder\n");
        builder.append("@NoArgsConstructor\n");
        builder.append("@AllArgsConstructor\n");
        builder.append("@JsonInclude(JsonInclude.Include.NON_NULL)\n");
        builder.append("public class ").append(entity.getName()).append("Dto {\n\n");

        // Primitive / non-relationship fields
        for (Field field : entity.getFields()) {
            if (!field.isRelationship()) {
                addFieldAnnotations(builder, field);

                String rawType = safeType(field.getType());
                String simpleType = simplifyType(rawType);

                // Special case: EmbeddedId object on entity DTO should point to PK DTO
                if (isCompositeKeyObjectField(field, simpleType)) {
                    simpleType = resolveRelationshipDtoType(simpleType); // -> XxxPKDto / XxxKeyDto
                }

                builder.append("    private ")
                        .append(simpleType)
                        .append(" ")
                        .append(field.getName())
                        .append(";\n\n");
            }
        }

        // Relationship fields
        for (Field field : entity.getFields()) {
            if (field.isRelationship()) {
                String rawType = safeType(field.getType());

                if (rawType.startsWith("List<") || rawType.startsWith("java.util.List<")) {
                    String inner = extractGenericInner(rawType);
                    String relatedType = simplifyType(inner);

                    builder.append("    private List<")
                            .append(resolveRelationshipDtoType(relatedType))
                            .append("> ")
                            .append(field.getName())
                            .append(";\n\n");
                } else {
                    String relatedType = simplifyType(rawType);

                    builder.append("    private ")
                            .append(resolveRelationshipDtoType(relatedType))
                            .append(" ")
                            .append(field.getName())
                            .append(";\n\n");
                }
            }
        }

        builder.append("}\n");
    }

    private static boolean isCompositeKeyObjectField(Field field, String simpleType) {
        if (field == null) {
            return false;
        }

        String fieldName = safeType(field.getName());
        String type = safeType(simpleType);

        if (!"id".equals(fieldName)) {
            return false;
        }

        if (isScalarType(type)) {
            return false;
        }

        return type.endsWith("PK") || type.endsWith("Key");
    }

    private static String resolveRelationshipDtoType(String relatedSimpleType) {
        String t = safeType(relatedSimpleType);
        if (t.isEmpty()) return "Object";

        if (isScalarType(t)) {
            return t;
        }

        if (t.endsWith("Dto")) {
            return t;
        }

        return t + "Dto";
    }

    private static boolean isScalarType(String simpleType) {
        String t = safeType(simpleType);

        return t.equals("String")
                || t.equals("Long") || t.equals("Integer") || t.equals("Boolean")
                || t.equals("Double") || t.equals("Float") || t.equals("Short")
                || t.equals("Byte") || t.equals("Character")
                || t.equals("long") || t.equals("int") || t.equals("boolean")
                || t.equals("double") || t.equals("float") || t.equals("short")
                || t.equals("byte") || t.equals("char")
                || t.equals("UUID")
                || t.equals("BigDecimal")
                || t.equals("LocalDate")
                || t.equals("LocalTime")
                || t.equals("LocalDateTime");
    }

    private void writeDTOToFile(String content, Path dtoDir, String entityName) {
        Path outputPath = dtoDir.resolve(entityName + "Dto.java");

        try {
            Files.createDirectories(dtoDir);
            Files.writeString(outputPath, content, StandardCharsets.UTF_8);
            log.info("✅ DTO file generated: {}", outputPath.toAbsolutePath());
        } catch (IOException e) {
            log.error("❌ Failed to write DTO file for entity: {}", entityName, e);
        }
    }

    private static String safeType(String s) {
        return s == null ? "" : s.trim();
    }

    private static String simplifyType(String rawType) {
        String t = safeType(rawType);
        if (t.isEmpty()) return "Object";

        int lt = t.indexOf('<');
        int gt = t.lastIndexOf('>');
        if (lt > 0 && gt > lt) {
            String outer = t.substring(0, lt).trim();
            String inner = t.substring(lt + 1, gt).trim();
            String outerSimple = simplifyNonGenericType(outer);
            String innerSimple = simplifyType(inner);
            return outerSimple + "<" + innerSimple + ">";
        }

        return simplifyNonGenericType(t);
    }

    private static String simplifyNonGenericType(String t) {
        if (t.startsWith("java.lang.")) {
            return t.substring("java.lang.".length());
        }
        if (t.contains(".")) {
            return t.substring(t.lastIndexOf('.') + 1);
        }
        return t;
    }

    private static String extractGenericInner(String rawType) {
        String t = safeType(rawType);
        int lt = t.indexOf('<');
        int gt = t.lastIndexOf('>');
        if (lt > 0 && gt > lt) {
            return t.substring(lt + 1, gt).trim();
        }
        return "Object";
    }

    private static final Set<String> JAVA_KEYWORDS = Set.of(
            "abstract","assert","boolean","break","byte","case","catch","char","class","const","continue",
            "default","do","double","else","enum","extends","final","finally","float","for","goto","if",
            "implements","import","instanceof","int","interface","long","native","new","package","private",
            "protected","public","return","short","static","strictfp","super","switch","synchronized","this",
            "throw","throws","transient","try","void","volatile","while","true","false","null","record","sealed","permits"
    );

    private static String sanitizeJavaFieldName(String rawName) {
        String name = rawName == null ? "" : rawName.trim();

        // Remove SQL identifier quotes if present: "version"
        if (name.length() >= 2 && name.startsWith("\"") && name.endsWith("\"")) {
            name = name.substring(1, name.length() - 1);
        }

        // Fallback
        if (name.isBlank()) {
            return "field";
        }

        // Replace invalid chars with underscore
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < name.length(); i++) {
            char ch = name.charAt(i);

            if (i == 0) {
                if (Character.isJavaIdentifierStart(ch)) {
                    sb.append(ch);
                } else if (Character.isJavaIdentifierPart(ch)) {
                    sb.append('_').append(ch);
                } else {
                    sb.append('_');
                }
            } else {
                sb.append(Character.isJavaIdentifierPart(ch) ? ch : '_');
            }
        }

        String result = sb.toString();

        // If starts with digit after cleanup (extra safety)
        if (!result.isEmpty() && Character.isDigit(result.charAt(0))) {
            result = "field" + result;
        }

        // Optional camelCase normalization for snake_case names
        result = toCamelCasePreservingSimple(result);

        // Avoid Java keywords
        if (JAVA_KEYWORDS.contains(result)) {
            result = result + "Field";
        }

        return result;
    }

    private static String toCamelCasePreservingSimple(String value) {
        if (value == null || value.isBlank()) return "field";

        if (!value.contains("_")) {
            return value;
        }

        String[] parts = value.split("_+");
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < parts.length; i++) {
            String p = parts[i];
            if (p.isBlank()) continue;

            if (i == 0) {
                sb.append(p.substring(0, 1).toLowerCase()).append(p.substring(1));
            } else {
                sb.append(p.substring(0, 1).toUpperCase()).append(p.substring(1));
            }
        }

        return sb.isEmpty() ? "field" : sb.toString();
    }

    private static boolean isNumericType(String rawType) {
        String t = simplifyType(safeType(rawType));
        return t.equals("BigDecimal")
                || t.equals("Long") || t.equals("Integer") || t.equals("Short") || t.equals("Byte")
                || t.equals("Double") || t.equals("Float")
                || t.equals("long") || t.equals("int") || t.equals("short") || t.equals("byte")
                || t.equals("double") || t.equals("float");
    }
}
