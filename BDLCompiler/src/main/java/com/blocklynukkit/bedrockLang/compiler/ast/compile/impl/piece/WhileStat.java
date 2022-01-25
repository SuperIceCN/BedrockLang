package com.blocklynukkit.bedrockLang.compiler.ast.compile.impl.piece;

import com.blocklynukkit.bedrockLang.compiler.ast.compile.*;
import com.blocklynukkit.bedrockLang.compiler.ast.compile.gen.WhileStatGenerator;
import com.blocklynukkit.bedrockLang.compiler.ast.util.SourcePos;
import org.objectweb.asm.Label;

public final class WhileStat extends StatBase implements LoopStat {
    private final String name;
    private Expr condition;
    private Block block;
    /**
     * 最后用于退出循环的标签，循环中不满足条件或者遇到break就应该跳转到这个标签
     */
    private final Label exitLoopLabel = new Label();
    /**
     * 循环开始标签，包括判断条件和执行内容，循环一开始或者遇到continue就应该跳转到这个标签
     */
    private final Label startLoopLabel = new Label();

    public WhileStat(SourcePos sourcePos, Piece parent) {
        super(sourcePos, parent);
        this.name = "";
    }

    public WhileStat(SourcePos sourcePos, Piece parent, String name) {
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

    public String getName() {
        return this.name;
    }

    public Expr getCondition() {
        return this.condition;
    }

    public Block getBlock() {
        return this.block;
    }

    public Label getExitLoopLabel() {
        return this.exitLoopLabel;
    }

    public Label getStartLoopLabel() {
        return this.startLoopLabel;
    }

    public void setCondition(Expr condition) {
        this.condition = condition;
    }

    public void setBlock(Block block) {
        this.block = block;
    }
}
