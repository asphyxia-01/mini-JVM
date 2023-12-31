package tzy.tinyPros.jvm.rtda.heap.constantpool;

import tzy.tinyPros.parser.constantpool.impl.ConstantMemberRefInfo;
import tzy.tinyPros.jvm.rtda.heap.methodarea.Klass;
import tzy.tinyPros.jvm.rtda.heap.methodarea.Method;
import tzy.tinyPros.jvm.rtda.heap.methodarea.MethodLookup;

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

    private void resolveInterfaceMethodRef() {
        Klass visitor = this.runTimeConstantPool.clazz;
        Klass holder = this.getClazz();
        if (!holder.isInterface()) {
            throw new IncompatibleClassChangeError();
        }
        Method ans = lookupInterfaceMethod(holder, this.name, this.descriptor);
        if (ans == null) {
            throw new NoSuchMethodError();
        }
        if (!ans.isAccessibleTo(visitor)) {
            throw new IllegalAccessError();
        }
        this.method = ans;
    }

    private Method lookupInterfaceMethod(Klass clazz, String name, String descriptor) {
        for (Method var0 : clazz.methods) {
            if (var0.name.equals(name) && var0.descriptor.equals(descriptor)) {
                return var0;
            }
        }
        return MethodLookup.lookupMethodInInterfaces(clazz.interfaces, name, descriptor);
    }
}
