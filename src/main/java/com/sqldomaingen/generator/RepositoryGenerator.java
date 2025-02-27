package com.sqldomaingen.generator;

import com.sqldomaingen.model.Column;
import com.sqldomaingen.model.Table;
import com.sqldomaingen.util.NamingConverter;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class RepositoryGenerator {

    private static final String OUTPUT_PATH = "output/repositories";

    public void generateRepositories(List<Table> tables) {
        ensureOutputDirectory();

        // ✅ Δημιουργία `tablesMap`
        Map<String, Table> tablesMap = tables.stream()
                .collect(Collectors.toMap(Table::getName, t -> t));

        for (Table table : tables) {
            String repositoryCode = generateRepositoryForTable(table, tablesMap);
            writeRepositoryToFile(repositoryCode, table.getName());
        }
    }

    public String generateRepositoryForTable(Table table, Map<String, Table> tablesMap) {
        String tableName = NamingConverter.toPascalCase(table.getName());
        String primaryKeyType = detectPrimaryKeyType(table);

        StringBuilder sb = new StringBuilder();
        sb.append("package com.sqldomaingen.repository;\n\n");
        sb.append("import com.sqldomaingen.model.").append(tableName).append(";\n");
        sb.append("import org.springframework.data.jpa.repository.JpaRepository;\n");
        sb.append("import org.springframework.stereotype.Repository;\n\n");
        sb.append("@Repository\n");
        sb.append("public interface ").append(tableName).append("Repository extends JpaRepository<")
                .append(tableName).append(", ").append(primaryKeyType).append("> {\n\n");

        sb.append(generateCustomQueries(table, tablesMap));
        sb.append("}\n");

        return sb.toString();
    }

    public String detectPrimaryKeyType(Table table) {
        return table.getColumns().stream()
                .filter(Column::isPrimaryKey)
                .findFirst()
                .map(Column::getJavaType)
                .orElseThrow(() -> new IllegalStateException("❌ No Primary Key found for table: " + table.getName()));
    }

    public boolean hasRelationships(Table table) {
        return table.getRelationships() != null && !table.getRelationships().isEmpty();
    }

    public String generateCustomQueries(Table table, Map<String, Table> tablesMap) {
        StringBuilder sb = new StringBuilder();

        if (!hasRelationships(table)) {
            return "";
        }

        table.getRelationships().forEach(relationship -> {
            String relatedEntity = NamingConverter.toPascalCase(relationship.getTargetTable());

            Table relatedTable = tablesMap.get(relationship.getTargetTable()); // ✅ Διόρθωση!
            if (relatedTable == null) {
                throw new IllegalStateException("❌ Related table not found: " + relationship.getTargetTable());
            }

            String relatedPrimaryKeyType = relatedTable.getColumns().stream()
                    .filter(Column::isPrimaryKey)
                    .findFirst()
                    .map(Column::getJavaType)
                    .orElseThrow(() -> new IllegalStateException(
                            "❌ No Primary Key found for related table: " + relatedEntity));

            sb.append("    List<").append(NamingConverter.toPascalCase(table.getName())).append("> findBy")

                    .append(relatedEntity).append("Id(")
                    .append(relatedPrimaryKeyType).append(" id);\n");
        });

        return sb.toString();
    }

    private void writeRepositoryToFile(String repositoryCode, String entityName) {
        Path filePath = Paths.get(OUTPUT_PATH, entityName + "Repository.java");
        try {
            Files.write(filePath, repositoryCode.getBytes());
            System.out.println("✅ Repository generated: " + filePath);
        } catch (IOException e) {
            System.err.println("❌ Failed to write repository file: " + filePath);
        }
    }

    private void ensureOutputDirectory() {
        Path path = Paths.get(OUTPUT_PATH);
        if (!Files.exists(path)) {
            try {
                Files.createDirectories(path);
            } catch (IOException e) {
                System.err.println("❌ Failed to create output directory: " + OUTPUT_PATH);
            }
        }
    }
}
