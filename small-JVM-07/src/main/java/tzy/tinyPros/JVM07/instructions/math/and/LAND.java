package tzy.tinyPros.JVM07.instructions.math.and;

import tzy.tinyPros.JVM07.instructions.base.NoOperandsInstruction;
import tzy.tinyPros.JVM07.rtda.thread.Frame;
import tzy.tinyPros.JVM07.rtda.thread.OperandStack;

/**
 * @author TPureZY
 * @since 2023/7/16 20:40
 **/
public class LAND extends NoOperandsInstruction {
    @Override
    public void execute(Frame frame) {
        OperandStack stack = frame.getOperandStack();
        long v0 = stack.popLong();
        long v1 = stack.popLong();
        stack.pushLong(v0 & v1);
    }
}
