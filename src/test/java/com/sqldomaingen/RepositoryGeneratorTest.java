package com.sqldomaingen;

import com.sqldomaingen.generator.RepositoryGenerator;
import com.sqldomaingen.model.Column;
import com.sqldomaingen.model.Table;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Map;
import java.util.List;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class RepositoryGeneratorTest {

    private static final Logger logger = LoggerFactory.getLogger(RepositoryGeneratorTest.class);
    private RepositoryGenerator generator;
    private static final String OUTPUT_DIR = "output/repository";

    @BeforeEach
    void setUp() throws IOException {
        logger.info("Ξεκινάει η αρχικοποίηση του τεστ...");
        generator = new RepositoryGenerator();

        // Δημιουργία φακέλου αν δεν υπάρχει
        Files.createDirectories(Paths.get(OUTPUT_DIR));

        logger.info("Η αρχικοποίηση του τεστ ολοκληρώθηκε.");
    }

    private Table createTable(String name, String pkColumn, String javaType) {
        Table table = new Table();
        table.setName(name);

        Column primaryKeyColumn = new Column();
        primaryKeyColumn.setName(pkColumn);
        primaryKeyColumn.setJavaType(javaType);
        primaryKeyColumn.setPrimaryKey(true);

        table.setColumns(List.of(primaryKeyColumn));
        return table;
    }

    @Test
    void testGenerateRepositoriesAndWriteToFile() throws IOException {
        logger.info("Εκτελείται το τεστ: testGenerateRepositoriesAndWriteToFile");

        // Δημιουργία πινάκων με διαφορετικούς primary key τύπους
        Table courseTable = createTable("Course", "id", "Long");
        Table studentTable = createTable("Student", "id", "String");
        Table professorTable = createTable("Professor", "id", "UUID");
        Table enrollmentTable = createTable("Enrollment", "id", "Long");

        // Δημιουργία map με όλους τους πίνακες
        Map<String, Table> tablesMap = new HashMap<>();
        tablesMap.put("Course", courseTable);
        tablesMap.put("Student", studentTable);
        tablesMap.put("Professor", professorTable);
        tablesMap.put("Enrollment", enrollmentTable);

        // Γεννήτρια Repository
        for (Table table : tablesMap.values()) {
            String repositoryContent = generator.generateRepositoryForTable(table, tablesMap);
            Path filePath = Paths.get(OUTPUT_DIR, table.getName() + "Repository.java");

            // Εγγραφή του repository στο αρχείο
            Files.writeString(filePath, repositoryContent, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);

            // Έλεγχος αν το αρχείο δημιουργήθηκε σωστά
            assertTrue(Files.exists(filePath), "Το αρχείο " + filePath + " δεν δημιουργήθηκε!");
            logger.info("Το Repository δημιουργήθηκε: {}", filePath);
        }

        logger.info("Το τεστ ολοκληρώθηκε επιτυχώς!");
    }
}
