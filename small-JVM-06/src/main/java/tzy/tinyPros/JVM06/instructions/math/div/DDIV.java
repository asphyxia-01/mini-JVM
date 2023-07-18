package tzy.tinyPros.JVM06.instructions.math.div;

import tzy.tinyPros.JVM06.instructions.base.NoOperandsInstruction;
import tzy.tinyPros.JVM06.rtda.thread.Frame;
import tzy.tinyPros.JVM06.rtda.thread.OperandStack;

/**
 * @author TPureZY
 * @since 2023/7/16 20:41
 **/
public class DDIV extends NoOperandsInstruction {
    @Override
    public void execute(Frame frame) {
        OperandStack stack = frame.getOperandStack();
        double v0 = stack.popDouble();
        double v1 = stack.popDouble();
        stack.pushDouble(v1 / v0);
    }
}
