package tzy.tinyPros.jvm.rtda.heap.methodarea;

import tzy.tinyPros.parser.MemberInfo;
import tzy.tinyPros.jvm.rtda.heap.constantpool.AccessFlags;

/**
 * @author TPureZY
 * @since 2023/7/18 21:06
 * <p>
 * 区别于classfile中的MemberInfo，这里对Field特殊处理，将原本attributes属性中的部分信息提取出来显式定义在Field类中
 **/
public class Field extends ClassMember {

    /**
     * static final关键字定义的常量字段才有这个
     * <p>
     * ①.static final 修饰的常量如果是编译时常量（在编译阶段就有明确的值），通过类名点调用时不需要加载所在类的。
     * <p>
     * ②.static final 修饰的常量如果不是编译时常量（在编译阶段就没有明确的值），通过类名点调用时是要加载所在类的。
     * <p>
     * ③.只用static修饰的变量在用 类名调用时也是需要加载所在类的。
     */
    public final int constValueIndex;
    public int slotId;

    public Field(Klass clazz, MemberInfo info) {
        super(info, clazz);
        if (info.getConstantValueAttributeInfo() != null) {
            this.constValueIndex = info.getConstantValueAttributeInfo().getConstantValueIdx();
        } else {
            this.constValueIndex = 0;
        }
    }

    public static Field[] newFields(Klass clazz, MemberInfo[] info) {
        Field[] fields = new Field[info.length];
        for (int i = 0; i < fields.length; i++) {
            fields[i] = new Field(clazz, info[i]);
        }
        return fields;
    }

    public boolean isVolatile() {
        return 0 != (this.accessFlags & AccessFlags.ACC_VOLATILE);
    }

    public boolean isTransient() {
        return 0 != (this.accessFlags & AccessFlags.ACC_TRANSIENT);
    }

    public boolean isEnum() {
        return 0 != (this.accessFlags & AccessFlags.ACC_ENUM);
    }

    public boolean isLongOrDouble() {
        // "J"表示long,"D"表示double
        return this.descriptor.equals("J") || this.descriptor.equals("D");
    }
}
