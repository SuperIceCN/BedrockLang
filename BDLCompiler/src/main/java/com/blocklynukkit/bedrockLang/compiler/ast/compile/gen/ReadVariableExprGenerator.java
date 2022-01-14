package com.blocklynukkit.bedrockLang.compiler.ast.compile.gen;

import com.blocklynukkit.bedrockLang.compiler.ast.compile.ExprCodeGenerator;
import com.blocklynukkit.bedrockLang.compiler.ast.compile.Unit;
import com.blocklynukkit.bedrockLang.compiler.ast.compile.ValueType;
import com.blocklynukkit.bedrockLang.compiler.ast.compile.VariableRecord;
import com.blocklynukkit.bedrockLang.compiler.ast.compile.impl.piece.ReadVariableExpr;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.val;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import static com.blocklynukkit.bedrockLang.compiler.ast.util.RequireUtils.requireASM;

@RequiredArgsConstructor
public final class ReadVariableExprGenerator implements ExprCodeGenerator {
    private final ReadVariableExpr expr;

    @Override
    public ValueType generate(Unit unit) {
        val asmUnit = requireASM(unit);
        @NonNull
        val mv = asmUnit.getCurrentMethodVisitor();
        val lookup = asmUnit.getTypeLookup();
        val variable = expr.getVariable();
        if(variable.getType() == VariableRecord.VariableType.LOCAL){
            mv.visitVarInsn(lookup.lookup(variable.getVariable().getType()).getOpcode(ILOAD), variable.getIndex());
        }else if(variable.getType() == VariableRecord.VariableType.UNIT_GLOBAL){
            mv.visitFieldInsn(GETSTATIC, asmUnit.getTypeLookup().lookup(unit).getInternalName(), variable.getVariable().getName()
                    , asmUnit.getTypeLookup().lookup(variable.getVariable().getType()).getDescriptor());
        }else {
            // TODO: 2022/1/3 完成外部域导入
            throw new NotImplementedException();
        }
        return variable.getVariable().getType();
    }
}
