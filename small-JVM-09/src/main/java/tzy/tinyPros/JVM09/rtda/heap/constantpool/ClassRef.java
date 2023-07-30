package tzy.tinyPros.JVM09.rtda.heap.constantpool;

import tzy.tinyPros.JVM09.classfile.constantpool.impl.ConstantClassInfo;

/**
 * @author TPureZY
 * @since 2023/7/19 0:49
 **/
public class ClassRef extends SymRef {
    private ClassRef(RunTimeConstantPool runTimeConstantPool, ConstantClassInfo info) {
        super(runTimeConstantPool, info);
    }

    public static ClassRef newClassRef(RunTimeConstantPool runTimeConstantPool, ConstantClassInfo info) {
        return new ClassRef(runTimeConstantPool, info);
    }

}
