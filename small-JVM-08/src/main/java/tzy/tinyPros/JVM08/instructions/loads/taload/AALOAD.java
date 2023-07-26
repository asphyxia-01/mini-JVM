package tzy.tinyPros.JVM08.instructions.loads.taload;

import tzy.tinyPros.JVM08.instructions.base.ArrayInstructionHelper;
import tzy.tinyPros.JVM08.rtda.thread.Frame;

/**
 * @author TPureZY
 * @since 2023/7/26 21:51
 **/
public class AALOAD extends AbstractALOAD {
    @Override
    public void execute(Frame frame) {
        this.acquireStackInfo(frame.getOperandStack());
        frame.getOperandStack().pushRef(this.arrObj.refs()[this.idx]);
    }
}
