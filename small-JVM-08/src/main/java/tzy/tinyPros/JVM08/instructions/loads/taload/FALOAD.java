package tzy.tinyPros.JVM08.instructions.loads.taload;

import tzy.tinyPros.JVM08.rtda.thread.Frame;

/**
 * @author TPureZY
 * @since 2023/7/26 22:10
 **/
public class FALOAD extends AbstractALOAD {
    @Override
    public void execute(Frame frame) {
        this.acquireStackInfo(frame.getOperandStack());
        frame.getOperandStack().pushFloat(this.arrObj.floats()[this.idx]);
    }
}
