package com.sqldomaingen.parseroutputfiles;

import com.sqldomaingen.model.Entity;
import com.sqldomaingen.model.Field;
import lombok.extern.log4j.Log4j2;

import java.io.IOException;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Log4j2
public class EntityParser {

    /**
     * Scans a directory for generated Java entity files and parses them into {@link Entity} objects.
     *
     * @param entitiesDir directory path that contains "*.java" entity files
     * @return list of parsed entities (empty list if none found or if an IO error occurs)
     */
    public List<Entity> parseEntities(String entitiesDir) {
        log.info("Starting entity directory scan: {}", entitiesDir);

        List<Entity> entityList = new ArrayList<>();

        try (DirectoryStream<Path> stream = Files.newDirectoryStream(Paths.get(entitiesDir), "*.java")) {
            for (Path entry : stream) {
                log.info("Reading entity file: {}", entry.getFileName());

                Entity entity = parseEntityFile(entry);
                if (entity != null) {
                    entityList.add(entity);
                    log.info("Entity parsed and added: {}", entity.getName());
                } else {
                    log.warn("No entity produced from file: {}", entry.getFileName());
                }
            }
        } catch (IOException e) {
            log.error("Failed to read directory: {}", entitiesDir, e);
        }

        log.info("Entity scan finished. Total parsed entities: {}", entityList.size());
        return entityList;
    }

    /**
     * Parses a single Java file and attempts to extract an {@link Entity} representation.
     *
     * @param entityFilePath path to the *.java file
     * @return parsed entity or null if parsing fails
     */
    private Entity parseEntityFile(Path entityFilePath) {
        log.debug("Parsing entity file: {}", entityFilePath);

        try {
            List<String> lines = Files.readAllLines(entityFilePath);
            Entity entity = extractEntityInfo(lines);

            if (entity != null) {
                log.info("Entity extracted successfully: {} -> {}", entityFilePath.getFileName(), entity.getName());
            } else {
                log.warn("Failed to extract entity from file: {}", entityFilePath.getFileName());
            }

            return entity;
        } catch (IOException e) {
            log.error("Failed to read file: {}", entityFilePath, e);
            return null;
        }
    }

    /**
     * Extracts entity information from raw Java source lines.
     * <p>
     * Current parsing approach:
     * <ul>
     *   <li>Detects class name via "public class X"</li>
     *   <li>Collects field-level annotations (lines starting with '@')</li>
     *   <li>Detects fields via lines starting with "private "</li>
     *   <li>Applies collected annotations to the field, then clears annotation buffer</li>
     * </ul>
     *
     * @param lines source file lines
     * @return entity model or null if class name cannot be detected
     */
    private Entity extractEntityInfo(List<String> lines) {
        log.debug("Extracting entity info from file content...");

        String className = null;
        List<Field> fields = new ArrayList<>();
        List<String> currentAnnotations = new ArrayList<>();

        for (String line : lines) {
            line = line.trim();

            if (className == null) {
                className = extractClassName(line);
            }

            if (line.startsWith("@")) {
                currentAnnotations.add(line);
                continue;
            }

            if (line.startsWith("private ")) {
                Field field = extractField(line);
                log.debug("Detected field: '{}' (type='{}')", field.getName(), field.getType());

                processAnnotations(currentAnnotations, field);
                fields.add(field);

                log.info("Field parsed -> name={}, type={}, pk={}, fk={}, nullable={}, unique={}, length={}, columnName={}, referencedEntity={}, referencedColumn={}",
                        field.getName(), field.getType(), field.isPrimaryKey(), field.isForeignKey(), field.isNullable(), field.isUnique(),
                        field.getLength(), field.getColumnName(), field.getReferencedEntity(), field.getReferencedColumn());

                currentAnnotations.clear();
            }
        }

        if (className == null) {
            log.warn("No class declaration found in file.");
            return null;
        }

        log.info("Entity '{}' extracted with {} fields.", className, fields.size());
        return new Entity(className, fields);
    }

    /**
     * Extracts a Java class name from a line like: "public class Department {"
     *
     * @param line source line
     * @return class name or null if no match
     */
    private String extractClassName(String line) {
        Pattern classPattern = Pattern.compile("public class (\\w+)");
        Matcher matcher = classPattern.matcher(line);
        if (matcher.find()) {
            String className = matcher.group(1);
            log.debug("Class detected: {}", className);
            return className;
        }
        return null;
    }

    /**
     * Extracts a field from a line like: "private String name;"
     *
     * @param line field declaration line
     * @return field model with name/type set
     */
    private Field extractField(String line) {
        String[] parts = line.split("\\s+");
        String type = parts[1];
        String name = parts[2].replace(";", "");

        Field field = new Field();
        field.setName(name);
        field.setType(type);

        log.debug("Field created -> name='{}', type='{}'", name, type);
        return field;
    }

    /**
     * Applies collected annotations to the provided {@link Field}.
     *
     * @param annotations annotation lines collected right before the field
     * @param field       target field model
     */
    private void processAnnotations(List<String> annotations, Field field) {
        log.debug("Applying annotations to field '{}' -> {}", field.getName(), annotations);

        for (String annotation : annotations) {
            if (annotation.contains("@Id")) {
                field.setPrimaryKey(true);
            } else if (isRelationshipAnnotation(annotation)) {
                processRelationshipAnnotation(field);
            } else if (annotation.startsWith("@Column")) {
                processColumnAnnotation(annotation, field);
            } else if (annotation.startsWith("@JoinColumn")) {
                processJoinColumnAnnotation(annotation, field);
            }
        }
    }

    /**
     * Marks the field as a relationship and records the referenced entity type.
     * <p>
     * Note: This parser treats any relationship annotation as "foreign key" in the output model,
     * which is sufficient for validation checks but does not represent the full JPA semantics.
     */
    private void processRelationshipAnnotation(Field field) {
        field.setForeignKey(true);
        field.setReferencedEntity(field.getType());
        log.debug("Relationship detected -> field='{}' references entity='{}'", field.getName(), field.getReferencedEntity());
    }

    /**
     * Checks whether the annotation line represents a JPA relationship annotation.
     */
    private boolean isRelationshipAnnotation(String annotation) {
        return annotation.contains("@ManyToOne") || annotation.contains("@OneToMany")
                || annotation.contains("@OneToOne") || annotation.contains("@ManyToMany");
    }

    /**
     * Parses basic @Column attributes and copies them into the {@link Field} model.
     * Supported:
     * <ul>
     *   <li>nullable</li>
     *   <li>unique</li>
     *   <li>length</li>
     *   <li>name</li>
     * </ul>
     */
    private void processColumnAnnotation(String annotation, Field field) {
        log.debug("Processing @Column for field '{}': {}", field.getName(), annotation);

        if (annotation.contains("nullable = false")) {
            field.setNullable(false);
        }
        if (annotation.contains("unique = true")) {
            field.setUnique(true);
        }
        if (annotation.contains("length = ")) {
            int start = annotation.indexOf("length = ") + 9;
            int end = annotation.indexOf(",", start);
            if (end == -1) end = annotation.indexOf(")", start);
            if (start > 0 && end > 0) {
                String lengthValue = annotation.substring(start, end).trim();
                field.setLength(Integer.parseInt(lengthValue));
            }
        }
        if (annotation.contains("name = ")) {
            String columnName = extractValueFromAnnotation(annotation, "name");
            field.setColumnName(columnName);
        }
    }

    /**
     * Parses @JoinColumn attributes and copies them into the {@link Field} model.
     * Supported:
     * <ul>
     *   <li>name</li>
     *   <li>referencedColumnName</li>
     * </ul>
     */
    private void processJoinColumnAnnotation(String annotation, Field field) {
        log.debug("Processing @JoinColumn for field '{}': {}", field.getName(), annotation);

        if (annotation.contains("referencedColumnName = ")) {
            String referencedColumn = extractValueFromAnnotation(annotation, "referencedColumnName");
            field.setReferencedColumn(referencedColumn);
        }
        if (annotation.contains("name = ")) {
            String columnName = extractValueFromAnnotation(annotation, "name");
            field.setColumnName(columnName);
        }
    }

    /**
     * Extracts quoted values from annotations like: name = "my_col"
     *
     * @param annotation full annotation line
     * @param key        attribute key (e.g. name, referencedColumnName)
     * @return extracted value or null
     */
    private String extractValueFromAnnotation(String annotation, String key) {
        int start = annotation.indexOf(key + " = ") + key.length() + 3;
        int end = annotation.indexOf("\"", start + 1);
        if (start > 0 && end > 0) {
            return annotation.substring(start, end);
        }
        return null;
    }

    /**
     * Public helper to parse a single entity file (useful for tests).
     */
    public Entity parseEntityFromFile(Path entityFilePath) {
        log.info("Parsing single entity file: {}", entityFilePath);
        return parseEntityFile(entityFilePath);
    }
}
