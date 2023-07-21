package tzy.tinyPros.JVM07.classfile.constantpool.impl;

import tzy.tinyPros.JVM07.classfile.constantpool.ConstantPool;

/**
 * @author TPureZY
 * @since 2023/7/7 2:36
 **/
public class ConstantInterfaceMethodRefInfo extends ConstantMemberRefInfo{
    public ConstantInterfaceMethodRefInfo(ConstantPool cp) {
        super(cp);
    }

    @Override
    public int tag() {
        return CONSTANT_TAG_INTERFACEMETHODREF;
    }
}
