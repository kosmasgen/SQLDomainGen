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
		T__113=114, T__114=115, T__115=116, T__116=117, T__117=118, T__118=119, 
		T__119=120, T__120=121, T__121=122, T__122=123, T__123=124, T__124=125, 
		T__125=126, T__126=127, T__127=128, T__128=129, T__129=130, T__130=131, 
		T__131=132, T__132=133, T__133=134, T__134=135, T__135=136, T__136=137, 
		T__137=138, T__138=139, T__139=140, T__140=141, T__141=142, T__142=143, 
		T__143=144, T__144=145, T__145=146, T__146=147, T__147=148, LPAREN=149, 
		RPAREN=150, LBRACE=151, RBRACE=152, SEMICOLON=153, COLON=154, DOUBLE_COLON=155, 
		COMMA=156, DOUBLE_QUOTE=157, ON=158, DELETE=159, RULE=160, DO=161, AS=162, 
		TO=163, INSTEAD=164, NOTHING=165, FOR=166, SELECT=167, USING=168, DOLLAR_QUOTE=169, 
		NEW=170, TABLE=171, CREATE=172, RELATIONSHIP=173, DECIMAL=174, INT=175, 
		VARCHAR=176, DOT=177, CONSTRAINT=178, NULL=179, NOT=180, SET=181, REFERENCES=182, 
		SERIAL=183, PRIMARY_KEY=184, CHECK=185, ANY=186, LBRACKET=187, RBRACKET=188, 
		ARRAY=189, DEFAULT=190, NEXTVAL=191, REGCLASS=192, CASCADE=193, SET_NULL=194, 
		SET_DEFAULT=195, NO_ACTION=196, RESTRICT=197, ON_DELETE=198, ON_UPDATE=199, 
		UNIQUE=200, EQUALS=201, TEXT=202, INTEGER=203, BOOLEAN=204, FOREIGN_KEY=205, 
		NOT_NULL=206, TRIGGER=207, BEFORE=208, AFTER=209, UPDATE=210, INSERT=211, 
		EACH=212, ROW=213, EXECUTE=214, FUNCTION=215, IDENTIFIER=216, STRING=217, 
		NUMBER=218, DIGIT=219, WS=220;
	public static final int
		RULE_createTableStatement = 0, RULE_columnDef = 1, RULE_columnTypeModifier = 2, 
		RULE_generatedColumn = 3, RULE_collateClause = 4, RULE_alterTableStatement = 5, 
		RULE_alterAction = 6, RULE_alterColumnAction = 7, RULE_dataType = 8, RULE_onDeleteAction = 9, 
		RULE_onUpdateAction = 10, RULE_onUpdateClause = 11, RULE_constraint = 12, 
		RULE_tableConstraint = 13, RULE_excludeElementList = 14, RULE_excludeElement = 15, 
		RULE_onAction = 16, RULE_action = 17, RULE_triggerStatement = 18, RULE_triggerTiming = 19, 
		RULE_triggerEvent = 20, RULE_dataManipulationStatement = 21, RULE_insertStatement = 22, 
		RULE_updateStatement = 23, RULE_deleteStatement = 24, RULE_condition = 25, 
		RULE_subquery = 26, RULE_comparisonOperator = 27, RULE_arithmeticOperator = 28, 
		RULE_sqlScript = 29, RULE_createSchemaStatement = 30, RULE_createViewStatement = 31, 
		RULE_createFunctionStatement = 32, RULE_parameter = 33, RULE_selectStatement = 34, 
		RULE_selectList = 35, RULE_tableReference = 36, RULE_whereClause = 37, 
		RULE_orderByClause = 38, RULE_limitClause = 39, RULE_columnNameList = 40, 
		RULE_foreignTable = 41, RULE_conflictAction = 42, RULE_schemaName = 43, 
		RULE_tableName = 44, RULE_columnName = 45, RULE_relationshipType = 46, 
		RULE_decimalType = 47, RULE_partitionClause = 48, RULE_partitionStrategy = 49, 
		RULE_createPartitionStatement = 50, RULE_partitionValuesClause = 51, RULE_partitionOfClause = 52, 
		RULE_createIndexStatement = 53, RULE_indexOptionsClause = 54, RULE_expression = 55, 
		RULE_value = 56, RULE_expressionList = 57, RULE_arrayConstructor = 58, 
		RULE_createPolicyStatement = 59, RULE_plpgsqlBlock = 60, RULE_statement = 61, 
		RULE_createRuleStatement = 62, RULE_ruleEvent = 63, RULE_ruleAction = 64, 
		RULE_sqlAction = 65, RULE_defaultValue = 66, RULE_numericLiteral = 67, 
		RULE_booleanLiteral = 68, RULE_foreignKeyAction = 69, RULE_referentialAction = 70, 
		RULE_anyExpression = 71, RULE_intervalLiteral = 72, RULE_typeName = 73;
	private static String[] makeRuleNames() {
		return new String[] {
			"createTableStatement", "columnDef", "columnTypeModifier", "generatedColumn", 
			"collateClause", "alterTableStatement", "alterAction", "alterColumnAction", 
			"dataType", "onDeleteAction", "onUpdateAction", "onUpdateClause", "constraint", 
			"tableConstraint", "excludeElementList", "excludeElement", "onAction", 
			"action", "triggerStatement", "triggerTiming", "triggerEvent", "dataManipulationStatement", 
			"insertStatement", "updateStatement", "deleteStatement", "condition", 
			"subquery", "comparisonOperator", "arithmeticOperator", "sqlScript", 
			"createSchemaStatement", "createViewStatement", "createFunctionStatement", 
			"parameter", "selectStatement", "selectList", "tableReference", "whereClause", 
			"orderByClause", "limitClause", "columnNameList", "foreignTable", "conflictAction", 
			"schemaName", "tableName", "columnName", "relationshipType", "decimalType", 
			"partitionClause", "partitionStrategy", "createPartitionStatement", "partitionValuesClause", 
			"partitionOfClause", "createIndexStatement", "indexOptionsClause", "expression", 
			"value", "expressionList", "arrayConstructor", "createPolicyStatement", 
			"plpgsqlBlock", "statement", "createRuleStatement", "ruleEvent", "ruleAction", 
			"sqlAction", "defaultValue", "numericLiteral", "booleanLiteral", "foreignKeyAction", 
			"referentialAction", "anyExpression", "intervalLiteral", "typeName"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'PARTITION OF'", "'GENERATED'", "'ALWAYS'", "'BY DEFAULT'", "'STORED'", 
			"'COLLATE'", "'ALTER'", "'ADD'", "'COLUMN'", "'DROP'", "'RENAME COLUMN'", 
			"'RENAME TO'", "'SCHEMA'", "'TABLESPACE'", "'ENABLE'", "'REPLICA'", "'DISABLE'", 
			"'CLUSTER'", "'SET WITHOUT CLUSTER'", "'OWNER TO'", "'DROP DEFAULT'", 
			"'SET NOT NULL'", "'DROP NOT NULL'", "'SET STATISTICS'", "'SET STORAGE'", 
			"'PLAIN'", "'EXTERNAL'", "'EXTENDED'", "'MAIN'", "'SET COMPRESSION'", 
			"'RESET'", "'STORAGE'", "'STATISTICS'", "'COMPRESSION'", "'TYPE'", "'FLOAT8'", 
			"'FLOAT4'", "'INT8'", "'SMALLINT'", "'BIGINT'", "'BIGSERIAL'", "'SMALLSERIAL'", 
			"'REAL'", "'DOUBLE PRECISION'", "'NUMERIC'", "'MONEY'", "'CHAR'", "'JSON'", 
			"'JSONB'", "'DATE'", "'TIME'", "'WITHOUT'", "'ZONE'", "'WITH'", "'TIMESTAMP'", 
			"'INTERVAL'", "'UUID'", "'BYTEA'", "'ENUM'", "'CITEXT'", "'TSVECTOR'", 
			"'INET'", "'CIDR'", "'MACADDR'", "'XML'", "'PG_LSN'", "'BIT'", "'VARBIT'", 
			"'CURRENT_TIMESTAMP'", "'ON CONFLICT'", "'EXCLUDE'", "'WHERE'", "'AUTO_INCREMENT'", 
			"'DEFERRABLE'", "'INITIALLY'", "'DEFERRED'", "'IMMEDIATE'", "'FOR EACH ROW'", 
			"'EXECUTE FUNCTION'", "'INSTEAD OF'", "'OR'", "'INSERT INTO'", "'VALUES'", 
			"'DELETE FROM'", "'IN'", "'BETWEEN'", "'AND'", "'IS'", "'IS NOT NULL'", 
			"'LIKE'", "'ILIKE'", "'true'", "'false'", "'<>'", "'!='", "'<'", "'<='", 
			"'>'", "'>='", "'@>'", "'<@'", "'&&'", "'?'", "'?|'", "'?&'", "'+'", 
			"'-'", "'*'", "'/'", "'%'", "'^'", "'CREATE SCHEMA'", "'CREATE VIEW'", 
			"'CREATE FUNCTION'", "'RETURNS'", "'LANGUAGE'", "'FROM'", "'ORDER BY'", 
			"'ASC'", "'DESC'", "'LIMIT'", "'DO NOTHING'", "'DO UPDATE'", "'ONETOONE'", 
			"'MANYTOONE'", "'ONETOMANY'", "'MANYTOMANY'", "'PARTITION BY'", "'RANGE'", 
			"'LIST'", "'HASH'", "'FOR VALUES'", "'MODULUS'", "'REMAINDER'", "'INDEX'", 
			"'TRUE'", "'FALSE'", "'current_user'", "'now'", "'infinity'", "'NaN'", 
			"'POLICY'", "'ALL'", "'WITH CHECK'", "'BEGIN'", "'END'", "'RETURN'", 
			"'INSTEAD NOTHING'", "'('", "')'", "'{'", "'}'", "';'", "':'", "'::'", 
			"','", "'\"'", "'ON'", "'DELETE'", "'RULE'", "'DO'", "'AS'", "'TO'", 
			"'INSTEAD'", "'NOTHING'", "'FOR'", "'SELECT'", "'USING'", "'$$'", "'NEW'", 
			"'TABLE'", "'CREATE'", "'RELATIONSHIP'", "'DECIMAL'", "'INT'", "'VARCHAR'", 
			"'.'", "'CONSTRAINT'", "'NULL'", "'NOT'", "'SET'", "'REFERENCES'", "'SERIAL'", 
			"'PRIMARY KEY'", "'CHECK'", "'ANY'", "'['", "']'", "'ARRAY'", "'DEFAULT'", 
			"'nextval'", "'regclass'", "'CASCADE'", "'SET NULL'", "'SET DEFAULT'", 
			"'NO ACTION'", "'RESTRICT'", "'ON DELETE'", "'ON UPDATE'", "'UNIQUE'", 
			"'='", "'TEXT'", "'INTEGER'", "'BOOLEAN'", "'FOREIGN KEY'", "'NOT NULL'", 
			"'TRIGGER'", "'BEFORE'", "'AFTER'", "'UPDATE'", "'INSERT'", "'EACH'", 
			"'ROW'", "'EXECUTE'", "'FUNCTION'"
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
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, "LPAREN", "RPAREN", "LBRACE", "RBRACE", 
			"SEMICOLON", "COLON", "DOUBLE_COLON", "COMMA", "DOUBLE_QUOTE", "ON", 
			"DELETE", "RULE", "DO", "AS", "TO", "INSTEAD", "NOTHING", "FOR", "SELECT", 
			"USING", "DOLLAR_QUOTE", "NEW", "TABLE", "CREATE", "RELATIONSHIP", "DECIMAL", 
			"INT", "VARCHAR", "DOT", "CONSTRAINT", "NULL", "NOT", "SET", "REFERENCES", 
			"SERIAL", "PRIMARY_KEY", "CHECK", "ANY", "LBRACKET", "RBRACKET", "ARRAY", 
			"DEFAULT", "NEXTVAL", "REGCLASS", "CASCADE", "SET_NULL", "SET_DEFAULT", 
			"NO_ACTION", "RESTRICT", "ON_DELETE", "ON_UPDATE", "UNIQUE", "EQUALS", 
			"TEXT", "INTEGER", "BOOLEAN", "FOREIGN_KEY", "NOT_NULL", "TRIGGER", "BEFORE", 
			"AFTER", "UPDATE", "INSERT", "EACH", "ROW", "EXECUTE", "FUNCTION", "IDENTIFIER", 
			"STRING", "NUMBER", "DIGIT", "WS"
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
		enterRule(_localctx, 0, RULE_createTableStatement);
		int _la;
		try {
			setState(180);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,4,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(148);
				match(CREATE);
				setState(149);
				match(TABLE);
				setState(150);
				tableName();
				setState(151);
				match(LPAREN);
				setState(154);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case T__70:
				case CONSTRAINT:
					{
					setState(152);
					tableConstraint();
					}
					break;
				case FOREIGN_KEY:
				case IDENTIFIER:
					{
					setState(153);
					columnDef();
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(163);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(156);
					match(COMMA);
					setState(159);
					_errHandler.sync(this);
					switch (_input.LA(1)) {
					case T__70:
					case CONSTRAINT:
						{
						setState(157);
						tableConstraint();
						}
						break;
					case FOREIGN_KEY:
					case IDENTIFIER:
						{
						setState(158);
						columnDef();
						}
						break;
					default:
						throw new NoViableAltException(this);
					}
					}
					}
					setState(165);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(166);
				match(RPAREN);
				setState(168);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__127) {
					{
					setState(167);
					partitionClause();
					}
				}

				setState(170);
				match(SEMICOLON);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(172);
				match(CREATE);
				setState(173);
				match(TABLE);
				setState(174);
				tableName();
				setState(175);
				match(T__0);
				setState(176);
				tableName();
				setState(177);
				partitionValuesClause();
				setState(178);
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
		public ColumnTypeModifierContext columnTypeModifier() {
			return getRuleContext(ColumnTypeModifierContext.class,0);
		}
		public List<ConstraintContext> constraint() {
			return getRuleContexts(ConstraintContext.class);
		}
		public ConstraintContext constraint(int i) {
			return getRuleContext(ConstraintContext.class,i);
		}
		public GeneratedColumnContext generatedColumn() {
			return getRuleContext(GeneratedColumnContext.class,0);
		}
		public CollateClauseContext collateClause() {
			return getRuleContext(CollateClauseContext.class,0);
		}
		public OnUpdateClauseContext onUpdateClause() {
			return getRuleContext(OnUpdateClauseContext.class,0);
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
		public TerminalNode RELATIONSHIP() { return getToken(PostgreSQLParser.RELATIONSHIP, 0); }
		public RelationshipTypeContext relationshipType() {
			return getRuleContext(RelationshipTypeContext.class,0);
		}
		public List<OnActionContext> onAction() {
			return getRuleContexts(OnActionContext.class);
		}
		public OnActionContext onAction(int i) {
			return getRuleContext(OnActionContext.class,i);
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
		enterRule(_localctx, 2, RULE_columnDef);
		int _la;
		try {
			setState(219);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case IDENTIFIER:
				enterOuterAlt(_localctx, 1);
				{
				setState(182);
				columnName();
				setState(183);
				dataType();
				setState(185);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==LPAREN) {
					{
					setState(184);
					columnTypeModifier();
					}
				}

				setState(190);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__70 || _la==T__72 || ((((_la - 179)) & ~0x3f) == 0 && ((1L << (_la - 179)) & 203425897L) != 0)) {
					{
					{
					setState(187);
					constraint();
					}
					}
					setState(192);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(195);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case T__1:
					{
					setState(193);
					generatedColumn();
					}
					break;
				case T__5:
					{
					setState(194);
					collateClause();
					}
					break;
				case EOF:
				case T__6:
				case T__81:
				case T__83:
				case T__111:
				case T__112:
				case T__113:
				case RPAREN:
				case SEMICOLON:
				case COMMA:
				case CREATE:
				case ON_UPDATE:
				case UPDATE:
					break;
				default:
					break;
				}
				setState(198);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==ON_UPDATE) {
					{
					setState(197);
					onUpdateClause();
					}
				}

				}
				break;
			case FOREIGN_KEY:
				enterOuterAlt(_localctx, 2);
				{
				setState(200);
				match(FOREIGN_KEY);
				setState(201);
				match(LPAREN);
				setState(202);
				columnNameList();
				setState(203);
				match(RPAREN);
				setState(204);
				match(REFERENCES);
				setState(205);
				tableName();
				setState(206);
				match(LPAREN);
				setState(207);
				columnNameList();
				setState(208);
				match(RPAREN);
				setState(211);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==RELATIONSHIP) {
					{
					setState(209);
					match(RELATIONSHIP);
					setState(210);
					relationshipType();
					}
				}

				setState(216);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__73 || _la==ON_DELETE || _la==ON_UPDATE) {
					{
					{
					setState(213);
					onAction();
					}
					}
					setState(218);
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
		enterRule(_localctx, 4, RULE_columnTypeModifier);
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
		public TerminalNode LPAREN() { return getToken(PostgreSQLParser.LPAREN, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode RPAREN() { return getToken(PostgreSQLParser.RPAREN, 0); }
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
		enterRule(_localctx, 6, RULE_generatedColumn);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(229);
			match(T__1);
			setState(230);
			_la = _input.LA(1);
			if ( !(_la==T__2 || _la==T__3) ) {
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
			match(LPAREN);
			setState(233);
			expression(0);
			setState(234);
			match(RPAREN);
			setState(235);
			match(T__4);
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
		enterRule(_localctx, 8, RULE_collateClause);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(237);
			match(T__5);
			setState(238);
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
		enterRule(_localctx, 10, RULE_alterTableStatement);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(240);
			match(T__6);
			setState(241);
			match(TABLE);
			setState(242);
			tableName();
			setState(243);
			alterAction();
			setState(248);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(244);
				match(COMMA);
				setState(245);
				alterAction();
				}
				}
				setState(250);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(252);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==SEMICOLON) {
				{
				setState(251);
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
		enterRule(_localctx, 12, RULE_alterAction);
		int _la;
		try {
			setState(319);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,19,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(254);
				match(T__7);
				setState(255);
				match(T__8);
				setState(256);
				columnDef();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(257);
				match(T__9);
				setState(258);
				match(T__8);
				setState(259);
				columnName();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(260);
				match(T__7);
				setState(263);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==CONSTRAINT) {
					{
					setState(261);
					match(CONSTRAINT);
					setState(262);
					match(IDENTIFIER);
					}
				}

				setState(265);
				match(FOREIGN_KEY);
				setState(266);
				match(LPAREN);
				setState(267);
				columnNameList();
				setState(268);
				match(RPAREN);
				setState(269);
				match(REFERENCES);
				setState(270);
				tableName();
				setState(275);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==LPAREN) {
					{
					setState(271);
					match(LPAREN);
					setState(272);
					columnNameList();
					setState(273);
					match(RPAREN);
					}
				}

				setState(280);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__73 || _la==ON_DELETE || _la==ON_UPDATE) {
					{
					{
					setState(277);
					onAction();
					}
					}
					setState(282);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(283);
				match(T__9);
				setState(284);
				match(CONSTRAINT);
				setState(285);
				match(IDENTIFIER);
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(286);
				match(T__6);
				setState(287);
				match(T__8);
				setState(288);
				columnName();
				setState(289);
				alterColumnAction();
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(291);
				match(T__10);
				setState(292);
				columnName();
				setState(293);
				match(TO);
				setState(294);
				columnName();
				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(296);
				match(T__11);
				setState(297);
				tableName();
				}
				break;
			case 8:
				enterOuterAlt(_localctx, 8);
				{
				setState(298);
				match(SET);
				setState(303);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case T__12:
					{
					setState(299);
					match(T__12);
					setState(300);
					schemaName();
					}
					break;
				case T__13:
					{
					setState(301);
					match(T__13);
					setState(302);
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
				setState(305);
				match(T__14);
				setState(306);
				match(T__15);
				setState(307);
				match(TRIGGER);
				setState(308);
				match(IDENTIFIER);
				}
				break;
			case 10:
				enterOuterAlt(_localctx, 10);
				{
				setState(309);
				match(T__16);
				setState(310);
				match(T__15);
				setState(311);
				match(TRIGGER);
				setState(312);
				match(IDENTIFIER);
				}
				break;
			case 11:
				enterOuterAlt(_localctx, 11);
				{
				setState(313);
				match(T__17);
				setState(314);
				match(ON);
				setState(315);
				match(IDENTIFIER);
				}
				break;
			case 12:
				enterOuterAlt(_localctx, 12);
				{
				setState(316);
				match(T__18);
				}
				break;
			case 13:
				enterOuterAlt(_localctx, 13);
				{
				setState(317);
				match(T__19);
				setState(318);
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
		enterRule(_localctx, 14, RULE_alterColumnAction);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(336); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				setState(336);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case SET_DEFAULT:
					{
					setState(321);
					match(SET_DEFAULT);
					setState(322);
					value();
					}
					break;
				case T__20:
					{
					setState(323);
					match(T__20);
					}
					break;
				case T__21:
					{
					setState(324);
					match(T__21);
					}
					break;
				case T__22:
					{
					setState(325);
					match(T__22);
					}
					break;
				case T__23:
					{
					setState(326);
					match(T__23);
					setState(327);
					match(NUMBER);
					}
					break;
				case T__24:
					{
					setState(328);
					match(T__24);
					setState(329);
					_la = _input.LA(1);
					if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 1006632960L) != 0)) ) {
					_errHandler.recoverInline(this);
					}
					else {
						if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
						_errHandler.reportMatch(this);
						consume();
					}
					}
					break;
				case T__29:
					{
					setState(330);
					match(T__29);
					setState(331);
					match(IDENTIFIER);
					}
					break;
				case T__30:
					{
					setState(332);
					match(T__30);
					setState(333);
					_la = _input.LA(1);
					if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 30064771072L) != 0)) ) {
					_errHandler.recoverInline(this);
					}
					else {
						if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
						_errHandler.reportMatch(this);
						consume();
					}
					}
					break;
				case T__34:
					{
					setState(334);
					match(T__34);
					setState(335);
					dataType();
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				setState(338); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & 37645975552L) != 0) || _la==SET_DEFAULT );
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
		public TerminalNode INT() { return getToken(PostgreSQLParser.INT, 0); }
		public TerminalNode SERIAL() { return getToken(PostgreSQLParser.SERIAL, 0); }
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
		public TerminalNode VARCHAR() { return getToken(PostgreSQLParser.VARCHAR, 0); }
		public TerminalNode TEXT() { return getToken(PostgreSQLParser.TEXT, 0); }
		public TerminalNode BOOLEAN() { return getToken(PostgreSQLParser.BOOLEAN, 0); }
		public TerminalNode ARRAY() { return getToken(PostgreSQLParser.ARRAY, 0); }
		public List<TerminalNode> STRING() { return getTokens(PostgreSQLParser.STRING); }
		public TerminalNode STRING(int i) {
			return getToken(PostgreSQLParser.STRING, i);
		}
		public TerminalNode TRIGGER() { return getToken(PostgreSQLParser.TRIGGER, 0); }
		public TerminalNode IDENTIFIER() { return getToken(PostgreSQLParser.IDENTIFIER, 0); }
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
		enterRule(_localctx, 16, RULE_dataType);
		int _la;
		try {
			setState(435);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,31,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(340);
				match(INTEGER);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(341);
				match(T__35);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(342);
				match(T__36);
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(343);
				match(T__37);
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(344);
				match(INT);
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(345);
				match(T__38);
				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(346);
				match(T__39);
				}
				break;
			case 8:
				enterOuterAlt(_localctx, 8);
				{
				setState(347);
				match(T__40);
				}
				break;
			case 9:
				enterOuterAlt(_localctx, 9);
				{
				setState(348);
				match(SERIAL);
				}
				break;
			case 10:
				enterOuterAlt(_localctx, 10);
				{
				setState(349);
				match(T__41);
				}
				break;
			case 11:
				enterOuterAlt(_localctx, 11);
				{
				setState(350);
				match(T__42);
				}
				break;
			case 12:
				enterOuterAlt(_localctx, 12);
				{
				setState(351);
				match(T__43);
				}
				break;
			case 13:
				enterOuterAlt(_localctx, 13);
				{
				setState(352);
				match(T__44);
				setState(360);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,23,_ctx) ) {
				case 1:
					{
					setState(353);
					match(LPAREN);
					setState(354);
					match(NUMBER);
					setState(357);
					_errHandler.sync(this);
					_la = _input.LA(1);
					if (_la==COMMA) {
						{
						setState(355);
						match(COMMA);
						setState(356);
						match(NUMBER);
						}
					}

					setState(359);
					match(RPAREN);
					}
					break;
				}
				}
				break;
			case 14:
				enterOuterAlt(_localctx, 14);
				{
				setState(362);
				decimalType();
				}
				break;
			case 15:
				enterOuterAlt(_localctx, 15);
				{
				setState(363);
				match(T__45);
				}
				break;
			case 16:
				enterOuterAlt(_localctx, 16);
				{
				setState(364);
				match(T__46);
				setState(368);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,24,_ctx) ) {
				case 1:
					{
					setState(365);
					match(LPAREN);
					setState(366);
					match(NUMBER);
					setState(367);
					match(RPAREN);
					}
					break;
				}
				}
				break;
			case 17:
				enterOuterAlt(_localctx, 17);
				{
				setState(370);
				match(VARCHAR);
				setState(374);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,25,_ctx) ) {
				case 1:
					{
					setState(371);
					match(LPAREN);
					setState(372);
					match(NUMBER);
					setState(373);
					match(RPAREN);
					}
					break;
				}
				}
				break;
			case 18:
				enterOuterAlt(_localctx, 18);
				{
				setState(376);
				match(TEXT);
				}
				break;
			case 19:
				enterOuterAlt(_localctx, 19);
				{
				setState(377);
				match(BOOLEAN);
				}
				break;
			case 20:
				enterOuterAlt(_localctx, 20);
				{
				setState(378);
				match(T__47);
				}
				break;
			case 21:
				enterOuterAlt(_localctx, 21);
				{
				setState(379);
				match(T__48);
				}
				break;
			case 22:
				enterOuterAlt(_localctx, 22);
				{
				setState(380);
				match(T__49);
				}
				break;
			case 23:
				enterOuterAlt(_localctx, 23);
				{
				setState(381);
				match(T__50);
				setState(388);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,26,_ctx) ) {
				case 1:
					{
					setState(382);
					match(T__51);
					setState(383);
					match(T__50);
					setState(384);
					match(T__52);
					}
					break;
				case 2:
					{
					setState(385);
					match(T__53);
					setState(386);
					match(T__50);
					setState(387);
					match(T__52);
					}
					break;
				}
				}
				break;
			case 24:
				enterOuterAlt(_localctx, 24);
				{
				setState(390);
				match(T__54);
				setState(397);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,27,_ctx) ) {
				case 1:
					{
					setState(391);
					match(T__51);
					setState(392);
					match(T__50);
					setState(393);
					match(T__52);
					}
					break;
				case 2:
					{
					setState(394);
					match(T__53);
					setState(395);
					match(T__50);
					setState(396);
					match(T__52);
					}
					break;
				}
				}
				break;
			case 25:
				enterOuterAlt(_localctx, 25);
				{
				setState(399);
				match(T__55);
				}
				break;
			case 26:
				enterOuterAlt(_localctx, 26);
				{
				setState(400);
				match(T__56);
				}
				break;
			case 27:
				enterOuterAlt(_localctx, 27);
				{
				setState(401);
				match(ARRAY);
				}
				break;
			case 28:
				enterOuterAlt(_localctx, 28);
				{
				setState(402);
				match(T__57);
				}
				break;
			case 29:
				enterOuterAlt(_localctx, 29);
				{
				setState(403);
				match(T__58);
				setState(404);
				match(LPAREN);
				setState(405);
				match(STRING);
				setState(410);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(406);
					match(COMMA);
					setState(407);
					match(STRING);
					}
					}
					setState(412);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(413);
				match(RPAREN);
				}
				break;
			case 30:
				enterOuterAlt(_localctx, 30);
				{
				setState(414);
				match(T__59);
				}
				break;
			case 31:
				enterOuterAlt(_localctx, 31);
				{
				setState(415);
				match(T__60);
				}
				break;
			case 32:
				enterOuterAlt(_localctx, 32);
				{
				setState(416);
				match(T__61);
				}
				break;
			case 33:
				enterOuterAlt(_localctx, 33);
				{
				setState(417);
				match(T__62);
				}
				break;
			case 34:
				enterOuterAlt(_localctx, 34);
				{
				setState(418);
				match(T__63);
				}
				break;
			case 35:
				enterOuterAlt(_localctx, 35);
				{
				setState(419);
				match(T__64);
				}
				break;
			case 36:
				enterOuterAlt(_localctx, 36);
				{
				setState(420);
				match(T__65);
				}
				break;
			case 37:
				enterOuterAlt(_localctx, 37);
				{
				setState(421);
				match(T__66);
				setState(425);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,29,_ctx) ) {
				case 1:
					{
					setState(422);
					match(LPAREN);
					setState(423);
					match(NUMBER);
					setState(424);
					match(RPAREN);
					}
					break;
				}
				}
				break;
			case 38:
				enterOuterAlt(_localctx, 38);
				{
				setState(427);
				match(T__67);
				setState(431);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,30,_ctx) ) {
				case 1:
					{
					setState(428);
					match(LPAREN);
					setState(429);
					match(NUMBER);
					setState(430);
					match(RPAREN);
					}
					break;
				}
				}
				break;
			case 39:
				enterOuterAlt(_localctx, 39);
				{
				setState(433);
				match(TRIGGER);
				}
				break;
			case 40:
				enterOuterAlt(_localctx, 40);
				{
				setState(434);
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
	public static class OnDeleteActionContext extends ParserRuleContext {
		public TerminalNode CASCADE() { return getToken(PostgreSQLParser.CASCADE, 0); }
		public TerminalNode SET_NULL() { return getToken(PostgreSQLParser.SET_NULL, 0); }
		public TerminalNode SET_DEFAULT() { return getToken(PostgreSQLParser.SET_DEFAULT, 0); }
		public TerminalNode NO_ACTION() { return getToken(PostgreSQLParser.NO_ACTION, 0); }
		public OnDeleteActionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_onDeleteAction; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PostgreSQLListener ) ((PostgreSQLListener)listener).enterOnDeleteAction(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PostgreSQLListener ) ((PostgreSQLListener)listener).exitOnDeleteAction(this);
		}
	}

	public final OnDeleteActionContext onDeleteAction() throws RecognitionException {
		OnDeleteActionContext _localctx = new OnDeleteActionContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_onDeleteAction);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(437);
			_la = _input.LA(1);
			if ( !(((((_la - 193)) & ~0x3f) == 0 && ((1L << (_la - 193)) & 15L) != 0)) ) {
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
	public static class OnUpdateActionContext extends ParserRuleContext {
		public TerminalNode CASCADE() { return getToken(PostgreSQLParser.CASCADE, 0); }
		public TerminalNode SET_NULL() { return getToken(PostgreSQLParser.SET_NULL, 0); }
		public TerminalNode SET_DEFAULT() { return getToken(PostgreSQLParser.SET_DEFAULT, 0); }
		public TerminalNode NO_ACTION() { return getToken(PostgreSQLParser.NO_ACTION, 0); }
		public OnUpdateActionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_onUpdateAction; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PostgreSQLListener ) ((PostgreSQLListener)listener).enterOnUpdateAction(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PostgreSQLListener ) ((PostgreSQLListener)listener).exitOnUpdateAction(this);
		}
	}

	public final OnUpdateActionContext onUpdateAction() throws RecognitionException {
		OnUpdateActionContext _localctx = new OnUpdateActionContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_onUpdateAction);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(439);
			_la = _input.LA(1);
			if ( !(((((_la - 193)) & ~0x3f) == 0 && ((1L << (_la - 193)) & 15L) != 0)) ) {
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
		enterRule(_localctx, 22, RULE_onUpdateClause);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(441);
			match(ON_UPDATE);
			setState(442);
			match(T__68);
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
		public TerminalNode NOT_NULL() { return getToken(PostgreSQLParser.NOT_NULL, 0); }
		public TerminalNode NULL() { return getToken(PostgreSQLParser.NULL, 0); }
		public TerminalNode UNIQUE() { return getToken(PostgreSQLParser.UNIQUE, 0); }
		public ConflictActionContext conflictAction() {
			return getRuleContext(ConflictActionContext.class,0);
		}
		public TerminalNode DEFAULT() { return getToken(PostgreSQLParser.DEFAULT, 0); }
		public ValueContext value() {
			return getRuleContext(ValueContext.class,0);
		}
		public TerminalNode CHECK() { return getToken(PostgreSQLParser.CHECK, 0); }
		public TerminalNode LPAREN() { return getToken(PostgreSQLParser.LPAREN, 0); }
		public ConditionContext condition() {
			return getRuleContext(ConditionContext.class,0);
		}
		public TerminalNode RPAREN() { return getToken(PostgreSQLParser.RPAREN, 0); }
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
		public OnActionContext onAction() {
			return getRuleContext(OnActionContext.class,0);
		}
		public TerminalNode FOREIGN_KEY() { return getToken(PostgreSQLParser.FOREIGN_KEY, 0); }
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
		public TerminalNode USING() { return getToken(PostgreSQLParser.USING, 0); }
		public TerminalNode IDENTIFIER() { return getToken(PostgreSQLParser.IDENTIFIER, 0); }
		public ExcludeElementListContext excludeElementList() {
			return getRuleContext(ExcludeElementListContext.class,0);
		}
		public TerminalNode PRIMARY_KEY() { return getToken(PostgreSQLParser.PRIMARY_KEY, 0); }
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
		enterRule(_localctx, 24, RULE_constraint);
		int _la;
		try {
			setState(497);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case NOT_NULL:
				enterOuterAlt(_localctx, 1);
				{
				setState(444);
				match(NOT_NULL);
				}
				break;
			case NULL:
				enterOuterAlt(_localctx, 2);
				{
				setState(445);
				match(NULL);
				}
				break;
			case UNIQUE:
				enterOuterAlt(_localctx, 3);
				{
				setState(446);
				match(UNIQUE);
				setState(449);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__69) {
					{
					setState(447);
					match(T__69);
					setState(448);
					conflictAction();
					}
				}

				}
				break;
			case DEFAULT:
				enterOuterAlt(_localctx, 4);
				{
				setState(451);
				match(DEFAULT);
				setState(452);
				value();
				}
				break;
			case CHECK:
				enterOuterAlt(_localctx, 5);
				{
				setState(453);
				match(CHECK);
				setState(454);
				match(LPAREN);
				setState(455);
				condition(0);
				setState(456);
				match(RPAREN);
				}
				break;
			case REFERENCES:
				enterOuterAlt(_localctx, 6);
				{
				setState(458);
				match(REFERENCES);
				setState(462);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,33,_ctx) ) {
				case 1:
					{
					setState(459);
					schemaName();
					setState(460);
					match(DOT);
					}
					break;
				}
				setState(464);
				tableName();
				setState(465);
				match(LPAREN);
				setState(466);
				columnName();
				setState(467);
				match(RPAREN);
				setState(469);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,34,_ctx) ) {
				case 1:
					{
					setState(468);
					onAction();
					}
					break;
				}
				}
				break;
			case FOREIGN_KEY:
				enterOuterAlt(_localctx, 7);
				{
				setState(471);
				match(FOREIGN_KEY);
				setState(472);
				columnNameList();
				setState(473);
				match(REFERENCES);
				setState(477);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,35,_ctx) ) {
				case 1:
					{
					setState(474);
					schemaName();
					setState(475);
					match(DOT);
					}
					break;
				}
				setState(479);
				tableName();
				setState(480);
				columnNameList();
				setState(483);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==RELATIONSHIP) {
					{
					setState(481);
					match(RELATIONSHIP);
					setState(482);
					relationshipType();
					}
				}

				}
				break;
			case T__70:
				enterOuterAlt(_localctx, 8);
				{
				setState(485);
				match(T__70);
				setState(486);
				match(USING);
				setState(487);
				match(IDENTIFIER);
				setState(488);
				match(LPAREN);
				setState(489);
				excludeElementList();
				setState(490);
				match(RPAREN);
				setState(493);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__71) {
					{
					setState(491);
					match(T__71);
					setState(492);
					condition(0);
					}
				}

				}
				break;
			case T__72:
				enterOuterAlt(_localctx, 9);
				{
				setState(495);
				match(T__72);
				}
				break;
			case PRIMARY_KEY:
				enterOuterAlt(_localctx, 10);
				{
				setState(496);
				match(PRIMARY_KEY);
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
		public TerminalNode LPAREN() { return getToken(PostgreSQLParser.LPAREN, 0); }
		public List<ColumnNameListContext> columnNameList() {
			return getRuleContexts(ColumnNameListContext.class);
		}
		public ColumnNameListContext columnNameList(int i) {
			return getRuleContext(ColumnNameListContext.class,i);
		}
		public TerminalNode RPAREN() { return getToken(PostgreSQLParser.RPAREN, 0); }
		public TerminalNode IDENTIFIER() { return getToken(PostgreSQLParser.IDENTIFIER, 0); }
		public List<TerminalNode> COMMA() { return getTokens(PostgreSQLParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(PostgreSQLParser.COMMA, i);
		}
		public List<ColumnNameContext> columnName() {
			return getRuleContexts(ColumnNameContext.class);
		}
		public ColumnNameContext columnName(int i) {
			return getRuleContext(ColumnNameContext.class,i);
		}
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
		enterRule(_localctx, 26, RULE_tableConstraint);
		int _la;
		try {
			setState(557);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,46,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(499);
				match(CONSTRAINT);
				setState(501);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==IDENTIFIER) {
					{
					setState(500);
					match(IDENTIFIER);
					}
				}

				setState(503);
				match(PRIMARY_KEY);
				setState(504);
				match(LPAREN);
				setState(505);
				columnNameList();
				setState(510);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(506);
					match(COMMA);
					setState(507);
					columnName();
					}
					}
					setState(512);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(513);
				match(RPAREN);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(515);
				match(CONSTRAINT);
				setState(517);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==IDENTIFIER) {
					{
					setState(516);
					match(IDENTIFIER);
					}
				}

				setState(519);
				match(FOREIGN_KEY);
				setState(520);
				columnNameList();
				setState(521);
				match(REFERENCES);
				setState(525);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,42,_ctx) ) {
				case 1:
					{
					setState(522);
					schemaName();
					setState(523);
					match(DOT);
					}
					break;
				}
				setState(527);
				tableName();
				setState(528);
				columnNameList();
				setState(532);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__73 || _la==ON_DELETE || _la==ON_UPDATE) {
					{
					{
					setState(529);
					onAction();
					}
					}
					setState(534);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(535);
				match(CONSTRAINT);
				setState(537);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==IDENTIFIER) {
					{
					setState(536);
					match(IDENTIFIER);
					}
				}

				setState(539);
				match(UNIQUE);
				setState(540);
				columnNameList();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(541);
				match(CONSTRAINT);
				setState(543);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==IDENTIFIER) {
					{
					setState(542);
					match(IDENTIFIER);
					}
				}

				setState(545);
				match(CHECK);
				setState(546);
				match(LPAREN);
				setState(547);
				condition(0);
				setState(548);
				match(RPAREN);
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(550);
				match(T__70);
				setState(551);
				match(USING);
				setState(552);
				match(IDENTIFIER);
				setState(553);
				match(LPAREN);
				setState(554);
				excludeElementList();
				setState(555);
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
		enterRule(_localctx, 28, RULE_excludeElementList);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(559);
			excludeElement();
			setState(564);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(560);
				match(COMMA);
				setState(561);
				excludeElement();
				}
				}
				setState(566);
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
		enterRule(_localctx, 30, RULE_excludeElement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(567);
			columnName();
			setState(568);
			match(T__53);
			setState(569);
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
		enterRule(_localctx, 32, RULE_onAction);
		int _la;
		try {
			setState(580);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case ON_DELETE:
				enterOuterAlt(_localctx, 1);
				{
				setState(571);
				match(ON_DELETE);
				setState(572);
				action();
				}
				break;
			case ON_UPDATE:
				enterOuterAlt(_localctx, 2);
				{
				setState(573);
				match(ON_UPDATE);
				setState(574);
				action();
				}
				break;
			case T__73:
				enterOuterAlt(_localctx, 3);
				{
				setState(575);
				match(T__73);
				setState(578);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__74) {
					{
					setState(576);
					match(T__74);
					setState(577);
					_la = _input.LA(1);
					if ( !(_la==T__75 || _la==T__76) ) {
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
		enterRule(_localctx, 34, RULE_action);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(582);
			_la = _input.LA(1);
			if ( !(((((_la - 193)) & ~0x3f) == 0 && ((1L << (_la - 193)) & 31L) != 0)) ) {
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
		enterRule(_localctx, 36, RULE_triggerStatement);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(584);
			match(CREATE);
			setState(585);
			match(TRIGGER);
			setState(586);
			match(IDENTIFIER);
			setState(587);
			triggerTiming();
			setState(588);
			triggerEvent();
			setState(589);
			match(ON);
			setState(590);
			tableName();
			setState(591);
			match(T__77);
			setState(592);
			match(T__78);
			setState(593);
			match(IDENTIFIER);
			setState(594);
			match(LPAREN);
			setState(603);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__55 || _la==T__68 || ((((_la - 136)) & ~0x3f) == 0 && ((1L << (_la - 136)) & 9015995347763263L) != 0) || _la==STRING || _la==NUMBER) {
				{
				setState(595);
				value();
				setState(600);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(596);
					match(COMMA);
					setState(597);
					value();
					}
					}
					setState(602);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
			}

			setState(605);
			match(RPAREN);
			setState(606);
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
		enterRule(_localctx, 38, RULE_triggerTiming);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(608);
			_la = _input.LA(1);
			if ( !(_la==T__79 || _la==BEFORE || _la==AFTER) ) {
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
		enterRule(_localctx, 40, RULE_triggerEvent);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(610);
			_la = _input.LA(1);
			if ( !(((((_la - 159)) & ~0x3f) == 0 && ((1L << (_la - 159)) & 6755399441055745L) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			setState(615);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__80) {
				{
				{
				setState(611);
				match(T__80);
				setState(612);
				_la = _input.LA(1);
				if ( !(((((_la - 159)) & ~0x3f) == 0 && ((1L << (_la - 159)) & 6755399441055745L) != 0)) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				}
				}
				setState(617);
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
		enterRule(_localctx, 42, RULE_dataManipulationStatement);
		try {
			setState(621);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__81:
				enterOuterAlt(_localctx, 1);
				{
				setState(618);
				insertStatement();
				}
				break;
			case UPDATE:
				enterOuterAlt(_localctx, 2);
				{
				setState(619);
				updateStatement();
				}
				break;
			case T__83:
				enterOuterAlt(_localctx, 3);
				{
				setState(620);
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
		enterRule(_localctx, 44, RULE_insertStatement);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(623);
			match(T__81);
			setState(624);
			tableName();
			setState(625);
			match(LPAREN);
			setState(626);
			columnName();
			setState(631);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(627);
				match(COMMA);
				setState(628);
				columnName();
				}
				}
				setState(633);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(634);
			match(RPAREN);
			setState(635);
			match(T__82);
			setState(636);
			match(LPAREN);
			setState(637);
			value();
			setState(642);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(638);
				match(COMMA);
				setState(639);
				value();
				}
				}
				setState(644);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(645);
			match(RPAREN);
			setState(646);
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
		enterRule(_localctx, 46, RULE_updateStatement);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(648);
			match(UPDATE);
			setState(649);
			tableName();
			setState(650);
			match(SET);
			setState(651);
			columnName();
			setState(652);
			match(EQUALS);
			setState(653);
			value();
			setState(661);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(654);
				match(COMMA);
				setState(655);
				columnName();
				setState(656);
				match(EQUALS);
				setState(657);
				value();
				}
				}
				setState(663);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(666);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__71) {
				{
				setState(664);
				match(T__71);
				setState(665);
				condition(0);
				}
			}

			setState(668);
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
		enterRule(_localctx, 48, RULE_deleteStatement);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(670);
			match(T__83);
			setState(671);
			tableName();
			setState(674);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__71) {
				{
				setState(672);
				match(T__71);
				setState(673);
				condition(0);
				}
			}

			setState(676);
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
		public ColumnNameContext columnName() {
			return getRuleContext(ColumnNameContext.class,0);
		}
		public ComparisonOperatorContext comparisonOperator() {
			return getRuleContext(ComparisonOperatorContext.class,0);
		}
		public List<ValueContext> value() {
			return getRuleContexts(ValueContext.class);
		}
		public ValueContext value(int i) {
			return getRuleContext(ValueContext.class,i);
		}
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
		public TerminalNode NULL() { return getToken(PostgreSQLParser.NULL, 0); }
		public TerminalNode STRING() { return getToken(PostgreSQLParser.STRING, 0); }
		public TerminalNode NOT() { return getToken(PostgreSQLParser.NOT, 0); }
		public List<ConditionContext> condition() {
			return getRuleContexts(ConditionContext.class);
		}
		public ConditionContext condition(int i) {
			return getRuleContext(ConditionContext.class,i);
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
		return condition(0);
	}

	private ConditionContext condition(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		ConditionContext _localctx = new ConditionContext(_ctx, _parentState);
		ConditionContext _prevctx = _localctx;
		int _startState = 50;
		enterRecursionRule(_localctx, 50, RULE_condition, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(727);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,62,_ctx) ) {
			case 1:
				{
				setState(679);
				columnName();
				setState(680);
				comparisonOperator();
				setState(681);
				value();
				}
				break;
			case 2:
				{
				setState(683);
				columnName();
				setState(684);
				match(T__84);
				setState(685);
				match(LPAREN);
				setState(688);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case T__55:
				case T__68:
				case T__135:
				case T__136:
				case T__137:
				case T__138:
				case T__139:
				case T__140:
				case NULL:
				case ARRAY:
				case STRING:
				case NUMBER:
					{
					setState(686);
					value();
					}
					break;
				case LPAREN:
					{
					setState(687);
					subquery();
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(697);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(690);
					match(COMMA);
					setState(693);
					_errHandler.sync(this);
					switch (_input.LA(1)) {
					case T__55:
					case T__68:
					case T__135:
					case T__136:
					case T__137:
					case T__138:
					case T__139:
					case T__140:
					case NULL:
					case ARRAY:
					case STRING:
					case NUMBER:
						{
						setState(691);
						value();
						}
						break;
					case LPAREN:
						{
						setState(692);
						subquery();
						}
						break;
					default:
						throw new NoViableAltException(this);
					}
					}
					}
					setState(699);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(700);
				match(RPAREN);
				}
				break;
			case 3:
				{
				setState(702);
				columnName();
				setState(703);
				match(T__85);
				setState(704);
				value();
				setState(705);
				match(T__86);
				setState(706);
				value();
				}
				break;
			case 4:
				{
				setState(708);
				columnName();
				setState(709);
				match(T__87);
				setState(710);
				match(NULL);
				}
				break;
			case 5:
				{
				setState(712);
				columnName();
				setState(713);
				match(T__88);
				}
				break;
			case 6:
				{
				setState(715);
				columnName();
				setState(716);
				match(T__89);
				setState(717);
				match(STRING);
				}
				break;
			case 7:
				{
				setState(719);
				columnName();
				setState(720);
				match(T__90);
				setState(721);
				match(STRING);
				}
				break;
			case 8:
				{
				setState(723);
				match(NOT);
				setState(724);
				condition(5);
				}
				break;
			case 9:
				{
				setState(725);
				match(T__91);
				}
				break;
			case 10:
				{
				setState(726);
				match(T__92);
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(737);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,64,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(735);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,63,_ctx) ) {
					case 1:
						{
						_localctx = new ConditionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_condition);
						setState(729);
						if (!(precpred(_ctx, 4))) throw new FailedPredicateException(this, "precpred(_ctx, 4)");
						setState(730);
						match(T__86);
						setState(731);
						condition(5);
						}
						break;
					case 2:
						{
						_localctx = new ConditionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_condition);
						setState(732);
						if (!(precpred(_ctx, 3))) throw new FailedPredicateException(this, "precpred(_ctx, 3)");
						setState(733);
						match(T__80);
						setState(734);
						condition(4);
						}
						break;
					}
					} 
				}
				setState(739);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,64,_ctx);
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
		enterRule(_localctx, 52, RULE_subquery);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(740);
			match(LPAREN);
			setState(741);
			sqlScript();
			setState(742);
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
		enterRule(_localctx, 54, RULE_comparisonOperator);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(744);
			_la = _input.LA(1);
			if ( !(((((_la - 94)) & ~0x3f) == 0 && ((1L << (_la - 94)) & 4095L) != 0) || _la==EQUALS) ) {
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
	public static class ArithmeticOperatorContext extends ParserRuleContext {
		public ArithmeticOperatorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_arithmeticOperator; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PostgreSQLListener ) ((PostgreSQLListener)listener).enterArithmeticOperator(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PostgreSQLListener ) ((PostgreSQLListener)listener).exitArithmeticOperator(this);
		}
	}

	public final ArithmeticOperatorContext arithmeticOperator() throws RecognitionException {
		ArithmeticOperatorContext _localctx = new ArithmeticOperatorContext(_ctx, getState());
		enterRule(_localctx, 56, RULE_arithmeticOperator);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(746);
			_la = _input.LA(1);
			if ( !(((((_la - 106)) & ~0x3f) == 0 && ((1L << (_la - 106)) & 63L) != 0)) ) {
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
		enterRule(_localctx, 58, RULE_sqlScript);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(760);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__6 || ((((_la - 82)) & ~0x3f) == 0 && ((1L << (_la - 82)) & 7516192773L) != 0) || _la==CREATE || _la==UPDATE) {
				{
				setState(758);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,65,_ctx) ) {
				case 1:
					{
					setState(748);
					createTableStatement();
					}
					break;
				case 2:
					{
					setState(749);
					createSchemaStatement();
					}
					break;
				case 3:
					{
					setState(750);
					createViewStatement();
					}
					break;
				case 4:
					{
					setState(751);
					createFunctionStatement();
					}
					break;
				case 5:
					{
					setState(752);
					createIndexStatement();
					}
					break;
				case 6:
					{
					setState(753);
					createRuleStatement();
					}
					break;
				case 7:
					{
					setState(754);
					triggerStatement();
					}
					break;
				case 8:
					{
					setState(755);
					alterTableStatement();
					}
					break;
				case 9:
					{
					setState(756);
					dataManipulationStatement();
					}
					break;
				case 10:
					{
					setState(757);
					createPolicyStatement();
					}
					break;
				}
				}
				setState(762);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(763);
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
		enterRule(_localctx, 60, RULE_createSchemaStatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(765);
			match(T__111);
			setState(766);
			match(IDENTIFIER);
			setState(767);
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
		enterRule(_localctx, 62, RULE_createViewStatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(769);
			match(T__112);
			setState(770);
			match(IDENTIFIER);
			setState(771);
			match(AS);
			setState(772);
			selectStatement();
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
		enterRule(_localctx, 64, RULE_createFunctionStatement);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(775);
			match(T__113);
			setState(776);
			match(IDENTIFIER);
			setState(777);
			match(LPAREN);
			setState(786);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==IDENTIFIER) {
				{
				setState(778);
				parameter();
				setState(783);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(779);
					match(COMMA);
					setState(780);
					parameter();
					}
					}
					setState(785);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
			}

			setState(788);
			match(RPAREN);
			setState(789);
			match(T__114);
			setState(790);
			dataType();
			setState(791);
			match(AS);
			setState(792);
			match(DOLLAR_QUOTE);
			setState(793);
			plpgsqlBlock();
			setState(794);
			match(DOLLAR_QUOTE);
			setState(795);
			match(T__115);
			setState(796);
			match(IDENTIFIER);
			setState(797);
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
		enterRule(_localctx, 66, RULE_parameter);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(799);
			match(IDENTIFIER);
			setState(800);
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
		enterRule(_localctx, 68, RULE_selectStatement);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(802);
			match(SELECT);
			setState(803);
			selectList();
			setState(813);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__116) {
				{
				setState(804);
				match(T__116);
				setState(805);
				tableReference();
				setState(810);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(806);
					match(COMMA);
					setState(807);
					tableReference();
					}
					}
					setState(812);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
			}

			setState(816);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__71) {
				{
				setState(815);
				whereClause();
				}
			}

			setState(819);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__117) {
				{
				setState(818);
				orderByClause();
				}
			}

			setState(822);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__120) {
				{
				setState(821);
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
		enterRule(_localctx, 70, RULE_selectList);
		int _la;
		try {
			setState(833);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__107:
				enterOuterAlt(_localctx, 1);
				{
				setState(824);
				match(T__107);
				}
				break;
			case IDENTIFIER:
				enterOuterAlt(_localctx, 2);
				{
				setState(825);
				columnName();
				setState(830);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(826);
					match(COMMA);
					setState(827);
					columnName();
					}
					}
					setState(832);
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
		enterRule(_localctx, 72, RULE_tableReference);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(835);
			tableName();
			setState(838);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==AS) {
				{
				setState(836);
				match(AS);
				setState(837);
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
		enterRule(_localctx, 74, RULE_whereClause);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(840);
			match(T__71);
			setState(841);
			condition(0);
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
		enterRule(_localctx, 76, RULE_orderByClause);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(843);
			match(T__117);
			setState(844);
			columnName();
			setState(846);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__118 || _la==T__119) {
				{
				setState(845);
				_la = _input.LA(1);
				if ( !(_la==T__118 || _la==T__119) ) {
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
		enterRule(_localctx, 78, RULE_limitClause);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(848);
			match(T__120);
			setState(849);
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
		enterRule(_localctx, 80, RULE_columnNameList);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(851);
			columnName();
			setState(856);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,78,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(852);
					match(COMMA);
					setState(853);
					columnName();
					}
					} 
				}
				setState(858);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,78,_ctx);
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
	public static class ForeignTableContext extends ParserRuleContext {
		public TerminalNode LPAREN() { return getToken(PostgreSQLParser.LPAREN, 0); }
		public List<ColumnNameContext> columnName() {
			return getRuleContexts(ColumnNameContext.class);
		}
		public ColumnNameContext columnName(int i) {
			return getRuleContext(ColumnNameContext.class,i);
		}
		public TerminalNode RPAREN() { return getToken(PostgreSQLParser.RPAREN, 0); }
		public TerminalNode DOT() { return getToken(PostgreSQLParser.DOT, 0); }
		public TableNameContext tableName() {
			return getRuleContext(TableNameContext.class,0);
		}
		public List<TerminalNode> COMMA() { return getTokens(PostgreSQLParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(PostgreSQLParser.COMMA, i);
		}
		public SchemaNameContext schemaName() {
			return getRuleContext(SchemaNameContext.class,0);
		}
		public ForeignTableContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_foreignTable; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PostgreSQLListener ) ((PostgreSQLListener)listener).enterForeignTable(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PostgreSQLListener ) ((PostgreSQLListener)listener).exitForeignTable(this);
		}
	}

	public final ForeignTableContext foreignTable() throws RecognitionException {
		ForeignTableContext _localctx = new ForeignTableContext(_ctx, getState());
		enterRule(_localctx, 82, RULE_foreignTable);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(865);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,80,_ctx) ) {
			case 1:
				{
				setState(860);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==IDENTIFIER) {
					{
					setState(859);
					schemaName();
					}
				}

				setState(862);
				match(DOT);
				setState(863);
				tableName();
				}
				break;
			case 2:
				{
				setState(864);
				tableName();
				}
				break;
			}
			setState(867);
			match(LPAREN);
			setState(868);
			columnName();
			setState(873);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(869);
				match(COMMA);
				setState(870);
				columnName();
				}
				}
				setState(875);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(876);
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
		enterRule(_localctx, 84, RULE_conflictAction);
		int _la;
		try {
			setState(888);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__121:
				enterOuterAlt(_localctx, 1);
				{
				setState(878);
				match(T__121);
				}
				break;
			case T__122:
				enterOuterAlt(_localctx, 2);
				{
				setState(879);
				match(T__122);
				setState(880);
				match(SET);
				setState(881);
				columnName();
				setState(882);
				match(EQUALS);
				setState(883);
				value();
				setState(886);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__71) {
					{
					setState(884);
					match(T__71);
					setState(885);
					condition(0);
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
		enterRule(_localctx, 86, RULE_schemaName);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(890);
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
		enterRule(_localctx, 88, RULE_tableName);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(895);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,84,_ctx) ) {
			case 1:
				{
				setState(892);
				schemaName();
				setState(893);
				match(DOT);
				}
				break;
			}
			setState(897);
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
		enterRule(_localctx, 90, RULE_columnName);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(899);
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
		enterRule(_localctx, 92, RULE_relationshipType);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(901);
			_la = _input.LA(1);
			if ( !(((((_la - 124)) & ~0x3f) == 0 && ((1L << (_la - 124)) & 15L) != 0)) ) {
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
		public TerminalNode DECIMAL() { return getToken(PostgreSQLParser.DECIMAL, 0); }
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
		enterRule(_localctx, 94, RULE_decimalType);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(903);
			_la = _input.LA(1);
			if ( !(_la==T__44 || _la==DECIMAL) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			setState(911);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,86,_ctx) ) {
			case 1:
				{
				setState(904);
				match(LPAREN);
				setState(905);
				((DecimalTypeContext)_localctx).precision = match(NUMBER);
				setState(908);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==COMMA) {
					{
					setState(906);
					match(COMMA);
					setState(907);
					((DecimalTypeContext)_localctx).scale = match(NUMBER);
					}
				}

				setState(910);
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
		enterRule(_localctx, 96, RULE_partitionClause);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(913);
			match(T__127);
			setState(914);
			partitionStrategy();
			setState(915);
			match(LPAREN);
			setState(916);
			columnName();
			setState(917);
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
		enterRule(_localctx, 98, RULE_partitionStrategy);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(919);
			_la = _input.LA(1);
			if ( !(((((_la - 129)) & ~0x3f) == 0 && ((1L << (_la - 129)) & 7L) != 0)) ) {
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
	public static class CreatePartitionStatementContext extends ParserRuleContext {
		public TerminalNode CREATE() { return getToken(PostgreSQLParser.CREATE, 0); }
		public TerminalNode TABLE() { return getToken(PostgreSQLParser.TABLE, 0); }
		public List<TableNameContext> tableName() {
			return getRuleContexts(TableNameContext.class);
		}
		public TableNameContext tableName(int i) {
			return getRuleContext(TableNameContext.class,i);
		}
		public PartitionValuesClauseContext partitionValuesClause() {
			return getRuleContext(PartitionValuesClauseContext.class,0);
		}
		public TerminalNode SEMICOLON() { return getToken(PostgreSQLParser.SEMICOLON, 0); }
		public CreatePartitionStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_createPartitionStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PostgreSQLListener ) ((PostgreSQLListener)listener).enterCreatePartitionStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PostgreSQLListener ) ((PostgreSQLListener)listener).exitCreatePartitionStatement(this);
		}
	}

	public final CreatePartitionStatementContext createPartitionStatement() throws RecognitionException {
		CreatePartitionStatementContext _localctx = new CreatePartitionStatementContext(_ctx, getState());
		enterRule(_localctx, 100, RULE_createPartitionStatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(921);
			match(CREATE);
			setState(922);
			match(TABLE);
			setState(923);
			tableName();
			setState(924);
			match(T__0);
			setState(925);
			tableName();
			setState(926);
			partitionValuesClause();
			setState(927);
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
		enterRule(_localctx, 102, RULE_partitionValuesClause);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(929);
			match(T__131);
			setState(955);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__116:
				{
				setState(930);
				match(T__116);
				setState(931);
				match(LPAREN);
				setState(932);
				value();
				setState(933);
				match(RPAREN);
				setState(934);
				match(TO);
				setState(935);
				match(LPAREN);
				setState(936);
				value();
				setState(937);
				match(RPAREN);
				}
				break;
			case T__84:
				{
				setState(939);
				match(T__84);
				setState(940);
				match(LPAREN);
				setState(941);
				value();
				setState(946);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(942);
					match(COMMA);
					setState(943);
					value();
					}
					}
					setState(948);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(949);
				match(RPAREN);
				}
				break;
			case T__132:
				{
				setState(951);
				match(T__132);
				setState(952);
				match(NUMBER);
				setState(953);
				match(T__133);
				setState(954);
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
	public static class PartitionOfClauseContext extends ParserRuleContext {
		public TableNameContext tableName() {
			return getRuleContext(TableNameContext.class,0);
		}
		public PartitionValuesClauseContext partitionValuesClause() {
			return getRuleContext(PartitionValuesClauseContext.class,0);
		}
		public PartitionOfClauseContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_partitionOfClause; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PostgreSQLListener ) ((PostgreSQLListener)listener).enterPartitionOfClause(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PostgreSQLListener ) ((PostgreSQLListener)listener).exitPartitionOfClause(this);
		}
	}

	public final PartitionOfClauseContext partitionOfClause() throws RecognitionException {
		PartitionOfClauseContext _localctx = new PartitionOfClauseContext(_ctx, getState());
		enterRule(_localctx, 104, RULE_partitionOfClause);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(957);
			match(T__0);
			setState(958);
			tableName();
			setState(959);
			partitionValuesClause();
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
		public TerminalNode IDENTIFIER() { return getToken(PostgreSQLParser.IDENTIFIER, 0); }
		public TerminalNode ON() { return getToken(PostgreSQLParser.ON, 0); }
		public TableNameContext tableName() {
			return getRuleContext(TableNameContext.class,0);
		}
		public TerminalNode LPAREN() { return getToken(PostgreSQLParser.LPAREN, 0); }
		public List<ColumnNameContext> columnName() {
			return getRuleContexts(ColumnNameContext.class);
		}
		public ColumnNameContext columnName(int i) {
			return getRuleContext(ColumnNameContext.class,i);
		}
		public TerminalNode RPAREN() { return getToken(PostgreSQLParser.RPAREN, 0); }
		public TerminalNode SEMICOLON() { return getToken(PostgreSQLParser.SEMICOLON, 0); }
		public List<TerminalNode> COMMA() { return getTokens(PostgreSQLParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(PostgreSQLParser.COMMA, i);
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
		enterRule(_localctx, 106, RULE_createIndexStatement);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(961);
			match(CREATE);
			setState(962);
			match(T__134);
			setState(963);
			match(IDENTIFIER);
			setState(964);
			match(ON);
			setState(965);
			tableName();
			setState(966);
			match(LPAREN);
			setState(967);
			columnName();
			setState(972);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(968);
				match(COMMA);
				setState(969);
				columnName();
				}
				}
				setState(974);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(975);
			match(RPAREN);
			setState(977);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__13) {
				{
				setState(976);
				indexOptionsClause();
				}
			}

			setState(979);
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
		enterRule(_localctx, 108, RULE_indexOptionsClause);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(981);
			match(T__13);
			setState(982);
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
		public ValueContext value() {
			return getRuleContext(ValueContext.class,0);
		}
		public ColumnNameContext columnName() {
			return getRuleContext(ColumnNameContext.class,0);
		}
		public AnyExpressionContext anyExpression() {
			return getRuleContext(AnyExpressionContext.class,0);
		}
		public ArrayConstructorContext arrayConstructor() {
			return getRuleContext(ArrayConstructorContext.class,0);
		}
		public TerminalNode LPAREN() { return getToken(PostgreSQLParser.LPAREN, 0); }
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public TerminalNode RPAREN() { return getToken(PostgreSQLParser.RPAREN, 0); }
		public ComparisonOperatorContext comparisonOperator() {
			return getRuleContext(ComparisonOperatorContext.class,0);
		}
		public TerminalNode DOUBLE_COLON() { return getToken(PostgreSQLParser.DOUBLE_COLON, 0); }
		public TypeNameContext typeName() {
			return getRuleContext(TypeNameContext.class,0);
		}
		public DataTypeContext dataType() {
			return getRuleContext(DataTypeContext.class,0);
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
		return expression(0);
	}

	private ExpressionContext expression(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		ExpressionContext _localctx = new ExpressionContext(_ctx, _parentState);
		ExpressionContext _prevctx = _localctx;
		int _startState = 110;
		enterRecursionRule(_localctx, 110, RULE_expression, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(993);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,91,_ctx) ) {
			case 1:
				{
				setState(985);
				value();
				}
				break;
			case 2:
				{
				setState(986);
				columnName();
				}
				break;
			case 3:
				{
				setState(987);
				anyExpression();
				}
				break;
			case 4:
				{
				setState(988);
				arrayConstructor();
				}
				break;
			case 5:
				{
				setState(989);
				match(LPAREN);
				setState(990);
				expression(0);
				setState(991);
				match(RPAREN);
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(1016);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,94,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(1014);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,93,_ctx) ) {
					case 1:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(995);
						if (!(precpred(_ctx, 5))) throw new FailedPredicateException(this, "precpred(_ctx, 5)");
						setState(996);
						_la = _input.LA(1);
						if ( !(((((_la - 106)) & ~0x3f) == 0 && ((1L << (_la - 106)) & 15L) != 0)) ) {
						_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(997);
						expression(6);
						}
						break;
					case 2:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(998);
						if (!(precpred(_ctx, 4))) throw new FailedPredicateException(this, "precpred(_ctx, 4)");
						setState(999);
						comparisonOperator();
						setState(1000);
						expression(5);
						}
						break;
					case 3:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(1002);
						if (!(precpred(_ctx, 3))) throw new FailedPredicateException(this, "precpred(_ctx, 3)");
						setState(1003);
						match(T__86);
						setState(1004);
						expression(4);
						}
						break;
					case 4:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(1005);
						if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
						setState(1006);
						match(T__80);
						setState(1007);
						expression(3);
						}
						break;
					case 5:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(1008);
						if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
						setState(1009);
						match(DOUBLE_COLON);
						setState(1012);
						_errHandler.sync(this);
						switch ( getInterpreter().adaptivePredict(_input,92,_ctx) ) {
						case 1:
							{
							setState(1010);
							typeName();
							}
							break;
						case 2:
							{
							setState(1011);
							dataType();
							}
							break;
						}
						}
						break;
					}
					} 
				}
				setState(1018);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,94,_ctx);
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
	public static class ValueContext extends ParserRuleContext {
		public TerminalNode STRING() { return getToken(PostgreSQLParser.STRING, 0); }
		public TerminalNode NUMBER() { return getToken(PostgreSQLParser.NUMBER, 0); }
		public TerminalNode NULL() { return getToken(PostgreSQLParser.NULL, 0); }
		public IntervalLiteralContext intervalLiteral() {
			return getRuleContext(IntervalLiteralContext.class,0);
		}
		public ArrayConstructorContext arrayConstructor() {
			return getRuleContext(ArrayConstructorContext.class,0);
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
		enterRule(_localctx, 112, RULE_value);
		try {
			setState(1031);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case STRING:
				enterOuterAlt(_localctx, 1);
				{
				setState(1019);
				match(STRING);
				}
				break;
			case NUMBER:
				enterOuterAlt(_localctx, 2);
				{
				setState(1020);
				match(NUMBER);
				}
				break;
			case T__135:
				enterOuterAlt(_localctx, 3);
				{
				setState(1021);
				match(T__135);
				}
				break;
			case T__136:
				enterOuterAlt(_localctx, 4);
				{
				setState(1022);
				match(T__136);
				}
				break;
			case NULL:
				enterOuterAlt(_localctx, 5);
				{
				setState(1023);
				match(NULL);
				}
				break;
			case T__68:
				enterOuterAlt(_localctx, 6);
				{
				setState(1024);
				match(T__68);
				}
				break;
			case T__137:
				enterOuterAlt(_localctx, 7);
				{
				setState(1025);
				match(T__137);
				}
				break;
			case T__138:
				enterOuterAlt(_localctx, 8);
				{
				setState(1026);
				match(T__138);
				}
				break;
			case T__139:
				enterOuterAlt(_localctx, 9);
				{
				setState(1027);
				match(T__139);
				}
				break;
			case T__140:
				enterOuterAlt(_localctx, 10);
				{
				setState(1028);
				match(T__140);
				}
				break;
			case T__55:
				enterOuterAlt(_localctx, 11);
				{
				setState(1029);
				intervalLiteral();
				}
				break;
			case ARRAY:
				enterOuterAlt(_localctx, 12);
				{
				setState(1030);
				arrayConstructor();
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
		enterRule(_localctx, 114, RULE_expressionList);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1033);
			expression(0);
			setState(1038);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(1034);
				match(COMMA);
				setState(1035);
				expression(0);
				}
				}
				setState(1040);
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
		public TerminalNode DOUBLE_COLON() { return getToken(PostgreSQLParser.DOUBLE_COLON, 0); }
		public DataTypeContext dataType() {
			return getRuleContext(DataTypeContext.class,0);
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
		enterRule(_localctx, 116, RULE_arrayConstructor);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1041);
			match(ARRAY);
			setState(1042);
			match(LBRACKET);
			setState(1043);
			expressionList();
			setState(1044);
			match(RBRACKET);
			setState(1047);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,97,_ctx) ) {
			case 1:
				{
				setState(1045);
				match(DOUBLE_COLON);
				setState(1046);
				dataType();
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
		enterRule(_localctx, 118, RULE_createPolicyStatement);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1049);
			match(CREATE);
			setState(1050);
			match(T__141);
			setState(1051);
			match(IDENTIFIER);
			setState(1052);
			match(ON);
			setState(1053);
			tableName();
			setState(1056);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==FOR) {
				{
				setState(1054);
				match(FOR);
				setState(1055);
				_la = _input.LA(1);
				if ( !(((((_la - 143)) & ~0x3f) == 0 && ((1L << (_la - 143)) & 16842753L) != 0) || _la==UPDATE || _la==INSERT) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				}
			}

			setState(1063);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==USING) {
				{
				setState(1058);
				match(USING);
				setState(1059);
				match(LPAREN);
				setState(1060);
				condition(0);
				setState(1061);
				match(RPAREN);
				}
			}

			setState(1070);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__143) {
				{
				setState(1065);
				match(T__143);
				setState(1066);
				match(LPAREN);
				setState(1067);
				condition(0);
				setState(1068);
				match(RPAREN);
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
		enterRule(_localctx, 120, RULE_plpgsqlBlock);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1074);
			match(T__144);
			setState(1078);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__146 || _la==NEW || _la==IDENTIFIER) {
				{
				{
				setState(1075);
				statement();
				}
				}
				setState(1080);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(1081);
			match(T__145);
			setState(1082);
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
		enterRule(_localctx, 122, RULE_statement);
		try {
			setState(1101);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case IDENTIFIER:
				enterOuterAlt(_localctx, 1);
				{
				setState(1084);
				columnName();
				setState(1085);
				match(DOT);
				setState(1086);
				columnName();
				setState(1087);
				match(EQUALS);
				setState(1088);
				value();
				setState(1089);
				match(SEMICOLON);
				}
				break;
			case T__146:
				enterOuterAlt(_localctx, 2);
				{
				setState(1091);
				match(T__146);
				setState(1092);
				match(NEW);
				setState(1093);
				match(SEMICOLON);
				}
				break;
			case NEW:
				enterOuterAlt(_localctx, 3);
				{
				setState(1094);
				match(NEW);
				setState(1095);
				match(DOT);
				setState(1096);
				columnName();
				setState(1097);
				match(EQUALS);
				setState(1098);
				value();
				setState(1099);
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
		enterRule(_localctx, 124, RULE_createRuleStatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1103);
			match(CREATE);
			setState(1104);
			match(RULE);
			setState(1105);
			match(IDENTIFIER);
			setState(1106);
			match(AS);
			setState(1107);
			match(ON);
			setState(1108);
			ruleEvent();
			setState(1109);
			match(TO);
			setState(1110);
			tableName();
			setState(1111);
			match(DO);
			setState(1112);
			ruleAction();
			setState(1113);
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
		enterRule(_localctx, 126, RULE_ruleEvent);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1115);
			_la = _input.LA(1);
			if ( !(((((_la - 159)) & ~0x3f) == 0 && ((1L << (_la - 159)) & 6755399441056001L) != 0)) ) {
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
		enterRule(_localctx, 128, RULE_ruleAction);
		try {
			setState(1120);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__147:
				enterOuterAlt(_localctx, 1);
				{
				setState(1117);
				match(T__147);
				}
				break;
			case INSTEAD:
				enterOuterAlt(_localctx, 2);
				{
				setState(1118);
				match(INSTEAD);
				setState(1119);
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
		enterRule(_localctx, 130, RULE_sqlAction);
		int _la;
		try {
			setState(1155);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__81:
				enterOuterAlt(_localctx, 1);
				{
				setState(1122);
				match(T__81);
				setState(1123);
				tableName();
				setState(1124);
				match(LPAREN);
				setState(1125);
				columnName();
				setState(1130);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(1126);
					match(COMMA);
					setState(1127);
					columnName();
					}
					}
					setState(1132);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(1133);
				match(RPAREN);
				setState(1134);
				match(T__82);
				setState(1135);
				match(LPAREN);
				setState(1136);
				value();
				setState(1141);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(1137);
					match(COMMA);
					setState(1138);
					value();
					}
					}
					setState(1143);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(1144);
				match(RPAREN);
				}
				break;
			case UPDATE:
				enterOuterAlt(_localctx, 2);
				{
				setState(1146);
				match(UPDATE);
				setState(1147);
				tableName();
				setState(1148);
				match(SET);
				setState(1149);
				columnName();
				setState(1150);
				match(EQUALS);
				setState(1151);
				value();
				}
				break;
			case T__83:
				enterOuterAlt(_localctx, 3);
				{
				setState(1153);
				match(T__83);
				setState(1154);
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
	public static class DefaultValueContext extends ParserRuleContext {
		public TerminalNode DEFAULT() { return getToken(PostgreSQLParser.DEFAULT, 0); }
		public NumericLiteralContext numericLiteral() {
			return getRuleContext(NumericLiteralContext.class,0);
		}
		public BooleanLiteralContext booleanLiteral() {
			return getRuleContext(BooleanLiteralContext.class,0);
		}
		public DefaultValueContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_defaultValue; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PostgreSQLListener ) ((PostgreSQLListener)listener).enterDefaultValue(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PostgreSQLListener ) ((PostgreSQLListener)listener).exitDefaultValue(this);
		}
	}

	public final DefaultValueContext defaultValue() throws RecognitionException {
		DefaultValueContext _localctx = new DefaultValueContext(_ctx, getState());
		enterRule(_localctx, 132, RULE_defaultValue);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1157);
			match(DEFAULT);
			setState(1162);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case EOF:
				{
				}
				break;
			case T__106:
			case DIGIT:
				{
				setState(1159);
				numericLiteral();
				}
				break;
			case T__135:
			case T__136:
				{
				setState(1160);
				booleanLiteral();
				}
				break;
			case T__68:
				{
				setState(1161);
				match(T__68);
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
	public static class NumericLiteralContext extends ParserRuleContext {
		public List<TerminalNode> DIGIT() { return getTokens(PostgreSQLParser.DIGIT); }
		public TerminalNode DIGIT(int i) {
			return getToken(PostgreSQLParser.DIGIT, i);
		}
		public TerminalNode DOT() { return getToken(PostgreSQLParser.DOT, 0); }
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
		enterRule(_localctx, 134, RULE_numericLiteral);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1165);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__106) {
				{
				setState(1164);
				match(T__106);
				}
			}

			setState(1168); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(1167);
				match(DIGIT);
				}
				}
				setState(1170); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==DIGIT );
			setState(1178);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==DOT) {
				{
				setState(1172);
				match(DOT);
				setState(1174); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(1173);
					match(DIGIT);
					}
					}
					setState(1176); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( _la==DIGIT );
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
		enterRule(_localctx, 136, RULE_booleanLiteral);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1180);
			_la = _input.LA(1);
			if ( !(_la==T__135 || _la==T__136) ) {
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
	public static class ForeignKeyActionContext extends ParserRuleContext {
		public ReferentialActionContext fkAction;
		public TerminalNode ON_DELETE() { return getToken(PostgreSQLParser.ON_DELETE, 0); }
		public ReferentialActionContext referentialAction() {
			return getRuleContext(ReferentialActionContext.class,0);
		}
		public TerminalNode ON_UPDATE() { return getToken(PostgreSQLParser.ON_UPDATE, 0); }
		public ForeignKeyActionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_foreignKeyAction; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PostgreSQLListener ) ((PostgreSQLListener)listener).enterForeignKeyAction(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PostgreSQLListener ) ((PostgreSQLListener)listener).exitForeignKeyAction(this);
		}
	}

	public final ForeignKeyActionContext foreignKeyAction() throws RecognitionException {
		ForeignKeyActionContext _localctx = new ForeignKeyActionContext(_ctx, getState());
		enterRule(_localctx, 138, RULE_foreignKeyAction);
		try {
			setState(1186);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case ON_DELETE:
				enterOuterAlt(_localctx, 1);
				{
				setState(1182);
				match(ON_DELETE);
				setState(1183);
				((ForeignKeyActionContext)_localctx).fkAction = referentialAction();
				}
				break;
			case ON_UPDATE:
				enterOuterAlt(_localctx, 2);
				{
				setState(1184);
				match(ON_UPDATE);
				setState(1185);
				((ForeignKeyActionContext)_localctx).fkAction = referentialAction();
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
	public static class ReferentialActionContext extends ParserRuleContext {
		public TerminalNode CASCADE() { return getToken(PostgreSQLParser.CASCADE, 0); }
		public TerminalNode SET_NULL() { return getToken(PostgreSQLParser.SET_NULL, 0); }
		public TerminalNode SET_DEFAULT() { return getToken(PostgreSQLParser.SET_DEFAULT, 0); }
		public TerminalNode RESTRICT() { return getToken(PostgreSQLParser.RESTRICT, 0); }
		public TerminalNode NO_ACTION() { return getToken(PostgreSQLParser.NO_ACTION, 0); }
		public ReferentialActionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_referentialAction; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PostgreSQLListener ) ((PostgreSQLListener)listener).enterReferentialAction(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PostgreSQLListener ) ((PostgreSQLListener)listener).exitReferentialAction(this);
		}
	}

	public final ReferentialActionContext referentialAction() throws RecognitionException {
		ReferentialActionContext _localctx = new ReferentialActionContext(_ctx, getState());
		enterRule(_localctx, 140, RULE_referentialAction);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1188);
			_la = _input.LA(1);
			if ( !(((((_la - 193)) & ~0x3f) == 0 && ((1L << (_la - 193)) & 31L) != 0)) ) {
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
		public ArrayConstructorContext arrayConstructor() {
			return getRuleContext(ArrayConstructorContext.class,0);
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
		enterRule(_localctx, 142, RULE_anyExpression);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1190);
			match(ANY);
			setState(1191);
			match(LPAREN);
			setState(1192);
			arrayConstructor();
			setState(1193);
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
		enterRule(_localctx, 144, RULE_intervalLiteral);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1195);
			match(T__55);
			setState(1196);
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
		enterRule(_localctx, 146, RULE_typeName);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1198);
			match(IDENTIFIER);
			setState(1201);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,113,_ctx) ) {
			case 1:
				{
				setState(1199);
				match(DOT);
				setState(1200);
				match(IDENTIFIER);
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

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 25:
			return condition_sempred((ConditionContext)_localctx, predIndex);
		case 55:
			return expression_sempred((ExpressionContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean condition_sempred(ConditionContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 4);
		case 1:
			return precpred(_ctx, 3);
		}
		return true;
	}
	private boolean expression_sempred(ExpressionContext _localctx, int predIndex) {
		switch (predIndex) {
		case 2:
			return precpred(_ctx, 5);
		case 3:
			return precpred(_ctx, 4);
		case 4:
			return precpred(_ctx, 3);
		case 5:
			return precpred(_ctx, 2);
		case 6:
			return precpred(_ctx, 1);
		}
		return true;
	}

	public static final String _serializedATN =
		"\u0004\u0001\u00dc\u04b4\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001"+
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
		"E\u0002F\u0007F\u0002G\u0007G\u0002H\u0007H\u0002I\u0007I\u0001\u0000"+
		"\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0000\u0003\u0000"+
		"\u009b\b\u0000\u0001\u0000\u0001\u0000\u0001\u0000\u0003\u0000\u00a0\b"+
		"\u0000\u0005\u0000\u00a2\b\u0000\n\u0000\f\u0000\u00a5\t\u0000\u0001\u0000"+
		"\u0001\u0000\u0003\u0000\u00a9\b\u0000\u0001\u0000\u0001\u0000\u0001\u0000"+
		"\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0000"+
		"\u0001\u0000\u0003\u0000\u00b5\b\u0000\u0001\u0001\u0001\u0001\u0001\u0001"+
		"\u0003\u0001\u00ba\b\u0001\u0001\u0001\u0005\u0001\u00bd\b\u0001\n\u0001"+
		"\f\u0001\u00c0\t\u0001\u0001\u0001\u0001\u0001\u0003\u0001\u00c4\b\u0001"+
		"\u0001\u0001\u0003\u0001\u00c7\b\u0001\u0001\u0001\u0001\u0001\u0001\u0001"+
		"\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001"+
		"\u0001\u0001\u0001\u0001\u0003\u0001\u00d4\b\u0001\u0001\u0001\u0005\u0001"+
		"\u00d7\b\u0001\n\u0001\f\u0001\u00da\t\u0001\u0003\u0001\u00dc\b\u0001"+
		"\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0003\u0002\u00e2\b\u0002"+
		"\u0001\u0002\u0001\u0002\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003"+
		"\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0004\u0001\u0004"+
		"\u0001\u0004\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005"+
		"\u0001\u0005\u0005\u0005\u00f7\b\u0005\n\u0005\f\u0005\u00fa\t\u0005\u0001"+
		"\u0005\u0003\u0005\u00fd\b\u0005\u0001\u0006\u0001\u0006\u0001\u0006\u0001"+
		"\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0003"+
		"\u0006\u0108\b\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001"+
		"\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0003"+
		"\u0006\u0114\b\u0006\u0001\u0006\u0005\u0006\u0117\b\u0006\n\u0006\f\u0006"+
		"\u011a\t\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006"+
		"\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006"+
		"\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006"+
		"\u0001\u0006\u0001\u0006\u0001\u0006\u0003\u0006\u0130\b\u0006\u0001\u0006"+
		"\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006"+
		"\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006"+
		"\u0001\u0006\u0003\u0006\u0140\b\u0006\u0001\u0007\u0001\u0007\u0001\u0007"+
		"\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007"+
		"\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007"+
		"\u0004\u0007\u0151\b\u0007\u000b\u0007\f\u0007\u0152\u0001\b\u0001\b\u0001"+
		"\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001"+
		"\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0003\b\u0166\b\b\u0001\b\u0003"+
		"\b\u0169\b\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0003\b\u0171"+
		"\b\b\u0001\b\u0001\b\u0001\b\u0001\b\u0003\b\u0177\b\b\u0001\b\u0001\b"+
		"\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001"+
		"\b\u0001\b\u0003\b\u0185\b\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001"+
		"\b\u0001\b\u0003\b\u018e\b\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001"+
		"\b\u0001\b\u0001\b\u0001\b\u0005\b\u0199\b\b\n\b\f\b\u019c\t\b\u0001\b"+
		"\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001"+
		"\b\u0001\b\u0001\b\u0003\b\u01aa\b\b\u0001\b\u0001\b\u0001\b\u0001\b\u0003"+
		"\b\u01b0\b\b\u0001\b\u0001\b\u0003\b\u01b4\b\b\u0001\t\u0001\t\u0001\n"+
		"\u0001\n\u0001\u000b\u0001\u000b\u0001\u000b\u0001\f\u0001\f\u0001\f\u0001"+
		"\f\u0001\f\u0003\f\u01c2\b\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001"+
		"\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0003\f\u01cf\b\f\u0001\f\u0001"+
		"\f\u0001\f\u0001\f\u0001\f\u0003\f\u01d6\b\f\u0001\f\u0001\f\u0001\f\u0001"+
		"\f\u0001\f\u0001\f\u0003\f\u01de\b\f\u0001\f\u0001\f\u0001\f\u0001\f\u0003"+
		"\f\u01e4\b\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001"+
		"\f\u0003\f\u01ee\b\f\u0001\f\u0001\f\u0003\f\u01f2\b\f\u0001\r\u0001\r"+
		"\u0003\r\u01f6\b\r\u0001\r\u0001\r\u0001\r\u0001\r\u0001\r\u0005\r\u01fd"+
		"\b\r\n\r\f\r\u0200\t\r\u0001\r\u0001\r\u0001\r\u0001\r\u0003\r\u0206\b"+
		"\r\u0001\r\u0001\r\u0001\r\u0001\r\u0001\r\u0001\r\u0003\r\u020e\b\r\u0001"+
		"\r\u0001\r\u0001\r\u0005\r\u0213\b\r\n\r\f\r\u0216\t\r\u0001\r\u0001\r"+
		"\u0003\r\u021a\b\r\u0001\r\u0001\r\u0001\r\u0001\r\u0003\r\u0220\b\r\u0001"+
		"\r\u0001\r\u0001\r\u0001\r\u0001\r\u0001\r\u0001\r\u0001\r\u0001\r\u0001"+
		"\r\u0001\r\u0001\r\u0003\r\u022e\b\r\u0001\u000e\u0001\u000e\u0001\u000e"+
		"\u0005\u000e\u0233\b\u000e\n\u000e\f\u000e\u0236\t\u000e\u0001\u000f\u0001"+
		"\u000f\u0001\u000f\u0001\u000f\u0001\u0010\u0001\u0010\u0001\u0010\u0001"+
		"\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0003\u0010\u0243\b\u0010\u0003"+
		"\u0010\u0245\b\u0010\u0001\u0011\u0001\u0011\u0001\u0012\u0001\u0012\u0001"+
		"\u0012\u0001\u0012\u0001\u0012\u0001\u0012\u0001\u0012\u0001\u0012\u0001"+
		"\u0012\u0001\u0012\u0001\u0012\u0001\u0012\u0001\u0012\u0001\u0012\u0005"+
		"\u0012\u0257\b\u0012\n\u0012\f\u0012\u025a\t\u0012\u0003\u0012\u025c\b"+
		"\u0012\u0001\u0012\u0001\u0012\u0001\u0012\u0001\u0013\u0001\u0013\u0001"+
		"\u0014\u0001\u0014\u0001\u0014\u0005\u0014\u0266\b\u0014\n\u0014\f\u0014"+
		"\u0269\t\u0014\u0001\u0015\u0001\u0015\u0001\u0015\u0003\u0015\u026e\b"+
		"\u0015\u0001\u0016\u0001\u0016\u0001\u0016\u0001\u0016\u0001\u0016\u0001"+
		"\u0016\u0005\u0016\u0276\b\u0016\n\u0016\f\u0016\u0279\t\u0016\u0001\u0016"+
		"\u0001\u0016\u0001\u0016\u0001\u0016\u0001\u0016\u0001\u0016\u0005\u0016"+
		"\u0281\b\u0016\n\u0016\f\u0016\u0284\t\u0016\u0001\u0016\u0001\u0016\u0001"+
		"\u0016\u0001\u0017\u0001\u0017\u0001\u0017\u0001\u0017\u0001\u0017\u0001"+
		"\u0017\u0001\u0017\u0001\u0017\u0001\u0017\u0001\u0017\u0001\u0017\u0005"+
		"\u0017\u0294\b\u0017\n\u0017\f\u0017\u0297\t\u0017\u0001\u0017\u0001\u0017"+
		"\u0003\u0017\u029b\b\u0017\u0001\u0017\u0001\u0017\u0001\u0018\u0001\u0018"+
		"\u0001\u0018\u0001\u0018\u0003\u0018\u02a3\b\u0018\u0001\u0018\u0001\u0018"+
		"\u0001\u0019\u0001\u0019\u0001\u0019\u0001\u0019\u0001\u0019\u0001\u0019"+
		"\u0001\u0019\u0001\u0019\u0001\u0019\u0001\u0019\u0003\u0019\u02b1\b\u0019"+
		"\u0001\u0019\u0001\u0019\u0001\u0019\u0003\u0019\u02b6\b\u0019\u0005\u0019"+
		"\u02b8\b\u0019\n\u0019\f\u0019\u02bb\t\u0019\u0001\u0019\u0001\u0019\u0001"+
		"\u0019\u0001\u0019\u0001\u0019\u0001\u0019\u0001\u0019\u0001\u0019\u0001"+
		"\u0019\u0001\u0019\u0001\u0019\u0001\u0019\u0001\u0019\u0001\u0019\u0001"+
		"\u0019\u0001\u0019\u0001\u0019\u0001\u0019\u0001\u0019\u0001\u0019\u0001"+
		"\u0019\u0001\u0019\u0001\u0019\u0001\u0019\u0001\u0019\u0001\u0019\u0001"+
		"\u0019\u0003\u0019\u02d8\b\u0019\u0001\u0019\u0001\u0019\u0001\u0019\u0001"+
		"\u0019\u0001\u0019\u0001\u0019\u0005\u0019\u02e0\b\u0019\n\u0019\f\u0019"+
		"\u02e3\t\u0019\u0001\u001a\u0001\u001a\u0001\u001a\u0001\u001a\u0001\u001b"+
		"\u0001\u001b\u0001\u001c\u0001\u001c\u0001\u001d\u0001\u001d\u0001\u001d"+
		"\u0001\u001d\u0001\u001d\u0001\u001d\u0001\u001d\u0001\u001d\u0001\u001d"+
		"\u0001\u001d\u0005\u001d\u02f7\b\u001d\n\u001d\f\u001d\u02fa\t\u001d\u0001"+
		"\u001d\u0001\u001d\u0001\u001e\u0001\u001e\u0001\u001e\u0001\u001e\u0001"+
		"\u001f\u0001\u001f\u0001\u001f\u0001\u001f\u0001\u001f\u0001\u001f\u0001"+
		" \u0001 \u0001 \u0001 \u0001 \u0001 \u0005 \u030e\b \n \f \u0311\t \u0003"+
		" \u0313\b \u0001 \u0001 \u0001 \u0001 \u0001 \u0001 \u0001 \u0001 \u0001"+
		" \u0001 \u0001 \u0001!\u0001!\u0001!\u0001\"\u0001\"\u0001\"\u0001\"\u0001"+
		"\"\u0001\"\u0005\"\u0329\b\"\n\"\f\"\u032c\t\"\u0003\"\u032e\b\"\u0001"+
		"\"\u0003\"\u0331\b\"\u0001\"\u0003\"\u0334\b\"\u0001\"\u0003\"\u0337\b"+
		"\"\u0001#\u0001#\u0001#\u0001#\u0005#\u033d\b#\n#\f#\u0340\t#\u0003#\u0342"+
		"\b#\u0001$\u0001$\u0001$\u0003$\u0347\b$\u0001%\u0001%\u0001%\u0001&\u0001"+
		"&\u0001&\u0003&\u034f\b&\u0001\'\u0001\'\u0001\'\u0001(\u0001(\u0001("+
		"\u0005(\u0357\b(\n(\f(\u035a\t(\u0001)\u0003)\u035d\b)\u0001)\u0001)\u0001"+
		")\u0003)\u0362\b)\u0001)\u0001)\u0001)\u0001)\u0005)\u0368\b)\n)\f)\u036b"+
		"\t)\u0001)\u0001)\u0001*\u0001*\u0001*\u0001*\u0001*\u0001*\u0001*\u0001"+
		"*\u0003*\u0377\b*\u0003*\u0379\b*\u0001+\u0001+\u0001,\u0001,\u0001,\u0003"+
		",\u0380\b,\u0001,\u0001,\u0001-\u0001-\u0001.\u0001.\u0001/\u0001/\u0001"+
		"/\u0001/\u0001/\u0003/\u038d\b/\u0001/\u0003/\u0390\b/\u00010\u00010\u0001"+
		"0\u00010\u00010\u00010\u00011\u00011\u00012\u00012\u00012\u00012\u0001"+
		"2\u00012\u00012\u00012\u00013\u00013\u00013\u00013\u00013\u00013\u0001"+
		"3\u00013\u00013\u00013\u00013\u00013\u00013\u00013\u00013\u00053\u03b1"+
		"\b3\n3\f3\u03b4\t3\u00013\u00013\u00013\u00013\u00013\u00013\u00033\u03bc"+
		"\b3\u00014\u00014\u00014\u00014\u00015\u00015\u00015\u00015\u00015\u0001"+
		"5\u00015\u00015\u00015\u00055\u03cb\b5\n5\f5\u03ce\t5\u00015\u00015\u0003"+
		"5\u03d2\b5\u00015\u00015\u00016\u00016\u00016\u00017\u00017\u00017\u0001"+
		"7\u00017\u00017\u00017\u00017\u00017\u00037\u03e2\b7\u00017\u00017\u0001"+
		"7\u00017\u00017\u00017\u00017\u00017\u00017\u00017\u00017\u00017\u0001"+
		"7\u00017\u00017\u00017\u00017\u00037\u03f5\b7\u00057\u03f7\b7\n7\f7\u03fa"+
		"\t7\u00018\u00018\u00018\u00018\u00018\u00018\u00018\u00018\u00018\u0001"+
		"8\u00018\u00018\u00038\u0408\b8\u00019\u00019\u00019\u00059\u040d\b9\n"+
		"9\f9\u0410\t9\u0001:\u0001:\u0001:\u0001:\u0001:\u0001:\u0003:\u0418\b"+
		":\u0001;\u0001;\u0001;\u0001;\u0001;\u0001;\u0001;\u0003;\u0421\b;\u0001"+
		";\u0001;\u0001;\u0001;\u0001;\u0003;\u0428\b;\u0001;\u0001;\u0001;\u0001"+
		";\u0001;\u0003;\u042f\b;\u0001;\u0001;\u0001<\u0001<\u0005<\u0435\b<\n"+
		"<\f<\u0438\t<\u0001<\u0001<\u0001<\u0001=\u0001=\u0001=\u0001=\u0001="+
		"\u0001=\u0001=\u0001=\u0001=\u0001=\u0001=\u0001=\u0001=\u0001=\u0001"+
		"=\u0001=\u0001=\u0003=\u044e\b=\u0001>\u0001>\u0001>\u0001>\u0001>\u0001"+
		">\u0001>\u0001>\u0001>\u0001>\u0001>\u0001>\u0001?\u0001?\u0001@\u0001"+
		"@\u0001@\u0003@\u0461\b@\u0001A\u0001A\u0001A\u0001A\u0001A\u0001A\u0005"+
		"A\u0469\bA\nA\fA\u046c\tA\u0001A\u0001A\u0001A\u0001A\u0001A\u0001A\u0005"+
		"A\u0474\bA\nA\fA\u0477\tA\u0001A\u0001A\u0001A\u0001A\u0001A\u0001A\u0001"+
		"A\u0001A\u0001A\u0001A\u0001A\u0003A\u0484\bA\u0001B\u0001B\u0001B\u0001"+
		"B\u0001B\u0003B\u048b\bB\u0001C\u0003C\u048e\bC\u0001C\u0004C\u0491\b"+
		"C\u000bC\fC\u0492\u0001C\u0001C\u0004C\u0497\bC\u000bC\fC\u0498\u0003"+
		"C\u049b\bC\u0001D\u0001D\u0001E\u0001E\u0001E\u0001E\u0003E\u04a3\bE\u0001"+
		"F\u0001F\u0001G\u0001G\u0001G\u0001G\u0001G\u0001H\u0001H\u0001H\u0001"+
		"I\u0001I\u0001I\u0003I\u04b2\bI\u0001I\u0000\u00022nJ\u0000\u0002\u0004"+
		"\u0006\b\n\f\u000e\u0010\u0012\u0014\u0016\u0018\u001a\u001c\u001e \""+
		"$&(*,.02468:<>@BDFHJLNPRTVXZ\\^`bdfhjlnprtvxz|~\u0080\u0082\u0084\u0086"+
		"\u0088\u008a\u008c\u008e\u0090\u0092\u0000\u0012\u0001\u0000\u0003\u0004"+
		"\u0001\u0000\u001a\u001d\u0001\u0000 \"\u0001\u0000\u00c1\u00c4\u0001"+
		"\u0000LM\u0001\u0000\u00c1\u00c5\u0002\u0000PP\u00d0\u00d1\u0002\u0000"+
		"\u009f\u009f\u00d2\u00d3\u0002\u0000^i\u00c9\u00c9\u0001\u0000jo\u0001"+
		"\u0000wx\u0001\u0000|\u007f\u0002\u0000--\u00ae\u00ae\u0001\u0000\u0081"+
		"\u0083\u0001\u0000jm\u0004\u0000\u008f\u008f\u009f\u009f\u00a7\u00a7\u00d2"+
		"\u00d3\u0003\u0000\u009f\u009f\u00a7\u00a7\u00d2\u00d3\u0001\u0000\u0088"+
		"\u0089\u0548\u0000\u00b4\u0001\u0000\u0000\u0000\u0002\u00db\u0001\u0000"+
		"\u0000\u0000\u0004\u00dd\u0001\u0000\u0000\u0000\u0006\u00e5\u0001\u0000"+
		"\u0000\u0000\b\u00ed\u0001\u0000\u0000\u0000\n\u00f0\u0001\u0000\u0000"+
		"\u0000\f\u013f\u0001\u0000\u0000\u0000\u000e\u0150\u0001\u0000\u0000\u0000"+
		"\u0010\u01b3\u0001\u0000\u0000\u0000\u0012\u01b5\u0001\u0000\u0000\u0000"+
		"\u0014\u01b7\u0001\u0000\u0000\u0000\u0016\u01b9\u0001\u0000\u0000\u0000"+
		"\u0018\u01f1\u0001\u0000\u0000\u0000\u001a\u022d\u0001\u0000\u0000\u0000"+
		"\u001c\u022f\u0001\u0000\u0000\u0000\u001e\u0237\u0001\u0000\u0000\u0000"+
		" \u0244\u0001\u0000\u0000\u0000\"\u0246\u0001\u0000\u0000\u0000$\u0248"+
		"\u0001\u0000\u0000\u0000&\u0260\u0001\u0000\u0000\u0000(\u0262\u0001\u0000"+
		"\u0000\u0000*\u026d\u0001\u0000\u0000\u0000,\u026f\u0001\u0000\u0000\u0000"+
		".\u0288\u0001\u0000\u0000\u00000\u029e\u0001\u0000\u0000\u00002\u02d7"+
		"\u0001\u0000\u0000\u00004\u02e4\u0001\u0000\u0000\u00006\u02e8\u0001\u0000"+
		"\u0000\u00008\u02ea\u0001\u0000\u0000\u0000:\u02f8\u0001\u0000\u0000\u0000"+
		"<\u02fd\u0001\u0000\u0000\u0000>\u0301\u0001\u0000\u0000\u0000@\u0307"+
		"\u0001\u0000\u0000\u0000B\u031f\u0001\u0000\u0000\u0000D\u0322\u0001\u0000"+
		"\u0000\u0000F\u0341\u0001\u0000\u0000\u0000H\u0343\u0001\u0000\u0000\u0000"+
		"J\u0348\u0001\u0000\u0000\u0000L\u034b\u0001\u0000\u0000\u0000N\u0350"+
		"\u0001\u0000\u0000\u0000P\u0353\u0001\u0000\u0000\u0000R\u0361\u0001\u0000"+
		"\u0000\u0000T\u0378\u0001\u0000\u0000\u0000V\u037a\u0001\u0000\u0000\u0000"+
		"X\u037f\u0001\u0000\u0000\u0000Z\u0383\u0001\u0000\u0000\u0000\\\u0385"+
		"\u0001\u0000\u0000\u0000^\u0387\u0001\u0000\u0000\u0000`\u0391\u0001\u0000"+
		"\u0000\u0000b\u0397\u0001\u0000\u0000\u0000d\u0399\u0001\u0000\u0000\u0000"+
		"f\u03a1\u0001\u0000\u0000\u0000h\u03bd\u0001\u0000\u0000\u0000j\u03c1"+
		"\u0001\u0000\u0000\u0000l\u03d5\u0001\u0000\u0000\u0000n\u03e1\u0001\u0000"+
		"\u0000\u0000p\u0407\u0001\u0000\u0000\u0000r\u0409\u0001\u0000\u0000\u0000"+
		"t\u0411\u0001\u0000\u0000\u0000v\u0419\u0001\u0000\u0000\u0000x\u0432"+
		"\u0001\u0000\u0000\u0000z\u044d\u0001\u0000\u0000\u0000|\u044f\u0001\u0000"+
		"\u0000\u0000~\u045b\u0001\u0000\u0000\u0000\u0080\u0460\u0001\u0000\u0000"+
		"\u0000\u0082\u0483\u0001\u0000\u0000\u0000\u0084\u0485\u0001\u0000\u0000"+
		"\u0000\u0086\u048d\u0001\u0000\u0000\u0000\u0088\u049c\u0001\u0000\u0000"+
		"\u0000\u008a\u04a2\u0001\u0000\u0000\u0000\u008c\u04a4\u0001\u0000\u0000"+
		"\u0000\u008e\u04a6\u0001\u0000\u0000\u0000\u0090\u04ab\u0001\u0000\u0000"+
		"\u0000\u0092\u04ae\u0001\u0000\u0000\u0000\u0094\u0095\u0005\u00ac\u0000"+
		"\u0000\u0095\u0096\u0005\u00ab\u0000\u0000\u0096\u0097\u0003X,\u0000\u0097"+
		"\u009a\u0005\u0095\u0000\u0000\u0098\u009b\u0003\u001a\r\u0000\u0099\u009b"+
		"\u0003\u0002\u0001\u0000\u009a\u0098\u0001\u0000\u0000\u0000\u009a\u0099"+
		"\u0001\u0000\u0000\u0000\u009b\u00a3\u0001\u0000\u0000\u0000\u009c\u009f"+
		"\u0005\u009c\u0000\u0000\u009d\u00a0\u0003\u001a\r\u0000\u009e\u00a0\u0003"+
		"\u0002\u0001\u0000\u009f\u009d\u0001\u0000\u0000\u0000\u009f\u009e\u0001"+
		"\u0000\u0000\u0000\u00a0\u00a2\u0001\u0000\u0000\u0000\u00a1\u009c\u0001"+
		"\u0000\u0000\u0000\u00a2\u00a5\u0001\u0000\u0000\u0000\u00a3\u00a1\u0001"+
		"\u0000\u0000\u0000\u00a3\u00a4\u0001\u0000\u0000\u0000\u00a4\u00a6\u0001"+
		"\u0000\u0000\u0000\u00a5\u00a3\u0001\u0000\u0000\u0000\u00a6\u00a8\u0005"+
		"\u0096\u0000\u0000\u00a7\u00a9\u0003`0\u0000\u00a8\u00a7\u0001\u0000\u0000"+
		"\u0000\u00a8\u00a9\u0001\u0000\u0000\u0000\u00a9\u00aa\u0001\u0000\u0000"+
		"\u0000\u00aa\u00ab\u0005\u0099\u0000\u0000\u00ab\u00b5\u0001\u0000\u0000"+
		"\u0000\u00ac\u00ad\u0005\u00ac\u0000\u0000\u00ad\u00ae\u0005\u00ab\u0000"+
		"\u0000\u00ae\u00af\u0003X,\u0000\u00af\u00b0\u0005\u0001\u0000\u0000\u00b0"+
		"\u00b1\u0003X,\u0000\u00b1\u00b2\u0003f3\u0000\u00b2\u00b3\u0005\u0099"+
		"\u0000\u0000\u00b3\u00b5\u0001\u0000\u0000\u0000\u00b4\u0094\u0001\u0000"+
		"\u0000\u0000\u00b4\u00ac\u0001\u0000\u0000\u0000\u00b5\u0001\u0001\u0000"+
		"\u0000\u0000\u00b6\u00b7\u0003Z-\u0000\u00b7\u00b9\u0003\u0010\b\u0000"+
		"\u00b8\u00ba\u0003\u0004\u0002\u0000\u00b9\u00b8\u0001\u0000\u0000\u0000"+
		"\u00b9\u00ba\u0001\u0000\u0000\u0000\u00ba\u00be\u0001\u0000\u0000\u0000"+
		"\u00bb\u00bd\u0003\u0018\f\u0000\u00bc\u00bb\u0001\u0000\u0000\u0000\u00bd"+
		"\u00c0\u0001\u0000\u0000\u0000\u00be\u00bc\u0001\u0000\u0000\u0000\u00be"+
		"\u00bf\u0001\u0000\u0000\u0000\u00bf\u00c3\u0001\u0000\u0000\u0000\u00c0"+
		"\u00be\u0001\u0000\u0000\u0000\u00c1\u00c4\u0003\u0006\u0003\u0000\u00c2"+
		"\u00c4\u0003\b\u0004\u0000\u00c3\u00c1\u0001\u0000\u0000\u0000\u00c3\u00c2"+
		"\u0001\u0000\u0000\u0000\u00c3\u00c4\u0001\u0000\u0000\u0000\u00c4\u00c6"+
		"\u0001\u0000\u0000\u0000\u00c5\u00c7\u0003\u0016\u000b\u0000\u00c6\u00c5"+
		"\u0001\u0000\u0000\u0000\u00c6\u00c7\u0001\u0000\u0000\u0000\u00c7\u00dc"+
		"\u0001\u0000\u0000\u0000\u00c8\u00c9\u0005\u00cd\u0000\u0000\u00c9\u00ca"+
		"\u0005\u0095\u0000\u0000\u00ca\u00cb\u0003P(\u0000\u00cb\u00cc\u0005\u0096"+
		"\u0000\u0000\u00cc\u00cd\u0005\u00b6\u0000\u0000\u00cd\u00ce\u0003X,\u0000"+
		"\u00ce\u00cf\u0005\u0095\u0000\u0000\u00cf\u00d0\u0003P(\u0000\u00d0\u00d3"+
		"\u0005\u0096\u0000\u0000\u00d1\u00d2\u0005\u00ad\u0000\u0000\u00d2\u00d4"+
		"\u0003\\.\u0000\u00d3\u00d1\u0001\u0000\u0000\u0000\u00d3\u00d4\u0001"+
		"\u0000\u0000\u0000\u00d4\u00d8\u0001\u0000\u0000\u0000\u00d5\u00d7\u0003"+
		" \u0010\u0000\u00d6\u00d5\u0001\u0000\u0000\u0000\u00d7\u00da\u0001\u0000"+
		"\u0000\u0000\u00d8\u00d6\u0001\u0000\u0000\u0000\u00d8\u00d9\u0001\u0000"+
		"\u0000\u0000\u00d9\u00dc\u0001\u0000\u0000\u0000\u00da\u00d8\u0001\u0000"+
		"\u0000\u0000\u00db\u00b6\u0001\u0000\u0000\u0000\u00db\u00c8\u0001\u0000"+
		"\u0000\u0000\u00dc\u0003\u0001\u0000\u0000\u0000\u00dd\u00de\u0005\u0095"+
		"\u0000\u0000\u00de\u00e1\u0005\u00da\u0000\u0000\u00df\u00e0\u0005\u009c"+
		"\u0000\u0000\u00e0\u00e2\u0005\u00da\u0000\u0000\u00e1\u00df\u0001\u0000"+
		"\u0000\u0000\u00e1\u00e2\u0001\u0000\u0000\u0000\u00e2\u00e3\u0001\u0000"+
		"\u0000\u0000\u00e3\u00e4\u0005\u0096\u0000\u0000\u00e4\u0005\u0001\u0000"+
		"\u0000\u0000\u00e5\u00e6\u0005\u0002\u0000\u0000\u00e6\u00e7\u0007\u0000"+
		"\u0000\u0000\u00e7\u00e8\u0005\u00a2\u0000\u0000\u00e8\u00e9\u0005\u0095"+
		"\u0000\u0000\u00e9\u00ea\u0003n7\u0000\u00ea\u00eb\u0005\u0096\u0000\u0000"+
		"\u00eb\u00ec\u0005\u0005\u0000\u0000\u00ec\u0007\u0001\u0000\u0000\u0000"+
		"\u00ed\u00ee\u0005\u0006\u0000\u0000\u00ee\u00ef\u0005\u00d9\u0000\u0000"+
		"\u00ef\t\u0001\u0000\u0000\u0000\u00f0\u00f1\u0005\u0007\u0000\u0000\u00f1"+
		"\u00f2\u0005\u00ab\u0000\u0000\u00f2\u00f3\u0003X,\u0000\u00f3\u00f8\u0003"+
		"\f\u0006\u0000\u00f4\u00f5\u0005\u009c\u0000\u0000\u00f5\u00f7\u0003\f"+
		"\u0006\u0000\u00f6\u00f4\u0001\u0000\u0000\u0000\u00f7\u00fa\u0001\u0000"+
		"\u0000\u0000\u00f8\u00f6\u0001\u0000\u0000\u0000\u00f8\u00f9\u0001\u0000"+
		"\u0000\u0000\u00f9\u00fc\u0001\u0000\u0000\u0000\u00fa\u00f8\u0001\u0000"+
		"\u0000\u0000\u00fb\u00fd\u0005\u0099\u0000\u0000\u00fc\u00fb\u0001\u0000"+
		"\u0000\u0000\u00fc\u00fd\u0001\u0000\u0000\u0000\u00fd\u000b\u0001\u0000"+
		"\u0000\u0000\u00fe\u00ff\u0005\b\u0000\u0000\u00ff\u0100\u0005\t\u0000"+
		"\u0000\u0100\u0140\u0003\u0002\u0001\u0000\u0101\u0102\u0005\n\u0000\u0000"+
		"\u0102\u0103\u0005\t\u0000\u0000\u0103\u0140\u0003Z-\u0000\u0104\u0107"+
		"\u0005\b\u0000\u0000\u0105\u0106\u0005\u00b2\u0000\u0000\u0106\u0108\u0005"+
		"\u00d8\u0000\u0000\u0107\u0105\u0001\u0000\u0000\u0000\u0107\u0108\u0001"+
		"\u0000\u0000\u0000\u0108\u0109\u0001\u0000\u0000\u0000\u0109\u010a\u0005"+
		"\u00cd\u0000\u0000\u010a\u010b\u0005\u0095\u0000\u0000\u010b\u010c\u0003"+
		"P(\u0000\u010c\u010d\u0005\u0096\u0000\u0000\u010d\u010e\u0005\u00b6\u0000"+
		"\u0000\u010e\u0113\u0003X,\u0000\u010f\u0110\u0005\u0095\u0000\u0000\u0110"+
		"\u0111\u0003P(\u0000\u0111\u0112\u0005\u0096\u0000\u0000\u0112\u0114\u0001"+
		"\u0000\u0000\u0000\u0113\u010f\u0001\u0000\u0000\u0000\u0113\u0114\u0001"+
		"\u0000\u0000\u0000\u0114\u0118\u0001\u0000\u0000\u0000\u0115\u0117\u0003"+
		" \u0010\u0000\u0116\u0115\u0001\u0000\u0000\u0000\u0117\u011a\u0001\u0000"+
		"\u0000\u0000\u0118\u0116\u0001\u0000\u0000\u0000\u0118\u0119\u0001\u0000"+
		"\u0000\u0000\u0119\u0140\u0001\u0000\u0000\u0000\u011a\u0118\u0001\u0000"+
		"\u0000\u0000\u011b\u011c\u0005\n\u0000\u0000\u011c\u011d\u0005\u00b2\u0000"+
		"\u0000\u011d\u0140\u0005\u00d8\u0000\u0000\u011e\u011f\u0005\u0007\u0000"+
		"\u0000\u011f\u0120\u0005\t\u0000\u0000\u0120\u0121\u0003Z-\u0000\u0121"+
		"\u0122\u0003\u000e\u0007\u0000\u0122\u0140\u0001\u0000\u0000\u0000\u0123"+
		"\u0124\u0005\u000b\u0000\u0000\u0124\u0125\u0003Z-\u0000\u0125\u0126\u0005"+
		"\u00a3\u0000\u0000\u0126\u0127\u0003Z-\u0000\u0127\u0140\u0001\u0000\u0000"+
		"\u0000\u0128\u0129\u0005\f\u0000\u0000\u0129\u0140\u0003X,\u0000\u012a"+
		"\u012f\u0005\u00b5\u0000\u0000\u012b\u012c\u0005\r\u0000\u0000\u012c\u0130"+
		"\u0003V+\u0000\u012d\u012e\u0005\u000e\u0000\u0000\u012e\u0130\u0005\u00d8"+
		"\u0000\u0000\u012f\u012b\u0001\u0000\u0000\u0000\u012f\u012d\u0001\u0000"+
		"\u0000\u0000\u0130\u0140\u0001\u0000\u0000\u0000\u0131\u0132\u0005\u000f"+
		"\u0000\u0000\u0132\u0133\u0005\u0010\u0000\u0000\u0133\u0134\u0005\u00cf"+
		"\u0000\u0000\u0134\u0140\u0005\u00d8\u0000\u0000\u0135\u0136\u0005\u0011"+
		"\u0000\u0000\u0136\u0137\u0005\u0010\u0000\u0000\u0137\u0138\u0005\u00cf"+
		"\u0000\u0000\u0138\u0140\u0005\u00d8\u0000\u0000\u0139\u013a\u0005\u0012"+
		"\u0000\u0000\u013a\u013b\u0005\u009e\u0000\u0000\u013b\u0140\u0005\u00d8"+
		"\u0000\u0000\u013c\u0140\u0005\u0013\u0000\u0000\u013d\u013e\u0005\u0014"+
		"\u0000\u0000\u013e\u0140\u0005\u00d8\u0000\u0000\u013f\u00fe\u0001\u0000"+
		"\u0000\u0000\u013f\u0101\u0001\u0000\u0000\u0000\u013f\u0104\u0001\u0000"+
		"\u0000\u0000\u013f\u011b\u0001\u0000\u0000\u0000\u013f\u011e\u0001\u0000"+
		"\u0000\u0000\u013f\u0123\u0001\u0000\u0000\u0000\u013f\u0128\u0001\u0000"+
		"\u0000\u0000\u013f\u012a\u0001\u0000\u0000\u0000\u013f\u0131\u0001\u0000"+
		"\u0000\u0000\u013f\u0135\u0001\u0000\u0000\u0000\u013f\u0139\u0001\u0000"+
		"\u0000\u0000\u013f\u013c\u0001\u0000\u0000\u0000\u013f\u013d\u0001\u0000"+
		"\u0000\u0000\u0140\r\u0001\u0000\u0000\u0000\u0141\u0142\u0005\u00c3\u0000"+
		"\u0000\u0142\u0151\u0003p8\u0000\u0143\u0151\u0005\u0015\u0000\u0000\u0144"+
		"\u0151\u0005\u0016\u0000\u0000\u0145\u0151\u0005\u0017\u0000\u0000\u0146"+
		"\u0147\u0005\u0018\u0000\u0000\u0147\u0151\u0005\u00da\u0000\u0000\u0148"+
		"\u0149\u0005\u0019\u0000\u0000\u0149\u0151\u0007\u0001\u0000\u0000\u014a"+
		"\u014b\u0005\u001e\u0000\u0000\u014b\u0151\u0005\u00d8\u0000\u0000\u014c"+
		"\u014d\u0005\u001f\u0000\u0000\u014d\u0151\u0007\u0002\u0000\u0000\u014e"+
		"\u014f\u0005#\u0000\u0000\u014f\u0151\u0003\u0010\b\u0000\u0150\u0141"+
		"\u0001\u0000\u0000\u0000\u0150\u0143\u0001\u0000\u0000\u0000\u0150\u0144"+
		"\u0001\u0000\u0000\u0000\u0150\u0145\u0001\u0000\u0000\u0000\u0150\u0146"+
		"\u0001\u0000\u0000\u0000\u0150\u0148\u0001\u0000\u0000\u0000\u0150\u014a"+
		"\u0001\u0000\u0000\u0000\u0150\u014c\u0001\u0000\u0000\u0000\u0150\u014e"+
		"\u0001\u0000\u0000\u0000\u0151\u0152\u0001\u0000\u0000\u0000\u0152\u0150"+
		"\u0001\u0000\u0000\u0000\u0152\u0153\u0001\u0000\u0000\u0000\u0153\u000f"+
		"\u0001\u0000\u0000\u0000\u0154\u01b4\u0005\u00cb\u0000\u0000\u0155\u01b4"+
		"\u0005$\u0000\u0000\u0156\u01b4\u0005%\u0000\u0000\u0157\u01b4\u0005&"+
		"\u0000\u0000\u0158\u01b4\u0005\u00af\u0000\u0000\u0159\u01b4\u0005\'\u0000"+
		"\u0000\u015a\u01b4\u0005(\u0000\u0000\u015b\u01b4\u0005)\u0000\u0000\u015c"+
		"\u01b4\u0005\u00b7\u0000\u0000\u015d\u01b4\u0005*\u0000\u0000\u015e\u01b4"+
		"\u0005+\u0000\u0000\u015f\u01b4\u0005,\u0000\u0000\u0160\u0168\u0005-"+
		"\u0000\u0000\u0161\u0162\u0005\u0095\u0000\u0000\u0162\u0165\u0005\u00da"+
		"\u0000\u0000\u0163\u0164\u0005\u009c\u0000\u0000\u0164\u0166\u0005\u00da"+
		"\u0000\u0000\u0165\u0163\u0001\u0000\u0000\u0000\u0165\u0166\u0001\u0000"+
		"\u0000\u0000\u0166\u0167\u0001\u0000\u0000\u0000\u0167\u0169\u0005\u0096"+
		"\u0000\u0000\u0168\u0161\u0001\u0000\u0000\u0000\u0168\u0169\u0001\u0000"+
		"\u0000\u0000\u0169\u01b4\u0001\u0000\u0000\u0000\u016a\u01b4\u0003^/\u0000"+
		"\u016b\u01b4\u0005.\u0000\u0000\u016c\u0170\u0005/\u0000\u0000\u016d\u016e"+
		"\u0005\u0095\u0000\u0000\u016e\u016f\u0005\u00da\u0000\u0000\u016f\u0171"+
		"\u0005\u0096\u0000\u0000\u0170\u016d\u0001\u0000\u0000\u0000\u0170\u0171"+
		"\u0001\u0000\u0000\u0000\u0171\u01b4\u0001\u0000\u0000\u0000\u0172\u0176"+
		"\u0005\u00b0\u0000\u0000\u0173\u0174\u0005\u0095\u0000\u0000\u0174\u0175"+
		"\u0005\u00da\u0000\u0000\u0175\u0177\u0005\u0096\u0000\u0000\u0176\u0173"+
		"\u0001\u0000\u0000\u0000\u0176\u0177\u0001\u0000\u0000\u0000\u0177\u01b4"+
		"\u0001\u0000\u0000\u0000\u0178\u01b4\u0005\u00ca\u0000\u0000\u0179\u01b4"+
		"\u0005\u00cc\u0000\u0000\u017a\u01b4\u00050\u0000\u0000\u017b\u01b4\u0005"+
		"1\u0000\u0000\u017c\u01b4\u00052\u0000\u0000\u017d\u0184\u00053\u0000"+
		"\u0000\u017e\u017f\u00054\u0000\u0000\u017f\u0180\u00053\u0000\u0000\u0180"+
		"\u0185\u00055\u0000\u0000\u0181\u0182\u00056\u0000\u0000\u0182\u0183\u0005"+
		"3\u0000\u0000\u0183\u0185\u00055\u0000\u0000\u0184\u017e\u0001\u0000\u0000"+
		"\u0000\u0184\u0181\u0001\u0000\u0000\u0000\u0184\u0185\u0001\u0000\u0000"+
		"\u0000\u0185\u01b4\u0001\u0000\u0000\u0000\u0186\u018d\u00057\u0000\u0000"+
		"\u0187\u0188\u00054\u0000\u0000\u0188\u0189\u00053\u0000\u0000\u0189\u018e"+
		"\u00055\u0000\u0000\u018a\u018b\u00056\u0000\u0000\u018b\u018c\u00053"+
		"\u0000\u0000\u018c\u018e\u00055\u0000\u0000\u018d\u0187\u0001\u0000\u0000"+
		"\u0000\u018d\u018a\u0001\u0000\u0000\u0000\u018d\u018e\u0001\u0000\u0000"+
		"\u0000\u018e\u01b4\u0001\u0000\u0000\u0000\u018f\u01b4\u00058\u0000\u0000"+
		"\u0190\u01b4\u00059\u0000\u0000\u0191\u01b4\u0005\u00bd\u0000\u0000\u0192"+
		"\u01b4\u0005:\u0000\u0000\u0193\u0194\u0005;\u0000\u0000\u0194\u0195\u0005"+
		"\u0095\u0000\u0000\u0195\u019a\u0005\u00d9\u0000\u0000\u0196\u0197\u0005"+
		"\u009c\u0000\u0000\u0197\u0199\u0005\u00d9\u0000\u0000\u0198\u0196\u0001"+
		"\u0000\u0000\u0000\u0199\u019c\u0001\u0000\u0000\u0000\u019a\u0198\u0001"+
		"\u0000\u0000\u0000\u019a\u019b\u0001\u0000\u0000\u0000\u019b\u019d\u0001"+
		"\u0000\u0000\u0000\u019c\u019a\u0001\u0000\u0000\u0000\u019d\u01b4\u0005"+
		"\u0096\u0000\u0000\u019e\u01b4\u0005<\u0000\u0000\u019f\u01b4\u0005=\u0000"+
		"\u0000\u01a0\u01b4\u0005>\u0000\u0000\u01a1\u01b4\u0005?\u0000\u0000\u01a2"+
		"\u01b4\u0005@\u0000\u0000\u01a3\u01b4\u0005A\u0000\u0000\u01a4\u01b4\u0005"+
		"B\u0000\u0000\u01a5\u01a9\u0005C\u0000\u0000\u01a6\u01a7\u0005\u0095\u0000"+
		"\u0000\u01a7\u01a8\u0005\u00da\u0000\u0000\u01a8\u01aa\u0005\u0096\u0000"+
		"\u0000\u01a9\u01a6\u0001\u0000\u0000\u0000\u01a9\u01aa\u0001\u0000\u0000"+
		"\u0000\u01aa\u01b4\u0001\u0000\u0000\u0000\u01ab\u01af\u0005D\u0000\u0000"+
		"\u01ac\u01ad\u0005\u0095\u0000\u0000\u01ad\u01ae\u0005\u00da\u0000\u0000"+
		"\u01ae\u01b0\u0005\u0096\u0000\u0000\u01af\u01ac\u0001\u0000\u0000\u0000"+
		"\u01af\u01b0\u0001\u0000\u0000\u0000\u01b0\u01b4\u0001\u0000\u0000\u0000"+
		"\u01b1\u01b4\u0005\u00cf\u0000\u0000\u01b2\u01b4\u0005\u00d8\u0000\u0000"+
		"\u01b3\u0154\u0001\u0000\u0000\u0000\u01b3\u0155\u0001\u0000\u0000\u0000"+
		"\u01b3\u0156\u0001\u0000\u0000\u0000\u01b3\u0157\u0001\u0000\u0000\u0000"+
		"\u01b3\u0158\u0001\u0000\u0000\u0000\u01b3\u0159\u0001\u0000\u0000\u0000"+
		"\u01b3\u015a\u0001\u0000\u0000\u0000\u01b3\u015b\u0001\u0000\u0000\u0000"+
		"\u01b3\u015c\u0001\u0000\u0000\u0000\u01b3\u015d\u0001\u0000\u0000\u0000"+
		"\u01b3\u015e\u0001\u0000\u0000\u0000\u01b3\u015f\u0001\u0000\u0000\u0000"+
		"\u01b3\u0160\u0001\u0000\u0000\u0000\u01b3\u016a\u0001\u0000\u0000\u0000"+
		"\u01b3\u016b\u0001\u0000\u0000\u0000\u01b3\u016c\u0001\u0000\u0000\u0000"+
		"\u01b3\u0172\u0001\u0000\u0000\u0000\u01b3\u0178\u0001\u0000\u0000\u0000"+
		"\u01b3\u0179\u0001\u0000\u0000\u0000\u01b3\u017a\u0001\u0000\u0000\u0000"+
		"\u01b3\u017b\u0001\u0000\u0000\u0000\u01b3\u017c\u0001\u0000\u0000\u0000"+
		"\u01b3\u017d\u0001\u0000\u0000\u0000\u01b3\u0186\u0001\u0000\u0000\u0000"+
		"\u01b3\u018f\u0001\u0000\u0000\u0000\u01b3\u0190\u0001\u0000\u0000\u0000"+
		"\u01b3\u0191\u0001\u0000\u0000\u0000\u01b3\u0192\u0001\u0000\u0000\u0000"+
		"\u01b3\u0193\u0001\u0000\u0000\u0000\u01b3\u019e\u0001\u0000\u0000\u0000"+
		"\u01b3\u019f\u0001\u0000\u0000\u0000\u01b3\u01a0\u0001\u0000\u0000\u0000"+
		"\u01b3\u01a1\u0001\u0000\u0000\u0000\u01b3\u01a2\u0001\u0000\u0000\u0000"+
		"\u01b3\u01a3\u0001\u0000\u0000\u0000\u01b3\u01a4\u0001\u0000\u0000\u0000"+
		"\u01b3\u01a5\u0001\u0000\u0000\u0000\u01b3\u01ab\u0001\u0000\u0000\u0000"+
		"\u01b3\u01b1\u0001\u0000\u0000\u0000\u01b3\u01b2\u0001\u0000\u0000\u0000"+
		"\u01b4\u0011\u0001\u0000\u0000\u0000\u01b5\u01b6\u0007\u0003\u0000\u0000"+
		"\u01b6\u0013\u0001\u0000\u0000\u0000\u01b7\u01b8\u0007\u0003\u0000\u0000"+
		"\u01b8\u0015\u0001\u0000\u0000\u0000\u01b9\u01ba\u0005\u00c7\u0000\u0000"+
		"\u01ba\u01bb\u0005E\u0000\u0000\u01bb\u0017\u0001\u0000\u0000\u0000\u01bc"+
		"\u01f2\u0005\u00ce\u0000\u0000\u01bd\u01f2\u0005\u00b3\u0000\u0000\u01be"+
		"\u01c1\u0005\u00c8\u0000\u0000\u01bf\u01c0\u0005F\u0000\u0000\u01c0\u01c2"+
		"\u0003T*\u0000\u01c1\u01bf\u0001\u0000\u0000\u0000\u01c1\u01c2\u0001\u0000"+
		"\u0000\u0000\u01c2\u01f2\u0001\u0000\u0000\u0000\u01c3\u01c4\u0005\u00be"+
		"\u0000\u0000\u01c4\u01f2\u0003p8\u0000\u01c5\u01c6\u0005\u00b9\u0000\u0000"+
		"\u01c6\u01c7\u0005\u0095\u0000\u0000\u01c7\u01c8\u00032\u0019\u0000\u01c8"+
		"\u01c9\u0005\u0096\u0000\u0000\u01c9\u01f2\u0001\u0000\u0000\u0000\u01ca"+
		"\u01ce\u0005\u00b6\u0000\u0000\u01cb\u01cc\u0003V+\u0000\u01cc\u01cd\u0005"+
		"\u00b1\u0000\u0000\u01cd\u01cf\u0001\u0000\u0000\u0000\u01ce\u01cb\u0001"+
		"\u0000\u0000\u0000\u01ce\u01cf\u0001\u0000\u0000\u0000\u01cf\u01d0\u0001"+
		"\u0000\u0000\u0000\u01d0\u01d1\u0003X,\u0000\u01d1\u01d2\u0005\u0095\u0000"+
		"\u0000\u01d2\u01d3\u0003Z-\u0000\u01d3\u01d5\u0005\u0096\u0000\u0000\u01d4"+
		"\u01d6\u0003 \u0010\u0000\u01d5\u01d4\u0001\u0000\u0000\u0000\u01d5\u01d6"+
		"\u0001\u0000\u0000\u0000\u01d6\u01f2\u0001\u0000\u0000\u0000\u01d7\u01d8"+
		"\u0005\u00cd\u0000\u0000\u01d8\u01d9\u0003P(\u0000\u01d9\u01dd\u0005\u00b6"+
		"\u0000\u0000\u01da\u01db\u0003V+\u0000\u01db\u01dc\u0005\u00b1\u0000\u0000"+
		"\u01dc\u01de\u0001\u0000\u0000\u0000\u01dd\u01da\u0001\u0000\u0000\u0000"+
		"\u01dd\u01de\u0001\u0000\u0000\u0000\u01de\u01df\u0001\u0000\u0000\u0000"+
		"\u01df\u01e0\u0003X,\u0000\u01e0\u01e3\u0003P(\u0000\u01e1\u01e2\u0005"+
		"\u00ad\u0000\u0000\u01e2\u01e4\u0003\\.\u0000\u01e3\u01e1\u0001\u0000"+
		"\u0000\u0000\u01e3\u01e4\u0001\u0000\u0000\u0000\u01e4\u01f2\u0001\u0000"+
		"\u0000\u0000\u01e5\u01e6\u0005G\u0000\u0000\u01e6\u01e7\u0005\u00a8\u0000"+
		"\u0000\u01e7\u01e8\u0005\u00d8\u0000\u0000\u01e8\u01e9\u0005\u0095\u0000"+
		"\u0000\u01e9\u01ea\u0003\u001c\u000e\u0000\u01ea\u01ed\u0005\u0096\u0000"+
		"\u0000\u01eb\u01ec\u0005H\u0000\u0000\u01ec\u01ee\u00032\u0019\u0000\u01ed"+
		"\u01eb\u0001\u0000\u0000\u0000\u01ed\u01ee\u0001\u0000\u0000\u0000\u01ee"+
		"\u01f2\u0001\u0000\u0000\u0000\u01ef\u01f2\u0005I\u0000\u0000\u01f0\u01f2"+
		"\u0005\u00b8\u0000\u0000\u01f1\u01bc\u0001\u0000\u0000\u0000\u01f1\u01bd"+
		"\u0001\u0000\u0000\u0000\u01f1\u01be\u0001\u0000\u0000\u0000\u01f1\u01c3"+
		"\u0001\u0000\u0000\u0000\u01f1\u01c5\u0001\u0000\u0000\u0000\u01f1\u01ca"+
		"\u0001\u0000\u0000\u0000\u01f1\u01d7\u0001\u0000\u0000\u0000\u01f1\u01e5"+
		"\u0001\u0000\u0000\u0000\u01f1\u01ef\u0001\u0000\u0000\u0000\u01f1\u01f0"+
		"\u0001\u0000\u0000\u0000\u01f2\u0019\u0001\u0000\u0000\u0000\u01f3\u01f5"+
		"\u0005\u00b2\u0000\u0000\u01f4\u01f6\u0005\u00d8\u0000\u0000\u01f5\u01f4"+
		"\u0001\u0000\u0000\u0000\u01f5\u01f6\u0001\u0000\u0000\u0000\u01f6\u01f7"+
		"\u0001\u0000\u0000\u0000\u01f7\u01f8\u0005\u00b8\u0000\u0000\u01f8\u01f9"+
		"\u0005\u0095\u0000\u0000\u01f9\u01fe\u0003P(\u0000\u01fa\u01fb\u0005\u009c"+
		"\u0000\u0000\u01fb\u01fd\u0003Z-\u0000\u01fc\u01fa\u0001\u0000\u0000\u0000"+
		"\u01fd\u0200\u0001\u0000\u0000\u0000\u01fe\u01fc\u0001\u0000\u0000\u0000"+
		"\u01fe\u01ff\u0001\u0000\u0000\u0000\u01ff\u0201\u0001\u0000\u0000\u0000"+
		"\u0200\u01fe\u0001\u0000\u0000\u0000\u0201\u0202\u0005\u0096\u0000\u0000"+
		"\u0202\u022e\u0001\u0000\u0000\u0000\u0203\u0205\u0005\u00b2\u0000\u0000"+
		"\u0204\u0206\u0005\u00d8\u0000\u0000\u0205\u0204\u0001\u0000\u0000\u0000"+
		"\u0205\u0206\u0001\u0000\u0000\u0000\u0206\u0207\u0001\u0000\u0000\u0000"+
		"\u0207\u0208\u0005\u00cd\u0000\u0000\u0208\u0209\u0003P(\u0000\u0209\u020d"+
		"\u0005\u00b6\u0000\u0000\u020a\u020b\u0003V+\u0000\u020b\u020c\u0005\u00b1"+
		"\u0000\u0000\u020c\u020e\u0001\u0000\u0000\u0000\u020d\u020a\u0001\u0000"+
		"\u0000\u0000\u020d\u020e\u0001\u0000\u0000\u0000\u020e\u020f\u0001\u0000"+
		"\u0000\u0000\u020f\u0210\u0003X,\u0000\u0210\u0214\u0003P(\u0000\u0211"+
		"\u0213\u0003 \u0010\u0000\u0212\u0211\u0001\u0000\u0000\u0000\u0213\u0216"+
		"\u0001\u0000\u0000\u0000\u0214\u0212\u0001\u0000\u0000\u0000\u0214\u0215"+
		"\u0001\u0000\u0000\u0000\u0215\u022e\u0001\u0000\u0000\u0000\u0216\u0214"+
		"\u0001\u0000\u0000\u0000\u0217\u0219\u0005\u00b2\u0000\u0000\u0218\u021a"+
		"\u0005\u00d8\u0000\u0000\u0219\u0218\u0001\u0000\u0000\u0000\u0219\u021a"+
		"\u0001\u0000\u0000\u0000\u021a\u021b\u0001\u0000\u0000\u0000\u021b\u021c"+
		"\u0005\u00c8\u0000\u0000\u021c\u022e\u0003P(\u0000\u021d\u021f\u0005\u00b2"+
		"\u0000\u0000\u021e\u0220\u0005\u00d8\u0000\u0000\u021f\u021e\u0001\u0000"+
		"\u0000\u0000\u021f\u0220\u0001\u0000\u0000\u0000\u0220\u0221\u0001\u0000"+
		"\u0000\u0000\u0221\u0222\u0005\u00b9\u0000\u0000\u0222\u0223\u0005\u0095"+
		"\u0000\u0000\u0223\u0224\u00032\u0019\u0000\u0224\u0225\u0005\u0096\u0000"+
		"\u0000\u0225\u022e\u0001\u0000\u0000\u0000\u0226\u0227\u0005G\u0000\u0000"+
		"\u0227\u0228\u0005\u00a8\u0000\u0000\u0228\u0229\u0005\u00d8\u0000\u0000"+
		"\u0229\u022a\u0005\u0095\u0000\u0000\u022a\u022b\u0003\u001c\u000e\u0000"+
		"\u022b\u022c\u0005\u0096\u0000\u0000\u022c\u022e\u0001\u0000\u0000\u0000"+
		"\u022d\u01f3\u0001\u0000\u0000\u0000\u022d\u0203\u0001\u0000\u0000\u0000"+
		"\u022d\u0217\u0001\u0000\u0000\u0000\u022d\u021d\u0001\u0000\u0000\u0000"+
		"\u022d\u0226\u0001\u0000\u0000\u0000\u022e\u001b\u0001\u0000\u0000\u0000"+
		"\u022f\u0234\u0003\u001e\u000f\u0000\u0230\u0231\u0005\u009c\u0000\u0000"+
		"\u0231\u0233\u0003\u001e\u000f\u0000\u0232\u0230\u0001\u0000\u0000\u0000"+
		"\u0233\u0236\u0001\u0000\u0000\u0000\u0234\u0232\u0001\u0000\u0000\u0000"+
		"\u0234\u0235\u0001\u0000\u0000\u0000\u0235\u001d\u0001\u0000\u0000\u0000"+
		"\u0236\u0234\u0001\u0000\u0000\u0000\u0237\u0238\u0003Z-\u0000\u0238\u0239"+
		"\u00056\u0000\u0000\u0239\u023a\u00036\u001b\u0000\u023a\u001f\u0001\u0000"+
		"\u0000\u0000\u023b\u023c\u0005\u00c6\u0000\u0000\u023c\u0245\u0003\"\u0011"+
		"\u0000\u023d\u023e\u0005\u00c7\u0000\u0000\u023e\u0245\u0003\"\u0011\u0000"+
		"\u023f\u0242\u0005J\u0000\u0000\u0240\u0241\u0005K\u0000\u0000\u0241\u0243"+
		"\u0007\u0004\u0000\u0000\u0242\u0240\u0001\u0000\u0000\u0000\u0242\u0243"+
		"\u0001\u0000\u0000\u0000\u0243\u0245\u0001\u0000\u0000\u0000\u0244\u023b"+
		"\u0001\u0000\u0000\u0000\u0244\u023d\u0001\u0000\u0000\u0000\u0244\u023f"+
		"\u0001\u0000\u0000\u0000\u0245!\u0001\u0000\u0000\u0000\u0246\u0247\u0007"+
		"\u0005\u0000\u0000\u0247#\u0001\u0000\u0000\u0000\u0248\u0249\u0005\u00ac"+
		"\u0000\u0000\u0249\u024a\u0005\u00cf\u0000\u0000\u024a\u024b\u0005\u00d8"+
		"\u0000\u0000\u024b\u024c\u0003&\u0013\u0000\u024c\u024d\u0003(\u0014\u0000"+
		"\u024d\u024e\u0005\u009e\u0000\u0000\u024e\u024f\u0003X,\u0000\u024f\u0250"+
		"\u0005N\u0000\u0000\u0250\u0251\u0005O\u0000\u0000\u0251\u0252\u0005\u00d8"+
		"\u0000\u0000\u0252\u025b\u0005\u0095\u0000\u0000\u0253\u0258\u0003p8\u0000"+
		"\u0254\u0255\u0005\u009c\u0000\u0000\u0255\u0257\u0003p8\u0000\u0256\u0254"+
		"\u0001\u0000\u0000\u0000\u0257\u025a\u0001\u0000\u0000\u0000\u0258\u0256"+
		"\u0001\u0000\u0000\u0000\u0258\u0259\u0001\u0000\u0000\u0000\u0259\u025c"+
		"\u0001\u0000\u0000\u0000\u025a\u0258\u0001\u0000\u0000\u0000\u025b\u0253"+
		"\u0001\u0000\u0000\u0000\u025b\u025c\u0001\u0000\u0000\u0000\u025c\u025d"+
		"\u0001\u0000\u0000\u0000\u025d\u025e\u0005\u0096\u0000\u0000\u025e\u025f"+
		"\u0005\u0099\u0000\u0000\u025f%\u0001\u0000\u0000\u0000\u0260\u0261\u0007"+
		"\u0006\u0000\u0000\u0261\'\u0001\u0000\u0000\u0000\u0262\u0267\u0007\u0007"+
		"\u0000\u0000\u0263\u0264\u0005Q\u0000\u0000\u0264\u0266\u0007\u0007\u0000"+
		"\u0000\u0265\u0263\u0001\u0000\u0000\u0000\u0266\u0269\u0001\u0000\u0000"+
		"\u0000\u0267\u0265\u0001\u0000\u0000\u0000\u0267\u0268\u0001\u0000\u0000"+
		"\u0000\u0268)\u0001\u0000\u0000\u0000\u0269\u0267\u0001\u0000\u0000\u0000"+
		"\u026a\u026e\u0003,\u0016\u0000\u026b\u026e\u0003.\u0017\u0000\u026c\u026e"+
		"\u00030\u0018\u0000\u026d\u026a\u0001\u0000\u0000\u0000\u026d\u026b\u0001"+
		"\u0000\u0000\u0000\u026d\u026c\u0001\u0000\u0000\u0000\u026e+\u0001\u0000"+
		"\u0000\u0000\u026f\u0270\u0005R\u0000\u0000\u0270\u0271\u0003X,\u0000"+
		"\u0271\u0272\u0005\u0095\u0000\u0000\u0272\u0277\u0003Z-\u0000\u0273\u0274"+
		"\u0005\u009c\u0000\u0000\u0274\u0276\u0003Z-\u0000\u0275\u0273\u0001\u0000"+
		"\u0000\u0000\u0276\u0279\u0001\u0000\u0000\u0000\u0277\u0275\u0001\u0000"+
		"\u0000\u0000\u0277\u0278\u0001\u0000\u0000\u0000\u0278\u027a\u0001\u0000"+
		"\u0000\u0000\u0279\u0277\u0001\u0000\u0000\u0000\u027a\u027b\u0005\u0096"+
		"\u0000\u0000\u027b\u027c\u0005S\u0000\u0000\u027c\u027d\u0005\u0095\u0000"+
		"\u0000\u027d\u0282\u0003p8\u0000\u027e\u027f\u0005\u009c\u0000\u0000\u027f"+
		"\u0281\u0003p8\u0000\u0280\u027e\u0001\u0000\u0000\u0000\u0281\u0284\u0001"+
		"\u0000\u0000\u0000\u0282\u0280\u0001\u0000\u0000\u0000\u0282\u0283\u0001"+
		"\u0000\u0000\u0000\u0283\u0285\u0001\u0000\u0000\u0000\u0284\u0282\u0001"+
		"\u0000\u0000\u0000\u0285\u0286\u0005\u0096\u0000\u0000\u0286\u0287\u0005"+
		"\u0099\u0000\u0000\u0287-\u0001\u0000\u0000\u0000\u0288\u0289\u0005\u00d2"+
		"\u0000\u0000\u0289\u028a\u0003X,\u0000\u028a\u028b\u0005\u00b5\u0000\u0000"+
		"\u028b\u028c\u0003Z-\u0000\u028c\u028d\u0005\u00c9\u0000\u0000\u028d\u0295"+
		"\u0003p8\u0000\u028e\u028f\u0005\u009c\u0000\u0000\u028f\u0290\u0003Z"+
		"-\u0000\u0290\u0291\u0005\u00c9\u0000\u0000\u0291\u0292\u0003p8\u0000"+
		"\u0292\u0294\u0001\u0000\u0000\u0000\u0293\u028e\u0001\u0000\u0000\u0000"+
		"\u0294\u0297\u0001\u0000\u0000\u0000\u0295\u0293\u0001\u0000\u0000\u0000"+
		"\u0295\u0296\u0001\u0000\u0000\u0000\u0296\u029a\u0001\u0000\u0000\u0000"+
		"\u0297\u0295\u0001\u0000\u0000\u0000\u0298\u0299\u0005H\u0000\u0000\u0299"+
		"\u029b\u00032\u0019\u0000\u029a\u0298\u0001\u0000\u0000\u0000\u029a\u029b"+
		"\u0001\u0000\u0000\u0000\u029b\u029c\u0001\u0000\u0000\u0000\u029c\u029d"+
		"\u0005\u0099\u0000\u0000\u029d/\u0001\u0000\u0000\u0000\u029e\u029f\u0005"+
		"T\u0000\u0000\u029f\u02a2\u0003X,\u0000\u02a0\u02a1\u0005H\u0000\u0000"+
		"\u02a1\u02a3\u00032\u0019\u0000\u02a2\u02a0\u0001\u0000\u0000\u0000\u02a2"+
		"\u02a3\u0001\u0000\u0000\u0000\u02a3\u02a4\u0001\u0000\u0000\u0000\u02a4"+
		"\u02a5\u0005\u0099\u0000\u0000\u02a51\u0001\u0000\u0000\u0000\u02a6\u02a7"+
		"\u0006\u0019\uffff\uffff\u0000\u02a7\u02a8\u0003Z-\u0000\u02a8\u02a9\u0003"+
		"6\u001b\u0000\u02a9\u02aa\u0003p8\u0000\u02aa\u02d8\u0001\u0000\u0000"+
		"\u0000\u02ab\u02ac\u0003Z-\u0000\u02ac\u02ad\u0005U\u0000\u0000\u02ad"+
		"\u02b0\u0005\u0095\u0000\u0000\u02ae\u02b1\u0003p8\u0000\u02af\u02b1\u0003"+
		"4\u001a\u0000\u02b0\u02ae\u0001\u0000\u0000\u0000\u02b0\u02af\u0001\u0000"+
		"\u0000\u0000\u02b1\u02b9\u0001\u0000\u0000\u0000\u02b2\u02b5\u0005\u009c"+
		"\u0000\u0000\u02b3\u02b6\u0003p8\u0000\u02b4\u02b6\u00034\u001a\u0000"+
		"\u02b5\u02b3\u0001\u0000\u0000\u0000\u02b5\u02b4\u0001\u0000\u0000\u0000"+
		"\u02b6\u02b8\u0001\u0000\u0000\u0000\u02b7\u02b2\u0001\u0000\u0000\u0000"+
		"\u02b8\u02bb\u0001\u0000\u0000\u0000\u02b9\u02b7\u0001\u0000\u0000\u0000"+
		"\u02b9\u02ba\u0001\u0000\u0000\u0000\u02ba\u02bc\u0001\u0000\u0000\u0000"+
		"\u02bb\u02b9\u0001\u0000\u0000\u0000\u02bc\u02bd\u0005\u0096\u0000\u0000"+
		"\u02bd\u02d8\u0001\u0000\u0000\u0000\u02be\u02bf\u0003Z-\u0000\u02bf\u02c0"+
		"\u0005V\u0000\u0000\u02c0\u02c1\u0003p8\u0000\u02c1\u02c2\u0005W\u0000"+
		"\u0000\u02c2\u02c3\u0003p8\u0000\u02c3\u02d8\u0001\u0000\u0000\u0000\u02c4"+
		"\u02c5\u0003Z-\u0000\u02c5\u02c6\u0005X\u0000\u0000\u02c6\u02c7\u0005"+
		"\u00b3\u0000\u0000\u02c7\u02d8\u0001\u0000\u0000\u0000\u02c8\u02c9\u0003"+
		"Z-\u0000\u02c9\u02ca\u0005Y\u0000\u0000\u02ca\u02d8\u0001\u0000\u0000"+
		"\u0000\u02cb\u02cc\u0003Z-\u0000\u02cc\u02cd\u0005Z\u0000\u0000\u02cd"+
		"\u02ce\u0005\u00d9\u0000\u0000\u02ce\u02d8\u0001\u0000\u0000\u0000\u02cf"+
		"\u02d0\u0003Z-\u0000\u02d0\u02d1\u0005[\u0000\u0000\u02d1\u02d2\u0005"+
		"\u00d9\u0000\u0000\u02d2\u02d8\u0001\u0000\u0000\u0000\u02d3\u02d4\u0005"+
		"\u00b4\u0000\u0000\u02d4\u02d8\u00032\u0019\u0005\u02d5\u02d8\u0005\\"+
		"\u0000\u0000\u02d6\u02d8\u0005]\u0000\u0000\u02d7\u02a6\u0001\u0000\u0000"+
		"\u0000\u02d7\u02ab\u0001\u0000\u0000\u0000\u02d7\u02be\u0001\u0000\u0000"+
		"\u0000\u02d7\u02c4\u0001\u0000\u0000\u0000\u02d7\u02c8\u0001\u0000\u0000"+
		"\u0000\u02d7\u02cb\u0001\u0000\u0000\u0000\u02d7\u02cf\u0001\u0000\u0000"+
		"\u0000\u02d7\u02d3\u0001\u0000\u0000\u0000\u02d7\u02d5\u0001\u0000\u0000"+
		"\u0000\u02d7\u02d6\u0001\u0000\u0000\u0000\u02d8\u02e1\u0001\u0000\u0000"+
		"\u0000\u02d9\u02da\n\u0004\u0000\u0000\u02da\u02db\u0005W\u0000\u0000"+
		"\u02db\u02e0\u00032\u0019\u0005\u02dc\u02dd\n\u0003\u0000\u0000\u02dd"+
		"\u02de\u0005Q\u0000\u0000\u02de\u02e0\u00032\u0019\u0004\u02df\u02d9\u0001"+
		"\u0000\u0000\u0000\u02df\u02dc\u0001\u0000\u0000\u0000\u02e0\u02e3\u0001"+
		"\u0000\u0000\u0000\u02e1\u02df\u0001\u0000\u0000\u0000\u02e1\u02e2\u0001"+
		"\u0000\u0000\u0000\u02e23\u0001\u0000\u0000\u0000\u02e3\u02e1\u0001\u0000"+
		"\u0000\u0000\u02e4\u02e5\u0005\u0095\u0000\u0000\u02e5\u02e6\u0003:\u001d"+
		"\u0000\u02e6\u02e7\u0005\u0096\u0000\u0000\u02e75\u0001\u0000\u0000\u0000"+
		"\u02e8\u02e9\u0007\b\u0000\u0000\u02e97\u0001\u0000\u0000\u0000\u02ea"+
		"\u02eb\u0007\t\u0000\u0000\u02eb9\u0001\u0000\u0000\u0000\u02ec\u02f7"+
		"\u0003\u0000\u0000\u0000\u02ed\u02f7\u0003<\u001e\u0000\u02ee\u02f7\u0003"+
		">\u001f\u0000\u02ef\u02f7\u0003@ \u0000\u02f0\u02f7\u0003j5\u0000\u02f1"+
		"\u02f7\u0003|>\u0000\u02f2\u02f7\u0003$\u0012\u0000\u02f3\u02f7\u0003"+
		"\n\u0005\u0000\u02f4\u02f7\u0003*\u0015\u0000\u02f5\u02f7\u0003v;\u0000"+
		"\u02f6\u02ec\u0001\u0000\u0000\u0000\u02f6\u02ed\u0001\u0000\u0000\u0000"+
		"\u02f6\u02ee\u0001\u0000\u0000\u0000\u02f6\u02ef\u0001\u0000\u0000\u0000"+
		"\u02f6\u02f0\u0001\u0000\u0000\u0000\u02f6\u02f1\u0001\u0000\u0000\u0000"+
		"\u02f6\u02f2\u0001\u0000\u0000\u0000\u02f6\u02f3\u0001\u0000\u0000\u0000"+
		"\u02f6\u02f4\u0001\u0000\u0000\u0000\u02f6\u02f5\u0001\u0000\u0000\u0000"+
		"\u02f7\u02fa\u0001\u0000\u0000\u0000\u02f8\u02f6\u0001\u0000\u0000\u0000"+
		"\u02f8\u02f9\u0001\u0000\u0000\u0000\u02f9\u02fb\u0001\u0000\u0000\u0000"+
		"\u02fa\u02f8\u0001\u0000\u0000\u0000\u02fb\u02fc\u0005\u0000\u0000\u0001"+
		"\u02fc;\u0001\u0000\u0000\u0000\u02fd\u02fe\u0005p\u0000\u0000\u02fe\u02ff"+
		"\u0005\u00d8\u0000\u0000\u02ff\u0300\u0005\u0099\u0000\u0000\u0300=\u0001"+
		"\u0000\u0000\u0000\u0301\u0302\u0005q\u0000\u0000\u0302\u0303\u0005\u00d8"+
		"\u0000\u0000\u0303\u0304\u0005\u00a2\u0000\u0000\u0304\u0305\u0003D\""+
		"\u0000\u0305\u0306\u0005\u0099\u0000\u0000\u0306?\u0001\u0000\u0000\u0000"+
		"\u0307\u0308\u0005r\u0000\u0000\u0308\u0309\u0005\u00d8\u0000\u0000\u0309"+
		"\u0312\u0005\u0095\u0000\u0000\u030a\u030f\u0003B!\u0000\u030b\u030c\u0005"+
		"\u009c\u0000\u0000\u030c\u030e\u0003B!\u0000\u030d\u030b\u0001\u0000\u0000"+
		"\u0000\u030e\u0311\u0001\u0000\u0000\u0000\u030f\u030d\u0001\u0000\u0000"+
		"\u0000\u030f\u0310\u0001\u0000\u0000\u0000\u0310\u0313\u0001\u0000\u0000"+
		"\u0000\u0311\u030f\u0001\u0000\u0000\u0000\u0312\u030a\u0001\u0000\u0000"+
		"\u0000\u0312\u0313\u0001\u0000\u0000\u0000\u0313\u0314\u0001\u0000\u0000"+
		"\u0000\u0314\u0315\u0005\u0096\u0000\u0000\u0315\u0316\u0005s\u0000\u0000"+
		"\u0316\u0317\u0003\u0010\b\u0000\u0317\u0318\u0005\u00a2\u0000\u0000\u0318"+
		"\u0319\u0005\u00a9\u0000\u0000\u0319\u031a\u0003x<\u0000\u031a\u031b\u0005"+
		"\u00a9\u0000\u0000\u031b\u031c\u0005t\u0000\u0000\u031c\u031d\u0005\u00d8"+
		"\u0000\u0000\u031d\u031e\u0005\u0099\u0000\u0000\u031eA\u0001\u0000\u0000"+
		"\u0000\u031f\u0320\u0005\u00d8\u0000\u0000\u0320\u0321\u0003\u0010\b\u0000"+
		"\u0321C\u0001\u0000\u0000\u0000\u0322\u0323\u0005\u00a7\u0000\u0000\u0323"+
		"\u032d\u0003F#\u0000\u0324\u0325\u0005u\u0000\u0000\u0325\u032a\u0003"+
		"H$\u0000\u0326\u0327\u0005\u009c\u0000\u0000\u0327\u0329\u0003H$\u0000"+
		"\u0328\u0326\u0001\u0000\u0000\u0000\u0329\u032c\u0001\u0000\u0000\u0000"+
		"\u032a\u0328\u0001\u0000\u0000\u0000\u032a\u032b\u0001\u0000\u0000\u0000"+
		"\u032b\u032e\u0001\u0000\u0000\u0000\u032c\u032a\u0001\u0000\u0000\u0000"+
		"\u032d\u0324\u0001\u0000\u0000\u0000\u032d\u032e\u0001\u0000\u0000\u0000"+
		"\u032e\u0330\u0001\u0000\u0000\u0000\u032f\u0331\u0003J%\u0000\u0330\u032f"+
		"\u0001\u0000\u0000\u0000\u0330\u0331\u0001\u0000\u0000\u0000\u0331\u0333"+
		"\u0001\u0000\u0000\u0000\u0332\u0334\u0003L&\u0000\u0333\u0332\u0001\u0000"+
		"\u0000\u0000\u0333\u0334\u0001\u0000\u0000\u0000\u0334\u0336\u0001\u0000"+
		"\u0000\u0000\u0335\u0337\u0003N\'\u0000\u0336\u0335\u0001\u0000\u0000"+
		"\u0000\u0336\u0337\u0001\u0000\u0000\u0000\u0337E\u0001\u0000\u0000\u0000"+
		"\u0338\u0342\u0005l\u0000\u0000\u0339\u033e\u0003Z-\u0000\u033a\u033b"+
		"\u0005\u009c\u0000\u0000\u033b\u033d\u0003Z-\u0000\u033c\u033a\u0001\u0000"+
		"\u0000\u0000\u033d\u0340\u0001\u0000\u0000\u0000\u033e\u033c\u0001\u0000"+
		"\u0000\u0000\u033e\u033f\u0001\u0000\u0000\u0000\u033f\u0342\u0001\u0000"+
		"\u0000\u0000\u0340\u033e\u0001\u0000\u0000\u0000\u0341\u0338\u0001\u0000"+
		"\u0000\u0000\u0341\u0339\u0001\u0000\u0000\u0000\u0342G\u0001\u0000\u0000"+
		"\u0000\u0343\u0346\u0003X,\u0000\u0344\u0345\u0005\u00a2\u0000\u0000\u0345"+
		"\u0347\u0005\u00d8\u0000\u0000\u0346\u0344\u0001\u0000\u0000\u0000\u0346"+
		"\u0347\u0001\u0000\u0000\u0000\u0347I\u0001\u0000\u0000\u0000\u0348\u0349"+
		"\u0005H\u0000\u0000\u0349\u034a\u00032\u0019\u0000\u034aK\u0001\u0000"+
		"\u0000\u0000\u034b\u034c\u0005v\u0000\u0000\u034c\u034e\u0003Z-\u0000"+
		"\u034d\u034f\u0007\n\u0000\u0000\u034e\u034d\u0001\u0000\u0000\u0000\u034e"+
		"\u034f\u0001\u0000\u0000\u0000\u034fM\u0001\u0000\u0000\u0000\u0350\u0351"+
		"\u0005y\u0000\u0000\u0351\u0352\u0005\u00da\u0000\u0000\u0352O\u0001\u0000"+
		"\u0000\u0000\u0353\u0358\u0003Z-\u0000\u0354\u0355\u0005\u009c\u0000\u0000"+
		"\u0355\u0357\u0003Z-\u0000\u0356\u0354\u0001\u0000\u0000\u0000\u0357\u035a"+
		"\u0001\u0000\u0000\u0000\u0358\u0356\u0001\u0000\u0000\u0000\u0358\u0359"+
		"\u0001\u0000\u0000\u0000\u0359Q\u0001\u0000\u0000\u0000\u035a\u0358\u0001"+
		"\u0000\u0000\u0000\u035b\u035d\u0003V+\u0000\u035c\u035b\u0001\u0000\u0000"+
		"\u0000\u035c\u035d\u0001\u0000\u0000\u0000\u035d\u035e\u0001\u0000\u0000"+
		"\u0000\u035e\u035f\u0005\u00b1\u0000\u0000\u035f\u0362\u0003X,\u0000\u0360"+
		"\u0362\u0003X,\u0000\u0361\u035c\u0001\u0000\u0000\u0000\u0361\u0360\u0001"+
		"\u0000\u0000\u0000\u0362\u0363\u0001\u0000\u0000\u0000\u0363\u0364\u0005"+
		"\u0095\u0000\u0000\u0364\u0369\u0003Z-\u0000\u0365\u0366\u0005\u009c\u0000"+
		"\u0000\u0366\u0368\u0003Z-\u0000\u0367\u0365\u0001\u0000\u0000\u0000\u0368"+
		"\u036b\u0001\u0000\u0000\u0000\u0369\u0367\u0001\u0000\u0000\u0000\u0369"+
		"\u036a\u0001\u0000\u0000\u0000\u036a\u036c\u0001\u0000\u0000\u0000\u036b"+
		"\u0369\u0001\u0000\u0000\u0000\u036c\u036d\u0005\u0096\u0000\u0000\u036d"+
		"S\u0001\u0000\u0000\u0000\u036e\u0379\u0005z\u0000\u0000\u036f\u0370\u0005"+
		"{\u0000\u0000\u0370\u0371\u0005\u00b5\u0000\u0000\u0371\u0372\u0003Z-"+
		"\u0000\u0372\u0373\u0005\u00c9\u0000\u0000\u0373\u0376\u0003p8\u0000\u0374"+
		"\u0375\u0005H\u0000\u0000\u0375\u0377\u00032\u0019\u0000\u0376\u0374\u0001"+
		"\u0000\u0000\u0000\u0376\u0377\u0001\u0000\u0000\u0000\u0377\u0379\u0001"+
		"\u0000\u0000\u0000\u0378\u036e\u0001\u0000\u0000\u0000\u0378\u036f\u0001"+
		"\u0000\u0000\u0000\u0379U\u0001\u0000\u0000\u0000\u037a\u037b\u0005\u00d8"+
		"\u0000\u0000\u037bW\u0001\u0000\u0000\u0000\u037c\u037d\u0003V+\u0000"+
		"\u037d\u037e\u0005\u00b1\u0000\u0000\u037e\u0380\u0001\u0000\u0000\u0000"+
		"\u037f\u037c\u0001\u0000\u0000\u0000\u037f\u0380\u0001\u0000\u0000\u0000"+
		"\u0380\u0381\u0001\u0000\u0000\u0000\u0381\u0382\u0005\u00d8\u0000\u0000"+
		"\u0382Y\u0001\u0000\u0000\u0000\u0383\u0384\u0005\u00d8\u0000\u0000\u0384"+
		"[\u0001\u0000\u0000\u0000\u0385\u0386\u0007\u000b\u0000\u0000\u0386]\u0001"+
		"\u0000\u0000\u0000\u0387\u038f\u0007\f\u0000\u0000\u0388\u0389\u0005\u0095"+
		"\u0000\u0000\u0389\u038c\u0005\u00da\u0000\u0000\u038a\u038b\u0005\u009c"+
		"\u0000\u0000\u038b\u038d\u0005\u00da\u0000\u0000\u038c\u038a\u0001\u0000"+
		"\u0000\u0000\u038c\u038d\u0001\u0000\u0000\u0000\u038d\u038e\u0001\u0000"+
		"\u0000\u0000\u038e\u0390\u0005\u0096\u0000\u0000\u038f\u0388\u0001\u0000"+
		"\u0000\u0000\u038f\u0390\u0001\u0000\u0000\u0000\u0390_\u0001\u0000\u0000"+
		"\u0000\u0391\u0392\u0005\u0080\u0000\u0000\u0392\u0393\u0003b1\u0000\u0393"+
		"\u0394\u0005\u0095\u0000\u0000\u0394\u0395\u0003Z-\u0000\u0395\u0396\u0005"+
		"\u0096\u0000\u0000\u0396a\u0001\u0000\u0000\u0000\u0397\u0398\u0007\r"+
		"\u0000\u0000\u0398c\u0001\u0000\u0000\u0000\u0399\u039a\u0005\u00ac\u0000"+
		"\u0000\u039a\u039b\u0005\u00ab\u0000\u0000\u039b\u039c\u0003X,\u0000\u039c"+
		"\u039d\u0005\u0001\u0000\u0000\u039d\u039e\u0003X,\u0000\u039e\u039f\u0003"+
		"f3\u0000\u039f\u03a0\u0005\u0099\u0000\u0000\u03a0e\u0001\u0000\u0000"+
		"\u0000\u03a1\u03bb\u0005\u0084\u0000\u0000\u03a2\u03a3\u0005u\u0000\u0000"+
		"\u03a3\u03a4\u0005\u0095\u0000\u0000\u03a4\u03a5\u0003p8\u0000\u03a5\u03a6"+
		"\u0005\u0096\u0000\u0000\u03a6\u03a7\u0005\u00a3\u0000\u0000\u03a7\u03a8"+
		"\u0005\u0095\u0000\u0000\u03a8\u03a9\u0003p8\u0000\u03a9\u03aa\u0005\u0096"+
		"\u0000\u0000\u03aa\u03bc\u0001\u0000\u0000\u0000\u03ab\u03ac\u0005U\u0000"+
		"\u0000\u03ac\u03ad\u0005\u0095\u0000\u0000\u03ad\u03b2\u0003p8\u0000\u03ae"+
		"\u03af\u0005\u009c\u0000\u0000\u03af\u03b1\u0003p8\u0000\u03b0\u03ae\u0001"+
		"\u0000\u0000\u0000\u03b1\u03b4\u0001\u0000\u0000\u0000\u03b2\u03b0\u0001"+
		"\u0000\u0000\u0000\u03b2\u03b3\u0001\u0000\u0000\u0000\u03b3\u03b5\u0001"+
		"\u0000\u0000\u0000\u03b4\u03b2\u0001\u0000\u0000\u0000\u03b5\u03b6\u0005"+
		"\u0096\u0000\u0000\u03b6\u03bc\u0001\u0000\u0000\u0000\u03b7\u03b8\u0005"+
		"\u0085\u0000\u0000\u03b8\u03b9\u0005\u00da\u0000\u0000\u03b9\u03ba\u0005"+
		"\u0086\u0000\u0000\u03ba\u03bc\u0005\u00da\u0000\u0000\u03bb\u03a2\u0001"+
		"\u0000\u0000\u0000\u03bb\u03ab\u0001\u0000\u0000\u0000\u03bb\u03b7\u0001"+
		"\u0000\u0000\u0000\u03bcg\u0001\u0000\u0000\u0000\u03bd\u03be\u0005\u0001"+
		"\u0000\u0000\u03be\u03bf\u0003X,\u0000\u03bf\u03c0\u0003f3\u0000\u03c0"+
		"i\u0001\u0000\u0000\u0000\u03c1\u03c2\u0005\u00ac\u0000\u0000\u03c2\u03c3"+
		"\u0005\u0087\u0000\u0000\u03c3\u03c4\u0005\u00d8\u0000\u0000\u03c4\u03c5"+
		"\u0005\u009e\u0000\u0000\u03c5\u03c6\u0003X,\u0000\u03c6\u03c7\u0005\u0095"+
		"\u0000\u0000\u03c7\u03cc\u0003Z-\u0000\u03c8\u03c9\u0005\u009c\u0000\u0000"+
		"\u03c9\u03cb\u0003Z-\u0000\u03ca\u03c8\u0001\u0000\u0000\u0000\u03cb\u03ce"+
		"\u0001\u0000\u0000\u0000\u03cc\u03ca\u0001\u0000\u0000\u0000\u03cc\u03cd"+
		"\u0001\u0000\u0000\u0000\u03cd\u03cf\u0001\u0000\u0000\u0000\u03ce\u03cc"+
		"\u0001\u0000\u0000\u0000\u03cf\u03d1\u0005\u0096\u0000\u0000\u03d0\u03d2"+
		"\u0003l6\u0000\u03d1\u03d0\u0001\u0000\u0000\u0000\u03d1\u03d2\u0001\u0000"+
		"\u0000\u0000\u03d2\u03d3\u0001\u0000\u0000\u0000\u03d3\u03d4\u0005\u0099"+
		"\u0000\u0000\u03d4k\u0001\u0000\u0000\u0000\u03d5\u03d6\u0005\u000e\u0000"+
		"\u0000\u03d6\u03d7\u0005\u00d8\u0000\u0000\u03d7m\u0001\u0000\u0000\u0000"+
		"\u03d8\u03d9\u00067\uffff\uffff\u0000\u03d9\u03e2\u0003p8\u0000\u03da"+
		"\u03e2\u0003Z-\u0000\u03db\u03e2\u0003\u008eG\u0000\u03dc\u03e2\u0003"+
		"t:\u0000\u03dd\u03de\u0005\u0095\u0000\u0000\u03de\u03df\u0003n7\u0000"+
		"\u03df\u03e0\u0005\u0096\u0000\u0000\u03e0\u03e2\u0001\u0000\u0000\u0000"+
		"\u03e1\u03d8\u0001\u0000\u0000\u0000\u03e1\u03da\u0001\u0000\u0000\u0000"+
		"\u03e1\u03db\u0001\u0000\u0000\u0000\u03e1\u03dc\u0001\u0000\u0000\u0000"+
		"\u03e1\u03dd\u0001\u0000\u0000\u0000\u03e2\u03f8\u0001\u0000\u0000\u0000"+
		"\u03e3\u03e4\n\u0005\u0000\u0000\u03e4\u03e5\u0007\u000e\u0000\u0000\u03e5"+
		"\u03f7\u0003n7\u0006\u03e6\u03e7\n\u0004\u0000\u0000\u03e7\u03e8\u0003"+
		"6\u001b\u0000\u03e8\u03e9\u0003n7\u0005\u03e9\u03f7\u0001\u0000\u0000"+
		"\u0000\u03ea\u03eb\n\u0003\u0000\u0000\u03eb\u03ec\u0005W\u0000\u0000"+
		"\u03ec\u03f7\u0003n7\u0004\u03ed\u03ee\n\u0002\u0000\u0000\u03ee\u03ef"+
		"\u0005Q\u0000\u0000\u03ef\u03f7\u0003n7\u0003\u03f0\u03f1\n\u0001\u0000"+
		"\u0000\u03f1\u03f4\u0005\u009b\u0000\u0000\u03f2\u03f5\u0003\u0092I\u0000"+
		"\u03f3\u03f5\u0003\u0010\b\u0000\u03f4\u03f2\u0001\u0000\u0000\u0000\u03f4"+
		"\u03f3\u0001\u0000\u0000\u0000\u03f5\u03f7\u0001\u0000\u0000\u0000\u03f6"+
		"\u03e3\u0001\u0000\u0000\u0000\u03f6\u03e6\u0001\u0000\u0000\u0000\u03f6"+
		"\u03ea\u0001\u0000\u0000\u0000\u03f6\u03ed\u0001\u0000\u0000\u0000\u03f6"+
		"\u03f0\u0001\u0000\u0000\u0000\u03f7\u03fa\u0001\u0000\u0000\u0000\u03f8"+
		"\u03f6\u0001\u0000\u0000\u0000\u03f8\u03f9\u0001\u0000\u0000\u0000\u03f9"+
		"o\u0001\u0000\u0000\u0000\u03fa\u03f8\u0001\u0000\u0000\u0000\u03fb\u0408"+
		"\u0005\u00d9\u0000\u0000\u03fc\u0408\u0005\u00da\u0000\u0000\u03fd\u0408"+
		"\u0005\u0088\u0000\u0000\u03fe\u0408\u0005\u0089\u0000\u0000\u03ff\u0408"+
		"\u0005\u00b3\u0000\u0000\u0400\u0408\u0005E\u0000\u0000\u0401\u0408\u0005"+
		"\u008a\u0000\u0000\u0402\u0408\u0005\u008b\u0000\u0000\u0403\u0408\u0005"+
		"\u008c\u0000\u0000\u0404\u0408\u0005\u008d\u0000\u0000\u0405\u0408\u0003"+
		"\u0090H\u0000\u0406\u0408\u0003t:\u0000\u0407\u03fb\u0001\u0000\u0000"+
		"\u0000\u0407\u03fc\u0001\u0000\u0000\u0000\u0407\u03fd\u0001\u0000\u0000"+
		"\u0000\u0407\u03fe\u0001\u0000\u0000\u0000\u0407\u03ff\u0001\u0000\u0000"+
		"\u0000\u0407\u0400\u0001\u0000\u0000\u0000\u0407\u0401\u0001\u0000\u0000"+
		"\u0000\u0407\u0402\u0001\u0000\u0000\u0000\u0407\u0403\u0001\u0000\u0000"+
		"\u0000\u0407\u0404\u0001\u0000\u0000\u0000\u0407\u0405\u0001\u0000\u0000"+
		"\u0000\u0407\u0406\u0001\u0000\u0000\u0000\u0408q\u0001\u0000\u0000\u0000"+
		"\u0409\u040e\u0003n7\u0000\u040a\u040b\u0005\u009c\u0000\u0000\u040b\u040d"+
		"\u0003n7\u0000\u040c\u040a\u0001\u0000\u0000\u0000\u040d\u0410\u0001\u0000"+
		"\u0000\u0000\u040e\u040c\u0001\u0000\u0000\u0000\u040e\u040f\u0001\u0000"+
		"\u0000\u0000\u040fs\u0001\u0000\u0000\u0000\u0410\u040e\u0001\u0000\u0000"+
		"\u0000\u0411\u0412\u0005\u00bd\u0000\u0000\u0412\u0413\u0005\u00bb\u0000"+
		"\u0000\u0413\u0414\u0003r9\u0000\u0414\u0417\u0005\u00bc\u0000\u0000\u0415"+
		"\u0416\u0005\u009b\u0000\u0000\u0416\u0418\u0003\u0010\b\u0000\u0417\u0415"+
		"\u0001\u0000\u0000\u0000\u0417\u0418\u0001\u0000\u0000\u0000\u0418u\u0001"+
		"\u0000\u0000\u0000\u0419\u041a\u0005\u00ac\u0000\u0000\u041a\u041b\u0005"+
		"\u008e\u0000\u0000\u041b\u041c\u0005\u00d8\u0000\u0000\u041c\u041d\u0005"+
		"\u009e\u0000\u0000\u041d\u0420\u0003X,\u0000\u041e\u041f\u0005\u00a6\u0000"+
		"\u0000\u041f\u0421\u0007\u000f\u0000\u0000\u0420\u041e\u0001\u0000\u0000"+
		"\u0000\u0420\u0421\u0001\u0000\u0000\u0000\u0421\u0427\u0001\u0000\u0000"+
		"\u0000\u0422\u0423\u0005\u00a8\u0000\u0000\u0423\u0424\u0005\u0095\u0000"+
		"\u0000\u0424\u0425\u00032\u0019\u0000\u0425\u0426\u0005\u0096\u0000\u0000"+
		"\u0426\u0428\u0001\u0000\u0000\u0000\u0427\u0422\u0001\u0000\u0000\u0000"+
		"\u0427\u0428\u0001\u0000\u0000\u0000\u0428\u042e\u0001\u0000\u0000\u0000"+
		"\u0429\u042a\u0005\u0090\u0000\u0000\u042a\u042b\u0005\u0095\u0000\u0000"+
		"\u042b\u042c\u00032\u0019\u0000\u042c\u042d\u0005\u0096\u0000\u0000\u042d"+
		"\u042f\u0001\u0000\u0000\u0000\u042e\u0429\u0001\u0000\u0000\u0000\u042e"+
		"\u042f\u0001\u0000\u0000\u0000\u042f\u0430\u0001\u0000\u0000\u0000\u0430"+
		"\u0431\u0005\u0099\u0000\u0000\u0431w\u0001\u0000\u0000\u0000\u0432\u0436"+
		"\u0005\u0091\u0000\u0000\u0433\u0435\u0003z=\u0000\u0434\u0433\u0001\u0000"+
		"\u0000\u0000\u0435\u0438\u0001\u0000\u0000\u0000\u0436\u0434\u0001\u0000"+
		"\u0000\u0000\u0436\u0437\u0001\u0000\u0000\u0000\u0437\u0439\u0001\u0000"+
		"\u0000\u0000\u0438\u0436\u0001\u0000\u0000\u0000\u0439\u043a\u0005\u0092"+
		"\u0000\u0000\u043a\u043b\u0005\u0099\u0000\u0000\u043by\u0001\u0000\u0000"+
		"\u0000\u043c\u043d\u0003Z-\u0000\u043d\u043e\u0005\u00b1\u0000\u0000\u043e"+
		"\u043f\u0003Z-\u0000\u043f\u0440\u0005\u00c9\u0000\u0000\u0440\u0441\u0003"+
		"p8\u0000\u0441\u0442\u0005\u0099\u0000\u0000\u0442\u044e\u0001\u0000\u0000"+
		"\u0000\u0443\u0444\u0005\u0093\u0000\u0000\u0444\u0445\u0005\u00aa\u0000"+
		"\u0000\u0445\u044e\u0005\u0099\u0000\u0000\u0446\u0447\u0005\u00aa\u0000"+
		"\u0000\u0447\u0448\u0005\u00b1\u0000\u0000\u0448\u0449\u0003Z-\u0000\u0449"+
		"\u044a\u0005\u00c9\u0000\u0000\u044a\u044b\u0003p8\u0000\u044b\u044c\u0005"+
		"\u0099\u0000\u0000\u044c\u044e\u0001\u0000\u0000\u0000\u044d\u043c\u0001"+
		"\u0000\u0000\u0000\u044d\u0443\u0001\u0000\u0000\u0000\u044d\u0446\u0001"+
		"\u0000\u0000\u0000\u044e{\u0001\u0000\u0000\u0000\u044f\u0450\u0005\u00ac"+
		"\u0000\u0000\u0450\u0451\u0005\u00a0\u0000\u0000\u0451\u0452\u0005\u00d8"+
		"\u0000\u0000\u0452\u0453\u0005\u00a2\u0000\u0000\u0453\u0454\u0005\u009e"+
		"\u0000\u0000\u0454\u0455\u0003~?\u0000\u0455\u0456\u0005\u00a3\u0000\u0000"+
		"\u0456\u0457\u0003X,\u0000\u0457\u0458\u0005\u00a1\u0000\u0000\u0458\u0459"+
		"\u0003\u0080@\u0000\u0459\u045a\u0005\u0099\u0000\u0000\u045a}\u0001\u0000"+
		"\u0000\u0000\u045b\u045c\u0007\u0010\u0000\u0000\u045c\u007f\u0001\u0000"+
		"\u0000\u0000\u045d\u0461\u0005\u0094\u0000\u0000\u045e\u045f\u0005\u00a4"+
		"\u0000\u0000\u045f\u0461\u0003\u0082A\u0000\u0460\u045d\u0001\u0000\u0000"+
		"\u0000\u0460\u045e\u0001\u0000\u0000\u0000\u0461\u0081\u0001\u0000\u0000"+
		"\u0000\u0462\u0463\u0005R\u0000\u0000\u0463\u0464\u0003X,\u0000\u0464"+
		"\u0465\u0005\u0095\u0000\u0000\u0465\u046a\u0003Z-\u0000\u0466\u0467\u0005"+
		"\u009c\u0000\u0000\u0467\u0469\u0003Z-\u0000\u0468\u0466\u0001\u0000\u0000"+
		"\u0000\u0469\u046c\u0001\u0000\u0000\u0000\u046a\u0468\u0001\u0000\u0000"+
		"\u0000\u046a\u046b\u0001\u0000\u0000\u0000\u046b\u046d\u0001\u0000\u0000"+
		"\u0000\u046c\u046a\u0001\u0000\u0000\u0000\u046d\u046e\u0005\u0096\u0000"+
		"\u0000\u046e\u046f\u0005S\u0000\u0000\u046f\u0470\u0005\u0095\u0000\u0000"+
		"\u0470\u0475\u0003p8\u0000\u0471\u0472\u0005\u009c\u0000\u0000\u0472\u0474"+
		"\u0003p8\u0000\u0473\u0471\u0001\u0000\u0000\u0000\u0474\u0477\u0001\u0000"+
		"\u0000\u0000\u0475\u0473\u0001\u0000\u0000\u0000\u0475\u0476\u0001\u0000"+
		"\u0000\u0000\u0476\u0478\u0001\u0000\u0000\u0000\u0477\u0475\u0001\u0000"+
		"\u0000\u0000\u0478\u0479\u0005\u0096\u0000\u0000\u0479\u0484\u0001\u0000"+
		"\u0000\u0000\u047a\u047b\u0005\u00d2\u0000\u0000\u047b\u047c\u0003X,\u0000"+
		"\u047c\u047d\u0005\u00b5\u0000\u0000\u047d\u047e\u0003Z-\u0000\u047e\u047f"+
		"\u0005\u00c9\u0000\u0000\u047f\u0480\u0003p8\u0000\u0480\u0484\u0001\u0000"+
		"\u0000\u0000\u0481\u0482\u0005T\u0000\u0000\u0482\u0484\u0003X,\u0000"+
		"\u0483\u0462\u0001\u0000\u0000\u0000\u0483\u047a\u0001\u0000\u0000\u0000"+
		"\u0483\u0481\u0001\u0000\u0000\u0000\u0484\u0083\u0001\u0000\u0000\u0000"+
		"\u0485\u048a\u0005\u00be\u0000\u0000\u0486\u048b\u0001\u0000\u0000\u0000"+
		"\u0487\u048b\u0003\u0086C\u0000\u0488\u048b\u0003\u0088D\u0000\u0489\u048b"+
		"\u0005E\u0000\u0000\u048a\u0486\u0001\u0000\u0000\u0000\u048a\u0487\u0001"+
		"\u0000\u0000\u0000\u048a\u0488\u0001\u0000\u0000\u0000\u048a\u0489\u0001"+
		"\u0000\u0000\u0000\u048b\u0085\u0001\u0000\u0000\u0000\u048c\u048e\u0005"+
		"k\u0000\u0000\u048d\u048c\u0001\u0000\u0000\u0000\u048d\u048e\u0001\u0000"+
		"\u0000\u0000\u048e\u0490\u0001\u0000\u0000\u0000\u048f\u0491\u0005\u00db"+
		"\u0000\u0000\u0490\u048f\u0001\u0000\u0000\u0000\u0491\u0492\u0001\u0000"+
		"\u0000\u0000\u0492\u0490\u0001\u0000\u0000\u0000\u0492\u0493\u0001\u0000"+
		"\u0000\u0000\u0493\u049a\u0001\u0000\u0000\u0000\u0494\u0496\u0005\u00b1"+
		"\u0000\u0000\u0495\u0497\u0005\u00db\u0000\u0000\u0496\u0495\u0001\u0000"+
		"\u0000\u0000\u0497\u0498\u0001\u0000\u0000\u0000\u0498\u0496\u0001\u0000"+
		"\u0000\u0000\u0498\u0499\u0001\u0000\u0000\u0000\u0499\u049b\u0001\u0000"+
		"\u0000\u0000\u049a\u0494\u0001\u0000\u0000\u0000\u049a\u049b\u0001\u0000"+
		"\u0000\u0000\u049b\u0087\u0001\u0000\u0000\u0000\u049c\u049d\u0007\u0011"+
		"\u0000\u0000\u049d\u0089\u0001\u0000\u0000\u0000\u049e\u049f\u0005\u00c6"+
		"\u0000\u0000\u049f\u04a3\u0003\u008cF\u0000\u04a0\u04a1\u0005\u00c7\u0000"+
		"\u0000\u04a1\u04a3\u0003\u008cF\u0000\u04a2\u049e\u0001\u0000\u0000\u0000"+
		"\u04a2\u04a0\u0001\u0000\u0000\u0000\u04a3\u008b\u0001\u0000\u0000\u0000"+
		"\u04a4\u04a5\u0007\u0005\u0000\u0000\u04a5\u008d\u0001\u0000\u0000\u0000"+
		"\u04a6\u04a7\u0005\u00ba\u0000\u0000\u04a7\u04a8\u0005\u0095\u0000\u0000"+
		"\u04a8\u04a9\u0003t:\u0000\u04a9\u04aa\u0005\u0096\u0000\u0000\u04aa\u008f"+
		"\u0001\u0000\u0000\u0000\u04ab\u04ac\u00058\u0000\u0000\u04ac\u04ad\u0005"+
		"\u00d9\u0000\u0000\u04ad\u0091\u0001\u0000\u0000\u0000\u04ae\u04b1\u0005"+
		"\u00d8\u0000\u0000\u04af\u04b0\u0005\u00b1\u0000\u0000\u04b0\u04b2\u0005"+
		"\u00d8\u0000\u0000\u04b1\u04af\u0001\u0000\u0000\u0000\u04b1\u04b2\u0001"+
		"\u0000\u0000\u0000\u04b2\u0093\u0001\u0000\u0000\u0000r\u009a\u009f\u00a3"+
		"\u00a8\u00b4\u00b9\u00be\u00c3\u00c6\u00d3\u00d8\u00db\u00e1\u00f8\u00fc"+
		"\u0107\u0113\u0118\u012f\u013f\u0150\u0152\u0165\u0168\u0170\u0176\u0184"+
		"\u018d\u019a\u01a9\u01af\u01b3\u01c1\u01ce\u01d5\u01dd\u01e3\u01ed\u01f1"+
		"\u01f5\u01fe\u0205\u020d\u0214\u0219\u021f\u022d\u0234\u0242\u0244\u0258"+
		"\u025b\u0267\u026d\u0277\u0282\u0295\u029a\u02a2\u02b0\u02b5\u02b9\u02d7"+
		"\u02df\u02e1\u02f6\u02f8\u030f\u0312\u032a\u032d\u0330\u0333\u0336\u033e"+
		"\u0341\u0346\u034e\u0358\u035c\u0361\u0369\u0376\u0378\u037f\u038c\u038f"+
		"\u03b2\u03bb\u03cc\u03d1\u03e1\u03f4\u03f6\u03f8\u0407\u040e\u0417\u0420"+
		"\u0427\u042e\u0436\u044d\u0460\u046a\u0475\u0483\u048a\u048d\u0492\u0498"+
		"\u049a\u04a2\u04b1";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}