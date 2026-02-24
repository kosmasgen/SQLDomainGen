package com.sqldomaingen.parser;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.ParseTree;
import org.springframework.stereotype.Component;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.List;

import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.Token;

/**
 * SQL parser wrapper around the ANTLR PostgreSQL grammar.
 * <p>
 * Responsibilities:
 * <ul>
 *   <li>Validate that SQL input exists</li>
 *   <li>Create and configure the ANTLR lexer/parser</li>
 *   <li>Expose helper methods to parse full scripts, constraints, or just tokens</li>
 * </ul>
 */
@Getter
@Setter
@Component
@AllArgsConstructor
@NoArgsConstructor
@Log4j2
public class SQLParser {

    private String sqlContent;

    /** Error message used when SQL input is missing. */
    private static final String EMPTY_SQL_ERROR_MESSAGE = "SQL content is empty or not set.";

    /**
     * Creates a configured {@link PostgreSQLParser} for the current {@code sqlContent}.
     * <p>
     * Configuration includes:
     * <ul>
     *   <li>Custom {@link BaseErrorListener} that throws {@link IllegalArgumentException} on syntax errors</li>
     *   <li>ANTLR trace enabled (useful while developing/debugging grammar issues)</li>
     * </ul>
     *
     * @return a ready-to-use ANTLR PostgreSQL parser
     * @throws IllegalArgumentException if {@code sqlContent} is null or blank
     */
    public PostgreSQLParser createParser() {
        if (!isSQLContentValid()) {
            log.error(EMPTY_SQL_ERROR_MESSAGE);
            throw new IllegalArgumentException(EMPTY_SQL_ERROR_MESSAGE);
        }

        log.debug("ANTLR trace is enabled (grammar rule tracing).");

        PostgreSQLLexer lexer = new PostgreSQLLexer(CharStreams.fromString(sqlContent));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        PostgreSQLParser parser = new PostgreSQLParser(tokens);

        // Replace default listeners to fail fast on syntax errors.
        parser.removeErrorListeners();
        parser.addErrorListener(new BaseErrorListener() {
            @Override
            public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol,
                                    int line, int charPositionInLine, String msg,
                                    RecognitionException e) {
                throw new IllegalArgumentException(
                        "Syntax error at line " + line + ", position " + charPositionInLine + ": " + msg
                );
            }
        });

        // Enable tracing for debugging (consider disabling for production use).
        parser.setTrace(true);

        return parser;
    }

    /**
     * Parses the current SQL script and returns the produced {@link ParseTree}.
     *
     * @return the parse tree created by the ANTLR parser
     * @throws IllegalArgumentException if {@code sqlContent} is invalid or contains syntax errors
     */
    public ParseTree parseTreeFromSQL() {
        if (!isSQLContentValid()) {
            log.error("Cannot generate ParseTree: SQL content is invalid.");
            throw new IllegalArgumentException(EMPTY_SQL_ERROR_MESSAGE);
        }

        PostgreSQLParser parser = createParser();

        // Debug: log extracted tokens before running the grammar entry rule.
        CommonTokenStream tokenStream = (CommonTokenStream) parser.getInputStream();
        List<Token> tokens = tokenStream.getTokens();
        log.debug("Tokens extracted: {}", tokens.stream()
                .map(token -> String.format("[%s -> %s]", token.getText(), parser.getVocabulary().getSymbolicName(token.getType())))
                .toList());

        ParseTree tree = parser.sqlScript();

        log.info("ParseTree generated: {}", tree.toStringTree(parser));
        return tree;
    }

    /**
     * Parses a standalone constraint rule using the grammar {@code constraint} entry.
     * <p>
     * This is useful for isolated constraint parsing, tests, or when the input is known
     * to contain only a constraint clause.
     *
     * @return the {@link PostgreSQLParser.ConstraintContext}
     * @throws IllegalArgumentException if {@code sqlContent} is invalid or contains syntax errors
     */
    public PostgreSQLParser.ConstraintContext parseConstraint() {
        if (!isSQLContentValid()) {
            log.error("Cannot parse constraint: SQL content is invalid.");
            throw new IllegalArgumentException(EMPTY_SQL_ERROR_MESSAGE);
        }

        PostgreSQLParser parser = createParser();

        // createParser() already installs a fail-fast listener,
        // but we keep this here to ensure constraint parsing also fails fast explicitly.
        parser.removeErrorListeners();
        parser.addErrorListener(new BaseErrorListener() {
            @Override
            public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol,
                                    int line, int charPositionInLine, String msg,
                                    RecognitionException e) {
                throw new IllegalArgumentException(
                        "Syntax error at line " + line + ", position " + charPositionInLine + ": " + msg
                );
            }
        });

        PostgreSQLParser.ConstraintContext context = parser.constraint();

        // Defensive check: if ANTLR recorded an exception, reject the result.
        if (context.exception != null) {
            log.error("ConstraintContext contains parsing errors.");
            throw new IllegalArgumentException("SQL contains errors and cannot be parsed.");
        }

        log.info("ConstraintContext created successfully: {}", context.getText());
        return context;
    }

    /**
     * Tokenizes the current SQL input and returns the {@link TokenStream}.
     * Useful for debugging lexer behavior without executing grammar rules.
     *
     * @return a token stream produced by the lexer
     * @throws IllegalArgumentException if {@code sqlContent} is invalid
     */
    public TokenStream parseSQL() {
        if (!isSQLContentValid()) {
            log.error("Cannot generate TokenStream: SQL content is invalid.");
            throw new IllegalArgumentException(EMPTY_SQL_ERROR_MESSAGE);
        }

        PostgreSQLLexer lexer = new PostgreSQLLexer(CharStreams.fromString(sqlContent));
        CommonTokenStream tokens = new CommonTokenStream(lexer);

        // Debug: dump all tokens with their symbolic types.
        tokens.fill();
        for (Token token : tokens.getTokens()) {
            log.debug("Token: '{}' -> Type: {}", token.getText(), lexer.getVocabulary().getSymbolicName(token.getType()));
        }

        log.info("TokenStream generated successfully for SQL content.");
        return tokens;
    }

    /**
     * Validates that {@code sqlContent} is present.
     *
     * @return true if SQL content is not null and not empty
     */
    public boolean isSQLContentValid() {
        return sqlContent != null && !sqlContent.isEmpty();
    }
}
