package tzy.tinyPros.jvm.instructions.loads.taload;

import tzy.tinyPros.jvm.rtda.thread.Frame;

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
