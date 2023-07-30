package tzy.tinyPros.JVM09.instructions.loads.taload;

import tzy.tinyPros.JVM09.rtda.thread.Frame;

/**
 * @author TPureZY
 * @since 2023/7/26 22:11
 **/
public class LALOAD extends AbstractALOAD {
    @Override
    public void execute(Frame frame) {
        this.acquireStackInfo(frame.getOperandStack());
        frame.getOperandStack().pushLong(this.arrObj.longs()[this.idx]);
    }
}