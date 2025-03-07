package com.sqldomaingen;

import com.sqldomaingen.generator.ServiceGenerator;
import com.sqldomaingen.model.Column;
import com.sqldomaingen.model.Table;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.*;

class ServiceGeneratorTest {

    private static final Logger logger = LoggerFactory.getLogger(ServiceGeneratorTest.class);
    private static final String OUTPUT_PATH = "output/services";
    private ServiceGenerator serviceGenerator;

    @BeforeEach
    void setUp() {
        serviceGenerator = new ServiceGenerator();
        logger.info("🔄 ServiceGeneratorTest initialized.");
    }

    @Test
    void testGenerateServiceInterfaces() {
        logger.info("🚀 Running test: testGenerateServiceInterfaces");

        // Δημιουργία πινάκων με διαφορετικά Primary Keys
        Table courseTable = createTable("Course", "id", "Long");
        Table studentTable = createTable("Student", "id", "String");
        Table professorTable = createTable("Professor", "id", "UUID");
        Table enrollmentTable = createTable("Enrollment", "id", "Long");

        List<Table> tables = List.of(courseTable, studentTable, professorTable, enrollmentTable);

        // Δημιουργία Service Interfaces
        serviceGenerator.generateAllServices(tables);

        // Έλεγχος αν τα αρχεία δημιουργήθηκαν σωστά
        checkServiceFile("CourseService.java");
        checkServiceFile("StudentService.java");
        checkServiceFile("ProfessorService.java");
        checkServiceFile("EnrollmentService.java");

        // ΝΕΟΙ ΕΛΕΓΧΟΙ: Έλεγχος `ServiceImpl`
        checkServiceImplFile("CourseServiceImpl.java", "courseRepository", "courseMapper");
        checkServiceImplFile("StudentServiceImpl.java", "studentRepository", "studentMapper");
        checkServiceImplFile("ProfessorServiceImpl.java", "professorRepository", "professorMapper");
        checkServiceImplFile("EnrollmentServiceImpl.java", "enrollmentRepository", "enrollmentMapper");

        logger.info("✅ All service interfaces and implementations generated successfully!");


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

    private void checkServiceFile(String fileName) {
        Path filePath = Paths.get(OUTPUT_PATH, fileName);
        assertTrue(Files.exists(filePath), "❌ File missing: " + fileName);

        try {
            String content = Files.readString(filePath);
            assertTrue(content.contains("package com.sqldomaingen.service;"), "❌ Incorrect package in " + fileName);
            assertTrue(content.contains("getAll"), "❌ Method getAll missing in " + fileName);
            assertTrue(content.contains("getById"), "❌ Method getById missing in " + fileName);
            assertTrue(content.contains("create"), "❌ Method create missing in " + fileName);
            assertTrue(content.contains("update"), "❌ Method update missing in " + fileName);
            assertTrue(content.contains("deleteById"), "❌ Method deleteById missing in " + fileName);
            assertTrue(content.contains("existsById"), "❌ Method existsById missing in " + fileName);
            assertTrue(content.contains("count"), "❌ Method count missing in " + fileName);

            if (fileName.contains("ProfessorService") && !content.contains("import java.util.UUID;")) {
                fail("❌ UUID import missing in " + fileName);
            }
        } catch (IOException e) {
            fail("❌ Failed to read file: " + fileName);
        }
    }

    private void checkServiceImplFile(String fileName, String expectedRepoName, String expectedMapperName) {
        Path filePath = Paths.get(OUTPUT_PATH, fileName);
        assertTrue(Files.exists(filePath), "❌ File missing: " + fileName);

        try {
            String content = Files.readString(filePath);
            System.out.println("🔍 Generated CourseServiceImpl.java content:\n" + content);

            // Έλεγχος αν υπάρχει το package
            assertTrue(content.contains("package com.sqldomaingen.service.impl;"), "❌ Incorrect package in " + fileName);

            // Έλεγχος για το @Transactional στην κλάση
            assertTrue(content.contains("@Transactional"), "❌ Missing @Transactional annotation in " + fileName);

            // Έλεγχος για camelCase στις μεταβλητές repository & mapper
            assertTrue(Pattern.compile("private final \\w+ " + expectedRepoName + ";").matcher(content).find(),
                    "❌ Repository variable is not camelCase in " + fileName);
            assertTrue(Pattern.compile("private final \\w+ " + expectedMapperName + ";").matcher(content).find(),
                    "❌ Mapper variable is not camelCase in " + fileName);

            assertTrue(content.contains("getAll("), "❌ Method getAll() missing in " + fileName);
            assertTrue(content.contains("getById("), "❌ Method getById() missing in " + fileName);
            assertTrue(content.contains("create("), "❌ Method create() missing in " + fileName);
            assertTrue(content.contains("update("), "❌ Method update() missing in " + fileName);
            assertTrue(content.contains("deleteById("), "❌ Method deleteById() missing in " + fileName);
            assertTrue(content.contains("existsById("), "❌ Method existsById() missing in " + fileName);
            assertTrue(content.contains("count("), "❌ Method count() missing in " + fileName);

        } catch (IOException e) {
            fail("❌ Failed to read file: " + fileName);
        }
    }
}
