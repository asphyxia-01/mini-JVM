package tzy.tinyPros.JVM09.classfile.constantpool.impl;

import tzy.tinyPros.JVM09.classfile.ClassReader;
import tzy.tinyPros.JVM09.classfile.constantpool.ConstantInfo;

/**
 * @author TPureZY
 * @since 2023/7/7 2:51
 *
 *
 **/
public class ConstantMethodHandleInfo implements ConstantInfo {

    private int referenceKind;
    private int referenceIdx;

    @Override
    public void readInfo(ClassReader cr) {
        this.referenceKind = cr.readU1();
        this.referenceIdx = cr.readU2();
    }

    @Override
    public int tag() {
        return CONSTANT_TAG_METHODHANDLE;
    }
}
