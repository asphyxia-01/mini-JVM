package tzy.tinyPros.JVM08.instructions.stores.tastore;

import tzy.tinyPros.JVM08.rtda.thread.Frame;
import tzy.tinyPros.JVM08.rtda.thread.OperandStack;

/**
 * @author TPureZY
 * @since 2023/7/26 22:24
 **/
public class BASTORE extends AbstractASTORE {

    private int val;

    @Override
    protected void acquireTargetVal(OperandStack stack) {
        this.val = stack.popInt();
    }

    @Override
    public void execute(Frame frame) {
        this.acquireStackInfo(frame.getOperandStack());
        this.arrObj.bytes()[this.idx] = (byte) this.val;
    }
}
