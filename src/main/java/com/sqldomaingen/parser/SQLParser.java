package com.sqldomaingen.parser;

import lombok.Getter;
import lombok.Setter;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.ParseTree;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

/**
 * Κλάση για την ανάλυση SQL scripts.
 */
@Getter
@Setter
@Component
@AllArgsConstructor
@NoArgsConstructor
public class SQLParser {

    private String sqlContent;

    private static final Logger logger = LoggerFactory.getLogger(SQLParser.class);

    // Σταθερό μήνυμα για την επικύρωση του περιεχομένου SQL
    private static final String EMPTY_SQL_ERROR_MESSAGE = "Το περιεχόμενο του SQL είναι κενό ή δεν έχει οριστεί.";

    /**
     * Δημιουργεί έναν SQLParser.
     *
     * @return Ένας έτοιμος parser για το SQL περιεχόμενο.
     * @throws IllegalArgumentException Αν το SQL περιεχόμενο είναι κενό ή null.
     */
    public PostgreSQLParser createParser() {
        if (!isSQLContentValid()) {
            logger.error(EMPTY_SQL_ERROR_MESSAGE);
            throw new IllegalArgumentException(EMPTY_SQL_ERROR_MESSAGE);
        }

        PostgreSQLLexer lexer = new PostgreSQLLexer(CharStreams.fromString(sqlContent));
        CommonTokenStream tokens = new CommonTokenStream(lexer);

        tokens.fill();
        for (Token token : tokens.getTokens()) {
            logger.debug("Token: '{}' -> Type: {}", token.getText(), lexer.getVocabulary().getSymbolicName(token.getType()));
        }

        return new PostgreSQLParser(tokens);
    }

    /**
     * Δημιουργεί ένα ParseTree από το SQL script.
     *
     * @return Το ParseTree που παράγεται από τον ANTLR parser.
     */
    public ParseTree parseTreeFromSQL() {
        if (!isSQLContentValid()) {
            logger.error("Cannot generate ParseTree: SQL content is invalid.");
            throw new IllegalArgumentException(EMPTY_SQL_ERROR_MESSAGE);
        }

        PostgreSQLParser parser = createParser();
        ParseTree tree = parser.sqlScript();

        // Logging για το ParseTree
        logger.info("ParseTree generated: {}", tree.toStringTree(parser));

        return tree;
    }

    /**
     * Αναλύει το SQL script και επιστρέφει ένα ConstraintContext.
     * Ελέγχει αν υπάρχει λάθος σύνταξης στο δέντρο και διακόπτει αν εντοπιστεί.
     *
     * @return Το ConstraintContext από τον ANTLR parser.
     * @throws IllegalArgumentException Αν το SQL περιέχει σφάλματα σύνταξης.
     */
    public PostgreSQLParser.ConstraintContext parseConstraint() {
        if (!isSQLContentValid()) {
            logger.error("Cannot parse constraint: SQL content is invalid.");
            throw new IllegalArgumentException(EMPTY_SQL_ERROR_MESSAGE);
        }

        PostgreSQLParser parser = createParser();

        // Προσθήκη Custom Error Listener
        parser.removeErrorListeners();
        parser.addErrorListener(new BaseErrorListener() {
            @Override
            public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol,
                                    int line, int charPositionInLine, String msg,
                                    RecognitionException e) {
                throw new IllegalArgumentException("Σφάλμα σύνταξης στη γραμμή " + line + ", θέση " + charPositionInLine + ": " + msg);
            }
        });

        PostgreSQLParser.ConstraintContext context = parser.constraint();

        // Έλεγχος για σφάλματα σύνταξης στο ParseTree
        if (context.exception != null) {
            logger.error("Σφάλμα: Το ConstraintContext περιέχει σφάλματα.");
            throw new IllegalArgumentException("Το SQL περιέχει σφάλματα και δεν μπορεί να αναλυθεί.");
        }

        logger.info("Το ConstraintContext δημιουργήθηκε επιτυχώς: {}", context.getText());
        return context;
    }

    /**
     * Αναλύει το περιεχόμενο του SQL script και επιστρέφει το TokenStream.
     *
     * @return Το TokenStream που παράγεται από τον ANTLR parser.
     * @throws IllegalArgumentException Αν το SQL περιεχόμενο είναι κενό ή null.
     */
    public TokenStream parseSQL() {
        if (!isSQLContentValid()) {
            logger.error("Cannot generate TokenStream: SQL content is invalid.");
            throw new IllegalArgumentException(EMPTY_SQL_ERROR_MESSAGE);
        }

        // Δημιουργία lexer και token stream
        PostgreSQLLexer lexer = new PostgreSQLLexer(CharStreams.fromString(sqlContent));
        CommonTokenStream tokens = new CommonTokenStream(lexer);

        // Logging για τα tokens
        tokens.fill();
        for (Token token : tokens.getTokens()) {
            logger.debug("Token: '{}' -> Type: {}", token.getText(), lexer.getVocabulary().getSymbolicName(token.getType()));
        }

        logger.info("TokenStream generated successfully for SQL content.");
        return tokens;
    }
    public boolean isSQLContentValid() {
        return sqlContent != null && !sqlContent.isEmpty();
    }
}