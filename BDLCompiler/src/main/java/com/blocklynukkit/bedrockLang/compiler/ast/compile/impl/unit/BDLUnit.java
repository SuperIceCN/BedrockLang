package com.blocklynukkit.bedrockLang.compiler.ast.compile.impl.unit;

import com.blocklynukkit.bedrockLang.compiler.ast.compile.*;
import com.blocklynukkit.bedrockLang.compiler.ast.compile.gen.BDLUnitCompiler;
import com.blocklynukkit.bedrockLang.compiler.ast.compile.type.BDLMethodInfo;
import com.blocklynukkit.bedrockLang.compiler.ast.compile.type.TypeLookup;
import com.blocklynukkit.bedrockLang.compiler.ast.exception.CommandAlreadyExistException;
import com.blocklynukkit.bedrockLang.compiler.ast.exception.VariableAlreadyExistException;
import com.blocklynukkit.bedrockLang.compiler.ast.util.SourcePos;
import lombok.val;
import org.objectweb.asm.*;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static org.objectweb.asm.ClassWriter.COMPUTE_FRAMES;
import static org.objectweb.asm.ClassWriter.COMPUTE_MAXS;

public class BDLUnit implements Unit, GenerateWithASM, Opcodes {
    private final String name;
    private final String sourceName;

    public final ClassWriter bdlClassWriter;
    private MethodVisitor currentMethodVisitor = null;
    public final TypeLookup typeLookup;

    private final Map<String, Variable> variableMap;
    private final Map<String, List<Command>> commandMap;
    protected final List<Piece> codePieces;

    public BDLUnit(String name, String sourceName) {
        this.name = name;
        this.sourceName = sourceName;
        this.bdlClassWriter = new ClassWriter(COMPUTE_FRAMES); //让asm自动计算方法栈帧和操作数栈大小
        this.variableMap = new LinkedHashMap<>();
        this.commandMap = new LinkedHashMap<>();
        this.codePieces = new ArrayList<>();
        this.typeLookup = new TypeLookup(this);
        this.typeLookup.importPackage("java.lang");
        this.typeLookup.addBDLClass(this);
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public Map<String, Variable> getUnitVariables() {
        return this.variableMap;
    }

    @Override
    public Map<String, List<Command>> getUnitCommands() {
        return this.commandMap;
    }

    @Override
    public TypeLookup getTypeLookup() {
        return this.typeLookup;
    }

    @Override
    public void addVariable(Variable variable) {
        if (this.variableMap.containsKey(variable.getName())) {
            throw new VariableAlreadyExistException(variable);
        }
        this.variableMap.put(variable.getName(), variable);
    }

    @Override
    public void addCommand(Command command) {
        val list = this.commandMap.get(command.getName());
        if (list == null) {
            val newList = new ArrayList<Command>();
            newList.add(command);
            this.commandMap.put(command.getName(), newList);
        } else {
            if (list.contains(command)) {
                throw new CommandAlreadyExistException(command);
            } else {
                list.add(command);
            }
        }
    }

    @Override
    public VariableStore getVariableStoreParent() {
        return null;
    }

    @Override
    public VariableRecord findVariable(String name) {
        return new VariableRecord(this.variableMap.get(name), VariableRecord.VariableType.UNIT_GLOBAL, VariableRecord.NONLOCAL_INDEX);
    }

    @Override
    public SourcePos getSourcePos() {
        return new SourcePos(sourceName, 0, 0);
    }

    @Override
    public boolean hasReturnType() {
        return false;
    }

    @Override
    public ValueType getReturnType() {
        return null;
    }

    @Override
    public Piece getPieceParent() {
        return null;
    }

    @Override
    public BDLUnitCompiler getCodeGenerator() {
        return new BDLUnitCompiler();
    }

    @Override
    public Map<String, Variable> getVariables() {
        return this.getUnitVariables();
    }

    @Override
    public List<Piece> getCodePieces() {
        return this.codePieces;
    }

    @Override
    public void addCodePiece(Piece piece) {
        if (piece instanceof Declaration<?>) {
            val res = ((Declaration<?>) piece).declareTo(this);
            // 如果是命令就直接导入
            if (res instanceof Command) {
                this.typeLookup.importStaticMethod(new BDLMethodInfo((Command) res, typeLookup));
            }
        }
        this.codePieces.add(piece);
    }

    @Override
    public ClassWriter getClassWriter() {
        return bdlClassWriter;
    }

    @Override
    public void setCurrentMethodVisitor(MethodVisitor methodVisitor) {
        this.currentMethodVisitor = methodVisitor;
    }

    @Override
    public MethodVisitor getCurrentMethodVisitor() {
        return this.currentMethodVisitor;
    }
}
