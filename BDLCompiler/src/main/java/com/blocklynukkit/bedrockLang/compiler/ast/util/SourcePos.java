package com.blocklynukkit.bedrockLang.compiler.ast.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;

@Data
@AllArgsConstructor
public final class SourcePos implements Comparable<SourcePos> {
    private static int autoInc = 0;
    public static String defaultSourceName = "";

    public String sourceName;
    public int line;
    public int column;

    @Override
    public String toString() {
        return sourceName + " " + line + ":" + column;
    }

    @Override
    public int compareTo(@NonNull SourcePos o) {
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
}
