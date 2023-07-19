package tzy.tinyPros.JVM06.rtda.heap.constantpool;

import tzy.tinyPros.JVM06.classfile.constantpool.impl.ConstantMemberRefInfo;
import tzy.tinyPros.JVM06.rtda.heap.methodarea.Method;

/**
 * @author TPureZY
 * @since 2023/7/19 20:30
 **/
public class InterfaceMethodRef extends MemberRef {

    private Method method;

    private InterfaceMethodRef(RunTimeConstantPool runTimeConstantPool, ConstantMemberRefInfo info) {
        super(runTimeConstantPool, info);
    }

    public static InterfaceMethodRef newInterfaceMethodRef(RunTimeConstantPool runTimeConstantPool, ConstantMemberRefInfo info) {
        return new InterfaceMethodRef(runTimeConstantPool, info);
    }

    public Method resolvedInterfaceMethod() {
        if (this.method == null) {
            this.resolveInterfaceMethodRef();
        }
        return this.method;
    }

    public void resolveInterfaceMethodRef() {
        // TODO: 完善 InterfaceMethodRef 类
    }
}
