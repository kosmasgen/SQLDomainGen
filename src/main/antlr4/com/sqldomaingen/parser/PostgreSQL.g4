grammar PostgreSQL;

// Ειδικοί χαρακτήρες
LPAREN : '(';
RPAREN : ')';
LBRACE : '{';
RBRACE : '}';
SEMICOLON : ';';
COLON : ':';
DOUBLE_COLON : '::';
COMMA : ',';
DOUBLE_QUOTE : '"';
ON : 'ON';
DELETE : 'DELETE';
RULE : 'RULE';
DO : 'DO';
AS : 'AS';
TO : 'TO';
INSTEAD : 'INSTEAD';
NOTHING : 'NOTHING';
FOR : 'FOR';
SELECT : 'SELECT';
USING : 'USING';
DOLLAR_QUOTE: '$$';
NEW : 'NEW';
TABLE : 'TABLE';
CREATE : 'CREATE';
RELATIONSHIP : 'RELATIONSHIP';
DECIMAL: 'DECIMAL';
INT: 'INT';
VARCHAR  : 'VARCHAR' ;
DOT : '.';
CONSTRAINT : 'CONSTRAINT';
NULL : 'NULL';
NOT : 'NOT';
SET : 'SET';
REFERENCES : 'REFERENCES';
SERIAL : 'SERIAL';
PRIMARY_KEY : 'PRIMARY KEY';
CHECK : 'CHECK';
ANY : 'ANY';
LBRACKET : '[';
RBRACKET : ']';
ARRAY : 'ARRAY';
DEFAULT : 'DEFAULT';
NEXTVAL : 'nextval';
REGCLASS : 'regclass';
CASCADE : 'CASCADE';
SET_NULL : 'SET NULL';
SET_DEFAULT : 'SET DEFAULT';
NO_ACTION : 'NO ACTION';
RESTRICT : 'RESTRICT';
ON_DELETE : 'ON DELETE';
ON_UPDATE : 'ON UPDATE';
UNIQUE : 'UNIQUE';
EQUALS : '=';
TEXT    : 'TEXT' ;
INTEGER : 'INTEGER' ;
BOOLEAN : 'BOOLEAN' ;
FOREIGN_KEY : 'FOREIGN KEY';
NOT_NULL    : 'NOT NULL';
TRIGGER      : 'TRIGGER';
BEFORE       : 'BEFORE';
AFTER        : 'AFTER';
UPDATE       : 'UPDATE';
INSERT       : 'INSERT';
EACH         : 'EACH';
ROW          : 'ROW';
EXECUTE      : 'EXECUTE';
FUNCTION     : 'FUNCTION';




// -------------------------
// Δημιουργία Πίνακα
// -------------------------
createTableStatement
    : CREATE TABLE tableName '(' ( tableConstraint | columnDef ) (',' (tableConstraint | columnDef ))* ')' (partitionClause)? SEMICOLON
    | CREATE TABLE tableName 'PARTITION OF' tableName partitionValuesClause SEMICOLON
    ;


// Ορισμός μιας στήλης στον πίνακα
columnDef
    : columnName dataType columnTypeModifier? (constraint)* (generatedColumn | collateClause)? onUpdateClause?
    ;

columnTypeModifier
    : '(' NUMBER (',' NUMBER)? ')'
    ;


// Υπολογιζόμενη στήλη
generatedColumn
    : 'GENERATED' ('ALWAYS' | 'BY DEFAULT') 'AS' '(' expression ')' 'STORED'
    ;

// COLLATE για στήλες
collateClause
    : 'COLLATE' STRING
    ;

// Δηλώσεις ALTER TABLE
alterTableStatement
    : 'ALTER' 'TABLE' tableName alterAction (',' alterAction)*';'?
    ;

// Τύποι τροποποίησης πίνακα
alterAction
    : 'ADD' 'COLUMN' columnDef
    | 'DROP' 'COLUMN' columnName
    | 'ADD' ('CONSTRAINT' IDENTIFIER)? 'FOREIGN KEY' '(' columnNameList ')' 'REFERENCES' tableName ('(' columnNameList ')')? (onAction)*
    | 'DROP' 'CONSTRAINT' IDENTIFIER
    | 'ALTER' 'COLUMN' columnName alterColumnAction
    | 'RENAME COLUMN' columnName 'TO' columnName
    | 'RENAME TO' tableName
    | 'SET' ('SCHEMA' schemaName | 'TABLESPACE' IDENTIFIER)
    | 'ENABLE' 'REPLICA' 'TRIGGER' IDENTIFIER
    | 'DISABLE' 'REPLICA' 'TRIGGER' IDENTIFIER
    | 'CLUSTER' 'ON' IDENTIFIER
    | 'SET WITHOUT CLUSTER'
    | 'OWNER TO' IDENTIFIER
    ;

// Τροποποιήσεις σε στήλες
alterColumnAction
    : (('SET DEFAULT' value
      | 'DROP DEFAULT'
      | 'SET NOT NULL'
      | 'DROP NOT NULL'
      | 'SET STATISTICS' NUMBER
      | 'SET STORAGE' ('PLAIN' | 'EXTERNAL' | 'EXTENDED' | 'MAIN')
      | 'SET COMPRESSION' IDENTIFIER
      | 'RESET' ('STORAGE' | 'STATISTICS' | 'COMPRESSION')
      | 'TYPE' dataType)+)
    ;

// Τύποι δεδομένων (PostgreSQL-specific)
dataType
    : 'INTEGER'
    | 'FLOAT8'
    | 'FLOAT4'
    | 'INT8'
    | 'INT'
    | 'SMALLINT'
    | 'BIGINT'
    | 'BIGSERIAL'
    | 'SERIAL'
    | 'SMALLSERIAL'
    | 'REAL'
    | 'DOUBLE PRECISION'
    | 'NUMERIC' ('(' NUMBER (',' NUMBER)? ')')?
    | decimalType
    | 'MONEY'
    | 'CHAR' ('(' NUMBER ')')?
    | 'VARCHAR' ('(' NUMBER ')')?
    | 'TEXT'
    | 'BOOLEAN'
    | 'JSON'
    | 'JSONB'
    | 'DATE'
    | 'TIME' ('WITHOUT' 'TIME' 'ZONE' | 'WITH' 'TIME' 'ZONE')?
    | 'TIMESTAMP' ('WITHOUT' 'TIME' 'ZONE' | 'WITH' 'TIME' 'ZONE')?
    | 'TIMESTAMP'
    | 'INTERVAL'
    | 'UUID'
    | 'ARRAY'
    | 'BYTEA'
    | 'ENUM' '(' STRING (',' STRING)* ')'
    | 'CITEXT'
    | 'TSVECTOR'
    | 'INET'
    | 'CIDR'
    | 'MACADDR'
    | 'XML'
    | 'PG_LSN'
    | 'BIT' ('(' NUMBER ')')?
    | 'VARBIT' ('(' NUMBER ')')?
    | 'TRIGGER'
    | IDENTIFIER
    ;




// Ενέργειες ON DELETE
onDeleteAction
    : 'CASCADE'
    | 'SET NULL'
    | 'SET DEFAULT'
    | 'NO ACTION'
    ;

// Ενέργειες ON UPDATE
onUpdateAction
    : 'CASCADE'
    | 'SET NULL'
    | 'SET DEFAULT'
    | 'NO ACTION'
    ;

onUpdateClause
    : 'ON UPDATE' 'CURRENT_TIMESTAMP'
    ;



// In-line constraints (για μεμονωμένες στήλες)
constraint
    : 'NOT NULL'
    | 'NULL'
    | 'UNIQUE' ('ON CONFLICT' conflictAction)?
    | MANYTOMANY ('ON CONFLICT' conflictAction)?
    | 'DEFAULT' value
    | 'CHECK' '(' condition ')'
    | 'REFERENCES' (schemaName DOT)? tableName '(' columnName ')' (onAction)?
    | 'FOREIGN KEY' '(' columnNameList ')' 'REFERENCES' (schemaName DOT)? tableName '(' columnNameList ')' ('RELATIONSHIP' relationshipType)? (onAction)*
    | 'EXCLUDE' 'USING' IDENTIFIER '(' excludeElementList ')' ('WHERE' condition)?
    | 'AUTO_INCREMENT'
    | 'PRIMARY KEY'
    ;

// Table-level constraints (για ολόκληρο τον πίνακα)
tableConstraint
    : 'CONSTRAINT' IDENTIFIER? 'PRIMARY KEY' '(' columnNameList ')'
    | 'CONSTRAINT' IDENTIFIER? 'FOREIGN KEY' '(' columnNameList ')' 'REFERENCES' (schemaName DOT)? tableName '(' columnNameList ')' (onAction)*
    | 'FOREIGN KEY' '(' columnNameList ')' 'REFERENCES' (schemaName DOT)? tableName '(' columnNameList ')' (onAction)*
    | 'CONSTRAINT' IDENTIFIER? 'UNIQUE' '(' columnNameList ')'
    | 'CONSTRAINT' IDENTIFIER? 'CHECK' '(' condition ')'
    | 'EXCLUDE' 'USING' IDENTIFIER '(' excludeElementList ')'
    | 'PRIMARY KEY' '(' columnNameList ')'
    ;



// Εξαιρέσεις για EXCLUDE constraints
excludeElementList
    : excludeElement (',' excludeElement)*
    ;

// Στοιχείο EXCLUDE constraint
excludeElement
    : columnName 'WITH' comparisonOperator
    ;

// Ενέργειες ON DELETE/UPDATE
onAction
    : 'ON DELETE' action
    | 'ON UPDATE' action
    | 'DEFERRABLE' ('INITIALLY' ('DEFERRED' | 'IMMEDIATE'))?
    ;

action
    : 'CASCADE'
    | 'SET NULL'
    | 'SET DEFAULT'
    | 'NO ACTION'
    | 'RESTRICT'
    ;

// Trigger
triggerStatement
    : 'CREATE' 'TRIGGER' IDENTIFIER triggerTiming triggerEvent 'ON' tableName 'FOR EACH ROW' 'EXECUTE FUNCTION' IDENTIFIER '(' (value (',' value)*)? ')' SEMICOLON
    ;

// Πότε εκτελείται το trigger
triggerTiming
    : 'BEFORE'
    | 'AFTER'
    | 'INSTEAD OF'
    ;

// Ενέργειες που ενεργοποιούν το trigger
triggerEvent
    : ('INSERT' | 'UPDATE' | 'DELETE') ('OR' ('INSERT' | 'UPDATE' | 'DELETE'))*
    ;

// Εντολές INSERT/UPDATE/DELETE
dataManipulationStatement
    : insertStatement
    | updateStatement
    | deleteStatement
    ;

// Εντολή INSERT
insertStatement
    : 'INSERT INTO' tableName '(' columnName (',' columnName)* ')' 'VALUES' '(' value (',' value)* ')' ';'
    ;

// Εντολή UPDATE
updateStatement
    : 'UPDATE' tableName 'SET' columnName '=' value (',' columnName '=' value)* ('WHERE' condition)? ';'
    ;

// Εντολή DELETE
deleteStatement
    : 'DELETE FROM' tableName ('WHERE' condition)? ';'
    ;

// Σύνθετες συνθήκες
condition
    : columnName comparisonOperator value
    | columnName 'IN' '(' (value | subquery) (',' (value | subquery))* ')'
    | columnName 'BETWEEN' value 'AND' value
    | columnName 'IS' 'NULL'
    | columnName 'IS NOT NULL'
    | columnName 'LIKE' STRING
    | columnName 'ILIKE' STRING
    | 'NOT' condition
    | condition 'AND' condition
    | condition 'OR' condition
    | 'true'
    | 'false'
    ;

// Υποερώτημα (subquery)
subquery
    : '(' sqlScript ')'
    ;

// Τελεστές σύγκρισης
comparisonOperator
    : '=' | '<>' | '!=' | '<' | '<=' | '>' | '>=' | '@>' | '<@' | '&&' | '?' | '?|' | '?&'
    ;

// Αριθμητικοί τελεστές
arithmeticOperator
    : '+' | '-' | '*' | '/' | '%' | '^'
    ;

// Σενάριο SQL
sqlScript
    : (createTableStatement
    | createSchemaStatement
    | createViewStatement
    | createFunctionStatement
    | createIndexStatement
    | createRuleStatement
    | triggerStatement
    | alterTableStatement
    | dataManipulationStatement
    | createPolicyStatement
    )* EOF
    ;

// Δημιουργία σχήματος
createSchemaStatement
    : 'CREATE SCHEMA' IDENTIFIER ';'
    ;

// Δημιουργία view
createViewStatement
    : 'CREATE VIEW' IDENTIFIER 'AS' selectStatement ';'
    ;

// Δημιουργία function
createFunctionStatement
    : 'CREATE FUNCTION' IDENTIFIER '(' (parameter (',' parameter)*)? ')'
      'RETURNS' dataType
      'AS' DOLLAR_QUOTE plpgsqlBlock DOLLAR_QUOTE
      'LANGUAGE' IDENTIFIER ';'
    ;

// Παράμετρος
parameter
    : IDENTIFIER dataType
    ;

// Εντολή SELECT
selectStatement
    : 'SELECT' selectList ('FROM' tableReference (',' tableReference)*)? whereClause? orderByClause? limitClause?
    ;

// Λίστα στηλών SELECT
selectList
    : '*'
    | columnName (',' columnName)*
    ;

// Αναφορά πίνακα
tableReference
    : tableName ('AS' IDENTIFIER)?
    ;

// WHERE clause
whereClause
    : 'WHERE' condition
    ;

// ORDER BY clause
orderByClause
    : 'ORDER BY' columnName ('ASC' | 'DESC')?
    ;

// LIMIT clause
limitClause
    : 'LIMIT' NUMBER
    ;

// Λίστα στηλών
columnNameList
    :  columnName (',' columnName)*
    ;

// Ξένος πίνακας
foreignTable
    : (schemaName? '.' tableName | tableName) '(' columnName (',' columnName)* ')'
    ;

// Ενέργειες για ON CONFLICT
conflictAction
    : 'DO NOTHING'
    | 'DO UPDATE' 'SET' columnName '=' value ('WHERE' condition)?
    ;

// Όνομα σχήματος
schemaName
    : IDENTIFIER
    ;

// Όνομα πίνακα
tableName
    : (schemaName DOT)? IDENTIFIER
    ;

// Όνομα στήλης
columnName
    : IDENTIFIER
    ;

// Τύποι σχέσεων
relationshipType
    : 'ONETOONE'
    | 'MANYTOONE'
    | 'ONETOMANY'
    | 'MANYTOMANY'
    ;

MANYTOMANY : 'MANYTOMANY';



decimalType
    : ('DECIMAL' | 'NUMERIC') ('(' precision=NUMBER (',' scale=NUMBER)? ')')?
    ;


// Ορισμός partitioning για πίνακα
partitionClause
    : 'PARTITION BY' partitionStrategy '(' columnName ')'
    ;

// Υποστήριξη για διαφορετικές στρατηγικές partitioning
partitionStrategy
    : 'RANGE'
    | 'LIST'
    | 'HASH'
    ;

// Δημιουργία partition από υπάρχον partitioned πίνακα
createPartitionStatement
    : CREATE TABLE tableName 'PARTITION OF' tableName partitionValuesClause SEMICOLON
    ;

// Ορισμός τιμών για partition
partitionValuesClause
    : 'FOR VALUES' ('FROM' '(' value ')' 'TO' '(' value ')'
                   | 'IN' '(' value (',' value)* ')'
                   | 'MODULUS' NUMBER 'REMAINDER' NUMBER)
    ;

partitionOfClause
    : 'PARTITION OF' tableName partitionValuesClause
    ;

// Δημιουργία Index
createIndexStatement
    : CREATE 'INDEX' IDENTIFIER ON tableName '(' columnName (',' columnName)* ')' (indexOptionsClause)? SEMICOLON
    ;

// Επιλογές για Index
indexOptionsClause
    : 'TABLESPACE' IDENTIFIER
    ;

// Ορισμός για υποστηριζόμενες εκφράσεις.
expression
    : value
    | columnName
    | anyExpression
    | arrayConstructor
    | '(' expression ')'
    | expression ('+' | '-' | '*' | '/') expression
    | expression comparisonOperator expression
    | expression 'AND' expression
    | expression 'OR' expression
    | expression DOUBLE_COLON (typeName | dataType)
    ;


    valueAtom
        : STRING
        | numericLiteral
        | booleanLiteral
        | 'CURRENT_TIMESTAMP'
        | NULL
        | (IDENTIFIER | 'now') ('(' valueList? ')')?
        ;

    typeCast
        : DOUBLE_COLON IDENTIFIER (IDENTIFIER)?
        ;

    valueList
        : value (',' value)*
        ;

// Τιμές για στήλες, εκφράσεις, και DEFAULT
value
    : STRING
    | NUMBER
    | valueAtom typeCast?
    | 'TRUE'
    | 'FALSE'
    | 'NULL'
    | 'CURRENT_TIMESTAMP'
    | 'current_user'
    | 'now'
    | 'infinity'
    | 'NaN'
    | intervalLiteral
    | arrayConstructor
    ;

expressionList
    : expression (',' expression)*
    ;


arrayConstructor
    : 'ARRAY' '[' expressionList ']' (DOUBLE_COLON dataType)?
    ;


// Δημιουργία πολιτικής (Policy) - Κανόνας
createPolicyStatement
    : 'CREATE' 'POLICY' IDENTIFIER 'ON' tableName
      ('FOR' ('ALL' | 'SELECT' | 'INSERT' | 'UPDATE' | 'DELETE'))?
      ('USING' '(' condition ')')?
      ('WITH CHECK' '(' condition ')')? SEMICOLON
    ;

// PL/pgSQL Block
plpgsqlBlock
    : 'BEGIN' statement* 'END' SEMICOLON
    ;

// Δηλώσεις (Statements)
statement
    : columnName '.' columnName '=' value SEMICOLON
    | 'RETURN' 'NEW' SEMICOLON
    | 'NEW' '.' columnName '=' value SEMICOLON
    ;

// Δημιουργία κανόνα (Rule)
createRuleStatement
    : 'CREATE' 'RULE' IDENTIFIER 'AS' 'ON' ruleEvent 'TO' tableName 'DO' ruleAction SEMICOLON
    ;

// Συμβάν κανόνα (Rule Event)
ruleEvent
    : 'DELETE'
    | 'INSERT'
    | 'UPDATE'
    | 'SELECT'
    ;

// Ενέργεια κανόνα (Rule Action)
ruleAction
    : 'INSTEAD NOTHING'
    | 'INSTEAD' sqlAction
    ;

// SQL Ενέργεια
sqlAction
    : 'INSERT INTO' tableName '(' columnName (',' columnName)* ')' 'VALUES' '(' value (',' value)* ')'
    | 'UPDATE' tableName 'SET' columnName '=' value
    | 'DELETE FROM' tableName
    ;

// Αναγνώριση ταυτοτήτων (ονόματα πινάκων, στηλών, κ.λπ.)
IDENTIFIER
    : [a-zA-Z_][a-zA-Z0-9_]*   // Κανονικά ονόματα
    | '"' (~["\r\n])* '"'      // Εισαγωγικά ονόματα
    ;

defaultValue
    : 'DEFAULT' (| numericLiteral | booleanLiteral | 'CURRENT_TIMESTAMP')
    ;


numericLiteral : '-'? DIGIT+ ('.' DIGIT+)?;

booleanLiteral
    : 'TRUE'
    | 'FALSE'
    ;

foreignKeyAction
    : 'ON DELETE' fkAction=referentialAction
    | 'ON UPDATE' fkAction=referentialAction
    ;

referentialAction
    : 'CASCADE'
    | 'SET NULL'
    | 'SET DEFAULT'
    | 'RESTRICT'
    | 'NO ACTION'
    ;



anyExpression
    : 'ANY' '(' arrayConstructor ')'
    ;

intervalLiteral
    : 'INTERVAL' STRING
    ;

typeName
    : IDENTIFIER ('.' IDENTIFIER)?
    ;


// Αναγνώριση αλφαριθμητικών τιμών με υποστήριξη escapes και multiline strings
STRING
    : '\'' ( ~[\r\n'] | '\'\'' )* '\''
    ;

// Αναγνώριση αριθμών (π.χ. 123, 123.45, -123, 1.23e10)
NUMBER
    : DIGIT+                                          // Απλοί ακέραιοι αριθμοί
    | '-'? DIGIT+ ('.' DIGIT+)? ([eE] [+-]? DIGIT+)? // Δεκαδικοί & επιστημονική σημειογραφία
    ;

DIGIT : [0-9] ;

// Παραβλέπει κενά διαστήματα
WS
    : [ \t\r\n]+ -> skip
    ;
