package com.blocklynukkit.bedrockLang.compiler.ast.compile.gen;

import com.blocklynukkit.bedrockLang.compiler.ast.compile.*;
import lombok.RequiredArgsConstructor;
import lombok.val;

@RequiredArgsConstructor
public final class UnitGlobalVariableGenerator implements StatCodeGenerator {
    public final Variable variable;

    @Override
    public Void generate(Unit unit) {
        if(unit instanceof GenerateWithASM){
            val asmUnit = (GenerateWithASM)unit;
            val cw = asmUnit.getClassWriter();
            val fv = cw.visitField(ACC_PUBLIC | ACC_STATIC, variable.getName(), asmUnit.getTypeLookup().lookup(variable.getType()).getDescriptor(), null, null);
            fv.visitEnd();
        }
        return null;
    }
}
