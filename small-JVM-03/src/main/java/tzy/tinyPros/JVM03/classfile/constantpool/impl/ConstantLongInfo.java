package tzy.tinyPros.JVM03.classfile.constantpool.impl;

import tzy.tinyPros.JVM03.classfile.ClassReader;
import tzy.tinyPros.JVM03.classfile.constantpool.ConstantInfo;

/**
 * @author TPureZY
 * @since 2023/7/7 2:49
 *
 * 和Constant_Double_info一样由两个u4组成，注意class文件使用大端存储
 **/
public class ConstantLongInfo implements ConstantInfo {

    /**
     * {
     *     u4 highByte;
     *     u4 lowByte;
     * }
     */
    private long val;

    @Override
    public void readInfo(ClassReader cr) {
        this.val = cr.readUInt64ToLong();
    }

    @Override
    public int tag() {
        return CONSTANT_TAG_LONG;
    }

    public long getVal(){
        return this.val;
    }
}
