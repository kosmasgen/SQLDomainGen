package com.sqldomaingen.parser;

import com.sqldomaingen.util.TypeMapper;
import com.sqldomaingen.model.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Log4j2
public class ColumnDefinition extends PostgreSQLBaseListener {

    private String columnName;
    private String sqlType;
    private String javaType;
    private int length;
    private boolean primaryKey = false;
    private boolean nullable = true;
    private boolean unique = false;
    private String defaultValue;
    private String checkConstraint;
    private String referencedTable;
    private String referencedColumn;
    private boolean foreignKey = false;
    private int precision;
    private int scale;
    private String onUpdate;
    private String onDelete;
    private String mappedBy;
    private boolean manyToMany = false;

    /**
     * Builds a {@link ColumnDefinition} by walking the ANTLR parse context with this listener.
     * <p>
     * The resulting object contains:
     * <ul>
     *   <li>column name</li>
     *   <li>raw SQL type + extracted attributes (length/precision/scale)</li>
     *   <li>basic column constraints (PK/nullable/unique/default/check)</li>
     *   <li>FK metadata if the column contains REFERENCES</li>
     * </ul>
     *
     * @param ctx ANTLR column definition context
     * @return populated {@link ColumnDefinition}
     */
    public static ColumnDefinition fromContext(PostgreSQLParser.ColumnDefContext ctx) {
        if (ctx == null) {
            throw new IllegalArgumentException("ColumnDefContext is null");
        }

        log.info("Parsing column definition: {}", ctx.getText());

        ColumnDefinition columnDefinition = new ColumnDefinition();
        ParseTreeWalker.DEFAULT.walk(columnDefinition, ctx);

        // Map SQL type to Java type after the listener collected sqlType.
        columnDefinition.javaType = TypeMapper.mapToJavaType(columnDefinition.sqlType);

        log.info("Parsed column -> name='{}', sqlType='{}', javaType='{}', pk={}",
                columnDefinition.getColumnName(),
                columnDefinition.getSqlType(),
                columnDefinition.getJavaType(),
                columnDefinition.isPrimaryKey());

        return columnDefinition;
    }

    /**
     * Captures the column name when entering a column definition.
     */
    @Override
    public void enterColumnDef(PostgreSQLParser.ColumnDefContext ctx) {
        columnName = ctx.columnName().getText();
        log.debug("Column name extracted: {}", columnName);
    }

    /**
     * Captures the SQL data type and extracts size attributes.
     * <p>
     * Examples:
     * <ul>
     *   <li>VARCHAR(100) -> length=100</li>
     *   <li>DECIMAL(12,2) -> precision=12, scale=2</li>
     * </ul>
     */
    @Override
    public void enterDataType(PostgreSQLParser.DataTypeContext ctx) {
        sqlType = ctx.getText().toUpperCase();

        if (ctx.decimalType() != null) {
            precision = ctx.decimalType().precision != null ? Integer.parseInt(ctx.decimalType().precision.getText()) : 0;
            scale = ctx.decimalType().scale != null ? Integer.parseInt(ctx.decimalType().scale.getText()) : 0;
        }

        length = extractLength(sqlType);

        log.debug("SQL type extracted: '{}' | precision={} | scale={} | length={}", sqlType, precision, scale, length);
    }

    /**
     * Handles column-level constraints and updates the flags/metadata accordingly.
     * <p>
     * Supports:
     * <ul>
     *   <li>PRIMARY KEY</li>
     *   <li>NOT NULL</li>
     *   <li>UNIQUE</li>
     *   <li>DEFAULT ...</li>
     *   <li>CHECK (...)</li>
     *   <li>REFERENCES ...</li>
     * </ul>
     */
    @Override
    public void enterConstraint(PostgreSQLParser.ConstraintContext ctx) {
        String constraintText = ctx.getText().toUpperCase();

        if (constraintText.contains("PRIMARY KEY")) {
            this.primaryKey = true;
            this.nullable = false; // PRIMARY KEY implies NOT NULL
            log.info("Column '{}' marked as PRIMARY KEY", this.columnName);
        }

        if (constraintText.contains("NOT NULL")) {
            this.nullable = false;
        }

        if (constraintText.contains("UNIQUE")) {
            this.unique = true;
        }

        // Note: This is not a standard SQL keyword. Keep it only if your grammar emits it intentionally.
        if (constraintText.contains("MANYTOMANY")) {
            this.manyToMany = true;
        }

        if (constraintText.contains("DEFAULT")) {
            this.defaultValue = extractDefaultValue(ctx);
        }

        if (constraintText.contains("CHECK")) {
            this.checkConstraint = extractCheckConstraint(ctx);
        }

        if (constraintText.contains("REFERENCES")) {
            this.foreignKey = true;
            extractForeignKeyDetails(ctx);
        }

        log.debug("Constraints extracted for '{}' -> pk={}, nullable={}, unique={}, manyToMany={}, default={}, check={}, fk={}",
                this.columnName, this.primaryKey, this.nullable, this.unique, this.manyToMany, this.defaultValue, this.checkConstraint, this.foreignKey);
    }

    /**
     * Returns the SQL base type without parameters (e.g. VARCHAR(20) -> VARCHAR).
     */
    public String getBaseSqlType() {
        return sqlType.contains("(") ? sqlType.substring(0, sqlType.indexOf("(")) : sqlType;
    }

    /**
     * Extracts the "length" argument from SQL types that include size (e.g. VARCHAR(255)).
     * Returns a default value when not available or not parseable.
     */
    private int extractLength(String typeText) {
        if (typeText.contains("(") && typeText.contains(")")) {
            try {
                String insideParentheses = typeText.substring(typeText.indexOf("(") + 1, typeText.indexOf(")"));
                String[] parts = insideParentheses.split(",");
                return Integer.parseInt(parts[0].trim());
            } catch (NumberFormatException e) {
                log.warn("Invalid length format in SQL type '{}'. Defaulting to 255.", typeText);
            }
        }
        return 255;
    }

    /**
     * Extracts the DEFAULT value from the constraint context.
     */
    private String extractDefaultValue(PostgreSQLParser.ConstraintContext ctx) {
        if (ctx.value() != null) {
            return ctx.value().getText().replaceAll("[\"']", "").trim();
        }
        return null;
    }

    /**
     * Extracts the CHECK constraint expression and normalizes basic operator spacing.
     */
    private String extractCheckConstraint(PostgreSQLParser.ConstraintContext ctx) {
        if (ctx.getText().contains("CHECK")) {
            int start = ctx.getText().indexOf("CHECK") + 5;
            int openParen = ctx.getText().indexOf("(", start);
            int closeParen = ctx.getText().lastIndexOf(")");

            if (openParen != -1 && closeParen != -1 && closeParen > openParen) {
                String constraint = ctx.getText().substring(openParen + 1, closeParen).trim();

                // Add spaces around common operators to make the expression readable.
                constraint = constraint.replaceAll("\\s*(>=|<=|>|<|=|AND|OR|BETWEEN)\\s*", " $1 ");

                return "(" + constraint + ")";
            }
        }
        return null;
    }

    /**
     * Extracts FK target table/column from a REFERENCES clause.
     */
    private void extractForeignKeyDetails(PostgreSQLParser.ConstraintContext ctx) {
        log.debug("Extracting FOREIGN KEY details from: {}", ctx.getText());

        if (ctx.tableName() != null) {
            referencedTable = ctx.tableName().getText();
        } else {
            log.warn("FK extraction: tableName is null.");
        }

        if (ctx.columnName() != null) {
            referencedColumn = ctx.columnName().getText();
        } else {
            log.warn("FK extraction: columnName is null.");
        }

        log.info("FK extracted -> referencedTable='{}', referencedColumn='{}'", referencedTable, referencedColumn);
    }

    /**
     * Converts this definition to the internal {@link Column} model used by the generator.
     */
    public Column toColumn() {
        log.info("Converting ColumnDefinition -> Column | name='{}'", this.columnName);

        Column column = new Column();
        column.setName(this.columnName);
        column.setJavaType(this.javaType);
        column.setSqlType(this.sqlType);
        column.setLength(this.length);
        column.setPrecision(this.precision);
        column.setScale(this.scale);
        column.setPrimaryKey(this.primaryKey);
        column.setNullable(this.nullable);
        column.setDefaultValue(this.defaultValue);
        column.setUnique(this.unique);
        column.setManyToMany(this.manyToMany);
        column.setCheckConstraint(this.checkConstraint);
        column.setOnDelete(this.onDelete);
        column.setOnUpdate(this.onUpdate);
        column.setMappedBy(this.mappedBy);

        if (this.foreignKey) {
            column.setForeignKey(true);
            column.setReferencedTable(this.referencedTable);
            column.setReferencedColumn(this.referencedColumn);

            log.debug("FK column mapped: '{}' -> {}.{} (mappedBy={})",
                    this.columnName, this.referencedTable, this.referencedColumn, this.mappedBy);

            log.info("Column is a FOREIGN KEY: {} -> {}.{}",
                    column.getName(), column.getReferencedTable(), column.getReferencedColumn());
        }

        return column;
    }
}
