package tzy.tinyPros.JVM08.instructions.extended;

import tzy.tinyPros.JVM08.instructions.base.Instruction;
import tzy.tinyPros.JVM08.instructions.base.InstructionBranch;
import tzy.tinyPros.JVM08.rtda.thread.Frame;

/**
 * @author TPureZY
 * @since 2023/7/17 17:34
 **/
public class IFNONNULL extends InstructionBranch {
    @Override
    public void execute(Frame frame) {
        Object ref = frame.getOperandStack().popRef();
        if(ref != null){
            Instruction.branch(frame,this.getOffset());
        }
    }
}
