package tzy.tinyPros.JVM05.instructions.math.add;

import tzy.tinyPros.JVM05.instructions.base.NoOperandsInstruction;
import tzy.tinyPros.JVM05.rtda.thread.Frame;
import tzy.tinyPros.JVM05.rtda.thread.OperandStack;

/**
 * @author TPureZY
 * @since 2023/7/16 20:33
 **/
public class FADD extends NoOperandsInstruction {
    @Override
    public void execute(Frame frame) {
        OperandStack stack = frame.getOperandStack();
        float v0 = stack.popFloat();
        float v1 = stack.popFloat();
        stack.pushFloat(v0 + v1);
    }
}
