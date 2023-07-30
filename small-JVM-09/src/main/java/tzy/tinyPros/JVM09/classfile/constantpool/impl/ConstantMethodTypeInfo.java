package tzy.tinyPros.JVM09.classfile.constantpool.impl;

import tzy.tinyPros.JVM09.classfile.ClassReader;
import tzy.tinyPros.JVM09.classfile.constantpool.ConstantInfo;

/**
 * @author TPureZY
 * @since 2023/7/7 2:53
 **/
public class ConstantMethodTypeInfo implements ConstantInfo {

    private int descriptorIdx;
    @Override
    public void readInfo(ClassReader cr) {
        this.descriptorIdx = cr.readU2();
    }

    @Override
    public int tag() {
        return CONSTANT_TAG_METHODTYPE;
    }
}
