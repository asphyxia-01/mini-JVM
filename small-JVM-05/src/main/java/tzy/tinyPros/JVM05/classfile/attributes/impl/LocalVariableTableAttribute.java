package tzy.tinyPros.JVM05.classfile.attributes.impl;

import tzy.tinyPros.JVM05.classfile.ClassReader;
import tzy.tinyPros.JVM05.classfile.attributes.AttributeInfo;
import tzy.tinyPros.JVM05.classfile.attributes.BaseAttributeInfoElements;

/**
 * @author TPureZY
 * @since 2023/7/9 2:16
 * <p>
 * 方法的局部变量描述
 **/
public class LocalVariableTableAttribute extends BaseAttributeInfoElements implements AttributeInfo {

    private LocalVariableEntry[] entries;

    public LocalVariableTableAttribute(String attrName, int attrLen) {
        super(attrName, attrLen);
    }

    @Override
    public void readInfo(ClassReader cr) {
        int cnt = cr.readU2();
        this.entries = new LocalVariableEntry[cnt];
        for (int i = 0; i < cnt; i++) {
            this.entries[i] = new LocalVariableEntry(cr.readU2(), cr.readU2(), cr.readU2(), cr.readU2(), cr.readU2());
            // TODO: 还需要判断是否是Long或者Double类型，如果是，则占用两个索引，需要先i++再进入下一个循环
        }
    }

    static class LocalVariableEntry {
        /**
         * The given local variable must have a value at indices into the code array in the interval [start_pc, start_pc + length), that is, between start_pc inclusive and start_pc + length exclusive
         */
        private int startPC;
        private int length;

        private int nameIdx;
        private int descriptorIdx;
        /**
         * The given local variable must be at index in the local variable array of the current frame.
         * <p>
         * If the local variable at index is of type double or long, it occupies both index and index + 1.
         */
        private int index;

        LocalVariableEntry(int var0, int var1, int var2, int var3, int var4) {
            this.startPC = var0;
            this.length = var1;
            this.nameIdx = var2;
            this.descriptorIdx = var3;
            this.index = var4;
        }

    }
}
