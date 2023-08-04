package tzy.tinyPros.jvm.instructions.math.sub;

import tzy.tinyPros.jvm.instructions.base.NoOperandsInstruction;
import tzy.tinyPros.jvm.rtda.thread.Frame;
import tzy.tinyPros.jvm.rtda.thread.OperandStack;

/**
 * @author TPureZY
 * @since 2023/7/17 2:08
 **/
public class LSUB extends NoOperandsInstruction {
    @Override
    public void execute(Frame frame) {
        OperandStack stack = frame.getOperandStack();
        long var0 = stack.popLong();
        long var1 = stack.popLong();
        stack.pushLong(var1 - var0);
    }
}
