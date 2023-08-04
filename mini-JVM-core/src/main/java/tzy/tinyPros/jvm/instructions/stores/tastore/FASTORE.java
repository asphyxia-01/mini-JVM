package tzy.tinyPros.jvm.instructions.stores.tastore;

import tzy.tinyPros.jvm.rtda.thread.Frame;
import tzy.tinyPros.jvm.rtda.thread.OperandStack;

/**
 * @author TPureZY
 * @since 2023/7/26 22:28
 **/
public class FASTORE extends AbstractASTORE {

    private float val;

    @Override
    protected void acquireTargetVal(OperandStack stack) {
        this.val = stack.popFloat();
    }

    @Override
    public void execute(Frame frame) {
        this.acquireStackInfo(frame.getOperandStack());
        this.arrObj.floats()[this.idx] = this.val;
    }
}
