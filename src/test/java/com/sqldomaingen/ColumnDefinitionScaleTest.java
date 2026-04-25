package com.sqldomaingen;

import com.sqldomaingen.parser.ColumnDefinition;
import com.sqldomaingen.parser.PostgreSQLLexer;
import com.sqldomaingen.parser.PostgreSQLParser;
import lombok.extern.log4j.Log4j2;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Log4j2
class ColumnDefinitionScaleTest {

    private PostgreSQLParser.ColumnDefContext getColumnDefContext(String sql) {
        PostgreSQLLexer lexer = new PostgreSQLLexer(
                CharStreams.fromString("CREATE TABLE test (" + sql + ");")
        );
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        PostgreSQLParser parser = new PostgreSQLParser(tokens);
        PostgreSQLParser.CreateTableStatementContext createTableStatementContext = parser.createTableStatement();

        return createTableStatementContext.columnDef(0);
    }

    private ColumnDefinition parseColumn(String sql) {
        PostgreSQLParser.ColumnDefContext columnDefContext = getColumnDefContext(sql);
        ColumnDefinition columnDefinition = ColumnDefinition.fromContext(columnDefContext);

        log.info(
                "Parsed column. sql='{}', columnName='{}', sqlType='{}', baseSqlType='{}', precision={}, scale={}, length={}",
                sql,
                columnDefinition.getColumnName(),
                columnDefinition.getSqlType(),
                columnDefinition.getBaseSqlType(),
                columnDefinition.getPrecision(),
                columnDefinition.getScale(),
                columnDefinition.getLength()
        );

        return columnDefinition;
    }

    @Test
    void testFromContext_WithNumericPrecisionAndScale() {
        ColumnDefinition columnDefinition = parseColumn("chamber_amount NUMERIC(19, 2) NULL");

        assertEquals("chamber_amount", columnDefinition.getColumnName());
        assertEquals("NUMERIC", columnDefinition.getBaseSqlType());
        assertEquals(19, columnDefinition.getPrecision(), "Precision should be 19");
        assertEquals(2, columnDefinition.getScale(), "Scale should be 2");
    }

    @Test
    void testFromContext_WithDecimalPrecisionAndScale() {
        ColumnDefinition columnDefinition = parseColumn("amount DECIMAL(10, 2) NOT NULL");

        assertEquals("amount", columnDefinition.getColumnName());
        assertEquals("DECIMAL", columnDefinition.getBaseSqlType());
        assertEquals(10, columnDefinition.getPrecision(), "Precision should be 10");
        assertEquals(2, columnDefinition.getScale(), "Scale should be 2");
    }

    @Test
    void testFromContext_WithNumericPrecisionOnly() {
        ColumnDefinition columnDefinition = parseColumn("gemi_payment_id NUMERIC(19) NULL");

        assertEquals("gemi_payment_id", columnDefinition.getColumnName());
        assertEquals("NUMERIC", columnDefinition.getBaseSqlType());
        assertEquals(19, columnDefinition.getPrecision(), "Precision should be 19");
        assertEquals(0, columnDefinition.getScale(), "Scale should default to 0");
    }

    @Test
    void testFromContext_WithDecimalPrecisionOnly() {
        ColumnDefinition columnDefinition = parseColumn("weight DECIMAL(8) NULL");

        assertEquals("weight", columnDefinition.getColumnName());
        assertEquals("DECIMAL", columnDefinition.getBaseSqlType());
        assertEquals(8, columnDefinition.getPrecision(), "Precision should be 8");
        assertEquals(0, columnDefinition.getScale(), "Scale should default to 0");
    }

    @Test
    void testFromContext_WithNumericWithoutPrecisionAndScale() {
        ColumnDefinition columnDefinition = parseColumn("cancel_flag NUMERIC NULL");

        assertEquals("cancel_flag", columnDefinition.getColumnName());
        assertEquals("NUMERIC", columnDefinition.getBaseSqlType());
        assertEquals(0, columnDefinition.getPrecision(), "Precision should default to 0");
        assertEquals(0, columnDefinition.getScale(), "Scale should default to 0");
    }

    @Test
    void testFromContext_WithDecimalDefaultValueAndScale() {
        ColumnDefinition columnDefinition = parseColumn("score DECIMAL(5, 2) DEFAULT 99.99");

        assertEquals("score", columnDefinition.getColumnName());
        assertEquals("DECIMAL", columnDefinition.getBaseSqlType());
        assertEquals(5, columnDefinition.getPrecision(), "Precision should be 5");
        assertEquals(2, columnDefinition.getScale(), "Scale should be 2");
        assertEquals("99.99", columnDefinition.getDefaultValue());
    }

    @Test
    void testFromContext_WithMultipleNumericColumns_FirstColumnScaleOnly() {
        ColumnDefinition columnDefinition = parseColumn("total_amount_paid NUMERIC(19, 2), other_col INTEGER");

        assertEquals("total_amount_paid", columnDefinition.getColumnName());
        assertEquals("NUMERIC", columnDefinition.getBaseSqlType());
        assertEquals(19, columnDefinition.getPrecision(), "Precision should be 19");
        assertEquals(2, columnDefinition.getScale(), "Scale should be 2");
    }

    @Test
    void testFromContext_WithNumericScaleFromIncomeGemiPaymentExample() {
        ColumnDefinition columnDefinition = parseColumn("chamber_amount_for_certs NUMERIC(19, 2) NULL");

        assertEquals("chamber_amount_for_certs", columnDefinition.getColumnName());
        assertEquals("NUMERIC", columnDefinition.getBaseSqlType());
        assertEquals(19, columnDefinition.getPrecision(), "Precision should be 19");
        assertEquals(2, columnDefinition.getScale(), "Scale should be 2");
    }
}