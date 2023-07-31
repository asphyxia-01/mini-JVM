package tzy.tinyPros.JVM09.rtda.heap.constantpool;

import tzy.tinyPros.JVM09.classfile.constantpool.impl.ConstantMemberRefInfo;
import tzy.tinyPros.JVM09.rtda.heap.methodarea.Klass;
import tzy.tinyPros.JVM09.rtda.heap.methodarea.Method;
import tzy.tinyPros.JVM09.rtda.heap.methodarea.MethodLookup;

/**
 * @author TPureZY
 * @since 2023/7/19 17:50
 **/
public class MethodRef extends MemberRef {

    private Method method;

    private MethodRef(RunTimeConstantPool runTimeConstantPool, ConstantMemberRefInfo info) {
        super(runTimeConstantPool, info);
    }

    public static MethodRef newMethodRef(RunTimeConstantPool runTimeConstantPool, ConstantMemberRefInfo info) {
        return new MethodRef(runTimeConstantPool, info);
    }

    public Method resolvedMethod() {
        if (this.method == null) {
            this.resolveMethodRef();
        }
        return this.method;
    }

    private void resolveMethodRef() {
        Klass visitor = this.runTimeConstantPool.clazz;
        Klass holder = this.getClazz();
        if (holder.isInterface()) {
            throw new IncompatibleClassChangeError();
        }
        Method ans = lookupMethod(holder, this.name, this.descriptor);
        if (ans == null) {
            throw new NoSuchMethodError();
        }
        if (!ans.isAccessibleTo(visitor)) {
            throw new IllegalAccessError();
        }
        this.method = ans;
    }

    private Method lookupMethod(Klass clazz, String name, String descriptor) {
        Method ans = MethodLookup.lookupMethodInClass(clazz, name, descriptor);
        if (ans == null) {
            // 在这种情况下访问起始入口不能是接口，但是方法最终归属可以是接口，所以需要查找一下
            ans = MethodLookup.lookupMethodInInterfaces(clazz.interfaces, name, descriptor);
        }
        return ans;
    }

}
