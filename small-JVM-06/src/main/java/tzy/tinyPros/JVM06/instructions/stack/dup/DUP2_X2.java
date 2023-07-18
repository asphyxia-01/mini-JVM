package tzy.tinyPros.JVM06.instructions.stack.dup;

import tzy.tinyPros.JVM06.instructions.base.NoOperandsInstruction;
import tzy.tinyPros.JVM06.rtda.thread.Frame;
import tzy.tinyPros.JVM06.rtda.thread.OperandStack;
import tzy.tinyPros.JVM06.rtda.thread.Slot;

/**
 * @author TPureZY
 * @since 2023/7/16 17:26
 * <p>
 * 复制栈顶两个变量，然后将复制的变量放到与被复制变量相隔 2 个变量的位置
 * <blockquote><pre>
 * bottom -> top
 * [...][d][c][b][a]
 *        ____/ __/
 *       |   __/
 *       V  V
 * [...][b][a][d][c][b][a]
 * </pre></blockquote>
 **/
public class DUP2_X2 extends NoOperandsInstruction {
    @Override
    public void execute(Frame frame) {
        OperandStack stack = frame.getOperandStack();
        Slot var0 = stack.popSlot();
        Slot var1 = stack.popSlot();
        Slot var2 = stack.popSlot();
        Slot var3 = stack.popSlot();
        stack.pushSlot(var1);
        stack.pushSlot(var0);
        stack.pushSlot(var3);
        stack.pushSlot(var2);
        stack.pushSlot(var1);
        stack.pushSlot(var0);
    }
}
