package tzy.tinyPros.JVM08.instructions.math.rem;

import tzy.tinyPros.JVM08.instructions.base.NoOperandsInstruction;
import tzy.tinyPros.JVM08.rtda.thread.Frame;
import tzy.tinyPros.JVM08.rtda.thread.OperandStack;

/**
 * @author TPureZY
 * @since 2023/7/17 1:49
 **/
public class IREM extends NoOperandsInstruction {
    @Override
    public void execute(Frame frame) {
        OperandStack stack = frame.getOperandStack();
        int var0 = stack.popInt();
        int var1 = stack.popInt();
        if(var0 == 0){
            throw new ArithmeticException("rem bt zero!");
        }
        stack.pushInt(var1 % var0);
    }
}