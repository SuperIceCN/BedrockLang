package com.blocklynukkit.bedrockLang.compiler.ast.compile;

import com.blocklynukkit.bedrockLang.compiler.ast.compile.impl.type.ArrayValueType;
import com.blocklynukkit.bedrockLang.compiler.ast.compile.impl.type.BasicValueType;
import com.blocklynukkit.bedrockLang.compiler.ast.compile.impl.type.ClassValueType;
import com.blocklynukkit.bedrockLang.compiler.ast.exception.InvalidValueTypeException;
import com.blocklynukkit.bedrockLang.compiler.ast.util.ArrayUtils;
import org.objectweb.asm.Type;

/**
 * 用于描述值类型的抽象类
 */
public abstract class ValueType {
    public abstract String getName();

    /**
     * 获取值的类型特征
     *
     * @return 基本类，数组类或其他对象类
     */
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
            final ValueType tmp = (ValueType) obj;
            return tmp.getTrait() == this.getTrait() && tmp.getName().equals(this.getName());
        }
        return false;
    }

    @Override
    public String toString() {
        return getName();
    }

    /**
     * 根据名称创建类型描述符
     *
     * @param clazz 类名，可以为基本类，数组类（类名+[]），其他Java类的全名（包名+类名）
     * @return 类型
     */
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
                    String clzName = clazz.substring(0, clazz.length() - 2);
                    if (clzName.equals("string")) clzName = "java.lang.String";
                    return new ArrayValueType(clzName);
                } else {
                    return new ClassValueType(clazz);
                }
        }
    }

    /**
     * 将ASM类型描述符转为BDL类型描述符
     *
     * @param value ASM类型描述
     * @return BDL类型描述
     */
    public static ValueType fromASM(Type value) {
        return from(value.getClassName());
    }

    /**
     * 获取对象的值的类型
     *
     * @param value 要获取类型的对象
     * @return 对象的类型
     */
    public static ValueType fromObj(Object value) {
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
            final String clazz = value.getClass().getName();
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

    /**
     * 检测是否为基本类，后续可能检测其他类，如BigInteger，尚未实现
     *
     * @param type 值类型
     * @return 是基本类为true
     */
    public static boolean isNumberType(ValueType type) {
        initSizeOrder();
        return ArrayUtils.contains(sizeOrder, type);
    }

    /**
     * 比较两个基本类哪个占用空间更大，通常用于检查基本类的相互转换关系，如int+long=long
     *
     * @param a 一个基本类
     * @param b 另一个基本类
     * @return a和b中更大的
     * @throws InvalidValueTypeException 如果参数传入了非基本类则抛出此错误
     */
    public static ValueType largerNumberType(ValueType a, ValueType b) {
        initSizeOrder();
        if (a instanceof BasicValueType && isNumberType(a)) {
            if (b instanceof BasicValueType && isNumberType(b)) {
                if ((a == BasicValueType.LONG && b == BasicValueType.FLOAT) || (a == BasicValueType.FLOAT && b == BasicValueType.LONG)) {
                    return BasicValueType.DOUBLE;
                }
                final int ia = ArrayUtils.indexOf(sizeOrder, a);
                final int ib = ArrayUtils.indexOf(sizeOrder, b);
                return sizeOrder[Math.max(3, Math.max(ia, ib))];
            } else {
                throw new InvalidValueTypeException(b);
            }
        } else {
            throw new InvalidValueTypeException(a);
        }
    }

    /**
     * 检测是否为装箱类
     *
     * @param a 要检测的类
     * @return 是否为装箱类
     */
    public static boolean isBoxType(ValueType a) {
        switch (a.getName()) {
            case "java.lang.Boolean":
            case "java.lang.Byte":
            case "java.lang.Short":
            case "java.lang.Integer":
            case "java.lang.Long":
            case "java.lang.Character":
            case "java.lang.Float":
            case "java.lang.Double":
                return true;
            default:
                return false;
        }
    }
}

