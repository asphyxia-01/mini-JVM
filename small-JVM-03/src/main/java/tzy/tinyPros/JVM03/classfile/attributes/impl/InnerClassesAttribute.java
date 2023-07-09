package tzy.tinyPros.JVM03.classfile.attributes.impl;

import tzy.tinyPros.JVM03.classfile.ClassReader;
import tzy.tinyPros.JVM03.classfile.attributes.AttributeInfo;
import tzy.tinyPros.JVM03.classfile.attributes.BaseAttributeInfoElements;

/**
 * @author TPureZY
 * @since 2023/7/9 1:50
 * <p>
 * 内部类列表
 **/
public class InnerClassesAttribute extends BaseAttributeInfoElements implements AttributeInfo {

    private InnerClassInfo[] infos;

    public InnerClassesAttribute(String attrName, int attrLen) {
        super(attrName, attrLen);
    }

    @Override
    public void readInfo(ClassReader cr) {
        int cnt = cr.readU2();
        this.infos = new InnerClassInfo[cnt];
        for (int i = 0; i < cnt; i++) {
            this.infos[i] = new InnerClassInfo(cr.readU2(), cr.readU2(), cr.readU2(), cr.readU2());
        }
    }

    static class InnerClassInfo {
        private int innerClassInfoIdx;
        private int outerClassInfoIdx;
        private int innerNameIdx;
        private int innerClassAccessFlags;

        InnerClassInfo(int var1, int var2, int var3, int var4) {
            this.innerClassInfoIdx = var1;
            this.outerClassInfoIdx = var2;
            this.innerNameIdx = var3;
            this.innerClassAccessFlags = var4;
        }
    }
}
