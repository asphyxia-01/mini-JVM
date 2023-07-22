package tzy.tinyPros.JVM08.classfile.attributes.impl;

import tzy.tinyPros.JVM08.classfile.ClassReader;
import tzy.tinyPros.JVM08.classfile.attributes.AttributeInfo;
import tzy.tinyPros.JVM08.classfile.attributes.BaseAttributeInfoElements;

/**
 * @author TPureZY
 * @since 2023/7/9 2:02
 * <p>
 * Java源码的行号与字节码指令的对应关系
 **/
public class LineNumberTableAttribute extends BaseAttributeInfoElements implements AttributeInfo {

    private LineNumberEntry[] entries;

    public LineNumberTableAttribute(String attrName, int attrLen) {
        super(attrName, attrLen);
    }

    @Override
    public void readInfo(ClassReader cr) {
        int cnt = cr.readU2();
        this.entries = new LineNumberEntry[cnt];
        for (int i = 0; i < cnt; i++) {
            this.entries[i] = new LineNumberEntry(cr.readU2(), cr.readU2());
        }
    }

    public int getLineNumeber(int pc) {
        // 一行代码可能对应多个汇编指令，即多个PC对应同一行
        for (int i = this.entries.length - 1; i >= 0; i--) {
            if (pc >= this.entries[i].startPC) {
                return this.entries[i].lineNumber;
            }
        }
        return -1;
    }

    /**
     * LineNumberEntry记录的行号和PC的对应关系如下
     * <p>
     * lineNumber不重复出现，由于可能多个PC对应同一行，所以仅记录某一行多个PC中开始的那个PC，由于PC递增的，那么这一行实际对应的PC是startPC及后的PC，而这些的PC的对应关系不再另外创建LineNumberEntry存储
     */
    static class LineNumberEntry {
        int startPC;
        int lineNumber;

        LineNumberEntry(int var0, int var1) {
            this.startPC = var0;
            this.lineNumber = var1;
        }
    }
}
