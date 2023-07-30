package tzy.tinyPros.JVM09.classfile.constantpool.impl;

import tzy.tinyPros.JVM09.classfile.constantpool.ConstantPool;

/**
 * @author TPureZY
 * @since 2023/7/7 2:54
 **/
public class ConstantMethodRefInfo extends ConstantMemberRefInfo{
    public ConstantMethodRefInfo(ConstantPool cp) {
        super(cp);
    }

    @Override
    public int tag() {
        return CONSTANT_TAG_METHODREF;
    }
}
