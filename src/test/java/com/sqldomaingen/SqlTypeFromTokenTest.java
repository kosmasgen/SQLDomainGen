package com.sqldomaingen;

import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.CommonToken;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.sqldomaingen.parser.ColumnDefinition;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class SqlTypeFromTokenTest {

    private static final Logger logger = LoggerFactory.getLogger(ColumnDefinitionTest.class);

    @Test
    void testSetSqlTypeFromToken() {
        ColumnDefinition columnDefinition = new ColumnDefinition();

        // Token με έγκυρο SQL τύπο
        Token validToken = new CommonToken(0, "VARCHAR");
        columnDefinition.setSqlTypeFromToken(validToken);

        logger.info("Testing valid token: VARCHAR");
        logger.info("Expected sqlType: VARCHAR, Actual sqlType: {}", columnDefinition.getSqlType());
        logger.info("Expected javaType: String, Actual javaType: {}", columnDefinition.getJavaType());

        assertEquals("VARCHAR", columnDefinition.getSqlType());
        assertEquals("String", columnDefinition.getJavaType());

        // Token με άδειο τύπο
        Token emptyToken = new CommonToken(0, "");
        columnDefinition.setSqlTypeFromToken(emptyToken);

        logger.info("Testing empty token");
        logger.info("Expected sqlType: VARCHAR, Actual sqlType: {}", columnDefinition.getSqlType());
        logger.info("Expected javaType: String, Actual javaType: {}", columnDefinition.getJavaType());

        assertEquals("VARCHAR", columnDefinition.getSqlType());
        assertEquals("String", columnDefinition.getJavaType());

        // Token με μη υποστηριζόμενο τύπο
        Token unsupportedToken = new CommonToken(0, "UNSUPPORTED_TYPE");
        columnDefinition.setSqlTypeFromToken(unsupportedToken);

        logger.info("Testing unsupported token: UNSUPPORTED_TYPE");
        logger.info("Expected sqlType: UNSUPPORTED_TYPE, Actual sqlType: {}", columnDefinition.getSqlType());
        logger.info("Expected javaType: String, Actual javaType: {}", columnDefinition.getJavaType());

        assertEquals("UNSUPPORTED_TYPE", columnDefinition.getSqlType());
        assertNotNull(columnDefinition.getJavaType(), "Java type mapping should handle unsupported types gracefully.");
    }
}
