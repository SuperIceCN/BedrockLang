package com.blocklynukkit.bedrockLang.compiler.ast.compile.gen;

import com.blocklynukkit.bedrockLang.compiler.ast.compile.*;
import com.blocklynukkit.bedrockLang.compiler.ast.compile.gen.unfinished.IfElseUnfinishedGoto;
import com.blocklynukkit.bedrockLang.compiler.ast.compile.gen.unfinished.WhileUnfinishedJump;
import com.blocklynukkit.bedrockLang.compiler.ast.compile.impl.piece.ReturnStat;
import com.blocklynukkit.bedrockLang.compiler.ast.compile.impl.type.BasicValueType;
import com.blocklynukkit.bedrockLang.compiler.ast.compile.impl.piece.DefineCommandBlock;
import com.blocklynukkit.bedrockLang.compiler.ast.exception.InvalidReturnTypeException;
import com.blocklynukkit.bedrockLang.compiler.ast.util.RequireUtils;
import lombok.RequiredArgsConstructor;
import lombok.val;
import lombok.var;
import org.objectweb.asm.Label;
import org.objectweb.asm.Type;

import java.util.Arrays;

@RequiredArgsConstructor
public final class UnitCommandGenerator implements StatCodeGenerator {
    private final DefineCommandBlock defineCommandBlock;

    @Override
    public Void generate(Unit unit) {
        val asmUnit = RequireUtils.requireASM(unit);
        val cmd = defineCommandBlock.declare();
        val rt = cmd.getReturnType();
        val typeLookup = asmUnit.getTypeLookup();
        val cw = asmUnit.getClassWriter();
        val mv = cw.visitMethod(ACC_PUBLIC | ACC_STATIC, cmd.getMethodName(),
                Type.getMethodDescriptor(typeLookup.lookup(rt), Arrays.stream(cmd.getArgs())
                        .filter(CmdArg::hasValueType)
                        .map(cmdArg -> typeLookup.lookup(cmdArg.getValueType())).toArray(Type[]::new)), null, null);
        asmUnit.setCurrentMethodVisitor(mv);
        mv.visitCode();

        val label0 = new Label();
        mv.visitLabel(label0);
        mv.visitLineNumber(defineCommandBlock.getSourcePos().getLine(), label0);

        var returned = false;
        //上一次生成未完成的跳转标签，目前用于ifelse和while执行完毕后的终末跳转
        UnfinishedGen<Label, ?> unfinishedLabel = null;
        for (val each : defineCommandBlock.getCodePieces()) {
            val tmpLabel = new Label();
            if (unfinishedLabel != null) { //如果上一次代码最后坠着一个悬空goto，就让他跳转到当前label
                unfinishedLabel.offer(tmpLabel).generate(unit);
            }
            mv.visitLabel(tmpLabel);
            mv.visitLineNumber(each.getSourcePos().getLine(), tmpLabel);
            if (each instanceof ReturnStat) {
                if (((ReturnStat) each).getExpr().getReturnType().equals(this.defineCommandBlock.getReturnType())) {
                    returned = true;
                } else {
                    throw new InvalidReturnTypeException(defineCommandBlock.getSourcePos(), defineCommandBlock.declare().getName(), defineCommandBlock.getReturnType().getName(), ((ReturnStat) each).getExpr().getReturnType().getName());
                }
            }
            val genResult = each.getCodeGenerator().generate(unit);
            if (genResult != null) {
                if (genResult instanceof IfElseUnfinishedGoto) {
                    unfinishedLabel = (IfElseUnfinishedGoto) genResult;
                } else if (genResult instanceof WhileUnfinishedJump) {
                    unfinishedLabel = (WhileUnfinishedJump) genResult;
                }
            }
        }

        if (!returned) {
            val tmpLabel = new Label();
            if (unfinishedLabel != null) { //如果上一次代码最后坠着一个悬空goto，就让他跳转到函数末尾自动添加的返回
                unfinishedLabel.offer(tmpLabel).generate(unit);
            }
            mv.visitLabel(tmpLabel);
            if (rt != BasicValueType.VOID) {
                if (rt.isBasic()) {
                    switch (rt.getName()) {
                        case "byte":
                        case "short":
                        case "int":
                        case "char":
                        case "boolean":
                            mv.visitInsn(ICONST_0);
                            mv.visitInsn(IRETURN);
                            break;
                        case "long":
                            mv.visitInsn(LCONST_0);
                            mv.visitInsn(LRETURN);
                            break;
                        case "float":
                            mv.visitInsn(FCONST_0);
                            mv.visitInsn(FRETURN);
                            break;
                        case "double":
                            mv.visitInsn(DCONST_0);
                            mv.visitInsn(DRETURN);
                            break;
                        case "java.lang.String":
                            mv.visitInsn(ACONST_NULL);
                            mv.visitInsn(ARETURN);
                            break;
                    }
                } else {
                    mv.visitInsn(ACONST_NULL);
                    mv.visitInsn(ARETURN);
                }
            } else {
                mv.visitInsn(RETURN);
            }
        }

        val label1 = new Label();
        mv.visitLabel(label1);
        for (val each : cmd.getAllLocalVariables()) {
            val id = each.leftInt();
            val variable = each.right();
            mv.visitLocalVariable(variable.getName(), typeLookup.lookup(variable.getType()).getDescriptor(), null, label0, label1, id);
        }
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        asmUnit.clearCurrentMethodVisitor();
        return null;
    }
}
