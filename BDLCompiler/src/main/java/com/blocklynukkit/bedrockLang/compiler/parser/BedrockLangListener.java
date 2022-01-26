// Generated from D:/ideaProject/BedrockLang/BDLCompiler/src/main/antlr\BedrockLang.g4 by ANTLR 4.9.1
package com.blocklynukkit.bedrockLang.compiler.parser;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link BedrockLangParser}.
 */
public interface BedrockLangListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link BedrockLangParser#program}.
	 * @param ctx the parse tree
	 */
	void enterProgram(BedrockLangParser.ProgramContext ctx);
	/**
	 * Exit a parse tree produced by {@link BedrockLangParser#program}.
	 * @param ctx the parse tree
	 */
	void exitProgram(BedrockLangParser.ProgramContext ctx);
	/**
	 * Enter a parse tree produced by {@link BedrockLangParser#stat}.
	 * @param ctx the parse tree
	 */
	void enterStat(BedrockLangParser.StatContext ctx);
	/**
	 * Exit a parse tree produced by {@link BedrockLangParser#stat}.
	 * @param ctx the parse tree
	 */
	void exitStat(BedrockLangParser.StatContext ctx);
	/**
	 * Enter a parse tree produced by the {@code commandExpr}
	 * labeled alternative in {@link BedrockLangParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterCommandExpr(BedrockLangParser.CommandExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code commandExpr}
	 * labeled alternative in {@link BedrockLangParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitCommandExpr(BedrockLangParser.CommandExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code divideExpr}
	 * labeled alternative in {@link BedrockLangParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterDivideExpr(BedrockLangParser.DivideExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code divideExpr}
	 * labeled alternative in {@link BedrockLangParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitDivideExpr(BedrockLangParser.DivideExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code lowerEqualExpr}
	 * labeled alternative in {@link BedrockLangParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterLowerEqualExpr(BedrockLangParser.LowerEqualExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code lowerEqualExpr}
	 * labeled alternative in {@link BedrockLangParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitLowerEqualExpr(BedrockLangParser.LowerEqualExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code bracketExpr}
	 * labeled alternative in {@link BedrockLangParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterBracketExpr(BedrockLangParser.BracketExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code bracketExpr}
	 * labeled alternative in {@link BedrockLangParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitBracketExpr(BedrockLangParser.BracketExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code negativeExpr}
	 * labeled alternative in {@link BedrockLangParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterNegativeExpr(BedrockLangParser.NegativeExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code negativeExpr}
	 * labeled alternative in {@link BedrockLangParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitNegativeExpr(BedrockLangParser.NegativeExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code multiplyExpr}
	 * labeled alternative in {@link BedrockLangParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterMultiplyExpr(BedrockLangParser.MultiplyExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code multiplyExpr}
	 * labeled alternative in {@link BedrockLangParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitMultiplyExpr(BedrockLangParser.MultiplyExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code minusExpr}
	 * labeled alternative in {@link BedrockLangParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterMinusExpr(BedrockLangParser.MinusExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code minusExpr}
	 * labeled alternative in {@link BedrockLangParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitMinusExpr(BedrockLangParser.MinusExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code greaterEqualExpr}
	 * labeled alternative in {@link BedrockLangParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterGreaterEqualExpr(BedrockLangParser.GreaterEqualExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code greaterEqualExpr}
	 * labeled alternative in {@link BedrockLangParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitGreaterEqualExpr(BedrockLangParser.GreaterEqualExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code remainExpr}
	 * labeled alternative in {@link BedrockLangParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterRemainExpr(BedrockLangParser.RemainExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code remainExpr}
	 * labeled alternative in {@link BedrockLangParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitRemainExpr(BedrockLangParser.RemainExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code plusExpr}
	 * labeled alternative in {@link BedrockLangParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterPlusExpr(BedrockLangParser.PlusExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code plusExpr}
	 * labeled alternative in {@link BedrockLangParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitPlusExpr(BedrockLangParser.PlusExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code varExpr}
	 * labeled alternative in {@link BedrockLangParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterVarExpr(BedrockLangParser.VarExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code varExpr}
	 * labeled alternative in {@link BedrockLangParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitVarExpr(BedrockLangParser.VarExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code setVarExpr}
	 * labeled alternative in {@link BedrockLangParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterSetVarExpr(BedrockLangParser.SetVarExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code setVarExpr}
	 * labeled alternative in {@link BedrockLangParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitSetVarExpr(BedrockLangParser.SetVarExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code greaterExpr}
	 * labeled alternative in {@link BedrockLangParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterGreaterExpr(BedrockLangParser.GreaterExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code greaterExpr}
	 * labeled alternative in {@link BedrockLangParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitGreaterExpr(BedrockLangParser.GreaterExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code lowerExpr}
	 * labeled alternative in {@link BedrockLangParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterLowerExpr(BedrockLangParser.LowerExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code lowerExpr}
	 * labeled alternative in {@link BedrockLangParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitLowerExpr(BedrockLangParser.LowerExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code literalExpr}
	 * labeled alternative in {@link BedrockLangParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterLiteralExpr(BedrockLangParser.LiteralExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code literalExpr}
	 * labeled alternative in {@link BedrockLangParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitLiteralExpr(BedrockLangParser.LiteralExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code chainVirtualFieldExpr}
	 * labeled alternative in {@link BedrockLangParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterChainVirtualFieldExpr(BedrockLangParser.ChainVirtualFieldExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code chainVirtualFieldExpr}
	 * labeled alternative in {@link BedrockLangParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitChainVirtualFieldExpr(BedrockLangParser.ChainVirtualFieldExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code notEqualExpr}
	 * labeled alternative in {@link BedrockLangParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterNotEqualExpr(BedrockLangParser.NotEqualExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code notEqualExpr}
	 * labeled alternative in {@link BedrockLangParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitNotEqualExpr(BedrockLangParser.NotEqualExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code positiveExpr}
	 * labeled alternative in {@link BedrockLangParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterPositiveExpr(BedrockLangParser.PositiveExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code positiveExpr}
	 * labeled alternative in {@link BedrockLangParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitPositiveExpr(BedrockLangParser.PositiveExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code chainStaticFieldExpr}
	 * labeled alternative in {@link BedrockLangParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterChainStaticFieldExpr(BedrockLangParser.ChainStaticFieldExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code chainStaticFieldExpr}
	 * labeled alternative in {@link BedrockLangParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitChainStaticFieldExpr(BedrockLangParser.ChainStaticFieldExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code equalExpr}
	 * labeled alternative in {@link BedrockLangParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterEqualExpr(BedrockLangParser.EqualExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code equalExpr}
	 * labeled alternative in {@link BedrockLangParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitEqualExpr(BedrockLangParser.EqualExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code importSingleStatic}
	 * labeled alternative in {@link BedrockLangParser#importStat}.
	 * @param ctx the parse tree
	 */
	void enterImportSingleStatic(BedrockLangParser.ImportSingleStaticContext ctx);
	/**
	 * Exit a parse tree produced by the {@code importSingleStatic}
	 * labeled alternative in {@link BedrockLangParser#importStat}.
	 * @param ctx the parse tree
	 */
	void exitImportSingleStatic(BedrockLangParser.ImportSingleStaticContext ctx);
	/**
	 * Enter a parse tree produced by the {@code importAllStatic}
	 * labeled alternative in {@link BedrockLangParser#importStat}.
	 * @param ctx the parse tree
	 */
	void enterImportAllStatic(BedrockLangParser.ImportAllStaticContext ctx);
	/**
	 * Exit a parse tree produced by the {@code importAllStatic}
	 * labeled alternative in {@link BedrockLangParser#importStat}.
	 * @param ctx the parse tree
	 */
	void exitImportAllStatic(BedrockLangParser.ImportAllStaticContext ctx);
	/**
	 * Enter a parse tree produced by the {@code importClass}
	 * labeled alternative in {@link BedrockLangParser#importStat}.
	 * @param ctx the parse tree
	 */
	void enterImportClass(BedrockLangParser.ImportClassContext ctx);
	/**
	 * Exit a parse tree produced by the {@code importClass}
	 * labeled alternative in {@link BedrockLangParser#importStat}.
	 * @param ctx the parse tree
	 */
	void exitImportClass(BedrockLangParser.ImportClassContext ctx);
	/**
	 * Enter a parse tree produced by {@link BedrockLangParser#whenStat}.
	 * @param ctx the parse tree
	 */
	void enterWhenStat(BedrockLangParser.WhenStatContext ctx);
	/**
	 * Exit a parse tree produced by {@link BedrockLangParser#whenStat}.
	 * @param ctx the parse tree
	 */
	void exitWhenStat(BedrockLangParser.WhenStatContext ctx);
	/**
	 * Enter a parse tree produced by {@link BedrockLangParser#ifElseStat}.
	 * @param ctx the parse tree
	 */
	void enterIfElseStat(BedrockLangParser.IfElseStatContext ctx);
	/**
	 * Exit a parse tree produced by {@link BedrockLangParser#ifElseStat}.
	 * @param ctx the parse tree
	 */
	void exitIfElseStat(BedrockLangParser.IfElseStatContext ctx);
	/**
	 * Enter a parse tree produced by {@link BedrockLangParser#whileStat}.
	 * @param ctx the parse tree
	 */
	void enterWhileStat(BedrockLangParser.WhileStatContext ctx);
	/**
	 * Exit a parse tree produced by {@link BedrockLangParser#whileStat}.
	 * @param ctx the parse tree
	 */
	void exitWhileStat(BedrockLangParser.WhileStatContext ctx);
	/**
	 * Enter a parse tree produced by {@link BedrockLangParser#defineCmdStat}.
	 * @param ctx the parse tree
	 */
	void enterDefineCmdStat(BedrockLangParser.DefineCmdStatContext ctx);
	/**
	 * Exit a parse tree produced by {@link BedrockLangParser#defineCmdStat}.
	 * @param ctx the parse tree
	 */
	void exitDefineCmdStat(BedrockLangParser.DefineCmdStatContext ctx);
	/**
	 * Enter a parse tree produced by {@link BedrockLangParser#defineSignature}.
	 * @param ctx the parse tree
	 */
	void enterDefineSignature(BedrockLangParser.DefineSignatureContext ctx);
	/**
	 * Exit a parse tree produced by {@link BedrockLangParser#defineSignature}.
	 * @param ctx the parse tree
	 */
	void exitDefineSignature(BedrockLangParser.DefineSignatureContext ctx);
	/**
	 * Enter a parse tree produced by {@link BedrockLangParser#defineSignatureWordSingle}.
	 * @param ctx the parse tree
	 */
	void enterDefineSignatureWordSingle(BedrockLangParser.DefineSignatureWordSingleContext ctx);
	/**
	 * Exit a parse tree produced by {@link BedrockLangParser#defineSignatureWordSingle}.
	 * @param ctx the parse tree
	 */
	void exitDefineSignatureWordSingle(BedrockLangParser.DefineSignatureWordSingleContext ctx);
	/**
	 * Enter a parse tree produced by {@link BedrockLangParser#defineSignatureVariable}.
	 * @param ctx the parse tree
	 */
	void enterDefineSignatureVariable(BedrockLangParser.DefineSignatureVariableContext ctx);
	/**
	 * Exit a parse tree produced by {@link BedrockLangParser#defineSignatureVariable}.
	 * @param ctx the parse tree
	 */
	void exitDefineSignatureVariable(BedrockLangParser.DefineSignatureVariableContext ctx);
	/**
	 * Enter a parse tree produced by the {@code hasTypeVarDeclare}
	 * labeled alternative in {@link BedrockLangParser#declareVarStat}.
	 * @param ctx the parse tree
	 */
	void enterHasTypeVarDeclare(BedrockLangParser.HasTypeVarDeclareContext ctx);
	/**
	 * Exit a parse tree produced by the {@code hasTypeVarDeclare}
	 * labeled alternative in {@link BedrockLangParser#declareVarStat}.
	 * @param ctx the parse tree
	 */
	void exitHasTypeVarDeclare(BedrockLangParser.HasTypeVarDeclareContext ctx);
	/**
	 * Enter a parse tree produced by the {@code inferTypeVarDeclare}
	 * labeled alternative in {@link BedrockLangParser#declareVarStat}.
	 * @param ctx the parse tree
	 */
	void enterInferTypeVarDeclare(BedrockLangParser.InferTypeVarDeclareContext ctx);
	/**
	 * Exit a parse tree produced by the {@code inferTypeVarDeclare}
	 * labeled alternative in {@link BedrockLangParser#declareVarStat}.
	 * @param ctx the parse tree
	 */
	void exitInferTypeVarDeclare(BedrockLangParser.InferTypeVarDeclareContext ctx);
	/**
	 * Enter a parse tree produced by {@link BedrockLangParser#returnStat}.
	 * @param ctx the parse tree
	 */
	void enterReturnStat(BedrockLangParser.ReturnStatContext ctx);
	/**
	 * Exit a parse tree produced by {@link BedrockLangParser#returnStat}.
	 * @param ctx the parse tree
	 */
	void exitReturnStat(BedrockLangParser.ReturnStatContext ctx);
	/**
	 * Enter a parse tree produced by {@link BedrockLangParser#breakStat}.
	 * @param ctx the parse tree
	 */
	void enterBreakStat(BedrockLangParser.BreakStatContext ctx);
	/**
	 * Exit a parse tree produced by {@link BedrockLangParser#breakStat}.
	 * @param ctx the parse tree
	 */
	void exitBreakStat(BedrockLangParser.BreakStatContext ctx);
	/**
	 * Enter a parse tree produced by {@link BedrockLangParser#continueStat}.
	 * @param ctx the parse tree
	 */
	void enterContinueStat(BedrockLangParser.ContinueStatContext ctx);
	/**
	 * Exit a parse tree produced by {@link BedrockLangParser#continueStat}.
	 * @param ctx the parse tree
	 */
	void exitContinueStat(BedrockLangParser.ContinueStatContext ctx);
	/**
	 * Enter a parse tree produced by {@link BedrockLangParser#command}.
	 * @param ctx the parse tree
	 */
	void enterCommand(BedrockLangParser.CommandContext ctx);
	/**
	 * Exit a parse tree produced by {@link BedrockLangParser#command}.
	 * @param ctx the parse tree
	 */
	void exitCommand(BedrockLangParser.CommandContext ctx);
	/**
	 * Enter a parse tree produced by {@link BedrockLangParser#block}.
	 * @param ctx the parse tree
	 */
	void enterBlock(BedrockLangParser.BlockContext ctx);
	/**
	 * Exit a parse tree produced by {@link BedrockLangParser#block}.
	 * @param ctx the parse tree
	 */
	void exitBlock(BedrockLangParser.BlockContext ctx);
	/**
	 * Enter a parse tree produced by {@link BedrockLangParser#typeid}.
	 * @param ctx the parse tree
	 */
	void enterTypeid(BedrockLangParser.TypeidContext ctx);
	/**
	 * Exit a parse tree produced by {@link BedrockLangParser#typeid}.
	 * @param ctx the parse tree
	 */
	void exitTypeid(BedrockLangParser.TypeidContext ctx);
	/**
	 * Enter a parse tree produced by {@link BedrockLangParser#id}.
	 * @param ctx the parse tree
	 */
	void enterId(BedrockLangParser.IdContext ctx);
	/**
	 * Exit a parse tree produced by {@link BedrockLangParser#id}.
	 * @param ctx the parse tree
	 */
	void exitId(BedrockLangParser.IdContext ctx);
	/**
	 * Enter a parse tree produced by {@link BedrockLangParser#varid}.
	 * @param ctx the parse tree
	 */
	void enterVarid(BedrockLangParser.VaridContext ctx);
	/**
	 * Exit a parse tree produced by {@link BedrockLangParser#varid}.
	 * @param ctx the parse tree
	 */
	void exitVarid(BedrockLangParser.VaridContext ctx);
	/**
	 * Enter a parse tree produced by the {@code invokeCommand}
	 * labeled alternative in {@link BedrockLangParser#commandId}.
	 * @param ctx the parse tree
	 */
	void enterInvokeCommand(BedrockLangParser.InvokeCommandContext ctx);
	/**
	 * Exit a parse tree produced by the {@code invokeCommand}
	 * labeled alternative in {@link BedrockLangParser#commandId}.
	 * @param ctx the parse tree
	 */
	void exitInvokeCommand(BedrockLangParser.InvokeCommandContext ctx);
	/**
	 * Enter a parse tree produced by the {@code callCommand}
	 * labeled alternative in {@link BedrockLangParser#commandId}.
	 * @param ctx the parse tree
	 */
	void enterCallCommand(BedrockLangParser.CallCommandContext ctx);
	/**
	 * Exit a parse tree produced by the {@code callCommand}
	 * labeled alternative in {@link BedrockLangParser#commandId}.
	 * @param ctx the parse tree
	 */
	void exitCallCommand(BedrockLangParser.CallCommandContext ctx);
	/**
	 * Enter a parse tree produced by the {@code virtualCommand}
	 * labeled alternative in {@link BedrockLangParser#commandId}.
	 * @param ctx the parse tree
	 */
	void enterVirtualCommand(BedrockLangParser.VirtualCommandContext ctx);
	/**
	 * Exit a parse tree produced by the {@code virtualCommand}
	 * labeled alternative in {@link BedrockLangParser#commandId}.
	 * @param ctx the parse tree
	 */
	void exitVirtualCommand(BedrockLangParser.VirtualCommandContext ctx);
}