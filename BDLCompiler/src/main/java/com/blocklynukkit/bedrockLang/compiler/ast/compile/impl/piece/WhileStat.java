package com.blocklynukkit.bedrockLang.compiler.ast.compile.impl.piece;

import com.blocklynukkit.bedrockLang.compiler.ast.compile.*;
import com.blocklynukkit.bedrockLang.compiler.ast.compile.gen.WhileStatGenerator;
import com.blocklynukkit.bedrockLang.compiler.ast.util.SourcePos;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import org.objectweb.asm.Label;

import java.util.ArrayList;
import java.util.List;

public final class WhileStat extends StatBase {
    @Getter
    @Setter
    @NonNull
    private Expr condition;
    @Getter
    @Setter
    @NonNull
    private Block block;
    private final List<Label> breakLabels = new ArrayList<>(1); //乐观地我们估计不会有break语句

    public WhileStat(@NonNull SourcePos sourcePos, @NonNull Piece parent) {
        super(sourcePos, parent);
    }

    @Override
    public WhileStatGenerator getCodeGenerator() {
        return new WhileStatGenerator(this);
    }

    /**
     * 添加一个循环中断标签，生成器需要将这些标签跳转到循环结束处
     * @param label 循环中断或结束处的跳转到循环下一个语句的标签，此标签不应当包含任何字节码
     */
    public void addBreakLabel(Label label) {
        this.breakLabels.add(label);
    }

    public List<Label> getAllBreakLabels() {
        return this.breakLabels;
    }
}
