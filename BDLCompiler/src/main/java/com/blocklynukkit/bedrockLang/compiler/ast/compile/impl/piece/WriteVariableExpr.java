package com.blocklynukkit.bedrockLang.compiler.ast.compile.impl.piece;

import com.blocklynukkit.bedrockLang.compiler.ast.compile.*;
import com.blocklynukkit.bedrockLang.compiler.ast.compile.gen.ReadVariableExprGenerator;
import com.blocklynukkit.bedrockLang.compiler.ast.compile.gen.WriteVariableExprGenerator;
import com.blocklynukkit.bedrockLang.compiler.ast.util.SourcePos;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

public final class WriteVariableExpr extends ExprBase {
    @NonNull
    private final String varName;
    private VariableStore variableStore;
    private VariableRecord variable;
    @NonNull
    @Setter
    private Expr valueExpr;

    public WriteVariableExpr(@NonNull SourcePos sourcePos, @NonNull Piece parent, @NonNull String varName) {
        super(sourcePos, parent);
        this.varName = varName;
    }

    private void init() {
        if(variableStore == null || variable == null){
            this.variableStore = getPieceParent().findVariableStoreBelongTo();
            this.variable = variableStore.findVariable(varName);
        }
    }

    public VariableStore getVariableStore() {
        init();
        return variableStore;
    }

    public VariableRecord getVariable() {
        init();
        return variable;
    }

    @Override
    public ValueType getReturnType() {
        init();
        return variable.getVariable().getType();
    }

    public Expr getValueExpr() {
        init();
        return valueExpr;
    }

    @Override
    public ExprCodeGenerator getCodeGenerator() {
        return new WriteVariableExprGenerator(this);
    }
}
