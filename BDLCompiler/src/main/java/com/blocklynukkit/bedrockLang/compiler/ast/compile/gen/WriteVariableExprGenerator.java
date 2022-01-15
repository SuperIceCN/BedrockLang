package com.blocklynukkit.bedrockLang.compiler.ast.compile.gen;

import com.blocklynukkit.bedrockLang.compiler.ast.compile.ExprCodeGenerator;
import com.blocklynukkit.bedrockLang.compiler.ast.compile.Unit;
import com.blocklynukkit.bedrockLang.compiler.ast.compile.ValueType;
import com.blocklynukkit.bedrockLang.compiler.ast.compile.VariableRecord;
import com.blocklynukkit.bedrockLang.compiler.ast.compile.impl.piece.WriteVariableExpr;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.val;

import static com.blocklynukkit.bedrockLang.compiler.ast.util.RequireUtils.requireASM;

@RequiredArgsConstructor
public final class WriteVariableExprGenerator implements ExprCodeGenerator {
    private final WriteVariableExpr expr;

    @Override
    public ValueType generate(Unit unit) {
        val asmUnit = requireASM(unit);
        //先生成值的表达式
        expr.getValueExpr().getCodeGenerator().generate(unit);
        //此时值应该位于栈顶
        @NonNull
        val mv = asmUnit.getCurrentMethodVisitor();
        val lookup = asmUnit.getTypeLookup();
        val variable = expr.getVariable();
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
