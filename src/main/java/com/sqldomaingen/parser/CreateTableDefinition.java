package com.sqldomaingen.parser;

import com.sqldomaingen.model.Column;
import com.sqldomaingen.model.Table;
import com.sqldomaingen.util.NamingConverter;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateTableDefinition {
    private static final Logger logger = LoggerFactory.getLogger(CreateTableDefinition.class);

    private String tableName;
    private List<ColumnDefinition> columnDefinitions = new ArrayList<>();
    private List<String> constraints = new ArrayList<>();

    public Table processCreateTable(PostgreSQLParser.CreateTableStatementContext ctx) {
        logger.info("🔍 FULL PARSE TREE: \n{}", ctx.toStringTree());
        logger.info("➡️ processCreateTable() - START | Context: {}", ctx);



        this.tableName = extractTableName(ctx);
        logger.info("Extracted table name: {}", this.tableName);

        logger.info("🛠 BEFORE extractColumnDefinitions: columnDefinitions = {}", this.columnDefinitions);
        this.columnDefinitions = extractColumnDefinitions(ctx);
        logger.info("🛠 AFTER extractColumnDefinitions: columnDefinitions = {} | Size: {}", this.columnDefinitions, this.columnDefinitions.size());

        // 🔑 Νέα μέθοδος που ανιχνεύει το PRIMARY KEY!
        if (ctx.tableConstraint() != null) {
            for (PostgreSQLParser.TableConstraintContext constraintCtx : ctx.tableConstraint()) {
                extractPrimaryKeyConstraint(constraintCtx); // Τώρα δίνουμε το σωστό context
            }
        }

        extractForeignKeyConstraints(ctx);


        Table table = toTable();
        logger.info("⬅️ processCreateTable() - END | Generated Table: {}", table.getName());

        return table;
    }

    public String extractTableName(PostgreSQLParser.CreateTableStatementContext ctx) {
        if (ctx == null || ctx.tableName() == null || ctx.tableName().isEmpty()) {
            throw new IllegalArgumentException("Table name not found in CREATE TABLE statement.");
        }

        this.tableName = ctx.tableName().get(0).getText();
        logger.info("Extracted raw table name: {}", tableName);

        // ✅ Αφαίρεση του schema αν υπάρχει
        if (tableName.contains(".")) {
            tableName = tableName.substring(tableName.indexOf(".") + 1);
        }

        return NamingConverter.toPascalCase(tableName);
    }


    public List<ColumnDefinition> extractColumnDefinitions(PostgreSQLParser.CreateTableStatementContext ctx) {
        logger.info("➡️ extractColumnDefinitions() - START | Context: {}", ctx);

        List<ColumnDefinition> extractedColumns = new ArrayList<>();

        if (ctx.columnDef() != null) {
            for (PostgreSQLParser.ColumnDefContext columnCtx : ctx.columnDef()) {
                try {
                    ColumnDefinition column = ColumnDefinition.fromContext(columnCtx);
                    extractedColumns.add(column);

                    logger.info("✅ Extracted column: {} | SQL Type: {} | Primary Key: {} | Default: {}",
                            column.getColumnName(), column.getSqlType(), column.isPrimaryKey(), column.getDefaultValue());
                } catch (Exception e) {
                    logger.error("❌ Error extracting column from context: {}", columnCtx.getText(), e);
                }
            }
        } else {
            logger.warn("⚠️ No columns found in the CREATE TABLE statement.");
        }

        logger.info("⬅️ extractColumnDefinitions() - END | Extracted {} columns.", extractedColumns.size());
        return extractedColumns;
    }

    private void extractPrimaryKeyConstraint(PostgreSQLParser.TableConstraintContext ctx) {
        logger.info("🔍 extractPrimaryKeyConstraint() - START");

        if (ctx.PRIMARY_KEY() != null) {
            logger.debug("🔑 PRIMARY KEY constraint found.");

            if (ctx.columnNameList() != null && !ctx.columnNameList().isEmpty()) {
                String primaryKeyColumns = ctx.columnNameList().get(0).getText(); // Παίρνουμε το πρώτο στοιχείο
                     // Παίρνουμε όλα τα ονόματα ως string
                for (String primaryKeyColumn : primaryKeyColumns.replace("(", "").replace(")", "").split(",")) {
                    primaryKeyColumn = primaryKeyColumn.replace("\"", "").trim();

                    boolean found = false;
                    for (ColumnDefinition column : columnDefinitions) {
                        if (column.getColumnName().equalsIgnoreCase(primaryKeyColumn)) {
                            column.setPrimaryKey(true);
                            column.setNullable(false);
                            found = true;
                            logger.info("✅ PRIMARY KEY applied to column: {}", column.getColumnName());
                            break;
                        }
                    }

                    if (!found) {
                        logger.warn("⚠️ PRIMARY KEY column '{}' not found in columnDefinitions!", primaryKeyColumn);
                    }
                }
            } else {
                logger.warn("⚠️ PRIMARY KEY constraint found but no column names detected!");
            }
        }

        logger.info("⬅️ extractPrimaryKeyConstraint() - END");
    }

    public void extractForeignKeyConstraints(PostgreSQLParser.CreateTableStatementContext ctx) {
        logger.info("➡️ extractForeignKeyConstraints() - START");

        if (ctx.tableConstraint() != null) {
            for (PostgreSQLParser.TableConstraintContext constraintCtx : ctx.tableConstraint()) {

                String constraintText = constraintCtx.getText().toUpperCase();

                if (constraintText.contains("FOREIGN KEY") && constraintText.contains("REFERENCES")) {

                    List<PostgreSQLParser.ColumnNameListContext> columnLists = constraintCtx.columnNameList();
                    if (columnLists.size() != 2) {
                        logger.warn("⚠️ FOREIGN KEY constraint does not contain both FK and referenced column lists.");
                        continue;
                    }

                    List<String> fkColumns = columnLists.get(0).columnName().stream()
                            .map(c -> c.getText().replace("\"", "").trim())
                            .toList();

                    List<String> refColumns = columnLists.get(1).columnName().stream()
                            .map(c -> c.getText().replace("\"", "").trim())
                            .toList();

                    String referencedTable = constraintCtx.tableName().getText().replace("\"", "").trim();

                    if (fkColumns.size() == 1 && refColumns.size() == 1) {
                        String fkColumn = fkColumns.get(0);
                        String refColumn = refColumns.get(0);

                        logger.info("🔗 Found FOREIGN KEY: {} -> {}.{}", fkColumn, referencedTable, refColumn);

                        columnDefinitions.stream()
                                .filter(col -> col.getColumnName().equals(fkColumn))
                                .findFirst()
                                .ifPresentOrElse(col -> {
                                    col.setForeignKey(true);
                                    col.setReferencedTable(referencedTable);
                                    col.setReferencedColumn(refColumn);
                                }, () -> logger.warn("⚠️ FK column '{}' not found in columnDefinitions!", fkColumn));
                    } else {
                        logger.warn("⚠️ Composite FOREIGN KEY not supported yet: {} -> {} ({})",
                                fkColumns, referencedTable, refColumns);
                    }
                }
            }
        }

        logger.info("⬅️ extractForeignKeyConstraints() - END");
    }


    // Σε αναμονή
    public Map<String, Table> parseAllTables(List<PostgreSQLParser.CreateTableStatementContext> createTableStatements) {
        logger.info("➡️ parseAllTables() - START");

        Map<String, Table> tableMap = new HashMap<>();
        for (PostgreSQLParser.CreateTableStatementContext ctx : createTableStatements) {
            Table table = processCreateTable(ctx);
            tableMap.put(table.getName(), table);
            logger.info("✅ Added table '{}' to tableMap", table.getName());
        }

        logger.info("⬅️ parseAllTables() - END | Total tables parsed: {}", tableMap.size());
        return tableMap;
    }

    public Table toTable() {
        logger.info("➡️ toTable() - START | Table Name: {}", this.tableName);

        Table table = new Table();
        table.setName(this.tableName);

        List<Column> columns = this.columnDefinitions.stream()
                .map(ColumnDefinition::toColumn)
                .toList();
        table.setColumns(columns);

        table.addConstraints(this.constraints);
        logger.info("⬅️ toTable() - END | Converted Table: {} with {} columns.",
                table.getName(), columns.size());

        return table;
    }
}
