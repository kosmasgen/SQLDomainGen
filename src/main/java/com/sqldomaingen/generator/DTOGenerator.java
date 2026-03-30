package com.sqldomaingen.generator;

import com.sqldomaingen.model.Entity;
import com.sqldomaingen.model.Field;
import com.sqldomaingen.util.PackageResolver;
import lombok.extern.log4j.Log4j2;
import com.sqldomaingen.util.GeneratorSupport;
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
     * @param entities entity models (non-null)
     * @param outputDir project root output directory (non-null)
     * @param basePackage base Java package (non-null)
     */
    public void generateDTOs(List<Entity> entities, String outputDir, String basePackage) {
        Objects.requireNonNull(entities, "entities must not be null");
        Objects.requireNonNull(outputDir, "outputDir must not be null");
        Objects.requireNonNull(basePackage, "basePackage must not be null");

        String dtoPackage = PackageResolver.resolvePackageName(basePackage, "dto");
        Path dtoDir = GeneratorSupport.ensureDirectory(
                PackageResolver.resolvePath(outputDir, basePackage, "dto")
        );

        log.info("Starting DTO generation...");

        for (Entity entity : entities) {
            log.info("Generating DTO for entity: {}", entity.getName());

            String dtoContent = createDTOContent(entity, dtoPackage);
            Path outputPath = dtoDir.resolve(entity.getName() + "Dto.java");
            GeneratorSupport.writeFile(outputPath, dtoContent);
        }

        log.info("DTO generation complete. Output directory: {}", dtoDir.toAbsolutePath());
    }

    /**
     * Creates the full DTO source code for the given entity.
     *
     * @param entity source entity metadata
     * @param dtoPackage target DTO package name
     * @return generated DTO source code
     */
    private String createDTOContent(Entity entity, String dtoPackage) {
        StringBuilder builder = new StringBuilder();

        addPackageAndImports(builder, dtoPackage, entity);
        addClassDefinition(builder, entity);

        return builder.toString();
    }

    /**
     * Adds the package declaration and required imports to the DTO source.
     *
     * @param builder target source builder
     * @param dtoPackage target DTO package name
     * @param entity source entity metadata
     */
    private void addPackageAndImports(StringBuilder builder, String dtoPackage, Entity entity) {
        builder.append("package ").append(dtoPackage).append(";\n\n");

        builder.append("import com.fasterxml.jackson.annotation.JsonInclude;\n");
        builder.append("import lombok.AllArgsConstructor;\n");
        builder.append("import lombok.Builder;\n");
        builder.append("import lombok.Data;\n");
        builder.append("import lombok.NoArgsConstructor;\n");

        Set<String> imports = collectConditionalImports(entity);
        imports.stream()
                .sorted()
                .forEach(importName -> builder.append("import ").append(importName).append(";\n"));

        builder.append("\n");
    }

    /**
     * Collects conditional imports required by the generated DTO fields.
     *
     * @param entity source entity metadata
     * @return set of required import class names
     */
    private Set<String> collectConditionalImports(Entity entity) {
        Set<String> imports = new TreeSet<>();
        List<Field> fields = entity.getFields() == null ? List.of() : entity.getFields();

        for (Field field : fields) {
            String rawType = GeneratorSupport.trimToEmpty(field.getType());

            if (needsListImport(field, rawType)) {
                imports.add("java.util.List");
            }

            if (needsSetImport(field, rawType)) {
                imports.add("java.util.Set");
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

            if (containsType(rawType, "BigInteger")) {
                imports.add("java.math.BigInteger");
            }

            if (containsType(rawType, "UUID")) {
                imports.add("java.util.UUID");
            }

            if (!field.isRelationship()
                    && field.getLength() != null
                    && field.getLength() > 0
                    && isStringType(rawType)) {
                imports.add("jakarta.validation.constraints.Size");
            }
        }

        return imports;
    }

    /**
     * Determines whether a Set import is required for the field.
     *
     * @param field source field metadata
     * @param rawType raw Java type
     * @return true when the DTO field requires Set import
     */
    private boolean needsSetImport(Field field, String rawType) {
        return field.isRelationship()
                && (rawType.startsWith("Set<") || rawType.startsWith("java.util.Set<"));
    }

    /**
     * Determines whether a List import is required for the field.
     *
     * @param field source field metadata
     * @param rawType raw Java type
     * @return true when the DTO field requires List import
     */
    private static boolean needsListImport(Field field, String rawType) {
        if (!field.isRelationship()) {
            return false;
        }

        String normalizedType = GeneratorSupport.trimToEmpty(rawType);
        return normalizedType.startsWith("List<")
                || normalizedType.startsWith("java.util.List<")
                || "List".equals(normalizedType)
                || "java.util.List".equals(normalizedType);
    }

    /**
     * Checks whether the raw type matches or contains the given simple type name.
     *
     * @param rawType raw Java type
     * @param typeSimpleName target simple type name
     * @return true when the type matches directly or appears inside a generic type
     */
    private static boolean containsType(String rawType, String typeSimpleName) {
        String normalizedType = GeneratorSupport.trimToEmpty(rawType);

        if (normalizedType.equals(typeSimpleName)) {
            return true;
        }

        if (normalizedType.endsWith("." + typeSimpleName)) {
            return true;
        }

        return normalizedType.contains("<") && normalizedType.contains(typeSimpleName);
    }

    /**
     * Adds field-level DTO annotations.
     * Only @JsonFormat and @Size are emitted.
     */
    private void addFieldAnnotations(StringBuilder builder, Field field) {
        if (field.isRelationship()) {
            return;
        }

        String rawType = GeneratorSupport.trimToEmpty(field.getType());

        if (containsType(rawType, "LocalDateTime")) {
            builder.append("    @JsonFormat(pattern = \"yyyy-MM-dd HH:mm:ss\")\n");
        } else if (containsType(rawType, "LocalDate")) {
            builder.append("    @JsonFormat(pattern = \"yyyy-MM-dd\")\n");
        } else if (containsType(rawType, "LocalTime")) {
            builder.append("    @JsonFormat(pattern = \"HH:mm:ss\")\n");
        }

        if (field.getLength() != null && field.getLength() > 0 && isStringType(rawType)) {
            builder.append("    @Size(max = ").append(field.getLength()).append(")\n");
        }
    }

    /**
     * Determines whether the raw type represents a String.
     *
     * @param rawType raw Java type
     * @return true when the type is String
     */
    private static boolean isStringType(String rawType) {
        String normalizedType = GeneratorSupport.trimToEmpty(rawType);
        return "String".equals(normalizedType) || "java.lang.String".equals(normalizedType);
    }


    /**
     * Adds the DTO class definition and fields to the source builder.
     *
     * @param builder target source builder
     * @param entity source entity metadata
     */
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

        List<Field> fields = entity.getFields() == null ? List.of() : entity.getFields();

        for (Field field : fields) {
            String fieldName = field.getName();

            if (!field.isRelationship()) {
                addFieldAnnotations(builder, field);

                String rawType = GeneratorSupport.trimToEmpty(field.getType());
                String simpleType = simplifyType(rawType);

                if (isCompositeKeyObjectField(field, simpleType)) {
                    simpleType = resolveRelationshipDtoType(simpleType);
                }

                builder.append("    private ")
                        .append(simpleType)
                        .append(" ")
                        .append(fieldName)
                        .append(";\n\n");
            }
        }

        for (Field field : fields) {
            String fieldName = field.getName();

            if (field.isRelationship()) {
                String rawType = GeneratorSupport.trimToEmpty(field.getType());

                if (rawType.startsWith("List<") || rawType.startsWith("java.util.List<")) {
                    String innerType = extractGenericInner(rawType);
                    String relatedType = simplifyType(innerType);

                    builder.append("    private List<")
                            .append(resolveRelationshipDtoType(relatedType))
                            .append("> ")
                            .append(fieldName)
                            .append(";\n\n");
                } else if (rawType.startsWith("Set<") || rawType.startsWith("java.util.Set<")) {
                    String innerType = extractGenericInner(rawType);
                    String relatedType = simplifyType(innerType);

                    builder.append("    private Set<")
                            .append(resolveRelationshipDtoType(relatedType))
                            .append("> ")
                            .append(fieldName)
                            .append(";\n\n");
                } else {
                    String relatedType = simplifyType(rawType);

                    builder.append("    private ")
                            .append(resolveRelationshipDtoType(relatedType))
                            .append(" ")
                            .append(fieldName)
                            .append(";\n\n");
                }
            }
        }

        builder.append("}\n");
    }

    /**
     * Determines whether the field represents a composite key object.
     *
     * @param field source field metadata
     * @param simpleType simplified field type
     * @return true when the field is a composite key object reference
     */
    private static boolean isCompositeKeyObjectField(Field field, String simpleType) {
        if (field == null) {
            return false;
        }

        String fieldName = GeneratorSupport.trimToEmpty(field.getName());
        String type = GeneratorSupport.trimToEmpty(simpleType);

        if (!"id".equalsIgnoreCase(fieldName)) {
            return false;
        }

        if (isScalarType(type)) {
            return false;
        }

        return type.endsWith("PK") || type.endsWith("Key");
    }

    /**
     * Resolves the DTO type name for a related field type.
     *
     * @param relatedSimpleType simplified related type name
     * @return DTO type name or scalar type as-is
     */
    private static String resolveRelationshipDtoType(String relatedSimpleType) {
        String normalizedType = GeneratorSupport.trimToEmpty(relatedSimpleType);
        if (normalizedType.isEmpty()) {
            return "Object";
        }

        if (isScalarType(normalizedType)) {
            return normalizedType;
        }

        if (normalizedType.endsWith("Dto")) {
            return normalizedType;
        }

        return normalizedType + "Dto";
    }

    /**
     * Determines whether the given type is treated as a scalar DTO field type.
     *
     * @param simpleType simplified type name
     * @return true when the type is scalar
     */
    private static boolean isScalarType(String simpleType) {
        String normalizedType = GeneratorSupport.trimToEmpty(simpleType);

        return normalizedType.equals("String")
                || normalizedType.equals("Long")
                || normalizedType.equals("Integer")
                || normalizedType.equals("Boolean")
                || normalizedType.equals("Double")
                || normalizedType.equals("Float")
                || normalizedType.equals("Short")
                || normalizedType.equals("Byte")
                || normalizedType.equals("Character")
                || normalizedType.equals("long")
                || normalizedType.equals("int")
                || normalizedType.equals("boolean")
                || normalizedType.equals("double")
                || normalizedType.equals("float")
                || normalizedType.equals("short")
                || normalizedType.equals("byte")
                || normalizedType.equals("char")
                || normalizedType.equals("UUID")
                || normalizedType.equals("BigDecimal")
                || normalizedType.equals("BigInteger")
                || normalizedType.equals("LocalDate")
                || normalizedType.equals("LocalTime")
                || normalizedType.equals("LocalDateTime");
    }


    /**
     * Simplifies a raw Java type by removing package names while preserving generic structure.
     *
     * @param rawType raw Java type
     * @return simplified Java type
     */
    private static String simplifyType(String rawType) {
        String normalizedType = GeneratorSupport.trimToEmpty(rawType);
        if (normalizedType.isEmpty()) {
            return "Object";
        }

        int leftBracketIndex = normalizedType.indexOf('<');
        int rightBracketIndex = normalizedType.lastIndexOf('>');

        if (leftBracketIndex > 0 && rightBracketIndex > leftBracketIndex) {
            String outerType = normalizedType.substring(0, leftBracketIndex).trim();
            String innerType = normalizedType.substring(leftBracketIndex + 1, rightBracketIndex).trim();

            String simplifiedOuterType = simplifyNonGenericType(outerType);
            String simplifiedInnerType = simplifyType(innerType);

            return simplifiedOuterType + "<" + simplifiedInnerType + ">";
        }

        return simplifyNonGenericType(normalizedType);
    }

    /**
     * Simplifies a non-generic Java type by removing the package name.
     *
     * @param typeName raw non-generic type name
     * @return simplified type name
     */
    private static String simplifyNonGenericType(String typeName) {
        if (typeName.startsWith("java.lang.")) {
            return typeName.substring("java.lang.".length());
        }

        if (typeName.contains(".")) {
            return typeName.substring(typeName.lastIndexOf('.') + 1);
        }

        return typeName;
    }

    /**
     * Extracts the inner generic type from a raw generic declaration.
     *
     * @param rawType raw Java type
     * @return inner generic type, or Object when not present
     */
    private static String extractGenericInner(String rawType) {
        String normalizedType = GeneratorSupport.trimToEmpty(rawType);
        int leftBracketIndex = normalizedType.indexOf('<');
        int rightBracketIndex = normalizedType.lastIndexOf('>');

        if (leftBracketIndex > 0 && rightBracketIndex > leftBracketIndex) {
            return normalizedType.substring(leftBracketIndex + 1, rightBracketIndex).trim();
        }

        return "Object";
    }
}