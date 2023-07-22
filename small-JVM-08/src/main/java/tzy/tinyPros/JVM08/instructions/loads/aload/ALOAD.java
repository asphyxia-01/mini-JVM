package tzy.tinyPros.JVM08.instructions.loads.aload;

import tzy.tinyPros.JVM08.instructions.base.Index8Instruction;
import tzy.tinyPros.JVM08.rtda.thread.Frame;
import tzy.tinyPros.JVM08.rtda.heap.methodarea.Object;

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
