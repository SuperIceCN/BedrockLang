package com.blocklynukkit.bedrockLang.compiler.ast.compile.impl.piece;

import com.blocklynukkit.bedrockLang.compiler.ast.compile.*;
import com.blocklynukkit.bedrockLang.compiler.ast.compile.gen.LocalVariableGenerator;
import com.blocklynukkit.bedrockLang.compiler.ast.compile.impl.variable.LocalVariable;
import com.blocklynukkit.bedrockLang.compiler.ast.exception.InvalidVariableTypeException;
import com.blocklynukkit.bedrockLang.compiler.ast.util.SourcePos;

import static com.blocklynukkit.bedrockLang.compiler.ast.util.NullUtils.Ok;

public final class LocalVariableDeclareStat extends StatBase implements Declaration<LocalVariable> {
    private final String variableName;
    private ValueType type = null;
    private Expr initExpr = null;
    private LocalVariable variable = null;

    public LocalVariableDeclareStat(SourcePos sourcePos, Block parent, String variableName) {
        super(sourcePos, parent);
        this.variableName = variableName;
    }

    public ValueType getType() {
        return type;
    }

    public void setType(ValueType type) {
        this.type = type;
    }

    public Expr getInitExpr() {
        return initExpr;
    }

    public void setInitExpr(Expr initExpr) {
        this.initExpr = initExpr;
        if (initExpr.hasReturnType()) {
            this.type = initExpr.getReturnType();
        }
        checkType();
    }

    @Override
    public CodeGenerator<?> getCodeGenerator() {
        checkType();
        return new LocalVariableGenerator(this);
    }

    @Override
    public LocalVariable declare() {
        checkType();
        return variable = Ok(variable, new LocalVariable(variableName, type, getSourcePos()));
    }

    private void checkType() {
        if (this.type == null) {
            throw new InvalidVariableTypeException(this.getSourcePos(), this.variableName);
        } else if (this.initExpr != null && !this.type.equals(initExpr.getReturnType())) {
            throw new InvalidVariableTypeException(this.getSourcePos(), this.variableName, this.type, this.initExpr.getReturnType());
        }
    }
}
