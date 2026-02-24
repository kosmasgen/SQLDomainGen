package com.sqldomaingen.parser;

import com.sqldomaingen.model.Column;
import com.sqldomaingen.model.Table;
import com.sqldomaingen.util.NamingConverter;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;

import java.util.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Log4j2
public class CreateTableDefinition {

    private String tableName;
    private List<ColumnDefinition> columnDefinitions = new ArrayList<>();
    private List<String> constraints = new ArrayList<>();

    public Table processCreateTable(PostgreSQLParser.CreateTableStatementContext ctx) {
        log.info("Parse tree (CreateTableStatement): \n{}", ctx.toStringTree());
        log.info("processCreateTable() - START | ctx: {}", ctx);

        this.tableName = extractTableName(ctx);
        log.info("Extracted table name: {}", this.tableName);

        log.debug("Column definitions BEFORE extraction: {}", this.columnDefinitions);
        this.columnDefinitions = extractColumnDefinitions(ctx);
        log.info("Column definitions AFTER extraction | count={}", this.columnDefinitions.size());

        // Apply table-level PRIMARY KEY constraints to the corresponding columns.
        if (ctx.tableConstraint() != null) {
            for (PostgreSQLParser.TableConstraintContext constraintCtx : ctx.tableConstraint()) {
                extractPrimaryKeyConstraint(constraintCtx);
            }
        }

        // Extract table-level FOREIGN KEY constraints and mark FK columns accordingly.
        extractForeignKeyConstraints(ctx);

        Table table = toTable();
        log.info("processCreateTable() - END | Generated Table: {}", table.getName());

        return table;
    }

    public String extractTableName(PostgreSQLParser.CreateTableStatementContext ctx) {
        if (ctx == null || ctx.tableName() == null || ctx.tableName().isEmpty()) {
            throw new IllegalArgumentException("Table name not found in CREATE TABLE statement.");
        }

        this.tableName = ctx.tableName().get(0).getText();
        log.info("Extracted raw table name: {}", tableName);

        // Remove schema prefix if present (e.g. public.department -> department).
        if (tableName.contains(".")) {
            tableName = tableName.substring(tableName.indexOf(".") + 1);
        }

        // The internal Table model uses PascalCase entity/table naming.
        return NamingConverter.toPascalCase(tableName);
    }

    public List<ColumnDefinition> extractColumnDefinitions(PostgreSQLParser.CreateTableStatementContext ctx) {
        log.info("extractColumnDefinitions() - START");

        List<ColumnDefinition> extractedColumns = new ArrayList<>();

        if (ctx.columnDef() != null) {
            for (PostgreSQLParser.ColumnDefContext columnCtx : ctx.columnDef()) {
                try {
                    ColumnDefinition column = ColumnDefinition.fromContext(columnCtx);
                    extractedColumns.add(column);

                    log.info("Extracted column: '{}' | SQL type: '{}' | PK: {} | Default: {}",
                            column.getColumnName(), column.getSqlType(), column.isPrimaryKey(), column.getDefaultValue());
                } catch (Exception e) {
                    log.error("Failed to extract column from: {}", columnCtx.getText(), e);
                }
            }
        } else {
            log.warn("No columns found in the CREATE TABLE statement.");
        }

        log.info("extractColumnDefinitions() - END | Extracted {} columns.", extractedColumns.size());
        return extractedColumns;
    }

    private void extractPrimaryKeyConstraint(PostgreSQLParser.TableConstraintContext ctx) {
        log.info("extractPrimaryKeyConstraint() - START");

        if (ctx == null) {
            log.warn("TableConstraintContext is null.");
            log.info("extractPrimaryKeyConstraint() - END");
            return;
        }

        // Handle table-level primary key declarations, including named constraints:
        //   PRIMARY KEY (col1, col2, ...)
        //   CONSTRAINT pk_name PRIMARY KEY (col1, col2, ...)
        String constraintText = ctx.getText();
        boolean isPrimaryKey =
                ctx.PRIMARY_KEY() != null ||
                        (constraintText != null && constraintText.toUpperCase().contains("PRIMARYKEY"));

        if (isPrimaryKey) {
            log.debug("PRIMARY KEY constraint found.");

            if (ctx.columnNameList() != null && !ctx.columnNameList().isEmpty()) {
                String primaryKeyColumns = ctx.columnNameList().get(0).getText();

                for (String primaryKeyColumn : primaryKeyColumns.replace("(", "").replace(")", "").split(",")) {
                    primaryKeyColumn = primaryKeyColumn.replace("\"", "").trim();

                    boolean found = false;
                    for (ColumnDefinition column : columnDefinitions) {
                        if (column.getColumnName().equalsIgnoreCase(primaryKeyColumn)) {
                            column.setPrimaryKey(true);
                            column.setNullable(false);
                            found = true;
                            log.info("PRIMARY KEY applied to column: {}", column.getColumnName());
                            break;
                        }
                    }

                    if (!found) {
                        log.warn("PRIMARY KEY column '{}' not found in extracted column definitions.", primaryKeyColumn);
                    }
                }
            } else {
                log.warn("PRIMARY KEY constraint found but no column list was detected.");
            }
        }

        log.info("extractPrimaryKeyConstraint() - END");
    }

    public void extractForeignKeyConstraints(PostgreSQLParser.CreateTableStatementContext ctx) {
        log.info("extractForeignKeyConstraints() - START");

        if (ctx == null || ctx.tableConstraint() == null || ctx.tableConstraint().isEmpty()) {
            log.info("extractForeignKeyConstraints() - END (no table constraints)");
            return;
        }

        for (PostgreSQLParser.TableConstraintContext constraintCtx : ctx.tableConstraint()) {
            if (constraintCtx == null) continue;

            // ANTLR getText() returns tokens concatenated WITHOUT whitespace.
            // So we must look for "FOREIGNKEY" not "FOREIGN KEY".
            String constraintText = constraintCtx.getText();
            if (constraintText == null) continue;

            String upper = constraintText.toUpperCase(Locale.ROOT);
            boolean isFk = upper.contains("FOREIGNKEY") && upper.contains("REFERENCES");
            if (!isFk) {
                continue;
            }

            List<PostgreSQLParser.ColumnNameListContext> columnLists = constraintCtx.columnNameList();
            if (columnLists == null || columnLists.isEmpty()) {
                log.warn("FOREIGN KEY constraint found but no column lists present: {}", constraintText);
                continue;
            }

            // 1st list = FK columns
            List<String> fkColumns = columnLists.get(0).columnName().stream()
                    .map(c -> c.getText().replace("\"", "").trim())
                    .filter(s -> !s.isBlank())
                    .toList();

            // 2nd list (optional) = referenced columns (can be omitted in Postgres)
            List<String> refColumns = Collections.emptyList();
            if (columnLists.size() >= 2) {
                refColumns = columnLists.get(1).columnName().stream()
                        .map(c -> c.getText().replace("\"", "").trim())
                        .filter(s -> !s.isBlank())
                        .toList();
            }

            String tableOnly = (constraintCtx.tableName() != null)
                    ? constraintCtx.tableName().getText().replace("\"", "").trim()
                    : null;

            String schemaOnly = (constraintCtx.schemaName() != null)
                    ? constraintCtx.schemaName().getText().replace("\"", "").trim()
                    : null;

            if (tableOnly == null || tableOnly.isBlank()) {
                log.warn("FOREIGN KEY constraint found but referenced table name is missing: {}", constraintText);
                continue;
            }

            String referencedTable = (schemaOnly != null && !schemaOnly.isBlank())
                    ? (schemaOnly + "." + tableOnly)
                    : tableOnly;

            if (fkColumns.isEmpty()) {
                log.warn("FOREIGN KEY constraint has empty FK column list: {}", constraintText);
                continue;
            }

            // If referenced columns are present and sizes match, map pairs.
            // Otherwise, mark FK columns with referencedTable and leave referencedColumn null.
            if (!refColumns.isEmpty() && fkColumns.size() == refColumns.size()) {
                for (int i = 0; i < fkColumns.size(); i++) {
                    String fkColumn = fkColumns.get(i);
                    String refColumn = refColumns.get(i);

                    log.info("Foreign key detected: {} -> {}.{}", fkColumn, referencedTable, refColumn);

                    final String fkColFinal = fkColumn;
                    final String refColFinal = refColumn;

                    columnDefinitions.stream()
                            .filter(col -> col.getColumnName().equals(fkColFinal))
                            .findFirst()
                            .ifPresentOrElse(col -> {
                                col.setForeignKey(true);
                                col.setReferencedTable(referencedTable);
                                col.setReferencedColumn(refColFinal);
                            }, () -> log.warn("FK column '{}' not found in extracted column definitions.", fkColFinal));
                }
            } else {
                if (refColumns.isEmpty()) {
                    log.warn("FK referenced column list is missing (PostgreSQL allows this). Will set referencedTable only. Constraint: {}", constraintText);
                } else {
                    log.warn("Composite FOREIGN KEY columns mismatch (FK cols: {}, REF cols: {}). Constraint: {}",
                            fkColumns.size(), refColumns.size(), constraintText);
                }

                for (String fkColumn : fkColumns) {
                    log.info("Foreign key detected: {} -> {} (referenced column unspecified)", fkColumn, referencedTable);

                    final String fkColFinal = fkColumn;
                    columnDefinitions.stream()
                            .filter(col -> col.getColumnName().equals(fkColFinal))
                            .findFirst()
                            .ifPresentOrElse(col -> {
                                col.setForeignKey(true);
                                col.setReferencedTable(referencedTable);
                                col.setReferencedColumn(null);
                            }, () -> log.warn("FK column '{}' not found in extracted column definitions.", fkColFinal));
                }
            }
        }

        log.info("extractForeignKeyConstraints() - END");
    }

    /**
     * Parses all CREATE TABLE statements and returns a map keyed by the generated Table name.
     */
    public Map<String, Table> parseAllTables(List<PostgreSQLParser.CreateTableStatementContext> createTableStatements) {
        log.info("parseAllTables() - START");

        Map<String, Table> tableMap = new HashMap<>();
        for (PostgreSQLParser.CreateTableStatementContext ctx : createTableStatements) {
            Table table = processCreateTable(ctx);
            tableMap.put(table.getName(), table);
            log.info("Added table '{}' to tableMap", table.getName());
        }

        log.info("parseAllTables() - END | Total tables parsed: {}", tableMap.size());
        return tableMap;
    }

    /**
     * Converts this parser result into the internal {@link Table} model.
     */
    public Table toTable() {
        log.info("toTable() - START | tableName={}", this.tableName);

        Table table = new Table();
        table.setName(this.tableName);

        List<Column> columns = this.columnDefinitions.stream()
                .map(ColumnDefinition::toColumn)
                .toList();
        table.setColumns(columns);

        table.addConstraints(this.constraints);

        log.info("toTable() - END | Table '{}' with {} columns.", table.getName(), columns.size());
        return table;
    }
}
