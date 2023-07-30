package tzy.tinyPros.JVM09.instructions.loads.lload;

import tzy.tinyPros.JVM09.instructions.base.Index8Instruction;
import tzy.tinyPros.JVM09.rtda.thread.Frame;

/**
 * @author TPureZY
 * @since 2023/7/16 2:18
 **/
public class LLOAD extends Index8Instruction {
    @Override
    public void execute(Frame frame) {
        frame.getOperandStack().pushLong(frame.getLocalVarsTable().getLong(this.getIdx()));
    }
}
