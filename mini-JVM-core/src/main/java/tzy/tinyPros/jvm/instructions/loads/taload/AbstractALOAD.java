package tzy.tinyPros.jvm.instructions.loads.taload;

import tzy.tinyPros.jvm.instructions.base.ArrayInstructionHelper;
import tzy.tinyPros.jvm.instructions.base.NoOperandsInstruction;
import tzy.tinyPros.jvm.rtda.heap.methodarea.Object;
import tzy.tinyPros.jvm.rtda.thread.OperandStack;

/**
 * @author TPureZY
 * @since 2023/7/26 22:00
 **/
public abstract class AbstractALOAD extends NoOperandsInstruction {
    protected int idx;
    protected Object arrObj;

    protected void acquireStackInfo(OperandStack stack) {
        this.idx = stack.popInt();
        this.arrObj = stack.popRef();
        ArrayInstructionHelper.checkNotNull(this.arrObj);
        ArrayInstructionHelper.checkIndex(this.idx, this.arrObj.arrayLength());
    }
}
