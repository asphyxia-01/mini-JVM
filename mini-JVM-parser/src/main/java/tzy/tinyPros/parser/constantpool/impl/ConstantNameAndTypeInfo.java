package tzy.tinyPros.parser.constantpool.impl;

import tzy.tinyPros.parser.ClassReader;
import tzy.tinyPros.parser.constantpool.ConstantInfo;

/**
 * @author TPureZY
 * @since 2023/7/7 2:56
 *
 * Constant_NameAndType_info一般用于中介，即作为其他常量池数据结构的类型的出现，不会单独出现也不会单独求其引用的真实数据
 **/
public class ConstantNameAndTypeInfo implements ConstantInfo {
    private int nameIdx;
    private int descriptorIdx;
    @Override
    public void readInfo(ClassReader cr) {
        this.nameIdx = cr.readU2();
        this.descriptorIdx = cr.readU2();
    }

    @Override
    public int tag() {
        return CONSTANT_TAG_NAMEANDTYPE;
    }

    public int getNameIdx(){
        return this.nameIdx;
    }

    public int getDescriptorIdx(){
        return this.descriptorIdx;
    }
}
