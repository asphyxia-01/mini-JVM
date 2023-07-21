package tzy.tinyPros.JVM07.instructions.comparisons.if_acmp;

import tzy.tinyPros.JVM07.instructions.base.Instruction;
import tzy.tinyPros.JVM07.instructions.base.InstructionBranch;
import tzy.tinyPros.JVM07.rtda.thread.Frame;

/**
 * @author TPureZY
 * @since 2023/7/17 14:44
 * <p>
 * 带有IF的指令都是有跳转功能的指令，操作码后面的操作数是offset
 **/
public class IF_ACMPEQ extends InstructionBranch {
    @Override
    public void execute(Frame frame) {
        if (this._acmp(frame)) {
            Instruction.branch(frame, this.getOffset());
        }
    }
}