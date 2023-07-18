package tzy.tinyPros.JVM06.instructions.math.div;

import tzy.tinyPros.JVM06.instructions.base.NoOperandsInstruction;
import tzy.tinyPros.JVM06.rtda.thread.Frame;
import tzy.tinyPros.JVM06.rtda.thread.OperandStack;

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
