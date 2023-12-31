package tzy.tinyPros.jvm.instructions.stores.tastore;

import tzy.tinyPros.jvm.rtda.thread.Frame;
import tzy.tinyPros.jvm.rtda.thread.OperandStack;

/**
 * @author TPureZY
 * @since 2023/7/26 22:27
 **/
public class DASTORE extends AbstractASTORE {

    private double val;

    @Override
    protected void acquireTargetVal(OperandStack stack) {
        this.val = stack.popDouble();
    }

    @Override
    public void execute(Frame frame) {
        this.acquireStackInfo(frame.getOperandStack());
        this.arrObj.doubles()[this.idx] = val;
    }
}
