package tzy.tinyPros.jvm.instructions.math.div;

import tzy.tinyPros.jvm.instructions.base.NoOperandsInstruction;
import tzy.tinyPros.jvm.rtda.thread.Frame;
import tzy.tinyPros.jvm.rtda.thread.OperandStack;

/**
 * @author TPureZY
 * @since 2023/7/16 20:43
 **/
public class IDIV extends NoOperandsInstruction {
    @Override
    public void execute(Frame frame) {
        OperandStack stack = frame.getOperandStack();
        int v0 = stack.popInt();
        int v1 = stack.popInt();
        if(v0 == 0){
            throw new ArithmeticException("div by zero!");
        }
        stack.pushInt(v1 / v0);
    }
}
