package com.blocklynukkit.bedrockLang.compiler.ast.compile.gen.operator;

import com.blocklynukkit.bedrockLang.compiler.ast.compile.BoxHelper;
import com.blocklynukkit.bedrockLang.compiler.ast.compile.ExprCodeGenerator;
import com.blocklynukkit.bedrockLang.compiler.ast.compile.Unit;
import com.blocklynukkit.bedrockLang.compiler.ast.compile.ValueType;
import com.blocklynukkit.bedrockLang.compiler.ast.compile.gen.BasicCastGenerator;
import com.blocklynukkit.bedrockLang.compiler.ast.compile.impl.piece.operator.LowerEqualExpr;
import com.blocklynukkit.bedrockLang.compiler.ast.compile.impl.type.BasicValueType;
import com.blocklynukkit.bedrockLang.compiler.ast.util.CommonClassInfo;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.val;
import lombok.var;
import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;

import static com.blocklynukkit.bedrockLang.compiler.ast.compile.ValueType.isBoxType;
import static com.blocklynukkit.bedrockLang.compiler.ast.compile.ValueType.isNumberType;
import static com.blocklynukkit.bedrockLang.compiler.ast.util.RequireUtils.requireASM;

@RequiredArgsConstructor
public final class LowerEqualExprGenerator implements ExprCodeGenerator, BoxHelper {
    private final LowerEqualExpr expr;

    @SuppressWarnings("DuplicatedCode")
    @Override
    public ValueType generate(Unit unit) {
        val asmUnit = requireASM(unit);
        @NonNull
        val mv = asmUnit.getCurrentMethodVisitor();
        @NonNull
        val lookup = asmUnit.getTypeLookup();
        val left = expr.getLeft();
        val right = expr.getRight();
        //保证拿到的类型是完整的java类名而是不是部分导入的不完整类名，如Integer=>java.lang.Integer
        var leftValueType = ValueType.fromASM(lookup.lookup(expr.getLeft().getReturnType()));
        var rightValueType = ValueType.fromASM(lookup.lookup(expr.getRight().getReturnType()));
        val startLabel = new Label();
        mv.visitLabel(startLabel);
        //判断是否都是基本数字类型或布尔型
        if ((isNumberType(leftValueType) && isNumberType(rightValueType)) ||
                (leftValueType == BasicValueType.BOOLEAN && rightValueType == BasicValueType.BOOLEAN)) {
            val targetValueType = ValueType.largerNumberType(leftValueType, rightValueType);
            //将左表达式生成到操作栈上并转换到目标比较类型
            left.getCodeGenerator().generate(unit);
            new BasicCastGenerator(leftValueType, targetValueType).generate(unit);
            //将右表达式生成到操作栈上并转换到目标比较类型
            right.getCodeGenerator().generate(unit);
            new BasicCastGenerator(rightValueType, targetValueType).generate(unit);
            //生成比较代码
            compare(mv, targetValueType);
        } else { //非基本数字类型比较
            //装箱类型拆箱比较
            if ((isBoxType(leftValueType) && isNumberType(rightValueType)) ||
                    (isNumberType(leftValueType) && isBoxType(rightValueType)) ||
                    (isBoxType(leftValueType) && isBoxType(rightValueType))) {
                //先拆箱为敬
                boolean isLeftBox = false, isRightBox = false;
                val previousLeftValueType = leftValueType;
                val previousRightValueType = rightValueType;
                if (isBoxType(leftValueType)) {
                    leftValueType = unBoxValueType(leftValueType);
                    isLeftBox = true;
                }
                if (isBoxType(rightValueType)) {
                    rightValueType = unBoxValueType(rightValueType);
                    isRightBox = true;
                }
                //计算目标比较类型
                val targetValueType = ValueType.largerNumberType(leftValueType, rightValueType);
                //将左表达式生成到操作栈上并拆箱
                left.getCodeGenerator().generate(unit);
                if (isLeftBox) {
                    unBox(mv, previousLeftValueType);
                }
                if (!leftValueType.equals(targetValueType)) {
                    new BasicCastGenerator(leftValueType, targetValueType).generate(unit);
                }
                //将右表达式生成到操作栈上并拆箱
                right.getCodeGenerator().generate(unit);
                if (isRightBox) {
                    unBox(mv, previousRightValueType);
                }
                if (!rightValueType.equals(targetValueType)) {
                    new BasicCastGenerator(rightValueType, targetValueType).generate(unit);
                }
                //生成比较代码
                compare(mv, targetValueType);
            } else if(isNumberType(leftValueType) || //两个引用类型比较，如果左表达式类型实现了Comparable，调用compareTo
                    lookup.lookupClass(leftValueType).hasImplementInterface(CommonClassInfo.Comparable)) {
                //将左表达式生成到操作栈上
                left.getCodeGenerator().generate(unit);
                if (isNumberType(leftValueType)) { //基本类型要装箱
                    box(mv, leftValueType);
                }
                //将右表达式生成到操作栈上
                right.getCodeGenerator().generate(unit);
                if (isNumberType(rightValueType)) { //基本类型要装箱
                    box(mv, rightValueType);
                }
                val clz = lookup.lookupClass(leftValueType).matchClassImplementedInterface(CommonClassInfo.Comparable);
                //生成比较标签和比较后的跳转标签
                val finishLabel = new Label(); //照例一个NOP trick，把控制流全部放到一个空标签上避免紊乱
                val trueLabel = new Label();
                val cmpLabel = new Label();
                mv.visitLabel(cmpLabel);
                //比较栈上的两个操作对象
                mv.visitMethodInsn(INVOKEVIRTUAL, clz.getQualifiedName(), "compareTo", "(Ljava/lang/String;)I", false);
                mv.visitJumpInsn(IFLE, trueLabel); // 小于等于零
                mv.visitInsn(ICONST_0);
                mv.visitJumpInsn(GOTO, finishLabel); //不要执行trueLabel

                mv.visitLabel(trueLabel);
                mv.visitInsn(ICONST_1);

                mv.visitLabel(finishLabel);
                mv.visitInsn(NOP); //只有NOP的标签后期会被优化掉
            }
        }
        return expr.getReturnType();
    }

    /*
     * 这里有个巨大的天坑，记得使用两个数字的双比较绝对不能做一个Label的结尾
     */
    private void compare(MethodVisitor mv, ValueType targetValueType) {
        //生成比较标签和比较后的跳转标签
        val finishLabel = new Label(); //照例一个NOP trick，把控制流全部放到一个空标签上避免紊乱
        val trueLabel = new Label();
        val cmpLabel = new Label();
        mv.visitLabel(cmpLabel);
        //分类进行比较并生成比较标签内容
        if (targetValueType == BasicValueType.BOOLEAN ||
                targetValueType == BasicValueType.BYTE ||
                targetValueType == BasicValueType.SHORT ||
                targetValueType == BasicValueType.CHAR ||
                targetValueType == BasicValueType.INT) {
            mv.visitJumpInsn(IF_ICMPLE, trueLabel);
        } else if (targetValueType == BasicValueType.LONG) {
            mv.visitInsn(LCMP);
            mv.visitJumpInsn(IFLE, trueLabel);
        } else if (targetValueType == BasicValueType.FLOAT) {
            mv.visitInsn(FCMPL);
            mv.visitJumpInsn(IFLE, trueLabel);
        } else if (targetValueType == BasicValueType.DOUBLE) {
            mv.visitInsn(DCMPL);
            mv.visitJumpInsn(IFLE, trueLabel);
        }
        mv.visitInsn(ICONST_0);
        mv.visitJumpInsn(GOTO, finishLabel); //不要执行trueLabel

        mv.visitLabel(trueLabel);
        mv.visitInsn(ICONST_1);

        mv.visitLabel(finishLabel);
        mv.visitInsn(NOP); //只有NOP的标签后期会被优化掉
    }
}
