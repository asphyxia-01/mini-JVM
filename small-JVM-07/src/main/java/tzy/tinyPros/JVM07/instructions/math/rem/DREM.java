package tzy.tinyPros.JVM07.instructions.math.rem;

import tzy.tinyPros.JVM07.instructions.base.NoOperandsInstruction;
import tzy.tinyPros.JVM07.rtda.thread.Frame;
import tzy.tinyPros.JVM07.rtda.thread.OperandStack;

/**
 * @author TPureZY
 * @since 2023/7/17 1:49
 **/
public class DREM extends NoOperandsInstruction {
    @Override
    public void execute(Frame frame) {
        OperandStack stack = frame.getOperandStack();
        double var0 = stack.popDouble();
        double var1 = stack.popDouble();
        stack.pushDouble(var1 % var0);
    }
}
