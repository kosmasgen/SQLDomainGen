package com.sqldomaingen;

import com.sqldomaingen.parser.ColumnDefinition;
import org.antlr.v4.runtime.CommonToken;
import org.antlr.v4.runtime.Token;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.sqldomaingen.model.Column;
import static org.junit.jupiter.api.Assertions.*;

public class ColumnDefinitionTest {

    private static final Logger logger = LoggerFactory.getLogger(ColumnDefinitionTest.class);

    @Test
    void testSetColumnNameFromToken() {
        logger.info("Testing setColumnNameFromToken method...");

        Token token = new CommonToken(0, "id INTEGER NOT NULL");
        logger.debug("Before setColumnNameFromToken - Token text: {}", token.getText());

        ColumnDefinition columnDefinition = new ColumnDefinition();
        columnDefinition.setColumnNameFromToken(token);

        logger.debug("After setColumnNameFromToken - Column name: {}", columnDefinition.getColumnName());
        assertEquals("id", columnDefinition.getColumnName(), "Column name should be 'id'.");
        logger.info("Test for setColumnNameFromToken passed.");
    }

    @Test
    void testSetSqlTypeFromToken() {
        logger.info("Testing setSqlTypeFromToken method...");

        Token token = new CommonToken(0, "id INTEGER(10)");
        logger.debug("Before setSqlTypeFromToken - Token text: {}", token.getText());

        ColumnDefinition columnDefinition = new ColumnDefinition();
        columnDefinition.setSqlTypeFromToken(token);

        logger.debug("After setSqlTypeFromToken - SQL Type: {}, Java Type: {}, Length: {}",
                columnDefinition.getSqlType(), columnDefinition.getJavaType(), columnDefinition.getLength());

        logger.info("Test for setSqlTypeFromToken passed.");
    }

    @Test
    void testIsPrimaryKey() {
        logger.info("Testing isPrimaryKey method...");

        Token token = new CommonToken(0, "id PRIMARY KEY");
        logger.debug("Before isPrimaryKey - Token text: {}", token.getText());

        ColumnDefinition columnDefinition = new ColumnDefinition();
        columnDefinition.isPrimaryKey(token);

        logger.debug("After isPrimaryKey - PrimaryKey: {}", columnDefinition.isPrimaryKey());
        assertTrue(columnDefinition.isPrimaryKey(), "Column should be primary key.");
        logger.info("Test for isPrimaryKey passed.");
    }

    @Test
    void testIsNullable() {
        logger.info("Testing isNullable method...");

        Token token = new CommonToken(0, "id INTEGER NOT NULL");
        logger.debug("Before isNullable - Token text: {}", token.getText());

        ColumnDefinition columnDefinition = new ColumnDefinition();
        columnDefinition.isNullable(token);

        logger.debug("After isNullable - Nullable: {}", columnDefinition.isNullable());
        assertFalse(columnDefinition.isNullable(), "Column should not be nullable.");
        logger.info("Test for isNullable passed.");
    }

    @Test
    void testIsUnique() {
        logger.info("Testing isUnique method...");

        Token token = new CommonToken(0, "id INTEGER UNIQUE");
        logger.debug("Before isUnique - Token text: {}", token.getText());

        ColumnDefinition columnDefinition = new ColumnDefinition();
        columnDefinition.isUnique(token);

        logger.debug("After isUnique - Unique: {}", columnDefinition.isUnique());
        assertTrue(columnDefinition.isUnique(), "Column should be unique.");
        logger.info("Test for isUnique passed.");
    }

    @Test
    void testIsDefaultValue() {
        logger.info("Testing isDefaultValue method...");

        Token token = new CommonToken(0, "id INTEGER DEFAULT 100");
        logger.debug("Before isDefaultValue - Token text: {}", token.getText());

        ColumnDefinition columnDefinition = new ColumnDefinition();
        columnDefinition.isDefaultValue(token);

        logger.debug("After isDefaultValue - DefaultValue: {}", columnDefinition.getDefaultValue());
        assertEquals("100", columnDefinition.getDefaultValue(), "Default value should be '100'.");
        logger.info("Test for isDefaultValue passed.");
    }

    @Test
    void testSetCheckConstraint() {
        logger.info("Testing setCheckConstraint method...");

        Token token = new CommonToken(0, "CHECK (id > 0)");
        logger.debug("Before setCheckConstraint - Token text: {}", token.getText());

        ColumnDefinition columnDefinition = new ColumnDefinition();
        columnDefinition.setCheckConstraint(token);

        logger.debug("After setCheckConstraint - CheckConstraint: {}", columnDefinition.getCheckConstraint());
        assertEquals("(id > 0)", columnDefinition.getCheckConstraint(), "Check constraint should be '(id > 0)'.");
        logger.info("Test for setCheckConstraint passed.");
    }

    @Test
    void testIsForeignKey() {
        logger.info("Testing isForeignKey method...");

        Token token = new CommonToken(0, "id INTEGER REFERENCES users(id)");
        logger.debug("Before isForeignKey - Token text: {}", token.getText());

        ColumnDefinition columnDefinition = new ColumnDefinition();
        columnDefinition.isForeignKey(token);

        logger.debug("After isForeignKey - ForeignKey: {}, ReferencedTable: {}, ReferencedColumn: {}",
                columnDefinition.isForeignKey(), columnDefinition.getReferencedTable(), columnDefinition.getReferencedColumn());
        assertTrue(columnDefinition.isForeignKey(), "Column should be a foreign key.");
        assertEquals("users", columnDefinition.getReferencedTable(), "Referenced table should be 'users'.");
        assertEquals("id", columnDefinition.getReferencedColumn(), "Referenced column should be 'id'.");
        logger.info("Test for isForeignKey passed.");
    }

    @Test
    void testSetReferencedTableFromToken() {
        logger.info("Testing setReferencedTableFromToken method...");

        Token token = new CommonToken(0, "FOREIGN KEY REFERENCES users(id)");
        logger.debug("Before setReferencedTableFromToken - Token text: {}", token.getText());

        ColumnDefinition columnDefinition = new ColumnDefinition();
        columnDefinition.setReferencedTableFromToken(token);

        logger.debug("After setReferencedTableFromToken - ReferencedTable: {}", columnDefinition.getReferencedTable());
        assertEquals("users", columnDefinition.getReferencedTable(), "Referenced table should be 'users'.");
        logger.info("Test for setReferencedTableFromToken passed.");
    }

    @Test
    void testSetReferencedColumnFromToken() {
        logger.info("Testing setReferencedColumnFromToken method...");

        Token token = new CommonToken(0, "FOREIGN KEY REFERENCES users(id)");
        logger.debug("Before setReferencedColumnFromToken - Token text: {}", token.getText());

        ColumnDefinition columnDefinition = new ColumnDefinition();
        columnDefinition.setReferencedColumnFromToken(token);

        logger.debug("After setReferencedColumnFromToken - ReferencedColumn: {}", columnDefinition.getReferencedColumn());
        assertEquals("id", columnDefinition.getReferencedColumn(), "Referenced column should be 'id'.");
        logger.info("Test for setReferencedColumnFromToken passed.");
    }

    @Test
    void testToColumn() {
        logger.info("Testing toColumn method...");

        ColumnDefinition columnDefinition = new ColumnDefinition();
        columnDefinition.setColumnName("id");
        columnDefinition.setSqlType("INTEGER");
        columnDefinition.setJavaType("Integer");
        columnDefinition.setLength(10);
        columnDefinition.setPrimaryKey(true);
        columnDefinition.setNullable(false);
        columnDefinition.setDefaultValue("100");
        columnDefinition.setUnique(true);

        Column column = columnDefinition.toColumn();

        logger.debug("After toColumn - Converted Column: {}", column);
        assertEquals("id", column.getName(), "Column name should be 'id'.");
        assertEquals("Long", column.getType(), "Column type should be 'Long'.");
        assertEquals(10, column.getLength(), "Column length should be 10.");
        assertTrue(column.isPrimaryKey(), "Column should be primary key.");
        assertFalse(column.isNullable(), "Column should not be nullable.");
        assertEquals("100", column.getDefaultValue(), "Default value should be '100'.");
        assertTrue(column.isUnique(), "Column should be unique.");
        logger.info("Test for toColumn passed.");
    }
    @Test
    public void testExtractLength_withValidLength() {
        // Test case with valid length
        String sqlType = "VARCHAR(50)";
        int result = ColumnDefinition.extractLength(sqlType);
        assertEquals(50, result, "The extracted length should be 50.");
    }

    @Test
    public void testExtractLength_withDefaultLength() {
        // Test case with no length specified
        String sqlType = "TEXT";
        int result = ColumnDefinition.extractLength(sqlType);
        assertEquals(255, result, "The default length should be 255 when no length is specified.");
    }

    @Test
    public void testExtractLength_withInvalidLengthFormat() {
        // Test case with invalid length format
        String sqlType = "VARCHAR(abc)";
        int result = ColumnDefinition.extractLength(sqlType);
        assertEquals(255, result, "The default length should be 255 when the length format is invalid.");
    }

    @Test
    public void testExtractLength_withNullSqlType() {
        // Test case with null sqlType
        String sqlType = null;
        int result = ColumnDefinition.extractLength(sqlType);
        assertEquals(255, result, "The default length should be 255 when sqlType is null.");
    }

    @Test
    public void testExtractLength_withEmptyParentheses() {
        // Test case with empty parentheses
        String sqlType = "VARCHAR()";
        int result = ColumnDefinition.extractLength(sqlType);
        assertEquals(255, result, "The default length should be 255 when parentheses are empty.");
    }

    @Test
    public void testExtractLength_withCompositeLength() {
        // Test case with composite length (e.g., DECIMAL(10,2))
        String sqlType = "DECIMAL(10,2)";
        int result = ColumnDefinition.extractLength(sqlType);
        assertEquals(10, result, "The extracted length should be 10 for composite types like DECIMAL.");
    }
}
