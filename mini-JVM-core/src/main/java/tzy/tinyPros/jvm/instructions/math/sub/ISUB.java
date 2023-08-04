package tzy.tinyPros.jvm.instructions.math.sub;

import tzy.tinyPros.jvm.instructions.base.NoOperandsInstruction;
import tzy.tinyPros.jvm.rtda.thread.Frame;
import tzy.tinyPros.jvm.rtda.thread.OperandStack;

/**
 * @author TPureZY
 * @since 2023/7/17 2:08
 **/
public class ISUB extends NoOperandsInstruction {
    @Override
    public void execute(Frame frame) {
        OperandStack stack = frame.getOperandStack();
        int var0 = stack.popInt();
        int var1 = stack.popInt();
        stack.pushInt(var1 - var0);
    }
}
