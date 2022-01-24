package com.blocklynukkit.bedrockLang.compiler.ast.compile.impl.piece;

import com.blocklynukkit.bedrockLang.compiler.ast.compile.ExprBase;
import com.blocklynukkit.bedrockLang.compiler.ast.compile.ExprCodeGenerator;
import com.blocklynukkit.bedrockLang.compiler.ast.compile.Piece;
import com.blocklynukkit.bedrockLang.compiler.ast.compile.ValueType;
import com.blocklynukkit.bedrockLang.compiler.ast.compile.gen.FieldChainExprGenerator;
import com.blocklynukkit.bedrockLang.compiler.ast.compile.impl.unit.BDLUnit;
import com.blocklynukkit.bedrockLang.compiler.ast.compile.type.ClassInfo;
import com.blocklynukkit.bedrockLang.compiler.ast.compile.type.FieldInfo;
import com.blocklynukkit.bedrockLang.compiler.ast.compile.type.MethodInfo;
import com.blocklynukkit.bedrockLang.compiler.ast.compile.type.TypeLookup;
import com.blocklynukkit.bedrockLang.compiler.ast.exception.ClassNotFoundException;
import com.blocklynukkit.bedrockLang.compiler.ast.exception.ClassUnsupportedException;
import com.blocklynukkit.bedrockLang.compiler.ast.exception.MethodNotFoundException;
import com.blocklynukkit.bedrockLang.compiler.ast.exception.NonStaticFieldException;
import com.blocklynukkit.bedrockLang.compiler.ast.util.SourcePos;
import lombok.*;
import org.objectweb.asm.Type;

/**
 * FieldChainExpr是针对多级字段调用的表达式，返回指定的字段对象。
 * <br/>
 * FieldChainExpr的用例主要如下：<br/>
 * 1. 用于调用某个对象的字段 <code>var $x = $y.$z;</code> <br/>
 * 2. 用于调用某个类的静态字段 <code>var $myOut = System.$out.println;</code> <br/>
 * 3. 用于多级调用字段 <code>var $x = $a.$b.$c.;</code> <br/>
 * 4. 用于链式地调用没有参数有返回值的方法并最终返回一个字段 <code>var $x = $a.setATrue.setBTrue.$r;</code> <br/>
 * <br/>
 */
public final class FieldChainExpr extends ExprBase {
    @NonNull
    @Getter
    private final String fieldName;
    private FieldInfo finalField = null;
    @Getter
    private ChainAction[] chainActions = null;

    public FieldChainExpr(@NonNull SourcePos sourcePos, @NonNull Piece parent, @NonNull String fieldName) {
        super(sourcePos, parent);
        this.fieldName = fieldName;
    }

    @SuppressWarnings("DuplicatedCode")
    private void init() {
        val tokens = fieldName.split("\\.");
        val actions = new ChainAction[tokens.length];
        /*
          true => class
          false => var
         */
        var clazzOrVar = true;
        // TODO: 2022/1/23 重构架构，避免在不生成代码的时候使用unit
        val unit = this.findParent(BDLUnit.class);
        val lookup = unit.getTypeLookup();
        val variableStoreBelongTo = this.findVariableStoreBelongTo();
        for (var i = 0; i < tokens.length; i++) {
            val each = tokens[i];
            if (i == 0) {
                if (each.startsWith("$")) {
                    clazzOrVar = false;
                    val varName = each.substring(1);
                    actions[0] = new ChainAction(ActionType.Var, varName, lookup.lookupClass(
                            variableStoreBelongTo.findVariable(varName).getVariable().getType()
                    ));
                } else {
                    val clazz = unit.getTypeLookup().lookupClass(each);
                    if (clazz == null) {
                        throw new ClassNotFoundException(this.getSourcePos(), each);
                    } else if (clazz.toASMType().getSort() != Type.OBJECT) {
                        throw new ClassUnsupportedException(this.getSourcePos(), each);
                    } else {
                        actions[0] = new ChainAction(ActionType.Class, each, clazz);
                    }
                }
            } else if (i == tokens.length - 1) {
                val fieldName = each.substring(1);
                val fieldInfo = actions[0].getClassInfo().getField(fieldName);
                if (i == 1) { // 一共只有一层
                    if (clazzOrVar) {
                        actions[1] = new ChainAction(ActionType.StaticField, fieldName, fieldInfo.getClassType());
                    } else {
                        actions[1] = new ChainAction(ActionType.VirtualField, fieldName, fieldInfo.getClassType());
                    }
                } else { // 不止一层，说明最后一个肯定是对象字段
                    actions[i] = new ChainAction(ActionType.VirtualField, fieldName, fieldInfo.getClassType());
                }
                this.finalField = fieldInfo;
            } else {
                if (each.startsWith("$")) {
                    val fieldName = each.substring(1);
                    val field = actions[0].getClassInfo().getField(fieldName);
                    val classInfo = field.getClassType();
                    if (i == 1) {
                        if (clazzOrVar) {
                            if (field.isStatic()) {
                                actions[1] = new ChainAction(ActionType.StaticField, fieldName, classInfo);
                            } else {
                                throw new NonStaticFieldException(this.getSourcePos(), each);
                            }
                        } else {
                            actions[1] = new ChainAction(ActionType.VirtualField, fieldName, classInfo);
                        }
                    } else {
                        actions[1] = new ChainAction(ActionType.VirtualField, fieldName, classInfo);
                    }
                    clazzOrVar = false;
                } else {
                    if (i == 1) { // 第一层
                        val methodInfo = findMethodWithoutArg(lookup, actions[0].getClassInfo(), each);
                        val returnClassInfo = methodInfo.getReturnClassType();
                        if (clazzOrVar) {
                            actions[1] = new ChainAction(ActionType.StaticMethod, each, returnClassInfo).setMethodDescriptor(Type.getMethodDescriptor(methodInfo.getReturnASMType(), methodInfo.getArgumentASMTypes()));
                        } else {
                            actions[1] = new ChainAction(ActionType.VirtualMethod, each, returnClassInfo).setMethodDescriptor(Type.getMethodDescriptor(methodInfo.getReturnASMType(), methodInfo.getArgumentASMTypes()));
                        }
                    } else { // 不止一层，说明肯定是对象成员函数
                        val methodInfo = findMethodWithoutArg(lookup, actions[i - 1].getClassInfo(), each);
                        val returnClassInfo = methodInfo.getReturnClassType();
                        actions[i] = new ChainAction(ActionType.VirtualMethod, each, returnClassInfo).setMethodDescriptor(Type.getMethodDescriptor(methodInfo.getReturnASMType(), methodInfo.getArgumentASMTypes()));
                    }
                    clazzOrVar = true;
                }
            }
        }
        this.chainActions = actions;
    }

    @Override
    public ValueType getReturnType() {
        init();
        return ValueType.fromASM(finalField.getASMType());
    }

    @Override
    public ExprCodeGenerator getCodeGenerator() {
        init();
        return new FieldChainExprGenerator(this);
    }

    @Data
    public static class ChainAction {
        private final ActionType actionType;
        private final String content;
        private final ClassInfo classInfo;
        private String methodDescriptor;

        public ChainAction setMethodDescriptor(String methodDescriptor) {
            this.methodDescriptor = methodDescriptor;
            return this;
        }
    }

    public enum ActionType {
        Class, Var, StaticField, VirtualField, StaticMethod, VirtualMethod
    }

    private MethodInfo findMethodWithoutArg(TypeLookup lookup, ClassInfo previous, String methodName) {
        //生成函数名
        val methodInfo = previous.getMethod(methodName, new Type[0]);
        if (methodInfo == null) {
            throw new MethodNotFoundException(this.getSourcePos(), methodName);
        }
        return methodInfo;
    }
}
