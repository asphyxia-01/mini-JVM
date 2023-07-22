package tzy.tinyPros.JVM08.classfile.attributes.impl;

import tzy.tinyPros.JVM08.classfile.ClassReader;
import tzy.tinyPros.JVM08.classfile.attributes.AttributeInfo;
import tzy.tinyPros.JVM08.classfile.attributes.BaseAttributeInfoElements;

/**
 * @author TPureZY
 * @since 2023/7/10 0:18
 * <p>
 * 处理没有在JVM规范中出现的属性(即自定义属性)
 **/
public class UnparsedAttribute extends BaseAttributeInfoElements implements AttributeInfo {

    private byte[] data;

    public UnparsedAttribute(String attrName, int attrLen) {
        super(attrName, attrLen);
    }

    @Override
    public void readInfo(ClassReader cr) {
        this.data = cr.readBytes(super.attrLen);
    }

    public byte[] getData() {
        return this.data;
    }
}
