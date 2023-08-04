package tzy.tinyPros.jvm.instructions.extended;

import tzy.tinyPros.jvm.instructions.base.Instruction;
import tzy.tinyPros.jvm.instructions.base.InstructionBranch;
import tzy.tinyPros.jvm.rtda.thread.Frame;

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
