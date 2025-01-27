package com.sqldomaingen;

import com.sqldomaingen.model.Column;
import com.sqldomaingen.model.Table;
import com.sqldomaingen.generator.EntityGenerator;
import com.sqldomaingen.generator.RelationshipResolver;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collections;
import java.util.Map;

class EntityGeneratorTest {

    private static final Logger logger = LoggerFactory.getLogger(EntityGeneratorTest.class);

    private final EntityGenerator generator = new EntityGenerator();

    @Test
    void testGenerateEntityWithRelationships() {
        logger.info("Starting testGenerateEntityWithRelationships...");

        // Δημιουργία mock πίνακα `example_table`
        Table exampleTable = new Table();
        exampleTable.setName("example_table");
        logger.debug("Created table: {}", exampleTable.getName());

        // Δημιουργία στήλης Primary Key
        Column idColumn = new Column();
        idColumn.setName("id");
        idColumn.setPrimaryKey(true);
        idColumn.setType("BIGINT");
        idColumn.setJavaType("Long");
        exampleTable.addColumn(idColumn);
        logger.debug("Added primary key column: {}", idColumn);

        // Δημιουργία στήλης Foreign Key
        Column fkColumn = new Column();
        fkColumn.setName("user_id");
        fkColumn.setForeignKey(true);
        fkColumn.setReferencedColumn("id");
        fkColumn.setType("BIGINT");
        fkColumn.setJavaType("Long");
        exampleTable.addColumn(fkColumn);
        logger.debug("Added foreign key column: {}", fkColumn);

        // Δημιουργία mock πίνακα `user_table`
        Table userTable = new Table();
        userTable.setName("user_table");
        Column userIdColumn = new Column();
        userIdColumn.setName("id");
        userIdColumn.setPrimaryKey(true);
        userIdColumn.setType("BIGINT");
        userIdColumn.setJavaType("Long");
        userTable.addColumn(userIdColumn);
        logger.debug("Created related table: {}", userTable.getName());

        // Δημιουργία του allTables map
        Map<String, Table> allTables = Map.of(
                "user_table", userTable
        );

        // Επίλυση σχέσεων
        RelationshipResolver resolver = new RelationshipResolver();
        resolver.resolveRelationships(exampleTable, allTables);

        // Προσωρινός φάκελος εξόδου
        Path outputDir = null;
        try {
            outputDir = Files.createTempDirectory("entity-test-output");
            logger.info("Temporary output directory created at: {}", outputDir);
        } catch (IOException e) {
            logger.error("Failed to create temporary output directory.", e);
            Assertions.fail("Failed to create temporary output directory.");
        }

        String packageName = "com.example";

        // Γεννήτρια entity
        try {
            generator.generate(Collections.singletonList(exampleTable), outputDir.toString(), packageName, true, true);
            logger.info("Entity generation completed.");
        } catch (Exception e) {
            logger.error("Entity generation failed.", e);
            Assertions.fail("Entity generation failed.");
        }

        // Έλεγχος για το αρχείο που δημιουργήθηκε
        Path entityFile = outputDir.resolve("ExampleTable.java");
        Assertions.assertTrue(Files.exists(entityFile), "The entity file should exist");
        logger.info("Generated entity file exists: {}", entityFile);

        // Έλεγχος περιεχομένου αρχείου
        try {
            String content = Files.readString(entityFile);
            logger.info("Generated entity content:\n{}", content);

            Assertions.assertTrue(content.contains("@Entity"), "Content should include @Entity annotation");
            Assertions.assertTrue(content.contains("@Id"), "Content should include @Id annotation");
            Assertions.assertTrue(content.contains("@GeneratedValue(strategy = GenerationType.IDENTITY)"),
                    "Content should include @GeneratedValue annotation for the primary key");
            Assertions.assertTrue(content.contains("@ManyToOne"), "Content should include @ManyToOne annotation");
            Assertions.assertTrue(content.contains("@JoinColumn(name = \"user_id\", referencedColumnName = \"id\")"),
                    "Content should include @JoinColumn for FK");
        } catch (IOException e) {
            logger.error("Failed to read generated file.", e);
            Assertions.fail("Failed to read generated file.");
        }

        // Καθαρισμός προσωρινού φακέλου
        try {
            Files.deleteIfExists(entityFile);
            Files.deleteIfExists(outputDir);
            logger.info("Temporary output directory cleaned up.");
        } catch (IOException e) {
            logger.warn("Failed to clean up temporary directory.", e);
        }
    }

}
