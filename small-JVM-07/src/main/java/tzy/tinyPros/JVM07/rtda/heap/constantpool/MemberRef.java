package tzy.tinyPros.JVM07.rtda.heap.constantpool;

import tzy.tinyPros.JVM07.classfile.constantpool.impl.ConstantMemberRefInfo;

/**
 * @author TPureZY
 * @since 2023/7/19 0:41
 **/
public class MemberRef extends SymRef {

    public final String name;
    public final String descriptor;

    protected MemberRef(RunTimeConstantPool runTimeConstantPool, ConstantMemberRefInfo info) {
        super(runTimeConstantPool, info);
        this.name = info.getNameAndDescriptor().getName();
        this.descriptor = info.getNameAndDescriptor().getDescriptor();
    }

    public String getName() {
        return name;
    }

    public String getDescriptor() {
        return descriptor;
    }
}
