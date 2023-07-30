package tzy.tinyPros.JVM09.rtda.heap.constantpool;

import tzy.tinyPros.JVM09.classfile.constantpool.impl.ConstantClassInfo;
import tzy.tinyPros.JVM09.classfile.constantpool.impl.ConstantMemberRefInfo;
import tzy.tinyPros.JVM09.rtda.heap.methodarea.Klass;

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
    /**
     * 被访问时候是通过的哪个类实例，在编译时候也就会记录那个类Class，但同样的子类可以访问父类字段和方法，所以常量池记录的不一定就是最直接的那个持有该字段或方法的类Class
     */
    private Klass clazz;

    protected SymRef(RunTimeConstantPool runTimeConstantPool, ConstantMemberRefInfo info) {
        this.runTimeConstantPool = runTimeConstantPool;
        this.className = info.getClassName();
    }

    protected SymRef(RunTimeConstantPool runTimeConstantPool, ConstantClassInfo info) {
        this.runTimeConstantPool = runTimeConstantPool;
        this.className = info.getName();
    }

    public void parseClass() {
        if (this.clazz == null) {
            this.clazz = this.runTimeConstantPool.clazz.loader.loadClass(this.className);
            if (this.clazz == null) {
                throw new NoClassDefFoundError();
            }
        }
    }

    /**
     * 用到时再加载所属的类
     */
    public Klass getClazz() {
        parseClass();
        return this.clazz;
    }

}
