package tzy.tinyPros.JVM07.instructions.math.sub;

import tzy.tinyPros.JVM07.instructions.base.NoOperandsInstruction;
import tzy.tinyPros.JVM07.rtda.thread.Frame;
import tzy.tinyPros.JVM07.rtda.thread.OperandStack;

/**
 * @author TPureZY
 * @since 2023/7/17 2:08
 **/
public class FSUB extends NoOperandsInstruction {
    @Override
    public void execute(Frame frame) {
        OperandStack stack = frame.getOperandStack();
        float var0 = stack.popFloat();
        float var1 = stack.popFloat();
        stack.pushFloat(var1 - var0);
    }
}
