package com.blocklynukkit.bedrockLang.compiler.ast.compile.gen;

import com.blocklynukkit.bedrockLang.compiler.ast.compile.*;
import com.blocklynukkit.bedrockLang.compiler.ast.compile.impl.piece.ReadVariableExpr;
import com.blocklynukkit.bedrockLang.compiler.ast.compile.impl.piece.WriteVariableExpr;
import com.blocklynukkit.bedrockLang.compiler.ast.compile.type.TypeLookup;
import org.objectweb.asm.MethodVisitor;

import static com.blocklynukkit.bedrockLang.compiler.ast.util.RequireUtils.requireASM;

public final class WriteVariableExprGenerator implements ExprCodeGenerator {
    final WriteVariableExpr expr;

    public WriteVariableExprGenerator(WriteVariableExpr expr) {
        this.expr = expr;
    }

    @Override
    public ValueType generate(Unit unit) {
        final GenerateWithASM asmUnit = requireASM(unit);
        //先生成值的表达式
        final Expr valueExpr = expr.getValueExpr();
        valueExpr.getCodeGenerator().generate(unit);
        if (valueExpr instanceof WriteVariableExpr) { // 允许连续赋值
            new ReadVariableExpr(expr.getSourcePos(), expr, ((WriteVariableExpr) valueExpr).getVarName()).getCodeGenerator().generate(unit);
        }
        //转换基本值的类型
        if (expr.getReturnType().isBasic()) {
            new BasicCastGenerator(expr.getValueExpr().getReturnType(), expr.getReturnType()).generate(unit);
        }
        //此时值应该位于栈顶
        final MethodVisitor mv = asmUnit.getCurrentMethodVisitor();
        final TypeLookup lookup = asmUnit.getTypeLookup();
        final VariableRecord variable = expr.getVariable();
        if (variable.getType() == VariableRecord.VariableType.LOCAL) {
            mv.visitVarInsn(lookup.lookup(variable.getVariable().getType()).getOpcode(ISTORE), variable.getIndex());
        } else if (variable.getType() == VariableRecord.VariableType.UNIT_GLOBAL) {
            mv.visitFieldInsn(PUTSTATIC, asmUnit.getTypeLookup().lookup(unit).getInternalName(), variable.getVariable().getName()
                    , asmUnit.getTypeLookup().lookup(variable.getVariable().getType()).getDescriptor());
        } else {
            // TODO: 2022/1/3 完成外部域静态变量修改
        }
        return variable.getVariable().getType();
    }
}
