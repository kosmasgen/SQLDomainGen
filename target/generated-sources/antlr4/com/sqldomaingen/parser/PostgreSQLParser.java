// Generated from com/sqldomaingen/parser/PostgreSQL.g4 by ANTLR 4.13.1
package com.sqldomaingen.parser;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue"})
public class PostgreSQLParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.13.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, T__11=12, T__12=13, T__13=14, T__14=15, T__15=16, T__16=17, 
		T__17=18, T__18=19, T__19=20, T__20=21, T__21=22, T__22=23, T__23=24, 
		T__24=25, T__25=26, T__26=27, T__27=28, T__28=29, T__29=30, T__30=31, 
		T__31=32, T__32=33, T__33=34, T__34=35, T__35=36, T__36=37, T__37=38, 
		T__38=39, T__39=40, T__40=41, T__41=42, T__42=43, T__43=44, T__44=45, 
		T__45=46, T__46=47, T__47=48, T__48=49, T__49=50, T__50=51, T__51=52, 
		T__52=53, T__53=54, T__54=55, T__55=56, T__56=57, T__57=58, T__58=59, 
		T__59=60, T__60=61, T__61=62, T__62=63, T__63=64, T__64=65, T__65=66, 
		T__66=67, T__67=68, T__68=69, T__69=70, T__70=71, T__71=72, T__72=73, 
		T__73=74, T__74=75, T__75=76, T__76=77, T__77=78, T__78=79, T__79=80, 
		T__80=81, T__81=82, T__82=83, T__83=84, T__84=85, T__85=86, T__86=87, 
		T__87=88, T__88=89, T__89=90, T__90=91, T__91=92, T__92=93, T__93=94, 
		T__94=95, T__95=96, T__96=97, T__97=98, T__98=99, T__99=100, T__100=101, 
		T__101=102, T__102=103, T__103=104, T__104=105, T__105=106, T__106=107, 
		T__107=108, T__108=109, T__109=110, T__110=111, T__111=112, T__112=113, 
		T__113=114, T__114=115, T__115=116, T__116=117, LPAREN=118, RPAREN=119, 
		LBRACE=120, RBRACE=121, SEMICOLON=122, COLON=123, DOUBLE_COLON=124, COMMA=125, 
		DOUBLE_QUOTE=126, ON=127, DELETE=128, RULE=129, DO=130, AS=131, TO=132, 
		INSTEAD=133, NOTHING=134, FOR=135, SELECT=136, USING=137, DOLLAR_QUOTE=138, 
		NEW=139, TABLE=140, CREATE=141, RELATIONSHIP=142, DECIMAL=143, INT=144, 
		VARCHAR=145, DOT=146, CONSTRAINT=147, NULL=148, NOT=149, IS=150, SET=151, 
		REFERENCES=152, SERIAL=153, PRIMARY_KEY=154, CHECK=155, ANY=156, LBRACKET=157, 
		RBRACKET=158, ARRAY=159, DEFAULT=160, NEXTVAL=161, REGCLASS=162, CASCADE=163, 
		SET_NULL=164, SET_DEFAULT=165, NO_ACTION=166, RESTRICT=167, ON_DELETE=168, 
		ON_UPDATE=169, UNIQUE=170, EQUALS=171, TEXT=172, INTEGER=173, BOOLEAN=174, 
		FOREIGN_KEY=175, TRIGGER=176, BEFORE=177, AFTER=178, UPDATE=179, INSERT=180, 
		EACH=181, ROW=182, EXECUTE=183, FUNCTION=184, IN=185, BETWEEN=186, AND=187, 
		LIKE=188, ILIKE=189, PRIMARY=190, KEY=191, FOREIGN=192, EXCLUDE=193, BIGSERIAL=194, 
		JSONB=195, TIMESTAMP=196, INDEX=197, NOW=198, FLOAT8=199, FLOAT4=200, 
		INT8=201, SMALLINT=202, BIGINT=203, SMALLSERIAL=204, REAL=205, DOUBLE=206, 
		PRECISION=207, NUMERIC=208, MONEY=209, CHAR=210, CHARACTER=211, JSON=212, 
		DATE=213, TIME=214, INTERVAL=215, UUID=216, BYTEA=217, ENUM=218, CITEXT=219, 
		TSVECTOR=220, INET=221, CIDR=222, MACADDR=223, XML=224, PG_LSN=225, BIT=226, 
		VARBIT=227, VARYING=228, BPCHAR=229, WITHOUT=230, WITH=231, ZONE=232, 
		MANYTOMANY=233, IDENTIFIER=234, STRING=235, NUMBER=236, DIGIT=237, WS=238;
	public static final int
		RULE_identityColumn = 0, RULE_createTableStatement = 1, RULE_columnDef = 2, 
		RULE_columnAttribute = 3, RULE_columnTypeModifier = 4, RULE_generatedColumn = 5, 
		RULE_generatedColumnBody = 6, RULE_identityOptions = 7, RULE_identityOption = 8, 
		RULE_collateClause = 9, RULE_alterTableStatement = 10, RULE_alterAction = 11, 
		RULE_alterColumnAction = 12, RULE_dataType = 13, RULE_functionCall = 14, 
		RULE_onUpdateClause = 15, RULE_constraint = 16, RULE_tableConstraint = 17, 
		RULE_excludeElementList = 18, RULE_excludeElement = 19, RULE_onAction = 20, 
		RULE_action = 21, RULE_triggerStatement = 22, RULE_triggerTiming = 23, 
		RULE_triggerEvent = 24, RULE_dataManipulationStatement = 25, RULE_insertStatement = 26, 
		RULE_updateStatement = 27, RULE_deleteStatement = 28, RULE_condition = 29, 
		RULE_booleanCondition = 30, RULE_predicate = 31, RULE_subquery = 32, RULE_comparisonOperator = 33, 
		RULE_sqlScript = 34, RULE_createSchemaStatement = 35, RULE_createViewStatement = 36, 
		RULE_createFunctionStatement = 37, RULE_parameter = 38, RULE_selectStatement = 39, 
		RULE_selectList = 40, RULE_tableReference = 41, RULE_whereClause = 42, 
		RULE_orderByClause = 43, RULE_limitClause = 44, RULE_columnNameList = 45, 
		RULE_conflictAction = 46, RULE_schemaName = 47, RULE_tableName = 48, RULE_columnName = 49, 
		RULE_relationshipType = 50, RULE_decimalType = 51, RULE_partitionClause = 52, 
		RULE_partitionStrategy = 53, RULE_partitionValuesClause = 54, RULE_createIndexStatement = 55, 
		RULE_indexElement = 56, RULE_indexOptionsClause = 57, RULE_expression = 58, 
		RULE_valueAtom = 59, RULE_typeCast = 60, RULE_valueList = 61, RULE_value = 62, 
		RULE_expressionList = 63, RULE_arrayConstructor = 64, RULE_createPolicyStatement = 65, 
		RULE_plpgsqlBlock = 66, RULE_statement = 67, RULE_createRuleStatement = 68, 
		RULE_ruleEvent = 69, RULE_ruleAction = 70, RULE_sqlAction = 71, RULE_numericLiteral = 72, 
		RULE_booleanLiteral = 73, RULE_anyExpression = 74, RULE_intervalLiteral = 75, 
		RULE_typeName = 76;
	private static String[] makeRuleNames() {
		return new String[] {
			"identityColumn", "createTableStatement", "columnDef", "columnAttribute", 
			"columnTypeModifier", "generatedColumn", "generatedColumnBody", "identityOptions", 
			"identityOption", "collateClause", "alterTableStatement", "alterAction", 
			"alterColumnAction", "dataType", "functionCall", "onUpdateClause", "constraint", 
			"tableConstraint", "excludeElementList", "excludeElement", "onAction", 
			"action", "triggerStatement", "triggerTiming", "triggerEvent", "dataManipulationStatement", 
			"insertStatement", "updateStatement", "deleteStatement", "condition", 
			"booleanCondition", "predicate", "subquery", "comparisonOperator", "sqlScript", 
			"createSchemaStatement", "createViewStatement", "createFunctionStatement", 
			"parameter", "selectStatement", "selectList", "tableReference", "whereClause", 
			"orderByClause", "limitClause", "columnNameList", "conflictAction", "schemaName", 
			"tableName", "columnName", "relationshipType", "decimalType", "partitionClause", 
			"partitionStrategy", "partitionValuesClause", "createIndexStatement", 
			"indexElement", "indexOptionsClause", "expression", "valueAtom", "typeCast", 
			"valueList", "value", "expressionList", "arrayConstructor", "createPolicyStatement", 
			"plpgsqlBlock", "statement", "createRuleStatement", "ruleEvent", "ruleAction", 
			"sqlAction", "numericLiteral", "booleanLiteral", "anyExpression", "intervalLiteral", 
			"typeName"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'GENERATED'", "'ALWAYS'", "'BY'", "'IDENTITY'", "'PARTITION OF'", 
			"'BY DEFAULT'", "'STORED'", "'INCREMENT'", "'MINVALUE'", "'MAXVALUE'", 
			"'START'", "'CACHE'", "'CYCLE'", "'NO'", "'COLLATE'", "'ALTER'", "'ADD'", 
			"'COLUMN'", "'DROP'", "'RENAME COLUMN'", "'RENAME TO'", "'SCHEMA'", "'TABLESPACE'", 
			"'ENABLE'", "'REPLICA'", "'DISABLE'", "'CLUSTER'", "'SET WITHOUT CLUSTER'", 
			"'OWNER TO'", "'DROP DEFAULT'", "'SET NOT NULL'", "'DROP NOT NULL'", 
			"'SET STATISTICS'", "'SET STORAGE'", "'PLAIN'", "'EXTERNAL'", "'EXTENDED'", 
			"'MAIN'", "'SET COMPRESSION'", "'RESET'", "'STORAGE'", "'STATISTICS'", 
			"'COMPRESSION'", "'TYPE'", "'WITHOUT'", "'TIME'", "'ZONE'", "'WITH'", 
			"'CURRENT_TIMESTAMP'", "'ON CONFLICT'", "'WHERE'", "'AUTO_INCREMENT'", 
			"'DEFERRABLE'", "'INITIALLY'", "'DEFERRED'", "'IMMEDIATE'", "'FOR EACH ROW'", 
			"'EXECUTE FUNCTION'", "'INSTEAD OF'", "'OR'", "'INSERT INTO'", "'VALUES'", 
			"'DELETE FROM'", "'<>'", "'!='", "'<'", "'<='", "'>'", "'>='", "'@>'", 
			"'<@'", "'&&'", "'?'", "'?|'", "'?&'", "'CREATE SCHEMA'", "'CREATE VIEW'", 
			"'CREATE FUNCTION'", "'RETURNS'", "'LANGUAGE'", "'FROM'", "'*'", "'ORDER BY'", 
			"'ASC'", "'DESC'", "'LIMIT'", "'DO NOTHING'", "'DO UPDATE'", "'ONETOONE'", 
			"'MANYTOONE'", "'ONETOMANY'", "'DECIMAL'", "'NUMERIC'", "'PARTITION BY'", 
			"'RANGE'", "'LIST'", "'HASH'", "'FOR VALUES'", "'MODULUS'", "'REMAINDER'", 
			"'INDEX'", "'now'", "'infinity'", "'NaN'", "'POLICY'", "'ALL'", "'WITH CHECK'", 
			"'BEGIN'", "'END'", "'RETURN'", "'INSTEAD NOTHING'", "'-'", "'TRUE'", 
			"'FALSE'", "'true'", "'false'", "'INTERVAL'", "'('", "')'", "'{'", "'}'", 
			"';'", "':'", "'::'", "','", "'\"'", "'ON'", "'DELETE'", "'RULE'", "'DO'", 
			"'AS'", "'TO'", "'INSTEAD'", "'NOTHING'", "'FOR'", "'SELECT'", "'USING'", 
			"'$$'", "'NEW'", "'TABLE'", "'CREATE'", "'RELATIONSHIP'", null, null, 
			null, "'.'", "'CONSTRAINT'", "'NULL'", "'NOT'", "'IS'", "'SET'", "'REFERENCES'", 
			"'SERIAL'", "'PRIMARY KEY'", "'CHECK'", "'ANY'", "'['", "']'", "'ARRAY'", 
			"'DEFAULT'", "'nextval'", "'regclass'", "'CASCADE'", "'SET NULL'", "'SET DEFAULT'", 
			"'NO ACTION'", "'RESTRICT'", "'ON DELETE'", "'ON UPDATE'", "'UNIQUE'", 
			"'='", null, null, null, "'FOREIGN KEY'", "'TRIGGER'", "'BEFORE'", "'AFTER'", 
			"'UPDATE'", "'INSERT'", "'EACH'", "'ROW'", "'EXECUTE'", "'FUNCTION'", 
			"'IN'", "'BETWEEN'", "'AND'", "'LIKE'", "'ILIKE'", "'PRIMARY'", "'KEY'", 
			"'FOREIGN'", "'EXCLUDE'", null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, "'MANYTOMANY'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, "LPAREN", 
			"RPAREN", "LBRACE", "RBRACE", "SEMICOLON", "COLON", "DOUBLE_COLON", "COMMA", 
			"DOUBLE_QUOTE", "ON", "DELETE", "RULE", "DO", "AS", "TO", "INSTEAD", 
			"NOTHING", "FOR", "SELECT", "USING", "DOLLAR_QUOTE", "NEW", "TABLE", 
			"CREATE", "RELATIONSHIP", "DECIMAL", "INT", "VARCHAR", "DOT", "CONSTRAINT", 
			"NULL", "NOT", "IS", "SET", "REFERENCES", "SERIAL", "PRIMARY_KEY", "CHECK", 
			"ANY", "LBRACKET", "RBRACKET", "ARRAY", "DEFAULT", "NEXTVAL", "REGCLASS", 
			"CASCADE", "SET_NULL", "SET_DEFAULT", "NO_ACTION", "RESTRICT", "ON_DELETE", 
			"ON_UPDATE", "UNIQUE", "EQUALS", "TEXT", "INTEGER", "BOOLEAN", "FOREIGN_KEY", 
			"TRIGGER", "BEFORE", "AFTER", "UPDATE", "INSERT", "EACH", "ROW", "EXECUTE", 
			"FUNCTION", "IN", "BETWEEN", "AND", "LIKE", "ILIKE", "PRIMARY", "KEY", 
			"FOREIGN", "EXCLUDE", "BIGSERIAL", "JSONB", "TIMESTAMP", "INDEX", "NOW", 
			"FLOAT8", "FLOAT4", "INT8", "SMALLINT", "BIGINT", "SMALLSERIAL", "REAL", 
			"DOUBLE", "PRECISION", "NUMERIC", "MONEY", "CHAR", "CHARACTER", "JSON", 
			"DATE", "TIME", "INTERVAL", "UUID", "BYTEA", "ENUM", "CITEXT", "TSVECTOR", 
			"INET", "CIDR", "MACADDR", "XML", "PG_LSN", "BIT", "VARBIT", "VARYING", 
			"BPCHAR", "WITHOUT", "WITH", "ZONE", "MANYTOMANY", "IDENTIFIER", "STRING", 
			"NUMBER", "DIGIT", "WS"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "PostgreSQL.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public PostgreSQLParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@SuppressWarnings("CheckReturnValue")
	public static class IdentityColumnContext extends ParserRuleContext {
		public TerminalNode AS() { return getToken(PostgreSQLParser.AS, 0); }
		public TerminalNode DEFAULT() { return getToken(PostgreSQLParser.DEFAULT, 0); }
		public TerminalNode LPAREN() { return getToken(PostgreSQLParser.LPAREN, 0); }
		public TerminalNode RPAREN() { return getToken(PostgreSQLParser.RPAREN, 0); }
		public IdentityColumnContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_identityColumn; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PostgreSQLListener ) ((PostgreSQLListener)listener).enterIdentityColumn(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PostgreSQLListener ) ((PostgreSQLListener)listener).exitIdentityColumn(this);
		}
	}

	public final IdentityColumnContext identityColumn() throws RecognitionException {
		IdentityColumnContext _localctx = new IdentityColumnContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_identityColumn);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(154);
			match(T__0);
			setState(158);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__1:
				{
				setState(155);
				match(T__1);
				}
				break;
			case T__2:
				{
				setState(156);
				match(T__2);
				setState(157);
				match(DEFAULT);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(160);
			match(AS);
			setState(161);
			match(T__3);
			setState(170);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==LPAREN) {
				{
				setState(162);
				match(LPAREN);
				setState(166);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,1,_ctx);
				while ( _alt!=1 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1+1 ) {
						{
						{
						setState(163);
						matchWildcard();
						}
						} 
					}
					setState(168);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,1,_ctx);
				}
				setState(169);
				match(RPAREN);
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class CreateTableStatementContext extends ParserRuleContext {
		public TerminalNode CREATE() { return getToken(PostgreSQLParser.CREATE, 0); }
		public TerminalNode TABLE() { return getToken(PostgreSQLParser.TABLE, 0); }
		public List<TableNameContext> tableName() {
			return getRuleContexts(TableNameContext.class);
		}
		public TableNameContext tableName(int i) {
			return getRuleContext(TableNameContext.class,i);
		}
		public TerminalNode LPAREN() { return getToken(PostgreSQLParser.LPAREN, 0); }
		public TerminalNode RPAREN() { return getToken(PostgreSQLParser.RPAREN, 0); }
		public TerminalNode SEMICOLON() { return getToken(PostgreSQLParser.SEMICOLON, 0); }
		public List<TableConstraintContext> tableConstraint() {
			return getRuleContexts(TableConstraintContext.class);
		}
		public TableConstraintContext tableConstraint(int i) {
			return getRuleContext(TableConstraintContext.class,i);
		}
		public List<ColumnDefContext> columnDef() {
			return getRuleContexts(ColumnDefContext.class);
		}
		public ColumnDefContext columnDef(int i) {
			return getRuleContext(ColumnDefContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(PostgreSQLParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(PostgreSQLParser.COMMA, i);
		}
		public PartitionClauseContext partitionClause() {
			return getRuleContext(PartitionClauseContext.class,0);
		}
		public PartitionValuesClauseContext partitionValuesClause() {
			return getRuleContext(PartitionValuesClauseContext.class,0);
		}
		public CreateTableStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_createTableStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PostgreSQLListener ) ((PostgreSQLListener)listener).enterCreateTableStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PostgreSQLListener ) ((PostgreSQLListener)listener).exitCreateTableStatement(this);
		}
	}

	public final CreateTableStatementContext createTableStatement() throws RecognitionException {
		CreateTableStatementContext _localctx = new CreateTableStatementContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_createTableStatement);
		int _la;
		try {
			setState(204);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,7,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(172);
				match(CREATE);
				setState(173);
				match(TABLE);
				setState(174);
				tableName();
				setState(175);
				match(LPAREN);
				setState(178);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case CONSTRAINT:
				case PRIMARY_KEY:
				case FOREIGN_KEY:
				case EXCLUDE:
					{
					setState(176);
					tableConstraint();
					}
					break;
				case IDENTIFIER:
					{
					setState(177);
					columnDef();
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(187);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(180);
					match(COMMA);
					setState(183);
					_errHandler.sync(this);
					switch (_input.LA(1)) {
					case CONSTRAINT:
					case PRIMARY_KEY:
					case FOREIGN_KEY:
					case EXCLUDE:
						{
						setState(181);
						tableConstraint();
						}
						break;
					case IDENTIFIER:
						{
						setState(182);
						columnDef();
						}
						break;
					default:
						throw new NoViableAltException(this);
					}
					}
					}
					setState(189);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(190);
				match(RPAREN);
				setState(192);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__93) {
					{
					setState(191);
					partitionClause();
					}
				}

				setState(194);
				match(SEMICOLON);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(196);
				match(CREATE);
				setState(197);
				match(TABLE);
				setState(198);
				tableName();
				setState(199);
				match(T__4);
				setState(200);
				tableName();
				setState(201);
				partitionValuesClause();
				setState(202);
				match(SEMICOLON);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ColumnDefContext extends ParserRuleContext {
		public ColumnNameContext columnName() {
			return getRuleContext(ColumnNameContext.class,0);
		}
		public DataTypeContext dataType() {
			return getRuleContext(DataTypeContext.class,0);
		}
		public List<ColumnAttributeContext> columnAttribute() {
			return getRuleContexts(ColumnAttributeContext.class);
		}
		public ColumnAttributeContext columnAttribute(int i) {
			return getRuleContext(ColumnAttributeContext.class,i);
		}
		public ColumnDefContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_columnDef; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PostgreSQLListener ) ((PostgreSQLListener)listener).enterColumnDef(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PostgreSQLListener ) ((PostgreSQLListener)listener).exitColumnDef(this);
		}
	}

	public final ColumnDefContext columnDef() throws RecognitionException {
		ColumnDefContext _localctx = new ColumnDefContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_columnDef);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(206);
			columnName();
			setState(207);
			dataType();
			setState(211);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 4503599627403266L) != 0) || ((((_la - 148)) & ~0x3f) == 0 && ((1L << (_la - 148)) & 57174610940051L) != 0) || _la==MANYTOMANY) {
				{
				{
				setState(208);
				columnAttribute();
				}
				}
				setState(213);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ColumnAttributeContext extends ParserRuleContext {
		public ConstraintContext constraint() {
			return getRuleContext(ConstraintContext.class,0);
		}
		public GeneratedColumnContext generatedColumn() {
			return getRuleContext(GeneratedColumnContext.class,0);
		}
		public IdentityColumnContext identityColumn() {
			return getRuleContext(IdentityColumnContext.class,0);
		}
		public CollateClauseContext collateClause() {
			return getRuleContext(CollateClauseContext.class,0);
		}
		public OnUpdateClauseContext onUpdateClause() {
			return getRuleContext(OnUpdateClauseContext.class,0);
		}
		public ColumnAttributeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_columnAttribute; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PostgreSQLListener ) ((PostgreSQLListener)listener).enterColumnAttribute(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PostgreSQLListener ) ((PostgreSQLListener)listener).exitColumnAttribute(this);
		}
	}

	public final ColumnAttributeContext columnAttribute() throws RecognitionException {
		ColumnAttributeContext _localctx = new ColumnAttributeContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_columnAttribute);
		try {
			setState(219);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,9,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(214);
				constraint();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(215);
				generatedColumn();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(216);
				identityColumn();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(217);
				collateClause();
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(218);
				onUpdateClause();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ColumnTypeModifierContext extends ParserRuleContext {
		public TerminalNode LPAREN() { return getToken(PostgreSQLParser.LPAREN, 0); }
		public List<TerminalNode> NUMBER() { return getTokens(PostgreSQLParser.NUMBER); }
		public TerminalNode NUMBER(int i) {
			return getToken(PostgreSQLParser.NUMBER, i);
		}
		public TerminalNode RPAREN() { return getToken(PostgreSQLParser.RPAREN, 0); }
		public TerminalNode COMMA() { return getToken(PostgreSQLParser.COMMA, 0); }
		public ColumnTypeModifierContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_columnTypeModifier; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PostgreSQLListener ) ((PostgreSQLListener)listener).enterColumnTypeModifier(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PostgreSQLListener ) ((PostgreSQLListener)listener).exitColumnTypeModifier(this);
		}
	}

	public final ColumnTypeModifierContext columnTypeModifier() throws RecognitionException {
		ColumnTypeModifierContext _localctx = new ColumnTypeModifierContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_columnTypeModifier);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(221);
			match(LPAREN);
			setState(222);
			match(NUMBER);
			setState(225);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==COMMA) {
				{
				setState(223);
				match(COMMA);
				setState(224);
				match(NUMBER);
				}
			}

			setState(227);
			match(RPAREN);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class GeneratedColumnContext extends ParserRuleContext {
		public TerminalNode AS() { return getToken(PostgreSQLParser.AS, 0); }
		public GeneratedColumnBodyContext generatedColumnBody() {
			return getRuleContext(GeneratedColumnBodyContext.class,0);
		}
		public GeneratedColumnContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_generatedColumn; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PostgreSQLListener ) ((PostgreSQLListener)listener).enterGeneratedColumn(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PostgreSQLListener ) ((PostgreSQLListener)listener).exitGeneratedColumn(this);
		}
	}

	public final GeneratedColumnContext generatedColumn() throws RecognitionException {
		GeneratedColumnContext _localctx = new GeneratedColumnContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_generatedColumn);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(229);
			match(T__0);
			setState(230);
			_la = _input.LA(1);
			if ( !(_la==T__1 || _la==T__5) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			setState(231);
			match(AS);
			setState(232);
			generatedColumnBody();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class GeneratedColumnBodyContext extends ParserRuleContext {
		public TerminalNode LPAREN() { return getToken(PostgreSQLParser.LPAREN, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode RPAREN() { return getToken(PostgreSQLParser.RPAREN, 0); }
		public IdentityOptionsContext identityOptions() {
			return getRuleContext(IdentityOptionsContext.class,0);
		}
		public GeneratedColumnBodyContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_generatedColumnBody; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PostgreSQLListener ) ((PostgreSQLListener)listener).enterGeneratedColumnBody(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PostgreSQLListener ) ((PostgreSQLListener)listener).exitGeneratedColumnBody(this);
		}
	}

	public final GeneratedColumnBodyContext generatedColumnBody() throws RecognitionException {
		GeneratedColumnBodyContext _localctx = new GeneratedColumnBodyContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_generatedColumnBody);
		int _la;
		try {
			setState(243);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case LPAREN:
				enterOuterAlt(_localctx, 1);
				{
				setState(234);
				match(LPAREN);
				setState(235);
				expression();
				setState(236);
				match(RPAREN);
				setState(237);
				match(T__6);
				}
				break;
			case T__3:
				enterOuterAlt(_localctx, 2);
				{
				setState(239);
				match(T__3);
				setState(241);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==LPAREN) {
					{
					setState(240);
					identityOptions();
					}
				}

				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class IdentityOptionsContext extends ParserRuleContext {
		public TerminalNode LPAREN() { return getToken(PostgreSQLParser.LPAREN, 0); }
		public TerminalNode RPAREN() { return getToken(PostgreSQLParser.RPAREN, 0); }
		public List<IdentityOptionContext> identityOption() {
			return getRuleContexts(IdentityOptionContext.class);
		}
		public IdentityOptionContext identityOption(int i) {
			return getRuleContext(IdentityOptionContext.class,i);
		}
		public IdentityOptionsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_identityOptions; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PostgreSQLListener ) ((PostgreSQLListener)listener).enterIdentityOptions(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PostgreSQLListener ) ((PostgreSQLListener)listener).exitIdentityOptions(this);
		}
	}

	public final IdentityOptionsContext identityOptions() throws RecognitionException {
		IdentityOptionsContext _localctx = new IdentityOptionsContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_identityOptions);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(245);
			match(LPAREN);
			setState(249);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 32512L) != 0)) {
				{
				{
				setState(246);
				identityOption();
				}
				}
				setState(251);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(252);
			match(RPAREN);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class IdentityOptionContext extends ParserRuleContext {
		public NumericLiteralContext numericLiteral() {
			return getRuleContext(NumericLiteralContext.class,0);
		}
		public IdentityOptionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_identityOption; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PostgreSQLListener ) ((PostgreSQLListener)listener).enterIdentityOption(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PostgreSQLListener ) ((PostgreSQLListener)listener).exitIdentityOption(this);
		}
	}

	public final IdentityOptionContext identityOption() throws RecognitionException {
		IdentityOptionContext _localctx = new IdentityOptionContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_identityOption);
		try {
			setState(268);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__7:
				enterOuterAlt(_localctx, 1);
				{
				setState(254);
				match(T__7);
				setState(255);
				match(T__2);
				setState(256);
				numericLiteral();
				}
				break;
			case T__8:
				enterOuterAlt(_localctx, 2);
				{
				setState(257);
				match(T__8);
				setState(258);
				numericLiteral();
				}
				break;
			case T__9:
				enterOuterAlt(_localctx, 3);
				{
				setState(259);
				match(T__9);
				setState(260);
				numericLiteral();
				}
				break;
			case T__10:
				enterOuterAlt(_localctx, 4);
				{
				setState(261);
				match(T__10);
				setState(262);
				numericLiteral();
				}
				break;
			case T__11:
				enterOuterAlt(_localctx, 5);
				{
				setState(263);
				match(T__11);
				setState(264);
				numericLiteral();
				}
				break;
			case T__12:
				enterOuterAlt(_localctx, 6);
				{
				setState(265);
				match(T__12);
				}
				break;
			case T__13:
				enterOuterAlt(_localctx, 7);
				{
				setState(266);
				match(T__13);
				setState(267);
				match(T__12);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class CollateClauseContext extends ParserRuleContext {
		public TerminalNode STRING() { return getToken(PostgreSQLParser.STRING, 0); }
		public CollateClauseContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_collateClause; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PostgreSQLListener ) ((PostgreSQLListener)listener).enterCollateClause(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PostgreSQLListener ) ((PostgreSQLListener)listener).exitCollateClause(this);
		}
	}

	public final CollateClauseContext collateClause() throws RecognitionException {
		CollateClauseContext _localctx = new CollateClauseContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_collateClause);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(270);
			match(T__14);
			setState(271);
			match(STRING);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class AlterTableStatementContext extends ParserRuleContext {
		public TerminalNode TABLE() { return getToken(PostgreSQLParser.TABLE, 0); }
		public TableNameContext tableName() {
			return getRuleContext(TableNameContext.class,0);
		}
		public List<AlterActionContext> alterAction() {
			return getRuleContexts(AlterActionContext.class);
		}
		public AlterActionContext alterAction(int i) {
			return getRuleContext(AlterActionContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(PostgreSQLParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(PostgreSQLParser.COMMA, i);
		}
		public TerminalNode SEMICOLON() { return getToken(PostgreSQLParser.SEMICOLON, 0); }
		public AlterTableStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_alterTableStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PostgreSQLListener ) ((PostgreSQLListener)listener).enterAlterTableStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PostgreSQLListener ) ((PostgreSQLListener)listener).exitAlterTableStatement(this);
		}
	}

	public final AlterTableStatementContext alterTableStatement() throws RecognitionException {
		AlterTableStatementContext _localctx = new AlterTableStatementContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_alterTableStatement);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(273);
			match(T__15);
			setState(274);
			match(TABLE);
			setState(275);
			tableName();
			setState(276);
			alterAction();
			setState(281);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(277);
				match(COMMA);
				setState(278);
				alterAction();
				}
				}
				setState(283);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(285);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==SEMICOLON) {
				{
				setState(284);
				match(SEMICOLON);
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class AlterActionContext extends ParserRuleContext {
		public ColumnDefContext columnDef() {
			return getRuleContext(ColumnDefContext.class,0);
		}
		public List<ColumnNameContext> columnName() {
			return getRuleContexts(ColumnNameContext.class);
		}
		public ColumnNameContext columnName(int i) {
			return getRuleContext(ColumnNameContext.class,i);
		}
		public TerminalNode FOREIGN_KEY() { return getToken(PostgreSQLParser.FOREIGN_KEY, 0); }
		public List<TerminalNode> LPAREN() { return getTokens(PostgreSQLParser.LPAREN); }
		public TerminalNode LPAREN(int i) {
			return getToken(PostgreSQLParser.LPAREN, i);
		}
		public List<ColumnNameListContext> columnNameList() {
			return getRuleContexts(ColumnNameListContext.class);
		}
		public ColumnNameListContext columnNameList(int i) {
			return getRuleContext(ColumnNameListContext.class,i);
		}
		public List<TerminalNode> RPAREN() { return getTokens(PostgreSQLParser.RPAREN); }
		public TerminalNode RPAREN(int i) {
			return getToken(PostgreSQLParser.RPAREN, i);
		}
		public TerminalNode REFERENCES() { return getToken(PostgreSQLParser.REFERENCES, 0); }
		public TableNameContext tableName() {
			return getRuleContext(TableNameContext.class,0);
		}
		public TerminalNode CONSTRAINT() { return getToken(PostgreSQLParser.CONSTRAINT, 0); }
		public TerminalNode IDENTIFIER() { return getToken(PostgreSQLParser.IDENTIFIER, 0); }
		public List<OnActionContext> onAction() {
			return getRuleContexts(OnActionContext.class);
		}
		public OnActionContext onAction(int i) {
			return getRuleContext(OnActionContext.class,i);
		}
		public AlterColumnActionContext alterColumnAction() {
			return getRuleContext(AlterColumnActionContext.class,0);
		}
		public TerminalNode TO() { return getToken(PostgreSQLParser.TO, 0); }
		public TerminalNode SET() { return getToken(PostgreSQLParser.SET, 0); }
		public SchemaNameContext schemaName() {
			return getRuleContext(SchemaNameContext.class,0);
		}
		public TerminalNode TRIGGER() { return getToken(PostgreSQLParser.TRIGGER, 0); }
		public TerminalNode ON() { return getToken(PostgreSQLParser.ON, 0); }
		public AlterActionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_alterAction; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PostgreSQLListener ) ((PostgreSQLListener)listener).enterAlterAction(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PostgreSQLListener ) ((PostgreSQLListener)listener).exitAlterAction(this);
		}
	}

	public final AlterActionContext alterAction() throws RecognitionException {
		AlterActionContext _localctx = new AlterActionContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_alterAction);
		int _la;
		try {
			setState(352);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,21,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(287);
				match(T__16);
				setState(288);
				match(T__17);
				setState(289);
				columnDef();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(290);
				match(T__18);
				setState(291);
				match(T__17);
				setState(292);
				columnName();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(293);
				match(T__16);
				setState(296);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==CONSTRAINT) {
					{
					setState(294);
					match(CONSTRAINT);
					setState(295);
					match(IDENTIFIER);
					}
				}

				setState(298);
				match(FOREIGN_KEY);
				setState(299);
				match(LPAREN);
				setState(300);
				columnNameList();
				setState(301);
				match(RPAREN);
				setState(302);
				match(REFERENCES);
				setState(303);
				tableName();
				setState(308);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==LPAREN) {
					{
					setState(304);
					match(LPAREN);
					setState(305);
					columnNameList();
					setState(306);
					match(RPAREN);
					}
				}

				setState(313);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__52 || _la==ON_DELETE || _la==ON_UPDATE) {
					{
					{
					setState(310);
					onAction();
					}
					}
					setState(315);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(316);
				match(T__18);
				setState(317);
				match(CONSTRAINT);
				setState(318);
				match(IDENTIFIER);
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(319);
				match(T__15);
				setState(320);
				match(T__17);
				setState(321);
				columnName();
				setState(322);
				alterColumnAction();
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(324);
				match(T__19);
				setState(325);
				columnName();
				setState(326);
				match(TO);
				setState(327);
				columnName();
				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(329);
				match(T__20);
				setState(330);
				tableName();
				}
				break;
			case 8:
				enterOuterAlt(_localctx, 8);
				{
				setState(331);
				match(SET);
				setState(336);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case T__21:
					{
					setState(332);
					match(T__21);
					setState(333);
					schemaName();
					}
					break;
				case T__22:
					{
					setState(334);
					match(T__22);
					setState(335);
					match(IDENTIFIER);
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				break;
			case 9:
				enterOuterAlt(_localctx, 9);
				{
				setState(338);
				match(T__23);
				setState(339);
				match(T__24);
				setState(340);
				match(TRIGGER);
				setState(341);
				match(IDENTIFIER);
				}
				break;
			case 10:
				enterOuterAlt(_localctx, 10);
				{
				setState(342);
				match(T__25);
				setState(343);
				match(T__24);
				setState(344);
				match(TRIGGER);
				setState(345);
				match(IDENTIFIER);
				}
				break;
			case 11:
				enterOuterAlt(_localctx, 11);
				{
				setState(346);
				match(T__26);
				setState(347);
				match(ON);
				setState(348);
				match(IDENTIFIER);
				}
				break;
			case 12:
				enterOuterAlt(_localctx, 12);
				{
				setState(349);
				match(T__27);
				}
				break;
			case 13:
				enterOuterAlt(_localctx, 13);
				{
				setState(350);
				match(T__28);
				setState(351);
				match(IDENTIFIER);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class AlterColumnActionContext extends ParserRuleContext {
		public List<TerminalNode> SET_DEFAULT() { return getTokens(PostgreSQLParser.SET_DEFAULT); }
		public TerminalNode SET_DEFAULT(int i) {
			return getToken(PostgreSQLParser.SET_DEFAULT, i);
		}
		public List<ValueContext> value() {
			return getRuleContexts(ValueContext.class);
		}
		public ValueContext value(int i) {
			return getRuleContext(ValueContext.class,i);
		}
		public List<TerminalNode> NUMBER() { return getTokens(PostgreSQLParser.NUMBER); }
		public TerminalNode NUMBER(int i) {
			return getToken(PostgreSQLParser.NUMBER, i);
		}
		public List<TerminalNode> IDENTIFIER() { return getTokens(PostgreSQLParser.IDENTIFIER); }
		public TerminalNode IDENTIFIER(int i) {
			return getToken(PostgreSQLParser.IDENTIFIER, i);
		}
		public List<DataTypeContext> dataType() {
			return getRuleContexts(DataTypeContext.class);
		}
		public DataTypeContext dataType(int i) {
			return getRuleContext(DataTypeContext.class,i);
		}
		public AlterColumnActionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_alterColumnAction; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PostgreSQLListener ) ((PostgreSQLListener)listener).enterAlterColumnAction(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PostgreSQLListener ) ((PostgreSQLListener)listener).exitAlterColumnAction(this);
		}
	}

	public final AlterColumnActionContext alterColumnAction() throws RecognitionException {
		AlterColumnActionContext _localctx = new AlterColumnActionContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_alterColumnAction);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(369); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				setState(369);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case SET_DEFAULT:
					{
					setState(354);
					match(SET_DEFAULT);
					setState(355);
					value();
					}
					break;
				case T__29:
					{
					setState(356);
					match(T__29);
					}
					break;
				case T__30:
					{
					setState(357);
					match(T__30);
					}
					break;
				case T__31:
					{
					setState(358);
					match(T__31);
					}
					break;
				case T__32:
					{
					setState(359);
					match(T__32);
					setState(360);
					match(NUMBER);
					}
					break;
				case T__33:
					{
					setState(361);
					match(T__33);
					setState(362);
					_la = _input.LA(1);
					if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 515396075520L) != 0)) ) {
					_errHandler.recoverInline(this);
					}
					else {
						if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
						_errHandler.reportMatch(this);
						consume();
					}
					}
					break;
				case T__38:
					{
					setState(363);
					match(T__38);
					setState(364);
					match(IDENTIFIER);
					}
					break;
				case T__39:
					{
					setState(365);
					match(T__39);
					setState(366);
					_la = _input.LA(1);
					if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 15393162788864L) != 0)) ) {
					_errHandler.recoverInline(this);
					}
					else {
						if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
						_errHandler.reportMatch(this);
						consume();
					}
					}
					break;
				case T__43:
					{
					setState(367);
					match(T__43);
					setState(368);
					dataType();
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				setState(371); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & 19274739482624L) != 0) || _la==SET_DEFAULT );
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class DataTypeContext extends ParserRuleContext {
		public TerminalNode INTEGER() { return getToken(PostgreSQLParser.INTEGER, 0); }
		public TerminalNode FLOAT8() { return getToken(PostgreSQLParser.FLOAT8, 0); }
		public TerminalNode FLOAT4() { return getToken(PostgreSQLParser.FLOAT4, 0); }
		public TerminalNode INT8() { return getToken(PostgreSQLParser.INT8, 0); }
		public TerminalNode INT() { return getToken(PostgreSQLParser.INT, 0); }
		public TerminalNode SMALLINT() { return getToken(PostgreSQLParser.SMALLINT, 0); }
		public TerminalNode BIGINT() { return getToken(PostgreSQLParser.BIGINT, 0); }
		public TerminalNode BIGSERIAL() { return getToken(PostgreSQLParser.BIGSERIAL, 0); }
		public TerminalNode SERIAL() { return getToken(PostgreSQLParser.SERIAL, 0); }
		public TerminalNode SMALLSERIAL() { return getToken(PostgreSQLParser.SMALLSERIAL, 0); }
		public TerminalNode REAL() { return getToken(PostgreSQLParser.REAL, 0); }
		public TerminalNode DOUBLE() { return getToken(PostgreSQLParser.DOUBLE, 0); }
		public TerminalNode PRECISION() { return getToken(PostgreSQLParser.PRECISION, 0); }
		public TerminalNode NUMERIC() { return getToken(PostgreSQLParser.NUMERIC, 0); }
		public TerminalNode LPAREN() { return getToken(PostgreSQLParser.LPAREN, 0); }
		public List<TerminalNode> NUMBER() { return getTokens(PostgreSQLParser.NUMBER); }
		public TerminalNode NUMBER(int i) {
			return getToken(PostgreSQLParser.NUMBER, i);
		}
		public TerminalNode RPAREN() { return getToken(PostgreSQLParser.RPAREN, 0); }
		public List<TerminalNode> COMMA() { return getTokens(PostgreSQLParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(PostgreSQLParser.COMMA, i);
		}
		public DecimalTypeContext decimalType() {
			return getRuleContext(DecimalTypeContext.class,0);
		}
		public TerminalNode MONEY() { return getToken(PostgreSQLParser.MONEY, 0); }
		public TerminalNode CHAR() { return getToken(PostgreSQLParser.CHAR, 0); }
		public TerminalNode VARCHAR() { return getToken(PostgreSQLParser.VARCHAR, 0); }
		public TerminalNode CHARACTER() { return getToken(PostgreSQLParser.CHARACTER, 0); }
		public TerminalNode VARYING() { return getToken(PostgreSQLParser.VARYING, 0); }
		public TerminalNode TEXT() { return getToken(PostgreSQLParser.TEXT, 0); }
		public TerminalNode BOOLEAN() { return getToken(PostgreSQLParser.BOOLEAN, 0); }
		public TerminalNode BPCHAR() { return getToken(PostgreSQLParser.BPCHAR, 0); }
		public TerminalNode JSON() { return getToken(PostgreSQLParser.JSON, 0); }
		public TerminalNode JSONB() { return getToken(PostgreSQLParser.JSONB, 0); }
		public TerminalNode DATE() { return getToken(PostgreSQLParser.DATE, 0); }
		public TerminalNode TIME() { return getToken(PostgreSQLParser.TIME, 0); }
		public TerminalNode TIMESTAMP() { return getToken(PostgreSQLParser.TIMESTAMP, 0); }
		public TerminalNode INTERVAL() { return getToken(PostgreSQLParser.INTERVAL, 0); }
		public TerminalNode UUID() { return getToken(PostgreSQLParser.UUID, 0); }
		public TerminalNode ARRAY() { return getToken(PostgreSQLParser.ARRAY, 0); }
		public TerminalNode BYTEA() { return getToken(PostgreSQLParser.BYTEA, 0); }
		public TerminalNode ENUM() { return getToken(PostgreSQLParser.ENUM, 0); }
		public List<TerminalNode> STRING() { return getTokens(PostgreSQLParser.STRING); }
		public TerminalNode STRING(int i) {
			return getToken(PostgreSQLParser.STRING, i);
		}
		public TerminalNode CITEXT() { return getToken(PostgreSQLParser.CITEXT, 0); }
		public TerminalNode TSVECTOR() { return getToken(PostgreSQLParser.TSVECTOR, 0); }
		public TerminalNode INET() { return getToken(PostgreSQLParser.INET, 0); }
		public TerminalNode CIDR() { return getToken(PostgreSQLParser.CIDR, 0); }
		public TerminalNode MACADDR() { return getToken(PostgreSQLParser.MACADDR, 0); }
		public TerminalNode XML() { return getToken(PostgreSQLParser.XML, 0); }
		public TerminalNode PG_LSN() { return getToken(PostgreSQLParser.PG_LSN, 0); }
		public TerminalNode BIT() { return getToken(PostgreSQLParser.BIT, 0); }
		public TerminalNode VARBIT() { return getToken(PostgreSQLParser.VARBIT, 0); }
		public TerminalNode TRIGGER() { return getToken(PostgreSQLParser.TRIGGER, 0); }
		public List<TerminalNode> IDENTIFIER() { return getTokens(PostgreSQLParser.IDENTIFIER); }
		public TerminalNode IDENTIFIER(int i) {
			return getToken(PostgreSQLParser.IDENTIFIER, i);
		}
		public DataTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_dataType; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PostgreSQLListener ) ((PostgreSQLListener)listener).enterDataType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PostgreSQLListener ) ((PostgreSQLListener)listener).exitDataType(this);
		}
	}

	public final DataTypeContext dataType() throws RecognitionException {
		DataTypeContext _localctx = new DataTypeContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_dataType);
		int _la;
		try {
			setState(494);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,37,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(373);
				match(INTEGER);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(374);
				match(FLOAT8);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(375);
				match(FLOAT4);
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(376);
				match(INT8);
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(377);
				match(INT);
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(378);
				match(SMALLINT);
				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(379);
				match(BIGINT);
				}
				break;
			case 8:
				enterOuterAlt(_localctx, 8);
				{
				setState(380);
				match(BIGSERIAL);
				}
				break;
			case 9:
				enterOuterAlt(_localctx, 9);
				{
				setState(381);
				match(SERIAL);
				}
				break;
			case 10:
				enterOuterAlt(_localctx, 10);
				{
				setState(382);
				match(SMALLSERIAL);
				}
				break;
			case 11:
				enterOuterAlt(_localctx, 11);
				{
				setState(383);
				match(REAL);
				}
				break;
			case 12:
				enterOuterAlt(_localctx, 12);
				{
				setState(384);
				match(DOUBLE);
				setState(385);
				match(PRECISION);
				}
				break;
			case 13:
				enterOuterAlt(_localctx, 13);
				{
				setState(386);
				match(NUMERIC);
				setState(394);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,25,_ctx) ) {
				case 1:
					{
					setState(387);
					match(LPAREN);
					setState(388);
					match(NUMBER);
					setState(391);
					_errHandler.sync(this);
					_la = _input.LA(1);
					if (_la==COMMA) {
						{
						setState(389);
						match(COMMA);
						setState(390);
						match(NUMBER);
						}
					}

					setState(393);
					match(RPAREN);
					}
					break;
				}
				}
				break;
			case 14:
				enterOuterAlt(_localctx, 14);
				{
				setState(396);
				decimalType();
				}
				break;
			case 15:
				enterOuterAlt(_localctx, 15);
				{
				setState(397);
				match(MONEY);
				}
				break;
			case 16:
				enterOuterAlt(_localctx, 16);
				{
				setState(398);
				match(CHAR);
				setState(402);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,26,_ctx) ) {
				case 1:
					{
					setState(399);
					match(LPAREN);
					setState(400);
					match(NUMBER);
					setState(401);
					match(RPAREN);
					}
					break;
				}
				}
				break;
			case 17:
				enterOuterAlt(_localctx, 17);
				{
				setState(404);
				match(VARCHAR);
				setState(408);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,27,_ctx) ) {
				case 1:
					{
					setState(405);
					match(LPAREN);
					setState(406);
					match(NUMBER);
					setState(407);
					match(RPAREN);
					}
					break;
				}
				}
				break;
			case 18:
				enterOuterAlt(_localctx, 18);
				{
				setState(410);
				match(CHARACTER);
				setState(411);
				match(VARYING);
				setState(415);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,28,_ctx) ) {
				case 1:
					{
					setState(412);
					match(LPAREN);
					setState(413);
					match(NUMBER);
					setState(414);
					match(RPAREN);
					}
					break;
				}
				}
				break;
			case 19:
				enterOuterAlt(_localctx, 19);
				{
				setState(417);
				match(TEXT);
				}
				break;
			case 20:
				enterOuterAlt(_localctx, 20);
				{
				setState(418);
				match(BOOLEAN);
				}
				break;
			case 21:
				enterOuterAlt(_localctx, 21);
				{
				setState(419);
				match(BPCHAR);
				setState(423);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,29,_ctx) ) {
				case 1:
					{
					setState(420);
					match(LPAREN);
					setState(421);
					match(NUMBER);
					setState(422);
					match(RPAREN);
					}
					break;
				}
				}
				break;
			case 22:
				enterOuterAlt(_localctx, 22);
				{
				setState(425);
				match(JSON);
				}
				break;
			case 23:
				enterOuterAlt(_localctx, 23);
				{
				setState(426);
				match(JSONB);
				}
				break;
			case 24:
				enterOuterAlt(_localctx, 24);
				{
				setState(427);
				match(DATE);
				}
				break;
			case 25:
				enterOuterAlt(_localctx, 25);
				{
				setState(428);
				match(TIME);
				setState(432);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,30,_ctx) ) {
				case 1:
					{
					setState(429);
					match(LPAREN);
					setState(430);
					match(NUMBER);
					setState(431);
					match(RPAREN);
					}
					break;
				}
				setState(440);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,31,_ctx) ) {
				case 1:
					{
					setState(434);
					match(T__44);
					setState(435);
					match(T__45);
					setState(436);
					match(T__46);
					}
					break;
				case 2:
					{
					setState(437);
					match(T__47);
					setState(438);
					match(T__45);
					setState(439);
					match(T__46);
					}
					break;
				}
				}
				break;
			case 26:
				enterOuterAlt(_localctx, 26);
				{
				setState(442);
				match(TIMESTAMP);
				setState(446);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,32,_ctx) ) {
				case 1:
					{
					setState(443);
					match(LPAREN);
					setState(444);
					match(NUMBER);
					setState(445);
					match(RPAREN);
					}
					break;
				}
				setState(454);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,33,_ctx) ) {
				case 1:
					{
					setState(448);
					match(T__44);
					setState(449);
					match(T__45);
					setState(450);
					match(T__46);
					}
					break;
				case 2:
					{
					setState(451);
					match(T__47);
					setState(452);
					match(T__45);
					setState(453);
					match(T__46);
					}
					break;
				}
				}
				break;
			case 27:
				enterOuterAlt(_localctx, 27);
				{
				setState(456);
				match(INTERVAL);
				}
				break;
			case 28:
				enterOuterAlt(_localctx, 28);
				{
				setState(457);
				match(UUID);
				}
				break;
			case 29:
				enterOuterAlt(_localctx, 29);
				{
				setState(458);
				match(ARRAY);
				}
				break;
			case 30:
				enterOuterAlt(_localctx, 30);
				{
				setState(459);
				match(BYTEA);
				}
				break;
			case 31:
				enterOuterAlt(_localctx, 31);
				{
				setState(460);
				match(ENUM);
				setState(461);
				match(LPAREN);
				setState(462);
				match(STRING);
				setState(467);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(463);
					match(COMMA);
					setState(464);
					match(STRING);
					}
					}
					setState(469);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(470);
				match(RPAREN);
				}
				break;
			case 32:
				enterOuterAlt(_localctx, 32);
				{
				setState(471);
				match(CITEXT);
				}
				break;
			case 33:
				enterOuterAlt(_localctx, 33);
				{
				setState(472);
				match(TSVECTOR);
				}
				break;
			case 34:
				enterOuterAlt(_localctx, 34);
				{
				setState(473);
				match(INET);
				}
				break;
			case 35:
				enterOuterAlt(_localctx, 35);
				{
				setState(474);
				match(CIDR);
				}
				break;
			case 36:
				enterOuterAlt(_localctx, 36);
				{
				setState(475);
				match(MACADDR);
				}
				break;
			case 37:
				enterOuterAlt(_localctx, 37);
				{
				setState(476);
				match(XML);
				}
				break;
			case 38:
				enterOuterAlt(_localctx, 38);
				{
				setState(477);
				match(PG_LSN);
				}
				break;
			case 39:
				enterOuterAlt(_localctx, 39);
				{
				setState(478);
				match(BIT);
				setState(482);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,35,_ctx) ) {
				case 1:
					{
					setState(479);
					match(LPAREN);
					setState(480);
					match(NUMBER);
					setState(481);
					match(RPAREN);
					}
					break;
				}
				}
				break;
			case 40:
				enterOuterAlt(_localctx, 40);
				{
				setState(484);
				match(VARBIT);
				setState(488);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,36,_ctx) ) {
				case 1:
					{
					setState(485);
					match(LPAREN);
					setState(486);
					match(NUMBER);
					setState(487);
					match(RPAREN);
					}
					break;
				}
				}
				break;
			case 41:
				enterOuterAlt(_localctx, 41);
				{
				setState(490);
				match(TRIGGER);
				}
				break;
			case 42:
				enterOuterAlt(_localctx, 42);
				{
				setState(491);
				match(IDENTIFIER);
				setState(492);
				match(IDENTIFIER);
				}
				break;
			case 43:
				enterOuterAlt(_localctx, 43);
				{
				setState(493);
				match(IDENTIFIER);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class FunctionCallContext extends ParserRuleContext {
		public TerminalNode IDENTIFIER() { return getToken(PostgreSQLParser.IDENTIFIER, 0); }
		public TerminalNode LPAREN() { return getToken(PostgreSQLParser.LPAREN, 0); }
		public TerminalNode RPAREN() { return getToken(PostgreSQLParser.RPAREN, 0); }
		public ExpressionListContext expressionList() {
			return getRuleContext(ExpressionListContext.class,0);
		}
		public FunctionCallContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_functionCall; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PostgreSQLListener ) ((PostgreSQLListener)listener).enterFunctionCall(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PostgreSQLListener ) ((PostgreSQLListener)listener).exitFunctionCall(this);
		}
	}

	public final FunctionCallContext functionCall() throws RecognitionException {
		FunctionCallContext _localctx = new FunctionCallContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_functionCall);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(496);
			match(IDENTIFIER);
			setState(497);
			match(LPAREN);
			setState(499);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__48 || ((((_la - 102)) & ~0x3f) == 0 && ((1L << (_la - 102)) & 162199955329645575L) != 0) || ((((_la - 234)) & ~0x3f) == 0 && ((1L << (_la - 234)) & 7L) != 0)) {
				{
				setState(498);
				expressionList();
				}
			}

			setState(501);
			match(RPAREN);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class OnUpdateClauseContext extends ParserRuleContext {
		public TerminalNode ON_UPDATE() { return getToken(PostgreSQLParser.ON_UPDATE, 0); }
		public OnUpdateClauseContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_onUpdateClause; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PostgreSQLListener ) ((PostgreSQLListener)listener).enterOnUpdateClause(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PostgreSQLListener ) ((PostgreSQLListener)listener).exitOnUpdateClause(this);
		}
	}

	public final OnUpdateClauseContext onUpdateClause() throws RecognitionException {
		OnUpdateClauseContext _localctx = new OnUpdateClauseContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_onUpdateClause);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(503);
			match(ON_UPDATE);
			setState(504);
			match(T__48);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ConstraintContext extends ParserRuleContext {
		public TerminalNode NOT() { return getToken(PostgreSQLParser.NOT, 0); }
		public TerminalNode NULL() { return getToken(PostgreSQLParser.NULL, 0); }
		public TerminalNode UNIQUE() { return getToken(PostgreSQLParser.UNIQUE, 0); }
		public ConflictActionContext conflictAction() {
			return getRuleContext(ConflictActionContext.class,0);
		}
		public TerminalNode MANYTOMANY() { return getToken(PostgreSQLParser.MANYTOMANY, 0); }
		public TerminalNode DEFAULT() { return getToken(PostgreSQLParser.DEFAULT, 0); }
		public ValueContext value() {
			return getRuleContext(ValueContext.class,0);
		}
		public TerminalNode CHECK() { return getToken(PostgreSQLParser.CHECK, 0); }
		public List<TerminalNode> LPAREN() { return getTokens(PostgreSQLParser.LPAREN); }
		public TerminalNode LPAREN(int i) {
			return getToken(PostgreSQLParser.LPAREN, i);
		}
		public ConditionContext condition() {
			return getRuleContext(ConditionContext.class,0);
		}
		public List<TerminalNode> RPAREN() { return getTokens(PostgreSQLParser.RPAREN); }
		public TerminalNode RPAREN(int i) {
			return getToken(PostgreSQLParser.RPAREN, i);
		}
		public TerminalNode REFERENCES() { return getToken(PostgreSQLParser.REFERENCES, 0); }
		public TableNameContext tableName() {
			return getRuleContext(TableNameContext.class,0);
		}
		public ColumnNameContext columnName() {
			return getRuleContext(ColumnNameContext.class,0);
		}
		public SchemaNameContext schemaName() {
			return getRuleContext(SchemaNameContext.class,0);
		}
		public TerminalNode DOT() { return getToken(PostgreSQLParser.DOT, 0); }
		public List<OnActionContext> onAction() {
			return getRuleContexts(OnActionContext.class);
		}
		public OnActionContext onAction(int i) {
			return getRuleContext(OnActionContext.class,i);
		}
		public TerminalNode FOREIGN() { return getToken(PostgreSQLParser.FOREIGN, 0); }
		public TerminalNode KEY() { return getToken(PostgreSQLParser.KEY, 0); }
		public List<ColumnNameListContext> columnNameList() {
			return getRuleContexts(ColumnNameListContext.class);
		}
		public ColumnNameListContext columnNameList(int i) {
			return getRuleContext(ColumnNameListContext.class,i);
		}
		public TerminalNode RELATIONSHIP() { return getToken(PostgreSQLParser.RELATIONSHIP, 0); }
		public RelationshipTypeContext relationshipType() {
			return getRuleContext(RelationshipTypeContext.class,0);
		}
		public TerminalNode EXCLUDE() { return getToken(PostgreSQLParser.EXCLUDE, 0); }
		public TerminalNode USING() { return getToken(PostgreSQLParser.USING, 0); }
		public TerminalNode IDENTIFIER() { return getToken(PostgreSQLParser.IDENTIFIER, 0); }
		public ExcludeElementListContext excludeElementList() {
			return getRuleContext(ExcludeElementListContext.class,0);
		}
		public TerminalNode PRIMARY() { return getToken(PostgreSQLParser.PRIMARY, 0); }
		public ConstraintContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_constraint; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PostgreSQLListener ) ((PostgreSQLListener)listener).enterConstraint(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PostgreSQLListener ) ((PostgreSQLListener)listener).exitConstraint(this);
		}
	}

	public final ConstraintContext constraint() throws RecognitionException {
		ConstraintContext _localctx = new ConstraintContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_constraint);
		int _la;
		try {
			int _alt;
			setState(577);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case NOT:
				enterOuterAlt(_localctx, 1);
				{
				setState(506);
				match(NOT);
				setState(507);
				match(NULL);
				}
				break;
			case NULL:
				enterOuterAlt(_localctx, 2);
				{
				setState(508);
				match(NULL);
				}
				break;
			case UNIQUE:
				enterOuterAlt(_localctx, 3);
				{
				setState(509);
				match(UNIQUE);
				setState(512);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__49) {
					{
					setState(510);
					match(T__49);
					setState(511);
					conflictAction();
					}
				}

				}
				break;
			case MANYTOMANY:
				enterOuterAlt(_localctx, 4);
				{
				setState(514);
				match(MANYTOMANY);
				setState(517);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__49) {
					{
					setState(515);
					match(T__49);
					setState(516);
					conflictAction();
					}
				}

				}
				break;
			case DEFAULT:
				enterOuterAlt(_localctx, 5);
				{
				setState(519);
				match(DEFAULT);
				setState(520);
				value();
				}
				break;
			case CHECK:
				enterOuterAlt(_localctx, 6);
				{
				setState(521);
				match(CHECK);
				setState(522);
				match(LPAREN);
				setState(523);
				condition();
				setState(524);
				match(RPAREN);
				}
				break;
			case REFERENCES:
				enterOuterAlt(_localctx, 7);
				{
				setState(526);
				match(REFERENCES);
				setState(530);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,41,_ctx) ) {
				case 1:
					{
					setState(527);
					schemaName();
					setState(528);
					match(DOT);
					}
					break;
				}
				setState(532);
				tableName();
				setState(533);
				match(LPAREN);
				setState(534);
				columnName();
				setState(535);
				match(RPAREN);
				setState(537);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,42,_ctx) ) {
				case 1:
					{
					setState(536);
					onAction();
					}
					break;
				}
				}
				break;
			case FOREIGN:
				enterOuterAlt(_localctx, 8);
				{
				setState(539);
				match(FOREIGN);
				setState(540);
				match(KEY);
				setState(541);
				match(LPAREN);
				setState(542);
				columnNameList();
				setState(543);
				match(RPAREN);
				setState(544);
				match(REFERENCES);
				setState(548);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,43,_ctx) ) {
				case 1:
					{
					setState(545);
					schemaName();
					setState(546);
					match(DOT);
					}
					break;
				}
				setState(550);
				tableName();
				setState(551);
				match(LPAREN);
				setState(552);
				columnNameList();
				setState(553);
				match(RPAREN);
				setState(556);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==RELATIONSHIP) {
					{
					setState(554);
					match(RELATIONSHIP);
					setState(555);
					relationshipType();
					}
				}

				setState(561);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,45,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(558);
						onAction();
						}
						} 
					}
					setState(563);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,45,_ctx);
				}
				}
				break;
			case EXCLUDE:
				enterOuterAlt(_localctx, 9);
				{
				setState(564);
				match(EXCLUDE);
				setState(565);
				match(USING);
				setState(566);
				match(IDENTIFIER);
				setState(567);
				match(LPAREN);
				setState(568);
				excludeElementList();
				setState(569);
				match(RPAREN);
				setState(572);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__50) {
					{
					setState(570);
					match(T__50);
					setState(571);
					condition();
					}
				}

				}
				break;
			case T__51:
				enterOuterAlt(_localctx, 10);
				{
				setState(574);
				match(T__51);
				}
				break;
			case PRIMARY:
				enterOuterAlt(_localctx, 11);
				{
				setState(575);
				match(PRIMARY);
				setState(576);
				match(KEY);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class TableConstraintContext extends ParserRuleContext {
		public TerminalNode CONSTRAINT() { return getToken(PostgreSQLParser.CONSTRAINT, 0); }
		public TerminalNode PRIMARY_KEY() { return getToken(PostgreSQLParser.PRIMARY_KEY, 0); }
		public List<TerminalNode> LPAREN() { return getTokens(PostgreSQLParser.LPAREN); }
		public TerminalNode LPAREN(int i) {
			return getToken(PostgreSQLParser.LPAREN, i);
		}
		public List<ColumnNameListContext> columnNameList() {
			return getRuleContexts(ColumnNameListContext.class);
		}
		public ColumnNameListContext columnNameList(int i) {
			return getRuleContext(ColumnNameListContext.class,i);
		}
		public List<TerminalNode> RPAREN() { return getTokens(PostgreSQLParser.RPAREN); }
		public TerminalNode RPAREN(int i) {
			return getToken(PostgreSQLParser.RPAREN, i);
		}
		public TerminalNode IDENTIFIER() { return getToken(PostgreSQLParser.IDENTIFIER, 0); }
		public TerminalNode FOREIGN_KEY() { return getToken(PostgreSQLParser.FOREIGN_KEY, 0); }
		public TerminalNode REFERENCES() { return getToken(PostgreSQLParser.REFERENCES, 0); }
		public TableNameContext tableName() {
			return getRuleContext(TableNameContext.class,0);
		}
		public SchemaNameContext schemaName() {
			return getRuleContext(SchemaNameContext.class,0);
		}
		public TerminalNode DOT() { return getToken(PostgreSQLParser.DOT, 0); }
		public List<OnActionContext> onAction() {
			return getRuleContexts(OnActionContext.class);
		}
		public OnActionContext onAction(int i) {
			return getRuleContext(OnActionContext.class,i);
		}
		public TerminalNode UNIQUE() { return getToken(PostgreSQLParser.UNIQUE, 0); }
		public TerminalNode CHECK() { return getToken(PostgreSQLParser.CHECK, 0); }
		public ConditionContext condition() {
			return getRuleContext(ConditionContext.class,0);
		}
		public TerminalNode EXCLUDE() { return getToken(PostgreSQLParser.EXCLUDE, 0); }
		public TerminalNode USING() { return getToken(PostgreSQLParser.USING, 0); }
		public ExcludeElementListContext excludeElementList() {
			return getRuleContext(ExcludeElementListContext.class,0);
		}
		public TableConstraintContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_tableConstraint; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PostgreSQLListener ) ((PostgreSQLListener)listener).enterTableConstraint(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PostgreSQLListener ) ((PostgreSQLListener)listener).exitTableConstraint(this);
		}
	}

	public final TableConstraintContext tableConstraint() throws RecognitionException {
		TableConstraintContext _localctx = new TableConstraintContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_tableConstraint);
		int _la;
		try {
			setState(662);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,56,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(579);
				match(CONSTRAINT);
				setState(581);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==IDENTIFIER) {
					{
					setState(580);
					match(IDENTIFIER);
					}
				}

				setState(583);
				match(PRIMARY_KEY);
				setState(584);
				match(LPAREN);
				setState(585);
				columnNameList();
				setState(586);
				match(RPAREN);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(588);
				match(CONSTRAINT);
				setState(590);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==IDENTIFIER) {
					{
					setState(589);
					match(IDENTIFIER);
					}
				}

				setState(592);
				match(FOREIGN_KEY);
				setState(593);
				match(LPAREN);
				setState(594);
				columnNameList();
				setState(595);
				match(RPAREN);
				setState(596);
				match(REFERENCES);
				setState(600);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,50,_ctx) ) {
				case 1:
					{
					setState(597);
					schemaName();
					setState(598);
					match(DOT);
					}
					break;
				}
				setState(602);
				tableName();
				setState(603);
				match(LPAREN);
				setState(604);
				columnNameList();
				setState(605);
				match(RPAREN);
				setState(609);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__52 || _la==ON_DELETE || _la==ON_UPDATE) {
					{
					{
					setState(606);
					onAction();
					}
					}
					setState(611);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(612);
				match(FOREIGN_KEY);
				setState(613);
				match(LPAREN);
				setState(614);
				columnNameList();
				setState(615);
				match(RPAREN);
				setState(616);
				match(REFERENCES);
				setState(620);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,52,_ctx) ) {
				case 1:
					{
					setState(617);
					schemaName();
					setState(618);
					match(DOT);
					}
					break;
				}
				setState(622);
				tableName();
				setState(623);
				match(LPAREN);
				setState(624);
				columnNameList();
				setState(625);
				match(RPAREN);
				setState(629);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__52 || _la==ON_DELETE || _la==ON_UPDATE) {
					{
					{
					setState(626);
					onAction();
					}
					}
					setState(631);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(632);
				match(CONSTRAINT);
				setState(634);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==IDENTIFIER) {
					{
					setState(633);
					match(IDENTIFIER);
					}
				}

				setState(636);
				match(UNIQUE);
				setState(637);
				match(LPAREN);
				setState(638);
				columnNameList();
				setState(639);
				match(RPAREN);
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(641);
				match(CONSTRAINT);
				setState(643);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==IDENTIFIER) {
					{
					setState(642);
					match(IDENTIFIER);
					}
				}

				setState(645);
				match(CHECK);
				setState(646);
				match(LPAREN);
				setState(647);
				condition();
				setState(648);
				match(RPAREN);
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(650);
				match(EXCLUDE);
				setState(651);
				match(USING);
				setState(652);
				match(IDENTIFIER);
				setState(653);
				match(LPAREN);
				setState(654);
				excludeElementList();
				setState(655);
				match(RPAREN);
				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(657);
				match(PRIMARY_KEY);
				setState(658);
				match(LPAREN);
				setState(659);
				columnNameList();
				setState(660);
				match(RPAREN);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ExcludeElementListContext extends ParserRuleContext {
		public List<ExcludeElementContext> excludeElement() {
			return getRuleContexts(ExcludeElementContext.class);
		}
		public ExcludeElementContext excludeElement(int i) {
			return getRuleContext(ExcludeElementContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(PostgreSQLParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(PostgreSQLParser.COMMA, i);
		}
		public ExcludeElementListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_excludeElementList; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PostgreSQLListener ) ((PostgreSQLListener)listener).enterExcludeElementList(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PostgreSQLListener ) ((PostgreSQLListener)listener).exitExcludeElementList(this);
		}
	}

	public final ExcludeElementListContext excludeElementList() throws RecognitionException {
		ExcludeElementListContext _localctx = new ExcludeElementListContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_excludeElementList);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(664);
			excludeElement();
			setState(669);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(665);
				match(COMMA);
				setState(666);
				excludeElement();
				}
				}
				setState(671);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ExcludeElementContext extends ParserRuleContext {
		public ColumnNameContext columnName() {
			return getRuleContext(ColumnNameContext.class,0);
		}
		public ComparisonOperatorContext comparisonOperator() {
			return getRuleContext(ComparisonOperatorContext.class,0);
		}
		public ExcludeElementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_excludeElement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PostgreSQLListener ) ((PostgreSQLListener)listener).enterExcludeElement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PostgreSQLListener ) ((PostgreSQLListener)listener).exitExcludeElement(this);
		}
	}

	public final ExcludeElementContext excludeElement() throws RecognitionException {
		ExcludeElementContext _localctx = new ExcludeElementContext(_ctx, getState());
		enterRule(_localctx, 38, RULE_excludeElement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(672);
			columnName();
			setState(673);
			match(T__47);
			setState(674);
			comparisonOperator();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class OnActionContext extends ParserRuleContext {
		public TerminalNode ON_DELETE() { return getToken(PostgreSQLParser.ON_DELETE, 0); }
		public ActionContext action() {
			return getRuleContext(ActionContext.class,0);
		}
		public TerminalNode ON_UPDATE() { return getToken(PostgreSQLParser.ON_UPDATE, 0); }
		public OnActionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_onAction; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PostgreSQLListener ) ((PostgreSQLListener)listener).enterOnAction(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PostgreSQLListener ) ((PostgreSQLListener)listener).exitOnAction(this);
		}
	}

	public final OnActionContext onAction() throws RecognitionException {
		OnActionContext _localctx = new OnActionContext(_ctx, getState());
		enterRule(_localctx, 40, RULE_onAction);
		int _la;
		try {
			setState(685);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case ON_DELETE:
				enterOuterAlt(_localctx, 1);
				{
				setState(676);
				match(ON_DELETE);
				setState(677);
				action();
				}
				break;
			case ON_UPDATE:
				enterOuterAlt(_localctx, 2);
				{
				setState(678);
				match(ON_UPDATE);
				setState(679);
				action();
				}
				break;
			case T__52:
				enterOuterAlt(_localctx, 3);
				{
				setState(680);
				match(T__52);
				setState(683);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__53) {
					{
					setState(681);
					match(T__53);
					setState(682);
					_la = _input.LA(1);
					if ( !(_la==T__54 || _la==T__55) ) {
					_errHandler.recoverInline(this);
					}
					else {
						if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
						_errHandler.reportMatch(this);
						consume();
					}
					}
				}

				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ActionContext extends ParserRuleContext {
		public TerminalNode CASCADE() { return getToken(PostgreSQLParser.CASCADE, 0); }
		public TerminalNode SET_NULL() { return getToken(PostgreSQLParser.SET_NULL, 0); }
		public TerminalNode SET_DEFAULT() { return getToken(PostgreSQLParser.SET_DEFAULT, 0); }
		public TerminalNode NO_ACTION() { return getToken(PostgreSQLParser.NO_ACTION, 0); }
		public TerminalNode RESTRICT() { return getToken(PostgreSQLParser.RESTRICT, 0); }
		public ActionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_action; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PostgreSQLListener ) ((PostgreSQLListener)listener).enterAction(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PostgreSQLListener ) ((PostgreSQLListener)listener).exitAction(this);
		}
	}

	public final ActionContext action() throws RecognitionException {
		ActionContext _localctx = new ActionContext(_ctx, getState());
		enterRule(_localctx, 42, RULE_action);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(687);
			_la = _input.LA(1);
			if ( !(((((_la - 163)) & ~0x3f) == 0 && ((1L << (_la - 163)) & 31L) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class TriggerStatementContext extends ParserRuleContext {
		public TerminalNode CREATE() { return getToken(PostgreSQLParser.CREATE, 0); }
		public TerminalNode TRIGGER() { return getToken(PostgreSQLParser.TRIGGER, 0); }
		public List<TerminalNode> IDENTIFIER() { return getTokens(PostgreSQLParser.IDENTIFIER); }
		public TerminalNode IDENTIFIER(int i) {
			return getToken(PostgreSQLParser.IDENTIFIER, i);
		}
		public TriggerTimingContext triggerTiming() {
			return getRuleContext(TriggerTimingContext.class,0);
		}
		public TriggerEventContext triggerEvent() {
			return getRuleContext(TriggerEventContext.class,0);
		}
		public TerminalNode ON() { return getToken(PostgreSQLParser.ON, 0); }
		public TableNameContext tableName() {
			return getRuleContext(TableNameContext.class,0);
		}
		public TerminalNode LPAREN() { return getToken(PostgreSQLParser.LPAREN, 0); }
		public TerminalNode RPAREN() { return getToken(PostgreSQLParser.RPAREN, 0); }
		public TerminalNode SEMICOLON() { return getToken(PostgreSQLParser.SEMICOLON, 0); }
		public List<ValueContext> value() {
			return getRuleContexts(ValueContext.class);
		}
		public ValueContext value(int i) {
			return getRuleContext(ValueContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(PostgreSQLParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(PostgreSQLParser.COMMA, i);
		}
		public TriggerStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_triggerStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PostgreSQLListener ) ((PostgreSQLListener)listener).enterTriggerStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PostgreSQLListener ) ((PostgreSQLListener)listener).exitTriggerStatement(this);
		}
	}

	public final TriggerStatementContext triggerStatement() throws RecognitionException {
		TriggerStatementContext _localctx = new TriggerStatementContext(_ctx, getState());
		enterRule(_localctx, 44, RULE_triggerStatement);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(689);
			match(CREATE);
			setState(690);
			match(TRIGGER);
			setState(691);
			match(IDENTIFIER);
			setState(692);
			triggerTiming();
			setState(693);
			triggerEvent();
			setState(694);
			match(ON);
			setState(695);
			tableName();
			setState(696);
			match(T__56);
			setState(697);
			match(T__57);
			setState(698);
			match(IDENTIFIER);
			setState(699);
			match(LPAREN);
			setState(708);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__48 || ((((_la - 102)) & ~0x3f) == 0 && ((1L << (_la - 102)) & 144185556820098055L) != 0) || ((((_la - 234)) & ~0x3f) == 0 && ((1L << (_la - 234)) & 7L) != 0)) {
				{
				setState(700);
				value();
				setState(705);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(701);
					match(COMMA);
					setState(702);
					value();
					}
					}
					setState(707);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
			}

			setState(710);
			match(RPAREN);
			setState(711);
			match(SEMICOLON);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class TriggerTimingContext extends ParserRuleContext {
		public TerminalNode BEFORE() { return getToken(PostgreSQLParser.BEFORE, 0); }
		public TerminalNode AFTER() { return getToken(PostgreSQLParser.AFTER, 0); }
		public TriggerTimingContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_triggerTiming; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PostgreSQLListener ) ((PostgreSQLListener)listener).enterTriggerTiming(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PostgreSQLListener ) ((PostgreSQLListener)listener).exitTriggerTiming(this);
		}
	}

	public final TriggerTimingContext triggerTiming() throws RecognitionException {
		TriggerTimingContext _localctx = new TriggerTimingContext(_ctx, getState());
		enterRule(_localctx, 46, RULE_triggerTiming);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(713);
			_la = _input.LA(1);
			if ( !(_la==T__58 || _la==BEFORE || _la==AFTER) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class TriggerEventContext extends ParserRuleContext {
		public List<TerminalNode> INSERT() { return getTokens(PostgreSQLParser.INSERT); }
		public TerminalNode INSERT(int i) {
			return getToken(PostgreSQLParser.INSERT, i);
		}
		public List<TerminalNode> UPDATE() { return getTokens(PostgreSQLParser.UPDATE); }
		public TerminalNode UPDATE(int i) {
			return getToken(PostgreSQLParser.UPDATE, i);
		}
		public List<TerminalNode> DELETE() { return getTokens(PostgreSQLParser.DELETE); }
		public TerminalNode DELETE(int i) {
			return getToken(PostgreSQLParser.DELETE, i);
		}
		public TriggerEventContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_triggerEvent; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PostgreSQLListener ) ((PostgreSQLListener)listener).enterTriggerEvent(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PostgreSQLListener ) ((PostgreSQLListener)listener).exitTriggerEvent(this);
		}
	}

	public final TriggerEventContext triggerEvent() throws RecognitionException {
		TriggerEventContext _localctx = new TriggerEventContext(_ctx, getState());
		enterRule(_localctx, 48, RULE_triggerEvent);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(715);
			_la = _input.LA(1);
			if ( !(((((_la - 128)) & ~0x3f) == 0 && ((1L << (_la - 128)) & 6755399441055745L) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			setState(720);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__59) {
				{
				{
				setState(716);
				match(T__59);
				setState(717);
				_la = _input.LA(1);
				if ( !(((((_la - 128)) & ~0x3f) == 0 && ((1L << (_la - 128)) & 6755399441055745L) != 0)) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				}
				}
				setState(722);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class DataManipulationStatementContext extends ParserRuleContext {
		public InsertStatementContext insertStatement() {
			return getRuleContext(InsertStatementContext.class,0);
		}
		public UpdateStatementContext updateStatement() {
			return getRuleContext(UpdateStatementContext.class,0);
		}
		public DeleteStatementContext deleteStatement() {
			return getRuleContext(DeleteStatementContext.class,0);
		}
		public DataManipulationStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_dataManipulationStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PostgreSQLListener ) ((PostgreSQLListener)listener).enterDataManipulationStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PostgreSQLListener ) ((PostgreSQLListener)listener).exitDataManipulationStatement(this);
		}
	}

	public final DataManipulationStatementContext dataManipulationStatement() throws RecognitionException {
		DataManipulationStatementContext _localctx = new DataManipulationStatementContext(_ctx, getState());
		enterRule(_localctx, 50, RULE_dataManipulationStatement);
		try {
			setState(726);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__60:
				enterOuterAlt(_localctx, 1);
				{
				setState(723);
				insertStatement();
				}
				break;
			case UPDATE:
				enterOuterAlt(_localctx, 2);
				{
				setState(724);
				updateStatement();
				}
				break;
			case T__62:
				enterOuterAlt(_localctx, 3);
				{
				setState(725);
				deleteStatement();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class InsertStatementContext extends ParserRuleContext {
		public TableNameContext tableName() {
			return getRuleContext(TableNameContext.class,0);
		}
		public List<TerminalNode> LPAREN() { return getTokens(PostgreSQLParser.LPAREN); }
		public TerminalNode LPAREN(int i) {
			return getToken(PostgreSQLParser.LPAREN, i);
		}
		public List<ColumnNameContext> columnName() {
			return getRuleContexts(ColumnNameContext.class);
		}
		public ColumnNameContext columnName(int i) {
			return getRuleContext(ColumnNameContext.class,i);
		}
		public List<TerminalNode> RPAREN() { return getTokens(PostgreSQLParser.RPAREN); }
		public TerminalNode RPAREN(int i) {
			return getToken(PostgreSQLParser.RPAREN, i);
		}
		public List<ValueContext> value() {
			return getRuleContexts(ValueContext.class);
		}
		public ValueContext value(int i) {
			return getRuleContext(ValueContext.class,i);
		}
		public TerminalNode SEMICOLON() { return getToken(PostgreSQLParser.SEMICOLON, 0); }
		public List<TerminalNode> COMMA() { return getTokens(PostgreSQLParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(PostgreSQLParser.COMMA, i);
		}
		public InsertStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_insertStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PostgreSQLListener ) ((PostgreSQLListener)listener).enterInsertStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PostgreSQLListener ) ((PostgreSQLListener)listener).exitInsertStatement(this);
		}
	}

	public final InsertStatementContext insertStatement() throws RecognitionException {
		InsertStatementContext _localctx = new InsertStatementContext(_ctx, getState());
		enterRule(_localctx, 52, RULE_insertStatement);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(728);
			match(T__60);
			setState(729);
			tableName();
			setState(730);
			match(LPAREN);
			setState(731);
			columnName();
			setState(736);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(732);
				match(COMMA);
				setState(733);
				columnName();
				}
				}
				setState(738);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(739);
			match(RPAREN);
			setState(740);
			match(T__61);
			setState(741);
			match(LPAREN);
			setState(742);
			value();
			setState(747);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(743);
				match(COMMA);
				setState(744);
				value();
				}
				}
				setState(749);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(750);
			match(RPAREN);
			setState(751);
			match(SEMICOLON);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class UpdateStatementContext extends ParserRuleContext {
		public TerminalNode UPDATE() { return getToken(PostgreSQLParser.UPDATE, 0); }
		public TableNameContext tableName() {
			return getRuleContext(TableNameContext.class,0);
		}
		public TerminalNode SET() { return getToken(PostgreSQLParser.SET, 0); }
		public List<ColumnNameContext> columnName() {
			return getRuleContexts(ColumnNameContext.class);
		}
		public ColumnNameContext columnName(int i) {
			return getRuleContext(ColumnNameContext.class,i);
		}
		public List<TerminalNode> EQUALS() { return getTokens(PostgreSQLParser.EQUALS); }
		public TerminalNode EQUALS(int i) {
			return getToken(PostgreSQLParser.EQUALS, i);
		}
		public List<ValueContext> value() {
			return getRuleContexts(ValueContext.class);
		}
		public ValueContext value(int i) {
			return getRuleContext(ValueContext.class,i);
		}
		public TerminalNode SEMICOLON() { return getToken(PostgreSQLParser.SEMICOLON, 0); }
		public List<TerminalNode> COMMA() { return getTokens(PostgreSQLParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(PostgreSQLParser.COMMA, i);
		}
		public ConditionContext condition() {
			return getRuleContext(ConditionContext.class,0);
		}
		public UpdateStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_updateStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PostgreSQLListener ) ((PostgreSQLListener)listener).enterUpdateStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PostgreSQLListener ) ((PostgreSQLListener)listener).exitUpdateStatement(this);
		}
	}

	public final UpdateStatementContext updateStatement() throws RecognitionException {
		UpdateStatementContext _localctx = new UpdateStatementContext(_ctx, getState());
		enterRule(_localctx, 54, RULE_updateStatement);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(753);
			match(UPDATE);
			setState(754);
			tableName();
			setState(755);
			match(SET);
			setState(756);
			columnName();
			setState(757);
			match(EQUALS);
			setState(758);
			value();
			setState(766);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(759);
				match(COMMA);
				setState(760);
				columnName();
				setState(761);
				match(EQUALS);
				setState(762);
				value();
				}
				}
				setState(768);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(771);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__50) {
				{
				setState(769);
				match(T__50);
				setState(770);
				condition();
				}
			}

			setState(773);
			match(SEMICOLON);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class DeleteStatementContext extends ParserRuleContext {
		public TableNameContext tableName() {
			return getRuleContext(TableNameContext.class,0);
		}
		public TerminalNode SEMICOLON() { return getToken(PostgreSQLParser.SEMICOLON, 0); }
		public ConditionContext condition() {
			return getRuleContext(ConditionContext.class,0);
		}
		public DeleteStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_deleteStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PostgreSQLListener ) ((PostgreSQLListener)listener).enterDeleteStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PostgreSQLListener ) ((PostgreSQLListener)listener).exitDeleteStatement(this);
		}
	}

	public final DeleteStatementContext deleteStatement() throws RecognitionException {
		DeleteStatementContext _localctx = new DeleteStatementContext(_ctx, getState());
		enterRule(_localctx, 56, RULE_deleteStatement);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(775);
			match(T__62);
			setState(776);
			tableName();
			setState(779);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__50) {
				{
				setState(777);
				match(T__50);
				setState(778);
				condition();
				}
			}

			setState(781);
			match(SEMICOLON);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ConditionContext extends ParserRuleContext {
		public BooleanConditionContext booleanCondition() {
			return getRuleContext(BooleanConditionContext.class,0);
		}
		public ConditionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_condition; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PostgreSQLListener ) ((PostgreSQLListener)listener).enterCondition(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PostgreSQLListener ) ((PostgreSQLListener)listener).exitCondition(this);
		}
	}

	public final ConditionContext condition() throws RecognitionException {
		ConditionContext _localctx = new ConditionContext(_ctx, getState());
		enterRule(_localctx, 58, RULE_condition);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(783);
			booleanCondition(0);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class BooleanConditionContext extends ParserRuleContext {
		public PredicateContext predicate() {
			return getRuleContext(PredicateContext.class,0);
		}
		public TerminalNode LPAREN() { return getToken(PostgreSQLParser.LPAREN, 0); }
		public List<BooleanConditionContext> booleanCondition() {
			return getRuleContexts(BooleanConditionContext.class);
		}
		public BooleanConditionContext booleanCondition(int i) {
			return getRuleContext(BooleanConditionContext.class,i);
		}
		public TerminalNode RPAREN() { return getToken(PostgreSQLParser.RPAREN, 0); }
		public TerminalNode NOT() { return getToken(PostgreSQLParser.NOT, 0); }
		public TerminalNode AND() { return getToken(PostgreSQLParser.AND, 0); }
		public BooleanConditionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_booleanCondition; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PostgreSQLListener ) ((PostgreSQLListener)listener).enterBooleanCondition(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PostgreSQLListener ) ((PostgreSQLListener)listener).exitBooleanCondition(this);
		}
	}

	public final BooleanConditionContext booleanCondition() throws RecognitionException {
		return booleanCondition(0);
	}

	private BooleanConditionContext booleanCondition(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		BooleanConditionContext _localctx = new BooleanConditionContext(_ctx, _parentState);
		BooleanConditionContext _prevctx = _localctx;
		int _startState = 60;
		enterRecursionRule(_localctx, 60, RULE_booleanCondition, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(793);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,69,_ctx) ) {
			case 1:
				{
				setState(786);
				predicate();
				}
				break;
			case 2:
				{
				setState(787);
				match(LPAREN);
				setState(788);
				booleanCondition(0);
				setState(789);
				match(RPAREN);
				}
				break;
			case 3:
				{
				setState(791);
				match(NOT);
				setState(792);
				booleanCondition(3);
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(803);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,71,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(801);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,70,_ctx) ) {
					case 1:
						{
						_localctx = new BooleanConditionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_booleanCondition);
						setState(795);
						if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
						setState(796);
						match(AND);
						setState(797);
						booleanCondition(3);
						}
						break;
					case 2:
						{
						_localctx = new BooleanConditionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_booleanCondition);
						setState(798);
						if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
						setState(799);
						match(T__59);
						setState(800);
						booleanCondition(2);
						}
						break;
					}
					} 
				}
				setState(805);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,71,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class PredicateContext extends ParserRuleContext {
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public TerminalNode IS() { return getToken(PostgreSQLParser.IS, 0); }
		public TerminalNode NOT() { return getToken(PostgreSQLParser.NOT, 0); }
		public TerminalNode NULL() { return getToken(PostgreSQLParser.NULL, 0); }
		public TerminalNode IN() { return getToken(PostgreSQLParser.IN, 0); }
		public TerminalNode LPAREN() { return getToken(PostgreSQLParser.LPAREN, 0); }
		public TerminalNode RPAREN() { return getToken(PostgreSQLParser.RPAREN, 0); }
		public List<SubqueryContext> subquery() {
			return getRuleContexts(SubqueryContext.class);
		}
		public SubqueryContext subquery(int i) {
			return getRuleContext(SubqueryContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(PostgreSQLParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(PostgreSQLParser.COMMA, i);
		}
		public TerminalNode BETWEEN() { return getToken(PostgreSQLParser.BETWEEN, 0); }
		public TerminalNode AND() { return getToken(PostgreSQLParser.AND, 0); }
		public TerminalNode LIKE() { return getToken(PostgreSQLParser.LIKE, 0); }
		public TerminalNode STRING() { return getToken(PostgreSQLParser.STRING, 0); }
		public TerminalNode ILIKE() { return getToken(PostgreSQLParser.ILIKE, 0); }
		public ComparisonOperatorContext comparisonOperator() {
			return getRuleContext(ComparisonOperatorContext.class,0);
		}
		public BooleanLiteralContext booleanLiteral() {
			return getRuleContext(BooleanLiteralContext.class,0);
		}
		public PredicateContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_predicate; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PostgreSQLListener ) ((PostgreSQLListener)listener).enterPredicate(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PostgreSQLListener ) ((PostgreSQLListener)listener).exitPredicate(this);
		}
	}

	public final PredicateContext predicate() throws RecognitionException {
		PredicateContext _localctx = new PredicateContext(_ctx, getState());
		enterRule(_localctx, 62, RULE_predicate);
		int _la;
		try {
			setState(853);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,75,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(806);
				expression();
				setState(807);
				match(IS);
				setState(808);
				match(NOT);
				setState(809);
				match(NULL);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(811);
				expression();
				setState(812);
				match(IS);
				setState(813);
				match(NULL);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(815);
				expression();
				setState(816);
				match(IN);
				setState(817);
				match(LPAREN);
				setState(820);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,72,_ctx) ) {
				case 1:
					{
					setState(818);
					expression();
					}
					break;
				case 2:
					{
					setState(819);
					subquery();
					}
					break;
				}
				setState(829);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(822);
					match(COMMA);
					setState(825);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,73,_ctx) ) {
					case 1:
						{
						setState(823);
						expression();
						}
						break;
					case 2:
						{
						setState(824);
						subquery();
						}
						break;
					}
					}
					}
					setState(831);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(832);
				match(RPAREN);
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(834);
				expression();
				setState(835);
				match(BETWEEN);
				setState(836);
				expression();
				setState(837);
				match(AND);
				setState(838);
				expression();
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(840);
				expression();
				setState(841);
				match(LIKE);
				setState(842);
				match(STRING);
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(844);
				expression();
				setState(845);
				match(ILIKE);
				setState(846);
				match(STRING);
				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(848);
				expression();
				setState(849);
				comparisonOperator();
				setState(850);
				expression();
				}
				break;
			case 8:
				enterOuterAlt(_localctx, 8);
				{
				setState(852);
				booleanLiteral();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class SubqueryContext extends ParserRuleContext {
		public TerminalNode LPAREN() { return getToken(PostgreSQLParser.LPAREN, 0); }
		public SqlScriptContext sqlScript() {
			return getRuleContext(SqlScriptContext.class,0);
		}
		public TerminalNode RPAREN() { return getToken(PostgreSQLParser.RPAREN, 0); }
		public SubqueryContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_subquery; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PostgreSQLListener ) ((PostgreSQLListener)listener).enterSubquery(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PostgreSQLListener ) ((PostgreSQLListener)listener).exitSubquery(this);
		}
	}

	public final SubqueryContext subquery() throws RecognitionException {
		SubqueryContext _localctx = new SubqueryContext(_ctx, getState());
		enterRule(_localctx, 64, RULE_subquery);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(855);
			match(LPAREN);
			setState(856);
			sqlScript();
			setState(857);
			match(RPAREN);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ComparisonOperatorContext extends ParserRuleContext {
		public TerminalNode EQUALS() { return getToken(PostgreSQLParser.EQUALS, 0); }
		public ComparisonOperatorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_comparisonOperator; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PostgreSQLListener ) ((PostgreSQLListener)listener).enterComparisonOperator(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PostgreSQLListener ) ((PostgreSQLListener)listener).exitComparisonOperator(this);
		}
	}

	public final ComparisonOperatorContext comparisonOperator() throws RecognitionException {
		ComparisonOperatorContext _localctx = new ComparisonOperatorContext(_ctx, getState());
		enterRule(_localctx, 66, RULE_comparisonOperator);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(859);
			_la = _input.LA(1);
			if ( !(((((_la - 64)) & ~0x3f) == 0 && ((1L << (_la - 64)) & 4095L) != 0) || _la==EQUALS) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class SqlScriptContext extends ParserRuleContext {
		public TerminalNode EOF() { return getToken(PostgreSQLParser.EOF, 0); }
		public List<CreateTableStatementContext> createTableStatement() {
			return getRuleContexts(CreateTableStatementContext.class);
		}
		public CreateTableStatementContext createTableStatement(int i) {
			return getRuleContext(CreateTableStatementContext.class,i);
		}
		public List<CreateSchemaStatementContext> createSchemaStatement() {
			return getRuleContexts(CreateSchemaStatementContext.class);
		}
		public CreateSchemaStatementContext createSchemaStatement(int i) {
			return getRuleContext(CreateSchemaStatementContext.class,i);
		}
		public List<CreateViewStatementContext> createViewStatement() {
			return getRuleContexts(CreateViewStatementContext.class);
		}
		public CreateViewStatementContext createViewStatement(int i) {
			return getRuleContext(CreateViewStatementContext.class,i);
		}
		public List<CreateFunctionStatementContext> createFunctionStatement() {
			return getRuleContexts(CreateFunctionStatementContext.class);
		}
		public CreateFunctionStatementContext createFunctionStatement(int i) {
			return getRuleContext(CreateFunctionStatementContext.class,i);
		}
		public List<CreateIndexStatementContext> createIndexStatement() {
			return getRuleContexts(CreateIndexStatementContext.class);
		}
		public CreateIndexStatementContext createIndexStatement(int i) {
			return getRuleContext(CreateIndexStatementContext.class,i);
		}
		public List<CreateRuleStatementContext> createRuleStatement() {
			return getRuleContexts(CreateRuleStatementContext.class);
		}
		public CreateRuleStatementContext createRuleStatement(int i) {
			return getRuleContext(CreateRuleStatementContext.class,i);
		}
		public List<TriggerStatementContext> triggerStatement() {
			return getRuleContexts(TriggerStatementContext.class);
		}
		public TriggerStatementContext triggerStatement(int i) {
			return getRuleContext(TriggerStatementContext.class,i);
		}
		public List<AlterTableStatementContext> alterTableStatement() {
			return getRuleContexts(AlterTableStatementContext.class);
		}
		public AlterTableStatementContext alterTableStatement(int i) {
			return getRuleContext(AlterTableStatementContext.class,i);
		}
		public List<DataManipulationStatementContext> dataManipulationStatement() {
			return getRuleContexts(DataManipulationStatementContext.class);
		}
		public DataManipulationStatementContext dataManipulationStatement(int i) {
			return getRuleContext(DataManipulationStatementContext.class,i);
		}
		public List<CreatePolicyStatementContext> createPolicyStatement() {
			return getRuleContexts(CreatePolicyStatementContext.class);
		}
		public CreatePolicyStatementContext createPolicyStatement(int i) {
			return getRuleContext(CreatePolicyStatementContext.class,i);
		}
		public SqlScriptContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_sqlScript; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PostgreSQLListener ) ((PostgreSQLListener)listener).enterSqlScript(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PostgreSQLListener ) ((PostgreSQLListener)listener).exitSqlScript(this);
		}
	}

	public final SqlScriptContext sqlScript() throws RecognitionException {
		SqlScriptContext _localctx = new SqlScriptContext(_ctx, getState());
		enterRule(_localctx, 68, RULE_sqlScript);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(873);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (((((_la - 16)) & ~0x3f) == 0 && ((1L << (_la - 16)) & 8070626454108372993L) != 0) || _la==CREATE || _la==UPDATE) {
				{
				setState(871);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,76,_ctx) ) {
				case 1:
					{
					setState(861);
					createTableStatement();
					}
					break;
				case 2:
					{
					setState(862);
					createSchemaStatement();
					}
					break;
				case 3:
					{
					setState(863);
					createViewStatement();
					}
					break;
				case 4:
					{
					setState(864);
					createFunctionStatement();
					}
					break;
				case 5:
					{
					setState(865);
					createIndexStatement();
					}
					break;
				case 6:
					{
					setState(866);
					createRuleStatement();
					}
					break;
				case 7:
					{
					setState(867);
					triggerStatement();
					}
					break;
				case 8:
					{
					setState(868);
					alterTableStatement();
					}
					break;
				case 9:
					{
					setState(869);
					dataManipulationStatement();
					}
					break;
				case 10:
					{
					setState(870);
					createPolicyStatement();
					}
					break;
				}
				}
				setState(875);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(876);
			match(EOF);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class CreateSchemaStatementContext extends ParserRuleContext {
		public TerminalNode IDENTIFIER() { return getToken(PostgreSQLParser.IDENTIFIER, 0); }
		public TerminalNode SEMICOLON() { return getToken(PostgreSQLParser.SEMICOLON, 0); }
		public CreateSchemaStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_createSchemaStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PostgreSQLListener ) ((PostgreSQLListener)listener).enterCreateSchemaStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PostgreSQLListener ) ((PostgreSQLListener)listener).exitCreateSchemaStatement(this);
		}
	}

	public final CreateSchemaStatementContext createSchemaStatement() throws RecognitionException {
		CreateSchemaStatementContext _localctx = new CreateSchemaStatementContext(_ctx, getState());
		enterRule(_localctx, 70, RULE_createSchemaStatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(878);
			match(T__75);
			setState(879);
			match(IDENTIFIER);
			setState(880);
			match(SEMICOLON);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class CreateViewStatementContext extends ParserRuleContext {
		public TerminalNode IDENTIFIER() { return getToken(PostgreSQLParser.IDENTIFIER, 0); }
		public TerminalNode AS() { return getToken(PostgreSQLParser.AS, 0); }
		public SelectStatementContext selectStatement() {
			return getRuleContext(SelectStatementContext.class,0);
		}
		public TerminalNode SEMICOLON() { return getToken(PostgreSQLParser.SEMICOLON, 0); }
		public CreateViewStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_createViewStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PostgreSQLListener ) ((PostgreSQLListener)listener).enterCreateViewStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PostgreSQLListener ) ((PostgreSQLListener)listener).exitCreateViewStatement(this);
		}
	}

	public final CreateViewStatementContext createViewStatement() throws RecognitionException {
		CreateViewStatementContext _localctx = new CreateViewStatementContext(_ctx, getState());
		enterRule(_localctx, 72, RULE_createViewStatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(882);
			match(T__76);
			setState(883);
			match(IDENTIFIER);
			setState(884);
			match(AS);
			setState(885);
			selectStatement();
			setState(886);
			match(SEMICOLON);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class CreateFunctionStatementContext extends ParserRuleContext {
		public List<TerminalNode> IDENTIFIER() { return getTokens(PostgreSQLParser.IDENTIFIER); }
		public TerminalNode IDENTIFIER(int i) {
			return getToken(PostgreSQLParser.IDENTIFIER, i);
		}
		public TerminalNode LPAREN() { return getToken(PostgreSQLParser.LPAREN, 0); }
		public TerminalNode RPAREN() { return getToken(PostgreSQLParser.RPAREN, 0); }
		public DataTypeContext dataType() {
			return getRuleContext(DataTypeContext.class,0);
		}
		public TerminalNode AS() { return getToken(PostgreSQLParser.AS, 0); }
		public List<TerminalNode> DOLLAR_QUOTE() { return getTokens(PostgreSQLParser.DOLLAR_QUOTE); }
		public TerminalNode DOLLAR_QUOTE(int i) {
			return getToken(PostgreSQLParser.DOLLAR_QUOTE, i);
		}
		public PlpgsqlBlockContext plpgsqlBlock() {
			return getRuleContext(PlpgsqlBlockContext.class,0);
		}
		public TerminalNode SEMICOLON() { return getToken(PostgreSQLParser.SEMICOLON, 0); }
		public List<ParameterContext> parameter() {
			return getRuleContexts(ParameterContext.class);
		}
		public ParameterContext parameter(int i) {
			return getRuleContext(ParameterContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(PostgreSQLParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(PostgreSQLParser.COMMA, i);
		}
		public CreateFunctionStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_createFunctionStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PostgreSQLListener ) ((PostgreSQLListener)listener).enterCreateFunctionStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PostgreSQLListener ) ((PostgreSQLListener)listener).exitCreateFunctionStatement(this);
		}
	}

	public final CreateFunctionStatementContext createFunctionStatement() throws RecognitionException {
		CreateFunctionStatementContext _localctx = new CreateFunctionStatementContext(_ctx, getState());
		enterRule(_localctx, 74, RULE_createFunctionStatement);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(888);
			match(T__77);
			setState(889);
			match(IDENTIFIER);
			setState(890);
			match(LPAREN);
			setState(899);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==IDENTIFIER) {
				{
				setState(891);
				parameter();
				setState(896);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(892);
					match(COMMA);
					setState(893);
					parameter();
					}
					}
					setState(898);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
			}

			setState(901);
			match(RPAREN);
			setState(902);
			match(T__78);
			setState(903);
			dataType();
			setState(904);
			match(AS);
			setState(905);
			match(DOLLAR_QUOTE);
			setState(906);
			plpgsqlBlock();
			setState(907);
			match(DOLLAR_QUOTE);
			setState(908);
			match(T__79);
			setState(909);
			match(IDENTIFIER);
			setState(910);
			match(SEMICOLON);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ParameterContext extends ParserRuleContext {
		public TerminalNode IDENTIFIER() { return getToken(PostgreSQLParser.IDENTIFIER, 0); }
		public DataTypeContext dataType() {
			return getRuleContext(DataTypeContext.class,0);
		}
		public ParameterContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_parameter; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PostgreSQLListener ) ((PostgreSQLListener)listener).enterParameter(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PostgreSQLListener ) ((PostgreSQLListener)listener).exitParameter(this);
		}
	}

	public final ParameterContext parameter() throws RecognitionException {
		ParameterContext _localctx = new ParameterContext(_ctx, getState());
		enterRule(_localctx, 76, RULE_parameter);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(912);
			match(IDENTIFIER);
			setState(913);
			dataType();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class SelectStatementContext extends ParserRuleContext {
		public TerminalNode SELECT() { return getToken(PostgreSQLParser.SELECT, 0); }
		public SelectListContext selectList() {
			return getRuleContext(SelectListContext.class,0);
		}
		public List<TableReferenceContext> tableReference() {
			return getRuleContexts(TableReferenceContext.class);
		}
		public TableReferenceContext tableReference(int i) {
			return getRuleContext(TableReferenceContext.class,i);
		}
		public WhereClauseContext whereClause() {
			return getRuleContext(WhereClauseContext.class,0);
		}
		public OrderByClauseContext orderByClause() {
			return getRuleContext(OrderByClauseContext.class,0);
		}
		public LimitClauseContext limitClause() {
			return getRuleContext(LimitClauseContext.class,0);
		}
		public List<TerminalNode> COMMA() { return getTokens(PostgreSQLParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(PostgreSQLParser.COMMA, i);
		}
		public SelectStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_selectStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PostgreSQLListener ) ((PostgreSQLListener)listener).enterSelectStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PostgreSQLListener ) ((PostgreSQLListener)listener).exitSelectStatement(this);
		}
	}

	public final SelectStatementContext selectStatement() throws RecognitionException {
		SelectStatementContext _localctx = new SelectStatementContext(_ctx, getState());
		enterRule(_localctx, 78, RULE_selectStatement);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(915);
			match(SELECT);
			setState(916);
			selectList();
			setState(926);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__80) {
				{
				setState(917);
				match(T__80);
				setState(918);
				tableReference();
				setState(923);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(919);
					match(COMMA);
					setState(920);
					tableReference();
					}
					}
					setState(925);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
			}

			setState(929);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__50) {
				{
				setState(928);
				whereClause();
				}
			}

			setState(932);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__82) {
				{
				setState(931);
				orderByClause();
				}
			}

			setState(935);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__85) {
				{
				setState(934);
				limitClause();
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class SelectListContext extends ParserRuleContext {
		public List<ColumnNameContext> columnName() {
			return getRuleContexts(ColumnNameContext.class);
		}
		public ColumnNameContext columnName(int i) {
			return getRuleContext(ColumnNameContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(PostgreSQLParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(PostgreSQLParser.COMMA, i);
		}
		public SelectListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_selectList; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PostgreSQLListener ) ((PostgreSQLListener)listener).enterSelectList(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PostgreSQLListener ) ((PostgreSQLListener)listener).exitSelectList(this);
		}
	}

	public final SelectListContext selectList() throws RecognitionException {
		SelectListContext _localctx = new SelectListContext(_ctx, getState());
		enterRule(_localctx, 80, RULE_selectList);
		int _la;
		try {
			setState(946);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__81:
				enterOuterAlt(_localctx, 1);
				{
				setState(937);
				match(T__81);
				}
				break;
			case IDENTIFIER:
				enterOuterAlt(_localctx, 2);
				{
				setState(938);
				columnName();
				setState(943);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(939);
					match(COMMA);
					setState(940);
					columnName();
					}
					}
					setState(945);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class TableReferenceContext extends ParserRuleContext {
		public TableNameContext tableName() {
			return getRuleContext(TableNameContext.class,0);
		}
		public TerminalNode AS() { return getToken(PostgreSQLParser.AS, 0); }
		public TerminalNode IDENTIFIER() { return getToken(PostgreSQLParser.IDENTIFIER, 0); }
		public TableReferenceContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_tableReference; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PostgreSQLListener ) ((PostgreSQLListener)listener).enterTableReference(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PostgreSQLListener ) ((PostgreSQLListener)listener).exitTableReference(this);
		}
	}

	public final TableReferenceContext tableReference() throws RecognitionException {
		TableReferenceContext _localctx = new TableReferenceContext(_ctx, getState());
		enterRule(_localctx, 82, RULE_tableReference);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(948);
			tableName();
			setState(951);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==AS) {
				{
				setState(949);
				match(AS);
				setState(950);
				match(IDENTIFIER);
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class WhereClauseContext extends ParserRuleContext {
		public ConditionContext condition() {
			return getRuleContext(ConditionContext.class,0);
		}
		public WhereClauseContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_whereClause; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PostgreSQLListener ) ((PostgreSQLListener)listener).enterWhereClause(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PostgreSQLListener ) ((PostgreSQLListener)listener).exitWhereClause(this);
		}
	}

	public final WhereClauseContext whereClause() throws RecognitionException {
		WhereClauseContext _localctx = new WhereClauseContext(_ctx, getState());
		enterRule(_localctx, 84, RULE_whereClause);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(953);
			match(T__50);
			setState(954);
			condition();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class OrderByClauseContext extends ParserRuleContext {
		public ColumnNameContext columnName() {
			return getRuleContext(ColumnNameContext.class,0);
		}
		public OrderByClauseContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_orderByClause; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PostgreSQLListener ) ((PostgreSQLListener)listener).enterOrderByClause(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PostgreSQLListener ) ((PostgreSQLListener)listener).exitOrderByClause(this);
		}
	}

	public final OrderByClauseContext orderByClause() throws RecognitionException {
		OrderByClauseContext _localctx = new OrderByClauseContext(_ctx, getState());
		enterRule(_localctx, 86, RULE_orderByClause);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(956);
			match(T__82);
			setState(957);
			columnName();
			setState(959);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__83 || _la==T__84) {
				{
				setState(958);
				_la = _input.LA(1);
				if ( !(_la==T__83 || _la==T__84) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class LimitClauseContext extends ParserRuleContext {
		public TerminalNode NUMBER() { return getToken(PostgreSQLParser.NUMBER, 0); }
		public LimitClauseContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_limitClause; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PostgreSQLListener ) ((PostgreSQLListener)listener).enterLimitClause(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PostgreSQLListener ) ((PostgreSQLListener)listener).exitLimitClause(this);
		}
	}

	public final LimitClauseContext limitClause() throws RecognitionException {
		LimitClauseContext _localctx = new LimitClauseContext(_ctx, getState());
		enterRule(_localctx, 88, RULE_limitClause);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(961);
			match(T__85);
			setState(962);
			match(NUMBER);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ColumnNameListContext extends ParserRuleContext {
		public List<ColumnNameContext> columnName() {
			return getRuleContexts(ColumnNameContext.class);
		}
		public ColumnNameContext columnName(int i) {
			return getRuleContext(ColumnNameContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(PostgreSQLParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(PostgreSQLParser.COMMA, i);
		}
		public ColumnNameListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_columnNameList; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PostgreSQLListener ) ((PostgreSQLListener)listener).enterColumnNameList(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PostgreSQLListener ) ((PostgreSQLListener)listener).exitColumnNameList(this);
		}
	}

	public final ColumnNameListContext columnNameList() throws RecognitionException {
		ColumnNameListContext _localctx = new ColumnNameListContext(_ctx, getState());
		enterRule(_localctx, 90, RULE_columnNameList);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(964);
			columnName();
			setState(969);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(965);
				match(COMMA);
				setState(966);
				columnName();
				}
				}
				setState(971);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ConflictActionContext extends ParserRuleContext {
		public TerminalNode SET() { return getToken(PostgreSQLParser.SET, 0); }
		public ColumnNameContext columnName() {
			return getRuleContext(ColumnNameContext.class,0);
		}
		public TerminalNode EQUALS() { return getToken(PostgreSQLParser.EQUALS, 0); }
		public ValueContext value() {
			return getRuleContext(ValueContext.class,0);
		}
		public ConditionContext condition() {
			return getRuleContext(ConditionContext.class,0);
		}
		public ConflictActionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_conflictAction; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PostgreSQLListener ) ((PostgreSQLListener)listener).enterConflictAction(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PostgreSQLListener ) ((PostgreSQLListener)listener).exitConflictAction(this);
		}
	}

	public final ConflictActionContext conflictAction() throws RecognitionException {
		ConflictActionContext _localctx = new ConflictActionContext(_ctx, getState());
		enterRule(_localctx, 92, RULE_conflictAction);
		int _la;
		try {
			setState(982);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__86:
				enterOuterAlt(_localctx, 1);
				{
				setState(972);
				match(T__86);
				}
				break;
			case T__87:
				enterOuterAlt(_localctx, 2);
				{
				setState(973);
				match(T__87);
				setState(974);
				match(SET);
				setState(975);
				columnName();
				setState(976);
				match(EQUALS);
				setState(977);
				value();
				setState(980);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__50) {
					{
					setState(978);
					match(T__50);
					setState(979);
					condition();
					}
				}

				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class SchemaNameContext extends ParserRuleContext {
		public TerminalNode IDENTIFIER() { return getToken(PostgreSQLParser.IDENTIFIER, 0); }
		public SchemaNameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_schemaName; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PostgreSQLListener ) ((PostgreSQLListener)listener).enterSchemaName(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PostgreSQLListener ) ((PostgreSQLListener)listener).exitSchemaName(this);
		}
	}

	public final SchemaNameContext schemaName() throws RecognitionException {
		SchemaNameContext _localctx = new SchemaNameContext(_ctx, getState());
		enterRule(_localctx, 94, RULE_schemaName);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(984);
			match(IDENTIFIER);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class TableNameContext extends ParserRuleContext {
		public TerminalNode IDENTIFIER() { return getToken(PostgreSQLParser.IDENTIFIER, 0); }
		public SchemaNameContext schemaName() {
			return getRuleContext(SchemaNameContext.class,0);
		}
		public TerminalNode DOT() { return getToken(PostgreSQLParser.DOT, 0); }
		public TableNameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_tableName; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PostgreSQLListener ) ((PostgreSQLListener)listener).enterTableName(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PostgreSQLListener ) ((PostgreSQLListener)listener).exitTableName(this);
		}
	}

	public final TableNameContext tableName() throws RecognitionException {
		TableNameContext _localctx = new TableNameContext(_ctx, getState());
		enterRule(_localctx, 96, RULE_tableName);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(989);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,92,_ctx) ) {
			case 1:
				{
				setState(986);
				schemaName();
				setState(987);
				match(DOT);
				}
				break;
			}
			setState(991);
			match(IDENTIFIER);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ColumnNameContext extends ParserRuleContext {
		public TerminalNode IDENTIFIER() { return getToken(PostgreSQLParser.IDENTIFIER, 0); }
		public ColumnNameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_columnName; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PostgreSQLListener ) ((PostgreSQLListener)listener).enterColumnName(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PostgreSQLListener ) ((PostgreSQLListener)listener).exitColumnName(this);
		}
	}

	public final ColumnNameContext columnName() throws RecognitionException {
		ColumnNameContext _localctx = new ColumnNameContext(_ctx, getState());
		enterRule(_localctx, 98, RULE_columnName);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(993);
			match(IDENTIFIER);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class RelationshipTypeContext extends ParserRuleContext {
		public TerminalNode MANYTOMANY() { return getToken(PostgreSQLParser.MANYTOMANY, 0); }
		public RelationshipTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_relationshipType; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PostgreSQLListener ) ((PostgreSQLListener)listener).enterRelationshipType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PostgreSQLListener ) ((PostgreSQLListener)listener).exitRelationshipType(this);
		}
	}

	public final RelationshipTypeContext relationshipType() throws RecognitionException {
		RelationshipTypeContext _localctx = new RelationshipTypeContext(_ctx, getState());
		enterRule(_localctx, 100, RULE_relationshipType);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(995);
			_la = _input.LA(1);
			if ( !(((((_la - 89)) & ~0x3f) == 0 && ((1L << (_la - 89)) & 7L) != 0) || _la==MANYTOMANY) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class DecimalTypeContext extends ParserRuleContext {
		public Token precision;
		public Token scale;
		public TerminalNode LPAREN() { return getToken(PostgreSQLParser.LPAREN, 0); }
		public TerminalNode RPAREN() { return getToken(PostgreSQLParser.RPAREN, 0); }
		public List<TerminalNode> NUMBER() { return getTokens(PostgreSQLParser.NUMBER); }
		public TerminalNode NUMBER(int i) {
			return getToken(PostgreSQLParser.NUMBER, i);
		}
		public TerminalNode COMMA() { return getToken(PostgreSQLParser.COMMA, 0); }
		public DecimalTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_decimalType; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PostgreSQLListener ) ((PostgreSQLListener)listener).enterDecimalType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PostgreSQLListener ) ((PostgreSQLListener)listener).exitDecimalType(this);
		}
	}

	public final DecimalTypeContext decimalType() throws RecognitionException {
		DecimalTypeContext _localctx = new DecimalTypeContext(_ctx, getState());
		enterRule(_localctx, 102, RULE_decimalType);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(997);
			_la = _input.LA(1);
			if ( !(_la==T__91 || _la==T__92) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			setState(1005);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,94,_ctx) ) {
			case 1:
				{
				setState(998);
				match(LPAREN);
				setState(999);
				((DecimalTypeContext)_localctx).precision = match(NUMBER);
				setState(1002);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==COMMA) {
					{
					setState(1000);
					match(COMMA);
					setState(1001);
					((DecimalTypeContext)_localctx).scale = match(NUMBER);
					}
				}

				setState(1004);
				match(RPAREN);
				}
				break;
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class PartitionClauseContext extends ParserRuleContext {
		public PartitionStrategyContext partitionStrategy() {
			return getRuleContext(PartitionStrategyContext.class,0);
		}
		public TerminalNode LPAREN() { return getToken(PostgreSQLParser.LPAREN, 0); }
		public ColumnNameContext columnName() {
			return getRuleContext(ColumnNameContext.class,0);
		}
		public TerminalNode RPAREN() { return getToken(PostgreSQLParser.RPAREN, 0); }
		public PartitionClauseContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_partitionClause; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PostgreSQLListener ) ((PostgreSQLListener)listener).enterPartitionClause(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PostgreSQLListener ) ((PostgreSQLListener)listener).exitPartitionClause(this);
		}
	}

	public final PartitionClauseContext partitionClause() throws RecognitionException {
		PartitionClauseContext _localctx = new PartitionClauseContext(_ctx, getState());
		enterRule(_localctx, 104, RULE_partitionClause);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1007);
			match(T__93);
			setState(1008);
			partitionStrategy();
			setState(1009);
			match(LPAREN);
			setState(1010);
			columnName();
			setState(1011);
			match(RPAREN);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class PartitionStrategyContext extends ParserRuleContext {
		public PartitionStrategyContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_partitionStrategy; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PostgreSQLListener ) ((PostgreSQLListener)listener).enterPartitionStrategy(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PostgreSQLListener ) ((PostgreSQLListener)listener).exitPartitionStrategy(this);
		}
	}

	public final PartitionStrategyContext partitionStrategy() throws RecognitionException {
		PartitionStrategyContext _localctx = new PartitionStrategyContext(_ctx, getState());
		enterRule(_localctx, 106, RULE_partitionStrategy);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1013);
			_la = _input.LA(1);
			if ( !(((((_la - 95)) & ~0x3f) == 0 && ((1L << (_la - 95)) & 7L) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class PartitionValuesClauseContext extends ParserRuleContext {
		public List<TerminalNode> LPAREN() { return getTokens(PostgreSQLParser.LPAREN); }
		public TerminalNode LPAREN(int i) {
			return getToken(PostgreSQLParser.LPAREN, i);
		}
		public List<ValueContext> value() {
			return getRuleContexts(ValueContext.class);
		}
		public ValueContext value(int i) {
			return getRuleContext(ValueContext.class,i);
		}
		public List<TerminalNode> RPAREN() { return getTokens(PostgreSQLParser.RPAREN); }
		public TerminalNode RPAREN(int i) {
			return getToken(PostgreSQLParser.RPAREN, i);
		}
		public TerminalNode TO() { return getToken(PostgreSQLParser.TO, 0); }
		public TerminalNode IN() { return getToken(PostgreSQLParser.IN, 0); }
		public List<TerminalNode> NUMBER() { return getTokens(PostgreSQLParser.NUMBER); }
		public TerminalNode NUMBER(int i) {
			return getToken(PostgreSQLParser.NUMBER, i);
		}
		public List<TerminalNode> COMMA() { return getTokens(PostgreSQLParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(PostgreSQLParser.COMMA, i);
		}
		public PartitionValuesClauseContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_partitionValuesClause; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PostgreSQLListener ) ((PostgreSQLListener)listener).enterPartitionValuesClause(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PostgreSQLListener ) ((PostgreSQLListener)listener).exitPartitionValuesClause(this);
		}
	}

	public final PartitionValuesClauseContext partitionValuesClause() throws RecognitionException {
		PartitionValuesClauseContext _localctx = new PartitionValuesClauseContext(_ctx, getState());
		enterRule(_localctx, 108, RULE_partitionValuesClause);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1015);
			match(T__97);
			setState(1041);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__80:
				{
				setState(1016);
				match(T__80);
				setState(1017);
				match(LPAREN);
				setState(1018);
				value();
				setState(1019);
				match(RPAREN);
				setState(1020);
				match(TO);
				setState(1021);
				match(LPAREN);
				setState(1022);
				value();
				setState(1023);
				match(RPAREN);
				}
				break;
			case IN:
				{
				setState(1025);
				match(IN);
				setState(1026);
				match(LPAREN);
				setState(1027);
				value();
				setState(1032);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(1028);
					match(COMMA);
					setState(1029);
					value();
					}
					}
					setState(1034);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(1035);
				match(RPAREN);
				}
				break;
			case T__98:
				{
				setState(1037);
				match(T__98);
				setState(1038);
				match(NUMBER);
				setState(1039);
				match(T__99);
				setState(1040);
				match(NUMBER);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class CreateIndexStatementContext extends ParserRuleContext {
		public TerminalNode CREATE() { return getToken(PostgreSQLParser.CREATE, 0); }
		public List<TerminalNode> IDENTIFIER() { return getTokens(PostgreSQLParser.IDENTIFIER); }
		public TerminalNode IDENTIFIER(int i) {
			return getToken(PostgreSQLParser.IDENTIFIER, i);
		}
		public TerminalNode ON() { return getToken(PostgreSQLParser.ON, 0); }
		public TableNameContext tableName() {
			return getRuleContext(TableNameContext.class,0);
		}
		public TerminalNode LPAREN() { return getToken(PostgreSQLParser.LPAREN, 0); }
		public List<IndexElementContext> indexElement() {
			return getRuleContexts(IndexElementContext.class);
		}
		public IndexElementContext indexElement(int i) {
			return getRuleContext(IndexElementContext.class,i);
		}
		public TerminalNode RPAREN() { return getToken(PostgreSQLParser.RPAREN, 0); }
		public TerminalNode SEMICOLON() { return getToken(PostgreSQLParser.SEMICOLON, 0); }
		public TerminalNode UNIQUE() { return getToken(PostgreSQLParser.UNIQUE, 0); }
		public TerminalNode USING() { return getToken(PostgreSQLParser.USING, 0); }
		public List<TerminalNode> COMMA() { return getTokens(PostgreSQLParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(PostgreSQLParser.COMMA, i);
		}
		public ConditionContext condition() {
			return getRuleContext(ConditionContext.class,0);
		}
		public IndexOptionsClauseContext indexOptionsClause() {
			return getRuleContext(IndexOptionsClauseContext.class,0);
		}
		public CreateIndexStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_createIndexStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PostgreSQLListener ) ((PostgreSQLListener)listener).enterCreateIndexStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PostgreSQLListener ) ((PostgreSQLListener)listener).exitCreateIndexStatement(this);
		}
	}

	public final CreateIndexStatementContext createIndexStatement() throws RecognitionException {
		CreateIndexStatementContext _localctx = new CreateIndexStatementContext(_ctx, getState());
		enterRule(_localctx, 110, RULE_createIndexStatement);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1043);
			match(CREATE);
			setState(1045);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==UNIQUE) {
				{
				setState(1044);
				match(UNIQUE);
				}
			}

			setState(1047);
			match(T__100);
			setState(1048);
			match(IDENTIFIER);
			setState(1049);
			match(ON);
			setState(1050);
			tableName();
			setState(1053);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==USING) {
				{
				setState(1051);
				match(USING);
				setState(1052);
				match(IDENTIFIER);
				}
			}

			setState(1055);
			match(LPAREN);
			setState(1056);
			indexElement();
			setState(1061);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(1057);
				match(COMMA);
				setState(1058);
				indexElement();
				}
				}
				setState(1063);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(1064);
			match(RPAREN);
			setState(1067);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__50) {
				{
				setState(1065);
				match(T__50);
				setState(1066);
				condition();
				}
			}

			setState(1070);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__22) {
				{
				setState(1069);
				indexOptionsClause();
				}
			}

			setState(1072);
			match(SEMICOLON);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class IndexElementContext extends ParserRuleContext {
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode IDENTIFIER() { return getToken(PostgreSQLParser.IDENTIFIER, 0); }
		public IndexElementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_indexElement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PostgreSQLListener ) ((PostgreSQLListener)listener).enterIndexElement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PostgreSQLListener ) ((PostgreSQLListener)listener).exitIndexElement(this);
		}
	}

	public final IndexElementContext indexElement() throws RecognitionException {
		IndexElementContext _localctx = new IndexElementContext(_ctx, getState());
		enterRule(_localctx, 112, RULE_indexElement);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1074);
			expression();
			setState(1076);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==IDENTIFIER) {
				{
				setState(1075);
				match(IDENTIFIER);
				}
			}

			setState(1079);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__83 || _la==T__84) {
				{
				setState(1078);
				_la = _input.LA(1);
				if ( !(_la==T__83 || _la==T__84) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class IndexOptionsClauseContext extends ParserRuleContext {
		public TerminalNode IDENTIFIER() { return getToken(PostgreSQLParser.IDENTIFIER, 0); }
		public IndexOptionsClauseContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_indexOptionsClause; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PostgreSQLListener ) ((PostgreSQLListener)listener).enterIndexOptionsClause(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PostgreSQLListener ) ((PostgreSQLListener)listener).exitIndexOptionsClause(this);
		}
	}

	public final IndexOptionsClauseContext indexOptionsClause() throws RecognitionException {
		IndexOptionsClauseContext _localctx = new IndexOptionsClauseContext(_ctx, getState());
		enterRule(_localctx, 114, RULE_indexOptionsClause);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1081);
			match(T__22);
			setState(1082);
			match(IDENTIFIER);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ExpressionContext extends ParserRuleContext {
		public TerminalNode LPAREN() { return getToken(PostgreSQLParser.LPAREN, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode RPAREN() { return getToken(PostgreSQLParser.RPAREN, 0); }
		public TypeCastContext typeCast() {
			return getRuleContext(TypeCastContext.class,0);
		}
		public AnyExpressionContext anyExpression() {
			return getRuleContext(AnyExpressionContext.class,0);
		}
		public FunctionCallContext functionCall() {
			return getRuleContext(FunctionCallContext.class,0);
		}
		public ColumnNameContext columnName() {
			return getRuleContext(ColumnNameContext.class,0);
		}
		public ValueContext value() {
			return getRuleContext(ValueContext.class,0);
		}
		public ExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PostgreSQLListener ) ((PostgreSQLListener)listener).enterExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PostgreSQLListener ) ((PostgreSQLListener)listener).exitExpression(this);
		}
	}

	public final ExpressionContext expression() throws RecognitionException {
		ExpressionContext _localctx = new ExpressionContext(_ctx, getState());
		enterRule(_localctx, 116, RULE_expression);
		try {
			setState(1100);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,107,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(1084);
				match(LPAREN);
				setState(1085);
				expression();
				setState(1086);
				match(RPAREN);
				setState(1088);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,104,_ctx) ) {
				case 1:
					{
					setState(1087);
					typeCast();
					}
					break;
				}
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(1090);
				anyExpression();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(1091);
				functionCall();
				setState(1093);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,105,_ctx) ) {
				case 1:
					{
					setState(1092);
					typeCast();
					}
					break;
				}
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(1095);
				columnName();
				setState(1097);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,106,_ctx) ) {
				case 1:
					{
					setState(1096);
					typeCast();
					}
					break;
				}
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(1099);
				value();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ValueAtomContext extends ParserRuleContext {
		public TerminalNode STRING() { return getToken(PostgreSQLParser.STRING, 0); }
		public NumericLiteralContext numericLiteral() {
			return getRuleContext(NumericLiteralContext.class,0);
		}
		public BooleanLiteralContext booleanLiteral() {
			return getRuleContext(BooleanLiteralContext.class,0);
		}
		public TerminalNode NULL() { return getToken(PostgreSQLParser.NULL, 0); }
		public TerminalNode IDENTIFIER() { return getToken(PostgreSQLParser.IDENTIFIER, 0); }
		public TerminalNode LPAREN() { return getToken(PostgreSQLParser.LPAREN, 0); }
		public TerminalNode RPAREN() { return getToken(PostgreSQLParser.RPAREN, 0); }
		public ValueListContext valueList() {
			return getRuleContext(ValueListContext.class,0);
		}
		public ValueAtomContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_valueAtom; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PostgreSQLListener ) ((PostgreSQLListener)listener).enterValueAtom(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PostgreSQLListener ) ((PostgreSQLListener)listener).exitValueAtom(this);
		}
	}

	public final ValueAtomContext valueAtom() throws RecognitionException {
		ValueAtomContext _localctx = new ValueAtomContext(_ctx, getState());
		enterRule(_localctx, 118, RULE_valueAtom);
		int _la;
		try {
			setState(1115);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case STRING:
				enterOuterAlt(_localctx, 1);
				{
				setState(1102);
				match(STRING);
				}
				break;
			case T__111:
			case NUMBER:
				enterOuterAlt(_localctx, 2);
				{
				setState(1103);
				numericLiteral();
				}
				break;
			case T__112:
			case T__113:
			case T__114:
			case T__115:
				enterOuterAlt(_localctx, 3);
				{
				setState(1104);
				booleanLiteral();
				}
				break;
			case T__48:
				enterOuterAlt(_localctx, 4);
				{
				setState(1105);
				match(T__48);
				}
				break;
			case NULL:
				enterOuterAlt(_localctx, 5);
				{
				setState(1106);
				match(NULL);
				}
				break;
			case T__101:
			case IDENTIFIER:
				enterOuterAlt(_localctx, 6);
				{
				setState(1107);
				_la = _input.LA(1);
				if ( !(_la==T__101 || _la==IDENTIFIER) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(1113);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,109,_ctx) ) {
				case 1:
					{
					setState(1108);
					match(LPAREN);
					setState(1110);
					_errHandler.sync(this);
					_la = _input.LA(1);
					if (_la==T__48 || ((((_la - 102)) & ~0x3f) == 0 && ((1L << (_la - 102)) & 144185556820098055L) != 0) || ((((_la - 234)) & ~0x3f) == 0 && ((1L << (_la - 234)) & 7L) != 0)) {
						{
						setState(1109);
						valueList();
						}
					}

					setState(1112);
					match(RPAREN);
					}
					break;
				}
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class TypeCastContext extends ParserRuleContext {
		public TerminalNode DOUBLE_COLON() { return getToken(PostgreSQLParser.DOUBLE_COLON, 0); }
		public DataTypeContext dataType() {
			return getRuleContext(DataTypeContext.class,0);
		}
		public List<TerminalNode> LBRACKET() { return getTokens(PostgreSQLParser.LBRACKET); }
		public TerminalNode LBRACKET(int i) {
			return getToken(PostgreSQLParser.LBRACKET, i);
		}
		public List<TerminalNode> RBRACKET() { return getTokens(PostgreSQLParser.RBRACKET); }
		public TerminalNode RBRACKET(int i) {
			return getToken(PostgreSQLParser.RBRACKET, i);
		}
		public TypeCastContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_typeCast; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PostgreSQLListener ) ((PostgreSQLListener)listener).enterTypeCast(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PostgreSQLListener ) ((PostgreSQLListener)listener).exitTypeCast(this);
		}
	}

	public final TypeCastContext typeCast() throws RecognitionException {
		TypeCastContext _localctx = new TypeCastContext(_ctx, getState());
		enterRule(_localctx, 120, RULE_typeCast);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(1117);
			match(DOUBLE_COLON);
			setState(1118);
			dataType();
			setState(1123);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,111,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(1119);
					match(LBRACKET);
					setState(1120);
					match(RBRACKET);
					}
					} 
				}
				setState(1125);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,111,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ValueListContext extends ParserRuleContext {
		public List<ValueContext> value() {
			return getRuleContexts(ValueContext.class);
		}
		public ValueContext value(int i) {
			return getRuleContext(ValueContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(PostgreSQLParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(PostgreSQLParser.COMMA, i);
		}
		public ValueListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_valueList; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PostgreSQLListener ) ((PostgreSQLListener)listener).enterValueList(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PostgreSQLListener ) ((PostgreSQLListener)listener).exitValueList(this);
		}
	}

	public final ValueListContext valueList() throws RecognitionException {
		ValueListContext _localctx = new ValueListContext(_ctx, getState());
		enterRule(_localctx, 122, RULE_valueList);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1126);
			value();
			setState(1131);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(1127);
				match(COMMA);
				setState(1128);
				value();
				}
				}
				setState(1133);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ValueContext extends ParserRuleContext {
		public NumericLiteralContext numericLiteral() {
			return getRuleContext(NumericLiteralContext.class,0);
		}
		public TypeCastContext typeCast() {
			return getRuleContext(TypeCastContext.class,0);
		}
		public ValueAtomContext valueAtom() {
			return getRuleContext(ValueAtomContext.class,0);
		}
		public ArrayConstructorContext arrayConstructor() {
			return getRuleContext(ArrayConstructorContext.class,0);
		}
		public IntervalLiteralContext intervalLiteral() {
			return getRuleContext(IntervalLiteralContext.class,0);
		}
		public ValueContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_value; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PostgreSQLListener ) ((PostgreSQLListener)listener).enterValue(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PostgreSQLListener ) ((PostgreSQLListener)listener).exitValue(this);
		}
	}

	public final ValueContext value() throws RecognitionException {
		ValueContext _localctx = new ValueContext(_ctx, getState());
		enterRule(_localctx, 124, RULE_value);
		try {
			setState(1149);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,116,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(1134);
				numericLiteral();
				setState(1136);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,113,_ctx) ) {
				case 1:
					{
					setState(1135);
					typeCast();
					}
					break;
				}
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(1138);
				valueAtom();
				setState(1140);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,114,_ctx) ) {
				case 1:
					{
					setState(1139);
					typeCast();
					}
					break;
				}
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(1142);
				arrayConstructor();
				setState(1144);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,115,_ctx) ) {
				case 1:
					{
					setState(1143);
					typeCast();
					}
					break;
				}
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(1146);
				intervalLiteral();
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(1147);
				match(T__102);
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(1148);
				match(T__103);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ExpressionListContext extends ParserRuleContext {
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(PostgreSQLParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(PostgreSQLParser.COMMA, i);
		}
		public ExpressionListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expressionList; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PostgreSQLListener ) ((PostgreSQLListener)listener).enterExpressionList(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PostgreSQLListener ) ((PostgreSQLListener)listener).exitExpressionList(this);
		}
	}

	public final ExpressionListContext expressionList() throws RecognitionException {
		ExpressionListContext _localctx = new ExpressionListContext(_ctx, getState());
		enterRule(_localctx, 126, RULE_expressionList);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1151);
			expression();
			setState(1156);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(1152);
				match(COMMA);
				setState(1153);
				expression();
				}
				}
				setState(1158);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ArrayConstructorContext extends ParserRuleContext {
		public TerminalNode ARRAY() { return getToken(PostgreSQLParser.ARRAY, 0); }
		public TerminalNode LBRACKET() { return getToken(PostgreSQLParser.LBRACKET, 0); }
		public ExpressionListContext expressionList() {
			return getRuleContext(ExpressionListContext.class,0);
		}
		public TerminalNode RBRACKET() { return getToken(PostgreSQLParser.RBRACKET, 0); }
		public TypeCastContext typeCast() {
			return getRuleContext(TypeCastContext.class,0);
		}
		public ArrayConstructorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_arrayConstructor; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PostgreSQLListener ) ((PostgreSQLListener)listener).enterArrayConstructor(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PostgreSQLListener ) ((PostgreSQLListener)listener).exitArrayConstructor(this);
		}
	}

	public final ArrayConstructorContext arrayConstructor() throws RecognitionException {
		ArrayConstructorContext _localctx = new ArrayConstructorContext(_ctx, getState());
		enterRule(_localctx, 128, RULE_arrayConstructor);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1159);
			match(ARRAY);
			setState(1160);
			match(LBRACKET);
			setState(1161);
			expressionList();
			setState(1162);
			match(RBRACKET);
			setState(1164);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,118,_ctx) ) {
			case 1:
				{
				setState(1163);
				typeCast();
				}
				break;
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class CreatePolicyStatementContext extends ParserRuleContext {
		public TerminalNode CREATE() { return getToken(PostgreSQLParser.CREATE, 0); }
		public TerminalNode IDENTIFIER() { return getToken(PostgreSQLParser.IDENTIFIER, 0); }
		public TerminalNode ON() { return getToken(PostgreSQLParser.ON, 0); }
		public TableNameContext tableName() {
			return getRuleContext(TableNameContext.class,0);
		}
		public TerminalNode SEMICOLON() { return getToken(PostgreSQLParser.SEMICOLON, 0); }
		public TerminalNode FOR() { return getToken(PostgreSQLParser.FOR, 0); }
		public TerminalNode USING() { return getToken(PostgreSQLParser.USING, 0); }
		public List<TerminalNode> LPAREN() { return getTokens(PostgreSQLParser.LPAREN); }
		public TerminalNode LPAREN(int i) {
			return getToken(PostgreSQLParser.LPAREN, i);
		}
		public List<ConditionContext> condition() {
			return getRuleContexts(ConditionContext.class);
		}
		public ConditionContext condition(int i) {
			return getRuleContext(ConditionContext.class,i);
		}
		public List<TerminalNode> RPAREN() { return getTokens(PostgreSQLParser.RPAREN); }
		public TerminalNode RPAREN(int i) {
			return getToken(PostgreSQLParser.RPAREN, i);
		}
		public TerminalNode SELECT() { return getToken(PostgreSQLParser.SELECT, 0); }
		public TerminalNode INSERT() { return getToken(PostgreSQLParser.INSERT, 0); }
		public TerminalNode UPDATE() { return getToken(PostgreSQLParser.UPDATE, 0); }
		public TerminalNode DELETE() { return getToken(PostgreSQLParser.DELETE, 0); }
		public CreatePolicyStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_createPolicyStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PostgreSQLListener ) ((PostgreSQLListener)listener).enterCreatePolicyStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PostgreSQLListener ) ((PostgreSQLListener)listener).exitCreatePolicyStatement(this);
		}
	}

	public final CreatePolicyStatementContext createPolicyStatement() throws RecognitionException {
		CreatePolicyStatementContext _localctx = new CreatePolicyStatementContext(_ctx, getState());
		enterRule(_localctx, 130, RULE_createPolicyStatement);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1166);
			match(CREATE);
			setState(1167);
			match(T__104);
			setState(1168);
			match(IDENTIFIER);
			setState(1169);
			match(ON);
			setState(1170);
			tableName();
			setState(1173);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==FOR) {
				{
				setState(1171);
				match(FOR);
				setState(1172);
				_la = _input.LA(1);
				if ( !(((((_la - 106)) & ~0x3f) == 0 && ((1L << (_la - 106)) & 1077936129L) != 0) || _la==UPDATE || _la==INSERT) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				}
			}

			setState(1180);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==USING) {
				{
				setState(1175);
				match(USING);
				setState(1176);
				match(LPAREN);
				setState(1177);
				condition();
				setState(1178);
				match(RPAREN);
				}
			}

			setState(1187);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__106) {
				{
				setState(1182);
				match(T__106);
				setState(1183);
				match(LPAREN);
				setState(1184);
				condition();
				setState(1185);
				match(RPAREN);
				}
			}

			setState(1189);
			match(SEMICOLON);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class PlpgsqlBlockContext extends ParserRuleContext {
		public TerminalNode SEMICOLON() { return getToken(PostgreSQLParser.SEMICOLON, 0); }
		public List<StatementContext> statement() {
			return getRuleContexts(StatementContext.class);
		}
		public StatementContext statement(int i) {
			return getRuleContext(StatementContext.class,i);
		}
		public PlpgsqlBlockContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_plpgsqlBlock; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PostgreSQLListener ) ((PostgreSQLListener)listener).enterPlpgsqlBlock(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PostgreSQLListener ) ((PostgreSQLListener)listener).exitPlpgsqlBlock(this);
		}
	}

	public final PlpgsqlBlockContext plpgsqlBlock() throws RecognitionException {
		PlpgsqlBlockContext _localctx = new PlpgsqlBlockContext(_ctx, getState());
		enterRule(_localctx, 132, RULE_plpgsqlBlock);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1191);
			match(T__107);
			setState(1195);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__109 || _la==NEW || _la==IDENTIFIER) {
				{
				{
				setState(1192);
				statement();
				}
				}
				setState(1197);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(1198);
			match(T__108);
			setState(1199);
			match(SEMICOLON);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class StatementContext extends ParserRuleContext {
		public List<ColumnNameContext> columnName() {
			return getRuleContexts(ColumnNameContext.class);
		}
		public ColumnNameContext columnName(int i) {
			return getRuleContext(ColumnNameContext.class,i);
		}
		public TerminalNode DOT() { return getToken(PostgreSQLParser.DOT, 0); }
		public TerminalNode EQUALS() { return getToken(PostgreSQLParser.EQUALS, 0); }
		public ValueContext value() {
			return getRuleContext(ValueContext.class,0);
		}
		public TerminalNode SEMICOLON() { return getToken(PostgreSQLParser.SEMICOLON, 0); }
		public TerminalNode NEW() { return getToken(PostgreSQLParser.NEW, 0); }
		public StatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_statement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PostgreSQLListener ) ((PostgreSQLListener)listener).enterStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PostgreSQLListener ) ((PostgreSQLListener)listener).exitStatement(this);
		}
	}

	public final StatementContext statement() throws RecognitionException {
		StatementContext _localctx = new StatementContext(_ctx, getState());
		enterRule(_localctx, 134, RULE_statement);
		try {
			setState(1218);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case IDENTIFIER:
				enterOuterAlt(_localctx, 1);
				{
				setState(1201);
				columnName();
				setState(1202);
				match(DOT);
				setState(1203);
				columnName();
				setState(1204);
				match(EQUALS);
				setState(1205);
				value();
				setState(1206);
				match(SEMICOLON);
				}
				break;
			case T__109:
				enterOuterAlt(_localctx, 2);
				{
				setState(1208);
				match(T__109);
				setState(1209);
				match(NEW);
				setState(1210);
				match(SEMICOLON);
				}
				break;
			case NEW:
				enterOuterAlt(_localctx, 3);
				{
				setState(1211);
				match(NEW);
				setState(1212);
				match(DOT);
				setState(1213);
				columnName();
				setState(1214);
				match(EQUALS);
				setState(1215);
				value();
				setState(1216);
				match(SEMICOLON);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class CreateRuleStatementContext extends ParserRuleContext {
		public TerminalNode CREATE() { return getToken(PostgreSQLParser.CREATE, 0); }
		public TerminalNode RULE() { return getToken(PostgreSQLParser.RULE, 0); }
		public TerminalNode IDENTIFIER() { return getToken(PostgreSQLParser.IDENTIFIER, 0); }
		public TerminalNode AS() { return getToken(PostgreSQLParser.AS, 0); }
		public TerminalNode ON() { return getToken(PostgreSQLParser.ON, 0); }
		public RuleEventContext ruleEvent() {
			return getRuleContext(RuleEventContext.class,0);
		}
		public TerminalNode TO() { return getToken(PostgreSQLParser.TO, 0); }
		public TableNameContext tableName() {
			return getRuleContext(TableNameContext.class,0);
		}
		public TerminalNode DO() { return getToken(PostgreSQLParser.DO, 0); }
		public RuleActionContext ruleAction() {
			return getRuleContext(RuleActionContext.class,0);
		}
		public TerminalNode SEMICOLON() { return getToken(PostgreSQLParser.SEMICOLON, 0); }
		public CreateRuleStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_createRuleStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PostgreSQLListener ) ((PostgreSQLListener)listener).enterCreateRuleStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PostgreSQLListener ) ((PostgreSQLListener)listener).exitCreateRuleStatement(this);
		}
	}

	public final CreateRuleStatementContext createRuleStatement() throws RecognitionException {
		CreateRuleStatementContext _localctx = new CreateRuleStatementContext(_ctx, getState());
		enterRule(_localctx, 136, RULE_createRuleStatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1220);
			match(CREATE);
			setState(1221);
			match(RULE);
			setState(1222);
			match(IDENTIFIER);
			setState(1223);
			match(AS);
			setState(1224);
			match(ON);
			setState(1225);
			ruleEvent();
			setState(1226);
			match(TO);
			setState(1227);
			tableName();
			setState(1228);
			match(DO);
			setState(1229);
			ruleAction();
			setState(1230);
			match(SEMICOLON);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class RuleEventContext extends ParserRuleContext {
		public TerminalNode DELETE() { return getToken(PostgreSQLParser.DELETE, 0); }
		public TerminalNode INSERT() { return getToken(PostgreSQLParser.INSERT, 0); }
		public TerminalNode UPDATE() { return getToken(PostgreSQLParser.UPDATE, 0); }
		public TerminalNode SELECT() { return getToken(PostgreSQLParser.SELECT, 0); }
		public RuleEventContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ruleEvent; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PostgreSQLListener ) ((PostgreSQLListener)listener).enterRuleEvent(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PostgreSQLListener ) ((PostgreSQLListener)listener).exitRuleEvent(this);
		}
	}

	public final RuleEventContext ruleEvent() throws RecognitionException {
		RuleEventContext _localctx = new RuleEventContext(_ctx, getState());
		enterRule(_localctx, 138, RULE_ruleEvent);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1232);
			_la = _input.LA(1);
			if ( !(((((_la - 128)) & ~0x3f) == 0 && ((1L << (_la - 128)) & 6755399441056001L) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class RuleActionContext extends ParserRuleContext {
		public TerminalNode INSTEAD() { return getToken(PostgreSQLParser.INSTEAD, 0); }
		public SqlActionContext sqlAction() {
			return getRuleContext(SqlActionContext.class,0);
		}
		public RuleActionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ruleAction; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PostgreSQLListener ) ((PostgreSQLListener)listener).enterRuleAction(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PostgreSQLListener ) ((PostgreSQLListener)listener).exitRuleAction(this);
		}
	}

	public final RuleActionContext ruleAction() throws RecognitionException {
		RuleActionContext _localctx = new RuleActionContext(_ctx, getState());
		enterRule(_localctx, 140, RULE_ruleAction);
		try {
			setState(1237);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__110:
				enterOuterAlt(_localctx, 1);
				{
				setState(1234);
				match(T__110);
				}
				break;
			case INSTEAD:
				enterOuterAlt(_localctx, 2);
				{
				setState(1235);
				match(INSTEAD);
				setState(1236);
				sqlAction();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class SqlActionContext extends ParserRuleContext {
		public TableNameContext tableName() {
			return getRuleContext(TableNameContext.class,0);
		}
		public List<TerminalNode> LPAREN() { return getTokens(PostgreSQLParser.LPAREN); }
		public TerminalNode LPAREN(int i) {
			return getToken(PostgreSQLParser.LPAREN, i);
		}
		public List<ColumnNameContext> columnName() {
			return getRuleContexts(ColumnNameContext.class);
		}
		public ColumnNameContext columnName(int i) {
			return getRuleContext(ColumnNameContext.class,i);
		}
		public List<TerminalNode> RPAREN() { return getTokens(PostgreSQLParser.RPAREN); }
		public TerminalNode RPAREN(int i) {
			return getToken(PostgreSQLParser.RPAREN, i);
		}
		public List<ValueContext> value() {
			return getRuleContexts(ValueContext.class);
		}
		public ValueContext value(int i) {
			return getRuleContext(ValueContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(PostgreSQLParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(PostgreSQLParser.COMMA, i);
		}
		public TerminalNode UPDATE() { return getToken(PostgreSQLParser.UPDATE, 0); }
		public TerminalNode SET() { return getToken(PostgreSQLParser.SET, 0); }
		public TerminalNode EQUALS() { return getToken(PostgreSQLParser.EQUALS, 0); }
		public SqlActionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_sqlAction; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PostgreSQLListener ) ((PostgreSQLListener)listener).enterSqlAction(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PostgreSQLListener ) ((PostgreSQLListener)listener).exitSqlAction(this);
		}
	}

	public final SqlActionContext sqlAction() throws RecognitionException {
		SqlActionContext _localctx = new SqlActionContext(_ctx, getState());
		enterRule(_localctx, 142, RULE_sqlAction);
		int _la;
		try {
			setState(1272);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__60:
				enterOuterAlt(_localctx, 1);
				{
				setState(1239);
				match(T__60);
				setState(1240);
				tableName();
				setState(1241);
				match(LPAREN);
				setState(1242);
				columnName();
				setState(1247);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(1243);
					match(COMMA);
					setState(1244);
					columnName();
					}
					}
					setState(1249);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(1250);
				match(RPAREN);
				setState(1251);
				match(T__61);
				setState(1252);
				match(LPAREN);
				setState(1253);
				value();
				setState(1258);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(1254);
					match(COMMA);
					setState(1255);
					value();
					}
					}
					setState(1260);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(1261);
				match(RPAREN);
				}
				break;
			case UPDATE:
				enterOuterAlt(_localctx, 2);
				{
				setState(1263);
				match(UPDATE);
				setState(1264);
				tableName();
				setState(1265);
				match(SET);
				setState(1266);
				columnName();
				setState(1267);
				match(EQUALS);
				setState(1268);
				value();
				}
				break;
			case T__62:
				enterOuterAlt(_localctx, 3);
				{
				setState(1270);
				match(T__62);
				setState(1271);
				tableName();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class NumericLiteralContext extends ParserRuleContext {
		public TerminalNode NUMBER() { return getToken(PostgreSQLParser.NUMBER, 0); }
		public NumericLiteralContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_numericLiteral; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PostgreSQLListener ) ((PostgreSQLListener)listener).enterNumericLiteral(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PostgreSQLListener ) ((PostgreSQLListener)listener).exitNumericLiteral(this);
		}
	}

	public final NumericLiteralContext numericLiteral() throws RecognitionException {
		NumericLiteralContext _localctx = new NumericLiteralContext(_ctx, getState());
		enterRule(_localctx, 144, RULE_numericLiteral);
		try {
			setState(1277);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case NUMBER:
				enterOuterAlt(_localctx, 1);
				{
				setState(1274);
				match(NUMBER);
				}
				break;
			case T__111:
				enterOuterAlt(_localctx, 2);
				{
				setState(1275);
				match(T__111);
				setState(1276);
				match(NUMBER);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class BooleanLiteralContext extends ParserRuleContext {
		public BooleanLiteralContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_booleanLiteral; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PostgreSQLListener ) ((PostgreSQLListener)listener).enterBooleanLiteral(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PostgreSQLListener ) ((PostgreSQLListener)listener).exitBooleanLiteral(this);
		}
	}

	public final BooleanLiteralContext booleanLiteral() throws RecognitionException {
		BooleanLiteralContext _localctx = new BooleanLiteralContext(_ctx, getState());
		enterRule(_localctx, 146, RULE_booleanLiteral);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1279);
			_la = _input.LA(1);
			if ( !(((((_la - 113)) & ~0x3f) == 0 && ((1L << (_la - 113)) & 15L) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class AnyExpressionContext extends ParserRuleContext {
		public TerminalNode ANY() { return getToken(PostgreSQLParser.ANY, 0); }
		public TerminalNode LPAREN() { return getToken(PostgreSQLParser.LPAREN, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode RPAREN() { return getToken(PostgreSQLParser.RPAREN, 0); }
		public AnyExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_anyExpression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PostgreSQLListener ) ((PostgreSQLListener)listener).enterAnyExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PostgreSQLListener ) ((PostgreSQLListener)listener).exitAnyExpression(this);
		}
	}

	public final AnyExpressionContext anyExpression() throws RecognitionException {
		AnyExpressionContext _localctx = new AnyExpressionContext(_ctx, getState());
		enterRule(_localctx, 148, RULE_anyExpression);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1281);
			match(ANY);
			setState(1282);
			match(LPAREN);
			setState(1283);
			expression();
			setState(1284);
			match(RPAREN);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class IntervalLiteralContext extends ParserRuleContext {
		public TerminalNode STRING() { return getToken(PostgreSQLParser.STRING, 0); }
		public IntervalLiteralContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_intervalLiteral; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PostgreSQLListener ) ((PostgreSQLListener)listener).enterIntervalLiteral(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PostgreSQLListener ) ((PostgreSQLListener)listener).exitIntervalLiteral(this);
		}
	}

	public final IntervalLiteralContext intervalLiteral() throws RecognitionException {
		IntervalLiteralContext _localctx = new IntervalLiteralContext(_ctx, getState());
		enterRule(_localctx, 150, RULE_intervalLiteral);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1286);
			match(T__116);
			setState(1287);
			match(STRING);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class TypeNameContext extends ParserRuleContext {
		public List<TerminalNode> IDENTIFIER() { return getTokens(PostgreSQLParser.IDENTIFIER); }
		public TerminalNode IDENTIFIER(int i) {
			return getToken(PostgreSQLParser.IDENTIFIER, i);
		}
		public TerminalNode DOT() { return getToken(PostgreSQLParser.DOT, 0); }
		public TypeNameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_typeName; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PostgreSQLListener ) ((PostgreSQLListener)listener).enterTypeName(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PostgreSQLListener ) ((PostgreSQLListener)listener).exitTypeName(this);
		}
	}

	public final TypeNameContext typeName() throws RecognitionException {
		TypeNameContext _localctx = new TypeNameContext(_ctx, getState());
		enterRule(_localctx, 152, RULE_typeName);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1289);
			match(IDENTIFIER);
			setState(1292);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==DOT) {
				{
				setState(1290);
				match(DOT);
				setState(1291);
				match(IDENTIFIER);
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 30:
			return booleanCondition_sempred((BooleanConditionContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean booleanCondition_sempred(BooleanConditionContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 2);
		case 1:
			return precpred(_ctx, 1);
		}
		return true;
	}

	public static final String _serializedATN =
		"\u0004\u0001\u00ee\u050f\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001"+
		"\u0002\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004\u0007\u0004"+
		"\u0002\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007\u0007\u0007"+
		"\u0002\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002\u000b\u0007\u000b"+
		"\u0002\f\u0007\f\u0002\r\u0007\r\u0002\u000e\u0007\u000e\u0002\u000f\u0007"+
		"\u000f\u0002\u0010\u0007\u0010\u0002\u0011\u0007\u0011\u0002\u0012\u0007"+
		"\u0012\u0002\u0013\u0007\u0013\u0002\u0014\u0007\u0014\u0002\u0015\u0007"+
		"\u0015\u0002\u0016\u0007\u0016\u0002\u0017\u0007\u0017\u0002\u0018\u0007"+
		"\u0018\u0002\u0019\u0007\u0019\u0002\u001a\u0007\u001a\u0002\u001b\u0007"+
		"\u001b\u0002\u001c\u0007\u001c\u0002\u001d\u0007\u001d\u0002\u001e\u0007"+
		"\u001e\u0002\u001f\u0007\u001f\u0002 \u0007 \u0002!\u0007!\u0002\"\u0007"+
		"\"\u0002#\u0007#\u0002$\u0007$\u0002%\u0007%\u0002&\u0007&\u0002\'\u0007"+
		"\'\u0002(\u0007(\u0002)\u0007)\u0002*\u0007*\u0002+\u0007+\u0002,\u0007"+
		",\u0002-\u0007-\u0002.\u0007.\u0002/\u0007/\u00020\u00070\u00021\u0007"+
		"1\u00022\u00072\u00023\u00073\u00024\u00074\u00025\u00075\u00026\u0007"+
		"6\u00027\u00077\u00028\u00078\u00029\u00079\u0002:\u0007:\u0002;\u0007"+
		";\u0002<\u0007<\u0002=\u0007=\u0002>\u0007>\u0002?\u0007?\u0002@\u0007"+
		"@\u0002A\u0007A\u0002B\u0007B\u0002C\u0007C\u0002D\u0007D\u0002E\u0007"+
		"E\u0002F\u0007F\u0002G\u0007G\u0002H\u0007H\u0002I\u0007I\u0002J\u0007"+
		"J\u0002K\u0007K\u0002L\u0007L\u0001\u0000\u0001\u0000\u0001\u0000\u0001"+
		"\u0000\u0003\u0000\u009f\b\u0000\u0001\u0000\u0001\u0000\u0001\u0000\u0001"+
		"\u0000\u0005\u0000\u00a5\b\u0000\n\u0000\f\u0000\u00a8\t\u0000\u0001\u0000"+
		"\u0003\u0000\u00ab\b\u0000\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001"+
		"\u0001\u0001\u0001\u0001\u0003\u0001\u00b3\b\u0001\u0001\u0001\u0001\u0001"+
		"\u0001\u0001\u0003\u0001\u00b8\b\u0001\u0005\u0001\u00ba\b\u0001\n\u0001"+
		"\f\u0001\u00bd\t\u0001\u0001\u0001\u0001\u0001\u0003\u0001\u00c1\b\u0001"+
		"\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001"+
		"\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0003\u0001\u00cd\b\u0001"+
		"\u0001\u0002\u0001\u0002\u0001\u0002\u0005\u0002\u00d2\b\u0002\n\u0002"+
		"\f\u0002\u00d5\t\u0002\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003"+
		"\u0001\u0003\u0003\u0003\u00dc\b\u0003\u0001\u0004\u0001\u0004\u0001\u0004"+
		"\u0001\u0004\u0003\u0004\u00e2\b\u0004\u0001\u0004\u0001\u0004\u0001\u0005"+
		"\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0006\u0001\u0006"+
		"\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0003\u0006"+
		"\u00f2\b\u0006\u0003\u0006\u00f4\b\u0006\u0001\u0007\u0001\u0007\u0005"+
		"\u0007\u00f8\b\u0007\n\u0007\f\u0007\u00fb\t\u0007\u0001\u0007\u0001\u0007"+
		"\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001"+
		"\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0003\b\u010d\b\b\u0001\t\u0001"+
		"\t\u0001\t\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0005\n\u0118"+
		"\b\n\n\n\f\n\u011b\t\n\u0001\n\u0003\n\u011e\b\n\u0001\u000b\u0001\u000b"+
		"\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b"+
		"\u0001\u000b\u0003\u000b\u0129\b\u000b\u0001\u000b\u0001\u000b\u0001\u000b"+
		"\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b"+
		"\u0001\u000b\u0003\u000b\u0135\b\u000b\u0001\u000b\u0005\u000b\u0138\b"+
		"\u000b\n\u000b\f\u000b\u013b\t\u000b\u0001\u000b\u0001\u000b\u0001\u000b"+
		"\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b"+
		"\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b"+
		"\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0003\u000b"+
		"\u0151\b\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b"+
		"\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b"+
		"\u0001\u000b\u0001\u000b\u0001\u000b\u0003\u000b\u0161\b\u000b\u0001\f"+
		"\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001"+
		"\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0004\f\u0172\b\f\u000b\f\f"+
		"\f\u0173\u0001\r\u0001\r\u0001\r\u0001\r\u0001\r\u0001\r\u0001\r\u0001"+
		"\r\u0001\r\u0001\r\u0001\r\u0001\r\u0001\r\u0001\r\u0001\r\u0001\r\u0001"+
		"\r\u0001\r\u0003\r\u0188\b\r\u0001\r\u0003\r\u018b\b\r\u0001\r\u0001\r"+
		"\u0001\r\u0001\r\u0001\r\u0001\r\u0003\r\u0193\b\r\u0001\r\u0001\r\u0001"+
		"\r\u0001\r\u0003\r\u0199\b\r\u0001\r\u0001\r\u0001\r\u0001\r\u0001\r\u0003"+
		"\r\u01a0\b\r\u0001\r\u0001\r\u0001\r\u0001\r\u0001\r\u0001\r\u0003\r\u01a8"+
		"\b\r\u0001\r\u0001\r\u0001\r\u0001\r\u0001\r\u0001\r\u0001\r\u0003\r\u01b1"+
		"\b\r\u0001\r\u0001\r\u0001\r\u0001\r\u0001\r\u0001\r\u0003\r\u01b9\b\r"+
		"\u0001\r\u0001\r\u0001\r\u0001\r\u0003\r\u01bf\b\r\u0001\r\u0001\r\u0001"+
		"\r\u0001\r\u0001\r\u0001\r\u0003\r\u01c7\b\r\u0001\r\u0001\r\u0001\r\u0001"+
		"\r\u0001\r\u0001\r\u0001\r\u0001\r\u0001\r\u0005\r\u01d2\b\r\n\r\f\r\u01d5"+
		"\t\r\u0001\r\u0001\r\u0001\r\u0001\r\u0001\r\u0001\r\u0001\r\u0001\r\u0001"+
		"\r\u0001\r\u0001\r\u0001\r\u0003\r\u01e3\b\r\u0001\r\u0001\r\u0001\r\u0001"+
		"\r\u0003\r\u01e9\b\r\u0001\r\u0001\r\u0001\r\u0001\r\u0003\r\u01ef\b\r"+
		"\u0001\u000e\u0001\u000e\u0001\u000e\u0003\u000e\u01f4\b\u000e\u0001\u000e"+
		"\u0001\u000e\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u0010\u0001\u0010"+
		"\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0003\u0010\u0201\b\u0010"+
		"\u0001\u0010\u0001\u0010\u0001\u0010\u0003\u0010\u0206\b\u0010\u0001\u0010"+
		"\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010"+
		"\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0003\u0010\u0213\b\u0010"+
		"\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0003\u0010"+
		"\u021a\b\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010"+
		"\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0003\u0010\u0225\b\u0010"+
		"\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010"+
		"\u0003\u0010\u022d\b\u0010\u0001\u0010\u0005\u0010\u0230\b\u0010\n\u0010"+
		"\f\u0010\u0233\t\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010"+
		"\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0003\u0010\u023d\b\u0010"+
		"\u0001\u0010\u0001\u0010\u0001\u0010\u0003\u0010\u0242\b\u0010\u0001\u0011"+
		"\u0001\u0011\u0003\u0011\u0246\b\u0011\u0001\u0011\u0001\u0011\u0001\u0011"+
		"\u0001\u0011\u0001\u0011\u0001\u0011\u0001\u0011\u0003\u0011\u024f\b\u0011"+
		"\u0001\u0011\u0001\u0011\u0001\u0011\u0001\u0011\u0001\u0011\u0001\u0011"+
		"\u0001\u0011\u0001\u0011\u0003\u0011\u0259\b\u0011\u0001\u0011\u0001\u0011"+
		"\u0001\u0011\u0001\u0011\u0001\u0011\u0005\u0011\u0260\b\u0011\n\u0011"+
		"\f\u0011\u0263\t\u0011\u0001\u0011\u0001\u0011\u0001\u0011\u0001\u0011"+
		"\u0001\u0011\u0001\u0011\u0001\u0011\u0001\u0011\u0003\u0011\u026d\b\u0011"+
		"\u0001\u0011\u0001\u0011\u0001\u0011\u0001\u0011\u0001\u0011\u0005\u0011"+
		"\u0274\b\u0011\n\u0011\f\u0011\u0277\t\u0011\u0001\u0011\u0001\u0011\u0003"+
		"\u0011\u027b\b\u0011\u0001\u0011\u0001\u0011\u0001\u0011\u0001\u0011\u0001"+
		"\u0011\u0001\u0011\u0001\u0011\u0003\u0011\u0284\b\u0011\u0001\u0011\u0001"+
		"\u0011\u0001\u0011\u0001\u0011\u0001\u0011\u0001\u0011\u0001\u0011\u0001"+
		"\u0011\u0001\u0011\u0001\u0011\u0001\u0011\u0001\u0011\u0001\u0011\u0001"+
		"\u0011\u0001\u0011\u0001\u0011\u0001\u0011\u0003\u0011\u0297\b\u0011\u0001"+
		"\u0012\u0001\u0012\u0001\u0012\u0005\u0012\u029c\b\u0012\n\u0012\f\u0012"+
		"\u029f\t\u0012\u0001\u0013\u0001\u0013\u0001\u0013\u0001\u0013\u0001\u0014"+
		"\u0001\u0014\u0001\u0014\u0001\u0014\u0001\u0014\u0001\u0014\u0001\u0014"+
		"\u0003\u0014\u02ac\b\u0014\u0003\u0014\u02ae\b\u0014\u0001\u0015\u0001"+
		"\u0015\u0001\u0016\u0001\u0016\u0001\u0016\u0001\u0016\u0001\u0016\u0001"+
		"\u0016\u0001\u0016\u0001\u0016\u0001\u0016\u0001\u0016\u0001\u0016\u0001"+
		"\u0016\u0001\u0016\u0001\u0016\u0005\u0016\u02c0\b\u0016\n\u0016\f\u0016"+
		"\u02c3\t\u0016\u0003\u0016\u02c5\b\u0016\u0001\u0016\u0001\u0016\u0001"+
		"\u0016\u0001\u0017\u0001\u0017\u0001\u0018\u0001\u0018\u0001\u0018\u0005"+
		"\u0018\u02cf\b\u0018\n\u0018\f\u0018\u02d2\t\u0018\u0001\u0019\u0001\u0019"+
		"\u0001\u0019\u0003\u0019\u02d7\b\u0019\u0001\u001a\u0001\u001a\u0001\u001a"+
		"\u0001\u001a\u0001\u001a\u0001\u001a\u0005\u001a\u02df\b\u001a\n\u001a"+
		"\f\u001a\u02e2\t\u001a\u0001\u001a\u0001\u001a\u0001\u001a\u0001\u001a"+
		"\u0001\u001a\u0001\u001a\u0005\u001a\u02ea\b\u001a\n\u001a\f\u001a\u02ed"+
		"\t\u001a\u0001\u001a\u0001\u001a\u0001\u001a\u0001\u001b\u0001\u001b\u0001"+
		"\u001b\u0001\u001b\u0001\u001b\u0001\u001b\u0001\u001b\u0001\u001b\u0001"+
		"\u001b\u0001\u001b\u0001\u001b\u0005\u001b\u02fd\b\u001b\n\u001b\f\u001b"+
		"\u0300\t\u001b\u0001\u001b\u0001\u001b\u0003\u001b\u0304\b\u001b\u0001"+
		"\u001b\u0001\u001b\u0001\u001c\u0001\u001c\u0001\u001c\u0001\u001c\u0003"+
		"\u001c\u030c\b\u001c\u0001\u001c\u0001\u001c\u0001\u001d\u0001\u001d\u0001"+
		"\u001e\u0001\u001e\u0001\u001e\u0001\u001e\u0001\u001e\u0001\u001e\u0001"+
		"\u001e\u0001\u001e\u0003\u001e\u031a\b\u001e\u0001\u001e\u0001\u001e\u0001"+
		"\u001e\u0001\u001e\u0001\u001e\u0001\u001e\u0005\u001e\u0322\b\u001e\n"+
		"\u001e\f\u001e\u0325\t\u001e\u0001\u001f\u0001\u001f\u0001\u001f\u0001"+
		"\u001f\u0001\u001f\u0001\u001f\u0001\u001f\u0001\u001f\u0001\u001f\u0001"+
		"\u001f\u0001\u001f\u0001\u001f\u0001\u001f\u0001\u001f\u0003\u001f\u0335"+
		"\b\u001f\u0001\u001f\u0001\u001f\u0001\u001f\u0003\u001f\u033a\b\u001f"+
		"\u0005\u001f\u033c\b\u001f\n\u001f\f\u001f\u033f\t\u001f\u0001\u001f\u0001"+
		"\u001f\u0001\u001f\u0001\u001f\u0001\u001f\u0001\u001f\u0001\u001f\u0001"+
		"\u001f\u0001\u001f\u0001\u001f\u0001\u001f\u0001\u001f\u0001\u001f\u0001"+
		"\u001f\u0001\u001f\u0001\u001f\u0001\u001f\u0001\u001f\u0001\u001f\u0001"+
		"\u001f\u0001\u001f\u0003\u001f\u0356\b\u001f\u0001 \u0001 \u0001 \u0001"+
		" \u0001!\u0001!\u0001\"\u0001\"\u0001\"\u0001\"\u0001\"\u0001\"\u0001"+
		"\"\u0001\"\u0001\"\u0001\"\u0005\"\u0368\b\"\n\"\f\"\u036b\t\"\u0001\""+
		"\u0001\"\u0001#\u0001#\u0001#\u0001#\u0001$\u0001$\u0001$\u0001$\u0001"+
		"$\u0001$\u0001%\u0001%\u0001%\u0001%\u0001%\u0001%\u0005%\u037f\b%\n%"+
		"\f%\u0382\t%\u0003%\u0384\b%\u0001%\u0001%\u0001%\u0001%\u0001%\u0001"+
		"%\u0001%\u0001%\u0001%\u0001%\u0001%\u0001&\u0001&\u0001&\u0001\'\u0001"+
		"\'\u0001\'\u0001\'\u0001\'\u0001\'\u0005\'\u039a\b\'\n\'\f\'\u039d\t\'"+
		"\u0003\'\u039f\b\'\u0001\'\u0003\'\u03a2\b\'\u0001\'\u0003\'\u03a5\b\'"+
		"\u0001\'\u0003\'\u03a8\b\'\u0001(\u0001(\u0001(\u0001(\u0005(\u03ae\b"+
		"(\n(\f(\u03b1\t(\u0003(\u03b3\b(\u0001)\u0001)\u0001)\u0003)\u03b8\b)"+
		"\u0001*\u0001*\u0001*\u0001+\u0001+\u0001+\u0003+\u03c0\b+\u0001,\u0001"+
		",\u0001,\u0001-\u0001-\u0001-\u0005-\u03c8\b-\n-\f-\u03cb\t-\u0001.\u0001"+
		".\u0001.\u0001.\u0001.\u0001.\u0001.\u0001.\u0003.\u03d5\b.\u0003.\u03d7"+
		"\b.\u0001/\u0001/\u00010\u00010\u00010\u00030\u03de\b0\u00010\u00010\u0001"+
		"1\u00011\u00012\u00012\u00013\u00013\u00013\u00013\u00013\u00033\u03eb"+
		"\b3\u00013\u00033\u03ee\b3\u00014\u00014\u00014\u00014\u00014\u00014\u0001"+
		"5\u00015\u00016\u00016\u00016\u00016\u00016\u00016\u00016\u00016\u0001"+
		"6\u00016\u00016\u00016\u00016\u00016\u00016\u00056\u0407\b6\n6\f6\u040a"+
		"\t6\u00016\u00016\u00016\u00016\u00016\u00016\u00036\u0412\b6\u00017\u0001"+
		"7\u00037\u0416\b7\u00017\u00017\u00017\u00017\u00017\u00017\u00037\u041e"+
		"\b7\u00017\u00017\u00017\u00017\u00057\u0424\b7\n7\f7\u0427\t7\u00017"+
		"\u00017\u00017\u00037\u042c\b7\u00017\u00037\u042f\b7\u00017\u00017\u0001"+
		"8\u00018\u00038\u0435\b8\u00018\u00038\u0438\b8\u00019\u00019\u00019\u0001"+
		":\u0001:\u0001:\u0001:\u0003:\u0441\b:\u0001:\u0001:\u0001:\u0003:\u0446"+
		"\b:\u0001:\u0001:\u0003:\u044a\b:\u0001:\u0003:\u044d\b:\u0001;\u0001"+
		";\u0001;\u0001;\u0001;\u0001;\u0001;\u0001;\u0003;\u0457\b;\u0001;\u0003"+
		";\u045a\b;\u0003;\u045c\b;\u0001<\u0001<\u0001<\u0001<\u0005<\u0462\b"+
		"<\n<\f<\u0465\t<\u0001=\u0001=\u0001=\u0005=\u046a\b=\n=\f=\u046d\t=\u0001"+
		">\u0001>\u0003>\u0471\b>\u0001>\u0001>\u0003>\u0475\b>\u0001>\u0001>\u0003"+
		">\u0479\b>\u0001>\u0001>\u0001>\u0003>\u047e\b>\u0001?\u0001?\u0001?\u0005"+
		"?\u0483\b?\n?\f?\u0486\t?\u0001@\u0001@\u0001@\u0001@\u0001@\u0003@\u048d"+
		"\b@\u0001A\u0001A\u0001A\u0001A\u0001A\u0001A\u0001A\u0003A\u0496\bA\u0001"+
		"A\u0001A\u0001A\u0001A\u0001A\u0003A\u049d\bA\u0001A\u0001A\u0001A\u0001"+
		"A\u0001A\u0003A\u04a4\bA\u0001A\u0001A\u0001B\u0001B\u0005B\u04aa\bB\n"+
		"B\fB\u04ad\tB\u0001B\u0001B\u0001B\u0001C\u0001C\u0001C\u0001C\u0001C"+
		"\u0001C\u0001C\u0001C\u0001C\u0001C\u0001C\u0001C\u0001C\u0001C\u0001"+
		"C\u0001C\u0001C\u0003C\u04c3\bC\u0001D\u0001D\u0001D\u0001D\u0001D\u0001"+
		"D\u0001D\u0001D\u0001D\u0001D\u0001D\u0001D\u0001E\u0001E\u0001F\u0001"+
		"F\u0001F\u0003F\u04d6\bF\u0001G\u0001G\u0001G\u0001G\u0001G\u0001G\u0005"+
		"G\u04de\bG\nG\fG\u04e1\tG\u0001G\u0001G\u0001G\u0001G\u0001G\u0001G\u0005"+
		"G\u04e9\bG\nG\fG\u04ec\tG\u0001G\u0001G\u0001G\u0001G\u0001G\u0001G\u0001"+
		"G\u0001G\u0001G\u0001G\u0001G\u0003G\u04f9\bG\u0001H\u0001H\u0001H\u0003"+
		"H\u04fe\bH\u0001I\u0001I\u0001J\u0001J\u0001J\u0001J\u0001J\u0001K\u0001"+
		"K\u0001K\u0001L\u0001L\u0001L\u0003L\u050d\bL\u0001L\u0001\u00a6\u0001"+
		"<M\u0000\u0002\u0004\u0006\b\n\f\u000e\u0010\u0012\u0014\u0016\u0018\u001a"+
		"\u001c\u001e \"$&(*,.02468:<>@BDFHJLNPRTVXZ\\^`bdfhjlnprtvxz|~\u0080\u0082"+
		"\u0084\u0086\u0088\u008a\u008c\u008e\u0090\u0092\u0094\u0096\u0098\u0000"+
		"\u0010\u0002\u0000\u0002\u0002\u0006\u0006\u0001\u0000#&\u0001\u0000)"+
		"+\u0001\u000078\u0001\u0000\u00a3\u00a7\u0002\u0000;;\u00b1\u00b2\u0002"+
		"\u0000\u0080\u0080\u00b3\u00b4\u0002\u0000@K\u00ab\u00ab\u0001\u0000T"+
		"U\u0002\u0000Y[\u00e9\u00e9\u0001\u0000\\]\u0001\u0000_a\u0002\u0000f"+
		"f\u00ea\u00ea\u0004\u0000jj\u0080\u0080\u0088\u0088\u00b3\u00b4\u0003"+
		"\u0000\u0080\u0080\u0088\u0088\u00b3\u00b4\u0001\u0000qt\u05b5\u0000\u009a"+
		"\u0001\u0000\u0000\u0000\u0002\u00cc\u0001\u0000\u0000\u0000\u0004\u00ce"+
		"\u0001\u0000\u0000\u0000\u0006\u00db\u0001\u0000\u0000\u0000\b\u00dd\u0001"+
		"\u0000\u0000\u0000\n\u00e5\u0001\u0000\u0000\u0000\f\u00f3\u0001\u0000"+
		"\u0000\u0000\u000e\u00f5\u0001\u0000\u0000\u0000\u0010\u010c\u0001\u0000"+
		"\u0000\u0000\u0012\u010e\u0001\u0000\u0000\u0000\u0014\u0111\u0001\u0000"+
		"\u0000\u0000\u0016\u0160\u0001\u0000\u0000\u0000\u0018\u0171\u0001\u0000"+
		"\u0000\u0000\u001a\u01ee\u0001\u0000\u0000\u0000\u001c\u01f0\u0001\u0000"+
		"\u0000\u0000\u001e\u01f7\u0001\u0000\u0000\u0000 \u0241\u0001\u0000\u0000"+
		"\u0000\"\u0296\u0001\u0000\u0000\u0000$\u0298\u0001\u0000\u0000\u0000"+
		"&\u02a0\u0001\u0000\u0000\u0000(\u02ad\u0001\u0000\u0000\u0000*\u02af"+
		"\u0001\u0000\u0000\u0000,\u02b1\u0001\u0000\u0000\u0000.\u02c9\u0001\u0000"+
		"\u0000\u00000\u02cb\u0001\u0000\u0000\u00002\u02d6\u0001\u0000\u0000\u0000"+
		"4\u02d8\u0001\u0000\u0000\u00006\u02f1\u0001\u0000\u0000\u00008\u0307"+
		"\u0001\u0000\u0000\u0000:\u030f\u0001\u0000\u0000\u0000<\u0319\u0001\u0000"+
		"\u0000\u0000>\u0355\u0001\u0000\u0000\u0000@\u0357\u0001\u0000\u0000\u0000"+
		"B\u035b\u0001\u0000\u0000\u0000D\u0369\u0001\u0000\u0000\u0000F\u036e"+
		"\u0001\u0000\u0000\u0000H\u0372\u0001\u0000\u0000\u0000J\u0378\u0001\u0000"+
		"\u0000\u0000L\u0390\u0001\u0000\u0000\u0000N\u0393\u0001\u0000\u0000\u0000"+
		"P\u03b2\u0001\u0000\u0000\u0000R\u03b4\u0001\u0000\u0000\u0000T\u03b9"+
		"\u0001\u0000\u0000\u0000V\u03bc\u0001\u0000\u0000\u0000X\u03c1\u0001\u0000"+
		"\u0000\u0000Z\u03c4\u0001\u0000\u0000\u0000\\\u03d6\u0001\u0000\u0000"+
		"\u0000^\u03d8\u0001\u0000\u0000\u0000`\u03dd\u0001\u0000\u0000\u0000b"+
		"\u03e1\u0001\u0000\u0000\u0000d\u03e3\u0001\u0000\u0000\u0000f\u03e5\u0001"+
		"\u0000\u0000\u0000h\u03ef\u0001\u0000\u0000\u0000j\u03f5\u0001\u0000\u0000"+
		"\u0000l\u03f7\u0001\u0000\u0000\u0000n\u0413\u0001\u0000\u0000\u0000p"+
		"\u0432\u0001\u0000\u0000\u0000r\u0439\u0001\u0000\u0000\u0000t\u044c\u0001"+
		"\u0000\u0000\u0000v\u045b\u0001\u0000\u0000\u0000x\u045d\u0001\u0000\u0000"+
		"\u0000z\u0466\u0001\u0000\u0000\u0000|\u047d\u0001\u0000\u0000\u0000~"+
		"\u047f\u0001\u0000\u0000\u0000\u0080\u0487\u0001\u0000\u0000\u0000\u0082"+
		"\u048e\u0001\u0000\u0000\u0000\u0084\u04a7\u0001\u0000\u0000\u0000\u0086"+
		"\u04c2\u0001\u0000\u0000\u0000\u0088\u04c4\u0001\u0000\u0000\u0000\u008a"+
		"\u04d0\u0001\u0000\u0000\u0000\u008c\u04d5\u0001\u0000\u0000\u0000\u008e"+
		"\u04f8\u0001\u0000\u0000\u0000\u0090\u04fd\u0001\u0000\u0000\u0000\u0092"+
		"\u04ff\u0001\u0000\u0000\u0000\u0094\u0501\u0001\u0000\u0000\u0000\u0096"+
		"\u0506\u0001\u0000\u0000\u0000\u0098\u0509\u0001\u0000\u0000\u0000\u009a"+
		"\u009e\u0005\u0001\u0000\u0000\u009b\u009f\u0005\u0002\u0000\u0000\u009c"+
		"\u009d\u0005\u0003\u0000\u0000\u009d\u009f\u0005\u00a0\u0000\u0000\u009e"+
		"\u009b\u0001\u0000\u0000\u0000\u009e\u009c\u0001\u0000\u0000\u0000\u009f"+
		"\u00a0\u0001\u0000\u0000\u0000\u00a0\u00a1\u0005\u0083\u0000\u0000\u00a1"+
		"\u00aa\u0005\u0004\u0000\u0000\u00a2\u00a6\u0005v\u0000\u0000\u00a3\u00a5"+
		"\t\u0000\u0000\u0000\u00a4\u00a3\u0001\u0000\u0000\u0000\u00a5\u00a8\u0001"+
		"\u0000\u0000\u0000\u00a6\u00a7\u0001\u0000\u0000\u0000\u00a6\u00a4\u0001"+
		"\u0000\u0000\u0000\u00a7\u00a9\u0001\u0000\u0000\u0000\u00a8\u00a6\u0001"+
		"\u0000\u0000\u0000\u00a9\u00ab\u0005w\u0000\u0000\u00aa\u00a2\u0001\u0000"+
		"\u0000\u0000\u00aa\u00ab\u0001\u0000\u0000\u0000\u00ab\u0001\u0001\u0000"+
		"\u0000\u0000\u00ac\u00ad\u0005\u008d\u0000\u0000\u00ad\u00ae\u0005\u008c"+
		"\u0000\u0000\u00ae\u00af\u0003`0\u0000\u00af\u00b2\u0005v\u0000\u0000"+
		"\u00b0\u00b3\u0003\"\u0011\u0000\u00b1\u00b3\u0003\u0004\u0002\u0000\u00b2"+
		"\u00b0\u0001\u0000\u0000\u0000\u00b2\u00b1\u0001\u0000\u0000\u0000\u00b3"+
		"\u00bb\u0001\u0000\u0000\u0000\u00b4\u00b7\u0005}\u0000\u0000\u00b5\u00b8"+
		"\u0003\"\u0011\u0000\u00b6\u00b8\u0003\u0004\u0002\u0000\u00b7\u00b5\u0001"+
		"\u0000\u0000\u0000\u00b7\u00b6\u0001\u0000\u0000\u0000\u00b8\u00ba\u0001"+
		"\u0000\u0000\u0000\u00b9\u00b4\u0001\u0000\u0000\u0000\u00ba\u00bd\u0001"+
		"\u0000\u0000\u0000\u00bb\u00b9\u0001\u0000\u0000\u0000\u00bb\u00bc\u0001"+
		"\u0000\u0000\u0000\u00bc\u00be\u0001\u0000\u0000\u0000\u00bd\u00bb\u0001"+
		"\u0000\u0000\u0000\u00be\u00c0\u0005w\u0000\u0000\u00bf\u00c1\u0003h4"+
		"\u0000\u00c0\u00bf\u0001\u0000\u0000\u0000\u00c0\u00c1\u0001\u0000\u0000"+
		"\u0000\u00c1\u00c2\u0001\u0000\u0000\u0000\u00c2\u00c3\u0005z\u0000\u0000"+
		"\u00c3\u00cd\u0001\u0000\u0000\u0000\u00c4\u00c5\u0005\u008d\u0000\u0000"+
		"\u00c5\u00c6\u0005\u008c\u0000\u0000\u00c6\u00c7\u0003`0\u0000\u00c7\u00c8"+
		"\u0005\u0005\u0000\u0000\u00c8\u00c9\u0003`0\u0000\u00c9\u00ca\u0003l"+
		"6\u0000\u00ca\u00cb\u0005z\u0000\u0000\u00cb\u00cd\u0001\u0000\u0000\u0000"+
		"\u00cc\u00ac\u0001\u0000\u0000\u0000\u00cc\u00c4\u0001\u0000\u0000\u0000"+
		"\u00cd\u0003\u0001\u0000\u0000\u0000\u00ce\u00cf\u0003b1\u0000\u00cf\u00d3"+
		"\u0003\u001a\r\u0000\u00d0\u00d2\u0003\u0006\u0003\u0000\u00d1\u00d0\u0001"+
		"\u0000\u0000\u0000\u00d2\u00d5\u0001\u0000\u0000\u0000\u00d3\u00d1\u0001"+
		"\u0000\u0000\u0000\u00d3\u00d4\u0001\u0000\u0000\u0000\u00d4\u0005\u0001"+
		"\u0000\u0000\u0000\u00d5\u00d3\u0001\u0000\u0000\u0000\u00d6\u00dc\u0003"+
		" \u0010\u0000\u00d7\u00dc\u0003\n\u0005\u0000\u00d8\u00dc\u0003\u0000"+
		"\u0000\u0000\u00d9\u00dc\u0003\u0012\t\u0000\u00da\u00dc\u0003\u001e\u000f"+
		"\u0000\u00db\u00d6\u0001\u0000\u0000\u0000\u00db\u00d7\u0001\u0000\u0000"+
		"\u0000\u00db\u00d8\u0001\u0000\u0000\u0000\u00db\u00d9\u0001\u0000\u0000"+
		"\u0000\u00db\u00da\u0001\u0000\u0000\u0000\u00dc\u0007\u0001\u0000\u0000"+
		"\u0000\u00dd\u00de\u0005v\u0000\u0000\u00de\u00e1\u0005\u00ec\u0000\u0000"+
		"\u00df\u00e0\u0005}\u0000\u0000\u00e0\u00e2\u0005\u00ec\u0000\u0000\u00e1"+
		"\u00df\u0001\u0000\u0000\u0000\u00e1\u00e2\u0001\u0000\u0000\u0000\u00e2"+
		"\u00e3\u0001\u0000\u0000\u0000\u00e3\u00e4\u0005w\u0000\u0000\u00e4\t"+
		"\u0001\u0000\u0000\u0000\u00e5\u00e6\u0005\u0001\u0000\u0000\u00e6\u00e7"+
		"\u0007\u0000\u0000\u0000\u00e7\u00e8\u0005\u0083\u0000\u0000\u00e8\u00e9"+
		"\u0003\f\u0006\u0000\u00e9\u000b\u0001\u0000\u0000\u0000\u00ea\u00eb\u0005"+
		"v\u0000\u0000\u00eb\u00ec\u0003t:\u0000\u00ec\u00ed\u0005w\u0000\u0000"+
		"\u00ed\u00ee\u0005\u0007\u0000\u0000\u00ee\u00f4\u0001\u0000\u0000\u0000"+
		"\u00ef\u00f1\u0005\u0004\u0000\u0000\u00f0\u00f2\u0003\u000e\u0007\u0000"+
		"\u00f1\u00f0\u0001\u0000\u0000\u0000\u00f1\u00f2\u0001\u0000\u0000\u0000"+
		"\u00f2\u00f4\u0001\u0000\u0000\u0000\u00f3\u00ea\u0001\u0000\u0000\u0000"+
		"\u00f3\u00ef\u0001\u0000\u0000\u0000\u00f4\r\u0001\u0000\u0000\u0000\u00f5"+
		"\u00f9\u0005v\u0000\u0000\u00f6\u00f8\u0003\u0010\b\u0000\u00f7\u00f6"+
		"\u0001\u0000\u0000\u0000\u00f8\u00fb\u0001\u0000\u0000\u0000\u00f9\u00f7"+
		"\u0001\u0000\u0000\u0000\u00f9\u00fa\u0001\u0000\u0000\u0000\u00fa\u00fc"+
		"\u0001\u0000\u0000\u0000\u00fb\u00f9\u0001\u0000\u0000\u0000\u00fc\u00fd"+
		"\u0005w\u0000\u0000\u00fd\u000f\u0001\u0000\u0000\u0000\u00fe\u00ff\u0005"+
		"\b\u0000\u0000\u00ff\u0100\u0005\u0003\u0000\u0000\u0100\u010d\u0003\u0090"+
		"H\u0000\u0101\u0102\u0005\t\u0000\u0000\u0102\u010d\u0003\u0090H\u0000"+
		"\u0103\u0104\u0005\n\u0000\u0000\u0104\u010d\u0003\u0090H\u0000\u0105"+
		"\u0106\u0005\u000b\u0000\u0000\u0106\u010d\u0003\u0090H\u0000\u0107\u0108"+
		"\u0005\f\u0000\u0000\u0108\u010d\u0003\u0090H\u0000\u0109\u010d\u0005"+
		"\r\u0000\u0000\u010a\u010b\u0005\u000e\u0000\u0000\u010b\u010d\u0005\r"+
		"\u0000\u0000\u010c\u00fe\u0001\u0000\u0000\u0000\u010c\u0101\u0001\u0000"+
		"\u0000\u0000\u010c\u0103\u0001\u0000\u0000\u0000\u010c\u0105\u0001\u0000"+
		"\u0000\u0000\u010c\u0107\u0001\u0000\u0000\u0000\u010c\u0109\u0001\u0000"+
		"\u0000\u0000\u010c\u010a\u0001\u0000\u0000\u0000\u010d\u0011\u0001\u0000"+
		"\u0000\u0000\u010e\u010f\u0005\u000f\u0000\u0000\u010f\u0110\u0005\u00eb"+
		"\u0000\u0000\u0110\u0013\u0001\u0000\u0000\u0000\u0111\u0112\u0005\u0010"+
		"\u0000\u0000\u0112\u0113\u0005\u008c\u0000\u0000\u0113\u0114\u0003`0\u0000"+
		"\u0114\u0119\u0003\u0016\u000b\u0000\u0115\u0116\u0005}\u0000\u0000\u0116"+
		"\u0118\u0003\u0016\u000b\u0000\u0117\u0115\u0001\u0000\u0000\u0000\u0118"+
		"\u011b\u0001\u0000\u0000\u0000\u0119\u0117\u0001\u0000\u0000\u0000\u0119"+
		"\u011a\u0001\u0000\u0000\u0000\u011a\u011d\u0001\u0000\u0000\u0000\u011b"+
		"\u0119\u0001\u0000\u0000\u0000\u011c\u011e\u0005z\u0000\u0000\u011d\u011c"+
		"\u0001\u0000\u0000\u0000\u011d\u011e\u0001\u0000\u0000\u0000\u011e\u0015"+
		"\u0001\u0000\u0000\u0000\u011f\u0120\u0005\u0011\u0000\u0000\u0120\u0121"+
		"\u0005\u0012\u0000\u0000\u0121\u0161\u0003\u0004\u0002\u0000\u0122\u0123"+
		"\u0005\u0013\u0000\u0000\u0123\u0124\u0005\u0012\u0000\u0000\u0124\u0161"+
		"\u0003b1\u0000\u0125\u0128\u0005\u0011\u0000\u0000\u0126\u0127\u0005\u0093"+
		"\u0000\u0000\u0127\u0129\u0005\u00ea\u0000\u0000\u0128\u0126\u0001\u0000"+
		"\u0000\u0000\u0128\u0129\u0001\u0000\u0000\u0000\u0129\u012a\u0001\u0000"+
		"\u0000\u0000\u012a\u012b\u0005\u00af\u0000\u0000\u012b\u012c\u0005v\u0000"+
		"\u0000\u012c\u012d\u0003Z-\u0000\u012d\u012e\u0005w\u0000\u0000\u012e"+
		"\u012f\u0005\u0098\u0000\u0000\u012f\u0134\u0003`0\u0000\u0130\u0131\u0005"+
		"v\u0000\u0000\u0131\u0132\u0003Z-\u0000\u0132\u0133\u0005w\u0000\u0000"+
		"\u0133\u0135\u0001\u0000\u0000\u0000\u0134\u0130\u0001\u0000\u0000\u0000"+
		"\u0134\u0135\u0001\u0000\u0000\u0000\u0135\u0139\u0001\u0000\u0000\u0000"+
		"\u0136\u0138\u0003(\u0014\u0000\u0137\u0136\u0001\u0000\u0000\u0000\u0138"+
		"\u013b\u0001\u0000\u0000\u0000\u0139\u0137\u0001\u0000\u0000\u0000\u0139"+
		"\u013a\u0001\u0000\u0000\u0000\u013a\u0161\u0001\u0000\u0000\u0000\u013b"+
		"\u0139\u0001\u0000\u0000\u0000\u013c\u013d\u0005\u0013\u0000\u0000\u013d"+
		"\u013e\u0005\u0093\u0000\u0000\u013e\u0161\u0005\u00ea\u0000\u0000\u013f"+
		"\u0140\u0005\u0010\u0000\u0000\u0140\u0141\u0005\u0012\u0000\u0000\u0141"+
		"\u0142\u0003b1\u0000\u0142\u0143\u0003\u0018\f\u0000\u0143\u0161\u0001"+
		"\u0000\u0000\u0000\u0144\u0145\u0005\u0014\u0000\u0000\u0145\u0146\u0003"+
		"b1\u0000\u0146\u0147\u0005\u0084\u0000\u0000\u0147\u0148\u0003b1\u0000"+
		"\u0148\u0161\u0001\u0000\u0000\u0000\u0149\u014a\u0005\u0015\u0000\u0000"+
		"\u014a\u0161\u0003`0\u0000\u014b\u0150\u0005\u0097\u0000\u0000\u014c\u014d"+
		"\u0005\u0016\u0000\u0000\u014d\u0151\u0003^/\u0000\u014e\u014f\u0005\u0017"+
		"\u0000\u0000\u014f\u0151\u0005\u00ea\u0000\u0000\u0150\u014c\u0001\u0000"+
		"\u0000\u0000\u0150\u014e\u0001\u0000\u0000\u0000\u0151\u0161\u0001\u0000"+
		"\u0000\u0000\u0152\u0153\u0005\u0018\u0000\u0000\u0153\u0154\u0005\u0019"+
		"\u0000\u0000\u0154\u0155\u0005\u00b0\u0000\u0000\u0155\u0161\u0005\u00ea"+
		"\u0000\u0000\u0156\u0157\u0005\u001a\u0000\u0000\u0157\u0158\u0005\u0019"+
		"\u0000\u0000\u0158\u0159\u0005\u00b0\u0000\u0000\u0159\u0161\u0005\u00ea"+
		"\u0000\u0000\u015a\u015b\u0005\u001b\u0000\u0000\u015b\u015c\u0005\u007f"+
		"\u0000\u0000\u015c\u0161\u0005\u00ea\u0000\u0000\u015d\u0161\u0005\u001c"+
		"\u0000\u0000\u015e\u015f\u0005\u001d\u0000\u0000\u015f\u0161\u0005\u00ea"+
		"\u0000\u0000\u0160\u011f\u0001\u0000\u0000\u0000\u0160\u0122\u0001\u0000"+
		"\u0000\u0000\u0160\u0125\u0001\u0000\u0000\u0000\u0160\u013c\u0001\u0000"+
		"\u0000\u0000\u0160\u013f\u0001\u0000\u0000\u0000\u0160\u0144\u0001\u0000"+
		"\u0000\u0000\u0160\u0149\u0001\u0000\u0000\u0000\u0160\u014b\u0001\u0000"+
		"\u0000\u0000\u0160\u0152\u0001\u0000\u0000\u0000\u0160\u0156\u0001\u0000"+
		"\u0000\u0000\u0160\u015a\u0001\u0000\u0000\u0000\u0160\u015d\u0001\u0000"+
		"\u0000\u0000\u0160\u015e\u0001\u0000\u0000\u0000\u0161\u0017\u0001\u0000"+
		"\u0000\u0000\u0162\u0163\u0005\u00a5\u0000\u0000\u0163\u0172\u0003|>\u0000"+
		"\u0164\u0172\u0005\u001e\u0000\u0000\u0165\u0172\u0005\u001f\u0000\u0000"+
		"\u0166\u0172\u0005 \u0000\u0000\u0167\u0168\u0005!\u0000\u0000\u0168\u0172"+
		"\u0005\u00ec\u0000\u0000\u0169\u016a\u0005\"\u0000\u0000\u016a\u0172\u0007"+
		"\u0001\u0000\u0000\u016b\u016c\u0005\'\u0000\u0000\u016c\u0172\u0005\u00ea"+
		"\u0000\u0000\u016d\u016e\u0005(\u0000\u0000\u016e\u0172\u0007\u0002\u0000"+
		"\u0000\u016f\u0170\u0005,\u0000\u0000\u0170\u0172\u0003\u001a\r\u0000"+
		"\u0171\u0162\u0001\u0000\u0000\u0000\u0171\u0164\u0001\u0000\u0000\u0000"+
		"\u0171\u0165\u0001\u0000\u0000\u0000\u0171\u0166\u0001\u0000\u0000\u0000"+
		"\u0171\u0167\u0001\u0000\u0000\u0000\u0171\u0169\u0001\u0000\u0000\u0000"+
		"\u0171\u016b\u0001\u0000\u0000\u0000\u0171\u016d\u0001\u0000\u0000\u0000"+
		"\u0171\u016f\u0001\u0000\u0000\u0000\u0172\u0173\u0001\u0000\u0000\u0000"+
		"\u0173\u0171\u0001\u0000\u0000\u0000\u0173\u0174\u0001\u0000\u0000\u0000"+
		"\u0174\u0019\u0001\u0000\u0000\u0000\u0175\u01ef\u0005\u00ad\u0000\u0000"+
		"\u0176\u01ef\u0005\u00c7\u0000\u0000\u0177\u01ef\u0005\u00c8\u0000\u0000"+
		"\u0178\u01ef\u0005\u00c9\u0000\u0000\u0179\u01ef\u0005\u0090\u0000\u0000"+
		"\u017a\u01ef\u0005\u00ca\u0000\u0000\u017b\u01ef\u0005\u00cb\u0000\u0000"+
		"\u017c\u01ef\u0005\u00c2\u0000\u0000\u017d\u01ef\u0005\u0099\u0000\u0000"+
		"\u017e\u01ef\u0005\u00cc\u0000\u0000\u017f\u01ef\u0005\u00cd\u0000\u0000"+
		"\u0180\u0181\u0005\u00ce\u0000\u0000\u0181\u01ef\u0005\u00cf\u0000\u0000"+
		"\u0182\u018a\u0005\u00d0\u0000\u0000\u0183\u0184\u0005v\u0000\u0000\u0184"+
		"\u0187\u0005\u00ec\u0000\u0000\u0185\u0186\u0005}\u0000\u0000\u0186\u0188"+
		"\u0005\u00ec\u0000\u0000\u0187\u0185\u0001\u0000\u0000\u0000\u0187\u0188"+
		"\u0001\u0000\u0000\u0000\u0188\u0189\u0001\u0000\u0000\u0000\u0189\u018b"+
		"\u0005w\u0000\u0000\u018a\u0183\u0001\u0000\u0000\u0000\u018a\u018b\u0001"+
		"\u0000\u0000\u0000\u018b\u01ef\u0001\u0000\u0000\u0000\u018c\u01ef\u0003"+
		"f3\u0000\u018d\u01ef\u0005\u00d1\u0000\u0000\u018e\u0192\u0005\u00d2\u0000"+
		"\u0000\u018f\u0190\u0005v\u0000\u0000\u0190\u0191\u0005\u00ec\u0000\u0000"+
		"\u0191\u0193\u0005w\u0000\u0000\u0192\u018f\u0001\u0000\u0000\u0000\u0192"+
		"\u0193\u0001\u0000\u0000\u0000\u0193\u01ef\u0001\u0000\u0000\u0000\u0194"+
		"\u0198\u0005\u0091\u0000\u0000\u0195\u0196\u0005v\u0000\u0000\u0196\u0197"+
		"\u0005\u00ec\u0000\u0000\u0197\u0199\u0005w\u0000\u0000\u0198\u0195\u0001"+
		"\u0000\u0000\u0000\u0198\u0199\u0001\u0000\u0000\u0000\u0199\u01ef\u0001"+
		"\u0000\u0000\u0000\u019a\u019b\u0005\u00d3\u0000\u0000\u019b\u019f\u0005"+
		"\u00e4\u0000\u0000\u019c\u019d\u0005v\u0000\u0000\u019d\u019e\u0005\u00ec"+
		"\u0000\u0000\u019e\u01a0\u0005w\u0000\u0000\u019f\u019c\u0001\u0000\u0000"+
		"\u0000\u019f\u01a0\u0001\u0000\u0000\u0000\u01a0\u01ef\u0001\u0000\u0000"+
		"\u0000\u01a1\u01ef\u0005\u00ac\u0000\u0000\u01a2\u01ef\u0005\u00ae\u0000"+
		"\u0000\u01a3\u01a7\u0005\u00e5\u0000\u0000\u01a4\u01a5\u0005v\u0000\u0000"+
		"\u01a5\u01a6\u0005\u00ec\u0000\u0000\u01a6\u01a8\u0005w\u0000\u0000\u01a7"+
		"\u01a4\u0001\u0000\u0000\u0000\u01a7\u01a8\u0001\u0000\u0000\u0000\u01a8"+
		"\u01ef\u0001\u0000\u0000\u0000\u01a9\u01ef\u0005\u00d4\u0000\u0000\u01aa"+
		"\u01ef\u0005\u00c3\u0000\u0000\u01ab\u01ef\u0005\u00d5\u0000\u0000\u01ac"+
		"\u01b0\u0005\u00d6\u0000\u0000\u01ad\u01ae\u0005v\u0000\u0000\u01ae\u01af"+
		"\u0005\u00ec\u0000\u0000\u01af\u01b1\u0005w\u0000\u0000\u01b0\u01ad\u0001"+
		"\u0000\u0000\u0000\u01b0\u01b1\u0001\u0000\u0000\u0000\u01b1\u01b8\u0001"+
		"\u0000\u0000\u0000\u01b2\u01b3\u0005-\u0000\u0000\u01b3\u01b4\u0005.\u0000"+
		"\u0000\u01b4\u01b9\u0005/\u0000\u0000\u01b5\u01b6\u00050\u0000\u0000\u01b6"+
		"\u01b7\u0005.\u0000\u0000\u01b7\u01b9\u0005/\u0000\u0000\u01b8\u01b2\u0001"+
		"\u0000\u0000\u0000\u01b8\u01b5\u0001\u0000\u0000\u0000\u01b8\u01b9\u0001"+
		"\u0000\u0000\u0000\u01b9\u01ef\u0001\u0000\u0000\u0000\u01ba\u01be\u0005"+
		"\u00c4\u0000\u0000\u01bb\u01bc\u0005v\u0000\u0000\u01bc\u01bd\u0005\u00ec"+
		"\u0000\u0000\u01bd\u01bf\u0005w\u0000\u0000\u01be\u01bb\u0001\u0000\u0000"+
		"\u0000\u01be\u01bf\u0001\u0000\u0000\u0000\u01bf\u01c6\u0001\u0000\u0000"+
		"\u0000\u01c0\u01c1\u0005-\u0000\u0000\u01c1\u01c2\u0005.\u0000\u0000\u01c2"+
		"\u01c7\u0005/\u0000\u0000\u01c3\u01c4\u00050\u0000\u0000\u01c4\u01c5\u0005"+
		".\u0000\u0000\u01c5\u01c7\u0005/\u0000\u0000\u01c6\u01c0\u0001\u0000\u0000"+
		"\u0000\u01c6\u01c3\u0001\u0000\u0000\u0000\u01c6\u01c7\u0001\u0000\u0000"+
		"\u0000\u01c7\u01ef\u0001\u0000\u0000\u0000\u01c8\u01ef\u0005\u00d7\u0000"+
		"\u0000\u01c9\u01ef\u0005\u00d8\u0000\u0000\u01ca\u01ef\u0005\u009f\u0000"+
		"\u0000\u01cb\u01ef\u0005\u00d9\u0000\u0000\u01cc\u01cd\u0005\u00da\u0000"+
		"\u0000\u01cd\u01ce\u0005v\u0000\u0000\u01ce\u01d3\u0005\u00eb\u0000\u0000"+
		"\u01cf\u01d0\u0005}\u0000\u0000\u01d0\u01d2\u0005\u00eb\u0000\u0000\u01d1"+
		"\u01cf\u0001\u0000\u0000\u0000\u01d2\u01d5\u0001\u0000\u0000\u0000\u01d3"+
		"\u01d1\u0001\u0000\u0000\u0000\u01d3\u01d4\u0001\u0000\u0000\u0000\u01d4"+
		"\u01d6\u0001\u0000\u0000\u0000\u01d5\u01d3\u0001\u0000\u0000\u0000\u01d6"+
		"\u01ef\u0005w\u0000\u0000\u01d7\u01ef\u0005\u00db\u0000\u0000\u01d8\u01ef"+
		"\u0005\u00dc\u0000\u0000\u01d9\u01ef\u0005\u00dd\u0000\u0000\u01da\u01ef"+
		"\u0005\u00de\u0000\u0000\u01db\u01ef\u0005\u00df\u0000\u0000\u01dc\u01ef"+
		"\u0005\u00e0\u0000\u0000\u01dd\u01ef\u0005\u00e1\u0000\u0000\u01de\u01e2"+
		"\u0005\u00e2\u0000\u0000\u01df\u01e0\u0005v\u0000\u0000\u01e0\u01e1\u0005"+
		"\u00ec\u0000\u0000\u01e1\u01e3\u0005w\u0000\u0000\u01e2\u01df\u0001\u0000"+
		"\u0000\u0000\u01e2\u01e3\u0001\u0000\u0000\u0000\u01e3\u01ef\u0001\u0000"+
		"\u0000\u0000\u01e4\u01e8\u0005\u00e3\u0000\u0000\u01e5\u01e6\u0005v\u0000"+
		"\u0000\u01e6\u01e7\u0005\u00ec\u0000\u0000\u01e7\u01e9\u0005w\u0000\u0000"+
		"\u01e8\u01e5\u0001\u0000\u0000\u0000\u01e8\u01e9\u0001\u0000\u0000\u0000"+
		"\u01e9\u01ef\u0001\u0000\u0000\u0000\u01ea\u01ef\u0005\u00b0\u0000\u0000"+
		"\u01eb\u01ec\u0005\u00ea\u0000\u0000\u01ec\u01ef\u0005\u00ea\u0000\u0000"+
		"\u01ed\u01ef\u0005\u00ea\u0000\u0000\u01ee\u0175\u0001\u0000\u0000\u0000"+
		"\u01ee\u0176\u0001\u0000\u0000\u0000\u01ee\u0177\u0001\u0000\u0000\u0000"+
		"\u01ee\u0178\u0001\u0000\u0000\u0000\u01ee\u0179\u0001\u0000\u0000\u0000"+
		"\u01ee\u017a\u0001\u0000\u0000\u0000\u01ee\u017b\u0001\u0000\u0000\u0000"+
		"\u01ee\u017c\u0001\u0000\u0000\u0000\u01ee\u017d\u0001\u0000\u0000\u0000"+
		"\u01ee\u017e\u0001\u0000\u0000\u0000\u01ee\u017f\u0001\u0000\u0000\u0000"+
		"\u01ee\u0180\u0001\u0000\u0000\u0000\u01ee\u0182\u0001\u0000\u0000\u0000"+
		"\u01ee\u018c\u0001\u0000\u0000\u0000\u01ee\u018d\u0001\u0000\u0000\u0000"+
		"\u01ee\u018e\u0001\u0000\u0000\u0000\u01ee\u0194\u0001\u0000\u0000\u0000"+
		"\u01ee\u019a\u0001\u0000\u0000\u0000\u01ee\u01a1\u0001\u0000\u0000\u0000"+
		"\u01ee\u01a2\u0001\u0000\u0000\u0000\u01ee\u01a3\u0001\u0000\u0000\u0000"+
		"\u01ee\u01a9\u0001\u0000\u0000\u0000\u01ee\u01aa\u0001\u0000\u0000\u0000"+
		"\u01ee\u01ab\u0001\u0000\u0000\u0000\u01ee\u01ac\u0001\u0000\u0000\u0000"+
		"\u01ee\u01ba\u0001\u0000\u0000\u0000\u01ee\u01c8\u0001\u0000\u0000\u0000"+
		"\u01ee\u01c9\u0001\u0000\u0000\u0000\u01ee\u01ca\u0001\u0000\u0000\u0000"+
		"\u01ee\u01cb\u0001\u0000\u0000\u0000\u01ee\u01cc\u0001\u0000\u0000\u0000"+
		"\u01ee\u01d7\u0001\u0000\u0000\u0000\u01ee\u01d8\u0001\u0000\u0000\u0000"+
		"\u01ee\u01d9\u0001\u0000\u0000\u0000\u01ee\u01da\u0001\u0000\u0000\u0000"+
		"\u01ee\u01db\u0001\u0000\u0000\u0000\u01ee\u01dc\u0001\u0000\u0000\u0000"+
		"\u01ee\u01dd\u0001\u0000\u0000\u0000\u01ee\u01de\u0001\u0000\u0000\u0000"+
		"\u01ee\u01e4\u0001\u0000\u0000\u0000\u01ee\u01ea\u0001\u0000\u0000\u0000"+
		"\u01ee\u01eb\u0001\u0000\u0000\u0000\u01ee\u01ed\u0001\u0000\u0000\u0000"+
		"\u01ef\u001b\u0001\u0000\u0000\u0000\u01f0\u01f1\u0005\u00ea\u0000\u0000"+
		"\u01f1\u01f3\u0005v\u0000\u0000\u01f2\u01f4\u0003~?\u0000\u01f3\u01f2"+
		"\u0001\u0000\u0000\u0000\u01f3\u01f4\u0001\u0000\u0000\u0000\u01f4\u01f5"+
		"\u0001\u0000\u0000\u0000\u01f5\u01f6\u0005w\u0000\u0000\u01f6\u001d\u0001"+
		"\u0000\u0000\u0000\u01f7\u01f8\u0005\u00a9\u0000\u0000\u01f8\u01f9\u0005"+
		"1\u0000\u0000\u01f9\u001f\u0001\u0000\u0000\u0000\u01fa\u01fb\u0005\u0095"+
		"\u0000\u0000\u01fb\u0242\u0005\u0094\u0000\u0000\u01fc\u0242\u0005\u0094"+
		"\u0000\u0000\u01fd\u0200\u0005\u00aa\u0000\u0000\u01fe\u01ff\u00052\u0000"+
		"\u0000\u01ff\u0201\u0003\\.\u0000\u0200\u01fe\u0001\u0000\u0000\u0000"+
		"\u0200\u0201\u0001\u0000\u0000\u0000\u0201\u0242\u0001\u0000\u0000\u0000"+
		"\u0202\u0205\u0005\u00e9\u0000\u0000\u0203\u0204\u00052\u0000\u0000\u0204"+
		"\u0206\u0003\\.\u0000\u0205\u0203\u0001\u0000\u0000\u0000\u0205\u0206"+
		"\u0001\u0000\u0000\u0000\u0206\u0242\u0001\u0000\u0000\u0000\u0207\u0208"+
		"\u0005\u00a0\u0000\u0000\u0208\u0242\u0003|>\u0000\u0209\u020a\u0005\u009b"+
		"\u0000\u0000\u020a\u020b\u0005v\u0000\u0000\u020b\u020c\u0003:\u001d\u0000"+
		"\u020c\u020d\u0005w\u0000\u0000\u020d\u0242\u0001\u0000\u0000\u0000\u020e"+
		"\u0212\u0005\u0098\u0000\u0000\u020f\u0210\u0003^/\u0000\u0210\u0211\u0005"+
		"\u0092\u0000\u0000\u0211\u0213\u0001\u0000\u0000\u0000\u0212\u020f\u0001"+
		"\u0000\u0000\u0000\u0212\u0213\u0001\u0000\u0000\u0000\u0213\u0214\u0001"+
		"\u0000\u0000\u0000\u0214\u0215\u0003`0\u0000\u0215\u0216\u0005v\u0000"+
		"\u0000\u0216\u0217\u0003b1\u0000\u0217\u0219\u0005w\u0000\u0000\u0218"+
		"\u021a\u0003(\u0014\u0000\u0219\u0218\u0001\u0000\u0000\u0000\u0219\u021a"+
		"\u0001\u0000\u0000\u0000\u021a\u0242\u0001\u0000\u0000\u0000\u021b\u021c"+
		"\u0005\u00c0\u0000\u0000\u021c\u021d\u0005\u00bf\u0000\u0000\u021d\u021e"+
		"\u0005v\u0000\u0000\u021e\u021f\u0003Z-\u0000\u021f\u0220\u0005w\u0000"+
		"\u0000\u0220\u0224\u0005\u0098\u0000\u0000\u0221\u0222\u0003^/\u0000\u0222"+
		"\u0223\u0005\u0092\u0000\u0000\u0223\u0225\u0001\u0000\u0000\u0000\u0224"+
		"\u0221\u0001\u0000\u0000\u0000\u0224\u0225\u0001\u0000\u0000\u0000\u0225"+
		"\u0226\u0001\u0000\u0000\u0000\u0226\u0227\u0003`0\u0000\u0227\u0228\u0005"+
		"v\u0000\u0000\u0228\u0229\u0003Z-\u0000\u0229\u022c\u0005w\u0000\u0000"+
		"\u022a\u022b\u0005\u008e\u0000\u0000\u022b\u022d\u0003d2\u0000\u022c\u022a"+
		"\u0001\u0000\u0000\u0000\u022c\u022d\u0001\u0000\u0000\u0000\u022d\u0231"+
		"\u0001\u0000\u0000\u0000\u022e\u0230\u0003(\u0014\u0000\u022f\u022e\u0001"+
		"\u0000\u0000\u0000\u0230\u0233\u0001\u0000\u0000\u0000\u0231\u022f\u0001"+
		"\u0000\u0000\u0000\u0231\u0232\u0001\u0000\u0000\u0000\u0232\u0242\u0001"+
		"\u0000\u0000\u0000\u0233\u0231\u0001\u0000\u0000\u0000\u0234\u0235\u0005"+
		"\u00c1\u0000\u0000\u0235\u0236\u0005\u0089\u0000\u0000\u0236\u0237\u0005"+
		"\u00ea\u0000\u0000\u0237\u0238\u0005v\u0000\u0000\u0238\u0239\u0003$\u0012"+
		"\u0000\u0239\u023c\u0005w\u0000\u0000\u023a\u023b\u00053\u0000\u0000\u023b"+
		"\u023d\u0003:\u001d\u0000\u023c\u023a\u0001\u0000\u0000\u0000\u023c\u023d"+
		"\u0001\u0000\u0000\u0000\u023d\u0242\u0001\u0000\u0000\u0000\u023e\u0242"+
		"\u00054\u0000\u0000\u023f\u0240\u0005\u00be\u0000\u0000\u0240\u0242\u0005"+
		"\u00bf\u0000\u0000\u0241\u01fa\u0001\u0000\u0000\u0000\u0241\u01fc\u0001"+
		"\u0000\u0000\u0000\u0241\u01fd\u0001\u0000\u0000\u0000\u0241\u0202\u0001"+
		"\u0000\u0000\u0000\u0241\u0207\u0001\u0000\u0000\u0000\u0241\u0209\u0001"+
		"\u0000\u0000\u0000\u0241\u020e\u0001\u0000\u0000\u0000\u0241\u021b\u0001"+
		"\u0000\u0000\u0000\u0241\u0234\u0001\u0000\u0000\u0000\u0241\u023e\u0001"+
		"\u0000\u0000\u0000\u0241\u023f\u0001\u0000\u0000\u0000\u0242!\u0001\u0000"+
		"\u0000\u0000\u0243\u0245\u0005\u0093\u0000\u0000\u0244\u0246\u0005\u00ea"+
		"\u0000\u0000\u0245\u0244\u0001\u0000\u0000\u0000\u0245\u0246\u0001\u0000"+
		"\u0000\u0000\u0246\u0247\u0001\u0000\u0000\u0000\u0247\u0248\u0005\u009a"+
		"\u0000\u0000\u0248\u0249\u0005v\u0000\u0000\u0249\u024a\u0003Z-\u0000"+
		"\u024a\u024b\u0005w\u0000\u0000\u024b\u0297\u0001\u0000\u0000\u0000\u024c"+
		"\u024e\u0005\u0093\u0000\u0000\u024d\u024f\u0005\u00ea\u0000\u0000\u024e"+
		"\u024d\u0001\u0000\u0000\u0000\u024e\u024f\u0001\u0000\u0000\u0000\u024f"+
		"\u0250\u0001\u0000\u0000\u0000\u0250\u0251\u0005\u00af\u0000\u0000\u0251"+
		"\u0252\u0005v\u0000\u0000\u0252\u0253\u0003Z-\u0000\u0253\u0254\u0005"+
		"w\u0000\u0000\u0254\u0258\u0005\u0098\u0000\u0000\u0255\u0256\u0003^/"+
		"\u0000\u0256\u0257\u0005\u0092\u0000\u0000\u0257\u0259\u0001\u0000\u0000"+
		"\u0000\u0258\u0255\u0001\u0000\u0000\u0000\u0258\u0259\u0001\u0000\u0000"+
		"\u0000\u0259\u025a\u0001\u0000\u0000\u0000\u025a\u025b\u0003`0\u0000\u025b"+
		"\u025c\u0005v\u0000\u0000\u025c\u025d\u0003Z-\u0000\u025d\u0261\u0005"+
		"w\u0000\u0000\u025e\u0260\u0003(\u0014\u0000\u025f\u025e\u0001\u0000\u0000"+
		"\u0000\u0260\u0263\u0001\u0000\u0000\u0000\u0261\u025f\u0001\u0000\u0000"+
		"\u0000\u0261\u0262\u0001\u0000\u0000\u0000\u0262\u0297\u0001\u0000\u0000"+
		"\u0000\u0263\u0261\u0001\u0000\u0000\u0000\u0264\u0265\u0005\u00af\u0000"+
		"\u0000\u0265\u0266\u0005v\u0000\u0000\u0266\u0267\u0003Z-\u0000\u0267"+
		"\u0268\u0005w\u0000\u0000\u0268\u026c\u0005\u0098\u0000\u0000\u0269\u026a"+
		"\u0003^/\u0000\u026a\u026b\u0005\u0092\u0000\u0000\u026b\u026d\u0001\u0000"+
		"\u0000\u0000\u026c\u0269\u0001\u0000\u0000\u0000\u026c\u026d\u0001\u0000"+
		"\u0000\u0000\u026d\u026e\u0001\u0000\u0000\u0000\u026e\u026f\u0003`0\u0000"+
		"\u026f\u0270\u0005v\u0000\u0000\u0270\u0271\u0003Z-\u0000\u0271\u0275"+
		"\u0005w\u0000\u0000\u0272\u0274\u0003(\u0014\u0000\u0273\u0272\u0001\u0000"+
		"\u0000\u0000\u0274\u0277\u0001\u0000\u0000\u0000\u0275\u0273\u0001\u0000"+
		"\u0000\u0000\u0275\u0276\u0001\u0000\u0000\u0000\u0276\u0297\u0001\u0000"+
		"\u0000\u0000\u0277\u0275\u0001\u0000\u0000\u0000\u0278\u027a\u0005\u0093"+
		"\u0000\u0000\u0279\u027b\u0005\u00ea\u0000\u0000\u027a\u0279\u0001\u0000"+
		"\u0000\u0000\u027a\u027b\u0001\u0000\u0000\u0000\u027b\u027c\u0001\u0000"+
		"\u0000\u0000\u027c\u027d\u0005\u00aa\u0000\u0000\u027d\u027e\u0005v\u0000"+
		"\u0000\u027e\u027f\u0003Z-\u0000\u027f\u0280\u0005w\u0000\u0000\u0280"+
		"\u0297\u0001\u0000\u0000\u0000\u0281\u0283\u0005\u0093\u0000\u0000\u0282"+
		"\u0284\u0005\u00ea\u0000\u0000\u0283\u0282\u0001\u0000\u0000\u0000\u0283"+
		"\u0284\u0001\u0000\u0000\u0000\u0284\u0285\u0001\u0000\u0000\u0000\u0285"+
		"\u0286\u0005\u009b\u0000\u0000\u0286\u0287\u0005v\u0000\u0000\u0287\u0288"+
		"\u0003:\u001d\u0000\u0288\u0289\u0005w\u0000\u0000\u0289\u0297\u0001\u0000"+
		"\u0000\u0000\u028a\u028b\u0005\u00c1\u0000\u0000\u028b\u028c\u0005\u0089"+
		"\u0000\u0000\u028c\u028d\u0005\u00ea\u0000\u0000\u028d\u028e\u0005v\u0000"+
		"\u0000\u028e\u028f\u0003$\u0012\u0000\u028f\u0290\u0005w\u0000\u0000\u0290"+
		"\u0297\u0001\u0000\u0000\u0000\u0291\u0292\u0005\u009a\u0000\u0000\u0292"+
		"\u0293\u0005v\u0000\u0000\u0293\u0294\u0003Z-\u0000\u0294\u0295\u0005"+
		"w\u0000\u0000\u0295\u0297\u0001\u0000\u0000\u0000\u0296\u0243\u0001\u0000"+
		"\u0000\u0000\u0296\u024c\u0001\u0000\u0000\u0000\u0296\u0264\u0001\u0000"+
		"\u0000\u0000\u0296\u0278\u0001\u0000\u0000\u0000\u0296\u0281\u0001\u0000"+
		"\u0000\u0000\u0296\u028a\u0001\u0000\u0000\u0000\u0296\u0291\u0001\u0000"+
		"\u0000\u0000\u0297#\u0001\u0000\u0000\u0000\u0298\u029d\u0003&\u0013\u0000"+
		"\u0299\u029a\u0005}\u0000\u0000\u029a\u029c\u0003&\u0013\u0000\u029b\u0299"+
		"\u0001\u0000\u0000\u0000\u029c\u029f\u0001\u0000\u0000\u0000\u029d\u029b"+
		"\u0001\u0000\u0000\u0000\u029d\u029e\u0001\u0000\u0000\u0000\u029e%\u0001"+
		"\u0000\u0000\u0000\u029f\u029d\u0001\u0000\u0000\u0000\u02a0\u02a1\u0003"+
		"b1\u0000\u02a1\u02a2\u00050\u0000\u0000\u02a2\u02a3\u0003B!\u0000\u02a3"+
		"\'\u0001\u0000\u0000\u0000\u02a4\u02a5\u0005\u00a8\u0000\u0000\u02a5\u02ae"+
		"\u0003*\u0015\u0000\u02a6\u02a7\u0005\u00a9\u0000\u0000\u02a7\u02ae\u0003"+
		"*\u0015\u0000\u02a8\u02ab\u00055\u0000\u0000\u02a9\u02aa\u00056\u0000"+
		"\u0000\u02aa\u02ac\u0007\u0003\u0000\u0000\u02ab\u02a9\u0001\u0000\u0000"+
		"\u0000\u02ab\u02ac\u0001\u0000\u0000\u0000\u02ac\u02ae\u0001\u0000\u0000"+
		"\u0000\u02ad\u02a4\u0001\u0000\u0000\u0000\u02ad\u02a6\u0001\u0000\u0000"+
		"\u0000\u02ad\u02a8\u0001\u0000\u0000\u0000\u02ae)\u0001\u0000\u0000\u0000"+
		"\u02af\u02b0\u0007\u0004\u0000\u0000\u02b0+\u0001\u0000\u0000\u0000\u02b1"+
		"\u02b2\u0005\u008d\u0000\u0000\u02b2\u02b3\u0005\u00b0\u0000\u0000\u02b3"+
		"\u02b4\u0005\u00ea\u0000\u0000\u02b4\u02b5\u0003.\u0017\u0000\u02b5\u02b6"+
		"\u00030\u0018\u0000\u02b6\u02b7\u0005\u007f\u0000\u0000\u02b7\u02b8\u0003"+
		"`0\u0000\u02b8\u02b9\u00059\u0000\u0000\u02b9\u02ba\u0005:\u0000\u0000"+
		"\u02ba\u02bb\u0005\u00ea\u0000\u0000\u02bb\u02c4\u0005v\u0000\u0000\u02bc"+
		"\u02c1\u0003|>\u0000\u02bd\u02be\u0005}\u0000\u0000\u02be\u02c0\u0003"+
		"|>\u0000\u02bf\u02bd\u0001\u0000\u0000\u0000\u02c0\u02c3\u0001\u0000\u0000"+
		"\u0000\u02c1\u02bf\u0001\u0000\u0000\u0000\u02c1\u02c2\u0001\u0000\u0000"+
		"\u0000\u02c2\u02c5\u0001\u0000\u0000\u0000\u02c3\u02c1\u0001\u0000\u0000"+
		"\u0000\u02c4\u02bc\u0001\u0000\u0000\u0000\u02c4\u02c5\u0001\u0000\u0000"+
		"\u0000\u02c5\u02c6\u0001\u0000\u0000\u0000\u02c6\u02c7\u0005w\u0000\u0000"+
		"\u02c7\u02c8\u0005z\u0000\u0000\u02c8-\u0001\u0000\u0000\u0000\u02c9\u02ca"+
		"\u0007\u0005\u0000\u0000\u02ca/\u0001\u0000\u0000\u0000\u02cb\u02d0\u0007"+
		"\u0006\u0000\u0000\u02cc\u02cd\u0005<\u0000\u0000\u02cd\u02cf\u0007\u0006"+
		"\u0000\u0000\u02ce\u02cc\u0001\u0000\u0000\u0000\u02cf\u02d2\u0001\u0000"+
		"\u0000\u0000\u02d0\u02ce\u0001\u0000\u0000\u0000\u02d0\u02d1\u0001\u0000"+
		"\u0000\u0000\u02d11\u0001\u0000\u0000\u0000\u02d2\u02d0\u0001\u0000\u0000"+
		"\u0000\u02d3\u02d7\u00034\u001a\u0000\u02d4\u02d7\u00036\u001b\u0000\u02d5"+
		"\u02d7\u00038\u001c\u0000\u02d6\u02d3\u0001\u0000\u0000\u0000\u02d6\u02d4"+
		"\u0001\u0000\u0000\u0000\u02d6\u02d5\u0001\u0000\u0000\u0000\u02d73\u0001"+
		"\u0000\u0000\u0000\u02d8\u02d9\u0005=\u0000\u0000\u02d9\u02da\u0003`0"+
		"\u0000\u02da\u02db\u0005v\u0000\u0000\u02db\u02e0\u0003b1\u0000\u02dc"+
		"\u02dd\u0005}\u0000\u0000\u02dd\u02df\u0003b1\u0000\u02de\u02dc\u0001"+
		"\u0000\u0000\u0000\u02df\u02e2\u0001\u0000\u0000\u0000\u02e0\u02de\u0001"+
		"\u0000\u0000\u0000\u02e0\u02e1\u0001\u0000\u0000\u0000\u02e1\u02e3\u0001"+
		"\u0000\u0000\u0000\u02e2\u02e0\u0001\u0000\u0000\u0000\u02e3\u02e4\u0005"+
		"w\u0000\u0000\u02e4\u02e5\u0005>\u0000\u0000\u02e5\u02e6\u0005v\u0000"+
		"\u0000\u02e6\u02eb\u0003|>\u0000\u02e7\u02e8\u0005}\u0000\u0000\u02e8"+
		"\u02ea\u0003|>\u0000\u02e9\u02e7\u0001\u0000\u0000\u0000\u02ea\u02ed\u0001"+
		"\u0000\u0000\u0000\u02eb\u02e9\u0001\u0000\u0000\u0000\u02eb\u02ec\u0001"+
		"\u0000\u0000\u0000\u02ec\u02ee\u0001\u0000\u0000\u0000\u02ed\u02eb\u0001"+
		"\u0000\u0000\u0000\u02ee\u02ef\u0005w\u0000\u0000\u02ef\u02f0\u0005z\u0000"+
		"\u0000\u02f05\u0001\u0000\u0000\u0000\u02f1\u02f2\u0005\u00b3\u0000\u0000"+
		"\u02f2\u02f3\u0003`0\u0000\u02f3\u02f4\u0005\u0097\u0000\u0000\u02f4\u02f5"+
		"\u0003b1\u0000\u02f5\u02f6\u0005\u00ab\u0000\u0000\u02f6\u02fe\u0003|"+
		">\u0000\u02f7\u02f8\u0005}\u0000\u0000\u02f8\u02f9\u0003b1\u0000\u02f9"+
		"\u02fa\u0005\u00ab\u0000\u0000\u02fa\u02fb\u0003|>\u0000\u02fb\u02fd\u0001"+
		"\u0000\u0000\u0000\u02fc\u02f7\u0001\u0000\u0000\u0000\u02fd\u0300\u0001"+
		"\u0000\u0000\u0000\u02fe\u02fc\u0001\u0000\u0000\u0000\u02fe\u02ff\u0001"+
		"\u0000\u0000\u0000\u02ff\u0303\u0001\u0000\u0000\u0000\u0300\u02fe\u0001"+
		"\u0000\u0000\u0000\u0301\u0302\u00053\u0000\u0000\u0302\u0304\u0003:\u001d"+
		"\u0000\u0303\u0301\u0001\u0000\u0000\u0000\u0303\u0304\u0001\u0000\u0000"+
		"\u0000\u0304\u0305\u0001\u0000\u0000\u0000\u0305\u0306\u0005z\u0000\u0000"+
		"\u03067\u0001\u0000\u0000\u0000\u0307\u0308\u0005?\u0000\u0000\u0308\u030b"+
		"\u0003`0\u0000\u0309\u030a\u00053\u0000\u0000\u030a\u030c\u0003:\u001d"+
		"\u0000\u030b\u0309\u0001\u0000\u0000\u0000\u030b\u030c\u0001\u0000\u0000"+
		"\u0000\u030c\u030d\u0001\u0000\u0000\u0000\u030d\u030e\u0005z\u0000\u0000"+
		"\u030e9\u0001\u0000\u0000\u0000\u030f\u0310\u0003<\u001e\u0000\u0310;"+
		"\u0001\u0000\u0000\u0000\u0311\u0312\u0006\u001e\uffff\uffff\u0000\u0312"+
		"\u031a\u0003>\u001f\u0000\u0313\u0314\u0005v\u0000\u0000\u0314\u0315\u0003"+
		"<\u001e\u0000\u0315\u0316\u0005w\u0000\u0000\u0316\u031a\u0001\u0000\u0000"+
		"\u0000\u0317\u0318\u0005\u0095\u0000\u0000\u0318\u031a\u0003<\u001e\u0003"+
		"\u0319\u0311\u0001\u0000\u0000\u0000\u0319\u0313\u0001\u0000\u0000\u0000"+
		"\u0319\u0317\u0001\u0000\u0000\u0000\u031a\u0323\u0001\u0000\u0000\u0000"+
		"\u031b\u031c\n\u0002\u0000\u0000\u031c\u031d\u0005\u00bb\u0000\u0000\u031d"+
		"\u0322\u0003<\u001e\u0003\u031e\u031f\n\u0001\u0000\u0000\u031f\u0320"+
		"\u0005<\u0000\u0000\u0320\u0322\u0003<\u001e\u0002\u0321\u031b\u0001\u0000"+
		"\u0000\u0000\u0321\u031e\u0001\u0000\u0000\u0000\u0322\u0325\u0001\u0000"+
		"\u0000\u0000\u0323\u0321\u0001\u0000\u0000\u0000\u0323\u0324\u0001\u0000"+
		"\u0000\u0000\u0324=\u0001\u0000\u0000\u0000\u0325\u0323\u0001\u0000\u0000"+
		"\u0000\u0326\u0327\u0003t:\u0000\u0327\u0328\u0005\u0096\u0000\u0000\u0328"+
		"\u0329\u0005\u0095\u0000\u0000\u0329\u032a\u0005\u0094\u0000\u0000\u032a"+
		"\u0356\u0001\u0000\u0000\u0000\u032b\u032c\u0003t:\u0000\u032c\u032d\u0005"+
		"\u0096\u0000\u0000\u032d\u032e\u0005\u0094\u0000\u0000\u032e\u0356\u0001"+
		"\u0000\u0000\u0000\u032f\u0330\u0003t:\u0000\u0330\u0331\u0005\u00b9\u0000"+
		"\u0000\u0331\u0334\u0005v\u0000\u0000\u0332\u0335\u0003t:\u0000\u0333"+
		"\u0335\u0003@ \u0000\u0334\u0332\u0001\u0000\u0000\u0000\u0334\u0333\u0001"+
		"\u0000\u0000\u0000\u0335\u033d\u0001\u0000\u0000\u0000\u0336\u0339\u0005"+
		"}\u0000\u0000\u0337\u033a\u0003t:\u0000\u0338\u033a\u0003@ \u0000\u0339"+
		"\u0337\u0001\u0000\u0000\u0000\u0339\u0338\u0001\u0000\u0000\u0000\u033a"+
		"\u033c\u0001\u0000\u0000\u0000\u033b\u0336\u0001\u0000\u0000\u0000\u033c"+
		"\u033f\u0001\u0000\u0000\u0000\u033d\u033b\u0001\u0000\u0000\u0000\u033d"+
		"\u033e\u0001\u0000\u0000\u0000\u033e\u0340\u0001\u0000\u0000\u0000\u033f"+
		"\u033d\u0001\u0000\u0000\u0000\u0340\u0341\u0005w\u0000\u0000\u0341\u0356"+
		"\u0001\u0000\u0000\u0000\u0342\u0343\u0003t:\u0000\u0343\u0344\u0005\u00ba"+
		"\u0000\u0000\u0344\u0345\u0003t:\u0000\u0345\u0346\u0005\u00bb\u0000\u0000"+
		"\u0346\u0347\u0003t:\u0000\u0347\u0356\u0001\u0000\u0000\u0000\u0348\u0349"+
		"\u0003t:\u0000\u0349\u034a\u0005\u00bc\u0000\u0000\u034a\u034b\u0005\u00eb"+
		"\u0000\u0000\u034b\u0356\u0001\u0000\u0000\u0000\u034c\u034d\u0003t:\u0000"+
		"\u034d\u034e\u0005\u00bd\u0000\u0000\u034e\u034f\u0005\u00eb\u0000\u0000"+
		"\u034f\u0356\u0001\u0000\u0000\u0000\u0350\u0351\u0003t:\u0000\u0351\u0352"+
		"\u0003B!\u0000\u0352\u0353\u0003t:\u0000\u0353\u0356\u0001\u0000\u0000"+
		"\u0000\u0354\u0356\u0003\u0092I\u0000\u0355\u0326\u0001\u0000\u0000\u0000"+
		"\u0355\u032b\u0001\u0000\u0000\u0000\u0355\u032f\u0001\u0000\u0000\u0000"+
		"\u0355\u0342\u0001\u0000\u0000\u0000\u0355\u0348\u0001\u0000\u0000\u0000"+
		"\u0355\u034c\u0001\u0000\u0000\u0000\u0355\u0350\u0001\u0000\u0000\u0000"+
		"\u0355\u0354\u0001\u0000\u0000\u0000\u0356?\u0001\u0000\u0000\u0000\u0357"+
		"\u0358\u0005v\u0000\u0000\u0358\u0359\u0003D\"\u0000\u0359\u035a\u0005"+
		"w\u0000\u0000\u035aA\u0001\u0000\u0000\u0000\u035b\u035c\u0007\u0007\u0000"+
		"\u0000\u035cC\u0001\u0000\u0000\u0000\u035d\u0368\u0003\u0002\u0001\u0000"+
		"\u035e\u0368\u0003F#\u0000\u035f\u0368\u0003H$\u0000\u0360\u0368\u0003"+
		"J%\u0000\u0361\u0368\u0003n7\u0000\u0362\u0368\u0003\u0088D\u0000\u0363"+
		"\u0368\u0003,\u0016\u0000\u0364\u0368\u0003\u0014\n\u0000\u0365\u0368"+
		"\u00032\u0019\u0000\u0366\u0368\u0003\u0082A\u0000\u0367\u035d\u0001\u0000"+
		"\u0000\u0000\u0367\u035e\u0001\u0000\u0000\u0000\u0367\u035f\u0001\u0000"+
		"\u0000\u0000\u0367\u0360\u0001\u0000\u0000\u0000\u0367\u0361\u0001\u0000"+
		"\u0000\u0000\u0367\u0362\u0001\u0000\u0000\u0000\u0367\u0363\u0001\u0000"+
		"\u0000\u0000\u0367\u0364\u0001\u0000\u0000\u0000\u0367\u0365\u0001\u0000"+
		"\u0000\u0000\u0367\u0366\u0001\u0000\u0000\u0000\u0368\u036b\u0001\u0000"+
		"\u0000\u0000\u0369\u0367\u0001\u0000\u0000\u0000\u0369\u036a\u0001\u0000"+
		"\u0000\u0000\u036a\u036c\u0001\u0000\u0000\u0000\u036b\u0369\u0001\u0000"+
		"\u0000\u0000\u036c\u036d\u0005\u0000\u0000\u0001\u036dE\u0001\u0000\u0000"+
		"\u0000\u036e\u036f\u0005L\u0000\u0000\u036f\u0370\u0005\u00ea\u0000\u0000"+
		"\u0370\u0371\u0005z\u0000\u0000\u0371G\u0001\u0000\u0000\u0000\u0372\u0373"+
		"\u0005M\u0000\u0000\u0373\u0374\u0005\u00ea\u0000\u0000\u0374\u0375\u0005"+
		"\u0083\u0000\u0000\u0375\u0376\u0003N\'\u0000\u0376\u0377\u0005z\u0000"+
		"\u0000\u0377I\u0001\u0000\u0000\u0000\u0378\u0379\u0005N\u0000\u0000\u0379"+
		"\u037a\u0005\u00ea\u0000\u0000\u037a\u0383\u0005v\u0000\u0000\u037b\u0380"+
		"\u0003L&\u0000\u037c\u037d\u0005}\u0000\u0000\u037d\u037f\u0003L&\u0000"+
		"\u037e\u037c\u0001\u0000\u0000\u0000\u037f\u0382\u0001\u0000\u0000\u0000"+
		"\u0380\u037e\u0001\u0000\u0000\u0000\u0380\u0381\u0001\u0000\u0000\u0000"+
		"\u0381\u0384\u0001\u0000\u0000\u0000\u0382\u0380\u0001\u0000\u0000\u0000"+
		"\u0383\u037b\u0001\u0000\u0000\u0000\u0383\u0384\u0001\u0000\u0000\u0000"+
		"\u0384\u0385\u0001\u0000\u0000\u0000\u0385\u0386\u0005w\u0000\u0000\u0386"+
		"\u0387\u0005O\u0000\u0000\u0387\u0388\u0003\u001a\r\u0000\u0388\u0389"+
		"\u0005\u0083\u0000\u0000\u0389\u038a\u0005\u008a\u0000\u0000\u038a\u038b"+
		"\u0003\u0084B\u0000\u038b\u038c\u0005\u008a\u0000\u0000\u038c\u038d\u0005"+
		"P\u0000\u0000\u038d\u038e\u0005\u00ea\u0000\u0000\u038e\u038f\u0005z\u0000"+
		"\u0000\u038fK\u0001\u0000\u0000\u0000\u0390\u0391\u0005\u00ea\u0000\u0000"+
		"\u0391\u0392\u0003\u001a\r\u0000\u0392M\u0001\u0000\u0000\u0000\u0393"+
		"\u0394\u0005\u0088\u0000\u0000\u0394\u039e\u0003P(\u0000\u0395\u0396\u0005"+
		"Q\u0000\u0000\u0396\u039b\u0003R)\u0000\u0397\u0398\u0005}\u0000\u0000"+
		"\u0398\u039a\u0003R)\u0000\u0399\u0397\u0001\u0000\u0000\u0000\u039a\u039d"+
		"\u0001\u0000\u0000\u0000\u039b\u0399\u0001\u0000\u0000\u0000\u039b\u039c"+
		"\u0001\u0000\u0000\u0000\u039c\u039f\u0001\u0000\u0000\u0000\u039d\u039b"+
		"\u0001\u0000\u0000\u0000\u039e\u0395\u0001\u0000\u0000\u0000\u039e\u039f"+
		"\u0001\u0000\u0000\u0000\u039f\u03a1\u0001\u0000\u0000\u0000\u03a0\u03a2"+
		"\u0003T*\u0000\u03a1\u03a0\u0001\u0000\u0000\u0000\u03a1\u03a2\u0001\u0000"+
		"\u0000\u0000\u03a2\u03a4\u0001\u0000\u0000\u0000\u03a3\u03a5\u0003V+\u0000"+
		"\u03a4\u03a3\u0001\u0000\u0000\u0000\u03a4\u03a5\u0001\u0000\u0000\u0000"+
		"\u03a5\u03a7\u0001\u0000\u0000\u0000\u03a6\u03a8\u0003X,\u0000\u03a7\u03a6"+
		"\u0001\u0000\u0000\u0000\u03a7\u03a8\u0001\u0000\u0000\u0000\u03a8O\u0001"+
		"\u0000\u0000\u0000\u03a9\u03b3\u0005R\u0000\u0000\u03aa\u03af\u0003b1"+
		"\u0000\u03ab\u03ac\u0005}\u0000\u0000\u03ac\u03ae\u0003b1\u0000\u03ad"+
		"\u03ab\u0001\u0000\u0000\u0000\u03ae\u03b1\u0001\u0000\u0000\u0000\u03af"+
		"\u03ad\u0001\u0000\u0000\u0000\u03af\u03b0\u0001\u0000\u0000\u0000\u03b0"+
		"\u03b3\u0001\u0000\u0000\u0000\u03b1\u03af\u0001\u0000\u0000\u0000\u03b2"+
		"\u03a9\u0001\u0000\u0000\u0000\u03b2\u03aa\u0001\u0000\u0000\u0000\u03b3"+
		"Q\u0001\u0000\u0000\u0000\u03b4\u03b7\u0003`0\u0000\u03b5\u03b6\u0005"+
		"\u0083\u0000\u0000\u03b6\u03b8\u0005\u00ea\u0000\u0000\u03b7\u03b5\u0001"+
		"\u0000\u0000\u0000\u03b7\u03b8\u0001\u0000\u0000\u0000\u03b8S\u0001\u0000"+
		"\u0000\u0000\u03b9\u03ba\u00053\u0000\u0000\u03ba\u03bb\u0003:\u001d\u0000"+
		"\u03bbU\u0001\u0000\u0000\u0000\u03bc\u03bd\u0005S\u0000\u0000\u03bd\u03bf"+
		"\u0003b1\u0000\u03be\u03c0\u0007\b\u0000\u0000\u03bf\u03be\u0001\u0000"+
		"\u0000\u0000\u03bf\u03c0\u0001\u0000\u0000\u0000\u03c0W\u0001\u0000\u0000"+
		"\u0000\u03c1\u03c2\u0005V\u0000\u0000\u03c2\u03c3\u0005\u00ec\u0000\u0000"+
		"\u03c3Y\u0001\u0000\u0000\u0000\u03c4\u03c9\u0003b1\u0000\u03c5\u03c6"+
		"\u0005}\u0000\u0000\u03c6\u03c8\u0003b1\u0000\u03c7\u03c5\u0001\u0000"+
		"\u0000\u0000\u03c8\u03cb\u0001\u0000\u0000\u0000\u03c9\u03c7\u0001\u0000"+
		"\u0000\u0000\u03c9\u03ca\u0001\u0000\u0000\u0000\u03ca[\u0001\u0000\u0000"+
		"\u0000\u03cb\u03c9\u0001\u0000\u0000\u0000\u03cc\u03d7\u0005W\u0000\u0000"+
		"\u03cd\u03ce\u0005X\u0000\u0000\u03ce\u03cf\u0005\u0097\u0000\u0000\u03cf"+
		"\u03d0\u0003b1\u0000\u03d0\u03d1\u0005\u00ab\u0000\u0000\u03d1\u03d4\u0003"+
		"|>\u0000\u03d2\u03d3\u00053\u0000\u0000\u03d3\u03d5\u0003:\u001d\u0000"+
		"\u03d4\u03d2\u0001\u0000\u0000\u0000\u03d4\u03d5\u0001\u0000\u0000\u0000"+
		"\u03d5\u03d7\u0001\u0000\u0000\u0000\u03d6\u03cc\u0001\u0000\u0000\u0000"+
		"\u03d6\u03cd\u0001\u0000\u0000\u0000\u03d7]\u0001\u0000\u0000\u0000\u03d8"+
		"\u03d9\u0005\u00ea\u0000\u0000\u03d9_\u0001\u0000\u0000\u0000\u03da\u03db"+
		"\u0003^/\u0000\u03db\u03dc\u0005\u0092\u0000\u0000\u03dc\u03de\u0001\u0000"+
		"\u0000\u0000\u03dd\u03da\u0001\u0000\u0000\u0000\u03dd\u03de\u0001\u0000"+
		"\u0000\u0000\u03de\u03df\u0001\u0000\u0000\u0000\u03df\u03e0\u0005\u00ea"+
		"\u0000\u0000\u03e0a\u0001\u0000\u0000\u0000\u03e1\u03e2\u0005\u00ea\u0000"+
		"\u0000\u03e2c\u0001\u0000\u0000\u0000\u03e3\u03e4\u0007\t\u0000\u0000"+
		"\u03e4e\u0001\u0000\u0000\u0000\u03e5\u03ed\u0007\n\u0000\u0000\u03e6"+
		"\u03e7\u0005v\u0000\u0000\u03e7\u03ea\u0005\u00ec\u0000\u0000\u03e8\u03e9"+
		"\u0005}\u0000\u0000\u03e9\u03eb\u0005\u00ec\u0000\u0000\u03ea\u03e8\u0001"+
		"\u0000\u0000\u0000\u03ea\u03eb\u0001\u0000\u0000\u0000\u03eb\u03ec\u0001"+
		"\u0000\u0000\u0000\u03ec\u03ee\u0005w\u0000\u0000\u03ed\u03e6\u0001\u0000"+
		"\u0000\u0000\u03ed\u03ee\u0001\u0000\u0000\u0000\u03eeg\u0001\u0000\u0000"+
		"\u0000\u03ef\u03f0\u0005^\u0000\u0000\u03f0\u03f1\u0003j5\u0000\u03f1"+
		"\u03f2\u0005v\u0000\u0000\u03f2\u03f3\u0003b1\u0000\u03f3\u03f4\u0005"+
		"w\u0000\u0000\u03f4i\u0001\u0000\u0000\u0000\u03f5\u03f6\u0007\u000b\u0000"+
		"\u0000\u03f6k\u0001\u0000\u0000\u0000\u03f7\u0411\u0005b\u0000\u0000\u03f8"+
		"\u03f9\u0005Q\u0000\u0000\u03f9\u03fa\u0005v\u0000\u0000\u03fa\u03fb\u0003"+
		"|>\u0000\u03fb\u03fc\u0005w\u0000\u0000\u03fc\u03fd\u0005\u0084\u0000"+
		"\u0000\u03fd\u03fe\u0005v\u0000\u0000\u03fe\u03ff\u0003|>\u0000\u03ff"+
		"\u0400\u0005w\u0000\u0000\u0400\u0412\u0001\u0000\u0000\u0000\u0401\u0402"+
		"\u0005\u00b9\u0000\u0000\u0402\u0403\u0005v\u0000\u0000\u0403\u0408\u0003"+
		"|>\u0000\u0404\u0405\u0005}\u0000\u0000\u0405\u0407\u0003|>\u0000\u0406"+
		"\u0404\u0001\u0000\u0000\u0000\u0407\u040a\u0001\u0000\u0000\u0000\u0408"+
		"\u0406\u0001\u0000\u0000\u0000\u0408\u0409\u0001\u0000\u0000\u0000\u0409"+
		"\u040b\u0001\u0000\u0000\u0000\u040a\u0408\u0001\u0000\u0000\u0000\u040b"+
		"\u040c\u0005w\u0000\u0000\u040c\u0412\u0001\u0000\u0000\u0000\u040d\u040e"+
		"\u0005c\u0000\u0000\u040e\u040f\u0005\u00ec\u0000\u0000\u040f\u0410\u0005"+
		"d\u0000\u0000\u0410\u0412\u0005\u00ec\u0000\u0000\u0411\u03f8\u0001\u0000"+
		"\u0000\u0000\u0411\u0401\u0001\u0000\u0000\u0000\u0411\u040d\u0001\u0000"+
		"\u0000\u0000\u0412m\u0001\u0000\u0000\u0000\u0413\u0415\u0005\u008d\u0000"+
		"\u0000\u0414\u0416\u0005\u00aa\u0000\u0000\u0415\u0414\u0001\u0000\u0000"+
		"\u0000\u0415\u0416\u0001\u0000\u0000\u0000\u0416\u0417\u0001\u0000\u0000"+
		"\u0000\u0417\u0418\u0005e\u0000\u0000\u0418\u0419\u0005\u00ea\u0000\u0000"+
		"\u0419\u041a\u0005\u007f\u0000\u0000\u041a\u041d\u0003`0\u0000\u041b\u041c"+
		"\u0005\u0089\u0000\u0000\u041c\u041e\u0005\u00ea\u0000\u0000\u041d\u041b"+
		"\u0001\u0000\u0000\u0000\u041d\u041e\u0001\u0000\u0000\u0000\u041e\u041f"+
		"\u0001\u0000\u0000\u0000\u041f\u0420\u0005v\u0000\u0000\u0420\u0425\u0003"+
		"p8\u0000\u0421\u0422\u0005}\u0000\u0000\u0422\u0424\u0003p8\u0000\u0423"+
		"\u0421\u0001\u0000\u0000\u0000\u0424\u0427\u0001\u0000\u0000\u0000\u0425"+
		"\u0423\u0001\u0000\u0000\u0000\u0425\u0426\u0001\u0000\u0000\u0000\u0426"+
		"\u0428\u0001\u0000\u0000\u0000\u0427\u0425\u0001\u0000\u0000\u0000\u0428"+
		"\u042b\u0005w\u0000\u0000\u0429\u042a\u00053\u0000\u0000\u042a\u042c\u0003"+
		":\u001d\u0000\u042b\u0429\u0001\u0000\u0000\u0000\u042b\u042c\u0001\u0000"+
		"\u0000\u0000\u042c\u042e\u0001\u0000\u0000\u0000\u042d\u042f\u0003r9\u0000"+
		"\u042e\u042d\u0001\u0000\u0000\u0000\u042e\u042f\u0001\u0000\u0000\u0000"+
		"\u042f\u0430\u0001\u0000\u0000\u0000\u0430\u0431\u0005z\u0000\u0000\u0431"+
		"o\u0001\u0000\u0000\u0000\u0432\u0434\u0003t:\u0000\u0433\u0435\u0005"+
		"\u00ea\u0000\u0000\u0434\u0433\u0001\u0000\u0000\u0000\u0434\u0435\u0001"+
		"\u0000\u0000\u0000\u0435\u0437\u0001\u0000\u0000\u0000\u0436\u0438\u0007"+
		"\b\u0000\u0000\u0437\u0436\u0001\u0000\u0000\u0000\u0437\u0438\u0001\u0000"+
		"\u0000\u0000\u0438q\u0001\u0000\u0000\u0000\u0439\u043a\u0005\u0017\u0000"+
		"\u0000\u043a\u043b\u0005\u00ea\u0000\u0000\u043bs\u0001\u0000\u0000\u0000"+
		"\u043c\u043d\u0005v\u0000\u0000\u043d\u043e\u0003t:\u0000\u043e\u0440"+
		"\u0005w\u0000\u0000\u043f\u0441\u0003x<\u0000\u0440\u043f\u0001\u0000"+
		"\u0000\u0000\u0440\u0441\u0001\u0000\u0000\u0000\u0441\u044d\u0001\u0000"+
		"\u0000\u0000\u0442\u044d\u0003\u0094J\u0000\u0443\u0445\u0003\u001c\u000e"+
		"\u0000\u0444\u0446\u0003x<\u0000\u0445\u0444\u0001\u0000\u0000\u0000\u0445"+
		"\u0446\u0001\u0000\u0000\u0000\u0446\u044d\u0001\u0000\u0000\u0000\u0447"+
		"\u0449\u0003b1\u0000\u0448\u044a\u0003x<\u0000\u0449\u0448\u0001\u0000"+
		"\u0000\u0000\u0449\u044a\u0001\u0000\u0000\u0000\u044a\u044d\u0001\u0000"+
		"\u0000\u0000\u044b\u044d\u0003|>\u0000\u044c\u043c\u0001\u0000\u0000\u0000"+
		"\u044c\u0442\u0001\u0000\u0000\u0000\u044c\u0443\u0001\u0000\u0000\u0000"+
		"\u044c\u0447\u0001\u0000\u0000\u0000\u044c\u044b\u0001\u0000\u0000\u0000"+
		"\u044du\u0001\u0000\u0000\u0000\u044e\u045c\u0005\u00eb\u0000\u0000\u044f"+
		"\u045c\u0003\u0090H\u0000\u0450\u045c\u0003\u0092I\u0000\u0451\u045c\u0005"+
		"1\u0000\u0000\u0452\u045c\u0005\u0094\u0000\u0000\u0453\u0459\u0007\f"+
		"\u0000\u0000\u0454\u0456\u0005v\u0000\u0000\u0455\u0457\u0003z=\u0000"+
		"\u0456\u0455\u0001\u0000\u0000\u0000\u0456\u0457\u0001\u0000\u0000\u0000"+
		"\u0457\u0458\u0001\u0000\u0000\u0000\u0458\u045a\u0005w\u0000\u0000\u0459"+
		"\u0454\u0001\u0000\u0000\u0000\u0459\u045a\u0001\u0000\u0000\u0000\u045a"+
		"\u045c\u0001\u0000\u0000\u0000\u045b\u044e\u0001\u0000\u0000\u0000\u045b"+
		"\u044f\u0001\u0000\u0000\u0000\u045b\u0450\u0001\u0000\u0000\u0000\u045b"+
		"\u0451\u0001\u0000\u0000\u0000\u045b\u0452\u0001\u0000\u0000\u0000\u045b"+
		"\u0453\u0001\u0000\u0000\u0000\u045cw\u0001\u0000\u0000\u0000\u045d\u045e"+
		"\u0005|\u0000\u0000\u045e\u0463\u0003\u001a\r\u0000\u045f\u0460\u0005"+
		"\u009d\u0000\u0000\u0460\u0462\u0005\u009e\u0000\u0000\u0461\u045f\u0001"+
		"\u0000\u0000\u0000\u0462\u0465\u0001\u0000\u0000\u0000\u0463\u0461\u0001"+
		"\u0000\u0000\u0000\u0463\u0464\u0001\u0000\u0000\u0000\u0464y\u0001\u0000"+
		"\u0000\u0000\u0465\u0463\u0001\u0000\u0000\u0000\u0466\u046b\u0003|>\u0000"+
		"\u0467\u0468\u0005}\u0000\u0000\u0468\u046a\u0003|>\u0000\u0469\u0467"+
		"\u0001\u0000\u0000\u0000\u046a\u046d\u0001\u0000\u0000\u0000\u046b\u0469"+
		"\u0001\u0000\u0000\u0000\u046b\u046c\u0001\u0000\u0000\u0000\u046c{\u0001"+
		"\u0000\u0000\u0000\u046d\u046b\u0001\u0000\u0000\u0000\u046e\u0470\u0003"+
		"\u0090H\u0000\u046f\u0471\u0003x<\u0000\u0470\u046f\u0001\u0000\u0000"+
		"\u0000\u0470\u0471\u0001\u0000\u0000\u0000\u0471\u047e\u0001\u0000\u0000"+
		"\u0000\u0472\u0474\u0003v;\u0000\u0473\u0475\u0003x<\u0000\u0474\u0473"+
		"\u0001\u0000\u0000\u0000\u0474\u0475\u0001\u0000\u0000\u0000\u0475\u047e"+
		"\u0001\u0000\u0000\u0000\u0476\u0478\u0003\u0080@\u0000\u0477\u0479\u0003"+
		"x<\u0000\u0478\u0477\u0001\u0000\u0000\u0000\u0478\u0479\u0001\u0000\u0000"+
		"\u0000\u0479\u047e\u0001\u0000\u0000\u0000\u047a\u047e\u0003\u0096K\u0000"+
		"\u047b\u047e\u0005g\u0000\u0000\u047c\u047e\u0005h\u0000\u0000\u047d\u046e"+
		"\u0001\u0000\u0000\u0000\u047d\u0472\u0001\u0000\u0000\u0000\u047d\u0476"+
		"\u0001\u0000\u0000\u0000\u047d\u047a\u0001\u0000\u0000\u0000\u047d\u047b"+
		"\u0001\u0000\u0000\u0000\u047d\u047c\u0001\u0000\u0000\u0000\u047e}\u0001"+
		"\u0000\u0000\u0000\u047f\u0484\u0003t:\u0000\u0480\u0481\u0005}\u0000"+
		"\u0000\u0481\u0483\u0003t:\u0000\u0482\u0480\u0001\u0000\u0000\u0000\u0483"+
		"\u0486\u0001\u0000\u0000\u0000\u0484\u0482\u0001\u0000\u0000\u0000\u0484"+
		"\u0485\u0001\u0000\u0000\u0000\u0485\u007f\u0001\u0000\u0000\u0000\u0486"+
		"\u0484\u0001\u0000\u0000\u0000\u0487\u0488\u0005\u009f\u0000\u0000\u0488"+
		"\u0489\u0005\u009d\u0000\u0000\u0489\u048a\u0003~?\u0000\u048a\u048c\u0005"+
		"\u009e\u0000\u0000\u048b\u048d\u0003x<\u0000\u048c\u048b\u0001\u0000\u0000"+
		"\u0000\u048c\u048d\u0001\u0000\u0000\u0000\u048d\u0081\u0001\u0000\u0000"+
		"\u0000\u048e\u048f\u0005\u008d\u0000\u0000\u048f\u0490\u0005i\u0000\u0000"+
		"\u0490\u0491\u0005\u00ea\u0000\u0000\u0491\u0492\u0005\u007f\u0000\u0000"+
		"\u0492\u0495\u0003`0\u0000\u0493\u0494\u0005\u0087\u0000\u0000\u0494\u0496"+
		"\u0007\r\u0000\u0000\u0495\u0493\u0001\u0000\u0000\u0000\u0495\u0496\u0001"+
		"\u0000\u0000\u0000\u0496\u049c\u0001\u0000\u0000\u0000\u0497\u0498\u0005"+
		"\u0089\u0000\u0000\u0498\u0499\u0005v\u0000\u0000\u0499\u049a\u0003:\u001d"+
		"\u0000\u049a\u049b\u0005w\u0000\u0000\u049b\u049d\u0001\u0000\u0000\u0000"+
		"\u049c\u0497\u0001\u0000\u0000\u0000\u049c\u049d\u0001\u0000\u0000\u0000"+
		"\u049d\u04a3\u0001\u0000\u0000\u0000\u049e\u049f\u0005k\u0000\u0000\u049f"+
		"\u04a0\u0005v\u0000\u0000\u04a0\u04a1\u0003:\u001d\u0000\u04a1\u04a2\u0005"+
		"w\u0000\u0000\u04a2\u04a4\u0001\u0000\u0000\u0000\u04a3\u049e\u0001\u0000"+
		"\u0000\u0000\u04a3\u04a4\u0001\u0000\u0000\u0000\u04a4\u04a5\u0001\u0000"+
		"\u0000\u0000\u04a5\u04a6\u0005z\u0000\u0000\u04a6\u0083\u0001\u0000\u0000"+
		"\u0000\u04a7\u04ab\u0005l\u0000\u0000\u04a8\u04aa\u0003\u0086C\u0000\u04a9"+
		"\u04a8\u0001\u0000\u0000\u0000\u04aa\u04ad\u0001\u0000\u0000\u0000\u04ab"+
		"\u04a9\u0001\u0000\u0000\u0000\u04ab\u04ac\u0001\u0000\u0000\u0000\u04ac"+
		"\u04ae\u0001\u0000\u0000\u0000\u04ad\u04ab\u0001\u0000\u0000\u0000\u04ae"+
		"\u04af\u0005m\u0000\u0000\u04af\u04b0\u0005z\u0000\u0000\u04b0\u0085\u0001"+
		"\u0000\u0000\u0000\u04b1\u04b2\u0003b1\u0000\u04b2\u04b3\u0005\u0092\u0000"+
		"\u0000\u04b3\u04b4\u0003b1\u0000\u04b4\u04b5\u0005\u00ab\u0000\u0000\u04b5"+
		"\u04b6\u0003|>\u0000\u04b6\u04b7\u0005z\u0000\u0000\u04b7\u04c3\u0001"+
		"\u0000\u0000\u0000\u04b8\u04b9\u0005n\u0000\u0000\u04b9\u04ba\u0005\u008b"+
		"\u0000\u0000\u04ba\u04c3\u0005z\u0000\u0000\u04bb\u04bc\u0005\u008b\u0000"+
		"\u0000\u04bc\u04bd\u0005\u0092\u0000\u0000\u04bd\u04be\u0003b1\u0000\u04be"+
		"\u04bf\u0005\u00ab\u0000\u0000\u04bf\u04c0\u0003|>\u0000\u04c0\u04c1\u0005"+
		"z\u0000\u0000\u04c1\u04c3\u0001\u0000\u0000\u0000\u04c2\u04b1\u0001\u0000"+
		"\u0000\u0000\u04c2\u04b8\u0001\u0000\u0000\u0000\u04c2\u04bb\u0001\u0000"+
		"\u0000\u0000\u04c3\u0087\u0001\u0000\u0000\u0000\u04c4\u04c5\u0005\u008d"+
		"\u0000\u0000\u04c5\u04c6\u0005\u0081\u0000\u0000\u04c6\u04c7\u0005\u00ea"+
		"\u0000\u0000\u04c7\u04c8\u0005\u0083\u0000\u0000\u04c8\u04c9\u0005\u007f"+
		"\u0000\u0000\u04c9\u04ca\u0003\u008aE\u0000\u04ca\u04cb\u0005\u0084\u0000"+
		"\u0000\u04cb\u04cc\u0003`0\u0000\u04cc\u04cd\u0005\u0082\u0000\u0000\u04cd"+
		"\u04ce\u0003\u008cF\u0000\u04ce\u04cf\u0005z\u0000\u0000\u04cf\u0089\u0001"+
		"\u0000\u0000\u0000\u04d0\u04d1\u0007\u000e\u0000\u0000\u04d1\u008b\u0001"+
		"\u0000\u0000\u0000\u04d2\u04d6\u0005o\u0000\u0000\u04d3\u04d4\u0005\u0085"+
		"\u0000\u0000\u04d4\u04d6\u0003\u008eG\u0000\u04d5\u04d2\u0001\u0000\u0000"+
		"\u0000\u04d5\u04d3\u0001\u0000\u0000\u0000\u04d6\u008d\u0001\u0000\u0000"+
		"\u0000\u04d7\u04d8\u0005=\u0000\u0000\u04d8\u04d9\u0003`0\u0000\u04d9"+
		"\u04da\u0005v\u0000\u0000\u04da\u04df\u0003b1\u0000\u04db\u04dc\u0005"+
		"}\u0000\u0000\u04dc\u04de\u0003b1\u0000\u04dd\u04db\u0001\u0000\u0000"+
		"\u0000\u04de\u04e1\u0001\u0000\u0000\u0000\u04df\u04dd\u0001\u0000\u0000"+
		"\u0000\u04df\u04e0\u0001\u0000\u0000\u0000\u04e0\u04e2\u0001\u0000\u0000"+
		"\u0000\u04e1\u04df\u0001\u0000\u0000\u0000\u04e2\u04e3\u0005w\u0000\u0000"+
		"\u04e3\u04e4\u0005>\u0000\u0000\u04e4\u04e5\u0005v\u0000\u0000\u04e5\u04ea"+
		"\u0003|>\u0000\u04e6\u04e7\u0005}\u0000\u0000\u04e7\u04e9\u0003|>\u0000"+
		"\u04e8\u04e6\u0001\u0000\u0000\u0000\u04e9\u04ec\u0001\u0000\u0000\u0000"+
		"\u04ea\u04e8\u0001\u0000\u0000\u0000\u04ea\u04eb\u0001\u0000\u0000\u0000"+
		"\u04eb\u04ed\u0001\u0000\u0000\u0000\u04ec\u04ea\u0001\u0000\u0000\u0000"+
		"\u04ed\u04ee\u0005w\u0000\u0000\u04ee\u04f9\u0001\u0000\u0000\u0000\u04ef"+
		"\u04f0\u0005\u00b3\u0000\u0000\u04f0\u04f1\u0003`0\u0000\u04f1\u04f2\u0005"+
		"\u0097\u0000\u0000\u04f2\u04f3\u0003b1\u0000\u04f3\u04f4\u0005\u00ab\u0000"+
		"\u0000\u04f4\u04f5\u0003|>\u0000\u04f5\u04f9\u0001\u0000\u0000\u0000\u04f6"+
		"\u04f7\u0005?\u0000\u0000\u04f7\u04f9\u0003`0\u0000\u04f8\u04d7\u0001"+
		"\u0000\u0000\u0000\u04f8\u04ef\u0001\u0000\u0000\u0000\u04f8\u04f6\u0001"+
		"\u0000\u0000\u0000\u04f9\u008f\u0001\u0000\u0000\u0000\u04fa\u04fe\u0005"+
		"\u00ec\u0000\u0000\u04fb\u04fc\u0005p\u0000\u0000\u04fc\u04fe\u0005\u00ec"+
		"\u0000\u0000\u04fd\u04fa\u0001\u0000\u0000\u0000\u04fd\u04fb\u0001\u0000"+
		"\u0000\u0000\u04fe\u0091\u0001\u0000\u0000\u0000\u04ff\u0500\u0007\u000f"+
		"\u0000\u0000\u0500\u0093\u0001\u0000\u0000\u0000\u0501\u0502\u0005\u009c"+
		"\u0000\u0000\u0502\u0503\u0005v\u0000\u0000\u0503\u0504\u0003t:\u0000"+
		"\u0504\u0505\u0005w\u0000\u0000\u0505\u0095\u0001\u0000\u0000\u0000\u0506"+
		"\u0507\u0005u\u0000\u0000\u0507\u0508\u0005\u00eb\u0000\u0000\u0508\u0097"+
		"\u0001\u0000\u0000\u0000\u0509\u050c\u0005\u00ea\u0000\u0000\u050a\u050b"+
		"\u0005\u0092\u0000\u0000\u050b\u050d\u0005\u00ea\u0000\u0000\u050c\u050a"+
		"\u0001\u0000\u0000\u0000\u050c\u050d\u0001\u0000\u0000\u0000\u050d\u0099"+
		"\u0001\u0000\u0000\u0000\u0082\u009e\u00a6\u00aa\u00b2\u00b7\u00bb\u00c0"+
		"\u00cc\u00d3\u00db\u00e1\u00f1\u00f3\u00f9\u010c\u0119\u011d\u0128\u0134"+
		"\u0139\u0150\u0160\u0171\u0173\u0187\u018a\u0192\u0198\u019f\u01a7\u01b0"+
		"\u01b8\u01be\u01c6\u01d3\u01e2\u01e8\u01ee\u01f3\u0200\u0205\u0212\u0219"+
		"\u0224\u022c\u0231\u023c\u0241\u0245\u024e\u0258\u0261\u026c\u0275\u027a"+
		"\u0283\u0296\u029d\u02ab\u02ad\u02c1\u02c4\u02d0\u02d6\u02e0\u02eb\u02fe"+
		"\u0303\u030b\u0319\u0321\u0323\u0334\u0339\u033d\u0355\u0367\u0369\u0380"+
		"\u0383\u039b\u039e\u03a1\u03a4\u03a7\u03af\u03b2\u03b7\u03bf\u03c9\u03d4"+
		"\u03d6\u03dd\u03ea\u03ed\u0408\u0411\u0415\u041d\u0425\u042b\u042e\u0434"+
		"\u0437\u0440\u0445\u0449\u044c\u0456\u0459\u045b\u0463\u046b\u0470\u0474"+
		"\u0478\u047d\u0484\u048c\u0495\u049c\u04a3\u04ab\u04c2\u04d5\u04df\u04ea"+
		"\u04f8\u04fd\u050c";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}