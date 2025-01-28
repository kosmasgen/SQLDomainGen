package com.sqldomaingen.parser;

import com.sqldomaingen.model.Column;
import com.sqldomaingen.model.Relationship;
import com.sqldomaingen.model.Table;
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
    private List<Relationship> relationships = new ArrayList<>();

    public Table processCreateTable(PostgreSQLParser.CreateTableStatementContext ctx) {
        logger.info("➡️ processCreateTable() - START | Context: {}", ctx);

        // Extract table name
        this.tableName = extractTableName(ctx);
        logger.info("Extracted table name: {}", this.tableName);

        logger.info("🛠 BEFORE extractColumnDefinitions: columnDefinitions = {}", this.columnDefinitions);
        this.columnDefinitions = extractColumnDefinitions(ctx);
        logger.info("🛠 AFTER extractColumnDefinitions: columnDefinitions = {} | Size: {}", this.columnDefinitions, this.columnDefinitions.size());


        for (ColumnDefinition column : columnDefinitions) {
            if (column.isForeignKey()) {
                logger.info("🔗 Column {} is a Foreign Key, references {}.{}", column.getColumnName(),
                        column.getReferencedTable(), column.getReferencedColumn());
            }
        }


        if (!this.columnDefinitions.isEmpty()) {
            extractRelationships();
        } else {
            logger.warn("⚠️ Skipping extractRelationships() because no columns were extracted.");
        }

        Table table = toTable();
        logger.info("⬅️ processCreateTable() - END | Generated Table: {}", table.getName());

        return table;
    }



    public String extractTableName(PostgreSQLParser.CreateTableStatementContext ctx) {
        if (ctx == null || ctx.tableName() == null || ctx.tableName().isEmpty()) {
            throw new IllegalArgumentException("Table name not found in CREATE TABLE statement.");
        }

        // Παίρνουμε το πρώτο tableName από τη λίστα
        this.tableName = ctx.tableName().get(0).getText();

        // Logging για debugging
        logger.info("Extracted raw table name: {}", tableName);

        return NamingConverter.toPascalCase(tableName);
    }

    public List<ColumnDefinition> extractColumnDefinitions(PostgreSQLParser.CreateTableStatementContext ctx) {
        logger.info("➡️ extractColumnDefinitions() - START | Context: {}", ctx);

        List<ColumnDefinition> extractedColumns = new ArrayList<>();

        if (ctx.columnDef() != null) {
            for (PostgreSQLParser.ColumnDefContext columnCtx : ctx.columnDef()) {
                try {
                    // Χρησιμοποιούμε το fromContext() για σωστή ανάλυση της στήλης
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



     /**
     * 🔹 Εξαγωγή των relationships από τα foreign keys των `ColumnDefinition`
     */
    public void extractRelationships() {
        logger.info("➡️ extractRelationships() - START | Processing {} columns.", columnDefinitions.size());


        for (ColumnDefinition columnDef : columnDefinitions) {
            if (columnDef.isForeignKey()) {
                Relationship relationship = Relationship.builder()
                        .sourceTable(this.tableName)
                        .sourceColumn(columnDef.getColumnName())
                        .targetTable(columnDef.getReferencedTable())
                        .targetColumn(columnDef.getReferencedColumn())
                        .relationshipType(Relationship.RelationshipType.MANYTOONE) // Ανάλογα με το context, προσαρμόζεται
                        .onUpdate(columnDef.getOnUpdate())
                        .onDelete(columnDef.getOnDelete())
                        .build();

                relationships.add(relationship);

                logger.info("✅ Extracted Foreign Key Relationship: {} -> {}.{}",
                        columnDef.getColumnName(), columnDef.getReferencedTable(), columnDef.getReferencedColumn());
            }
        }

        if (relationships.isEmpty()) {
            logger.warn("⚠️ No relationships extracted, even though columns were present.");
        }

        logger.info("⬅️ extractRelationships() - END | Extracted {} relationships.", relationships.size());
    }




    public Table toTable() {
        logger.info("➡️ toTable() - START | Table Name: {}", this.tableName);

        Table table = new Table();
        table.setName(this.tableName);

        List<Column> columns = this.columnDefinitions.stream()
                .map(ColumnDefinition::toColumn)
                .toList();
        table.setColumns(columns);

        // ✅ Προσθήκη των relationships στη Table
        table.setRelationships(this.relationships);

        table.addConstraints(this.constraints);
        logger.info("⬅️ toTable() - END | Converted Table: {} with {} columns and {} relationships.",
                table.getName(), columns.size(), relationships.size());

        return table;
    }
}
