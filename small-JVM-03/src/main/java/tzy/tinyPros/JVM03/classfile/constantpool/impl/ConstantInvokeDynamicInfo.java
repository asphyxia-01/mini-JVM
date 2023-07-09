package tzy.tinyPros.JVM03.classfile.constantpool.impl;

import tzy.tinyPros.JVM03.classfile.ClassReader;
import tzy.tinyPros.JVM03.classfile.constantpool.ConstantInfo;

/**
 * @author TPureZY
 * @since 2023/7/7 2:45
 *
 * 和Constant_MethodHandle_info、Constant_MethodType_info配合使用，作用和反射略微类似，Java7后加入，先不考虑
 **/
public class ConstantInvokeDynamicInfo implements ConstantInfo {

    private int bootstrapMethodAttrIdx;
    private int nameAndTypeIdx;

    @Override
    public void readInfo(ClassReader cr) {
        this.bootstrapMethodAttrIdx = cr.readU2();
        this.nameAndTypeIdx = cr.readU2();
    }

    @Override
    public int tag() {
        return CONSTANT_TAG_INVOKEDYNAMIC;
    }
}
