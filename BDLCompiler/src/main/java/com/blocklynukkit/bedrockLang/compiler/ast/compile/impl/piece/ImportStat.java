package com.blocklynukkit.bedrockLang.compiler.ast.compile.impl.piece;

import com.blocklynukkit.bedrockLang.compiler.ast.compile.Piece;
import com.blocklynukkit.bedrockLang.compiler.ast.compile.StatBase;
import com.blocklynukkit.bedrockLang.compiler.ast.compile.gen.ImportStatGenerator;
import com.blocklynukkit.bedrockLang.compiler.ast.compile.impl.unit.BDLUnit;
import com.blocklynukkit.bedrockLang.compiler.ast.compile.type.TypeLookup;
import com.blocklynukkit.bedrockLang.compiler.ast.util.SourcePos;
import lombok.Getter;
import lombok.NonNull;

public final class ImportStat extends StatBase {
    @Getter
    private final TypeLookup lookup;
    @Getter
    private final String pkgOrClazzName;
    @Getter
    private final String[] methods;

    private ImportStat(@NonNull SourcePos sourcePos, @NonNull Piece parent, TypeLookup lookup, String pkgOrClazzName, String[] methods) {
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
    public static ImportStat ofPackage(@NonNull SourcePos sourcePos, @NonNull BDLUnit parent, @NonNull String packageName) {
        return new ImportStat(sourcePos, parent, parent.getTypeLookup(), packageName, null);
    }

    /**
     * 导入一个类中的所有静态方法
     * @param sourcePos 位置
     * @param parent 父单元
     * @param className 类名
     */
    public static ImportStat ofAllStaticMethods(@NonNull SourcePos sourcePos, @NonNull BDLUnit parent, @NonNull String className) {
        return new ImportStat(sourcePos, parent, parent.getTypeLookup(), className, new String[0]);
    }

    /**
     * 导入一个类中的指定静态方法
     * @param sourcePos 位置
     * @param parent 父单元
     * @param className 类名
     * @param methodNames 要导入的静态方法名
     */
    public static ImportStat ofSpecificStaticMethods(@NonNull SourcePos sourcePos, @NonNull BDLUnit parent, @NonNull String className, @NonNull String... methodNames) {
        return new ImportStat(sourcePos, parent, parent.getTypeLookup(), className, methodNames);
    }

    @Override
    public ImportStatGenerator getCodeGenerator() {
        return new ImportStatGenerator(this);
    }
}
