package tzy.tinyPros.JVM07.classfile.constantpool.impl;

import tzy.tinyPros.JVM07.classfile.ClassReader;
import tzy.tinyPros.JVM07.classfile.constantpool.ConstantInfo;

/**
 * @author TPureZY
 * @since 2023/7/7 3:05
 **/
public class ConstantUtf8Info implements ConstantInfo {

    private int length;
    private String str;

    @Override
    public void readInfo(ClassReader cr) {
        this.length = cr.readU2();
        this.str = new String(cr.readBytes(length));
    }

    @Override
    public int tag() {
        return CONSTANT_TAG_UTF8;
    }

    public String getStr(){
        return this.str;
    }
}
