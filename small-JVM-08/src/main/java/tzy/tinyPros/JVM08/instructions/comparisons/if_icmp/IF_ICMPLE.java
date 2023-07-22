package tzy.tinyPros.JVM08.instructions.comparisons.if_icmp;

import tzy.tinyPros.JVM08.instructions.base.Instruction;
import tzy.tinyPros.JVM08.instructions.base.InstructionBranch;
import tzy.tinyPros.JVM08.rtda.thread.Frame;
import tzy.tinyPros.JVM08.rtda.thread.OperandStack;

/**
 * @author TPureZY
 * @since 2023/7/17 14:59
 **/
public class IF_ICMPLE extends InstructionBranch {
    @Override
    public void execute(Frame frame) {
        OperandStack stack = frame.getOperandStack();
        int var0 = stack.popInt();
        int var1 = stack.popInt();
        if (var1 <= var0) {
            Instruction.branch(frame, this.getOffset());
        }
    }
}
