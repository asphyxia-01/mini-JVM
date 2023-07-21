package tzy.tinyPros.JVM07.classfile.attributes.impl;

import tzy.tinyPros.JVM07.classfile.ClassReader;
import tzy.tinyPros.JVM07.classfile.attributes.AttributeInfo;
import tzy.tinyPros.JVM07.classfile.attributes.BaseAttributeInfoElements;

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
