package tzy.tinyPros.JVM06.rtda.heap.constantpool;

import tzy.tinyPros.JVM06.classfile.constantpool.impl.ConstantClassInfo;
import tzy.tinyPros.JVM06.classfile.constantpool.impl.ConstantMemberRefInfo;
import tzy.tinyPros.JVM06.rtda.heap.methodarea.Class;

/**
 * @author TPureZY
 * @since 2023/7/19 0:28
 * <p>
 * 共同的变量独立程SymRef，这里面的变量主要是内部使用，方便方法的实现
 **/
public class SymRef {
    public final RunTimeConstantPool runTimeConstantPool;
    public final String className;
    public final Class clazz;

    protected SymRef(RunTimeConstantPool runTimeConstantPool, ConstantMemberRefInfo info) {
        this.runTimeConstantPool = runTimeConstantPool;
        this.className = info.getClassName();
        this.clazz = this.runTimeConstantPool.clazz.loader.loadClass(this.className);
    }

    protected SymRef(RunTimeConstantPool runTimeConstantPool, ConstantClassInfo info) {
        this.runTimeConstantPool = runTimeConstantPool;
        this.className = info.getName();
        this.clazz = null;
    }

    public Class getClazz() {
        return clazz;
    }
}
