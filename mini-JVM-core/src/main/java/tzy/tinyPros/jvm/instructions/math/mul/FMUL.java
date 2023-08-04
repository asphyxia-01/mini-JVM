package tzy.tinyPros.jvm.instructions.math.mul;

import tzy.tinyPros.jvm.instructions.base.NoOperandsInstruction;
import tzy.tinyPros.jvm.rtda.thread.Frame;
import tzy.tinyPros.jvm.rtda.thread.OperandStack;

/**
 * @author TPureZY
 * @since 2023/7/17 1:39
 **/
public class FMUL extends NoOperandsInstruction {
    @Override
    public void execute(Frame frame) {
        OperandStack stack = frame.getOperandStack();
        float var0 = stack.popFloat();
        float var1 = stack.popFloat();
        stack.pushFloat(var0 * var1);
    }
}
