package com.blocklynukkit.bedrockLang.compiler.ast.compile.impl.piece.operator;

import com.blocklynukkit.bedrockLang.compiler.ast.compile.*;
import com.blocklynukkit.bedrockLang.compiler.ast.compile.gen.operator.EqualExprGenerator;
import com.blocklynukkit.bedrockLang.compiler.ast.compile.impl.type.BasicValueType;
import com.blocklynukkit.bedrockLang.compiler.ast.util.SourcePos;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

/**
 * 判断是否相等表达式 (==) <br/>
 * 基本类型将比较值是否相等，比较时将会将基本类型尝试转为数据宽度较大的类型 <br/>
 * 对于装箱类型，将进行拆箱操作后再进行比较 <br/>
 * 对于其他类型，将调用{@link Object#equals(Object)}进行比较 <br/>
 * 特别的，{@code null == null} {@code null != 其他对象} {@code null != 基本类型} <br/> <br/>
 * 警告：这样做会使得{@code a==b}和{@code b==a}的结果不一定一致！
 */
@Getter
@Setter
public final class EqualExpr extends ExprBase {
    private Expr left;
    private Expr right;

    public EqualExpr(@NonNull SourcePos sourcePos, @NonNull Piece parent) {
        super(sourcePos, parent);
    }

    @Override
    public ValueType getReturnType() {
        return BasicValueType.BOOLEAN;
    }

    @Override
    public ExprCodeGenerator getCodeGenerator() {
        return new EqualExprGenerator(this);
    }
}
