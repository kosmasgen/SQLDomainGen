// Generated from com/sqldomaingen/parser/PostgreSQL.g4 by ANTLR 4.13.1
package com.sqldomaingen.parser;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link PostgreSQLParser}.
 */
public interface PostgreSQLListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link PostgreSQLParser#createTableStatement}.
	 * @param ctx the parse tree
	 */
	void enterCreateTableStatement(PostgreSQLParser.CreateTableStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link PostgreSQLParser#createTableStatement}.
	 * @param ctx the parse tree
	 */
	void exitCreateTableStatement(PostgreSQLParser.CreateTableStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link PostgreSQLParser#columnDef}.
	 * @param ctx the parse tree
	 */
	void enterColumnDef(PostgreSQLParser.ColumnDefContext ctx);
	/**
	 * Exit a parse tree produced by {@link PostgreSQLParser#columnDef}.
	 * @param ctx the parse tree
	 */
	void exitColumnDef(PostgreSQLParser.ColumnDefContext ctx);
	/**
	 * Enter a parse tree produced by {@link PostgreSQLParser#generatedColumn}.
	 * @param ctx the parse tree
	 */
	void enterGeneratedColumn(PostgreSQLParser.GeneratedColumnContext ctx);
	/**
	 * Exit a parse tree produced by {@link PostgreSQLParser#generatedColumn}.
	 * @param ctx the parse tree
	 */
	void exitGeneratedColumn(PostgreSQLParser.GeneratedColumnContext ctx);
	/**
	 * Enter a parse tree produced by {@link PostgreSQLParser#collateClause}.
	 * @param ctx the parse tree
	 */
	void enterCollateClause(PostgreSQLParser.CollateClauseContext ctx);
	/**
	 * Exit a parse tree produced by {@link PostgreSQLParser#collateClause}.
	 * @param ctx the parse tree
	 */
	void exitCollateClause(PostgreSQLParser.CollateClauseContext ctx);
	/**
	 * Enter a parse tree produced by {@link PostgreSQLParser#alterTableStatement}.
	 * @param ctx the parse tree
	 */
	void enterAlterTableStatement(PostgreSQLParser.AlterTableStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link PostgreSQLParser#alterTableStatement}.
	 * @param ctx the parse tree
	 */
	void exitAlterTableStatement(PostgreSQLParser.AlterTableStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link PostgreSQLParser#alterAction}.
	 * @param ctx the parse tree
	 */
	void enterAlterAction(PostgreSQLParser.AlterActionContext ctx);
	/**
	 * Exit a parse tree produced by {@link PostgreSQLParser#alterAction}.
	 * @param ctx the parse tree
	 */
	void exitAlterAction(PostgreSQLParser.AlterActionContext ctx);
	/**
	 * Enter a parse tree produced by {@link PostgreSQLParser#alterColumnAction}.
	 * @param ctx the parse tree
	 */
	void enterAlterColumnAction(PostgreSQLParser.AlterColumnActionContext ctx);
	/**
	 * Exit a parse tree produced by {@link PostgreSQLParser#alterColumnAction}.
	 * @param ctx the parse tree
	 */
	void exitAlterColumnAction(PostgreSQLParser.AlterColumnActionContext ctx);
	/**
	 * Enter a parse tree produced by {@link PostgreSQLParser#dataType}.
	 * @param ctx the parse tree
	 */
	void enterDataType(PostgreSQLParser.DataTypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link PostgreSQLParser#dataType}.
	 * @param ctx the parse tree
	 */
	void exitDataType(PostgreSQLParser.DataTypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link PostgreSQLParser#constraint}.
	 * @param ctx the parse tree
	 */
	void enterConstraint(PostgreSQLParser.ConstraintContext ctx);
	/**
	 * Exit a parse tree produced by {@link PostgreSQLParser#constraint}.
	 * @param ctx the parse tree
	 */
	void exitConstraint(PostgreSQLParser.ConstraintContext ctx);
	/**
	 * Enter a parse tree produced by {@link PostgreSQLParser#onDeleteAction}.
	 * @param ctx the parse tree
	 */
	void enterOnDeleteAction(PostgreSQLParser.OnDeleteActionContext ctx);
	/**
	 * Exit a parse tree produced by {@link PostgreSQLParser#onDeleteAction}.
	 * @param ctx the parse tree
	 */
	void exitOnDeleteAction(PostgreSQLParser.OnDeleteActionContext ctx);
	/**
	 * Enter a parse tree produced by {@link PostgreSQLParser#onUpdateAction}.
	 * @param ctx the parse tree
	 */
	void enterOnUpdateAction(PostgreSQLParser.OnUpdateActionContext ctx);
	/**
	 * Exit a parse tree produced by {@link PostgreSQLParser#onUpdateAction}.
	 * @param ctx the parse tree
	 */
	void exitOnUpdateAction(PostgreSQLParser.OnUpdateActionContext ctx);
	/**
	 * Enter a parse tree produced by {@link PostgreSQLParser#tableConstraint}.
	 * @param ctx the parse tree
	 */
	void enterTableConstraint(PostgreSQLParser.TableConstraintContext ctx);
	/**
	 * Exit a parse tree produced by {@link PostgreSQLParser#tableConstraint}.
	 * @param ctx the parse tree
	 */
	void exitTableConstraint(PostgreSQLParser.TableConstraintContext ctx);
	/**
	 * Enter a parse tree produced by {@link PostgreSQLParser#excludeElementList}.
	 * @param ctx the parse tree
	 */
	void enterExcludeElementList(PostgreSQLParser.ExcludeElementListContext ctx);
	/**
	 * Exit a parse tree produced by {@link PostgreSQLParser#excludeElementList}.
	 * @param ctx the parse tree
	 */
	void exitExcludeElementList(PostgreSQLParser.ExcludeElementListContext ctx);
	/**
	 * Enter a parse tree produced by {@link PostgreSQLParser#excludeElement}.
	 * @param ctx the parse tree
	 */
	void enterExcludeElement(PostgreSQLParser.ExcludeElementContext ctx);
	/**
	 * Exit a parse tree produced by {@link PostgreSQLParser#excludeElement}.
	 * @param ctx the parse tree
	 */
	void exitExcludeElement(PostgreSQLParser.ExcludeElementContext ctx);
	/**
	 * Enter a parse tree produced by {@link PostgreSQLParser#onAction}.
	 * @param ctx the parse tree
	 */
	void enterOnAction(PostgreSQLParser.OnActionContext ctx);
	/**
	 * Exit a parse tree produced by {@link PostgreSQLParser#onAction}.
	 * @param ctx the parse tree
	 */
	void exitOnAction(PostgreSQLParser.OnActionContext ctx);
	/**
	 * Enter a parse tree produced by {@link PostgreSQLParser#action}.
	 * @param ctx the parse tree
	 */
	void enterAction(PostgreSQLParser.ActionContext ctx);
	/**
	 * Exit a parse tree produced by {@link PostgreSQLParser#action}.
	 * @param ctx the parse tree
	 */
	void exitAction(PostgreSQLParser.ActionContext ctx);
	/**
	 * Enter a parse tree produced by {@link PostgreSQLParser#triggerStatement}.
	 * @param ctx the parse tree
	 */
	void enterTriggerStatement(PostgreSQLParser.TriggerStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link PostgreSQLParser#triggerStatement}.
	 * @param ctx the parse tree
	 */
	void exitTriggerStatement(PostgreSQLParser.TriggerStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link PostgreSQLParser#triggerTiming}.
	 * @param ctx the parse tree
	 */
	void enterTriggerTiming(PostgreSQLParser.TriggerTimingContext ctx);
	/**
	 * Exit a parse tree produced by {@link PostgreSQLParser#triggerTiming}.
	 * @param ctx the parse tree
	 */
	void exitTriggerTiming(PostgreSQLParser.TriggerTimingContext ctx);
	/**
	 * Enter a parse tree produced by {@link PostgreSQLParser#triggerEvent}.
	 * @param ctx the parse tree
	 */
	void enterTriggerEvent(PostgreSQLParser.TriggerEventContext ctx);
	/**
	 * Exit a parse tree produced by {@link PostgreSQLParser#triggerEvent}.
	 * @param ctx the parse tree
	 */
	void exitTriggerEvent(PostgreSQLParser.TriggerEventContext ctx);
	/**
	 * Enter a parse tree produced by {@link PostgreSQLParser#dataManipulationStatement}.
	 * @param ctx the parse tree
	 */
	void enterDataManipulationStatement(PostgreSQLParser.DataManipulationStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link PostgreSQLParser#dataManipulationStatement}.
	 * @param ctx the parse tree
	 */
	void exitDataManipulationStatement(PostgreSQLParser.DataManipulationStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link PostgreSQLParser#insertStatement}.
	 * @param ctx the parse tree
	 */
	void enterInsertStatement(PostgreSQLParser.InsertStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link PostgreSQLParser#insertStatement}.
	 * @param ctx the parse tree
	 */
	void exitInsertStatement(PostgreSQLParser.InsertStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link PostgreSQLParser#updateStatement}.
	 * @param ctx the parse tree
	 */
	void enterUpdateStatement(PostgreSQLParser.UpdateStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link PostgreSQLParser#updateStatement}.
	 * @param ctx the parse tree
	 */
	void exitUpdateStatement(PostgreSQLParser.UpdateStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link PostgreSQLParser#deleteStatement}.
	 * @param ctx the parse tree
	 */
	void enterDeleteStatement(PostgreSQLParser.DeleteStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link PostgreSQLParser#deleteStatement}.
	 * @param ctx the parse tree
	 */
	void exitDeleteStatement(PostgreSQLParser.DeleteStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link PostgreSQLParser#condition}.
	 * @param ctx the parse tree
	 */
	void enterCondition(PostgreSQLParser.ConditionContext ctx);
	/**
	 * Exit a parse tree produced by {@link PostgreSQLParser#condition}.
	 * @param ctx the parse tree
	 */
	void exitCondition(PostgreSQLParser.ConditionContext ctx);
	/**
	 * Enter a parse tree produced by {@link PostgreSQLParser#subquery}.
	 * @param ctx the parse tree
	 */
	void enterSubquery(PostgreSQLParser.SubqueryContext ctx);
	/**
	 * Exit a parse tree produced by {@link PostgreSQLParser#subquery}.
	 * @param ctx the parse tree
	 */
	void exitSubquery(PostgreSQLParser.SubqueryContext ctx);
	/**
	 * Enter a parse tree produced by {@link PostgreSQLParser#comparisonOperator}.
	 * @param ctx the parse tree
	 */
	void enterComparisonOperator(PostgreSQLParser.ComparisonOperatorContext ctx);
	/**
	 * Exit a parse tree produced by {@link PostgreSQLParser#comparisonOperator}.
	 * @param ctx the parse tree
	 */
	void exitComparisonOperator(PostgreSQLParser.ComparisonOperatorContext ctx);
	/**
	 * Enter a parse tree produced by {@link PostgreSQLParser#arithmeticOperator}.
	 * @param ctx the parse tree
	 */
	void enterArithmeticOperator(PostgreSQLParser.ArithmeticOperatorContext ctx);
	/**
	 * Exit a parse tree produced by {@link PostgreSQLParser#arithmeticOperator}.
	 * @param ctx the parse tree
	 */
	void exitArithmeticOperator(PostgreSQLParser.ArithmeticOperatorContext ctx);
	/**
	 * Enter a parse tree produced by {@link PostgreSQLParser#sqlScript}.
	 * @param ctx the parse tree
	 */
	void enterSqlScript(PostgreSQLParser.SqlScriptContext ctx);
	/**
	 * Exit a parse tree produced by {@link PostgreSQLParser#sqlScript}.
	 * @param ctx the parse tree
	 */
	void exitSqlScript(PostgreSQLParser.SqlScriptContext ctx);
	/**
	 * Enter a parse tree produced by {@link PostgreSQLParser#createSchemaStatement}.
	 * @param ctx the parse tree
	 */
	void enterCreateSchemaStatement(PostgreSQLParser.CreateSchemaStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link PostgreSQLParser#createSchemaStatement}.
	 * @param ctx the parse tree
	 */
	void exitCreateSchemaStatement(PostgreSQLParser.CreateSchemaStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link PostgreSQLParser#createViewStatement}.
	 * @param ctx the parse tree
	 */
	void enterCreateViewStatement(PostgreSQLParser.CreateViewStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link PostgreSQLParser#createViewStatement}.
	 * @param ctx the parse tree
	 */
	void exitCreateViewStatement(PostgreSQLParser.CreateViewStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link PostgreSQLParser#createFunctionStatement}.
	 * @param ctx the parse tree
	 */
	void enterCreateFunctionStatement(PostgreSQLParser.CreateFunctionStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link PostgreSQLParser#createFunctionStatement}.
	 * @param ctx the parse tree
	 */
	void exitCreateFunctionStatement(PostgreSQLParser.CreateFunctionStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link PostgreSQLParser#parameter}.
	 * @param ctx the parse tree
	 */
	void enterParameter(PostgreSQLParser.ParameterContext ctx);
	/**
	 * Exit a parse tree produced by {@link PostgreSQLParser#parameter}.
	 * @param ctx the parse tree
	 */
	void exitParameter(PostgreSQLParser.ParameterContext ctx);
	/**
	 * Enter a parse tree produced by {@link PostgreSQLParser#selectStatement}.
	 * @param ctx the parse tree
	 */
	void enterSelectStatement(PostgreSQLParser.SelectStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link PostgreSQLParser#selectStatement}.
	 * @param ctx the parse tree
	 */
	void exitSelectStatement(PostgreSQLParser.SelectStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link PostgreSQLParser#selectList}.
	 * @param ctx the parse tree
	 */
	void enterSelectList(PostgreSQLParser.SelectListContext ctx);
	/**
	 * Exit a parse tree produced by {@link PostgreSQLParser#selectList}.
	 * @param ctx the parse tree
	 */
	void exitSelectList(PostgreSQLParser.SelectListContext ctx);
	/**
	 * Enter a parse tree produced by {@link PostgreSQLParser#tableReference}.
	 * @param ctx the parse tree
	 */
	void enterTableReference(PostgreSQLParser.TableReferenceContext ctx);
	/**
	 * Exit a parse tree produced by {@link PostgreSQLParser#tableReference}.
	 * @param ctx the parse tree
	 */
	void exitTableReference(PostgreSQLParser.TableReferenceContext ctx);
	/**
	 * Enter a parse tree produced by {@link PostgreSQLParser#whereClause}.
	 * @param ctx the parse tree
	 */
	void enterWhereClause(PostgreSQLParser.WhereClauseContext ctx);
	/**
	 * Exit a parse tree produced by {@link PostgreSQLParser#whereClause}.
	 * @param ctx the parse tree
	 */
	void exitWhereClause(PostgreSQLParser.WhereClauseContext ctx);
	/**
	 * Enter a parse tree produced by {@link PostgreSQLParser#orderByClause}.
	 * @param ctx the parse tree
	 */
	void enterOrderByClause(PostgreSQLParser.OrderByClauseContext ctx);
	/**
	 * Exit a parse tree produced by {@link PostgreSQLParser#orderByClause}.
	 * @param ctx the parse tree
	 */
	void exitOrderByClause(PostgreSQLParser.OrderByClauseContext ctx);
	/**
	 * Enter a parse tree produced by {@link PostgreSQLParser#limitClause}.
	 * @param ctx the parse tree
	 */
	void enterLimitClause(PostgreSQLParser.LimitClauseContext ctx);
	/**
	 * Exit a parse tree produced by {@link PostgreSQLParser#limitClause}.
	 * @param ctx the parse tree
	 */
	void exitLimitClause(PostgreSQLParser.LimitClauseContext ctx);
	/**
	 * Enter a parse tree produced by {@link PostgreSQLParser#columnNameList}.
	 * @param ctx the parse tree
	 */
	void enterColumnNameList(PostgreSQLParser.ColumnNameListContext ctx);
	/**
	 * Exit a parse tree produced by {@link PostgreSQLParser#columnNameList}.
	 * @param ctx the parse tree
	 */
	void exitColumnNameList(PostgreSQLParser.ColumnNameListContext ctx);
	/**
	 * Enter a parse tree produced by {@link PostgreSQLParser#foreignTable}.
	 * @param ctx the parse tree
	 */
	void enterForeignTable(PostgreSQLParser.ForeignTableContext ctx);
	/**
	 * Exit a parse tree produced by {@link PostgreSQLParser#foreignTable}.
	 * @param ctx the parse tree
	 */
	void exitForeignTable(PostgreSQLParser.ForeignTableContext ctx);
	/**
	 * Enter a parse tree produced by {@link PostgreSQLParser#conflictAction}.
	 * @param ctx the parse tree
	 */
	void enterConflictAction(PostgreSQLParser.ConflictActionContext ctx);
	/**
	 * Exit a parse tree produced by {@link PostgreSQLParser#conflictAction}.
	 * @param ctx the parse tree
	 */
	void exitConflictAction(PostgreSQLParser.ConflictActionContext ctx);
	/**
	 * Enter a parse tree produced by {@link PostgreSQLParser#schemaName}.
	 * @param ctx the parse tree
	 */
	void enterSchemaName(PostgreSQLParser.SchemaNameContext ctx);
	/**
	 * Exit a parse tree produced by {@link PostgreSQLParser#schemaName}.
	 * @param ctx the parse tree
	 */
	void exitSchemaName(PostgreSQLParser.SchemaNameContext ctx);
	/**
	 * Enter a parse tree produced by {@link PostgreSQLParser#tableName}.
	 * @param ctx the parse tree
	 */
	void enterTableName(PostgreSQLParser.TableNameContext ctx);
	/**
	 * Exit a parse tree produced by {@link PostgreSQLParser#tableName}.
	 * @param ctx the parse tree
	 */
	void exitTableName(PostgreSQLParser.TableNameContext ctx);
	/**
	 * Enter a parse tree produced by {@link PostgreSQLParser#columnName}.
	 * @param ctx the parse tree
	 */
	void enterColumnName(PostgreSQLParser.ColumnNameContext ctx);
	/**
	 * Exit a parse tree produced by {@link PostgreSQLParser#columnName}.
	 * @param ctx the parse tree
	 */
	void exitColumnName(PostgreSQLParser.ColumnNameContext ctx);
	/**
	 * Enter a parse tree produced by {@link PostgreSQLParser#relationshipType}.
	 * @param ctx the parse tree
	 */
	void enterRelationshipType(PostgreSQLParser.RelationshipTypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link PostgreSQLParser#relationshipType}.
	 * @param ctx the parse tree
	 */
	void exitRelationshipType(PostgreSQLParser.RelationshipTypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link PostgreSQLParser#decimalType}.
	 * @param ctx the parse tree
	 */
	void enterDecimalType(PostgreSQLParser.DecimalTypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link PostgreSQLParser#decimalType}.
	 * @param ctx the parse tree
	 */
	void exitDecimalType(PostgreSQLParser.DecimalTypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link PostgreSQLParser#partitionClause}.
	 * @param ctx the parse tree
	 */
	void enterPartitionClause(PostgreSQLParser.PartitionClauseContext ctx);
	/**
	 * Exit a parse tree produced by {@link PostgreSQLParser#partitionClause}.
	 * @param ctx the parse tree
	 */
	void exitPartitionClause(PostgreSQLParser.PartitionClauseContext ctx);
	/**
	 * Enter a parse tree produced by {@link PostgreSQLParser#partitionStrategy}.
	 * @param ctx the parse tree
	 */
	void enterPartitionStrategy(PostgreSQLParser.PartitionStrategyContext ctx);
	/**
	 * Exit a parse tree produced by {@link PostgreSQLParser#partitionStrategy}.
	 * @param ctx the parse tree
	 */
	void exitPartitionStrategy(PostgreSQLParser.PartitionStrategyContext ctx);
	/**
	 * Enter a parse tree produced by {@link PostgreSQLParser#createPartitionStatement}.
	 * @param ctx the parse tree
	 */
	void enterCreatePartitionStatement(PostgreSQLParser.CreatePartitionStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link PostgreSQLParser#createPartitionStatement}.
	 * @param ctx the parse tree
	 */
	void exitCreatePartitionStatement(PostgreSQLParser.CreatePartitionStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link PostgreSQLParser#partitionValuesClause}.
	 * @param ctx the parse tree
	 */
	void enterPartitionValuesClause(PostgreSQLParser.PartitionValuesClauseContext ctx);
	/**
	 * Exit a parse tree produced by {@link PostgreSQLParser#partitionValuesClause}.
	 * @param ctx the parse tree
	 */
	void exitPartitionValuesClause(PostgreSQLParser.PartitionValuesClauseContext ctx);
	/**
	 * Enter a parse tree produced by {@link PostgreSQLParser#partitionOfClause}.
	 * @param ctx the parse tree
	 */
	void enterPartitionOfClause(PostgreSQLParser.PartitionOfClauseContext ctx);
	/**
	 * Exit a parse tree produced by {@link PostgreSQLParser#partitionOfClause}.
	 * @param ctx the parse tree
	 */
	void exitPartitionOfClause(PostgreSQLParser.PartitionOfClauseContext ctx);
	/**
	 * Enter a parse tree produced by {@link PostgreSQLParser#createIndexStatement}.
	 * @param ctx the parse tree
	 */
	void enterCreateIndexStatement(PostgreSQLParser.CreateIndexStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link PostgreSQLParser#createIndexStatement}.
	 * @param ctx the parse tree
	 */
	void exitCreateIndexStatement(PostgreSQLParser.CreateIndexStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link PostgreSQLParser#indexOptionsClause}.
	 * @param ctx the parse tree
	 */
	void enterIndexOptionsClause(PostgreSQLParser.IndexOptionsClauseContext ctx);
	/**
	 * Exit a parse tree produced by {@link PostgreSQLParser#indexOptionsClause}.
	 * @param ctx the parse tree
	 */
	void exitIndexOptionsClause(PostgreSQLParser.IndexOptionsClauseContext ctx);
	/**
	 * Enter a parse tree produced by {@link PostgreSQLParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterExpression(PostgreSQLParser.ExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link PostgreSQLParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitExpression(PostgreSQLParser.ExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link PostgreSQLParser#value}.
	 * @param ctx the parse tree
	 */
	void enterValue(PostgreSQLParser.ValueContext ctx);
	/**
	 * Exit a parse tree produced by {@link PostgreSQLParser#value}.
	 * @param ctx the parse tree
	 */
	void exitValue(PostgreSQLParser.ValueContext ctx);
	/**
	 * Enter a parse tree produced by {@link PostgreSQLParser#createPolicyStatement}.
	 * @param ctx the parse tree
	 */
	void enterCreatePolicyStatement(PostgreSQLParser.CreatePolicyStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link PostgreSQLParser#createPolicyStatement}.
	 * @param ctx the parse tree
	 */
	void exitCreatePolicyStatement(PostgreSQLParser.CreatePolicyStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link PostgreSQLParser#plpgsqlBlock}.
	 * @param ctx the parse tree
	 */
	void enterPlpgsqlBlock(PostgreSQLParser.PlpgsqlBlockContext ctx);
	/**
	 * Exit a parse tree produced by {@link PostgreSQLParser#plpgsqlBlock}.
	 * @param ctx the parse tree
	 */
	void exitPlpgsqlBlock(PostgreSQLParser.PlpgsqlBlockContext ctx);
	/**
	 * Enter a parse tree produced by {@link PostgreSQLParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterStatement(PostgreSQLParser.StatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link PostgreSQLParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitStatement(PostgreSQLParser.StatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link PostgreSQLParser#createRuleStatement}.
	 * @param ctx the parse tree
	 */
	void enterCreateRuleStatement(PostgreSQLParser.CreateRuleStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link PostgreSQLParser#createRuleStatement}.
	 * @param ctx the parse tree
	 */
	void exitCreateRuleStatement(PostgreSQLParser.CreateRuleStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link PostgreSQLParser#ruleEvent}.
	 * @param ctx the parse tree
	 */
	void enterRuleEvent(PostgreSQLParser.RuleEventContext ctx);
	/**
	 * Exit a parse tree produced by {@link PostgreSQLParser#ruleEvent}.
	 * @param ctx the parse tree
	 */
	void exitRuleEvent(PostgreSQLParser.RuleEventContext ctx);
	/**
	 * Enter a parse tree produced by {@link PostgreSQLParser#ruleAction}.
	 * @param ctx the parse tree
	 */
	void enterRuleAction(PostgreSQLParser.RuleActionContext ctx);
	/**
	 * Exit a parse tree produced by {@link PostgreSQLParser#ruleAction}.
	 * @param ctx the parse tree
	 */
	void exitRuleAction(PostgreSQLParser.RuleActionContext ctx);
	/**
	 * Enter a parse tree produced by {@link PostgreSQLParser#sqlAction}.
	 * @param ctx the parse tree
	 */
	void enterSqlAction(PostgreSQLParser.SqlActionContext ctx);
	/**
	 * Exit a parse tree produced by {@link PostgreSQLParser#sqlAction}.
	 * @param ctx the parse tree
	 */
	void exitSqlAction(PostgreSQLParser.SqlActionContext ctx);
	/**
	 * Enter a parse tree produced by {@link PostgreSQLParser#defaultValue}.
	 * @param ctx the parse tree
	 */
	void enterDefaultValue(PostgreSQLParser.DefaultValueContext ctx);
	/**
	 * Exit a parse tree produced by {@link PostgreSQLParser#defaultValue}.
	 * @param ctx the parse tree
	 */
	void exitDefaultValue(PostgreSQLParser.DefaultValueContext ctx);
	/**
	 * Enter a parse tree produced by {@link PostgreSQLParser#numericLiteral}.
	 * @param ctx the parse tree
	 */
	void enterNumericLiteral(PostgreSQLParser.NumericLiteralContext ctx);
	/**
	 * Exit a parse tree produced by {@link PostgreSQLParser#numericLiteral}.
	 * @param ctx the parse tree
	 */
	void exitNumericLiteral(PostgreSQLParser.NumericLiteralContext ctx);
	/**
	 * Enter a parse tree produced by {@link PostgreSQLParser#booleanLiteral}.
	 * @param ctx the parse tree
	 */
	void enterBooleanLiteral(PostgreSQLParser.BooleanLiteralContext ctx);
	/**
	 * Exit a parse tree produced by {@link PostgreSQLParser#booleanLiteral}.
	 * @param ctx the parse tree
	 */
	void exitBooleanLiteral(PostgreSQLParser.BooleanLiteralContext ctx);
	/**
	 * Enter a parse tree produced by {@link PostgreSQLParser#foreignKeyAction}.
	 * @param ctx the parse tree
	 */
	void enterForeignKeyAction(PostgreSQLParser.ForeignKeyActionContext ctx);
	/**
	 * Exit a parse tree produced by {@link PostgreSQLParser#foreignKeyAction}.
	 * @param ctx the parse tree
	 */
	void exitForeignKeyAction(PostgreSQLParser.ForeignKeyActionContext ctx);
	/**
	 * Enter a parse tree produced by {@link PostgreSQLParser#referentialAction}.
	 * @param ctx the parse tree
	 */
	void enterReferentialAction(PostgreSQLParser.ReferentialActionContext ctx);
	/**
	 * Exit a parse tree produced by {@link PostgreSQLParser#referentialAction}.
	 * @param ctx the parse tree
	 */
	void exitReferentialAction(PostgreSQLParser.ReferentialActionContext ctx);
}