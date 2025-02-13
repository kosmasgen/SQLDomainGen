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
        logger.info("➡️ processCreateTable() - START | Context: {}", ctx);

        // Extract table name
        this.tableName = extractTableName(ctx);
        logger.info("Extracted table name: {}", this.tableName);

        logger.info("🛠 BEFORE extractColumnDefinitions: columnDefinitions = {}", this.columnDefinitions);
        this.columnDefinitions = extractColumnDefinitions(ctx);
        logger.info("🛠 AFTER extractColumnDefinitions: columnDefinitions = {} | Size: {}", this.columnDefinitions, this.columnDefinitions.size());

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
