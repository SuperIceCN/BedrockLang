package com.blocklynukkit.bedrockLang.compiler.ast.compile;

import com.blocklynukkit.bedrockLang.compiler.ast.util.SourcePos;
import it.unimi.dsi.fastutil.ints.IntObjectPair;
import lombok.val;
import lombok.var;

import java.util.List;

/**
 * 命令接口，所有命令都应实现此接口 <br/><br/>
 * 在BDL中，命令是组织代码的方式，相当于java的静态方法<br/>
 */
public interface Command {
    /**
     * 获取命令名，没有相关的命令参数附加
     * @return 命令名
     */
    String getName();

    /**
     * 获取该命令对应的java方法名<br/>
     * 在bdl中允许命令包含静态单词{@link com.blocklynukkit.bedrockLang.compiler.ast.compile.impl.command.WordCmdArg}，
     * 所有静态单词会符加在命令名+$后面用于在jvm字节码中区分<br/><br/>
     * 如：<br/>
     * {@code say playerOnly "hello"} 相当于java {@code  say$playerOnly("hello");} <br/>
     * {@code fill outline 23 top bottom} 相当于java {@code  fill$outline_top_bottom(23);} <br/>
     * @return 此命令对应的java方法名
     */
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

    /**
     * 获取该命令的局部变量最大索引，现已托管至ASM自动计算
     * @return 局部变量最大索引值
     */
    @Deprecated
    int getLocalMax();

    /**
     * 向此命令的局部变量表中添加一个变量 <br/>
     * 请注意，此操作并不影响{@link com.blocklynukkit.bedrockLang.compiler.ast.compile.impl.piece.DefineCommandBlock}的变量域，
     * 仍然需要再添加一遍变量域
     * @param variable 要添加到局部变量表中的变量
     * @return 该变量被分配的局部变量id
     */
    int addLocalVariable(Variable variable);

    /**
     * 查找此命令的局部变量表中是否含有一个局部变量
     * @param name 局部变量名
     * @return 是否含有
     */
    boolean hasLocalVariable(String name);

    /**
     * 查找局部变量
     * @param name 变量名称
     * @return 该局部变量的信息，如果不存在返回null
     */
    IntObjectPair<Variable> findLocalVariable(String name);

    /**
     * 获取该命令的局部变量表中的所有局部变量
     * @return 所有局部变量
     */
    List<IntObjectPair<Variable>> getAllLocalVariables();
}
