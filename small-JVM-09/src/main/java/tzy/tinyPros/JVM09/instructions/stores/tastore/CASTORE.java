package tzy.tinyPros.JVM09.instructions.stores.tastore;

import tzy.tinyPros.JVM09.rtda.thread.Frame;
import tzy.tinyPros.JVM09.rtda.thread.OperandStack;

/**
 * @author TPureZY
 * @since 2023/7/26 22:25
 **/
public class CASTORE extends AbstractASTORE {
    private int val;

    @Override
    protected void acquireTargetVal(OperandStack stack) {
        this.val = stack.popInt();
    }

    @Override
    public void execute(Frame frame) {
        this.acquireStackInfo(frame.getOperandStack());
        this.arrObj.chars()[this.idx] = (char) this.val;
    }
}
