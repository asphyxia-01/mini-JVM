package tzy.tinyPros.JVM08.instructions.comparisons.if_acmp;

import tzy.tinyPros.JVM08.instructions.base.Instruction;
import tzy.tinyPros.JVM08.instructions.base.InstructionBranch;
import tzy.tinyPros.JVM08.rtda.thread.Frame;

/**
 * @author TPureZY
 * @since 2023/7/17 14:50
 * <p>
 * 带有IF的指令都是有跳转功能的指令，操作码后面的操作数是offset
 **/
public class IF_ACMPNE extends InstructionBranch {
    @Override
    public void execute(Frame frame) {
        if (!this._acmp(frame)) {
            Instruction.branch(frame, this.getOffset());
        }
    }
}
