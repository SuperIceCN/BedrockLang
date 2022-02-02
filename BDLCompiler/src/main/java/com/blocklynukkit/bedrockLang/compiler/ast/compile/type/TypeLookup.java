package com.blocklynukkit.bedrockLang.compiler.ast.compile.type;

import com.blocklynukkit.bedrockLang.compiler.ast.compile.Unit;
import com.blocklynukkit.bedrockLang.compiler.ast.compile.ValueType;
import com.blocklynukkit.bedrockLang.compiler.ast.compile.impl.unit.BDLUnit;
import com.blocklynukkit.bedrockLang.compiler.ast.exception.InvalidStaticMethodException;
import com.blocklynukkit.bedrockLang.compiler.ast.exception.InvalidValueTypeException;
import com.blocklynukkit.bedrockLang.compiler.ast.util.ArrayUtils;
import com.blocklynukkit.bedrockLang.compiler.ast.util.StringUtils;
import org.objectweb.asm.Type;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 用于查找ASM类型的工具类，被每个{@link BDLUnit}实例化。
 * <br/>
 * 该类持有一个哈希表作为类池，类池记录所有已经导入过的类型。
 * TODO: 2022/1/15 抽象化类型查找器
 */
public class TypeLookup {
    private static final Pattern arraySuffixPattern = Pattern.compile("(\\[])");

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

    /**
     * 初始化一个类型查找器
     *
     * @param unit 持有该类型查找器的BDL编译单元
     */
    public TypeLookup(BDLUnit unit) {
        this.unit = unit;
        this.classLoader = Thread.currentThread().getContextClassLoader();
    }

    /**
     * 导入一个包，后续在查找的时候会尝试在导入的包下查找<br/>
     * 请注意：java.lang不默认导入
     *
     * @param packageName 包名
     */
    public void importPackage(String packageName) {
        packagesImported.add(packageName);
    }

    /**
     * 导入一个静态方法
     *
     * @param staticMethod 静态方法描述
     */
    public void importStaticMethod(MethodInfo staticMethod) {
        if (!staticMethod.isStatic()) {
            throw new InvalidStaticMethodException(staticMethod);
        }
        List<MethodInfo> tmp = staticMethodImported.get(staticMethod.getSimpleName());
        if (tmp == null) {
            tmp = new ArrayList<>();
        }
        tmp.add(staticMethod);
        staticMethodImported.put(staticMethod.getSimpleName(), tmp);
    }

    /**
     * 模糊查找已经导入的静态方法
     *
     * @param simpleName 静态方法简易名，即完整方法名$前面的部分，若不含$则等同于完整方法名
     * @param argTypes   参数类型
     * @return 找到的所有静态方法的描述
     */
    public MethodInfo[] findStaticMethodFuzzy(String simpleName, ValueType... argTypes) {
        final List<MethodInfo> methods = staticMethodImported.get(simpleName);
        if (methods != null) {
            final ClassInfo[] givenTypes = new ClassInfo[argTypes.length];
            for (int i = 0; i < givenTypes.length; i++) {
                givenTypes[i] = this.lookupClass(argTypes[i]);
            }
            final ArrayList<MethodInfo> out = new ArrayList<>(1);
            for (final MethodInfo each : methods) {
                if (ArrayUtils.equals(each.getArgumentClassTypes(), givenTypes, (a, b) -> b.canCastTo(a))) {
                    out.add(each);
                }
            }
            return out.toArray(new MethodInfo[0]);
        } else {
            return new MethodInfo[0];
        }
    }

    /**
     * 精确查找已经导入的静态方法
     *
     * @param fullName 静态方法简易名，即完整方法名$前面的部分，若不含$则等同于完整方法名
     * @param argTypes 参数类型
     * @return 找到的所有静态方法的描述
     */
    public MethodInfo findStaticMethodExact(String fullName, ValueType... argTypes) {
        final String simpleName = fullName.contains("$") ? fullName.substring(0, fullName.indexOf('$')) : fullName;
        for (final MethodInfo each : findStaticMethodFuzzy(simpleName, argTypes)) {
            if (each.getName().equals(fullName)) {
                return each;
            }
        }
        return null;
    }

    /**
     * 将一个BDL编译单元添加到该查找器中
     *
     * @param unit bdl编译单元
     */
    public void addBDLClass(BDLUnit unit) {
        bdlClasses.put(unit.getName(), new BDLClassInfo(unit));
    }

    /**
     * 根据类名查找ASM类型
     *
     * @param typeName 类名（基本类型，数组，其他java类，bdl编译单元名）
     * @return ASM类型
     */
    public Type lookup(String typeName) {
        Type clazz = lookupBasic(typeName);
        if (clazz != null) return clazz;
        clazz = lookupBDL(typeName);
        if (clazz != null) return clazz;
        if (typeName.endsWith("[]")) {
            final String singleTypeName = typeName.replace("[]","");
            final Matcher matcher = arraySuffixPattern.matcher(typeName);
            final int arrayDimensions = 1 + (matcher.find() ? matcher.groupCount() : 0);
            final String arrayPrefix = StringUtils.repeat("[", arrayDimensions);
            switch (singleTypeName) {
                case "boolean":
                    return Type.getType(arrayPrefix + "Z");
                case "byte":
                    return Type.getType(arrayPrefix + "B");
                case "char":
                    return Type.getType(arrayPrefix + "C");
                case "short":
                    return Type.getType(arrayPrefix + "S");
                case "int":
                    return Type.getType(arrayPrefix + "I");
                case "long":
                    return Type.getType(arrayPrefix + "J");
                case "float":
                    return Type.getType(arrayPrefix + "F");
                case "double":
                    return Type.getType(arrayPrefix + "D");
                default:
                    return lookupOther(arrayPrefix + "L" + singleTypeName + ";");
            }
        }
        clazz = lookupOther(typeName);
        return clazz;
    }

    /**
     * 查找值类型描述符的ASM类型
     *
     * @param valueType 值类型描述符
     * @return ASM类型
     */
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
            final String typeName = valueType.getName().replace("[]", "");
            final Matcher matcher = arraySuffixPattern.matcher(valueType.getName());
            final int arrayDimensions = 1 + (matcher.find() ? matcher.groupCount() : 0);
            final String arrayPrefix = StringUtils.repeat("[", arrayDimensions);
            switch (typeName) {
                case "boolean":
                    res = Type.getType(arrayPrefix + "Z");
                    break;
                case "byte":
                    res = Type.getType(arrayPrefix + "B");
                    break;
                case "char":
                    res = Type.getType(arrayPrefix + "C");
                    break;
                case "short":
                    res = Type.getType(arrayPrefix + "S");
                    break;
                case "int":
                    res = Type.getType(arrayPrefix + "I");
                    break;
                case "long":
                    res = Type.getType(arrayPrefix + "J");
                    break;
                case "float":
                    res = Type.getType(arrayPrefix + "F");
                    break;
                case "double":
                    res = Type.getType(arrayPrefix + "D");
                    break;
                default:
                    res = lookupOther(arrayPrefix + "L" + typeName + ";");
            }
            typeStore.put(valueType, res);
            return res;
        } else if (valueType.isClass()) {
            res = lookupOther(valueType.getName());
            typeStore.put(valueType, res);
            return res;
        }
        throw new InvalidValueTypeException(valueType);
    }

    /**
     * 将编译单元转为ASM类型
     *
     * @param unit 编译单元
     * @return ASM类型
     */
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
        ClassInfo clazz = findFromInternal(typeName);
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
        final int lastDotPos = className.lastIndexOf('.');
        try {
            InternalJavaClassInfo info = new InternalJavaClassInfo(Class.forName(className, false, classLoader));
            classPool.put(className, info);
            return info;
        } catch (ClassNotFoundException e) {
            if (className.contains(".")) {
                InternalJavaClassInfo info;
                try {
                    info = new InternalJavaClassInfo(Class.forName(className.substring(0, lastDotPos) + "$" + className.substring(lastDotPos + 1), false, classLoader));
                } catch (ClassNotFoundException ex) {
                    return null;
                }
                classPool.put(className, info);
                return info;
            }
        }
        return null;
    }

    /**
     * 根据类名查找类信息
     *
     * @param typeName 类名（基本类型，数组，其他java类，bdl编译单元名）
     * @return 类信息
     */
    public ClassInfo lookupClass(String typeName) {
        ClassInfo clazz = lookupBasicClass(typeName);
        if (clazz != null) return clazz;
        clazz = lookupBDLClass(typeName);
        if (clazz != null) return clazz;
        if(typeName.endsWith("[]")){
            final String singleTypeName = typeName.replace("[]", "");
            final Matcher matcher = arraySuffixPattern.matcher(singleTypeName);
            final int arrayDimensions = 1 + (matcher.find() ? matcher.groupCount() : 0);
            final String arraySuffix = StringUtils.repeat("[]", arrayDimensions);
            try{
                switch (singleTypeName) {
                    case "boolean":
                        return new InternalJavaClassInfo(classLoader.loadClass("boolean" + arraySuffix));
                    case "byte":
                        return new InternalJavaClassInfo(classLoader.loadClass("byte" + arraySuffix));
                    case "char":
                        return new InternalJavaClassInfo(classLoader.loadClass("char" + arraySuffix));
                    case "short":
                        return new InternalJavaClassInfo(classLoader.loadClass("short" + arraySuffix));
                    case "int":
                        return new InternalJavaClassInfo(classLoader.loadClass("int" + arraySuffix));
                    case "long":
                        return new InternalJavaClassInfo(classLoader.loadClass("long" + arraySuffix));
                    case "float":
                        return new InternalJavaClassInfo(classLoader.loadClass("float" + arraySuffix));
                    case "double":
                        return new InternalJavaClassInfo(classLoader.loadClass("double" + arraySuffix));
                    default:
                        return lookupOtherClass(StringUtils.repeat("[", arrayDimensions) + "L" + singleTypeName + ";");
                }
            } catch (ClassNotFoundException e) {
                return null;
            }
        }
        clazz = lookupOtherClass(typeName);
        return clazz;
    }

    /**
     * 查找值类型描述符的类信息
     *
     * @param valueType 值类型描述符
     * @return 类信息
     */
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
            final String typeName = valueType.getName().replace("[]", "");
            final Matcher matcher = arraySuffixPattern.matcher(valueType.getName());
            final int arrayDimensions = 1 + (matcher.find() ? matcher.groupCount() : 0);
            final String arraySuffix = StringUtils.repeat("[]", arrayDimensions);
            try{
                switch (typeName) {
                    case "boolean":
                        res = new InternalJavaClassInfo(classLoader.loadClass("boolean" + arraySuffix));
                        break;
                    case "byte":
                        res = new InternalJavaClassInfo(classLoader.loadClass("byte" + arraySuffix));
                        break;
                    case "char":
                        res = new InternalJavaClassInfo(classLoader.loadClass("char" + arraySuffix));
                        break;
                    case "short":
                        res = new InternalJavaClassInfo(classLoader.loadClass("short" + arraySuffix));
                        break;
                    case "int":
                        res = new InternalJavaClassInfo(classLoader.loadClass("int" + arraySuffix));
                        break;
                    case "long":
                        res = new InternalJavaClassInfo(classLoader.loadClass("long" + arraySuffix));
                        break;
                    case "float":
                        res = new InternalJavaClassInfo(classLoader.loadClass("float" + arraySuffix));
                        break;
                    case "double":
                        res = new InternalJavaClassInfo(classLoader.loadClass("double" + arraySuffix));
                        break;
                    default:
                        res = lookupOtherClass(StringUtils.repeat("[", arrayDimensions) + "L" + typeName + ";");
                }
            } catch (ClassNotFoundException e) {
                return null;
            }
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
        ClassInfo clazz = findFromInternal(typeName);
        if (clazz != null) return clazz;
        for (String each : packagesImported) {
            clazz = findFromInternal(each + "." + typeName);
            if (clazz != null) return clazz;
        }
        return null;
    }

    public BDLUnit getUnit() {
        return this.unit;
    }
}
