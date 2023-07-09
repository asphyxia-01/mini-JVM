package tzy.tinyPros.JVM03.classfile;

import tzy.tinyPros.JVM03.classfile.attributes.AttributeInfo;
import tzy.tinyPros.JVM03.classfile.constantpool.ConstantPool;

/**
 * @author TPureZY
 * @since 2023/7/6 17:05
 * <p>
 * 将class字节流分解成具体的结构
 * <blockquote><pre>
 * ClassFile {
 *     u4             magic;
 *     u2             minor_version;
 *     u2             major_version;
 *     u2             constant_pool_count;
 *     cp_info        constant_pool[constant_pool_count-1];
 *     u2             access_flags;
 *     u2             this_class;
 *     u2             super_class;
 *     u2             interfaces_count;
 *     u2             interfaces[interfaces_count];
 *     u2             fields_count;
 *     field_info     fields[fields_count];
 *     u2             methods_count;
 *     method_info    methods[methods_count];
 *     u2             attributes_count;
 *     attribute_info attributes[attributes_count];
 * }
 * </pre></blockquote>
 **/
public class ClassFile {

    private long magic;
    private int minorVersion;
    private int majorVersion;
    private ConstantPool constantPool;
    private int accessFlags;
    private int thisClassIdx;
    private int superClassIdx;
    private int[] interfaceIdxs;
    /**
     * 字段表
     */
    private MemberInfo[] fields;
    /**
     * 方法表
     */
    private MemberInfo[] methods;
    /**
     * 属性表（注意不要和字段混淆）
     */
    private AttributeInfo[] attributes;

    public ClassFile(byte[] classData) {
        ClassReader cr = new ClassReader(classData);
        this.readAndCheckMagic(cr);
        this.readAndCheckVersion(cr);
        this.constantPool = this.readConstantPool(cr);
        this.accessFlags = cr.readU2();
        this.thisClassIdx = cr.readU2();
        this.superClassIdx = cr.readU2();
        this.interfaceIdxs = cr.readU2sByHeadLen();
        this.fields = MemberInfo.readFieldsOrMethods(cr, this.constantPool);
        this.methods = MemberInfo.readFieldsOrMethods(cr, this.constantPool);
        this.attributes = AttributeInfo.readAttributes(cr, this.constantPool);
    }

    private void readAndCheckMagic(ClassReader cr) {
        this.magic = cr.readU4();
        if ((magic ^ (0xCAFEBABE & 0x0FFFFFFFFL)) != 0) {
            throw new ClassFormatError();
        }
    }

    private void readAndCheckVersion(ClassReader cr) {
        // 次版本号
        this.minorVersion = cr.readU2();
        // 主版本号
        this.majorVersion = cr.readU2();
        // 使用jre1.8，jre1.1之后(不含jre1.1)都没有minorVersion，jre1.2的majorVersion是46，jre1.8是52
        switch (majorVersion) {
            case 45:
                return;
            case 46:
            case 47:
            case 48:
            case 49:
            case 50:
            case 51:
            case 52:
                if (minorVersion == 0) {
                    return;
                }
            default:
                throw new UnsupportedClassVersionError();
        }
    }

    private ConstantPool readConstantPool(ClassReader cr) {
        return new ConstantPool(cr);

    }

    public int getMinorVersion() {
        return this.minorVersion;
    }

    public int getMajorVersion() {
        return this.majorVersion;
    }

    public int getAccessFlags() {
        return this.accessFlags;
    }

    public String getClassName() {
        return this.constantPool.getClassName(this.thisClassIdx);
    }

    public String getSuperClassName() {
        return this.constantPool.getClassName(this.superClassIdx);
    }

    public String[] getInterfaceNames() {
        String[] names = new String[this.interfaceIdxs.length];
        for (int i = 0; i < this.interfaceIdxs.length; i++) {
            names[i] = this.constantPool.getClassName(this.interfaceIdxs[i]);
        }
        return names;
    }

    public ConstantPool getConstantPool(){
        return this.constantPool;
    }

    public MemberInfo[] getFields() {
        return this.fields;
    }

    public MemberInfo[] getMethods() {
        return this.methods;
    }
}
