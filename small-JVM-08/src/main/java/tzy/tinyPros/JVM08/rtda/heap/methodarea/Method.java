package tzy.tinyPros.JVM08.rtda.heap.methodarea;

import tzy.tinyPros.JVM08.classfile.MemberInfo;
import tzy.tinyPros.JVM08.classfile.attributes.impl.CodeAttribute;
import tzy.tinyPros.JVM08.rtda.heap.constantpool.AccessFlags;

/**
 * @author TPureZY
 * @since 2023/7/18 21:07
 **/
public class Method extends ClassMember {

    /**
     * 操作数栈最大深度
     */
    public final int maxStackDepth;
    /**
     * 局部变量表最大长度
     */
    public final int maxLocalVarsLength;
    /**
     * 方法字节码
     */
    public final byte[] code;

    /**
     * 方法参数列表的参数个数
     */
    public final int argSlotCnt;

    public Method(MemberInfo info, Class clazz) {
        super(info, clazz);
        CodeAttribute codeAttributeInfo = info.getCodeAttributeInfo();
        if (codeAttributeInfo != null) {
            this.maxStackDepth = codeAttributeInfo.getMaxStack();
            this.maxLocalVarsLength = codeAttributeInfo.getMaxLocals();
            this.code = codeAttributeInfo.getCode();
            this.argSlotCnt = MethodDescriptor.calcArgSlotCount(MethodDescriptorParse.parseMethodDescriptorParser(info.getDescriptor()), this.isStatic());
        } else {
            this.maxStackDepth = 0;
            this.maxLocalVarsLength = 0;
            this.code = null;
            this.argSlotCnt = 0;
        }
    }

    public static Method[] newMethods(Class clazz, MemberInfo[] infos) {
        Method[] methods = new Method[infos.length];
        for (int i = 0; i < methods.length; i++) {
            methods[i] = new Method(infos[i], clazz);
        }
        return methods;
    }

    public boolean isSynchronized() {
        return 0 != (this.accessFlags & AccessFlags.ACC_SYNCHRONIZED);
    }

    public boolean isBridge() {
        return 0 != (this.accessFlags & AccessFlags.ACC_BRIDGE);
    }

    public boolean isVarargs() {
        return 0 != (this.accessFlags & AccessFlags.ACC_VARARGS);
    }

    public boolean isNative() {
        return 0 != (this.accessFlags & AccessFlags.ACC_NATIVE);
    }

    public boolean isAbstract() {
        return 0 != (this.accessFlags & AccessFlags.ACC_ABSTRACT);
    }

    public boolean isStrict() {
        return 0 != (this.accessFlags & AccessFlags.ACC_STRICT);
    }

}
