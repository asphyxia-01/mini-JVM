package tzy.tinyPros.jvm.instructions.stores.tastore;

import tzy.tinyPros.jvm.rtda.thread.Frame;
import tzy.tinyPros.jvm.rtda.thread.OperandStack;

/**
 * @author TPureZY
 * @since 2023/7/26 23:21
 **/
public class SASTORE extends AbstractASTORE {

    private int val;

    @Override
    protected void acquireTargetVal(OperandStack stack) {
        this.val = stack.popInt();
    }

    @Override
    public void execute(Frame frame) {
        this.acquireStackInfo(frame.getOperandStack());
        this.arrObj.shorts()[this.idx] = (short) this.val;
    }
}
