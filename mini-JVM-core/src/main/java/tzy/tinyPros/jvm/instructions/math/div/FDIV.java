package tzy.tinyPros.jvm.instructions.math.div;

import tzy.tinyPros.jvm.instructions.base.NoOperandsInstruction;
import tzy.tinyPros.jvm.rtda.thread.Frame;
import tzy.tinyPros.jvm.rtda.thread.OperandStack;

/**
 * @author TPureZY
 * @since 2023/7/16 20:42
 **/
public class FDIV extends NoOperandsInstruction {
    @Override
    public void execute(Frame frame) {
        OperandStack stack = frame.getOperandStack();
        float v0 = stack.popFloat();
        float v1 = stack.popFloat();
        stack.pushFloat(v1 / v0);
    }
}
