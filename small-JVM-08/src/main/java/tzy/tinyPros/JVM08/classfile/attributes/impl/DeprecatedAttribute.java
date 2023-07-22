package tzy.tinyPros.JVM08.classfile.attributes.impl;

import tzy.tinyPros.JVM08.classfile.ClassReader;
import tzy.tinyPros.JVM08.classfile.attributes.AttributeInfo;
import tzy.tinyPros.JVM08.classfile.attributes.BaseAttributeInfoElements;

/**
 * @author TPureZY
 * @since 2023/7/9 0:59
 * <p>
 * 被声明为deprecated的方法和字段，这个字段严格意义上没有主体部分，即只有attribute_name_idx和attribute_length
 **/
public class DeprecatedAttribute extends BaseAttributeInfoElements implements AttributeInfo {
    public DeprecatedAttribute(String attrName, int attrLen) {
        super(attrName, attrLen);
    }

    @Override
    public void readInfo(ClassReader cr) {

    }
}
