package tzy.tinyPros.jvm.instructions.math.div;

import tzy.tinyPros.jvm.instructions.base.NoOperandsInstruction;
import tzy.tinyPros.jvm.rtda.thread.Frame;
import tzy.tinyPros.jvm.rtda.thread.OperandStack;

/**
 * @author TPureZY
 * @since 2023/7/16 20:45
 **/
public class LDIV extends NoOperandsInstruction {
    @Override
    public void execute(Frame frame) {
        OperandStack stack = frame.getOperandStack();
        long v0 = stack.popLong();
        long v1 = stack.popLong();
        if(v0 == 0){
            throw new ArithmeticException("div by zero!");
        }
        stack.pushLong(v1 / v0);
    }
}
