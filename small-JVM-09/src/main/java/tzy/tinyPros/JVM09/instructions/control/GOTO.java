package tzy.tinyPros.JVM09.instructions.control;

import tzy.tinyPros.JVM09.instructions.base.Instruction;
import tzy.tinyPros.JVM09.instructions.base.InstructionBranch;
import tzy.tinyPros.JVM09.rtda.thread.Frame;

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