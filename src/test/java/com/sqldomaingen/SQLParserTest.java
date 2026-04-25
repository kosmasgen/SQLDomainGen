package com.sqldomaingen;

import com.sqldomaingen.parser.SQLParser;
import lombok.extern.log4j.Log4j2;
import org.antlr.v4.runtime.tree.ParseTree;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@Log4j2
class SQLParserTest {

    private SQLParser sqlParser;

    @BeforeEach
    void setUp() {
        sqlParser = new SQLParser();
    }

    @Test
    void testParseCreateBusinessLocationI18nTable_PostgreSQL_WithCompositePkAndFkConstraints() {
        String sql = """
        CREATE TABLE pep_schema.business_location_i18n (
            description varchar(1000) NOT NULL,
            code varchar(255) NOT NULL,
            date_created timestamp DEFAULT CURRENT_TIMESTAMP NOT NULL,
            last_updated timestamp DEFAULT CURRENT_TIMESTAMP NOT NULL,
            recdeleted bool DEFAULT FALSE NOT NULL,
            business_location_id uuid NOT NULL,
            language_id uuid NOT NULL,
            CONSTRAINT business_location_i18n_pkey PRIMARY KEY (business_location_id, language_id),
            CONSTRAINT fk_business_location FOREIGN KEY (business_location_id) REFERENCES pep_schema.business_location(id) ON DELETE CASCADE,
            CONSTRAINT fk_business_location_language FOREIGN KEY (language_id) REFERENCES pep_schema.languages(id)
        );
        """;

        sqlParser.setSqlContent(sql);
        log.info("Testing PostgreSQL CREATE TABLE for 'pep_schema.business_location_i18n' with composite PK + FKs:\n{}", sql);

        assertDoesNotThrow(() -> {
            ParseTree parseTree = sqlParser.parseTreeFromSQL();
            assertNotNull(parseTree, "ParseTree should not be null for CREATE TABLE business_location_i18n.");

            String tree = parseTree.toStringTree().toUpperCase();

            assertTrue(tree.contains("PEP_SCHEMA"), "Expected schema name 'pep_schema' in parse tree.");
            assertTrue(tree.contains("BUSINESS_LOCATION_I18N"), "Expected table name 'business_location_i18n' in parse tree.");

            assertTrue(tree.contains("BUSINESS_LOCATION_ID"), "Expected 'business_location_id' in parse tree.");
            assertTrue(tree.contains("LANGUAGE_ID"), "Expected 'language_id' in parse tree.");

            assertTrue(
                    (tree.contains("PRIMARY KEY") || tree.contains("PRIMARYKEY")),
                    "Expected composite PRIMARY KEY in parse tree."
            );

            assertTrue(tree.contains("FOREIGN KEY") || tree.contains("FOREIGNKEY"),
                    "Expected FOREIGN KEY constraints in parse tree.");
            assertTrue(tree.contains("REFERENCES"), "Expected REFERENCES in parse tree.");

            assertTrue(tree.contains("FK_BUSINESS_LOCATION"),
                    "Expected FK constraint name 'fk_business_location' in parse tree.");
            assertTrue(tree.contains("FK_BUSINESS_LOCATION_LANGUAGE"),
                    "Expected FK constraint name 'fk_business_location_language' in parse tree.");

            assertTrue(tree.contains("UUID"), "Expected UUID type in parse tree.");
            assertTrue(tree.contains("TIMESTAMP"), "Expected TIMESTAMP type in parse tree.");
            assertTrue(tree.contains("VARCHAR"), "Expected VARCHAR type in parse tree.");
            assertTrue(tree.contains("DEFAULT"), "Expected DEFAULT keyword in parse tree.");

            // PostgreSQL FK action clause (best effort)
            assertTrue(tree.contains("CASCADE"), "Expected ON DELETE CASCADE in parse tree.");
        });
    }


    @Test
    void testParseCreateBgPoiTable_PostgreSQL() {
        String sql = """
            CREATE TABLE bg_poi (
                id uuid NOT NULL,
                chamber_id int4 NOT NULL,
                date_created timestamp DEFAULT CURRENT_TIMESTAMP NOT NULL,
                last_updated timestamp DEFAULT CURRENT_TIMESTAMP NOT NULL,
                recdeleted bool DEFAULT FALSE NOT NULL,
                latitude varchar(255) NOT NULL,
                longitude varchar(255) NOT NULL,
                CONSTRAINT pk_bg_poi PRIMARY KEY (id)
            );
            """;

        sqlParser.setSqlContent(sql);
        log.info("Testing PostgreSQL CREATE TABLE for 'bg_poi':\n{}", sql);

        assertDoesNotThrow(() -> {
            ParseTree parseTree = sqlParser.parseTreeFromSQL();
            assertNotNull(parseTree, "ParseTree should not be null for CREATE TABLE bg_poi.");

            String tree = parseTree.toStringTree().toUpperCase();

            // Core table/columns
            assertTrue(tree.contains("BG_POI"), "Expected table name 'bg_poi' in parse tree.");
            assertTrue(tree.contains("ID"), "Expected 'id' column in parse tree.");
            assertTrue(tree.contains("CHAMBER_ID"), "Expected 'chamber_id' column in parse tree.");
            assertTrue(tree.contains("DATE_CREATED"), "Expected 'date_created' column in parse tree.");
            assertTrue(tree.contains("LAST_UPDATED"), "Expected 'last_updated' column in parse tree.");
            assertTrue(tree.contains("RECDELETED"), "Expected 'recdeleted' column in parse tree.");
            assertTrue(tree.contains("LATITUDE"), "Expected 'latitude' column in parse tree.");
            assertTrue(tree.contains("LONGITUDE"), "Expected 'longitude' column in parse tree.");

            // Types present in this SQL
            assertTrue(tree.contains("UUID"), "Expected 'UUID' type in parse tree.");
            assertTrue(tree.contains("INT4") || tree.contains("INT"), "Expected 'int4' (or 'int') type in parse tree.");
            assertTrue(tree.contains("TIMESTAMP"), "Expected 'TIMESTAMP' type in parse tree.");
            assertTrue(tree.contains("BOOL") || tree.contains("BOOLEAN"), "Expected 'bool/boolean' type in parse tree.");
            assertTrue(tree.contains("VARCHAR"), "Expected 'VARCHAR' type in parse tree.");

            // Constraints / keywords present in this SQL
            assertTrue(
                    (tree.contains("PRIMARY") && tree.contains("KEY")) || tree.contains("PRIMARYKEY"),
                    "Expected 'PRIMARY KEY' in parse tree."
            );
            assertTrue(
                    tree.contains("NOT NULL") || tree.contains("NOTNULL"),
                    "Expected 'NOT NULL' in parse tree."
            );
            assertTrue(tree.contains("DEFAULT"), "Expected 'DEFAULT' keyword in parse tree.");

            // Defaults specifically (DEFAULT now(), DEFAULT false)
            assertTrue(tree.contains("FALSE"), "Expected boolean default 'false' in parse tree.");

            // Sanity: ensure we did not accidentally expect FK syntax
            assertFalse(tree.contains("FOREIGN KEY") || tree.contains("FOREIGNKEY"),
                    "Did not expect 'FOREIGN KEY' in parse tree for this SQL.");
            assertFalse(tree.contains("REFERENCES"),
                    "Did not expect 'REFERENCES' in parse tree for this SQL.");
        });
    }

    @Test
    void testParseCreateTriggerStatement_PostgreSQL() {
        String sql = """
                CREATE TRIGGER trg_department_set_updated_at
                BEFORE UPDATE ON department
                FOR EACH ROW
                EXECUTE FUNCTION set_updated_at();
                """;

        sqlParser.setSqlContent(sql);
        log.info("Testing PostgreSQL CREATE TRIGGER for 'department':\n{}", sql);

        assertDoesNotThrow(() -> {
            ParseTree parseTree = sqlParser.parseTreeFromSQL();
            assertNotNull(parseTree, "ParseTree should not be null for CREATE TRIGGER statement.");

            String tree = parseTree.toStringTree().toUpperCase();

            // PostgreSQL-specific trigger assertions
            assertTrue(tree.contains("TRIGGER"), "Expected 'TRIGGER' keyword.");
            assertTrue(tree.contains("BEFORE"), "Expected 'BEFORE' keyword.");
            assertTrue(tree.contains("UPDATE"), "Expected 'UPDATE' keyword.");
            assertTrue(tree.contains("ON"), "Expected 'ON' keyword.");
            assertTrue(tree.contains("DEPARTMENT"), "Expected table name 'department'.");
            assertTrue(tree.contains("FOR EACH ROW"), "Expected 'FOR EACH ROW' clause.");
            assertTrue(tree.contains("EXECUTE FUNCTION"), "Expected 'EXECUTE FUNCTION' clause.");
            assertTrue(tree.contains("SET_UPDATED_AT"), "Expected function name 'set_updated_at'.");
        });
    }

    @Test
    void testParseCreateProfessionI18nTable_PostgreSQL_WithFkConstraints() {
        String sql = """
            CREATE TABLE pep_schema.professioni18n (
                profession_id uuid NOT NULL,
                language_id uuid NOT NULL,
                recdeleted bool DEFAULT FALSE NOT NULL,
                description varchar(500) NOT NULL,
                date_created timestamp DEFAULT CURRENT_TIMESTAMP NULL,
                last_updated timestamp DEFAULT CURRENT_TIMESTAMP NULL,
                chamber_i18n_id int4 NULL,
                CONSTRAINT pk_profession_i18n PRIMARY KEY (profession_id, language_id),
                CONSTRAINT uk_chamber_profession_i18n UNIQUE (profession_id, chamber_i18n_id),
                CONSTRAINT fk_profession_i18n FOREIGN KEY (profession_id) REFERENCES pep_schema.profession(id),
                CONSTRAINT fk_profession_i18n_lang FOREIGN KEY (language_id) REFERENCES pep_schema.languages(id)
            );
            """;

        sqlParser.setSqlContent(sql);
        log.info("Testing PostgreSQL CREATE TABLE for 'pep_schema.professioni18n' with FKs:\n{}", sql);

        assertDoesNotThrow(() -> {
            ParseTree parseTree = sqlParser.parseTreeFromSQL();
            assertNotNull(parseTree, "ParseTree should not be null for CREATE TABLE professioni18n.");

            String tree = parseTree.toStringTree().toUpperCase();

            assertTrue(tree.contains("PEP_SCHEMA"), "Expected schema name 'pep_schema' in parse tree.");
            assertTrue(tree.contains("PROFESSIONI18N"), "Expected table name 'professioni18n' in parse tree.");

            assertTrue(tree.contains("PRIMARY KEY"), "Expected 'PRIMARY KEY' in parse tree.");
            assertTrue(tree.contains("UNIQUE"), "Expected 'UNIQUE' in parse tree.");

            assertTrue(tree.contains("FOREIGN KEY"), "Expected 'FOREIGN KEY' in parse tree.");
            assertTrue(tree.contains("REFERENCES"), "Expected 'REFERENCES' in parse tree.");

            assertTrue(tree.contains("FK_PROFESSION_I18N"), "Expected FK constraint name 'fk_profession_i18n' in parse tree.");
            assertTrue(tree.contains("FK_PROFESSION_I18N_LANG"), "Expected FK constraint name 'fk_profession_i18n_lang' in parse tree.");
        });
    }

    @Test
    void testParseCreateUserTable_PostgreSQL() {
        String sql = """
                CREATE TABLE user (
                    user_id SERIAL PRIMARY KEY,
                    username VARCHAR(50) NOT NULL UNIQUE,
                    password VARCHAR(255) NOT NULL,
                    email VARCHAR(100) NOT NULL UNIQUE,
                    full_name VARCHAR(100) NOT NULL,
                    department_id INT,
                    role VARCHAR(50) NOT NULL,
                    supervisor_id INT,
                    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                    FOREIGN KEY (department_id) REFERENCES department(department_id),
                    FOREIGN KEY (supervisor_id) REFERENCES user(user_id)
                );
                """;

        sqlParser.setSqlContent(sql);
        log.info("Testing PostgreSQL CREATE TABLE for 'user':\n{}", sql);

        assertDoesNotThrow(() -> {
            ParseTree parseTree = sqlParser.parseTreeFromSQL();
            assertNotNull(parseTree, "ParseTree should not be null for CREATE TABLE user.");

            String tree = parseTree.toStringTree().toUpperCase();

            // PostgreSQL-style assertions
            assertTrue(tree.contains("USER_ID"), "Expected 'user_id' in parse tree.");
            assertTrue(tree.contains("PRIMARY KEY"), "Expected 'PRIMARY KEY' in parse tree.");
            assertTrue(tree.contains("SERIAL"), "Expected 'SERIAL' in parse tree.");
            assertTrue(tree.contains("NOT NULL"), "Expected 'NOT NULL' in parse tree.");
            assertTrue(tree.contains("UNIQUE"), "Expected 'UNIQUE' in parse tree.");
            assertTrue(tree.contains("FOREIGN KEY"), "Expected 'FOREIGN KEY' in parse tree.");
            assertTrue(tree.contains("REFERENCES"), "Expected 'REFERENCES' in parse tree.");
            assertTrue(tree.contains("UPDATED_AT"), "Expected 'updated_at' column.");
            assertTrue(tree.contains("DEFAULT"), "Expected 'DEFAULT' keyword for timestamp columns.");
        });
    }

    @Test
    void testParseCreateRecurringPatternTable_PostgreSQL() {
        String sql = """
                CREATE TABLE recurring_pattern (
                    pattern_id SERIAL PRIMARY KEY,
                    pattern_type VARCHAR(50) NOT NULL,
                    frequency VARCHAR(50),
                    days_of_week VARCHAR(50),
                    day_of_month INT,
                    month_of_year INT,
                    end_date DATE,
                    end_after_occur INT,
                    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
                );
                """;

        sqlParser.setSqlContent(sql);
        log.info("Testing PostgreSQL CREATE TABLE for 'recurring_pattern':\n{}", sql);

        assertDoesNotThrow(() -> {
            ParseTree parseTree = sqlParser.parseTreeFromSQL();
            assertNotNull(parseTree, "ParseTree should not be null for CREATE TABLE recurring_pattern.");

            String tree = parseTree.toStringTree().toUpperCase();

            assertTrue(tree.contains("RECURRING_PATTERN"), "Expected 'recurring_pattern' in parse tree.");
            assertTrue(tree.contains("PATTERN_ID"), "Expected 'pattern_id' in parse tree.");
            assertTrue(tree.contains("PRIMARY KEY"), "Expected 'PRIMARY KEY' in parse tree.");
            assertTrue(tree.contains("SERIAL"), "Expected 'SERIAL' in parse tree.");
            assertTrue(tree.contains("PATTERN_TYPE"), "Expected 'pattern_type' in parse tree.");
            assertTrue(tree.contains("NOT NULL"), "Expected 'NOT NULL' in parse tree.");
            assertTrue(tree.contains("UPDATED_AT"), "Expected 'updated_at' column.");
            assertTrue(tree.contains("DEFAULT"), "Expected 'DEFAULT' keyword for timestamp columns.");
        });
    }

    @Test
    void testParseCreateEventTable_PostgreSQL() {
        String sql = """
                CREATE TABLE event (
                    event_id SERIAL PRIMARY KEY,
                    title VARCHAR(100) NOT NULL,
                    description TEXT,
                    start_time TIMESTAMP NOT NULL,
                    end_time TIMESTAMP NOT NULL,
                    location VARCHAR(255),
                    event_type VARCHAR(50) NOT NULL,
                    visibility_type VARCHAR(50) NOT NULL,
                    creator_id INT NOT NULL,
                    is_recurring BOOLEAN DEFAULT FALSE,
                    recur_pattern_id INT,
                    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                    FOREIGN KEY (creator_id) REFERENCES user(user_id),
                    FOREIGN KEY (recur_pattern_id) REFERENCES recurring_pattern(pattern_id)
                );
                """;

        sqlParser.setSqlContent(sql);
        log.info("Testing PostgreSQL CREATE TABLE for 'event':\n{}", sql);

        assertDoesNotThrow(() -> {
            ParseTree parseTree = sqlParser.parseTreeFromSQL();
            assertNotNull(parseTree, "ParseTree should not be null for CREATE TABLE event.");

            String tree = parseTree.toStringTree().toUpperCase();

            assertTrue(tree.contains("EVENT_ID"), "Expected 'event_id' in parse tree.");
            assertTrue(tree.contains("SERIAL"), "Expected 'SERIAL' in parse tree.");
            assertTrue(tree.contains("PRIMARY KEY"), "Expected 'PRIMARY KEY' in parse tree.");
            assertTrue(tree.contains("TITLE"), "Expected 'title' in parse tree.");
            assertTrue(tree.contains("NOT NULL"), "Expected 'NOT NULL' in parse tree.");
            assertTrue(tree.contains("TIMESTAMP"), "Expected 'TIMESTAMP' instead of 'DATETIME'.");
            assertTrue(tree.contains("BOOLEAN"), "Expected 'BOOLEAN' in parse tree.");
            assertTrue(tree.contains("DEFAULT"), "Expected 'DEFAULT' in parse tree.");
            assertTrue(tree.contains("FOREIGN KEY"), "Expected 'FOREIGN KEY' in parse tree.");
            assertTrue(tree.contains("CREATOR_ID"), "Expected 'creator_id' in parse tree.");
            assertTrue(tree.contains("RECUR_PATTERN_ID"), "Expected 'recur_pattern_id' in parse tree.");
        });
    }

    @Test
    void testParseCreateEventAssignmentTable_PostgreSQL() {
        String sql = """
                CREATE TABLE event_assignment (
                    assignment_id SERIAL PRIMARY KEY,
                    event_id INT NOT NULL,
                    user_id INT,
                    department_id INT,
                    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                    FOREIGN KEY (event_id) REFERENCES event(event_id),
                    FOREIGN KEY (user_id) REFERENCES user(user_id),
                    FOREIGN KEY (department_id) REFERENCES department(department_id)
                );
                """;

        sqlParser.setSqlContent(sql);
        log.info("Testing PostgreSQL CREATE TABLE for 'event_assignment':\n{}", sql);

        assertDoesNotThrow(() -> {
            ParseTree parseTree = sqlParser.parseTreeFromSQL();
            assertNotNull(parseTree, "ParseTree should not be null for CREATE TABLE event_assignment.");

            String tree = parseTree.toStringTree().toUpperCase();

            assertTrue(tree.contains("ASSIGNMENT_ID"), "Expected 'assignment_id' in parse tree.");
            assertTrue(tree.contains("SERIAL"), "Expected 'SERIAL' in parse tree.");
            assertTrue(tree.contains("PRIMARY KEY"), "Expected 'PRIMARY KEY' in parse tree.");
            assertTrue(tree.contains("EVENT_ID"), "Expected 'event_id' in parse tree.");
            assertTrue(tree.contains("NOT NULL"), "Expected 'NOT NULL' in parse tree.");
            assertTrue(tree.contains("USER_ID"), "Expected 'user_id' in parse tree.");
            assertTrue(tree.contains("DEPARTMENT_ID"), "Expected 'department_id' in parse tree.");
            assertTrue(tree.contains("TIMESTAMP"), "Expected 'TIMESTAMP' in parse tree.");
            assertTrue(tree.contains("DEFAULT"), "Expected 'DEFAULT' in parse tree.");
            assertTrue(tree.contains("CURRENT_TIMESTAMP"), "Expected 'CURRENT_TIMESTAMP' in parse tree.");
            assertTrue(tree.contains("FOREIGN KEY"), "Expected 'FOREIGN KEY' in parse tree.");
        });
    }

    @Test
    void testParseCreateEventExceptionTable_PostgreSQL() {
        String sql = """
                CREATE TABLE event_exception (
                    exception_id SERIAL PRIMARY KEY,
                    event_id INT NOT NULL,
                    exception_date DATE NOT NULL,
                    is_rescheduled BOOLEAN DEFAULT FALSE,
                    new_start_time TIMESTAMP,
                    new_end_time TIMESTAMP,
                    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                    FOREIGN KEY (event_id) REFERENCES event(event_id)
                );
                """;

        sqlParser.setSqlContent(sql);
        log.info("Testing PostgreSQL CREATE TABLE for 'event_exception':\n{}", sql);

        assertDoesNotThrow(() -> {
            ParseTree parseTree = sqlParser.parseTreeFromSQL();
            assertNotNull(parseTree, "ParseTree should not be null for CREATE TABLE event_exception.");

            String tree = parseTree.toStringTree().toUpperCase();

            assertTrue(tree.contains("EXCEPTION_ID"), "Expected 'exception_id' in parse tree.");
            assertTrue(tree.contains("SERIAL"), "Expected 'SERIAL' in parse tree.");
            assertTrue(tree.contains("PRIMARY KEY"), "Expected 'PRIMARY KEY' in parse tree.");
            assertTrue(tree.contains("NOT NULL"), "Expected 'NOT NULL' in parse tree.");
            assertTrue(tree.contains("BOOLEAN"), "Expected 'BOOLEAN' in parse tree.");
            assertTrue(tree.contains("DEFAULT"), "Expected 'DEFAULT' in parse tree.");
            assertTrue(tree.contains("CURRENT_TIMESTAMP"), "Expected 'CURRENT_TIMESTAMP' in parse tree.");
            assertTrue(tree.contains("FOREIGN KEY"), "Expected 'FOREIGN KEY' in parse tree.");
        });
    }

    @Test
    void testParseCreateTimeOffRequestTable_PostgreSQL() {
        String sql = """
                CREATE TABLE time_off_request (
                    request_id SERIAL PRIMARY KEY,
                    user_id INT NOT NULL,
                    start_date DATE NOT NULL,
                    end_date DATE NOT NULL,
                    type VARCHAR(50) NOT NULL,
                    status VARCHAR(50) NOT NULL,
                    supervisor_id INT,
                    reason TEXT,
                    comments TEXT,
                    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                    FOREIGN KEY (user_id) REFERENCES user(user_id),
                    FOREIGN KEY (supervisor_id) REFERENCES user(user_id)
                );
                """;

        sqlParser.setSqlContent(sql);
        log.info("Testing PostgreSQL CREATE TABLE for 'time_off_request':\n{}", sql);

        assertDoesNotThrow(() -> {
            ParseTree parseTree = sqlParser.parseTreeFromSQL();
            assertNotNull(parseTree, "ParseTree should not be null for CREATE TABLE time_off_request.");

            String tree = parseTree.toStringTree().toUpperCase();

            assertTrue(tree.contains("REQUEST_ID"), "Expected 'request_id' in parse tree.");
            assertTrue(tree.contains("SERIAL"), "Expected 'SERIAL' in parse tree.");
            assertTrue(tree.contains("PRIMARY KEY"), "Expected 'PRIMARY KEY' in parse tree.");
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
            assertTrue(tree.contains("FOREIGN KEY"), "Expected 'FOREIGN KEY' in parse tree.");
        });
    }

    @Test
    void testParseCreateHolidayTable_PostgreSQL() {
        String sql = """
            CREATE TABLE holiday (
                holiday_id SERIAL PRIMARY KEY,
                name VARCHAR(100) NOT NULL,
                description TEXT,
                holiday_date DATE NOT NULL,
                is_recurring BOOLEAN DEFAULT FALSE,
                recur_pattern_id INT,
                created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                FOREIGN KEY (recur_pattern_id) REFERENCES recurring_pattern(pattern_id)
            );
            """;

        sqlParser.setSqlContent(sql);
        log.info("Testing PostgreSQL CREATE TABLE for 'holiday':\n{}", sql);

        assertDoesNotThrow(() -> {
            ParseTree tree = sqlParser.parseTreeFromSQL();
            assertNotNull(tree, "Parse tree should not be null.");
        });
    }

    @Test
    void testParseCreateDepartmentDayOffTable_PostgreSQL() {
        String sql = """
            CREATE TABLE department_day_off (
                day_off_id SERIAL PRIMARY KEY,
                department_id INT NOT NULL,
                name VARCHAR(100) NOT NULL,
                description TEXT,
                day_off_date DATE NOT NULL,
                is_recurring BOOLEAN DEFAULT FALSE,
                recur_pattern_id INT,
                created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                FOREIGN KEY (department_id) REFERENCES department(department_id),
                FOREIGN KEY (recur_pattern_id) REFERENCES recurring_pattern(pattern_id)
            );
            """;

        sqlParser.setSqlContent(sql);
        log.info("Testing PostgreSQL CREATE TABLE for 'department_day_off':\n{}", sql);

        assertDoesNotThrow(() -> {
            ParseTree parseTree = sqlParser.parseTreeFromSQL();
            assertNotNull(parseTree, "ParseTree should not be null for CREATE TABLE department_day_off.");

            String tree = parseTree.toStringTree().toUpperCase();

            assertTrue(tree.contains("DAY_OFF_ID"), "Expected 'day_off_id' in parse tree.");
            assertTrue(tree.contains("SERIAL"), "Expected 'SERIAL' in parse tree.");
            assertTrue(tree.contains("PRIMARY KEY"), "Expected 'PRIMARY KEY' in parse tree.");
            assertTrue(tree.contains("DEPARTMENT_ID"), "Expected 'department_id' in parse tree.");
            assertTrue(tree.contains("NAME"), "Expected 'name' in parse tree.");
            assertTrue(tree.contains("DESCRIPTION"), "Expected 'description' in parse tree.");
            assertTrue(tree.contains("DAY_OFF_DATE"), "Expected 'day_off_date' in parse tree.");
            assertTrue(tree.contains("IS_RECURRING"), "Expected 'is_recurring' in parse tree.");
            assertTrue(tree.contains("BOOLEAN"), "Expected 'BOOLEAN' in parse tree.");
            assertTrue(tree.contains("DEFAULT"), "Expected 'DEFAULT' in parse tree.");
            assertTrue(tree.contains("CURRENT_TIMESTAMP"), "Expected 'CURRENT_TIMESTAMP' in parse tree.");
            assertTrue(tree.contains("RECUR_PATTERN_ID"), "Expected 'recur_pattern_id' in parse tree.");
            assertTrue(tree.contains("FOREIGN KEY"), "Expected 'FOREIGN KEY' in parse tree.");
        });
    }

    @Test
    void testParseCreateAbsenceTable_PostgreSQL() {
        String sql = """
                CREATE TABLE absence (
                    absence_id SERIAL PRIMARY KEY,
                    user_id INT NOT NULL,
                    start_time TIMESTAMP NOT NULL,
                    end_time TIMESTAMP NOT NULL,
                    reason TEXT,
                    is_notification BOOLEAN DEFAULT FALSE,
                    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                    FOREIGN KEY (user_id) REFERENCES user(user_id)
                );
                """;

        sqlParser.setSqlContent(sql);
        log.info("Testing PostgreSQL CREATE TABLE for 'absence':\n{}", sql);

        assertDoesNotThrow(() -> {
            ParseTree parseTree = sqlParser.parseTreeFromSQL();
            assertNotNull(parseTree, "ParseTree should not be null for CREATE TABLE absence.");

            String tree = parseTree.toStringTree().toUpperCase();

            assertTrue(tree.contains("ABSENCE_ID"), "Expected 'absence_id' in parse tree.");
            assertTrue(tree.contains("SERIAL"), "Expected 'SERIAL' in parse tree.");
            assertTrue(tree.contains("PRIMARY KEY"), "Expected 'PRIMARY KEY' in parse tree.");
            assertTrue(tree.contains("USER_ID"), "Expected 'user_id' in parse tree.");
            assertTrue(tree.contains("START_TIME"), "Expected 'start_time' in parse tree.");
            assertTrue(tree.contains("END_TIME"), "Expected 'end_time' in parse tree.");
            assertTrue(tree.contains("REASON"), "Expected 'reason' in parse tree.");
            assertTrue(tree.contains("IS_NOTIFICATION"), "Expected 'is_notification' in parse tree.");
            assertTrue(tree.contains("BOOLEAN"), "Expected 'BOOLEAN' in parse tree.");
            assertTrue(tree.contains("DEFAULT"), "Expected 'DEFAULT' in parse tree.");
            assertTrue(tree.contains("CURRENT_TIMESTAMP"), "Expected 'CURRENT_TIMESTAMP' in parse tree.");
            assertTrue(tree.contains("FOREIGN KEY"), "Expected 'FOREIGN KEY' in parse tree.");
        });
    }

    @Test
    void testParseAlterRecurringPatternAddEventId_PostgreSQL() {
        String sql = """
                ALTER TABLE recurring_pattern
                ADD COLUMN event_id INT,
                ADD FOREIGN KEY (event_id) REFERENCES event(event_id);
                """;

        sqlParser.setSqlContent(sql);
        log.info("Testing PostgreSQL ALTER TABLE for 'recurring_pattern':\n{}", sql);

        assertDoesNotThrow(() -> {
            ParseTree parseTree = sqlParser.parseTreeFromSQL();
            assertNotNull(parseTree, "ParseTree should not be null for ALTER TABLE recurring_pattern.");

            String tree = parseTree.toStringTree().toUpperCase();

            assertTrue(tree.contains("ALTER"), "Expected 'ALTER' in parse tree.");
            assertTrue(tree.contains("RECURRING_PATTERN"), "Expected 'recurring_pattern' in parse tree.");
            assertTrue(tree.contains("ADD"), "Expected 'ADD' in parse tree.");
            assertTrue(tree.contains("COLUMN"), "Expected 'COLUMN' keyword in parse tree.");
            assertTrue(tree.contains("EVENT_ID"), "Expected 'event_id' in parse tree.");
            assertTrue(tree.contains("FOREIGN"), "Expected 'FOREIGN' in parse tree.");
            assertTrue(tree.contains("KEY"), "Expected 'KEY' in parse tree.");
            assertTrue(tree.contains("REFERENCES"), "Expected 'REFERENCES' in parse tree.");
            assertTrue(tree.contains("EVENT"), "Expected 'event' table reference in parse tree.");
        });
    }

    @Test
    void testParseCreateEmployeeDepartmentTable_WithCompositePrimaryKeyAndFKs() {
        String sql = """
                CREATE TABLE employee_department (
                    employee_id INT NOT NULL,
                    department_id INT NOT NULL,
                    assigned_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                    assigned_by VARCHAR(100),
                    PRIMARY KEY (employee_id, department_id),
                    FOREIGN KEY (employee_id) REFERENCES employee(id),
                    FOREIGN KEY (department_id) REFERENCES department(id)
                );
                """;

        sqlParser.setSqlContent(sql);
        log.info("Testing PostgreSQL CREATE TABLE with composite primary key and two FKs:\n{}", sql);

        assertDoesNotThrow(() -> {
            ParseTree parseTree = sqlParser.parseTreeFromSQL();
            assertNotNull(parseTree, "ParseTree should not be null for CREATE TABLE employee_department.");

            String tree = parseTree.toStringTree().toUpperCase();

            assertTrue(tree.contains("EMPLOYEE_DEPARTMENT"), "Expected 'employee_department' in parse tree.");
            assertTrue(tree.contains("EMPLOYEE_ID"), "Expected 'employee_id' in parse tree.");
            assertTrue(tree.contains("DEPARTMENT_ID"), "Expected 'department_id' in parse tree.");
            assertTrue(tree.contains("PRIMARY KEY"), "Expected composite 'PRIMARY KEY' in parse tree.");
            assertTrue(tree.contains("FOREIGN KEY"), "Expected 'FOREIGN KEY' in parse tree.");
            assertTrue(tree.contains("REFERENCES"), "Expected 'REFERENCES' in parse tree.");
            assertTrue(tree.contains("EMPLOYEE"), "Expected reference to table 'employee'.");
            assertTrue(tree.contains("DEPARTMENT"), "Expected reference to table 'department'.");
            assertTrue(tree.contains("ASSIGNED_AT"), "Expected 'assigned_at' column.");
            assertTrue(tree.contains("DEFAULT"), "Expected 'DEFAULT' keyword.");
            assertTrue(tree.contains("CURRENT_TIMESTAMP"), "Expected 'CURRENT_TIMESTAMP' in parse tree.");
        });
    }

    @Test
    void testParseCreateUserProfileWithManyToMany_PostgreSQL() {
        String sql = """
            CREATE TABLE user_profile (
                profile_id SERIAL PRIMARY KEY,
                bio TEXT,
                phone VARCHAR(20),
                user_id INT MANYTOMANY,
                FOREIGN KEY (user_id) REFERENCES user(user_id)
            );
            """;

        sqlParser.setSqlContent(sql);
        log.info("Testing PostgreSQL CREATE TABLE with pseudo-constraint MANYTOMANY:\n{}", sql);

        assertDoesNotThrow(() -> {
            ParseTree parseTree = sqlParser.parseTreeFromSQL();
            assertNotNull(parseTree, "ParseTree should not be null for CREATE TABLE user_profile.");

            String tree = parseTree.toStringTree().toUpperCase();

            assertTrue(tree.contains("USER_PROFILE"), "Expected 'user_profile' in parse tree.");
            assertTrue(tree.contains("USER_ID"), "Expected 'user_id' in parse tree.");
            assertTrue(tree.contains("MANYTOMANY"), "Expected pseudo-constraint 'MANYTOMANY' in parse tree.");
            assertTrue(tree.contains("FOREIGN KEY"), "Expected 'FOREIGN KEY' in parse tree.");
            assertTrue(tree.contains("REFERENCES"), "Expected 'REFERENCES' in parse tree.");
            assertTrue(tree.contains("USER"), "Expected 'user' as referenced table.");
        });
    }

    @Test
    void testParseCreateCompanyTable_PostgreSQL_WithSelfReferenceAndFk() {
        String sql = """
        CREATE TABLE pep_schema.company (
            id uuid NOT NULL,
            chamber_id int4 NOT NULL,
            chamber_company_id numeric NULL,
            co_name varchar(1000) NOT NULL,
            contact_email varchar(100) NULL,
            date_created timestamp DEFAULT CURRENT_TIMESTAMP NULL,
            last_updated timestamp DEFAULT CURRENT_TIMESTAMP NULL,
            rec_deleted boolean DEFAULT FALSE NOT NULL,
            company_status_id uuid NULL,
            parent_company_id uuid NULL,
            CONSTRAINT pk_company PRIMARY KEY (id),
            CONSTRAINT uk_company UNIQUE (chamber_id, chamber_company_id),
            CONSTRAINT fk_company_status FOREIGN KEY (company_status_id)
                REFERENCES pep_schema.company_status(id),
            CONSTRAINT fk_company_parent FOREIGN KEY (parent_company_id)
                REFERENCES pep_schema.company(id)
        );
        """;

        sqlParser.setSqlContent(sql);
        log.info("Testing PostgreSQL CREATE TABLE for 'pep_schema.company':\n{}", sql);

        assertDoesNotThrow(() -> {
            ParseTree parseTree = sqlParser.parseTreeFromSQL();
            assertNotNull(parseTree, "ParseTree should not be null for CREATE TABLE company.");

            String tree = parseTree.toStringTree().toUpperCase();

            assertTrue(tree.contains("PEP_SCHEMA"), "Expected schema name 'pep_schema' in parse tree.");
            assertTrue(tree.contains("COMPANY"), "Expected table name 'company' in parse tree.");
            assertTrue(tree.contains("COMPANY_STATUS_ID"), "Expected 'company_status_id' in parse tree.");
            assertTrue(tree.contains("PARENT_COMPANY_ID"), "Expected 'parent_company_id' in parse tree.");

            assertTrue(tree.contains("PRIMARY KEY") || tree.contains("PRIMARYKEY"),
                    "Expected PRIMARY KEY in parse tree.");
            assertTrue(tree.contains("UNIQUE"), "Expected UNIQUE constraint in parse tree.");
            assertTrue(tree.contains("FOREIGN KEY") || tree.contains("FOREIGNKEY"),
                    "Expected FOREIGN KEY constraints in parse tree.");
            assertTrue(tree.contains("REFERENCES"), "Expected REFERENCES in parse tree.");

            assertTrue(tree.contains("FK_COMPANY_STATUS"), "Expected FK name 'fk_company_status'.");
            assertTrue(tree.contains("FK_COMPANY_PARENT"), "Expected FK name 'fk_company_parent'.");
        });
    }

    @Test
    void testParseCreateCompanyProfileI18nTable_PostgreSQL_WithCompositePkAndFkConstraints() {
        String sql = """
        CREATE TABLE pep_schema.company_profile_i18n (
            company_profile_id uuid NOT NULL,
            language_id uuid NOT NULL,
            rec_deleted boolean DEFAULT FALSE NOT NULL,
            name varchar(1000) NULL,
            address_city varchar(50) NULL,
            address_region varchar(50) NULL,
            address_street varchar(100) NULL,
            objective text NULL,
            date_created timestamp DEFAULT CURRENT_TIMESTAMP NULL,
            last_updated timestamp DEFAULT CURRENT_TIMESTAMP NULL,
            CONSTRAINT pk_company_profile_i18n PRIMARY KEY (company_profile_id, language_id),
            CONSTRAINT fk_company_profile_i18n_profile FOREIGN KEY (company_profile_id)
                REFERENCES pep_schema.company_profile(id) ON DELETE CASCADE,
            CONSTRAINT fk_company_profile_i18n_language FOREIGN KEY (language_id)
                REFERENCES pep_schema.languages(id)
        );
        """;

        sqlParser.setSqlContent(sql);
        log.info("Testing PostgreSQL CREATE TABLE for 'pep_schema.company_profile_i18n':\n{}", sql);

        assertDoesNotThrow(() -> {
            ParseTree parseTree = sqlParser.parseTreeFromSQL();
            assertNotNull(parseTree, "ParseTree should not be null for CREATE TABLE company_profile_i18n.");

            String tree = parseTree.toStringTree().toUpperCase();

            assertTrue(tree.contains("COMPANY_PROFILE_I18N"), "Expected table name 'company_profile_i18n'.");
            assertTrue(tree.contains("COMPANY_PROFILE_ID"), "Expected 'company_profile_id' in parse tree.");
            assertTrue(tree.contains("LANGUAGE_ID"), "Expected 'language_id' in parse tree.");

            assertTrue(tree.contains("PRIMARY KEY") || tree.contains("PRIMARYKEY"),
                    "Expected composite PRIMARY KEY in parse tree.");
            assertTrue(tree.contains("FOREIGN KEY") || tree.contains("FOREIGNKEY"),
                    "Expected FOREIGN KEY constraints in parse tree.");
            assertTrue(tree.contains("REFERENCES"), "Expected REFERENCES in parse tree.");
            assertTrue(tree.contains("CASCADE"), "Expected ON DELETE CASCADE in parse tree.");
        });
    }


    @Test
    void testParseCreateProfessionTable_PostgreSQL_WithParentFkAndSystemFk() {
        String sql = """
        CREATE TABLE pep_schema.profession (
            id uuid NOT NULL,
            chamber_id int4 NOT NULL,
            chamber_profession_id int4 NULL,
            parent_profession_id uuid NULL,
            profession_system_id uuid NOT NULL,
            code varchar(255) NOT NULL,
            description varchar(1000) NULL,
            date_created timestamp DEFAULT CURRENT_TIMESTAMP NULL,
            last_updated timestamp DEFAULT CURRENT_TIMESTAMP NULL,
            rec_deleted bool DEFAULT FALSE NOT NULL,
            CONSTRAINT pk_profession PRIMARY KEY (id),
            CONSTRAINT uk_profession UNIQUE (chamber_id, chamber_profession_id),
            CONSTRAINT fk_profession_parent FOREIGN KEY (parent_profession_id)
                REFERENCES pep_schema.profession(id),
            CONSTRAINT fk_profession_system FOREIGN KEY (profession_system_id)
                REFERENCES pep_schema.profession_system(id)
        );
        """;

        sqlParser.setSqlContent(sql);
        log.info("Testing PostgreSQL CREATE TABLE for 'pep_schema.profession':\n{}", sql);

        assertDoesNotThrow(() -> {
            ParseTree parseTree = sqlParser.parseTreeFromSQL();
            assertNotNull(parseTree, "ParseTree should not be null for CREATE TABLE profession.");

            String tree = parseTree.toStringTree().toUpperCase();

            assertTrue(tree.contains("PROFESSION"), "Expected table name 'profession'.");
            assertTrue(tree.contains("PARENT_PROFESSION_ID"), "Expected 'parent_profession_id'.");
            assertTrue(tree.contains("PROFESSION_SYSTEM_ID"), "Expected 'profession_system_id'.");

            assertTrue(tree.contains("PRIMARY KEY") || tree.contains("PRIMARYKEY"),
                    "Expected PRIMARY KEY in parse tree.");
            assertTrue(tree.contains("UNIQUE"), "Expected UNIQUE in parse tree.");
            assertTrue(tree.contains("FOREIGN KEY") || tree.contains("FOREIGNKEY"),
                    "Expected FOREIGN KEY constraints in parse tree.");
            assertTrue(tree.contains("FK_PROFESSION_PARENT"), "Expected FK name 'fk_profession_parent'.");
            assertTrue(tree.contains("FK_PROFESSION_SYSTEM"), "Expected FK name 'fk_profession_system'.");
        });
    }


    @Test
    void testParseCreateCompanyProfessionTable_PostgreSQL_WithUniqueAndCheckAndFks() {
        String sql = """
                CREATE TABLE pep_schema.company_profession (
                    id uuid NOT NULL,
                    chamber_id int4 NOT NULL,
                    chamber_company_profession_id int4 NULL,
                    company_id uuid NOT NULL,
                    profession_id uuid NOT NULL,
                    date_created timestamp DEFAULT CURRENT_TIMESTAMP NULL,
                    from_date timestamp NULL,
                    to_date timestamp NULL,
                    last_updated timestamp DEFAULT CURRENT_TIMESTAMP NULL,
                    rec_deleted bool DEFAULT FALSE NOT NULL,
                    notes varchar(1000) NULL,
                    CONSTRAINT pk_company_profession PRIMARY KEY (id),
                    CONSTRAINT uk_company_profession_chamber UNIQUE (chamber_id, chamber_company_profession_id),
                    CONSTRAINT uk_company_profession_unique_pair UNIQUE (company_id, profession_id, from_date),
                    CONSTRAINT ck_company_profession_dates CHECK (
                        to_date IS NULL OR from_date IS NULL OR to_date >= from_date
                    ),
                    CONSTRAINT fk_company_profession_company FOREIGN KEY (company_id)
                        REFERENCES pep_schema.company(id) ON DELETE CASCADE,
                    CONSTRAINT fk_company_profession_profession FOREIGN KEY (profession_id)
                        REFERENCES pep_schema.profession(id)
                );
                """;

        sqlParser.setSqlContent(sql);
        log.info("Testing PostgreSQL CREATE TABLE for 'pep_schema.company_profession':\n{}", sql);

        assertDoesNotThrow(() -> {
            ParseTree parseTree = sqlParser.parseTreeFromSQL();
            assertNotNull(parseTree, "ParseTree should not be null for CREATE TABLE company_profession.");

            String tree = parseTree.toStringTree().toUpperCase();

            assertTrue(tree.contains("COMPANY_PROFESSION"), "Expected table name 'company_profession'.");
            assertTrue(tree.contains("COMPANY_ID"), "Expected 'company_id' in parse tree.");
            assertTrue(tree.contains("PROFESSION_ID"), "Expected 'profession_id' in parse tree.");

            assertTrue(tree.contains("PRIMARY KEY") || tree.contains("PRIMARYKEY"),
                    "Expected PRIMARY KEY in parse tree.");
            assertTrue(tree.contains("UNIQUE"), "Expected UNIQUE constraints in parse tree.");
            assertTrue(tree.contains("CHECK"), "Expected CHECK constraint in parse tree.");
            assertTrue(tree.contains("FOREIGN KEY") || tree.contains("FOREIGNKEY"),
                    "Expected FOREIGN KEY constraints in parse tree.");
            assertTrue(tree.contains("CASCADE"), "Expected ON DELETE CASCADE in parse tree.");
        });


    }


    @Test
    void testParseCreateCompanyProfessionSystemLinkTable_PostgreSQL_WithCompositePkAndTwoCascadeFks() {
        String sql = """
        CREATE TABLE pep_schema.company_profession_system_link (
            company_id uuid NOT NULL,
            profession_system_id uuid NOT NULL,
            CONSTRAINT pk_company_prof_system_link PRIMARY KEY (company_id, profession_system_id),
            CONSTRAINT fk_cpsl_company FOREIGN KEY (company_id)
                REFERENCES pep_schema.company(id) ON DELETE CASCADE,
            CONSTRAINT fk_cpsl_prof_system FOREIGN KEY (profession_system_id)
                REFERENCES pep_schema.profession_system(id) ON DELETE CASCADE
        );
        """;

        sqlParser.setSqlContent(sql);
        log.info("Testing PostgreSQL CREATE TABLE for 'pep_schema.company_profession_system_link':\n{}", sql);

        assertDoesNotThrow(() -> {
            ParseTree parseTree = sqlParser.parseTreeFromSQL();
            assertNotNull(parseTree, "ParseTree should not be null for CREATE TABLE company_profession_system_link.");

            String tree = parseTree.toStringTree().toUpperCase();

            assertTrue(tree.contains("COMPANY_PROFESSION_SYSTEM_LINK"),
                    "Expected table name 'company_profession_system_link'.");
            assertTrue(tree.contains("COMPANY_ID"), "Expected 'company_id' in parse tree.");
            assertTrue(tree.contains("PROFESSION_SYSTEM_ID"), "Expected 'profession_system_id' in parse tree.");

            assertTrue(tree.contains("PRIMARY KEY") || tree.contains("PRIMARYKEY"),
                    "Expected composite PRIMARY KEY in parse tree.");
            assertTrue(tree.contains("FOREIGN KEY") || tree.contains("FOREIGNKEY"),
                    "Expected FOREIGN KEY constraints in parse tree.");
            assertTrue(tree.contains("CASCADE"), "Expected ON DELETE CASCADE in parse tree.");
        });
    }


    @Test
    void testParseCreateCompanyProfileLanguageTable_PostgreSQL_WithCompositePkAndTwoCascadeFks() {
        String sql = """
        CREATE TABLE pep_schema.company_profile_language (
            company_profile_id uuid NOT NULL,
            language_id uuid NOT NULL,
            CONSTRAINT pk_company_profile_language PRIMARY KEY (company_profile_id, language_id),
            CONSTRAINT fk_cpl_profile FOREIGN KEY (company_profile_id)
                REFERENCES pep_schema.company_profile(id) ON DELETE CASCADE,
            CONSTRAINT fk_cpl_language FOREIGN KEY (language_id)
                REFERENCES pep_schema.languages(id) ON DELETE CASCADE
        );
        """;

        sqlParser.setSqlContent(sql);
        log.info("Testing PostgreSQL CREATE TABLE for 'pep_schema.company_profile_language':\n{}", sql);

        assertDoesNotThrow(() -> {
            ParseTree parseTree = sqlParser.parseTreeFromSQL();
            assertNotNull(parseTree, "ParseTree should not be null for CREATE TABLE company_profile_language.");

            String tree = parseTree.toStringTree().toUpperCase();

            assertTrue(tree.contains("COMPANY_PROFILE_LANGUAGE"), "Expected table name 'company_profile_language'.");
            assertTrue(tree.contains("COMPANY_PROFILE_ID"), "Expected 'company_profile_id' in parse tree.");
            assertTrue(tree.contains("LANGUAGE_ID"), "Expected 'language_id' in parse tree.");

            assertTrue(tree.contains("PRIMARY KEY") || tree.contains("PRIMARYKEY"),
                    "Expected composite PRIMARY KEY in parse tree.");
            assertTrue(tree.contains("FOREIGN KEY") || tree.contains("FOREIGNKEY"),
                    "Expected FOREIGN KEY constraints in parse tree.");
            assertTrue(tree.contains("CASCADE"), "Expected ON DELETE CASCADE in parse tree.");
        });
    }

    @Test
    void testParseCreateCompanyStatusViewRulesTable_PostgreSQL_WithCompositePkAndCascadeFks() {
        String sql = """
        CREATE TABLE pep_schema.company_status_view_rules (
            company_status_id uuid NOT NULL,
            company_view_rules_id uuid NOT NULL,
            exclude_companies bool NULL,
            date_created timestamp DEFAULT CURRENT_TIMESTAMP NULL,
            last_updated timestamp DEFAULT CURRENT_TIMESTAMP NULL,
            CONSTRAINT pk_company_status_view_rules PRIMARY KEY (company_status_id, company_view_rules_id),
            CONSTRAINT fk_company_status_view_rules_status FOREIGN KEY (company_status_id)
                REFERENCES pep_schema.company_status(id) ON DELETE CASCADE,
            CONSTRAINT fk_company_status_view_rules_rules FOREIGN KEY (company_view_rules_id)
                REFERENCES pep_schema.company_view_rules(id) ON DELETE CASCADE
        );
        """;

        sqlParser.setSqlContent(sql);
        log.info("Testing PostgreSQL CREATE TABLE for 'pep_schema.company_status_view_rules':\n{}", sql);

        assertDoesNotThrow(() -> {
            ParseTree parseTree = sqlParser.parseTreeFromSQL();
            assertNotNull(parseTree, "ParseTree should not be null for CREATE TABLE company_status_view_rules.");

            String tree = parseTree.toStringTree().toUpperCase();

            assertTrue(tree.contains("COMPANY_STATUS_VIEW_RULES"), "Expected table name 'company_status_view_rules'.");
            assertTrue(tree.contains("COMPANY_STATUS_ID"), "Expected 'company_status_id' in parse tree.");
            assertTrue(tree.contains("COMPANY_VIEW_RULES_ID"), "Expected 'company_view_rules_id' in parse tree.");

            assertTrue(tree.contains("PRIMARY KEY") || tree.contains("PRIMARYKEY"),
                    "Expected composite PRIMARY KEY in parse tree.");
            assertTrue(tree.contains("FOREIGN KEY") || tree.contains("FOREIGNKEY"),
                    "Expected FOREIGN KEY constraints in parse tree.");
            assertTrue(tree.contains("REFERENCES"), "Expected REFERENCES in parse tree.");
            assertTrue(tree.contains("CASCADE"), "Expected ON DELETE CASCADE in parse tree.");
        });
    }

}
