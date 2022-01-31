package com.blocklynukkit.bedrockLang.compiler.ast.compile.gen;

import com.blocklynukkit.bedrockLang.compiler.ast.compile.*;
import com.blocklynukkit.bedrockLang.compiler.ast.compile.impl.piece.WriteVariableExpr;
import com.blocklynukkit.bedrockLang.compiler.ast.compile.type.TypeLookup;
import org.objectweb.asm.MethodVisitor;

import static com.blocklynukkit.bedrockLang.compiler.ast.util.RequireUtils.requireASM;

public final class WriteVariableExprGenerator implements ExprCodeGenerator, StackHelper {
    final WriteVariableExpr expr;

    public WriteVariableExprGenerator(WriteVariableExpr expr) {
        this.expr = expr;
    }

    @Override
    public ValueType generate(Unit unit) {
        final GenerateWithASM asmUnit = requireASM(unit);
        final MethodVisitor mv = asmUnit.getCurrentMethodVisitor();
        //先生成值的表达式
        final Expr valueExpr = expr.getValueExpr();
        valueExpr.getCodeGenerator().generate(unit);
        /*
         * 允许连续赋值
         * 当父表达式同样为赋值表达式的时候，就将此时的值表达式复制一份，这样当前表达式用掉一份，
         * 剩下的一份将会留在栈顶供父赋值表达式使用
         */
        if(expr.getPieceParent() instanceof WriteVariableExpr) {
            dup(mv, valueExpr.getReturnType());
        }
        //转换基本值的类型
        if (expr.getReturnType().isBasic()) {
            new BasicCastGenerator(expr.getValueExpr().getReturnType(), expr.getReturnType()).generate(unit);
        }
        //此时值应该位于栈顶
        final TypeLookup lookup = asmUnit.getTypeLookup();
        final VariableRecord variable = expr.getVariable();
        final ValueType valueType = variable.getVariable().getType();
        if (variable.getType() == VariableRecord.VariableType.LOCAL) {
            mv.visitVarInsn(lookup.lookup(valueType).getOpcode(ISTORE), variable.getIndex());
        } else if (variable.getType() == VariableRecord.VariableType.UNIT_GLOBAL) {
            mv.visitFieldInsn(PUTSTATIC, asmUnit.getTypeLookup().lookup(unit).getInternalName(), variable.getVariable().getName()
                    , asmUnit.getTypeLookup().lookup(valueType).getDescriptor());
        } else {
            // TODO: 2022/1/3 完成外部域静态变量修改
        }
        return variable.getVariable().getType();
    }
}
