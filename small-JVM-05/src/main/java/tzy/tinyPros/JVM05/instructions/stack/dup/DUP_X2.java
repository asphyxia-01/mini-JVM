package tzy.tinyPros.JVM05.instructions.stack.dup;

import tzy.tinyPros.JVM05.instructions.base.NoOperandsInstruction;
import tzy.tinyPros.JVM05.rtda.thread.Frame;
import tzy.tinyPros.JVM05.rtda.thread.OperandStack;
import tzy.tinyPros.JVM05.rtda.thread.Slot;

/**
 * @author TPureZY
 * @since 2023/7/16 17:20
 * <p>
 * 复制栈顶变量，然后将复制的变量放到与被复制变量相隔 2 个变量（栈顶及下两三个）的位置
 * <blockquote><pre>
 * bottom -> top
 * [...][c][b][a]
 *        _____/
 *       |
 *       V
 * [...][a][c][b][a]
 * </pre></blockquote>
 **/
public class DUP_X2 extends NoOperandsInstruction {
    @Override
    public void execute(Frame frame) {
        OperandStack stack = frame.getOperandStack();
        Slot var0 = stack.popSlot();
        Slot var1 = stack.popSlot();
        Slot var2 = stack.popSlot();
        stack.pushSlot(var0);
        stack.pushSlot(var2);
        stack.pushSlot(var1);
        stack.pushSlot(var0);
    }
}
