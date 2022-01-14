package com.blocklynukkit.bedrockLang.compiler.ast.compile;

import com.blocklynukkit.bedrockLang.compiler.ast.util.SourcePos;
import it.unimi.dsi.fastutil.ints.IntObjectPair;
import lombok.val;
import lombok.var;

import java.util.List;

public interface Command {
    String getName();

    default String getMethodName() {
        val sb = new StringBuilder(this.getName());
        var findWordArg = false;
        for (val each : this.getArgs()) {
            if (!each.hasValueType()) {
                if (!findWordArg) {
                    sb.append("$").append(each.getName());
                    findWordArg = true;
                } else {
                    sb.append("_").append(each.getName());
                }
            }
        }
        return sb.toString();
    }

    CmdArg[] getArgs();

    ValueType getReturnType();

    SourcePos getSourcePos();

    int getLocalMax();

    int addLocalVariable(Variable variable);

    boolean hasLocalVariable(String name);

    /**
     * 查找局部变量
     * @param name 变量名称
     * @return 该局部变量的信息，如果不存在返回null
     */
    IntObjectPair<Variable> findLocalVariable(String name);

    List<IntObjectPair<Variable>> getAllLocalVariables();
}
