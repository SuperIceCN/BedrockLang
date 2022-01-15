package com.blocklynukkit.bedrockLang.compiler.ast.compile.impl.command;

import com.blocklynukkit.bedrockLang.compiler.ast.compile.CmdArg;
import com.blocklynukkit.bedrockLang.compiler.ast.compile.Command;
import com.blocklynukkit.bedrockLang.compiler.ast.compile.ValueType;
import com.blocklynukkit.bedrockLang.compiler.ast.compile.Variable;
import com.blocklynukkit.bedrockLang.compiler.ast.compile.impl.variable.CmdArgVariable;
import com.blocklynukkit.bedrockLang.compiler.ast.util.SourcePos;
import it.unimi.dsi.fastutil.ints.Int2ObjectLinkedOpenHashMap;
import it.unimi.dsi.fastutil.ints.IntObjectImmutablePair;
import it.unimi.dsi.fastutil.ints.IntObjectPair;
import it.unimi.dsi.fastutil.objects.Object2IntLinkedOpenHashMap;
import lombok.val;

import java.util.ArrayList;
import java.util.List;

public final class UnitCommand implements Command {
    private final String name;
    private final CmdArg[] cmdArgs;
    private final ValueType returnType;
    private final SourcePos getSourcePos;
    /*
     * 这里的设计是为了防止特殊情况下有不可及变量被删除后导致id顺序错乱，所以用双重映射而不用列表
     */
    private final Object2IntLinkedOpenHashMap<String> localVariablesIdMap;
    private final Int2ObjectLinkedOpenHashMap<Variable> localVariableObjMap;

    public UnitCommand(String name, CmdArg[] cmdArgs, ValueType returnType, SourcePos getSourcePos) {
        this.name = name;
        this.cmdArgs = cmdArgs;
        this.returnType = returnType;
        this.getSourcePos = getSourcePos;
        this.localVariablesIdMap = new Object2IntLinkedOpenHashMap<>();
        this.localVariableObjMap = new Int2ObjectLinkedOpenHashMap<>();
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public CmdArg[] getArgs() {
        return cmdArgs;
    }

    @Override
    public ValueType getReturnType() {
        return returnType;
    }

    @Override
    public SourcePos getSourcePos() {
        return getSourcePos;
    }

    @Override
    public int getLocalMax() {
        return localVariablesIdMap.size();
    }

    @Override
    public int addLocalVariable(Variable variable) {
        if (localVariableObjMap.containsValue(variable)) {
            return localVariablesIdMap.getInt(variable.getName());
        }
        val newId = localVariablesIdMap.size();
        localVariablesIdMap.put(variable.getName(), newId);
        localVariableObjMap.put(newId, variable);
        return newId;
    }

    @Override
    public boolean hasLocalVariable(String name) {
        return localVariablesIdMap.containsKey(name);
    }

    @Override
    public IntObjectImmutablePair<Variable> findLocalVariable(String name) {
        if (localVariablesIdMap.containsKey(name)) {
            val id = localVariablesIdMap.getInt(name);
            return new IntObjectImmutablePair<>(id, localVariableObjMap.get(id));
        } else {
            return null;
        }
    }

    @Override
    public List<IntObjectPair<Variable>> getAllLocalVariables() {
        val list = new ArrayList<IntObjectPair<Variable>>(localVariablesIdMap.size());
        for (val each : localVariableObjMap.int2ObjectEntrySet()) {
            list.add(new IntObjectImmutablePair<>(each.getIntKey(), each.getValue()));
        }
        return list;
    }

}
