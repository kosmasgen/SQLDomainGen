package com.sqldomaingen;

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;
import com.sqldomaingen.parser.PostgreSQLLexer;
import com.sqldomaingen.parser.PostgreSQLParser;
import com.sqldomaingen.parser.PostgreSQLBaseListener;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class TableParsingTest {

    private static final Logger logger = LoggerFactory.getLogger(TableParsingTest.class);

    private void parseAndValidate(String sqlScript) {
        try {
            logger.info("Starting SQL parsing test...");
            PostgreSQLLexer lexer = new PostgreSQLLexer(CharStreams.fromString(sqlScript));
            CommonTokenStream tokens = new CommonTokenStream(lexer);
            logger.debug("Token stream created.");

            // 🔍 Logging κάθε token για debugging
            tokens.fill();
            for (Token token : tokens.getTokens()) {
                logger.debug("Token: '{}' -> Type: {}", token.getText(), lexer.getVocabulary().getSymbolicName(token.getType()));
            }

            PostgreSQLParser parser = new PostgreSQLParser(tokens);

            // 🔍 Debugging: Ενεργοποιούμε ANTLR tracing
            parser.setTrace(true);

            ParseTree tree = parser.sqlScript();

            logger.info("🔍 FULL PARSE TREE: \n{}", tree.toStringTree(parser));
            logger.debug("ParseTree generated: {}", tree.toStringTree(parser));

            assertNotNull(tree, "The ParseTree should not be null.");

            ParseTreeWalker walker = new ParseTreeWalker();
            PostgreSQLBaseListener listener = new PostgreSQLBaseListener() {
                @Override
                public void enterCreateTableStatement(PostgreSQLParser.CreateTableStatementContext ctx) {
                    assertNotNull(ctx, "The CreateTableStatementContext should not be null.");
                    if (ctx.tableName() == null || ctx.tableName().size() == 0) {
                        logger.error("Table name not found in context.");
                        fail("Table name should not be null.");
                    }
                    String tableName = ctx.tableName(0).getText();
                    assertNotNull(tableName, "Table name should not be null.");
                    logger.info("Table Name: {}", tableName);

                    // 🔍 Debugging για το tableConstraint
                    if (ctx.tableConstraint() != null) {
                        logger.info("🔍 Table Constraints Count: {}", ctx.tableConstraint().size());
                        ctx.tableConstraint().forEach(constraintCtx -> {
                            logger.info("➡️ Table Constraint Found: {}", constraintCtx.getText());
                        });
                    } else {
                        logger.warn("⚠️ No table constraints found in CreateTableStatementContext!");
                    }
                }
            };

            walker.walk(listener, tree);
            logger.info("SQL parsing test completed successfully.");
        } catch (Exception e) {
            logger.error("Parsing failed: {}", e.getMessage(), e);
            fail("Parsing failed: " + e.getMessage());
        }
    }

    @Test
    void testParseDepartmentTable() {
        String sql = """
        CREATE TABLE department (
            department_id SERIAL PRIMARY KEY,
            name VARCHAR(100) NOT NULL,
            description TEXT,
            parent_dept_id INT,
            created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
            updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
            FOREIGN KEY (parent_dept_id) REFERENCES department(department_id)
        );
    """;

        parseAndValidate(sql);
    }

    @Test
    void testParseUserTable() {
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

        parseAndValidate(sql);
    }

    @Test
    void testParseRecurringPatternTable() {
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

        parseAndValidate(sql);
    }


    @Test
    void testParseEventTable() {
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

        parseAndValidate(sql);
    }

    @Test
    void testParseEventAssignmentTable() {
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

        parseAndValidate(sql);
    }

    @Test
    void testParseEventExceptionTable() {
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

        parseAndValidate(sql);
    }

    @Test
    void testParseTimeOffRequestTable() {
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

        parseAndValidate(sql);
    }

    @Test
    void testParseHolidayTable() {
        String sql = """
    CREATE TABLE holiday (
        holiday_id SERIAL PRIMARY KEY,
        name VARCHAR(100) NOT NULL,
        description TEXT,
        date DATE NOT NULL,
        is_recurring BOOLEAN DEFAULT FALSE,
        recur_pattern_id INT,
        created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
        updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
        FOREIGN KEY (recur_pattern_id) REFERENCES recurring_pattern(pattern_id)
    );
    """;

        parseAndValidate(sql);
    }

    @Test
    void testParseDepartmentDayOffTable() {
        String sql = """
    CREATE TABLE department_day_off (
        day_off_id SERIAL PRIMARY KEY,
        department_id INT NOT NULL,
        name VARCHAR(100) NOT NULL,
        description TEXT,
        date DATE NOT NULL,
        is_recurring BOOLEAN DEFAULT FALSE,
        recur_pattern_id INT,
        created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
        updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
        FOREIGN KEY (department_id) REFERENCES department(department_id),
        FOREIGN KEY (recur_pattern_id) REFERENCES recurring_pattern(pattern_id)
    );
    """;

        parseAndValidate(sql);
    }


    @Test
    void testParseAbsenceTable() {
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

        parseAndValidate(sql);
    }


    @Test
    void testParseAlterRecurringPatternAddEventId() {
        String sql = """
    ALTER TABLE recurring_pattern
    ADD COLUMN event_id INT,
    ADD FOREIGN KEY (event_id) REFERENCES event(event_id);
    """;

        parseAndValidate(sql);
    }


    @Test
    void testParseCreateTriggerStatement() {
        String sql = """
    CREATE TRIGGER trg_department_set_updated_at
    BEFORE UPDATE ON department
    FOR EACH ROW
    EXECUTE FUNCTION set_updated_at();
    """;

        parseAndValidate(sql);
    }

    @Test
    void testParseEmployeeDepartmentTable_WithCompositePrimaryKeyAndFKs() {
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

        parseAndValidate(sql);
    }



}

