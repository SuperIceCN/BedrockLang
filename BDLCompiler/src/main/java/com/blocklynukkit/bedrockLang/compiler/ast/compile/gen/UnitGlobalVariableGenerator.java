package com.blocklynukkit.bedrockLang.compiler.ast.compile.gen;

import com.blocklynukkit.bedrockLang.compiler.ast.compile.GenerateWithASM;
import com.blocklynukkit.bedrockLang.compiler.ast.compile.StatCodeGenerator;
import com.blocklynukkit.bedrockLang.compiler.ast.compile.Unit;
import com.blocklynukkit.bedrockLang.compiler.ast.compile.Variable;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.FieldVisitor;

public final class UnitGlobalVariableGenerator implements StatCodeGenerator {
    public final Variable variable;

    public UnitGlobalVariableGenerator(Variable variable) {
        this.variable = variable;
    }

    @Override
    public Void generate(Unit unit) {
        if(unit instanceof GenerateWithASM){
            final GenerateWithASM asmUnit = (GenerateWithASM) unit;
            final ClassWriter cw = asmUnit.getClassWriter();
            final FieldVisitor fv = cw.visitField(ACC_PUBLIC | ACC_STATIC, variable.getName(), asmUnit.getTypeLookup().lookup(variable.getType()).getDescriptor(), null, null);
            fv.visitEnd();
        }
        return null;
    }
}
