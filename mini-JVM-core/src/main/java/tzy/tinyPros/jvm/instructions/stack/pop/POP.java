package tzy.tinyPros.jvm.instructions.stack.pop;

import tzy.tinyPros.jvm.instructions.base.NoOperandsInstruction;
import tzy.tinyPros.jvm.rtda.thread.Frame;

/**
 * @author TPureZY
 * @since 2023/7/16 16:51
 **/
public class POP extends NoOperandsInstruction {
    @Override
    public void execute(Frame frame) {
        frame.getOperandStack().popSlot();
    }
}
