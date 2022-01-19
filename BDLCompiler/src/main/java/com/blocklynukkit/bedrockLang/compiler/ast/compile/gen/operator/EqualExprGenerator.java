package com.blocklynukkit.bedrockLang.compiler.ast.compile.gen.operator;

import com.blocklynukkit.bedrockLang.compiler.ast.compile.BoxHelper;
import com.blocklynukkit.bedrockLang.compiler.ast.compile.ExprCodeGenerator;
import com.blocklynukkit.bedrockLang.compiler.ast.compile.Unit;
import com.blocklynukkit.bedrockLang.compiler.ast.compile.ValueType;
import com.blocklynukkit.bedrockLang.compiler.ast.compile.gen.BasicCastGenerator;
import com.blocklynukkit.bedrockLang.compiler.ast.compile.impl.piece.operator.EqualExpr;
import com.blocklynukkit.bedrockLang.compiler.ast.compile.impl.type.BasicValueType;
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
public final class EqualExprGenerator implements ExprCodeGenerator, BoxHelper {
    private final EqualExpr expr;

    @Override
    public ValueType generate(Unit unit) {
        val asmUnit = requireASM(unit);
        @NonNull
        val mv = asmUnit.getCurrentMethodVisitor();
        val left = expr.getLeft();
        val right = expr.getRight();
        var leftValueType = expr.getLeft().getReturnType();
        var rightValueType = expr.getRight().getReturnType();
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
            if((isBoxType(leftValueType) && isNumberType(rightValueType)) ||
                    (isNumberType(leftValueType) && isBoxType(rightValueType)) ||
                    (isBoxType(leftValueType) && isBoxType(rightValueType))) {
                //将左表达式生成到操作栈上并拆箱
                left.getCodeGenerator().generate(unit);
                if(isBoxType(leftValueType)){
                    leftValueType = unBox(mv, leftValueType);
                }
                val leftCast = new Label(); //留一个标签用于转换类型
                mv.visitLabel(leftCast);
                //将右表达式生成到操作栈上并拆箱
                right.getCodeGenerator().generate(unit);
                if(isBoxType(rightValueType)){
                    rightValueType = unBox(mv, rightValueType);
                }
                val rightCast = new Label();
                mv.visitLabel(rightCast);
                //计算目标比较类型
                val targetValueType = ValueType.largerNumberType(leftValueType, rightValueType);
                //转换两个操作数的类型
                mv.visitLabel(leftCast);
                if(!leftValueType.equals(targetValueType)){
                    new BasicCastGenerator(leftValueType, targetValueType).generate(unit);
                }else {
                    mv.visitInsn(NOP);
                }
                mv.visitLabel(rightCast);
                if(!rightValueType.equals(targetValueType)){
                    new BasicCastGenerator(rightValueType, targetValueType).generate(unit);
                }else {
                    mv.visitInsn(NOP);
                }
                //生成比较代码
                compare(mv, targetValueType);
            } else { //两个引用类型比较，调用equals
                //将左表达式生成到操作栈上
                left.getCodeGenerator().generate(unit);
                if(isNumberType(leftValueType)){ //基本类型要装箱
                    box(mv, leftValueType);
                }
                //将右表达式生成到操作栈上
                right.getCodeGenerator().generate(unit);
                if(isNumberType(rightValueType)){ //基本类型要装箱
                    box(mv, rightValueType);
                }
                //比较栈上的两个操作对象
                mv.visitMethodInsn(INVOKESTATIC, "java/util/Objects", "equals", "(Ljava/lang/Object;Ljava/lang/Object;)Z", false);
            }
        }
        return expr.getReturnType();
    }

    private void compare(MethodVisitor mv, ValueType targetValueType){
        //生成比较标签和比较后的跳转标签
        val cmpLabel = new Label();
        mv.visitLabel(cmpLabel);
        val trueLabel = new Label();
        mv.visitLabel(trueLabel);
        mv.visitInsn(ICONST_1);
        val jumpAfterFalseLabel = new Label();
        mv.visitLabel(jumpAfterFalseLabel); //这个label用来避免执行false内容
        val falseLabel = new Label();
        mv.visitLabel(falseLabel);
        mv.visitInsn(ICONST_0);
        val finishLabel = new Label(); //照例一个NOP trick，把控制流全部放到一个空标签上避免紊乱
        mv.visitLabel(finishLabel);
        mv.visitInsn(NOP); //只有NOP的标签后期会被优化掉
        mv.visitLabel(jumpAfterFalseLabel); //操作栈上放上1之后就到空标签上面来
        mv.visitJumpInsn(GOTO, falseLabel);
        //分类进行比较并生成比较标签内容
        if (targetValueType == BasicValueType.BOOLEAN ||
                targetValueType == BasicValueType.BYTE ||
                targetValueType == BasicValueType.SHORT ||
                targetValueType == BasicValueType.CHAR ||
                targetValueType == BasicValueType.INT) {
            mv.visitLabel(cmpLabel);
            mv.visitJumpInsn(IF_ICMPNE, falseLabel);
        } else if (targetValueType == BasicValueType.LONG) {
            mv.visitLabel(cmpLabel);
            mv.visitInsn(LCMP);
            mv.visitJumpInsn(IF_ICMPNE, falseLabel);
        } else if (targetValueType == BasicValueType.FLOAT) {
            mv.visitLabel(cmpLabel);
            mv.visitInsn(FCMPL);
            mv.visitJumpInsn(IF_ICMPNE, falseLabel);
        } else if (targetValueType == BasicValueType.DOUBLE) {
            mv.visitLabel(cmpLabel);
            mv.visitInsn(DCMPL);
            mv.visitJumpInsn(IF_ICMPNE, falseLabel);
        }
    }
}
