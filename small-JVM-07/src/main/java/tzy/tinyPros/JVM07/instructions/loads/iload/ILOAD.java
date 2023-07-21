package tzy.tinyPros.JVM07.instructions.loads.iload;

import tzy.tinyPros.JVM07.instructions.base.Index8Instruction;
import tzy.tinyPros.JVM07.rtda.thread.Frame;

/**
 * @author TPureZY
 * @since 2023/7/16 2:16
 **/
public class ILOAD extends Index8Instruction {
    @Override
    public void execute(Frame frame) {
        frame.getOperandStack().pushInt(frame.getLocalVarsTable().getInt(this.getIdx()));
    }
}
