package tzy.tinyPros.parser.attributes.impl;

import tzy.tinyPros.parser.ClassReader;
import tzy.tinyPros.parser.attributes.AttributeInfo;
import tzy.tinyPros.parser.attributes.BaseAttributeInfoElements;
import tzy.tinyPros.parser.constantpool.ConstantPool;

/**
 * @author TPureZY
 * @since 2023/7/10 0:05
 * <p>
 * 用于支持泛型情况下的方法签名
 **/
public class SignatureAttribute extends BaseAttributeInfoElements implements AttributeInfo {

    private int signatureIdx;
    private ConstantPool cp;

    public SignatureAttribute(String attrName, int attrLen, ConstantPool cp) {
        super(attrName, attrLen);
        this.cp = cp;
    }

    @Override
    public void readInfo(ClassReader cr) {
        this.signatureIdx = cr.readU2();
    }

    public String getSignature() {
        return cp.getUtf8(this.signatureIdx);
    }
}
