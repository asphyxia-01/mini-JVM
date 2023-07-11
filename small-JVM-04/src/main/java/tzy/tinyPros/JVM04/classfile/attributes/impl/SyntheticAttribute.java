package tzy.tinyPros.JVM04.classfile.attributes.impl;

import tzy.tinyPros.JVM04.classfile.ClassReader;
import tzy.tinyPros.JVM04.classfile.attributes.AttributeInfo;
import tzy.tinyPros.JVM04.classfile.attributes.BaseAttributeInfoElements;

/**
 * @author TPureZY
 * @since 2023/7/10 0:16
 * <p>
 * 标志方法或字段为编译器自动生成的(比如静态默认无参构造器等)
 **/
public class SyntheticAttribute extends BaseAttributeInfoElements implements AttributeInfo {
    public SyntheticAttribute(String attrName, int attrLen) {
        super(attrName, attrLen);
    }

    @Override
    public void readInfo(ClassReader cr) {

    }
}
