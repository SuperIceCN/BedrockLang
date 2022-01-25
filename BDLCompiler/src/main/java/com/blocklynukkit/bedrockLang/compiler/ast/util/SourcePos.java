package com.blocklynukkit.bedrockLang.compiler.ast.util;

import java.util.Objects;

public final class SourcePos implements Comparable<SourcePos> {
    private static int autoInc = 0;
    public static String defaultSourceName = "";

    public String sourceName;
    public int line;
    public int column;

    public SourcePos(String sourceName, int line, int column) {
        this.sourceName = sourceName;
        this.line = line;
        this.column = column;
    }

    @Override
    public String toString() {
        return sourceName + " " + line + ":" + column;
    }

    @Override
    public int compareTo(SourcePos o) {
        if (o.line < this.line) {
            return -1;
        } else if (o.line == this.line) {
            if (o.column == this.column) {
                return 0;
            } else if (o.column < this.column) {
                return -1;
            }
        }
        return 1;
    }

    public static SourcePos auto() {
        return new SourcePos(defaultSourceName, ++autoInc, 0);
    }

    public String getSourceName() {
        return this.sourceName;
    }

    public int getLine() {
        return this.line;
    }

    public int getColumn() {
        return this.column;
    }

    public void setSourceName(String sourceName) {
        this.sourceName = sourceName;
    }

    public void setLine(int line) {
        this.line = line;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof SourcePos)) return false;
        final SourcePos other = (SourcePos) o;
        final Object this$sourceName = this.getSourceName();
        final Object other$sourceName = other.getSourceName();
        if (!Objects.equals(this$sourceName, other$sourceName))
            return false;
        if (this.getLine() != other.getLine()) return false;
        return this.getColumn() == other.getColumn();
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $sourceName = this.getSourceName();
        result = result * PRIME + ($sourceName == null ? 43 : $sourceName.hashCode());
        result = result * PRIME + this.getLine();
        result = result * PRIME + this.getColumn();
        return result;
    }
}
