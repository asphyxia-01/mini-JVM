package tzy.tinyPros.parser;

import tzy.tinyPros.parser.attributes.AttributeInfo;
import tzy.tinyPros.parser.attributes.impl.CodeAttribute;
import tzy.tinyPros.parser.attributes.impl.ConstantValueAttribute;
import tzy.tinyPros.parser.constantpool.ConstantPool;

/**
 * @author TPureZY
 * @since 2023/7/8 2:40
 * <p>
 * 该类主要用于解析字段表、方法表，每一个解析出来的字段、方法都是一个MemberInfo实例，如下
 * <blockquote><pre>
 *    {
 *        u2 accessFlags;
 *        u2 nameIdx;
 *        u2 descriptorIdx;
 *        // 下面是该字段、方法的属性集合
 *        u2 attributesCount;
 *        AttributeInfo[] infos;
 *   }
 * </pre></blockquote>
 * <p>字段和方法的结构是相同的不需要区分，所以没必要设计成接口
 **/
public class MemberInfo {
    private ConstantPool cp;
    private int accessFlags;
    private int nameIdx;
    /**
     * 描述符：用来解释字段、方法的字符串（类型、是否数组 等 || 参数、返回值、类型、是否数组 等）
     */
    private int descriptorIdx;
    /**
     * 字段、方法的属性表，在字节码中还会在属性表之前给出表长即属性个数，这里不显式定义个数数据
     */
    private AttributeInfo[] attributes;

    public MemberInfo(ClassReader cr, ConstantPool cp) {
        this.cp = cp;
        this.accessFlags = cr.readU2();
        this.nameIdx = cr.readU2();
        this.descriptorIdx = cr.readU2();
        // 这部分使用AttributeInfo类进行读取
        this.attributes = AttributeInfo.readAttributes(cr, cp);
    }

    static public MemberInfo[] readFieldsOrMethods(ClassReader cr, ConstantPool cp) {
        int memberCount = cr.readU2();
        MemberInfo[] members = new MemberInfo[memberCount];
        for (int i = 0; i < memberCount; i++) {
            members[i] = new MemberInfo(cr, cp);
        }
        return members;
    }

    public String getName() {
        return cp.getUtf8(this.nameIdx);
    }

    public String getDescriptor() {
        return cp.getUtf8(this.descriptorIdx);
    }

    public int getAccessFlags() {
        return this.accessFlags;
    }

    /**
     * Method的重要属性
     */
    public CodeAttribute getCodeAttributeInfo() {
        for (AttributeInfo attribute : this.attributes) {
            if (attribute instanceof CodeAttribute) {
                return (CodeAttribute) attribute;
            }
        }
        return null;
    }

    /**
     * Field的重要属性
     */
    public ConstantValueAttribute getConstantValueAttributeInfo() {
        for (AttributeInfo attribute : this.attributes) {
            if (attribute instanceof ConstantValueAttribute) {
                return (ConstantValueAttribute) attribute;
            }
        }
        return null;
    }

}
