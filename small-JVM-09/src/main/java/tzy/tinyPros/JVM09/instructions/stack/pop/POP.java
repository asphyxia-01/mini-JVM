package tzy.tinyPros.JVM09.instructions.stack.pop;

import tzy.tinyPros.JVM09.instructions.base.NoOperandsInstruction;
import tzy.tinyPros.JVM09.rtda.thread.Frame;

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
