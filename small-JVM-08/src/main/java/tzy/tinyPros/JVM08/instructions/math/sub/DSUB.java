package tzy.tinyPros.JVM08.instructions.math.sub;

import tzy.tinyPros.JVM08.instructions.base.NoOperandsInstruction;
import tzy.tinyPros.JVM08.rtda.thread.Frame;
import tzy.tinyPros.JVM08.rtda.thread.OperandStack;

/**
 * @author TPureZY
 * @since 2023/7/17 2:08
 **/
public class DSUB extends NoOperandsInstruction {
    @Override
    public void execute(Frame frame) {
        OperandStack stack = frame.getOperandStack();
        double var0 = stack.popDouble();
        double var1 = stack.popDouble();
        stack.pushDouble(var1 - var0);
    }
}
