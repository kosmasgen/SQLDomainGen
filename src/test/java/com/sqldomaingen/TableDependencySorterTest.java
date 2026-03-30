package com.sqldomaingen;

import com.sqldomaingen.liquibase.TableDependencySorter;
import org.junit.jupiter.api.Test;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TableDependencySorterTest {

    private final TableDependencySorter sorter = new TableDependencySorter();

    @Test
    void shouldReturnSameOrderRegardlessOfGraphInsertionOrder() {
        Map<String, Set<String>> graphA = new LinkedHashMap<>();
        graphA.put("pep_schema.project_assignment", Set.of("pep_schema.project", "pep_schema.employee"));
        graphA.put("pep_schema.employee_i18n", Set.of("pep_schema.employee", "pep_schema.languages"));
        graphA.put("pep_schema.employee", Set.of("pep_schema.company", "pep_schema.department", "pep_schema.company_status"));
        graphA.put("pep_schema.department", Set.of("pep_schema.company"));
        graphA.put("pep_schema.company_profile_i18n", Set.of("pep_schema.company_profile", "pep_schema.languages"));
        graphA.put("pep_schema.company_profile", Set.of("pep_schema.company"));
        graphA.put("pep_schema.project", Set.of("pep_schema.company"));
        graphA.put("pep_schema.audit_trail", Set.of("pep_schema.employee"));
        graphA.put("pep_schema.company", Set.of());
        graphA.put("pep_schema.company_status", Set.of());
        graphA.put("pep_schema.languages", Set.of());

        Map<String, Set<String>> graphB = new LinkedHashMap<>();
        graphB.put("pep_schema.languages", Set.of());
        graphB.put("pep_schema.company_status", Set.of());
        graphB.put("pep_schema.company", Set.of());
        graphB.put("pep_schema.audit_trail", Set.of("pep_schema.employee"));
        graphB.put("pep_schema.project", Set.of("pep_schema.company"));
        graphB.put("pep_schema.company_profile", Set.of("pep_schema.company"));
        graphB.put("pep_schema.company_profile_i18n", Set.of("pep_schema.company_profile", "pep_schema.languages"));
        graphB.put("pep_schema.department", Set.of("pep_schema.company"));
        graphB.put("pep_schema.employee", Set.of("pep_schema.company", "pep_schema.department", "pep_schema.company_status"));
        graphB.put("pep_schema.employee_i18n", Set.of("pep_schema.employee", "pep_schema.languages"));
        graphB.put("pep_schema.project_assignment", Set.of("pep_schema.project", "pep_schema.employee"));

        List<String> orderedA = sorter.sortTables(graphA);
        List<String> orderedB = sorter.sortTables(graphB);

        assertEquals(orderedA, orderedB);
    }

    @Test
    void shouldRespectAllDependenciesInWideAndDeepGraph() {
        Map<String, Set<String>> graph = new LinkedHashMap<>();
        graph.put("pep_schema.languages", Set.of());
        graph.put("pep_schema.company_status", Set.of());
        graph.put("pep_schema.company", Set.of());
        graph.put("pep_schema.department", Set.of("pep_schema.company"));
        graph.put("pep_schema.company_profile", Set.of("pep_schema.company"));
        graph.put("pep_schema.company_profile_i18n", Set.of("pep_schema.company_profile", "pep_schema.languages"));
        graph.put("pep_schema.employee", Set.of("pep_schema.company", "pep_schema.department", "pep_schema.company_status"));
        graph.put("pep_schema.employee_i18n", Set.of("pep_schema.employee", "pep_schema.languages"));
        graph.put("pep_schema.project", Set.of("pep_schema.company"));
        graph.put("pep_schema.project_assignment", Set.of("pep_schema.project", "pep_schema.employee"));
        graph.put("pep_schema.audit_trail", Set.of("pep_schema.employee"));

        List<String> ordered = sorter.sortTables(graph);

        assertEquals(11, ordered.size());
        assertBefore(ordered, "pep_schema.company", "pep_schema.department");
        assertBefore(ordered, "pep_schema.company", "pep_schema.company_profile");
        assertBefore(ordered, "pep_schema.company_profile", "pep_schema.company_profile_i18n");
        assertBefore(ordered, "pep_schema.languages", "pep_schema.company_profile_i18n");
        assertBefore(ordered, "pep_schema.company", "pep_schema.employee");
        assertBefore(ordered, "pep_schema.department", "pep_schema.employee");
        assertBefore(ordered, "pep_schema.company_status", "pep_schema.employee");
        assertBefore(ordered, "pep_schema.employee", "pep_schema.employee_i18n");
        assertBefore(ordered, "pep_schema.languages", "pep_schema.employee_i18n");
        assertBefore(ordered, "pep_schema.company", "pep_schema.project");
        assertBefore(ordered, "pep_schema.project", "pep_schema.project_assignment");
        assertBefore(ordered, "pep_schema.employee", "pep_schema.project_assignment");
        assertBefore(ordered, "pep_schema.employee", "pep_schema.audit_trail");
    }

    @Test
    void shouldThrowOnCycleHiddenInsideLargerGraph() {
        Map<String, Set<String>> graph = new LinkedHashMap<>();
        graph.put("pep_schema.languages", Set.of());
        graph.put("pep_schema.company", Set.of());
        graph.put("pep_schema.department", Set.of("pep_schema.company", "pep_schema.employee"));
        graph.put("pep_schema.employee", Set.of("pep_schema.department"));
        graph.put("pep_schema.audit_trail", Set.of("pep_schema.employee"));

        IllegalStateException exception = assertThrows(
                IllegalStateException.class,
                () -> sorter.sortTables(graph)
        );

        assertTrue(exception.getMessage().toLowerCase().contains("cyclic"));
    }

    private void assertBefore(List<String> ordered, String first, String second) {
        int firstIndex = ordered.indexOf(first);
        int secondIndex = ordered.indexOf(second);

        assertTrue(firstIndex >= 0, "Missing node: " + first);
        assertTrue(secondIndex >= 0, "Missing node: " + second);
        assertTrue(
                firstIndex < secondIndex,
                "Expected '" + first + "' before '" + second + "' but got: " + ordered
        );
    }
}