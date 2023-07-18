package tzy.tinyPros.JVM06.classfile.constantpool.impl;

import tzy.tinyPros.JVM06.classfile.ClassReader;
import tzy.tinyPros.JVM06.classfile.constantpool.ConstantInfo;
import tzy.tinyPros.JVM06.classfile.constantpool.ConstantPool;

/**
 * @author TPureZY
 * @since 2023/7/7 0:41
 *
 * 对于Constant_FieldRef_info、Constant_MethodRef_info和Constant_InterfaceMethodRef_info在JVM的常量池中的结构是一样的
 * {
 *     u1 tag;
 *     u2 class_index;
 *     u2 name_and_type_index;
 * }
 * 统一使用MemberRefInfo类作为这三者的基类
 **/
public abstract class ConstantMemberRefInfo implements ConstantInfo {

    private ConstantPool cp;
    private int classIdx;
    private int nameAndTypeIdx;

    public ConstantMemberRefInfo(ConstantPool cp){
        this.cp = cp;
    }

    @Override
    public void readInfo(ClassReader cr) {
        this.classIdx = cr.readU2();
        this.nameAndTypeIdx = cr.readU2();
    }

    @Override
    public int tag() {
        return 0;
    }

    public String getClassName(){
        return cp.getUtf8(this.classIdx);
    }

    public NameAndTypeInstance getNameAndDescriptor(){
        return cp.getNameAndType(this.nameAndTypeIdx);
    }
}
