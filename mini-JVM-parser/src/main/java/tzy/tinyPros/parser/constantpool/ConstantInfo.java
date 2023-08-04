package tzy.tinyPros.parser.constantpool;

import tzy.tinyPros.parser.ClassReader;
import tzy.tinyPros.parser.constantpool.impl.*;

/**
 * @author TPureZY
 * @since 2023/7/6 20:34
 **/
public interface ConstantInfo {

    /**
     * 常量池中的每一个数据的tag字段都是使用的u1类型，即只占用1个字节
     * 字面量：数字常量、字符串常量
     * 符号引用：类、接口名、字段、方法
     */
    int CONSTANT_TAG_CLASS = 7;
    int CONSTANT_TAG_FIELDREF = 9;
    int CONSTANT_TAG_METHODREF = 10;
    int CONSTANT_TAG_INTERFACEMETHODREF = 11;
    int CONSTANT_TAG_STRING = 8;
    int CONSTANT_TAG_INTEGER = 3;
    int CONSTANT_TAG_FLOAT = 4;
    int CONSTANT_TAG_LONG = 5;
    int CONSTANT_TAG_DOUBLE = 6;
    int CONSTANT_TAG_NAMEANDTYPE = 12;
    int CONSTANT_TAG_UTF8 = 1;
    int CONSTANT_TAG_METHODHANDLE = 15;
    int CONSTANT_TAG_METHODTYPE = 16;
    int CONSTANT_TAG_INVOKEDYNAMIC = 18;

    void readInfo(ClassReader cr);

    int tag();

    static ConstantInfo readConstantInfo(ClassReader cr, ConstantPool cp) {
        int tag = cr.readU1();
        // 先获取实例，但是没有对其内容进行填充，传入ContantPool是因为有字面量也有符号引用(部分字段数据是索引，索引回常量池找字面量)
        ConstantInfo constantInfo = newConstantInfo(cp, tag);
        // 使用ClassReader按序读取后面剩余属于该constantInfo的数据
        constantInfo.readInfo(cr);
        return constantInfo;
    }

    static ConstantInfo newConstantInfo(ConstantPool cp, int tag) {
        switch (tag) {
            case CONSTANT_TAG_CLASS:
                return new ConstantClassInfo(cp);
            case CONSTANT_TAG_DOUBLE:
                return new ConstantDoubleInfo();
            case CONSTANT_TAG_FIELDREF:
                return new ConstantFieldRefInfo(cp);
            case CONSTANT_TAG_FLOAT:
                return new ConstantFloatInfo();
            case CONSTANT_TAG_INTERFACEMETHODREF:
                return new ConstantInterfaceMethodRefInfo(cp);
            case CONSTANT_TAG_METHODREF:
                return new ConstantMethodRefInfo(cp);
            case CONSTANT_TAG_STRING:
                return new ConstantStringInfo(cp);
            case CONSTANT_TAG_INTEGER:
                return new ConstantIntegerInfo();
            case CONSTANT_TAG_LONG:
                return new ConstantLongInfo();
            case CONSTANT_TAG_NAMEANDTYPE:
                return new ConstantNameAndTypeInfo();
            case CONSTANT_TAG_UTF8:
                return new ConstantUtf8Info();
            case CONSTANT_TAG_METHODHANDLE:
                return new ConstantMethodHandleInfo();
            case CONSTANT_TAG_METHODTYPE:
                return new ConstantMethodTypeInfo();
            case CONSTANT_TAG_INVOKEDYNAMIC:
                return new ConstantInvokeDynamicInfo();
            default:
                System.out.println();
                throw new ClassFormatError("tag error "+tag);
        }
    }
}
