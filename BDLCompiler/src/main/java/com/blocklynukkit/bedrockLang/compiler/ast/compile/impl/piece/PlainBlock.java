package com.blocklynukkit.bedrockLang.compiler.ast.compile.impl.piece;

import com.blocklynukkit.bedrockLang.compiler.ast.compile.*;
import com.blocklynukkit.bedrockLang.compiler.ast.compile.gen.PlainBlockCodeGenerator;
import com.blocklynukkit.bedrockLang.compiler.ast.util.SourcePos;

import static com.blocklynukkit.bedrockLang.compiler.ast.util.NullUtils.Ok;

/**
 * 一个最基础的Block实现，单独使用没有任何额外功能，仅仅是用于限制局部变量作用域<br/>
 * 通常用于其他块中。
 */
public final class PlainBlock extends BlockBase {
    private DefineCommandBlock parentCommand = null;

    public PlainBlock(SourcePos sourcePos, Piece pieceParent, VariableStore variableStoreParent) {
        super(sourcePos, pieceParent, variableStoreParent);
    }

    @Override
    protected VariableRecord makeVariableRecord(Variable variable) {
        return new VariableRecord(variable, VariableRecord.VariableType.LOCAL,
                Ok(parentCommand, () -> {
                    parentCommand = this.findParent(DefineCommandBlock.class);
                    return parentCommand;
                }).getLocalVariableIndex(variable));
    }

    @Override
    public PlainBlockCodeGenerator getCodeGenerator() {
        return new PlainBlockCodeGenerator(this);
    }

    public DefineCommandBlock getParentCommand() {
        return Ok(parentCommand, () -> {
            parentCommand = this.findParent(DefineCommandBlock.class);
            return parentCommand;
        });
    }
}
