package tzy.tinyPros.jvm.instructions.control;

import tzy.tinyPros.jvm.instructions.base.Instruction;
import tzy.tinyPros.jvm.instructions.base.InstructionBranch;
import tzy.tinyPros.jvm.rtda.thread.Frame;

/**
 * @author TPureZY
 * @since 2023/7/17 16:04
 **/
public class GOTO extends InstructionBranch {
    @Override
    public void execute(Frame frame) {
        Instruction.branch(frame,this.getOffset());
    }
}
