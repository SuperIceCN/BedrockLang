package com.blocklynukkit.bedrockLang.compiler.ast.compile.impl.piece;

import com.blocklynukkit.bedrockLang.compiler.ast.compile.*;
import com.blocklynukkit.bedrockLang.compiler.ast.compile.gen.ReadVariableExprGenerator;
import com.blocklynukkit.bedrockLang.compiler.ast.util.SourcePos;

public final class ReadVariableExpr extends ExprBase {
    private final String varName;
    private VariableStore variableStore;
    private VariableRecord variable;

    public ReadVariableExpr(SourcePos sourcePos, Piece parent, String varName) {
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

    @Override
    public ExprCodeGenerator getCodeGenerator() {
        return new ReadVariableExprGenerator(this);
    }
}
