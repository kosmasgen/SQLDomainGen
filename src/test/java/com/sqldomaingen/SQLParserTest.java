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
    void testParseDataTypes() {
        sqlParser.setSqlContent(
                "CREATE TABLE data_types_test (\n" +
                        "id INT PRIMARY KEY,\n" +
                        "name VARCHAR(100),\n" +
                        "price DECIMAL(10, 2),\n" +
                        "created_at TIMESTAMP\n" +
                        ");"
        );

        assertDoesNotThrow(() -> {
            ParseTree parseTree = sqlParser.parseTreeFromSQL();
            assertNotNull(parseTree, "ParseTree should not be null for CREATE TABLE with various data types.");
            logger.info("ParseTree generated for CREATE TABLE with data types: {}", parseTree.toStringTree());
            assertTrue(parseTree.toStringTree().contains("INT"));
            assertTrue(parseTree.toStringTree().contains("VARCHAR"));
            assertTrue(parseTree.toStringTree().contains("DECIMAL"));
            assertTrue(parseTree.toStringTree().contains("TIMESTAMP"));
        });
    }

    @Test
    void testParseConstraints() {
        sqlParser.setSqlContent(
                "CREATE TABLE constraints_test (\n" +
                        "id INT PRIMARY KEY,\n" +
                        "email VARCHAR(255) UNIQUE NOT NULL,\n" +
                        "salary DECIMAL(10, 2) DEFAULT 1000.0,\n" +
                        "age INT CHECK (age >= 18 AND age <= 65)\n" +
                        ");"
        );

        assertDoesNotThrow(() -> {
            ParseTree parseTree = sqlParser.parseTreeFromSQL();
            assertNotNull(parseTree, "ParseTree should not be null for CREATE TABLE with constraints.");
            logger.info("ParseTree generated for CREATE TABLE with constraints: {}", parseTree.toStringTree());
            assertTrue(parseTree.toStringTree().contains("PRIMARY KEY"));
            assertTrue(parseTree.toStringTree().contains("UNIQUE"));
            assertTrue(parseTree.toStringTree().contains("NOT NULL"));
            assertTrue(parseTree.toStringTree().contains("DEFAULT"));
            assertTrue(parseTree.toStringTree().contains("CHECK"));
        });
    }

    @Test
    void testParseComplexFeatures() {
        sqlParser.setSqlContent(
                "CREATE TABLE complex_features_test (\n" +
                        "id SERIAL PRIMARY KEY,\n" +
                        "name VARCHAR(100)\n" +
                        ");\n" +
                        "CREATE TRIGGER update_trigger\n" +
                        "AFTER UPDATE ON complex_features_test\n" +
                        "FOR EACH ROW EXECUTE FUNCTION log_update();\n" +
                        "CREATE POLICY update_policy ON complex_features_test\n" +
                        "FOR UPDATE USING (name IS NOT NULL);\n" +
                        "CREATE TABLE partitioned_table (\n" +
                        "id SERIAL PRIMARY KEY,\n" +
                        "data VARCHAR(255)\n" +
                        ") PARTITION BY RANGE (id);"
        );

        assertDoesNotThrow(() -> {
            ParseTree parseTree = sqlParser.parseTreeFromSQL();
            assertNotNull(parseTree, "ParseTree should not be null for complex features like triggers, policies, and partitioning.");
            logger.info("ParseTree generated for complex features: {}", parseTree.toStringTree());
            assertTrue(parseTree.toStringTree().contains("TRIGGER"));
            assertTrue(parseTree.toStringTree().contains("POLICY"));
            assertTrue(parseTree.toStringTree().contains("PARTITION BY"));
        });
    }


    @Test
    void testParseCreateTableWithComplexTriggerAndPolicy() {
        String sql = "CREATE TABLE complex_test (\n" +
                "id SERIAL PRIMARY KEY,\n" +
                "name VARCHAR(100)\n" +
                ");\n" +
                "CREATE TRIGGER update_trigger\n" +
                "AFTER UPDATE ON complex_test\n" +
                "FOR EACH ROW EXECUTE FUNCTION log_update();\n" +
                "CREATE POLICY update_policy ON complex_test\n" +
                "FOR UPDATE USING (name IS NOT NULL);";
        sqlParser.setSqlContent(sql);
        logger.info("Testing CREATE TABLE with complex TRIGGER and POLICY:\n{}", sql);

        assertDoesNotThrow(() -> {
            ParseTree parseTree = sqlParser.parseTreeFromSQL();
            assertNotNull(parseTree, "ParseTree should not be null for CREATE TABLE with complex TRIGGER and POLICY.");
            logger.info("ParseTree generated successfully for CREATE TABLE with complex TRIGGER and POLICY.");
        });
    }

    @Test
    void testParseCreateTableWithPartitioning() {
        String sql = "CREATE TABLE partitioned_table (\n" +
                "id SERIAL PRIMARY KEY,\n" +
                "name VARCHAR(100) NOT NULL\n" +
                ") PARTITION BY RANGE (id);";
        sqlParser.setSqlContent(sql);
        logger.info("Testing CREATE TABLE with partitioning:\n{}", sql);

        assertDoesNotThrow(() -> {
            ParseTree parseTree = sqlParser.parseTreeFromSQL();
            assertNotNull(parseTree, "ParseTree should not be null for CREATE TABLE with partitioning.");
            logger.info("ParseTree generated successfully for CREATE TABLE with PARTITION BY.");
        });
    }

    @Test
    void testParseCreateTableWithComplexCheckConstraint() {
        String sql = "CREATE TABLE complex_check_test (\n" +
                "id INT,\n" +
                "salary DECIMAL(10, 2),\n" +
                "CHECK (salary > 0 AND salary <= 100000)\n" +
                ");";
        sqlParser.setSqlContent(sql);
        logger.info("Testing CREATE TABLE with complex CHECK constraint:\n{}", sql);

        assertDoesNotThrow(() -> {
            ParseTree parseTree = sqlParser.parseTreeFromSQL();
            assertNotNull(parseTree, "ParseTree should not be null for CREATE TABLE with complex CHECK constraint.");
            logger.info("ParseTree generated successfully for CREATE TABLE with complex CHECK constraint.");
        });
    }


    @Test
    void testParseCreateTableWithUniqueAndNotNullConstraints() {
        String sql = "CREATE TABLE unique_not_null_test (\n" +
                "id INT UNIQUE NOT NULL,\n" +
                "email VARCHAR(255) UNIQUE NOT NULL\n" +
                ");";
        sqlParser.setSqlContent(sql);
        logger.info("Testing CREATE TABLE with UNIQUE and NOT NULL constraints:\n{}", sql);

        assertDoesNotThrow(() -> {
            ParseTree parseTree = sqlParser.parseTreeFromSQL();
            assertNotNull(parseTree, "ParseTree should not be null for CREATE TABLE with UNIQUE and NOT NULL constraints.");
            logger.info("ParseTree generated successfully for CREATE TABLE with UNIQUE and NOT NULL constraints.");
        });
    }

    @Test
    void testParseCreateTableWithMultipleForeignKeys() {
        String sql = "CREATE TABLE multi_foreign_key_test (\n" +
                "id INT PRIMARY KEY,\n" +
                "dept_id INT,\n" +
                "manager_id INT,\n" +
                "FOREIGN KEY (dept_id) REFERENCES department(id),\n" +
                "FOREIGN KEY (manager_id) REFERENCES manager(id)\n" +
                ");";
        sqlParser.setSqlContent(sql);
        logger.info("Testing CREATE TABLE with multiple foreign keys:\n{}", sql);

        assertDoesNotThrow(() -> {
            ParseTree parseTree = sqlParser.parseTreeFromSQL();
            assertNotNull(parseTree, "ParseTree should not be null for CREATE TABLE with multiple foreign keys.");
            logger.info("ParseTree generated successfully for CREATE TABLE with multiple foreign keys.");
        });
    }

    @Test
    void testParseInvalidSQLWithDetailedErrorMessage() {
        sqlParser.setSqlContent("CREATETABL invalid_syntax_test ( id INT PRIMARY KEY );");
        logger.info("Testing invalid SQL syntax...");

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            sqlParser.parseTreeFromSQL();
        });

        assertTrue(exception.getMessage().contains("Syntax error"), "Expected syntax error message, but got: " + exception.getMessage());
        logger.info("Test for invalid SQL passed with expected exception: {}", exception.getMessage());
    }
    @Test
    void testParseCreateTableWithCompositePrimaryKey() {
        String sql = "CREATE TABLE composite_key_test (\n" +
                "id INT,\n" +
                "dept_id INT,\n" +
                "PRIMARY KEY (id, dept_id)\n" +
                ");";
        sqlParser.setSqlContent(sql);
        logger.info("Testing CREATE TABLE with composite primary key:\n{}", sql);

        assertDoesNotThrow(() -> {
            ParseTree parseTree = sqlParser.parseTreeFromSQL();
            assertNotNull(parseTree, "ParseTree should not be null for CREATE TABLE with composite primary key.");
            logger.info("ParseTree generated successfully for CREATE TABLE with composite primary key.");
        });
    }


    @Test
    void testParseCreateTableWithComplexTriggerAndPolicys() {
        String sql = "CREATE TABLE complex_test (\n" +
                "id SERIAL PRIMARY KEY,\n" +
                "name VARCHAR(100)\n" +
                ");\n" +
                "CREATE TRIGGER update_trigger\n" +
                "AFTER UPDATE ON complex_test\n" +
                "FOR EACH ROW EXECUTE FUNCTION log_update();\n" +
                "CREATE POLICY update_policy ON complex_test\n" +
                "FOR UPDATE USING (name IS NOT NULL);";
        sqlParser.setSqlContent(sql);
        logger.info("Testing CREATE TABLE with complex TRIGGER and POLICY:\n{}", sql);

        assertDoesNotThrow(() -> {
            ParseTree parseTree = sqlParser.parseTreeFromSQL();
            assertNotNull(parseTree, "ParseTree should not be null for CREATE TABLE with complex TRIGGER and POLICY.");
            logger.info("ParseTree generated successfully for CREATE TABLE with complex TRIGGER and POLICY.");
        });
    }

    @Test
    void testParseCreateTableWithPartitionings() {
        String sql = "CREATE TABLE partitioned_table (\n" +
                "id SERIAL PRIMARY KEY,\n" +
                "name VARCHAR(100) NOT NULL\n" +
                ") PARTITION BY RANGE (id);";
        sqlParser.setSqlContent(sql);
        logger.info("Testing CREATE TABLE with partitioning:\n{}", sql);

        assertDoesNotThrow(() -> {
            ParseTree parseTree = sqlParser.parseTreeFromSQL();
            assertNotNull(parseTree, "ParseTree should not be null for CREATE TABLE with partitioning.");
            logger.info("ParseTree generated successfully for CREATE TABLE with PARTITION BY.");
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
    @Test
    void testParseSelfReferencingForeignKey() {
        String sql = "CREATE TABLE employee (\n" +
                "id INT PRIMARY KEY,\n" +
                "manager_id INT,\n" +
                "FOREIGN KEY (manager_id) REFERENCES employee(id)\n" +
                ");";

        sqlParser.setSqlContent(sql);
        logger.info("Testing self-referencing FOREIGN KEY:\n{}", sql);

        assertDoesNotThrow(() -> {
            ParseTree parseTree = sqlParser.parseTreeFromSQL();
            assertNotNull(parseTree, "ParseTree should not be null for self-referencing FOREIGN KEY.");
            assertTrue(parseTree.toStringTree().contains("FOREIGN KEY"), "Expected FOREIGN KEY in parse tree.");
            logger.info("ParseTree successfully generated for self-referencing FOREIGN KEY.");
        });
    }

    @Test
    void testParseCompositeForeignKey() {
        String sql = "CREATE TABLE department_location (\n" +
                "dept_id INT,\n" +
                "location_id INT,\n" +
                "PRIMARY KEY (dept_id, location_id),\n" +
                "FOREIGN KEY (dept_id, location_id) REFERENCES department(dept_id, location_id)\n" +
                ");";

        sqlParser.setSqlContent(sql);
        logger.info("Testing composite FOREIGN KEY:\n{}", sql);

        assertDoesNotThrow(() -> {
            ParseTree parseTree = sqlParser.parseTreeFromSQL();
            assertNotNull(parseTree, "ParseTree should not be null for composite FOREIGN KEY.");
            assertTrue(parseTree.toStringTree().contains("FOREIGN KEY"), "Expected FOREIGN KEY in parse tree.");
            logger.info("ParseTree successfully generated for composite FOREIGN KEY.");
        });
    }
    @Test
    void testParseForeignKeyWithRestrictAndNoAction() {
        String sql = "CREATE TABLE orders (\n" +
                "id INT PRIMARY KEY,\n" +
                "customer_id INT,\n" +
                "FOREIGN KEY (customer_id) REFERENCES customers(id) ON DELETE RESTRICT ON UPDATE NO ACTION\n" +
                ");";

        sqlParser.setSqlContent(sql);
        logger.info("Testing FOREIGN KEY with ON DELETE RESTRICT and ON UPDATE NO ACTION:\n{}", sql);

        assertDoesNotThrow(() -> {
            ParseTree parseTree = sqlParser.parseTreeFromSQL();
            assertNotNull(parseTree, "ParseTree should not be null for FOREIGN KEY with RESTRICT and NO ACTION.");
            logger.info("ParseTree generated: {}", parseTree.toStringTree());

            // Μετατροπή του ParseTree σε lowercase για case-insensitive έλεγχο
            String parseTreeStr = parseTree.toStringTree().toLowerCase();

            // Επιβεβαίωση ότι υπάρχει το ON DELETE RESTRICT στο δέντρο ανάλυσης
            boolean hasOnDeleteRestrict = parseTreeStr.contains("on delete") && parseTreeStr.contains("restrict");
            boolean hasOnUpdateNoAction = parseTreeStr.contains("on update") && parseTreeStr.contains("no action");

            assertTrue(hasOnDeleteRestrict, "Expected ON DELETE RESTRICT in parse tree.");
            assertTrue(hasOnUpdateNoAction, "Expected ON UPDATE NO ACTION in parse tree.");
        });
    }


    @Test
    void testParseNamedForeignKeyConstraint() {
        String sql = "CREATE TABLE employees (\n" +
                "id INT PRIMARY KEY,\n" +
                "dept_id INT,\n" +
                "CONSTRAINT fk_department FOREIGN KEY (dept_id) REFERENCES department(id)\n" +
                ");";

        sqlParser.setSqlContent(sql);
        logger.info("Testing named FOREIGN KEY constraint:\n{}", sql);

        assertDoesNotThrow(() -> {
            ParseTree parseTree = sqlParser.parseTreeFromSQL();
            assertNotNull(parseTree, "ParseTree should not be null for named FOREIGN KEY constraint.");
            assertTrue(parseTree.toStringTree().contains("CONSTRAINT fk_department"), "Expected named constraint fk_department in parse tree.");
            logger.info("ParseTree successfully generated for named FOREIGN KEY constraint.");
        });
    }
    @Test
    void testParseDeferrableForeignKeyConstraint() {
        String sql = "CREATE TABLE transactions (\n" +
                "id INT PRIMARY KEY,\n" +
                "account_id INT,\n" +
                "FOREIGN KEY (account_id) REFERENCES accounts(id) DEFERRABLE INITIALLY DEFERRED\n" +
                ");";

        sqlParser.setSqlContent(sql);
        logger.info("Testing FOREIGN KEY with DEFERRABLE INITIALLY DEFERRED:\n{}", sql);

        assertDoesNotThrow(() -> {
            ParseTree parseTree = sqlParser.parseTreeFromSQL();
            assertNotNull(parseTree, "ParseTree should not be null for FOREIGN KEY with DEFERRABLE INITIALLY DEFERRED.");
            logger.info("ParseTree generated: {}", parseTree.toStringTree());

            // Έλεγχος για το DEFERRABLE
            String parseTreeStr = parseTree.toStringTree();
            assertTrue(parseTreeStr.contains("DEFERRABLE"), "DEFERRABLE should be recognized.");
            assertTrue(parseTreeStr.contains("INITIALLY DEFERRED"), "INITIALLY DEFERRED should be recognized.");
        });
    }


}
