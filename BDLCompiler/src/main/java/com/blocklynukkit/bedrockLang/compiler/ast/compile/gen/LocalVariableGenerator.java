package com.blocklynukkit.bedrockLang.compiler.ast.compile.gen;

import com.blocklynukkit.bedrockLang.compiler.ast.compile.LocalVariableStore;
import com.blocklynukkit.bedrockLang.compiler.ast.compile.StatCodeGenerator;
import com.blocklynukkit.bedrockLang.compiler.ast.compile.Unit;
import com.blocklynukkit.bedrockLang.compiler.ast.compile.impl.piece.LocalVariableDeclareStat;
import com.blocklynukkit.bedrockLang.compiler.ast.compile.impl.piece.WriteVariableExpr;
import lombok.RequiredArgsConstructor;
import lombok.val;

@RequiredArgsConstructor
public final class LocalVariableGenerator implements StatCodeGenerator {
    private final LocalVariableDeclareStat stat;

    @Override
    public Void generate(Unit unit) {
        val variable = stat.declare();
        // 我们只需要将变量加入局部变量表即可，语义变量表会自动添加
        stat.findVariableStoreBelongTo(LocalVariableStore.class).addLocalVariableOnly(variable);
        // 如果有初始化的话就新建一个写变量生成器把它写进去
        if (stat.getInitExpr() != null) {
            // TODO: 2022/1/7 优化此处构造，减少无谓重复查找
            val expr = new WriteVariableExpr(stat.getSourcePos(), stat, variable.getName());
            expr.setValueExpr(stat.getInitExpr());
            expr.getCodeGenerator().generate(unit);
        }
        return null;
    }
}
