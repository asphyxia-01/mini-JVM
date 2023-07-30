package tzy.tinyPros.JVM09.classfile.constantpool.impl;

import tzy.tinyPros.JVM09.classfile.constantpool.ConstantPool;
import tzy.tinyPros.JVM09.classfile.constantpool.ConstantInfo;

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
        return ConstantInfo.CONSTANT_TAG_FIELDREF;
    }
}
