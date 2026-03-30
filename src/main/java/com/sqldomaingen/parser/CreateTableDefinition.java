package com.sqldomaingen.parser;

import com.sqldomaingen.model.Column;
import com.sqldomaingen.model.Table;
import com.sqldomaingen.model.UniqueConstraint;
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
    private List<UniqueConstraint> compositeUniqueConstraints = new ArrayList<>();

    public Table processCreateTable(PostgreSQLParser.CreateTableStatementContext ctx) {
        log.info("Parse tree (CreateTableStatement): \n{}", ctx.toStringTree());
        log.info("processCreateTable() - START | ctx: {}", ctx);

        this.tableName = null;
        this.columnDefinitions = new ArrayList<>();
        this.constraints = new ArrayList<>();
        this.compositeUniqueConstraints = new ArrayList<>();

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

        // Apply table-level UNIQUE constraints before resolving FK relationships.
        extractUniqueConstraints(ctx);

        // Extract table-level FOREIGN KEY constraints and mark FK columns accordingly.
        extractForeignKeyConstraints(ctx);

        Table table = toTable();
        log.info("processCreateTable() - END | Generated Table: {}", table.getName());

        return table;
    }

    /**
     * Extracts the physical table name from a CREATE TABLE statement.
     * <p>
     * The parser layer must keep the SQL table name as-is so that downstream
     * relationship resolution works with real database identifiers.
     *
     * @param ctx the CREATE TABLE parse context
     * @return the physical table name, including schema when present
     */
    public String extractTableName(PostgreSQLParser.CreateTableStatementContext ctx) {
        if (ctx == null || ctx.tableName() == null || ctx.tableName().isEmpty()) {
            throw new IllegalArgumentException("Table name not found in CREATE TABLE statement.");
        }

        this.tableName = ctx.tableName().get(0).getText().replace("\"", "").trim();
        log.info("Extracted physical table name: {}", this.tableName);

        return this.tableName;
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

    /**
     * Extracts table-level foreign key constraints and applies them
     * to the corresponding column definitions.
     *
     * @param ctx the CREATE TABLE parse context
     */
    public void extractForeignKeyConstraints(PostgreSQLParser.CreateTableStatementContext ctx) {
        log.info("extractForeignKeyConstraints() - START");

        if (ctx == null || ctx.tableConstraint() == null || ctx.tableConstraint().isEmpty()) {
            log.info("extractForeignKeyConstraints() - END (no table constraints)");
            return;
        }

        for (PostgreSQLParser.TableConstraintContext tableConstraintContext : ctx.tableConstraint()) {
            if (tableConstraintContext == null) {
                continue;
            }

            String constraintText = tableConstraintContext.getText();
            if (constraintText == null || constraintText.isBlank()) {
                continue;
            }

            String normalizedConstraintText = constraintText
                    .replace("\"", "")
                    .replaceAll("\\s+", "")
                    .toUpperCase(java.util.Locale.ROOT);

            boolean foreignKeyConstraint =
                    normalizedConstraintText.contains("FOREIGNKEY")
                            && normalizedConstraintText.contains("REFERENCES");

            if (!foreignKeyConstraint) {
                continue;
            }

            List<PostgreSQLParser.ColumnNameListContext> columnNameLists = tableConstraintContext.columnNameList();
            if (columnNameLists == null || columnNameLists.isEmpty()) {
                log.warn("Foreign key constraint found but no columnNameList was parsed: {}", constraintText);
                continue;
            }

            List<String> sourceColumns = columnNameLists.get(0).columnName().stream()
                    .map(columnNameContext -> columnNameContext.getText().replace("\"", "").trim())
                    .filter(columnName -> !columnName.isBlank())
                    .toList();

            List<String> referencedColumns = java.util.Collections.emptyList();
            if (columnNameLists.size() > 1) {
                referencedColumns = columnNameLists.get(1).columnName().stream()
                        .map(columnNameContext -> columnNameContext.getText().replace("\"", "").trim())
                        .filter(columnName -> !columnName.isBlank())
                        .toList();
            }

            String referencedTableName = tableConstraintContext.tableName() != null
                    ? tableConstraintContext.tableName().getText().replace("\"", "").trim()
                    : null;

            String referencedSchemaName = tableConstraintContext.schemaName() != null
                    ? tableConstraintContext.schemaName().getText().replace("\"", "").trim()
                    : null;

            if (referencedTableName == null || referencedTableName.isBlank()) {
                log.warn("Foreign key constraint found but referenced table is missing: {}", constraintText);
                continue;
            }

            String referencedTable = referencedSchemaName != null && !referencedSchemaName.isBlank()
                    ? referencedSchemaName + "." + referencedTableName
                    : referencedTableName;

            String onDeleteAction = extractReferentialAction(normalizedConstraintText, "ONDELETE");
            String onUpdateAction = extractReferentialAction(normalizedConstraintText, "ONUPDATE");

            for (int index = 0; index < sourceColumns.size(); index++) {
                String sourceColumn = sourceColumns.get(index);

                String referencedColumn;
                if (!referencedColumns.isEmpty() && referencedColumns.size() == sourceColumns.size()) {
                    referencedColumn = referencedColumns.get(index);
                } else if (sourceColumns.size() == 1) {
                    referencedColumn = "id";
                } else {
                    referencedColumn = null;
                }

                columnDefinitions.stream()
                        .filter(columnDefinition -> columnDefinition.getColumnName().equalsIgnoreCase(sourceColumn))
                        .findFirst()
                        .ifPresentOrElse(columnDefinition -> {
                            columnDefinition.setForeignKey(true);
                            columnDefinition.setReferencedTable(referencedTable);
                            columnDefinition.setReferencedColumn(referencedColumn);
                            columnDefinition.setOnDelete(onDeleteAction);
                            columnDefinition.setOnUpdate(onUpdateAction);

                            log.info("Foreign key applied: {} -> {}.{}",
                                    sourceColumn,
                                    referencedTable,
                                    referencedColumn);

                            if (onDeleteAction != null) {
                                log.info("ON DELETE applied to column '{}': {}", sourceColumn, onDeleteAction);
                            }

                            if (onUpdateAction != null) {
                                log.info("ON UPDATE applied to column '{}': {}", sourceColumn, onUpdateAction);
                            }
                        }, () -> log.warn("Could not find source column '{}' for FK constraint '{}'",
                                sourceColumn,
                                constraintText));
            }
        }

        log.info("extractForeignKeyConstraints() - END");
    }


    /**
     * Extracts a referential action from a normalized constraint text.
     *
     * @param constraintText the uppercase constraint text without guaranteed whitespace
     * @param keyword the keyword to search for, e.g. ONDELETE or ONUPDATE
     * @return the extracted action or null when not found
     */
    private String extractReferentialAction(String constraintText, String keyword) {
        int keywordIndex = constraintText.indexOf(keyword);
        if (keywordIndex < 0) {
            return null;
        }

        String tail = constraintText.substring(keywordIndex + keyword.length());

        if (tail.startsWith("CASCADE")) {
            return "CASCADE";
        }
        if (tail.startsWith("SETNULL")) {
            return "SET NULL";
        }
        if (tail.startsWith("SETDEFAULT")) {
            return "SET DEFAULT";
        }
        if (tail.startsWith("RESTRICT")) {
            return "RESTRICT";
        }
        if (tail.startsWith("NOACTION")) {
            return "NO ACTION";
        }

        return null;
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
     * Extracts table-level UNIQUE constraints and applies them to matching columns.
     * <p>
     * Single-column UNIQUE constraints are promoted to column uniqueness.
     * Multi-column UNIQUE constraints are stored as table-level unique constraints.
     *
     * @param ctx the CREATE TABLE parse context
     */
    public void extractUniqueConstraints(PostgreSQLParser.CreateTableStatementContext ctx) {
        log.info("extractUniqueConstraints() - START");

        if (ctx == null || ctx.tableConstraint() == null || ctx.tableConstraint().isEmpty()) {
            log.info("extractUniqueConstraints() - END (no table constraints)");
            return;
        }

        for (PostgreSQLParser.TableConstraintContext tableConstraintContext : ctx.tableConstraint()) {
            if (tableConstraintContext == null) {
                continue;
            }

            String constraintText = tableConstraintContext.getText();
            if (constraintText == null || constraintText.isBlank()) {
                continue;
            }

            String normalizedConstraintText = constraintText
                    .replace("\"", "")
                    .replaceAll("\\s+", "")
                    .toUpperCase(java.util.Locale.ROOT);

            if (!normalizedConstraintText.contains("UNIQUE")) {
                continue;
            }

            List<PostgreSQLParser.ColumnNameListContext> columnNameLists = tableConstraintContext.columnNameList();
            if (columnNameLists == null || columnNameLists.isEmpty()) {
                continue;
            }

            List<String> uniqueColumns = columnNameLists.get(0).columnName().stream()
                    .map(columnNameContext -> columnNameContext.getText().replace("\"", "").trim())
                    .filter(columnName -> !columnName.isBlank())
                    .toList();

            String constraintName = extractConstraintName(constraintText);

            if (uniqueColumns.size() == 1) {
                String uniqueColumnName = uniqueColumns.get(0);

                columnDefinitions.stream()
                        .filter(columnDefinition -> columnDefinition.getColumnName().equalsIgnoreCase(uniqueColumnName))
                        .findFirst()
                        .ifPresentOrElse(columnDefinition -> {
                            columnDefinition.setUnique(true);
                            log.info("UNIQUE applied to column: {}", uniqueColumnName);
                        }, () -> log.warn("Could not find column '{}' for UNIQUE constraint '{}'",
                                uniqueColumnName,
                                constraintText));

                continue;
            }

            UniqueConstraint uniqueConstraint = new UniqueConstraint(
                    constraintName,
                    new ArrayList<>(uniqueColumns)
            );

            this.compositeUniqueConstraints.add(uniqueConstraint);

            log.info("Composite UNIQUE detected: {} -> {}", constraintName, uniqueColumns);
        }

        log.info("extractUniqueConstraints() - END");
    }


    /**
     * Extracts the constraint name from a table constraint text.
     * <p>
     * Examples:
     * <ul>
     *     <li>CONSTRAINT uk_chamber_department UNIQUE (chamber_id, chamber_department_id)
     *     -> uk_chamber_department</li>
     *     <li>UNIQUE (email) -> generated fallback name</li>
     * </ul>
     *
     * @param constraintText raw constraint text
     * @return extracted constraint name or generated fallback name
     */
    private String extractConstraintName(String constraintText) {
        if (constraintText == null || constraintText.isBlank()) {
            return "uk_unknown";
        }

        String trimmedConstraintText = constraintText.replace("\"", "").trim();

        String upperConstraintText = trimmedConstraintText.toUpperCase(java.util.Locale.ROOT);
        int constraintIndex = upperConstraintText.indexOf("CONSTRAINT");
        int uniqueIndex = upperConstraintText.indexOf("UNIQUE");

        if (constraintIndex >= 0 && uniqueIndex > constraintIndex) {
            String extractedName = trimmedConstraintText
                    .substring(constraintIndex + "CONSTRAINT".length(), uniqueIndex)
                    .trim();

            if (!extractedName.isBlank()) {
                return extractedName;
            }
        }

        return "uk_" + normalizeTableName(this.tableName);
    }


    /**
     * Removes schema prefix from a physical table name.
     *
     * @param tableName raw table name
     * @return schema-free table name
     */
    private String normalizeTableName(String tableName) {
        if (tableName == null || tableName.isBlank()) {
            return "";
        }

        String trimmedTableName = tableName.trim();
        int dotIndex = trimmedTableName.lastIndexOf('.');

        if (dotIndex >= 0 && dotIndex < trimmedTableName.length() - 1) {
            return trimmedTableName.substring(dotIndex + 1);
        }

        return trimmedTableName;
    }


    /**
     * Converts this parser result into the internal {@link Table} model.
     *
     * @return populated table model
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
        table.setUniqueConstraints(new ArrayList<>(this.compositeUniqueConstraints));

        log.info("toTable() - END | Table '{}' with {} columns and {} composite unique constraints.",
                table.getName(),
                columns.size(),
                table.getUniqueConstraints().size());

        return table;
    }
}
