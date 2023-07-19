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
    /**
     * 无论是FieldRef还是MethodRef还是ClassRef这里都是访问他们的直接类的类名，所以也有可能字段、方法本身是在父类定义，子类继承后实例化子类访问他们，这里也会显示子类类名，但实际上是属于父类的
     */
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
        this.clazz = this.runTimeConstantPool.clazz.loader.loadClass(this.className);
    }

    public Class getClazz() {
        return clazz;
    }
}
