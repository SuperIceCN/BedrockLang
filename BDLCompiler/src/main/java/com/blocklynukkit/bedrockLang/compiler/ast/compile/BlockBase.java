package com.blocklynukkit.bedrockLang.compiler.ast.compile;

import com.blocklynukkit.bedrockLang.compiler.ast.exception.VariableAlreadyExistException;
import com.blocklynukkit.bedrockLang.compiler.ast.util.SourcePos;
import lombok.val;

import java.util.*;

public abstract class BlockBase implements Block {
    protected final Map<String, Variable> variableMap;
    protected final List<Piece> codePieces;
    protected final SourcePos sourcePos;
    protected final Piece pieceParent;
    protected final VariableStore variableStoreParent;
    protected final WeakHashMap<Variable, VariableRecord> variableRecordCache;

    public BlockBase(SourcePos sourcePos, Block parent) {
        this(sourcePos, parent, parent);
    }

    public BlockBase(SourcePos sourcePos, Unit parent) {
        this(sourcePos, parent, parent);
    }

    public BlockBase(SourcePos sourcePos, Piece pieceParent, VariableStore variableStoreParent) {
        this.variableMap = new LinkedHashMap<>();
        this.codePieces = new ArrayList<>();
        this.variableRecordCache = new WeakHashMap<>();
        this.sourcePos = sourcePos;
        this.pieceParent = pieceParent;
        this.variableStoreParent = variableStoreParent;
    }

    @Override
    public Map<String, Variable> getVariables() {
        return this.variableMap;
    }

    @Override
    public List<Piece> getCodePieces() {
        return this.codePieces;
    }

    @Override
    public void addCodePiece(Piece piece) {
        if (piece instanceof Declaration<?>) {
            ((Declaration<?>) piece).declareTo(this);
        }
        this.codePieces.add(piece);
    }

    @Override
    public SourcePos getSourcePos() {
        return this.sourcePos;
    }

    @Override
    public VariableRecord findVariable(String name) {
        if (this.variableMap.containsKey(name)) {
            val var = variableMap.get(name);
            if (variableRecordCache.containsKey(var)) {
                return variableRecordCache.get(var);
            }
            val tmp = makeVariableRecord(var);
            variableRecordCache.put(var, tmp);
            return tmp;
        } else if (variableStoreParent != null) {
            return getVariableStoreParent().findVariable(name);
        } else {
            return null;
        }
    }

    /**
     * 返回变量记录，包含变量及其类型，若为局部变量则包含id
     *
     * @param variable 变量
     * @return 变量记录
     */
    protected abstract VariableRecord makeVariableRecord(Variable variable);

    @Override
    public Piece getPieceParent() {
        return pieceParent;
    }

    @Override
    public void addVariable(Variable variable) {
        if (this.variableMap.containsKey(variable.getName())) {
            throw new VariableAlreadyExistException(variable);
        }
        this.variableMap.put(variable.getName(), variable);
    }
}
