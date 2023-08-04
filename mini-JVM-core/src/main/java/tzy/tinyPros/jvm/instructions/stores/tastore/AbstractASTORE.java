package tzy.tinyPros.jvm.instructions.stores.tastore;

import tzy.tinyPros.jvm.instructions.base.ArrayInstructionHelper;
import tzy.tinyPros.jvm.instructions.base.NoOperandsInstruction;
import tzy.tinyPros.jvm.rtda.heap.methodarea.Object;
import tzy.tinyPros.jvm.rtda.thread.OperandStack;

/**
 * @author TPureZY
 * @since 2023/7/26 22:16
 **/
public abstract class AbstractASTORE extends NoOperandsInstruction {

    protected int idx;
    protected Object arrObj;

    /**
     * 获取操作数栈中弹出的要store的值，该变量和获取方法需自行定义
     *
     * @param stack 操作数栈
     */
    protected abstract void acquireTargetVal(OperandStack stack);

    protected void acquireStackInfo(OperandStack stack) {
        acquireTargetVal(stack);
        this.idx = stack.popInt();
        this.arrObj = stack.popRef();
        ArrayInstructionHelper.checkNotNull(this.arrObj);
        ArrayInstructionHelper.checkIndex(this.idx, this.arrObj.arrayLength());
    }
}
