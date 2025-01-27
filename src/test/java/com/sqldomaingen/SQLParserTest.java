package com.sqldomaingen;

import com.sqldomaingen.parser.SQLParser;
import org.antlr.v4.runtime.TokenStream;
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
    void testParseCreateTableWithCompositePrimaryKey() {
        sqlParser.setSqlContent(
                "CREATE TABLE composite_key_test (\n" +
                        "id INT,\n" +
                        "dept_id INT,\n" +
                        "PRIMARY KEY (id, dept_id)\n" +
                        ");"
        );

        assertDoesNotThrow(() -> {
            ParseTree parseTree = sqlParser.parseTreeFromSQL();
            assertNotNull(parseTree, "ParseTree should not be null for CREATE TABLE with composite primary key.");
            logger.info("ParseTree generated successfully for CREATE TABLE with composite primary key.");
        });
    }

    @Test
    void testParseCreateTableWithMultipleForeignKeys() {
        sqlParser.setSqlContent(
                "CREATE TABLE multi_foreign_key_test (\n" +
                        "id INT PRIMARY KEY,\n" +
                        "dept_id INT,\n" +
                        "manager_id INT,\n" +
                        "FOREIGN KEY (dept_id) REFERENCES department(id),\n" +
                        "FOREIGN KEY (manager_id) REFERENCES manager(id)\n" +
                        ");"
        );

        assertDoesNotThrow(() -> {
            ParseTree parseTree = sqlParser.parseTreeFromSQL();
            assertNotNull(parseTree, "ParseTree should not be null for CREATE TABLE with multiple foreign keys.");
            logger.info("ParseTree generated successfully for CREATE TABLE with multiple foreign keys.");
        });
    }

    @Test
    void testParseCreateTableWithUniqueAndNotNullConstraints() {
        sqlParser.setSqlContent(
                "CREATE TABLE unique_not_null_test (\n" +
                        "id INT UNIQUE NOT NULL,\n" +
                        "email VARCHAR(255) UNIQUE NOT NULL\n" +
                        ");"
        );

        assertDoesNotThrow(() -> {
            ParseTree parseTree = sqlParser.parseTreeFromSQL();
            assertNotNull(parseTree, "ParseTree should not be null for CREATE TABLE with UNIQUE and NOT NULL constraints.");
            logger.info("ParseTree generated successfully for CREATE TABLE with UNIQUE and NOT NULL constraints.");
        });
    }

    @Test
    void testParseCreateTableWithMultipleDefaultConstraints() {
        sqlParser.setSqlContent(
                "CREATE TABLE multiple_default_test (\n" +
                        "id INT,\n" +
                        "status VARCHAR(20) DEFAULT 'active',\n" +
                        "created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP\n" +
                        ");"
        );

        assertDoesNotThrow(() -> {
            ParseTree parseTree = sqlParser.parseTreeFromSQL();
            assertNotNull(parseTree, "ParseTree should not be null for CREATE TABLE with multiple DEFAULT constraints.");
            logger.info("ParseTree generated successfully for CREATE TABLE with multiple DEFAULT constraints.");
        });
    }

    @Test
    void testParseCreateTableWithComplexCheckConstraint() {
        sqlParser.setSqlContent(
                "CREATE TABLE complex_check_test (\n" +
                        "id INT,\n" +
                        "salary DECIMAL(10, 2),\n" +
                        "CHECK (salary > 0 AND salary <= 100000)\n" +
                        ");"
        );

        assertDoesNotThrow(() -> {
            ParseTree parseTree = sqlParser.parseTreeFromSQL();
            assertNotNull(parseTree, "ParseTree should not be null for CREATE TABLE with complex CHECK constraint.");
            logger.info("ParseTree generated successfully for CREATE TABLE with complex CHECK constraint.");
        });
    }

    @Test
    void testParseCreateTableWithMultipleConstraints() {
        sqlParser.setSqlContent(
                "CREATE TABLE multiple_constraints_test (\n" +
                        "id INT PRIMARY KEY,\n" +
                        "email VARCHAR(255) UNIQUE NOT NULL,\n" +
                        "salary DECIMAL(10, 2) CHECK (salary > 0),\n" +
                        "dept_id INT,\n" +
                        "FOREIGN KEY (dept_id) REFERENCES department(id)\n" +
                        ");"
        );

        assertDoesNotThrow(() -> {
            ParseTree parseTree = sqlParser.parseTreeFromSQL();
            assertNotNull(parseTree, "ParseTree should not be null for CREATE TABLE with multiple constraints.");
            logger.info("ParseTree generated successfully for CREATE TABLE with multiple constraints.");
        });
    }

    @Test
    void testParseCreateTableWithAutoIncrementColumn() {
        sqlParser.setSqlContent(
                "CREATE TABLE auto_increment_test (\n" +
                        "id INT AUTO_INCREMENT PRIMARY KEY,\n" +
                        "name VARCHAR(100)\n" +
                        ");"
        );

        assertDoesNotThrow(() -> {
            ParseTree parseTree = sqlParser.parseTreeFromSQL();
            assertNotNull(parseTree, "ParseTree should not be null for CREATE TABLE with AUTO_INCREMENT column.");
            logger.info("ParseTree generated successfully for CREATE TABLE with AUTO_INCREMENT column.");
        });
    }

    @Test
    void testParseCreateTableWithComplexForeignKey() {
        sqlParser.setSqlContent(
                "CREATE TABLE complex_foreign_key_test (\n" +
                        "id INT PRIMARY KEY,\n" +
                        "parent_id INT,\n" +
                        "FOREIGN KEY (parent_id) REFERENCES parent_table(parent_id) ON DELETE CASCADE ON UPDATE SET NULL\n" +
                        ");"
        );

        assertDoesNotThrow(() -> {
            ParseTree parseTree = sqlParser.parseTreeFromSQL();
            assertNotNull(parseTree, "ParseTree should not be null for CREATE TABLE with complex FOREIGN KEY.");
            logger.info("ParseTree generated successfully for CREATE TABLE with complex FOREIGN KEY.");
        });
    }
    @Test
    void testParseCreateTableWithTriggers() {
        sqlParser.setSqlContent(
                "CREATE TABLE trigger_test (\n" +
                        "id SERIAL PRIMARY KEY,\n" +
                        "name VARCHAR(100)\n" +
                        ");\n" +
                        "CREATE TRIGGER before_insert_trigger\n" +
                        "BEFORE INSERT ON trigger_test\n" +
                        "FOR EACH ROW EXECUTE FUNCTION log_insert();"
        );

        assertDoesNotThrow(() -> {
            ParseTree parseTree = sqlParser.parseTreeFromSQL();
            assertNotNull(parseTree, "ParseTree should not be null for CREATE TABLE with TRIGGER.");
            logger.info("ParseTree generated successfully for CREATE TABLE with TRIGGER.");
        });
    }

    @Test
    void testParseCreateTableWithPolicies() {
        sqlParser.setSqlContent(
                "CREATE TABLE policy_test (\n" +
                        "id SERIAL PRIMARY KEY,\n" +
                        "name VARCHAR(100)\n" +
                        ");\n" +
                        "CREATE POLICY select_policy ON policy_test\n" +
                        "FOR SELECT USING (true);"
        );

        assertDoesNotThrow(() -> {
            ParseTree parseTree = sqlParser.parseTreeFromSQL();
            assertNotNull(parseTree, "ParseTree should not be null for CREATE TABLE with POLICY.");
            logger.info("ParseTree generated successfully for CREATE TABLE with POLICY.");
        });
    }

    @Test
    void testParseCreateTableWithOverlappingConstraints() {
        sqlParser.setSqlContent(
                "CREATE TABLE overlapping_constraints_test (\n" +
                        "id SERIAL PRIMARY KEY,\n" +
                        "email VARCHAR(255) UNIQUE NOT NULL,\n" +
                        "CHECK (id > 0),\n" +
                        "CHECK (email LIKE '%@example.com')\n" +
                        ");"
        );

        assertDoesNotThrow(() -> {
            ParseTree parseTree = sqlParser.parseTreeFromSQL();
            assertNotNull(parseTree, "ParseTree should not be null for CREATE TABLE with overlapping constraints.");
            logger.info("ParseTree generated successfully for CREATE TABLE with overlapping constraints.");
        });
    }

    @Test
    void testParseCreateTableWithComplexTriggerAndPolicy() {
        sqlParser.setSqlContent(
                "CREATE TABLE complex_test (\n" +
                        "id SERIAL PRIMARY KEY,\n" +
                        "name VARCHAR(100)\n" +
                        ");\n" +
                        "CREATE TRIGGER update_trigger\n" +
                        "AFTER UPDATE ON complex_test\n" +
                        "FOR EACH ROW EXECUTE FUNCTION log_update();\n" +
                        "CREATE POLICY update_policy ON complex_test\n" +
                        "FOR UPDATE USING (name IS NOT NULL);"
        );

        assertDoesNotThrow(() -> {
            ParseTree parseTree = sqlParser.parseTreeFromSQL();
            assertNotNull(parseTree, "ParseTree should not be null for CREATE TABLE with complex TRIGGER and POLICY.");
            logger.info("ParseTree generated successfully for CREATE TABLE with complex TRIGGER and POLICY.");
        });
    }
    @Test
    void testParseCreateTableWithPartitioning() {
        sqlParser.setSqlContent(
                "CREATE TABLE partitioned_table (\n" +
                        "id SERIAL PRIMARY KEY,\n" +
                        "name VARCHAR(100) NOT NULL\n" +
                        ") PARTITION BY RANGE (id);"
        );

        assertDoesNotThrow(() -> {
            ParseTree parseTree = sqlParser.parseTreeFromSQL();
            assertNotNull(parseTree, "ParseTree should not be null for CREATE TABLE with partitioning.");
            logger.info("ParseTree generated successfully for CREATE TABLE with PARTITION BY.");
        });
    }

    @Test
    void testParseCreateTableWithExclusionConstraint() {
        sqlParser.setSqlContent(
                "CREATE TABLE exclusion_test (\n" +
                        "id SERIAL PRIMARY KEY,\n" +
                        "range INT4RANGE,\n" +
                        "EXCLUDE USING gist (range WITH &&)\n" +
                        ");"
        );

        assertDoesNotThrow(() -> {
            ParseTree parseTree = sqlParser.parseTreeFromSQL();
            assertNotNull(parseTree, "ParseTree should not be null for CREATE TABLE with exclusion constraint.");
            logger.info("ParseTree generated successfully for CREATE TABLE with EXCLUDE constraint.");
        });
    }

    @Test
    void testParseCreateTableWithComplexTrigger() {
        sqlParser.setSqlContent(
                "CREATE TABLE trigger_test (\n" +
                        "id SERIAL PRIMARY KEY,\n" +
                        "name VARCHAR(100)\n" +
                        ");\n" +
                        "CREATE TRIGGER update_timestamp\n" +
                        "AFTER UPDATE ON trigger_test\n" +
                        "FOR EACH ROW EXECUTE FUNCTION update_modified_column();"
        );

        assertDoesNotThrow(() -> {
            ParseTree parseTree = sqlParser.parseTreeFromSQL();
            assertNotNull(parseTree, "ParseTree should not be null for CREATE TABLE with complex TRIGGER.");
            logger.info("ParseTree generated successfully for CREATE TABLE with complex TRIGGER.");
        });
    }

    @Test
    void testParseCreateTableWithMultiplePolicies() {
        sqlParser.setSqlContent(
                "CREATE TABLE policy_test (\n" +
                        "id SERIAL PRIMARY KEY,\n" +
                        "name VARCHAR(100)\n" +
                        ");\n" +
                        "CREATE POLICY select_policy ON policy_test FOR SELECT USING (true);\n" +
                        "CREATE POLICY update_policy ON policy_test FOR UPDATE USING (name IS NOT NULL);"
        );

        assertDoesNotThrow(() -> {
            ParseTree parseTree = sqlParser.parseTreeFromSQL();
            assertNotNull(parseTree, "ParseTree should not be null for CREATE TABLE with multiple policies.");
            logger.info("ParseTree generated successfully for CREATE TABLE with multiple policies.");
        });
    }

    @Test
    void testParseCreateTableWithComplexForeignKeys() {
        sqlParser.setSqlContent(
                "CREATE TABLE complex_foreign_key (\n" +
                        "id SERIAL PRIMARY KEY,\n" +
                        "parent_id INT,\n" +
                        "CONSTRAINT fk_parent FOREIGN KEY (parent_id)\n" +
                        "REFERENCES parent_table (id)\n" +
                        "ON DELETE CASCADE ON UPDATE SET NULL\n" +
                        ");"
        );

        assertDoesNotThrow(() -> {
            ParseTree parseTree = sqlParser.parseTreeFromSQL();
            assertNotNull(parseTree, "ParseTree should not be null for CREATE TABLE with complex FOREIGN KEY.");
            logger.info("ParseTree generated successfully for CREATE TABLE with complex FOREIGN KEY.");
        });
    }

    @Test
    void testParseCreateTableWithOverlappingIndexes() {
        sqlParser.setSqlContent(
                "CREATE TABLE index_test (\n" +
                        "id SERIAL PRIMARY KEY,\n" +
                        "email VARCHAR(255) UNIQUE,\n" +
                        "name VARCHAR(100)\n" +
                        ");\n" +
                        "CREATE INDEX idx_email_name ON index_test (email, name);"
        );

        assertDoesNotThrow(() -> {
            ParseTree parseTree = sqlParser.parseTreeFromSQL();
            assertNotNull(parseTree, "ParseTree should not be null for CREATE TABLE with overlapping indexes.");
            logger.info("ParseTree generated successfully for CREATE TABLE with overlapping indexes.");
        });
    }

    @Test
    void testParseCreateTableWithComplexCheckAndDefaultConstraints() {
        sqlParser.setSqlContent(
                "CREATE TABLE check_default_test (\n" +
                        "id SERIAL PRIMARY KEY,\n" +
                        "salary DECIMAL(10, 2) DEFAULT 1000.0,\n" +
                        "age INT CHECK (age >= 18 AND age <= 65)\n" +
                        ");"
        );

        assertDoesNotThrow(() -> {
            ParseTree parseTree = sqlParser.parseTreeFromSQL();
            assertNotNull(parseTree, "ParseTree should not be null for CREATE TABLE with complex CHECK and DEFAULT constraints.");
            logger.info("ParseTree generated successfully for CREATE TABLE with complex CHECK and DEFAULT constraints.");
        });
    }
}
