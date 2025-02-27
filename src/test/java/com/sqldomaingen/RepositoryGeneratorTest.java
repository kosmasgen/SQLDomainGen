package com.sqldomaingen; // ✅ ΣΩΣΤΟ ΠΑΚΕΤΟ ΓΙΑ ΟΛΑ ΤΑ ΤΕΣΤ

import com.sqldomaingen.generator.RepositoryGenerator;
import com.sqldomaingen.model.Column;
import com.sqldomaingen.model.Table;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.*;
import java.util.List;
import java.util.Map;
import java.util.Collections;



class RepositoryGeneratorTest {

    private RepositoryGenerator generator;
    private Table sampleTable;

    @BeforeEach
    void setUp() {
        generator = new RepositoryGenerator();

        // Δημιουργούμε έναν απλό πίνακα με ένα Primary Key
        Column idColumn = new Column();
        idColumn.setName("id");
        idColumn.setJavaType("Long");
        idColumn.setPrimaryKey(true);

        sampleTable = new Table();
        sampleTable.setName("Product");
        sampleTable.setColumns(List.of(idColumn));
    }

    @Test
    void testDetectPrimaryKeyType() {
        String primaryKeyType = generator.detectPrimaryKeyType(sampleTable);
        assertEquals("Long", primaryKeyType, "Το Primary Key Type πρέπει να είναι Long");
    }

    @Test
    void testGenerateRepositoryBasicStructure() {
        Map<String, Table> tablesMap = Collections.singletonMap("Product", sampleTable);
        String generatedRepo = generator.generateRepositoryForTable(sampleTable, tablesMap);

        assertTrue(generatedRepo.contains("public interface ProductRepository extends JpaRepository<Product, Long>"),
                "Το Repository δεν έχει τη σωστή κληρονομικότητα");
        assertTrue(generatedRepo.contains("package com.sqldomaingen.repository;"), "Το package δεν είναι σωστό");
    }

    @Test
    void testGenerateCustomQueriesForRelationships() {
        // Προσθήκη μιας σχέσης ManyToOne
        Column categoryColumn = new Column();
        categoryColumn.setName("category_id");
        categoryColumn.setJavaType("Long");
        categoryColumn.setForeignKey(true);

        sampleTable.getColumns().add(categoryColumn);

        Table categoryTable = new Table();
        categoryTable.setName("Category");
        categoryTable.setColumns(List.of(categoryColumn));

        Map<String, Table> tablesMap = Map.of(
                "Product", sampleTable,
                "Category", categoryTable
        );

        String generatedRepo = generator.generateCustomQueries(sampleTable, tablesMap);

        assertTrue(generatedRepo.contains("List<Product> findByCategoryId(Long id);"),
                "Το custom query για τη σχέση ManyToOne λείπει ή είναι λάθος");
    }


    @Test
    void testExceptionIfNoPrimaryKey() {
        Table invalidTable = new Table();
        invalidTable.setName("InvalidTable");

        Exception exception = assertThrows(IllegalStateException.class, () -> {
            generator.detectPrimaryKeyType(invalidTable);
        });

        assertTrue(exception.getMessage().contains("No Primary Key found"),
                "Πρέπει να ρίχνει εξαίρεση αν δεν υπάρχει Primary Key");
    }
}
