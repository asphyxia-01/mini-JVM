package tzy.tinyPros.JVM03.classfile.constantpool.impl;

import tzy.tinyPros.JVM03.classfile.ClassReader;
import tzy.tinyPros.JVM03.classfile.constantpool.ConstantInfo;

/**
 * @author TPureZY
 * @since 2023/7/7 1:12
 **/
public class ConstantIntegerInfo implements ConstantInfo {
    private int val;

    @Override
    public void readInfo(ClassReader cr) {
        this.val = cr.readUInt32ToInt();
    }

    @Override
    public int tag() {
        return CONSTANT_TAG_INTEGER;
    }

    public int getVal(){
        return this.val;
    }
}
