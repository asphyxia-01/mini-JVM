package tzy.tinyPros.jvm.instructions.stack.dup;

import tzy.tinyPros.jvm.instructions.base.NoOperandsInstruction;
import tzy.tinyPros.jvm.rtda.thread.Frame;
import tzy.tinyPros.jvm.rtda.thread.OperandStack;
import tzy.tinyPros.jvm.rtda.thread.Slot;

/**
 * @author TPureZY
 * @since 2023/7/16 17:23
 * <p>
 * 复制栈顶两个变量，然后将复制的变量放到与被复制变量相隔 1 个变量的位置
 * <blockquote><pre>
 * bottom -> top
 * [...][c][b][a]
 *        _/ __/
 *       |  |
 *       V  V
 * [...][b][a][c][b][a]
 * </pre></blockquote>
 **/
public class DUP2_X1 extends NoOperandsInstruction {
    @Override
    public void execute(Frame frame) {
        OperandStack stack = frame.getOperandStack();
        Slot var0 = stack.popSlot();
        Slot var1 = stack.popSlot();
        Slot var2 = stack.popSlot();
        stack.pushSlot(var1);
        stack.pushSlot(var0);
        stack.pushSlot(var2);
        stack.pushSlot(var1);
        stack.pushSlot(var0);
    }
}
