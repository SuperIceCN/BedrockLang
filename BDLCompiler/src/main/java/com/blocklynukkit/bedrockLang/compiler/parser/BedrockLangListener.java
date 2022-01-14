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
	 * Enter a parse tree produced by the {@code devideExpr}
	 * labeled alternative in {@link BedrockLangParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterDevideExpr(BedrockLangParser.DevideExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code devideExpr}
	 * labeled alternative in {@link BedrockLangParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitDevideExpr(BedrockLangParser.DevideExprContext ctx);
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
	 * Enter a parse tree produced by {@link BedrockLangParser#defineSignatureWordMultiple}.
	 * @param ctx the parse tree
	 */
	void enterDefineSignatureWordMultiple(BedrockLangParser.DefineSignatureWordMultipleContext ctx);
	/**
	 * Exit a parse tree produced by {@link BedrockLangParser#defineSignatureWordMultiple}.
	 * @param ctx the parse tree
	 */
	void exitDefineSignatureWordMultiple(BedrockLangParser.DefineSignatureWordMultipleContext ctx);
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
}