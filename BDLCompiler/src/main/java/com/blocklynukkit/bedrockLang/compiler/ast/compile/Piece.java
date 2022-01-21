package com.blocklynukkit.bedrockLang.compiler.ast.compile;

import com.blocklynukkit.bedrockLang.compiler.ast.util.SourcePos;
import it.unimi.dsi.fastutil.objects.Object2BooleanFunction;
import it.unimi.dsi.fastutil.objects.Object2IntFunction;
import lombok.val;
import lombok.var;

import java.util.function.Function;

/**
 * {@link Piece}代码片段是所有代码相关类都实现的抽象接口
 */
public interface Piece {
    /**
     * 获取源代码位置，用于将源代码映射到各个代码片段
     *
     * @return 源代码位置
     */
    SourcePos getSourcePos();

    /**
     * 代码片段是否有返回值
     *
     * @return 是否有返回值
     */
    boolean hasReturnType();

    /**
     * 获取代码片段的返回值类型
     *
     * @return 返回值类型
     */
    ValueType getReturnType();

    /**
     * 获取父代码片段
     *
     * @return 父代码片段
     */
    Piece getPieceParent();

    /**
     * 获取该代码片段对应的代码生成器
     *
     * @return 生成器
     */
    CodeGenerator<?> getCodeGenerator();

    /**
     * 递归上溯查找次代码片段属于哪个变量域，如果此代码片段就是变量域则返回自身
     *
     * @return 属于的变量域
     */
    default VariableStore findVariableStoreBelongTo() {
        var parent = this;
        while (parent != null) {
            if (parent instanceof VariableStore) {
                return (VariableStore) parent;
            } else {
                parent = parent.getPieceParent();
            }
        }
        return null;
    }

    /**
     * 递归上溯查找属于的具有特定类型的变量域
     *
     * @param _interface 变量域接口的字类接口
     * @param <T>        _interface
     * @return 属于的变量域
     */
    default <T extends VariableStore> T findVariableStoreBelongTo(Class<T> _interface) {
        var parent = this;
        while (parent != null) {
            if (_interface.isInstance(parent)) {
                return _interface.cast(parent);
            } else {
                parent = parent.getPieceParent();
            }
        }
        return null;
    }

    /**
     * 递归上溯查找某个为某个类子类的父亲
     *
     * @param clazz 要查找父亲的父类
     * @param <T>   clazz
     * @return 父piece
     */
    default <T extends Piece> T findParent(Class<T> clazz) {
        var parent = this;
        while (parent != null) {
            if (clazz.isInstance(parent)) {
                return clazz.cast(parent);
            } else {
                parent = parent.getPieceParent();
            }
        }
        return null;
    }

    /**
     * 递归上溯查找某个为某个类子类的父亲
     *
     * @param handler 条件函数
     * @param <T>     clazz
     * @return 父piece
     */
    default <T> T findParent(Function<Piece, T> handler) {
        var parent = this;
        while (parent != null) {
            val tmp = handler.apply(parent);
            if (tmp != null) {
                return tmp;
            } else {
                parent = parent.getPieceParent();
            }
        }
        return null;
    }
}
