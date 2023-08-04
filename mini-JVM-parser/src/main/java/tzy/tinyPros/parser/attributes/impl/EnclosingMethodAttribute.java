package tzy.tinyPros.parser.attributes.impl;

import tzy.tinyPros.parser.ClassReader;
import tzy.tinyPros.parser.attributes.AttributeInfo;
import tzy.tinyPros.parser.attributes.BaseAttributeInfoElements;
import tzy.tinyPros.parser.constantpool.ConstantPool;
import tzy.tinyPros.parser.constantpool.impl.NameAndTypeInstance;

/**
 * @author TPureZY
 * @since 2023/7/9 1:01
 * <p>
 * 仅当一个类为局部类或者匿名类是才能拥有这个属性，这个属性用于标识这个类所在的外围方法，类似于在闭包环境下使用（当然内部类也是闭包环境）
 **/
public class EnclosingMethodAttribute extends BaseAttributeInfoElements implements AttributeInfo {

    private ConstantPool cp;
    private int classIdx;
    /**
     * 这里就是NameAndType的index，指向方法的名称和描述符
     */
    private int methodIdx;

    public EnclosingMethodAttribute(String attrName, int attrLen, ConstantPool cp) {
        super(attrName, attrLen);
        this.cp = cp;
    }

    @Override
    public void readInfo(ClassReader cr) {
        this.classIdx = cr.readU2();
        this.methodIdx = cr.readU2();
    }

    public String getClassName() {
        return cp.getClassName(classIdx);
    }

    public NameAndTypeInstance getNameAndType() {
        if (this.methodIdx == 0) {
            return null;
        }
        return cp.getNameAndType(this.methodIdx);
    }
}
