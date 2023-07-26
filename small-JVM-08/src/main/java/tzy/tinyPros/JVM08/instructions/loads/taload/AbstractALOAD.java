package tzy.tinyPros.JVM08.instructions.loads.taload;

import tzy.tinyPros.JVM08.instructions.base.ArrayInstructionHelper;
import tzy.tinyPros.JVM08.instructions.base.NoOperandsInstruction;
import tzy.tinyPros.JVM08.rtda.heap.methodarea.Object;
import tzy.tinyPros.JVM08.rtda.thread.OperandStack;

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
