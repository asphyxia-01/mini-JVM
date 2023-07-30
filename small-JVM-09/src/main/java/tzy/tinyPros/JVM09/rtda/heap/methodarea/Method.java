package tzy.tinyPros.JVM09.rtda.heap.methodarea;

import tzy.tinyPros.JVM09.classfile.MemberInfo;
import tzy.tinyPros.JVM09.classfile.attributes.impl.CodeAttribute;
import tzy.tinyPros.JVM09.rtda.heap.constantpool.AccessFlags;

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
        MethodDescriptor mDesc = MethodDescriptorParse.parseMethodDescriptorParser(info.getDescriptor());
        this.argSlotCnt = MethodDescriptor.calcArgSlotCount(mDesc, this.isStatic());
        CodeAttribute codeAttributeInfo = info.getCodeAttributeInfo();
        if (codeAttributeInfo != null) {
            this.maxStackDepth = codeAttributeInfo.getMaxStack();
            this.maxLocalVarsLength = codeAttributeInfo.getMaxLocals();
            this.code = codeAttributeInfo.getCode();
        } else if (this.isNative()) {
            // need to be modified
            this.maxStackDepth = 4;
            this.maxLocalVarsLength = this.argSlotCnt;
            this.code = this.injectCodeInfo(mDesc.getReturnType());
        } else {
            this.maxStackDepth = 0;
            this.maxLocalVarsLength = 0;
            this.code = null;
        }
    }

    public byte[] injectCodeInfo(String returnType) {
        switch (returnType.charAt(0)) {
            case 'V':
                return new byte[]{(byte) 0xfe, (byte) 0xb1};
            case 'L':
            case '[':
                return new byte[]{(byte) 0xfe, (byte) 0xb0};
            case 'D':
                return new byte[]{(byte) 0xfe, (byte) 0xaf};
            case 'F':
                return new byte[]{(byte) 0xfe, (byte) 0xae};
            case 'J':
                return new byte[]{(byte) 0xfe, (byte) 0xad};
            default:
                // byte、char、short、int
                return new byte[]{(byte) 0xfe, (byte) 0xac};
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