package com.sqldomaingen.generator;

import com.sqldomaingen.model.Entity;
import com.sqldomaingen.model.Field;
import lombok.extern.log4j.Log4j2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Log4j2
public class DTOGenerator {

    public void generateDTOs(List<Entity> entities, String outputDir) {
        String packageName = "com.sqldomaingen.dto";

        log.info("Starting DTO generation...");

        for (Entity entity : entities) {
            log.info("Generating DTO for entity: {}", entity.getName());
            String dtoContent = createDTOContent(entity, packageName);
            writeDTOToFile(dtoContent, outputDir, entity.getName());
        }

        log.info("DTO generation complete. Output directory: {}", outputDir);
    }

    private String createDTOContent(Entity entity, String packageName) {
        StringBuilder builder = new StringBuilder();

        addImports(builder, packageName, entity);
        addClassDefinition(builder, entity);

        return builder.toString();
    }


    private void addImports(StringBuilder builder, String packageName, Entity entity) {
        builder.append("package ").append(packageName).append(";\n\n")
                .append("import lombok.Getter;\n")
                .append("import lombok.Setter;\n")
                .append("import lombok.NoArgsConstructor;\n\n")
                .append("import com.fasterxml.jackson.annotation.JsonInclude;\n");

        addConditionalImports(builder, entity);
        builder.append("\n");
    }


    private void addConditionalImports(StringBuilder builder, Entity entity) {
        Set<String> imports = new HashSet<>();

        for (Field field : entity.getFields()) {
            if ("LocalDateTime".equals(field.getType())) {
                imports.add("java.time.LocalDateTime");
                imports.add("com.fasterxml.jackson.annotation.JsonFormat");
            }
            if (!field.isNullable()) {
                imports.add("jakarta.validation.constraints.NotNull");
            }
            if (field.getLength() != null) {
                imports.add("jakarta.validation.constraints.Size");
            }
            if (isPositiveCandidate(field)) {
                imports.add("jakarta.validation.constraints.Positive");
            }
            if (isPatternCandidate(field)) {
                imports.add("jakarta.validation.constraints.Pattern");
            }

            // ✅ 1. Εισαγωγή List αν έχουμε σχέση List
            if (field.isRelationship() && field.getType().startsWith("List<")) {
                imports.add("java.util.List");
            }

            // ✅ 2. Import DTO της σχετιζόμενης οντότητας (Professor -> ProfessorDTO)
            if (field.isRelationship()) {
                String relatedEntityName;
                if (field.getType().startsWith("List<")) {
                    relatedEntityName = field.getType().substring(5, field.getType().length() - 1);
                } else {
                    relatedEntityName = field.getType();
                }
                imports.add("com.sqldomaingen.dto." + relatedEntityName + "DTO");
            }
        }

        for (String importStatement : imports) {
            builder.append("import ").append(importStatement).append(";\n");
        }
    }


    private boolean isPositiveCandidate(Field field) {
        String fieldName = field.getName().toLowerCase();
        return fieldName.contains("price") || fieldName.contains("amount") || fieldName.contains("quantity");
    }


    private boolean isPatternCandidate(Field field) {
        String fieldName = field.getName().toLowerCase();
        return fieldName.contains("email") || fieldName.contains("phone");
    }


    private void addFieldAnnotations(StringBuilder builder, Field field, String entityName) {
        if (field.isRelationship()) {
            return;
        }

        String validationPrefix = "validation." + entityName.toLowerCase() + "." + field.getName() + ".";

        if (!field.isNullable()) {
            builder.append("    @NotNull(message = \"{").append(validationPrefix).append("notnull}\")\n");
        }
        if ("LocalDateTime".equals(field.getType())) {
            builder.append("    @JsonFormat(pattern = \"yyyy-MM-dd HH:mm:ss\")\n");
        }
        if (field.getLength() != null) {
            builder.append("    @Size(max = ").append(field.getLength())
                    .append(", message = \"{").append(validationPrefix).append("size}\")\n");
        }
        if (isPositiveCandidate(field)) {
            builder.append("    @Positive(message = \"{").append(validationPrefix).append("positive}\")\n");
        }
        if (isPatternCandidate(field)) {
            builder.append("    @Pattern(regexp = \".*\", message = \"{").append(validationPrefix).append("pattern}\") // TODO: Βάλε σωστό regex\n");
        }
    }

    private void addClassDefinition(StringBuilder builder, Entity entity) {
        builder.append("@Getter\n@Setter\n@NoArgsConstructor\n@JsonInclude(JsonInclude.Include.NON_NULL)\n");
        builder.append("public class ").append(entity.getName()).append("DTO {\n\n");

        // Απλά πεδία (primitive fields)
        for (Field field : entity.getFields()) {
            if (!field.isRelationship()) {
                addFieldAnnotations(builder, field, entity.getName());
                builder.append("    private ").append(field.getType()).append(" ").append(field.getName()).append(";\n\n");
            }
        }

        // Σχέσεις (relationships)
        for (Field field : entity.getFields()) {
            if (field.isRelationship()) {
                // Εξάγουμε το όνομα της σχετιζόμενης οντότητας από το πεδίο
                String relatedEntityName;
                if (field.getType().startsWith("List<")) {
                    relatedEntityName = field.getType().substring(5, field.getType().length() - 1); // List<Student> -> Student
                    builder.append("    private List<").append(relatedEntityName).append("DTO> ").append(field.getName()).append(";\n\n");
                } else {
                    relatedEntityName = field.getType(); // Professor
                    builder.append("    private ").append(relatedEntityName).append("DTO ").append(field.getName()).append(";\n\n");
                }
            }
        }

        builder.append("}\n\n");
    }

    private void writeDTOToFile(String content, String outputDir, String entityName) {
        Path outputPath = Paths.get(outputDir, entityName + "DTO.java");
        try {
            Files.createDirectories(outputPath.getParent());
            Files.writeString(outputPath, content);
            log.info("DTO file generated: {}", outputPath);
        } catch (IOException e) {
            log.error("Failed to write DTO file for entity: {}", entityName, e);
        }
    }
}
