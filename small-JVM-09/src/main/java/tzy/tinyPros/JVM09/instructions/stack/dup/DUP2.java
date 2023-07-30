package tzy.tinyPros.JVM09.instructions.stack.dup;

import tzy.tinyPros.JVM09.instructions.base.NoOperandsInstruction;
import tzy.tinyPros.JVM09.rtda.thread.Frame;
import tzy.tinyPros.JVM09.rtda.thread.OperandStack;
import tzy.tinyPros.JVM09.rtda.thread.Slot;

/**
 * @author TPureZY
 * @since 2023/7/16 17:00
 * <p>
 * 区别于DUP，DUP2适用于需要占用两个Slot的类型，比如double、long
 * <p>
 * Duplicate the top one or two operand stack values
 * <blockquote><pre>
 * bottom -> top
 * [...][c][b][a]____
 *           \____   |
 *                |  |
 *                V  V
 * [...][c][b][a][b][a]
 * </pre></blockquote>
 * <p>
 * 两个为一组，按组复制，注意不是赋值两次的意思，是两个一组，按组复制依一次
 **/
public class DUP2 extends NoOperandsInstruction {
    @Override
    public void execute(Frame frame) {
        OperandStack stack = frame.getOperandStack();
        Slot var0 = stack.popSlot();
        Slot var1 = stack.popSlot();
        stack.pushSlot(var1);
        stack.pushSlot(var0);
        stack.pushSlot(var1);
        stack.pushSlot(var0);
    }
}
