package com.blocklynukkit.bedrockLang.compiler.ast.compile.impl.piece;

import com.blocklynukkit.bedrockLang.compiler.ast.compile.*;
import com.blocklynukkit.bedrockLang.compiler.ast.compile.gen.MethodCallExprGenerator;
import com.blocklynukkit.bedrockLang.compiler.ast.compile.impl.unit.BDLUnit;
import com.blocklynukkit.bedrockLang.compiler.ast.compile.type.MethodInfo;
import com.blocklynukkit.bedrockLang.compiler.ast.exception.MethodNotFoundException;
import com.blocklynukkit.bedrockLang.compiler.ast.util.SourcePos;
import lombok.*;

import java.util.Arrays;

/**
 * MethodCallExpr是针对静态方法调用的表达式，与MethodInvokeExpr相似，都允许以命令的形式调用方法。
 * 明显不同的是，Call针对当前类中声明的静态方法和导入的静态方法的调用。
 * <br/>
 * <br/>
 * 提示：MethodInvokeExpr不能用于调用在同一{@link Unit}中定义的命令，不能调用{@link ImportStat}导入的静态方法
 * @see MethodCallExprGenerator
 * @see MethodInvokeExpr
 */
public final class MethodCallExpr extends ExprBase {
    @NonNull
    @Getter
    private final String commandName;
    @Getter
    @Setter
    @NonNull
    private Expr[] args;
    private MethodInfo method = null;

    public MethodCallExpr(@NonNull SourcePos sourcePos, @NonNull Piece parent, @NonNull String commandName, Expr... args) {
        super(sourcePos, parent);
        this.commandName = commandName;
        if (args != null) {
            this.args = args;
        } else {
            this.args = new Expr[0];
        }
    }

    private void init() {
        if (method == null) {
            // TODO: 2022/1/12 重构架构，避免在不生成代码的时候使用unit
            val unit = this.findParent(BDLUnit.class);
            val sb = new StringBuilder(commandName);
            var firstWord = true;
            for (val each : args) {
                if (each instanceof CommandArgWordExpr) {
                    if (firstWord) {
                        sb.append("$").append(((CommandArgWordExpr) each).getWord());
                        firstWord = false;
                    } else {
                        sb.append("_").append(((CommandArgWordExpr) each).getWord());
                    }
                }
            }
            val res = unit.getTypeLookup().findStaticMethodExact(sb.toString(),
                    Arrays.stream(this.args).map(Expr::getReturnType).toArray(ValueType[]::new));
            if (res != null) {
                method = res;
            } else {
                throw new MethodNotFoundException(getSourcePos(), commandName);
            }
        }
    }

    public MethodInfo getMethod() {
        init();
        return method;
    }

    @Override
    public ValueType getReturnType() {
        init();
        return ValueType.fromASM(this.method.getReturnASMType());
    }

    @Override
    public ExprCodeGenerator getCodeGenerator() {
        init();
        return new MethodCallExprGenerator(this);
    }
}
