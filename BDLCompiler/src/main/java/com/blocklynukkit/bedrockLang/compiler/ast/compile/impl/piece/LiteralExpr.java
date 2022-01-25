package com.blocklynukkit.bedrockLang.compiler.ast.compile.impl.piece;

import com.blocklynukkit.bedrockLang.compiler.ast.compile.ExprBase;
import com.blocklynukkit.bedrockLang.compiler.ast.compile.ExprCodeGenerator;
import com.blocklynukkit.bedrockLang.compiler.ast.compile.Piece;
import com.blocklynukkit.bedrockLang.compiler.ast.compile.ValueType;
import com.blocklynukkit.bedrockLang.compiler.ast.compile.gen.LiteralExprGenerator;
import com.blocklynukkit.bedrockLang.compiler.ast.util.SourcePos;

public final class LiteralExpr extends ExprBase {
    private final Object value;
    private final ValueType valueType;

    public LiteralExpr(SourcePos sourcePos, Piece parent, Object value, ValueType valueType) {
        super(sourcePos, parent);
        this.value = value;
        this.valueType = ValueType.fromObj(value);
        if (!valueType.isBasic()) {
            throw new IllegalArgumentException("Only basic types are allowed!");
        }
    }

    @Override
    public ValueType getReturnType() {
        return valueType;
    }

    @Override
    public ExprCodeGenerator getCodeGenerator() {
        return new LiteralExprGenerator(this);
    }

    public Object getValue() {
        return this.value;
    }
}
