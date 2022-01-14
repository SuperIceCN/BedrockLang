package com.blocklynukkit.bedrockLang.compiler.ast.compile.impl.piece;

import com.blocklynukkit.bedrockLang.compiler.ast.compile.*;
import com.blocklynukkit.bedrockLang.compiler.ast.compile.gen.UnitCommandGenerator;
import com.blocklynukkit.bedrockLang.compiler.ast.compile.impl.command.UnitCommand;
import com.blocklynukkit.bedrockLang.compiler.ast.compile.impl.variable.CmdArgVariable;
import com.blocklynukkit.bedrockLang.compiler.ast.util.SourcePos;
import lombok.val;

import java.util.Objects;

import static com.blocklynukkit.bedrockLang.compiler.ast.util.NullUtils.Ok;

public final class DefineCommandBlock extends BlockBase implements Declaration<Command>, LocalVariableStore {
    private final String name;
    private final CmdArg[] args;
    private final ValueType returnType;
    private UnitCommand command = null;

    public DefineCommandBlock(SourcePos sourcePos, Unit parent, String name, ValueType returnType, CmdArg... args) {
        super(sourcePos, parent);
        this.name = name;
        this.args = args;
        this.returnType = returnType;
        for (val each : args) {
            if (each.hasValueType()) {
                this.addVariable(new CmdArgVariable(each.getName(), each.getValueType(), each.getSourcePos()));
            }
        }
    }

    @Override
    public Command declare() {
        this.command = Ok(command, () -> {
            val cmd = new UnitCommand(name, args, returnType, sourcePos);
            for (val each : this.getVariables().values()) {
                cmd.addLocalVariable(each);
            }
            return cmd;
        });
        return this.command;
    }

    @Override
    public boolean hasReturnType() {
        return true;
    }

    @Override
    public ValueType getReturnType() {
        return returnType;
    }

    @Override
    public UnitCommandGenerator getCodeGenerator() {
        return new UnitCommandGenerator(this);
    }

    @Override
    protected VariableRecord makeVariableRecord(Variable variable) {
        return new VariableRecord(variable, VariableRecord.VariableType.LOCAL, Objects.requireNonNull(this.command.findLocalVariable(variable.getName())).leftInt());
    }

    @Override
    public Unit getVariableStoreParent() {
        return (Unit) getPieceParent();
    }

    @Override
    public void addVariable(Variable variable) {
        super.addVariable(variable);
        if (this.command != null)
            this.command.addLocalVariable(variable);
    }

    @Override
    public int addLocalVariableOnly(Variable variable) {
        return this.command.addLocalVariable(variable);
    }

    @Override
    public boolean hasLocalVariable(String variableName) {
        return this.command.hasLocalVariable(variableName);
    }

    @Override
    public int getLocalVariableIndex(String variableName) {
        return Objects.requireNonNull(this.command.findLocalVariable(variableName)).leftInt();
    }
}
