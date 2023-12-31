package tzy.tinyPros.jvm.instructions.comparisons.ifcond;

import tzy.tinyPros.jvm.instructions.base.Instruction;
import tzy.tinyPros.jvm.instructions.base.InstructionBranch;
import tzy.tinyPros.jvm.rtda.thread.Frame;

/**
 * @author TPureZY
 * @since 2023/7/17 15:53
 **/
public class IFGT extends InstructionBranch {
    @Override
    public void execute(Frame frame) {
        int i = frame.getOperandStack().popInt();
        if(i > 0){
            Instruction.branch(frame,this.getOffset());
        }
    }
}
