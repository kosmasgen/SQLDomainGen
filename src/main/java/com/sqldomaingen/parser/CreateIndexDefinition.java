package com.sqldomaingen.parser;

import com.sqldomaingen.model.IndexDefinition;
import lombok.extern.log4j.Log4j2;

import java.util.ArrayList;
import java.util.List;

@Log4j2
public class CreateIndexDefinition {

    public IndexDefinition processCreateIndex(PostgreSQLParser.CreateIndexStatementContext ctx) {
        if (ctx == null) {
            throw new IllegalArgumentException("CreateIndexStatementContext is null");
        }

        IndexDefinition indexDefinition = new IndexDefinition();

        indexDefinition.setName(ctx.IDENTIFIER(0).getText().replace("\"", "").trim());
        indexDefinition.setTableName(ctx.tableName().getText().replace("\"", "").trim());

        List<String> columnNames = new ArrayList<>();
        for (PostgreSQLParser.IndexElementContext indexElementContext : ctx.indexElement()) {
            String columnName = indexElementContext.getText().replace("\"", "").trim();
            columnNames.add(columnName);
        }

        indexDefinition.getColumns().addAll(columnNames);

        String normalizedIndexText = ctx.getText()
                .replaceAll("\\s+", "")
                .toUpperCase(java.util.Locale.ROOT);

        indexDefinition.setUnique(normalizedIndexText.contains("CREATEUNIQUEINDEX"));

        log.info("Parsed index '{}' on table '{}' with columns {} and unique={}",
                indexDefinition.getName(),
                indexDefinition.getTableName(),
                indexDefinition.getColumns(),
                indexDefinition.isUnique());

        return indexDefinition;
    }
}