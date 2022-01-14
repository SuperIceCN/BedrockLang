package com.blocklynukkit.bedrockLang.compiler.ast.compile.impl.piece;

import com.blocklynukkit.bedrockLang.compiler.ast.compile.*;
import com.blocklynukkit.bedrockLang.compiler.ast.compile.gen.MethodInvokeExprGenerator;
import com.blocklynukkit.bedrockLang.compiler.ast.compile.impl.unit.BDLUnit;
import com.blocklynukkit.bedrockLang.compiler.ast.compile.type.ClassInfo;
import com.blocklynukkit.bedrockLang.compiler.ast.compile.type.MethodInfo;
import com.blocklynukkit.bedrockLang.compiler.ast.compile.type.TypeLookup;
import com.blocklynukkit.bedrockLang.compiler.ast.exception.MethodNotFoundException;
import com.blocklynukkit.bedrockLang.compiler.ast.util.SourcePos;
import lombok.*;
import org.objectweb.asm.Type;

import java.util.Arrays;

/**
 * MethodInvokeExpr是针对多级方法调用的表达式，与MethodCallExpr相似，都允许以命令的形式调用方法。
 * 明显不同的是，Invoke针对多级嵌套的调用，而Call只能针对当前类中声明的静态方法和导入的静态方法。
 * <br/>
 * MethodInvokeExpr的用例主要如下：<br/>
 * 1. 用于调用某个对象的成员函数 <code>$person.say "I'm sb.";</code> <br/>
 * 2. 用于调用某个类的静态字段的方法 <code>System.$out.println "hi bdl";</code> <br/>
 * 3. 用于多级调用 <code>$a.$b.$c.foo ;</code> <br/>
 * 4. 用于链式地调用没有参数有返回值的方法 <code>$a.setATrue.setBTrue</code> <br/>
 * <br/>
 * 提示：MethodInvokeExpr不能用于调用在同一{@link Unit}中定义的命令，不能调用{@link ImportStat}导入的静态方法
 * @see MethodInvokeExprGenerator
 * @see MethodCallExpr
 */
public final class MethodInvokeExpr extends ExprBase {
    @NonNull
    @Getter
    private final String commandName;
    @Getter
    @Setter
    @NonNull
    private Expr[] args;
    private MethodInfo finalMethod = null;
    @Getter
    private ChainAction[] chainActions = null;

    public MethodInvokeExpr(@NonNull SourcePos sourcePos, @NonNull Piece parent, @NonNull String commandName, Expr... args) {
        super(sourcePos, parent);
        this.commandName = commandName;
        if (args != null) {
            this.args = args;
        } else {
            this.args = new Expr[0];
        }
    }

    private void init() {
        val tokens = commandName.split("\\.");
        val actions = new ChainAction[tokens.length];
        /*
          true => class
          false => var
         */
        var clazzOrVar = true;
        // TODO: 2022/1/12 重构架构，避免在不生成代码的时候使用unit
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
                        // TODO: 2022/1/12 抛出找不到类错误
                    } else if (clazz.toASMType().getSort() != Type.OBJECT) {
                        // TODO: 2022/1/12 抛出无法导入基本类型错误
                    } else {
                        actions[0] = new ChainAction(ActionType.Class, each, clazz);
                    }
                }
            }
            // FIXME: 2022/1/12 这里逻辑错误，分类讨论重漏
            else if (i == tokens.length - 1) {
                MethodInfo methodInfo;
                if (i == 1) { // 一共只有一层
                    methodInfo = findMethod(lookup, actions[0].getClassInfo(), each);
                    val returnClassInfo = methodInfo.getReturnClassType();
                    if (clazzOrVar) {
                        actions[1] = new ChainAction(ActionType.StaticMethod, each, returnClassInfo).setMethodDescriptor(Type.getMethodDescriptor(methodInfo.getReturnASMType(), methodInfo.getArgumentASMTypes()));
                    } else {
                        actions[1] = new ChainAction(ActionType.VirtualMethod, each, returnClassInfo).setMethodDescriptor(Type.getMethodDescriptor(methodInfo.getReturnASMType(), methodInfo.getArgumentASMTypes()));
                    }
                } else { // 不止一层，说明最后一个肯定是对象成员函数
                    methodInfo = findMethod(lookup, actions[i - 1].getClassInfo(), each);
                    val returnClassInfo = methodInfo.getReturnClassType();
                    actions[i] = new ChainAction(ActionType.VirtualMethod, each, returnClassInfo).setMethodDescriptor(Type.getMethodDescriptor(methodInfo.getReturnASMType(), methodInfo.getArgumentASMTypes()));
                }
                this.finalMethod = methodInfo;
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
                                // TODO: 2022/1/14 抛出非静态成员变量错误
                            }
                        } else {
                            actions[1] = new ChainAction(ActionType.VirtualField, fieldName, classInfo);
                        }
                    } else {
                        actions[1] = new ChainAction(ActionType.VirtualField, fieldName, classInfo);
                    }
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
                }
            }
        }
        this.chainActions = actions;
    }

    @Override
    public ValueType getReturnType() {
        init();
        return ValueType.fromASM(finalMethod.getReturnASMType());
    }

    @Override
    public ExprCodeGenerator getCodeGenerator() {
        init();
        return new MethodInvokeExprGenerator(this);
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

    private MethodInfo findMethod(TypeLookup lookup, ClassInfo previous, String methodName) {
        //生成函数名
        val sb = new StringBuilder(methodName);
        var firstWord = true;
        for (val e : args) {
            if (e instanceof CommandArgWordExpr) {
                if (firstWord) {
                    sb.append("$").append(((CommandArgWordExpr) e).getWord());
                    firstWord = false;
                } else {
                    sb.append("_").append(((CommandArgWordExpr) e).getWord());
                }
            }
        }
        val tmp = Arrays.stream(this.args).map(expr -> lookup.lookup(expr.getReturnType())).toArray(Type[]::new);
        val methodInfo = previous.getMethod(sb.toString(), tmp);
        if (methodInfo == null) {
            throw new MethodNotFoundException(this.getSourcePos(), methodName);
        }
        return methodInfo;
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
