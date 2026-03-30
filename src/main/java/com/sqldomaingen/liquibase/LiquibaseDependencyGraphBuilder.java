package com.sqldomaingen.liquibase;

import com.sqldomaingen.model.Column;
import com.sqldomaingen.model.Table;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;

/**
 * Builds a deterministic table dependency graph for Liquibase changeSet planning.
 *
 * <p>Output format:
 * <ul>
 *     <li>key = table name</li>
 *     <li>value = parent tables that must be created before the key table</li>
 * </ul>
 *
 * <p>Rules:
 * <ul>
 *     <li>Includes all known tables as graph nodes, even if they have no dependencies</li>
 *     <li>Uses FOREIGN KEY columns to infer dependencies</li>
 *     <li>Ignores null or blank names</li>
 *     <li>Ignores self-dependencies</li>
 *     <li>Ignores unknown external referenced tables</li>
 *     <li>Produces deterministic output</li>
 * </ul>
 */
public class LiquibaseDependencyGraphBuilder {

    /**
     * Builds a deterministic dependency graph from the provided tables.
     *
     * @param tables parsed tables
     * @return graph where key is the table and value is the parent tables
     *         that must be created first
     */
    public Map<String, Set<String>> buildDependencyGraph(List<Table> tables) {
        if (tables == null || tables.isEmpty()) {
            return Collections.emptyMap();
        }

        List<Table> normalizedTables = normalizeInputTables(tables);
        Set<String> knownTableNames = collectKnownTableNames(normalizedTables);
        Map<String, Set<String>> graph = initializeGraphNodes(knownTableNames);

        populateDependencies(graph, normalizedTables, knownTableNames);
        return graph;
    }

    /**
     * Builds a deterministic dependency graph directly from a table map.
     *
     * @param tableMap parsed table map
     * @return graph where key is the table and value is the parent tables
     *         that must be created first
     */
    public Map<String, Set<String>> buildDependencyGraph(Map<String, Table> tableMap) {
        if (tableMap == null || tableMap.isEmpty()) {
            return Collections.emptyMap();
        }

        return buildDependencyGraph(new ArrayList<>(tableMap.values()));
    }

    /**
     * Normalizes input tables by removing null entries and tables with blank names.
     *
     * @param tables raw tables
     * @return cleaned table list
     */
    private List<Table> normalizeInputTables(Collection<Table> tables) {
        List<Table> normalizedTables = new ArrayList<>();

        for (Table table : tables) {
            if (table == null) {
                continue;
            }

            String tableName = safeTrim(table.getName());
            if (tableName == null) {
                continue;
            }

            normalizedTables.add(table);
        }

        return normalizedTables;
    }

    /**
     * Collects all known table names in deterministic order.
     *
     * @param tables cleaned table list
     * @return sorted known table names
     */
    private Set<String> collectKnownTableNames(List<Table> tables) {
        Set<String> knownTableNames = new TreeSet<>();

        for (Table table : tables) {
            String tableName = safeTrim(table.getName());
            if (tableName != null) {
                knownTableNames.add(tableName);
            }
        }

        return knownTableNames;
    }

    /**
     * Initializes graph nodes for all known tables with empty dependency sets.
     *
     * @param knownTableNames sorted known table names
     * @return initialized graph
     */
    private Map<String, Set<String>> initializeGraphNodes(Set<String> knownTableNames) {
        Map<String, Set<String>> graph = new LinkedHashMap<>();

        for (String tableName : knownTableNames) {
            graph.put(tableName, new TreeSet<>());
        }

        return graph;
    }

    /**
     * Populates dependency edges using FOREIGN KEY columns only.
     *
     * @param graph initialized graph
     * @param tables cleaned table list
     * @param knownTableNames known table names
     */
    private void populateDependencies(
            Map<String, Set<String>> graph,
            List<Table> tables,
            Set<String> knownTableNames
    ) {
        List<Table> sortedTables = new ArrayList<>(tables);
        sortedTables.sort(Comparator.comparing(
                table -> safeTrim(table.getName()),
                Comparator.nullsLast(String::compareTo)
        ));

        for (Table table : sortedTables) {
            String currentTableName = safeTrim(table.getName());
            if (currentTableName == null) {
                continue;
            }

            Set<String> dependencies = graph.get(currentTableName);
            if (dependencies == null) {
                continue;
            }

            addDependenciesFromForeignKeyColumns(
                    dependencies,
                    currentTableName,
                    table.getColumns(),
                    knownTableNames
            );
        }
    }

    /**
     * Adds dependencies inferred from FOREIGN KEY columns.
     *
     * <p>Main rule:
     * current table depends on referenced table.
     *
     * @param dependencies target dependency set for the current table
     * @param currentTableName current table name
     * @param columns table columns
     * @param knownTableNames all known table names
     */
    private void addDependenciesFromForeignKeyColumns(
            Set<String> dependencies,
            String currentTableName,
            Collection<Column> columns,
            Set<String> knownTableNames
    ) {
        if (columns == null || columns.isEmpty()) {
            return;
        }

        for (Column column : columns) {
            if (column == null || !column.isForeignKey()) {
                continue;
            }

            String referencedTableName = resolveKnownReferencedTable(
                    safeTrim(column.getReferencedTable()),
                    knownTableNames
            );

            addValidDependency(
                    dependencies,
                    currentTableName,
                    referencedTableName,
                    knownTableNames
            );
        }
    }

    /**
     * Resolves a referenced table name against the known table set.
     *
     * <p>Resolution strategy:
     * <ul>
     *     <li>exact physical name match</li>
     *     <li>schema-free unique match</li>
     * </ul>
     *
     * @param referencedTable raw referenced table name
     * @param knownTableNames known table names
     * @return resolved known table name or null
     */
    private String resolveKnownReferencedTable(String referencedTable, Set<String> knownTableNames) {
        if (referencedTable == null) {
            return null;
        }

        if (knownTableNames.contains(referencedTable)) {
            return referencedTable;
        }

        String referencedTableWithoutSchema = stripSchema(referencedTable);
        String matchedTableName = null;

        for (String knownTableName : knownTableNames) {
            if (Objects.equals(stripSchema(knownTableName), referencedTableWithoutSchema)) {
                if (matchedTableName != null) {
                    return null;
                }
                matchedTableName = knownTableName;
            }
        }

        return matchedTableName;
    }

    /**
     * Adds a dependency if it is valid.
     *
     * @param dependencies target dependency set
     * @param currentTableName current table name
     * @param candidateParent candidate parent table
     * @param knownTableNames all known table names
     */
    private void addValidDependency(
            Set<String> dependencies,
            String currentTableName,
            String candidateParent,
            Set<String> knownTableNames
    ) {
        if (candidateParent == null) {
            return;
        }

        if (Objects.equals(currentTableName, candidateParent)) {
            return;
        }

        if (!knownTableNames.contains(candidateParent)) {
            return;
        }

        dependencies.add(candidateParent);
    }

    /**
     * Removes schema prefix from a table name when present.
     *
     * @param tableName raw table name
     * @return schema-free table name
     */
    private String stripSchema(String tableName) {
        if (tableName == null) {
            return null;
        }

        int dotIndex = tableName.indexOf('.');
        return dotIndex >= 0 ? tableName.substring(dotIndex + 1) : tableName;
    }

    /**
     * Safely trims a string and returns null for blank values.
     *
     * @param value source value
     * @return trimmed value or null
     */
    private String safeTrim(String value) {
        if (value == null) {
            return null;
        }

        String trimmedValue = value.trim();
        return trimmedValue.isEmpty() ? null : trimmedValue;
    }
}