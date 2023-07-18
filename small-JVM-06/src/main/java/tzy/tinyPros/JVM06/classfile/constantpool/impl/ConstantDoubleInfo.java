package tzy.tinyPros.JVM06.classfile.constantpool.impl;

import tzy.tinyPros.JVM06.classfile.ClassReader;
import tzy.tinyPros.JVM06.classfile.constantpool.ConstantInfo;

/**
 * @author TPureZY
 * @since 2023/7/7 0:36
 **/
public class ConstantDoubleInfo implements ConstantInfo {

    /**
     * 在JVM中最大的类型是u4，而double占用8个字节，所以由两个u4组成（使用IEEE754编码），分别是 {u4 highByte; u4 lowByte}，这里用Java的double类型映射
     */
    private double val;

    @Override
    public void readInfo(ClassReader cr) {
        this.val = cr.readUInt64ToDouble();
    }

    public double getVal(){
        return this.val;
    }

    @Override
    public int tag() {
        return CONSTANT_TAG_DOUBLE;
    }
}
