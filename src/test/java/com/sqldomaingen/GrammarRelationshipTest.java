package com.sqldomaingen;

import com.sqldomaingen.parser.PostgreSQLLexer;
import com.sqldomaingen.parser.PostgreSQLParser;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.Token;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.jupiter.api.Assertions.*;

class GrammarRelationshipTest {

    private static final Logger logger = LoggerFactory.getLogger(GrammarRelationshipTest.class);

    @Test
    void testRelationshipManyToOneDefault() {
        String sql = "FOREIGN KEY (column_name) REFERENCES other_table(column_name);";
        PostgreSQLParser.ConstraintContext ctx = parseSQL(sql);
        assertNotNull(ctx, "Parse tree should not be null");
        assertNull(ctx.RELATIONSHIP(), "Default relationship should not have RELATIONSHIP keyword");

        String relationship = extractRelationship(ctx);
        assertEquals("ManyToOne", relationship, "Default relationship should be ManyToOne");
    }

    @Test
    void testRelationshipManyToMany() {
        String sql = "FOREIGN KEY (column_name) REFERENCES other_table(column_name) RELATIONSHIP MANYTOMANY;";
        PostgreSQLParser.ConstraintContext ctx = parseSQL(sql);
        assertNotNull(ctx, "Parse tree should not be null");
        assertNotNull(ctx.RELATIONSHIP(), "RELATIONSHIP keyword should be present");

        String relationship = extractRelationship(ctx);
        assertEquals("ManyToMany", relationship, "Relationship should be ManyToMany");
    }

    @Test
    void testRelationshipOneToOne() {
        String sql = "FOREIGN KEY (column_name) REFERENCES other_table(column_name) RELATIONSHIP ONETOONE;";
        PostgreSQLParser.ConstraintContext ctx = parseSQL(sql);
        assertNotNull(ctx, "Parse tree should not be null");

        String relationship = extractRelationship(ctx);
        assertEquals("OneToOne", relationship, "Relationship should be OneToOne");
    }

    @Test
    void testRelationshipOneToMany() {
        String sql = "FOREIGN KEY (column_name) REFERENCES other_table(column_name) RELATIONSHIP ONETOMANY;";
        PostgreSQLParser.ConstraintContext ctx = parseSQL(sql);
        assertNotNull(ctx, "Parse tree should not be null");

        String relationship = extractRelationship(ctx);
        assertEquals("OneToMany", relationship, "Relationship should be OneToMany");
    }

    private PostgreSQLParser.ConstraintContext parseSQL(String sql) {
        PostgreSQLLexer lexer = new PostgreSQLLexer(CharStreams.fromString(sql));
        CommonTokenStream tokens = new CommonTokenStream(lexer);

        tokens.fill();
        for (Token token : tokens.getTokens()) {
            logger.info("Token Type: {}, Text: {}", lexer.getVocabulary().getSymbolicName(token.getType()), token.getText());
        }

        PostgreSQLParser parser = new PostgreSQLParser(tokens);
        return parser.constraint();
    }

    private String extractRelationship(PostgreSQLParser.ConstraintContext ctx) {
        if (ctx.RELATIONSHIP() != null && ctx.relationshipType() != null) {
            String relationship = ctx.relationshipType().getText();
            switch (relationship) {
                case "ONETOONE":
                    return "OneToOne";
                case "MANYTOONE":
                    return "ManyToOne";
                case "ONETOMANY":
                    return "OneToMany";
                case "MANYTOMANY":
                    return "ManyToMany";
                default:
                    throw new IllegalArgumentException("Unknown relationship type: " + relationship);
            }
        }
        return "ManyToOne"; // Προεπιλογή αν δεν υπάρχει η λέξη RELATIONSHIP
    }
    @Test
    void testRelationshipTypeParsing() {
        String sql = "FOREIGN KEY (id) REFERENCES other_table(id) RELATIONSHIP ONETOONE;";
        PostgreSQLParser.ConstraintContext ctx = parseSQL(sql);
        assertNotNull(ctx, "Το Parse tree δεν πρέπει να είναι null.");
        assertNotNull(ctx.RELATIONSHIP(), "Η λέξη RELATIONSHIP πρέπει να υπάρχει.");
        assertEquals("ONETOONE", ctx.relationshipType().getText(), "Ο τύπος σχέσης πρέπει να είναι ONETOONE.");
    }



}
