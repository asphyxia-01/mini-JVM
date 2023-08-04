package tzy.tinyPros.jvm.rtda.heap.methodarea;

import tzy.tinyPros.parser.attributes.impl.LineNumberTableAttribute;

/**
 * @author TPureZY
 * @since 2023/8/4 19:52
 **/
public class LineNumberTable {

    private final LineNumberPair[] lineNumberPairs;

    public LineNumberTable(LineNumberTableAttribute attribute) {
        LineNumberTableAttribute.LineNumberEntry[] entries = attribute.getEntries();
        this.lineNumberPairs = new LineNumberPair[entries.length];
        for (int i = 0; i < entries.length; i++) {
            this.lineNumberPairs[i] = new LineNumberPair(entries[i].startPC, entries[i].lineNumber);
        }
    }

    public int findLineNumberByPC(int happenedPC) {
        int l = 0, r = this.lineNumberPairs.length - 1;
        int ans = -1;
        while (l < r) {
            int mid = l + ((r - l) >> 1);
            int startPC = this.lineNumberPairs[mid].startPC;
            if (startPC <= happenedPC) {
                ans = this.lineNumberPairs[mid].lineNumber;
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }
        return ans;
    }


    private class LineNumberPair {
        private final int startPC;
        private final int lineNumber;

        private LineNumberPair(int startPC, int lineNumber) {
            this.startPC = startPC;
            this.lineNumber = lineNumber;
        }

        private int pc2LineNumber(int pc) {
            if (pc >= this.startPC) {
                return this.lineNumber;
            }
            return -1;
        }
    }

}
