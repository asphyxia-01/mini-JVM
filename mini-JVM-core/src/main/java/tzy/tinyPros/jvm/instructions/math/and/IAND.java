package tzy.tinyPros.jvm.instructions.math.and;

import tzy.tinyPros.jvm.instructions.base.NoOperandsInstruction;
import tzy.tinyPros.jvm.rtda.thread.Frame;
import tzy.tinyPros.jvm.rtda.thread.OperandStack;

/**
 * @author TPureZY
 * @since 2023/7/16 20:39
 **/
public class IAND extends NoOperandsInstruction {
    @Override
    public void execute(Frame frame) {
        OperandStack stack = frame.getOperandStack();
        int v0 = stack.popInt();
        int v1 = stack.popInt();
        stack.pushInt(v0 & v1);
    }
}
