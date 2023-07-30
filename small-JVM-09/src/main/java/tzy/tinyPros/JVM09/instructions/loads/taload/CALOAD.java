package tzy.tinyPros.JVM09.instructions.loads.taload;

import tzy.tinyPros.JVM09.rtda.thread.Frame;

/**
 * @author TPureZY
 * @since 2023/7/26 22:06
 **/
public class CALOAD extends AbstractALOAD {
    @Override
    public void execute(Frame frame) {
        this.acquireStackInfo(frame.getOperandStack());
        frame.getOperandStack().pushInt(this.arrObj.chars()[this.idx]);
    }
}
