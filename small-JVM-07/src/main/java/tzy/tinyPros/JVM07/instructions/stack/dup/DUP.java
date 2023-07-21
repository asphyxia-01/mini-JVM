package tzy.tinyPros.JVM07.instructions.stack.dup;

import tzy.tinyPros.JVM07.instructions.base.NoOperandsInstruction;
import tzy.tinyPros.JVM07.rtda.thread.Frame;
import tzy.tinyPros.JVM07.rtda.thread.OperandStack;
import tzy.tinyPros.JVM07.rtda.thread.Slot;

/**
 * @author TPureZY
 * @since 2023/7/16 16:58
 **/
public class DUP extends NoOperandsInstruction {
    @Override
    public void execute(Frame frame) {
        OperandStack stack = frame.getOperandStack();
        Slot slot = stack.popSlot();
        // 除了初始时候赋值，后面不会改变slot中的值，所以可以共用一个对象
        stack.pushSlot(slot);
        stack.pushSlot(slot);
    }
}
