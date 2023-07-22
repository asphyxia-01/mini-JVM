package tzy.tinyPros.JVM08.classfile.attributes;

import tzy.tinyPros.JVM08.classfile.ClassReader;
import tzy.tinyPros.JVM08.classfile.attributes.impl.*;
import tzy.tinyPros.JVM08.classfile.constantpool.ConstantPool;

/**
 * @author TPureZY
 * @since 2023/7/8 2:47
 **/
public interface AttributeInfo {

    /**
     * 规范JVM中定义的属性有很多，这里仅仅列举个别，同时不同的JVM实现又有额外自主补充的属性
     */
    String BOOTSTRAPMETHODS = "BootstrapMethods";
    String CODE = "Code";
    String CONSTANTVALUE = "ConstantValue";
    String DEPRECATED = "Deprecated";
    String ENCLOSINGMETHOD = "EnclosingMethod";
    String EXCEPTIONS = "Exceptions";
    String INNERCLASSES = "InnerClasses";
    String LINENUMBERTABLE = "LineNumberTable";
    String LOCALVARIABLETABLE = "LocalVariableTable";
    String LOCALVARIABLETYPETABLE = "LocalVariableTypeTable";
    String SIGNATURE = "Signature";
    String SOURCEFILE = "SourceFile";
    String SYNTHETIC = "Synthetic";

    /**
     * 每种Attribute单独定义读取的规则，因为不同的Attribute的结构不一样
     *
     * @param cr 字节码读取工具
     */

    void readInfo(ClassReader cr);

    static AttributeInfo[] readAttributes(ClassReader cr, ConstantPool cp) {
        int attrCount = cr.readU2();
        AttributeInfo[] infos = new AttributeInfo[attrCount];
        for (int i = 0; i < attrCount; i++) {
            infos[i] = AttributeInfo.readAttribute(cr, cp);
        }
        return infos;
    }

    static AttributeInfo readAttribute(ClassReader cr, ConstantPool cp) {
        int nameIdx = cr.readU2();
        String attrName = cp.getUtf8(nameIdx);
        int attrLen = cr.readUInt32ToInt();
        AttributeInfo info = AttributeInfo.newAttribute(attrName, attrLen, cp);
        info.readInfo(cr);
        return info;
    }

    static AttributeInfo newAttribute(String attrName, int attrLen, ConstantPool cp) {
        switch (attrName) {
            case BOOTSTRAPMETHODS:
                return new BootstrapMethodsAttribute(attrName,attrLen);
            case CODE:
                return new CodeAttribute(attrName,attrLen,cp);
            case CONSTANTVALUE:
                return new ConstantValueAttribute(attrName,attrLen);
            case DEPRECATED:
                return new DeprecatedAttribute(attrName,attrLen);
            case ENCLOSINGMETHOD:
                return new EnclosingMethodAttribute(attrName,attrLen,cp);
            case EXCEPTIONS:
                return new ExceptionsAttribute(attrName,attrLen);
            case INNERCLASSES:
                return new InnerClassesAttribute(attrName,attrLen);
            case LINENUMBERTABLE:
                return new LineNumberTableAttribute(attrName,attrLen);
            case LOCALVARIABLETABLE:
                return new LocalVariableTableAttribute(attrName,attrLen);
            case LOCALVARIABLETYPETABLE:
                return new LocalVariableTypeTableAttribute(attrName,attrLen);
            case SIGNATURE:
                return new SignatureAttribute(attrName,attrLen,cp);
            case SOURCEFILE:
                return new SourceFileAttribute(attrName,attrLen,cp);
            case SYNTHETIC:
                return new SyntheticAttribute(attrName,attrLen);
            default:
                return new UnparsedAttribute(attrName,attrLen);
        }
    }
}
