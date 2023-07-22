package tzy.tinyPros.JVM08.instructions.math.sh;

import tzy.tinyPros.JVM08.instructions.base.NoOperandsInstruction;
import tzy.tinyPros.JVM08.rtda.thread.Frame;
import tzy.tinyPros.JVM08.rtda.thread.OperandStack;

/**
 * @author TPureZY
 * @since 2023/7/17 1:55
 * <p>
 * Logical shift right int
 **/
public class IUSHR extends NoOperandsInstruction {
    @Override
    public void execute(Frame frame) {
        OperandStack stack = frame.getOperandStack();
        int mov = stack.popInt();
        int num = stack.popInt();
        // 因为Int类型只有32位，所以只需要取前5位即可表示要移动的数目
        stack.pushInt(num >>> (mov & 0x1f));
    }
}
