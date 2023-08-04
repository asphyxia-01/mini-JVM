package tzy.tinyPros.jvm.instructions.stores.tastore;

import tzy.tinyPros.jvm.rtda.thread.Frame;
import tzy.tinyPros.jvm.rtda.thread.OperandStack;

/**
 * @author TPureZY
 * @since 2023/7/26 23:20
 **/
public class LASTORE extends AbstractASTORE {

    private long val;

    @Override
    protected void acquireTargetVal(OperandStack stack) {
        this.val = stack.popLong();
    }

    @Override
    public void execute(Frame frame) {
        this.acquireStackInfo(frame.getOperandStack());
        this.arrObj.longs()[this.idx] = this.val;
    }
}
