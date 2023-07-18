package tzy.tinyPros.JVM06.instructions.comparisons.if_icmp;

import tzy.tinyPros.JVM06.instructions.base.Instruction;
import tzy.tinyPros.JVM06.instructions.base.InstructionBranch;
import tzy.tinyPros.JVM06.rtda.thread.Frame;
import tzy.tinyPros.JVM06.rtda.thread.OperandStack;

/**
 * @author TPureZY
 * @since 2023/7/17 14:57
 **/
public class IF_ICMPGE extends InstructionBranch
{
    @Override
    public void execute(Frame frame) {
        OperandStack stack = frame.getOperandStack();
        int var0 = stack.popInt();
        int var1 = stack.popInt();
        if(var1 >= var0){
            Instruction.branch(frame,this.getOffset());
        }
    }
}