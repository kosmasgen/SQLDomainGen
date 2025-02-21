package com.sqldomaingen.parseroutputfiles;

import com.sqldomaingen.model.Entity;
import com.sqldomaingen.model.Field;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EntityParser {
    private static final Logger logger = LoggerFactory.getLogger(EntityParser.class);

    public List<Entity> parseEntities(String entitiesDir) {
        logger.info("🔍 Ξεκινάει η σάρωση του φακέλου: {}", entitiesDir);
        List<Entity> entityList = new ArrayList<>();

        try (DirectoryStream<Path> stream = Files.newDirectoryStream(Paths.get(entitiesDir), "*.java")) {
            for (Path entry : stream) {
                logger.info("📄 Διαβάζεται το αρχείο entity: {}", entry.getFileName());
                Entity entity = parseEntityFile(entry);
                if (entity != null) {
                    entityList.add(entity);
                    logger.info("✅ Προστέθηκε η οντότητα: {}", entity.getName());
                } else {
                    logger.warn("⚠️ Δεν δημιουργήθηκε οντότητα από το αρχείο: {}", entry.getFileName());
                }
            }
        } catch (IOException e) {
            logger.error("❌ Σφάλμα κατά την ανάγνωση του φακέλου: {}", entitiesDir, e);
        }

        logger.info("🏁 Ολοκληρώθηκε η ανάγνωση των οντοτήτων. Σύνολο: {}", entityList.size());
        return entityList;
    }

    private Entity parseEntityFile(Path entityFilePath) {
        logger.debug("📄 Ξεκινάει η ανάγνωση του αρχείου: {}", entityFilePath);
        try {
            List<String> lines = Files.readAllLines(entityFilePath);
            Entity entity = extractEntityInfo(lines);
            if (entity != null) {
                logger.info("✅ Επιτυχής εξαγωγή οντότητας από το αρχείο: {} -> {}", entityFilePath.getFileName(), entity.getName());
            } else {
                logger.warn("⚠️ Αποτυχία εξαγωγής οντότητας από το αρχείο: {}", entityFilePath.getFileName());
            }
            return entity;
        } catch (IOException e) {
            logger.error("❌ Σφάλμα ανάγνωσης αρχείου: {}", entityFilePath, e);
            return null;
        }
    }

    private Entity extractEntityInfo(List<String> lines) {
        logger.debug("🔍 Ξεκινά η επεξεργασία γραμμών για εξαγωγή οντότητας...");
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
                logger.debug("🆕 Εντοπίστηκε πεδίο: {} τύπου: {}", field.getName(), field.getType());

                processAnnotations(currentAnnotations, field);
                fields.add(field);

                logger.info("🔍 Πεδίο -> Όνομα: {}, Τύπος: {}, PrimaryKey: {}, ForeignKey: {}, Nullable: {}, Unique: {}, Length: {}, ColumnName: {}, ReferencedEntity: {}, ReferencedColumn: {}",
                        field.getName(), field.getType(), field.isPrimaryKey(), field.isForeignKey(), field.isNullable(), field.isUnique(),
                        field.getLength(), field.getColumnName(), field.getReferencedEntity(), field.getReferencedColumn());

                currentAnnotations.clear();
            }
        }

        if (className == null) {
            logger.warn("⚠️ Δεν βρέθηκε κλάση στην οντότητα!");
            return null;
        }

        logger.info("✅ Η οντότητα '{}' εξάχθηκε με {} πεδία.", className, fields.size());
        return new Entity(className, fields);
    }

    private String extractClassName(String line) {
        Pattern classPattern = Pattern.compile("public class (\\w+)");
        Matcher matcher = classPattern.matcher(line);
        if (matcher.find()) {
            String className = matcher.group(1);
            logger.debug("🔹 Εντοπίστηκε κλάση: {}", className);
            return className;
        }
        return null;
    }

    private Field extractField(String line) {
        String[] parts = line.split("\\s+");
        String type = parts[1];
        String name = parts[2].replace(";", "");

        Field field = new Field();
        field.setName(name);
        field.setType(type);

        logger.debug("🏷️ Δημιουργήθηκε πεδίο -> Όνομα: {}, Τύπος: {}", name, type);
        return field;
    }

    private void processAnnotations(List<String> annotations, Field field) {
        logger.debug("📜 Επεξεργασία annotations για το πεδίο: {} -> {}", field.getName(), annotations);

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

    private void processRelationshipAnnotation(Field field) {
        field.setForeignKey(true);
        field.setReferencedEntity(field.getType());
        logger.debug("🔗 Foreign Key -> {} -> {}", field.getName(), field.getReferencedEntity());
    }

    private boolean isRelationshipAnnotation(String annotation) {
        return annotation.contains("@ManyToOne") || annotation.contains("@OneToMany")
                || annotation.contains("@OneToOne") || annotation.contains("@ManyToMany");
    }

    private void processColumnAnnotation(String annotation, Field field) {
        logger.debug("🏷️ Επεξεργασία @Column για το πεδίο: {}", field.getName());

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

    private void processJoinColumnAnnotation(String annotation, Field field) {
        logger.debug("🔗 Επεξεργασία @JoinColumn για το πεδίο: {}", field.getName());

        if (annotation.contains("referencedColumnName = ")) {
            String referencedColumn = extractValueFromAnnotation(annotation, "referencedColumnName");
            field.setReferencedColumn(referencedColumn);
        }
        if (annotation.contains("name = ")) {
            String columnName = extractValueFromAnnotation(annotation, "name");
            field.setColumnName(columnName);
        }
    }

    private String extractValueFromAnnotation(String annotation, String key) {
        int start = annotation.indexOf(key + " = ") + key.length() + 3;
        int end = annotation.indexOf("\"", start + 1);
        if (start > 0 && end > 0) {
            return annotation.substring(start, end);
        }
        return null;
    }

    public Entity parseEntityFromFile(Path entityFilePath) {
        logger.info("📄 Έναρξη parsing για μεμονωμένο αρχείο: {}", entityFilePath);
        return parseEntityFile(entityFilePath);
    }
}
