package tzy.tinyPros.jvm.instructions.stores.tastore;

import tzy.tinyPros.jvm.rtda.heap.methodarea.Object;
import tzy.tinyPros.jvm.rtda.thread.Frame;
import tzy.tinyPros.jvm.rtda.thread.OperandStack;

/**
 * @author TPureZY
 * @since 2023/7/26 22:21
 **/
public class AASTORE extends AbstractASTORE {

    protected Object targetObj;

    @Override
    protected void acquireTargetVal(OperandStack stack) {
        this.targetObj = stack.popRef();
    }

    @Override
    public void execute(Frame frame) {
        this.acquireStackInfo(frame.getOperandStack());
        this.arrObj.refs()[this.idx] = this.targetObj;
    }
}
