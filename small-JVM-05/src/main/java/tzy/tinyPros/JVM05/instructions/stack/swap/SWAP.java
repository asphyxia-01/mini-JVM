package tzy.tinyPros.JVM05.instructions.stack.swap;

import tzy.tinyPros.JVM05.instructions.base.NoOperandsInstruction;
import tzy.tinyPros.JVM05.rtda.thread.Frame;
import tzy.tinyPros.JVM05.rtda.thread.OperandStack;
import tzy.tinyPros.JVM05.rtda.thread.Slot;

/**
 * @author TPureZY
 * @since 2023/7/16 17:29
 * <p>
 * Swap the top two operand stack values
 * <blockquote><pre>
 * bottom -> top
 * [...][c][b][a]
 *           \/
 *           /\
 *          V  V
 * [...][c][a][b]
 * </pre></blockquote>
 **/
public class SWAP extends NoOperandsInstruction {
    @Override
    public void execute(Frame frame) {
        OperandStack stack = frame.getOperandStack();
        Slot var0 = stack.popSlot();
        Slot var1 = stack.popSlot();
        stack.pushSlot(var0);
        stack.pushSlot(var1);
    }
}
