package tzy.tinyPros.JVM06.instructions.conversions.i2x;

import tzy.tinyPros.JVM06.instructions.base.NoOperandsInstruction;
import tzy.tinyPros.JVM06.rtda.thread.Frame;
import tzy.tinyPros.JVM06.rtda.thread.OperandStack;

/**
 * @author TPureZY
 * @since 2023/7/17 14:18
 **/
public class I2S extends NoOperandsInstruction {
    @Override
    public void execute(Frame frame) {
        OperandStack stack = frame.getOperandStack();
        int i = stack.popInt();
        stack.pushInt((short)i);
    }
}