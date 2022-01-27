package com.blocklynukkit.bedrockLang.compiler.ast.compile.impl.piece;

import com.blocklynukkit.bedrockLang.compiler.ast.compile.*;
import com.blocklynukkit.bedrockLang.compiler.ast.compile.gen.MethodInvokeExprGenerator;
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
import org.objectweb.asm.Type;

import java.util.Arrays;
import java.util.Objects;

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
 *
 * @see MethodInvokeExprGenerator
 * @see MethodCallExpr
 */
public final class MethodInvokeExpr extends ExprBase {
    private final String commandName;
    private Expr[] args;
    private MethodInfo finalMethod = null;
    private ChainAction[] chainActions = null;

    public MethodInvokeExpr(SourcePos sourcePos, Piece parent, String commandName, Expr... args) {
        super(sourcePos, parent);
        this.commandName = commandName;
        if (args != null) {
            this.args = args;
        } else {
            this.args = new Expr[0];
        }
    }

    private void init() {
        final String[] tokens = commandName.split("\\.");
        final ChainAction[] actions = new ChainAction[tokens.length];
        /*
          true => class
          false => var
         */
        boolean clazzOrVar = true;
        // TODO: 2022/1/12 重构架构，避免在不生成代码的时候使用unit
        final BDLUnit unit = this.findParent(BDLUnit.class);
        final TypeLookup lookup = unit.getTypeLookup();
        final VariableStore variableStoreBelongTo = this.findVariableStoreBelongTo();
        for (int i = 0; i < tokens.length; i++) {
            final String each = tokens[i];
            if (i == 0) { //最外层
                if (each.startsWith("$")) {
                    clazzOrVar = false;
                    final String varName = each.substring(1);
                    actions[0] = new ChainAction(ActionType.Var, varName, lookup.lookupClass(
                            variableStoreBelongTo.findVariable(varName).getVariable().getType()
                    ));
                } else {
                    final ClassInfo clazz = unit.getTypeLookup().lookupClass(each);
                    if (clazz == null) {
                        throw new ClassNotFoundException(this.getSourcePos(), each);
                    } else if (clazz.toASMType().getSort() != Type.OBJECT) {
                        throw new ClassUnsupportedException(this.getSourcePos(), each);
                    } else {
                        actions[0] = new ChainAction(ActionType.Class, each, clazz);
                    }
                }
            } else if (i == tokens.length - 1) { //最后一层，肯定是方法调用
                MethodInfo methodInfo;
                if (i == 1) { // 不计最外层一共只有一层
                    methodInfo = findMethod(lookup, actions[0].getClassInfo(), each);
                    final ClassInfo returnClassInfo = methodInfo.getReturnClassType();
                    if (clazzOrVar) {
                        actions[1] = new ChainAction(ActionType.StaticMethod, each, returnClassInfo).setMethodDescriptor(Type.getMethodDescriptor(methodInfo.getReturnASMType(), methodInfo.getArgumentASMTypes()));
                    } else {
                        actions[1] = new ChainAction(ActionType.VirtualMethod, each, returnClassInfo).setMethodDescriptor(Type.getMethodDescriptor(methodInfo.getReturnASMType(), methodInfo.getArgumentASMTypes()));
                    }
                } else { // 不止一层，说明最后一个肯定是对象成员函数
                    methodInfo = findMethod(lookup, actions[i - 1].getClassInfo(), each);
                    final ClassInfo returnClassInfo = methodInfo.getReturnClassType();
                    actions[i] = new ChainAction(ActionType.VirtualMethod, each, returnClassInfo).setMethodDescriptor(Type.getMethodDescriptor(methodInfo.getReturnASMType(), methodInfo.getArgumentASMTypes()));
                }
                this.finalMethod = methodInfo;
            } else {
                if (each.startsWith("$")) {
                    final String fieldName = each.substring(1);
                    final FieldInfo field = actions[0].getClassInfo().getField(fieldName);
                    final ClassInfo classInfo = field.getClassType();
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
                        final MethodInfo methodInfo = findMethodWithoutArg(actions[0].getClassInfo(), each);
                        final ClassInfo returnClassInfo = methodInfo.getReturnClassType();
                        if (clazzOrVar) {
                            actions[1] = new ChainAction(ActionType.StaticMethod, each, returnClassInfo).setMethodDescriptor(Type.getMethodDescriptor(methodInfo.getReturnASMType(), methodInfo.getArgumentASMTypes()));
                        } else {
                            actions[1] = new ChainAction(ActionType.VirtualMethod, each, returnClassInfo).setMethodDescriptor(Type.getMethodDescriptor(methodInfo.getReturnASMType(), methodInfo.getArgumentASMTypes()));
                        }
                    } else { // 不止一层，说明肯定是对象成员函数
                        final MethodInfo methodInfo = findMethodWithoutArg(actions[i - 1].getClassInfo(), each);
                        final ClassInfo returnClassInfo = methodInfo.getReturnClassType();
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
        return ValueType.fromASM(finalMethod.getReturnASMType());
    }

    @Override
    public ExprCodeGenerator getCodeGenerator() {
        init();
        return new MethodInvokeExprGenerator(this);
    }

    public String getCommandName() {
        return this.commandName;
    }

    public Expr[] getArgs() {
        return this.args;
    }

    public ChainAction[] getChainActions() {
        return this.chainActions;
    }

    public void setArgs(Expr[] args) {
        this.args = args;
    }

    public static class ChainAction {
        private final ActionType actionType;
        private final String content;
        private final ClassInfo classInfo;
        private String methodDescriptor;

        public ChainAction(ActionType actionType, String content, ClassInfo classInfo) {
            this.actionType = actionType;
            this.content = content;
            this.classInfo = classInfo;
        }

        public ChainAction setMethodDescriptor(String methodDescriptor) {
            this.methodDescriptor = methodDescriptor;
            return this;
        }

        public ActionType getActionType() {
            return this.actionType;
        }

        public String getContent() {
            return this.content;
        }

        public ClassInfo getClassInfo() {
            return this.classInfo;
        }

        public String getMethodDescriptor() {
            return this.methodDescriptor;
        }

        public boolean equals(final Object o) {
            if (o == this) return true;
            if (!(o instanceof ChainAction))
                return false;
            final ChainAction other = (ChainAction) o;
            if (!other.canEqual(this)) return false;
            final Object this$actionType = this.getActionType();
            final Object other$actionType = other.getActionType();
            if (!Objects.equals(this$actionType, other$actionType))
                return false;
            final Object this$content = this.getContent();
            final Object other$content = other.getContent();
            if (!Objects.equals(this$content, other$content)) return false;
            final Object this$classInfo = this.getClassInfo();
            final Object other$classInfo = other.getClassInfo();
            if (!Objects.equals(this$classInfo, other$classInfo))
                return false;
            final Object this$methodDescriptor = this.getMethodDescriptor();
            final Object other$methodDescriptor = other.getMethodDescriptor();
            return Objects.equals(this$methodDescriptor, other$methodDescriptor);
        }

        protected boolean canEqual(final Object other) {
            return other instanceof ChainAction;
        }

        public int hashCode() {
            final int PRIME = 59;
            int result = 1;
            final Object $actionType = this.getActionType();
            result = result * PRIME + ($actionType == null ? 43 : $actionType.hashCode());
            final Object $content = this.getContent();
            result = result * PRIME + ($content == null ? 43 : $content.hashCode());
            final Object $classInfo = this.getClassInfo();
            result = result * PRIME + ($classInfo == null ? 43 : $classInfo.hashCode());
            final Object $methodDescriptor = this.getMethodDescriptor();
            result = result * PRIME + ($methodDescriptor == null ? 43 : $methodDescriptor.hashCode());
            return result;
        }

        public String toString() {
            return "MethodInvokeExpr.ChainAction(actionType=" + this.getActionType() + ", content=" + this.getContent() + ", classInfo=" + this.getClassInfo() + ", methodDescriptor=" + this.getMethodDescriptor() + ")";
        }
    }

    public enum ActionType {
        Class, Var, StaticField, VirtualField, StaticMethod, VirtualMethod
    }

    private MethodInfo findMethod(TypeLookup lookup, ClassInfo previous, String methodName) {
        //生成函数名
        final StringBuilder sb = new StringBuilder(methodName);
        boolean firstWord = true;
        for (final Expr e : args) {
            if (e instanceof CommandArgWordExpr) {
                if (firstWord) {
                    sb.append("$").append(((CommandArgWordExpr) e).getWord());
                    firstWord = false;
                } else {
                    sb.append("_").append(((CommandArgWordExpr) e).getWord());
                }
            }
        }
        final ClassInfo[] tmp = Arrays.stream(this.args).map(expr -> lookup.lookupClass(expr.getReturnType())).toArray(ClassInfo[]::new);
        final MethodInfo methodInfo = previous.getMethod(sb.toString(), tmp);
        if (methodInfo == null) {
            throw new MethodNotFoundException(this.getSourcePos(), methodName);
        }
        return methodInfo;
    }

    private MethodInfo findMethodWithoutArg(ClassInfo previous, String methodName) {
        //生成函数名
        final MethodInfo methodInfo = previous.getMethod(methodName, new ClassInfo[0]);
        if (methodInfo == null) {
            throw new MethodNotFoundException(this.getSourcePos(), methodName);
        }
        return methodInfo;
    }
}
