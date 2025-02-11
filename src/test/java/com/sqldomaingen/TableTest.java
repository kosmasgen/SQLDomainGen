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
    void testToString() {
        logger.info("🔵 Testing toString method...");

        // Δημιουργία δείγματος πίνακα
        Table table = new Table();
        table.setName("SampleTable");

        Column idColumn = new Column();
        idColumn.setName("id");
        idColumn.setSqlType("INTEGER");
        idColumn.setLength(255);
        idColumn.setPrimaryKey(true);
        idColumn.setUnique(true);
        idColumn.setNullable(false);

        Column nameColumn = new Column();
        nameColumn.setName("name");
        nameColumn.setSqlType("String");
        nameColumn.setLength(255);
        nameColumn.setDefaultValue("default_name");
        nameColumn.setNullable(true);

        table.addColumn(idColumn);
        table.addColumn(nameColumn);

        // Αντί για addConstraint, χρησιμοποιούμε το setter
        List<String> constraints = Arrays.asList(
                "PRIMARY KEY (id)",
                "FOREIGN KEY (dept_id) REFERENCES Department(id)"
        );
        table.setConstraints(constraints); // Χρήση του setter


        // Αναμενόμενο αποτέλεσμα
        String expected = "Table{name='SampleTable', columns=[Column(name=id, sqlType=INTEGER, length=255, isIdentity=false, identityGeneration=null, sequenceName=null, constraints=[], defaultValue=null, primaryKey=true, unique=true, nullable=false, isDefaultExpression=null, defaultExpression=null, checkConstraint=null, targetTable=null, joinTableName=null, inverseJoinColumn=null, isRelationship=false, onUpdate=null, onDelete=null, generatedAs=null, javaType=null, foreignKey=false, formattedName=null, referencedTable=null, referencedColumn=null, precision=0, scale=0), " +
                "Column(name=name, sqlType=String, length=255, isIdentity=false, identityGeneration=null, sequenceName=null, constraints=[], defaultValue=default_name, primaryKey=false, unique=false, nullable=true, isDefaultExpression=null, defaultExpression=null, checkConstraint=null, targetTable=null, joinTableName=null, inverseJoinColumn=null, isRelationship=false, onUpdate=null, onDelete=null, generatedAs=null, javaType=null, foreignKey=false, formattedName=null, referencedTable=null, referencedColumn=null, precision=0, scale=0)], " +
                "constraints=[PRIMARY KEY (id), FOREIGN KEY (dept_id) REFERENCES Department(id)], relationships=[]}";

        logger.info("✅ Table state: {}", table);
        Assertions.assertEquals(expected, table.toString(), "Table toString should match the expected format.");
    }



}
