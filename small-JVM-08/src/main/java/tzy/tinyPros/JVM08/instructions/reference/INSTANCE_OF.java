package tzy.tinyPros.JVM08.instructions.reference;

import tzy.tinyPros.JVM08.instructions.base.Index16Instruction;
import tzy.tinyPros.JVM08.rtda.heap.constantpool.ClassRef;
import tzy.tinyPros.JVM08.rtda.heap.methodarea.Object;
import tzy.tinyPros.JVM08.rtda.thread.Frame;

/**
 * @author TPureZY
 * @since 2023/7/20 19:08
 * <p>
 * 判断对象是否是某个类的实例，并把判断结果推入操作数栈(0 / 1)
 * <p>
 * 两个操作数：类符号引用的常量池索引（指令给出）、对象引用（操作数栈给出）
 **/
public class INSTANCE_OF extends Index16Instruction {
    @Override
    public void execute(Frame frame) {
        ClassRef cr = (ClassRef) frame.getMethod().clazz.runTimeConstantPool.constants[this.getIdx()];
        Object ref = frame.getOperandStack().popRef();
        if (ref == null) {
            frame.getOperandStack().pushInt(0);
            return;
        }
        if (ref.isInstanceOf(cr.getClazz())) {
            frame.getOperandStack().pushInt(1);
        } else {
            frame.getOperandStack().pushInt(0);
        }
    }
}
