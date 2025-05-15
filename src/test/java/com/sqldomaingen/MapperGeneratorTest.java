package com.sqldomaingen;


import com.sqldomaingen.generator.MapperGenerator;
import com.sqldomaingen.model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class MapperGeneratorTest {

    private static final Logger logger = LoggerFactory.getLogger(MapperGeneratorTest.class);
    private MapperGenerator mapperGenerator;

    private Map<String, Table> tableMap;
    private final String outputPath = "output/mappers";

    @BeforeEach
    void setUp() {
        logger.info("🛠 Setting up test environment for MapperGenerator...");

        tableMap = new HashMap<>();

        // ✅ Δημιουργία Student Table
        Table studentTable = new Table();
        studentTable.setName("student");

        Column studentId = new Column();
        studentId.setName("id");
        studentId.setSqlType("BIGINT");
        studentId.setJavaType("Long");
        studentId.setPrimaryKey(true);
        studentTable.setColumns(new ArrayList<>(List.of(studentId)));

        // ✅ Δημιουργία Course Table
        Table courseTable = new Table();
        courseTable.setName("course");

        Column courseId = new Column();
        courseId.setName("id");
        courseId.setSqlType("BIGINT");
        courseId.setJavaType("Long");
        courseId.setPrimaryKey(true);
        courseTable.setColumns(new ArrayList<>(List.of(courseId)));

        // ✅ Δημιουργία Enrollment Table (ManyToMany)
        Table enrollmentTable = new Table();
        enrollmentTable.setName("enrollment");

        Column enrollmentId = new Column();
        enrollmentId.setName("id");
        enrollmentId.setSqlType("BIGINT");
        enrollmentId.setJavaType("Long");
        enrollmentId.setPrimaryKey(true);
        enrollmentTable.setColumns(new ArrayList<>(List.of(enrollmentId)));

        // ✅ Δημιουργία Professor Table
        Table professorTable = new Table();
        professorTable.setName("professor");

        Column professorId = new Column();
        professorId.setName("id");
        professorId.setSqlType("BIGINT");
        professorId.setJavaType("Long");
        professorId.setPrimaryKey(true);
        professorTable.setColumns(new ArrayList<>(List.of(professorId)));

        // ✅ Προσθήκη σχέσεων
        // 1. ManyToOne: Κάθε Course έχει έναν Professor
        Relationship courseProfessorRel = new Relationship();
        courseProfessorRel.setSourceTable("course");
        courseProfessorRel.setTargetTable("professor");
        courseProfessorRel.setRelationshipType(Relationship.RelationshipType.MANYTOONE); // ✅ Χρησιμοποιούμε το enum
        courseTable.setRelationships(List.of(courseProfessorRel));

        // 2. OneToMany: Ένας Professor έχει πολλά Courses
        Relationship professorCoursesRel = new Relationship();
        professorCoursesRel.setSourceTable("professor");
        professorCoursesRel.setTargetTable("course");
        professorCoursesRel.setRelationshipType(Relationship.RelationshipType.ONETOMANY); // ✅ Χρησιμοποιούμε το enum
        professorTable.setRelationships(List.of(professorCoursesRel));

        // 3. ManyToMany: Οι Students και Courses σχετίζονται μέσω Enrollment
        Relationship studentEnrollmentRel = new Relationship();
        studentEnrollmentRel.setSourceTable("student");
        studentEnrollmentRel.setTargetTable("enrollment");
        studentEnrollmentRel.setRelationshipType(Relationship.RelationshipType.ONETOMANY); // ✅ Χρησιμοποιούμε το enum

        Relationship enrollmentStudentRel = new Relationship();
        enrollmentStudentRel.setSourceTable("enrollment");
        enrollmentStudentRel.setTargetTable("student");
        enrollmentStudentRel.setRelationshipType(Relationship.RelationshipType.MANYTOONE); // ✅ Χρησιμοποιούμε το enum

        Relationship enrollmentCourseRel = new Relationship();
        enrollmentCourseRel.setSourceTable("enrollment");
        enrollmentCourseRel.setTargetTable("course");
        enrollmentCourseRel.setRelationshipType(Relationship.RelationshipType.MANYTOONE); // ✅ Χρησιμοποιούμε το enum

        Relationship courseEnrollmentRel = new Relationship();
        courseEnrollmentRel.setSourceTable("course");
        courseEnrollmentRel.setTargetTable("enrollment");
        courseEnrollmentRel.setRelationshipType(Relationship.RelationshipType.ONETOMANY); // ✅ Χρησιμοποιούμε το enum

        studentTable.setRelationships(List.of(studentEnrollmentRel));
        enrollmentTable.setRelationships(List.of(enrollmentStudentRel, enrollmentCourseRel));
        courseTable.setRelationships(List.of(courseEnrollmentRel));

        tableMap.put("student", studentTable);
        tableMap.put("course", courseTable);
        tableMap.put("enrollment", enrollmentTable);
        tableMap.put("professor", professorTable);

        logger.info("📌 Tables with relationships loaded into tableMap: {}", tableMap.keySet());
        mapperGenerator = new MapperGenerator(tableMap);

        logger.info("✅ Test setup complete.");
    }

    @Test
    void testGenerateMappers() throws IOException {
        logger.info("🚀 Starting test: generateMappers");

        logger.info("📌 Tables in tableMap before calling generateMappers: {}", tableMap.keySet());

        mapperGenerator.generateMappers("com.sqldomaingen");

        // ✅ Διόρθωση διαδρομών
        Path courseMapperPath = Paths.get(outputPath, "CourseMapper.java");
        Path studentMapperPath = Paths.get(outputPath, "StudentMapper.java");
        Path enrollmentMapperPath = Paths.get(outputPath, "EnrollmentMapper.java");
        Path professorMapperPath = Paths.get(outputPath, "ProfessorMapper.java");
        Path baseMapperPath = Paths.get(outputPath, "BaseMapper.java"); // ✅ Προσθήκη του BaseMapper

        logger.info("🔍 Checking expected file locations:");
        logger.info("📄 StudentMapper.java -> {}", studentMapperPath.toAbsolutePath());
        logger.info("📄 CourseMapper.java -> {}", courseMapperPath.toAbsolutePath());
        logger.info("📄 EnrollmentMapper.java -> {}", enrollmentMapperPath.toAbsolutePath());
        logger.info("📄 ProfessorMapper.java -> {}", professorMapperPath.toAbsolutePath());
        logger.info("📄 BaseMapper.java -> {}", baseMapperPath.toAbsolutePath()); // ✅ Προσθήκη καταγραφής για το BaseMapper

        // ✅ Έλεγχος αν τα αρχεία υπάρχουν
        assertTrue(Files.exists(courseMapperPath), "❌ CourseMapper.java was not generated.");
        logger.info("✅ CourseMapper.java exists.");

        assertTrue(Files.exists(enrollmentMapperPath), "❌ EnrollmentMapper.java was not generated.");
        logger.info("✅ EnrollmentMapper.java exists.");

        assertTrue(Files.exists(professorMapperPath), "❌ ProfessorMapper.java was not generated.");
        logger.info("✅ ProfessorMapper.java exists.");

        assertTrue(Files.exists(studentMapperPath), "❌ StudentMapper.java was not generated.");
        logger.info("✅ StudentMapper.java exists.");

        assertTrue(Files.exists(baseMapperPath), "❌ BaseMapper.java was not generated."); // ✅ Έλεγχος BaseMapper
        logger.info("✅ BaseMapper.java exists.");

        // ✅ Έλεγχος αν τα αρχεία έχουν περιεχόμενο
        assertTrue(Files.size(studentMapperPath) > 0, "❌ StudentMapper.java is empty!");
        logger.info("✅ StudentMapper.java is not empty.");

        assertTrue(Files.size(courseMapperPath) > 0, "❌ CourseMapper.java is empty!");
        logger.info("✅ CourseMapper.java is not empty.");

        assertTrue(Files.size(enrollmentMapperPath) > 0, "❌ EnrollmentMapper.java is empty!");
        logger.info("✅ EnrollmentMapper.java is not empty.");

        assertTrue(Files.size(professorMapperPath) > 0, "❌ ProfessorMapper.java is empty!");
        logger.info("✅ ProfessorMapper.java is not empty.");

        assertTrue(Files.size(baseMapperPath) > 0, "❌ BaseMapper.java is empty!"); // ✅ Έλεγχος BaseMapper
        logger.info("✅ BaseMapper.java is not empty.");

        // ✅ Έλεγχος του περιεχομένου του αρχείου
        String studentContent = Files.readString(studentMapperPath);
        assertTrue(studentContent.contains("public class StudentMapper"), "❌ StudentMapper.java content is incorrect!");
        logger.info("✅ StudentMapper.java content is correct.");

        String courseContent = Files.readString(courseMapperPath);
        assertTrue(courseContent.contains("public class CourseMapper"), "❌ CourseMapper.java content is incorrect!");
        logger.info("✅ CourseMapper.java content is correct.");

        String enrollmentContent = Files.readString(enrollmentMapperPath);
        assertTrue(enrollmentContent.contains("public class EnrollmentMapper"), "❌ EnrollmentMapper.java content is incorrect!");
        logger.info("✅ EnrollmentMapper.java content is correct.");

        String professorContent = Files.readString(professorMapperPath);
        assertTrue(professorContent.contains("public class ProfessorMapper"), "❌ ProfessorMapper.java content is incorrect!");
        logger.info("✅ ProfessorMapper.java content is correct.");

        String baseContent = Files.readString(baseMapperPath);
        assertTrue(baseContent.contains("public interface BaseMapper"), "❌ BaseMapper.java content is incorrect!"); // ✅ Έλεγχος περιεχομένου BaseMapper
        logger.info("✅ BaseMapper.java content is correct.");

        logger.info("🎉 All mapper files (including BaseMapper) were successfully generated and contain expected content!");
    }

}
