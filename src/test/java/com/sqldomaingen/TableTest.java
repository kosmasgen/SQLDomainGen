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
    public void testAddDecimalColumnToEventAssignmentTable() {
        logger.info("🔵 Testing addColumn with DECIMAL column in 'event_assignment' table...");

        // Δημιουργία πίνακα
        Table table = new Table();
        table.setName("event_assignment");

        // Δημιουργία και προσθήκη στηλών
        Column id = new Column();
        id.setName("assignment_id");
        id.setSqlType("SERIAL");
        id.setPrimaryKey(true);
        id.setNullable(false);

        Column eventId = new Column();
        eventId.setName("event_id");
        eventId.setSqlType("INT");
        eventId.setNullable(false);

        Column userId = new Column();
        userId.setName("user_id");
        userId.setSqlType("INT");
        userId.setNullable(false);

        Column workload = new Column();
        workload.setName("workload_percentage");
        workload.setSqlType("DECIMAL");
        workload.setPrecision(5);
        workload.setScale(2);
        workload.setNullable(false);

        Column note = new Column();
        note.setName("note");
        note.setSqlType("TEXT");
        note.setNullable(true);

        // Προσθήκη όλων των στηλών
        table.addColumn(id);
        table.addColumn(eventId);
        table.addColumn(userId);
        table.addColumn(workload);
        table.addColumn(note);

        // Logging
        logger.info("📋 Final table: {}", table);

        // Assertions
        Assertions.assertEquals(5, table.getColumns().size());

        Column decimalCol = table.getColumns().stream()
                .filter(c -> "workload_percentage".equals(c.getName()))
                .findFirst()
                .orElseThrow(() -> new AssertionError("DECIMAL column not found"));

        Assertions.assertEquals("DECIMAL", decimalCol.getSqlType());
        Assertions.assertEquals(5, decimalCol.getPrecision());
        Assertions.assertEquals(2, decimalCol.getScale());
        Assertions.assertFalse(decimalCol.isNullable());
    }


    @Test
    public void testAddConstraints() {
        logger.info("✅ Testing addConstraints with current schema...");

        // Δημιουργία πίνακα
        Table table = new Table();
        table.setName("user");

        // Στήλη με in-line PRIMARY KEY
        Column userId = new Column();
        userId.setName("user_id");
        userId.setSqlType("SERIAL");
        userId.setPrimaryKey(true);

        // Κανονική στήλη
        Column departmentId = new Column();
        departmentId.setName("department_id");
        departmentId.setSqlType("INT");

        // Προσθήκη στηλών
        table.addColumn(userId);
        table.addColumn(departmentId);

        // Προσθήκη table-level constraint (FOREIGN KEY)
        table.addConstraints(List.of("FOREIGN KEY (department_id) REFERENCES department(department_id)"));

        // Logging
        logger.info("🧱 Columns: {}", table.getColumns());
        logger.info("🔗 Constraints: {}", table.getConstraints());

        // Assertions
        Assertions.assertEquals(2, table.getColumns().size());
        Assertions.assertEquals("user_id", table.getColumns().get(0).getName());
        Assertions.assertTrue(table.getColumns().get(0).isPrimaryKey());

        Assertions.assertEquals(1, table.getConstraints().size());
        Assertions.assertTrue(table.getConstraints().get(0).contains("FOREIGN KEY"));
    }


    @Test
    void testParseCreateDepartmentTable_ToStringValidation() {
        logger.info("🔵 Testing Table.toString with parsed 'department' table structure...");

        Table table = new Table();
        table.setName("department");

        Column idColumn = new Column();
        idColumn.setName("department_id");
        idColumn.setSqlType("SERIAL");
        idColumn.setPrimaryKey(true);
        idColumn.setNullable(false);

        Column nameColumn = new Column();
        nameColumn.setName("name");
        nameColumn.setSqlType("VARCHAR");
        nameColumn.setLength(100);
        nameColumn.setNullable(false);

        Column descriptionColumn = new Column();
        descriptionColumn.setName("description");
        descriptionColumn.setSqlType("TEXT");
        descriptionColumn.setNullable(true);

        Column parentDeptIdColumn = new Column();
        parentDeptIdColumn.setName("parent_dept_id");
        parentDeptIdColumn.setSqlType("INT");
        parentDeptIdColumn.setNullable(true);

        Column createdAtColumn = new Column();
        createdAtColumn.setName("created_at");
        createdAtColumn.setSqlType("TIMESTAMP");
        createdAtColumn.setDefaultValue("CURRENT_TIMESTAMP");
        createdAtColumn.setNullable(true);

        Column updatedAtColumn = new Column();
        updatedAtColumn.setName("updated_at");
        updatedAtColumn.setSqlType("TIMESTAMP");
        updatedAtColumn.setDefaultValue("CURRENT_TIMESTAMP");
        updatedAtColumn.setNullable(true);

        table.addColumn(idColumn);
        table.addColumn(nameColumn);
        table.addColumn(descriptionColumn);
        table.addColumn(parentDeptIdColumn);
        table.addColumn(createdAtColumn);
        table.addColumn(updatedAtColumn);

        table.setConstraints(Arrays.asList(
                "PRIMARY KEY (department_id)",
                "FOREIGN KEY (parent_dept_id) REFERENCES department(department_id)"
        ));

        String expected = "Table{name='department', columns=[Column(name=department_id, sqlType=SERIAL, length=0, isIdentity=false, identityGeneration=null, sequenceName=null, constraints=[], defaultValue=null, primaryKey=true, unique=false, nullable=false, isDefaultExpression=null, defaultExpression=null, checkConstraint=null, targetTable=null, joinTableName=null, inverseJoinColumn=null, isRelationship=false, onUpdate=null, onDelete=null, generatedAs=null, javaType=null, foreignKey=false, formattedName=null, referencedTable=null, referencedColumn=null, precision=0, scale=0, mappedBy=null), Column(name=name, sqlType=VARCHAR, length=100, isIdentity=false, identityGeneration=null, sequenceName=null, constraints=[], defaultValue=null, primaryKey=false, unique=false, nullable=false, isDefaultExpression=null, defaultExpression=null, checkConstraint=null, targetTable=null, joinTableName=null, inverseJoinColumn=null, isRelationship=false, onUpdate=null, onDelete=null, generatedAs=null, javaType=null, foreignKey=false, formattedName=null, referencedTable=null, referencedColumn=null, precision=0, scale=0, mappedBy=null), Column(name=description, sqlType=TEXT, length=0, isIdentity=false, identityGeneration=null, sequenceName=null, constraints=[], defaultValue=null, primaryKey=false, unique=false, nullable=true, isDefaultExpression=null, defaultExpression=null, checkConstraint=null, targetTable=null, joinTableName=null, inverseJoinColumn=null, isRelationship=false, onUpdate=null, onDelete=null, generatedAs=null, javaType=null, foreignKey=false, formattedName=null, referencedTable=null, referencedColumn=null, precision=0, scale=0, mappedBy=null), Column(name=parent_dept_id, sqlType=INT, length=0, isIdentity=false, identityGeneration=null, sequenceName=null, constraints=[], defaultValue=null, primaryKey=false, unique=false, nullable=true, isDefaultExpression=null, defaultExpression=null, checkConstraint=null, targetTable=null, joinTableName=null, inverseJoinColumn=null, isRelationship=false, onUpdate=null, onDelete=null, generatedAs=null, javaType=null, foreignKey=false, formattedName=null, referencedTable=null, referencedColumn=null, precision=0, scale=0, mappedBy=null), Column(name=created_at, sqlType=TIMESTAMP, length=0, isIdentity=false, identityGeneration=null, sequenceName=null, constraints=[], defaultValue=CURRENT_TIMESTAMP, primaryKey=false, unique=false, nullable=true, isDefaultExpression=null, defaultExpression=null, checkConstraint=null, targetTable=null, joinTableName=null, inverseJoinColumn=null, isRelationship=false, onUpdate=null, onDelete=null, generatedAs=null, javaType=null, foreignKey=false, formattedName=null, referencedTable=null, referencedColumn=null, precision=0, scale=0, mappedBy=null), Column(name=updated_at, sqlType=TIMESTAMP, length=0, isIdentity=false, identityGeneration=null, sequenceName=null, constraints=[], defaultValue=CURRENT_TIMESTAMP, primaryKey=false, unique=false, nullable=true, isDefaultExpression=null, defaultExpression=null, checkConstraint=null, targetTable=null, joinTableName=null, inverseJoinColumn=null, isRelationship=false, onUpdate=null, onDelete=null, generatedAs=null, javaType=null, foreignKey=false, formattedName=null, referencedTable=null, referencedColumn=null, precision=0, scale=0, mappedBy=null)], constraints=[PRIMARY KEY (department_id), FOREIGN KEY (parent_dept_id) REFERENCES department(department_id)], relationships=[]}";


        logger.info("✅ Table state: {}", table);
        System.out.println("🔎 Actual table.toString():\n");
        System.out.println("📌 Expected:\n" + expected);
        Assertions.assertEquals(expected, table.toString(), "Table toString should match the expected format.");
    }

    @Test
    public void testRecurringPatternTable_WithForeignKeyColumn() {
        logger.info("🟡 Testing parsed structure of 'recurring_pattern' table...");

        Table table = new Table();
        table.setName("recurring_pattern");

        Column id = new Column();
        id.setName("pattern_id");
        id.setSqlType("SERIAL");
        id.setPrimaryKey(true);
        id.setNullable(false);

        Column recurrence = new Column();
        recurrence.setName("recurrence_rule");
        recurrence.setSqlType("VARCHAR");
        recurrence.setLength(255);
        recurrence.setNullable(true);

        Column eventId = new Column();
        eventId.setName("event_id");
        eventId.setSqlType("INT");
        eventId.setNullable(true);
        eventId.setForeignKey(true);
        eventId.setReferencedTable("event");
        eventId.setReferencedColumn("event_id");

        table.addColumn(id);
        table.addColumn(recurrence);
        table.addColumn(eventId);

        table.setConstraints(List.of(
                "PRIMARY KEY (pattern_id)",
                "FOREIGN KEY (event_id) REFERENCES event(event_id)"
        ));

        logger.info("📋 Recurring Pattern table:\n{}", table);

        Assertions.assertEquals(3, table.getColumns().size());
        Column fk = table.getColumns().get(2);
        Assertions.assertTrue(fk.isForeignKey());
        Assertions.assertEquals("event", fk.getReferencedTable());
        Assertions.assertEquals("event_id", fk.getReferencedColumn());
    }

    @Test
    public void testDepartmentTableStructure_UnaffectedByTrigger() {
        logger.info("🟢 Testing 'department' table structure – ignoring trigger effects...");

        Table table = new Table();
        table.setName("department");

        Column id = new Column();
        id.setName("department_id");
        id.setSqlType("SERIAL");
        id.setPrimaryKey(true);
        id.setNullable(false);

        Column name = new Column();
        name.setName("name");
        name.setSqlType("VARCHAR");
        name.setLength(100);
        name.setNullable(false);

        Column updatedAt = new Column();
        updatedAt.setName("updated_at");
        updatedAt.setSqlType("TIMESTAMP");
        updatedAt.setDefaultValue("CURRENT_TIMESTAMP");
        updatedAt.setNullable(true);

        table.addColumn(id);
        table.addColumn(name);
        table.addColumn(updatedAt);

        table.setConstraints(List.of("PRIMARY KEY (department_id)"));

        logger.info("📋 Department table (static structure):\n{}", table);

        Assertions.assertEquals(3, table.getColumns().size());
        Assertions.assertEquals("updated_at", table.getColumns().get(2).getName());
    }

    @Test
    public void testEmployeeDepartmentTable_WithCompositePrimaryKeyAndFKs() {
        logger.info("🟣 Testing 'employee_department' table with composite PK and two FKs...");

        Table table = new Table();
        table.setName("employee_department");

        Column employeeId = new Column();
        employeeId.setName("employee_id");
        employeeId.setSqlType("INT");
        employeeId.setNullable(false);
        employeeId.setPrimaryKey(true);
        employeeId.setForeignKey(true);
        employeeId.setReferencedTable("employee");
        employeeId.setReferencedColumn("id");

        Column departmentId = new Column();
        departmentId.setName("department_id");
        departmentId.setSqlType("INT");
        departmentId.setNullable(false);
        departmentId.setPrimaryKey(true);
        departmentId.setForeignKey(true);
        departmentId.setReferencedTable("department");
        departmentId.setReferencedColumn("id");

        Column assignedAt = new Column();
        assignedAt.setName("assigned_at");
        assignedAt.setSqlType("TIMESTAMP");
        assignedAt.setDefaultValue("CURRENT_TIMESTAMP");
        assignedAt.setNullable(true);

        Column assignedBy = new Column();
        assignedBy.setName("assigned_by");
        assignedBy.setSqlType("VARCHAR");
        assignedBy.setLength(100);
        assignedBy.setNullable(true);

        table.addColumn(employeeId);
        table.addColumn(departmentId);
        table.addColumn(assignedAt);
        table.addColumn(assignedBy);

        table.setConstraints(List.of(
                "PRIMARY KEY (employee_id, department_id)",
                "FOREIGN KEY (employee_id) REFERENCES employee(id)",
                "FOREIGN KEY (department_id) REFERENCES department(id)"
        ));

        logger.info("📋 Employee-Department table:\n{}", table);

        Assertions.assertEquals(4, table.getColumns().size());
        Assertions.assertEquals(3, table.getConstraints().size());

        // Έλεγχος composite PK
        long pkCount = table.getColumns().stream().filter(Column::isPrimaryKey).count();
        Assertions.assertEquals(2, pkCount, "There should be 2 primary key columns");

        // Έλεγχος FK ιδιοτήτων
        Assertions.assertTrue(employeeId.isForeignKey());
        Assertions.assertEquals("employee", employeeId.getReferencedTable());
        Assertions.assertEquals("id", employeeId.getReferencedColumn());

        Assertions.assertTrue(departmentId.isForeignKey());
        Assertions.assertEquals("department", departmentId.getReferencedTable());
        Assertions.assertEquals("id", departmentId.getReferencedColumn());
    }
}
