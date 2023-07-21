package tzy.tinyPros.JVM07.instructions.comparisons.ifcond;

import tzy.tinyPros.JVM07.instructions.base.Instruction;
import tzy.tinyPros.JVM07.instructions.base.InstructionBranch;
import tzy.tinyPros.JVM07.rtda.thread.Frame;

/**
 * @author TPureZY
 * @since 2023/7/17 15:53
 **/
public class IFLE extends InstructionBranch {
    @Override
    public void execute(Frame frame) {
        int i = frame.getOperandStack().popInt();
        if(i <= 0){
            Instruction.branch(frame,this.getOffset());
        }
    }
}
