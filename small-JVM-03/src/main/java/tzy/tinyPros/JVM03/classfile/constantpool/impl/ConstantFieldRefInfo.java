package tzy.tinyPros.JVM03.classfile.constantpool.impl;

import tzy.tinyPros.JVM03.classfile.constantpool.ConstantPool;

/**
 * @author TPureZY
 * @since 2023/7/7 1:05
 **/
public class ConstantFieldRefInfo extends ConstantMemberRefInfo {
    public ConstantFieldRefInfo(ConstantPool cp) {
        super(cp);
    }

    @Override
    public int tag() {
        return CONSTANT_TAG_FIELDREF;
    }
}
