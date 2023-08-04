package tzy.tinyPros.jvm.instructions.math.mul;

import tzy.tinyPros.jvm.instructions.base.NoOperandsInstruction;
import tzy.tinyPros.jvm.rtda.thread.Frame;
import tzy.tinyPros.jvm.rtda.thread.OperandStack;

/**
 * @author TPureZY
 * @since 2023/7/17 1:39
 **/
public class LMUL extends NoOperandsInstruction {
    @Override
    public void execute(Frame frame) {
        OperandStack stack = frame.getOperandStack();
        long var0 = stack.popLong();
        long var1 = stack.popLong();
        stack.pushLong(var0 * var1);
    }
}
