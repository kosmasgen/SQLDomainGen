package com.sqldomaingen;

import com.sqldomaingen.generator.ControllerGenerator;
import com.sqldomaingen.model.Column;
import com.sqldomaingen.model.Table;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class ControllerGeneratorTest {

    private static final Logger logger = LoggerFactory.getLogger(ControllerGeneratorTest.class);
    private ControllerGenerator controllerGenerator;

    @TempDir
    Path tempDir;

    @BeforeEach
    void setUp() {
        controllerGenerator = new ControllerGenerator();
    }

    @Test
    void testGenerateControllers() throws IOException {
        // 🔹 Δημιουργία mock Tables
        Table courseTable = createTable("Course", "id", "Long");
        Table studentTable = createTable("Student", "id", "String");
        Table professorTable = createTable("Professor", "id", "UUID");
        Table enrollmentTable = createTable("Enrollment", "id", "Long");



        List<Table> tables = List.of(courseTable, studentTable, professorTable, enrollmentTable);

        // ✅ Εκτέλεση της generateControllers()
        controllerGenerator.generateControllers(tables);

        // 🔎 Έλεγχος αν τα αρχεία δημιουργήθηκαν
        for (Table table : tables) {
            Path controllerPath = tempDir.resolve("output/controllers/" + table.getName() + "Controller.java");
            assertTrue(Files.exists(controllerPath), "❌ Controller file missing: " + controllerPath);

            // ✅ Διαβάζουμε το περιεχόμενο του controller
            String content = Files.readAllLines(controllerPath).stream().collect(Collectors.joining("\n"));

            assertTrue(content.contains("@RestController"), "❌ Missing @RestController in " + table.getName() + "Controller");
            assertTrue(content.contains("@RequestMapping(\"/api/" + table.getName().toLowerCase() + "s\")"), "❌ Wrong @RequestMapping in " + table.getName() + "Controller");
            assertTrue(content.contains("@GetMapping"), "❌ Missing @GetMapping in " + table.getName() + "Controller");
            assertTrue(content.contains("@PostMapping"), "❌ Missing @PostMapping in " + table.getName() + "Controller");
            assertTrue(content.contains("@PutMapping"), "❌ Missing @PutMapping in " + table.getName() + "Controller");
            assertTrue(content.contains("@DeleteMapping"), "❌ Missing @DeleteMapping in " + table.getName() + "Controller");

            logger.info("✅ Passed test for: {}Controller", table.getName());
        }
    }

    private Table createTable(String name, String pkName, String pkType) {
        Table table = new Table();
        table.setName(name);

        Column pkColumn = new Column();
        pkColumn.setName(pkName);
        pkColumn.setJavaType(pkType);
        pkColumn.setPrimaryKey(true);

        table.setColumns(List.of(pkColumn));
        return table;
    }
}
