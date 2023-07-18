package tzy.tinyPros.JVM06.instructions.math.add;

import tzy.tinyPros.JVM06.instructions.base.NoOperandsInstruction;
import tzy.tinyPros.JVM06.rtda.thread.Frame;
import tzy.tinyPros.JVM06.rtda.thread.OperandStack;

/**
 * @author TPureZY
 * @since 2023/7/16 20:31
 **/
public class DADD extends NoOperandsInstruction {
    @Override
    public void execute(Frame frame) {
        OperandStack stack = frame.getOperandStack();
        double v0 = stack.popDouble();
        double v1 = stack.popDouble();
        stack.pushDouble(v0 + v1);
    }
}