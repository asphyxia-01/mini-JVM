package tzy.tinyPros.JVM08.instructions.comparisons.lcmp;

import tzy.tinyPros.JVM08.instructions.base.NoOperandsInstruction;
import tzy.tinyPros.JVM08.rtda.thread.Frame;
import tzy.tinyPros.JVM08.rtda.thread.OperandStack;

/**
 * @author TPureZY
 * @since 2023/7/17 15:56
 * <p>
 * 对于整数的比较没有NaN的情况
 **/
public class LCMP extends NoOperandsInstruction {
    @Override
    public void execute(Frame frame) {
        OperandStack stack = frame.getOperandStack();
        long var0 = stack.popLong();
        long var1 = stack.popLong();
        if (var1 > var0) {
            stack.pushInt(1);
            return;
        }
        if (var1 == var0) {
            stack.pushInt(0);
            return;
        }
        stack.pushInt(-1);
    }
}
