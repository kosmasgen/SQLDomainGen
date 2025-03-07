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
		T__143=144, T__144=145, T__145=146, T__146=147, T__147=148, T__148=149, 
		T__149=150, T__150=151, T__151=152, T__152=153, T__153=154, LPAREN=155, 
		RPAREN=156, LBRACE=157, RBRACE=158, SEMICOLON=159, COLON=160, DOUBLE_COLON=161, 
		COMMA=162, DOUBLE_QUOTE=163, ON=164, DELETE=165, RULE=166, DO=167, AS=168, 
		TO=169, INSTEAD=170, NOTHING=171, FOR=172, SELECT=173, USING=174, DOLLAR_QUOTE=175, 
		NEW=176, TABLE=177, CREATE=178, RELATIONSHIP=179, DECIMAL=180, INT=181, 
		VARCHAR=182, DOT=183, CONSTRAINT=184, NULL=185, FOREIGN_KEY=186, NOT=187, 
		SET=188, REFERENCES=189, PRIMARY_KEY=190, NOT_NULL=191, CHECK=192, ANY=193, 
		LBRACKET=194, RBRACKET=195, ARRAY=196, DEFAULT=197, NEXTVAL=198, REGCLASS=199, 
		CASCADE=200, SET_NULL=201, SET_DEFAULT=202, NO_ACTION=203, RESTRICT=204, 
		ON_DELETE=205, ON_UPDATE=206, UNIQUE=207, EQUALS=208, TEXT=209, INTEGER=210, 
		BOOLEAN=211, IDENTIFIER=212, STRING=213, NUMBER=214, DIGIT=215, WS=216;
	public static final int
		RULE_createTableStatement = 0, RULE_columnDef = 1, RULE_generatedColumn = 2, 
		RULE_collateClause = 3, RULE_alterTableStatement = 4, RULE_alterAction = 5, 
		RULE_alterColumnAction = 6, RULE_dataType = 7, RULE_onDeleteAction = 8, 
		RULE_onUpdateAction = 9, RULE_constraint = 10, RULE_tableConstraint = 11, 
		RULE_excludeElementList = 12, RULE_excludeElement = 13, RULE_onAction = 14, 
		RULE_action = 15, RULE_triggerStatement = 16, RULE_triggerTiming = 17, 
		RULE_triggerEvent = 18, RULE_dataManipulationStatement = 19, RULE_insertStatement = 20, 
		RULE_updateStatement = 21, RULE_deleteStatement = 22, RULE_condition = 23, 
		RULE_subquery = 24, RULE_comparisonOperator = 25, RULE_arithmeticOperator = 26, 
		RULE_sqlScript = 27, RULE_createSchemaStatement = 28, RULE_createViewStatement = 29, 
		RULE_createFunctionStatement = 30, RULE_parameter = 31, RULE_selectStatement = 32, 
		RULE_selectList = 33, RULE_tableReference = 34, RULE_whereClause = 35, 
		RULE_orderByClause = 36, RULE_limitClause = 37, RULE_columnNameList = 38, 
		RULE_foreignTable = 39, RULE_conflictAction = 40, RULE_schemaName = 41, 
		RULE_tableName = 42, RULE_columnName = 43, RULE_relationshipType = 44, 
		RULE_decimalType = 45, RULE_partitionClause = 46, RULE_partitionStrategy = 47, 
		RULE_createPartitionStatement = 48, RULE_partitionValuesClause = 49, RULE_partitionOfClause = 50, 
		RULE_createIndexStatement = 51, RULE_indexOptionsClause = 52, RULE_expression = 53, 
		RULE_value = 54, RULE_expressionList = 55, RULE_arrayConstructor = 56, 
		RULE_createPolicyStatement = 57, RULE_plpgsqlBlock = 58, RULE_statement = 59, 
		RULE_createRuleStatement = 60, RULE_ruleEvent = 61, RULE_ruleAction = 62, 
		RULE_sqlAction = 63, RULE_defaultValue = 64, RULE_numericLiteral = 65, 
		RULE_booleanLiteral = 66, RULE_foreignKeyAction = 67, RULE_referentialAction = 68, 
		RULE_anyExpression = 69, RULE_intervalLiteral = 70, RULE_typeName = 71;
	private static String[] makeRuleNames() {
		return new String[] {
			"createTableStatement", "columnDef", "generatedColumn", "collateClause", 
			"alterTableStatement", "alterAction", "alterColumnAction", "dataType", 
			"onDeleteAction", "onUpdateAction", "constraint", "tableConstraint", 
			"excludeElementList", "excludeElement", "onAction", "action", "triggerStatement", 
			"triggerTiming", "triggerEvent", "dataManipulationStatement", "insertStatement", 
			"updateStatement", "deleteStatement", "condition", "subquery", "comparisonOperator", 
			"arithmeticOperator", "sqlScript", "createSchemaStatement", "createViewStatement", 
			"createFunctionStatement", "parameter", "selectStatement", "selectList", 
			"tableReference", "whereClause", "orderByClause", "limitClause", "columnNameList", 
			"foreignTable", "conflictAction", "schemaName", "tableName", "columnName", 
			"relationshipType", "decimalType", "partitionClause", "partitionStrategy", 
			"createPartitionStatement", "partitionValuesClause", "partitionOfClause", 
			"createIndexStatement", "indexOptionsClause", "expression", "value", 
			"expressionList", "arrayConstructor", "createPolicyStatement", "plpgsqlBlock", 
			"statement", "createRuleStatement", "ruleEvent", "ruleAction", "sqlAction", 
			"defaultValue", "numericLiteral", "booleanLiteral", "foreignKeyAction", 
			"referentialAction", "anyExpression", "intervalLiteral", "typeName"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'PARTITION OF'", "'GENERATED'", "'ALWAYS'", "'BY DEFAULT'", "'STORED'", 
			"'COLLATE'", "'ALTER'", "'ADD'", "'COLUMN'", "'DROP'", "'RENAME COLUMN'", 
			"'RENAME TO'", "'SCHEMA'", "'TABLESPACE'", "'ENABLE'", "'REPLICA'", "'TRIGGER'", 
			"'DISABLE'", "'CLUSTER'", "'SET WITHOUT CLUSTER'", "'OWNER TO'", "'DROP DEFAULT'", 
			"'SET NOT NULL'", "'DROP NOT NULL'", "'SET STATISTICS'", "'SET STORAGE'", 
			"'PLAIN'", "'EXTERNAL'", "'EXTENDED'", "'MAIN'", "'SET COMPRESSION'", 
			"'RESET'", "'STORAGE'", "'STATISTICS'", "'COMPRESSION'", "'TYPE'", "'FLOAT8'", 
			"'FLOAT4'", "'INT8'", "'SMALLINT'", "'BIGINT'", "'BIGSERIAL'", "'SERIAL'", 
			"'SMALLSERIAL'", "'REAL'", "'DOUBLE PRECISION'", "'NUMERIC'", "'MONEY'", 
			"'CHAR'", "'JSON'", "'JSONB'", "'DATE'", "'TIME'", "'WITHOUT'", "'ZONE'", 
			"'WITH'", "'TIMESTAMP'", "'INTERVAL'", "'UUID'", "'BYTEA'", "'ENUM'", 
			"'CITEXT'", "'TSVECTOR'", "'INET'", "'CIDR'", "'MACADDR'", "'XML'", "'PG_LSN'", 
			"'BIT'", "'VARBIT'", "'ON CONFLICT'", "'USING INDEX TABLESPACE'", "'EXCLUDE'", 
			"'WHERE'", "'DEFERRABLE'", "'INITIALLY'", "'DEFERRED'", "'IMMEDIATE'", 
			"'FOR EACH ROW'", "'EXECUTE FUNCTION'", "'BEFORE'", "'AFTER'", "'INSTEAD OF'", 
			"'INSERT'", "'UPDATE'", "'OR'", "'INSERT INTO'", "'VALUES'", "'DELETE FROM'", 
			"'IN'", "'BETWEEN'", "'AND'", "'IS'", "'IS NOT NULL'", "'LIKE'", "'ILIKE'", 
			"'true'", "'false'", "'<>'", "'!='", "'<'", "'<='", "'>'", "'>='", "'@>'", 
			"'<@'", "'&&'", "'?'", "'?|'", "'?&'", "'+'", "'-'", "'*'", "'/'", "'%'", 
			"'^'", "'CREATE SCHEMA'", "'CREATE VIEW'", "'CREATE FUNCTION'", "'RETURNS'", 
			"'LANGUAGE'", "'FROM'", "'ORDER BY'", "'ASC'", "'DESC'", "'LIMIT'", "'DO NOTHING'", 
			"'DO UPDATE'", "'ONETOONE'", "'MANYTOONE'", "'ONETOMANY'", "'MANYTOMANY'", 
			"'PARTITION BY'", "'RANGE'", "'LIST'", "'HASH'", "'FOR VALUES'", "'MODULUS'", 
			"'REMAINDER'", "'INDEX'", "'TRUE'", "'FALSE'", "'CURRENT_TIMESTAMP'", 
			"'current_user'", "'now'", "'infinity'", "'NaN'", "'POLICY'", "'ALL'", 
			"'WITH CHECK'", "'BEGIN'", "'END'", "'RETURN'", "'INSTEAD NOTHING'", 
			"'('", "')'", "'{'", "'}'", "';'", "':'", "'::'", "','", "'\"'", "'ON'", 
			"'DELETE'", "'RULE'", "'DO'", "'AS'", "'TO'", "'INSTEAD'", "'NOTHING'", 
			"'FOR'", "'SELECT'", "'USING'", "'$$'", "'NEW'", "'TABLE'", "'CREATE'", 
			"'RELATIONSHIP'", "'DECIMAL'", "'INT'", "'VARCHAR'", "'.'", "'CONSTRAINT'", 
			"'NULL'", "'FOREIGN KEY'", "'NOT'", "'SET'", "'REFERENCES'", "'PRIMARY KEY'", 
			"'NOT NULL'", "'CHECK'", "'ANY'", "'['", "']'", "'ARRAY'", "'DEFAULT'", 
			"'nextval'", "'regclass'", "'CASCADE'", "'SET NULL'", "'SET DEFAULT'", 
			"'NO ACTION'", "'RESTRICT'", "'ON DELETE'", "'ON UPDATE'", "'UNIQUE'", 
			"'='", "'TEXT'", "'INTEGER'", "'BOOLEAN'"
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
			null, null, null, null, null, null, null, null, null, null, null, "LPAREN", 
			"RPAREN", "LBRACE", "RBRACE", "SEMICOLON", "COLON", "DOUBLE_COLON", "COMMA", 
			"DOUBLE_QUOTE", "ON", "DELETE", "RULE", "DO", "AS", "TO", "INSTEAD", 
			"NOTHING", "FOR", "SELECT", "USING", "DOLLAR_QUOTE", "NEW", "TABLE", 
			"CREATE", "RELATIONSHIP", "DECIMAL", "INT", "VARCHAR", "DOT", "CONSTRAINT", 
			"NULL", "FOREIGN_KEY", "NOT", "SET", "REFERENCES", "PRIMARY_KEY", "NOT_NULL", 
			"CHECK", "ANY", "LBRACKET", "RBRACKET", "ARRAY", "DEFAULT", "NEXTVAL", 
			"REGCLASS", "CASCADE", "SET_NULL", "SET_DEFAULT", "NO_ACTION", "RESTRICT", 
			"ON_DELETE", "ON_UPDATE", "UNIQUE", "EQUALS", "TEXT", "INTEGER", "BOOLEAN", 
			"IDENTIFIER", "STRING", "NUMBER", "DIGIT", "WS"
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
		public List<ColumnDefContext> columnDef() {
			return getRuleContexts(ColumnDefContext.class);
		}
		public ColumnDefContext columnDef(int i) {
			return getRuleContext(ColumnDefContext.class,i);
		}
		public List<TableConstraintContext> tableConstraint() {
			return getRuleContexts(TableConstraintContext.class);
		}
		public TableConstraintContext tableConstraint(int i) {
			return getRuleContext(TableConstraintContext.class,i);
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
			setState(176);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,4,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(144);
				match(CREATE);
				setState(145);
				match(TABLE);
				setState(146);
				tableName();
				setState(147);
				match(LPAREN);
				setState(150);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case FOREIGN_KEY:
				case IDENTIFIER:
					{
					setState(148);
					columnDef();
					}
					break;
				case T__72:
				case CONSTRAINT:
					{
					setState(149);
					tableConstraint();
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(159);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(152);
					match(COMMA);
					setState(155);
					_errHandler.sync(this);
					switch (_input.LA(1)) {
					case FOREIGN_KEY:
					case IDENTIFIER:
						{
						setState(153);
						columnDef();
						}
						break;
					case T__72:
					case CONSTRAINT:
						{
						setState(154);
						tableConstraint();
						}
						break;
					default:
						throw new NoViableAltException(this);
					}
					}
					}
					setState(161);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(162);
				match(RPAREN);
				setState(164);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__132) {
					{
					setState(163);
					partitionClause();
					}
				}

				setState(166);
				match(SEMICOLON);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(168);
				match(CREATE);
				setState(169);
				match(TABLE);
				setState(170);
				tableName();
				setState(171);
				match(T__0);
				setState(172);
				tableName();
				setState(173);
				partitionValuesClause();
				setState(174);
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
			setState(209);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case IDENTIFIER:
				enterOuterAlt(_localctx, 1);
				{
				setState(178);
				columnName();
				setState(179);
				dataType();
				setState(183);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__72 || ((((_la - 185)) & ~0x3f) == 0 && ((1L << (_la - 185)) & 4198643L) != 0)) {
					{
					{
					setState(180);
					constraint();
					}
					}
					setState(185);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(188);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case T__1:
					{
					setState(186);
					generatedColumn();
					}
					break;
				case T__5:
					{
					setState(187);
					collateClause();
					}
					break;
				case EOF:
				case T__6:
				case T__84:
				case T__86:
				case T__88:
				case T__116:
				case T__117:
				case T__118:
				case RPAREN:
				case SEMICOLON:
				case COMMA:
				case CREATE:
					break;
				default:
					break;
				}
				}
				break;
			case FOREIGN_KEY:
				enterOuterAlt(_localctx, 2);
				{
				setState(190);
				match(FOREIGN_KEY);
				setState(191);
				match(LPAREN);
				setState(192);
				columnNameList();
				setState(193);
				match(RPAREN);
				setState(194);
				match(REFERENCES);
				setState(195);
				tableName();
				setState(196);
				match(LPAREN);
				setState(197);
				columnNameList();
				setState(198);
				match(RPAREN);
				setState(201);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==RELATIONSHIP) {
					{
					setState(199);
					match(RELATIONSHIP);
					setState(200);
					relationshipType();
					}
				}

				setState(206);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__74 || _la==ON_DELETE || _la==ON_UPDATE) {
					{
					{
					setState(203);
					onAction();
					}
					}
					setState(208);
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
		enterRule(_localctx, 4, RULE_generatedColumn);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(211);
			match(T__1);
			setState(212);
			_la = _input.LA(1);
			if ( !(_la==T__2 || _la==T__3) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			setState(213);
			match(AS);
			setState(214);
			match(LPAREN);
			setState(215);
			expression(0);
			setState(216);
			match(RPAREN);
			setState(217);
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
		enterRule(_localctx, 6, RULE_collateClause);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(219);
			match(T__5);
			setState(220);
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
		enterRule(_localctx, 8, RULE_alterTableStatement);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(222);
			match(T__6);
			setState(223);
			match(TABLE);
			setState(224);
			tableName();
			setState(225);
			alterAction();
			setState(230);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(226);
				match(COMMA);
				setState(227);
				alterAction();
				}
				}
				setState(232);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(234);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==SEMICOLON) {
				{
				setState(233);
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
		public TerminalNode CONSTRAINT() { return getToken(PostgreSQLParser.CONSTRAINT, 0); }
		public TerminalNode IDENTIFIER() { return getToken(PostgreSQLParser.IDENTIFIER, 0); }
		public TableConstraintContext tableConstraint() {
			return getRuleContext(TableConstraintContext.class,0);
		}
		public TerminalNode FOREIGN_KEY() { return getToken(PostgreSQLParser.FOREIGN_KEY, 0); }
		public List<ColumnNameListContext> columnNameList() {
			return getRuleContexts(ColumnNameListContext.class);
		}
		public ColumnNameListContext columnNameList(int i) {
			return getRuleContext(ColumnNameListContext.class,i);
		}
		public TerminalNode REFERENCES() { return getToken(PostgreSQLParser.REFERENCES, 0); }
		public TableNameContext tableName() {
			return getRuleContext(TableNameContext.class,0);
		}
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
		enterRule(_localctx, 10, RULE_alterAction);
		int _la;
		try {
			setState(294);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,14,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(236);
				match(T__7);
				setState(237);
				match(T__8);
				setState(238);
				columnDef();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(239);
				match(T__9);
				setState(240);
				match(T__8);
				setState(241);
				columnName();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(242);
				match(T__7);
				setState(243);
				match(CONSTRAINT);
				setState(244);
				match(IDENTIFIER);
				setState(245);
				tableConstraint();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(246);
				match(T__9);
				setState(247);
				match(CONSTRAINT);
				setState(248);
				match(IDENTIFIER);
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(249);
				match(T__7);
				setState(250);
				match(FOREIGN_KEY);
				setState(251);
				columnNameList();
				setState(252);
				match(REFERENCES);
				setState(253);
				tableName();
				setState(254);
				columnNameList();
				setState(258);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__74 || _la==ON_DELETE || _la==ON_UPDATE) {
					{
					{
					setState(255);
					onAction();
					}
					}
					setState(260);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(261);
				match(T__6);
				setState(262);
				match(T__8);
				setState(263);
				columnName();
				setState(264);
				alterColumnAction();
				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(266);
				match(T__10);
				setState(267);
				columnName();
				setState(268);
				match(TO);
				setState(269);
				columnName();
				}
				break;
			case 8:
				enterOuterAlt(_localctx, 8);
				{
				setState(271);
				match(T__11);
				setState(272);
				tableName();
				}
				break;
			case 9:
				enterOuterAlt(_localctx, 9);
				{
				setState(273);
				match(SET);
				setState(278);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case T__12:
					{
					setState(274);
					match(T__12);
					setState(275);
					schemaName();
					}
					break;
				case T__13:
					{
					setState(276);
					match(T__13);
					setState(277);
					match(IDENTIFIER);
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				break;
			case 10:
				enterOuterAlt(_localctx, 10);
				{
				setState(280);
				match(T__14);
				setState(281);
				match(T__15);
				setState(282);
				match(T__16);
				setState(283);
				match(IDENTIFIER);
				}
				break;
			case 11:
				enterOuterAlt(_localctx, 11);
				{
				setState(284);
				match(T__17);
				setState(285);
				match(T__15);
				setState(286);
				match(T__16);
				setState(287);
				match(IDENTIFIER);
				}
				break;
			case 12:
				enterOuterAlt(_localctx, 12);
				{
				setState(288);
				match(T__18);
				setState(289);
				match(ON);
				setState(290);
				match(IDENTIFIER);
				}
				break;
			case 13:
				enterOuterAlt(_localctx, 13);
				{
				setState(291);
				match(T__19);
				}
				break;
			case 14:
				enterOuterAlt(_localctx, 14);
				{
				setState(292);
				match(T__20);
				setState(293);
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
		enterRule(_localctx, 12, RULE_alterColumnAction);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(311); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				setState(311);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case SET_DEFAULT:
					{
					setState(296);
					match(SET_DEFAULT);
					setState(297);
					value();
					}
					break;
				case T__21:
					{
					setState(298);
					match(T__21);
					}
					break;
				case T__22:
					{
					setState(299);
					match(T__22);
					}
					break;
				case T__23:
					{
					setState(300);
					match(T__23);
					}
					break;
				case T__24:
					{
					setState(301);
					match(T__24);
					setState(302);
					match(NUMBER);
					}
					break;
				case T__25:
					{
					setState(303);
					match(T__25);
					setState(304);
					_la = _input.LA(1);
					if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 2013265920L) != 0)) ) {
					_errHandler.recoverInline(this);
					}
					else {
						if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
						_errHandler.reportMatch(this);
						consume();
					}
					}
					break;
				case T__30:
					{
					setState(305);
					match(T__30);
					setState(306);
					match(IDENTIFIER);
					}
					break;
				case T__31:
					{
					setState(307);
					match(T__31);
					setState(308);
					_la = _input.LA(1);
					if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 60129542144L) != 0)) ) {
					_errHandler.recoverInline(this);
					}
					else {
						if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
						_errHandler.reportMatch(this);
						consume();
					}
					}
					break;
				case T__35:
					{
					setState(309);
					match(T__35);
					setState(310);
					dataType();
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				setState(313); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & 75291951104L) != 0) || _la==SET_DEFAULT );
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
		enterRule(_localctx, 14, RULE_dataType);
		int _la;
		try {
			setState(410);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,26,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(315);
				match(INTEGER);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(316);
				match(T__36);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(317);
				match(T__37);
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(318);
				match(T__38);
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(319);
				match(INT);
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(320);
				match(T__39);
				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(321);
				match(T__40);
				}
				break;
			case 8:
				enterOuterAlt(_localctx, 8);
				{
				setState(322);
				match(T__41);
				}
				break;
			case 9:
				enterOuterAlt(_localctx, 9);
				{
				setState(323);
				match(T__42);
				}
				break;
			case 10:
				enterOuterAlt(_localctx, 10);
				{
				setState(324);
				match(T__43);
				}
				break;
			case 11:
				enterOuterAlt(_localctx, 11);
				{
				setState(325);
				match(T__44);
				}
				break;
			case 12:
				enterOuterAlt(_localctx, 12);
				{
				setState(326);
				match(T__45);
				}
				break;
			case 13:
				enterOuterAlt(_localctx, 13);
				{
				setState(327);
				match(T__46);
				setState(335);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,18,_ctx) ) {
				case 1:
					{
					setState(328);
					match(LPAREN);
					setState(329);
					match(NUMBER);
					setState(332);
					_errHandler.sync(this);
					_la = _input.LA(1);
					if (_la==COMMA) {
						{
						setState(330);
						match(COMMA);
						setState(331);
						match(NUMBER);
						}
					}

					setState(334);
					match(RPAREN);
					}
					break;
				}
				}
				break;
			case 14:
				enterOuterAlt(_localctx, 14);
				{
				setState(337);
				decimalType();
				}
				break;
			case 15:
				enterOuterAlt(_localctx, 15);
				{
				setState(338);
				match(T__47);
				}
				break;
			case 16:
				enterOuterAlt(_localctx, 16);
				{
				setState(339);
				match(T__48);
				setState(343);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,19,_ctx) ) {
				case 1:
					{
					setState(340);
					match(LPAREN);
					setState(341);
					match(NUMBER);
					setState(342);
					match(RPAREN);
					}
					break;
				}
				}
				break;
			case 17:
				enterOuterAlt(_localctx, 17);
				{
				setState(345);
				match(VARCHAR);
				setState(349);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,20,_ctx) ) {
				case 1:
					{
					setState(346);
					match(LPAREN);
					setState(347);
					match(NUMBER);
					setState(348);
					match(RPAREN);
					}
					break;
				}
				}
				break;
			case 18:
				enterOuterAlt(_localctx, 18);
				{
				setState(351);
				match(TEXT);
				}
				break;
			case 19:
				enterOuterAlt(_localctx, 19);
				{
				setState(352);
				match(BOOLEAN);
				}
				break;
			case 20:
				enterOuterAlt(_localctx, 20);
				{
				setState(353);
				match(T__49);
				}
				break;
			case 21:
				enterOuterAlt(_localctx, 21);
				{
				setState(354);
				match(T__50);
				}
				break;
			case 22:
				enterOuterAlt(_localctx, 22);
				{
				setState(355);
				match(T__51);
				}
				break;
			case 23:
				enterOuterAlt(_localctx, 23);
				{
				setState(356);
				match(T__52);
				setState(363);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,21,_ctx) ) {
				case 1:
					{
					setState(357);
					match(T__53);
					setState(358);
					match(T__52);
					setState(359);
					match(T__54);
					}
					break;
				case 2:
					{
					setState(360);
					match(T__55);
					setState(361);
					match(T__52);
					setState(362);
					match(T__54);
					}
					break;
				}
				}
				break;
			case 24:
				enterOuterAlt(_localctx, 24);
				{
				setState(365);
				match(T__56);
				setState(372);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,22,_ctx) ) {
				case 1:
					{
					setState(366);
					match(T__53);
					setState(367);
					match(T__52);
					setState(368);
					match(T__54);
					}
					break;
				case 2:
					{
					setState(369);
					match(T__55);
					setState(370);
					match(T__52);
					setState(371);
					match(T__54);
					}
					break;
				}
				}
				break;
			case 25:
				enterOuterAlt(_localctx, 25);
				{
				setState(374);
				match(T__57);
				}
				break;
			case 26:
				enterOuterAlt(_localctx, 26);
				{
				setState(375);
				match(T__58);
				}
				break;
			case 27:
				enterOuterAlt(_localctx, 27);
				{
				setState(376);
				match(ARRAY);
				}
				break;
			case 28:
				enterOuterAlt(_localctx, 28);
				{
				setState(377);
				match(T__59);
				}
				break;
			case 29:
				enterOuterAlt(_localctx, 29);
				{
				setState(378);
				match(T__60);
				setState(379);
				match(LPAREN);
				setState(380);
				match(STRING);
				setState(385);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(381);
					match(COMMA);
					setState(382);
					match(STRING);
					}
					}
					setState(387);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(388);
				match(RPAREN);
				}
				break;
			case 30:
				enterOuterAlt(_localctx, 30);
				{
				setState(389);
				match(T__61);
				}
				break;
			case 31:
				enterOuterAlt(_localctx, 31);
				{
				setState(390);
				match(T__62);
				}
				break;
			case 32:
				enterOuterAlt(_localctx, 32);
				{
				setState(391);
				match(T__63);
				}
				break;
			case 33:
				enterOuterAlt(_localctx, 33);
				{
				setState(392);
				match(T__64);
				}
				break;
			case 34:
				enterOuterAlt(_localctx, 34);
				{
				setState(393);
				match(T__65);
				}
				break;
			case 35:
				enterOuterAlt(_localctx, 35);
				{
				setState(394);
				match(T__66);
				}
				break;
			case 36:
				enterOuterAlt(_localctx, 36);
				{
				setState(395);
				match(T__67);
				}
				break;
			case 37:
				enterOuterAlt(_localctx, 37);
				{
				setState(396);
				match(T__68);
				setState(400);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,24,_ctx) ) {
				case 1:
					{
					setState(397);
					match(LPAREN);
					setState(398);
					match(NUMBER);
					setState(399);
					match(RPAREN);
					}
					break;
				}
				}
				break;
			case 38:
				enterOuterAlt(_localctx, 38);
				{
				setState(402);
				match(T__69);
				setState(406);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,25,_ctx) ) {
				case 1:
					{
					setState(403);
					match(LPAREN);
					setState(404);
					match(NUMBER);
					setState(405);
					match(RPAREN);
					}
					break;
				}
				}
				break;
			case 39:
				enterOuterAlt(_localctx, 39);
				{
				setState(408);
				match(T__16);
				}
				break;
			case 40:
				enterOuterAlt(_localctx, 40);
				{
				setState(409);
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
		enterRule(_localctx, 16, RULE_onDeleteAction);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(412);
			_la = _input.LA(1);
			if ( !(((((_la - 200)) & ~0x3f) == 0 && ((1L << (_la - 200)) & 15L) != 0)) ) {
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
		enterRule(_localctx, 18, RULE_onUpdateAction);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(414);
			_la = _input.LA(1);
			if ( !(((((_la - 200)) & ~0x3f) == 0 && ((1L << (_la - 200)) & 15L) != 0)) ) {
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
		public TerminalNode PRIMARY_KEY() { return getToken(PostgreSQLParser.PRIMARY_KEY, 0); }
		public TerminalNode IDENTIFIER() { return getToken(PostgreSQLParser.IDENTIFIER, 0); }
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
		public ExcludeElementListContext excludeElementList() {
			return getRuleContext(ExcludeElementListContext.class,0);
		}
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
		enterRule(_localctx, 20, RULE_constraint);
		int _la;
		try {
			setState(473);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,34,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(416);
				match(NOT_NULL);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(417);
				match(NULL);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(418);
				match(UNIQUE);
				setState(421);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__70) {
					{
					setState(419);
					match(T__70);
					setState(420);
					conflictAction();
					}
				}

				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(423);
				match(DEFAULT);
				setState(424);
				value();
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(425);
				match(CHECK);
				setState(426);
				match(LPAREN);
				setState(427);
				condition(0);
				setState(428);
				match(RPAREN);
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(430);
				match(REFERENCES);
				setState(434);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,28,_ctx) ) {
				case 1:
					{
					setState(431);
					schemaName();
					setState(432);
					match(DOT);
					}
					break;
				}
				setState(436);
				tableName();
				setState(437);
				match(LPAREN);
				setState(438);
				columnName();
				setState(439);
				match(RPAREN);
				setState(441);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__74 || _la==ON_DELETE || _la==ON_UPDATE) {
					{
					setState(440);
					onAction();
					}
				}

				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(443);
				match(PRIMARY_KEY);
				}
				break;
			case 8:
				enterOuterAlt(_localctx, 8);
				{
				setState(444);
				match(PRIMARY_KEY);
				setState(447);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__71) {
					{
					setState(445);
					match(T__71);
					setState(446);
					match(IDENTIFIER);
					}
				}

				}
				break;
			case 9:
				enterOuterAlt(_localctx, 9);
				{
				setState(449);
				match(FOREIGN_KEY);
				setState(450);
				columnNameList();
				setState(451);
				match(REFERENCES);
				setState(455);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,31,_ctx) ) {
				case 1:
					{
					setState(452);
					schemaName();
					setState(453);
					match(DOT);
					}
					break;
				}
				setState(457);
				tableName();
				setState(458);
				columnNameList();
				setState(461);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==RELATIONSHIP) {
					{
					setState(459);
					match(RELATIONSHIP);
					setState(460);
					relationshipType();
					}
				}

				}
				break;
			case 10:
				enterOuterAlt(_localctx, 10);
				{
				setState(463);
				match(T__72);
				setState(464);
				match(USING);
				setState(465);
				match(IDENTIFIER);
				setState(466);
				match(LPAREN);
				setState(467);
				excludeElementList();
				setState(468);
				match(RPAREN);
				setState(471);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__73) {
					{
					setState(469);
					match(T__73);
					setState(470);
					condition(0);
					}
				}

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
	public static class TableConstraintContext extends ParserRuleContext {
		public TerminalNode CONSTRAINT() { return getToken(PostgreSQLParser.CONSTRAINT, 0); }
		public TerminalNode PRIMARY_KEY() { return getToken(PostgreSQLParser.PRIMARY_KEY, 0); }
		public List<ColumnNameListContext> columnNameList() {
			return getRuleContexts(ColumnNameListContext.class);
		}
		public ColumnNameListContext columnNameList(int i) {
			return getRuleContext(ColumnNameListContext.class,i);
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
		public TerminalNode LPAREN() { return getToken(PostgreSQLParser.LPAREN, 0); }
		public ConditionContext condition() {
			return getRuleContext(ConditionContext.class,0);
		}
		public TerminalNode RPAREN() { return getToken(PostgreSQLParser.RPAREN, 0); }
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
		enterRule(_localctx, 22, RULE_tableConstraint);
		int _la;
		try {
			setState(523);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,41,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(475);
				match(CONSTRAINT);
				setState(477);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==IDENTIFIER) {
					{
					setState(476);
					match(IDENTIFIER);
					}
				}

				setState(479);
				match(PRIMARY_KEY);
				setState(480);
				columnNameList();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(481);
				match(CONSTRAINT);
				setState(483);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==IDENTIFIER) {
					{
					setState(482);
					match(IDENTIFIER);
					}
				}

				setState(485);
				match(FOREIGN_KEY);
				setState(486);
				columnNameList();
				setState(487);
				match(REFERENCES);
				setState(491);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,37,_ctx) ) {
				case 1:
					{
					setState(488);
					schemaName();
					setState(489);
					match(DOT);
					}
					break;
				}
				setState(493);
				tableName();
				setState(494);
				columnNameList();
				setState(498);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__74 || _la==ON_DELETE || _la==ON_UPDATE) {
					{
					{
					setState(495);
					onAction();
					}
					}
					setState(500);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(501);
				match(CONSTRAINT);
				setState(503);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==IDENTIFIER) {
					{
					setState(502);
					match(IDENTIFIER);
					}
				}

				setState(505);
				match(UNIQUE);
				setState(506);
				columnNameList();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(507);
				match(CONSTRAINT);
				setState(509);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==IDENTIFIER) {
					{
					setState(508);
					match(IDENTIFIER);
					}
				}

				setState(511);
				match(CHECK);
				setState(512);
				match(LPAREN);
				setState(513);
				condition(0);
				setState(514);
				match(RPAREN);
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(516);
				match(T__72);
				setState(517);
				match(USING);
				setState(518);
				match(IDENTIFIER);
				setState(519);
				match(LPAREN);
				setState(520);
				excludeElementList();
				setState(521);
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
		enterRule(_localctx, 24, RULE_excludeElementList);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(525);
			excludeElement();
			setState(530);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(526);
				match(COMMA);
				setState(527);
				excludeElement();
				}
				}
				setState(532);
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
		enterRule(_localctx, 26, RULE_excludeElement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(533);
			columnName();
			setState(534);
			match(T__55);
			setState(535);
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
		enterRule(_localctx, 28, RULE_onAction);
		int _la;
		try {
			setState(546);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case ON_DELETE:
				enterOuterAlt(_localctx, 1);
				{
				setState(537);
				match(ON_DELETE);
				setState(538);
				action();
				}
				break;
			case ON_UPDATE:
				enterOuterAlt(_localctx, 2);
				{
				setState(539);
				match(ON_UPDATE);
				setState(540);
				action();
				}
				break;
			case T__74:
				enterOuterAlt(_localctx, 3);
				{
				setState(541);
				match(T__74);
				setState(544);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__75) {
					{
					setState(542);
					match(T__75);
					setState(543);
					_la = _input.LA(1);
					if ( !(_la==T__76 || _la==T__77) ) {
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
		enterRule(_localctx, 30, RULE_action);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(548);
			_la = _input.LA(1);
			if ( !(((((_la - 200)) & ~0x3f) == 0 && ((1L << (_la - 200)) & 31L) != 0)) ) {
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
		enterRule(_localctx, 32, RULE_triggerStatement);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(550);
			match(CREATE);
			setState(551);
			match(T__16);
			setState(552);
			match(IDENTIFIER);
			setState(553);
			triggerTiming();
			setState(554);
			triggerEvent();
			setState(555);
			match(ON);
			setState(556);
			tableName();
			setState(557);
			match(T__78);
			setState(558);
			match(T__79);
			setState(559);
			match(IDENTIFIER);
			setState(560);
			match(LPAREN);
			setState(569);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__57 || ((((_la - 141)) & ~0x3f) == 0 && ((1L << (_la - 141)) & 36046389205008511L) != 0) || _la==STRING || _la==NUMBER) {
				{
				setState(561);
				value();
				setState(566);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(562);
					match(COMMA);
					setState(563);
					value();
					}
					}
					setState(568);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
			}

			setState(571);
			match(RPAREN);
			setState(572);
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
		enterRule(_localctx, 34, RULE_triggerTiming);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(574);
			_la = _input.LA(1);
			if ( !(((((_la - 81)) & ~0x3f) == 0 && ((1L << (_la - 81)) & 7L) != 0)) ) {
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
		enterRule(_localctx, 36, RULE_triggerEvent);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(576);
			_la = _input.LA(1);
			if ( !(_la==T__83 || _la==T__84 || _la==DELETE) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			setState(581);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__85) {
				{
				{
				setState(577);
				match(T__85);
				setState(578);
				_la = _input.LA(1);
				if ( !(_la==T__83 || _la==T__84 || _la==DELETE) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				}
				}
				setState(583);
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
		enterRule(_localctx, 38, RULE_dataManipulationStatement);
		try {
			setState(587);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__86:
				enterOuterAlt(_localctx, 1);
				{
				setState(584);
				insertStatement();
				}
				break;
			case T__84:
				enterOuterAlt(_localctx, 2);
				{
				setState(585);
				updateStatement();
				}
				break;
			case T__88:
				enterOuterAlt(_localctx, 3);
				{
				setState(586);
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
		enterRule(_localctx, 40, RULE_insertStatement);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(589);
			match(T__86);
			setState(590);
			tableName();
			setState(591);
			match(LPAREN);
			setState(592);
			columnName();
			setState(597);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(593);
				match(COMMA);
				setState(594);
				columnName();
				}
				}
				setState(599);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(600);
			match(RPAREN);
			setState(601);
			match(T__87);
			setState(602);
			match(LPAREN);
			setState(603);
			value();
			setState(608);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(604);
				match(COMMA);
				setState(605);
				value();
				}
				}
				setState(610);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(611);
			match(RPAREN);
			setState(612);
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
		enterRule(_localctx, 42, RULE_updateStatement);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(614);
			match(T__84);
			setState(615);
			tableName();
			setState(616);
			match(SET);
			setState(617);
			columnName();
			setState(618);
			match(EQUALS);
			setState(619);
			value();
			setState(627);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(620);
				match(COMMA);
				setState(621);
				columnName();
				setState(622);
				match(EQUALS);
				setState(623);
				value();
				}
				}
				setState(629);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(632);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__73) {
				{
				setState(630);
				match(T__73);
				setState(631);
				condition(0);
				}
			}

			setState(634);
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
		enterRule(_localctx, 44, RULE_deleteStatement);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(636);
			match(T__88);
			setState(637);
			tableName();
			setState(640);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__73) {
				{
				setState(638);
				match(T__73);
				setState(639);
				condition(0);
				}
			}

			setState(642);
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
		int _startState = 46;
		enterRecursionRule(_localctx, 46, RULE_condition, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(693);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,57,_ctx) ) {
			case 1:
				{
				setState(645);
				columnName();
				setState(646);
				comparisonOperator();
				setState(647);
				value();
				}
				break;
			case 2:
				{
				setState(649);
				columnName();
				setState(650);
				match(T__89);
				setState(651);
				match(LPAREN);
				setState(654);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case T__57:
				case T__140:
				case T__141:
				case T__142:
				case T__143:
				case T__144:
				case T__145:
				case T__146:
				case NULL:
				case ARRAY:
				case STRING:
				case NUMBER:
					{
					setState(652);
					value();
					}
					break;
				case LPAREN:
					{
					setState(653);
					subquery();
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(663);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(656);
					match(COMMA);
					setState(659);
					_errHandler.sync(this);
					switch (_input.LA(1)) {
					case T__57:
					case T__140:
					case T__141:
					case T__142:
					case T__143:
					case T__144:
					case T__145:
					case T__146:
					case NULL:
					case ARRAY:
					case STRING:
					case NUMBER:
						{
						setState(657);
						value();
						}
						break;
					case LPAREN:
						{
						setState(658);
						subquery();
						}
						break;
					default:
						throw new NoViableAltException(this);
					}
					}
					}
					setState(665);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(666);
				match(RPAREN);
				}
				break;
			case 3:
				{
				setState(668);
				columnName();
				setState(669);
				match(T__90);
				setState(670);
				value();
				setState(671);
				match(T__91);
				setState(672);
				value();
				}
				break;
			case 4:
				{
				setState(674);
				columnName();
				setState(675);
				match(T__92);
				setState(676);
				match(NULL);
				}
				break;
			case 5:
				{
				setState(678);
				columnName();
				setState(679);
				match(T__93);
				}
				break;
			case 6:
				{
				setState(681);
				columnName();
				setState(682);
				match(T__94);
				setState(683);
				match(STRING);
				}
				break;
			case 7:
				{
				setState(685);
				columnName();
				setState(686);
				match(T__95);
				setState(687);
				match(STRING);
				}
				break;
			case 8:
				{
				setState(689);
				match(NOT);
				setState(690);
				condition(5);
				}
				break;
			case 9:
				{
				setState(691);
				match(T__96);
				}
				break;
			case 10:
				{
				setState(692);
				match(T__97);
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(703);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,59,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(701);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,58,_ctx) ) {
					case 1:
						{
						_localctx = new ConditionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_condition);
						setState(695);
						if (!(precpred(_ctx, 4))) throw new FailedPredicateException(this, "precpred(_ctx, 4)");
						setState(696);
						match(T__91);
						setState(697);
						condition(5);
						}
						break;
					case 2:
						{
						_localctx = new ConditionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_condition);
						setState(698);
						if (!(precpred(_ctx, 3))) throw new FailedPredicateException(this, "precpred(_ctx, 3)");
						setState(699);
						match(T__85);
						setState(700);
						condition(4);
						}
						break;
					}
					} 
				}
				setState(705);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,59,_ctx);
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
		enterRule(_localctx, 48, RULE_subquery);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(706);
			match(LPAREN);
			setState(707);
			sqlScript();
			setState(708);
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
		enterRule(_localctx, 50, RULE_comparisonOperator);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(710);
			_la = _input.LA(1);
			if ( !(((((_la - 99)) & ~0x3f) == 0 && ((1L << (_la - 99)) & 4095L) != 0) || _la==EQUALS) ) {
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
		enterRule(_localctx, 52, RULE_arithmeticOperator);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(712);
			_la = _input.LA(1);
			if ( !(((((_la - 111)) & ~0x3f) == 0 && ((1L << (_la - 111)) & 63L) != 0)) ) {
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
		enterRule(_localctx, 54, RULE_sqlScript);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(726);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__6 || ((((_la - 85)) & ~0x3f) == 0 && ((1L << (_la - 85)) & 30064771093L) != 0) || _la==CREATE) {
				{
				setState(724);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,60,_ctx) ) {
				case 1:
					{
					setState(714);
					createTableStatement();
					}
					break;
				case 2:
					{
					setState(715);
					createSchemaStatement();
					}
					break;
				case 3:
					{
					setState(716);
					createViewStatement();
					}
					break;
				case 4:
					{
					setState(717);
					createFunctionStatement();
					}
					break;
				case 5:
					{
					setState(718);
					createIndexStatement();
					}
					break;
				case 6:
					{
					setState(719);
					createRuleStatement();
					}
					break;
				case 7:
					{
					setState(720);
					triggerStatement();
					}
					break;
				case 8:
					{
					setState(721);
					alterTableStatement();
					}
					break;
				case 9:
					{
					setState(722);
					dataManipulationStatement();
					}
					break;
				case 10:
					{
					setState(723);
					createPolicyStatement();
					}
					break;
				}
				}
				setState(728);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(729);
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
		enterRule(_localctx, 56, RULE_createSchemaStatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(731);
			match(T__116);
			setState(732);
			match(IDENTIFIER);
			setState(733);
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
		enterRule(_localctx, 58, RULE_createViewStatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(735);
			match(T__117);
			setState(736);
			match(IDENTIFIER);
			setState(737);
			match(AS);
			setState(738);
			selectStatement();
			setState(739);
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
		enterRule(_localctx, 60, RULE_createFunctionStatement);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(741);
			match(T__118);
			setState(742);
			match(IDENTIFIER);
			setState(743);
			match(LPAREN);
			setState(752);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==IDENTIFIER) {
				{
				setState(744);
				parameter();
				setState(749);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(745);
					match(COMMA);
					setState(746);
					parameter();
					}
					}
					setState(751);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
			}

			setState(754);
			match(RPAREN);
			setState(755);
			match(T__119);
			setState(756);
			dataType();
			setState(757);
			match(AS);
			setState(758);
			match(DOLLAR_QUOTE);
			setState(759);
			plpgsqlBlock();
			setState(760);
			match(DOLLAR_QUOTE);
			setState(761);
			match(T__120);
			setState(762);
			match(IDENTIFIER);
			setState(763);
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
		enterRule(_localctx, 62, RULE_parameter);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(765);
			match(IDENTIFIER);
			setState(766);
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
		enterRule(_localctx, 64, RULE_selectStatement);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(768);
			match(SELECT);
			setState(769);
			selectList();
			setState(779);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__121) {
				{
				setState(770);
				match(T__121);
				setState(771);
				tableReference();
				setState(776);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(772);
					match(COMMA);
					setState(773);
					tableReference();
					}
					}
					setState(778);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
			}

			setState(782);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__73) {
				{
				setState(781);
				whereClause();
				}
			}

			setState(785);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__122) {
				{
				setState(784);
				orderByClause();
				}
			}

			setState(788);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__125) {
				{
				setState(787);
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
		enterRule(_localctx, 66, RULE_selectList);
		int _la;
		try {
			setState(799);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__112:
				enterOuterAlt(_localctx, 1);
				{
				setState(790);
				match(T__112);
				}
				break;
			case IDENTIFIER:
				enterOuterAlt(_localctx, 2);
				{
				setState(791);
				columnName();
				setState(796);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(792);
					match(COMMA);
					setState(793);
					columnName();
					}
					}
					setState(798);
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
		enterRule(_localctx, 68, RULE_tableReference);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(801);
			tableName();
			setState(804);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==AS) {
				{
				setState(802);
				match(AS);
				setState(803);
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
		enterRule(_localctx, 70, RULE_whereClause);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(806);
			match(T__73);
			setState(807);
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
		enterRule(_localctx, 72, RULE_orderByClause);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(809);
			match(T__122);
			setState(810);
			columnName();
			setState(812);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__123 || _la==T__124) {
				{
				setState(811);
				_la = _input.LA(1);
				if ( !(_la==T__123 || _la==T__124) ) {
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
		enterRule(_localctx, 74, RULE_limitClause);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(814);
			match(T__125);
			setState(815);
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
		public TerminalNode LPAREN() { return getToken(PostgreSQLParser.LPAREN, 0); }
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
		enterRule(_localctx, 76, RULE_columnNameList);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(817);
			match(LPAREN);
			setState(818);
			columnName();
			setState(823);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,73,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(819);
					match(COMMA);
					setState(820);
					columnName();
					}
					} 
				}
				setState(825);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,73,_ctx);
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
		enterRule(_localctx, 78, RULE_foreignTable);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(832);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,75,_ctx) ) {
			case 1:
				{
				setState(827);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==IDENTIFIER) {
					{
					setState(826);
					schemaName();
					}
				}

				setState(829);
				match(DOT);
				setState(830);
				tableName();
				}
				break;
			case 2:
				{
				setState(831);
				tableName();
				}
				break;
			}
			setState(834);
			match(LPAREN);
			setState(835);
			columnName();
			setState(840);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(836);
				match(COMMA);
				setState(837);
				columnName();
				}
				}
				setState(842);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(843);
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
		enterRule(_localctx, 80, RULE_conflictAction);
		int _la;
		try {
			setState(855);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__126:
				enterOuterAlt(_localctx, 1);
				{
				setState(845);
				match(T__126);
				}
				break;
			case T__127:
				enterOuterAlt(_localctx, 2);
				{
				setState(846);
				match(T__127);
				setState(847);
				match(SET);
				setState(848);
				columnName();
				setState(849);
				match(EQUALS);
				setState(850);
				value();
				setState(853);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__73) {
					{
					setState(851);
					match(T__73);
					setState(852);
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
		enterRule(_localctx, 82, RULE_schemaName);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(857);
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
		enterRule(_localctx, 84, RULE_tableName);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(862);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,79,_ctx) ) {
			case 1:
				{
				setState(859);
				schemaName();
				setState(860);
				match(DOT);
				}
				break;
			}
			setState(864);
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
		enterRule(_localctx, 86, RULE_columnName);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(866);
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
		enterRule(_localctx, 88, RULE_relationshipType);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(868);
			_la = _input.LA(1);
			if ( !(((((_la - 129)) & ~0x3f) == 0 && ((1L << (_la - 129)) & 15L) != 0)) ) {
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
		enterRule(_localctx, 90, RULE_decimalType);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(870);
			_la = _input.LA(1);
			if ( !(_la==T__46 || _la==DECIMAL) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			setState(878);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,81,_ctx) ) {
			case 1:
				{
				setState(871);
				match(LPAREN);
				setState(872);
				((DecimalTypeContext)_localctx).precision = match(NUMBER);
				setState(875);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==COMMA) {
					{
					setState(873);
					match(COMMA);
					setState(874);
					((DecimalTypeContext)_localctx).scale = match(NUMBER);
					}
				}

				setState(877);
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
		enterRule(_localctx, 92, RULE_partitionClause);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(880);
			match(T__132);
			setState(881);
			partitionStrategy();
			setState(882);
			match(LPAREN);
			setState(883);
			columnName();
			setState(884);
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
		enterRule(_localctx, 94, RULE_partitionStrategy);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(886);
			_la = _input.LA(1);
			if ( !(((((_la - 134)) & ~0x3f) == 0 && ((1L << (_la - 134)) & 7L) != 0)) ) {
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
		enterRule(_localctx, 96, RULE_createPartitionStatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(888);
			match(CREATE);
			setState(889);
			match(TABLE);
			setState(890);
			tableName();
			setState(891);
			match(T__0);
			setState(892);
			tableName();
			setState(893);
			partitionValuesClause();
			setState(894);
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
		enterRule(_localctx, 98, RULE_partitionValuesClause);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(896);
			match(T__136);
			setState(922);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__121:
				{
				setState(897);
				match(T__121);
				setState(898);
				match(LPAREN);
				setState(899);
				value();
				setState(900);
				match(RPAREN);
				setState(901);
				match(TO);
				setState(902);
				match(LPAREN);
				setState(903);
				value();
				setState(904);
				match(RPAREN);
				}
				break;
			case T__89:
				{
				setState(906);
				match(T__89);
				setState(907);
				match(LPAREN);
				setState(908);
				value();
				setState(913);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(909);
					match(COMMA);
					setState(910);
					value();
					}
					}
					setState(915);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(916);
				match(RPAREN);
				}
				break;
			case T__137:
				{
				setState(918);
				match(T__137);
				setState(919);
				match(NUMBER);
				setState(920);
				match(T__138);
				setState(921);
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
		enterRule(_localctx, 100, RULE_partitionOfClause);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(924);
			match(T__0);
			setState(925);
			tableName();
			setState(926);
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
		enterRule(_localctx, 102, RULE_createIndexStatement);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(928);
			match(CREATE);
			setState(929);
			match(T__139);
			setState(930);
			match(IDENTIFIER);
			setState(931);
			match(ON);
			setState(932);
			tableName();
			setState(933);
			match(LPAREN);
			setState(934);
			columnName();
			setState(939);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(935);
				match(COMMA);
				setState(936);
				columnName();
				}
				}
				setState(941);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(942);
			match(RPAREN);
			setState(944);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__13) {
				{
				setState(943);
				indexOptionsClause();
				}
			}

			setState(946);
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
		enterRule(_localctx, 104, RULE_indexOptionsClause);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(948);
			match(T__13);
			setState(949);
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
		int _startState = 106;
		enterRecursionRule(_localctx, 106, RULE_expression, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(960);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,86,_ctx) ) {
			case 1:
				{
				setState(952);
				value();
				}
				break;
			case 2:
				{
				setState(953);
				columnName();
				}
				break;
			case 3:
				{
				setState(954);
				anyExpression();
				}
				break;
			case 4:
				{
				setState(955);
				arrayConstructor();
				}
				break;
			case 5:
				{
				setState(956);
				match(LPAREN);
				setState(957);
				expression(0);
				setState(958);
				match(RPAREN);
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(983);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,89,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(981);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,88,_ctx) ) {
					case 1:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(962);
						if (!(precpred(_ctx, 5))) throw new FailedPredicateException(this, "precpred(_ctx, 5)");
						setState(963);
						_la = _input.LA(1);
						if ( !(((((_la - 111)) & ~0x3f) == 0 && ((1L << (_la - 111)) & 15L) != 0)) ) {
						_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(964);
						expression(6);
						}
						break;
					case 2:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(965);
						if (!(precpred(_ctx, 4))) throw new FailedPredicateException(this, "precpred(_ctx, 4)");
						setState(966);
						comparisonOperator();
						setState(967);
						expression(5);
						}
						break;
					case 3:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(969);
						if (!(precpred(_ctx, 3))) throw new FailedPredicateException(this, "precpred(_ctx, 3)");
						setState(970);
						match(T__91);
						setState(971);
						expression(4);
						}
						break;
					case 4:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(972);
						if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
						setState(973);
						match(T__85);
						setState(974);
						expression(3);
						}
						break;
					case 5:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(975);
						if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
						setState(976);
						match(DOUBLE_COLON);
						setState(979);
						_errHandler.sync(this);
						switch ( getInterpreter().adaptivePredict(_input,87,_ctx) ) {
						case 1:
							{
							setState(977);
							typeName();
							}
							break;
						case 2:
							{
							setState(978);
							dataType();
							}
							break;
						}
						}
						break;
					}
					} 
				}
				setState(985);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,89,_ctx);
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
		enterRule(_localctx, 108, RULE_value);
		try {
			setState(998);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case STRING:
				enterOuterAlt(_localctx, 1);
				{
				setState(986);
				match(STRING);
				}
				break;
			case NUMBER:
				enterOuterAlt(_localctx, 2);
				{
				setState(987);
				match(NUMBER);
				}
				break;
			case T__140:
				enterOuterAlt(_localctx, 3);
				{
				setState(988);
				match(T__140);
				}
				break;
			case T__141:
				enterOuterAlt(_localctx, 4);
				{
				setState(989);
				match(T__141);
				}
				break;
			case NULL:
				enterOuterAlt(_localctx, 5);
				{
				setState(990);
				match(NULL);
				}
				break;
			case T__142:
				enterOuterAlt(_localctx, 6);
				{
				setState(991);
				match(T__142);
				}
				break;
			case T__143:
				enterOuterAlt(_localctx, 7);
				{
				setState(992);
				match(T__143);
				}
				break;
			case T__144:
				enterOuterAlt(_localctx, 8);
				{
				setState(993);
				match(T__144);
				}
				break;
			case T__145:
				enterOuterAlt(_localctx, 9);
				{
				setState(994);
				match(T__145);
				}
				break;
			case T__146:
				enterOuterAlt(_localctx, 10);
				{
				setState(995);
				match(T__146);
				}
				break;
			case T__57:
				enterOuterAlt(_localctx, 11);
				{
				setState(996);
				intervalLiteral();
				}
				break;
			case ARRAY:
				enterOuterAlt(_localctx, 12);
				{
				setState(997);
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
		enterRule(_localctx, 110, RULE_expressionList);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1000);
			expression(0);
			setState(1005);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(1001);
				match(COMMA);
				setState(1002);
				expression(0);
				}
				}
				setState(1007);
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
		enterRule(_localctx, 112, RULE_arrayConstructor);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1008);
			match(ARRAY);
			setState(1009);
			match(LBRACKET);
			setState(1010);
			expressionList();
			setState(1011);
			match(RBRACKET);
			setState(1014);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,92,_ctx) ) {
			case 1:
				{
				setState(1012);
				match(DOUBLE_COLON);
				setState(1013);
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
		enterRule(_localctx, 114, RULE_createPolicyStatement);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1016);
			match(CREATE);
			setState(1017);
			match(T__147);
			setState(1018);
			match(IDENTIFIER);
			setState(1019);
			match(ON);
			setState(1020);
			tableName();
			setState(1023);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==FOR) {
				{
				setState(1021);
				match(FOR);
				setState(1022);
				_la = _input.LA(1);
				if ( !(_la==T__83 || _la==T__84 || ((((_la - 149)) & ~0x3f) == 0 && ((1L << (_la - 149)) & 16842753L) != 0)) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				}
			}

			setState(1030);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==USING) {
				{
				setState(1025);
				match(USING);
				setState(1026);
				match(LPAREN);
				setState(1027);
				condition(0);
				setState(1028);
				match(RPAREN);
				}
			}

			setState(1037);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__149) {
				{
				setState(1032);
				match(T__149);
				setState(1033);
				match(LPAREN);
				setState(1034);
				condition(0);
				setState(1035);
				match(RPAREN);
				}
			}

			setState(1039);
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
		enterRule(_localctx, 116, RULE_plpgsqlBlock);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1041);
			match(T__150);
			setState(1045);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (((((_la - 153)) & ~0x3f) == 0 && ((1L << (_la - 153)) & 576460752311812097L) != 0)) {
				{
				{
				setState(1042);
				statement();
				}
				}
				setState(1047);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(1048);
			match(T__151);
			setState(1049);
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
		enterRule(_localctx, 118, RULE_statement);
		try {
			setState(1068);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case IDENTIFIER:
				enterOuterAlt(_localctx, 1);
				{
				setState(1051);
				columnName();
				setState(1052);
				match(DOT);
				setState(1053);
				columnName();
				setState(1054);
				match(EQUALS);
				setState(1055);
				value();
				setState(1056);
				match(SEMICOLON);
				}
				break;
			case T__152:
				enterOuterAlt(_localctx, 2);
				{
				setState(1058);
				match(T__152);
				setState(1059);
				match(NEW);
				setState(1060);
				match(SEMICOLON);
				}
				break;
			case NEW:
				enterOuterAlt(_localctx, 3);
				{
				setState(1061);
				match(NEW);
				setState(1062);
				match(DOT);
				setState(1063);
				columnName();
				setState(1064);
				match(EQUALS);
				setState(1065);
				value();
				setState(1066);
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
		enterRule(_localctx, 120, RULE_createRuleStatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1070);
			match(CREATE);
			setState(1071);
			match(RULE);
			setState(1072);
			match(IDENTIFIER);
			setState(1073);
			match(AS);
			setState(1074);
			match(ON);
			setState(1075);
			ruleEvent();
			setState(1076);
			match(TO);
			setState(1077);
			tableName();
			setState(1078);
			match(DO);
			setState(1079);
			ruleAction();
			setState(1080);
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
		enterRule(_localctx, 122, RULE_ruleEvent);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1082);
			_la = _input.LA(1);
			if ( !(_la==T__83 || _la==T__84 || _la==DELETE || _la==SELECT) ) {
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
		enterRule(_localctx, 124, RULE_ruleAction);
		try {
			setState(1087);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__153:
				enterOuterAlt(_localctx, 1);
				{
				setState(1084);
				match(T__153);
				}
				break;
			case INSTEAD:
				enterOuterAlt(_localctx, 2);
				{
				setState(1085);
				match(INSTEAD);
				setState(1086);
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
		enterRule(_localctx, 126, RULE_sqlAction);
		int _la;
		try {
			setState(1122);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__86:
				enterOuterAlt(_localctx, 1);
				{
				setState(1089);
				match(T__86);
				setState(1090);
				tableName();
				setState(1091);
				match(LPAREN);
				setState(1092);
				columnName();
				setState(1097);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(1093);
					match(COMMA);
					setState(1094);
					columnName();
					}
					}
					setState(1099);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(1100);
				match(RPAREN);
				setState(1101);
				match(T__87);
				setState(1102);
				match(LPAREN);
				setState(1103);
				value();
				setState(1108);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(1104);
					match(COMMA);
					setState(1105);
					value();
					}
					}
					setState(1110);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(1111);
				match(RPAREN);
				}
				break;
			case T__84:
				enterOuterAlt(_localctx, 2);
				{
				setState(1113);
				match(T__84);
				setState(1114);
				tableName();
				setState(1115);
				match(SET);
				setState(1116);
				columnName();
				setState(1117);
				match(EQUALS);
				setState(1118);
				value();
				}
				break;
			case T__88:
				enterOuterAlt(_localctx, 3);
				{
				setState(1120);
				match(T__88);
				setState(1121);
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
		enterRule(_localctx, 128, RULE_defaultValue);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1124);
			match(DEFAULT);
			setState(1129);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case EOF:
				{
				}
				break;
			case T__111:
			case DIGIT:
				{
				setState(1126);
				numericLiteral();
				}
				break;
			case T__140:
			case T__141:
				{
				setState(1127);
				booleanLiteral();
				}
				break;
			case T__142:
				{
				setState(1128);
				match(T__142);
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
		enterRule(_localctx, 130, RULE_numericLiteral);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1132);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__111) {
				{
				setState(1131);
				match(T__111);
				}
			}

			setState(1135); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(1134);
				match(DIGIT);
				}
				}
				setState(1137); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==DIGIT );
			setState(1145);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==DOT) {
				{
				setState(1139);
				match(DOT);
				setState(1141); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(1140);
					match(DIGIT);
					}
					}
					setState(1143); 
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
		enterRule(_localctx, 132, RULE_booleanLiteral);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1147);
			_la = _input.LA(1);
			if ( !(_la==T__140 || _la==T__141) ) {
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
		enterRule(_localctx, 134, RULE_foreignKeyAction);
		try {
			setState(1153);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case ON_DELETE:
				enterOuterAlt(_localctx, 1);
				{
				setState(1149);
				match(ON_DELETE);
				setState(1150);
				((ForeignKeyActionContext)_localctx).fkAction = referentialAction();
				}
				break;
			case ON_UPDATE:
				enterOuterAlt(_localctx, 2);
				{
				setState(1151);
				match(ON_UPDATE);
				setState(1152);
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
		enterRule(_localctx, 136, RULE_referentialAction);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1155);
			_la = _input.LA(1);
			if ( !(((((_la - 200)) & ~0x3f) == 0 && ((1L << (_la - 200)) & 31L) != 0)) ) {
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
		enterRule(_localctx, 138, RULE_anyExpression);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1157);
			match(ANY);
			setState(1158);
			match(LPAREN);
			setState(1159);
			arrayConstructor();
			setState(1160);
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
		enterRule(_localctx, 140, RULE_intervalLiteral);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1162);
			match(T__57);
			setState(1163);
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
		enterRule(_localctx, 142, RULE_typeName);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1165);
			match(IDENTIFIER);
			setState(1168);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,108,_ctx) ) {
			case 1:
				{
				setState(1166);
				match(DOT);
				setState(1167);
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
		case 23:
			return condition_sempred((ConditionContext)_localctx, predIndex);
		case 53:
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
		"\u0004\u0001\u00d8\u0493\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001"+
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
		"E\u0002F\u0007F\u0002G\u0007G\u0001\u0000\u0001\u0000\u0001\u0000\u0001"+
		"\u0000\u0001\u0000\u0001\u0000\u0003\u0000\u0097\b\u0000\u0001\u0000\u0001"+
		"\u0000\u0001\u0000\u0003\u0000\u009c\b\u0000\u0005\u0000\u009e\b\u0000"+
		"\n\u0000\f\u0000\u00a1\t\u0000\u0001\u0000\u0001\u0000\u0003\u0000\u00a5"+
		"\b\u0000\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0000\u0001"+
		"\u0000\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0000\u0003\u0000\u00b1"+
		"\b\u0000\u0001\u0001\u0001\u0001\u0001\u0001\u0005\u0001\u00b6\b\u0001"+
		"\n\u0001\f\u0001\u00b9\t\u0001\u0001\u0001\u0001\u0001\u0003\u0001\u00bd"+
		"\b\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001"+
		"\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0003"+
		"\u0001\u00ca\b\u0001\u0001\u0001\u0005\u0001\u00cd\b\u0001\n\u0001\f\u0001"+
		"\u00d0\t\u0001\u0003\u0001\u00d2\b\u0001\u0001\u0002\u0001\u0002\u0001"+
		"\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001"+
		"\u0003\u0001\u0003\u0001\u0003\u0001\u0004\u0001\u0004\u0001\u0004\u0001"+
		"\u0004\u0001\u0004\u0001\u0004\u0005\u0004\u00e5\b\u0004\n\u0004\f\u0004"+
		"\u00e8\t\u0004\u0001\u0004\u0003\u0004\u00eb\b\u0004\u0001\u0005\u0001"+
		"\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001"+
		"\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001"+
		"\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001"+
		"\u0005\u0005\u0005\u0101\b\u0005\n\u0005\f\u0005\u0104\t\u0005\u0001\u0005"+
		"\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005"+
		"\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005"+
		"\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0003\u0005\u0117\b\u0005"+
		"\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005"+
		"\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005"+
		"\u0001\u0005\u0001\u0005\u0003\u0005\u0127\b\u0005\u0001\u0006\u0001\u0006"+
		"\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006"+
		"\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006"+
		"\u0001\u0006\u0004\u0006\u0138\b\u0006\u000b\u0006\f\u0006\u0139\u0001"+
		"\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001"+
		"\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001"+
		"\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0003\u0007\u014d"+
		"\b\u0007\u0001\u0007\u0003\u0007\u0150\b\u0007\u0001\u0007\u0001\u0007"+
		"\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0003\u0007\u0158\b\u0007"+
		"\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0003\u0007\u015e\b\u0007"+
		"\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007"+
		"\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007"+
		"\u0003\u0007\u016c\b\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007"+
		"\u0001\u0007\u0001\u0007\u0001\u0007\u0003\u0007\u0175\b\u0007\u0001\u0007"+
		"\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007"+
		"\u0001\u0007\u0001\u0007\u0005\u0007\u0180\b\u0007\n\u0007\f\u0007\u0183"+
		"\t\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001"+
		"\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001"+
		"\u0007\u0003\u0007\u0191\b\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001"+
		"\u0007\u0003\u0007\u0197\b\u0007\u0001\u0007\u0001\u0007\u0003\u0007\u019b"+
		"\b\u0007\u0001\b\u0001\b\u0001\t\u0001\t\u0001\n\u0001\n\u0001\n\u0001"+
		"\n\u0001\n\u0003\n\u01a6\b\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001"+
		"\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0003\n\u01b3\b\n\u0001\n\u0001"+
		"\n\u0001\n\u0001\n\u0001\n\u0003\n\u01ba\b\n\u0001\n\u0001\n\u0001\n\u0001"+
		"\n\u0003\n\u01c0\b\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0003"+
		"\n\u01c8\b\n\u0001\n\u0001\n\u0001\n\u0001\n\u0003\n\u01ce\b\n\u0001\n"+
		"\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0003\n\u01d8"+
		"\b\n\u0003\n\u01da\b\n\u0001\u000b\u0001\u000b\u0003\u000b\u01de\b\u000b"+
		"\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0003\u000b\u01e4\b\u000b"+
		"\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b"+
		"\u0003\u000b\u01ec\b\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0005\u000b"+
		"\u01f1\b\u000b\n\u000b\f\u000b\u01f4\t\u000b\u0001\u000b\u0001\u000b\u0003"+
		"\u000b\u01f8\b\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0003"+
		"\u000b\u01fe\b\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001"+
		"\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001"+
		"\u000b\u0001\u000b\u0003\u000b\u020c\b\u000b\u0001\f\u0001\f\u0001\f\u0005"+
		"\f\u0211\b\f\n\f\f\f\u0214\t\f\u0001\r\u0001\r\u0001\r\u0001\r\u0001\u000e"+
		"\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e"+
		"\u0003\u000e\u0221\b\u000e\u0003\u000e\u0223\b\u000e\u0001\u000f\u0001"+
		"\u000f\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001"+
		"\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001"+
		"\u0010\u0001\u0010\u0001\u0010\u0005\u0010\u0235\b\u0010\n\u0010\f\u0010"+
		"\u0238\t\u0010\u0003\u0010\u023a\b\u0010\u0001\u0010\u0001\u0010\u0001"+
		"\u0010\u0001\u0011\u0001\u0011\u0001\u0012\u0001\u0012\u0001\u0012\u0005"+
		"\u0012\u0244\b\u0012\n\u0012\f\u0012\u0247\t\u0012\u0001\u0013\u0001\u0013"+
		"\u0001\u0013\u0003\u0013\u024c\b\u0013\u0001\u0014\u0001\u0014\u0001\u0014"+
		"\u0001\u0014\u0001\u0014\u0001\u0014\u0005\u0014\u0254\b\u0014\n\u0014"+
		"\f\u0014\u0257\t\u0014\u0001\u0014\u0001\u0014\u0001\u0014\u0001\u0014"+
		"\u0001\u0014\u0001\u0014\u0005\u0014\u025f\b\u0014\n\u0014\f\u0014\u0262"+
		"\t\u0014\u0001\u0014\u0001\u0014\u0001\u0014\u0001\u0015\u0001\u0015\u0001"+
		"\u0015\u0001\u0015\u0001\u0015\u0001\u0015\u0001\u0015\u0001\u0015\u0001"+
		"\u0015\u0001\u0015\u0001\u0015\u0005\u0015\u0272\b\u0015\n\u0015\f\u0015"+
		"\u0275\t\u0015\u0001\u0015\u0001\u0015\u0003\u0015\u0279\b\u0015\u0001"+
		"\u0015\u0001\u0015\u0001\u0016\u0001\u0016\u0001\u0016\u0001\u0016\u0003"+
		"\u0016\u0281\b\u0016\u0001\u0016\u0001\u0016\u0001\u0017\u0001\u0017\u0001"+
		"\u0017\u0001\u0017\u0001\u0017\u0001\u0017\u0001\u0017\u0001\u0017\u0001"+
		"\u0017\u0001\u0017\u0003\u0017\u028f\b\u0017\u0001\u0017\u0001\u0017\u0001"+
		"\u0017\u0003\u0017\u0294\b\u0017\u0005\u0017\u0296\b\u0017\n\u0017\f\u0017"+
		"\u0299\t\u0017\u0001\u0017\u0001\u0017\u0001\u0017\u0001\u0017\u0001\u0017"+
		"\u0001\u0017\u0001\u0017\u0001\u0017\u0001\u0017\u0001\u0017\u0001\u0017"+
		"\u0001\u0017\u0001\u0017\u0001\u0017\u0001\u0017\u0001\u0017\u0001\u0017"+
		"\u0001\u0017\u0001\u0017\u0001\u0017\u0001\u0017\u0001\u0017\u0001\u0017"+
		"\u0001\u0017\u0001\u0017\u0001\u0017\u0001\u0017\u0003\u0017\u02b6\b\u0017"+
		"\u0001\u0017\u0001\u0017\u0001\u0017\u0001\u0017\u0001\u0017\u0001\u0017"+
		"\u0005\u0017\u02be\b\u0017\n\u0017\f\u0017\u02c1\t\u0017\u0001\u0018\u0001"+
		"\u0018\u0001\u0018\u0001\u0018\u0001\u0019\u0001\u0019\u0001\u001a\u0001"+
		"\u001a\u0001\u001b\u0001\u001b\u0001\u001b\u0001\u001b\u0001\u001b\u0001"+
		"\u001b\u0001\u001b\u0001\u001b\u0001\u001b\u0001\u001b\u0005\u001b\u02d5"+
		"\b\u001b\n\u001b\f\u001b\u02d8\t\u001b\u0001\u001b\u0001\u001b\u0001\u001c"+
		"\u0001\u001c\u0001\u001c\u0001\u001c\u0001\u001d\u0001\u001d\u0001\u001d"+
		"\u0001\u001d\u0001\u001d\u0001\u001d\u0001\u001e\u0001\u001e\u0001\u001e"+
		"\u0001\u001e\u0001\u001e\u0001\u001e\u0005\u001e\u02ec\b\u001e\n\u001e"+
		"\f\u001e\u02ef\t\u001e\u0003\u001e\u02f1\b\u001e\u0001\u001e\u0001\u001e"+
		"\u0001\u001e\u0001\u001e\u0001\u001e\u0001\u001e\u0001\u001e\u0001\u001e"+
		"\u0001\u001e\u0001\u001e\u0001\u001e\u0001\u001f\u0001\u001f\u0001\u001f"+
		"\u0001 \u0001 \u0001 \u0001 \u0001 \u0001 \u0005 \u0307\b \n \f \u030a"+
		"\t \u0003 \u030c\b \u0001 \u0003 \u030f\b \u0001 \u0003 \u0312\b \u0001"+
		" \u0003 \u0315\b \u0001!\u0001!\u0001!\u0001!\u0005!\u031b\b!\n!\f!\u031e"+
		"\t!\u0003!\u0320\b!\u0001\"\u0001\"\u0001\"\u0003\"\u0325\b\"\u0001#\u0001"+
		"#\u0001#\u0001$\u0001$\u0001$\u0003$\u032d\b$\u0001%\u0001%\u0001%\u0001"+
		"&\u0001&\u0001&\u0001&\u0005&\u0336\b&\n&\f&\u0339\t&\u0001\'\u0003\'"+
		"\u033c\b\'\u0001\'\u0001\'\u0001\'\u0003\'\u0341\b\'\u0001\'\u0001\'\u0001"+
		"\'\u0001\'\u0005\'\u0347\b\'\n\'\f\'\u034a\t\'\u0001\'\u0001\'\u0001("+
		"\u0001(\u0001(\u0001(\u0001(\u0001(\u0001(\u0001(\u0003(\u0356\b(\u0003"+
		"(\u0358\b(\u0001)\u0001)\u0001*\u0001*\u0001*\u0003*\u035f\b*\u0001*\u0001"+
		"*\u0001+\u0001+\u0001,\u0001,\u0001-\u0001-\u0001-\u0001-\u0001-\u0003"+
		"-\u036c\b-\u0001-\u0003-\u036f\b-\u0001.\u0001.\u0001.\u0001.\u0001.\u0001"+
		".\u0001/\u0001/\u00010\u00010\u00010\u00010\u00010\u00010\u00010\u0001"+
		"0\u00011\u00011\u00011\u00011\u00011\u00011\u00011\u00011\u00011\u0001"+
		"1\u00011\u00011\u00011\u00011\u00011\u00051\u0390\b1\n1\f1\u0393\t1\u0001"+
		"1\u00011\u00011\u00011\u00011\u00011\u00031\u039b\b1\u00012\u00012\u0001"+
		"2\u00012\u00013\u00013\u00013\u00013\u00013\u00013\u00013\u00013\u0001"+
		"3\u00053\u03aa\b3\n3\f3\u03ad\t3\u00013\u00013\u00033\u03b1\b3\u00013"+
		"\u00013\u00014\u00014\u00014\u00015\u00015\u00015\u00015\u00015\u0001"+
		"5\u00015\u00015\u00015\u00035\u03c1\b5\u00015\u00015\u00015\u00015\u0001"+
		"5\u00015\u00015\u00015\u00015\u00015\u00015\u00015\u00015\u00015\u0001"+
		"5\u00015\u00015\u00035\u03d4\b5\u00055\u03d6\b5\n5\f5\u03d9\t5\u00016"+
		"\u00016\u00016\u00016\u00016\u00016\u00016\u00016\u00016\u00016\u0001"+
		"6\u00016\u00036\u03e7\b6\u00017\u00017\u00017\u00057\u03ec\b7\n7\f7\u03ef"+
		"\t7\u00018\u00018\u00018\u00018\u00018\u00018\u00038\u03f7\b8\u00019\u0001"+
		"9\u00019\u00019\u00019\u00019\u00019\u00039\u0400\b9\u00019\u00019\u0001"+
		"9\u00019\u00019\u00039\u0407\b9\u00019\u00019\u00019\u00019\u00019\u0003"+
		"9\u040e\b9\u00019\u00019\u0001:\u0001:\u0005:\u0414\b:\n:\f:\u0417\t:"+
		"\u0001:\u0001:\u0001:\u0001;\u0001;\u0001;\u0001;\u0001;\u0001;\u0001"+
		";\u0001;\u0001;\u0001;\u0001;\u0001;\u0001;\u0001;\u0001;\u0001;\u0001"+
		";\u0003;\u042d\b;\u0001<\u0001<\u0001<\u0001<\u0001<\u0001<\u0001<\u0001"+
		"<\u0001<\u0001<\u0001<\u0001<\u0001=\u0001=\u0001>\u0001>\u0001>\u0003"+
		">\u0440\b>\u0001?\u0001?\u0001?\u0001?\u0001?\u0001?\u0005?\u0448\b?\n"+
		"?\f?\u044b\t?\u0001?\u0001?\u0001?\u0001?\u0001?\u0001?\u0005?\u0453\b"+
		"?\n?\f?\u0456\t?\u0001?\u0001?\u0001?\u0001?\u0001?\u0001?\u0001?\u0001"+
		"?\u0001?\u0001?\u0001?\u0003?\u0463\b?\u0001@\u0001@\u0001@\u0001@\u0001"+
		"@\u0003@\u046a\b@\u0001A\u0003A\u046d\bA\u0001A\u0004A\u0470\bA\u000b"+
		"A\fA\u0471\u0001A\u0001A\u0004A\u0476\bA\u000bA\fA\u0477\u0003A\u047a"+
		"\bA\u0001B\u0001B\u0001C\u0001C\u0001C\u0001C\u0003C\u0482\bC\u0001D\u0001"+
		"D\u0001E\u0001E\u0001E\u0001E\u0001E\u0001F\u0001F\u0001F\u0001G\u0001"+
		"G\u0001G\u0003G\u0491\bG\u0001G\u0000\u0002.jH\u0000\u0002\u0004\u0006"+
		"\b\n\f\u000e\u0010\u0012\u0014\u0016\u0018\u001a\u001c\u001e \"$&(*,."+
		"02468:<>@BDFHJLNPRTVXZ\\^`bdfhjlnprtvxz|~\u0080\u0082\u0084\u0086\u0088"+
		"\u008a\u008c\u008e\u0000\u0012\u0001\u0000\u0003\u0004\u0001\u0000\u001b"+
		"\u001e\u0001\u0000!#\u0001\u0000\u00c8\u00cb\u0001\u0000MN\u0001\u0000"+
		"\u00c8\u00cc\u0001\u0000QS\u0002\u0000TU\u00a5\u00a5\u0002\u0000cn\u00d0"+
		"\u00d0\u0001\u0000ot\u0001\u0000|}\u0001\u0000\u0081\u0084\u0002\u0000"+
		"//\u00b4\u00b4\u0001\u0000\u0086\u0088\u0001\u0000or\u0004\u0000TU\u0095"+
		"\u0095\u00a5\u00a5\u00ad\u00ad\u0003\u0000TU\u00a5\u00a5\u00ad\u00ad\u0001"+
		"\u0000\u008d\u008e\u0525\u0000\u00b0\u0001\u0000\u0000\u0000\u0002\u00d1"+
		"\u0001\u0000\u0000\u0000\u0004\u00d3\u0001\u0000\u0000\u0000\u0006\u00db"+
		"\u0001\u0000\u0000\u0000\b\u00de\u0001\u0000\u0000\u0000\n\u0126\u0001"+
		"\u0000\u0000\u0000\f\u0137\u0001\u0000\u0000\u0000\u000e\u019a\u0001\u0000"+
		"\u0000\u0000\u0010\u019c\u0001\u0000\u0000\u0000\u0012\u019e\u0001\u0000"+
		"\u0000\u0000\u0014\u01d9\u0001\u0000\u0000\u0000\u0016\u020b\u0001\u0000"+
		"\u0000\u0000\u0018\u020d\u0001\u0000\u0000\u0000\u001a\u0215\u0001\u0000"+
		"\u0000\u0000\u001c\u0222\u0001\u0000\u0000\u0000\u001e\u0224\u0001\u0000"+
		"\u0000\u0000 \u0226\u0001\u0000\u0000\u0000\"\u023e\u0001\u0000\u0000"+
		"\u0000$\u0240\u0001\u0000\u0000\u0000&\u024b\u0001\u0000\u0000\u0000("+
		"\u024d\u0001\u0000\u0000\u0000*\u0266\u0001\u0000\u0000\u0000,\u027c\u0001"+
		"\u0000\u0000\u0000.\u02b5\u0001\u0000\u0000\u00000\u02c2\u0001\u0000\u0000"+
		"\u00002\u02c6\u0001\u0000\u0000\u00004\u02c8\u0001\u0000\u0000\u00006"+
		"\u02d6\u0001\u0000\u0000\u00008\u02db\u0001\u0000\u0000\u0000:\u02df\u0001"+
		"\u0000\u0000\u0000<\u02e5\u0001\u0000\u0000\u0000>\u02fd\u0001\u0000\u0000"+
		"\u0000@\u0300\u0001\u0000\u0000\u0000B\u031f\u0001\u0000\u0000\u0000D"+
		"\u0321\u0001\u0000\u0000\u0000F\u0326\u0001\u0000\u0000\u0000H\u0329\u0001"+
		"\u0000\u0000\u0000J\u032e\u0001\u0000\u0000\u0000L\u0331\u0001\u0000\u0000"+
		"\u0000N\u0340\u0001\u0000\u0000\u0000P\u0357\u0001\u0000\u0000\u0000R"+
		"\u0359\u0001\u0000\u0000\u0000T\u035e\u0001\u0000\u0000\u0000V\u0362\u0001"+
		"\u0000\u0000\u0000X\u0364\u0001\u0000\u0000\u0000Z\u0366\u0001\u0000\u0000"+
		"\u0000\\\u0370\u0001\u0000\u0000\u0000^\u0376\u0001\u0000\u0000\u0000"+
		"`\u0378\u0001\u0000\u0000\u0000b\u0380\u0001\u0000\u0000\u0000d\u039c"+
		"\u0001\u0000\u0000\u0000f\u03a0\u0001\u0000\u0000\u0000h\u03b4\u0001\u0000"+
		"\u0000\u0000j\u03c0\u0001\u0000\u0000\u0000l\u03e6\u0001\u0000\u0000\u0000"+
		"n\u03e8\u0001\u0000\u0000\u0000p\u03f0\u0001\u0000\u0000\u0000r\u03f8"+
		"\u0001\u0000\u0000\u0000t\u0411\u0001\u0000\u0000\u0000v\u042c\u0001\u0000"+
		"\u0000\u0000x\u042e\u0001\u0000\u0000\u0000z\u043a\u0001\u0000\u0000\u0000"+
		"|\u043f\u0001\u0000\u0000\u0000~\u0462\u0001\u0000\u0000\u0000\u0080\u0464"+
		"\u0001\u0000\u0000\u0000\u0082\u046c\u0001\u0000\u0000\u0000\u0084\u047b"+
		"\u0001\u0000\u0000\u0000\u0086\u0481\u0001\u0000\u0000\u0000\u0088\u0483"+
		"\u0001\u0000\u0000\u0000\u008a\u0485\u0001\u0000\u0000\u0000\u008c\u048a"+
		"\u0001\u0000\u0000\u0000\u008e\u048d\u0001\u0000\u0000\u0000\u0090\u0091"+
		"\u0005\u00b2\u0000\u0000\u0091\u0092\u0005\u00b1\u0000\u0000\u0092\u0093"+
		"\u0003T*\u0000\u0093\u0096\u0005\u009b\u0000\u0000\u0094\u0097\u0003\u0002"+
		"\u0001\u0000\u0095\u0097\u0003\u0016\u000b\u0000\u0096\u0094\u0001\u0000"+
		"\u0000\u0000\u0096\u0095\u0001\u0000\u0000\u0000\u0097\u009f\u0001\u0000"+
		"\u0000\u0000\u0098\u009b\u0005\u00a2\u0000\u0000\u0099\u009c\u0003\u0002"+
		"\u0001\u0000\u009a\u009c\u0003\u0016\u000b\u0000\u009b\u0099\u0001\u0000"+
		"\u0000\u0000\u009b\u009a\u0001\u0000\u0000\u0000\u009c\u009e\u0001\u0000"+
		"\u0000\u0000\u009d\u0098\u0001\u0000\u0000\u0000\u009e\u00a1\u0001\u0000"+
		"\u0000\u0000\u009f\u009d\u0001\u0000\u0000\u0000\u009f\u00a0\u0001\u0000"+
		"\u0000\u0000\u00a0\u00a2\u0001\u0000\u0000\u0000\u00a1\u009f\u0001\u0000"+
		"\u0000\u0000\u00a2\u00a4\u0005\u009c\u0000\u0000\u00a3\u00a5\u0003\\."+
		"\u0000\u00a4\u00a3\u0001\u0000\u0000\u0000\u00a4\u00a5\u0001\u0000\u0000"+
		"\u0000\u00a5\u00a6\u0001\u0000\u0000\u0000\u00a6\u00a7\u0005\u009f\u0000"+
		"\u0000\u00a7\u00b1\u0001\u0000\u0000\u0000\u00a8\u00a9\u0005\u00b2\u0000"+
		"\u0000\u00a9\u00aa\u0005\u00b1\u0000\u0000\u00aa\u00ab\u0003T*\u0000\u00ab"+
		"\u00ac\u0005\u0001\u0000\u0000\u00ac\u00ad\u0003T*\u0000\u00ad\u00ae\u0003"+
		"b1\u0000\u00ae\u00af\u0005\u009f\u0000\u0000\u00af\u00b1\u0001\u0000\u0000"+
		"\u0000\u00b0\u0090\u0001\u0000\u0000\u0000\u00b0\u00a8\u0001\u0000\u0000"+
		"\u0000\u00b1\u0001\u0001\u0000\u0000\u0000\u00b2\u00b3\u0003V+\u0000\u00b3"+
		"\u00b7\u0003\u000e\u0007\u0000\u00b4\u00b6\u0003\u0014\n\u0000\u00b5\u00b4"+
		"\u0001\u0000\u0000\u0000\u00b6\u00b9\u0001\u0000\u0000\u0000\u00b7\u00b5"+
		"\u0001\u0000\u0000\u0000\u00b7\u00b8\u0001\u0000\u0000\u0000\u00b8\u00bc"+
		"\u0001\u0000\u0000\u0000\u00b9\u00b7\u0001\u0000\u0000\u0000\u00ba\u00bd"+
		"\u0003\u0004\u0002\u0000\u00bb\u00bd\u0003\u0006\u0003\u0000\u00bc\u00ba"+
		"\u0001\u0000\u0000\u0000\u00bc\u00bb\u0001\u0000\u0000\u0000\u00bc\u00bd"+
		"\u0001\u0000\u0000\u0000\u00bd\u00d2\u0001\u0000\u0000\u0000\u00be\u00bf"+
		"\u0005\u00ba\u0000\u0000\u00bf\u00c0\u0005\u009b\u0000\u0000\u00c0\u00c1"+
		"\u0003L&\u0000\u00c1\u00c2\u0005\u009c\u0000\u0000\u00c2\u00c3\u0005\u00bd"+
		"\u0000\u0000\u00c3\u00c4\u0003T*\u0000\u00c4\u00c5\u0005\u009b\u0000\u0000"+
		"\u00c5\u00c6\u0003L&\u0000\u00c6\u00c9\u0005\u009c\u0000\u0000\u00c7\u00c8"+
		"\u0005\u00b3\u0000\u0000\u00c8\u00ca\u0003X,\u0000\u00c9\u00c7\u0001\u0000"+
		"\u0000\u0000\u00c9\u00ca\u0001\u0000\u0000\u0000\u00ca\u00ce\u0001\u0000"+
		"\u0000\u0000\u00cb\u00cd\u0003\u001c\u000e\u0000\u00cc\u00cb\u0001\u0000"+
		"\u0000\u0000\u00cd\u00d0\u0001\u0000\u0000\u0000\u00ce\u00cc\u0001\u0000"+
		"\u0000\u0000\u00ce\u00cf\u0001\u0000\u0000\u0000\u00cf\u00d2\u0001\u0000"+
		"\u0000\u0000\u00d0\u00ce\u0001\u0000\u0000\u0000\u00d1\u00b2\u0001\u0000"+
		"\u0000\u0000\u00d1\u00be\u0001\u0000\u0000\u0000\u00d2\u0003\u0001\u0000"+
		"\u0000\u0000\u00d3\u00d4\u0005\u0002\u0000\u0000\u00d4\u00d5\u0007\u0000"+
		"\u0000\u0000\u00d5\u00d6\u0005\u00a8\u0000\u0000\u00d6\u00d7\u0005\u009b"+
		"\u0000\u0000\u00d7\u00d8\u0003j5\u0000\u00d8\u00d9\u0005\u009c\u0000\u0000"+
		"\u00d9\u00da\u0005\u0005\u0000\u0000\u00da\u0005\u0001\u0000\u0000\u0000"+
		"\u00db\u00dc\u0005\u0006\u0000\u0000\u00dc\u00dd\u0005\u00d5\u0000\u0000"+
		"\u00dd\u0007\u0001\u0000\u0000\u0000\u00de\u00df\u0005\u0007\u0000\u0000"+
		"\u00df\u00e0\u0005\u00b1\u0000\u0000\u00e0\u00e1\u0003T*\u0000\u00e1\u00e6"+
		"\u0003\n\u0005\u0000\u00e2\u00e3\u0005\u00a2\u0000\u0000\u00e3\u00e5\u0003"+
		"\n\u0005\u0000\u00e4\u00e2\u0001\u0000\u0000\u0000\u00e5\u00e8\u0001\u0000"+
		"\u0000\u0000\u00e6\u00e4\u0001\u0000\u0000\u0000\u00e6\u00e7\u0001\u0000"+
		"\u0000\u0000\u00e7\u00ea\u0001\u0000\u0000\u0000\u00e8\u00e6\u0001\u0000"+
		"\u0000\u0000\u00e9\u00eb\u0005\u009f\u0000\u0000\u00ea\u00e9\u0001\u0000"+
		"\u0000\u0000\u00ea\u00eb\u0001\u0000\u0000\u0000\u00eb\t\u0001\u0000\u0000"+
		"\u0000\u00ec\u00ed\u0005\b\u0000\u0000\u00ed\u00ee\u0005\t\u0000\u0000"+
		"\u00ee\u0127\u0003\u0002\u0001\u0000\u00ef\u00f0\u0005\n\u0000\u0000\u00f0"+
		"\u00f1\u0005\t\u0000\u0000\u00f1\u0127\u0003V+\u0000\u00f2\u00f3\u0005"+
		"\b\u0000\u0000\u00f3\u00f4\u0005\u00b8\u0000\u0000\u00f4\u00f5\u0005\u00d4"+
		"\u0000\u0000\u00f5\u0127\u0003\u0016\u000b\u0000\u00f6\u00f7\u0005\n\u0000"+
		"\u0000\u00f7\u00f8\u0005\u00b8\u0000\u0000\u00f8\u0127\u0005\u00d4\u0000"+
		"\u0000\u00f9\u00fa\u0005\b\u0000\u0000\u00fa\u00fb\u0005\u00ba\u0000\u0000"+
		"\u00fb\u00fc\u0003L&\u0000\u00fc\u00fd\u0005\u00bd\u0000\u0000\u00fd\u00fe"+
		"\u0003T*\u0000\u00fe\u0102\u0003L&\u0000\u00ff\u0101\u0003\u001c\u000e"+
		"\u0000\u0100\u00ff\u0001\u0000\u0000\u0000\u0101\u0104\u0001\u0000\u0000"+
		"\u0000\u0102\u0100\u0001\u0000\u0000\u0000\u0102\u0103\u0001\u0000\u0000"+
		"\u0000\u0103\u0127\u0001\u0000\u0000\u0000\u0104\u0102\u0001\u0000\u0000"+
		"\u0000\u0105\u0106\u0005\u0007\u0000\u0000\u0106\u0107\u0005\t\u0000\u0000"+
		"\u0107\u0108\u0003V+\u0000\u0108\u0109\u0003\f\u0006\u0000\u0109\u0127"+
		"\u0001\u0000\u0000\u0000\u010a\u010b\u0005\u000b\u0000\u0000\u010b\u010c"+
		"\u0003V+\u0000\u010c\u010d\u0005\u00a9\u0000\u0000\u010d\u010e\u0003V"+
		"+\u0000\u010e\u0127\u0001\u0000\u0000\u0000\u010f\u0110\u0005\f\u0000"+
		"\u0000\u0110\u0127\u0003T*\u0000\u0111\u0116\u0005\u00bc\u0000\u0000\u0112"+
		"\u0113\u0005\r\u0000\u0000\u0113\u0117\u0003R)\u0000\u0114\u0115\u0005"+
		"\u000e\u0000\u0000\u0115\u0117\u0005\u00d4\u0000\u0000\u0116\u0112\u0001"+
		"\u0000\u0000\u0000\u0116\u0114\u0001\u0000\u0000\u0000\u0117\u0127\u0001"+
		"\u0000\u0000\u0000\u0118\u0119\u0005\u000f\u0000\u0000\u0119\u011a\u0005"+
		"\u0010\u0000\u0000\u011a\u011b\u0005\u0011\u0000\u0000\u011b\u0127\u0005"+
		"\u00d4\u0000\u0000\u011c\u011d\u0005\u0012\u0000\u0000\u011d\u011e\u0005"+
		"\u0010\u0000\u0000\u011e\u011f\u0005\u0011\u0000\u0000\u011f\u0127\u0005"+
		"\u00d4\u0000\u0000\u0120\u0121\u0005\u0013\u0000\u0000\u0121\u0122\u0005"+
		"\u00a4\u0000\u0000\u0122\u0127\u0005\u00d4\u0000\u0000\u0123\u0127\u0005"+
		"\u0014\u0000\u0000\u0124\u0125\u0005\u0015\u0000\u0000\u0125\u0127\u0005"+
		"\u00d4\u0000\u0000\u0126\u00ec\u0001\u0000\u0000\u0000\u0126\u00ef\u0001"+
		"\u0000\u0000\u0000\u0126\u00f2\u0001\u0000\u0000\u0000\u0126\u00f6\u0001"+
		"\u0000\u0000\u0000\u0126\u00f9\u0001\u0000\u0000\u0000\u0126\u0105\u0001"+
		"\u0000\u0000\u0000\u0126\u010a\u0001\u0000\u0000\u0000\u0126\u010f\u0001"+
		"\u0000\u0000\u0000\u0126\u0111\u0001\u0000\u0000\u0000\u0126\u0118\u0001"+
		"\u0000\u0000\u0000\u0126\u011c\u0001\u0000\u0000\u0000\u0126\u0120\u0001"+
		"\u0000\u0000\u0000\u0126\u0123\u0001\u0000\u0000\u0000\u0126\u0124\u0001"+
		"\u0000\u0000\u0000\u0127\u000b\u0001\u0000\u0000\u0000\u0128\u0129\u0005"+
		"\u00ca\u0000\u0000\u0129\u0138\u0003l6\u0000\u012a\u0138\u0005\u0016\u0000"+
		"\u0000\u012b\u0138\u0005\u0017\u0000\u0000\u012c\u0138\u0005\u0018\u0000"+
		"\u0000\u012d\u012e\u0005\u0019\u0000\u0000\u012e\u0138\u0005\u00d6\u0000"+
		"\u0000\u012f\u0130\u0005\u001a\u0000\u0000\u0130\u0138\u0007\u0001\u0000"+
		"\u0000\u0131\u0132\u0005\u001f\u0000\u0000\u0132\u0138\u0005\u00d4\u0000"+
		"\u0000\u0133\u0134\u0005 \u0000\u0000\u0134\u0138\u0007\u0002\u0000\u0000"+
		"\u0135\u0136\u0005$\u0000\u0000\u0136\u0138\u0003\u000e\u0007\u0000\u0137"+
		"\u0128\u0001\u0000\u0000\u0000\u0137\u012a\u0001\u0000\u0000\u0000\u0137"+
		"\u012b\u0001\u0000\u0000\u0000\u0137\u012c\u0001\u0000\u0000\u0000\u0137"+
		"\u012d\u0001\u0000\u0000\u0000\u0137\u012f\u0001\u0000\u0000\u0000\u0137"+
		"\u0131\u0001\u0000\u0000\u0000\u0137\u0133\u0001\u0000\u0000\u0000\u0137"+
		"\u0135\u0001\u0000\u0000\u0000\u0138\u0139\u0001\u0000\u0000\u0000\u0139"+
		"\u0137\u0001\u0000\u0000\u0000\u0139\u013a\u0001\u0000\u0000\u0000\u013a"+
		"\r\u0001\u0000\u0000\u0000\u013b\u019b\u0005\u00d2\u0000\u0000\u013c\u019b"+
		"\u0005%\u0000\u0000\u013d\u019b\u0005&\u0000\u0000\u013e\u019b\u0005\'"+
		"\u0000\u0000\u013f\u019b\u0005\u00b5\u0000\u0000\u0140\u019b\u0005(\u0000"+
		"\u0000\u0141\u019b\u0005)\u0000\u0000\u0142\u019b\u0005*\u0000\u0000\u0143"+
		"\u019b\u0005+\u0000\u0000\u0144\u019b\u0005,\u0000\u0000\u0145\u019b\u0005"+
		"-\u0000\u0000\u0146\u019b\u0005.\u0000\u0000\u0147\u014f\u0005/\u0000"+
		"\u0000\u0148\u0149\u0005\u009b\u0000\u0000\u0149\u014c\u0005\u00d6\u0000"+
		"\u0000\u014a\u014b\u0005\u00a2\u0000\u0000\u014b\u014d\u0005\u00d6\u0000"+
		"\u0000\u014c\u014a\u0001\u0000\u0000\u0000\u014c\u014d\u0001\u0000\u0000"+
		"\u0000\u014d\u014e\u0001\u0000\u0000\u0000\u014e\u0150\u0005\u009c\u0000"+
		"\u0000\u014f\u0148\u0001\u0000\u0000\u0000\u014f\u0150\u0001\u0000\u0000"+
		"\u0000\u0150\u019b\u0001\u0000\u0000\u0000\u0151\u019b\u0003Z-\u0000\u0152"+
		"\u019b\u00050\u0000\u0000\u0153\u0157\u00051\u0000\u0000\u0154\u0155\u0005"+
		"\u009b\u0000\u0000\u0155\u0156\u0005\u00d6\u0000\u0000\u0156\u0158\u0005"+
		"\u009c\u0000\u0000\u0157\u0154\u0001\u0000\u0000\u0000\u0157\u0158\u0001"+
		"\u0000\u0000\u0000\u0158\u019b\u0001\u0000\u0000\u0000\u0159\u015d\u0005"+
		"\u00b6\u0000\u0000\u015a\u015b\u0005\u009b\u0000\u0000\u015b\u015c\u0005"+
		"\u00d6\u0000\u0000\u015c\u015e\u0005\u009c\u0000\u0000\u015d\u015a\u0001"+
		"\u0000\u0000\u0000\u015d\u015e\u0001\u0000\u0000\u0000\u015e\u019b\u0001"+
		"\u0000\u0000\u0000\u015f\u019b\u0005\u00d1\u0000\u0000\u0160\u019b\u0005"+
		"\u00d3\u0000\u0000\u0161\u019b\u00052\u0000\u0000\u0162\u019b\u00053\u0000"+
		"\u0000\u0163\u019b\u00054\u0000\u0000\u0164\u016b\u00055\u0000\u0000\u0165"+
		"\u0166\u00056\u0000\u0000\u0166\u0167\u00055\u0000\u0000\u0167\u016c\u0005"+
		"7\u0000\u0000\u0168\u0169\u00058\u0000\u0000\u0169\u016a\u00055\u0000"+
		"\u0000\u016a\u016c\u00057\u0000\u0000\u016b\u0165\u0001\u0000\u0000\u0000"+
		"\u016b\u0168\u0001\u0000\u0000\u0000\u016b\u016c\u0001\u0000\u0000\u0000"+
		"\u016c\u019b\u0001\u0000\u0000\u0000\u016d\u0174\u00059\u0000\u0000\u016e"+
		"\u016f\u00056\u0000\u0000\u016f\u0170\u00055\u0000\u0000\u0170\u0175\u0005"+
		"7\u0000\u0000\u0171\u0172\u00058\u0000\u0000\u0172\u0173\u00055\u0000"+
		"\u0000\u0173\u0175\u00057\u0000\u0000\u0174\u016e\u0001\u0000\u0000\u0000"+
		"\u0174\u0171\u0001\u0000\u0000\u0000\u0174\u0175\u0001\u0000\u0000\u0000"+
		"\u0175\u019b\u0001\u0000\u0000\u0000\u0176\u019b\u0005:\u0000\u0000\u0177"+
		"\u019b\u0005;\u0000\u0000\u0178\u019b\u0005\u00c4\u0000\u0000\u0179\u019b"+
		"\u0005<\u0000\u0000\u017a\u017b\u0005=\u0000\u0000\u017b\u017c\u0005\u009b"+
		"\u0000\u0000\u017c\u0181\u0005\u00d5\u0000\u0000\u017d\u017e\u0005\u00a2"+
		"\u0000\u0000\u017e\u0180\u0005\u00d5\u0000\u0000\u017f\u017d\u0001\u0000"+
		"\u0000\u0000\u0180\u0183\u0001\u0000\u0000\u0000\u0181\u017f\u0001\u0000"+
		"\u0000\u0000\u0181\u0182\u0001\u0000\u0000\u0000\u0182\u0184\u0001\u0000"+
		"\u0000\u0000\u0183\u0181\u0001\u0000\u0000\u0000\u0184\u019b\u0005\u009c"+
		"\u0000\u0000\u0185\u019b\u0005>\u0000\u0000\u0186\u019b\u0005?\u0000\u0000"+
		"\u0187\u019b\u0005@\u0000\u0000\u0188\u019b\u0005A\u0000\u0000\u0189\u019b"+
		"\u0005B\u0000\u0000\u018a\u019b\u0005C\u0000\u0000\u018b\u019b\u0005D"+
		"\u0000\u0000\u018c\u0190\u0005E\u0000\u0000\u018d\u018e\u0005\u009b\u0000"+
		"\u0000\u018e\u018f\u0005\u00d6\u0000\u0000\u018f\u0191\u0005\u009c\u0000"+
		"\u0000\u0190\u018d\u0001\u0000\u0000\u0000\u0190\u0191\u0001\u0000\u0000"+
		"\u0000\u0191\u019b\u0001\u0000\u0000\u0000\u0192\u0196\u0005F\u0000\u0000"+
		"\u0193\u0194\u0005\u009b\u0000\u0000\u0194\u0195\u0005\u00d6\u0000\u0000"+
		"\u0195\u0197\u0005\u009c\u0000\u0000\u0196\u0193\u0001\u0000\u0000\u0000"+
		"\u0196\u0197\u0001\u0000\u0000\u0000\u0197\u019b\u0001\u0000\u0000\u0000"+
		"\u0198\u019b\u0005\u0011\u0000\u0000\u0199\u019b\u0005\u00d4\u0000\u0000"+
		"\u019a\u013b\u0001\u0000\u0000\u0000\u019a\u013c\u0001\u0000\u0000\u0000"+
		"\u019a\u013d\u0001\u0000\u0000\u0000\u019a\u013e\u0001\u0000\u0000\u0000"+
		"\u019a\u013f\u0001\u0000\u0000\u0000\u019a\u0140\u0001\u0000\u0000\u0000"+
		"\u019a\u0141\u0001\u0000\u0000\u0000\u019a\u0142\u0001\u0000\u0000\u0000"+
		"\u019a\u0143\u0001\u0000\u0000\u0000\u019a\u0144\u0001\u0000\u0000\u0000"+
		"\u019a\u0145\u0001\u0000\u0000\u0000\u019a\u0146\u0001\u0000\u0000\u0000"+
		"\u019a\u0147\u0001\u0000\u0000\u0000\u019a\u0151\u0001\u0000\u0000\u0000"+
		"\u019a\u0152\u0001\u0000\u0000\u0000\u019a\u0153\u0001\u0000\u0000\u0000"+
		"\u019a\u0159\u0001\u0000\u0000\u0000\u019a\u015f\u0001\u0000\u0000\u0000"+
		"\u019a\u0160\u0001\u0000\u0000\u0000\u019a\u0161\u0001\u0000\u0000\u0000"+
		"\u019a\u0162\u0001\u0000\u0000\u0000\u019a\u0163\u0001\u0000\u0000\u0000"+
		"\u019a\u0164\u0001\u0000\u0000\u0000\u019a\u016d\u0001\u0000\u0000\u0000"+
		"\u019a\u0176\u0001\u0000\u0000\u0000\u019a\u0177\u0001\u0000\u0000\u0000"+
		"\u019a\u0178\u0001\u0000\u0000\u0000\u019a\u0179\u0001\u0000\u0000\u0000"+
		"\u019a\u017a\u0001\u0000\u0000\u0000\u019a\u0185\u0001\u0000\u0000\u0000"+
		"\u019a\u0186\u0001\u0000\u0000\u0000\u019a\u0187\u0001\u0000\u0000\u0000"+
		"\u019a\u0188\u0001\u0000\u0000\u0000\u019a\u0189\u0001\u0000\u0000\u0000"+
		"\u019a\u018a\u0001\u0000\u0000\u0000\u019a\u018b\u0001\u0000\u0000\u0000"+
		"\u019a\u018c\u0001\u0000\u0000\u0000\u019a\u0192\u0001\u0000\u0000\u0000"+
		"\u019a\u0198\u0001\u0000\u0000\u0000\u019a\u0199\u0001\u0000\u0000\u0000"+
		"\u019b\u000f\u0001\u0000\u0000\u0000\u019c\u019d\u0007\u0003\u0000\u0000"+
		"\u019d\u0011\u0001\u0000\u0000\u0000\u019e\u019f\u0007\u0003\u0000\u0000"+
		"\u019f\u0013\u0001\u0000\u0000\u0000\u01a0\u01da\u0005\u00bf\u0000\u0000"+
		"\u01a1\u01da\u0005\u00b9\u0000\u0000\u01a2\u01a5\u0005\u00cf\u0000\u0000"+
		"\u01a3\u01a4\u0005G\u0000\u0000\u01a4\u01a6\u0003P(\u0000\u01a5\u01a3"+
		"\u0001\u0000\u0000\u0000\u01a5\u01a6\u0001\u0000\u0000\u0000\u01a6\u01da"+
		"\u0001\u0000\u0000\u0000\u01a7\u01a8\u0005\u00c5\u0000\u0000\u01a8\u01da"+
		"\u0003l6\u0000\u01a9\u01aa\u0005\u00c0\u0000\u0000\u01aa\u01ab\u0005\u009b"+
		"\u0000\u0000\u01ab\u01ac\u0003.\u0017\u0000\u01ac\u01ad\u0005\u009c\u0000"+
		"\u0000\u01ad\u01da\u0001\u0000\u0000\u0000\u01ae\u01b2\u0005\u00bd\u0000"+
		"\u0000\u01af\u01b0\u0003R)\u0000\u01b0\u01b1\u0005\u00b7\u0000\u0000\u01b1"+
		"\u01b3\u0001\u0000\u0000\u0000\u01b2\u01af\u0001\u0000\u0000\u0000\u01b2"+
		"\u01b3\u0001\u0000\u0000\u0000\u01b3\u01b4\u0001\u0000\u0000\u0000\u01b4"+
		"\u01b5\u0003T*\u0000\u01b5\u01b6\u0005\u009b\u0000\u0000\u01b6\u01b7\u0003"+
		"V+\u0000\u01b7\u01b9\u0005\u009c\u0000\u0000\u01b8\u01ba\u0003\u001c\u000e"+
		"\u0000\u01b9\u01b8\u0001\u0000\u0000\u0000\u01b9\u01ba\u0001\u0000\u0000"+
		"\u0000\u01ba\u01da\u0001\u0000\u0000\u0000\u01bb\u01da\u0005\u00be\u0000"+
		"\u0000\u01bc\u01bf\u0005\u00be\u0000\u0000\u01bd\u01be\u0005H\u0000\u0000"+
		"\u01be\u01c0\u0005\u00d4\u0000\u0000\u01bf\u01bd\u0001\u0000\u0000\u0000"+
		"\u01bf\u01c0\u0001\u0000\u0000\u0000\u01c0\u01da\u0001\u0000\u0000\u0000"+
		"\u01c1\u01c2\u0005\u00ba\u0000\u0000\u01c2\u01c3\u0003L&\u0000\u01c3\u01c7"+
		"\u0005\u00bd\u0000\u0000\u01c4\u01c5\u0003R)\u0000\u01c5\u01c6\u0005\u00b7"+
		"\u0000\u0000\u01c6\u01c8\u0001\u0000\u0000\u0000\u01c7\u01c4\u0001\u0000"+
		"\u0000\u0000\u01c7\u01c8\u0001\u0000\u0000\u0000\u01c8\u01c9\u0001\u0000"+
		"\u0000\u0000\u01c9\u01ca\u0003T*\u0000\u01ca\u01cd\u0003L&\u0000\u01cb"+
		"\u01cc\u0005\u00b3\u0000\u0000\u01cc\u01ce\u0003X,\u0000\u01cd\u01cb\u0001"+
		"\u0000\u0000\u0000\u01cd\u01ce\u0001\u0000\u0000\u0000\u01ce\u01da\u0001"+
		"\u0000\u0000\u0000\u01cf\u01d0\u0005I\u0000\u0000\u01d0\u01d1\u0005\u00ae"+
		"\u0000\u0000\u01d1\u01d2\u0005\u00d4\u0000\u0000\u01d2\u01d3\u0005\u009b"+
		"\u0000\u0000\u01d3\u01d4\u0003\u0018\f\u0000\u01d4\u01d7\u0005\u009c\u0000"+
		"\u0000\u01d5\u01d6\u0005J\u0000\u0000\u01d6\u01d8\u0003.\u0017\u0000\u01d7"+
		"\u01d5\u0001\u0000\u0000\u0000\u01d7\u01d8\u0001\u0000\u0000\u0000\u01d8"+
		"\u01da\u0001\u0000\u0000\u0000\u01d9\u01a0\u0001\u0000\u0000\u0000\u01d9"+
		"\u01a1\u0001\u0000\u0000\u0000\u01d9\u01a2\u0001\u0000\u0000\u0000\u01d9"+
		"\u01a7\u0001\u0000\u0000\u0000\u01d9\u01a9\u0001\u0000\u0000\u0000\u01d9"+
		"\u01ae\u0001\u0000\u0000\u0000\u01d9\u01bb\u0001\u0000\u0000\u0000\u01d9"+
		"\u01bc\u0001\u0000\u0000\u0000\u01d9\u01c1\u0001\u0000\u0000\u0000\u01d9"+
		"\u01cf\u0001\u0000\u0000\u0000\u01da\u0015\u0001\u0000\u0000\u0000\u01db"+
		"\u01dd\u0005\u00b8\u0000\u0000\u01dc\u01de\u0005\u00d4\u0000\u0000\u01dd"+
		"\u01dc\u0001\u0000\u0000\u0000\u01dd\u01de\u0001\u0000\u0000\u0000\u01de"+
		"\u01df\u0001\u0000\u0000\u0000\u01df\u01e0\u0005\u00be\u0000\u0000\u01e0"+
		"\u020c\u0003L&\u0000\u01e1\u01e3\u0005\u00b8\u0000\u0000\u01e2\u01e4\u0005"+
		"\u00d4\u0000\u0000\u01e3\u01e2\u0001\u0000\u0000\u0000\u01e3\u01e4\u0001"+
		"\u0000\u0000\u0000\u01e4\u01e5\u0001\u0000\u0000\u0000\u01e5\u01e6\u0005"+
		"\u00ba\u0000\u0000\u01e6\u01e7\u0003L&\u0000\u01e7\u01eb\u0005\u00bd\u0000"+
		"\u0000\u01e8\u01e9\u0003R)\u0000\u01e9\u01ea\u0005\u00b7\u0000\u0000\u01ea"+
		"\u01ec\u0001\u0000\u0000\u0000\u01eb\u01e8\u0001\u0000\u0000\u0000\u01eb"+
		"\u01ec\u0001\u0000\u0000\u0000\u01ec\u01ed\u0001\u0000\u0000\u0000\u01ed"+
		"\u01ee\u0003T*\u0000\u01ee\u01f2\u0003L&\u0000\u01ef\u01f1\u0003\u001c"+
		"\u000e\u0000\u01f0\u01ef\u0001\u0000\u0000\u0000\u01f1\u01f4\u0001\u0000"+
		"\u0000\u0000\u01f2\u01f0\u0001\u0000\u0000\u0000\u01f2\u01f3\u0001\u0000"+
		"\u0000\u0000\u01f3\u020c\u0001\u0000\u0000\u0000\u01f4\u01f2\u0001\u0000"+
		"\u0000\u0000\u01f5\u01f7\u0005\u00b8\u0000\u0000\u01f6\u01f8\u0005\u00d4"+
		"\u0000\u0000\u01f7\u01f6\u0001\u0000\u0000\u0000\u01f7\u01f8\u0001\u0000"+
		"\u0000\u0000\u01f8\u01f9\u0001\u0000\u0000\u0000\u01f9\u01fa\u0005\u00cf"+
		"\u0000\u0000\u01fa\u020c\u0003L&\u0000\u01fb\u01fd\u0005\u00b8\u0000\u0000"+
		"\u01fc\u01fe\u0005\u00d4\u0000\u0000\u01fd\u01fc\u0001\u0000\u0000\u0000"+
		"\u01fd\u01fe\u0001\u0000\u0000\u0000\u01fe\u01ff\u0001\u0000\u0000\u0000"+
		"\u01ff\u0200\u0005\u00c0\u0000\u0000\u0200\u0201\u0005\u009b\u0000\u0000"+
		"\u0201\u0202\u0003.\u0017\u0000\u0202\u0203\u0005\u009c\u0000\u0000\u0203"+
		"\u020c\u0001\u0000\u0000\u0000\u0204\u0205\u0005I\u0000\u0000\u0205\u0206"+
		"\u0005\u00ae\u0000\u0000\u0206\u0207\u0005\u00d4\u0000\u0000\u0207\u0208"+
		"\u0005\u009b\u0000\u0000\u0208\u0209\u0003\u0018\f\u0000\u0209\u020a\u0005"+
		"\u009c\u0000\u0000\u020a\u020c\u0001\u0000\u0000\u0000\u020b\u01db\u0001"+
		"\u0000\u0000\u0000\u020b\u01e1\u0001\u0000\u0000\u0000\u020b\u01f5\u0001"+
		"\u0000\u0000\u0000\u020b\u01fb\u0001\u0000\u0000\u0000\u020b\u0204\u0001"+
		"\u0000\u0000\u0000\u020c\u0017\u0001\u0000\u0000\u0000\u020d\u0212\u0003"+
		"\u001a\r\u0000\u020e\u020f\u0005\u00a2\u0000\u0000\u020f\u0211\u0003\u001a"+
		"\r\u0000\u0210\u020e\u0001\u0000\u0000\u0000\u0211\u0214\u0001\u0000\u0000"+
		"\u0000\u0212\u0210\u0001\u0000\u0000\u0000\u0212\u0213\u0001\u0000\u0000"+
		"\u0000\u0213\u0019\u0001\u0000\u0000\u0000\u0214\u0212\u0001\u0000\u0000"+
		"\u0000\u0215\u0216\u0003V+\u0000\u0216\u0217\u00058\u0000\u0000\u0217"+
		"\u0218\u00032\u0019\u0000\u0218\u001b\u0001\u0000\u0000\u0000\u0219\u021a"+
		"\u0005\u00cd\u0000\u0000\u021a\u0223\u0003\u001e\u000f\u0000\u021b\u021c"+
		"\u0005\u00ce\u0000\u0000\u021c\u0223\u0003\u001e\u000f\u0000\u021d\u0220"+
		"\u0005K\u0000\u0000\u021e\u021f\u0005L\u0000\u0000\u021f\u0221\u0007\u0004"+
		"\u0000\u0000\u0220\u021e\u0001\u0000\u0000\u0000\u0220\u0221\u0001\u0000"+
		"\u0000\u0000\u0221\u0223\u0001\u0000\u0000\u0000\u0222\u0219\u0001\u0000"+
		"\u0000\u0000\u0222\u021b\u0001\u0000\u0000\u0000\u0222\u021d\u0001\u0000"+
		"\u0000\u0000\u0223\u001d\u0001\u0000\u0000\u0000\u0224\u0225\u0007\u0005"+
		"\u0000\u0000\u0225\u001f\u0001\u0000\u0000\u0000\u0226\u0227\u0005\u00b2"+
		"\u0000\u0000\u0227\u0228\u0005\u0011\u0000\u0000\u0228\u0229\u0005\u00d4"+
		"\u0000\u0000\u0229\u022a\u0003\"\u0011\u0000\u022a\u022b\u0003$\u0012"+
		"\u0000\u022b\u022c\u0005\u00a4\u0000\u0000\u022c\u022d\u0003T*\u0000\u022d"+
		"\u022e\u0005O\u0000\u0000\u022e\u022f\u0005P\u0000\u0000\u022f\u0230\u0005"+
		"\u00d4\u0000\u0000\u0230\u0239\u0005\u009b\u0000\u0000\u0231\u0236\u0003"+
		"l6\u0000\u0232\u0233\u0005\u00a2\u0000\u0000\u0233\u0235\u0003l6\u0000"+
		"\u0234\u0232\u0001\u0000\u0000\u0000\u0235\u0238\u0001\u0000\u0000\u0000"+
		"\u0236\u0234\u0001\u0000\u0000\u0000\u0236\u0237\u0001\u0000\u0000\u0000"+
		"\u0237\u023a\u0001\u0000\u0000\u0000\u0238\u0236\u0001\u0000\u0000\u0000"+
		"\u0239\u0231\u0001\u0000\u0000\u0000\u0239\u023a\u0001\u0000\u0000\u0000"+
		"\u023a\u023b\u0001\u0000\u0000\u0000\u023b\u023c\u0005\u009c\u0000\u0000"+
		"\u023c\u023d\u0005\u009f\u0000\u0000\u023d!\u0001\u0000\u0000\u0000\u023e"+
		"\u023f\u0007\u0006\u0000\u0000\u023f#\u0001\u0000\u0000\u0000\u0240\u0245"+
		"\u0007\u0007\u0000\u0000\u0241\u0242\u0005V\u0000\u0000\u0242\u0244\u0007"+
		"\u0007\u0000\u0000\u0243\u0241\u0001\u0000\u0000\u0000\u0244\u0247\u0001"+
		"\u0000\u0000\u0000\u0245\u0243\u0001\u0000\u0000\u0000\u0245\u0246\u0001"+
		"\u0000\u0000\u0000\u0246%\u0001\u0000\u0000\u0000\u0247\u0245\u0001\u0000"+
		"\u0000\u0000\u0248\u024c\u0003(\u0014\u0000\u0249\u024c\u0003*\u0015\u0000"+
		"\u024a\u024c\u0003,\u0016\u0000\u024b\u0248\u0001\u0000\u0000\u0000\u024b"+
		"\u0249\u0001\u0000\u0000\u0000\u024b\u024a\u0001\u0000\u0000\u0000\u024c"+
		"\'\u0001\u0000\u0000\u0000\u024d\u024e\u0005W\u0000\u0000\u024e\u024f"+
		"\u0003T*\u0000\u024f\u0250\u0005\u009b\u0000\u0000\u0250\u0255\u0003V"+
		"+\u0000\u0251\u0252\u0005\u00a2\u0000\u0000\u0252\u0254\u0003V+\u0000"+
		"\u0253\u0251\u0001\u0000\u0000\u0000\u0254\u0257\u0001\u0000\u0000\u0000"+
		"\u0255\u0253\u0001\u0000\u0000\u0000\u0255\u0256\u0001\u0000\u0000\u0000"+
		"\u0256\u0258\u0001\u0000\u0000\u0000\u0257\u0255\u0001\u0000\u0000\u0000"+
		"\u0258\u0259\u0005\u009c\u0000\u0000\u0259\u025a\u0005X\u0000\u0000\u025a"+
		"\u025b\u0005\u009b\u0000\u0000\u025b\u0260\u0003l6\u0000\u025c\u025d\u0005"+
		"\u00a2\u0000\u0000\u025d\u025f\u0003l6\u0000\u025e\u025c\u0001\u0000\u0000"+
		"\u0000\u025f\u0262\u0001\u0000\u0000\u0000\u0260\u025e\u0001\u0000\u0000"+
		"\u0000\u0260\u0261\u0001\u0000\u0000\u0000\u0261\u0263\u0001\u0000\u0000"+
		"\u0000\u0262\u0260\u0001\u0000\u0000\u0000\u0263\u0264\u0005\u009c\u0000"+
		"\u0000\u0264\u0265\u0005\u009f\u0000\u0000\u0265)\u0001\u0000\u0000\u0000"+
		"\u0266\u0267\u0005U\u0000\u0000\u0267\u0268\u0003T*\u0000\u0268\u0269"+
		"\u0005\u00bc\u0000\u0000\u0269\u026a\u0003V+\u0000\u026a\u026b\u0005\u00d0"+
		"\u0000\u0000\u026b\u0273\u0003l6\u0000\u026c\u026d\u0005\u00a2\u0000\u0000"+
		"\u026d\u026e\u0003V+\u0000\u026e\u026f\u0005\u00d0\u0000\u0000\u026f\u0270"+
		"\u0003l6\u0000\u0270\u0272\u0001\u0000\u0000\u0000\u0271\u026c\u0001\u0000"+
		"\u0000\u0000\u0272\u0275\u0001\u0000\u0000\u0000\u0273\u0271\u0001\u0000"+
		"\u0000\u0000\u0273\u0274\u0001\u0000\u0000\u0000\u0274\u0278\u0001\u0000"+
		"\u0000\u0000\u0275\u0273\u0001\u0000\u0000\u0000\u0276\u0277\u0005J\u0000"+
		"\u0000\u0277\u0279\u0003.\u0017\u0000\u0278\u0276\u0001\u0000\u0000\u0000"+
		"\u0278\u0279\u0001\u0000\u0000\u0000\u0279\u027a\u0001\u0000\u0000\u0000"+
		"\u027a\u027b\u0005\u009f\u0000\u0000\u027b+\u0001\u0000\u0000\u0000\u027c"+
		"\u027d\u0005Y\u0000\u0000\u027d\u0280\u0003T*\u0000\u027e\u027f\u0005"+
		"J\u0000\u0000\u027f\u0281\u0003.\u0017\u0000\u0280\u027e\u0001\u0000\u0000"+
		"\u0000\u0280\u0281\u0001\u0000\u0000\u0000\u0281\u0282\u0001\u0000\u0000"+
		"\u0000\u0282\u0283\u0005\u009f\u0000\u0000\u0283-\u0001\u0000\u0000\u0000"+
		"\u0284\u0285\u0006\u0017\uffff\uffff\u0000\u0285\u0286\u0003V+\u0000\u0286"+
		"\u0287\u00032\u0019\u0000\u0287\u0288\u0003l6\u0000\u0288\u02b6\u0001"+
		"\u0000\u0000\u0000\u0289\u028a\u0003V+\u0000\u028a\u028b\u0005Z\u0000"+
		"\u0000\u028b\u028e\u0005\u009b\u0000\u0000\u028c\u028f\u0003l6\u0000\u028d"+
		"\u028f\u00030\u0018\u0000\u028e\u028c\u0001\u0000\u0000\u0000\u028e\u028d"+
		"\u0001\u0000\u0000\u0000\u028f\u0297\u0001\u0000\u0000\u0000\u0290\u0293"+
		"\u0005\u00a2\u0000\u0000\u0291\u0294\u0003l6\u0000\u0292\u0294\u00030"+
		"\u0018\u0000\u0293\u0291\u0001\u0000\u0000\u0000\u0293\u0292\u0001\u0000"+
		"\u0000\u0000\u0294\u0296\u0001\u0000\u0000\u0000\u0295\u0290\u0001\u0000"+
		"\u0000\u0000\u0296\u0299\u0001\u0000\u0000\u0000\u0297\u0295\u0001\u0000"+
		"\u0000\u0000\u0297\u0298\u0001\u0000\u0000\u0000\u0298\u029a\u0001\u0000"+
		"\u0000\u0000\u0299\u0297\u0001\u0000\u0000\u0000\u029a\u029b\u0005\u009c"+
		"\u0000\u0000\u029b\u02b6\u0001\u0000\u0000\u0000\u029c\u029d\u0003V+\u0000"+
		"\u029d\u029e\u0005[\u0000\u0000\u029e\u029f\u0003l6\u0000\u029f\u02a0"+
		"\u0005\\\u0000\u0000\u02a0\u02a1\u0003l6\u0000\u02a1\u02b6\u0001\u0000"+
		"\u0000\u0000\u02a2\u02a3\u0003V+\u0000\u02a3\u02a4\u0005]\u0000\u0000"+
		"\u02a4\u02a5\u0005\u00b9\u0000\u0000\u02a5\u02b6\u0001\u0000\u0000\u0000"+
		"\u02a6\u02a7\u0003V+\u0000\u02a7\u02a8\u0005^\u0000\u0000\u02a8\u02b6"+
		"\u0001\u0000\u0000\u0000\u02a9\u02aa\u0003V+\u0000\u02aa\u02ab\u0005_"+
		"\u0000\u0000\u02ab\u02ac\u0005\u00d5\u0000\u0000\u02ac\u02b6\u0001\u0000"+
		"\u0000\u0000\u02ad\u02ae\u0003V+\u0000\u02ae\u02af\u0005`\u0000\u0000"+
		"\u02af\u02b0\u0005\u00d5\u0000\u0000\u02b0\u02b6\u0001\u0000\u0000\u0000"+
		"\u02b1\u02b2\u0005\u00bb\u0000\u0000\u02b2\u02b6\u0003.\u0017\u0005\u02b3"+
		"\u02b6\u0005a\u0000\u0000\u02b4\u02b6\u0005b\u0000\u0000\u02b5\u0284\u0001"+
		"\u0000\u0000\u0000\u02b5\u0289\u0001\u0000\u0000\u0000\u02b5\u029c\u0001"+
		"\u0000\u0000\u0000\u02b5\u02a2\u0001\u0000\u0000\u0000\u02b5\u02a6\u0001"+
		"\u0000\u0000\u0000\u02b5\u02a9\u0001\u0000\u0000\u0000\u02b5\u02ad\u0001"+
		"\u0000\u0000\u0000\u02b5\u02b1\u0001\u0000\u0000\u0000\u02b5\u02b3\u0001"+
		"\u0000\u0000\u0000\u02b5\u02b4\u0001\u0000\u0000\u0000\u02b6\u02bf\u0001"+
		"\u0000\u0000\u0000\u02b7\u02b8\n\u0004\u0000\u0000\u02b8\u02b9\u0005\\"+
		"\u0000\u0000\u02b9\u02be\u0003.\u0017\u0005\u02ba\u02bb\n\u0003\u0000"+
		"\u0000\u02bb\u02bc\u0005V\u0000\u0000\u02bc\u02be\u0003.\u0017\u0004\u02bd"+
		"\u02b7\u0001\u0000\u0000\u0000\u02bd\u02ba\u0001\u0000\u0000\u0000\u02be"+
		"\u02c1\u0001\u0000\u0000\u0000\u02bf\u02bd\u0001\u0000\u0000\u0000\u02bf"+
		"\u02c0\u0001\u0000\u0000\u0000\u02c0/\u0001\u0000\u0000\u0000\u02c1\u02bf"+
		"\u0001\u0000\u0000\u0000\u02c2\u02c3\u0005\u009b\u0000\u0000\u02c3\u02c4"+
		"\u00036\u001b\u0000\u02c4\u02c5\u0005\u009c\u0000\u0000\u02c51\u0001\u0000"+
		"\u0000\u0000\u02c6\u02c7\u0007\b\u0000\u0000\u02c73\u0001\u0000\u0000"+
		"\u0000\u02c8\u02c9\u0007\t\u0000\u0000\u02c95\u0001\u0000\u0000\u0000"+
		"\u02ca\u02d5\u0003\u0000\u0000\u0000\u02cb\u02d5\u00038\u001c\u0000\u02cc"+
		"\u02d5\u0003:\u001d\u0000\u02cd\u02d5\u0003<\u001e\u0000\u02ce\u02d5\u0003"+
		"f3\u0000\u02cf\u02d5\u0003x<\u0000\u02d0\u02d5\u0003 \u0010\u0000\u02d1"+
		"\u02d5\u0003\b\u0004\u0000\u02d2\u02d5\u0003&\u0013\u0000\u02d3\u02d5"+
		"\u0003r9\u0000\u02d4\u02ca\u0001\u0000\u0000\u0000\u02d4\u02cb\u0001\u0000"+
		"\u0000\u0000\u02d4\u02cc\u0001\u0000\u0000\u0000\u02d4\u02cd\u0001\u0000"+
		"\u0000\u0000\u02d4\u02ce\u0001\u0000\u0000\u0000\u02d4\u02cf\u0001\u0000"+
		"\u0000\u0000\u02d4\u02d0\u0001\u0000\u0000\u0000\u02d4\u02d1\u0001\u0000"+
		"\u0000\u0000\u02d4\u02d2\u0001\u0000\u0000\u0000\u02d4\u02d3\u0001\u0000"+
		"\u0000\u0000\u02d5\u02d8\u0001\u0000\u0000\u0000\u02d6\u02d4\u0001\u0000"+
		"\u0000\u0000\u02d6\u02d7\u0001\u0000\u0000\u0000\u02d7\u02d9\u0001\u0000"+
		"\u0000\u0000\u02d8\u02d6\u0001\u0000\u0000\u0000\u02d9\u02da\u0005\u0000"+
		"\u0000\u0001\u02da7\u0001\u0000\u0000\u0000\u02db\u02dc\u0005u\u0000\u0000"+
		"\u02dc\u02dd\u0005\u00d4\u0000\u0000\u02dd\u02de\u0005\u009f\u0000\u0000"+
		"\u02de9\u0001\u0000\u0000\u0000\u02df\u02e0\u0005v\u0000\u0000\u02e0\u02e1"+
		"\u0005\u00d4\u0000\u0000\u02e1\u02e2\u0005\u00a8\u0000\u0000\u02e2\u02e3"+
		"\u0003@ \u0000\u02e3\u02e4\u0005\u009f\u0000\u0000\u02e4;\u0001\u0000"+
		"\u0000\u0000\u02e5\u02e6\u0005w\u0000\u0000\u02e6\u02e7\u0005\u00d4\u0000"+
		"\u0000\u02e7\u02f0\u0005\u009b\u0000\u0000\u02e8\u02ed\u0003>\u001f\u0000"+
		"\u02e9\u02ea\u0005\u00a2\u0000\u0000\u02ea\u02ec\u0003>\u001f\u0000\u02eb"+
		"\u02e9\u0001\u0000\u0000\u0000\u02ec\u02ef\u0001\u0000\u0000\u0000\u02ed"+
		"\u02eb\u0001\u0000\u0000\u0000\u02ed\u02ee\u0001\u0000\u0000\u0000\u02ee"+
		"\u02f1\u0001\u0000\u0000\u0000\u02ef\u02ed\u0001\u0000\u0000\u0000\u02f0"+
		"\u02e8\u0001\u0000\u0000\u0000\u02f0\u02f1\u0001\u0000\u0000\u0000\u02f1"+
		"\u02f2\u0001\u0000\u0000\u0000\u02f2\u02f3\u0005\u009c\u0000\u0000\u02f3"+
		"\u02f4\u0005x\u0000\u0000\u02f4\u02f5\u0003\u000e\u0007\u0000\u02f5\u02f6"+
		"\u0005\u00a8\u0000\u0000\u02f6\u02f7\u0005\u00af\u0000\u0000\u02f7\u02f8"+
		"\u0003t:\u0000\u02f8\u02f9\u0005\u00af\u0000\u0000\u02f9\u02fa\u0005y"+
		"\u0000\u0000\u02fa\u02fb\u0005\u00d4\u0000\u0000\u02fb\u02fc\u0005\u009f"+
		"\u0000\u0000\u02fc=\u0001\u0000\u0000\u0000\u02fd\u02fe\u0005\u00d4\u0000"+
		"\u0000\u02fe\u02ff\u0003\u000e\u0007\u0000\u02ff?\u0001\u0000\u0000\u0000"+
		"\u0300\u0301\u0005\u00ad\u0000\u0000\u0301\u030b\u0003B!\u0000\u0302\u0303"+
		"\u0005z\u0000\u0000\u0303\u0308\u0003D\"\u0000\u0304\u0305\u0005\u00a2"+
		"\u0000\u0000\u0305\u0307\u0003D\"\u0000\u0306\u0304\u0001\u0000\u0000"+
		"\u0000\u0307\u030a\u0001\u0000\u0000\u0000\u0308\u0306\u0001\u0000\u0000"+
		"\u0000\u0308\u0309\u0001\u0000\u0000\u0000\u0309\u030c\u0001\u0000\u0000"+
		"\u0000\u030a\u0308\u0001\u0000\u0000\u0000\u030b\u0302\u0001\u0000\u0000"+
		"\u0000\u030b\u030c\u0001\u0000\u0000\u0000\u030c\u030e\u0001\u0000\u0000"+
		"\u0000\u030d\u030f\u0003F#\u0000\u030e\u030d\u0001\u0000\u0000\u0000\u030e"+
		"\u030f\u0001\u0000\u0000\u0000\u030f\u0311\u0001\u0000\u0000\u0000\u0310"+
		"\u0312\u0003H$\u0000\u0311\u0310\u0001\u0000\u0000\u0000\u0311\u0312\u0001"+
		"\u0000\u0000\u0000\u0312\u0314\u0001\u0000\u0000\u0000\u0313\u0315\u0003"+
		"J%\u0000\u0314\u0313\u0001\u0000\u0000\u0000\u0314\u0315\u0001\u0000\u0000"+
		"\u0000\u0315A\u0001\u0000\u0000\u0000\u0316\u0320\u0005q\u0000\u0000\u0317"+
		"\u031c\u0003V+\u0000\u0318\u0319\u0005\u00a2\u0000\u0000\u0319\u031b\u0003"+
		"V+\u0000\u031a\u0318\u0001\u0000\u0000\u0000\u031b\u031e\u0001\u0000\u0000"+
		"\u0000\u031c\u031a\u0001\u0000\u0000\u0000\u031c\u031d\u0001\u0000\u0000"+
		"\u0000\u031d\u0320\u0001\u0000\u0000\u0000\u031e\u031c\u0001\u0000\u0000"+
		"\u0000\u031f\u0316\u0001\u0000\u0000\u0000\u031f\u0317\u0001\u0000\u0000"+
		"\u0000\u0320C\u0001\u0000\u0000\u0000\u0321\u0324\u0003T*\u0000\u0322"+
		"\u0323\u0005\u00a8\u0000\u0000\u0323\u0325\u0005\u00d4\u0000\u0000\u0324"+
		"\u0322\u0001\u0000\u0000\u0000\u0324\u0325\u0001\u0000\u0000\u0000\u0325"+
		"E\u0001\u0000\u0000\u0000\u0326\u0327\u0005J\u0000\u0000\u0327\u0328\u0003"+
		".\u0017\u0000\u0328G\u0001\u0000\u0000\u0000\u0329\u032a\u0005{\u0000"+
		"\u0000\u032a\u032c\u0003V+\u0000\u032b\u032d\u0007\n\u0000\u0000\u032c"+
		"\u032b\u0001\u0000\u0000\u0000\u032c\u032d\u0001\u0000\u0000\u0000\u032d"+
		"I\u0001\u0000\u0000\u0000\u032e\u032f\u0005~\u0000\u0000\u032f\u0330\u0005"+
		"\u00d6\u0000\u0000\u0330K\u0001\u0000\u0000\u0000\u0331\u0332\u0005\u009b"+
		"\u0000\u0000\u0332\u0337\u0003V+\u0000\u0333\u0334\u0005\u00a2\u0000\u0000"+
		"\u0334\u0336\u0003V+\u0000\u0335\u0333\u0001\u0000\u0000\u0000\u0336\u0339"+
		"\u0001\u0000\u0000\u0000\u0337\u0335\u0001\u0000\u0000\u0000\u0337\u0338"+
		"\u0001\u0000\u0000\u0000\u0338M\u0001\u0000\u0000\u0000\u0339\u0337\u0001"+
		"\u0000\u0000\u0000\u033a\u033c\u0003R)\u0000\u033b\u033a\u0001\u0000\u0000"+
		"\u0000\u033b\u033c\u0001\u0000\u0000\u0000\u033c\u033d\u0001\u0000\u0000"+
		"\u0000\u033d\u033e\u0005\u00b7\u0000\u0000\u033e\u0341\u0003T*\u0000\u033f"+
		"\u0341\u0003T*\u0000\u0340\u033b\u0001\u0000\u0000\u0000\u0340\u033f\u0001"+
		"\u0000\u0000\u0000\u0341\u0342\u0001\u0000\u0000\u0000\u0342\u0343\u0005"+
		"\u009b\u0000\u0000\u0343\u0348\u0003V+\u0000\u0344\u0345\u0005\u00a2\u0000"+
		"\u0000\u0345\u0347\u0003V+\u0000\u0346\u0344\u0001\u0000\u0000\u0000\u0347"+
		"\u034a\u0001\u0000\u0000\u0000\u0348\u0346\u0001\u0000\u0000\u0000\u0348"+
		"\u0349\u0001\u0000\u0000\u0000\u0349\u034b\u0001\u0000\u0000\u0000\u034a"+
		"\u0348\u0001\u0000\u0000\u0000\u034b\u034c\u0005\u009c\u0000\u0000\u034c"+
		"O\u0001\u0000\u0000\u0000\u034d\u0358\u0005\u007f\u0000\u0000\u034e\u034f"+
		"\u0005\u0080\u0000\u0000\u034f\u0350\u0005\u00bc\u0000\u0000\u0350\u0351"+
		"\u0003V+\u0000\u0351\u0352\u0005\u00d0\u0000\u0000\u0352\u0355\u0003l"+
		"6\u0000\u0353\u0354\u0005J\u0000\u0000\u0354\u0356\u0003.\u0017\u0000"+
		"\u0355\u0353\u0001\u0000\u0000\u0000\u0355\u0356\u0001\u0000\u0000\u0000"+
		"\u0356\u0358\u0001\u0000\u0000\u0000\u0357\u034d\u0001\u0000\u0000\u0000"+
		"\u0357\u034e\u0001\u0000\u0000\u0000\u0358Q\u0001\u0000\u0000\u0000\u0359"+
		"\u035a\u0005\u00d4\u0000\u0000\u035aS\u0001\u0000\u0000\u0000\u035b\u035c"+
		"\u0003R)\u0000\u035c\u035d\u0005\u00b7\u0000\u0000\u035d\u035f\u0001\u0000"+
		"\u0000\u0000\u035e\u035b\u0001\u0000\u0000\u0000\u035e\u035f\u0001\u0000"+
		"\u0000\u0000\u035f\u0360\u0001\u0000\u0000\u0000\u0360\u0361\u0005\u00d4"+
		"\u0000\u0000\u0361U\u0001\u0000\u0000\u0000\u0362\u0363\u0005\u00d4\u0000"+
		"\u0000\u0363W\u0001\u0000\u0000\u0000\u0364\u0365\u0007\u000b\u0000\u0000"+
		"\u0365Y\u0001\u0000\u0000\u0000\u0366\u036e\u0007\f\u0000\u0000\u0367"+
		"\u0368\u0005\u009b\u0000\u0000\u0368\u036b\u0005\u00d6\u0000\u0000\u0369"+
		"\u036a\u0005\u00a2\u0000\u0000\u036a\u036c\u0005\u00d6\u0000\u0000\u036b"+
		"\u0369\u0001\u0000\u0000\u0000\u036b\u036c\u0001\u0000\u0000\u0000\u036c"+
		"\u036d\u0001\u0000\u0000\u0000\u036d\u036f\u0005\u009c\u0000\u0000\u036e"+
		"\u0367\u0001\u0000\u0000\u0000\u036e\u036f\u0001\u0000\u0000\u0000\u036f"+
		"[\u0001\u0000\u0000\u0000\u0370\u0371\u0005\u0085\u0000\u0000\u0371\u0372"+
		"\u0003^/\u0000\u0372\u0373\u0005\u009b\u0000\u0000\u0373\u0374\u0003V"+
		"+\u0000\u0374\u0375\u0005\u009c\u0000\u0000\u0375]\u0001\u0000\u0000\u0000"+
		"\u0376\u0377\u0007\r\u0000\u0000\u0377_\u0001\u0000\u0000\u0000\u0378"+
		"\u0379\u0005\u00b2\u0000\u0000\u0379\u037a\u0005\u00b1\u0000\u0000\u037a"+
		"\u037b\u0003T*\u0000\u037b\u037c\u0005\u0001\u0000\u0000\u037c\u037d\u0003"+
		"T*\u0000\u037d\u037e\u0003b1\u0000\u037e\u037f\u0005\u009f\u0000\u0000"+
		"\u037fa\u0001\u0000\u0000\u0000\u0380\u039a\u0005\u0089\u0000\u0000\u0381"+
		"\u0382\u0005z\u0000\u0000\u0382\u0383\u0005\u009b\u0000\u0000\u0383\u0384"+
		"\u0003l6\u0000\u0384\u0385\u0005\u009c\u0000\u0000\u0385\u0386\u0005\u00a9"+
		"\u0000\u0000\u0386\u0387\u0005\u009b\u0000\u0000\u0387\u0388\u0003l6\u0000"+
		"\u0388\u0389\u0005\u009c\u0000\u0000\u0389\u039b\u0001\u0000\u0000\u0000"+
		"\u038a\u038b\u0005Z\u0000\u0000\u038b\u038c\u0005\u009b\u0000\u0000\u038c"+
		"\u0391\u0003l6\u0000\u038d\u038e\u0005\u00a2\u0000\u0000\u038e\u0390\u0003"+
		"l6\u0000\u038f\u038d\u0001\u0000\u0000\u0000\u0390\u0393\u0001\u0000\u0000"+
		"\u0000\u0391\u038f\u0001\u0000\u0000\u0000\u0391\u0392\u0001\u0000\u0000"+
		"\u0000\u0392\u0394\u0001\u0000\u0000\u0000\u0393\u0391\u0001\u0000\u0000"+
		"\u0000\u0394\u0395\u0005\u009c\u0000\u0000\u0395\u039b\u0001\u0000\u0000"+
		"\u0000\u0396\u0397\u0005\u008a\u0000\u0000\u0397\u0398\u0005\u00d6\u0000"+
		"\u0000\u0398\u0399\u0005\u008b\u0000\u0000\u0399\u039b\u0005\u00d6\u0000"+
		"\u0000\u039a\u0381\u0001\u0000\u0000\u0000\u039a\u038a\u0001\u0000\u0000"+
		"\u0000\u039a\u0396\u0001\u0000\u0000\u0000\u039bc\u0001\u0000\u0000\u0000"+
		"\u039c\u039d\u0005\u0001\u0000\u0000\u039d\u039e\u0003T*\u0000\u039e\u039f"+
		"\u0003b1\u0000\u039fe\u0001\u0000\u0000\u0000\u03a0\u03a1\u0005\u00b2"+
		"\u0000\u0000\u03a1\u03a2\u0005\u008c\u0000\u0000\u03a2\u03a3\u0005\u00d4"+
		"\u0000\u0000\u03a3\u03a4\u0005\u00a4\u0000\u0000\u03a4\u03a5\u0003T*\u0000"+
		"\u03a5\u03a6\u0005\u009b\u0000\u0000\u03a6\u03ab\u0003V+\u0000\u03a7\u03a8"+
		"\u0005\u00a2\u0000\u0000\u03a8\u03aa\u0003V+\u0000\u03a9\u03a7\u0001\u0000"+
		"\u0000\u0000\u03aa\u03ad\u0001\u0000\u0000\u0000\u03ab\u03a9\u0001\u0000"+
		"\u0000\u0000\u03ab\u03ac\u0001\u0000\u0000\u0000\u03ac\u03ae\u0001\u0000"+
		"\u0000\u0000\u03ad\u03ab\u0001\u0000\u0000\u0000\u03ae\u03b0\u0005\u009c"+
		"\u0000\u0000\u03af\u03b1\u0003h4\u0000\u03b0\u03af\u0001\u0000\u0000\u0000"+
		"\u03b0\u03b1\u0001\u0000\u0000\u0000\u03b1\u03b2\u0001\u0000\u0000\u0000"+
		"\u03b2\u03b3\u0005\u009f\u0000\u0000\u03b3g\u0001\u0000\u0000\u0000\u03b4"+
		"\u03b5\u0005\u000e\u0000\u0000\u03b5\u03b6\u0005\u00d4\u0000\u0000\u03b6"+
		"i\u0001\u0000\u0000\u0000\u03b7\u03b8\u00065\uffff\uffff\u0000\u03b8\u03c1"+
		"\u0003l6\u0000\u03b9\u03c1\u0003V+\u0000\u03ba\u03c1\u0003\u008aE\u0000"+
		"\u03bb\u03c1\u0003p8\u0000\u03bc\u03bd\u0005\u009b\u0000\u0000\u03bd\u03be"+
		"\u0003j5\u0000\u03be\u03bf\u0005\u009c\u0000\u0000\u03bf\u03c1\u0001\u0000"+
		"\u0000\u0000\u03c0\u03b7\u0001\u0000\u0000\u0000\u03c0\u03b9\u0001\u0000"+
		"\u0000\u0000\u03c0\u03ba\u0001\u0000\u0000\u0000\u03c0\u03bb\u0001\u0000"+
		"\u0000\u0000\u03c0\u03bc\u0001\u0000\u0000\u0000\u03c1\u03d7\u0001\u0000"+
		"\u0000\u0000\u03c2\u03c3\n\u0005\u0000\u0000\u03c3\u03c4\u0007\u000e\u0000"+
		"\u0000\u03c4\u03d6\u0003j5\u0006\u03c5\u03c6\n\u0004\u0000\u0000\u03c6"+
		"\u03c7\u00032\u0019\u0000\u03c7\u03c8\u0003j5\u0005\u03c8\u03d6\u0001"+
		"\u0000\u0000\u0000\u03c9\u03ca\n\u0003\u0000\u0000\u03ca\u03cb\u0005\\"+
		"\u0000\u0000\u03cb\u03d6\u0003j5\u0004\u03cc\u03cd\n\u0002\u0000\u0000"+
		"\u03cd\u03ce\u0005V\u0000\u0000\u03ce\u03d6\u0003j5\u0003\u03cf\u03d0"+
		"\n\u0001\u0000\u0000\u03d0\u03d3\u0005\u00a1\u0000\u0000\u03d1\u03d4\u0003"+
		"\u008eG\u0000\u03d2\u03d4\u0003\u000e\u0007\u0000\u03d3\u03d1\u0001\u0000"+
		"\u0000\u0000\u03d3\u03d2\u0001\u0000\u0000\u0000\u03d4\u03d6\u0001\u0000"+
		"\u0000\u0000\u03d5\u03c2\u0001\u0000\u0000\u0000\u03d5\u03c5\u0001\u0000"+
		"\u0000\u0000\u03d5\u03c9\u0001\u0000\u0000\u0000\u03d5\u03cc\u0001\u0000"+
		"\u0000\u0000\u03d5\u03cf\u0001\u0000\u0000\u0000\u03d6\u03d9\u0001\u0000"+
		"\u0000\u0000\u03d7\u03d5\u0001\u0000\u0000\u0000\u03d7\u03d8\u0001\u0000"+
		"\u0000\u0000\u03d8k\u0001\u0000\u0000\u0000\u03d9\u03d7\u0001\u0000\u0000"+
		"\u0000\u03da\u03e7\u0005\u00d5\u0000\u0000\u03db\u03e7\u0005\u00d6\u0000"+
		"\u0000\u03dc\u03e7\u0005\u008d\u0000\u0000\u03dd\u03e7\u0005\u008e\u0000"+
		"\u0000\u03de\u03e7\u0005\u00b9\u0000\u0000\u03df\u03e7\u0005\u008f\u0000"+
		"\u0000\u03e0\u03e7\u0005\u0090\u0000\u0000\u03e1\u03e7\u0005\u0091\u0000"+
		"\u0000\u03e2\u03e7\u0005\u0092\u0000\u0000\u03e3\u03e7\u0005\u0093\u0000"+
		"\u0000\u03e4\u03e7\u0003\u008cF\u0000\u03e5\u03e7\u0003p8\u0000\u03e6"+
		"\u03da\u0001\u0000\u0000\u0000\u03e6\u03db\u0001\u0000\u0000\u0000\u03e6"+
		"\u03dc\u0001\u0000\u0000\u0000\u03e6\u03dd\u0001\u0000\u0000\u0000\u03e6"+
		"\u03de\u0001\u0000\u0000\u0000\u03e6\u03df\u0001\u0000\u0000\u0000\u03e6"+
		"\u03e0\u0001\u0000\u0000\u0000\u03e6\u03e1\u0001\u0000\u0000\u0000\u03e6"+
		"\u03e2\u0001\u0000\u0000\u0000\u03e6\u03e3\u0001\u0000\u0000\u0000\u03e6"+
		"\u03e4\u0001\u0000\u0000\u0000\u03e6\u03e5\u0001\u0000\u0000\u0000\u03e7"+
		"m\u0001\u0000\u0000\u0000\u03e8\u03ed\u0003j5\u0000\u03e9\u03ea\u0005"+
		"\u00a2\u0000\u0000\u03ea\u03ec\u0003j5\u0000\u03eb\u03e9\u0001\u0000\u0000"+
		"\u0000\u03ec\u03ef\u0001\u0000\u0000\u0000\u03ed\u03eb\u0001\u0000\u0000"+
		"\u0000\u03ed\u03ee\u0001\u0000\u0000\u0000\u03eeo\u0001\u0000\u0000\u0000"+
		"\u03ef\u03ed\u0001\u0000\u0000\u0000\u03f0\u03f1\u0005\u00c4\u0000\u0000"+
		"\u03f1\u03f2\u0005\u00c2\u0000\u0000\u03f2\u03f3\u0003n7\u0000\u03f3\u03f6"+
		"\u0005\u00c3\u0000\u0000\u03f4\u03f5\u0005\u00a1\u0000\u0000\u03f5\u03f7"+
		"\u0003\u000e\u0007\u0000\u03f6\u03f4\u0001\u0000\u0000\u0000\u03f6\u03f7"+
		"\u0001\u0000\u0000\u0000\u03f7q\u0001\u0000\u0000\u0000\u03f8\u03f9\u0005"+
		"\u00b2\u0000\u0000\u03f9\u03fa\u0005\u0094\u0000\u0000\u03fa\u03fb\u0005"+
		"\u00d4\u0000\u0000\u03fb\u03fc\u0005\u00a4\u0000\u0000\u03fc\u03ff\u0003"+
		"T*\u0000\u03fd\u03fe\u0005\u00ac\u0000\u0000\u03fe\u0400\u0007\u000f\u0000"+
		"\u0000\u03ff\u03fd\u0001\u0000\u0000\u0000\u03ff\u0400\u0001\u0000\u0000"+
		"\u0000\u0400\u0406\u0001\u0000\u0000\u0000\u0401\u0402\u0005\u00ae\u0000"+
		"\u0000\u0402\u0403\u0005\u009b\u0000\u0000\u0403\u0404\u0003.\u0017\u0000"+
		"\u0404\u0405\u0005\u009c\u0000\u0000\u0405\u0407\u0001\u0000\u0000\u0000"+
		"\u0406\u0401\u0001\u0000\u0000\u0000\u0406\u0407\u0001\u0000\u0000\u0000"+
		"\u0407\u040d\u0001\u0000\u0000\u0000\u0408\u0409\u0005\u0096\u0000\u0000"+
		"\u0409\u040a\u0005\u009b\u0000\u0000\u040a\u040b\u0003.\u0017\u0000\u040b"+
		"\u040c\u0005\u009c\u0000\u0000\u040c\u040e\u0001\u0000\u0000\u0000\u040d"+
		"\u0408\u0001\u0000\u0000\u0000\u040d\u040e\u0001\u0000\u0000\u0000\u040e"+
		"\u040f\u0001\u0000\u0000\u0000\u040f\u0410\u0005\u009f\u0000\u0000\u0410"+
		"s\u0001\u0000\u0000\u0000\u0411\u0415\u0005\u0097\u0000\u0000\u0412\u0414"+
		"\u0003v;\u0000\u0413\u0412\u0001\u0000\u0000\u0000\u0414\u0417\u0001\u0000"+
		"\u0000\u0000\u0415\u0413\u0001\u0000\u0000\u0000\u0415\u0416\u0001\u0000"+
		"\u0000\u0000\u0416\u0418\u0001\u0000\u0000\u0000\u0417\u0415\u0001\u0000"+
		"\u0000\u0000\u0418\u0419\u0005\u0098\u0000\u0000\u0419\u041a\u0005\u009f"+
		"\u0000\u0000\u041au\u0001\u0000\u0000\u0000\u041b\u041c\u0003V+\u0000"+
		"\u041c\u041d\u0005\u00b7\u0000\u0000\u041d\u041e\u0003V+\u0000\u041e\u041f"+
		"\u0005\u00d0\u0000\u0000\u041f\u0420\u0003l6\u0000\u0420\u0421\u0005\u009f"+
		"\u0000\u0000\u0421\u042d\u0001\u0000\u0000\u0000\u0422\u0423\u0005\u0099"+
		"\u0000\u0000\u0423\u0424\u0005\u00b0\u0000\u0000\u0424\u042d\u0005\u009f"+
		"\u0000\u0000\u0425\u0426\u0005\u00b0\u0000\u0000\u0426\u0427\u0005\u00b7"+
		"\u0000\u0000\u0427\u0428\u0003V+\u0000\u0428\u0429\u0005\u00d0\u0000\u0000"+
		"\u0429\u042a\u0003l6\u0000\u042a\u042b\u0005\u009f\u0000\u0000\u042b\u042d"+
		"\u0001\u0000\u0000\u0000\u042c\u041b\u0001\u0000\u0000\u0000\u042c\u0422"+
		"\u0001\u0000\u0000\u0000\u042c\u0425\u0001\u0000\u0000\u0000\u042dw\u0001"+
		"\u0000\u0000\u0000\u042e\u042f\u0005\u00b2\u0000\u0000\u042f\u0430\u0005"+
		"\u00a6\u0000\u0000\u0430\u0431\u0005\u00d4\u0000\u0000\u0431\u0432\u0005"+
		"\u00a8\u0000\u0000\u0432\u0433\u0005\u00a4\u0000\u0000\u0433\u0434\u0003"+
		"z=\u0000\u0434\u0435\u0005\u00a9\u0000\u0000\u0435\u0436\u0003T*\u0000"+
		"\u0436\u0437\u0005\u00a7\u0000\u0000\u0437\u0438\u0003|>\u0000\u0438\u0439"+
		"\u0005\u009f\u0000\u0000\u0439y\u0001\u0000\u0000\u0000\u043a\u043b\u0007"+
		"\u0010\u0000\u0000\u043b{\u0001\u0000\u0000\u0000\u043c\u0440\u0005\u009a"+
		"\u0000\u0000\u043d\u043e\u0005\u00aa\u0000\u0000\u043e\u0440\u0003~?\u0000"+
		"\u043f\u043c\u0001\u0000\u0000\u0000\u043f\u043d\u0001\u0000\u0000\u0000"+
		"\u0440}\u0001\u0000\u0000\u0000\u0441\u0442\u0005W\u0000\u0000\u0442\u0443"+
		"\u0003T*\u0000\u0443\u0444\u0005\u009b\u0000\u0000\u0444\u0449\u0003V"+
		"+\u0000\u0445\u0446\u0005\u00a2\u0000\u0000\u0446\u0448\u0003V+\u0000"+
		"\u0447\u0445\u0001\u0000\u0000\u0000\u0448\u044b\u0001\u0000\u0000\u0000"+
		"\u0449\u0447\u0001\u0000\u0000\u0000\u0449\u044a\u0001\u0000\u0000\u0000"+
		"\u044a\u044c\u0001\u0000\u0000\u0000\u044b\u0449\u0001\u0000\u0000\u0000"+
		"\u044c\u044d\u0005\u009c\u0000\u0000\u044d\u044e\u0005X\u0000\u0000\u044e"+
		"\u044f\u0005\u009b\u0000\u0000\u044f\u0454\u0003l6\u0000\u0450\u0451\u0005"+
		"\u00a2\u0000\u0000\u0451\u0453\u0003l6\u0000\u0452\u0450\u0001\u0000\u0000"+
		"\u0000\u0453\u0456\u0001\u0000\u0000\u0000\u0454\u0452\u0001\u0000\u0000"+
		"\u0000\u0454\u0455\u0001\u0000\u0000\u0000\u0455\u0457\u0001\u0000\u0000"+
		"\u0000\u0456\u0454\u0001\u0000\u0000\u0000\u0457\u0458\u0005\u009c\u0000"+
		"\u0000\u0458\u0463\u0001\u0000\u0000\u0000\u0459\u045a\u0005U\u0000\u0000"+
		"\u045a\u045b\u0003T*\u0000\u045b\u045c\u0005\u00bc\u0000\u0000\u045c\u045d"+
		"\u0003V+\u0000\u045d\u045e\u0005\u00d0\u0000\u0000\u045e\u045f\u0003l"+
		"6\u0000\u045f\u0463\u0001\u0000\u0000\u0000\u0460\u0461\u0005Y\u0000\u0000"+
		"\u0461\u0463\u0003T*\u0000\u0462\u0441\u0001\u0000\u0000\u0000\u0462\u0459"+
		"\u0001\u0000\u0000\u0000\u0462\u0460\u0001\u0000\u0000\u0000\u0463\u007f"+
		"\u0001\u0000\u0000\u0000\u0464\u0469\u0005\u00c5\u0000\u0000\u0465\u046a"+
		"\u0001\u0000\u0000\u0000\u0466\u046a\u0003\u0082A\u0000\u0467\u046a\u0003"+
		"\u0084B\u0000\u0468\u046a\u0005\u008f\u0000\u0000\u0469\u0465\u0001\u0000"+
		"\u0000\u0000\u0469\u0466\u0001\u0000\u0000\u0000\u0469\u0467\u0001\u0000"+
		"\u0000\u0000\u0469\u0468\u0001\u0000\u0000\u0000\u046a\u0081\u0001\u0000"+
		"\u0000\u0000\u046b\u046d\u0005p\u0000\u0000\u046c\u046b\u0001\u0000\u0000"+
		"\u0000\u046c\u046d\u0001\u0000\u0000\u0000\u046d\u046f\u0001\u0000\u0000"+
		"\u0000\u046e\u0470\u0005\u00d7\u0000\u0000\u046f\u046e\u0001\u0000\u0000"+
		"\u0000\u0470\u0471\u0001\u0000\u0000\u0000\u0471\u046f\u0001\u0000\u0000"+
		"\u0000\u0471\u0472\u0001\u0000\u0000\u0000\u0472\u0479\u0001\u0000\u0000"+
		"\u0000\u0473\u0475\u0005\u00b7\u0000\u0000\u0474\u0476\u0005\u00d7\u0000"+
		"\u0000\u0475\u0474\u0001\u0000\u0000\u0000\u0476\u0477\u0001\u0000\u0000"+
		"\u0000\u0477\u0475\u0001\u0000\u0000\u0000\u0477\u0478\u0001\u0000\u0000"+
		"\u0000\u0478\u047a\u0001\u0000\u0000\u0000\u0479\u0473\u0001\u0000\u0000"+
		"\u0000\u0479\u047a\u0001\u0000\u0000\u0000\u047a\u0083\u0001\u0000\u0000"+
		"\u0000\u047b\u047c\u0007\u0011\u0000\u0000\u047c\u0085\u0001\u0000\u0000"+
		"\u0000\u047d\u047e\u0005\u00cd\u0000\u0000\u047e\u0482\u0003\u0088D\u0000"+
		"\u047f\u0480\u0005\u00ce\u0000\u0000\u0480\u0482\u0003\u0088D\u0000\u0481"+
		"\u047d\u0001\u0000\u0000\u0000\u0481\u047f\u0001\u0000\u0000\u0000\u0482"+
		"\u0087\u0001\u0000\u0000\u0000\u0483\u0484\u0007\u0005\u0000\u0000\u0484"+
		"\u0089\u0001\u0000\u0000\u0000\u0485\u0486\u0005\u00c1\u0000\u0000\u0486"+
		"\u0487\u0005\u009b\u0000\u0000\u0487\u0488\u0003p8\u0000\u0488\u0489\u0005"+
		"\u009c\u0000\u0000\u0489\u008b\u0001\u0000\u0000\u0000\u048a\u048b\u0005"+
		":\u0000\u0000\u048b\u048c\u0005\u00d5\u0000\u0000\u048c\u008d\u0001\u0000"+
		"\u0000\u0000\u048d\u0490\u0005\u00d4\u0000\u0000\u048e\u048f\u0005\u00b7"+
		"\u0000\u0000\u048f\u0491\u0005\u00d4\u0000\u0000\u0490\u048e\u0001\u0000"+
		"\u0000\u0000\u0490\u0491\u0001\u0000\u0000\u0000\u0491\u008f\u0001\u0000"+
		"\u0000\u0000m\u0096\u009b\u009f\u00a4\u00b0\u00b7\u00bc\u00c9\u00ce\u00d1"+
		"\u00e6\u00ea\u0102\u0116\u0126\u0137\u0139\u014c\u014f\u0157\u015d\u016b"+
		"\u0174\u0181\u0190\u0196\u019a\u01a5\u01b2\u01b9\u01bf\u01c7\u01cd\u01d7"+
		"\u01d9\u01dd\u01e3\u01eb\u01f2\u01f7\u01fd\u020b\u0212\u0220\u0222\u0236"+
		"\u0239\u0245\u024b\u0255\u0260\u0273\u0278\u0280\u028e\u0293\u0297\u02b5"+
		"\u02bd\u02bf\u02d4\u02d6\u02ed\u02f0\u0308\u030b\u030e\u0311\u0314\u031c"+
		"\u031f\u0324\u032c\u0337\u033b\u0340\u0348\u0355\u0357\u035e\u036b\u036e"+
		"\u0391\u039a\u03ab\u03b0\u03c0\u03d3\u03d5\u03d7\u03e6\u03ed\u03f6\u03ff"+
		"\u0406\u040d\u0415\u042c\u043f\u0449\u0454\u0462\u0469\u046c\u0471\u0477"+
		"\u0479\u0481\u0490";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}