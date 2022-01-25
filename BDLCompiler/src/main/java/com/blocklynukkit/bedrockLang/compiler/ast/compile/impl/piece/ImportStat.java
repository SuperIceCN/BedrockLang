package com.blocklynukkit.bedrockLang.compiler.ast.compile.impl.piece;

import com.blocklynukkit.bedrockLang.compiler.ast.compile.Piece;
import com.blocklynukkit.bedrockLang.compiler.ast.compile.StatBase;
import com.blocklynukkit.bedrockLang.compiler.ast.compile.gen.ImportStatGenerator;
import com.blocklynukkit.bedrockLang.compiler.ast.compile.impl.unit.BDLUnit;
import com.blocklynukkit.bedrockLang.compiler.ast.compile.type.TypeLookup;
import com.blocklynukkit.bedrockLang.compiler.ast.util.SourcePos;

public final class ImportStat extends StatBase {
    private final TypeLookup lookup;
    private final String pkgOrClazzName;
    private final String[] methods;

    private ImportStat(SourcePos sourcePos, Piece parent, TypeLookup lookup, String pkgOrClazzName, String[] methods) {
        super(sourcePos, parent);
        this.lookup = lookup;
        this.pkgOrClazzName = pkgOrClazzName;
        this.methods = methods;
    }

    /**
     * 导入一个包下的所有类
     * @param sourcePos 位置
     * @param parent 父单元
     * @param packageName 包名
     */
    public static ImportStat ofPackage(SourcePos sourcePos, BDLUnit parent, String packageName) {
        return new ImportStat(sourcePos, parent, parent.getTypeLookup(), packageName, null);
    }

    /**
     * 导入一个类中的所有静态方法
     * @param sourcePos 位置
     * @param parent 父单元
     * @param className 类名
     */
    public static ImportStat ofAllStaticMethods(SourcePos sourcePos, BDLUnit parent, String className) {
        return new ImportStat(sourcePos, parent, parent.getTypeLookup(), className, new String[0]);
    }

    /**
     * 导入一个类中的指定静态方法
     * @param sourcePos 位置
     * @param parent 父单元
     * @param className 类名
     * @param methodNames 要导入的静态方法名
     */
    public static ImportStat ofSpecificStaticMethods(SourcePos sourcePos, BDLUnit parent, String className, String... methodNames) {
        return new ImportStat(sourcePos, parent, parent.getTypeLookup(), className, methodNames);
    }

    @Override
    public ImportStatGenerator getCodeGenerator() {
        return new ImportStatGenerator(this);
    }

    public TypeLookup getLookup() {
        return this.lookup;
    }

    public String getPkgOrClazzName() {
        return this.pkgOrClazzName;
    }

    public String[] getMethods() {
        return this.methods;
    }
}
