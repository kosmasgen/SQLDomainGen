package com.sqldomaingen;

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;
import com.sqldomaingen.parser.PostgreSQLLexer;
import com.sqldomaingen.parser.PostgreSQLParser;
import com.sqldomaingen.parser.PostgreSQLBaseListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class GrammarTest {

    private static final Logger logger = LoggerFactory.getLogger(GrammarTest.class);

    @Test
    void testGrammarWithSQLScripts() {
        // Πίνακας SQL scripts για δοκιμή
        String[] sqlScripts = {
                // Απλό script με primary key
                "CREATE TABLE test (" +
                        "id INT PRIMARY KEY, " +
                        "name VARCHAR(100), " +
                        "price DECIMAL(10, 2)" +
                        ");",

                // Script με constraints
                "CREATE TABLE orders (" +
                        "order_id INT PRIMARY KEY, " +
                        "customer_id INT NOT NULL, " +
                        "order_date DATE NOT NULL, " +
                        "total_amount DECIMAL(10, 2) UNIQUE, " +
                        "FOREIGN KEY (customer_id) REFERENCES customers(customer_id)" +
                        ");",

                // Script με composite primary key
                "CREATE TABLE order_items (" +
                        "order_id INT, " +
                        "product_id INT, " +
                        "quantity INT NOT NULL, " +
                        "PRIMARY KEY (order_id, product_id)" +
                        ");",

                // Script με partition και generated columns
                "CREATE TABLE sales (" +
                        "sale_id SERIAL PRIMARY KEY, " +
                        "sale_date DATE NOT NULL, " +
                        "region VARCHAR(50), " +
                        "total_amount DECIMAL(10, 2) GENERATED ALWAYS AS (quantity * price) STORED" +
                        ") PARTITION BY LIST (region);"
        };

        for (String sqlScript : sqlScripts) {
            logger.info("Testing SQL Script:\n{}", sqlScript);

            try {
                // Δημιουργία Lexer και Parser
                PostgreSQLLexer lexer = new PostgreSQLLexer(CharStreams.fromString(sqlScript));
                CommonTokenStream tokens = new CommonTokenStream(lexer);

                // Εκτύπωση Tokens
                logger.info("Recognized Tokens:");
                tokens.fill();
                for (Token token : tokens.getTokens()) {
                    logger.debug("Token: {} | Type: {}", token.getText(),
                            PostgreSQLLexer.VOCABULARY.getSymbolicName(token.getType()));
                }

                PostgreSQLParser parser = new PostgreSQLParser(tokens);
                ParseTree tree = parser.sqlScript();
                assertNotNull(tree, "The ParseTree should not be null.");

                // Δημιουργία Listener για ParseTree
                ParseTreeWalker walker = new ParseTreeWalker();
                PostgreSQLBaseListener listener = new PostgreSQLBaseListener() {
                    @Override
                    public void enterCreateTableStatement(PostgreSQLParser.CreateTableStatementContext ctx) {
                        assertNotNull(ctx, "The Create_table_statementContext should not be null.");

                        // Έλεγχος για το όνομα του πίνακα
                        String tableName = ctx.tableName(0).getText();
                        assertNotNull(tableName, "The table name should not be null.");
                        logger.info("Table Name: {}", tableName);

                        // Έλεγχος για στήλες
                        if (ctx.columnDef() != null) {
                            int columnCount = ctx.columnDef().size();
                            logger.info("Total Columns: {}", columnCount);

                            ctx.columnDef().forEach(columnCtx -> {
                                String columnName = columnCtx.columnName().getText();
                                String dataType = columnCtx.dataType().getText();

                                logger.info("Column Name: {}, Data Type: {}", columnName, dataType);
                                assertNotNull(columnName, "Column name should not be null.");
                                assertNotNull(dataType, "Data type should not be null.");
                            });
                        }

                        // Έλεγχος για constraints
                        if (ctx.tableConstraint() != null) {
                            ctx.tableConstraint().forEach(constraintCtx -> {
                                logger.info("Table Constraint: {}", constraintCtx.getText());
                            });
                        }
                    }
                };

                walker.walk(listener, tree);

            } catch (Exception e) {
                fail("The grammar failed to parse the script: " + e.getMessage());
            }
        }
    }
}
