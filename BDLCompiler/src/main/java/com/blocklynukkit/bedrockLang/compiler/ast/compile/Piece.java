package com.blocklynukkit.bedrockLang.compiler.ast.compile;

import com.blocklynukkit.bedrockLang.compiler.ast.util.SourcePos;
import lombok.var;

public interface Piece {
    SourcePos getSourcePos();

    boolean hasReturnType();

    ValueType getReturnType();

    Piece getPieceParent();

    CodeGenerator<?> getCodeGenerator();

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
}
