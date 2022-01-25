package com.blocklynukkit.bedrockLang.compiler.ast.compile.type;

import com.blocklynukkit.bedrockLang.compiler.ast.compile.impl.variable.UnitGlobalVariable;
import org.objectweb.asm.Type;

import java.lang.reflect.Modifier;

public final class BDLFieldInfo extends FieldInfo{
    private final UnitGlobalVariable variable;
    private final TypeLookup lookup;

    public BDLFieldInfo(UnitGlobalVariable variable, TypeLookup lookup) {
        this.variable = variable;
        this.lookup = lookup;
    }

    @Override
    public String getName() {
        return variable.getName();
    }

    @Override
    public ClassInfo getClassType() {
        return lookup.lookupClass(variable.getType());
    }

    @Override
    public Type getASMType() {
        return lookup.lookup(variable.getType());
    }

    @Override
    public int getModifier() {
        return Modifier.PUBLIC | Modifier.STATIC;
    }
}
