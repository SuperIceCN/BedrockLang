package com.blocklynukkit.bedrockLang.compiler.ast.compile.gen;

import com.blocklynukkit.bedrockLang.compiler.ast.compile.StatCodeGenerator;
import com.blocklynukkit.bedrockLang.compiler.ast.compile.Unit;
import com.blocklynukkit.bedrockLang.compiler.ast.compile.impl.piece.ImportStat;
import com.blocklynukkit.bedrockLang.compiler.ast.exception.InvalidImportException;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.objectweb.asm.Type;

@RequiredArgsConstructor
public final class ImportStatGenerator implements StatCodeGenerator {
    private final ImportStat stat;

    @Override
    public Void generate(Unit unit) {
        val methods = stat.getMethods();
        val lookup = stat.getLookup();
        if(methods == null){ //此时应当为导入包
            lookup.importPackage(stat.getPkgOrClazzName());
        }else {
            val type = lookup.lookup(stat.getPkgOrClazzName());
            if(methods.length == 0){ //此时应当为导入所有静态方法
                if(type.getSort() == Type.OBJECT){
                    val classInfo = lookup.findFromClassPool(type.getClassName());
                    for(val each:classInfo.getMethods()){
                        if(each.isStatic()){
                            lookup.importStaticMethod(each);
                        }
                        // TODO: 2022/1/10 导入单例方法
                    }
                }else {
                    throw new InvalidImportException(stat.getSourcePos(), stat.getPkgOrClazzName());
                }
            }else {
                // 此时应当为导入指定静态方法
                if(type.getSort() == Type.OBJECT){
                    val classInfo = lookup.findFromClassPool(type.getClassName());
                    for(val me : methods){
                        for(val each:classInfo.getMethodFuzzy(me)){
                            if(each.isStatic()){
                                lookup.importStaticMethod(each);
                            }
                            // TODO: 2022/1/10 导入单例方法
                        }
                    }
                }else {
                    throw new InvalidImportException(stat.getSourcePos(), stat.getPkgOrClazzName());
                }
            }
        }
        return null;
    }
}
