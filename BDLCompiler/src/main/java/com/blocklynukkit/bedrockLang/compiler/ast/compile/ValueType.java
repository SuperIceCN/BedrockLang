package com.blocklynukkit.bedrockLang.compiler.ast.compile;

import com.blocklynukkit.bedrockLang.compiler.ast.compile.impl.type.ArrayValueType;
import com.blocklynukkit.bedrockLang.compiler.ast.compile.impl.type.BasicValueType;
import com.blocklynukkit.bedrockLang.compiler.ast.compile.impl.type.ClassValueType;
import com.blocklynukkit.bedrockLang.compiler.ast.exception.InvalidValueTypeException;
import com.blocklynukkit.bedrockLang.compiler.ast.util.ArrayUtils;
import lombok.val;
import org.objectweb.asm.Type;

import java.util.Arrays;

public abstract class ValueType {
    public abstract String getName();

    public abstract ValueTrait getTrait();

    public boolean isBasic() {
        return getTrait() == ValueTrait.Basic;
    }

    public boolean isArray() {
        return getTrait() == ValueTrait.Array;
    }

    public boolean isClass() {
        return getTrait() == ValueTrait.Class;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof ValueType) {
            val tmp = (ValueType) obj;
            return tmp.getTrait() == this.getTrait() && tmp.getName().equals(this.getName());
        }
        return false;
    }

    @Override
    public String toString() {
        return getName();
    }

    public static ValueType from(String clazz) {
        switch (clazz) {
            case "void":
                return BasicValueType.VOID;
            case "byte":
                return BasicValueType.BYTE;
            case "short":
                return BasicValueType.SHORT;
            case "int":
                return BasicValueType.INT;
            case "long":
                return BasicValueType.LONG;
            case "boolean":
                return BasicValueType.BOOLEAN;
            case "float":
                return BasicValueType.FLOAT;
            case "double":
                return BasicValueType.DOUBLE;
            case "char":
                return BasicValueType.CHAR;
            case "string":
                return BasicValueType.STRING;
            default:
                if (clazz.endsWith("[]")) {
                    return new ArrayValueType(clazz.substring(0, clazz.length() - 2));
                } else {
                    return new ClassValueType(clazz);
                }
        }
    }

    public static ValueType fromASM(Type value) {
        return from(value.getClassName());
    }

    public static ValueType from(Object value) {
        if (value == null) {
            return BasicValueType.VOID;
        } else if (value instanceof Byte) {
            return BasicValueType.BYTE;
        } else if (value instanceof Short) {
            return BasicValueType.SHORT;
        } else if (value instanceof Integer) {
            return BasicValueType.INT;
        } else if (value instanceof Long) {
            return BasicValueType.LONG;
        } else if (value instanceof Boolean) {
            return BasicValueType.BOOLEAN;
        } else if (value instanceof Float) {
            return BasicValueType.FLOAT;
        } else if (value instanceof Double) {
            return BasicValueType.DOUBLE;
        } else if (value instanceof Character) {
            return BasicValueType.CHAR;
        } else if (value instanceof String) {
            return BasicValueType.STRING;
        } else if (value.getClass().isArray()) {
            val clazz = value.getClass().getName();
            return new ArrayValueType(clazz.substring(0, clazz.length() - 2));
        } else {
            return new ClassValueType(value.getClass().getName());
        }
    }

    private static BasicValueType[] sizeOrder = null;

    /*
     * 避免在父类中引用子类导致类加载死锁
     */
    private static void initSizeOrder() {
        if (sizeOrder == null)
            sizeOrder = new BasicValueType[]{
                    BasicValueType.BYTE, BasicValueType.SHORT, BasicValueType.CHAR, BasicValueType.INT,
                    BasicValueType.FLOAT, BasicValueType.LONG, BasicValueType.DOUBLE
            };
    }

    public static boolean isNumberType(ValueType type) {
        initSizeOrder();
        return ArrayUtils.contains(sizeOrder, type);
    }

    public static ValueType largerNumberType(ValueType a, ValueType b) {
        initSizeOrder();
        if (a instanceof BasicValueType && isNumberType(a)) {
            if (b instanceof BasicValueType && isNumberType(b)) {
                if ((a == BasicValueType.LONG && b == BasicValueType.FLOAT) || (a == BasicValueType.FLOAT && b == BasicValueType.LONG)) {
                    return BasicValueType.DOUBLE;
                }
                val ia = ArrayUtils.indexOf(sizeOrder, a);
                val ib = ArrayUtils.indexOf(sizeOrder, b);
                return sizeOrder[Math.max(3, Math.max(ia, ib))];
            } else {
                throw new InvalidValueTypeException(b);
            }
        } else {
            throw new InvalidValueTypeException(a);
        }
    }
}

