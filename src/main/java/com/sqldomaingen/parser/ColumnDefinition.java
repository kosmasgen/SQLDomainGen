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
    private boolean  manyToMany = false;


    /**
     * Factory method που δημιουργεί `ColumnDefinition` χρησιμοποιώντας `Listener`
     */
    public static ColumnDefinition fromContext(PostgreSQLParser.ColumnDefContext ctx) {
        if (ctx == null) {
            throw new IllegalArgumentException("ColumnDefContext is null");
        }

        log.info("Parsing Column Definition -> {}", ctx.getText());

        ColumnDefinition columnDefinition = new ColumnDefinition();
        ParseTreeWalker.DEFAULT.walk(columnDefinition, ctx);
        columnDefinition.javaType = TypeMapper.mapToJavaType(columnDefinition.sqlType);
        log.info("Parsed Column -> Name: {}, SQL Type: {}, Java Type: {}, Primary Key: {}",
                columnDefinition.getColumnName(), columnDefinition.getSqlType(), columnDefinition.getJavaType(), columnDefinition.isPrimaryKey());

        return columnDefinition;
    }

    /**
     * Listener για την εξαγωγή του ονόματος της στήλης
     */
    @Override
    public void enterColumnDef(PostgreSQLParser.ColumnDefContext ctx) {
        columnName = ctx.columnName().getText();
        log.debug("Extracted column name: {}", columnName);
    }

    /**
     * Listener για την εξαγωγή του τύπου δεδομένων SQL και των παραμέτρων του (π.χ., DECIMAL(precision, scale))
     */
    @Override
    public void enterDataType(PostgreSQLParser.DataTypeContext ctx) {
        sqlType = ctx.getText().toUpperCase();

        if (ctx.decimalType() != null) {
            precision = ctx.decimalType().precision != null ? Integer.parseInt(ctx.decimalType().precision.getText()) : 0;
            scale = ctx.decimalType().scale != null ? Integer.parseInt(ctx.decimalType().scale.getText()) : 0;
        }
        length = extractLength(sqlType);

        log.debug("Extracted SQL type: {}, precision: {}, scale: {}", sqlType, precision, scale);
    }


    /**
     * Listener για `PRIMARY KEY`, `UNIQUE`, `NOT NULL`, και άλλα constraints
     */
    @Override
    public void enterConstraint(PostgreSQLParser.ConstraintContext ctx) {
        String constraintText = ctx.getText().toUpperCase();

        if (constraintText.contains("PRIMARY KEY")) {
            this.primaryKey = true;
            this.nullable = false; // PRIMARY KEY σημαίνει NOT NULL
            log.info("🔑 Column '{}' marked as PRIMARY KEY", this.columnName);
        }
        if (constraintText.contains("NOT NULL")) {
            this.nullable = false;
        }
        if (constraintText.contains("UNIQUE")) {
            this.unique = true;
        }
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

        log.debug("Extracted constraints for column '{}': PK={}, Nullable={}, Unique={}, ManyToMany{}, Default={}, Check={}, FK={}",
                this.columnName, this.primaryKey, this.nullable, this.unique, this.manyToMany, this.defaultValue, this.checkConstraint, this.foreignKey);
    }


    public String getBaseSqlType() {
        return sqlType.contains("(") ? sqlType.substring(0, sqlType.indexOf("(")) : sqlType;
    }


    /**
     * Υπολογίζει το μήκος του πεδίου αν υπάρχει (π.χ., VARCHAR(255))
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
     * Εξάγει την προεπιλεγμένη τιμή (`DEFAULT `)
     */
    private String extractDefaultValue(PostgreSQLParser.ConstraintContext ctx) {
        if (ctx.value() != null) {
            return ctx.value().getText().replaceAll("[\"']", "").trim();
        }
        return null;
    }


    /**
     * Εξάγει το `CHECK ()` constraint αν υπάρχει και διατηρεί σωστή μορφοποίηση.
     */
    private String extractCheckConstraint(PostgreSQLParser.ConstraintContext ctx) {
        if (ctx.getText().contains("CHECK")) {
            int start = ctx.getText().indexOf("CHECK") + 5;
            int openParen = ctx.getText().indexOf("(", start);
            int closeParen = ctx.getText().lastIndexOf(")");

            if (openParen != -1 && closeParen != -1 && closeParen > openParen) {
                String constraint = ctx.getText().substring(openParen + 1, closeParen).trim();

                // Διαχωρίζουμε σωστά τους τελεστές με κενά
                constraint = constraint.replaceAll("\\s*(>=|<=|>|<|=|AND|OR|BETWEEN)\\s*", " $1 ");

                return "(" + constraint + ")"; // Προσθέτουμε ξανά τις παρενθέσεις
            }
        }
        return null;
    }

    /**
     * Εξάγει τις πληροφορίες του Foreign Key (`REFERENCES ...`)
     */

    private void extractForeignKeyDetails(PostgreSQLParser.ConstraintContext ctx) {
        log.debug("Extracting Foreign Key: Context Text -> {}", ctx.getText());

        if (ctx.tableName() != null) {
            referencedTable = ctx.tableName().getText();
        } else {
            log.warn("ForeignKey Extraction: tableName is NULL!");
        }

        if (ctx.columnName() != null) {
            referencedColumn = ctx.columnName().getText();
        } else {
            log.warn("ForeignKey Extraction: columnName is NULL!");
        }

        log.debug("Foreign Key Check for column: {} -> foreignKey={}, referencedTable={}, referencedColumn={}",
                this.columnName, this.foreignKey, this.referencedTable, this.referencedColumn);

        log.info("Extracted Foreign Key -> Referenced Table: {}, Referenced Column: {}", referencedTable, referencedColumn);
    }


    public Column toColumn() {
        log.info("Converting ColumnDefinition to Column object...");
        log.debug("Before Column creation: name={}, foreignKey={}, referencedTable={}, referencedColumn={}, mappedBy={}",
                this.columnName, this.foreignKey, this.referencedTable, this.referencedColumn, this.mappedBy);

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

            log.debug("\u001B[33mForeign Key Check for column: {}, referencedTable={}, referencedColumn={}, mappedBy={}\u001B[0m",
                    this.columnName, this.referencedTable, this.referencedColumn, this.mappedBy);

            log.info("\u001B[32m✅ Column is Foreign Key: {} -> {}.{} (mappedBy={})\u001B[0m",
                    column.getName(), column.getReferencedTable(), column.getReferencedColumn(), column.getMappedBy());
        }

        return column;
    }
}