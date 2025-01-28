package com.sqldomaingen.parser;

import com.sqldomaingen.util.TypeMapper;
import com.sqldomaingen.model.Column;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.antlr.v4.runtime.Token;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Getter
@Setter
@NoArgsConstructor
public class ColumnDefinition {

    private static final Logger logger = LoggerFactory.getLogger(ColumnDefinition.class);

    private String columnName;
    private String sqlType;
    private String javaType;
    private int length;
    private boolean primaryKey;
    private boolean nullable = true;
    private boolean unique;
    private String defaultValue;
    private String checkConstraint;
    private String referencedTable;
    private String referencedColumn;
    private boolean foreignKey;

    /**
     * Sets the column name based on the provided token.
     *
     * @param token the token containing column definition
     */
    public void setColumnNameFromToken(Token token) {
        if (token != null && token.getText() != null) {
            String[] parts = token.getText().trim().split("\\s+");
            this.columnName = parts.length > 0 ? parts[0] : null;
            logger.debug("Set columnName: {}", this.columnName);
        } else {
            logger.warn("Token is null or does not contain text.");
        }
    }

    /**
     * Sets the SQL type, derives the Java type, and extracts the length from the provided token.
     *
     * @param token the token containing SQL type information
     */
    public void setSqlTypeFromToken(Token token) {
        if (token == null || token.getText() == null || token.getText().trim().isEmpty()) {
            logger.warn("Token is null or does not contain valid text for SQL type. Defaulting sqlType to 'VARCHAR'.");
            this.sqlType = "VARCHAR";
            this.javaType = TypeMapper.mapToJavaType(this.sqlType);
            return;
        }

        try {
            // Λήψη και καθαρισμός του SQL τύπου
            String text = token.getText().trim();
            this.sqlType = extractSqlType(text); // Καθαρισμός τύπου
            this.javaType = TypeMapper.mapToJavaType(this.sqlType); // Χαρτογράφηση σε Java τύπο

            logger.debug("Set sqlType: {}, javaType: {}", this.sqlType, this.javaType);
        } catch (Exception e) {
            logger.error("Error while extracting SQL type from token: {}", token.getText(), e);
            this.sqlType = "VARCHAR";
            this.javaType = TypeMapper.mapToJavaType(this.sqlType);
        }
    }

    /**
     * Καθαρίζει τον SQL τύπο, αφαιρώντας constraints όπως (255).
     *
     * @param sqlTypeText το αρχικό κείμενο του SQL τύπου
     * @return ο καθαρός SQL τύπος
     */
    private String extractSqlType(String sqlTypeText) {
        // Αφαιρούμε constraints όπως (255) ή CHECK
        return sqlTypeText.replaceAll("\\([^)]*\\)", "").trim().toUpperCase();

    }




    /**
     * Determines if the column is a primary key based on the provided token.
     *
     * @param token the token containing column constraints
     */
    public void isPrimaryKey(Token token) {
        if (token != null && token.getText() != null) {
            this.primaryKey = token.getText().toUpperCase().contains("PRIMARY KEY");
            logger.debug("Set primaryKey: {}", this.primaryKey);
        }
    }

    /**
     * Determines if the column is nullable based on the provided token.
     *
     * @param token the token containing column constraints
     */
    public void isNullable(Token token) {
        if (token != null && token.getText() != null) {
            this.nullable = !token.getText().toUpperCase().contains("NOT NULL");
            logger.debug("Set nullable: {}", this.nullable);
        }
    }

    /**
     * Determines if the column is unique based on the provided token.
     *
     * @param token the token containing column constraints
     */
    public void isUnique(Token token) {
        if (token != null && token.getText() != null) {
            this.unique = token.getText().toUpperCase().contains("UNIQUE");
            logger.debug("Set unique: {}", this.unique);
        }
    }

    /**
     * Extracts and sets the default value from the provided token.
     *
     * @param token the token containing column constraints
     */
    public void isDefaultValue(Token token) {
        if (token != null && token.getText() != null && token.getText().contains("DEFAULT")) {
            try {
                String[] parts = token.getText().split("DEFAULT");
                if (parts.length > 1) {
                    this.defaultValue = parts[1].trim().split("\\s+")[0].replaceAll("[\"']", "");
                    logger.debug("Set defaultValue: {}", this.defaultValue);
                }
            } catch (Exception e) {
                logger.warn("Error extracting default value from token: {}", token.getText(), e);
            }
        }
    }

    /**
     * Sets the check constraint from the provided token.
     *
     * @param token the token containing column constraints
     */
    public void setCheckConstraint(Token token) {
        if (token != null && token.getText() != null && token.getText().contains("CHECK")) {
            int start = token.getText().indexOf("CHECK") + 5;
            int openParen = token.getText().indexOf("(", start);
            int closeParen = token.getText().lastIndexOf(")");
            if (openParen != -1 && closeParen != -1 && closeParen > openParen) {
                this.checkConstraint = token.getText().substring(openParen, closeParen + 1).trim();
                logger.debug("Set checkConstraint: {}", this.checkConstraint);
            }
        }
    }

    /**
     * Determines if the column is a foreign key and sets the referenced table and column.
     *
     * @param token the token containing foreign key details
     */
    public void isForeignKey(Token token) {
        if (token != null && token.getText() != null && token.getText().toUpperCase().contains("REFERENCES")) {
            try {
                this.foreignKey = true;
                String text = token.getText();
                int refIndex = text.toUpperCase().indexOf("REFERENCES") + 10;
                String[] parts = text.substring(refIndex).trim().split("\\s+|\\(");
                this.referencedTable = parts.length > 0 ? parts[0] : null;

                int openParen = text.indexOf("(");
                int closeParen = text.indexOf(")");
                if (openParen != -1 && closeParen != -1 && closeParen > openParen) {
                    this.referencedColumn = text.substring(openParen + 1, closeParen).trim();
                }

                logger.debug("Set foreignKey: {}, referencedTable: {}, referencedColumn: {}",
                        this.foreignKey, this.referencedTable, this.referencedColumn);
            } catch (Exception e) {
                logger.warn("Error extracting foreign key details from token: {}", token.getText(), e);
            }
        }
    }

    /**
     * Extracts the length of the column from the SQL type.
     *
     * @param sqlType the SQL type string
     * @return the length of the column
     */
    public static int extractLength(String sqlType) {
        if (sqlType == null) {
            return 255; // Default length
        }

        try {
            if (sqlType.contains("(") && sqlType.contains(")")) {
                String insideParentheses = sqlType.substring(sqlType.indexOf("(") + 1, sqlType.indexOf(")"));
                String[] parts = insideParentheses.split(",");
                return Integer.parseInt(parts[0].trim());
            }
        } catch (NumberFormatException e) {
            logger.warn("Invalid length format in SQL type '{}'. Defaulting to 255.", sqlType);
        }
        return 255; // Default length for all other cases
    }

    /**
     * Sets the referenced table from the provided token.
     *
     * @param token the token containing foreign key details
     */
    public void setReferencedTableFromToken(Token token) {
        if (token != null && token.getText() != null && token.getText().toUpperCase().contains("REFERENCES")) {
            try {
                String text = token.getText();
                int refIndex = text.toUpperCase().indexOf("REFERENCES") + 10;
                String[] parts = text.substring(refIndex).trim().split("\\s+|\\(");
                this.referencedTable = parts.length > 0 ? parts[0] : null;
                logger.debug("Set referencedTable: {}", this.referencedTable);
            } catch (Exception e) {
                logger.warn("Error extracting referenced table from token: {}", token.getText(), e);
            }
        }
    }

    /**
     * Sets the referenced column from the provided token.
     *
     * @param token the token containing foreign key details
     */
    public void setReferencedColumnFromToken(Token token) {
        if (token != null && token.getText() != null && token.getText().toUpperCase().contains("REFERENCES")) {
            try {
                String text = token.getText();
                int refIndex = text.toUpperCase().indexOf("REFERENCES") + 10;
                int openParen = text.indexOf("(", refIndex);
                int closeParen = text.indexOf(")", openParen);
                if (openParen != -1 && closeParen != -1 && closeParen > openParen) {
                    this.referencedColumn = text.substring(openParen + 1, closeParen).trim();
                    logger.debug("Set referencedColumn: {}", this.referencedColumn);
                }
            } catch (Exception e) {
                logger.warn("Error extracting referenced column from token: {}", token.getText(), e);
            }
        }
    }

    /**
     * Converts the ColumnDefinition to a Column object.
     *
     * @return the converted Column object
     */
    /**
     * Converts the ColumnDefinition to a Column object.
     *
     * @return the converted Column object
     */
    public Column toColumn() {
        logger.info("Converting ColumnDefinition to Column object...");

        // Log all initial ColumnDefinition fields
        logger.debug("ColumnDefinition values: name={}, javaType={}, sqlType={}, length={}, primaryKey={}, nullable={}, defaultValue={}, unique={}, checkConstraint={}, referencedTable={}, referencedColumn={}",
                this.columnName, this.javaType, this.sqlType, this.length, this.primaryKey, this.nullable, this.defaultValue, this.unique, this.checkConstraint, this.referencedTable, this.referencedColumn);

        Column column = new Column();
        column.setName(this.columnName);
        column.setJavaType(this.javaType);
        column.setLength(this.length);
        column.setPrimaryKey(this.primaryKey);
        column.setNullable(this.nullable);
        column.setDefaultValue(this.defaultValue);
        column.setUnique(this.unique);
        column.setCheckConstraint(this.checkConstraint);
        column.setReferencedTable(this.referencedTable);
        column.setReferencedColumn(this.referencedColumn);
        column.setSqlType(this.sqlType);

        // Log the final Column object
        logger.debug("Converted Column: name={}, javaType={}, sqlType={}, length={}, primaryKey={}, nullable={}, defaultValue={}, unique={}, checkConstraint={}, referencedTable={}, referencedColumn={}",
                column.getName(), column.getJavaType(), column.getSqlType(), column.getLength(), column.isPrimaryKey(), column.isNullable(), column.getDefaultValue(), column.isUnique(), column.getCheckConstraint(), column.getReferencedTable(), column.getReferencedColumn());

        return column;
    }

}
