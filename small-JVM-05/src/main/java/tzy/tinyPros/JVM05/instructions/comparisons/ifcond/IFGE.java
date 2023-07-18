package tzy.tinyPros.JVM05.instructions.comparisons.ifcond;

import tzy.tinyPros.JVM05.instructions.base.Instruction;
import tzy.tinyPros.JVM05.instructions.base.InstructionBranch;
import tzy.tinyPros.JVM05.rtda.thread.Frame;
import tzy.tinyPros.JVM05.rtda.thread.OperandStack;

/**
 * @author TPureZY
 * @since 2023/7/17 15:53
 **/
public class IFGE extends InstructionBranch {
    @Override
    public void execute(Frame frame) {
        int i = frame.getOperandStack().popInt();
        if(i >= 0){
            Instruction.branch(frame,this.getOffset());
        }
    }
}