package tzy.tinyPros.jvm.instructions.comparisons.if_icmp;

import tzy.tinyPros.jvm.instructions.base.Instruction;
import tzy.tinyPros.jvm.instructions.base.InstructionBranch;
import tzy.tinyPros.jvm.rtda.thread.Frame;
import tzy.tinyPros.jvm.rtda.thread.OperandStack;

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
