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
		T__149=150, T__150=151, T__151=152, T__152=153, T__153=154, T__154=155, 
		T__155=156, T__156=157, T__157=158, T__158=159, T__159=160, T__160=161, 
		T__161=162, T__162=163, T__163=164, T__164=165, T__165=166, T__166=167, 
		STRING=168, NUMBER=169, DIGIT=170, WS=171, LPAREN=172, RPAREN=173, LBRACE=174, 
		RBRACE=175, SEMICOLON=176, COLON=177, DOUBLE_COLON=178, COMMA=179, DOUBLE_QUOTE=180, 
		ON=181, DELETE=182, RULE=183, DO=184, AS=185, TO=186, INSTEAD=187, NOTHING=188, 
		FOR=189, SELECT=190, USING=191, DOLLAR_QUOTE=192, NEW=193, CREATE_TABLE=194, 
		TABLE=195, CREATE=196, RELATIONSHIP=197, DECIMAL=198, INT=199, VARCHAR=200, 
		PRIMARY_KEY=201, IDENTIFIER=202;
	public static final int
		RULE_createTableStatement = 0, RULE_columnDef = 1, RULE_generatedColumn = 2, 
		RULE_collateClause = 3, RULE_alterTableStatement = 4, RULE_alterAction = 5, 
		RULE_alterColumnAction = 6, RULE_dataType = 7, RULE_constraint = 8, RULE_onDeleteAction = 9, 
		RULE_onUpdateAction = 10, RULE_tableConstraint = 11, RULE_excludeElementList = 12, 
		RULE_excludeElement = 13, RULE_onAction = 14, RULE_action = 15, RULE_triggerStatement = 16, 
		RULE_triggerTiming = 17, RULE_triggerEvent = 18, RULE_dataManipulationStatement = 19, 
		RULE_insertStatement = 20, RULE_updateStatement = 21, RULE_deleteStatement = 22, 
		RULE_condition = 23, RULE_subquery = 24, RULE_comparisonOperator = 25, 
		RULE_arithmeticOperator = 26, RULE_sqlScript = 27, RULE_createSchemaStatement = 28, 
		RULE_createViewStatement = 29, RULE_createFunctionStatement = 30, RULE_parameter = 31, 
		RULE_selectStatement = 32, RULE_selectList = 33, RULE_tableReference = 34, 
		RULE_whereClause = 35, RULE_orderByClause = 36, RULE_limitClause = 37, 
		RULE_columnNameList = 38, RULE_foreignTable = 39, RULE_conflictAction = 40, 
		RULE_schemaName = 41, RULE_tableName = 42, RULE_columnName = 43, RULE_relationshipType = 44, 
		RULE_decimalType = 45, RULE_partitionClause = 46, RULE_partitionStrategy = 47, 
		RULE_createPartitionStatement = 48, RULE_partitionValuesClause = 49, RULE_partitionOfClause = 50, 
		RULE_createIndexStatement = 51, RULE_indexOptionsClause = 52, RULE_expression = 53, 
		RULE_value = 54, RULE_createPolicyStatement = 55, RULE_plpgsqlBlock = 56, 
		RULE_statement = 57, RULE_createRuleStatement = 58, RULE_ruleEvent = 59, 
		RULE_ruleAction = 60, RULE_sqlAction = 61, RULE_defaultValue = 62, RULE_numericLiteral = 63, 
		RULE_booleanLiteral = 64;
	private static String[] makeRuleNames() {
		return new String[] {
			"createTableStatement", "columnDef", "generatedColumn", "collateClause", 
			"alterTableStatement", "alterAction", "alterColumnAction", "dataType", 
			"constraint", "onDeleteAction", "onUpdateAction", "tableConstraint", 
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
			"createPolicyStatement", "plpgsqlBlock", "statement", "createRuleStatement", 
			"ruleEvent", "ruleAction", "sqlAction", "defaultValue", "numericLiteral", 
			"booleanLiteral"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'PARTITION OF'", "'FOREIGN KEY'", "'REFERENCES'", "'GENERATED'", 
			"'ALWAYS'", "'BY DEFAULT'", "'STORED'", "'COLLATE'", "'ALTER'", "'ADD'", 
			"'COLUMN'", "'DROP'", "'CONSTRAINT'", "'RENAME COLUMN'", "'RENAME TO'", 
			"'SET'", "'SCHEMA'", "'TABLESPACE'", "'ENABLE'", "'REPLICA'", "'TRIGGER'", 
			"'DISABLE'", "'CLUSTER'", "'SET WITHOUT CLUSTER'", "'OWNER TO'", "'SET DEFAULT'", 
			"'DROP DEFAULT'", "'SET NOT NULL'", "'DROP NOT NULL'", "'SET STATISTICS'", 
			"'SET STORAGE'", "'PLAIN'", "'EXTERNAL'", "'EXTENDED'", "'MAIN'", "'SET COMPRESSION'", 
			"'RESET'", "'STORAGE'", "'STATISTICS'", "'COMPRESSION'", "'TYPE'", "'INTEGER'", 
			"'SMALLINT'", "'BIGINT'", "'BIGSERIAL'", "'SERIAL'", "'SMALLSERIAL'", 
			"'REAL'", "'DOUBLE PRECISION'", "'NUMERIC'", "'MONEY'", "'CHAR'", "'TEXT'", 
			"'BOOLEAN'", "'JSON'", "'JSONB'", "'DATE'", "'TIME'", "'WITHOUT'", "'ZONE'", 
			"'WITH'", "'TIMESTAMP'", "'INTERVAL'", "'UUID'", "'ARRAY'", "'BYTEA'", 
			"'ENUM'", "'CITEXT'", "'TSVECTOR'", "'INET'", "'CIDR'", "'MACADDR'", 
			"'XML'", "'PG_LSN'", "'BIT'", "'VARBIT'", "'NOT NULL'", "'NULL'", "'UNIQUE'", 
			"'ON CONFLICT'", "'DEFAULT'", "'CHECK'", "'USING INDEX TABLESPACE'", 
			"'EXCLUDE'", "'WHERE'", "'CASCADE'", "'SET NULL'", "'NO ACTION'", "'ON DELETE'", 
			"'ON UPDATE'", "'RESTRICT'", "'FOR EACH ROW'", "'EXECUTE FUNCTION'", 
			"'BEFORE'", "'AFTER'", "'INSTEAD OF'", "'INSERT'", "'UPDATE'", "'OR'", 
			"'INSERT INTO'", "'VALUES'", "'='", "'DELETE FROM'", "'IN'", "'BETWEEN'", 
			"'AND'", "'IS'", "'IS NOT NULL'", "'LIKE'", "'ILIKE'", "'NOT'", "'true'", 
			"'false'", "'<>'", "'!='", "'<'", "'<='", "'>'", "'>='", "'@>'", "'<@'", 
			"'&&'", "'?'", "'?|'", "'?&'", "'+'", "'-'", "'*'", "'/'", "'%'", "'^'", 
			"'CREATE SCHEMA'", "'CREATE VIEW'", "'CREATE FUNCTION'", "'RETURNS'", 
			"'LANGUAGE'", "'FROM'", "'ORDER BY'", "'ASC'", "'DESC'", "'LIMIT'", "'.'", 
			"'DO NOTHING'", "'DO UPDATE'", "'ONETOONE'", "'MANYTOONE'", "'ONETOMANY'", 
			"'MANYTOMANY'", "'PARTITION BY'", "'RANGE'", "'LIST'", "'HASH'", "'FOR VALUES'", 
			"'MODULUS'", "'REMAINDER'", "'INDEX'", "'TRUE'", "'FALSE'", "'CURRENT_TIMESTAMP'", 
			"'current_user'", "'POLICY'", "'ALL'", "'WITH CHECK'", "'BEGIN'", "'END'", 
			"'RETURN'", "'INSTEAD NOTHING'", null, null, null, null, "'('", "')'", 
			"'{'", "'}'", "';'", "':'", "'::'", "','", "'\"'", "'ON'", "'DELETE'", 
			"'RULE'", "'DO'", "'AS'", "'TO'", "'INSTEAD'", "'NOTHING'", "'FOR'", 
			"'SELECT'", "'USING'", "'$$'", "'NEW'", "'CREATE_TABLE'", "'TABLE'", 
			"'CREATE'", "'RELATIONSHIP'", "'DECIMAL'", "'INT'", "'VARCHAR'", "'PRIMARY KEY'"
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
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, null, null, 
			"STRING", "NUMBER", "DIGIT", "WS", "LPAREN", "RPAREN", "LBRACE", "RBRACE", 
			"SEMICOLON", "COLON", "DOUBLE_COLON", "COMMA", "DOUBLE_QUOTE", "ON", 
			"DELETE", "RULE", "DO", "AS", "TO", "INSTEAD", "NOTHING", "FOR", "SELECT", 
			"USING", "DOLLAR_QUOTE", "NEW", "CREATE_TABLE", "TABLE", "CREATE", "RELATIONSHIP", 
			"DECIMAL", "INT", "VARCHAR", "PRIMARY_KEY", "IDENTIFIER"
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
			setState(162);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,4,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(130);
				match(CREATE);
				setState(131);
				match(TABLE);
				setState(132);
				tableName();
				setState(133);
				match(LPAREN);
				setState(136);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,0,_ctx) ) {
				case 1:
					{
					setState(134);
					columnDef();
					}
					break;
				case 2:
					{
					setState(135);
					tableConstraint();
					}
					break;
				}
				setState(145);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(138);
					match(COMMA);
					setState(141);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,1,_ctx) ) {
					case 1:
						{
						setState(139);
						columnDef();
						}
						break;
					case 2:
						{
						setState(140);
						tableConstraint();
						}
						break;
					}
					}
					}
					setState(147);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(148);
				match(RPAREN);
				setState(150);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__148) {
					{
					setState(149);
					partitionClause();
					}
				}

				setState(152);
				match(SEMICOLON);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(154);
				match(CREATE);
				setState(155);
				match(TABLE);
				setState(156);
				tableName();
				setState(157);
				match(T__0);
				setState(158);
				tableName();
				setState(159);
				partitionValuesClause();
				setState(160);
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
			setState(195);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case IDENTIFIER:
				enterOuterAlt(_localctx, 1);
				{
				setState(164);
				columnName();
				setState(165);
				dataType();
				setState(169);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__1 || _la==T__2 || ((((_la - 77)) & ~0x3f) == 0 && ((1L << (_la - 77)) & 183L) != 0) || _la==PRIMARY_KEY) {
					{
					{
					setState(166);
					constraint();
					}
					}
					setState(171);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(174);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case T__3:
					{
					setState(172);
					generatedColumn();
					}
					break;
				case T__7:
					{
					setState(173);
					collateClause();
					}
					break;
				case EOF:
				case T__8:
				case T__97:
				case T__99:
				case T__102:
				case T__131:
				case T__132:
				case T__133:
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
			case T__1:
				enterOuterAlt(_localctx, 2);
				{
				setState(176);
				match(T__1);
				setState(177);
				match(LPAREN);
				setState(178);
				columnNameList();
				setState(179);
				match(RPAREN);
				setState(180);
				match(T__2);
				setState(181);
				tableName();
				setState(182);
				match(LPAREN);
				setState(183);
				columnNameList();
				setState(184);
				match(RPAREN);
				setState(187);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==RELATIONSHIP) {
					{
					setState(185);
					match(RELATIONSHIP);
					setState(186);
					relationshipType();
					}
				}

				setState(192);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__88 || _la==T__89) {
					{
					{
					setState(189);
					onAction();
					}
					}
					setState(194);
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
			setState(197);
			match(T__3);
			setState(198);
			_la = _input.LA(1);
			if ( !(_la==T__4 || _la==T__5) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			setState(199);
			match(AS);
			setState(200);
			match(LPAREN);
			setState(201);
			expression(0);
			setState(202);
			match(RPAREN);
			setState(203);
			match(T__6);
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
			setState(205);
			match(T__7);
			setState(206);
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
			setState(208);
			match(T__8);
			setState(209);
			match(TABLE);
			setState(210);
			tableName();
			setState(211);
			alterAction();
			setState(216);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(212);
				match(COMMA);
				setState(213);
				alterAction();
				}
				}
				setState(218);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(220);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==SEMICOLON) {
				{
				setState(219);
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
		public TerminalNode IDENTIFIER() { return getToken(PostgreSQLParser.IDENTIFIER, 0); }
		public TableConstraintContext tableConstraint() {
			return getRuleContext(TableConstraintContext.class,0);
		}
		public List<ColumnNameListContext> columnNameList() {
			return getRuleContexts(ColumnNameListContext.class);
		}
		public ColumnNameListContext columnNameList(int i) {
			return getRuleContext(ColumnNameListContext.class,i);
		}
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
			setState(280);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,14,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(222);
				match(T__9);
				setState(223);
				match(T__10);
				setState(224);
				columnDef();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(225);
				match(T__11);
				setState(226);
				match(T__10);
				setState(227);
				columnName();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(228);
				match(T__9);
				setState(229);
				match(T__12);
				setState(230);
				match(IDENTIFIER);
				setState(231);
				tableConstraint();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(232);
				match(T__11);
				setState(233);
				match(T__12);
				setState(234);
				match(IDENTIFIER);
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(235);
				match(T__9);
				setState(236);
				match(T__1);
				setState(237);
				columnNameList();
				setState(238);
				match(T__2);
				setState(239);
				tableName();
				setState(240);
				columnNameList();
				setState(244);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__88 || _la==T__89) {
					{
					{
					setState(241);
					onAction();
					}
					}
					setState(246);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(247);
				match(T__8);
				setState(248);
				match(T__10);
				setState(249);
				columnName();
				setState(250);
				alterColumnAction();
				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(252);
				match(T__13);
				setState(253);
				columnName();
				setState(254);
				match(TO);
				setState(255);
				columnName();
				}
				break;
			case 8:
				enterOuterAlt(_localctx, 8);
				{
				setState(257);
				match(T__14);
				setState(258);
				tableName();
				}
				break;
			case 9:
				enterOuterAlt(_localctx, 9);
				{
				setState(259);
				match(T__15);
				setState(264);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case T__16:
					{
					setState(260);
					match(T__16);
					setState(261);
					schemaName();
					}
					break;
				case T__17:
					{
					setState(262);
					match(T__17);
					setState(263);
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
				setState(266);
				match(T__18);
				setState(267);
				match(T__19);
				setState(268);
				match(T__20);
				setState(269);
				match(IDENTIFIER);
				}
				break;
			case 11:
				enterOuterAlt(_localctx, 11);
				{
				setState(270);
				match(T__21);
				setState(271);
				match(T__19);
				setState(272);
				match(T__20);
				setState(273);
				match(IDENTIFIER);
				}
				break;
			case 12:
				enterOuterAlt(_localctx, 12);
				{
				setState(274);
				match(T__22);
				setState(275);
				match(ON);
				setState(276);
				match(IDENTIFIER);
				}
				break;
			case 13:
				enterOuterAlt(_localctx, 13);
				{
				setState(277);
				match(T__23);
				}
				break;
			case 14:
				enterOuterAlt(_localctx, 14);
				{
				setState(278);
				match(T__24);
				setState(279);
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
			setState(297); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				setState(297);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case T__25:
					{
					setState(282);
					match(T__25);
					setState(283);
					value();
					}
					break;
				case T__26:
					{
					setState(284);
					match(T__26);
					}
					break;
				case T__27:
					{
					setState(285);
					match(T__27);
					}
					break;
				case T__28:
					{
					setState(286);
					match(T__28);
					}
					break;
				case T__29:
					{
					setState(287);
					match(T__29);
					setState(288);
					match(NUMBER);
					}
					break;
				case T__30:
					{
					setState(289);
					match(T__30);
					setState(290);
					_la = _input.LA(1);
					if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 64424509440L) != 0)) ) {
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
					setState(291);
					match(T__35);
					setState(292);
					match(IDENTIFIER);
					}
					break;
				case T__36:
					{
					setState(293);
					match(T__36);
					setState(294);
					_la = _input.LA(1);
					if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 1924145348608L) != 0)) ) {
					_errHandler.recoverInline(this);
					}
					else {
						if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
						_errHandler.reportMatch(this);
						consume();
					}
					}
					break;
				case T__40:
					{
					setState(295);
					match(T__40);
					setState(296);
					dataType();
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				setState(299); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & 2409409544192L) != 0) );
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
		public TerminalNode DECIMAL() { return getToken(PostgreSQLParser.DECIMAL, 0); }
		public TerminalNode VARCHAR() { return getToken(PostgreSQLParser.VARCHAR, 0); }
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
			setState(398);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__41:
				enterOuterAlt(_localctx, 1);
				{
				setState(301);
				match(T__41);
				}
				break;
			case INT:
				enterOuterAlt(_localctx, 2);
				{
				setState(302);
				match(INT);
				}
				break;
			case T__42:
				enterOuterAlt(_localctx, 3);
				{
				setState(303);
				match(T__42);
				}
				break;
			case T__43:
				enterOuterAlt(_localctx, 4);
				{
				setState(304);
				match(T__43);
				}
				break;
			case T__44:
				enterOuterAlt(_localctx, 5);
				{
				setState(305);
				match(T__44);
				}
				break;
			case T__45:
				enterOuterAlt(_localctx, 6);
				{
				setState(306);
				match(T__45);
				}
				break;
			case T__46:
				enterOuterAlt(_localctx, 7);
				{
				setState(307);
				match(T__46);
				}
				break;
			case T__47:
				enterOuterAlt(_localctx, 8);
				{
				setState(308);
				match(T__47);
				}
				break;
			case T__48:
				enterOuterAlt(_localctx, 9);
				{
				setState(309);
				match(T__48);
				}
				break;
			case T__49:
				enterOuterAlt(_localctx, 10);
				{
				setState(310);
				match(T__49);
				setState(318);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==LPAREN) {
					{
					setState(311);
					match(LPAREN);
					setState(312);
					match(NUMBER);
					setState(315);
					_errHandler.sync(this);
					_la = _input.LA(1);
					if (_la==COMMA) {
						{
						setState(313);
						match(COMMA);
						setState(314);
						match(NUMBER);
						}
					}

					setState(317);
					match(RPAREN);
					}
				}

				}
				break;
			case DECIMAL:
				enterOuterAlt(_localctx, 11);
				{
				setState(320);
				match(DECIMAL);
				setState(328);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==LPAREN) {
					{
					setState(321);
					match(LPAREN);
					setState(322);
					match(NUMBER);
					setState(325);
					_errHandler.sync(this);
					_la = _input.LA(1);
					if (_la==COMMA) {
						{
						setState(323);
						match(COMMA);
						setState(324);
						match(NUMBER);
						}
					}

					setState(327);
					match(RPAREN);
					}
				}

				}
				break;
			case T__50:
				enterOuterAlt(_localctx, 12);
				{
				setState(330);
				match(T__50);
				}
				break;
			case T__51:
				enterOuterAlt(_localctx, 13);
				{
				setState(331);
				match(T__51);
				setState(332);
				match(LPAREN);
				setState(333);
				match(NUMBER);
				setState(334);
				match(RPAREN);
				}
				break;
			case VARCHAR:
				enterOuterAlt(_localctx, 14);
				{
				setState(335);
				match(VARCHAR);
				setState(336);
				match(LPAREN);
				setState(337);
				match(NUMBER);
				setState(338);
				match(RPAREN);
				}
				break;
			case T__52:
				enterOuterAlt(_localctx, 15);
				{
				setState(339);
				match(T__52);
				}
				break;
			case T__53:
				enterOuterAlt(_localctx, 16);
				{
				setState(340);
				match(T__53);
				}
				break;
			case T__54:
				enterOuterAlt(_localctx, 17);
				{
				setState(341);
				match(T__54);
				}
				break;
			case T__55:
				enterOuterAlt(_localctx, 18);
				{
				setState(342);
				match(T__55);
				}
				break;
			case T__56:
				enterOuterAlt(_localctx, 19);
				{
				setState(343);
				match(T__56);
				}
				break;
			case T__57:
				enterOuterAlt(_localctx, 20);
				{
				setState(344);
				match(T__57);
				setState(351);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case T__58:
					{
					setState(345);
					match(T__58);
					setState(346);
					match(T__57);
					setState(347);
					match(T__59);
					}
					break;
				case T__60:
					{
					setState(348);
					match(T__60);
					setState(349);
					match(T__57);
					setState(350);
					match(T__59);
					}
					break;
				case EOF:
				case T__1:
				case T__2:
				case T__3:
				case T__7:
				case T__8:
				case T__25:
				case T__26:
				case T__27:
				case T__28:
				case T__29:
				case T__30:
				case T__35:
				case T__36:
				case T__40:
				case T__76:
				case T__77:
				case T__78:
				case T__80:
				case T__81:
				case T__83:
				case T__97:
				case T__99:
				case T__102:
				case T__131:
				case T__132:
				case T__133:
				case RPAREN:
				case SEMICOLON:
				case COMMA:
				case AS:
				case CREATE:
				case PRIMARY_KEY:
					break;
				default:
					break;
				}
				}
				break;
			case T__61:
				enterOuterAlt(_localctx, 21);
				{
				setState(353);
				match(T__61);
				setState(360);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case T__58:
					{
					setState(354);
					match(T__58);
					setState(355);
					match(T__57);
					setState(356);
					match(T__59);
					}
					break;
				case T__60:
					{
					setState(357);
					match(T__60);
					setState(358);
					match(T__57);
					setState(359);
					match(T__59);
					}
					break;
				case EOF:
				case T__1:
				case T__2:
				case T__3:
				case T__7:
				case T__8:
				case T__25:
				case T__26:
				case T__27:
				case T__28:
				case T__29:
				case T__30:
				case T__35:
				case T__36:
				case T__40:
				case T__76:
				case T__77:
				case T__78:
				case T__80:
				case T__81:
				case T__83:
				case T__97:
				case T__99:
				case T__102:
				case T__131:
				case T__132:
				case T__133:
				case RPAREN:
				case SEMICOLON:
				case COMMA:
				case AS:
				case CREATE:
				case PRIMARY_KEY:
					break;
				default:
					break;
				}
				}
				break;
			case T__62:
				enterOuterAlt(_localctx, 22);
				{
				setState(362);
				match(T__62);
				}
				break;
			case T__63:
				enterOuterAlt(_localctx, 23);
				{
				setState(363);
				match(T__63);
				}
				break;
			case T__64:
				enterOuterAlt(_localctx, 24);
				{
				setState(364);
				match(T__64);
				}
				break;
			case T__65:
				enterOuterAlt(_localctx, 25);
				{
				setState(365);
				match(T__65);
				}
				break;
			case T__66:
				enterOuterAlt(_localctx, 26);
				{
				setState(366);
				match(T__66);
				setState(367);
				match(LPAREN);
				setState(368);
				match(STRING);
				setState(373);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(369);
					match(COMMA);
					setState(370);
					match(STRING);
					}
					}
					setState(375);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(376);
				match(RPAREN);
				}
				break;
			case T__67:
				enterOuterAlt(_localctx, 27);
				{
				setState(377);
				match(T__67);
				}
				break;
			case T__68:
				enterOuterAlt(_localctx, 28);
				{
				setState(378);
				match(T__68);
				}
				break;
			case T__69:
				enterOuterAlt(_localctx, 29);
				{
				setState(379);
				match(T__69);
				}
				break;
			case T__70:
				enterOuterAlt(_localctx, 30);
				{
				setState(380);
				match(T__70);
				}
				break;
			case T__71:
				enterOuterAlt(_localctx, 31);
				{
				setState(381);
				match(T__71);
				}
				break;
			case T__72:
				enterOuterAlt(_localctx, 32);
				{
				setState(382);
				match(T__72);
				}
				break;
			case T__73:
				enterOuterAlt(_localctx, 33);
				{
				setState(383);
				match(T__73);
				}
				break;
			case T__74:
				enterOuterAlt(_localctx, 34);
				{
				setState(384);
				match(T__74);
				setState(388);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==LPAREN) {
					{
					setState(385);
					match(LPAREN);
					setState(386);
					match(NUMBER);
					setState(387);
					match(RPAREN);
					}
				}

				}
				break;
			case T__75:
				enterOuterAlt(_localctx, 35);
				{
				setState(390);
				match(T__75);
				setState(394);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==LPAREN) {
					{
					setState(391);
					match(LPAREN);
					setState(392);
					match(NUMBER);
					setState(393);
					match(RPAREN);
					}
				}

				}
				break;
			case T__20:
				enterOuterAlt(_localctx, 36);
				{
				setState(396);
				match(T__20);
				}
				break;
			case IDENTIFIER:
				enterOuterAlt(_localctx, 37);
				{
				setState(397);
				match(IDENTIFIER);
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
	public static class ConstraintContext extends ParserRuleContext {
		public ConflictActionContext conflictAction() {
			return getRuleContext(ConflictActionContext.class,0);
		}
		public ValueContext value() {
			return getRuleContext(ValueContext.class,0);
		}
		public TerminalNode LPAREN() { return getToken(PostgreSQLParser.LPAREN, 0); }
		public ConditionContext condition() {
			return getRuleContext(ConditionContext.class,0);
		}
		public TerminalNode RPAREN() { return getToken(PostgreSQLParser.RPAREN, 0); }
		public TableNameContext tableName() {
			return getRuleContext(TableNameContext.class,0);
		}
		public ColumnNameContext columnName() {
			return getRuleContext(ColumnNameContext.class,0);
		}
		public OnActionContext onAction() {
			return getRuleContext(OnActionContext.class,0);
		}
		public TerminalNode PRIMARY_KEY() { return getToken(PostgreSQLParser.PRIMARY_KEY, 0); }
		public TerminalNode IDENTIFIER() { return getToken(PostgreSQLParser.IDENTIFIER, 0); }
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
		enterRule(_localctx, 16, RULE_constraint);
		int _la;
		try {
			setState(447);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,32,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(400);
				match(T__76);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(401);
				match(T__77);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(402);
				match(T__78);
				setState(405);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__79) {
					{
					setState(403);
					match(T__79);
					setState(404);
					conflictAction();
					}
				}

				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(407);
				match(T__80);
				setState(408);
				value();
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(409);
				match(T__81);
				setState(410);
				match(LPAREN);
				setState(411);
				condition(0);
				setState(412);
				match(RPAREN);
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(414);
				match(T__2);
				setState(415);
				tableName();
				setState(416);
				match(LPAREN);
				setState(417);
				columnName();
				setState(418);
				match(RPAREN);
				setState(420);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__88 || _la==T__89) {
					{
					setState(419);
					onAction();
					}
				}

				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(422);
				match(PRIMARY_KEY);
				}
				break;
			case 8:
				enterOuterAlt(_localctx, 8);
				{
				setState(423);
				match(PRIMARY_KEY);
				setState(426);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__82) {
					{
					setState(424);
					match(T__82);
					setState(425);
					match(IDENTIFIER);
					}
				}

				}
				break;
			case 9:
				enterOuterAlt(_localctx, 9);
				{
				setState(428);
				match(T__1);
				setState(429);
				columnNameList();
				setState(430);
				match(T__2);
				setState(431);
				tableName();
				setState(432);
				columnNameList();
				setState(435);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==RELATIONSHIP) {
					{
					setState(433);
					match(RELATIONSHIP);
					setState(434);
					relationshipType();
					}
				}

				}
				break;
			case 10:
				enterOuterAlt(_localctx, 10);
				{
				setState(437);
				match(T__83);
				setState(438);
				match(USING);
				setState(439);
				match(IDENTIFIER);
				setState(440);
				match(LPAREN);
				setState(441);
				excludeElementList();
				setState(442);
				match(RPAREN);
				setState(445);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__84) {
					{
					setState(443);
					match(T__84);
					setState(444);
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
	public static class OnDeleteActionContext extends ParserRuleContext {
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
			setState(449);
			_la = _input.LA(1);
			if ( !(((((_la - 26)) & ~0x3f) == 0 && ((1L << (_la - 26)) & 8070450532247928833L) != 0)) ) {
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
			setState(451);
			_la = _input.LA(1);
			if ( !(((((_la - 26)) & ~0x3f) == 0 && ((1L << (_la - 26)) & 8070450532247928833L) != 0)) ) {
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
	public static class TableConstraintContext extends ParserRuleContext {
		public TerminalNode PRIMARY_KEY() { return getToken(PostgreSQLParser.PRIMARY_KEY, 0); }
		public List<ColumnNameListContext> columnNameList() {
			return getRuleContexts(ColumnNameListContext.class);
		}
		public ColumnNameListContext columnNameList(int i) {
			return getRuleContext(ColumnNameListContext.class,i);
		}
		public TerminalNode IDENTIFIER() { return getToken(PostgreSQLParser.IDENTIFIER, 0); }
		public TableNameContext tableName() {
			return getRuleContext(TableNameContext.class,0);
		}
		public List<OnActionContext> onAction() {
			return getRuleContexts(OnActionContext.class);
		}
		public OnActionContext onAction(int i) {
			return getRuleContext(OnActionContext.class,i);
		}
		public TerminalNode RELATIONSHIP() { return getToken(PostgreSQLParser.RELATIONSHIP, 0); }
		public RelationshipTypeContext relationshipType() {
			return getRuleContext(RelationshipTypeContext.class,0);
		}
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
			setState(497);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case PRIMARY_KEY:
				enterOuterAlt(_localctx, 1);
				{
				setState(453);
				match(PRIMARY_KEY);
				setState(454);
				columnNameList();
				}
				break;
			case T__12:
				enterOuterAlt(_localctx, 2);
				{
				setState(455);
				match(T__12);
				setState(456);
				match(IDENTIFIER);
				setState(457);
				match(T__1);
				setState(458);
				columnNameList();
				setState(459);
				match(T__2);
				setState(460);
				tableName();
				setState(461);
				columnNameList();
				setState(465);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__88 || _la==T__89) {
					{
					{
					setState(462);
					onAction();
					}
					}
					setState(467);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
				break;
			case T__1:
				enterOuterAlt(_localctx, 3);
				{
				setState(468);
				match(T__1);
				setState(469);
				columnNameList();
				setState(470);
				match(T__2);
				setState(471);
				tableName();
				setState(472);
				columnNameList();
				setState(475);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==RELATIONSHIP) {
					{
					setState(473);
					match(RELATIONSHIP);
					setState(474);
					relationshipType();
					}
				}

				setState(480);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__88 || _la==T__89) {
					{
					{
					setState(477);
					onAction();
					}
					}
					setState(482);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
				break;
			case T__78:
				enterOuterAlt(_localctx, 4);
				{
				setState(483);
				match(T__78);
				setState(484);
				columnNameList();
				}
				break;
			case T__81:
				enterOuterAlt(_localctx, 5);
				{
				setState(485);
				match(T__81);
				setState(486);
				match(LPAREN);
				setState(487);
				condition(0);
				setState(488);
				match(RPAREN);
				}
				break;
			case T__83:
				enterOuterAlt(_localctx, 6);
				{
				setState(490);
				match(T__83);
				setState(491);
				match(USING);
				setState(492);
				match(IDENTIFIER);
				setState(493);
				match(LPAREN);
				setState(494);
				excludeElementList();
				setState(495);
				match(RPAREN);
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
			setState(499);
			excludeElement();
			setState(504);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(500);
				match(COMMA);
				setState(501);
				excludeElement();
				}
				}
				setState(506);
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
			setState(507);
			columnName();
			setState(508);
			match(T__60);
			setState(509);
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
		public ActionContext action() {
			return getRuleContext(ActionContext.class,0);
		}
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
		try {
			setState(515);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__88:
				enterOuterAlt(_localctx, 1);
				{
				setState(511);
				match(T__88);
				setState(512);
				action();
				}
				break;
			case T__89:
				enterOuterAlt(_localctx, 2);
				{
				setState(513);
				match(T__89);
				setState(514);
				action();
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
			setState(517);
			_la = _input.LA(1);
			if ( !(_la==T__25 || ((((_la - 86)) & ~0x3f) == 0 && ((1L << (_la - 86)) & 39L) != 0)) ) {
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
			setState(519);
			match(CREATE);
			setState(520);
			match(T__20);
			setState(521);
			match(IDENTIFIER);
			setState(522);
			triggerTiming();
			setState(523);
			triggerEvent();
			setState(524);
			match(ON);
			setState(525);
			tableName();
			setState(526);
			match(T__91);
			setState(527);
			match(T__92);
			setState(528);
			match(IDENTIFIER);
			setState(529);
			match(LPAREN);
			setState(538);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__77 || ((((_la - 157)) & ~0x3f) == 0 && ((1L << (_la - 157)) & 6159L) != 0)) {
				{
				setState(530);
				value();
				setState(535);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(531);
					match(COMMA);
					setState(532);
					value();
					}
					}
					setState(537);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
			}

			setState(540);
			match(RPAREN);
			setState(541);
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
			setState(543);
			_la = _input.LA(1);
			if ( !(((((_la - 94)) & ~0x3f) == 0 && ((1L << (_la - 94)) & 7L) != 0)) ) {
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
			setState(545);
			_la = _input.LA(1);
			if ( !(_la==T__96 || _la==T__97 || _la==DELETE) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			setState(550);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__98) {
				{
				{
				setState(546);
				match(T__98);
				setState(547);
				_la = _input.LA(1);
				if ( !(_la==T__96 || _la==T__97 || _la==DELETE) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				}
				}
				setState(552);
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
			setState(556);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__99:
				enterOuterAlt(_localctx, 1);
				{
				setState(553);
				insertStatement();
				}
				break;
			case T__97:
				enterOuterAlt(_localctx, 2);
				{
				setState(554);
				updateStatement();
				}
				break;
			case T__102:
				enterOuterAlt(_localctx, 3);
				{
				setState(555);
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
			setState(558);
			match(T__99);
			setState(559);
			tableName();
			setState(560);
			match(LPAREN);
			setState(561);
			columnName();
			setState(566);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(562);
				match(COMMA);
				setState(563);
				columnName();
				}
				}
				setState(568);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(569);
			match(RPAREN);
			setState(570);
			match(T__100);
			setState(571);
			match(LPAREN);
			setState(572);
			value();
			setState(577);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(573);
				match(COMMA);
				setState(574);
				value();
				}
				}
				setState(579);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(580);
			match(RPAREN);
			setState(581);
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
		public List<ColumnNameContext> columnName() {
			return getRuleContexts(ColumnNameContext.class);
		}
		public ColumnNameContext columnName(int i) {
			return getRuleContext(ColumnNameContext.class,i);
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
			setState(583);
			match(T__97);
			setState(584);
			tableName();
			setState(585);
			match(T__15);
			setState(586);
			columnName();
			setState(587);
			match(T__101);
			setState(588);
			value();
			setState(596);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(589);
				match(COMMA);
				setState(590);
				columnName();
				setState(591);
				match(T__101);
				setState(592);
				value();
				}
				}
				setState(598);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(601);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__84) {
				{
				setState(599);
				match(T__84);
				setState(600);
				condition(0);
				}
			}

			setState(603);
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
			setState(605);
			match(T__102);
			setState(606);
			tableName();
			setState(609);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__84) {
				{
				setState(607);
				match(T__84);
				setState(608);
				condition(0);
				}
			}

			setState(611);
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
		public TerminalNode STRING() { return getToken(PostgreSQLParser.STRING, 0); }
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
			setState(662);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,51,_ctx) ) {
			case 1:
				{
				setState(614);
				columnName();
				setState(615);
				comparisonOperator();
				setState(616);
				value();
				}
				break;
			case 2:
				{
				setState(618);
				columnName();
				setState(619);
				match(T__103);
				setState(620);
				match(LPAREN);
				setState(623);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case T__77:
				case T__156:
				case T__157:
				case T__158:
				case T__159:
				case STRING:
				case NUMBER:
					{
					setState(621);
					value();
					}
					break;
				case LPAREN:
					{
					setState(622);
					subquery();
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(632);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(625);
					match(COMMA);
					setState(628);
					_errHandler.sync(this);
					switch (_input.LA(1)) {
					case T__77:
					case T__156:
					case T__157:
					case T__158:
					case T__159:
					case STRING:
					case NUMBER:
						{
						setState(626);
						value();
						}
						break;
					case LPAREN:
						{
						setState(627);
						subquery();
						}
						break;
					default:
						throw new NoViableAltException(this);
					}
					}
					}
					setState(634);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(635);
				match(RPAREN);
				}
				break;
			case 3:
				{
				setState(637);
				columnName();
				setState(638);
				match(T__104);
				setState(639);
				value();
				setState(640);
				match(T__105);
				setState(641);
				value();
				}
				break;
			case 4:
				{
				setState(643);
				columnName();
				setState(644);
				match(T__106);
				setState(645);
				match(T__77);
				}
				break;
			case 5:
				{
				setState(647);
				columnName();
				setState(648);
				match(T__107);
				}
				break;
			case 6:
				{
				setState(650);
				columnName();
				setState(651);
				match(T__108);
				setState(652);
				match(STRING);
				}
				break;
			case 7:
				{
				setState(654);
				columnName();
				setState(655);
				match(T__109);
				setState(656);
				match(STRING);
				}
				break;
			case 8:
				{
				setState(658);
				match(T__110);
				setState(659);
				condition(5);
				}
				break;
			case 9:
				{
				setState(660);
				match(T__111);
				}
				break;
			case 10:
				{
				setState(661);
				match(T__112);
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(672);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,53,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(670);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,52,_ctx) ) {
					case 1:
						{
						_localctx = new ConditionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_condition);
						setState(664);
						if (!(precpred(_ctx, 4))) throw new FailedPredicateException(this, "precpred(_ctx, 4)");
						setState(665);
						match(T__105);
						setState(666);
						condition(5);
						}
						break;
					case 2:
						{
						_localctx = new ConditionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_condition);
						setState(667);
						if (!(precpred(_ctx, 3))) throw new FailedPredicateException(this, "precpred(_ctx, 3)");
						setState(668);
						match(T__98);
						setState(669);
						condition(4);
						}
						break;
					}
					} 
				}
				setState(674);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,53,_ctx);
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
			setState(675);
			match(LPAREN);
			setState(676);
			sqlScript();
			setState(677);
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
			setState(679);
			_la = _input.LA(1);
			if ( !(((((_la - 102)) & ~0x3f) == 0 && ((1L << (_la - 102)) & 16773121L) != 0)) ) {
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
			setState(681);
			_la = _input.LA(1);
			if ( !(((((_la - 126)) & ~0x3f) == 0 && ((1L << (_la - 126)) & 63L) != 0)) ) {
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
			setState(695);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__8 || ((((_la - 98)) & ~0x3f) == 0 && ((1L << (_la - 98)) & 120259084325L) != 0) || _la==CREATE) {
				{
				setState(693);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,54,_ctx) ) {
				case 1:
					{
					setState(683);
					createTableStatement();
					}
					break;
				case 2:
					{
					setState(684);
					createSchemaStatement();
					}
					break;
				case 3:
					{
					setState(685);
					createViewStatement();
					}
					break;
				case 4:
					{
					setState(686);
					createFunctionStatement();
					}
					break;
				case 5:
					{
					setState(687);
					createIndexStatement();
					}
					break;
				case 6:
					{
					setState(688);
					createRuleStatement();
					}
					break;
				case 7:
					{
					setState(689);
					triggerStatement();
					}
					break;
				case 8:
					{
					setState(690);
					alterTableStatement();
					}
					break;
				case 9:
					{
					setState(691);
					dataManipulationStatement();
					}
					break;
				case 10:
					{
					setState(692);
					createPolicyStatement();
					}
					break;
				}
				}
				setState(697);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(698);
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
			setState(700);
			match(T__131);
			setState(701);
			match(IDENTIFIER);
			setState(702);
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
			setState(704);
			match(T__132);
			setState(705);
			match(IDENTIFIER);
			setState(706);
			match(AS);
			setState(707);
			selectStatement();
			setState(708);
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
			setState(710);
			match(T__133);
			setState(711);
			match(IDENTIFIER);
			setState(712);
			match(LPAREN);
			setState(721);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==IDENTIFIER) {
				{
				setState(713);
				parameter();
				setState(718);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(714);
					match(COMMA);
					setState(715);
					parameter();
					}
					}
					setState(720);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
			}

			setState(723);
			match(RPAREN);
			setState(724);
			match(T__134);
			setState(725);
			dataType();
			setState(726);
			match(AS);
			setState(727);
			match(DOLLAR_QUOTE);
			setState(728);
			plpgsqlBlock();
			setState(729);
			match(DOLLAR_QUOTE);
			setState(730);
			match(T__135);
			setState(731);
			match(IDENTIFIER);
			setState(732);
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
			setState(734);
			match(IDENTIFIER);
			setState(735);
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
			setState(737);
			match(SELECT);
			setState(738);
			selectList();
			setState(748);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__136) {
				{
				setState(739);
				match(T__136);
				setState(740);
				tableReference();
				setState(745);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(741);
					match(COMMA);
					setState(742);
					tableReference();
					}
					}
					setState(747);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
			}

			setState(751);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__84) {
				{
				setState(750);
				whereClause();
				}
			}

			setState(754);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__137) {
				{
				setState(753);
				orderByClause();
				}
			}

			setState(757);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__140) {
				{
				setState(756);
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
			setState(768);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__127:
				enterOuterAlt(_localctx, 1);
				{
				setState(759);
				match(T__127);
				}
				break;
			case IDENTIFIER:
				enterOuterAlt(_localctx, 2);
				{
				setState(760);
				columnName();
				setState(765);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(761);
					match(COMMA);
					setState(762);
					columnName();
					}
					}
					setState(767);
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
			setState(770);
			tableName();
			setState(773);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==AS) {
				{
				setState(771);
				match(AS);
				setState(772);
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
			setState(775);
			match(T__84);
			setState(776);
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
			setState(778);
			match(T__137);
			setState(779);
			columnName();
			setState(781);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__138 || _la==T__139) {
				{
				setState(780);
				_la = _input.LA(1);
				if ( !(_la==T__138 || _la==T__139) ) {
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
			setState(783);
			match(T__140);
			setState(784);
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
		public TerminalNode RPAREN() { return getToken(PostgreSQLParser.RPAREN, 0); }
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
			setState(786);
			match(LPAREN);
			setState(787);
			columnName();
			setState(792);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,67,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(788);
					match(COMMA);
					setState(789);
					columnName();
					}
					} 
				}
				setState(794);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,67,_ctx);
			}
			setState(796);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,68,_ctx) ) {
			case 1:
				{
				setState(795);
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
	public static class ForeignTableContext extends ParserRuleContext {
		public TerminalNode LPAREN() { return getToken(PostgreSQLParser.LPAREN, 0); }
		public List<ColumnNameContext> columnName() {
			return getRuleContexts(ColumnNameContext.class);
		}
		public ColumnNameContext columnName(int i) {
			return getRuleContext(ColumnNameContext.class,i);
		}
		public TerminalNode RPAREN() { return getToken(PostgreSQLParser.RPAREN, 0); }
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
			setState(804);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,70,_ctx) ) {
			case 1:
				{
				setState(799);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==IDENTIFIER) {
					{
					setState(798);
					schemaName();
					}
				}

				setState(801);
				match(T__141);
				setState(802);
				tableName();
				}
				break;
			case 2:
				{
				setState(803);
				tableName();
				}
				break;
			}
			setState(806);
			match(LPAREN);
			setState(807);
			columnName();
			setState(812);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(808);
				match(COMMA);
				setState(809);
				columnName();
				}
				}
				setState(814);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(815);
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
		public ColumnNameContext columnName() {
			return getRuleContext(ColumnNameContext.class,0);
		}
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
			setState(827);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__142:
				enterOuterAlt(_localctx, 1);
				{
				setState(817);
				match(T__142);
				}
				break;
			case T__143:
				enterOuterAlt(_localctx, 2);
				{
				setState(818);
				match(T__143);
				setState(819);
				match(T__15);
				setState(820);
				columnName();
				setState(821);
				match(T__101);
				setState(822);
				value();
				setState(825);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__84) {
					{
					setState(823);
					match(T__84);
					setState(824);
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
			setState(829);
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
			setState(831);
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
			setState(833);
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
			setState(835);
			_la = _input.LA(1);
			if ( !(((((_la - 145)) & ~0x3f) == 0 && ((1L << (_la - 145)) & 15L) != 0)) ) {
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
		public TerminalNode DECIMAL() { return getToken(PostgreSQLParser.DECIMAL, 0); }
		public TerminalNode LPAREN() { return getToken(PostgreSQLParser.LPAREN, 0); }
		public List<TerminalNode> NUMBER() { return getTokens(PostgreSQLParser.NUMBER); }
		public TerminalNode NUMBER(int i) {
			return getToken(PostgreSQLParser.NUMBER, i);
		}
		public TerminalNode RPAREN() { return getToken(PostgreSQLParser.RPAREN, 0); }
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
			setState(837);
			match(DECIMAL);
			setState(845);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==LPAREN) {
				{
				setState(838);
				match(LPAREN);
				setState(839);
				match(NUMBER);
				setState(842);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==COMMA) {
					{
					setState(840);
					match(COMMA);
					setState(841);
					match(NUMBER);
					}
				}

				setState(844);
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
			setState(847);
			match(T__148);
			setState(848);
			partitionStrategy();
			setState(849);
			match(LPAREN);
			setState(850);
			columnName();
			setState(851);
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
			setState(853);
			_la = _input.LA(1);
			if ( !(((((_la - 150)) & ~0x3f) == 0 && ((1L << (_la - 150)) & 7L) != 0)) ) {
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
			setState(855);
			match(CREATE);
			setState(856);
			match(TABLE);
			setState(857);
			tableName();
			setState(858);
			match(T__0);
			setState(859);
			tableName();
			setState(860);
			partitionValuesClause();
			setState(861);
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
			setState(863);
			match(T__152);
			setState(889);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__136:
				{
				setState(864);
				match(T__136);
				setState(865);
				match(LPAREN);
				setState(866);
				value();
				setState(867);
				match(RPAREN);
				setState(868);
				match(TO);
				setState(869);
				match(LPAREN);
				setState(870);
				value();
				setState(871);
				match(RPAREN);
				}
				break;
			case T__103:
				{
				setState(873);
				match(T__103);
				setState(874);
				match(LPAREN);
				setState(875);
				value();
				setState(880);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(876);
					match(COMMA);
					setState(877);
					value();
					}
					}
					setState(882);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(883);
				match(RPAREN);
				}
				break;
			case T__153:
				{
				setState(885);
				match(T__153);
				setState(886);
				match(NUMBER);
				setState(887);
				match(T__154);
				setState(888);
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
			setState(891);
			match(T__0);
			setState(892);
			tableName();
			setState(893);
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
			setState(895);
			match(CREATE);
			setState(896);
			match(T__155);
			setState(897);
			match(IDENTIFIER);
			setState(898);
			match(ON);
			setState(899);
			tableName();
			setState(900);
			match(LPAREN);
			setState(901);
			columnName();
			setState(906);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(902);
				match(COMMA);
				setState(903);
				columnName();
				}
				}
				setState(908);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(909);
			match(RPAREN);
			setState(911);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__17) {
				{
				setState(910);
				indexOptionsClause();
				}
			}

			setState(913);
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
			setState(915);
			match(T__17);
			setState(916);
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
		public TerminalNode LPAREN() { return getToken(PostgreSQLParser.LPAREN, 0); }
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public TerminalNode RPAREN() { return getToken(PostgreSQLParser.RPAREN, 0); }
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
			setState(925);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__77:
			case T__156:
			case T__157:
			case T__158:
			case T__159:
			case STRING:
			case NUMBER:
				{
				setState(919);
				value();
				}
				break;
			case IDENTIFIER:
				{
				setState(920);
				columnName();
				}
				break;
			case LPAREN:
				{
				setState(921);
				match(LPAREN);
				setState(922);
				expression(0);
				setState(923);
				match(RPAREN);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			_ctx.stop = _input.LT(-1);
			setState(932);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,81,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new ExpressionContext(_parentctx, _parentState);
					pushNewRecursionContext(_localctx, _startState, RULE_expression);
					setState(927);
					if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
					setState(928);
					_la = _input.LA(1);
					if ( !(((((_la - 126)) & ~0x3f) == 0 && ((1L << (_la - 126)) & 15L) != 0)) ) {
					_errHandler.recoverInline(this);
					}
					else {
						if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
						_errHandler.reportMatch(this);
						consume();
					}
					setState(929);
					expression(3);
					}
					} 
				}
				setState(934);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,81,_ctx);
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
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(935);
			_la = _input.LA(1);
			if ( !(_la==T__77 || ((((_la - 157)) & ~0x3f) == 0 && ((1L << (_la - 157)) & 6159L) != 0)) ) {
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
		enterRule(_localctx, 110, RULE_createPolicyStatement);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(937);
			match(CREATE);
			setState(938);
			match(T__160);
			setState(939);
			match(IDENTIFIER);
			setState(940);
			match(ON);
			setState(941);
			tableName();
			setState(944);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==FOR) {
				{
				setState(942);
				match(FOR);
				setState(943);
				_la = _input.LA(1);
				if ( !(_la==T__96 || _la==T__97 || ((((_la - 162)) & ~0x3f) == 0 && ((1L << (_la - 162)) & 269484033L) != 0)) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				}
			}

			setState(951);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==USING) {
				{
				setState(946);
				match(USING);
				setState(947);
				match(LPAREN);
				setState(948);
				condition(0);
				setState(949);
				match(RPAREN);
				}
			}

			setState(958);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__162) {
				{
				setState(953);
				match(T__162);
				setState(954);
				match(LPAREN);
				setState(955);
				condition(0);
				setState(956);
				match(RPAREN);
				}
			}

			setState(960);
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
		enterRule(_localctx, 112, RULE_plpgsqlBlock);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(962);
			match(T__163);
			setState(966);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (((((_la - 166)) & ~0x3f) == 0 && ((1L << (_la - 166)) & 68853694465L) != 0)) {
				{
				{
				setState(963);
				statement();
				}
				}
				setState(968);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(969);
			match(T__164);
			setState(970);
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
		enterRule(_localctx, 114, RULE_statement);
		try {
			setState(989);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case IDENTIFIER:
				enterOuterAlt(_localctx, 1);
				{
				setState(972);
				columnName();
				setState(973);
				match(T__141);
				setState(974);
				columnName();
				setState(975);
				match(T__101);
				setState(976);
				value();
				setState(977);
				match(SEMICOLON);
				}
				break;
			case T__165:
				enterOuterAlt(_localctx, 2);
				{
				setState(979);
				match(T__165);
				setState(980);
				match(NEW);
				setState(981);
				match(SEMICOLON);
				}
				break;
			case NEW:
				enterOuterAlt(_localctx, 3);
				{
				setState(982);
				match(NEW);
				setState(983);
				match(T__141);
				setState(984);
				columnName();
				setState(985);
				match(T__101);
				setState(986);
				value();
				setState(987);
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
		enterRule(_localctx, 116, RULE_createRuleStatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(991);
			match(CREATE);
			setState(992);
			match(RULE);
			setState(993);
			match(IDENTIFIER);
			setState(994);
			match(AS);
			setState(995);
			match(ON);
			setState(996);
			ruleEvent();
			setState(997);
			match(TO);
			setState(998);
			tableName();
			setState(999);
			match(DO);
			setState(1000);
			ruleAction();
			setState(1001);
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
		enterRule(_localctx, 118, RULE_ruleEvent);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1003);
			_la = _input.LA(1);
			if ( !(_la==T__96 || _la==T__97 || _la==DELETE || _la==SELECT) ) {
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
		enterRule(_localctx, 120, RULE_ruleAction);
		try {
			setState(1008);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__166:
				enterOuterAlt(_localctx, 1);
				{
				setState(1005);
				match(T__166);
				}
				break;
			case INSTEAD:
				enterOuterAlt(_localctx, 2);
				{
				setState(1006);
				match(INSTEAD);
				setState(1007);
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
		enterRule(_localctx, 122, RULE_sqlAction);
		int _la;
		try {
			setState(1043);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__99:
				enterOuterAlt(_localctx, 1);
				{
				setState(1010);
				match(T__99);
				setState(1011);
				tableName();
				setState(1012);
				match(LPAREN);
				setState(1013);
				columnName();
				setState(1018);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(1014);
					match(COMMA);
					setState(1015);
					columnName();
					}
					}
					setState(1020);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(1021);
				match(RPAREN);
				setState(1022);
				match(T__100);
				setState(1023);
				match(LPAREN);
				setState(1024);
				value();
				setState(1029);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(1025);
					match(COMMA);
					setState(1026);
					value();
					}
					}
					setState(1031);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(1032);
				match(RPAREN);
				}
				break;
			case T__97:
				enterOuterAlt(_localctx, 2);
				{
				setState(1034);
				match(T__97);
				setState(1035);
				tableName();
				setState(1036);
				match(T__15);
				setState(1037);
				columnName();
				setState(1038);
				match(T__101);
				setState(1039);
				value();
				}
				break;
			case T__102:
				enterOuterAlt(_localctx, 3);
				{
				setState(1041);
				match(T__102);
				setState(1042);
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
		enterRule(_localctx, 124, RULE_defaultValue);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1045);
			match(T__80);
			setState(1050);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case EOF:
				{
				}
				break;
			case T__126:
			case DIGIT:
				{
				setState(1047);
				numericLiteral();
				}
				break;
			case T__156:
			case T__157:
				{
				setState(1048);
				booleanLiteral();
				}
				break;
			case T__158:
				{
				setState(1049);
				match(T__158);
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
		enterRule(_localctx, 126, RULE_numericLiteral);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1053);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__126) {
				{
				setState(1052);
				match(T__126);
				}
			}

			setState(1056); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(1055);
				match(DIGIT);
				}
				}
				setState(1058); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==DIGIT );
			setState(1066);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__141) {
				{
				setState(1060);
				match(T__141);
				setState(1062); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(1061);
					match(DIGIT);
					}
					}
					setState(1064); 
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
		enterRule(_localctx, 128, RULE_booleanLiteral);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1068);
			_la = _input.LA(1);
			if ( !(_la==T__156 || _la==T__157) ) {
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
			return precpred(_ctx, 2);
		}
		return true;
	}

	public static final String _serializedATN =
		"\u0004\u0001\u00ca\u042f\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001"+
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
		"@\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0000"+
		"\u0003\u0000\u0089\b\u0000\u0001\u0000\u0001\u0000\u0001\u0000\u0003\u0000"+
		"\u008e\b\u0000\u0005\u0000\u0090\b\u0000\n\u0000\f\u0000\u0093\t\u0000"+
		"\u0001\u0000\u0001\u0000\u0003\u0000\u0097\b\u0000\u0001\u0000\u0001\u0000"+
		"\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0000"+
		"\u0001\u0000\u0001\u0000\u0003\u0000\u00a3\b\u0000\u0001\u0001\u0001\u0001"+
		"\u0001\u0001\u0005\u0001\u00a8\b\u0001\n\u0001\f\u0001\u00ab\t\u0001\u0001"+
		"\u0001\u0001\u0001\u0003\u0001\u00af\b\u0001\u0001\u0001\u0001\u0001\u0001"+
		"\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001"+
		"\u0001\u0001\u0001\u0001\u0001\u0003\u0001\u00bc\b\u0001\u0001\u0001\u0005"+
		"\u0001\u00bf\b\u0001\n\u0001\f\u0001\u00c2\t\u0001\u0003\u0001\u00c4\b"+
		"\u0001\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001"+
		"\u0002\u0001\u0002\u0001\u0002\u0001\u0003\u0001\u0003\u0001\u0003\u0001"+
		"\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0005"+
		"\u0004\u00d7\b\u0004\n\u0004\f\u0004\u00da\t\u0004\u0001\u0004\u0003\u0004"+
		"\u00dd\b\u0004\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005"+
		"\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005"+
		"\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005"+
		"\u0001\u0005\u0001\u0005\u0001\u0005\u0005\u0005\u00f3\b\u0005\n\u0005"+
		"\f\u0005\u00f6\t\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005"+
		"\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005"+
		"\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005"+
		"\u0001\u0005\u0003\u0005\u0109\b\u0005\u0001\u0005\u0001\u0005\u0001\u0005"+
		"\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005"+
		"\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0003\u0005"+
		"\u0119\b\u0005\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006"+
		"\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006"+
		"\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0004\u0006\u012a\b\u0006"+
		"\u000b\u0006\f\u0006\u012b\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007"+
		"\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007"+
		"\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0003\u0007\u013c\b\u0007"+
		"\u0001\u0007\u0003\u0007\u013f\b\u0007\u0001\u0007\u0001\u0007\u0001\u0007"+
		"\u0001\u0007\u0001\u0007\u0003\u0007\u0146\b\u0007\u0001\u0007\u0003\u0007"+
		"\u0149\b\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007"+
		"\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007"+
		"\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007"+
		"\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0003\u0007\u0160\b\u0007"+
		"\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007"+
		"\u0001\u0007\u0003\u0007\u0169\b\u0007\u0001\u0007\u0001\u0007\u0001\u0007"+
		"\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007"+
		"\u0005\u0007\u0174\b\u0007\n\u0007\f\u0007\u0177\t\u0007\u0001\u0007\u0001"+
		"\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001"+
		"\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0003\u0007\u0185"+
		"\b\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0003\u0007\u018b"+
		"\b\u0007\u0001\u0007\u0001\u0007\u0003\u0007\u018f\b\u0007\u0001\b\u0001"+
		"\b\u0001\b\u0001\b\u0001\b\u0003\b\u0196\b\b\u0001\b\u0001\b\u0001\b\u0001"+
		"\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001"+
		"\b\u0003\b\u01a5\b\b\u0001\b\u0001\b\u0001\b\u0001\b\u0003\b\u01ab\b\b"+
		"\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0003\b\u01b4"+
		"\b\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0003"+
		"\b\u01be\b\b\u0003\b\u01c0\b\b\u0001\t\u0001\t\u0001\n\u0001\n\u0001\u000b"+
		"\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b"+
		"\u0001\u000b\u0001\u000b\u0001\u000b\u0005\u000b\u01d0\b\u000b\n\u000b"+
		"\f\u000b\u01d3\t\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b"+
		"\u0001\u000b\u0001\u000b\u0001\u000b\u0003\u000b\u01dc\b\u000b\u0001\u000b"+
		"\u0005\u000b\u01df\b\u000b\n\u000b\f\u000b\u01e2\t\u000b\u0001\u000b\u0001"+
		"\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001"+
		"\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001"+
		"\u000b\u0003\u000b\u01f2\b\u000b\u0001\f\u0001\f\u0001\f\u0005\f\u01f7"+
		"\b\f\n\f\f\f\u01fa\t\f\u0001\r\u0001\r\u0001\r\u0001\r\u0001\u000e\u0001"+
		"\u000e\u0001\u000e\u0001\u000e\u0003\u000e\u0204\b\u000e\u0001\u000f\u0001"+
		"\u000f\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001"+
		"\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001"+
		"\u0010\u0001\u0010\u0001\u0010\u0005\u0010\u0216\b\u0010\n\u0010\f\u0010"+
		"\u0219\t\u0010\u0003\u0010\u021b\b\u0010\u0001\u0010\u0001\u0010\u0001"+
		"\u0010\u0001\u0011\u0001\u0011\u0001\u0012\u0001\u0012\u0001\u0012\u0005"+
		"\u0012\u0225\b\u0012\n\u0012\f\u0012\u0228\t\u0012\u0001\u0013\u0001\u0013"+
		"\u0001\u0013\u0003\u0013\u022d\b\u0013\u0001\u0014\u0001\u0014\u0001\u0014"+
		"\u0001\u0014\u0001\u0014\u0001\u0014\u0005\u0014\u0235\b\u0014\n\u0014"+
		"\f\u0014\u0238\t\u0014\u0001\u0014\u0001\u0014\u0001\u0014\u0001\u0014"+
		"\u0001\u0014\u0001\u0014\u0005\u0014\u0240\b\u0014\n\u0014\f\u0014\u0243"+
		"\t\u0014\u0001\u0014\u0001\u0014\u0001\u0014\u0001\u0015\u0001\u0015\u0001"+
		"\u0015\u0001\u0015\u0001\u0015\u0001\u0015\u0001\u0015\u0001\u0015\u0001"+
		"\u0015\u0001\u0015\u0001\u0015\u0005\u0015\u0253\b\u0015\n\u0015\f\u0015"+
		"\u0256\t\u0015\u0001\u0015\u0001\u0015\u0003\u0015\u025a\b\u0015\u0001"+
		"\u0015\u0001\u0015\u0001\u0016\u0001\u0016\u0001\u0016\u0001\u0016\u0003"+
		"\u0016\u0262\b\u0016\u0001\u0016\u0001\u0016\u0001\u0017\u0001\u0017\u0001"+
		"\u0017\u0001\u0017\u0001\u0017\u0001\u0017\u0001\u0017\u0001\u0017\u0001"+
		"\u0017\u0001\u0017\u0003\u0017\u0270\b\u0017\u0001\u0017\u0001\u0017\u0001"+
		"\u0017\u0003\u0017\u0275\b\u0017\u0005\u0017\u0277\b\u0017\n\u0017\f\u0017"+
		"\u027a\t\u0017\u0001\u0017\u0001\u0017\u0001\u0017\u0001\u0017\u0001\u0017"+
		"\u0001\u0017\u0001\u0017\u0001\u0017\u0001\u0017\u0001\u0017\u0001\u0017"+
		"\u0001\u0017\u0001\u0017\u0001\u0017\u0001\u0017\u0001\u0017\u0001\u0017"+
		"\u0001\u0017\u0001\u0017\u0001\u0017\u0001\u0017\u0001\u0017\u0001\u0017"+
		"\u0001\u0017\u0001\u0017\u0001\u0017\u0001\u0017\u0003\u0017\u0297\b\u0017"+
		"\u0001\u0017\u0001\u0017\u0001\u0017\u0001\u0017\u0001\u0017\u0001\u0017"+
		"\u0005\u0017\u029f\b\u0017\n\u0017\f\u0017\u02a2\t\u0017\u0001\u0018\u0001"+
		"\u0018\u0001\u0018\u0001\u0018\u0001\u0019\u0001\u0019\u0001\u001a\u0001"+
		"\u001a\u0001\u001b\u0001\u001b\u0001\u001b\u0001\u001b\u0001\u001b\u0001"+
		"\u001b\u0001\u001b\u0001\u001b\u0001\u001b\u0001\u001b\u0005\u001b\u02b6"+
		"\b\u001b\n\u001b\f\u001b\u02b9\t\u001b\u0001\u001b\u0001\u001b\u0001\u001c"+
		"\u0001\u001c\u0001\u001c\u0001\u001c\u0001\u001d\u0001\u001d\u0001\u001d"+
		"\u0001\u001d\u0001\u001d\u0001\u001d\u0001\u001e\u0001\u001e\u0001\u001e"+
		"\u0001\u001e\u0001\u001e\u0001\u001e\u0005\u001e\u02cd\b\u001e\n\u001e"+
		"\f\u001e\u02d0\t\u001e\u0003\u001e\u02d2\b\u001e\u0001\u001e\u0001\u001e"+
		"\u0001\u001e\u0001\u001e\u0001\u001e\u0001\u001e\u0001\u001e\u0001\u001e"+
		"\u0001\u001e\u0001\u001e\u0001\u001e\u0001\u001f\u0001\u001f\u0001\u001f"+
		"\u0001 \u0001 \u0001 \u0001 \u0001 \u0001 \u0005 \u02e8\b \n \f \u02eb"+
		"\t \u0003 \u02ed\b \u0001 \u0003 \u02f0\b \u0001 \u0003 \u02f3\b \u0001"+
		" \u0003 \u02f6\b \u0001!\u0001!\u0001!\u0001!\u0005!\u02fc\b!\n!\f!\u02ff"+
		"\t!\u0003!\u0301\b!\u0001\"\u0001\"\u0001\"\u0003\"\u0306\b\"\u0001#\u0001"+
		"#\u0001#\u0001$\u0001$\u0001$\u0003$\u030e\b$\u0001%\u0001%\u0001%\u0001"+
		"&\u0001&\u0001&\u0001&\u0005&\u0317\b&\n&\f&\u031a\t&\u0001&\u0003&\u031d"+
		"\b&\u0001\'\u0003\'\u0320\b\'\u0001\'\u0001\'\u0001\'\u0003\'\u0325\b"+
		"\'\u0001\'\u0001\'\u0001\'\u0001\'\u0005\'\u032b\b\'\n\'\f\'\u032e\t\'"+
		"\u0001\'\u0001\'\u0001(\u0001(\u0001(\u0001(\u0001(\u0001(\u0001(\u0001"+
		"(\u0003(\u033a\b(\u0003(\u033c\b(\u0001)\u0001)\u0001*\u0001*\u0001+\u0001"+
		"+\u0001,\u0001,\u0001-\u0001-\u0001-\u0001-\u0001-\u0003-\u034b\b-\u0001"+
		"-\u0003-\u034e\b-\u0001.\u0001.\u0001.\u0001.\u0001.\u0001.\u0001/\u0001"+
		"/\u00010\u00010\u00010\u00010\u00010\u00010\u00010\u00010\u00011\u0001"+
		"1\u00011\u00011\u00011\u00011\u00011\u00011\u00011\u00011\u00011\u0001"+
		"1\u00011\u00011\u00011\u00051\u036f\b1\n1\f1\u0372\t1\u00011\u00011\u0001"+
		"1\u00011\u00011\u00011\u00031\u037a\b1\u00012\u00012\u00012\u00012\u0001"+
		"3\u00013\u00013\u00013\u00013\u00013\u00013\u00013\u00013\u00053\u0389"+
		"\b3\n3\f3\u038c\t3\u00013\u00013\u00033\u0390\b3\u00013\u00013\u00014"+
		"\u00014\u00014\u00015\u00015\u00015\u00015\u00015\u00015\u00015\u0003"+
		"5\u039e\b5\u00015\u00015\u00015\u00055\u03a3\b5\n5\f5\u03a6\t5\u00016"+
		"\u00016\u00017\u00017\u00017\u00017\u00017\u00017\u00017\u00037\u03b1"+
		"\b7\u00017\u00017\u00017\u00017\u00017\u00037\u03b8\b7\u00017\u00017\u0001"+
		"7\u00017\u00017\u00037\u03bf\b7\u00017\u00017\u00018\u00018\u00058\u03c5"+
		"\b8\n8\f8\u03c8\t8\u00018\u00018\u00018\u00019\u00019\u00019\u00019\u0001"+
		"9\u00019\u00019\u00019\u00019\u00019\u00019\u00019\u00019\u00019\u0001"+
		"9\u00019\u00019\u00039\u03de\b9\u0001:\u0001:\u0001:\u0001:\u0001:\u0001"+
		":\u0001:\u0001:\u0001:\u0001:\u0001:\u0001:\u0001;\u0001;\u0001<\u0001"+
		"<\u0001<\u0003<\u03f1\b<\u0001=\u0001=\u0001=\u0001=\u0001=\u0001=\u0005"+
		"=\u03f9\b=\n=\f=\u03fc\t=\u0001=\u0001=\u0001=\u0001=\u0001=\u0001=\u0005"+
		"=\u0404\b=\n=\f=\u0407\t=\u0001=\u0001=\u0001=\u0001=\u0001=\u0001=\u0001"+
		"=\u0001=\u0001=\u0001=\u0001=\u0003=\u0414\b=\u0001>\u0001>\u0001>\u0001"+
		">\u0001>\u0003>\u041b\b>\u0001?\u0003?\u041e\b?\u0001?\u0004?\u0421\b"+
		"?\u000b?\f?\u0422\u0001?\u0001?\u0004?\u0427\b?\u000b?\f?\u0428\u0003"+
		"?\u042b\b?\u0001@\u0001@\u0001@\u0000\u0002.jA\u0000\u0002\u0004\u0006"+
		"\b\n\f\u000e\u0010\u0012\u0014\u0016\u0018\u001a\u001c\u001e \"$&(*,."+
		"02468:<>@BDFHJLNPRTVXZ\\^`bdfhjlnprtvxz|~\u0080\u0000\u0011\u0001\u0000"+
		"\u0005\u0006\u0001\u0000 #\u0001\u0000&(\u0002\u0000\u001a\u001aVX\u0003"+
		"\u0000\u001a\u001aVX[[\u0001\u0000^`\u0002\u0000ab\u00b6\u00b6\u0002\u0000"+
		"ffr}\u0001\u0000~\u0083\u0001\u0000\u008b\u008c\u0001\u0000\u0091\u0094"+
		"\u0001\u0000\u0096\u0098\u0001\u0000~\u0081\u0003\u0000NN\u009d\u00a0"+
		"\u00a8\u00a9\u0004\u0000ab\u00a2\u00a2\u00b6\u00b6\u00be\u00be\u0003\u0000"+
		"ab\u00b6\u00b6\u00be\u00be\u0001\u0000\u009d\u009e\u04a9\u0000\u00a2\u0001"+
		"\u0000\u0000\u0000\u0002\u00c3\u0001\u0000\u0000\u0000\u0004\u00c5\u0001"+
		"\u0000\u0000\u0000\u0006\u00cd\u0001\u0000\u0000\u0000\b\u00d0\u0001\u0000"+
		"\u0000\u0000\n\u0118\u0001\u0000\u0000\u0000\f\u0129\u0001\u0000\u0000"+
		"\u0000\u000e\u018e\u0001\u0000\u0000\u0000\u0010\u01bf\u0001\u0000\u0000"+
		"\u0000\u0012\u01c1\u0001\u0000\u0000\u0000\u0014\u01c3\u0001\u0000\u0000"+
		"\u0000\u0016\u01f1\u0001\u0000\u0000\u0000\u0018\u01f3\u0001\u0000\u0000"+
		"\u0000\u001a\u01fb\u0001\u0000\u0000\u0000\u001c\u0203\u0001\u0000\u0000"+
		"\u0000\u001e\u0205\u0001\u0000\u0000\u0000 \u0207\u0001\u0000\u0000\u0000"+
		"\"\u021f\u0001\u0000\u0000\u0000$\u0221\u0001\u0000\u0000\u0000&\u022c"+
		"\u0001\u0000\u0000\u0000(\u022e\u0001\u0000\u0000\u0000*\u0247\u0001\u0000"+
		"\u0000\u0000,\u025d\u0001\u0000\u0000\u0000.\u0296\u0001\u0000\u0000\u0000"+
		"0\u02a3\u0001\u0000\u0000\u00002\u02a7\u0001\u0000\u0000\u00004\u02a9"+
		"\u0001\u0000\u0000\u00006\u02b7\u0001\u0000\u0000\u00008\u02bc\u0001\u0000"+
		"\u0000\u0000:\u02c0\u0001\u0000\u0000\u0000<\u02c6\u0001\u0000\u0000\u0000"+
		">\u02de\u0001\u0000\u0000\u0000@\u02e1\u0001\u0000\u0000\u0000B\u0300"+
		"\u0001\u0000\u0000\u0000D\u0302\u0001\u0000\u0000\u0000F\u0307\u0001\u0000"+
		"\u0000\u0000H\u030a\u0001\u0000\u0000\u0000J\u030f\u0001\u0000\u0000\u0000"+
		"L\u0312\u0001\u0000\u0000\u0000N\u0324\u0001\u0000\u0000\u0000P\u033b"+
		"\u0001\u0000\u0000\u0000R\u033d\u0001\u0000\u0000\u0000T\u033f\u0001\u0000"+
		"\u0000\u0000V\u0341\u0001\u0000\u0000\u0000X\u0343\u0001\u0000\u0000\u0000"+
		"Z\u0345\u0001\u0000\u0000\u0000\\\u034f\u0001\u0000\u0000\u0000^\u0355"+
		"\u0001\u0000\u0000\u0000`\u0357\u0001\u0000\u0000\u0000b\u035f\u0001\u0000"+
		"\u0000\u0000d\u037b\u0001\u0000\u0000\u0000f\u037f\u0001\u0000\u0000\u0000"+
		"h\u0393\u0001\u0000\u0000\u0000j\u039d\u0001\u0000\u0000\u0000l\u03a7"+
		"\u0001\u0000\u0000\u0000n\u03a9\u0001\u0000\u0000\u0000p\u03c2\u0001\u0000"+
		"\u0000\u0000r\u03dd\u0001\u0000\u0000\u0000t\u03df\u0001\u0000\u0000\u0000"+
		"v\u03eb\u0001\u0000\u0000\u0000x\u03f0\u0001\u0000\u0000\u0000z\u0413"+
		"\u0001\u0000\u0000\u0000|\u0415\u0001\u0000\u0000\u0000~\u041d\u0001\u0000"+
		"\u0000\u0000\u0080\u042c\u0001\u0000\u0000\u0000\u0082\u0083\u0005\u00c4"+
		"\u0000\u0000\u0083\u0084\u0005\u00c3\u0000\u0000\u0084\u0085\u0003T*\u0000"+
		"\u0085\u0088\u0005\u00ac\u0000\u0000\u0086\u0089\u0003\u0002\u0001\u0000"+
		"\u0087\u0089\u0003\u0016\u000b\u0000\u0088\u0086\u0001\u0000\u0000\u0000"+
		"\u0088\u0087\u0001\u0000\u0000\u0000\u0089\u0091\u0001\u0000\u0000\u0000"+
		"\u008a\u008d\u0005\u00b3\u0000\u0000\u008b\u008e\u0003\u0002\u0001\u0000"+
		"\u008c\u008e\u0003\u0016\u000b\u0000\u008d\u008b\u0001\u0000\u0000\u0000"+
		"\u008d\u008c\u0001\u0000\u0000\u0000\u008e\u0090\u0001\u0000\u0000\u0000"+
		"\u008f\u008a\u0001\u0000\u0000\u0000\u0090\u0093\u0001\u0000\u0000\u0000"+
		"\u0091\u008f\u0001\u0000\u0000\u0000\u0091\u0092\u0001\u0000\u0000\u0000"+
		"\u0092\u0094\u0001\u0000\u0000\u0000\u0093\u0091\u0001\u0000\u0000\u0000"+
		"\u0094\u0096\u0005\u00ad\u0000\u0000\u0095\u0097\u0003\\.\u0000\u0096"+
		"\u0095\u0001\u0000\u0000\u0000\u0096\u0097\u0001\u0000\u0000\u0000\u0097"+
		"\u0098\u0001\u0000\u0000\u0000\u0098\u0099\u0005\u00b0\u0000\u0000\u0099"+
		"\u00a3\u0001\u0000\u0000\u0000\u009a\u009b\u0005\u00c4\u0000\u0000\u009b"+
		"\u009c\u0005\u00c3\u0000\u0000\u009c\u009d\u0003T*\u0000\u009d\u009e\u0005"+
		"\u0001\u0000\u0000\u009e\u009f\u0003T*\u0000\u009f\u00a0\u0003b1\u0000"+
		"\u00a0\u00a1\u0005\u00b0\u0000\u0000\u00a1\u00a3\u0001\u0000\u0000\u0000"+
		"\u00a2\u0082\u0001\u0000\u0000\u0000\u00a2\u009a\u0001\u0000\u0000\u0000"+
		"\u00a3\u0001\u0001\u0000\u0000\u0000\u00a4\u00a5\u0003V+\u0000\u00a5\u00a9"+
		"\u0003\u000e\u0007\u0000\u00a6\u00a8\u0003\u0010\b\u0000\u00a7\u00a6\u0001"+
		"\u0000\u0000\u0000\u00a8\u00ab\u0001\u0000\u0000\u0000\u00a9\u00a7\u0001"+
		"\u0000\u0000\u0000\u00a9\u00aa\u0001\u0000\u0000\u0000\u00aa\u00ae\u0001"+
		"\u0000\u0000\u0000\u00ab\u00a9\u0001\u0000\u0000\u0000\u00ac\u00af\u0003"+
		"\u0004\u0002\u0000\u00ad\u00af\u0003\u0006\u0003\u0000\u00ae\u00ac\u0001"+
		"\u0000\u0000\u0000\u00ae\u00ad\u0001\u0000\u0000\u0000\u00ae\u00af\u0001"+
		"\u0000\u0000\u0000\u00af\u00c4\u0001\u0000\u0000\u0000\u00b0\u00b1\u0005"+
		"\u0002\u0000\u0000\u00b1\u00b2\u0005\u00ac\u0000\u0000\u00b2\u00b3\u0003"+
		"L&\u0000\u00b3\u00b4\u0005\u00ad\u0000\u0000\u00b4\u00b5\u0005\u0003\u0000"+
		"\u0000\u00b5\u00b6\u0003T*\u0000\u00b6\u00b7\u0005\u00ac\u0000\u0000\u00b7"+
		"\u00b8\u0003L&\u0000\u00b8\u00bb\u0005\u00ad\u0000\u0000\u00b9\u00ba\u0005"+
		"\u00c5\u0000\u0000\u00ba\u00bc\u0003X,\u0000\u00bb\u00b9\u0001\u0000\u0000"+
		"\u0000\u00bb\u00bc\u0001\u0000\u0000\u0000\u00bc\u00c0\u0001\u0000\u0000"+
		"\u0000\u00bd\u00bf\u0003\u001c\u000e\u0000\u00be\u00bd\u0001\u0000\u0000"+
		"\u0000\u00bf\u00c2\u0001\u0000\u0000\u0000\u00c0\u00be\u0001\u0000\u0000"+
		"\u0000\u00c0\u00c1\u0001\u0000\u0000\u0000\u00c1\u00c4\u0001\u0000\u0000"+
		"\u0000\u00c2\u00c0\u0001\u0000\u0000\u0000\u00c3\u00a4\u0001\u0000\u0000"+
		"\u0000\u00c3\u00b0\u0001\u0000\u0000\u0000\u00c4\u0003\u0001\u0000\u0000"+
		"\u0000\u00c5\u00c6\u0005\u0004\u0000\u0000\u00c6\u00c7\u0007\u0000\u0000"+
		"\u0000\u00c7\u00c8\u0005\u00b9\u0000\u0000\u00c8\u00c9\u0005\u00ac\u0000"+
		"\u0000\u00c9\u00ca\u0003j5\u0000\u00ca\u00cb\u0005\u00ad\u0000\u0000\u00cb"+
		"\u00cc\u0005\u0007\u0000\u0000\u00cc\u0005\u0001\u0000\u0000\u0000\u00cd"+
		"\u00ce\u0005\b\u0000\u0000\u00ce\u00cf\u0005\u00a8\u0000\u0000\u00cf\u0007"+
		"\u0001\u0000\u0000\u0000\u00d0\u00d1\u0005\t\u0000\u0000\u00d1\u00d2\u0005"+
		"\u00c3\u0000\u0000\u00d2\u00d3\u0003T*\u0000\u00d3\u00d8\u0003\n\u0005"+
		"\u0000\u00d4\u00d5\u0005\u00b3\u0000\u0000\u00d5\u00d7\u0003\n\u0005\u0000"+
		"\u00d6\u00d4\u0001\u0000\u0000\u0000\u00d7\u00da\u0001\u0000\u0000\u0000"+
		"\u00d8\u00d6\u0001\u0000\u0000\u0000\u00d8\u00d9\u0001\u0000\u0000\u0000"+
		"\u00d9\u00dc\u0001\u0000\u0000\u0000\u00da\u00d8\u0001\u0000\u0000\u0000"+
		"\u00db\u00dd\u0005\u00b0\u0000\u0000\u00dc\u00db\u0001\u0000\u0000\u0000"+
		"\u00dc\u00dd\u0001\u0000\u0000\u0000\u00dd\t\u0001\u0000\u0000\u0000\u00de"+
		"\u00df\u0005\n\u0000\u0000\u00df\u00e0\u0005\u000b\u0000\u0000\u00e0\u0119"+
		"\u0003\u0002\u0001\u0000\u00e1\u00e2\u0005\f\u0000\u0000\u00e2\u00e3\u0005"+
		"\u000b\u0000\u0000\u00e3\u0119\u0003V+\u0000\u00e4\u00e5\u0005\n\u0000"+
		"\u0000\u00e5\u00e6\u0005\r\u0000\u0000\u00e6\u00e7\u0005\u00ca\u0000\u0000"+
		"\u00e7\u0119\u0003\u0016\u000b\u0000\u00e8\u00e9\u0005\f\u0000\u0000\u00e9"+
		"\u00ea\u0005\r\u0000\u0000\u00ea\u0119\u0005\u00ca\u0000\u0000\u00eb\u00ec"+
		"\u0005\n\u0000\u0000\u00ec\u00ed\u0005\u0002\u0000\u0000\u00ed\u00ee\u0003"+
		"L&\u0000\u00ee\u00ef\u0005\u0003\u0000\u0000\u00ef\u00f0\u0003T*\u0000"+
		"\u00f0\u00f4\u0003L&\u0000\u00f1\u00f3\u0003\u001c\u000e\u0000\u00f2\u00f1"+
		"\u0001\u0000\u0000\u0000\u00f3\u00f6\u0001\u0000\u0000\u0000\u00f4\u00f2"+
		"\u0001\u0000\u0000\u0000\u00f4\u00f5\u0001\u0000\u0000\u0000\u00f5\u0119"+
		"\u0001\u0000\u0000\u0000\u00f6\u00f4\u0001\u0000\u0000\u0000\u00f7\u00f8"+
		"\u0005\t\u0000\u0000\u00f8\u00f9\u0005\u000b\u0000\u0000\u00f9\u00fa\u0003"+
		"V+\u0000\u00fa\u00fb\u0003\f\u0006\u0000\u00fb\u0119\u0001\u0000\u0000"+
		"\u0000\u00fc\u00fd\u0005\u000e\u0000\u0000\u00fd\u00fe\u0003V+\u0000\u00fe"+
		"\u00ff\u0005\u00ba\u0000\u0000\u00ff\u0100\u0003V+\u0000\u0100\u0119\u0001"+
		"\u0000\u0000\u0000\u0101\u0102\u0005\u000f\u0000\u0000\u0102\u0119\u0003"+
		"T*\u0000\u0103\u0108\u0005\u0010\u0000\u0000\u0104\u0105\u0005\u0011\u0000"+
		"\u0000\u0105\u0109\u0003R)\u0000\u0106\u0107\u0005\u0012\u0000\u0000\u0107"+
		"\u0109\u0005\u00ca\u0000\u0000\u0108\u0104\u0001\u0000\u0000\u0000\u0108"+
		"\u0106\u0001\u0000\u0000\u0000\u0109\u0119\u0001\u0000\u0000\u0000\u010a"+
		"\u010b\u0005\u0013\u0000\u0000\u010b\u010c\u0005\u0014\u0000\u0000\u010c"+
		"\u010d\u0005\u0015\u0000\u0000\u010d\u0119\u0005\u00ca\u0000\u0000\u010e"+
		"\u010f\u0005\u0016\u0000\u0000\u010f\u0110\u0005\u0014\u0000\u0000\u0110"+
		"\u0111\u0005\u0015\u0000\u0000\u0111\u0119\u0005\u00ca\u0000\u0000\u0112"+
		"\u0113\u0005\u0017\u0000\u0000\u0113\u0114\u0005\u00b5\u0000\u0000\u0114"+
		"\u0119\u0005\u00ca\u0000\u0000\u0115\u0119\u0005\u0018\u0000\u0000\u0116"+
		"\u0117\u0005\u0019\u0000\u0000\u0117\u0119\u0005\u00ca\u0000\u0000\u0118"+
		"\u00de\u0001\u0000\u0000\u0000\u0118\u00e1\u0001\u0000\u0000\u0000\u0118"+
		"\u00e4\u0001\u0000\u0000\u0000\u0118\u00e8\u0001\u0000\u0000\u0000\u0118"+
		"\u00eb\u0001\u0000\u0000\u0000\u0118\u00f7\u0001\u0000\u0000\u0000\u0118"+
		"\u00fc\u0001\u0000\u0000\u0000\u0118\u0101\u0001\u0000\u0000\u0000\u0118"+
		"\u0103\u0001\u0000\u0000\u0000\u0118\u010a\u0001\u0000\u0000\u0000\u0118"+
		"\u010e\u0001\u0000\u0000\u0000\u0118\u0112\u0001\u0000\u0000\u0000\u0118"+
		"\u0115\u0001\u0000\u0000\u0000\u0118\u0116\u0001\u0000\u0000\u0000\u0119"+
		"\u000b\u0001\u0000\u0000\u0000\u011a\u011b\u0005\u001a\u0000\u0000\u011b"+
		"\u012a\u0003l6\u0000\u011c\u012a\u0005\u001b\u0000\u0000\u011d\u012a\u0005"+
		"\u001c\u0000\u0000\u011e\u012a\u0005\u001d\u0000\u0000\u011f\u0120\u0005"+
		"\u001e\u0000\u0000\u0120\u012a\u0005\u00a9\u0000\u0000\u0121\u0122\u0005"+
		"\u001f\u0000\u0000\u0122\u012a\u0007\u0001\u0000\u0000\u0123\u0124\u0005"+
		"$\u0000\u0000\u0124\u012a\u0005\u00ca\u0000\u0000\u0125\u0126\u0005%\u0000"+
		"\u0000\u0126\u012a\u0007\u0002\u0000\u0000\u0127\u0128\u0005)\u0000\u0000"+
		"\u0128\u012a\u0003\u000e\u0007\u0000\u0129\u011a\u0001\u0000\u0000\u0000"+
		"\u0129\u011c\u0001\u0000\u0000\u0000\u0129\u011d\u0001\u0000\u0000\u0000"+
		"\u0129\u011e\u0001\u0000\u0000\u0000\u0129\u011f\u0001\u0000\u0000\u0000"+
		"\u0129\u0121\u0001\u0000\u0000\u0000\u0129\u0123\u0001\u0000\u0000\u0000"+
		"\u0129\u0125\u0001\u0000\u0000\u0000\u0129\u0127\u0001\u0000\u0000\u0000"+
		"\u012a\u012b\u0001\u0000\u0000\u0000\u012b\u0129\u0001\u0000\u0000\u0000"+
		"\u012b\u012c\u0001\u0000\u0000\u0000\u012c\r\u0001\u0000\u0000\u0000\u012d"+
		"\u018f\u0005*\u0000\u0000\u012e\u018f\u0005\u00c7\u0000\u0000\u012f\u018f"+
		"\u0005+\u0000\u0000\u0130\u018f\u0005,\u0000\u0000\u0131\u018f\u0005-"+
		"\u0000\u0000\u0132\u018f\u0005.\u0000\u0000\u0133\u018f\u0005/\u0000\u0000"+
		"\u0134\u018f\u00050\u0000\u0000\u0135\u018f\u00051\u0000\u0000\u0136\u013e"+
		"\u00052\u0000\u0000\u0137\u0138\u0005\u00ac\u0000\u0000\u0138\u013b\u0005"+
		"\u00a9\u0000\u0000\u0139\u013a\u0005\u00b3\u0000\u0000\u013a\u013c\u0005"+
		"\u00a9\u0000\u0000\u013b\u0139\u0001\u0000\u0000\u0000\u013b\u013c\u0001"+
		"\u0000\u0000\u0000\u013c\u013d\u0001\u0000\u0000\u0000\u013d\u013f\u0005"+
		"\u00ad\u0000\u0000\u013e\u0137\u0001\u0000\u0000\u0000\u013e\u013f\u0001"+
		"\u0000\u0000\u0000\u013f\u018f\u0001\u0000\u0000\u0000\u0140\u0148\u0005"+
		"\u00c6\u0000\u0000\u0141\u0142\u0005\u00ac\u0000\u0000\u0142\u0145\u0005"+
		"\u00a9\u0000\u0000\u0143\u0144\u0005\u00b3\u0000\u0000\u0144\u0146\u0005"+
		"\u00a9\u0000\u0000\u0145\u0143\u0001\u0000\u0000\u0000\u0145\u0146\u0001"+
		"\u0000\u0000\u0000\u0146\u0147\u0001\u0000\u0000\u0000\u0147\u0149\u0005"+
		"\u00ad\u0000\u0000\u0148\u0141\u0001\u0000\u0000\u0000\u0148\u0149\u0001"+
		"\u0000\u0000\u0000\u0149\u018f\u0001\u0000\u0000\u0000\u014a\u018f\u0005"+
		"3\u0000\u0000\u014b\u014c\u00054\u0000\u0000\u014c\u014d\u0005\u00ac\u0000"+
		"\u0000\u014d\u014e\u0005\u00a9\u0000\u0000\u014e\u018f\u0005\u00ad\u0000"+
		"\u0000\u014f\u0150\u0005\u00c8\u0000\u0000\u0150\u0151\u0005\u00ac\u0000"+
		"\u0000\u0151\u0152\u0005\u00a9\u0000\u0000\u0152\u018f\u0005\u00ad\u0000"+
		"\u0000\u0153\u018f\u00055\u0000\u0000\u0154\u018f\u00056\u0000\u0000\u0155"+
		"\u018f\u00057\u0000\u0000\u0156\u018f\u00058\u0000\u0000\u0157\u018f\u0005"+
		"9\u0000\u0000\u0158\u015f\u0005:\u0000\u0000\u0159\u015a\u0005;\u0000"+
		"\u0000\u015a\u015b\u0005:\u0000\u0000\u015b\u0160\u0005<\u0000\u0000\u015c"+
		"\u015d\u0005=\u0000\u0000\u015d\u015e\u0005:\u0000\u0000\u015e\u0160\u0005"+
		"<\u0000\u0000\u015f\u0159\u0001\u0000\u0000\u0000\u015f\u015c\u0001\u0000"+
		"\u0000\u0000\u015f\u0160\u0001\u0000\u0000\u0000\u0160\u018f\u0001\u0000"+
		"\u0000\u0000\u0161\u0168\u0005>\u0000\u0000\u0162\u0163\u0005;\u0000\u0000"+
		"\u0163\u0164\u0005:\u0000\u0000\u0164\u0169\u0005<\u0000\u0000\u0165\u0166"+
		"\u0005=\u0000\u0000\u0166\u0167\u0005:\u0000\u0000\u0167\u0169\u0005<"+
		"\u0000\u0000\u0168\u0162\u0001\u0000\u0000\u0000\u0168\u0165\u0001\u0000"+
		"\u0000\u0000\u0168\u0169\u0001\u0000\u0000\u0000\u0169\u018f\u0001\u0000"+
		"\u0000\u0000\u016a\u018f\u0005?\u0000\u0000\u016b\u018f\u0005@\u0000\u0000"+
		"\u016c\u018f\u0005A\u0000\u0000\u016d\u018f\u0005B\u0000\u0000\u016e\u016f"+
		"\u0005C\u0000\u0000\u016f\u0170\u0005\u00ac\u0000\u0000\u0170\u0175\u0005"+
		"\u00a8\u0000\u0000\u0171\u0172\u0005\u00b3\u0000\u0000\u0172\u0174\u0005"+
		"\u00a8\u0000\u0000\u0173\u0171\u0001\u0000\u0000\u0000\u0174\u0177\u0001"+
		"\u0000\u0000\u0000\u0175\u0173\u0001\u0000\u0000\u0000\u0175\u0176\u0001"+
		"\u0000\u0000\u0000\u0176\u0178\u0001\u0000\u0000\u0000\u0177\u0175\u0001"+
		"\u0000\u0000\u0000\u0178\u018f\u0005\u00ad\u0000\u0000\u0179\u018f\u0005"+
		"D\u0000\u0000\u017a\u018f\u0005E\u0000\u0000\u017b\u018f\u0005F\u0000"+
		"\u0000\u017c\u018f\u0005G\u0000\u0000\u017d\u018f\u0005H\u0000\u0000\u017e"+
		"\u018f\u0005I\u0000\u0000\u017f\u018f\u0005J\u0000\u0000\u0180\u0184\u0005"+
		"K\u0000\u0000\u0181\u0182\u0005\u00ac\u0000\u0000\u0182\u0183\u0005\u00a9"+
		"\u0000\u0000\u0183\u0185\u0005\u00ad\u0000\u0000\u0184\u0181\u0001\u0000"+
		"\u0000\u0000\u0184\u0185\u0001\u0000\u0000\u0000\u0185\u018f\u0001\u0000"+
		"\u0000\u0000\u0186\u018a\u0005L\u0000\u0000\u0187\u0188\u0005\u00ac\u0000"+
		"\u0000\u0188\u0189\u0005\u00a9\u0000\u0000\u0189\u018b\u0005\u00ad\u0000"+
		"\u0000\u018a\u0187\u0001\u0000\u0000\u0000\u018a\u018b\u0001\u0000\u0000"+
		"\u0000\u018b\u018f\u0001\u0000\u0000\u0000\u018c\u018f\u0005\u0015\u0000"+
		"\u0000\u018d\u018f\u0005\u00ca\u0000\u0000\u018e\u012d\u0001\u0000\u0000"+
		"\u0000\u018e\u012e\u0001\u0000\u0000\u0000\u018e\u012f\u0001\u0000\u0000"+
		"\u0000\u018e\u0130\u0001\u0000\u0000\u0000\u018e\u0131\u0001\u0000\u0000"+
		"\u0000\u018e\u0132\u0001\u0000\u0000\u0000\u018e\u0133\u0001\u0000\u0000"+
		"\u0000\u018e\u0134\u0001\u0000\u0000\u0000\u018e\u0135\u0001\u0000\u0000"+
		"\u0000\u018e\u0136\u0001\u0000\u0000\u0000\u018e\u0140\u0001\u0000\u0000"+
		"\u0000\u018e\u014a\u0001\u0000\u0000\u0000\u018e\u014b\u0001\u0000\u0000"+
		"\u0000\u018e\u014f\u0001\u0000\u0000\u0000\u018e\u0153\u0001\u0000\u0000"+
		"\u0000\u018e\u0154\u0001\u0000\u0000\u0000\u018e\u0155\u0001\u0000\u0000"+
		"\u0000\u018e\u0156\u0001\u0000\u0000\u0000\u018e\u0157\u0001\u0000\u0000"+
		"\u0000\u018e\u0158\u0001\u0000\u0000\u0000\u018e\u0161\u0001\u0000\u0000"+
		"\u0000\u018e\u016a\u0001\u0000\u0000\u0000\u018e\u016b\u0001\u0000\u0000"+
		"\u0000\u018e\u016c\u0001\u0000\u0000\u0000\u018e\u016d\u0001\u0000\u0000"+
		"\u0000\u018e\u016e\u0001\u0000\u0000\u0000\u018e\u0179\u0001\u0000\u0000"+
		"\u0000\u018e\u017a\u0001\u0000\u0000\u0000\u018e\u017b\u0001\u0000\u0000"+
		"\u0000\u018e\u017c\u0001\u0000\u0000\u0000\u018e\u017d\u0001\u0000\u0000"+
		"\u0000\u018e\u017e\u0001\u0000\u0000\u0000\u018e\u017f\u0001\u0000\u0000"+
		"\u0000\u018e\u0180\u0001\u0000\u0000\u0000\u018e\u0186\u0001\u0000\u0000"+
		"\u0000\u018e\u018c\u0001\u0000\u0000\u0000\u018e\u018d\u0001\u0000\u0000"+
		"\u0000\u018f\u000f\u0001\u0000\u0000\u0000\u0190\u01c0\u0005M\u0000\u0000"+
		"\u0191\u01c0\u0005N\u0000\u0000\u0192\u0195\u0005O\u0000\u0000\u0193\u0194"+
		"\u0005P\u0000\u0000\u0194\u0196\u0003P(\u0000\u0195\u0193\u0001\u0000"+
		"\u0000\u0000\u0195\u0196\u0001\u0000\u0000\u0000\u0196\u01c0\u0001\u0000"+
		"\u0000\u0000\u0197\u0198\u0005Q\u0000\u0000\u0198\u01c0\u0003l6\u0000"+
		"\u0199\u019a\u0005R\u0000\u0000\u019a\u019b\u0005\u00ac\u0000\u0000\u019b"+
		"\u019c\u0003.\u0017\u0000\u019c\u019d\u0005\u00ad\u0000\u0000\u019d\u01c0"+
		"\u0001\u0000\u0000\u0000\u019e\u019f\u0005\u0003\u0000\u0000\u019f\u01a0"+
		"\u0003T*\u0000\u01a0\u01a1\u0005\u00ac\u0000\u0000\u01a1\u01a2\u0003V"+
		"+\u0000\u01a2\u01a4\u0005\u00ad\u0000\u0000\u01a3\u01a5\u0003\u001c\u000e"+
		"\u0000\u01a4\u01a3\u0001\u0000\u0000\u0000\u01a4\u01a5\u0001\u0000\u0000"+
		"\u0000\u01a5\u01c0\u0001\u0000\u0000\u0000\u01a6\u01c0\u0005\u00c9\u0000"+
		"\u0000\u01a7\u01aa\u0005\u00c9\u0000\u0000\u01a8\u01a9\u0005S\u0000\u0000"+
		"\u01a9\u01ab\u0005\u00ca\u0000\u0000\u01aa\u01a8\u0001\u0000\u0000\u0000"+
		"\u01aa\u01ab\u0001\u0000\u0000\u0000\u01ab\u01c0\u0001\u0000\u0000\u0000"+
		"\u01ac\u01ad\u0005\u0002\u0000\u0000\u01ad\u01ae\u0003L&\u0000\u01ae\u01af"+
		"\u0005\u0003\u0000\u0000\u01af\u01b0\u0003T*\u0000\u01b0\u01b3\u0003L"+
		"&\u0000\u01b1\u01b2\u0005\u00c5\u0000\u0000\u01b2\u01b4\u0003X,\u0000"+
		"\u01b3\u01b1\u0001\u0000\u0000\u0000\u01b3\u01b4\u0001\u0000\u0000\u0000"+
		"\u01b4\u01c0\u0001\u0000\u0000\u0000\u01b5\u01b6\u0005T\u0000\u0000\u01b6"+
		"\u01b7\u0005\u00bf\u0000\u0000\u01b7\u01b8\u0005\u00ca\u0000\u0000\u01b8"+
		"\u01b9\u0005\u00ac\u0000\u0000\u01b9\u01ba\u0003\u0018\f\u0000\u01ba\u01bd"+
		"\u0005\u00ad\u0000\u0000\u01bb\u01bc\u0005U\u0000\u0000\u01bc\u01be\u0003"+
		".\u0017\u0000\u01bd\u01bb\u0001\u0000\u0000\u0000\u01bd\u01be\u0001\u0000"+
		"\u0000\u0000\u01be\u01c0\u0001\u0000\u0000\u0000\u01bf\u0190\u0001\u0000"+
		"\u0000\u0000\u01bf\u0191\u0001\u0000\u0000\u0000\u01bf\u0192\u0001\u0000"+
		"\u0000\u0000\u01bf\u0197\u0001\u0000\u0000\u0000\u01bf\u0199\u0001\u0000"+
		"\u0000\u0000\u01bf\u019e\u0001\u0000\u0000\u0000\u01bf\u01a6\u0001\u0000"+
		"\u0000\u0000\u01bf\u01a7\u0001\u0000\u0000\u0000\u01bf\u01ac\u0001\u0000"+
		"\u0000\u0000\u01bf\u01b5\u0001\u0000\u0000\u0000\u01c0\u0011\u0001\u0000"+
		"\u0000\u0000\u01c1\u01c2\u0007\u0003\u0000\u0000\u01c2\u0013\u0001\u0000"+
		"\u0000\u0000\u01c3\u01c4\u0007\u0003\u0000\u0000\u01c4\u0015\u0001\u0000"+
		"\u0000\u0000\u01c5\u01c6\u0005\u00c9\u0000\u0000\u01c6\u01f2\u0003L&\u0000"+
		"\u01c7\u01c8\u0005\r\u0000\u0000\u01c8\u01c9\u0005\u00ca\u0000\u0000\u01c9"+
		"\u01ca\u0005\u0002\u0000\u0000\u01ca\u01cb\u0003L&\u0000\u01cb\u01cc\u0005"+
		"\u0003\u0000\u0000\u01cc\u01cd\u0003T*\u0000\u01cd\u01d1\u0003L&\u0000"+
		"\u01ce\u01d0\u0003\u001c\u000e\u0000\u01cf\u01ce\u0001\u0000\u0000\u0000"+
		"\u01d0\u01d3\u0001\u0000\u0000\u0000\u01d1\u01cf\u0001\u0000\u0000\u0000"+
		"\u01d1\u01d2\u0001\u0000\u0000\u0000\u01d2\u01f2\u0001\u0000\u0000\u0000"+
		"\u01d3\u01d1\u0001\u0000\u0000\u0000\u01d4\u01d5\u0005\u0002\u0000\u0000"+
		"\u01d5\u01d6\u0003L&\u0000\u01d6\u01d7\u0005\u0003\u0000\u0000\u01d7\u01d8"+
		"\u0003T*\u0000\u01d8\u01db\u0003L&\u0000\u01d9\u01da\u0005\u00c5\u0000"+
		"\u0000\u01da\u01dc\u0003X,\u0000\u01db\u01d9\u0001\u0000\u0000\u0000\u01db"+
		"\u01dc\u0001\u0000\u0000\u0000\u01dc\u01e0\u0001\u0000\u0000\u0000\u01dd"+
		"\u01df\u0003\u001c\u000e\u0000\u01de\u01dd\u0001\u0000\u0000\u0000\u01df"+
		"\u01e2\u0001\u0000\u0000\u0000\u01e0\u01de\u0001\u0000\u0000\u0000\u01e0"+
		"\u01e1\u0001\u0000\u0000\u0000\u01e1\u01f2\u0001\u0000\u0000\u0000\u01e2"+
		"\u01e0\u0001\u0000\u0000\u0000\u01e3\u01e4\u0005O\u0000\u0000\u01e4\u01f2"+
		"\u0003L&\u0000\u01e5\u01e6\u0005R\u0000\u0000\u01e6\u01e7\u0005\u00ac"+
		"\u0000\u0000\u01e7\u01e8\u0003.\u0017\u0000\u01e8\u01e9\u0005\u00ad\u0000"+
		"\u0000\u01e9\u01f2\u0001\u0000\u0000\u0000\u01ea\u01eb\u0005T\u0000\u0000"+
		"\u01eb\u01ec\u0005\u00bf\u0000\u0000\u01ec\u01ed\u0005\u00ca\u0000\u0000"+
		"\u01ed\u01ee\u0005\u00ac\u0000\u0000\u01ee\u01ef\u0003\u0018\f\u0000\u01ef"+
		"\u01f0\u0005\u00ad\u0000\u0000\u01f0\u01f2\u0001\u0000\u0000\u0000\u01f1"+
		"\u01c5\u0001\u0000\u0000\u0000\u01f1\u01c7\u0001\u0000\u0000\u0000\u01f1"+
		"\u01d4\u0001\u0000\u0000\u0000\u01f1\u01e3\u0001\u0000\u0000\u0000\u01f1"+
		"\u01e5\u0001\u0000\u0000\u0000\u01f1\u01ea\u0001\u0000\u0000\u0000\u01f2"+
		"\u0017\u0001\u0000\u0000\u0000\u01f3\u01f8\u0003\u001a\r\u0000\u01f4\u01f5"+
		"\u0005\u00b3\u0000\u0000\u01f5\u01f7\u0003\u001a\r\u0000\u01f6\u01f4\u0001"+
		"\u0000\u0000\u0000\u01f7\u01fa\u0001\u0000\u0000\u0000\u01f8\u01f6\u0001"+
		"\u0000\u0000\u0000\u01f8\u01f9\u0001\u0000\u0000\u0000\u01f9\u0019\u0001"+
		"\u0000\u0000\u0000\u01fa\u01f8\u0001\u0000\u0000\u0000\u01fb\u01fc\u0003"+
		"V+\u0000\u01fc\u01fd\u0005=\u0000\u0000\u01fd\u01fe\u00032\u0019\u0000"+
		"\u01fe\u001b\u0001\u0000\u0000\u0000\u01ff\u0200\u0005Y\u0000\u0000\u0200"+
		"\u0204\u0003\u001e\u000f\u0000\u0201\u0202\u0005Z\u0000\u0000\u0202\u0204"+
		"\u0003\u001e\u000f\u0000\u0203\u01ff\u0001\u0000\u0000\u0000\u0203\u0201"+
		"\u0001\u0000\u0000\u0000\u0204\u001d\u0001\u0000\u0000\u0000\u0205\u0206"+
		"\u0007\u0004\u0000\u0000\u0206\u001f\u0001\u0000\u0000\u0000\u0207\u0208"+
		"\u0005\u00c4\u0000\u0000\u0208\u0209\u0005\u0015\u0000\u0000\u0209\u020a"+
		"\u0005\u00ca\u0000\u0000\u020a\u020b\u0003\"\u0011\u0000\u020b\u020c\u0003"+
		"$\u0012\u0000\u020c\u020d\u0005\u00b5\u0000\u0000\u020d\u020e\u0003T*"+
		"\u0000\u020e\u020f\u0005\\\u0000\u0000\u020f\u0210\u0005]\u0000\u0000"+
		"\u0210\u0211\u0005\u00ca\u0000\u0000\u0211\u021a\u0005\u00ac\u0000\u0000"+
		"\u0212\u0217\u0003l6\u0000\u0213\u0214\u0005\u00b3\u0000\u0000\u0214\u0216"+
		"\u0003l6\u0000\u0215\u0213\u0001\u0000\u0000\u0000\u0216\u0219\u0001\u0000"+
		"\u0000\u0000\u0217\u0215\u0001\u0000\u0000\u0000\u0217\u0218\u0001\u0000"+
		"\u0000\u0000\u0218\u021b\u0001\u0000\u0000\u0000\u0219\u0217\u0001\u0000"+
		"\u0000\u0000\u021a\u0212\u0001\u0000\u0000\u0000\u021a\u021b\u0001\u0000"+
		"\u0000\u0000\u021b\u021c\u0001\u0000\u0000\u0000\u021c\u021d\u0005\u00ad"+
		"\u0000\u0000\u021d\u021e\u0005\u00b0\u0000\u0000\u021e!\u0001\u0000\u0000"+
		"\u0000\u021f\u0220\u0007\u0005\u0000\u0000\u0220#\u0001\u0000\u0000\u0000"+
		"\u0221\u0226\u0007\u0006\u0000\u0000\u0222\u0223\u0005c\u0000\u0000\u0223"+
		"\u0225\u0007\u0006\u0000\u0000\u0224\u0222\u0001\u0000\u0000\u0000\u0225"+
		"\u0228\u0001\u0000\u0000\u0000\u0226\u0224\u0001\u0000\u0000\u0000\u0226"+
		"\u0227\u0001\u0000\u0000\u0000\u0227%\u0001\u0000\u0000\u0000\u0228\u0226"+
		"\u0001\u0000\u0000\u0000\u0229\u022d\u0003(\u0014\u0000\u022a\u022d\u0003"+
		"*\u0015\u0000\u022b\u022d\u0003,\u0016\u0000\u022c\u0229\u0001\u0000\u0000"+
		"\u0000\u022c\u022a\u0001\u0000\u0000\u0000\u022c\u022b\u0001\u0000\u0000"+
		"\u0000\u022d\'\u0001\u0000\u0000\u0000\u022e\u022f\u0005d\u0000\u0000"+
		"\u022f\u0230\u0003T*\u0000\u0230\u0231\u0005\u00ac\u0000\u0000\u0231\u0236"+
		"\u0003V+\u0000\u0232\u0233\u0005\u00b3\u0000\u0000\u0233\u0235\u0003V"+
		"+\u0000\u0234\u0232\u0001\u0000\u0000\u0000\u0235\u0238\u0001\u0000\u0000"+
		"\u0000\u0236\u0234\u0001\u0000\u0000\u0000\u0236\u0237\u0001\u0000\u0000"+
		"\u0000\u0237\u0239\u0001\u0000\u0000\u0000\u0238\u0236\u0001\u0000\u0000"+
		"\u0000\u0239\u023a\u0005\u00ad\u0000\u0000\u023a\u023b\u0005e\u0000\u0000"+
		"\u023b\u023c\u0005\u00ac\u0000\u0000\u023c\u0241\u0003l6\u0000\u023d\u023e"+
		"\u0005\u00b3\u0000\u0000\u023e\u0240\u0003l6\u0000\u023f\u023d\u0001\u0000"+
		"\u0000\u0000\u0240\u0243\u0001\u0000\u0000\u0000\u0241\u023f\u0001\u0000"+
		"\u0000\u0000\u0241\u0242\u0001\u0000\u0000\u0000\u0242\u0244\u0001\u0000"+
		"\u0000\u0000\u0243\u0241\u0001\u0000\u0000\u0000\u0244\u0245\u0005\u00ad"+
		"\u0000\u0000\u0245\u0246\u0005\u00b0\u0000\u0000\u0246)\u0001\u0000\u0000"+
		"\u0000\u0247\u0248\u0005b\u0000\u0000\u0248\u0249\u0003T*\u0000\u0249"+
		"\u024a\u0005\u0010\u0000\u0000\u024a\u024b\u0003V+\u0000\u024b\u024c\u0005"+
		"f\u0000\u0000\u024c\u0254\u0003l6\u0000\u024d\u024e\u0005\u00b3\u0000"+
		"\u0000\u024e\u024f\u0003V+\u0000\u024f\u0250\u0005f\u0000\u0000\u0250"+
		"\u0251\u0003l6\u0000\u0251\u0253\u0001\u0000\u0000\u0000\u0252\u024d\u0001"+
		"\u0000\u0000\u0000\u0253\u0256\u0001\u0000\u0000\u0000\u0254\u0252\u0001"+
		"\u0000\u0000\u0000\u0254\u0255\u0001\u0000\u0000\u0000\u0255\u0259\u0001"+
		"\u0000\u0000\u0000\u0256\u0254\u0001\u0000\u0000\u0000\u0257\u0258\u0005"+
		"U\u0000\u0000\u0258\u025a\u0003.\u0017\u0000\u0259\u0257\u0001\u0000\u0000"+
		"\u0000\u0259\u025a\u0001\u0000\u0000\u0000\u025a\u025b\u0001\u0000\u0000"+
		"\u0000\u025b\u025c\u0005\u00b0\u0000\u0000\u025c+\u0001\u0000\u0000\u0000"+
		"\u025d\u025e\u0005g\u0000\u0000\u025e\u0261\u0003T*\u0000\u025f\u0260"+
		"\u0005U\u0000\u0000\u0260\u0262\u0003.\u0017\u0000\u0261\u025f\u0001\u0000"+
		"\u0000\u0000\u0261\u0262\u0001\u0000\u0000\u0000\u0262\u0263\u0001\u0000"+
		"\u0000\u0000\u0263\u0264\u0005\u00b0\u0000\u0000\u0264-\u0001\u0000\u0000"+
		"\u0000\u0265\u0266\u0006\u0017\uffff\uffff\u0000\u0266\u0267\u0003V+\u0000"+
		"\u0267\u0268\u00032\u0019\u0000\u0268\u0269\u0003l6\u0000\u0269\u0297"+
		"\u0001\u0000\u0000\u0000\u026a\u026b\u0003V+\u0000\u026b\u026c\u0005h"+
		"\u0000\u0000\u026c\u026f\u0005\u00ac\u0000\u0000\u026d\u0270\u0003l6\u0000"+
		"\u026e\u0270\u00030\u0018\u0000\u026f\u026d\u0001\u0000\u0000\u0000\u026f"+
		"\u026e\u0001\u0000\u0000\u0000\u0270\u0278\u0001\u0000\u0000\u0000\u0271"+
		"\u0274\u0005\u00b3\u0000\u0000\u0272\u0275\u0003l6\u0000\u0273\u0275\u0003"+
		"0\u0018\u0000\u0274\u0272\u0001\u0000\u0000\u0000\u0274\u0273\u0001\u0000"+
		"\u0000\u0000\u0275\u0277\u0001\u0000\u0000\u0000\u0276\u0271\u0001\u0000"+
		"\u0000\u0000\u0277\u027a\u0001\u0000\u0000\u0000\u0278\u0276\u0001\u0000"+
		"\u0000\u0000\u0278\u0279\u0001\u0000\u0000\u0000\u0279\u027b\u0001\u0000"+
		"\u0000\u0000\u027a\u0278\u0001\u0000\u0000\u0000\u027b\u027c\u0005\u00ad"+
		"\u0000\u0000\u027c\u0297\u0001\u0000\u0000\u0000\u027d\u027e\u0003V+\u0000"+
		"\u027e\u027f\u0005i\u0000\u0000\u027f\u0280\u0003l6\u0000\u0280\u0281"+
		"\u0005j\u0000\u0000\u0281\u0282\u0003l6\u0000\u0282\u0297\u0001\u0000"+
		"\u0000\u0000\u0283\u0284\u0003V+\u0000\u0284\u0285\u0005k\u0000\u0000"+
		"\u0285\u0286\u0005N\u0000\u0000\u0286\u0297\u0001\u0000\u0000\u0000\u0287"+
		"\u0288\u0003V+\u0000\u0288\u0289\u0005l\u0000\u0000\u0289\u0297\u0001"+
		"\u0000\u0000\u0000\u028a\u028b\u0003V+\u0000\u028b\u028c\u0005m\u0000"+
		"\u0000\u028c\u028d\u0005\u00a8\u0000\u0000\u028d\u0297\u0001\u0000\u0000"+
		"\u0000\u028e\u028f\u0003V+\u0000\u028f\u0290\u0005n\u0000\u0000\u0290"+
		"\u0291\u0005\u00a8\u0000\u0000\u0291\u0297\u0001\u0000\u0000\u0000\u0292"+
		"\u0293\u0005o\u0000\u0000\u0293\u0297\u0003.\u0017\u0005\u0294\u0297\u0005"+
		"p\u0000\u0000\u0295\u0297\u0005q\u0000\u0000\u0296\u0265\u0001\u0000\u0000"+
		"\u0000\u0296\u026a\u0001\u0000\u0000\u0000\u0296\u027d\u0001\u0000\u0000"+
		"\u0000\u0296\u0283\u0001\u0000\u0000\u0000\u0296\u0287\u0001\u0000\u0000"+
		"\u0000\u0296\u028a\u0001\u0000\u0000\u0000\u0296\u028e\u0001\u0000\u0000"+
		"\u0000\u0296\u0292\u0001\u0000\u0000\u0000\u0296\u0294\u0001\u0000\u0000"+
		"\u0000\u0296\u0295\u0001\u0000\u0000\u0000\u0297\u02a0\u0001\u0000\u0000"+
		"\u0000\u0298\u0299\n\u0004\u0000\u0000\u0299\u029a\u0005j\u0000\u0000"+
		"\u029a\u029f\u0003.\u0017\u0005\u029b\u029c\n\u0003\u0000\u0000\u029c"+
		"\u029d\u0005c\u0000\u0000\u029d\u029f\u0003.\u0017\u0004\u029e\u0298\u0001"+
		"\u0000\u0000\u0000\u029e\u029b\u0001\u0000\u0000\u0000\u029f\u02a2\u0001"+
		"\u0000\u0000\u0000\u02a0\u029e\u0001\u0000\u0000\u0000\u02a0\u02a1\u0001"+
		"\u0000\u0000\u0000\u02a1/\u0001\u0000\u0000\u0000\u02a2\u02a0\u0001\u0000"+
		"\u0000\u0000\u02a3\u02a4\u0005\u00ac\u0000\u0000\u02a4\u02a5\u00036\u001b"+
		"\u0000\u02a5\u02a6\u0005\u00ad\u0000\u0000\u02a61\u0001\u0000\u0000\u0000"+
		"\u02a7\u02a8\u0007\u0007\u0000\u0000\u02a83\u0001\u0000\u0000\u0000\u02a9"+
		"\u02aa\u0007\b\u0000\u0000\u02aa5\u0001\u0000\u0000\u0000\u02ab\u02b6"+
		"\u0003\u0000\u0000\u0000\u02ac\u02b6\u00038\u001c\u0000\u02ad\u02b6\u0003"+
		":\u001d\u0000\u02ae\u02b6\u0003<\u001e\u0000\u02af\u02b6\u0003f3\u0000"+
		"\u02b0\u02b6\u0003t:\u0000\u02b1\u02b6\u0003 \u0010\u0000\u02b2\u02b6"+
		"\u0003\b\u0004\u0000\u02b3\u02b6\u0003&\u0013\u0000\u02b4\u02b6\u0003"+
		"n7\u0000\u02b5\u02ab\u0001\u0000\u0000\u0000\u02b5\u02ac\u0001\u0000\u0000"+
		"\u0000\u02b5\u02ad\u0001\u0000\u0000\u0000\u02b5\u02ae\u0001\u0000\u0000"+
		"\u0000\u02b5\u02af\u0001\u0000\u0000\u0000\u02b5\u02b0\u0001\u0000\u0000"+
		"\u0000\u02b5\u02b1\u0001\u0000\u0000\u0000\u02b5\u02b2\u0001\u0000\u0000"+
		"\u0000\u02b5\u02b3\u0001\u0000\u0000\u0000\u02b5\u02b4\u0001\u0000\u0000"+
		"\u0000\u02b6\u02b9\u0001\u0000\u0000\u0000\u02b7\u02b5\u0001\u0000\u0000"+
		"\u0000\u02b7\u02b8\u0001\u0000\u0000\u0000\u02b8\u02ba\u0001\u0000\u0000"+
		"\u0000\u02b9\u02b7\u0001\u0000\u0000\u0000\u02ba\u02bb\u0005\u0000\u0000"+
		"\u0001\u02bb7\u0001\u0000\u0000\u0000\u02bc\u02bd\u0005\u0084\u0000\u0000"+
		"\u02bd\u02be\u0005\u00ca\u0000\u0000\u02be\u02bf\u0005\u00b0\u0000\u0000"+
		"\u02bf9\u0001\u0000\u0000\u0000\u02c0\u02c1\u0005\u0085\u0000\u0000\u02c1"+
		"\u02c2\u0005\u00ca\u0000\u0000\u02c2\u02c3\u0005\u00b9\u0000\u0000\u02c3"+
		"\u02c4\u0003@ \u0000\u02c4\u02c5\u0005\u00b0\u0000\u0000\u02c5;\u0001"+
		"\u0000\u0000\u0000\u02c6\u02c7\u0005\u0086\u0000\u0000\u02c7\u02c8\u0005"+
		"\u00ca\u0000\u0000\u02c8\u02d1\u0005\u00ac\u0000\u0000\u02c9\u02ce\u0003"+
		">\u001f\u0000\u02ca\u02cb\u0005\u00b3\u0000\u0000\u02cb\u02cd\u0003>\u001f"+
		"\u0000\u02cc\u02ca\u0001\u0000\u0000\u0000\u02cd\u02d0\u0001\u0000\u0000"+
		"\u0000\u02ce\u02cc\u0001\u0000\u0000\u0000\u02ce\u02cf\u0001\u0000\u0000"+
		"\u0000\u02cf\u02d2\u0001\u0000\u0000\u0000\u02d0\u02ce\u0001\u0000\u0000"+
		"\u0000\u02d1\u02c9\u0001\u0000\u0000\u0000\u02d1\u02d2\u0001\u0000\u0000"+
		"\u0000\u02d2\u02d3\u0001\u0000\u0000\u0000\u02d3\u02d4\u0005\u00ad\u0000"+
		"\u0000\u02d4\u02d5\u0005\u0087\u0000\u0000\u02d5\u02d6\u0003\u000e\u0007"+
		"\u0000\u02d6\u02d7\u0005\u00b9\u0000\u0000\u02d7\u02d8\u0005\u00c0\u0000"+
		"\u0000\u02d8\u02d9\u0003p8\u0000\u02d9\u02da\u0005\u00c0\u0000\u0000\u02da"+
		"\u02db\u0005\u0088\u0000\u0000\u02db\u02dc\u0005\u00ca\u0000\u0000\u02dc"+
		"\u02dd\u0005\u00b0\u0000\u0000\u02dd=\u0001\u0000\u0000\u0000\u02de\u02df"+
		"\u0005\u00ca\u0000\u0000\u02df\u02e0\u0003\u000e\u0007\u0000\u02e0?\u0001"+
		"\u0000\u0000\u0000\u02e1\u02e2\u0005\u00be\u0000\u0000\u02e2\u02ec\u0003"+
		"B!\u0000\u02e3\u02e4\u0005\u0089\u0000\u0000\u02e4\u02e9\u0003D\"\u0000"+
		"\u02e5\u02e6\u0005\u00b3\u0000\u0000\u02e6\u02e8\u0003D\"\u0000\u02e7"+
		"\u02e5\u0001\u0000\u0000\u0000\u02e8\u02eb\u0001\u0000\u0000\u0000\u02e9"+
		"\u02e7\u0001\u0000\u0000\u0000\u02e9\u02ea\u0001\u0000\u0000\u0000\u02ea"+
		"\u02ed\u0001\u0000\u0000\u0000\u02eb\u02e9\u0001\u0000\u0000\u0000\u02ec"+
		"\u02e3\u0001\u0000\u0000\u0000\u02ec\u02ed\u0001\u0000\u0000\u0000\u02ed"+
		"\u02ef\u0001\u0000\u0000\u0000\u02ee\u02f0\u0003F#\u0000\u02ef\u02ee\u0001"+
		"\u0000\u0000\u0000\u02ef\u02f0\u0001\u0000\u0000\u0000\u02f0\u02f2\u0001"+
		"\u0000\u0000\u0000\u02f1\u02f3\u0003H$\u0000\u02f2\u02f1\u0001\u0000\u0000"+
		"\u0000\u02f2\u02f3\u0001\u0000\u0000\u0000\u02f3\u02f5\u0001\u0000\u0000"+
		"\u0000\u02f4\u02f6\u0003J%\u0000\u02f5\u02f4\u0001\u0000\u0000\u0000\u02f5"+
		"\u02f6\u0001\u0000\u0000\u0000\u02f6A\u0001\u0000\u0000\u0000\u02f7\u0301"+
		"\u0005\u0080\u0000\u0000\u02f8\u02fd\u0003V+\u0000\u02f9\u02fa\u0005\u00b3"+
		"\u0000\u0000\u02fa\u02fc\u0003V+\u0000\u02fb\u02f9\u0001\u0000\u0000\u0000"+
		"\u02fc\u02ff\u0001\u0000\u0000\u0000\u02fd\u02fb\u0001\u0000\u0000\u0000"+
		"\u02fd\u02fe\u0001\u0000\u0000\u0000\u02fe\u0301\u0001\u0000\u0000\u0000"+
		"\u02ff\u02fd\u0001\u0000\u0000\u0000\u0300\u02f7\u0001\u0000\u0000\u0000"+
		"\u0300\u02f8\u0001\u0000\u0000\u0000\u0301C\u0001\u0000\u0000\u0000\u0302"+
		"\u0305\u0003T*\u0000\u0303\u0304\u0005\u00b9\u0000\u0000\u0304\u0306\u0005"+
		"\u00ca\u0000\u0000\u0305\u0303\u0001\u0000\u0000\u0000\u0305\u0306\u0001"+
		"\u0000\u0000\u0000\u0306E\u0001\u0000\u0000\u0000\u0307\u0308\u0005U\u0000"+
		"\u0000\u0308\u0309\u0003.\u0017\u0000\u0309G\u0001\u0000\u0000\u0000\u030a"+
		"\u030b\u0005\u008a\u0000\u0000\u030b\u030d\u0003V+\u0000\u030c\u030e\u0007"+
		"\t\u0000\u0000\u030d\u030c\u0001\u0000\u0000\u0000\u030d\u030e\u0001\u0000"+
		"\u0000\u0000\u030eI\u0001\u0000\u0000\u0000\u030f\u0310\u0005\u008d\u0000"+
		"\u0000\u0310\u0311\u0005\u00a9\u0000\u0000\u0311K\u0001\u0000\u0000\u0000"+
		"\u0312\u0313\u0005\u00ac\u0000\u0000\u0313\u0318\u0003V+\u0000\u0314\u0315"+
		"\u0005\u00b3\u0000\u0000\u0315\u0317\u0003V+\u0000\u0316\u0314\u0001\u0000"+
		"\u0000\u0000\u0317\u031a\u0001\u0000\u0000\u0000\u0318\u0316\u0001\u0000"+
		"\u0000\u0000\u0318\u0319\u0001\u0000\u0000\u0000\u0319\u031c\u0001\u0000"+
		"\u0000\u0000\u031a\u0318\u0001\u0000\u0000\u0000\u031b\u031d\u0005\u00ad"+
		"\u0000\u0000\u031c\u031b\u0001\u0000\u0000\u0000\u031c\u031d\u0001\u0000"+
		"\u0000\u0000\u031dM\u0001\u0000\u0000\u0000\u031e\u0320\u0003R)\u0000"+
		"\u031f\u031e\u0001\u0000\u0000\u0000\u031f\u0320\u0001\u0000\u0000\u0000"+
		"\u0320\u0321\u0001\u0000\u0000\u0000\u0321\u0322\u0005\u008e\u0000\u0000"+
		"\u0322\u0325\u0003T*\u0000\u0323\u0325\u0003T*\u0000\u0324\u031f\u0001"+
		"\u0000\u0000\u0000\u0324\u0323\u0001\u0000\u0000\u0000\u0325\u0326\u0001"+
		"\u0000\u0000\u0000\u0326\u0327\u0005\u00ac\u0000\u0000\u0327\u032c\u0003"+
		"V+\u0000\u0328\u0329\u0005\u00b3\u0000\u0000\u0329\u032b\u0003V+\u0000"+
		"\u032a\u0328\u0001\u0000\u0000\u0000\u032b\u032e\u0001\u0000\u0000\u0000"+
		"\u032c\u032a\u0001\u0000\u0000\u0000\u032c\u032d\u0001\u0000\u0000\u0000"+
		"\u032d\u032f\u0001\u0000\u0000\u0000\u032e\u032c\u0001\u0000\u0000\u0000"+
		"\u032f\u0330\u0005\u00ad\u0000\u0000\u0330O\u0001\u0000\u0000\u0000\u0331"+
		"\u033c\u0005\u008f\u0000\u0000\u0332\u0333\u0005\u0090\u0000\u0000\u0333"+
		"\u0334\u0005\u0010\u0000\u0000\u0334\u0335\u0003V+\u0000\u0335\u0336\u0005"+
		"f\u0000\u0000\u0336\u0339\u0003l6\u0000\u0337\u0338\u0005U\u0000\u0000"+
		"\u0338\u033a\u0003.\u0017\u0000\u0339\u0337\u0001\u0000\u0000\u0000\u0339"+
		"\u033a\u0001\u0000\u0000\u0000\u033a\u033c\u0001\u0000\u0000\u0000\u033b"+
		"\u0331\u0001\u0000\u0000\u0000\u033b\u0332\u0001\u0000\u0000\u0000\u033c"+
		"Q\u0001\u0000\u0000\u0000\u033d\u033e\u0005\u00ca\u0000\u0000\u033eS\u0001"+
		"\u0000\u0000\u0000\u033f\u0340\u0005\u00ca\u0000\u0000\u0340U\u0001\u0000"+
		"\u0000\u0000\u0341\u0342\u0005\u00ca\u0000\u0000\u0342W\u0001\u0000\u0000"+
		"\u0000\u0343\u0344\u0007\n\u0000\u0000\u0344Y\u0001\u0000\u0000\u0000"+
		"\u0345\u034d\u0005\u00c6\u0000\u0000\u0346\u0347\u0005\u00ac\u0000\u0000"+
		"\u0347\u034a\u0005\u00a9\u0000\u0000\u0348\u0349\u0005\u00b3\u0000\u0000"+
		"\u0349\u034b\u0005\u00a9\u0000\u0000\u034a\u0348\u0001\u0000\u0000\u0000"+
		"\u034a\u034b\u0001\u0000\u0000\u0000\u034b\u034c\u0001\u0000\u0000\u0000"+
		"\u034c\u034e\u0005\u00ad\u0000\u0000\u034d\u0346\u0001\u0000\u0000\u0000"+
		"\u034d\u034e\u0001\u0000\u0000\u0000\u034e[\u0001\u0000\u0000\u0000\u034f"+
		"\u0350\u0005\u0095\u0000\u0000\u0350\u0351\u0003^/\u0000\u0351\u0352\u0005"+
		"\u00ac\u0000\u0000\u0352\u0353\u0003V+\u0000\u0353\u0354\u0005\u00ad\u0000"+
		"\u0000\u0354]\u0001\u0000\u0000\u0000\u0355\u0356\u0007\u000b\u0000\u0000"+
		"\u0356_\u0001\u0000\u0000\u0000\u0357\u0358\u0005\u00c4\u0000\u0000\u0358"+
		"\u0359\u0005\u00c3\u0000\u0000\u0359\u035a\u0003T*\u0000\u035a\u035b\u0005"+
		"\u0001\u0000\u0000\u035b\u035c\u0003T*\u0000\u035c\u035d\u0003b1\u0000"+
		"\u035d\u035e\u0005\u00b0\u0000\u0000\u035ea\u0001\u0000\u0000\u0000\u035f"+
		"\u0379\u0005\u0099\u0000\u0000\u0360\u0361\u0005\u0089\u0000\u0000\u0361"+
		"\u0362\u0005\u00ac\u0000\u0000\u0362\u0363\u0003l6\u0000\u0363\u0364\u0005"+
		"\u00ad\u0000\u0000\u0364\u0365\u0005\u00ba\u0000\u0000\u0365\u0366\u0005"+
		"\u00ac\u0000\u0000\u0366\u0367\u0003l6\u0000\u0367\u0368\u0005\u00ad\u0000"+
		"\u0000\u0368\u037a\u0001\u0000\u0000\u0000\u0369\u036a\u0005h\u0000\u0000"+
		"\u036a\u036b\u0005\u00ac\u0000\u0000\u036b\u0370\u0003l6\u0000\u036c\u036d"+
		"\u0005\u00b3\u0000\u0000\u036d\u036f\u0003l6\u0000\u036e\u036c\u0001\u0000"+
		"\u0000\u0000\u036f\u0372\u0001\u0000\u0000\u0000\u0370\u036e\u0001\u0000"+
		"\u0000\u0000\u0370\u0371\u0001\u0000\u0000\u0000\u0371\u0373\u0001\u0000"+
		"\u0000\u0000\u0372\u0370\u0001\u0000\u0000\u0000\u0373\u0374\u0005\u00ad"+
		"\u0000\u0000\u0374\u037a\u0001\u0000\u0000\u0000\u0375\u0376\u0005\u009a"+
		"\u0000\u0000\u0376\u0377\u0005\u00a9\u0000\u0000\u0377\u0378\u0005\u009b"+
		"\u0000\u0000\u0378\u037a\u0005\u00a9\u0000\u0000\u0379\u0360\u0001\u0000"+
		"\u0000\u0000\u0379\u0369\u0001\u0000\u0000\u0000\u0379\u0375\u0001\u0000"+
		"\u0000\u0000\u037ac\u0001\u0000\u0000\u0000\u037b\u037c\u0005\u0001\u0000"+
		"\u0000\u037c\u037d\u0003T*\u0000\u037d\u037e\u0003b1\u0000\u037ee\u0001"+
		"\u0000\u0000\u0000\u037f\u0380\u0005\u00c4\u0000\u0000\u0380\u0381\u0005"+
		"\u009c\u0000\u0000\u0381\u0382\u0005\u00ca\u0000\u0000\u0382\u0383\u0005"+
		"\u00b5\u0000\u0000\u0383\u0384\u0003T*\u0000\u0384\u0385\u0005\u00ac\u0000"+
		"\u0000\u0385\u038a\u0003V+\u0000\u0386\u0387\u0005\u00b3\u0000\u0000\u0387"+
		"\u0389\u0003V+\u0000\u0388\u0386\u0001\u0000\u0000\u0000\u0389\u038c\u0001"+
		"\u0000\u0000\u0000\u038a\u0388\u0001\u0000\u0000\u0000\u038a\u038b\u0001"+
		"\u0000\u0000\u0000\u038b\u038d\u0001\u0000\u0000\u0000\u038c\u038a\u0001"+
		"\u0000\u0000\u0000\u038d\u038f\u0005\u00ad\u0000\u0000\u038e\u0390\u0003"+
		"h4\u0000\u038f\u038e\u0001\u0000\u0000\u0000\u038f\u0390\u0001\u0000\u0000"+
		"\u0000\u0390\u0391\u0001\u0000\u0000\u0000\u0391\u0392\u0005\u00b0\u0000"+
		"\u0000\u0392g\u0001\u0000\u0000\u0000\u0393\u0394\u0005\u0012\u0000\u0000"+
		"\u0394\u0395\u0005\u00ca\u0000\u0000\u0395i\u0001\u0000\u0000\u0000\u0396"+
		"\u0397\u00065\uffff\uffff\u0000\u0397\u039e\u0003l6\u0000\u0398\u039e"+
		"\u0003V+\u0000\u0399\u039a\u0005\u00ac\u0000\u0000\u039a\u039b\u0003j"+
		"5\u0000\u039b\u039c\u0005\u00ad\u0000\u0000\u039c\u039e\u0001\u0000\u0000"+
		"\u0000\u039d\u0396\u0001\u0000\u0000\u0000\u039d\u0398\u0001\u0000\u0000"+
		"\u0000\u039d\u0399\u0001\u0000\u0000\u0000\u039e\u03a4\u0001\u0000\u0000"+
		"\u0000\u039f\u03a0\n\u0002\u0000\u0000\u03a0\u03a1\u0007\f\u0000\u0000"+
		"\u03a1\u03a3\u0003j5\u0003\u03a2\u039f\u0001\u0000\u0000\u0000\u03a3\u03a6"+
		"\u0001\u0000\u0000\u0000\u03a4\u03a2\u0001\u0000\u0000\u0000\u03a4\u03a5"+
		"\u0001\u0000\u0000\u0000\u03a5k\u0001\u0000\u0000\u0000\u03a6\u03a4\u0001"+
		"\u0000\u0000\u0000\u03a7\u03a8\u0007\r\u0000\u0000\u03a8m\u0001\u0000"+
		"\u0000\u0000\u03a9\u03aa\u0005\u00c4\u0000\u0000\u03aa\u03ab\u0005\u00a1"+
		"\u0000\u0000\u03ab\u03ac\u0005\u00ca\u0000\u0000\u03ac\u03ad\u0005\u00b5"+
		"\u0000\u0000\u03ad\u03b0\u0003T*\u0000\u03ae\u03af\u0005\u00bd\u0000\u0000"+
		"\u03af\u03b1\u0007\u000e\u0000\u0000\u03b0\u03ae\u0001\u0000\u0000\u0000"+
		"\u03b0\u03b1\u0001\u0000\u0000\u0000\u03b1\u03b7\u0001\u0000\u0000\u0000"+
		"\u03b2\u03b3\u0005\u00bf\u0000\u0000\u03b3\u03b4\u0005\u00ac\u0000\u0000"+
		"\u03b4\u03b5\u0003.\u0017\u0000\u03b5\u03b6\u0005\u00ad\u0000\u0000\u03b6"+
		"\u03b8\u0001\u0000\u0000\u0000\u03b7\u03b2\u0001\u0000\u0000\u0000\u03b7"+
		"\u03b8\u0001\u0000\u0000\u0000\u03b8\u03be\u0001\u0000\u0000\u0000\u03b9"+
		"\u03ba\u0005\u00a3\u0000\u0000\u03ba\u03bb\u0005\u00ac\u0000\u0000\u03bb"+
		"\u03bc\u0003.\u0017\u0000\u03bc\u03bd\u0005\u00ad\u0000\u0000\u03bd\u03bf"+
		"\u0001\u0000\u0000\u0000\u03be\u03b9\u0001\u0000\u0000\u0000\u03be\u03bf"+
		"\u0001\u0000\u0000\u0000\u03bf\u03c0\u0001\u0000\u0000\u0000\u03c0\u03c1"+
		"\u0005\u00b0\u0000\u0000\u03c1o\u0001\u0000\u0000\u0000\u03c2\u03c6\u0005"+
		"\u00a4\u0000\u0000\u03c3\u03c5\u0003r9\u0000\u03c4\u03c3\u0001\u0000\u0000"+
		"\u0000\u03c5\u03c8\u0001\u0000\u0000\u0000\u03c6\u03c4\u0001\u0000\u0000"+
		"\u0000\u03c6\u03c7\u0001\u0000\u0000\u0000\u03c7\u03c9\u0001\u0000\u0000"+
		"\u0000\u03c8\u03c6\u0001\u0000\u0000\u0000\u03c9\u03ca\u0005\u00a5\u0000"+
		"\u0000\u03ca\u03cb\u0005\u00b0\u0000\u0000\u03cbq\u0001\u0000\u0000\u0000"+
		"\u03cc\u03cd\u0003V+\u0000\u03cd\u03ce\u0005\u008e\u0000\u0000\u03ce\u03cf"+
		"\u0003V+\u0000\u03cf\u03d0\u0005f\u0000\u0000\u03d0\u03d1\u0003l6\u0000"+
		"\u03d1\u03d2\u0005\u00b0\u0000\u0000\u03d2\u03de\u0001\u0000\u0000\u0000"+
		"\u03d3\u03d4\u0005\u00a6\u0000\u0000\u03d4\u03d5\u0005\u00c1\u0000\u0000"+
		"\u03d5\u03de\u0005\u00b0\u0000\u0000\u03d6\u03d7\u0005\u00c1\u0000\u0000"+
		"\u03d7\u03d8\u0005\u008e\u0000\u0000\u03d8\u03d9\u0003V+\u0000\u03d9\u03da"+
		"\u0005f\u0000\u0000\u03da\u03db\u0003l6\u0000\u03db\u03dc\u0005\u00b0"+
		"\u0000\u0000\u03dc\u03de\u0001\u0000\u0000\u0000\u03dd\u03cc\u0001\u0000"+
		"\u0000\u0000\u03dd\u03d3\u0001\u0000\u0000\u0000\u03dd\u03d6\u0001\u0000"+
		"\u0000\u0000\u03des\u0001\u0000\u0000\u0000\u03df\u03e0\u0005\u00c4\u0000"+
		"\u0000\u03e0\u03e1\u0005\u00b7\u0000\u0000\u03e1\u03e2\u0005\u00ca\u0000"+
		"\u0000\u03e2\u03e3\u0005\u00b9\u0000\u0000\u03e3\u03e4\u0005\u00b5\u0000"+
		"\u0000\u03e4\u03e5\u0003v;\u0000\u03e5\u03e6\u0005\u00ba\u0000\u0000\u03e6"+
		"\u03e7\u0003T*\u0000\u03e7\u03e8\u0005\u00b8\u0000\u0000\u03e8\u03e9\u0003"+
		"x<\u0000\u03e9\u03ea\u0005\u00b0\u0000\u0000\u03eau\u0001\u0000\u0000"+
		"\u0000\u03eb\u03ec\u0007\u000f\u0000\u0000\u03ecw\u0001\u0000\u0000\u0000"+
		"\u03ed\u03f1\u0005\u00a7\u0000\u0000\u03ee\u03ef\u0005\u00bb\u0000\u0000"+
		"\u03ef\u03f1\u0003z=\u0000\u03f0\u03ed\u0001\u0000\u0000\u0000\u03f0\u03ee"+
		"\u0001\u0000\u0000\u0000\u03f1y\u0001\u0000\u0000\u0000\u03f2\u03f3\u0005"+
		"d\u0000\u0000\u03f3\u03f4\u0003T*\u0000\u03f4\u03f5\u0005\u00ac\u0000"+
		"\u0000\u03f5\u03fa\u0003V+\u0000\u03f6\u03f7\u0005\u00b3\u0000\u0000\u03f7"+
		"\u03f9\u0003V+\u0000\u03f8\u03f6\u0001\u0000\u0000\u0000\u03f9\u03fc\u0001"+
		"\u0000\u0000\u0000\u03fa\u03f8\u0001\u0000\u0000\u0000\u03fa\u03fb\u0001"+
		"\u0000\u0000\u0000\u03fb\u03fd\u0001\u0000\u0000\u0000\u03fc\u03fa\u0001"+
		"\u0000\u0000\u0000\u03fd\u03fe\u0005\u00ad\u0000\u0000\u03fe\u03ff\u0005"+
		"e\u0000\u0000\u03ff\u0400\u0005\u00ac\u0000\u0000\u0400\u0405\u0003l6"+
		"\u0000\u0401\u0402\u0005\u00b3\u0000\u0000\u0402\u0404\u0003l6\u0000\u0403"+
		"\u0401\u0001\u0000\u0000\u0000\u0404\u0407\u0001\u0000\u0000\u0000\u0405"+
		"\u0403\u0001\u0000\u0000\u0000\u0405\u0406\u0001\u0000\u0000\u0000\u0406"+
		"\u0408\u0001\u0000\u0000\u0000\u0407\u0405\u0001\u0000\u0000\u0000\u0408"+
		"\u0409\u0005\u00ad\u0000\u0000\u0409\u0414\u0001\u0000\u0000\u0000\u040a"+
		"\u040b\u0005b\u0000\u0000\u040b\u040c\u0003T*\u0000\u040c\u040d\u0005"+
		"\u0010\u0000\u0000\u040d\u040e\u0003V+\u0000\u040e\u040f\u0005f\u0000"+
		"\u0000\u040f\u0410\u0003l6\u0000\u0410\u0414\u0001\u0000\u0000\u0000\u0411"+
		"\u0412\u0005g\u0000\u0000\u0412\u0414\u0003T*\u0000\u0413\u03f2\u0001"+
		"\u0000\u0000\u0000\u0413\u040a\u0001\u0000\u0000\u0000\u0413\u0411\u0001"+
		"\u0000\u0000\u0000\u0414{\u0001\u0000\u0000\u0000\u0415\u041a\u0005Q\u0000"+
		"\u0000\u0416\u041b\u0001\u0000\u0000\u0000\u0417\u041b\u0003~?\u0000\u0418"+
		"\u041b\u0003\u0080@\u0000\u0419\u041b\u0005\u009f\u0000\u0000\u041a\u0416"+
		"\u0001\u0000\u0000\u0000\u041a\u0417\u0001\u0000\u0000\u0000\u041a\u0418"+
		"\u0001\u0000\u0000\u0000\u041a\u0419\u0001\u0000\u0000\u0000\u041b}\u0001"+
		"\u0000\u0000\u0000\u041c\u041e\u0005\u007f\u0000\u0000\u041d\u041c\u0001"+
		"\u0000\u0000\u0000\u041d\u041e\u0001\u0000\u0000\u0000\u041e\u0420\u0001"+
		"\u0000\u0000\u0000\u041f\u0421\u0005\u00aa\u0000\u0000\u0420\u041f\u0001"+
		"\u0000\u0000\u0000\u0421\u0422\u0001\u0000\u0000\u0000\u0422\u0420\u0001"+
		"\u0000\u0000\u0000\u0422\u0423\u0001\u0000\u0000\u0000\u0423\u042a\u0001"+
		"\u0000\u0000\u0000\u0424\u0426\u0005\u008e\u0000\u0000\u0425\u0427\u0005"+
		"\u00aa\u0000\u0000\u0426\u0425\u0001\u0000\u0000\u0000\u0427\u0428\u0001"+
		"\u0000\u0000\u0000\u0428\u0426\u0001\u0000\u0000\u0000\u0428\u0429\u0001"+
		"\u0000\u0000\u0000\u0429\u042b\u0001\u0000\u0000\u0000\u042a\u0424\u0001"+
		"\u0000\u0000\u0000\u042a\u042b\u0001\u0000\u0000\u0000\u042b\u007f\u0001"+
		"\u0000\u0000\u0000\u042c\u042d\u0007\u0010\u0000\u0000\u042d\u0081\u0001"+
		"\u0000\u0000\u0000`\u0088\u008d\u0091\u0096\u00a2\u00a9\u00ae\u00bb\u00c0"+
		"\u00c3\u00d8\u00dc\u00f4\u0108\u0118\u0129\u012b\u013b\u013e\u0145\u0148"+
		"\u015f\u0168\u0175\u0184\u018a\u018e\u0195\u01a4\u01aa\u01b3\u01bd\u01bf"+
		"\u01d1\u01db\u01e0\u01f1\u01f8\u0203\u0217\u021a\u0226\u022c\u0236\u0241"+
		"\u0254\u0259\u0261\u026f\u0274\u0278\u0296\u029e\u02a0\u02b5\u02b7\u02ce"+
		"\u02d1\u02e9\u02ec\u02ef\u02f2\u02f5\u02fd\u0300\u0305\u030d\u0318\u031c"+
		"\u031f\u0324\u032c\u0339\u033b\u034a\u034d\u0370\u0379\u038a\u038f\u039d"+
		"\u03a4\u03b0\u03b7\u03be\u03c6\u03dd\u03f0\u03fa\u0405\u0413\u041a\u041d"+
		"\u0422\u0428\u042a";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}