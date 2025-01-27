package com.sqldomaingen.parser;

import com.sqldomaingen.model.Table;
import com.sqldomaingen.model.Column;
import com.sqldomaingen.util.NamingConverter;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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

    public void processCreateTable(PostgreSQLParser.CreateTableStatementContext ctx) {
        logger.info("Processing CREATE TABLE definition...");

        // Extract table name
        this.tableName = extractTableName(ctx);
        logger.info("Extracted table name: {}", this.tableName);

        // Extract column definitions
        this.columnDefinitions = extractColumnDefinitions(ctx);
        logger.info("Extracted {} columns.", this.columnDefinitions.size());

    }

    public String extractTableName(PostgreSQLParser.CreateTableStatementContext ctx) {
        if (ctx.tableName() != null && !ctx.tableName().isEmpty()) {
            String rawTableName = ctx.tableName(0).getText();
            return NamingConverter.toPascalCase(rawTableName);
        } else {
            throw new IllegalArgumentException("Table name not found in CREATE TABLE statement.");
        }
    }

    public List<ColumnDefinition> extractColumnDefinitions(PostgreSQLParser.CreateTableStatementContext ctx) {
        List<ColumnDefinition> extractedColumns = new ArrayList<>();

        // Extract columns from column_def rule
        if (ctx.columnDef() != null) {
            for (PostgreSQLParser.ColumnDefContext columnCtx : ctx.columnDef()) {
                ColumnDefinition column = new ColumnDefinition();

                // Extract column name
                String columnName = columnCtx.columnName().getText();
                column.setColumnName(columnName);

                // Extract data type
                String dataType = columnCtx.dataType().getText();
                column.setSqlType(dataType);

                extractedColumns.add(column);
                logger.info("Extracted column: {} with type {}", columnName, dataType);
            }
        } else {
            logger.warn("No columns found in the CREATE TABLE statement.");
        }

        return extractedColumns;
    }


    public Table toTable() {
        Table table = new Table();
        table.setName(this.tableName);

        List<Column> columns = this.columnDefinitions.stream()
                .map(ColumnDefinition::toColumn)
                .toList();
        table.setColumns(columns);

        table.addConstraints(this.constraints);
        logger.info("Converted to Table object: {} with {} columns.", table.getName(), columns.size());
        return table;
    }
}
