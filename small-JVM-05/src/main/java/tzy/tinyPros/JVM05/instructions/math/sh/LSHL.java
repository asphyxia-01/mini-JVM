package tzy.tinyPros.JVM05.instructions.math.sh;

import tzy.tinyPros.JVM05.instructions.base.NoOperandsInstruction;
import tzy.tinyPros.JVM05.rtda.thread.Frame;
import tzy.tinyPros.JVM05.rtda.thread.OperandStack;

/**
 * @author TPureZY
 * @since 2023/7/17 1:55
 *
 * 算术左移和逻辑左移都是这个指令，因为都是补 0
 **/
public class LSHL extends NoOperandsInstruction {
    @Override
    public void execute(Frame frame) {
        OperandStack stack = frame.getOperandStack();
        long mov = stack.popLong();
        long num = stack.popLong();
        // 因为long类型只有64位，所以只需要取前6位即可表示要移动的数目
        stack.pushLong(num << (mov & 0x3f));
    }
}
