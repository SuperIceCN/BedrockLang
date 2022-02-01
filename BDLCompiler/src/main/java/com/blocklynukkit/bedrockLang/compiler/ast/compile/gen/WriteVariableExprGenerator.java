package com.blocklynukkit.bedrockLang.compiler.ast.compile.gen;

import com.blocklynukkit.bedrockLang.compiler.ast.compile.*;
import com.blocklynukkit.bedrockLang.compiler.ast.compile.impl.piece.ReadVariableExpr;
import com.blocklynukkit.bedrockLang.compiler.ast.compile.impl.piece.WriteVariableExpr;
import com.blocklynukkit.bedrockLang.compiler.ast.compile.impl.type.BasicValueType;
import com.blocklynukkit.bedrockLang.compiler.ast.compile.type.TypeLookup;
import org.objectweb.asm.MethodVisitor;

import static com.blocklynukkit.bedrockLang.compiler.ast.compile.impl.type.BasicValueType.*;
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
        ValueType valueType = valueExpr.getReturnType(); // 被赋值的值的类型
        valueExpr.getCodeGenerator().generate(unit);
        /*
         * 允许连续赋值
         * 当父表达式同样为赋值表达式的时候，就将此时的值表达式复制一份，这样当前表达式用掉一份，
         * 剩下的一份将会留在栈顶供父赋值表达式使用
         */
        if (expr.getPieceParent() instanceof WriteVariableExpr) {
            dup(mv, valueExpr.getReturnType());
        }
        // 转换基本值的类型
        if (expr.getReturnType().isBasic()) {
            valueType = new BasicCastGenerator(expr.getValueExpr().getReturnType(), expr.getReturnType()).generate(unit);
        }
        // 此时值应该位于栈顶
        final TypeLookup lookup = asmUnit.getTypeLookup();
        final VariableRecord variable = expr.getVariable();
        final ValueType varType = variable.getVariable().getType();
        if (variable.getType() == VariableRecord.VariableType.LOCAL) {
            if (expr.isForArray()) { // 数组读取
                // 获取多级数组索引
                final Expr[] indexExprs = expr.getIndexExpr();
                // 先把根数组对象读取到栈上
                final ValueType arrayType = new ReadVariableExpr(this.expr.getSourcePos(), this.expr, variable.getVariable().getName()).getCodeGenerator().generate(unit);
                for (int i = 0, length = indexExprs.length; i < length; i++) {
                    if (i != length - 1) {
                        // 如果不是最后一层索引，就把索引生成到栈上然后读取上一层数组元素
                        indexExprs[i].getCodeGenerator().generate(unit);
                        mv.visitInsn(AALOAD);
                    } else {
                        // 如果是最后一层，就把当前栈上次栈顶的值和栈顶的数组引用交换
                        // 然后在栈顶生成索引值，最后交换次栈顶的值和栈顶的索引值交换
                        swap(mv, valueType, arrayType);
                        final ValueType indexType = indexExprs[i].getCodeGenerator().generate(unit);
                        swap(mv, valueType, indexType);
                        // 现在生成把值存入数组的字节码
                        if (valueType == BOOLEAN || valueType == BYTE) {
                            mv.visitInsn(BASTORE);
                        } else if (valueType == SHORT) {
                            mv.visitInsn(SASTORE);
                        } else if (valueType == CHAR) {
                            mv.visitInsn(CASTORE);
                        } else if (valueType == INT) {
                            mv.visitInsn(IASTORE);
                        } else if (valueType == BasicValueType.LONG) {
                            mv.visitInsn(LASTORE);
                        } else if (valueType == BasicValueType.FLOAT) {
                            mv.visitInsn(FASTORE);
                        } else if (valueType == BasicValueType.DOUBLE) {
                            mv.visitInsn(DASTORE);
                        } else {
                            mv.visitInsn(AASTORE);
                        }
                    }
                }
            } else {
                mv.visitVarInsn(lookup.lookup(varType).getOpcode(ISTORE), variable.getIndex());
            }
        } else if (variable.getType() == VariableRecord.VariableType.UNIT_GLOBAL) {
            mv.visitFieldInsn(PUTSTATIC, asmUnit.getTypeLookup().lookup(unit).getInternalName(), variable.getVariable().getName()
                    , asmUnit.getTypeLookup().lookup(varType).getDescriptor());
        } else {
            // TODO: 2022/1/3 完成外部域静态变量修改
        }
        return variable.getVariable().getType();
    }
}
