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
    void testAnnouncementsThemeTable() {
        String sql = """
        CREATE TABLE public.announcements_theme (
            "type" varchar(255) NOT NULL,
            description varchar(255) NOT NULL,
            el_desc varchar(255) NULL,
            tr_desc varchar(255) NULL,
            en_desc varchar(255) NULL,
            hr_desc varchar(255) NULL,
            CONSTRAINT announcements_theme_pkey PRIMARY KEY (type)
        );""";
        parseAndValidate(sql);
    }

    @Test
    void testProductCategoryTable() {
        String sql = """
        CREATE TABLE public.product_category (
            description varchar(255) NOT NULL,
            "type" varchar(255) NOT NULL,
            el_desc varchar(255) NULL,
            tr_desc varchar(255) NULL,
            en_desc varchar(255) NULL,
            hr_desc varchar(255) NULL,
            CONSTRAINT pk_product_category PRIMARY KEY (type)
        );""";
        parseAndValidate(sql);
    }

    @Test
    void testUserCategoryTable() {
        String sql = """
        CREATE TABLE public.user_category (
            status varchar(255) NOT NULL,
            description varchar(255) NULL,
            CONSTRAINT chk_user_status CHECK (((status)::text = ANY ((ARRAY['FARMER'::character varying, 'EXPERT'::character varying, 'ORGANIZATION'::character varying])::text[]))),
            CONSTRAINT user_category_pkey PRIMARY KEY (status)
        );""";
        parseAndValidate(sql);
    }

    @Test
    void testFieldActivityTable() {
        String sql = """
        CREATE TABLE public.field_activity (
            activity_type varchar(255) NOT NULL,
            description varchar(255) NULL,
            field_type varchar(255) NULL,
            el_desc varchar(255) NULL,
            tr_desc varchar(255) NULL,
            en_desc varchar(255) NULL,
            hr_desc varchar(255) NULL,
            CONSTRAINT field_activity_pkey PRIMARY KEY (activity_type),
            CONSTRAINT fk_field_activity_product FOREIGN KEY (field_type) REFERENCES public.product_category("type") ON DELETE SET NULL
        );""";
        parseAndValidate(sql);
    }
}

