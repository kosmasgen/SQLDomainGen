package com.sqldomaingen;

import com.sqldomaingen.model.Table;
import com.sqldomaingen.parser.CreateTableDefinition;
import com.sqldomaingen.parser.PostgreSQLParser;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.sqldomaingen.parser.PostgreSQLLexer;
import com.sqldomaingen.model.Relationship;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;
class CreateTableDefinitionTest {

    private static final Logger logger = LoggerFactory.getLogger(CreateTableDefinitionTest.class);

    private PostgreSQLParser getParser(String sql) {
        PostgreSQLLexer lexer = new PostgreSQLLexer(CharStreams.fromString(sql));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        logger.info("Generated tokens for SQL: {}", sql);
        return new PostgreSQLParser(tokens);
    }

    @Test
    void testProductTables() {
        String sql = """
            CREATE TABLE Product (
                product_id INT PRIMARY KEY,
                product_name VARCHAR(100) NOT NULL,
                category VARCHAR(50),
                price DECIMAL(10, 2) CHECK (price > 0),
                stock_quantity INT DEFAULT 0,
                created_at TIMESTAMP,
                updated_at TIMESTAMP
            );
            """;

        logger.info("Testing Product table with SQL:\n{}", sql);

        PostgreSQLParser parser = getParser(sql);
        PostgreSQLParser.CreateTableStatementContext ctx = parser.createTableStatement();
        assertNotNull(ctx, "Parser context should not be null.");

        CreateTableDefinition createTableDefinition = new CreateTableDefinition();
        createTableDefinition.processCreateTable(ctx);

        Table table = createTableDefinition.toTable();
        logger.info("Converted to Table object: {} with {} columns.", table.getName(), table.getColumns().size());

        assertEquals("Product", table.getName(), "Table name should be 'Product'.");
        assertEquals(7, table.getColumns().size(), "Product table should have 7 columns.");
    }

    @Test
    void testCustomerTables() {
        String sql = """
            CREATE TABLE Customer (
                customer_id INT PRIMARY KEY,
                customer_name VARCHAR(100) NOT NULL,
                email VARCHAR(150) UNIQUE,
                phone_number VARCHAR(15),
                address TEXT,
                registered_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                last_purchase TIMESTAMP
            );
            """;

        logger.info("Testing Customer table with SQL:\n{}", sql);

        PostgreSQLParser parser = getParser(sql);
        PostgreSQLParser.CreateTableStatementContext ctx = parser.createTableStatement();
        assertNotNull(ctx, "Parser context should not be null.");

        CreateTableDefinition createTableDefinition = new CreateTableDefinition();
        createTableDefinition.processCreateTable(ctx);

        Table table = createTableDefinition.toTable();
        logger.info("Converted to Table object: {} with {} columns.", table.getName(), table.getColumns().size());

        assertEquals("Customer", table.getName(), "Table name should be 'Customer'.");
        assertEquals(7, table.getColumns().size(), "Customer table should have 7 columns.");
    }



    @Test
    void testProductOrderTables() {
        String sql = """
            CREATE TABLE ProductOrder (
                product_id INT,
                order_id INT,
                quantity INT NOT NULL,
                unit_price DECIMAL(10, 2),
                discount DECIMAL(5, 2),
                PRIMARY KEY (product_id, order_id),
                FOREIGN KEY (product_id) REFERENCES Product(product_id),
                FOREIGN KEY (order_id) REFERENCES Orders(order_id)
            );
            """;

        logger.info("Testing ProductOrder table with SQL:\n{}", sql);

        PostgreSQLParser parser = getParser(sql);
        PostgreSQLParser.CreateTableStatementContext ctx = parser.createTableStatement();
        assertNotNull(ctx, "Parser context should not be null.");

        CreateTableDefinition createTableDefinition = new CreateTableDefinition();
        createTableDefinition.processCreateTable(ctx);

        Table table = createTableDefinition.toTable();
        logger.info("Converted to Table object: {} with {} columns.", table.getName(), table.getColumns().size());

        assertEquals("ProductOrder", table.getName(), "Table name should be 'ProductOrder'.");
        assertEquals(5, table.getColumns().size(), "ProductOrder table should have 5 columns.");
    }

    @Test
    void testInventoryTables() {
        String sql = """
            CREATE TABLE Inventory (
                inventory_id INT PRIMARY KEY,
                product_id INT NOT NULL,
                warehouse_location VARCHAR(10),
                stock_quantity INT,
                last_restock TIMESTAMP,
                FOREIGN KEY (product_id) REFERENCES Product(product_id)
            );
            """;

        logger.info("Testing Inventory table with SQL:\n{}", sql);

        PostgreSQLParser parser = getParser(sql);
        PostgreSQLParser.CreateTableStatementContext ctx = parser.createTableStatement();
        assertNotNull(ctx, "Parser context should not be null.");

        CreateTableDefinition createTableDefinition = new CreateTableDefinition();
        createTableDefinition.processCreateTable(ctx);

        Table table = createTableDefinition.toTable();
        logger.info("Converted to Table object: {} with {} columns.", table.getName(), table.getColumns().size());

        assertEquals("Inventory", table.getName(), "Table name should be 'Inventory'.");
        assertEquals(5, table.getColumns().size(), "Inventory table should have 5 columns.");
    }
    @Test
    void testInventoryTable() {
        String sql = """
            CREATE TABLE Inventory (
                inventory_id INT PRIMARY KEY,
                product_id INT NOT NULL,
                warehouse_location VARCHAR(100),
                stock_quantity INT,
                last_restock TIMESTAMP,
                FOREIGN KEY (product_id) REFERENCES Product(product_id)
            );
            """;

        logger.info("Testing Inventory table with SQL:\n{}", sql);

        PostgreSQLParser parser = getParser(sql);
        PostgreSQLParser.CreateTableStatementContext ctx = parser.createTableStatement();
        assertNotNull(ctx, "Parser context should not be null.");

        CreateTableDefinition createTableDefinition = new CreateTableDefinition();
        createTableDefinition.processCreateTable(ctx);

        Table table = createTableDefinition.toTable();
        logger.info("Converted to Table object: {} with {} columns.", table.getName(), table.getColumns().size());

        assertEquals("Inventory", table.getName(), "Table name should be 'Inventory'.");
        assertEquals(5, table.getColumns().size(), "Inventory table should have 5 columns.");
    }

    @Test
    void testEmployeeTable() {
        String sql = """
            CREATE TABLE Employee (
                employee_id INT PRIMARY KEY,
                name VARCHAR(100) NOT NULL,
                department_id INT,
                hire_date DATE,
                salary DECIMAL(15, 2),
                FOREIGN KEY (department_id) REFERENCES Department(department_id)
            );
            """;

        logger.info("Testing Employee table with SQL:\n{}", sql);

        PostgreSQLParser parser = getParser(sql);
        PostgreSQLParser.CreateTableStatementContext ctx = parser.createTableStatement();
        assertNotNull(ctx, "Parser context should not be null.");

        CreateTableDefinition createTableDefinition = new CreateTableDefinition();
        createTableDefinition.processCreateTable(ctx);

        Table table = createTableDefinition.toTable();
        logger.info("Converted to Table object: {} with {} columns.", table.getName(), table.getColumns().size());

        assertEquals("Employee", table.getName(), "Table name should be 'Employee'.");
        assertEquals(5, table.getColumns().size(), "Employee table should have 5 columns.");
    }

    @Test
    void testDepartmentTable() {
        String sql = """
            CREATE TABLE Department (
                department_id INT PRIMARY KEY,
                department_name VARCHAR(100) NOT NULL,
                location VARCHAR(100),
                manager_id INT,
                budget DECIMAL(15, 2)
            );
            """;

        logger.info("Testing Department table with SQL:\n{}", sql);

        PostgreSQLParser parser = getParser(sql);
        PostgreSQLParser.CreateTableStatementContext ctx = parser.createTableStatement();
        assertNotNull(ctx, "Parser context should not be null.");

        CreateTableDefinition createTableDefinition = new CreateTableDefinition();
        createTableDefinition.processCreateTable(ctx);

        Table table = createTableDefinition.toTable();
        logger.info("Converted to Table object: {} with {} columns.", table.getName(), table.getColumns().size());

        assertEquals("Department", table.getName(), "Table name should be 'Department'.");
        assertEquals(5, table.getColumns().size(), "Department table should have 5 columns.");
    }

    @Test
    void testProjectTable() {
        String sql = """
            CREATE TABLE Project (
                project_id INT PRIMARY KEY,
                project_name VARCHAR(100) NOT NULL,
                start_date DATE,
                end_date DATE,
                budget DECIMAL(20, 2),
                manager_id INT,
                FOREIGN KEY (manager_id) REFERENCES Employee(employee_id)
            );
            """;

        logger.info("Testing Project table with SQL:\n{}", sql);

        PostgreSQLParser parser = getParser(sql);
        PostgreSQLParser.CreateTableStatementContext ctx = parser.createTableStatement();
        assertNotNull(ctx, "Parser context should not be null.");

        CreateTableDefinition createTableDefinition = new CreateTableDefinition();
        createTableDefinition.processCreateTable(ctx);

        Table table = createTableDefinition.toTable();
        logger.info("Converted to Table object: {} with {} columns.", table.getName(), table.getColumns().size());

        assertEquals("Project", table.getName(), "Table name should be 'Project'.");
        assertEquals(6, table.getColumns().size(), "Project table should have 6 columns.");
    }


}
