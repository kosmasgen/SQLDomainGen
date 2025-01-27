package com.sqldomaingen.generator;
import com.sqldomaingen.model.Relationship;
import com.sqldomaingen.model.Table;
import com.sqldomaingen.util.NamingConverter;
import lombok.NoArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import com.sqldomaingen.model.Column;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

@NoArgsConstructor
@Component
public class EntityGenerator {

    private static final Logger logger = LoggerFactory.getLogger(EntityGenerator.class);

    /**
     * Δημιουργεί Java entities από πίνακες.
     *
     * @param tables      Λίστα με πίνακες για μετατροπή σε entities.
     * @param outputDir   Διαδρομή φακέλου για τα αρχεία εξόδου.
     * @param packageName Το όνομα του package.
     * @param overwrite   Αν θα αντικατασταθούν υπάρχοντα αρχεία.
     * @param useBuilder  Αν θα προστεθεί builder pattern.
     */
    public void generate(List<Table> tables, String outputDir, String packageName, boolean overwrite, boolean useBuilder) {
        logger.info("Starting entity generation...");

        logger.debug("Parameters - outputDir: {}, packageName: {}, overwrite: {}, useBuilder: {}",
                outputDir, packageName, overwrite, useBuilder);

        for (Table table : tables) {
            logger.debug("Processing table: {}", table.getName());

            // Δημιουργία περιεχομένου entity
            String entityContent = createEntityContent(table, packageName, useBuilder);
            logger.debug("Generated entity content for table '{}':\n{}", table.getName(), entityContent);

            // Ορισμός μονοπατιού εξόδου
            Path outputPath = Paths.get(outputDir, NamingConverter.toPascalCase(table.getName()) + ".java");
            String fileName = outputPath.toString();

            // Έλεγχος αν το αρχείο υπάρχει και δεν πρέπει να αντικατασταθεί
            if (!overwrite && outputPath.toFile().exists()) {
                logger.warn("File already exists, skipping: {}", fileName);
                continue;
            }

            // Εγγραφή στο αρχείο με χειρισμό εξαίρεσης
            try {
                writeToFile(fileName, entityContent);
                logger.info("Generated entity for table: {}", table.getName());
            } catch (IOException e) {
                logger.error("Failed to write entity file for table: {}", table.getName(), e);
            }
        }

        logger.info("Entity generation complete. Output directory: {}", outputDir);
    }

    /**
     * Δημιουργεί το περιεχόμενο του Java entity για έναν πίνακα.
     *
     * @param table       Το αντικείμενο Table που αναπαριστά τον πίνακα.
     * @param packageName Το όνομα του package.
     * @param useBuilder  Αν θα προστεθεί builder pattern.
     * @return Το περιεχόμενο του Java entity ως String.
     */
    public String createEntityContent(Table table, String packageName, boolean useBuilder) {
        StringBuilder entityBuilder = new StringBuilder();

        // 1. Δημιουργία package και imports
        generatePackageAndImports(entityBuilder, packageName);

        // 2. Προσθήκη annotations για την κλάση
        generateClassAnnotations(entityBuilder, table, useBuilder);

        // 3. Προσθήκη πεδίων
        generateFields(entityBuilder, table);

        // 4. Κλείσιμο κλάσης
        entityBuilder.append("}\n");

        logger.debug("Generated entity content for table '{}':\n{}", table.getName(), entityBuilder);
        return entityBuilder.toString();
    }

    public void generatePackageAndImports(StringBuilder builder, String packageName) {
        builder.append("package ").append(packageName).append(";\n\n");
        builder.append("import jakarta.persistence.*;\n");
        builder.append("import lombok.*;\n\n");
    }

    public void generateClassAnnotations(StringBuilder builder, Table table, boolean useBuilder) {
        builder.append("@Entity\n");
        builder.append("@Table(name = \"").append(table.getName()).append("\")\n");
        builder.append("@Getter\n@Setter\n");
        if (useBuilder) {
            builder.append("@Builder\n");
        }
        builder.append("@NoArgsConstructor\n@AllArgsConstructor\n");
        builder.append("public class ").append(NamingConverter.toPascalCase(table.getName())).append(" {\n\n");
    }

    public void generateFields(StringBuilder builder, Table table) {
        RelationshipResolver resolver = new RelationshipResolver(); // Instance για τη διαχείριση σχέσεων

        table.getColumns().forEach(column -> {
            logger.debug("Processing column: {}", column.getName());

            // Επεξεργασία Primary Key
            if (column.isPrimaryKey()) {
                addPrimaryKeyAnnotations(builder); // Προσθήκη Primary Key annotations
            }

            // Επεξεργασία Foreign Key
            if (column.isForeignKey()) {
                Relationship relationship = resolver.createRelationship(column, table, Map.of()); // Διασφάλιση συμβατότητας
                if (relationship != null) {
                    addForeignKeyAnnotations(builder, relationship); // Προσθήκη Foreign Key annotations
                }
            }

            // Επεξεργασία @Column και πεδίου
            addColumnField(builder, column); // Δημιουργία πεδίου με βάση τη στήλη
        });
    }



    private void addPrimaryKeyAnnotations(StringBuilder builder) {
        builder.append("    @Id\n");
        builder.append("    @GeneratedValue(strategy = GenerationType.IDENTITY)\n");
    }

    private void addForeignKeyAnnotations(StringBuilder builder, Relationship relationship) {
        switch (relationship.getRelationshipType()) {
            case MANYTOMANY:
                builder.append("    @ManyToMany\n");
                builder.append("    @JoinTable(name = \"").append(relationship.getJoinTableName())
                        .append("\", joinColumns = @JoinColumn(name = \"").append(relationship.getSourceColumn())
                        .append("\"), inverseJoinColumns = @JoinColumn(name = \"")
                        .append(relationship.getInverseJoinColumn()).append("\"))\n");
                break;

            case ONETOMANY:
                builder.append("    @OneToMany(mappedBy = \"").append(relationship.getSourceColumn()).append("\")\n");
                break;

            case ONETOONE:
                builder.append("    @OneToOne\n");
                builder.append("    @JoinColumn(name = \"").append(relationship.getSourceColumn())
                        .append("\", referencedColumnName = \"").append(relationship.getTargetColumn()).append("\")\n");
                break;

            case MANYTOONE:
            default:
                builder.append("    @ManyToOne\n");
                builder.append("    @JoinColumn(name = \"").append(relationship.getSourceColumn())
                        .append("\", referencedColumnName = \"").append(relationship.getTargetColumn()).append("\")\n");
                break;
        }
    }


    private void addColumnField(StringBuilder builder, Column column) {
        builder.append("    @Column(name = \"").append(column.getName()).append("\"");
        if (column.isUnique()) {
            builder.append(", unique = true");
        }
        builder.append(")\n");

        builder.append("    private ").append(column.getJavaType()).append(" ")
                .append(NamingConverter.toCamelCase(column.getName())).append(";\n\n");
    }


    /**
     * Γράφει το περιεχόμενο σε ένα αρχείο.
     *
     * @param filePath Το μονοπάτι του αρχείου.
     * @param content  Το περιεχόμενο προς αποθήκευση.
     */
    public void writeToFile(String filePath, String content) throws IOException {
        Path path = Paths.get(filePath);
        Files.createDirectories(path.getParent());
        Files.write(path, content.getBytes());
        logger.debug("File written successfully: {}", filePath);
    }
}
