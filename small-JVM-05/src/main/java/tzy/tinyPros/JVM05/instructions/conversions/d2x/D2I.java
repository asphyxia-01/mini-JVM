package tzy.tinyPros.JVM05.instructions.conversions.d2x;

import tzy.tinyPros.JVM05.instructions.base.NoOperandsInstruction;
import tzy.tinyPros.JVM05.rtda.thread.Frame;
import tzy.tinyPros.JVM05.rtda.thread.OperandStack;

/**
 * @author TPureZY
 * @since 2023/7/17 14:30
 **/
public class D2I extends NoOperandsInstruction {
    @Override
    public void execute(Frame frame) {
        OperandStack stack = frame.getOperandStack();
        double v = stack.popDouble();
        stack.pushInt((int)v);
    }
}
