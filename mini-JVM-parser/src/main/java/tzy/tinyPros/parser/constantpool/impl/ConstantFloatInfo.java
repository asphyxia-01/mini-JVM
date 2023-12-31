package tzy.tinyPros.parser.constantpool.impl;

import tzy.tinyPros.parser.ClassReader;
import tzy.tinyPros.parser.constantpool.ConstantInfo;

/**
 * @author TPureZY
 * @since 2023/7/7 1:08
 * {
 *     u1 tag;
 *     u4 data
 * }
 **/
public class ConstantFloatInfo implements ConstantInfo {

    private float val;

    @Override
    public void readInfo(ClassReader cr) {
        this.val = cr.readUInt32ToFloat();
    }

    @Override
    public int tag() {
        return CONSTANT_TAG_FLOAT;
    }

    public float getVal(){
        return this.val;
    }
}
