package tzy.tinyPros.jvm.instructions.stack.pop;

import tzy.tinyPros.jvm.instructions.base.NoOperandsInstruction;
import tzy.tinyPros.jvm.rtda.thread.Frame;

/**
 * @author TPureZY
 * @since 2023/7/16 16:56
 *
 * 区别于POP，POP2适用于需要占用两个Slot的类型，比如double、long
 **/
public class POP2 extends NoOperandsInstruction {
    @Override
    public void execute(Frame frame) {
        frame.getOperandStack().popSlot();
        frame.getOperandStack().popSlot();
    }
}
