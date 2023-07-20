package tzy.tinyPros.JVM06.rtda.heap.constantpool;

import tzy.tinyPros.JVM06.classfile.constantpool.ConstantInfo;
import tzy.tinyPros.JVM06.classfile.constantpool.ConstantPool;
import tzy.tinyPros.JVM06.classfile.constantpool.impl.*;
import tzy.tinyPros.JVM06.rtda.heap.methodarea.Class;

/**
 * @author TPureZY
 * @since 2023/7/18 22:41
 * <p>
 * 运行时常量池
 * <p>
 * 主要存放两类信息：字面量（整数、浮点数、字符串字面量）、符号引用（类符号引用、字段符号引用、方法符号引用、接口方法符号引用）
 **/
public class RunTimeConstantPool {

    /**
     * 表明这个运行时常量池属于哪一个类
     */
    public final Class clazz;
    public final Object[] constants;

    public RunTimeConstantPool(Class clazz, ConstantPool cp) {
        int cnt = cp.getSize();
        this.clazz = clazz;
        this.constants = new Object[cnt];
        ConstantInfo[] infos = cp.getInfos();
        for (int i = 1; i < cnt; i++) {
            ConstantInfo info = infos[i];
            switch (info.tag()) {
                case ConstantInfo.CONSTANT_TAG_INTEGER:
                    ConstantIntegerInfo var0 = (ConstantIntegerInfo) info;
                    this.constants[i] = var0.getVal();
                    break;
                case ConstantInfo.CONSTANT_TAG_FLOAT:
                    ConstantFloatInfo var1 = (ConstantFloatInfo) info;
                    this.constants[i] = var1.getVal();
                    break;
                case ConstantInfo.CONSTANT_TAG_LONG:
                    ConstantLongInfo var2 = (ConstantLongInfo) info;
                    this.constants[i++] = var2.getVal();
                    break;
                case ConstantInfo.CONSTANT_TAG_DOUBLE:
                    ConstantDoubleInfo var3 = (ConstantDoubleInfo) info;
                    this.constants[i++] = var3.getVal();
                    break;
                case ConstantInfo.CONSTANT_TAG_STRING:
                    ConstantStringInfo var4 = (ConstantStringInfo) info;
                    this.constants[i] = var4.getStr();
                    break;
                case ConstantInfo.CONSTANT_TAG_CLASS:
                    ConstantClassInfo var5 = (ConstantClassInfo) info;
                    this.constants[i] = ClassRef.newClassRef(this, var5);
                    break;
                case ConstantInfo.CONSTANT_TAG_FIELDREF:
                    ConstantFieldRefInfo var6 = (ConstantFieldRefInfo) info;
                    this.constants[i] = FieldRef.newFieldRef(this, var6);
                    break;
                case ConstantInfo.CONSTANT_TAG_INTERFACEMETHODREF:
                    ConstantInterfaceMethodRefInfo var7 = (ConstantInterfaceMethodRefInfo) info;
                    this.constants[i] = InterfaceMethodRef.newInterfaceMethodRef(this, var7);
                    break;
                case ConstantInfo.CONSTANT_TAG_METHODREF:
                    ConstantMethodRefInfo var8 = (ConstantMethodRefInfo) info;
                    this.constants[i] = MethodRef.newMethodRef(this, var8);
                    break;
            }
        }
    }

}
