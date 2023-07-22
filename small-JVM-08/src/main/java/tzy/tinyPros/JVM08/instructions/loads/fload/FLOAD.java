package tzy.tinyPros.JVM08.instructions.loads.fload;

import tzy.tinyPros.JVM08.instructions.base.Index8Instruction;
import tzy.tinyPros.JVM08.rtda.thread.Frame;

/**
 * @author TPureZY
 * @since 2023/7/16 2:14
 **/
public class FLOAD extends Index8Instruction {
    @Override
    public void execute(Frame frame) {
        frame.getOperandStack().pushFloat(frame.getLocalVarsTable().getFloat(this.getIdx()));
    }
}
