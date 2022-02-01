package com.blocklynukkit.bedrockLang.compiler.ast.compile.impl.piece;

import com.blocklynukkit.bedrockLang.compiler.ast.compile.*;
import com.blocklynukkit.bedrockLang.compiler.ast.compile.gen.WriteVariableExprGenerator;
import com.blocklynukkit.bedrockLang.compiler.ast.compile.impl.type.ArrayValueType;
import com.blocklynukkit.bedrockLang.compiler.ast.exception.InvalidClassException;
import com.blocklynukkit.bedrockLang.compiler.ast.util.SourcePos;

public final class WriteVariableExpr extends ExprBase {

    private final String varName;
    private VariableStore variableStore;
    private VariableRecord variable;
    private Expr[] indexExprs = null; //当为数组元素赋值时此表达式不为null

    private Expr valueExpr;

    public WriteVariableExpr(SourcePos sourcePos, Piece parent, String varName) {
        super(sourcePos, parent);
        this.varName = varName;
    }

    private void init() {
        if (variableStore == null || variable == null) {
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

    public Expr[] getIndexExpr() {
        return indexExprs;
    }

    public void setIndexExpr(Expr[] indexExprs) {
        this.indexExprs = indexExprs;
    }

    @Override
    public ValueType getReturnType() {
        init();
        ValueType valueType = variable.getVariable().getType();
        if (isForArray()) {
            if (valueType.isArray()) {
                while (valueType.isArray()) {
                    valueType = ((ArrayValueType) valueType).toSingleType();
                }
                return valueType;
            }
            throw new InvalidClassException(this.getSourcePos(), "set array elements", "array");
        } else {
            return valueType;
        }
    }

    public Expr getValueExpr() {
        init();
        return valueExpr;
    }

    public String getVarName() {
        return varName;
    }

    public boolean isForArray() {
        return this.indexExprs != null && this.indexExprs.length != 0;
    }

    @Override
    public ExprCodeGenerator getCodeGenerator() {
        return new WriteVariableExprGenerator(this);
    }

    public void setValueExpr(Expr valueExpr) {
        this.valueExpr = valueExpr;
    }
}
