package tzy.tinyPros.JVM06.instructions.math.neg;

import tzy.tinyPros.JVM06.instructions.base.NoOperandsInstruction;
import tzy.tinyPros.JVM06.rtda.thread.Frame;
import tzy.tinyPros.JVM06.rtda.thread.OperandStack;

/**
 * @author TPureZY
 * @since 2023/7/17 1:41
 **/
public class DNEG extends NoOperandsInstruction {
    @Override
    public void execute(Frame frame) {
        OperandStack stack = frame.getOperandStack();
        double var0 = stack.popDouble();
        stack.pushDouble(-var0);
    }
}