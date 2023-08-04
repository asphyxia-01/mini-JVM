package tzy.tinyPros.jvm.instructions.loads.taload;

import tzy.tinyPros.jvm.rtda.thread.Frame;

/**
 * @author TPureZY
 * @since 2023/7/26 22:08
 **/
public class DALOAD extends AbstractALOAD {
    @Override
    public void execute(Frame frame) {
        this.acquireStackInfo(frame.getOperandStack());
        frame.getOperandStack().pushDouble(this.arrObj.doubles()[this.idx]);
    }
}
