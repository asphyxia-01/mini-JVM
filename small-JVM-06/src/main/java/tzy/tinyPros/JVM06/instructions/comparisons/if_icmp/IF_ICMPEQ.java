package tzy.tinyPros.JVM06.instructions.comparisons.if_icmp;

import tzy.tinyPros.JVM06.instructions.base.Instruction;
import tzy.tinyPros.JVM06.instructions.base.InstructionBranch;
import tzy.tinyPros.JVM06.rtda.thread.Frame;
import tzy.tinyPros.JVM06.rtda.thread.OperandStack;

/**
 * @author TPureZY
 * @since 2023/7/17 14:52
 **/
public class IF_ICMPEQ extends InstructionBranch {
    @Override
    public void execute(Frame frame) {
        OperandStack stack = frame.getOperandStack();
        int var0 = stack.popInt();
        int var1 = stack.popInt();
        if(var0 == var1){
            Instruction.branch(frame,this.getOffset());
        }
    }
}
