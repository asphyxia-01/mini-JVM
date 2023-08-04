package tzy.tinyPros.jvm.instructions.conversions.f2x;

import tzy.tinyPros.jvm.instructions.base.NoOperandsInstruction;
import tzy.tinyPros.jvm.rtda.thread.Frame;
import tzy.tinyPros.jvm.rtda.thread.OperandStack;

/**
 * @author TPureZY
 * @since 2023/7/17 14:29
 **/
public class F2L extends NoOperandsInstruction {
    @Override
    public void execute(Frame frame) {
        OperandStack stack = frame.getOperandStack();
        float v = stack.popFloat();
        stack.pushLong((long)v);
    }
}
