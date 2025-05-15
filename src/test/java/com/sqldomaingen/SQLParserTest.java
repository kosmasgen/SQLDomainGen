package com.sqldomaingen;

import com.sqldomaingen.parser.SQLParser;
import org.antlr.v4.runtime.tree.ParseTree;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.jupiter.api.Assertions.*;

class SQLParserTest {

    private static final Logger logger = LoggerFactory.getLogger(SQLParserTest.class);
    private SQLParser sqlParser;

    @BeforeEach
    void setUp() {
        sqlParser = new SQLParser();
    }


    @Test
    void testParseCreateDepartmentTable() {
        String sql = """
                CREATE TABLE department (
                    department_id INT PRIMARY KEY AUTO_INCREMENT,
                    name VARCHAR(100) NOT NULL,
                    description TEXT,
                    parent_dept_id INT,
                    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                    FOREIGN KEY (parent_dept_id) REFERENCES department(department_id)
                );
                """;

        sqlParser.setSqlContent(sql);
        logger.info("Testing CREATE TABLE for 'department':\n{}", sql);

        assertDoesNotThrow(() -> {
            ParseTree parseTree = sqlParser.parseTreeFromSQL();
            assertNotNull(parseTree, "ParseTree should not be null for CREATE TABLE department.");

            String tree = parseTree.toStringTree().toUpperCase();

            // Έλεγχοι σε βασικά στοιχεία
            assertTrue(tree.contains("DEPARTMENT_ID"), "Expected 'department_id' in parse tree.");
            assertTrue(tree.contains("PRIMARY KEY"), "Expected 'PRIMARY KEY' in parse tree.");
            assertTrue(tree.contains("AUTO_INCREMENT"), "Expected 'AUTO_INCREMENT' in parse tree.");
            assertTrue(tree.contains("NOT NULL"), "Expected 'NOT NULL' in parse tree.");
            assertTrue(tree.contains("FOREIGN KEY"), "Expected 'FOREIGN KEY' in parse tree.");
            assertTrue(tree.contains("ON UPDATE"), "Expected 'ON UPDATE' clause in parse tree.");
        });
    }

    @Test
    void testParseCreateUserTable() {
        String sql = """
            CREATE TABLE user (
                user_id INT PRIMARY KEY AUTO_INCREMENT,
                username VARCHAR(50) NOT NULL UNIQUE,
                password VARCHAR(255) NOT NULL,
                email VARCHAR(100) NOT NULL UNIQUE,
                full_name VARCHAR(100) NOT NULL,
                department_id INT,
                role VARCHAR(50) NOT NULL,
                supervisor_id INT,
                created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                FOREIGN KEY (department_id) REFERENCES department(department_id),
                FOREIGN KEY (supervisor_id) REFERENCES user(user_id)
            );
            """;

        sqlParser.setSqlContent(sql);
        logger.info("Testing CREATE TABLE for 'user':\n{}", sql);

        assertDoesNotThrow(() -> {
            ParseTree parseTree = sqlParser.parseTreeFromSQL();
            assertNotNull(parseTree, "ParseTree should not be null for CREATE TABLE user.");

            String tree = parseTree.toStringTree().toUpperCase();

            assertTrue(tree.contains("USER_ID"), "Expected 'user_id' in parse tree.");
            assertTrue(tree.contains("PRIMARY KEY"), "Expected 'PRIMARY KEY' in parse tree.");
            assertTrue(tree.contains("AUTO_INCREMENT"), "Expected 'AUTO_INCREMENT' in parse tree.");
            assertTrue(tree.contains("NOT NULL"), "Expected 'NOT NULL' in parse tree.");
            assertTrue(tree.contains("UNIQUE"), "Expected 'UNIQUE' in parse tree.");
            assertTrue(tree.contains("FOREIGN KEY"), "Expected 'FOREIGN KEY' in parse tree.");
            assertTrue(tree.contains("ON UPDATE"), "Expected 'ON UPDATE' clause in parse tree.");
        });
    }

    @Test
    void testParseCreateRecurringPatternTable() {
        String sql = """
        CREATE TABLE recurring_pattern (
            pattern_id INT PRIMARY KEY AUTO_INCREMENT,
            pattern_type VARCHAR(50) NOT NULL,
            frequency VARCHAR(50),
            days_of_week VARCHAR(50),
            day_of_month INT,
            month_of_year INT,
            end_date DATE,
            end_after_occur INT,
            created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
            updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
        );
        """;

        sqlParser.setSqlContent(sql);
        logger.info("Testing CREATE TABLE for 'recurring_pattern':\n{}", sql);

        assertDoesNotThrow(() -> {
            ParseTree parseTree = sqlParser.parseTreeFromSQL();
            assertNotNull(parseTree, "ParseTree should not be null for CREATE TABLE recurring_pattern.");

            String tree = parseTree.toStringTree().toUpperCase();

            assertTrue(tree.contains("RECURRING_PATTERN"), "Expected 'recurring_pattern' in parse tree.");
            assertTrue(tree.contains("PATTERN_ID"), "Expected 'pattern_id' in parse tree.");
            assertTrue(tree.contains("PRIMARY KEY"), "Expected 'PRIMARY KEY' in parse tree.");
            assertTrue(tree.contains("AUTO_INCREMENT"), "Expected 'AUTO_INCREMENT' in parse tree.");
            assertTrue(tree.contains("PATTERN_TYPE"), "Expected 'pattern_type' in parse tree.");
            assertTrue(tree.contains("NOT NULL"), "Expected 'NOT NULL' in parse tree.");
            assertTrue(tree.contains("UPDATED_AT"), "Expected 'updated_at' in parse tree.");
            assertTrue(tree.contains("ON UPDATE"), "Expected 'ON UPDATE' clause in parse tree.");
        });
    }

    @Test
    void testParseCreateEventTable() {
        String sql = """
        CREATE TABLE event (
            event_id INT PRIMARY KEY AUTO_INCREMENT,
            title VARCHAR(100) NOT NULL,
            description TEXT,
            start_time DATETIME NOT NULL,
            end_time DATETIME NOT NULL,
            location VARCHAR(255),
            event_type VARCHAR(50) NOT NULL,
            visibility_type VARCHAR(50) NOT NULL,
            creator_id INT NOT NULL,
            is_recurring BOOLEAN DEFAULT FALSE,
            recur_pattern_id INT,
            created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
            updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
            FOREIGN KEY (creator_id) REFERENCES user(user_id),
            FOREIGN KEY (recur_pattern_id) REFERENCES recurring_pattern(pattern_id)
        );
        """;

        sqlParser.setSqlContent(sql);
        logger.info("Testing CREATE TABLE for 'event':\n{}", sql);

        assertDoesNotThrow(() -> {
            ParseTree parseTree = sqlParser.parseTreeFromSQL();
            assertNotNull(parseTree, "ParseTree should not be null for CREATE TABLE event.");

            String tree = parseTree.toStringTree().toUpperCase();

            assertTrue(tree.contains("EVENT_ID"), "Expected 'event_id' in parse tree.");
            assertTrue(tree.contains("PRIMARY KEY"), "Expected 'PRIMARY KEY' in parse tree.");
            assertTrue(tree.contains("AUTO_INCREMENT"), "Expected 'AUTO_INCREMENT' in parse tree.");
            assertTrue(tree.contains("TITLE"), "Expected 'title' in parse tree.");
            assertTrue(tree.contains("NOT NULL"), "Expected 'NOT NULL' in parse tree.");
            assertTrue(tree.contains("DATETIME"), "Expected 'DATETIME' in parse tree.");
            assertTrue(tree.contains("BOOLEAN"), "Expected 'BOOLEAN' in parse tree.");
            assertTrue(tree.contains("DEFAULT"), "Expected 'DEFAULT' in parse tree.");
            assertTrue(tree.contains("FOREIGN KEY"), "Expected 'FOREIGN KEY' in parse tree.");
            assertTrue(tree.contains("CREATOR_ID"), "Expected 'creator_id' in parse tree.");
            assertTrue(tree.contains("RECUR_PATTERN_ID"), "Expected 'recur_pattern_id' in parse tree.");
            assertTrue(tree.contains("ON UPDATE"), "Expected 'ON UPDATE' clause in parse tree.");
        });
    }

    @Test
    void testParseCreateEventAssignmentTable() {
        String sql = """
        CREATE TABLE event_assignment (
            assignment_id INT PRIMARY KEY AUTO_INCREMENT,
            event_id INT NOT NULL,
            user_id INT,
            department_id INT,
            created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
            updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
            FOREIGN KEY (event_id) REFERENCES event(event_id),
            FOREIGN KEY (user_id) REFERENCES user(user_id),
            FOREIGN KEY (department_id) REFERENCES department(department_id)
        );
        """;

        sqlParser.setSqlContent(sql);
        logger.info("Testing CREATE TABLE for 'event_assignment':\n{}", sql);

        assertDoesNotThrow(() -> {
            ParseTree parseTree = sqlParser.parseTreeFromSQL();
            assertNotNull(parseTree, "ParseTree should not be null for CREATE TABLE event_assignment.");

            String tree = parseTree.toStringTree().toUpperCase();

            assertTrue(tree.contains("ASSIGNMENT_ID"), "Expected 'assignment_id' in parse tree.");
            assertTrue(tree.contains("PRIMARY KEY"), "Expected 'PRIMARY KEY' in parse tree.");
            assertTrue(tree.contains("AUTO_INCREMENT"), "Expected 'AUTO_INCREMENT' in parse tree.");
            assertTrue(tree.contains("EVENT_ID"), "Expected 'event_id' in parse tree.");
            assertTrue(tree.contains("NOT NULL"), "Expected 'NOT NULL' in parse tree.");
            assertTrue(tree.contains("USER_ID"), "Expected 'user_id' in parse tree.");
            assertTrue(tree.contains("DEPARTMENT_ID"), "Expected 'department_id' in parse tree.");
            assertTrue(tree.contains("TIMESTAMP"), "Expected 'TIMESTAMP' in parse tree.");
            assertTrue(tree.contains("DEFAULT"), "Expected 'DEFAULT' in parse tree.");
            assertTrue(tree.contains("CURRENT_TIMESTAMP"), "Expected 'CURRENT_TIMESTAMP' in parse tree.");
            assertTrue(tree.contains("ON UPDATE CURRENT_TIMESTAMP"), "Expected 'ON UPDATE CURRENT_TIMESTAMP' in parse tree.");
            assertTrue(tree.contains("FOREIGN KEY"), "Expected 'FOREIGN KEY' in parse tree.");
        });
    }

    @Test
    void testParseCreateEventExceptionTable() {
        String sql = """
        CREATE TABLE event_exception (
            exception_id INT PRIMARY KEY AUTO_INCREMENT,
            event_id INT NOT NULL,
            exception_date DATE NOT NULL,
            is_rescheduled BOOLEAN DEFAULT FALSE,
            new_start_time DATETIME,
            new_end_time DATETIME,
            created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
            updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
            FOREIGN KEY (event_id) REFERENCES event(event_id)
        );
        """;

        sqlParser.setSqlContent(sql);
        logger.info("Testing CREATE TABLE for 'event_exception':\n{}", sql);

        assertDoesNotThrow(() -> {
            ParseTree parseTree = sqlParser.parseTreeFromSQL();
            assertNotNull(parseTree, "ParseTree should not be null for CREATE TABLE event_exception.");

            String tree = parseTree.toStringTree().toUpperCase();

            assertTrue(tree.contains("EXCEPTION_ID"), "Expected 'exception_id' in parse tree.");
            assertTrue(tree.contains("PRIMARY KEY"), "Expected 'PRIMARY KEY' in parse tree.");
            assertTrue(tree.contains("AUTO_INCREMENT"), "Expected 'AUTO_INCREMENT' in parse tree.");
            assertTrue(tree.contains("NOT NULL"), "Expected 'NOT NULL' in parse tree.");
            assertTrue(tree.contains("BOOLEAN"), "Expected 'BOOLEAN' in parse tree.");
            assertTrue(tree.contains("DEFAULT"), "Expected 'DEFAULT' in parse tree.");
            assertTrue(tree.contains("CURRENT_TIMESTAMP"), "Expected 'CURRENT_TIMESTAMP' in parse tree.");
            assertTrue(tree.contains("ON UPDATE"), "Expected 'ON UPDATE' clause in parse tree.");
            assertTrue(tree.contains("FOREIGN KEY"), "Expected 'FOREIGN KEY' in parse tree.");
        });
    }

    @Test
    void testParseCreateTimeOffRequestTable() {
        String sql = """
        CREATE TABLE time_off_request (
            request_id INT PRIMARY KEY AUTO_INCREMENT,
            user_id INT NOT NULL,
            start_date DATE NOT NULL,
            end_date DATE NOT NULL,
            type VARCHAR(50) NOT NULL,
            status VARCHAR(50) NOT NULL,
            supervisor_id INT,
            reason TEXT,
            comments TEXT,
            created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
            updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
            FOREIGN KEY (user_id) REFERENCES user(user_id),
            FOREIGN KEY (supervisor_id) REFERENCES user(user_id)
        );
        """;

        sqlParser.setSqlContent(sql);
        logger.info("Testing CREATE TABLE for 'time_off_request':\n{}", sql);

        assertDoesNotThrow(() -> {
            ParseTree parseTree = sqlParser.parseTreeFromSQL();
            assertNotNull(parseTree, "ParseTree should not be null for CREATE TABLE time_off_request.");

            String tree = parseTree.toStringTree().toUpperCase();

            assertTrue(tree.contains("REQUEST_ID"), "Expected 'request_id' in parse tree.");
            assertTrue(tree.contains("PRIMARY KEY"), "Expected 'PRIMARY KEY' in parse tree.");
            assertTrue(tree.contains("AUTO_INCREMENT"), "Expected 'AUTO_INCREMENT' in parse tree.");
            assertTrue(tree.contains("USER_ID"), "Expected 'user_id' in parse tree.");
            assertTrue(tree.contains("START_DATE"), "Expected 'start_date' in parse tree.");
            assertTrue(tree.contains("END_DATE"), "Expected 'end_date' in parse tree.");
            assertTrue(tree.contains("TYPE"), "Expected 'type' in parse tree.");
            assertTrue(tree.contains("STATUS"), "Expected 'status' in parse tree.");
            assertTrue(tree.contains("SUPERVISOR_ID"), "Expected 'supervisor_id' in parse tree.");
            assertTrue(tree.contains("REASON"), "Expected 'reason' in parse tree.");
            assertTrue(tree.contains("COMMENTS"), "Expected 'comments' in parse tree.");
            assertTrue(tree.contains("DEFAULT"), "Expected 'DEFAULT' in parse tree.");
            assertTrue(tree.contains("CURRENT_TIMESTAMP"), "Expected 'CURRENT_TIMESTAMP' in parse tree.");
            assertTrue(tree.contains("ON UPDATE"), "Expected 'ON UPDATE' clause in parse tree.");
            assertTrue(tree.contains("FOREIGN KEY"), "Expected 'FOREIGN KEY' in parse tree.");
        });
    }

    @Test
    void testParseCreateHolidayTable() {
        String sql = """
        CREATE TABLE holiday (
            holiday_id INT PRIMARY KEY AUTO_INCREMENT,
            name VARCHAR(100) NOT NULL,
            description TEXT,
            date DATE NOT NULL,
            is_recurring BOOLEAN DEFAULT FALSE,
            recur_pattern_id INT,
            created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
            updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
            FOREIGN KEY (recur_pattern_id) REFERENCES recurring_pattern(pattern_id)
        );
        """;

        sqlParser.setSqlContent(sql);
        logger.info("Testing CREATE TABLE for 'holiday':\n{}", sql);

        assertDoesNotThrow(() -> {
            ParseTree parseTree = sqlParser.parseTreeFromSQL();
            assertNotNull(parseTree, "ParseTree should not be null for CREATE TABLE holiday.");

            String tree = parseTree.toStringTree().toUpperCase();

            assertTrue(tree.contains("HOLIDAY_ID"), "Expected 'holiday_id' in parse tree.");
            assertTrue(tree.contains("PRIMARY KEY"), "Expected 'PRIMARY KEY' in parse tree.");
            assertTrue(tree.contains("AUTO_INCREMENT"), "Expected 'AUTO_INCREMENT' in parse tree.");
            assertTrue(tree.contains("NAME"), "Expected 'name' in parse tree.");
            assertTrue(tree.contains("DESCRIPTION"), "Expected 'description' in parse tree.");
            assertTrue(tree.contains("DATE"), "Expected 'date' in parse tree.");
            assertTrue(tree.contains("IS_RECURRING"), "Expected 'is_recurring' in parse tree.");
            assertTrue(tree.contains("BOOLEAN"), "Expected 'BOOLEAN' in parse tree.");
            assertTrue(tree.contains("DEFAULT"), "Expected 'DEFAULT' in parse tree.");
            assertTrue(tree.contains("CURRENT_TIMESTAMP"), "Expected 'CURRENT_TIMESTAMP' in parse tree.");
            assertTrue(tree.contains("RECUR_PATTERN_ID"), "Expected 'recur_pattern_id' in parse tree.");
            assertTrue(tree.contains("ON UPDATE"), "Expected 'ON UPDATE' clause in parse tree.");
            assertTrue(tree.contains("FOREIGN KEY"), "Expected 'FOREIGN KEY' in parse tree.");
        });
    }

    @Test
    void testParseCreateDepartmentDayOffTable() {
        String sql = """
        CREATE TABLE department_day_off (
            day_off_id INT PRIMARY KEY AUTO_INCREMENT,
            department_id INT NOT NULL,
            name VARCHAR(100) NOT NULL,
            description TEXT,
            date DATE NOT NULL,
            is_recurring BOOLEAN DEFAULT FALSE,
            recur_pattern_id INT,
            created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
            updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
            FOREIGN KEY (department_id) REFERENCES department(department_id),
            FOREIGN KEY (recur_pattern_id) REFERENCES recurring_pattern(pattern_id)
        );
        """;

        sqlParser.setSqlContent(sql);
        logger.info("Testing CREATE TABLE for 'department_day_off':\n{}", sql);

        assertDoesNotThrow(() -> {
            ParseTree parseTree = sqlParser.parseTreeFromSQL();
            assertNotNull(parseTree, "ParseTree should not be null for CREATE TABLE department_day_off.");

            String tree = parseTree.toStringTree().toUpperCase();

            assertTrue(tree.contains("DAY_OFF_ID"), "Expected 'day_off_id' in parse tree.");
            assertTrue(tree.contains("PRIMARY KEY"), "Expected 'PRIMARY KEY' in parse tree.");
            assertTrue(tree.contains("AUTO_INCREMENT"), "Expected 'AUTO_INCREMENT' in parse tree.");
            assertTrue(tree.contains("DEPARTMENT_ID"), "Expected 'department_id' in parse tree.");
            assertTrue(tree.contains("NAME"), "Expected 'name' in parse tree.");
            assertTrue(tree.contains("DESCRIPTION"), "Expected 'description' in parse tree.");
            assertTrue(tree.contains("DATE"), "Expected 'date' in parse tree.");
            assertTrue(tree.contains("IS_RECURRING"), "Expected 'is_recurring' in parse tree.");
            assertTrue(tree.contains("BOOLEAN"), "Expected 'BOOLEAN' in parse tree.");
            assertTrue(tree.contains("DEFAULT"), "Expected 'DEFAULT' in parse tree.");
            assertTrue(tree.contains("CURRENT_TIMESTAMP"), "Expected 'CURRENT_TIMESTAMP' in parse tree.");
            assertTrue(tree.contains("RECUR_PATTERN_ID"), "Expected 'recur_pattern_id' in parse tree.");
            assertTrue(tree.contains("ON UPDATE"), "Expected 'ON UPDATE' clause in parse tree.");
            assertTrue(tree.contains("FOREIGN KEY"), "Expected 'FOREIGN KEY' in parse tree.");
        });
    }

    @Test
    void testParseCreateAbsenceTable() {
        String sql = """
        CREATE TABLE absence (
            absence_id INT PRIMARY KEY AUTO_INCREMENT,
            user_id INT NOT NULL,
            start_time DATETIME NOT NULL,
            end_time DATETIME NOT NULL,
            reason TEXT,
            is_notification BOOLEAN DEFAULT FALSE,
            created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
            updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
            FOREIGN KEY (user_id) REFERENCES user(user_id)
        );
        """;

        sqlParser.setSqlContent(sql);
        logger.info("Testing CREATE TABLE for 'absence':\n{}", sql);

        assertDoesNotThrow(() -> {
            ParseTree parseTree = sqlParser.parseTreeFromSQL();
            assertNotNull(parseTree, "ParseTree should not be null for CREATE TABLE absence.");

            String tree = parseTree.toStringTree().toUpperCase();

            assertTrue(tree.contains("ABSENCE_ID"), "Expected 'absence_id' in parse tree.");
            assertTrue(tree.contains("PRIMARY KEY"), "Expected 'PRIMARY KEY' in parse tree.");
            assertTrue(tree.contains("AUTO_INCREMENT"), "Expected 'AUTO_INCREMENT' in parse tree.");
            assertTrue(tree.contains("USER_ID"), "Expected 'user_id' in parse tree.");
            assertTrue(tree.contains("START_TIME"), "Expected 'start_time' in parse tree.");
            assertTrue(tree.contains("END_TIME"), "Expected 'end_time' in parse tree.");
            assertTrue(tree.contains("REASON"), "Expected 'reason' in parse tree.");
            assertTrue(tree.contains("IS_NOTIFICATION"), "Expected 'is_notification' in parse tree.");
            assertTrue(tree.contains("BOOLEAN"), "Expected 'BOOLEAN' in parse tree.");
            assertTrue(tree.contains("DEFAULT"), "Expected 'DEFAULT' in parse tree.");
            assertTrue(tree.contains("CURRENT_TIMESTAMP"), "Expected 'CURRENT_TIMESTAMP' in parse tree.");
            assertTrue(tree.contains("ON UPDATE"), "Expected 'ON UPDATE' clause in parse tree.");
            assertTrue(tree.contains("FOREIGN KEY"), "Expected 'FOREIGN KEY' in parse tree.");
        });
    }

    @Test
    void testAlterRecurringPatternAddEventIdForeignKey() {
        String sql = """
        ALTER TABLE recurring_pattern
        ADD COLUMN event_id INT,
        ADD FOREIGN KEY (event_id) REFERENCES event(event_id);
        """;

        sqlParser.setSqlContent(sql);
        logger.info("Testing ALTER TABLE for 'recurring_pattern':\n{}", sql);

        assertDoesNotThrow(() -> {
            ParseTree parseTree = sqlParser.parseTreeFromSQL();
            assertNotNull(parseTree, "ParseTree should not be null for ALTER TABLE recurring_pattern.");

            String tree = parseTree.toStringTree().toUpperCase();

            assertTrue(tree.contains("ALTER"), "Expected 'ALTER' in parse tree.");
            assertTrue(tree.contains("RECURRING_PATTERN"), "Expected 'recurring_pattern' in parse tree.");
            assertTrue(tree.contains("ADD"), "Expected 'ADD' in parse tree.");
            assertTrue(tree.contains("EVENT_ID"), "Expected 'event_id' in parse tree.");
            assertTrue(tree.contains("FOREIGN"), "Expected 'FOREIGN' in parse tree.");
            assertTrue(tree.contains("KEY"), "Expected 'KEY' in parse tree.");
            assertTrue(tree.contains("REFERENCES"), "Expected 'REFERENCES' in parse tree.");
            assertTrue(tree.contains("EVENT"), "Expected 'event' table reference in parse tree.");
        });
    }
}


