package tzy.tinyPros.JVM06.instructions.math.add;

import tzy.tinyPros.JVM06.instructions.base.NoOperandsInstruction;
import tzy.tinyPros.JVM06.rtda.thread.Frame;
import tzy.tinyPros.JVM06.rtda.thread.OperandStack;

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
