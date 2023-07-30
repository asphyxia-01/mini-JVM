package tzy.tinyPros.JVM09.instructions.math.mul;

import tzy.tinyPros.JVM09.instructions.base.NoOperandsInstruction;
import tzy.tinyPros.JVM09.rtda.thread.Frame;
import tzy.tinyPros.JVM09.rtda.thread.OperandStack;

/**
 * @author TPureZY
 * @since 2023/7/17 1:39
 **/
public class DMUL extends NoOperandsInstruction {
    @Override
    public void execute(Frame frame) {
        OperandStack stack = frame.getOperandStack();
        double var0 = stack.popDouble();
        double var1 = stack.popDouble();
        stack.pushDouble(var0 * var1);
    }
}
