package tzy.tinyPros.JVM08.classfile.constantpool.impl;

import tzy.tinyPros.JVM08.classfile.ClassReader;
import tzy.tinyPros.JVM08.classfile.constantpool.ConstantInfo;
import tzy.tinyPros.JVM08.classfile.constantpool.ConstantPool;

/**
 * @author TPureZY
 * @since 2023/7/7 2:58
 **/
public class ConstantStringInfo implements ConstantInfo {

    private int strIdx;
    private ConstantPool cp;

    public ConstantStringInfo(ConstantPool cp){
        this.cp = cp;
    }

    @Override
    public void readInfo(ClassReader cr) {
        this.strIdx = cr.readU2();
    }

    @Override
    public int tag() {
        return CONSTANT_TAG_STRING;
    }

    public String getStr() {
        return this.cp.getUtf8(this.strIdx);
    }
}
