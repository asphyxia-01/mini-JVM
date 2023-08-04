package tzy.tinyPros.parser.constantpool.impl;

import tzy.tinyPros.parser.constantpool.ConstantPool;
import tzy.tinyPros.parser.constantpool.ConstantInfo;

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
