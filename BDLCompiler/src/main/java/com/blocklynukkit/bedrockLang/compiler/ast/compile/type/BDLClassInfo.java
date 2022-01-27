package com.blocklynukkit.bedrockLang.compiler.ast.compile.type;

import com.blocklynukkit.bedrockLang.compiler.ast.compile.CmdArg;
import com.blocklynukkit.bedrockLang.compiler.ast.compile.Command;
import com.blocklynukkit.bedrockLang.compiler.ast.compile.impl.unit.BDLUnit;
import com.blocklynukkit.bedrockLang.compiler.ast.compile.impl.variable.UnitGlobalVariable;
import com.blocklynukkit.bedrockLang.compiler.ast.util.ArrayUtils;
import org.objectweb.asm.Type;

import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public final class BDLClassInfo extends ClassInfo {
    private final BDLUnit bdlUnit;

    public BDLClassInfo(BDLUnit bdlUnit) {
        this.bdlUnit = bdlUnit;
    }

    @Override
    public String getQualifiedName() {
        return bdlUnit.getName().replace(".", "/");
    }

    @Override
    public String getSimpleName() {
        return bdlUnit.getName().substring(bdlUnit.getName().indexOf(".") + 1);
    }

    @Override
    public String getFullName() {
        return bdlUnit.getName();
    }

    @Override
    public String getPackage() {
        return bdlUnit.getName().substring(0, bdlUnit.getName().indexOf("."));
    }

    @Override
    public MethodInfo[] getMethods() {
        final ArrayList<MethodInfo> list = new ArrayList<>();
        for (final List<Command> each : bdlUnit.getUnitCommands().values()) {
            for (final Command cmd : each) {
                list.add(new BDLMethodInfo(cmd, bdlUnit.getTypeLookup()));
            }
        }
        return list.toArray(new MethodInfo[0]);
    }

    @Override
    public FieldInfo[] getFields() {
        return bdlUnit.getUnitVariables().values().stream()
                .map(variable -> new BDLFieldInfo((UnitGlobalVariable) variable, bdlUnit.getTypeLookup()))
                .toArray(FieldInfo[]::new);
    }

    @Override
    public MethodInfo[] getMethodFuzzy(String methodName) {
        final ArrayList<MethodInfo> list = new ArrayList<>();
        for (final Map.Entry<String, List<Command>> each : bdlUnit.getUnitCommands().entrySet()) {
            if (before$(each.getKey()).equals(methodName)) {
                for (final Command cmd : each.getValue()) {
                    list.add(new BDLMethodInfo(cmd, bdlUnit.getTypeLookup()));
                }
            }
        }
        return list.toArray(new MethodInfo[0]);
    }

    @Override
    public MethodInfo[] getMethodFuzzy(String methodName, ClassInfo... argTypes) {
        final ArrayList<MethodInfo> list = new ArrayList<>();
        for (final Map.Entry<String, List<Command>> each : bdlUnit.getUnitCommands().entrySet()) {
            if (before$(each.getKey()).equals(methodName)) {
                for (final Command cmd : each.getValue()) {
                    if (ArrayUtils.equals(Arrays.stream(cmd.getArgs()).filter(CmdArg::hasValueType)
                            .map(CmdArg::getValueType)
                            .map(vt -> bdlUnit.getTypeLookup().lookupClass(vt))
                            .toArray(ClassInfo[]::new), argTypes, (a, b) -> b.canCastTo(a))) {
                        list.add(new BDLMethodInfo(cmd, bdlUnit.getTypeLookup()));
                    }
                }
            }
        }
        return list.toArray(new MethodInfo[0]);
    }

    @Override
    public MethodInfo[] getMethod(String methodName) {
        final ArrayList<MethodInfo> list = new ArrayList<>();
        if (bdlUnit.getUnitCommands().containsKey(methodName)) {
            for (final Command cmd : bdlUnit.getUnitCommands().get(methodName)) {
                list.add(new BDLMethodInfo(cmd, bdlUnit.getTypeLookup()));
            }
        }
        return list.toArray(new MethodInfo[0]);
    }

    @Override
    public MethodInfo getMethod(String methodName, ClassInfo... argTypes) {
        if (bdlUnit.getUnitCommands().containsKey(methodName)) {
            for (final Command cmd : bdlUnit.getUnitCommands().get(methodName)) {
                if (ArrayUtils.equals(Arrays.stream(cmd.getArgs()).filter(CmdArg::hasValueType)
                        .map(CmdArg::getValueType)
                        .map(vt -> bdlUnit.getTypeLookup().lookupClass(vt))
                        .toArray(ClassInfo[]::new), argTypes, (a, b) -> b.canCastTo(a))) {
                    return new BDLMethodInfo(cmd, bdlUnit.getTypeLookup());
                }
            }
        }
        return null;
    }

    @Override
    public MethodInfo[] getConstructor() {
        return new MethodInfo[]{new BDLConstructorInfo(this)};
    }

    @Override
    public MethodInfo getConstructor(ClassInfo... argTypes) {
        return new BDLConstructorInfo(this);
    }

    @Override
    public FieldInfo getField(String name) {
        return new BDLFieldInfo((UnitGlobalVariable) bdlUnit.getUnitVariables().get(name), bdlUnit.getTypeLookup());
    }

    @Override
    public Type toASMType() {
        return Type.getType("L" + getQualifiedName() + ";");
    }

    @Override
    public int getModifier() {
        return Modifier.PUBLIC;
    }

    /*
     * 目前bdl类不会实现任何接口
     */
    @Override
    public boolean hasImplementInterface(ClassInfo interfaceClass) {
        return false;
    }

    /*
     * 目前bdl类不会实现任何接口
     */
    @Override
    public ClassInfo matchClassImplementedInterface(ClassInfo interfaceClass) {
        return null;
    }

    @Override
    public boolean canCastTo(ClassInfo classInfo) {
        return classInfo.getFullName().equals(this.getFullName());
    }

    private static String before$(String str) {
        return str.contains("$") ? str.substring(0, str.indexOf('$')) : str;
    }
}
