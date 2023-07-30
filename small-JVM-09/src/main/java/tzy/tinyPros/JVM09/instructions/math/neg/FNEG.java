package tzy.tinyPros.JVM09.instructions.math.neg;

import tzy.tinyPros.JVM09.instructions.base.NoOperandsInstruction;
import tzy.tinyPros.JVM09.rtda.thread.Frame;
import tzy.tinyPros.JVM09.rtda.thread.OperandStack;

/**
 * @author TPureZY
 * @since 2023/7/17 1:41
 **/
public class FNEG extends NoOperandsInstruction {
    @Override
    public void execute(Frame frame) {
        OperandStack stack = frame.getOperandStack();
        float var0 = stack.popFloat();
        stack.pushFloat(-var0);
    }
}