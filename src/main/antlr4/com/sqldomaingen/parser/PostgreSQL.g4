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
DECIMAL   : 'DECIMAL' | 'decimal';
INT       : 'INT' | 'int';
VARCHAR   : 'VARCHAR' | 'varchar';
DOT : '.';
CONSTRAINT : 'CONSTRAINT';
NULL : 'NULL';
NOT : 'NOT';
IS : 'IS';
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
TEXT      : 'TEXT' | 'text';
INTEGER   : 'INTEGER' | 'integer';
BOOLEAN   : 'BOOLEAN' | 'boolean';
FOREIGN_KEY : 'FOREIGN KEY';
TRIGGER      : 'TRIGGER';
BEFORE       : 'BEFORE';
AFTER        : 'AFTER';
UPDATE       : 'UPDATE';
INSERT       : 'INSERT';
EACH         : 'EACH';
ROW          : 'ROW';
EXECUTE      : 'EXECUTE';
FUNCTION     : 'FUNCTION';
IN : 'IN';
BETWEEN : 'BETWEEN';
AND : 'AND';
LIKE : 'LIKE';
ILIKE : 'ILIKE';
PRIMARY : 'PRIMARY';
KEY : 'KEY';
FOREIGN : 'FOREIGN';
EXCLUDE : 'EXCLUDE';
BIGSERIAL : 'BIGSERIAL' | 'bigserial';
JSONB     : 'JSONB' | 'jsonb';
TIMESTAMP : 'TIMESTAMP' | 'timestamp';
INDEX     : 'INDEX' | 'index';
NOW       : 'NOW' | 'now';
FLOAT8 : 'FLOAT8' | 'float8';
FLOAT4 : 'FLOAT4' | 'float4';
INT8 : 'INT8' | 'int8';
SMALLINT : 'SMALLINT' | 'smallint';
BIGINT : 'BIGINT' | 'bigint';
SMALLSERIAL : 'SMALLSERIAL' | 'smallserial';
REAL : 'REAL' | 'real';
DOUBLE : 'DOUBLE' | 'double';
PRECISION : 'PRECISION' | 'precision';
NUMERIC : 'NUMERIC' | 'numeric';
MONEY : 'MONEY' | 'money';
CHAR : 'CHAR' | 'char';
CHARACTER : 'CHARACTER' | 'character';
JSON : 'JSON' | 'json';
DATE : 'DATE' | 'date';
TIME : 'TIME' | 'time';
INTERVAL : 'INTERVAL' | 'interval';
UUID : 'UUID' | 'uuid';
BYTEA : 'BYTEA' | 'bytea';
ENUM : 'ENUM' | 'enum';
CITEXT : 'CITEXT' | 'citext';
TSVECTOR : 'TSVECTOR' | 'tsvector';
INET : 'INET' | 'inet';
CIDR : 'CIDR' | 'cidr';
MACADDR : 'MACADDR' | 'macaddr';
XML : 'XML' | 'xml';
PG_LSN : 'PG_LSN' | 'pg_lsn';
BIT : 'BIT' | 'bit';
VARBIT : 'VARBIT' | 'varbit';
VARYING : 'VARYING' | 'varying';
BPCHAR: [bB] [pP] [cC] [hH] [aA] [rR];
WITHOUT : 'WITHOUT' | 'without';
WITH : 'WITH' | 'with';
ZONE : 'ZONE' | 'zone';


identityColumn
    : 'GENERATED' ('ALWAYS' | 'BY' 'DEFAULT') 'AS' 'IDENTITY' ('(' .*? ')')?
    ;


// -------------------------
// Δημιουργία Πίνακα
// -------------------------
createTableStatement
    : CREATE TABLE tableName '(' ( tableConstraint | columnDef ) (',' (tableConstraint | columnDef ))* ')' (partitionClause)? SEMICOLON
    | CREATE TABLE tableName 'PARTITION OF' tableName partitionValuesClause SEMICOLON
    ;


// Ορισμός μιας στήλης στον πίνακα
columnDef
    : columnName dataType columnAttribute*
    ;

columnAttribute
    : constraint
    | generatedColumn
    | identityColumn
    | collateClause
    | onUpdateClause
    ;


columnTypeModifier
    : '(' NUMBER (',' NUMBER)? ')'
    ;


// Υπολογιζόμενη στήλη
generatedColumn
    : 'GENERATED' ('ALWAYS' | 'BY DEFAULT') 'AS' generatedColumnBody
    ;


 generatedColumnBody
     : '(' expression ')' 'STORED'
     | 'IDENTITY' identityOptions?
     ;

identityOptions
    : '(' identityOption* ')'
    ;

identityOption
    : 'INCREMENT' 'BY' numericLiteral
    | 'MINVALUE' numericLiteral
    | 'MAXVALUE' numericLiteral
    | 'START' numericLiteral
    | 'CACHE' numericLiteral
    | 'CYCLE'
    | 'NO' 'CYCLE'
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
    : INTEGER
    | FLOAT8
    | FLOAT4
    | INT8
    | INT
    | SMALLINT
    | BIGINT
    | BIGSERIAL
    | SERIAL
    | SMALLSERIAL
    | REAL
    | DOUBLE PRECISION
    | NUMERIC ('(' NUMBER (',' NUMBER)? ')')?
    | decimalType
    | MONEY
    | CHAR ('(' NUMBER ')')?
    | VARCHAR ('(' NUMBER ')')?
    | CHARACTER VARYING ('(' NUMBER ')')?
    | TEXT
    | BOOLEAN
    | BPCHAR ('(' NUMBER ')')?
    | JSON
    | JSONB
    | DATE
    | TIME ('(' NUMBER ')')? ('WITHOUT' 'TIME' 'ZONE' | 'WITH' 'TIME' 'ZONE')?
    | TIMESTAMP ('(' NUMBER ')')? ('WITHOUT' 'TIME' 'ZONE' | 'WITH' 'TIME' 'ZONE')?
    | INTERVAL
    | UUID
    | ARRAY
    | BYTEA
    | ENUM '(' STRING (',' STRING)* ')'
    | CITEXT
    | TSVECTOR
    | INET
    | CIDR
    | MACADDR
    | XML
    | PG_LSN
    | BIT ('(' NUMBER ')')?
    | VARBIT ('(' NUMBER ')')?
    | TRIGGER
    | IDENTIFIER IDENTIFIER
    | IDENTIFIER
    ;



functionCall
    : IDENTIFIER '(' expressionList? ')'
    ;






onUpdateClause
    : 'ON UPDATE' 'CURRENT_TIMESTAMP'
    ;



// In-line constraints (για μεμονωμένες στήλες)
constraint
    : NOT NULL
    | NULL
    | UNIQUE ('ON CONFLICT' conflictAction)?
    | MANYTOMANY ('ON CONFLICT' conflictAction)?
    | DEFAULT value
    | CHECK LPAREN condition RPAREN
    | REFERENCES (schemaName DOT)? tableName LPAREN columnName RPAREN onAction?
    | FOREIGN KEY LPAREN columnNameList RPAREN REFERENCES (schemaName DOT)? tableName LPAREN columnNameList RPAREN (RELATIONSHIP relationshipType)? onAction*
    | EXCLUDE USING IDENTIFIER LPAREN excludeElementList RPAREN ('WHERE' condition)?
    | 'AUTO_INCREMENT'
    | PRIMARY KEY
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


condition
    : booleanCondition
    ;

booleanCondition
    : predicate
    | LPAREN booleanCondition RPAREN
    | NOT booleanCondition
    | booleanCondition 'AND' booleanCondition
    | booleanCondition 'OR' booleanCondition
    ;

predicate
    : expression IS NOT NULL
    | expression IS NULL
    | expression IN LPAREN (expression | subquery) (COMMA (expression | subquery))* RPAREN
    | expression BETWEEN expression AND expression
    | expression LIKE STRING
    | expression ILIKE STRING
    | expression comparisonOperator expression
    | booleanLiteral
    ;



// Υποερώτημα (subquery)
subquery
    : '(' sqlScript ')'
    ;

// Τελεστές σύγκρισης
comparisonOperator
    : '=' | '<>' | '!=' | '<' | '<=' | '>' | '>=' | '@>' | '<@' | '&&' | '?' | '?|' | '?&'
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


// Ορισμός τιμών για partition
partitionValuesClause
    : 'FOR VALUES' ('FROM' '(' value ')' 'TO' '(' value ')'
                   | 'IN' '(' value (',' value)* ')'
                   | 'MODULUS' NUMBER 'REMAINDER' NUMBER)
    ;


// Δημιουργία Index
createIndexStatement
    : CREATE 'UNIQUE'? 'INDEX' IDENTIFIER
      ON tableName
      ('USING' IDENTIFIER)?
      '(' indexElement (',' indexElement)* ')'
      ('WHERE' condition)?
      (indexOptionsClause)?
      SEMICOLON
    ;



indexElement
    : expression IDENTIFIER? ('ASC' | 'DESC')?
    ;

// Επιλογές για Index
indexOptionsClause
    : 'TABLESPACE' IDENTIFIER
    ;

// Ορισμός για υποστηριζόμενες εκφράσεις.
expression
    : '(' expression ')' typeCast?
    | anyExpression
    | functionCall typeCast?
    | columnName typeCast?
    | value
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
    : DOUBLE_COLON dataType ('[' ']')*
    ;

valueList
    : value (',' value)*
    ;

// Τιμές για στήλες, εκφράσεις, και DEFAULT
value
    : numericLiteral typeCast?
    | valueAtom typeCast?
    | arrayConstructor typeCast?
    | intervalLiteral
    | 'infinity'
    | 'NaN'
    ;




expressionList
    : expression (',' expression)*
    ;


arrayConstructor
    : 'ARRAY' '[' expressionList ']' typeCast?
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


numericLiteral
    : NUMBER
    | '-' NUMBER
    ;

booleanLiteral
    : 'TRUE'
    | 'FALSE'
    | 'true'
    | 'false'
    ;



anyExpression
    : 'ANY' '(' expression ')'
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

LINE_COMMENT
    : '--' ~[\r\n]* -> skip
    ;

BLOCK_COMMENT
    : '/*' .*? '*/' -> skip
    ;


// Παραβλέπει κενά διαστήματα
WS
    : [ \t\r\n]+ -> skip
    ;
