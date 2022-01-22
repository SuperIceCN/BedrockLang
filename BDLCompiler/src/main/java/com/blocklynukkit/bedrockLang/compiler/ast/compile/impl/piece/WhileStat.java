package com.blocklynukkit.bedrockLang.compiler.ast.compile.impl.piece;

import com.blocklynukkit.bedrockLang.compiler.ast.compile.*;
import com.blocklynukkit.bedrockLang.compiler.ast.compile.gen.WhileStatGenerator;
import com.blocklynukkit.bedrockLang.compiler.ast.compile.LoopStat;
import com.blocklynukkit.bedrockLang.compiler.ast.util.SourcePos;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import org.objectweb.asm.Label;

public final class WhileStat extends StatBase implements LoopStat {
    @Getter
    @NonNull
    private final String name;
    @Getter
    @Setter
    @NonNull
    private Expr condition;
    @Getter
    @Setter
    @NonNull
    private Block block;
    /**
     * 最后用于退出循环的标签，循环中不满足条件或者遇到break就应该跳转到这个标签
     */
    @Getter
    private final Label exitLoopLabel = new Label();
    /**
     * 循环开始标签，包括判断条件和执行内容，循环一开始或者遇到continue就应该跳转到这个标签
     */
    @Getter
    private final Label startLoopLabel = new Label();

    public WhileStat(@NonNull SourcePos sourcePos, @NonNull Piece parent) {
        super(sourcePos, parent);
        this.name = "";
    }

    public WhileStat(@NonNull SourcePos sourcePos, @NonNull Piece parent, @NonNull String name) {
        super(sourcePos, parent);
        this.name = name;
    }

    @Override
    public WhileStatGenerator getCodeGenerator() {
        return new WhileStatGenerator(this);
    }

    @Override
    public Label getLoopStartLabel() {
        return startLoopLabel;
    }

    @Override
    public Label getLoopExitLabel() {
        return exitLoopLabel;
    }

    @Override
    public String getLoopName() {
        return name;
    }
}
