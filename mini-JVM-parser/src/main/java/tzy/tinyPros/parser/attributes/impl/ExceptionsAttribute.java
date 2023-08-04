package tzy.tinyPros.parser.attributes.impl;

import tzy.tinyPros.parser.ClassReader;
import tzy.tinyPros.parser.attributes.AttributeInfo;
import tzy.tinyPros.parser.attributes.BaseAttributeInfoElements;

/**
 * @author TPureZY
 * @since 2023/7/9 1:10
 * <p>
 * 方法抛出的异常
 **/
public class ExceptionsAttribute extends BaseAttributeInfoElements implements AttributeInfo {

    private int[] exceptionIdxTable;

    public ExceptionsAttribute(String attrName, int attrLen) {
        super(attrName, attrLen);
    }

    @Override
    public void readInfo(ClassReader cr) {
        // 开头length、后面有length个index
        this.exceptionIdxTable = cr.readU2sByHeadLen();
    }

    public int[] getExceptionIdxTable() {
        return this.exceptionIdxTable;
    }
}
