package tzy.tinyPros.JVM03.classfile.constantpool.impl;

import tzy.tinyPros.JVM03.classfile.ClassReader;
import tzy.tinyPros.JVM03.classfile.constantpool.ConstantInfo;
import tzy.tinyPros.JVM03.classfile.constantpool.ConstantPool;

/**
 * @author TPureZY
 * @since 2023/7/6 21:31
 **/
public class ConstantClassInfo implements ConstantInfo {

    private ConstantPool cp;
    private int nameIdx;

    public ConstantClassInfo(ConstantPool cp){
        this.cp = cp;
    }

    @Override
    public void readInfo(ClassReader cr) {
        // 索引占用两字节
        this.nameIdx = cr.readU2();
    }

    @Override
    public int tag() {
        return CONSTANT_TAG_CLASS;
    }

    public String getName(){
        // 在常量池中类名使用Constant_Utf8_info进行存储，Constant_Class_info中存储的是前者在池中的索引
        return this.cp.getUtf8(this.nameIdx);
    }

}
