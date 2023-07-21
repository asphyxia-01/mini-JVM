package tzy.tinyPros.JVM07.classfile.attributes.impl;

import tzy.tinyPros.JVM07.classfile.ClassReader;
import tzy.tinyPros.JVM07.classfile.attributes.AttributeInfo;
import tzy.tinyPros.JVM07.classfile.attributes.BaseAttributeInfoElements;
import tzy.tinyPros.JVM07.classfile.constantpool.ConstantPool;

/**
 * @author TPureZY
 * @since 2023/7/8 23:55
 * <p>
 * Java代码编译成的字节码指令
 **/
public class CodeAttribute extends BaseAttributeInfoElements implements AttributeInfo {

    private ConstantPool cp;
    /**
     * 操作数栈最大深度
     */
    private int maxStack;
    /**
     * 局部变量表大小
     */
    private int maxLocals;
    /**
     * 会先读取code_length
     */
    private byte[] code;
    /**
     * 会先读取exception_length
     */
    private ExceptionEntry[] exceptionTable;
    /**
     * 会先读取attribute_length
     *
     * 在 Attribute_Code中的attributes有LineNumberTable、LocalVariableTable、LocalVariableTypeTable、StackMapTable 等(不同JVM可以自己定义其他attribute)
     */
    private AttributeInfo[] attributeInfos;

    public CodeAttribute(String attrName, int attrLen, ConstantPool cp) {
        super(attrName, attrLen);
        this.cp = cp;
    }

    @Override
    public void readInfo(ClassReader cr) {
        // 这些字段在字节码中的排列是按顺序的，所以读取的时候也是按顺序读取
        this.maxStack = cr.readU2();
        this.maxLocals = cr.readU2();
        int codeLength = cr.readUInt32ToInt();
        this.code = cr.readBytes(codeLength);
        this.exceptionTable = ExceptionEntry.readExceptionEntries(cr);
        this.attributeInfos = AttributeInfo.readAttributes(cr,this.cp);
    }

    public int getMaxStack() {
        return maxStack;
    }

    public int getMaxLocals() {
        return maxLocals;
    }

    public byte[] getCode() {
        return code;
    }

    public ExceptionEntry[] getExceptionTable() {
        return exceptionTable;
    }

    public AttributeInfo[] getAttributeInfos() {
        return attributeInfos;
    }

    static class ExceptionEntry{
        private int startPC;
        private int endPC;
        private int handlePC;
        private int catchType;

        ExceptionEntry(int startPC,int endPC,int handlePC,int catchType){
            this.startPC = startPC;
            this.endPC = endPC;
            this.handlePC = handlePC;
            this.catchType = catchType;
        }

        static ExceptionEntry[] readExceptionEntries(ClassReader cr){
            int tableLength = cr.readU2();
            ExceptionEntry[] table = new ExceptionEntry[tableLength];
            for (int i = 0; i <tableLength; i++) {
                table[i] = new ExceptionEntry(cr.readU2(),cr.readU2(),cr.readU2(),cr.readU2());
            }
            return table;
        }

        public int getStartPC() {
            return startPC;
        }

        public int getEndPC() {
            return endPC;
        }

        public int getHandlePC() {
            return handlePC;
        }

        public int getCatchType() {
            return catchType;
        }
    }
}
