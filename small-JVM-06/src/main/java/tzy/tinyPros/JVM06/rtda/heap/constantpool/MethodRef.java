package tzy.tinyPros.JVM06.rtda.heap.constantpool;

import tzy.tinyPros.JVM06.classfile.constantpool.impl.ConstantMemberRefInfo;
import tzy.tinyPros.JVM06.rtda.heap.methodarea.Method;

/**
 * @author TPureZY
 * @since 2023/7/19 17:50
 **/
public class MethodRef extends MemberRef{

    private Method method;

    private MethodRef(RunTimeConstantPool runTimeConstantPool, ConstantMemberRefInfo info) {
        super(runTimeConstantPool, info);
    }

    public static MethodRef newMethodRef(RunTimeConstantPool runTimeConstantPool,ConstantMemberRefInfo info){
        return new MethodRef(runTimeConstantPool, info);
    }

    public Method resolvedMethod(){
        if(this.method == null){
            this.resolveMethodRef();
        }
        return this.method;
    }

    public void resolveMethodRef(){
        // TODO: 完善 MethodRef 类
    }

}
