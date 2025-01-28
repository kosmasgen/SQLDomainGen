package com.sqldomaingen;

import com.sqldomaingen.model.Column;
import com.sqldomaingen.model.Table;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.List;

public class TableTest {

    private static final Logger logger = LoggerFactory.getLogger(TableTest.class);

    @Test
    public void testAddColumn() {
        logger.info("Testing addColumn method...");

        // Δημιουργία αντικειμένου Table
        Table table = new Table();
        table.setName("TestTable");

        // Δημιουργία αντικειμένου Column
        Column column = new Column();
        column.setName("id");
        column.setJavaType("Long");
        column.setPrimaryKey(true);

        // Προσθήκη στήλης
        table.addColumn(column);

        // Logging
        logger.info("Added column: {}", column);
        logger.info("Table state after adding column: {}", table);

        // Επαλήθευση
        Assertions.assertEquals(1, table.getColumns().size());
        Assertions.assertEquals("id", table.getColumns().get(0).getName());
        Assertions.assertEquals("Long", table.getColumns().get(0).getJavaType());
        Assertions.assertTrue(table.getColumns().get(0).isPrimaryKey());
    }

    @Test
    public void testAddConstraints() {
        logger.info("Testing addConstraints method...");

        // Δημιουργία αντικειμένου Table
        Table table = new Table();
        table.setName("ConstraintTable");

        // Προσθήκη constraints
        table.addConstraints(Arrays.asList("PRIMARY KEY (id)", "FOREIGN KEY (dept_id) REFERENCES Department(id)"));

        // Logging
        logger.info("Added constraints: {}", table.getConstraints());

        // Επαλήθευση
        Assertions.assertEquals(2, table.getConstraints().size());
        Assertions.assertTrue(table.getConstraints().contains("PRIMARY KEY (id)"));
        Assertions.assertTrue(table.getConstraints().contains("FOREIGN KEY (dept_id) REFERENCES Department(id)"));
    }

    @Test
    public void testToString() {
        logger.info("Testing toString method...");

        Table table = new Table();
        table.setName("SampleTable");

        // Προσθήκη στηλών
        Column idColumn = new Column();
        idColumn.setName("id");
        idColumn.setSqlType("INT");
        idColumn.setLength(11);
        idColumn.setPrimaryKey(true);
        idColumn.setNullable(false);
        idColumn.setDefaultValue(null);
        idColumn.setUnique(true);

        Column nameColumn = new Column();
        nameColumn.setName("name");
        nameColumn.setSqlType("VARCHAR");
        nameColumn.setLength(255);
        nameColumn.setPrimaryKey(false);
        nameColumn.setNullable(true);
        nameColumn.setDefaultValue("default_name");
        nameColumn.setUnique(false);

        table.addColumn(idColumn);
        table.addColumn(nameColumn);

        // Προσθήκη constraints
        table.addConstraints(List.of("PRIMARY KEY (id)", "FOREIGN KEY (dept_id) REFERENCES Department(id)"));

        String expectedToString = "Table{name='SampleTable', columns=[" +
                "Column{name='id', type='INT', length=11, primaryKey=true, nullable=false, defaultValue='null', unique=true}, " +
                "Column{name='name', type='VARCHAR', length=255, primaryKey=false, nullable=true, defaultValue='default_name', unique=false}], " +
                "constraints=[PRIMARY KEY (id), FOREIGN KEY (dept_id) REFERENCES Department(id)]}";

        logger.info("Table state: {}", table);

        Assertions.assertEquals(expectedToString, table.toString());
    }
}
