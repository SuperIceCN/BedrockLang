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
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeProperty;
import org.antlr.v4.runtime.tree.TerminalNode;

import java.util.ArrayList;
import java.util.List;

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
        for (final ParseTree each : ctx.children) {
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
            final HasTypeVarDeclareContext varDeclare = (HasTypeVarDeclareContext) ctx;
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
        final DefineSignatureContext defSig = ctx.defineSignature();
        final ValueType returnType = ValueType.from(defSig.ID().size() > 1 ? defSig.ID(1).getText() : "void");
        final DefineCommandBlock defineCommandBlock = new DefineCommandBlock(pos(ctx), bdlUnit, defSig.ID(0).getText(), returnType,
                defSig.children.stream()
                        .filter(tree -> tree instanceof DefineSignatureVariableContext || tree instanceof DefineSignatureWordSingleContext)
                        .map(tree -> {
                            if (tree instanceof DefineSignatureVariableContext) {
                                final DefineSignatureVariableContext varCtx = (DefineSignatureVariableContext) tree;
                                return new VariableCmdArg(no$(varCtx.varid().getText()), ValueType.from(varCtx.ID().getText()), pos(varCtx));
                            } else {
                                final DefineSignatureWordSingleContext wordCtx = (DefineSignatureWordSingleContext) tree;
                                return new WordCmdArg(wordCtx.ID().getText(), pos(wordCtx));
                            }
                        }).toArray(CmdArg[]::new));
        //遍历命令内部内容
        for (final ParseTree each : ctx.block().children) {
            VisitResult<?, ?> tmp = visit(each, defineCommandBlock);
            if (tmp != null) {
                defineCommandBlock.addCodePiece(tmp.getPiece());
            }
        }
        return of(defineCommandBlock);
    }

    @Override
    public VisitResult<?, ?> visitHasTypeVarDeclare(HasTypeVarDeclareContext ctx) {
        final LocalVariableDeclareStat stat = new LocalVariableDeclareStat(pos(ctx), parent(ctx).findParent(Block.class), no$(ctx.varid().getText()));
        if (ctx.expr() != null) {
            stat.setInitExpr((Expr) visit(ctx.expr(), stat).getPiece());
        }
        stat.setType(ValueType.from(ctx.ID().getText()));
        return of(stat);
    }

    @Override
    public VisitResult<?, ?> visitInferTypeVarDeclare(InferTypeVarDeclareContext ctx) {
        final LocalVariableDeclareStat stat = new LocalVariableDeclareStat(pos(ctx),
                parent(ctx).findParent(Block.class),
                no$(ctx.varid().getText()));
        stat.setInitExpr((Expr) visit(ctx.expr(), stat).getPiece());
        return of(stat);
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
        final MinusExpr expr = new MinusExpr(pos(ctx), parent(ctx));
        expr.setLeft(new LiteralExpr(pos(ctx), expr, 0, ValueType.from("byte")));
        expr.setRight((Expr) visit(ctx.expr(), expr).getPiece());
        return of(expr);
    }

    @Override
    public VisitResult<?, ?> visitMultiplyExpr(MultiplyExprContext ctx) {
        final MultiplyExpr expr = new MultiplyExpr(pos(ctx), parent(ctx));
        expr.setLeft((Expr) visit(ctx.expr(0), expr).getPiece());
        expr.setRight((Expr) visit(ctx.expr(1), expr).getPiece());
        return of(expr);
    }

    @Override
    public VisitResult<?, ?> visitMinusExpr(MinusExprContext ctx) {
        final MinusExpr expr = new MinusExpr(pos(ctx), parent(ctx));
        expr.setLeft((Expr) visit(ctx.expr(0), expr).getPiece());
        expr.setRight((Expr) visit(ctx.expr(1), expr).getPiece());
        return of(expr);
    }

    @Override
    public VisitResult<?, ?> visitRemainExpr(RemainExprContext ctx) {
        final ModExpr expr = new ModExpr(pos(ctx), parent(ctx));
        expr.setLeft((Expr) visit(ctx.expr(0), expr).getPiece());
        expr.setRight((Expr) visit(ctx.expr(1), expr).getPiece());
        return of(expr);
    }

    @Override
    public VisitResult<?, ?> visitPlusExpr(PlusExprContext ctx) {
        final PlusExpr expr = new PlusExpr(pos(ctx), parent(ctx));
        expr.setLeft((Expr) visit(ctx.expr(0), expr).getPiece());
        expr.setRight((Expr) visit(ctx.expr(1), expr).getPiece());
        return of(expr);
    }

    @Override
    public VisitResult<?, ?> visitVarExpr(VarExprContext ctx) {
        return of(new ReadVariableExpr(pos(ctx), parent(ctx), no$(ctx.varid().getText())));
    }

    @Override
    public VisitResult<?, ?> visitSetVarExpr(SetVarExprContext ctx) {
        final WriteVariableExpr expr = new WriteVariableExpr(pos(ctx), parent(ctx), no$(ctx.varid().getText()));
        expr.setValueExpr((Expr) visit(ctx.expr(), expr).getPiece());
        return of(expr);
    }

    @Override
    public VisitResult<?, ?> visitDivideExpr(DivideExprContext ctx) {
        final DivideExpr expr = new DivideExpr(pos(ctx), parent(ctx));
        expr.setLeft((Expr) visit(ctx.expr(0), expr).getPiece());
        expr.setRight((Expr) visit(ctx.expr(1), expr).getPiece());
        return of(expr);
    }

    @Override
    public VisitResult<?, ?> visitLiteralExpr(LiteralExprContext ctx) {
        if (ctx.INT() != null) {
            try {
                final int value = Integer.parseInt(ctx.INT().getText());
                BasicValueType valueType = BasicValueType.INT;
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
            final String str = ctx.DEC().getText();
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
            final String str = ctx.STRING().getText();
            return of(new LiteralExpr(pos(ctx), parent(ctx), str.substring(1, str.length() - 1), BasicValueType.STRING));
        }
    }

    @Override
    public VisitResult<?, ?> visitLowerEqualExpr(LowerEqualExprContext ctx) {
        final LowerEqualExpr expr = new LowerEqualExpr(pos(ctx), parent(ctx));
        expr.setLeft((Expr) visit(ctx.expr(0), expr).getPiece());
        expr.setRight((Expr) visit(ctx.expr(1), expr).getPiece());
        return of(expr);
    }

    @Override
    public VisitResult<?, ?> visitGreaterEqualExpr(GreaterEqualExprContext ctx) {
        final GreaterEqualExpr expr = new GreaterEqualExpr(pos(ctx), parent(ctx));
        expr.setLeft((Expr) visit(ctx.expr(0), expr).getPiece());
        expr.setRight((Expr) visit(ctx.expr(1), expr).getPiece());
        return of(expr);
    }

    @Override
    public VisitResult<?, ?> visitGreaterExpr(GreaterExprContext ctx) {
        final GreaterExpr expr = new GreaterExpr(pos(ctx), parent(ctx));
        expr.setLeft((Expr) visit(ctx.expr(0), expr).getPiece());
        expr.setRight((Expr) visit(ctx.expr(1), expr).getPiece());
        return of(expr);
    }

    @Override
    public VisitResult<?, ?> visitLowerExpr(LowerExprContext ctx) {
        final LowerExpr expr = new LowerExpr(pos(ctx), parent(ctx));
        expr.setLeft((Expr) visit(ctx.expr(0), expr).getPiece());
        expr.setRight((Expr) visit(ctx.expr(1), expr).getPiece());
        return of(expr);
    }

    @Override
    public VisitResult<?, ?> visitNotEqualExpr(NotEqualExprContext ctx) {
        final NotEqualExpr expr = new NotEqualExpr(pos(ctx), parent(ctx));
        expr.setLeft((Expr) visit(ctx.expr(0), expr).getPiece());
        expr.setRight((Expr) visit(ctx.expr(1), expr).getPiece());
        return of(expr);
    }

    @Override
    public VisitResult<?, ?> visitEqualExpr(EqualExprContext ctx) {
        final EqualExpr expr = new EqualExpr(pos(ctx), parent(ctx));
        expr.setLeft((Expr) visit(ctx.expr(0), expr).getPiece());
        expr.setRight((Expr) visit(ctx.expr(1), expr).getPiece());
        return of(expr);
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
        final Piece parent = parent(ctx);
        final IfElseStat ifElseStat = new IfElseStat(pos(ctx), parent);
        final Expr[] exprs = ctx.expr().stream().map(each -> (Expr) visit(each, ifElseStat).getPiece()).toArray(Expr[]::new);
        final Block[] blocks = ctx.block().stream().map(each -> {
            final PlainBlock block = new PlainBlock(pos(each), ifElseStat, ifElseStat.findVariableStoreBelongTo());
            each.children.forEach(exprContext -> {
                VisitResult<?, ?> tmp = visit(exprContext, block);
                if (tmp != null) {
                    block.addCodePiece(tmp.getPiece());
                }
            });
            return block;
        }).toArray(Block[]::new);
        ifElseStat.setExprs(exprs);
        ifElseStat.setBlocks(blocks);
        return of(ifElseStat);
    }

    @Override
    public VisitResult<?, ?> visitWhileStat(WhileStatContext ctx) {
        final WhileStat whileStat = new WhileStat(pos(ctx), parent(ctx), ctx.ID() == null ? "" : ctx.ID().getText());
        whileStat.setCondition((Expr) visit(ctx.expr(), whileStat).getPiece());
        final PlainBlock block = new PlainBlock(pos(ctx.block()), whileStat, whileStat.findVariableStoreBelongTo());
        for (final ParseTree each : ctx.block().children) {
            VisitResult<?, ?> tmp = visit(each, block);
            if (tmp != null) {
                block.addCodePiece(tmp.getPiece());
            }
        }
        whileStat.setBlock(block);
        return of(whileStat);
    }

    @Override
    public VisitResult<?, ?> visitReturnStat(ReturnStatContext ctx) {
        final ReturnStat retStat = new ReturnStat(pos(ctx), parent(ctx));
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
        final Expr[] argExprs = new Expr[ctx.children.size() - 1];
        final List<ParseTree> children = new ArrayList<>(ctx.children);
        children.remove(0);

        final CommandIdContext commandIdCtx = ctx.commandId();
        if (commandIdCtx instanceof CallCommandContext) {
            final CallCommandContext callCommandContext = (CallCommandContext) commandIdCtx;
            final MethodCallExpr expr = new MethodCallExpr(pos(callCommandContext), parent(ctx), callCommandContext.getText());
            for (int i = 0, childrenSize = children.size(); i < childrenSize; i++) {
                argExprs[i] = (Expr) visit(children.get(i), expr).getPiece();
            }
            expr.setArgs(argExprs);
            return of(expr);
        } else if (commandIdCtx instanceof InvokeCommandContext) {
            final InvokeCommandContext invokeCommandContext = (InvokeCommandContext) commandIdCtx;
            final MethodInvokeExpr expr = new MethodInvokeExpr(pos(invokeCommandContext), parent(ctx), invokeCommandContext.getText());
            for (int i = 0, childrenSize = children.size(); i < childrenSize; i++) {
                argExprs[i] = (Expr) visit(children.get(i), expr).getPiece();
            }
            expr.setArgs(argExprs);
            return of(expr);
        } else {
            final VirtualCommandContext virtualCommandContext = (VirtualCommandContext) commandIdCtx;
            final MethodInvokeExpr expr = new MethodInvokeExpr(pos(virtualCommandContext), parent(ctx), virtualCommandContext.getText());
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

    @Override
    public VisitResult<?, ?> visitStat(StatContext ctx) {
        Piece parent = parent(ctx);
        ctx.children.removeIf(tree -> tree instanceof TerminalNode && tree.getText().equals(";"));
        ctx.children.forEach(tree -> parentMap.put(tree, parent));
        return super.visitStat(ctx);
    }

    @Override
    public VisitResult<?, ?> visitWhenStat(WhenStatContext ctx) {
        throw new NotImplementedException(pos(ctx), "When grammar is not available now. ");
    }

    @Override
    public VisitResult<?, ?> visitDefineSignature(DefineSignatureContext ctx) {
        throw new ShouldNotReachHereException(pos(ctx), "");
    }

    @Override
    public VisitResult<?, ?> visitDefineSignatureWordSingle(DefineSignatureWordSingleContext ctx) {
        throw new ShouldNotReachHereException(pos(ctx), "");
    }

    @Override
    public VisitResult<?, ?> visitDefineSignatureVariable(DefineSignatureVariableContext ctx) {
        throw new ShouldNotReachHereException(pos(ctx), "");
    }

    @Override
    public VisitResult<?, ?> visitBlock(BlockContext ctx) {
        throw new ShouldNotReachHereException(pos(ctx), "");
    }

    @Override
    public VisitResult<?, ?> visitId(IdContext ctx) {
        throw new ShouldNotReachHereException(pos(ctx), "");
    }

    @Override
    public VisitResult<?, ?> visitVarid(VaridContext ctx) {
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
