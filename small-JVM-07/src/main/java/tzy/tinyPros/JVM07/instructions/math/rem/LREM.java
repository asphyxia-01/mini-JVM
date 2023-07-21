package tzy.tinyPros.JVM07.instructions.math.rem;

import tzy.tinyPros.JVM07.instructions.base.NoOperandsInstruction;
import tzy.tinyPros.JVM07.rtda.thread.Frame;
import tzy.tinyPros.JVM07.rtda.thread.OperandStack;

/**
 * @author TPureZY
 * @since 2023/7/17 1:49
 **/
public class LREM extends NoOperandsInstruction {
    @Override
    public void execute(Frame frame) {
        OperandStack stack = frame.getOperandStack();
        long var0 = stack.popLong();
        long var1 = stack.popLong();
        if(var0 == 0){
            throw new ArithmeticException("rem bt zero!");
        }
        stack.pushLong(var1 % var0);
    }
}
