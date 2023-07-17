package tzy.tinyPros.JVM05.instructions.conversions.f2x;

import tzy.tinyPros.JVM05.instructions.base.NoOperandsInstruction;
import tzy.tinyPros.JVM05.rtda.thread.Frame;
import tzy.tinyPros.JVM05.rtda.thread.OperandStack;

/**
 * @author TPureZY
 * @since 2023/7/17 14:30
 **/
public class F2D extends NoOperandsInstruction {
    @Override
    public void execute(Frame frame) {
        OperandStack stack = frame.getOperandStack();
        float v = stack.popFloat();
        stack.pushDouble(v);
    }
}
