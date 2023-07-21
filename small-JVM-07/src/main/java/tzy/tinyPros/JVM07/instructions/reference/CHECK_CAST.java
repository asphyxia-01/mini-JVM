package tzy.tinyPros.JVM07.instructions.reference;

import tzy.tinyPros.JVM07.instructions.base.Index16Instruction;
import tzy.tinyPros.JVM07.rtda.heap.constantpool.ClassRef;
import tzy.tinyPros.JVM07.rtda.heap.methodarea.Object;
import tzy.tinyPros.JVM07.rtda.thread.Frame;
import tzy.tinyPros.JVM07.rtda.thread.OperandStack;

/**
 * @author TPureZY
 * @since 2023/7/20 19:25
 * <p>
 * check_cast 和 instance_of 指令很像，但前者不改变操作数栈（如果判断失败，会抛出ClassCastException异常）
 * <p>
 * 先从操作数栈中弹出对象引用，再推回去，如此不会改变操作数栈的状态。如果引用是null则指令执行结束（null可以转换为任何类型）；否则解析类符号引用，判断对象是否是类的实例，如果是，执行结束，不是则抛出ClassCastException异常
 * <p>
 * 一般而言instance_of和check_cast指令都是配合使用，前者会将结果推入操作数栈且不推回原ref，后者不推入结果但是保留原ref
 **/
public class CHECK_CAST extends Index16Instruction {
    @Override
    public void execute(Frame frame) {
        OperandStack stack = frame.getOperandStack();
        // 先推出
        Object ref = stack.popRef();
        // 再推回
        stack.pushRef(ref);
        // 如果ref是null，则一定能转换
        if (ref == null) {
            return;
        }
        ClassRef cr = (ClassRef) frame.getMethod().clazz.runTimeConstantPool.constants[this.getIdx()];
        if (!ref.isInstanceOf(cr.getClazz())) {
            throw new ClassCastException();
        }
    }
}
