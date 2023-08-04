package tzy.tinyPros.jvm.instructions.math.add;

import tzy.tinyPros.jvm.instructions.base.NoOperandsInstruction;
import tzy.tinyPros.jvm.rtda.thread.Frame;
import tzy.tinyPros.jvm.rtda.thread.OperandStack;

/**
 * @author TPureZY
 * @since 2023/7/16 20:32
 **/
public class LADD extends NoOperandsInstruction {
    @Override
    public void execute(Frame frame) {
        OperandStack stack = frame.getOperandStack();
        long v0 = stack.popLong();
        long v1 = stack.popLong();
        stack.pushLong(v0 + v1);
    }
}
