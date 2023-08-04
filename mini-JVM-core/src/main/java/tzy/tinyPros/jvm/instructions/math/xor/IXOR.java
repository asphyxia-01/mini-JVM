package tzy.tinyPros.jvm.instructions.math.xor;

import tzy.tinyPros.jvm.instructions.base.NoOperandsInstruction;
import tzy.tinyPros.jvm.rtda.thread.Frame;
import tzy.tinyPros.jvm.rtda.thread.OperandStack;

/**
 * @author TPureZY
 * @since 2023/7/17 2:11
 **/
public class IXOR extends NoOperandsInstruction {
    @Override
    public void execute(Frame frame) {
        OperandStack stack = frame.getOperandStack();
        int var0 = stack.popInt();
        int var1 = stack.popInt();
        stack.pushInt(var0 ^ var1);
    }
}
