package com.blocklynukkit.bedrockLang.compiler.ast.compile.type;

import com.blocklynukkit.bedrockLang.compiler.ast.compile.Unit;
import com.blocklynukkit.bedrockLang.compiler.ast.compile.ValueType;
import com.blocklynukkit.bedrockLang.compiler.ast.compile.impl.unit.BDLUnit;
import com.blocklynukkit.bedrockLang.compiler.ast.exception.InvalidStaticMethodException;
import com.blocklynukkit.bedrockLang.compiler.ast.exception.InvalidValueTypeException;
import com.blocklynukkit.bedrockLang.compiler.ast.util.ArrayUtils;
import lombok.Getter;
import lombok.NonNull;
import lombok.val;
import lombok.var;
import org.objectweb.asm.Type;

import java.util.*;

/**
 * 用于查找ASM类型的工具类，被每个{@link BDLUnit}实例化。
 * <p>
 * 该类持有一个哈希表作为类池，类池记录所有已经导入过的类型。
 */
public final class TypeLookup {
    @Getter
    private final BDLUnit unit;
    /**
     * 记录所有导入过的类型，可以使用{@link #lookup}来将类放入
     */
    private final Map<String, ClassInfo> classPool = new HashMap<>();
    private final ClassLoader classLoader;
    private final List<String> packagesImported = new ArrayList<>();
    private final Map<String, BDLClassInfo> bdlClasses = new HashMap<>();
    private final WeakHashMap<ValueType, Type> typeStore = new WeakHashMap<>();
    private final WeakHashMap<ValueType, ClassInfo> classInfoStore = new WeakHashMap<>();
    private final Map<String, List<MethodInfo>> staticMethodImported = new HashMap<>();


    public TypeLookup(BDLUnit unit) {
        this.unit = unit;
        this.classLoader = Thread.currentThread().getContextClassLoader();
    }

    public void importPackage(String packageName) {
        packagesImported.add(packageName);
    }

    public void importStaticMethod(MethodInfo staticMethod) {
        if (!staticMethod.isStatic()) {
            throw new InvalidStaticMethodException(staticMethod);
        }
        var tmp = staticMethodImported.get(staticMethod.getSimpleName());
        if (tmp == null) {
            tmp = new ArrayList<>();
        }
        tmp.add(staticMethod);
        staticMethodImported.put(staticMethod.getSimpleName(), tmp);
    }

    public MethodInfo[] findStaticMethodFuzzy(@NonNull String simpleName, ValueType... argTypes) {
        val methods = staticMethodImported.get(simpleName);
        if (methods != null) {
            val givenTypes = new Type[argTypes.length];
            for (var i = 0; i < givenTypes.length; i++) {
                givenTypes[i] = this.lookup(argTypes[i]);
            }
            val out = new ArrayList<MethodInfo>(1);
            for (val each : methods) {
                if (ArrayUtils.equals(each.getArgumentASMTypes(), givenTypes)) {
                    out.add(each);
                }
            }
            return out.toArray(new MethodInfo[0]);
        } else {
            return new MethodInfo[0];
        }
    }

    public MethodInfo findStaticMethodExact(@NonNull String fullName, ValueType... argTypes) {
        val simpleName = fullName.contains("$") ? fullName.substring(0, fullName.indexOf('$')) : fullName;
        for (val each : findStaticMethodFuzzy(simpleName, argTypes)) {
            if (each.getName().equals(fullName)) {
                return each;
            }
        }
        return null;
    }

    public void addBDLClass(BDLUnit unit) {
        bdlClasses.put(unit.getName(), new BDLClassInfo(unit));
    }

    public Type lookup(String typeName) {
        var clazz = lookupBasic(typeName);
        if (clazz != null) return clazz;
        clazz = lookupBDL(typeName);
        if (clazz != null) return clazz;
        clazz = lookupOther(typeName);
        return clazz;
    }

    public Type lookup(ValueType valueType) {
        if (typeStore.containsKey(valueType)) {
            return typeStore.get(valueType);
        }
        Type res;
        res = lookupBDL(valueType.getName());
        if (res != null) {
            typeStore.put(valueType, res);
            return res;
        }
        if (valueType.isBasic()) {
            res = lookupBasic(valueType.getName());
            typeStore.put(valueType, res);
            return res;
        } else if (valueType.isArray()) {
            res = lookupOther(valueType.getName() + "[]");
            typeStore.put(valueType, res);
            return res;
        } else if (valueType.isClass()) {
            res = lookupOther(valueType.getName());
            typeStore.put(valueType, res);
            return res;
        }
        throw new InvalidValueTypeException(valueType);
    }

    public Type lookup(Unit unit) {
        return bdlClasses.get(unit.getName()).toASMType();
    }

    private Type lookupBDL(String typeName) {
        // TODO: 2022/1/3 修复无法查找bdl类数组问题
        if (bdlClasses.containsKey(typeName)) {
            return bdlClasses.get(typeName).toASMType();
        }
        return null;
    }

    private Type lookupBasic(String typeName) {
        switch (typeName) {
            case "void":
                return Type.VOID_TYPE;
            case "byte":
                return Type.BYTE_TYPE;
            case "short":
                return Type.SHORT_TYPE;
            case "int":
                return Type.INT_TYPE;
            case "long":
                return Type.LONG_TYPE;
            case "boolean":
                return Type.BOOLEAN_TYPE;
            case "float":
                return Type.FLOAT_TYPE;
            case "double":
                return Type.DOUBLE_TYPE;
            case "char":
                return Type.CHAR_TYPE;
            case "string":
            case "java.lang.String":
                return Objects.requireNonNull(findFromInternal("java.lang.String")).toASMType();
            default:
                return null;
        }
    }

    private Type lookupOther(String typeName) {
        var clazz = findFromInternal(typeName);
        if (clazz != null) return clazz.toASMType();
        for (String each : packagesImported) {
            clazz = findFromInternal(each + "." + typeName);
            if (clazz != null) return clazz.toASMType();
        }
        return null;
    }

    private ClassInfo findFromInternal(String className) {
        if (classPool.containsKey(className)) {
            return classPool.get(className);
        }
        val lastDotPos = className.lastIndexOf('.');
        try {
            var info = new InternalJavaClassInfo(classLoader.loadClass(className));
            classPool.put(className, info);
            return info;
        } catch (ClassNotFoundException e) {
            if (className.contains(".")) {
                InternalJavaClassInfo info;
                try {
                    info = new InternalJavaClassInfo(classLoader.loadClass(className.substring(0, lastDotPos) + "$" + className.substring(lastDotPos + 1)));
                } catch (ClassNotFoundException ex) {
                    return null;
                }
                classPool.put(className, info);
                return info;
            }
        }
        return null;
    }

    public ClassInfo findFromClassPool(String className) {
        return classPool.get(className);
    }

    public ClassInfo lookupClass(String typeName) {
        var clazz = lookupBasicClass(typeName);
        if (clazz != null) return clazz;
        clazz = lookupBDLClass(typeName);
        if (clazz != null) return clazz;
        clazz = lookupOtherClass(typeName);
        return clazz;
    }

    public ClassInfo lookupClass(ValueType valueType) {
        if (classInfoStore.containsKey(valueType)) {
            return classInfoStore.get(valueType);
        }
        ClassInfo res;
        res = lookupBDLClass(valueType.getName());
        if (res != null) {
            classInfoStore.put(valueType, res);
            return res;
        }
        if (valueType.isBasic()) {
            res = lookupBasicClass(valueType.getName());
            classInfoStore.put(valueType, res);
            return res;
        } else if (valueType.isArray()) {
            res = lookupOtherClass(valueType.getName() + "[]");
            classInfoStore.put(valueType, res);
            return res;
        } else if (valueType.isClass()) {
            res = lookupOtherClass(valueType.getName());
            classInfoStore.put(valueType, res);
            return res;
        }
        throw new InvalidValueTypeException(valueType);
    }

    private ClassInfo lookupBasicClass(String typeName) {
        switch (typeName) {
            case "void":
                return new InternalJavaClassInfo(void.class);
            case "byte":
                return new InternalJavaClassInfo(byte.class);
            case "short":
                return new InternalJavaClassInfo(short.class);
            case "int":
                return new InternalJavaClassInfo(int.class);
            case "long":
                return new InternalJavaClassInfo(long.class);
            case "boolean":
                return new InternalJavaClassInfo(boolean.class);
            case "float":
                return new InternalJavaClassInfo(float.class);
            case "double":
                return new InternalJavaClassInfo(double.class);
            case "char":
                return new InternalJavaClassInfo(char.class);
            case "string":
            case "java.lang.String":
                return Objects.requireNonNull(findFromInternal("java.lang.String"));
            default:
                return null;
        }
    }

    private ClassInfo lookupBDLClass(String typeName) {
        // TODO: 2022/1/14 修复无法查找bdl类数组问题
        if (bdlClasses.containsKey(typeName)) {
            return bdlClasses.get(typeName);
        }
        return null;
    }

    private ClassInfo lookupOtherClass(String typeName) {
        var clazz = findFromInternal(typeName);
        if (clazz != null) return clazz;
        for (String each : packagesImported) {
            clazz = findFromInternal(each + "." + typeName);
            if (clazz != null) return clazz;
        }
        return null;
    }
}
