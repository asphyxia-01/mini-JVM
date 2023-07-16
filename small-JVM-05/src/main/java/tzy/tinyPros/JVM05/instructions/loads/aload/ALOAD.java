package tzy.tinyPros.JVM05.instructions.loads.aload;

import tzy.tinyPros.JVM05.instructions.base.Index8Instruction;
import tzy.tinyPros.JVM05.rtda.thread.Frame;

/**
 * @author TPureZY
 * @since 2023/7/16 0:18
 **/
public class ALOAD extends Index8Instruction {
    @Override
    public void execute(Frame frame) {
        Object ref = frame.getLocalVarsTable().getRef(this.getIdx());
        frame.getOperandStack().pushRef(ref);
    }
}
