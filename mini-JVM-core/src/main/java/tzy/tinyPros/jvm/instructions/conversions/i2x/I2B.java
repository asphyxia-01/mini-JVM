package tzy.tinyPros.jvm.instructions.conversions.i2x;

import tzy.tinyPros.jvm.instructions.base.NoOperandsInstruction;
import tzy.tinyPros.jvm.rtda.thread.Frame;
import tzy.tinyPros.jvm.rtda.thread.OperandStack;

/**
 * @author TPureZY
 * @since 2023/7/17 14:16
 **/
public class I2B extends NoOperandsInstruction {
    @Override
    public void execute(Frame frame) {
        OperandStack stack = frame.getOperandStack();
        int i = stack.popInt();
        stack.pushInt((byte)i);
    }
}
