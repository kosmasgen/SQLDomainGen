package com.sqldomaingen;

import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class TopologigalTest {

    @Test
    void shouldOrderParentsBeforeChildren_ForSchoolSchemaExample() {
        // Dependencies mean: KEY depends on VALUES (so VALUES must come first)
        Map<String, Set<String>> deps = new LinkedHashMap<>();
        deps.put("school", Set.of());
        deps.put("teacher", Set.of("school"));
        deps.put("student", Set.of("school", "teacher"));
        deps.put("course", Set.of("school", "teacher"));
        deps.put("course_student", Set.of("course", "student"));

        List<String> ordered = topologicalSort(deps);

        assertBefore(ordered, "school", "teacher");
        assertBefore(ordered, "school", "student");
        assertBefore(ordered, "school", "course");

        assertBefore(ordered, "teacher", "student");
        assertBefore(ordered, "teacher", "course");

        assertBefore(ordered, "course", "course_student");
        assertBefore(ordered, "student", "course_student");

        assertEquals(deps.keySet().size(), ordered.size(), "All tables must be present in the result");
        assertEquals(new HashSet<>(deps.keySet()), new HashSet<>(ordered), "Result must contain exactly the input tables");
    }

    @Test
    void shouldNotFailOnSelfReference_DepartmentParentId() {
        Map<String, Set<String>> deps = new LinkedHashMap<>();
        deps.put("department", Set.of("department")); // self-reference should not block ordering
        deps.put("employee", Set.of("department"));

        List<String> ordered = topologicalSort(deps);

        assertBefore(ordered, "department", "employee");
        assertEquals(2, ordered.size());
    }

    @Test
    void shouldThrowOnCycle() {
        Map<String, Set<String>> deps = new LinkedHashMap<>();
        deps.put("a", Set.of("b"));
        deps.put("b", Set.of("a"));

        IllegalStateException ex = assertThrows(IllegalStateException.class, () -> topologicalSort(deps));
        assertTrue(ex.getMessage().toLowerCase().contains("cycle"), "Exception message should mention cycle");
    }

    @Test
    void shouldSortCorrectly_EvenWhenInputOrderIsWrong() {
        // Intentionally WRONG insertion order:
        // join table first, then children, then parents.
        Map<String, Set<String>> deps = new LinkedHashMap<>();
        deps.put("course_student", Set.of("course", "student"));
        deps.put("student", Set.of("school", "teacher"));
        deps.put("course", Set.of("school", "teacher"));
        deps.put("teacher", Set.of("school"));
        deps.put("school", Set.of());

        List<String> ordered = topologicalSort(deps);

        // Parent tables must come before children no matter the input order.
        assertBefore(ordered, "school", "teacher");
        assertBefore(ordered, "school", "student");
        assertBefore(ordered, "school", "course");

        assertBefore(ordered, "teacher", "student");
        assertBefore(ordered, "teacher", "course");

        assertBefore(ordered, "course", "course_student");
        assertBefore(ordered, "student", "course_student");

        // Must contain exactly the same tables.
        assertEquals(deps.keySet().size(), ordered.size());
        assertEquals(new HashSet<>(deps.keySet()), new HashSet<>(ordered));
    }

    @Test
    void shouldProduceExactDeterministicOrder_ForSchoolSchema() {
        Map<String, Set<String>> deps = new LinkedHashMap<>();
        deps.put("course_student", Set.of("course", "student"));
        deps.put("student", Set.of("school", "teacher"));
        deps.put("course", Set.of("school", "teacher"));
        deps.put("teacher", Set.of("school"));
        deps.put("school", Set.of());

        List<String> ordered = topologicalSort(deps);

        // With TreeSet picking smallest "ready" node, this order is deterministic.
        assertEquals(List.of("school", "teacher", "course", "student", "course_student"), ordered);
    }

    @Test
    void shouldRespectAllDependencies_ForAnyGraph() {
        Map<String, Set<String>> deps = new LinkedHashMap<>();
        deps.put("course_student", Set.of("course", "student"));
        deps.put("student", Set.of("school", "teacher"));
        deps.put("course", Set.of("school", "teacher"));
        deps.put("teacher", Set.of("school"));
        deps.put("school", Set.of());

        List<String> ordered = topologicalSort(deps);

        Map<String, Integer> index = new HashMap<>();
        for (int i = 0; i < ordered.size(); i++) {
            index.put(ordered.get(i), i);
        }

        for (Map.Entry<String, Set<String>> e : deps.entrySet()) {
            String table = e.getKey();
            for (String parent : e.getValue()) {
                assertTrue(index.get(parent) < index.get(table),
                        "Expected '" + parent + "' before '" + table + "' but got: " + ordered);
            }
        }
    }



    private static void assertBefore(List<String> ordered, String first, String second) {
        int i = ordered.indexOf(first);
        int j = ordered.indexOf(second);
        assertTrue(i >= 0, "Missing node: " + first);
        assertTrue(j >= 0, "Missing node: " + second);
        assertTrue(i < j, "Expected '" + first + "' before '" + second + "' but got: " + ordered);
    }

    /**
     * Deterministic topological sort.
     * Rule: If deps.get(X) contains Y, then Y must appear before X.
     * Self-dependencies are ignored.
     * Unknown dependencies (not present as keys) are ignored.
     */
    private static List<String> topologicalSort(Map<String, Set<String>> deps) {
        Map<String, Set<String>> normalizedDeps = new HashMap<>();
        for (Map.Entry<String, Set<String>> e : deps.entrySet()) {
            String node = e.getKey();
            Set<String> d = new HashSet<>();
            for (String dep : e.getValue()) {
                if (dep == null) continue;
                if (dep.equals(node)) continue;          // ignore self-reference
                if (!deps.containsKey(dep)) continue;    // ignore external tables
                d.add(dep);
            }
            normalizedDeps.put(node, d);
        }

        Map<String, Integer> inDegree = new HashMap<>();
        Map<String, Set<String>> reverse = new HashMap<>();

        for (String node : normalizedDeps.keySet()) {
            inDegree.put(node, 0);
            reverse.put(node, new HashSet<>());
        }

        for (Map.Entry<String, Set<String>> e : normalizedDeps.entrySet()) {
            String node = e.getKey();
            for (String dep : e.getValue()) {
                inDegree.put(node, inDegree.get(node) + 1);
                reverse.get(dep).add(node);
            }
        }

        // Use lexicographic order for deterministic results
        NavigableSet<String> ready = new TreeSet<>();
        for (Map.Entry<String, Integer> e : inDegree.entrySet()) {
            if (e.getValue() == 0) {
                ready.add(e.getKey());
            }
        }

        List<String> result = new ArrayList<>(normalizedDeps.size());

        while (!ready.isEmpty()) {
            String current = ready.pollFirst();
            result.add(current);

            for (String child : reverse.getOrDefault(current, Set.of())) {
                int deg = inDegree.get(child) - 1;
                inDegree.put(child, deg);
                if (deg == 0) {
                    ready.add(child);
                }
            }
        }

        if (result.size() != normalizedDeps.size()) {
            Set<String> remaining = new TreeSet<>();
            for (Map.Entry<String, Integer> e : inDegree.entrySet()) {
                if (e.getValue() > 0) remaining.add(e.getKey());
            }
            throw new IllegalStateException("Cycle detected in table dependencies: " + remaining);
        }

        return result;
    }


}
