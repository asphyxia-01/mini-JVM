package tzy.tinyPros.JVM05.instructions.math.div;

import tzy.tinyPros.JVM05.instructions.base.NoOperandsInstruction;
import tzy.tinyPros.JVM05.rtda.thread.Frame;
import tzy.tinyPros.JVM05.rtda.thread.OperandStack;

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
