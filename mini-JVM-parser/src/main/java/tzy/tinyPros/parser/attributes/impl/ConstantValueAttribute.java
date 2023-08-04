package tzy.tinyPros.parser.attributes.impl;

import tzy.tinyPros.parser.ClassReader;
import tzy.tinyPros.parser.attributes.AttributeInfo;
import tzy.tinyPros.parser.attributes.BaseAttributeInfoElements;

/**
 * @author TPureZY
 * @since 2023/7/9 0:56
 * <p>
 * static final关键字定义的常量字段
 **/
public class ConstantValueAttribute extends BaseAttributeInfoElements implements AttributeInfo {

    private int constantValueIdx;

    public ConstantValueAttribute(String attrName, int attrLen) {
        super(attrName, attrLen);
    }

    @Override
    public void readInfo(ClassReader cr) {
        this.constantValueIdx = cr.readU2();
    }

    public int getConstantValueIdx() {
        return this.constantValueIdx;
    }
}
