package com.blocklynukkit.bedrockLang.compiler.ast.compile.gen;

import com.blocklynukkit.bedrockLang.compiler.ast.compile.*;
import com.blocklynukkit.bedrockLang.compiler.ast.compile.impl.piece.DefineCommandBlock;
import com.blocklynukkit.bedrockLang.compiler.ast.compile.impl.piece.ReturnStat;
import com.blocklynukkit.bedrockLang.compiler.ast.compile.impl.type.BasicValueType;
import com.blocklynukkit.bedrockLang.compiler.ast.compile.type.TypeLookup;
import com.blocklynukkit.bedrockLang.compiler.ast.exception.InvalidReturnTypeException;
import com.blocklynukkit.bedrockLang.compiler.ast.util.RequireUtils;
import it.unimi.dsi.fastutil.ints.IntObjectPair;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Type;

import java.util.Arrays;

public final class UnitCommandGenerator implements StatCodeGenerator {
    private final DefineCommandBlock defineCommandBlock;

    public UnitCommandGenerator(DefineCommandBlock defineCommandBlock) {
        this.defineCommandBlock = defineCommandBlock;
    }

    @Override
    public Void generate(Unit unit) {
        final GenerateWithASM asmUnit = RequireUtils.requireASM(unit);
        final Command cmd = defineCommandBlock.declare();
        final ValueType rt = cmd.getReturnType();
        final TypeLookup typeLookup = asmUnit.getTypeLookup();
        final ClassWriter cw = asmUnit.getClassWriter();
        final MethodVisitor mv = cw.visitMethod(ACC_PUBLIC | ACC_STATIC, cmd.getMethodName(),
                Type.getMethodDescriptor(typeLookup.lookup(rt), Arrays.stream(cmd.getArgs())
                        .filter(CmdArg::hasValueType)
                        .map(cmdArg -> typeLookup.lookup(cmdArg.getValueType())).toArray(Type[]::new)), null, null);
        asmUnit.setCurrentMethodVisitor(mv);
        mv.visitCode();

        final Label label0 = new Label();
        mv.visitLabel(label0);
        mv.visitLineNumber(defineCommandBlock.getSourcePos().getLine(), label0);

        boolean returned = false;
        for (final Piece each : defineCommandBlock.getCodePieces()) {
            final Label tmpLabel = new Label();
            mv.visitLabel(tmpLabel);
            mv.visitLineNumber(each.getSourcePos().getLine(), tmpLabel);
            if (each instanceof ReturnStat) {
                if (((ReturnStat) each).getExpr().getReturnType().equals(this.defineCommandBlock.getReturnType())) {
                    returned = true;
                } else {
                    throw new InvalidReturnTypeException(defineCommandBlock.getSourcePos(), defineCommandBlock.declare().getName(), defineCommandBlock.getReturnType().getName(), ((ReturnStat) each).getExpr().getReturnType().getName());
                }
            }
            each.getCodeGenerator().generate(unit);
        }

        if (!returned) {
            final Label tmpLabel = new Label();
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

        final Label label1 = new Label();
        mv.visitLabel(label1);
        for (final IntObjectPair<Variable> each : cmd.getAllLocalVariables()) {
            final int id = each.leftInt();
            final Variable variable = each.right();
            mv.visitLocalVariable(variable.getName(), typeLookup.lookup(variable.getType()).getDescriptor(), null, label0, label1, id);
        }
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        asmUnit.clearCurrentMethodVisitor();
        return null;
    }
}
