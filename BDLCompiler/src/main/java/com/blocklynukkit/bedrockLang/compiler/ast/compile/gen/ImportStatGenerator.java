package com.blocklynukkit.bedrockLang.compiler.ast.compile.gen;

import com.blocklynukkit.bedrockLang.compiler.ast.compile.StatCodeGenerator;
import com.blocklynukkit.bedrockLang.compiler.ast.compile.Unit;
import com.blocklynukkit.bedrockLang.compiler.ast.compile.impl.piece.ImportStat;
import com.blocklynukkit.bedrockLang.compiler.ast.compile.type.ClassInfo;
import com.blocklynukkit.bedrockLang.compiler.ast.compile.type.MethodInfo;
import com.blocklynukkit.bedrockLang.compiler.ast.compile.type.TypeLookup;
import com.blocklynukkit.bedrockLang.compiler.ast.exception.InvalidImportException;
import org.objectweb.asm.Type;

public final class ImportStatGenerator implements StatCodeGenerator {
    private final ImportStat stat;

    public ImportStatGenerator(ImportStat stat) {
        this.stat = stat;
    }

    @Override
    public Void generate(Unit unit) {
        final String[] methods = stat.getMethods();
        final TypeLookup lookup = stat.getLookup();
        if(methods == null){ //此时应当为导入包
            lookup.importPackage(stat.getPkgOrClazzName());
        }else {
            final Type type = lookup.lookup(stat.getPkgOrClazzName());
            if(methods.length == 0){ //此时应当为导入所有静态方法
                if(type.getSort() == Type.OBJECT){
                    final ClassInfo classInfo = lookup.lookupClass(type.getClassName());
                    for(final MethodInfo each:classInfo.getMethods()){
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
                    final ClassInfo classInfo = lookup.lookupClass(type.getClassName());
                    for(final String me : methods){
                        for(final MethodInfo each:classInfo.getMethodFuzzy(me)){
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
