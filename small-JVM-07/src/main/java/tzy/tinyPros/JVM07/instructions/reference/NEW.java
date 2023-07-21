package tzy.tinyPros.JVM07.instructions.reference;

import tzy.tinyPros.JVM07.instructions.base.Index16Instruction;
import tzy.tinyPros.JVM07.rtda.heap.constantpool.ClassRef;
import tzy.tinyPros.JVM07.rtda.heap.constantpool.RunTimeConstantPool;
import tzy.tinyPros.JVM07.rtda.heap.methodarea.Class;
import tzy.tinyPros.JVM07.rtda.heap.methodarea.Object;
import tzy.tinyPros.JVM07.rtda.thread.Frame;

/**
 * @author TPureZY
 * @since 2023/7/20 15:23
 * <p>
 * new 指令后面跟着的是 ClassRef 在常量池中的索引，占用两个字节
 **/
public class NEW extends Index16Instruction {
    @Override
    public void execute(Frame frame) {
        RunTimeConstantPool rp = frame.getMethod().clazz.runTimeConstantPool;
        ClassRef cr = (ClassRef) rp.constants[this.getIdx()];
        Class clazz = cr.getClazz();
        // 不能是抽象或者接口
        if (clazz.isAbstract() || clazz.isInterface()) {
            throw new InstantiationError();
        }
        // 此Object非彼Object，表示的是对象
        Object object = clazz.newObject();
        frame.getOperandStack().pushRef(object);
    }
}
