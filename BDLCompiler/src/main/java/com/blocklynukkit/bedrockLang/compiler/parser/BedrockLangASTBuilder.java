package com.blocklynukkit.bedrockLang.compiler.parser;

import com.blocklynukkit.bedrockLang.compiler.ast.compile.*;
import com.blocklynukkit.bedrockLang.compiler.ast.compile.impl.command.VariableCmdArg;
import com.blocklynukkit.bedrockLang.compiler.ast.compile.impl.command.WordCmdArg;
import com.blocklynukkit.bedrockLang.compiler.ast.compile.impl.piece.*;
import com.blocklynukkit.bedrockLang.compiler.ast.compile.impl.piece.operator.*;
import com.blocklynukkit.bedrockLang.compiler.ast.compile.impl.type.BasicValueType;
import com.blocklynukkit.bedrockLang.compiler.ast.compile.impl.unit.BDLUnit;
import com.blocklynukkit.bedrockLang.compiler.ast.exception.GlobalVariableCannotInitException;
import com.blocklynukkit.bedrockLang.compiler.ast.exception.NotImplementedException;
import com.blocklynukkit.bedrockLang.compiler.ast.exception.ShouldNotReachHereException;
import com.blocklynukkit.bedrockLang.compiler.ast.util.SourcePos;
import com.blocklynukkit.bedrockLang.compiler.parser.BedrockLangParser.*;
import lombok.val;
import lombok.var;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeProperty;

import static com.blocklynukkit.bedrockLang.compiler.parser.VisitResult.from;
import static com.blocklynukkit.bedrockLang.compiler.parser.VisitResult.of;

public class BedrockLangASTBuilder extends BedrockLangBaseVisitor<VisitResult<?, ?>> {
    private final BDLUnit bdlUnit;
    private final String sourceName;
    private final ParseTreeProperty<Piece> parentMap;

    public BedrockLangASTBuilder(String sourceName) {
        this.bdlUnit = new BDLUnit(sourceName.replace(".bdl", ""), sourceName);
        this.sourceName = sourceName;
        this.parentMap = new ParseTreeProperty<>();
    }

    @Override
    public VisitResult<BDLUnit, byte[]> visitProgram(ProgramContext ctx) {
        for (val each : ctx.children) {
            if (each instanceof ImportSingleStaticContext) {
                bdlUnit.addCodePiece(visitImportSingleStatic((ImportSingleStaticContext) each).getPiece());
            } else if (each instanceof ImportAllStaticContext) {
                bdlUnit.addCodePiece(visitImportAllStatic((ImportAllStaticContext) each).getPiece());
            } else if (each instanceof ImportClassContext) {
                bdlUnit.addCodePiece(visitImportClass((ImportClassContext) each).getPiece());
            } else if (each instanceof DeclareVarStatContext) {
                bdlUnit.addCodePiece(visitGlobalVarDeclareStat((DeclareVarStatContext) each).getPiece());
            } else if (each instanceof DefineCmdStatContext) {
                bdlUnit.addCodePiece(visitDefineCmdStat((DefineCmdStatContext) each).getPiece());
            } else {
                // TODO: 2022/1/24 支持更多语法
                throw new NotImplementedException(pos((ParserRuleContext) each), "Unimplemented grammar. ");
            }
        }
        return from(bdlUnit, bdlUnit.getCodeGenerator().generate(bdlUnit));
    }

    public VisitResult<UnitGlobalVariableDeclareStat, Void> visitGlobalVarDeclareStat(DeclareVarStatContext ctx) {
        if (ctx instanceof InferTypeVarDeclareContext) {
            throw new GlobalVariableCannotInitException(pos(ctx));
        } else {
            val varDeclare = (HasTypeVarDeclareContext) ctx;
            if (varDeclare.expr() != null) {
                throw new GlobalVariableCannotInitException(pos(ctx));
            }
            return of(new UnitGlobalVariableDeclareStat(no$(varDeclare.VAR().getText()),
                    ValueType.from(varDeclare.ID().getText()), pos(ctx), bdlUnit));
        }
    }

    @Override
    public VisitResult<ImportStat, Void> visitImportSingleStatic(ImportSingleStaticContext ctx) {
        return of(ImportStat.ofSpecificStaticMethods(
                pos(ctx), bdlUnit, ctx.id().getText(), ctx.ID().stream().map(ParseTree::getText).toArray(String[]::new)
        ));
    }

    @Override
    public VisitResult<ImportStat, Void> visitImportAllStatic(ImportAllStaticContext ctx) {
        return of(ImportStat.ofAllStaticMethods(pos(ctx), bdlUnit, ctx.id().getText()));
    }

    @Override
    public VisitResult<ImportStat, Void> visitImportClass(ImportClassContext ctx) {
        return of(ImportStat.ofPackage(pos(ctx), bdlUnit, ctx.id().getText()));
    }

    @Override
    public VisitResult<DefineCommandBlock, Void> visitDefineCmdStat(DefineCmdStatContext ctx) {
        val defSig = ctx.defineSignature();
        val returnType = ValueType.from(defSig.ID().size() > 1 ? defSig.ID(1).getText() : "void");
        val defineCommandBlock = new DefineCommandBlock(pos(ctx), bdlUnit, defSig.ID(0).getText(), returnType,
                defSig.children.stream()
                        .filter(tree -> tree instanceof DefineSignatureVariableContext || tree instanceof DefineSignatureWordSingleContext)
                        .map(tree -> {
                            if (tree instanceof DefineSignatureVariableContext) {
                                val varCtx = (DefineSignatureVariableContext) tree;
                                return new VariableCmdArg(no$(varCtx.varid().getText()), ValueType.from(varCtx.ID().getText()), pos(varCtx));
                            } else {
                                val wordCtx = (DefineSignatureWordSingleContext) tree;
                                return new WordCmdArg(wordCtx.ID().getText(), pos(wordCtx));
                            }
                        }).toArray(CmdArg[]::new));
        //遍历命令内部内容
        for (val each : ctx.block().children) {
            defineCommandBlock.addCodePiece(visit(each, defineCommandBlock).getPiece());
        }
        return of(defineCommandBlock);
    }

    @Override
    public VisitResult<?, ?> visitCommandExpr(CommandExprContext ctx) {
        return visit(ctx.command(), parent(ctx));
    }

    @Override
    public VisitResult<?, ?> visitBracketExpr(BracketExprContext ctx) {
        return visit(ctx.expr(), parent(ctx));
    }

    @Override
    public VisitResult<?, ?> visitNegativeExpr(NegativeExprContext ctx) {
        val expr = new MinusExpr(pos(ctx), parent(ctx));
        expr.setLeft(new LiteralExpr(pos(ctx), expr, 0, ValueType.from("byte")));
        expr.setRight((Expr) visit(ctx.expr(), expr).getPiece());
        return of(expr);
    }

    @Override
    public VisitResult<?, ?> visitMultiplyExpr(MultiplyExprContext ctx) {
        val expr = new MultiplyExpr(pos(ctx), parent(ctx));
        expr.setLeft((Expr) visit(ctx.expr(0), expr).getPiece());
        expr.setRight((Expr) visit(ctx.expr(1), expr).getPiece());
        return of(expr);
    }

    @Override
    public VisitResult<?, ?> visitMinusExpr(MinusExprContext ctx) {
        val expr = new MinusExpr(pos(ctx), parent(ctx));
        expr.setLeft((Expr) visit(ctx.expr(0), expr).getPiece());
        expr.setRight((Expr) visit(ctx.expr(1), expr).getPiece());
        return of(expr);
    }

    @Override
    public VisitResult<?, ?> visitRemainExpr(RemainExprContext ctx) {
        val expr = new ModExpr(pos(ctx), parent(ctx));
        expr.setLeft((Expr) visit(ctx.expr(0), expr).getPiece());
        expr.setRight((Expr) visit(ctx.expr(1), expr).getPiece());
        return of(expr);
    }

    @Override
    public VisitResult<?, ?> visitPlusExpr(PlusExprContext ctx) {
        val expr = new PlusExpr(pos(ctx), parent(ctx));
        expr.setLeft((Expr) visit(ctx.expr(0), expr).getPiece());
        expr.setRight((Expr) visit(ctx.expr(1), expr).getPiece());
        return of(expr);
    }

    @Override
    public VisitResult<?, ?> visitVarExpr(VarExprContext ctx) {
        return of(new ReadVariableExpr(pos(ctx), parent(ctx), ctx.varid().getText()));
    }

    @Override
    public VisitResult<?, ?> visitSetVarExpr(SetVarExprContext ctx) {
        val expr = new WriteVariableExpr(pos(ctx), parent(ctx), ctx.varid().getText());
        expr.setValueExpr((Expr) visit(ctx.expr(), expr).getPiece());
        return super.visitSetVarExpr(ctx);
    }

    @Override
    public VisitResult<?, ?> visitDivideExpr(DivideExprContext ctx) {
        val expr = new DivideExpr(pos(ctx), parent(ctx));
        expr.setLeft((Expr) visit(ctx.expr(0), expr).getPiece());
        expr.setRight((Expr) visit(ctx.expr(1), expr).getPiece());
        return of(expr);
    }

    @Override
    public VisitResult<?, ?> visitLiteralExpr(LiteralExprContext ctx) {
        if (ctx.INT() != null) {
            try {
                val value = Integer.parseInt(ctx.INT().getText());
                var valueType = BasicValueType.INT;
                if (value <= Byte.MAX_VALUE && value >= Byte.MIN_VALUE) {
                    valueType = BasicValueType.BYTE;
                } else if (value <= Short.MAX_VALUE && value >= Short.MIN_VALUE) {
                    valueType = BasicValueType.SHORT;
                }
                return of(new LiteralExpr(pos(ctx), parent(ctx), value, valueType));
            } catch (NumberFormatException e) {
                return of(new LiteralExpr(pos(ctx), parent(ctx), Long.parseLong(ctx.INT().getText()), BasicValueType.LONG));
            }
        } else if (ctx.DEC() != null) {
            val str = ctx.DEC().getText();
            if (str.length() <= 7) {
                return of(new LiteralExpr(pos(ctx), parent(ctx), Float.parseFloat(str), BasicValueType.FLOAT));
            } else {
                return of(new LiteralExpr(pos(ctx), parent(ctx), Double.parseDouble(str), BasicValueType.DOUBLE));
            }
        } else if (ctx.BOOL() != null) {
            if (ctx.BOOL().getText().equals("true")) {
                return of(new LiteralExpr(pos(ctx), parent(ctx), true, BasicValueType.BOOLEAN));
            } else {
                return of(new LiteralExpr(pos(ctx), parent(ctx), false, BasicValueType.BOOLEAN));
            }
        } else if (ctx.NULL() != null) {
            return of(new LiteralExpr(pos(ctx), parent(ctx), null, ValueType.from("java.lang.Object")));
        } else {
            return of(new LiteralExpr(pos(ctx), parent(ctx), ctx.STRING().getText(), BasicValueType.STRING));
        }
    }

    @Override
    public VisitResult<?, ?> visitChainVirtualFieldExpr(ChainVirtualFieldExprContext ctx) {
        return of(new FieldChainExpr(pos(ctx), parent(ctx), ctx.getText()));
    }

    @Override
    public VisitResult<?, ?> visitPositiveExpr(PositiveExprContext ctx) {
        return of(visit(ctx.expr(), parent(ctx)).getPiece());
    }

    @Override
    public VisitResult<?, ?> visitChainStaticFieldExpr(ChainStaticFieldExprContext ctx) {
        return of(new FieldChainExpr(pos(ctx), parent(ctx), ctx.getText()));
    }

    @Override
    public VisitResult<?, ?> visitIfElseStat(IfElseStatContext ctx) {
        val parent = parent(ctx);
        val ifElseStat = new IfElseStat(pos(ctx), parent);
        val exprs = ctx.expr().stream().map(each -> (Expr) visit(each, ifElseStat).getPiece()).toArray(Expr[]::new);
        val blocks = ctx.block().stream().map(each -> {
            val block = new PlainBlock(pos(each), ifElseStat, ifElseStat.findVariableStoreBelongTo());
            each.expr().forEach(exprContext -> block.addCodePiece(visit(exprContext, block).getPiece()));
            return block;
        }).toArray(Block[]::new);
        ifElseStat.setExprs(exprs);
        ifElseStat.setBlocks(blocks);
        return of(ifElseStat);
    }

    @Override
    public VisitResult<?, ?> visitWhileStat(WhileStatContext ctx) {
        val whileStat = new WhileStat(pos(ctx), parent(ctx), ctx.ID() == null ? "" : ctx.ID().getText());
        whileStat.setCondition((Expr) visit(ctx.expr(), whileStat).getPiece());
        val block = new PlainBlock(pos(ctx.block()), whileStat, whileStat.findVariableStoreBelongTo());
        for(val each : ctx.block().children){
            block.addCodePiece(visit(each, block).getPiece());
        }
        whileStat.setBlock(block);
        return of(whileStat);
    }

    @Override
    public VisitResult<?, ?> visitReturnStat(ReturnStatContext ctx) {
        val retStat = new ReturnStat(pos(ctx), parent(ctx));
        retStat.setExpr((Expr) visit(ctx.expr(), retStat).getPiece());
        return of(retStat);
    }

    @Override
    public VisitResult<?, ?> visitBreakStat(BreakStatContext ctx) {
        return of(new BreakLoopStat(pos(ctx), parent(ctx), ctx.ID() == null ? "" : ctx.ID().getText()));
    }

    @Override
    public VisitResult<?, ?> visitContinueStat(ContinueStatContext ctx) {
        return of(new ContinueLoopStat(pos(ctx), parent(ctx), ctx.ID() == null ? "" : ctx.ID().getText()));
    }

    @Override
    public VisitResult<?, ?> visitCommand(CommandContext ctx) {
        val argExprs = new Expr[ctx.children.size() - 1];
        val children = ctx.children;

        val commandIdCtx = ctx.commandId();
        if (commandIdCtx instanceof CallCommandContext) {
            val callCommandContext = (CallCommandContext) commandIdCtx;
            val expr = new MethodCallExpr(pos(callCommandContext), parent(ctx), callCommandContext.getText());
            for (int i = 0, childrenSize = children.size(); i < childrenSize; i++) {
                argExprs[i] = (Expr) visit(children.get(i), expr).getPiece();
            }
            expr.setArgs(argExprs);
            return of(expr);
        } else if (commandIdCtx instanceof InvokeCommandContext) {
            val invokeCommandContext = (InvokeCommandContext) commandIdCtx;
            val expr = new MethodInvokeExpr(pos(invokeCommandContext), parent(ctx), invokeCommandContext.getText());
            for (int i = 0, childrenSize = children.size(); i < childrenSize; i++) {
                argExprs[i] = (Expr) visit(children.get(i), expr).getPiece();
            }
            expr.setArgs(argExprs);
            return of(expr);
        } else {
            val virtualCommandContext = (VirtualCommandContext) commandIdCtx;
            val expr = new MethodInvokeExpr(pos(virtualCommandContext), parent(ctx), virtualCommandContext.getText());
            for (int i = 0, childrenSize = children.size(); i < childrenSize; i++) {
                argExprs[i] = (Expr) visit(children.get(i), expr).getPiece();
            }
            expr.setArgs(argExprs);
            return of(expr);
        }
    }

    @Override
    public VisitResult<?, ?> visitInvokeCommand(InvokeCommandContext ctx) {
        throw new ShouldNotReachHereException(pos(ctx), "");
    }

    @Override
    public VisitResult<?, ?> visitCallCommand(CallCommandContext ctx) {
        throw new ShouldNotReachHereException(pos(ctx), "");
    }

    @Override
    public VisitResult<?, ?> visitVirtualCommand(VirtualCommandContext ctx) {
        throw new ShouldNotReachHereException(pos(ctx), "");
    }

    /**
     * @param node parser语法节点
     * @return 该语法节点的源码位置对象
     */
    private SourcePos pos(ParserRuleContext node) {
        return new SourcePos(sourceName, node.getStart().getLine(), node.getStart().getCharPositionInLine());
    }

    /**
     * @param varName 带有$开头的变量名
     * @return 去掉了$开头的变量名
     */
    private String no$(String varName) {
        if (varName.startsWith("$")) return varName.substring(1);
        return varName;
    }

    /**
     * visit指定节点并提供解析完毕的父{@link Piece}
     *
     * @param tree   接下来要遍历的节点
     * @param parent 解析完毕的父{@link Piece}
     * @return 遍历结果
     */
    public final VisitResult<?, ?> visit(ParseTree tree, Piece parent) {
        parentMap.put(tree, parent);
        return super.visit(tree);
    }

    /**
     * 获取该节点的父{@link Piece}
     *
     * @param tree 节点
     * @return 父{@link Piece}
     */
    public final Piece parent(ParseTree tree) {
        return parentMap.get(tree);
    }
}
