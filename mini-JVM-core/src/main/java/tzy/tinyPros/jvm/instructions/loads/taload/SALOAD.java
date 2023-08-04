package tzy.tinyPros.jvm.instructions.loads.taload;

import tzy.tinyPros.jvm.rtda.thread.Frame;

/**
 * @author TPureZY
 * @since 2023/7/26 22:13
 **/
public class SALOAD extends AbstractALOAD {
    @Override
    public void execute(Frame frame) {
        this.acquireStackInfo(frame.getOperandStack());
        frame.getOperandStack().pushInt(this.arrObj.shorts()[this.idx]);
    }
}
