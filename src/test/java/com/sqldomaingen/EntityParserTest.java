package com.sqldomaingen;

import com.sqldomaingen.model.Entity;
import com.sqldomaingen.model.Field;
import com.sqldomaingen.parseroutputfiles.EntityParser;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class EntityParserTest {
    private static final Logger logger = LoggerFactory.getLogger(EntityParserTest.class);
    private final EntityParser parser = new EntityParser();

    @Test
    void testParseEntityFromFile() {
        logger.info("🚀 Ξεκινάει το τεστ parseEntityFromFile...");
        Path path = Paths.get("output/entities/Course.java");
        logger.info("📄 Διαβάζεται το αρχείο: {}", path.toAbsolutePath());

        Entity entity = parser.parseEntityFromFile(path);
        assertNotNull(entity, "Η οντότητα δεν πρέπει να είναι null");
        logger.info("✅ Η οντότητα φορτώθηκε με όνομα: {}", entity.getName());

        assertEquals("Course", entity.getName(), "Το όνομα της οντότητας πρέπει να είναι 'Course'");

        List<Field> fields = entity.getFields();
        assertFalse(fields.isEmpty(), "Η λίστα των πεδίων δεν πρέπει να είναι άδεια");
        logger.info("🔄 Βρέθηκαν {} πεδία στην οντότητα: {}", fields.size(), fields);

        boolean idExists = fields.stream().anyMatch(f -> f.getName().equals("id") && f.getType().equals("Long") && f.isPrimaryKey());
        assertTrue(idExists, "Το πεδίο id δεν βρέθηκε σωστά");
        logger.info("✅ Το πεδίο 'id' ανιχνεύθηκε σωστά ως Primary Key");

        boolean titleExists = fields.stream().anyMatch(f -> f.getName().equals("title") && f.getType().equals("String"));
        assertTrue(titleExists, "Το πεδίο title δεν βρέθηκε σωστά");
        logger.info("✅ Το πεδίο 'title' ανιχνεύθηκε σωστά ως String");

        boolean professorExists = fields.stream().anyMatch(f -> f.getName().equals("professor") && f.getType().equals("Professor") && f.isForeignKey());
        assertTrue(professorExists, "Το πεδίο professor δεν βρέθηκε σωστά");
        logger.info("✅ Το πεδίο 'professor' ανιχνεύθηκε σωστά ως Foreign Key");

        boolean enrollmentsExists = fields.stream().anyMatch(f -> f.getName().equals("enrollments") && f.getType().equals("List<Enrollment>"));
        assertTrue(enrollmentsExists, "Το πεδίο enrollments δεν βρέθηκε σωστά");
        logger.info("✅ Το πεδίο 'enrollments' ανιχνεύθηκε σωστά ως List<Enrollment>");

        boolean studentsExists = fields.stream().anyMatch(f -> f.getName().equals("students") && f.getType().equals("List<Student>"));
        assertTrue(studentsExists, "Το πεδίο students δεν βρέθηκε σωστά");
        logger.info("✅ Το πεδίο 'students' ανιχνεύθηκε σωστά ως List<Student>");

        logger.info("🎉 ✅ Όλες οι δοκιμές για τα πεδία πέρασαν επιτυχώς.");
    }

    @Test
    void testParseEntityWithConstraints() {
        logger.info("🚀 Ξεκινάει το τεστ για περιορισμούς στις στήλες...");
        Path path = Paths.get("output/entities/Course.java");
        logger.info("📄 Διαβάζεται το αρχείο οντότητας από: {}", path.toAbsolutePath());

        Entity entity = parser.parseEntityFromFile(path);
        assertNotNull(entity, "Η οντότητα δεν πρέπει να είναι null");
        logger.info("✅ Η οντότητα φορτώθηκε με όνομα: {}", entity.getName());

        List<Field> fields = entity.getFields();
        assertFalse(fields.isEmpty(), "Η λίστα των πεδίων δεν πρέπει να είναι άδεια");
        logger.info("🔍 Βρέθηκαν {} πεδία στην οντότητα: {}", fields.size(), fields);

        Field titleField = fields.stream().filter(f -> f.getName().equals("title")).findFirst().orElse(null);
        assertNotNull(titleField, "Το πεδίο title πρέπει να υπάρχει");
        logger.info("✅ Το πεδίο 'title' εντοπίστηκε: {}", titleField);

        assertEquals(100, titleField.getLength(), "Το πεδίο title πρέπει να έχει μήκος 100");
        logger.info("✅ Το πεδίο 'title' έχει το σωστό μήκος: {}", titleField.getLength());

        assertFalse(titleField.isNullable(), "Το πεδίο title πρέπει να είναι NOT NULL");
        logger.info("✅ Το πεδίο 'title' έχει nullable=false όπως αναμενόταν.");

        Field professorField = fields.stream().filter(f -> f.getName().equals("professor")).findFirst().orElse(null);
        assertNotNull(professorField, "Το πεδίο professor πρέπει να υπάρχει");
        logger.info("✅ Το πεδίο 'professor' εντοπίστηκε: {}", professorField);

        assertTrue(professorField.isForeignKey(), "Το πεδίο professor πρέπει να είναι Foreign Key");
        logger.info("✅ Το πεδίο 'professor' ανιχνεύθηκε σωστά ως Foreign Key.");

        logger.info("🎉 ✅ Όλες οι δοκιμές για τους περιορισμούς στις στήλες πέρασαν επιτυχώς.");
    }

    @Test
    void parseEntityFromFile_Course() {
        logger.info("🚀 Ξεκινάει το τεστ parseEntityFromFile_Course...");
        Path path = Paths.get("output/entities/Course.java");
        logger.info("📄 Διαβάζεται το αρχείο: {}", path.toAbsolutePath());

        Entity entity = parser.parseEntityFromFile(path);
        assertNotNull(entity, "Η οντότητα δεν πρέπει να είναι null");
        assertEquals("Course", entity.getName(), "Το όνομα της οντότητας πρέπει να είναι 'Course'");
        logger.info("✅ Η οντότητα φορτώθηκε με όνομα: {}", entity.getName());

        List<Field> fields = entity.getFields();
        assertEquals(5, fields.size(), "Η οντότητα Course πρέπει να έχει 5 πεδία.");
        logger.info("🔄 Βρέθηκαν {} πεδία: {}", fields.size(), fields);

        Field idField = fields.stream().filter(f -> f.getName().equals("id")).findFirst().orElse(null);
        assertNotNull(idField);
        assertEquals("Long", idField.getType());
        assertTrue(idField.isPrimaryKey());

        Field titleField = fields.stream().filter(f -> f.getName().equals("title")).findFirst().orElse(null);
        assertNotNull(titleField);
        assertEquals("String", titleField.getType());
        assertEquals(100, titleField.getLength());
        assertFalse(titleField.isNullable());

        Field professorField = fields.stream().filter(f -> f.getName().equals("professor")).findFirst().orElse(null);
        assertNotNull(professorField);
        assertEquals("Professor", professorField.getType());
        assertTrue(professorField.isForeignKey());

        logger.info("🎉 ✅ Όλες οι δοκιμές για το Course πέρασαν.");
    }

    @Test
    void parseEntityFromFile_Student() {
        logger.info("🚀 Ξεκινάει το τεστ parseEntityFromFile_Student...");
        Path path = Paths.get("output/entities/Student.java");
        logger.info("📄 Διαβάζεται το αρχείο: {}", path.toAbsolutePath());

        Entity entity = parser.parseEntityFromFile(path);
        assertNotNull(entity, "Η οντότητα δεν πρέπει να είναι null");
        assertEquals("Student", entity.getName(), "Το όνομα της οντότητας πρέπει να είναι 'Student'");
        logger.info("✅ Η οντότητα φορτώθηκε με όνομα: {}", entity.getName());

        List<Field> fields = entity.getFields();
        assertEquals(4, fields.size(), "Η οντότητα Student πρέπει να έχει 4 πεδία.");
        logger.info("🔄 Βρέθηκαν {} πεδία: {}", fields.size(), fields);

        Field idField = fields.stream().filter(f -> f.getName().equals("id")).findFirst().orElse(null);
        assertNotNull(idField);
        assertEquals("Long", idField.getType());
        assertTrue(idField.isPrimaryKey());

        Field sNameField = fields.stream().filter(f -> f.getName().equals("sName")).findFirst().orElse(null);
        assertNotNull(sNameField);
        assertEquals("String", sNameField.getType());

        logger.info("🎉 ✅ Όλες οι δοκιμές για το Student πέρασαν.");
    }

    @Test
    void parseEntitiesFromDirectory() {
        logger.info("🚀 Ξεκινάει το τεστ parseEntitiesFromDirectory...");
        String entitiesDir = "output/entities";

        List<Entity> entities = parser.parseEntities(entitiesDir);

        assertNotNull(entities, "Η λίστα των οντοτήτων δεν πρέπει να είναι null");
        assertEquals(4, entities.size(), "Πρέπει να βρεθούν 4 οντότητες (Course, Student, Enrollment, Professor)");

        boolean foundCourse = entities.stream().anyMatch(e -> e.getName().equals("Course"));
        boolean foundStudent = entities.stream().anyMatch(e -> e.getName().equals("Student"));
        boolean foundEnrollment = entities.stream().anyMatch(e -> e.getName().equals("Enrollment"));
        boolean foundProfessor = entities.stream().anyMatch(e -> e.getName().equals("Professor"));

        assertTrue(foundCourse, "Η οντότητα 'Course' δεν βρέθηκε");
        assertTrue(foundStudent, "Η οντότητα 'Student' δεν βρέθηκε");
        assertTrue(foundEnrollment, "Η οντότητα 'Enrollment' δεν βρέθηκε");
        assertTrue(foundProfessor, "Η οντότητα 'Professor' δεν βρέθηκε");

        logger.info("✅ Βρέθηκαν οι οντότητες: {}", entities);
    }




}
