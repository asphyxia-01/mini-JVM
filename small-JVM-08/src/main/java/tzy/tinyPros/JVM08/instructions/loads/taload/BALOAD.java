package tzy.tinyPros.JVM08.instructions.loads.taload;

import tzy.tinyPros.JVM08.instructions.base.ArrayInstructionHelper;
import tzy.tinyPros.JVM08.instructions.base.NoOperandsInstruction;
import tzy.tinyPros.JVM08.rtda.thread.Frame;

/**
 * @author TPureZY
 * @since 2023/7/26 22:00
 **/
public class BALOAD extends AbstractALOAD {
    @Override
    public void execute(Frame frame) {
        this.acquireStackInfo(frame.getOperandStack());
        frame.getOperandStack().pushInt(this.arrObj.bytes()[this.idx]);
    }
}
